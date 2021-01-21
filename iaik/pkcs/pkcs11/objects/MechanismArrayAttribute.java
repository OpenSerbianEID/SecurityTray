package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class MechanismArrayAttribute extends Attribute {
   MechanismArrayAttribute() {
   }

   public MechanismArrayAttribute(Long type) {
      super(type);
   }

   public void setMechanismAttributeArrayValue(iaik.pkcs.pkcs11.Mechanism[] value) {
      long[] values = null;
      if (value != null) {
         values = new long[value.length];

         for(int i = 0; i < value.length; ++i) {
            values[i] = value[i].getMechanismCode();
         }
      }

      this.ckAttribute_.pValue = values;
      this.present_ = true;
   }

   public iaik.pkcs.pkcs11.Mechanism[] getMechanismAttributeArrayValue() {
      iaik.pkcs.pkcs11.Mechanism[] mechanisms = null;
      if (this.ckAttribute_.pValue != null) {
         long[] values = (long[])((long[])this.ckAttribute_.pValue);
         if (values != null && values.length > 0) {
            mechanisms = new iaik.pkcs.pkcs11.Mechanism[values.length];

            for(int i = 0; i < values.length; ++i) {
               iaik.pkcs.pkcs11.Mechanism mechanism = new iaik.pkcs.pkcs11.Mechanism(values[i]);
               mechanisms[i] = mechanism;
            }
         }
      }

      return mechanisms;
   }

   protected String getValueString() {
      StringBuffer buffer = new StringBuffer(1024);
      iaik.pkcs.pkcs11.Mechanism[] allowedMechanisms = this.getMechanismAttributeArrayValue();
      if (allowedMechanisms != null && allowedMechanisms.length > 0) {
         for(int i = 0; i < allowedMechanisms.length; ++i) {
            buffer.append(Constants.NEWLINE);
            buffer.append("      ");
            buffer.append(allowedMechanisms[i].getName());
         }

         return buffer.toString();
      } else {
         return "<NULL_PTR>";
      }
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof MechanismArrayAttribute) {
         MechanismArrayAttribute other = (MechanismArrayAttribute)otherObject;
         equal = this == other || !this.present_ && !other.present_ || this.present_ && other.present_ && this.sensitive_ == other.sensitive_ && Functions.equals((long[])((long[])this.ckAttribute_.pValue), (long[])((long[])other.ckAttribute_.pValue));
      }

      return equal;
   }

   public int hashCode() {
      return this.ckAttribute_.pValue != null ? Functions.hashCode((long[])((long[])this.ckAttribute_.pValue)) : 0;
   }

   public java.lang.Object clone() {
      MechanismArrayAttribute clone = (MechanismArrayAttribute)super.clone();
      if (this.ckAttribute_.pValue != null) {
         clone.ckAttribute_.pValue = ((long[])((long[])this.ckAttribute_.pValue)).clone();
      } else {
         clone.ckAttribute_.pValue = null;
      }

      return clone;
   }
}
