package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_SSL3_KEY_MAT_OUT;
import iaik.pkcs.pkcs11.wrapper.CK_SSL3_KEY_MAT_PARAMS;
import iaik.pkcs.pkcs11.wrapper.CK_SSL3_RANDOM_DATA;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class SSL3KeyMaterialParameters implements Parameters {
   protected long macSizeInBits_;
   protected long keySizeInBits_;
   protected long initializationVectorSizeInBits_;
   protected boolean export_;
   protected SSL3RandomDataParameters randomInfo_;
   protected SSL3KeyMaterialOutParameters returnedKeyMaterial_;

   public SSL3KeyMaterialParameters(long macSizeInBits, long keySizeInBits, long initializationVectorSizeInBits, boolean export, SSL3RandomDataParameters randomInfo, SSL3KeyMaterialOutParameters returnedKeyMaterial) {
      if (randomInfo == null) {
         throw new NullPointerException("Argument \"randomInfo\" must not be null.");
      } else if (returnedKeyMaterial == null) {
         throw new NullPointerException("Argument \"returnedKeyMaterial\" must not be null.");
      } else {
         this.macSizeInBits_ = macSizeInBits;
         this.keySizeInBits_ = keySizeInBits;
         this.initializationVectorSizeInBits_ = initializationVectorSizeInBits;
         this.export_ = export;
         this.randomInfo_ = randomInfo;
         this.returnedKeyMaterial_ = returnedKeyMaterial;
      }
   }

   public Object clone() {
      try {
         SSL3KeyMaterialParameters clone = (SSL3KeyMaterialParameters)super.clone();
         clone.randomInfo_ = (SSL3RandomDataParameters)this.randomInfo_.clone();
         clone.returnedKeyMaterial_ = (SSL3KeyMaterialOutParameters)this.returnedKeyMaterial_.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      CK_SSL3_KEY_MAT_PARAMS params = new CK_SSL3_KEY_MAT_PARAMS();
      params.ulMacSizeInBits = this.macSizeInBits_;
      params.ulKeySizeInBits = this.keySizeInBits_;
      params.ulIVSizeInBits = this.initializationVectorSizeInBits_;
      params.bIsExport = this.export_;
      params.RandomInfo = (CK_SSL3_RANDOM_DATA)this.randomInfo_.getPKCS11ParamsObject();
      params.pReturnedKeyMaterial = (CK_SSL3_KEY_MAT_OUT)this.returnedKeyMaterial_.getPKCS11ParamsObject();
      return params;
   }

   public long getMacSizeInBits() {
      return this.macSizeInBits_;
   }

   public long getKeySizeInBits() {
      return this.keySizeInBits_;
   }

   public long getInitializationVectorSizeInBits() {
      return this.initializationVectorSizeInBits_;
   }

   public boolean isExport() {
      return this.export_;
   }

   public SSL3RandomDataParameters getRandomInfo() {
      return this.randomInfo_;
   }

   public SSL3KeyMaterialOutParameters getReturnedKeyMaterial() {
      return this.returnedKeyMaterial_;
   }

   public void setMacSizeInBits(long macSizeInBits) {
      this.macSizeInBits_ = macSizeInBits;
   }

   public void setKeySizeInBits(long keySizeInBits) {
      this.keySizeInBits_ = keySizeInBits;
   }

   public void setInitializationVectorSizeInBits(long initializationVectorSizeInBits) {
      this.initializationVectorSizeInBits_ = initializationVectorSizeInBits;
   }

   public void isExport(boolean export) {
      this.export_ = export;
   }

   public void setRandomInfo(SSL3RandomDataParameters randomInfo) {
      if (randomInfo == null) {
         throw new NullPointerException("Argument \"randomInfo\" must not be null.");
      } else {
         this.randomInfo_ = randomInfo;
      }
   }

   public void setReturnedKeyMaterial(SSL3KeyMaterialOutParameters returnedKeyMaterial) {
      if (returnedKeyMaterial == null) {
         throw new NullPointerException("Argument \"returnedKeyMaterial\" must not be null.");
      } else {
         this.returnedKeyMaterial_ = returnedKeyMaterial;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("MAC Size in Bits (dec): ");
      buffer.append(this.macSizeInBits_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Key Size in Bits (dec): ");
      buffer.append(this.keySizeInBits_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Initialization Vector Size in Bits (dec): ");
      buffer.append(this.initializationVectorSizeInBits_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("For Export Version: ");
      buffer.append(this.export_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Client's and Server'S Random Information (hex): ");
      buffer.append(Constants.NEWLINE);
      buffer.append(this.randomInfo_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Handles of the generated Keys and IVs: ");
      buffer.append(this.returnedKeyMaterial_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof SSL3KeyMaterialParameters) {
         SSL3KeyMaterialParameters other = (SSL3KeyMaterialParameters)otherObject;
         equal = this == other || this.macSizeInBits_ == other.macSizeInBits_ && this.keySizeInBits_ == other.keySizeInBits_ && this.initializationVectorSizeInBits_ == other.initializationVectorSizeInBits_ && this.export_ == other.export_ && this.randomInfo_.equals(other.randomInfo_) && this.returnedKeyMaterial_.equals(other.returnedKeyMaterial_);
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.macSizeInBits_ ^ (int)this.keySizeInBits_ ^ (int)this.initializationVectorSizeInBits_ ^ this.randomInfo_.hashCode() ^ this.returnedKeyMaterial_.hashCode();
   }
}
