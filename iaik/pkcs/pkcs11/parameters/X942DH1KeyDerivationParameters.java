package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.wrapper.CK_X9_42_DH1_DERIVE_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class X942DH1KeyDerivationParameters extends DHKeyDerivationParameters {
   protected byte[] otherInfo_;

   public X942DH1KeyDerivationParameters(long keyDerivationFunction, byte[] otherInfo, byte[] publicData) {
      super(keyDerivationFunction, publicData);
      this.otherInfo_ = otherInfo;
   }

   public Object clone() {
      X942DH1KeyDerivationParameters clone = (X942DH1KeyDerivationParameters)super.clone();
      clone.otherInfo_ = (byte[])((byte[])this.otherInfo_.clone());
      return clone;
   }

   public Object getPKCS11ParamsObject() {
      CK_X9_42_DH1_DERIVE_PARAMS params = new CK_X9_42_DH1_DERIVE_PARAMS();
      params.kdf = this.keyDerivationFunction_;
      params.pOtherInfo = this.otherInfo_;
      params.pPublicData = this.publicData_;
      return params;
   }

   public byte[] getOtherInfo() {
      return this.otherInfo_;
   }

   public void setOtherInfo(byte[] otherInfo) {
      this.otherInfo_ = otherInfo;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Other Info: ");
      buffer.append(Functions.toHexString(this.otherInfo_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof X942DH1KeyDerivationParameters) {
         X942DH1KeyDerivationParameters other = (X942DH1KeyDerivationParameters)otherObject;
         equal = this == other || super.equals(other) && Functions.equals(this.otherInfo_, other.otherInfo_);
      }

      return equal;
   }

   public int hashCode() {
      return super.hashCode() ^ Functions.hashCode(this.otherInfo_);
   }
}
