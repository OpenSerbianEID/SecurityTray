package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.CK_VERSION;

public class Version implements Cloneable {
   protected byte major_;
   protected byte minor_;

   protected Version() {
   }

   protected Version(CK_VERSION ckVersion) {
      if (ckVersion == null) {
         throw new NullPointerException("Argument \"ckVersion\" must not be null.");
      } else {
         this.major_ = ckVersion.major;
         this.minor_ = ckVersion.minor;
      }
   }

   public Object clone() {
      try {
         Version clone = (Version)super.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public byte getMajor() {
      return this.major_;
   }

   public byte getMinor() {
      return this.minor_;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(this.major_ & 255);
      buffer.append('.');
      if (this.minor_ < 10) {
         buffer.append('0');
      }

      buffer.append(this.minor_ & 255);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Version) {
         Version other = (Version)otherObject;
         equal = this == other || this.major_ == other.major_ && this.minor_ == other.minor_;
      }

      return equal;
   }

   public int hashCode() {
      return this.major_ ^ this.minor_;
   }
}
