package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.CK_INFO;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class Info implements Cloneable {
   protected Version cryptokiVersion_;
   protected String manufacturerID_;
   protected String libraryDescription_;
   protected Version libraryVersion_;

   protected Info(CK_INFO ckInfo) {
      if (ckInfo == null) {
         throw new NullPointerException("Argument \"ckInfo\" must not be null.");
      } else {
         this.cryptokiVersion_ = new Version(ckInfo.cryptokiVersion);
         this.manufacturerID_ = new String(ckInfo.manufacturerID);
         this.libraryDescription_ = new String(ckInfo.libraryDescription);
         this.libraryVersion_ = new Version(ckInfo.libraryVersion);
      }
   }

   public Object clone() {
      try {
         Info clone = (Info)super.clone();
         clone.cryptokiVersion_ = (Version)this.cryptokiVersion_.clone();
         clone.libraryVersion_ = (Version)this.libraryVersion_.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Version getCryptokiVersion() {
      return this.cryptokiVersion_;
   }

   public String getManufacturerID() {
      return this.manufacturerID_;
   }

   public String getLibraryDescription() {
      return this.libraryDescription_;
   }

   public Version getLibraryVersion() {
      return this.libraryVersion_;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("Cryptoki Version: ");
      buffer.append(this.cryptokiVersion_);
      buffer.append(Constants.NEWLINE);
      buffer.append("ManufacturerID: ");
      buffer.append(this.manufacturerID_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Library Description: ");
      buffer.append(this.libraryDescription_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Library Version: ");
      buffer.append(this.libraryVersion_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Info) {
         Info other = (Info)otherObject;
         equal = this == other || this.cryptokiVersion_.equals(other.cryptokiVersion_) && this.manufacturerID_.equals(other.manufacturerID_) && this.libraryDescription_.equals(other.libraryDescription_) && this.libraryVersion_.equals(other.libraryVersion_);
      }

      return equal;
   }

   public int hashCode() {
      return this.cryptokiVersion_.hashCode() ^ this.manufacturerID_.hashCode() ^ this.libraryDescription_.hashCode() ^ this.libraryVersion_.hashCode();
   }
}
