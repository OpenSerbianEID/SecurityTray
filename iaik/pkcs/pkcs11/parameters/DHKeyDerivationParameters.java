package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public abstract class DHKeyDerivationParameters implements Parameters {
   protected long keyDerivationFunction_;
   protected byte[] publicData_;

   protected DHKeyDerivationParameters(long keyDerivationFunction, byte[] publicData) {
      if (keyDerivationFunction != 1L && keyDerivationFunction != 2L && keyDerivationFunction != 3L && keyDerivationFunction != 4L) {
         throw new IllegalArgumentException("Illegal value for argument\"keyDerivationFunction\": " + Functions.toHexString(keyDerivationFunction));
      } else if (publicData == null) {
         throw new NullPointerException("Argument \"publicData\" must not be null.");
      } else {
         this.keyDerivationFunction_ = keyDerivationFunction;
         this.publicData_ = publicData;
      }
   }

   public Object clone() {
      try {
         DHKeyDerivationParameters clone = (DHKeyDerivationParameters)super.clone();
         clone.publicData_ = (byte[])((byte[])this.publicData_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public long getKeyDerivationFunction() {
      return this.keyDerivationFunction_;
   }

   public byte[] getPublicData() {
      return this.publicData_;
   }

   public void setKeyDerivationFunction(long keyDerivationFunction) {
      if (keyDerivationFunction != 1L && keyDerivationFunction != 2L && keyDerivationFunction != 3L && keyDerivationFunction != 4L) {
         throw new IllegalArgumentException("Illegal value for argument\"keyDerivationFunction\": " + Functions.toHexString(keyDerivationFunction));
      } else {
         this.keyDerivationFunction_ = keyDerivationFunction;
      }
   }

   public void setPublicData(byte[] publicData) {
      if (publicData == null) {
         throw new NullPointerException("Argument \"publicData\" must not be null.");
      } else {
         this.publicData_ = publicData;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Key Derivation Function: ");
      if (this.keyDerivationFunction_ == 1L) {
         buffer.append("NULL");
      } else if (this.keyDerivationFunction_ == 2L) {
         buffer.append("SHA1_KDF");
      } else if (this.keyDerivationFunction_ == 3L) {
         buffer.append("SHA1_KDF_ASN1");
      } else if (this.keyDerivationFunction_ == 4L) {
         buffer.append("SHA1_KDF_CONCATENATE");
      } else {
         buffer.append("<unknown>");
      }

      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Public Data: ");
      buffer.append(Functions.toHexString(this.publicData_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof DHKeyDerivationParameters) {
         DHKeyDerivationParameters other = (DHKeyDerivationParameters)otherObject;
         equal = this == other || this.keyDerivationFunction_ == other.keyDerivationFunction_ && Functions.equals(this.publicData_, other.publicData_);
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.keyDerivationFunction_ ^ Functions.hashCode(this.publicData_);
   }

   public interface KeyDerivationFunctionType {
      long NULL = 1L;
      long SHA1_KDF = 2L;
      long SHA1_KDF_ASN1 = 3L;
      long SHA1_KDF_CONCATENATE = 4L;
   }
}
