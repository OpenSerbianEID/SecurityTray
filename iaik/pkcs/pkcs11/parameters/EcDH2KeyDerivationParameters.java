package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.objects.Object;
import iaik.pkcs.pkcs11.wrapper.CK_ECDH2_DERIVE_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class EcDH2KeyDerivationParameters extends EcDH1KeyDerivationParameters {
   protected long privateDataLength_;
   protected Object privateData_;
   protected byte[] publicData2_;

   public EcDH2KeyDerivationParameters(long keyDerivationFunction, byte[] sharedData, byte[] publicData, long privateDataLength, Object privateData, byte[] publicData2) {
      super(keyDerivationFunction, sharedData, publicData);
      if (privateData == null) {
         throw new NullPointerException("Argument \"privateData\" must not be null.");
      } else if (publicData2 == null) {
         throw new NullPointerException("Argument \"publicData2\" must not be null.");
      } else {
         this.privateDataLength_ = privateDataLength;
         this.privateData_ = privateData;
         this.publicData2_ = publicData2;
      }
   }

   public java.lang.Object clone() {
      EcDH2KeyDerivationParameters clone = (EcDH2KeyDerivationParameters)super.clone();
      clone.privateData_ = (Object)this.privateData_.clone();
      clone.publicData2_ = (byte[])((byte[])this.publicData2_.clone());
      return clone;
   }

   public java.lang.Object getPKCS11ParamsObject() {
      CK_ECDH2_DERIVE_PARAMS params = new CK_ECDH2_DERIVE_PARAMS();
      params.kdf = this.keyDerivationFunction_;
      params.pSharedData = this.sharedData_;
      params.pPublicData = this.publicData_;
      params.ulPrivateDataLen = this.privateDataLength_;
      params.hPrivateData = this.privateData_.getObjectHandle();
      params.pPublicData2 = this.publicData2_;
      return params;
   }

   public Object getPrivateData() {
      return this.privateData_;
   }

   public long getPrivateDataLength() {
      return this.privateDataLength_;
   }

   public byte[] getPublicData2() {
      return this.publicData2_;
   }

   public void setPrivateData(Object privateData) {
      if (privateData == null) {
         throw new NullPointerException("Argument \"privateData\" must not be null.");
      } else {
         this.privateData_ = privateData;
      }
   }

   public void setPrivateDataLength(long privateDataLength) {
      this.privateDataLength_ = privateDataLength;
   }

   public void setPublicData2(byte[] publicData2) {
      if (publicData2 == null) {
         throw new NullPointerException("Argument \"publicData2\" must not be null.");
      } else {
         this.publicData2_ = publicData2;
      }
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
      if (otherObject instanceof EcDH2KeyDerivationParameters) {
         EcDH2KeyDerivationParameters other = (EcDH2KeyDerivationParameters)otherObject;
         equal = this == other || super.equals(other) && this.privateDataLength_ == other.privateDataLength_ && this.privateData_.equals(other.privateData_) && Functions.equals(this.publicData2_, other.publicData2_);
      }

      return equal;
   }

   public int hashCode() {
      return super.hashCode() ^ (int)this.privateDataLength_ ^ this.privateData_.hashCode() ^ Functions.hashCode(this.publicData2_);
   }
}
