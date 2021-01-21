package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.CK_MECHANISM_INFO;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class MechanismInfo implements Cloneable {
   protected long minKeySize_;
   protected long maxKeySize_;
   protected long flags_;

   public MechanismInfo() {
   }

   public MechanismInfo(CK_MECHANISM_INFO ckMechanismInfo) {
      if (ckMechanismInfo == null) {
         throw new NullPointerException("Argument \"ckMechanismInfo\" must not be null.");
      } else {
         this.minKeySize_ = ckMechanismInfo.ulMinKeySize;
         this.maxKeySize_ = ckMechanismInfo.ulMaxKeySize;
         this.flags_ = ckMechanismInfo.flags;
      }
   }

   public Object clone() {
      try {
         MechanismInfo clone = (MechanismInfo)super.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public boolean equals(Object otherObject) {
      boolean euqal = false;
      if (otherObject instanceof MechanismInfo) {
         MechanismInfo other = (MechanismInfo)otherObject;
         euqal = this == other || this.minKeySize_ == other.minKeySize_ && this.maxKeySize_ == other.maxKeySize_ && this.flags_ == other.flags_;
      }

      return euqal;
   }

   public int hashCode() {
      return (int)(this.minKeySize_ ^ this.maxKeySize_ ^ this.flags_);
   }

   public long getMinKeySize() {
      return this.minKeySize_;
   }

   public long getMaxKeySize() {
      return this.maxKeySize_;
   }

   public boolean isHw() {
      return (this.flags_ & 1L) != 0L;
   }

   public boolean isEncrypt() {
      return (this.flags_ & 256L) != 0L;
   }

   public boolean isDecrypt() {
      return (this.flags_ & 512L) != 0L;
   }

   public boolean isDigest() {
      return (this.flags_ & 1024L) != 0L;
   }

   public boolean isSign() {
      return (this.flags_ & 2048L) != 0L;
   }

   public boolean isSignRecover() {
      return (this.flags_ & 4096L) != 0L;
   }

   public boolean isVerify() {
      return (this.flags_ & 8192L) != 0L;
   }

   public boolean isVerifyRecover() {
      return (this.flags_ & 16384L) != 0L;
   }

   public boolean isGenerate() {
      return (this.flags_ & 32768L) != 0L;
   }

   public boolean isGenerateKeyPair() {
      return (this.flags_ & 65536L) != 0L;
   }

   public boolean isWrap() {
      return (this.flags_ & 131072L) != 0L;
   }

   public boolean isUnwrap() {
      return (this.flags_ & 262144L) != 0L;
   }

   public boolean isDerive() {
      return (this.flags_ & 524288L) != 0L;
   }

   public boolean isEcFp() {
      return (this.flags_ & 1048576L) != 0L;
   }

   public boolean isEcF2m() {
      return (this.flags_ & 2097152L) != 0L;
   }

   public boolean isEcEcParameters() {
      return (this.flags_ & 4194304L) != 0L;
   }

   public boolean isEcNamedCurve() {
      return (this.flags_ & 8388608L) != 0L;
   }

   public boolean isEcUncompress() {
      return (this.flags_ & 16777216L) != 0L;
   }

   public boolean isEcCompress() {
      return (this.flags_ & 33554432L) != 0L;
   }

   public boolean isExtension() {
      return (this.flags_ & 2147483648L) != 0L;
   }

   public MechanismInfo or(MechanismInfo other) {
      MechanismInfo result;
      if (other != null) {
         result = new MechanismInfo();
         result.flags_ = this.flags_ | other.flags_;
         result.minKeySize_ = this.minKeySize_ < other.minKeySize_ ? this.minKeySize_ : other.minKeySize_;
         result.maxKeySize_ = this.maxKeySize_ > other.maxKeySize_ ? this.maxKeySize_ : other.maxKeySize_;
      } else {
         result = (MechanismInfo)this.clone();
      }

      return result;
   }

   public MechanismInfo and(MechanismInfo other) {
      MechanismInfo result = new MechanismInfo();
      if (other != null) {
         result.flags_ = this.flags_ & other.flags_;
         result.minKeySize_ = this.minKeySize_ > other.minKeySize_ ? this.minKeySize_ : other.minKeySize_;
         result.maxKeySize_ = this.maxKeySize_ < other.maxKeySize_ ? this.maxKeySize_ : other.maxKeySize_;
      }

      return result;
   }

   public MechanismInfo not() {
      MechanismInfo result = (MechanismInfo)this.clone();
      result.flags_ = ~this.flags_;
      return result;
   }

   public void setMinKeySize(long minKeySize) {
      this.minKeySize_ = minKeySize;
   }

   public void setMaxKeySize(long maxKeySize) {
      this.maxKeySize_ = maxKeySize;
   }

   public void setHw(boolean hw) {
      this.setFlagBit(1L, hw);
   }

   public void setEncrypt(boolean encrypt) {
      this.setFlagBit(256L, encrypt);
   }

   public void setDecrypt(boolean decrypt) {
      this.setFlagBit(512L, decrypt);
   }

   public void setDigest(boolean digest) {
      this.setFlagBit(1024L, digest);
   }

   public void setSign(boolean sign) {
      this.setFlagBit(2048L, sign);
   }

   public void setSignRecover(boolean signRecover) {
      this.setFlagBit(4096L, signRecover);
   }

   public void setVerify(boolean verfy) {
      this.setFlagBit(8192L, verfy);
   }

   public void setVerifyRecover(boolean verifyRecover) {
      this.setFlagBit(16384L, verifyRecover);
   }

   public void setGenerate(boolean generate) {
      this.setFlagBit(32768L, generate);
   }

   public void setGenerateKeyPair(boolean generateKeyPair) {
      this.setFlagBit(65536L, generateKeyPair);
   }

   public void setWrap(boolean wrap) {
      this.setFlagBit(131072L, wrap);
   }

   public void setUnwrap(boolean unwrap) {
      this.setFlagBit(262144L, unwrap);
   }

   public void setDerive(boolean derive) {
      this.setFlagBit(524288L, derive);
   }

   public void setEcFp(boolean ecFp) {
      this.setFlagBit(1048576L, ecFp);
   }

   public void setEcF2m(boolean ecF2m) {
      this.setFlagBit(2097152L, ecF2m);
   }

   public void setEcEcParameters(boolean ecEcParameters) {
      this.setFlagBit(4194304L, ecEcParameters);
   }

   public void setEcNamedCurve(boolean ecNamedCurve) {
      this.setFlagBit(8388608L, ecNamedCurve);
   }

   public void setEcUncompress(boolean ecUncompress) {
      this.setFlagBit(16777216L, ecUncompress);
   }

   public void setEcCompress(boolean ecCompress) {
      this.setFlagBit(33554432L, ecCompress);
   }

   public void setExtension(boolean extension) {
      this.setFlagBit(2147483648L, extension);
   }

   public boolean supports(MechanismInfo requiredFeatures) {
      if (requiredFeatures == null) {
         throw new NullPointerException("Argument \"requiredFeatures\" must not be null.");
      } else {
         long requiredMaxKeySize = requiredFeatures.getMaxKeySize();
         if (requiredMaxKeySize != 0L && requiredMaxKeySize > this.maxKeySize_) {
            return false;
         } else {
            long requiredMinKeySize = requiredFeatures.getMinKeySize();
            if (requiredMinKeySize != 0L && requiredMinKeySize < this.minKeySize_) {
               return false;
            } else {
               return (requiredFeatures.flags_ & this.flags_) == requiredFeatures.flags_;
            }
         }
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(256);
      buffer.append("  ");
      buffer.append("Minimum Key-Size: ");
      buffer.append(this.minKeySize_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Maximum Key-Size: ");
      buffer.append(this.maxKeySize_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Hardware: ");
      buffer.append(this.isHw());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Encrypt: ");
      buffer.append(this.isEncrypt());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Decrypt: ");
      buffer.append(this.isDecrypt());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Digest: ");
      buffer.append(this.isDigest());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Sign: ");
      buffer.append(this.isSign());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Sign Recover: ");
      buffer.append(this.isSignRecover());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Verify: ");
      buffer.append(this.isVerify());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Verify Recover: ");
      buffer.append(this.isVerifyRecover());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Generate: ");
      buffer.append(this.isGenerate());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Generate Key-Pair: ");
      buffer.append(this.isGenerateKeyPair());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Wrap: ");
      buffer.append(this.isWrap());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Unwrap: ");
      buffer.append(this.isUnwrap());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Derive: ");
      buffer.append(this.isDerive());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("EC F(p): ");
      buffer.append(this.isEcFp());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("EC F(2^m): ");
      buffer.append(this.isEcF2m());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("EC Parameters: ");
      buffer.append(this.isEcEcParameters());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("EC Named Curve: ");
      buffer.append(this.isEcNamedCurve());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("EC Uncompress: ");
      buffer.append(this.isEcUncompress());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("EC Compress: ");
      buffer.append(this.isEcCompress());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Extension: ");
      buffer.append(this.isExtension());
      return buffer.toString();
   }

   protected void setFlagBit(long flagMask, boolean value) {
      if (value) {
         this.flags_ |= flagMask;
      } else {
         this.flags_ &= ~flagMask;
      }

   }
}
