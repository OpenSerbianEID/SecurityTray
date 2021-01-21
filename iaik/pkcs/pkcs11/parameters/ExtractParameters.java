package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;

public class ExtractParameters implements Parameters {
   protected long bitIndex_;

   public ExtractParameters(long bitIndex) {
      this.bitIndex_ = bitIndex;
   }

   public Object clone() {
      try {
         ExtractParameters clone = (ExtractParameters)super.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      return new Long(this.bitIndex_);
   }

   public long getBitIndex() {
      return this.bitIndex_;
   }

   public void setBitIndex(long bitIndex) {
      this.bitIndex_ = bitIndex;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Bit Index (dec): ");
      buffer.append(this.bitIndex_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof ExtractParameters) {
         ExtractParameters other = (ExtractParameters)otherObject;
         equal = this == other || this.bitIndex_ == other.bitIndex_;
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.bitIndex_;
   }
}
