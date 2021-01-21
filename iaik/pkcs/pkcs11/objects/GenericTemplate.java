package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import java.util.Enumeration;

public class GenericTemplate extends Object {
   public GenericTemplate() {
      this.attributeTable_.clear();
   }

   public void addAttribute(Attribute attribute) {
      if (attribute == null) {
         throw new NullPointerException("Argument \"attribute\" must not be null.");
      } else {
         this.attributeTable_.put(attribute.getType(), attribute);
      }
   }

   public void addAllAttributes(Object object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         Enumeration newAttributeKeysEnumeration = object.attributeTable_.keys();

         while(newAttributeKeysEnumeration.hasMoreElements()) {
            java.lang.Object newKey = newAttributeKeysEnumeration.nextElement();
            this.attributeTable_.put(newKey, object.attributeTable_.get(newKey));
         }

      }
   }

   public void addAllPresentAttributes(Object object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         Enumeration attributeEnumaeration = object.attributeTable_.elements();

         while(attributeEnumaeration.hasMoreElements()) {
            Attribute attribute = (Attribute)attributeEnumaeration.nextElement();
            if (attribute.isPresent()) {
               this.attributeTable_.put(attribute.getType(), attribute);
            }
         }

      }
   }

   public java.lang.Object clone() {
      GenericTemplate clone = (GenericTemplate)super.clone();
      clone.attributeTable_.clear();
      Enumeration attributesEnumeration = this.attributeTable_.elements();

      while(attributesEnumeration.hasMoreElements()) {
         Attribute attribute = (Attribute)attributesEnumeration.nextElement();
         Attribute clonedAttribute = (Attribute)attribute.clone();
         clone.attributeTable_.put(clonedAttribute.getType(), clonedAttribute);
      }

      return clone;
   }

   public boolean containsAttribute(Attribute attribute) {
      if (attribute == null) {
         throw new NullPointerException("Argument \"attribute\" must not be null.");
      } else {
         return this.attributeTable_.containsKey(attribute.getType());
      }
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof GenericTemplate) {
         GenericTemplate other = (GenericTemplate)otherObject;
         equal = this == other || this.attributeTable_.equals(other.attributeTable_);
      }

      return equal;
   }

   public int hashCode() {
      return this.attributeTable_.hashCode();
   }

   public void readAttributes(Session session) throws TokenException {
      if (this.objectHandle_ == -1L) {
         throw new TokenException("Object handle is not set to an valid value. Use setObjectHandle(long) to set.");
      } else {
         super.readAttributes(session);
         Enumeration attributeEnumeration = this.attributeTable_.elements();

         while(attributeEnumeration.hasMoreElements()) {
            Attribute attribute = (Attribute)attributeEnumeration.nextElement();
            Object.getAttributeValue(session, this.objectHandle_, attribute);
         }

      }
   }

   public Attribute removeAttribute(Attribute attribute) {
      if (attribute == null) {
         throw new NullPointerException("Argument \"attribute\" must not be null.");
      } else {
         return (Attribute)this.attributeTable_.remove(attribute.getType());
      }
   }

   public void removeAllAttributes(Object object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         Enumeration keysToRemove = object.attributeTable_.keys();

         while(keysToRemove.hasMoreElements()) {
            this.attributeTable_.remove(keysToRemove.nextElement());
         }

      }
   }

   public void removeAllPresentAttributes(Object object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         Enumeration keysToRemove = object.attributeTable_.keys();

         while(keysToRemove.hasMoreElements()) {
            Attribute attribute = (Attribute)object.attributeTable_.get(keysToRemove.nextElement());
            if (attribute.isPresent()) {
               this.attributeTable_.remove(attribute);
            }
         }

      }
   }

   protected void setAllPresentFlags(boolean present) {
      Enumeration attributesEnumeration = this.attributeTable_.elements();

      while(attributesEnumeration.hasMoreElements()) {
         Attribute attribute = (Attribute)attributesEnumeration.nextElement();
         attribute.setPresent(present);
      }

   }

   public String toString() {
      return this.toString(false, true, "  ");
   }

   public String toString(boolean newline, boolean withName, String indent) {
      StringBuffer buffer = new StringBuffer(1024);
      Enumeration attributesEnumeration = this.attributeTable_.elements();
      boolean firstAttribute = !newline;

      while(attributesEnumeration.hasMoreElements()) {
         Attribute attribute = (Attribute)attributesEnumeration.nextElement();
         if (attribute.isPresent()) {
            if (!firstAttribute) {
               buffer.append(Constants.NEWLINE);
            }

            buffer.append(indent);
            buffer.append(attribute.toString(withName));
            firstAttribute = false;
         }
      }

      return buffer.toString();
   }
}
