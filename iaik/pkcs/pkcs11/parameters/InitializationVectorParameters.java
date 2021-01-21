package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class InitializationVectorParameters implements Parameters {
   protected byte[] initializationVector_;

   public InitializationVectorParameters(byte[] initializationVector) {
      if (initializationVector == null) {
         throw new NullPointerException("Argument \"initializationVector\" must not be null.");
      } else {
         this.initializationVector_ = initializationVector;
      }
   }

   public Object clone() {
      try {
         InitializationVectorParameters clone = (InitializationVectorParameters)super.clone();
         clone.initializationVector_ = (byte[])((byte[])this.initializationVector_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      return this.initializationVector_;
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
      buffer.append("Initialization Vector (hex): ");
      buffer.append(Functions.toHexString(this.initializationVector_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof InitializationVectorParameters) {
         InitializationVectorParameters other = (InitializationVectorParameters)otherObject;
         equal = this == other || Functions.equals(this.initializationVector_, other.initializationVector_);
      }

      return equal;
   }

   public int hashCode() {
      return Functions.hashCode(this.initializationVector_);
   }
}
