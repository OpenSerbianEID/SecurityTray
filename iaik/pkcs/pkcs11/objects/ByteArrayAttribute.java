package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.wrapper.Functions;

public class ByteArrayAttribute extends Attribute {
   ByteArrayAttribute() {
   }

   public ByteArrayAttribute(Long type) {
      super(type);
   }

   public void setByteArrayValue(byte[] value) {
      this.ckAttribute_.pValue = value;
      this.present_ = true;
   }

   public byte[] getByteArrayValue() {
      return (byte[])((byte[])this.ckAttribute_.pValue);
   }

   protected String getValueString() {
      String valueString;
      if (this.ckAttribute_ != null && this.ckAttribute_.pValue != null) {
         valueString = Functions.toHexString((byte[])((byte[])this.ckAttribute_.pValue));
      } else {
         valueString = "<NULL_PTR>";
      }

      return valueString;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof ByteArrayAttribute) {
         ByteArrayAttribute other = (ByteArrayAttribute)otherObject;
         equal = this == other || !this.present_ && !other.present_ || this.present_ && other.present_ && this.sensitive_ == other.sensitive_ && Functions.equals((byte[])((byte[])this.ckAttribute_.pValue), (byte[])((byte[])other.ckAttribute_.pValue));
      }

      return equal;
   }

   public int hashCode() {
      return this.ckAttribute_.pValue != null ? Functions.hashCode((byte[])((byte[])this.ckAttribute_.pValue)) : 0;
   }
}
