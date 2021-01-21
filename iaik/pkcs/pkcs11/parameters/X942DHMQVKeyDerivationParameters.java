package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.objects.Object;
import iaik.pkcs.pkcs11.wrapper.CK_X9_42_DHMQV_DERIVE_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class X942DHMQVKeyDerivationParameters extends X942DH2KeyDerivationParameters {
   private Object publicKey_;

   public X942DHMQVKeyDerivationParameters(long keyDerivationFunction, byte[] sharedData, byte[] publicData, long privateDataLength, Object privateData, byte[] publicData2, Object publicKey) {
      super(keyDerivationFunction, sharedData, publicData, privateDataLength, privateData, publicData2);
      this.publicKey_ = publicKey;
   }

   public java.lang.Object clone() {
      X942DHMQVKeyDerivationParameters clone = (X942DHMQVKeyDerivationParameters)super.clone();
      clone.publicKey_ = (Object)this.publicKey_.clone();
      return clone;
   }

   public java.lang.Object getPKCS11ParamsObject() {
      CK_X9_42_DHMQV_DERIVE_PARAMS params = new CK_X9_42_DHMQV_DERIVE_PARAMS();
      params.kdf = this.keyDerivationFunction_;
      params.pOtherInfo = this.otherInfo_;
      params.pPublicData = this.publicData_;
      params.ulPrivateDataLen = this.privateDataLength_;
      params.hPrivateData = this.privateData_.getObjectHandle();
      params.pPublicData2 = this.publicData2_;
      params.hPublicKey = this.publicKey_.getObjectHandle();
      return params;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Private Data Length (dec): ");
      buffer.append(this.privateDataLength_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Private Data: ");
      buffer.append(this.privateData_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Public Data 2: ");
      buffer.append(Functions.toHexString(this.publicData2_));
      return buffer.toString();
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof X942DHMQVKeyDerivationParameters) {
         X942DHMQVKeyDerivationParameters other = (X942DHMQVKeyDerivationParameters)otherObject;
         equal = this == other || super.equals(other) && this.publicKey_.equals(other.publicKey_);
      }

      return equal;
   }

   public int hashCode() {
      return super.hashCode() ^ this.publicKey_.hashCode();
   }
}
