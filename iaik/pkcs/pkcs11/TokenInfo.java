package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.CK_TOKEN_INFO;
import iaik.pkcs.pkcs11.wrapper.Constants;
import java.util.Date;

public class TokenInfo implements Cloneable {
   public static final long EFFECTIVELY_INFINITE = 0L;
   public static final long UNAVAILABLE_INFORMATION = 4294967295L;
   protected String label_;
   protected String manufacturerID_;
   protected String model_;
   protected String serialNumber_;
   protected long maxSessionCount_;
   protected long sessionCount_;
   protected long maxRwSessionCount_;
   protected long rwSessionCount_;
   protected long maxPinLen_;
   protected long minPinLen_;
   protected long totalPublicMemory_;
   protected long freePublicMemory_;
   protected long totalPrivateMemory_;
   protected long freePrivateMemory_;
   protected Version hardwareVersion_;
   protected Version firmwareVersion_;
   protected Date time_;
   protected boolean rng_;
   protected boolean writeProtected_;
   protected boolean loginRequired_;
   protected boolean userPinInitialized_;
   protected boolean restoreKeyNotNeeded_;
   protected boolean clockOnToken_;
   protected boolean protectedAuthenticationPath_;
   protected boolean dualCryptoOperations_;
   protected boolean tokenInitialized_;
   protected boolean secondaryAuthentication_;
   protected boolean userPinCountLow_;
   protected boolean userPinFinalTry_;
   protected boolean userPinLocked_;
   protected boolean userPinToBeChanged_;
   protected boolean soPinCountLow_;
   protected boolean soPinFinalTry_;
   protected boolean soPinLocked_;
   protected boolean soPinToBeChanged_;

   protected TokenInfo(CK_TOKEN_INFO ckTokenInfo) {
      if (ckTokenInfo == null) {
         throw new NullPointerException("Argument \"ckTokenInfo\" must not be null.");
      } else {
         this.label_ = new String(ckTokenInfo.label);
         this.manufacturerID_ = new String(ckTokenInfo.manufacturerID);
         this.model_ = new String(ckTokenInfo.model);
         this.serialNumber_ = new String(ckTokenInfo.serialNumber);
         this.maxSessionCount_ = ckTokenInfo.ulMaxSessionCount;
         this.sessionCount_ = ckTokenInfo.ulSessionCount;
         this.maxRwSessionCount_ = ckTokenInfo.ulMaxRwSessionCount;
         this.rwSessionCount_ = ckTokenInfo.ulRwSessionCount;
         this.maxPinLen_ = ckTokenInfo.ulMaxPinLen;
         this.minPinLen_ = ckTokenInfo.ulMinPinLen;
         this.totalPublicMemory_ = ckTokenInfo.ulTotalPublicMemory;
         this.freePublicMemory_ = ckTokenInfo.ulFreePublicMemory;
         this.totalPrivateMemory_ = ckTokenInfo.ulTotalPrivateMemory;
         this.freePrivateMemory_ = ckTokenInfo.ulFreePrivateMemory;
         this.hardwareVersion_ = new Version(ckTokenInfo.hardwareVersion);
         this.firmwareVersion_ = new Version(ckTokenInfo.firmwareVersion);
         this.time_ = Util.parseTime(ckTokenInfo.utcTime);
         this.rng_ = (ckTokenInfo.flags & 1L) != 0L;
         this.writeProtected_ = (ckTokenInfo.flags & 2L) != 0L;
         this.loginRequired_ = (ckTokenInfo.flags & 4L) != 0L;
         this.userPinInitialized_ = (ckTokenInfo.flags & 8L) != 0L;
         this.restoreKeyNotNeeded_ = (ckTokenInfo.flags & 32L) != 0L;
         this.clockOnToken_ = (ckTokenInfo.flags & 64L) != 0L;
         this.protectedAuthenticationPath_ = (ckTokenInfo.flags & 256L) != 0L;
         this.dualCryptoOperations_ = (ckTokenInfo.flags & 512L) != 0L;
         this.tokenInitialized_ = (ckTokenInfo.flags & 1024L) != 0L;
         this.secondaryAuthentication_ = (ckTokenInfo.flags & 2048L) != 0L;
         this.userPinCountLow_ = (ckTokenInfo.flags & 65536L) != 0L;
         this.userPinFinalTry_ = (ckTokenInfo.flags & 131072L) != 0L;
         this.userPinLocked_ = (ckTokenInfo.flags & 262144L) != 0L;
         this.userPinToBeChanged_ = (ckTokenInfo.flags & 524288L) != 0L;
         this.soPinCountLow_ = (ckTokenInfo.flags & 1048576L) != 0L;
         this.soPinFinalTry_ = (ckTokenInfo.flags & 2097152L) != 0L;
         this.soPinLocked_ = (ckTokenInfo.flags & 4194304L) != 0L;
         this.soPinToBeChanged_ = (ckTokenInfo.flags & 8388608L) != 0L;
      }
   }

   public Object clone() {
      try {
         TokenInfo clone = (TokenInfo)super.clone();
         clone.hardwareVersion_ = (Version)this.hardwareVersion_.clone();
         clone.firmwareVersion_ = (Version)this.firmwareVersion_.clone();
         clone.time_ = new Date(this.time_.getTime());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public String getLabel() {
      return this.label_;
   }

   public String getManufacturerID() {
      return this.manufacturerID_;
   }

   public String getModel() {
      return this.model_;
   }

   public String getSerialNumber() {
      return this.serialNumber_;
   }

   public long getMaxSessionCount() {
      return this.maxSessionCount_;
   }

   public long getSessionCount() {
      return this.sessionCount_;
   }

   public long getMaxRwSessionCount() {
      return this.maxRwSessionCount_;
   }

   public long getRwSessionCount() {
      return this.rwSessionCount_;
   }

   public long getMaxPinLen() {
      return this.maxPinLen_;
   }

   public long getMinPinLen() {
      return this.minPinLen_;
   }

   public long getTotalPublicMemory() {
      return this.totalPublicMemory_;
   }

   public long getFreePublicMemory() {
      return this.freePublicMemory_;
   }

   public long getTotalPrivateMemory() {
      return this.totalPrivateMemory_;
   }

   public long getFreePrivateMemory() {
      return this.freePrivateMemory_;
   }

   public Version getHardwareVersion() {
      return this.hardwareVersion_;
   }

   public Version getFirmwareVersion() {
      return this.firmwareVersion_;
   }

   public Date getTime() {
      return this.time_;
   }

   public boolean isRNG() {
      return this.rng_;
   }

   public boolean isWriteProtected() {
      return this.writeProtected_;
   }

   public boolean isLoginRequired() {
      return this.loginRequired_;
   }

   public boolean isUserPinInitialized() {
      return this.userPinInitialized_;
   }

   public boolean isRestoreKeyNotNeeded() {
      return this.restoreKeyNotNeeded_;
   }

   public boolean isClockOnToken() {
      return this.clockOnToken_;
   }

   public boolean isProtectedAuthenticationPath() {
      return this.protectedAuthenticationPath_;
   }

   public boolean isDualCryptoOperations() {
      return this.dualCryptoOperations_;
   }

   public boolean isTokenInitialized() {
      return this.tokenInitialized_;
   }

   public boolean isSecondaryAuthentication() {
      return this.secondaryAuthentication_;
   }

   public boolean isUserPinCountLow() {
      return this.userPinCountLow_;
   }

   public boolean isUserPinFinalTry() {
      return this.userPinFinalTry_;
   }

   public boolean isUserPinLocked() {
      return this.userPinLocked_;
   }

   public boolean isUserPinToBeChanged() {
      return this.userPinToBeChanged_;
   }

   public boolean isSoPinCountLow() {
      return this.soPinCountLow_;
   }

   public boolean isSoPinFinalTry() {
      return this.soPinFinalTry_;
   }

   public boolean isSoPinLocked() {
      return this.soPinLocked_;
   }

   public boolean isSoPinToBeChanged() {
      return this.soPinToBeChanged_;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("Label: ");
      buffer.append(this.label_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Manufacturer ID: ");
      buffer.append(this.manufacturerID_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Model: ");
      buffer.append(this.model_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Serial Number: ");
      buffer.append(this.serialNumber_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Random Number Generator: ");
      buffer.append(this.rng_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Write protected: ");
      buffer.append(this.writeProtected_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Login required: ");
      buffer.append(this.loginRequired_);
      buffer.append(Constants.NEWLINE);
      buffer.append("User PIN initialized: ");
      buffer.append(this.userPinInitialized_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Restore Key not needed: ");
      buffer.append(this.restoreKeyNotNeeded_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Clock on Token: ");
      buffer.append(this.clockOnToken_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Protected Authentication Path: ");
      buffer.append(this.protectedAuthenticationPath_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Dual Crypto Operations: ");
      buffer.append(this.dualCryptoOperations_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Token initialized: ");
      buffer.append(this.tokenInitialized_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Secondary Authentication: ");
      buffer.append(this.secondaryAuthentication_);
      buffer.append(Constants.NEWLINE);
      buffer.append("User PIN-Count low: ");
      buffer.append(this.userPinCountLow_);
      buffer.append(Constants.NEWLINE);
      buffer.append("User PIN final Try: ");
      buffer.append(this.userPinFinalTry_);
      buffer.append(Constants.NEWLINE);
      buffer.append("User PIN locked: ");
      buffer.append(this.userPinLocked_);
      buffer.append(Constants.NEWLINE);
      buffer.append("User PIN to be changed: ");
      buffer.append(this.userPinToBeChanged_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Security Officer PIN-Count low: ");
      buffer.append(this.soPinCountLow_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Security Officer PIN final Try: ");
      buffer.append(this.soPinFinalTry_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Security Officer PIN locked: ");
      buffer.append(this.soPinLocked_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Security Officer PIN to be changed: ");
      buffer.append(this.soPinToBeChanged_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Maximum Session Count: ");
      buffer.append(this.maxSessionCount_ == 4294967295L ? "<Information unavailable>" : (this.maxSessionCount_ == 0L ? "<effectively infinite>" : Long.toString(this.maxSessionCount_)));
      buffer.append(Constants.NEWLINE);
      buffer.append("Session Count: ");
      buffer.append(this.sessionCount_ == 4294967295L ? "<Information unavailable>" : Long.toString(this.sessionCount_));
      buffer.append(Constants.NEWLINE);
      buffer.append("Maximum Read/Write Session Count: ");
      buffer.append(this.maxRwSessionCount_ == 4294967295L ? "<Information unavailable>" : (this.maxRwSessionCount_ == 0L ? "<effectively infinite>" : Long.toString(this.maxRwSessionCount_)));
      buffer.append(Constants.NEWLINE);
      buffer.append("Read/Write Session Count: ");
      buffer.append(this.rwSessionCount_ == 4294967295L ? "<Information unavailable>" : Long.toString(this.rwSessionCount_));
      buffer.append(Constants.NEWLINE);
      buffer.append("Maximum PIN Length: ");
      buffer.append(this.maxPinLen_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Minimum PIN Length: ");
      buffer.append(this.minPinLen_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Total Public Memory: ");
      buffer.append(this.totalPublicMemory_ == 4294967295L ? "<Information unavailable>" : Long.toString(this.totalPublicMemory_));
      buffer.append(Constants.NEWLINE);
      buffer.append("Free Public Memory: ");
      buffer.append(this.freePublicMemory_ == 4294967295L ? "<Information unavailable>" : Long.toString(this.freePublicMemory_));
      buffer.append(Constants.NEWLINE);
      buffer.append("Total Private Memory: ");
      buffer.append(this.totalPrivateMemory_ == 4294967295L ? "<Information unavailable>" : Long.toString(this.totalPrivateMemory_));
      buffer.append(Constants.NEWLINE);
      buffer.append("Free Private Memory: ");
      buffer.append(this.freePrivateMemory_ == 4294967295L ? "<Information unavailable>" : Long.toString(this.freePrivateMemory_));
      buffer.append(Constants.NEWLINE);
      buffer.append("Hardware Version: ");
      buffer.append(this.hardwareVersion_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Firmware Version: ");
      buffer.append(this.firmwareVersion_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Time: ");
      buffer.append(this.time_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof TokenInfo) {
         TokenInfo other = (TokenInfo)otherObject;
         equal = this == other || this.label_.equals(other.label_) && this.manufacturerID_.equals(other.manufacturerID_) && this.model_.equals(other.model_) && this.serialNumber_.equals(other.serialNumber_) && this.maxSessionCount_ == other.maxSessionCount_ && this.sessionCount_ == other.sessionCount_ && this.maxRwSessionCount_ == other.maxRwSessionCount_ && this.rwSessionCount_ == other.rwSessionCount_ && this.maxPinLen_ == other.maxPinLen_ && this.minPinLen_ == other.minPinLen_ && this.totalPublicMemory_ == other.totalPublicMemory_ && this.freePublicMemory_ == other.freePublicMemory_ && this.totalPrivateMemory_ == other.totalPrivateMemory_ && this.freePrivateMemory_ == other.freePrivateMemory_ && this.hardwareVersion_.equals(other.hardwareVersion_) && this.firmwareVersion_.equals(other.firmwareVersion_) && this.time_.equals(other.time_) && this.rng_ == other.rng_ && this.writeProtected_ == other.writeProtected_ && this.loginRequired_ == other.loginRequired_ && this.userPinInitialized_ == other.userPinInitialized_ && this.restoreKeyNotNeeded_ == other.restoreKeyNotNeeded_ && this.clockOnToken_ == other.clockOnToken_ && this.protectedAuthenticationPath_ == other.protectedAuthenticationPath_ && this.dualCryptoOperations_ == other.dualCryptoOperations_ && this.tokenInitialized_ == other.tokenInitialized_ && this.secondaryAuthentication_ == other.secondaryAuthentication_ && this.userPinCountLow_ == other.userPinCountLow_ && this.userPinFinalTry_ == other.userPinFinalTry_ && this.userPinLocked_ == other.userPinLocked_ && this.userPinToBeChanged_ == other.userPinToBeChanged_ && this.soPinCountLow_ == other.soPinCountLow_ && this.soPinFinalTry_ == other.soPinFinalTry_ && this.soPinLocked_ == other.soPinLocked_ && this.soPinToBeChanged_ == other.soPinToBeChanged_;
      }

      return equal;
   }

   public int hashCode() {
      return this.label_.hashCode() ^ this.manufacturerID_.hashCode() ^ this.model_.hashCode() ^ this.serialNumber_.hashCode();
   }
}
