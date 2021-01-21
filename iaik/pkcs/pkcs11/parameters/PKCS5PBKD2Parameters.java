package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_PKCS5_PBKD2_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class PKCS5PBKD2Parameters implements Parameters {
   protected long saltSource_;
   protected byte[] saltSourceData_;
   protected long iterations_;
   protected long pseudoRandomFunction_;
   protected byte[] pseudoRandomFunctionData_;

   public PKCS5PBKD2Parameters(long saltSource, byte[] saltSourceData, long iterations, long pseudoRandomFunction, byte[] pseudoRandomFunctionData) {
      if (saltSource != 1L) {
         throw new IllegalArgumentException("Illegal value for argument\"saltSource\": " + Functions.toHexString(saltSource));
      } else if (saltSourceData == null) {
         throw new NullPointerException("Argument \"saltSourceData\" must not be null.");
      } else if (pseudoRandomFunction != 1L) {
         throw new IllegalArgumentException("Illegal value for argument\"pseudoRandomFunction\": " + Functions.toHexString(pseudoRandomFunction));
      } else if (pseudoRandomFunctionData == null) {
         throw new NullPointerException("Argument \"pseudoRandomFunctionData\" must not be null.");
      } else {
         this.saltSource_ = saltSource;
         this.saltSourceData_ = saltSourceData;
         this.iterations_ = iterations;
         this.pseudoRandomFunction_ = pseudoRandomFunction;
         this.pseudoRandomFunctionData_ = pseudoRandomFunctionData;
      }
   }

   public Object clone() {
      try {
         PKCS5PBKD2Parameters clone = (PKCS5PBKD2Parameters)super.clone();
         clone.saltSourceData_ = (byte[])((byte[])this.saltSourceData_.clone());
         clone.pseudoRandomFunctionData_ = (byte[])((byte[])this.pseudoRandomFunctionData_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      CK_PKCS5_PBKD2_PARAMS params = new CK_PKCS5_PBKD2_PARAMS();
      params.saltSource = this.saltSource_;
      params.pSaltSourceData = this.saltSourceData_;
      params.iterations = this.iterations_;
      params.prf = this.pseudoRandomFunction_;
      params.pPrfData = this.pseudoRandomFunctionData_;
      return params;
   }

   public long getSaltSource() {
      return this.saltSource_;
   }

   public byte[] getSaltSourceData() {
      return this.saltSourceData_;
   }

   public long getIterations() {
      return this.iterations_;
   }

   public long getPseudoRandomFunction() {
      return this.pseudoRandomFunction_;
   }

   public byte[] getPseudoRandomFunctionData() {
      return this.pseudoRandomFunctionData_;
   }

   public void setSaltSource(long saltSource) {
      if (saltSource != 1L) {
         throw new IllegalArgumentException("Illegal value for argument\"saltSource\": " + Functions.toHexString(saltSource));
      } else {
         this.saltSource_ = saltSource;
      }
   }

   public void setSaltSourceData(byte[] saltSourceData) {
      if (saltSourceData == null) {
         throw new NullPointerException("Argument \"saltSourceData\" must not be null.");
      } else {
         this.saltSourceData_ = saltSourceData;
      }
   }

   public void setIterations(long iterations) {
      this.iterations_ = iterations;
   }

   public void setPseudoRandomFunction(long pseudoRandomFunction) {
      if (pseudoRandomFunction != 1L) {
         throw new IllegalArgumentException("Illegal value for argument\"pseudoRandomFunction\": " + Functions.toHexString(pseudoRandomFunction));
      } else {
         this.pseudoRandomFunction_ = pseudoRandomFunction;
      }
   }

   public void setPseudoRandomFunctionData(byte[] pseudoRandomFunctionData) {
      if (pseudoRandomFunctionData == null) {
         throw new NullPointerException("Argument \"pseudoRandomFunctionData\" must not be null.");
      } else {
         this.pseudoRandomFunctionData_ = pseudoRandomFunctionData;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Salt Source: ");
      if (this.saltSource_ == 1L) {
         buffer.append("Salt Specified");
      } else {
         buffer.append("<unknown>");
      }

      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Salt Source Data (hex): ");
      buffer.append(Functions.toHexString(this.saltSourceData_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Iterations (dec): ");
      buffer.append(this.iterations_);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Pseudo-Random Function: ");
      if (this.pseudoRandomFunction_ == 1L) {
         buffer.append("HMAC SHA-1");
      } else {
         buffer.append("<unknown>");
      }

      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Pseudo-Random Function Data (hex): ");
      buffer.append(Functions.toHexString(this.pseudoRandomFunctionData_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof PKCS5PBKD2Parameters) {
         PKCS5PBKD2Parameters other = (PKCS5PBKD2Parameters)otherObject;
         equal = this == other || this.saltSource_ == other.saltSource_ && Functions.equals(this.saltSourceData_, other.saltSourceData_) && this.iterations_ == other.iterations_ && this.pseudoRandomFunction_ == other.pseudoRandomFunction_ && Functions.equals(this.pseudoRandomFunctionData_, other.pseudoRandomFunctionData_);
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.saltSource_ ^ Functions.hashCode(this.saltSourceData_) ^ (int)this.iterations_ ^ (int)this.pseudoRandomFunction_ ^ Functions.hashCode(this.pseudoRandomFunctionData_);
   }

   public interface SaltSourceType {
      long SALT_SPECIFIED = 1L;
   }

   public interface PseudoRandomFunctionType {
      long HMAC_SHA1 = 1L;
   }
}
