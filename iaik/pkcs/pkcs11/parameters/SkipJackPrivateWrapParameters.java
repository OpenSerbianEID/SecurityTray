package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_SKIPJACK_PRIVATE_WRAP_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class SkipJackPrivateWrapParameters implements Parameters {
   protected byte[] password_;
   protected byte[] publicData_;
   protected byte[] randomA_;
   protected byte[] primeP_;
   protected byte[] baseG_;
   protected byte[] subprimeQ_;

   public SkipJackPrivateWrapParameters(byte[] password, byte[] publicData, byte[] randomA, byte[] primeP, byte[] baseG, byte[] subprimeQ) {
      if (password == null) {
         throw new NullPointerException("Argument \"password\" must not be null.");
      } else if (publicData == null) {
         throw new NullPointerException("Argument \"publicData\" must not be null.");
      } else if (randomA == null) {
         throw new NullPointerException("Argument \"randomA\" must not be null.");
      } else if (primeP == null) {
         throw new NullPointerException("Argument \"primeP\" must not be null.");
      } else if (baseG == null) {
         throw new NullPointerException("Argument \"baseG\" must not be null.");
      } else if (subprimeQ == null) {
         throw new NullPointerException("Argument \"subprimeQ\" must not be null.");
      } else {
         this.password_ = password;
         this.publicData_ = publicData;
         this.randomA_ = randomA;
         this.primeP_ = primeP;
         this.baseG_ = baseG;
         this.subprimeQ_ = subprimeQ;
      }
   }

   public Object clone() {
      try {
         SkipJackPrivateWrapParameters clone = (SkipJackPrivateWrapParameters)super.clone();
         clone.password_ = (byte[])((byte[])this.password_.clone());
         clone.publicData_ = (byte[])((byte[])this.publicData_.clone());
         clone.randomA_ = (byte[])((byte[])this.randomA_.clone());
         clone.primeP_ = (byte[])((byte[])this.primeP_.clone());
         clone.baseG_ = (byte[])((byte[])this.baseG_.clone());
         clone.subprimeQ_ = (byte[])((byte[])this.subprimeQ_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      CK_SKIPJACK_PRIVATE_WRAP_PARAMS params = new CK_SKIPJACK_PRIVATE_WRAP_PARAMS();
      params.pPassword = this.password_;
      params.pPublicData = this.publicData_;
      params.pRandomA = this.randomA_;
      params.pPrimeP = this.primeP_;
      params.pBaseG = this.baseG_;
      params.pSubprimeQ = this.subprimeQ_;
      return params;
   }

   public byte[] getPassword() {
      return this.password_;
   }

   public byte[] getPublicData() {
      return this.publicData_;
   }

   public byte[] getRandomA() {
      return this.randomA_;
   }

   public byte[] getPrimeP() {
      return this.primeP_;
   }

   public byte[] getBaseG() {
      return this.baseG_;
   }

   public byte[] getSubprimeQ() {
      return this.subprimeQ_;
   }

   public void setPassword(byte[] password) {
      if (password == null) {
         throw new NullPointerException("Argument \"password\" must not be null.");
      } else {
         this.password_ = password;
      }
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

   public void setPrimeP(byte[] primeP) {
      if (primeP == null) {
         throw new NullPointerException("Argument \"primeP\" must not be null.");
      } else {
         this.primeP_ = primeP;
      }
   }

   public void setBaseG(byte[] baseG) {
      if (baseG == null) {
         throw new NullPointerException("Argument \"baseG\" must not be null.");
      } else {
         this.baseG_ = baseG;
      }
   }

   public void setSubprimeQ(byte[] subprimeQ) {
      if (subprimeQ == null) {
         throw new NullPointerException("Argument \"subprimeQ\" must not be null.");
      } else {
         this.subprimeQ_ = subprimeQ;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Passord (hex): ");
      buffer.append(Functions.toHexString(this.password_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Public Data (hex): ");
      buffer.append(Functions.toHexString(this.publicData_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Random Data A (hex): ");
      buffer.append(Functions.toHexString(this.randomA_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Prime P (hex): ");
      buffer.append(Functions.toHexString(this.primeP_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Base G (hex): ");
      buffer.append(Functions.toHexString(this.baseG_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Subprime Q (hex): ");
      buffer.append(Functions.toHexString(this.subprimeQ_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof SkipJackPrivateWrapParameters) {
         SkipJackPrivateWrapParameters other = (SkipJackPrivateWrapParameters)otherObject;
         equal = this == other || Functions.equals(this.password_, other.password_) && Functions.equals(this.publicData_, other.publicData_) && Functions.equals(this.randomA_, other.randomA_) && Functions.equals(this.primeP_, other.primeP_) && Functions.equals(this.baseG_, other.baseG_) && Functions.equals(this.subprimeQ_, other.subprimeQ_);
      }

      return equal;
   }

   public int hashCode() {
      return Functions.hashCode(this.password_) ^ Functions.hashCode(this.publicData_) ^ Functions.hashCode(this.randomA_) ^ Functions.hashCode(this.primeP_) ^ Functions.hashCode(this.baseG_) ^ Functions.hashCode(this.subprimeQ_);
   }
}
