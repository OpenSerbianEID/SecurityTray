package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.wrapper.CK_RC2_MAC_GENERAL_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class RC2MacGeneralParameters extends RC2Parameters {
   protected long macLength_;

   public RC2MacGeneralParameters(long effectiveBits, long macLength) {
      super(effectiveBits);
      this.macLength_ = macLength;
   }

   public long getMacLength() {
      return this.macLength_;
   }

   public Object getPKCS11ParamsObject() {
      CK_RC2_MAC_GENERAL_PARAMS params = new CK_RC2_MAC_GENERAL_PARAMS();
      params.ulEffectiveBits = this.effectiveBits_;
      params.ulMacLength = this.macLength_;
      return params;
   }

   public void setMacLength(long macLength) {
      this.macLength_ = macLength;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Effective Bits (dec): ");
      buffer.append(this.effectiveBits_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Mac Length (dec): ");
      buffer.append(this.macLength_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RC2MacGeneralParameters) {
         RC2MacGeneralParameters other = (RC2MacGeneralParameters)otherObject;
         equal = this == other || super.equals(other) && this.macLength_ == other.macLength_;
      }

      return equal;
   }

   public int hashCode() {
      return super.hashCode() ^ (int)this.macLength_;
   }
}
