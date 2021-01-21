package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.wrapper.Functions;

public class CharArrayAttribute extends Attribute {
   CharArrayAttribute() {
   }

   public CharArrayAttribute(Long type) {
      super(type);
   }

   public void setCharArrayValue(char[] value) {
      this.ckAttribute_.pValue = value;
      this.present_ = true;
   }

   public char[] getCharArrayValue() {
      return (char[])((char[])this.ckAttribute_.pValue);
   }

   protected String getValueString() {
      String valueString;
      if (this.ckAttribute_ != null && this.ckAttribute_.pValue != null) {
         valueString = new String((char[])((char[])this.ckAttribute_.pValue));
      } else {
         valueString = "<NULL_PTR>";
      }

      return valueString;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof CharArrayAttribute) {
         CharArrayAttribute other = (CharArrayAttribute)otherObject;
         equal = this == other || !this.present_ && !other.present_ || this.present_ && other.present_ && this.sensitive_ == other.sensitive_ && Functions.equals((char[])((char[])this.ckAttribute_.pValue), (char[])((char[])other.ckAttribute_.pValue));
      }

      return equal;
   }

   public int hashCode() {
      return this.ckAttribute_.pValue != null ? Functions.hashCode((char[])((char[])this.ckAttribute_.pValue)) : 0;
   }
}
