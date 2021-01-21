package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class DHPkcsDeriveParameters implements Parameters {
   protected byte[] publicValue_;

   public DHPkcsDeriveParameters(byte[] publicValue) {
      this.publicValue_ = publicValue;
   }

   public Object clone() {
      try {
         DHPkcsDeriveParameters clone = (DHPkcsDeriveParameters)super.clone();
         clone.publicValue_ = (byte[])((byte[])this.publicValue_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      return this.publicValue_;
   }

   public byte[] getPublicValue() {
      return this.publicValue_;
   }

   public void setPublicValue(byte[] publicValue) {
      if (publicValue == null) {
         throw new NullPointerException("Argument \"publicValue\" must not be null.");
      } else {
         this.publicValue_ = publicValue;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Public Value (hex): ");
      buffer.append(Functions.toHexString(this.publicValue_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof DHPkcsDeriveParameters) {
         DHPkcsDeriveParameters other = (DHPkcsDeriveParameters)otherObject;
         equal = this == other || Functions.equals(this.publicValue_, other.publicValue_);
      }

      return equal;
   }

   public int hashCode() {
      return Functions.hashCode(this.publicValue_);
   }
}
