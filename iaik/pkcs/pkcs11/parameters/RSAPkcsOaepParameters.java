package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.Mechanism;
import iaik.pkcs.pkcs11.wrapper.CK_RSA_PKCS_OAEP_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class RSAPkcsOaepParameters extends RSAPkcsParameters {
   protected long source_;
   protected byte[] sourceData_;

   public RSAPkcsOaepParameters(Mechanism hashAlgorithm, long maskGenerationFunction, long source, byte[] sourceData) {
      super(hashAlgorithm, maskGenerationFunction);
      if (source != 0L && source != 1L) {
         throw new IllegalArgumentException("Illegal value for argument\"source\": " + Functions.toHexString(source));
      } else {
         this.source_ = source;
         this.sourceData_ = sourceData;
      }
   }

   public Object clone() {
      RSAPkcsOaepParameters clone = (RSAPkcsOaepParameters)super.clone();
      clone.sourceData_ = (byte[])((byte[])this.sourceData_.clone());
      return clone;
   }

   public Object getPKCS11ParamsObject() {
      CK_RSA_PKCS_OAEP_PARAMS params = new CK_RSA_PKCS_OAEP_PARAMS();
      params.hashAlg = this.hashAlgorithm_.getMechanismCode();
      params.mgf = this.maskGenerationFunction_;
      params.source = this.source_;
      params.pSourceData = this.sourceData_;
      return params;
   }

   public long getSource() {
      return this.source_;
   }

   public byte[] getSourceData() {
      return this.sourceData_;
   }

   public void setSource(long source) {
      if (source != 0L && source != 1L) {
         throw new IllegalArgumentException("Illegal value for argument\"source\": " + Functions.toHexString(source));
      } else {
         this.source_ = source;
      }
   }

   public void setSourceData(byte[] sourceData) {
      this.sourceData_ = sourceData;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Source: ");
      if (this.source_ == 0L) {
         buffer.append("Empty");
      } else if (this.source_ == 1L) {
         buffer.append("Data Specified");
      } else {
         buffer.append("<unknown>");
      }

      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Source Data (hex): ");
      buffer.append(Functions.toHexString(this.sourceData_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RSAPkcsOaepParameters) {
         RSAPkcsOaepParameters other = (RSAPkcsOaepParameters)otherObject;
         equal = this == other || super.equals(other) && this.source_ == other.source_ && Functions.equals(this.sourceData_, other.sourceData_);
      }

      return equal;
   }

   public int hashCode() {
      return super.hashCode() ^ (int)this.source_ ^ Functions.hashCode(this.sourceData_);
   }

   public interface SourceType {
      long EMPTY = 0L;
      long DATA_SPECIFIED = 1L;
   }
}
