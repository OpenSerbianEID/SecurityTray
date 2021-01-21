package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.wrapper.CK_RC5_CBC_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class RC5CbcParameters extends RC5Parameters {
   protected byte[] initializationVector_;

   public RC5CbcParameters(long wordSize, long rounds, byte[] initializationVector) {
      super(wordSize, rounds);
      if (initializationVector == null) {
         throw new NullPointerException("Argument \"initializationVector\" must not be null.");
      } else {
         this.initializationVector_ = initializationVector;
      }
   }

   public Object clone() {
      RC5CbcParameters clone = (RC5CbcParameters)super.clone();
      clone.initializationVector_ = (byte[])((byte[])this.initializationVector_.clone());
      return clone;
   }

   public Object getPKCS11ParamsObject() {
      CK_RC5_CBC_PARAMS params = new CK_RC5_CBC_PARAMS();
      params.ulWordsize = this.wordSize_;
      params.ulRounds = this.rounds_;
      params.pIv = this.initializationVector_;
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
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Initialization Vector (hex): ");
      buffer.append(Functions.toHexString(this.initializationVector_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RC5CbcParameters) {
         RC5CbcParameters other = (RC5CbcParameters)otherObject;
         equal = this == other || super.equals(other) && Functions.equals(this.initializationVector_, other.initializationVector_);
      }

      return equal;
   }

   public int hashCode() {
      return super.hashCode() ^ Functions.hashCode(this.initializationVector_);
   }
}
