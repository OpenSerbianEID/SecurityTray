package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.wrapper.CK_RC2_CBC_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class RC2CbcParameters extends RC2Parameters {
   protected byte[] initializationVector_;

   public RC2CbcParameters(long effectiveBits, byte[] initializationVector) {
      super(effectiveBits);
      if (initializationVector == null) {
         throw new NullPointerException("Argument \"initializationVector\" must not be null.");
      } else {
         this.initializationVector_ = initializationVector;
      }
   }

   public Object clone() {
      RC2CbcParameters clone = (RC2CbcParameters)super.clone();
      clone.initializationVector_ = (byte[])((byte[])this.initializationVector_.clone());
      return clone;
   }

   public Object getPKCS11ParamsObject() {
      CK_RC2_CBC_PARAMS params = new CK_RC2_CBC_PARAMS();
      params.ulEffectiveBits = this.effectiveBits_;
      params.iv = this.initializationVector_;
      return params;
   }

   public byte[] getInitializationVector() {
      return this.initializationVector_;
   }

   public void setInitializationVector(byte[] initializationVector) {
      if (initializationVector == null) {
         throw new NullPointerException("Argument \"initializationVector\" must not be null.");
      } else {
         this.initializationVector_ = initializationVector;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Effective Bits (dec): ");
      buffer.append(this.effectiveBits_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Initialization Vector (hex): ");
      buffer.append(Functions.toHexString(this.initializationVector_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RC2CbcParameters) {
         RC2CbcParameters other = (RC2CbcParameters)otherObject;
         equal = this == other || super.equals(other) && Functions.equals(this.initializationVector_, other.initializationVector_);
      }

      return equal;
   }

   public int hashCode() {
      return super.hashCode() ^ Functions.hashCode(this.initializationVector_);
   }
}
