package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_KEA_DERIVE_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class KEADeriveParameters implements Parameters {
   protected boolean isSender_;
   protected byte[] randomA_;
   protected byte[] randomB_;
   protected byte[] publicData_;

   public KEADeriveParameters(boolean isSender, byte[] randomA, byte[] randomB, byte[] publicData) {
      if (randomA == null) {
         throw new NullPointerException("Argument \"randomA\" must not be null.");
      } else if (randomB == null) {
         throw new NullPointerException("Argument \"randomB\" must not be null.");
      } else if (publicData == null) {
         throw new NullPointerException("Argument \"publicData\" must not be null.");
      } else {
         this.isSender_ = isSender;
         this.randomA_ = randomA;
         this.randomB_ = randomB;
         this.publicData_ = publicData;
      }
   }

   public Object clone() {
      try {
         KEADeriveParameters clone = (KEADeriveParameters)super.clone();
         clone.randomA_ = (byte[])((byte[])this.randomA_.clone());
         clone.randomB_ = (byte[])((byte[])this.randomB_.clone());
         clone.publicData_ = (byte[])((byte[])this.publicData_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      CK_KEA_DERIVE_PARAMS params = new CK_KEA_DERIVE_PARAMS();
      params.isSender = this.isSender_;
      params.pRandomA = this.randomA_;
      params.pRandomB = this.randomB_;
      params.pPublicData = this.publicData_;
      return params;
   }

   public byte[] getPublicData() {
      return this.publicData_;
   }

   public byte[] getRandomA() {
      return this.randomA_;
   }

   public byte[] getRandomB() {
      return this.randomB_;
   }

   public boolean isSender() {
      return this.isSender_;
   }

   public void setPublicData(byte[] publicData) {
      if (publicData == null) {
         throw new NullPointerException("Argument \"publicData\" must not be null.");
      } else {
         this.publicData_ = publicData;
      }
   }

   public void setRandomA(byte[] randomA) {
      if (randomA == null) {
         throw new NullPointerException("Argument \"randomA\" must not be null.");
      } else {
         this.randomA_ = randomA;
      }
   }

   public void setRandomB(byte[] randomB) {
      if (randomB == null) {
         throw new NullPointerException("Argument \"randomB\" must not be null.");
      } else {
         this.randomB_ = randomB;
      }
   }

   public void setSender(boolean isSender) {
      this.isSender_ = isSender;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Is Sender: ");
      buffer.append(this.isSender_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Random Data A (hex): ");
      buffer.append(Functions.toHexString(this.randomA_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Random Data B (hex): ");
      buffer.append(Functions.toHexString(this.randomB_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Public Data (hex): ");
      buffer.append(Functions.toHexString(this.publicData_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof KEADeriveParameters) {
         KEADeriveParameters other = (KEADeriveParameters)otherObject;
         equal = this == other || this.isSender_ == other.isSender_ && Functions.equals(this.randomA_, other.randomA_) && Functions.equals(this.randomB_, other.randomB_) && Functions.equals(this.publicData_, other.publicData_);
      }

      return equal;
   }

   public int hashCode() {
      return Functions.hashCode(this.randomA_) ^ Functions.hashCode(this.randomB_) ^ Functions.hashCode(this.publicData_);
   }
}
