package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_SSL3_MASTER_KEY_DERIVE_PARAMS;
import iaik.pkcs.pkcs11.wrapper.CK_SSL3_RANDOM_DATA;
import iaik.pkcs.pkcs11.wrapper.CK_VERSION;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class SSL3MasterKeyDeriveParameters implements Parameters {
   protected SSL3RandomDataParameters randomInfo_;
   protected VersionParameters version_;

   public SSL3MasterKeyDeriveParameters(SSL3RandomDataParameters randomInfo, VersionParameters version) {
      if (randomInfo == null) {
         throw new NullPointerException("Argument \"randomInfo\" must not be null.");
      } else if (version == null) {
         throw new NullPointerException("Argument \"version\" must not be null.");
      } else {
         this.randomInfo_ = randomInfo;
         this.version_ = version;
      }
   }

   public Object clone() {
      try {
         SSL3MasterKeyDeriveParameters clone = (SSL3MasterKeyDeriveParameters)super.clone();
         clone.randomInfo_ = (SSL3RandomDataParameters)this.randomInfo_.clone();
         clone.version_ = (VersionParameters)this.version_.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      CK_SSL3_MASTER_KEY_DERIVE_PARAMS params = new CK_SSL3_MASTER_KEY_DERIVE_PARAMS();
      params.RandomInfo = (CK_SSL3_RANDOM_DATA)this.randomInfo_.getPKCS11ParamsObject();
      params.pVersion = (CK_VERSION)this.version_.getPKCS11ParamsObject();
      return params;
   }

   public SSL3RandomDataParameters getRandomInfo() {
      return this.randomInfo_;
   }

   public VersionParameters getVersion() {
      return this.version_;
   }

   public void setRandomInfo(SSL3RandomDataParameters randomInfo) {
      if (randomInfo == null) {
         throw new NullPointerException("Argument \"randomInfo\" must not be null.");
      } else {
         this.randomInfo_ = randomInfo;
      }
   }

   public void setVersion(VersionParameters version) {
      if (version == null) {
         throw new NullPointerException("Argument \"version\" must not be null.");
      } else {
         this.version_ = version;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Random Information:");
      buffer.append(Constants.NEWLINE);
      buffer.append(this.randomInfo_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Version: ");
      buffer.append(this.version_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof SSL3MasterKeyDeriveParameters) {
         SSL3MasterKeyDeriveParameters other = (SSL3MasterKeyDeriveParameters)otherObject;
         equal = this == other || this.randomInfo_.equals(other.randomInfo_) && this.version_.equals(other.version_);
      }

      return equal;
   }

   public int hashCode() {
      return this.randomInfo_.hashCode() ^ this.version_.hashCode();
   }
}
