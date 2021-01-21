package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_KEY_DERIVATION_STRING_DATA;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class KeyDerivationStringDataParameters implements Parameters {
   protected byte[] data_;

   public KeyDerivationStringDataParameters(byte[] data) {
      if (data == null) {
         throw new NullPointerException("Argument \"data\" must not be null.");
      } else {
         this.data_ = data;
      }
   }

   public Object clone() {
      try {
         KeyDerivationStringDataParameters clone = (KeyDerivationStringDataParameters)super.clone();
         clone.data_ = (byte[])((byte[])this.data_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      CK_KEY_DERIVATION_STRING_DATA params = new CK_KEY_DERIVATION_STRING_DATA();
      params.pData = this.data_;
      return params;
   }

   public byte[] getData() {
      return this.data_;
   }

   public void setData(byte[] data) {
      if (data == null) {
         throw new NullPointerException("Argument \"data\" must not be null.");
      } else {
         this.data_ = data;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("String data (hex): ");
      buffer.append(Functions.toHexString(this.data_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof KeyDerivationStringDataParameters) {
         KeyDerivationStringDataParameters other = (KeyDerivationStringDataParameters)otherObject;
         equal = this == other || Functions.equals(this.data_, other.data_);
      }

      return equal;
   }

   public int hashCode() {
      return Functions.hashCode(this.data_);
   }
}
