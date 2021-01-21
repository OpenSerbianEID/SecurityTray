package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.wrapper.CK_RC5_MAC_GENERAL_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class RC5MacGeneralParameters extends RC5Parameters {
   protected long macLength_;

   public RC5MacGeneralParameters(long wordSize, long rounds, long macLength) {
      super(wordSize, rounds);
      this.macLength_ = macLength;
   }

   public Object getPKCS11ParamsObject() {
      CK_RC5_MAC_GENERAL_PARAMS params = new CK_RC5_MAC_GENERAL_PARAMS();
      params.ulWordsize = this.wordSize_;
      params.ulRounds = this.rounds_;
      params.ulMacLength = this.macLength_;
      return params;
   }

   public long getMacLength() {
      return this.macLength_;
   }

   public void setMacLength(long macLength) {
      this.macLength_ = macLength;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Mac Length (dec): ");
      buffer.append(this.macLength_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RC5MacGeneralParameters) {
         RC5MacGeneralParameters other = (RC5MacGeneralParameters)otherObject;
         equal = this == other || super.equals(other) && this.macLength_ == other.macLength_;
      }

      return equal;
   }

   public int hashCode() {
      return super.hashCode() ^ (int)this.macLength_;
   }
}
