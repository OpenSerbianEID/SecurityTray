package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;

public class MacGeneralParameters implements Parameters {
   protected long macLength_;

   public MacGeneralParameters(long macLength) {
      this.macLength_ = macLength;
   }

   public Object clone() {
      try {
         MacGeneralParameters clone = (MacGeneralParameters)super.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      return new Long(this.macLength_);
   }

   public long getMacLength() {
      return this.macLength_;
   }

   public void setMacLength(long macLength) {
      this.macLength_ = macLength;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Mac Length (dec): ");
      buffer.append(this.macLength_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof MacGeneralParameters) {
         MacGeneralParameters other = (MacGeneralParameters)otherObject;
         equal = this == other || this.macLength_ == other.macLength_;
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.macLength_;
   }
}
