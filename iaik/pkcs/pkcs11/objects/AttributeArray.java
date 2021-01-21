package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.wrapper.CK_ATTRIBUTE;
import java.util.Enumeration;

public class AttributeArray extends Attribute {
   protected GenericTemplate template_;

   AttributeArray() {
   }

   public AttributeArray(Long type) {
      super(type);
   }

   public void setAttributeArrayValue(GenericTemplate value) {
      this.template_ = value;
      int length = this.template_.attributeTable_.size();
      CK_ATTRIBUTE[] attributes = null;
      if (length > 0) {
         attributes = new CK_ATTRIBUTE[length];
         Enumeration attributeEnumeration = this.template_.attributeTable_.elements();

         for(int counter = 0; attributeEnumeration.hasMoreElements(); ++counter) {
            Attribute attribute = (Attribute)attributeEnumeration.nextElement();
            attributes[counter] = attribute.getCkAttribute();
         }
      }

      this.ckAttribute_.pValue = attributes;
      this.present_ = true;
   }

   public GenericTemplate getAttributeArrayValue() {
      if (this.template_ == null) {
         if (this.ckAttribute_.pValue != null && ((CK_ATTRIBUTE[])((CK_ATTRIBUTE[])this.ckAttribute_.pValue)).length > 0) {
            CK_ATTRIBUTE[] attributesArray = (CK_ATTRIBUTE[])((CK_ATTRIBUTE[])this.ckAttribute_.pValue);
            GenericTemplate template = new GenericTemplate();

            for(int i = 0; i < attributesArray.length; ++i) {
               Long type = new Long(attributesArray[i].type);
               Class implementation = Attribute.getAttributeClass(type);
               Attribute attribute;
               if (implementation == null) {
                  attribute = new Attribute(type);
                  attribute.setCkAttribute(attributesArray[i]);
               } else {
                  try {
                     attribute = (Attribute)implementation.newInstance();
                     attribute.setCkAttribute(attributesArray[i]);
                     attribute.setPresent(true);
                     template.addAttribute(attribute);
                  } catch (Exception var8) {
                     System.err.println("Error when trying to create a " + implementation + " instance for " + type + ": " + var8.getMessage());
                     System.err.flush();
                     System.exit(1);
                  }
               }
            }

            return template;
         } else {
            return null;
         }
      } else {
         return this.template_;
      }
   }

   protected String getValueString() {
      String valueString = "";
      if (this.template_ == null) {
         this.template_ = this.getAttributeArrayValue();
      }

      if (this.template_ == null) {
         valueString = "<NULL_PTR>";
      } else {
         String indent = "      ";
         valueString = valueString + this.template_.toString(true, true, indent);
      }

      return valueString;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof AttributeArray) {
         AttributeArray other = (AttributeArray)otherObject;
         if (this.template_ == null) {
            this.template_ = this.getAttributeArrayValue();
         }

         if (other.template_ == null) {
            other.template_ = other.getAttributeArrayValue();
         }

         equal = this == other || !this.present_ && !other.present_ || this.present_ && other.present_ && this.sensitive_ == other.sensitive_ && this.template_.equals(other.template_);
      }

      return equal;
   }

   public int hashCode() {
      if (this.template_ == null) {
         this.template_ = this.getAttributeArrayValue();
      }

      return this.template_.hashCode();
   }

   public java.lang.Object clone() {
      AttributeArray clone = (AttributeArray)super.clone();
      if (this.template_ == null) {
         this.template_ = this.getAttributeArrayValue();
      }

      if (this.template_ != null) {
         clone.template_ = (GenericTemplate)this.template_.clone();
      }

      return clone;
   }
}
