package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.Mechanism;
import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public abstract class RSAPkcsParameters implements Parameters {
   protected Mechanism hashAlgorithm_;
   protected long maskGenerationFunction_;

   protected RSAPkcsParameters(Mechanism hashAlgorithm, long maskGenerationFunction) {
      if (hashAlgorithm == null) {
         throw new NullPointerException("Argument \"hashAlgorithm\" must not be null.");
      } else if (maskGenerationFunction != 1L && maskGenerationFunction != 2L && maskGenerationFunction != 3L && maskGenerationFunction != 4L) {
         throw new IllegalArgumentException("Illegal value for argument\"maskGenerationFunction\": " + Functions.toHexString(maskGenerationFunction));
      } else {
         this.hashAlgorithm_ = hashAlgorithm;
         this.maskGenerationFunction_ = maskGenerationFunction;
      }
   }

   public Object clone() {
      try {
         RSAPkcsParameters clone = (RSAPkcsParameters)super.clone();
         clone.hashAlgorithm_ = (Mechanism)this.hashAlgorithm_.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Mechanism getHashAlgorithm() {
      return this.hashAlgorithm_;
   }

   public long getMaskGenerationFunction() {
      return this.maskGenerationFunction_;
   }

   public void setHashAlgorithm(Mechanism hashAlgorithm) {
      if (hashAlgorithm == null) {
         throw new NullPointerException("Argument \"hashAlgorithm\" must not be null.");
      } else {
         this.hashAlgorithm_ = hashAlgorithm;
      }
   }

   public void setMaskGenerationFunction(long maskGenerationFunction) {
      if (maskGenerationFunction != 1L && maskGenerationFunction != 2L && maskGenerationFunction != 3L && maskGenerationFunction != 4L) {
         throw new IllegalArgumentException("Illegal value for argument\"maskGenerationFunction\": " + Functions.toHexString(maskGenerationFunction));
      } else {
         this.maskGenerationFunction_ = maskGenerationFunction;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Hash Algorithm: ");
      buffer.append(this.hashAlgorithm_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Mask Generation Function: ");
      if (this.maskGenerationFunction_ == 1L) {
         buffer.append("SHA-1");
      } else if (this.maskGenerationFunction_ == 2L) {
         buffer.append("SHA-256");
      } else if (this.maskGenerationFunction_ == 3L) {
         buffer.append("SHA-384");
      } else if (this.maskGenerationFunction_ == 4L) {
         buffer.append("SHA-512");
      } else {
         buffer.append("<unknown>");
      }

      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RSAPkcsParameters) {
         RSAPkcsParameters other = (RSAPkcsParameters)otherObject;
         equal = this == other || this.hashAlgorithm_.equals(other.hashAlgorithm_) && this.maskGenerationFunction_ == other.maskGenerationFunction_;
      }

      return equal;
   }

   public int hashCode() {
      return this.hashAlgorithm_.hashCode() ^ (int)this.maskGenerationFunction_;
   }

   public interface MessageGenerationFunctionType {
      long SHA1 = 1L;
      long SHA256 = 2L;
      long SHA384 = 3L;
      long SHA512 = 4L;
   }
}
