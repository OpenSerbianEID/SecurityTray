package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.wrapper.CK_ECDH1_DERIVE_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class EcDH1KeyDerivationParameters extends DHKeyDerivationParameters {
   protected byte[] sharedData_;

   public EcDH1KeyDerivationParameters(long keyDerivationFunction, byte[] sharedData, byte[] publicData) {
      super(keyDerivationFunction, publicData);
      this.sharedData_ = sharedData;
   }

   public Object clone() {
      EcDH1KeyDerivationParameters clone = (EcDH1KeyDerivationParameters)super.clone();
      clone.sharedData_ = (byte[])((byte[])this.sharedData_.clone());
      return clone;
   }

   public Object getPKCS11ParamsObject() {
      CK_ECDH1_DERIVE_PARAMS params = new CK_ECDH1_DERIVE_PARAMS();
      params.kdf = this.keyDerivationFunction_;
      params.pSharedData = this.sharedData_;
      params.pPublicData = this.publicData_;
      return params;
   }

   public byte[] getSharedData() {
      return this.sharedData_;
   }

   public void setSharedData(byte[] sharedData) {
      this.sharedData_ = sharedData;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Shared Data: ");
      buffer.append(Functions.toHexString(this.sharedData_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof EcDH1KeyDerivationParameters) {
         EcDH1KeyDerivationParameters other = (EcDH1KeyDerivationParameters)otherObject;
         equal = this == other || super.equals(other) && Functions.equals(this.sharedData_, other.sharedData_);
      }

      return equal;
   }

   public int hashCode() {
      return super.hashCode() ^ Functions.hashCode(this.sharedData_);
   }
}
