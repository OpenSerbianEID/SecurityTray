package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

public class DomainParameters extends Storage {
   protected static DomainParameters.VendorDefinedDomainParametersBuilder vendorDomainParametersBuilder_;
   protected KeyTypeAttribute keyType_;
   protected BooleanAttribute local_;

   public DomainParameters() {
      this.objectClass_.setLongValue(Object.ObjectClass.DOMAIN_PARAMETERS);
   }

   protected DomainParameters(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.objectClass_.setLongValue(Object.ObjectClass.DOMAIN_PARAMETERS);
   }

   public static void setVendorDefinedDomainParametersBuilder(DomainParameters.VendorDefinedDomainParametersBuilder builder) {
      vendorDomainParametersBuilder_ = builder;
   }

   public static DomainParameters.VendorDefinedDomainParametersBuilder getVendorDefinedDomainParametersBuilder() {
      return vendorDomainParametersBuilder_;
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         KeyTypeAttribute keyTypeAttribute = new KeyTypeAttribute();
         getAttributeValue(session, objectHandle, keyTypeAttribute);
         Long keyType = keyTypeAttribute.getLongValue();
         Object newObject;
         if (keyTypeAttribute.isPresent() && keyType != null) {
            if (keyType.equals(Key.KeyType.DSA)) {
               newObject = DSAParams.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.DH)) {
               newObject = DHParams.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.X9_42_DH)) {
               newObject = X942DHParams.getInstance(session, objectHandle);
            } else if ((keyType & Key.KeyType.VENDOR_DEFINED) != 0L) {
               newObject = getUnknownDomainParameters(session, objectHandle);
            } else {
               newObject = getUnknownDomainParameters(session, objectHandle);
            }
         } else {
            newObject = getUnknownDomainParameters(session, objectHandle);
         }

         return newObject;
      }
   }

   protected static Object getUnknownDomainParameters(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         java.lang.Object newObject;
         if (vendorDomainParametersBuilder_ != null) {
            try {
               newObject = vendorDomainParametersBuilder_.build(session, objectHandle);
            } catch (PKCS11Exception var5) {
               newObject = new DomainParameters(session, objectHandle);
            }
         } else {
            newObject = new DomainParameters(session, objectHandle);
         }

         return (Object)newObject;
      }
   }

   protected static void putAttributesInTable(DomainParameters object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.KEY_TYPE, object.keyType_);
         object.attributeTable_.put(Attribute.LOCAL, object.local_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.keyType_ = new KeyTypeAttribute();
      this.local_ = new BooleanAttribute(Attribute.LOCAL);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      DomainParameters clone = (DomainParameters)super.clone();
      clone.keyType_ = (KeyTypeAttribute)this.keyType_.clone();
      clone.local_ = (BooleanAttribute)this.local_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof DomainParameters) {
         DomainParameters other = (DomainParameters)otherObject;
         equal = this == other || super.equals(other) && this.keyType_.equals(other.keyType_) && this.local_.equals(other.local_);
      }

      return equal;
   }

   public LongAttribute getKeyType() {
      return this.keyType_;
   }

   public int hashCode() {
      return this.keyType_.hashCode() ^ this.local_.hashCode();
   }

   public BooleanAttribute isLocal() {
      return this.local_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValue(session, this.objectHandle_, this.local_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(256);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Key Type: ");
      if (this.keyType_ != null) {
         buffer.append(this.keyType_.toString());
      } else {
         buffer.append("<unavailable>");
      }

      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Local: ");
      buffer.append(this.local_.toString());
      return buffer.toString();
   }

   public interface VendorDefinedDomainParametersBuilder {
      Object build(Session var1, long var2) throws PKCS11Exception;
   }
}
