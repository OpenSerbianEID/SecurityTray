package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_PBE_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class PBEParameters implements Parameters {
   protected char[] initializationVector_;
   protected char[] password_;
   protected char[] salt_;
   protected long iterations_;

   public PBEParameters(char[] initializationVector, char[] password, char[] salt, long iterations) {
      if (initializationVector != null && initializationVector.length != 8) {
         throw new IllegalArgumentException("Argument \"initializationVector\" must be null or must have length 8, if it is not null.");
      } else if (password == null) {
         throw new NullPointerException("Argument \"password\" must not be null.");
      } else if (salt == null) {
         throw new NullPointerException("Argument \"salt\" must not be null.");
      } else {
         this.initializationVector_ = initializationVector;
         this.password_ = password;
         this.salt_ = salt;
         this.iterations_ = iterations;
      }
   }

   public Object clone() {
      try {
         PBEParameters clone = (PBEParameters)super.clone();
         clone.initializationVector_ = (char[])((char[])this.initializationVector_.clone());
         clone.password_ = (char[])((char[])this.password_.clone());
         clone.salt_ = (char[])((char[])this.salt_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      CK_PBE_PARAMS params = new CK_PBE_PARAMS();
      params.pInitVector = this.initializationVector_;
      params.pPassword = this.password_;
      params.pSalt = this.salt_;
      params.ulIteration = this.iterations_;
      return params;
   }

   public char[] getInitializationVector() {
      return this.initializationVector_;
   }

   public char[] getPassword() {
      return this.password_;
   }

   public char[] getSalt() {
      return this.salt_;
   }

   public long getIterations() {
      return this.iterations_;
   }

   public void setInitializationVector(char[] initializationVector) {
      if (initializationVector != null && initializationVector.length != 8) {
         throw new IllegalArgumentException("Argument \"initializationVector\" must be null or must have length 8, if it is not null.");
      } else {
         this.initializationVector_ = initializationVector;
      }
   }

   public void setPassword(char[] password) {
      if (password == null) {
         throw new NullPointerException("Argument \"password\" must not be null.");
      } else {
         this.password_ = password;
      }
   }

   public void setSalt(char[] salt) {
      if (salt == null) {
         throw new NullPointerException("Argument \"salt\" must not be null.");
      } else {
         this.salt_ = salt;
      }
   }

   public void setIterations(long iterations) {
      this.iterations_ = iterations;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Initialization Vector: ");
      buffer.append(this.initializationVector_ != null ? new String(this.initializationVector_) : null);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Password: ");
      buffer.append(this.password_ != null ? new String(this.password_) : null);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Salt: ");
      buffer.append(this.salt_ != null ? new String(this.salt_) : null);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Iterations (dec): ");
      buffer.append(this.iterations_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof PBEParameters) {
         PBEParameters other = (PBEParameters)otherObject;
         equal = this == other || Functions.equals(this.initializationVector_, other.initializationVector_) && Functions.equals(this.password_, other.password_) && Functions.equals(this.salt_, other.salt_) && this.iterations_ == other.iterations_;
      }

      return equal;
   }

   public int hashCode() {
      return Functions.hashCode(this.initializationVector_) ^ Functions.hashCode(this.password_) ^ Functions.hashCode(this.salt_) ^ (int)this.iterations_;
   }
}
