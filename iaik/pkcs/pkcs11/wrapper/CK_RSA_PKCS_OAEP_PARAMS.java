package iaik.pkcs.pkcs11.wrapper;

public class CK_RSA_PKCS_OAEP_PARAMS {
   public long hashAlg;
   public long mgf;
   public long source;
   public byte[] pSourceData;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("hashAlg: ");
      buffer.append(this.hashAlg);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("mgf: ");
      buffer.append(this.mgf);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("source: ");
      buffer.append(this.source);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pSourceDataLen: ");
      buffer.append(Functions.toHexString(this.pSourceData));
      return buffer.toString();
   }
}
