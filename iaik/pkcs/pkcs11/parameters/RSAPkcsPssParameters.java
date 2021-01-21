package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.Mechanism;
import iaik.pkcs.pkcs11.wrapper.CK_RSA_PKCS_PSS_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class RSAPkcsPssParameters extends RSAPkcsParameters {
   protected long saltLength_;

   public RSAPkcsPssParameters(Mechanism hashAlgorithm, long maskGenerationFunction, long saltLength) {
      super(hashAlgorithm, maskGenerationFunction);
      this.saltLength_ = saltLength;
   }

   public Object getPKCS11ParamsObject() {
      CK_RSA_PKCS_PSS_PARAMS params = new CK_RSA_PKCS_PSS_PARAMS();
      params.hashAlg = this.hashAlgorithm_.getMechanismCode();
      params.mgf = this.maskGenerationFunction_;
      params.sLen = this.saltLength_;
      return params;
   }

   public long getSaltLength() {
      return this.saltLength_;
   }

   public void setSaltLength(long saltLength) {
      this.saltLength_ = saltLength;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Salt Length (octets, dec): ");
      buffer.append(this.saltLength_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RSAPkcsPssParameters) {
         RSAPkcsPssParameters other = (RSAPkcsPssParameters)otherObject;
         equal = this == other || super.equals(other) && this.saltLength_ == other.saltLength_;
      }

      return equal;
   }

   public int hashCode() {
      return super.hashCode() ^ (int)this.saltLength_;
   }
}
