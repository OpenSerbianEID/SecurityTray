package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class KeyPair implements Cloneable {
   protected PublicKey publicKey_;
   protected PrivateKey privateKey_;

   public KeyPair(PublicKey publicKey, PrivateKey privateKey) {
      if (publicKey == null) {
         throw new NullPointerException("Argument \"publicKey\" must not be null.");
      } else if (privateKey == null) {
         throw new NullPointerException("Argument \"privateKey\" must not be null.");
      } else {
         this.publicKey_ = publicKey;
         this.privateKey_ = privateKey;
      }
   }

   public java.lang.Object clone() {
      try {
         KeyPair clone = (KeyPair)super.clone();
         clone.privateKey_ = (PrivateKey)this.privateKey_.clone();
         clone.publicKey_ = (PublicKey)this.publicKey_.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public PublicKey getPublicKey() {
      return this.publicKey_;
   }

   public void setPublicKey(PublicKey publicKey) {
      if (publicKey == null) {
         throw new NullPointerException("Argument \"publicKey\" must not be null.");
      } else {
         this.publicKey_ = publicKey;
      }
   }

   public PrivateKey getPrivateKey() {
      return this.privateKey_;
   }

   public void setPrivateKey(PrivateKey privateKey) {
      if (privateKey == null) {
         throw new NullPointerException("Argument \"privateKey\" must not be null.");
      } else {
         this.privateKey_ = privateKey;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(128);
      buffer.append("  ");
      buffer.append(this.publicKey_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append(this.privateKey_);
      return buffer.toString();
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof KeyPair) {
         KeyPair other = (KeyPair)otherObject;
         equal = this == other || this.publicKey_.equals(other.publicKey_) && this.privateKey_.equals(other.privateKey_);
      }

      return equal;
   }

   public int hashCode() {
      return this.publicKey_.hashCode() ^ this.privateKey_.hashCode();
   }
}
