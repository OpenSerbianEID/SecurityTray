package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;

public class RC2Parameters implements Parameters {
   protected long effectiveBits_;

   public RC2Parameters(long effectiveBits) {
      if (effectiveBits >= 1L && effectiveBits <= 1024L) {
         this.effectiveBits_ = effectiveBits;
      } else {
         throw new IllegalArgumentException("Argument \"effectiveBits\" must be in the range [1, 1024].");
      }
   }

   public Object clone() {
      try {
         RC2Parameters clone = (RC2Parameters)super.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      return new Long(this.effectiveBits_);
   }

   public long getEffectiveBits() {
      return this.effectiveBits_;
   }

   public void setEffectiveBits(long effectiveBits) {
      if (effectiveBits >= 1L && effectiveBits <= 1024L) {
         this.effectiveBits_ = effectiveBits;
      } else {
         throw new IllegalArgumentException("Argument \"effectiveBits\" must be in the range [1, 1024].");
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Effective Bits (dec): ");
      buffer.append(this.effectiveBits_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RC2Parameters) {
         RC2Parameters other = (RC2Parameters)otherObject;
         equal = this == other || this.effectiveBits_ == other.effectiveBits_;
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.effectiveBits_;
   }
}
