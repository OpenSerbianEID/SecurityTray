package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public abstract class CbcEncryptDataParameters implements Parameters {
   protected int blockSize_;
   protected byte[] iv_;
   protected byte[] data_;

   protected CbcEncryptDataParameters(int blockSize, byte[] iv, byte[] data) {
      if (iv == null) {
         throw new NullPointerException("Argument \"iv\" must not be null.");
      } else if (iv.length != blockSize) {
         throw new IllegalArgumentException("Argument \"iv\" must have length blockSize.");
      } else if (data == null) {
         throw new NullPointerException("Argument \"data\" must not be null.");
      } else if (data.length % blockSize != 0) {
         throw new IllegalArgumentException("Argument \"data\" must have a length that is a multiple of blockSize.");
      } else {
         this.blockSize_ = blockSize;
         this.iv_ = iv;
         this.data_ = data;
      }
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var2);
      }
   }

   public int getBlockSize() {
      return this.blockSize_;
   }

   public byte[] getInitializationVector() {
      return this.iv_;
   }

   public void setInitializationVector(byte[] iv) {
      if (iv == null) {
         throw new NullPointerException("Argument \"iv\" must not be null.");
      } else if (iv.length != this.blockSize_) {
         throw new IllegalArgumentException("Argument \"iv\" must have length getBlockSize().");
      } else {
         this.iv_ = iv;
      }
   }

   public byte[] getData() {
      return this.data_;
   }

   public void setData(byte[] data) {
      if (data == null) {
         throw new NullPointerException("Argument \"data\" must not be null.");
      } else if (data.length % this.blockSize_ != 0) {
         throw new IllegalArgumentException("Argument \"data\" must have a length that is a multiple of getBlockSize().");
      } else {
         this.data_ = data;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Initialization Vector (hex): ");
      buffer.append(Functions.toHexString(this.iv_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Data (hex): ");
      buffer.append(Functions.toHexString(this.data_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof CbcEncryptDataParameters) {
         CbcEncryptDataParameters other = (CbcEncryptDataParameters)otherObject;
         equal = this == other || this.blockSize_ == other.blockSize_ && Functions.equals(this.iv_, other.iv_) && Functions.equals(this.data_, other.data_);
      }

      return equal;
   }

   public int hashCode() {
      return Functions.hashCode(this.iv_) ^ Functions.hashCode(this.data_);
   }
}
