package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_RC5_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class RC5Parameters implements Parameters {
   protected long wordSize_;
   protected long rounds_;

   public RC5Parameters(long wordSize, long rounds) {
      this.wordSize_ = wordSize;
      this.rounds_ = rounds;
   }

   public Object clone() {
      try {
         RC5Parameters clone = (RC5Parameters)super.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public long getWordSize() {
      return this.wordSize_;
   }

   public long getRounds() {
      return this.rounds_;
   }

   public Object getPKCS11ParamsObject() {
      CK_RC5_PARAMS params = new CK_RC5_PARAMS();
      params.ulWordsize = this.wordSize_;
      params.ulRounds = this.rounds_;
      return params;
   }

   public void setWordSize(long wordSize) {
      this.wordSize_ = wordSize;
   }

   public void setMacLength(long rounds) {
      this.rounds_ = rounds;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Word Size (dec): ");
      buffer.append(this.wordSize_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Rounds (dec): ");
      buffer.append(this.rounds_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RC5Parameters) {
         RC5Parameters other = (RC5Parameters)otherObject;
         equal = this == other || this.wordSize_ == other.wordSize_ && this.rounds_ == other.rounds_;
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.wordSize_ ^ (int)this.rounds_;
   }
}
