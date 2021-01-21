package iaik.pkcs.pkcs11.wrapper;

public class CK_RSA_PKCS_PSS_PARAMS {
   public long hashAlg;
   public long mgf;
   public long sLen;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("hashAlg: 0x");
      buffer.append(Functions.toFullHexString(this.hashAlg));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("mgf: 0x");
      buffer.append(Functions.toFullHexString(this.mgf));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("sLen: ");
      buffer.append(this.sLen);
      return buffer.toString();
   }
}
