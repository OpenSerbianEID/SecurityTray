package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.wrapper.CK_ECMQV_DERIVE_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class EcMQVKeyDerivationParameters extends DHKeyDerivationParameters {
   protected byte[] sharedData_;
   protected long ulPrivateDataLen_;
   protected long hPrivateData_;
   protected byte[] pPublicData2_;
   protected long publicKey_;

   public EcMQVKeyDerivationParameters(long keyDerivationFunction, byte[] sharedData, byte[] publicData, long ulPrivateDataLen, long hPrivateData, byte[] pPublicData2, long publicKey) {
      super(keyDerivationFunction, publicData);
      this.sharedData_ = sharedData;
      this.ulPrivateDataLen_ = ulPrivateDataLen;
      this.hPrivateData_ = hPrivateData;
      this.pPublicData2_ = pPublicData2;
      this.publicKey_ = publicKey;
   }

   public Object clone() {
      EcMQVKeyDerivationParameters clone = (EcMQVKeyDerivationParameters)super.clone();
      clone.sharedData_ = (byte[])((byte[])this.sharedData_.clone());
      clone.ulPrivateDataLen_ = this.ulPrivateDataLen_;
      clone.hPrivateData_ = this.hPrivateData_;
      clone.pPublicData2_ = (byte[])((byte[])this.pPublicData2_.clone());
      clone.publicKey_ = this.publicKey_;
      return clone;
   }

   public Object getPKCS11ParamsObject() {
      CK_ECMQV_DERIVE_PARAMS params = new CK_ECMQV_DERIVE_PARAMS();
      params.kdf = this.keyDerivationFunction_;
      params.pSharedData = this.sharedData_;
      params.pPublicData = this.publicData_;
      params.ulPrivateDataLen = this.ulPrivateDataLen_;
      params.hPrivateData = this.hPrivateData_;
      params.pPublicData2 = this.pPublicData2_;
      params.publicKey = this.publicKey_;
      return params;
   }

   public byte[] getSharedData() {
      return this.sharedData_;
   }

   public void setSharedData(byte[] sharedData) {
      this.sharedData_ = sharedData;
   }

   public long getUlPrivateDataLen() {
      return this.ulPrivateDataLen_;
   }

   public void setUlPrivateDataLen(long ulPrivateDataLen) {
      this.ulPrivateDataLen_ = ulPrivateDataLen;
   }

   public long gethPrivateData() {
      return this.hPrivateData_;
   }

   public void sethPrivateData_(long hPrivateData) {
      this.hPrivateData_ = hPrivateData;
   }

   public byte[] getpPublicData2() {
      return this.pPublicData2_;
   }

   public void setpPublicData2_(byte[] pPublicData2) {
      this.pPublicData2_ = pPublicData2;
   }

   public long getPublicKey() {
      return this.publicKey_;
   }

   public void setPublicKey(long publicKey) {
      this.publicKey_ = publicKey;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Shared Data: ");
      buffer.append(Functions.toHexString(this.sharedData_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Private Data Handle: ");
      buffer.append(this.hPrivateData_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Public Data 2: ");
      buffer.append(Functions.toHexString(this.pPublicData2_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("public key handle: ");
      buffer.append(this.publicKey_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof EcMQVKeyDerivationParameters) {
         EcMQVKeyDerivationParameters other = (EcMQVKeyDerivationParameters)otherObject;
         equal = this == other || super.equals(other) && Functions.equals(this.sharedData_, other.sharedData_) && this.hPrivateData_ == other.hPrivateData_ && Functions.equals(this.pPublicData2_, other.pPublicData2_) && this.publicKey_ == other.publicKey_;
      }

      return equal;
   }

   public int hashCode() {
      return (int)((long)(super.hashCode() ^ Functions.hashCode(this.sharedData_)) ^ this.hPrivateData_ ^ (long)Functions.hashCode(this.pPublicData2_) ^ this.publicKey_);
   }
}
