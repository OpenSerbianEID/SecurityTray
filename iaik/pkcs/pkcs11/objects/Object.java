package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.Util;
import iaik.pkcs.pkcs11.wrapper.CK_ATTRIBUTE;
import iaik.pkcs.pkcs11.wrapper.PKCS11;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Object implements Cloneable {
   protected static Object.VendorDefinedObjectBuilder vendorObjectBuilder_;
   protected static Hashtable objectClassNames_;
   protected Hashtable attributeTable_ = new Hashtable(32);
   protected ObjectClassAttribute objectClass_;
   protected long objectHandle_ = -1L;

   public Object() {
      this.allocateAttributes();
   }

   protected Object(Session session, long objectHandle) throws TokenException {
      this.allocateAttributes();
      this.objectHandle_ = objectHandle;
      this.readAttributes(session);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         ObjectClassAttribute objectClassAttribute = new ObjectClassAttribute();
         getAttributeValue(session, objectHandle, objectClassAttribute);
         Long objectClass = objectClassAttribute.getLongValue();
         Object newObject;
         if (objectClassAttribute.isPresent() && objectClass != null) {
            if (objectClass.equals(Object.ObjectClass.PRIVATE_KEY)) {
               newObject = PrivateKey.getInstance(session, objectHandle);
            } else if (objectClass.equals(Object.ObjectClass.PUBLIC_KEY)) {
               newObject = PublicKey.getInstance(session, objectHandle);
            } else if (objectClass.equals(Object.ObjectClass.CERTIFICATE)) {
               newObject = Certificate.getInstance(session, objectHandle);
            } else if (objectClass.equals(Object.ObjectClass.SECRET_KEY)) {
               newObject = SecretKey.getInstance(session, objectHandle);
            } else if (objectClass.equals(Object.ObjectClass.DATA)) {
               newObject = Data.getInstance(session, objectHandle);
            } else if (objectClass.equals(Object.ObjectClass.DOMAIN_PARAMETERS)) {
               newObject = DomainParameters.getInstance(session, objectHandle);
            } else if (objectClass.equals(Object.ObjectClass.MECHANISM)) {
               newObject = Mechanism.getInstance(session, objectHandle);
            } else if (objectClass.equals(Object.ObjectClass.HW_FEATURE)) {
               newObject = HardwareFeature.getInstance(session, objectHandle);
            } else if ((objectClass & Object.ObjectClass.VENDOR_DEFINED) != 0L) {
               newObject = getUnknownObject(session, objectHandle);
            } else {
               newObject = getUnknownObject(session, objectHandle);
            }
         } else {
            newObject = getUnknownObject(session, objectHandle);
         }

         return newObject;
      }
   }

   protected static Object getUnknownObject(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         Object newObject;
         if (vendorObjectBuilder_ != null) {
            try {
               newObject = vendorObjectBuilder_.build(session, objectHandle);
            } catch (PKCS11Exception var5) {
               newObject = new Object(session, objectHandle);
            }
         } else {
            newObject = new Object(session, objectHandle);
         }

         return newObject;
      }
   }

   public static void setVendorDefinedObjectBuilder(Object.VendorDefinedObjectBuilder builder) {
      vendorObjectBuilder_ = builder;
   }

   public static String getObjectClassName(Long objectClass) {
      if (objectClass == null) {
         throw new NullPointerException("Argument \"objectClass\" must not be null.");
      } else {
         String objectClassName;
         if ((objectClass & 2147483648L) != 0L) {
            objectClassName = "Vendor Defined";
         } else {
            if (objectClassNames_ == null) {
               Hashtable objectClassNames = new Hashtable(7);
               objectClassNames.put(Object.ObjectClass.DATA, "Data");
               objectClassNames.put(Object.ObjectClass.CERTIFICATE, "Certificate");
               objectClassNames.put(Object.ObjectClass.PUBLIC_KEY, "Public Key");
               objectClassNames.put(Object.ObjectClass.PRIVATE_KEY, "Private Key");
               objectClassNames.put(Object.ObjectClass.SECRET_KEY, "Secret Key");
               objectClassNames.put(Object.ObjectClass.HW_FEATURE, "Hardware Feature");
               objectClassNames.put(Object.ObjectClass.DOMAIN_PARAMETERS, "Domain Parameters");
               objectClassNames_ = objectClassNames;
            }

            objectClassName = (String)objectClassNames_.get(objectClass);
            if (objectClassName == null) {
               objectClassName = "<unknown>";
            }
         }

         return objectClassName;
      }
   }

   public static Object.VendorDefinedObjectBuilder getVendorDefinedObjectBuilder() {
      return vendorObjectBuilder_;
   }

   protected static void putAttributesInTable(Object object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.CLASS, object.objectClass_);
      }
   }

   protected void allocateAttributes() {
      this.objectClass_ = new ObjectClassAttribute();
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      try {
         Object clone = (Object)super.clone();
         clone.objectClass_ = (ObjectClassAttribute)this.objectClass_.clone();
         clone.attributeTable_ = new Hashtable(32);
         putAttributesInTable(clone);
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Object) {
         Object other = (Object)otherObject;
         equal = this == other || this.objectHandle_ == other.objectHandle_ && this.objectClass_.equals(other.objectClass_);
      }

      return equal;
   }

   public Hashtable getAttributeTable() {
      return (Hashtable)this.attributeTable_.clone();
   }

   public long getObjectHandle() {
      return this.objectHandle_;
   }

   public void setObjectHandle(long objectHandle) {
      this.objectHandle_ = objectHandle;
   }

   public LongAttribute getObjectClass() {
      return this.objectClass_;
   }

   public Vector getSetAttributes() {
      Vector attributeCollection = new Vector(this.attributeTable_.size());
      Enumeration attributeEnumeration = this.attributeTable_.elements();

      while(attributeEnumeration.hasMoreElements()) {
         Attribute attribute = (Attribute)attributeEnumeration.nextElement();
         if (attribute.isPresent()) {
            CK_ATTRIBUTE ckAttribute = attribute.getCkAttribute();
            attributeCollection.addElement(ckAttribute);
         }
      }

      return attributeCollection;
   }

   public int hashCode() {
      return this.objectClass_.hashCode() ^ (int)this.objectHandle_;
   }

   public void readAttributes(Session session) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(32);
      buffer.append("  ");
      buffer.append("Object Class: ");
      if (this.objectClass_ != null) {
         buffer.append(this.objectClass_.toString());
      } else {
         buffer.append("<unavailable>");
      }

      return buffer.toString();
   }

   public static CK_ATTRIBUTE[] getSetAttributes(Object object) throws PKCS11Exception {
      Vector setAttributes = object != null ? object.getSetAttributes() : null;
      CK_ATTRIBUTE[] ckAttributes = setAttributes != null ? Util.convertAttributesVectorToArray(setAttributes) : null;
      return ckAttributes;
   }

   protected static void getAttributeValue(Session session, long objectHandle, Attribute attribute) throws PKCS11Exception {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         PKCS11 pkcs11Module = session.getModule().getPKCS11Module();
         long sessionHandle = session.getSessionHandle();
         long attributeCode = attribute.getCkAttribute().type;

         try {
            CK_ATTRIBUTE[] attributeTemplateList = new CK_ATTRIBUTE[]{new CK_ATTRIBUTE()};
            attributeTemplateList[0].type = attributeCode;
            pkcs11Module.C_GetAttributeValue(sessionHandle, objectHandle, attributeTemplateList);
            attribute.setCkAttribute(attributeTemplateList[0]);
            attribute.setPresent(true);
            attribute.setSensitive(false);
         } catch (PKCS11Exception var10) {
            if (var10.getErrorCode() == 18L) {
               attribute.setPresent(false);
            } else {
               if (var10.getErrorCode() != 17L) {
                  throw var10;
               }

               attribute.setPresent(true);
               attribute.setSensitive(true);
            }
         }

      }
   }

   protected static void getAttributeValues(Session session, long objectHandle, Attribute[] attributes) throws PKCS11Exception {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else if (attributes == null) {
         throw new NullPointerException("Argument \"attributes\" must not be null.");
      } else {
         PKCS11 pkcs11Module = session.getModule().getPKCS11Module();
         long sessionHandle = session.getSessionHandle();

         int i;
         try {
            CK_ATTRIBUTE[] attributeTemplateList = new CK_ATTRIBUTE[attributes.length];

            for(i = 0; i < attributes.length; ++i) {
               CK_ATTRIBUTE attribute = new CK_ATTRIBUTE();
               attribute.type = attributes[i].getCkAttribute().type;
               attributeTemplateList[i] = attribute;
            }

            pkcs11Module.C_GetAttributeValue(sessionHandle, objectHandle, attributeTemplateList);

            for(i = 0; i < attributes.length; ++i) {
               attributes[i].setCkAttribute(attributeTemplateList[i]);
               attributes[i].setPresent(true);
               attributes[i].setSensitive(false);
            }
         } catch (PKCS11Exception var10) {
            for(i = 0; i < attributes.length; ++i) {
               getAttributeValue(session, objectHandle, attributes[i]);
            }
         } catch (OutOfMemoryError var11) {
         }

      }
   }

   public interface VendorDefinedObjectBuilder {
      Object build(Session var1, long var2) throws PKCS11Exception;
   }

   public interface ObjectClass {
      Long DATA = new Long(0L);
      Long CERTIFICATE = new Long(1L);
      Long PUBLIC_KEY = new Long(2L);
      Long PRIVATE_KEY = new Long(3L);
      Long SECRET_KEY = new Long(4L);
      Long HW_FEATURE = new Long(5L);
      Long DOMAIN_PARAMETERS = new Long(6L);
      Long MECHANISM = new Long(7L);
      Long VENDOR_DEFINED = new Long(2147483648L);
   }
}
