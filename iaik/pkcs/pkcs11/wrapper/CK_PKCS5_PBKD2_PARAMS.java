package iaik.pkcs.pkcs11.wrapper;

public class CK_PKCS5_PBKD2_PARAMS {
   public long saltSource;
   public byte[] pSaltSourceData;
   public long iterations;
   public long prf;
   public byte[] pPrfData;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("saltSource: ");
      buffer.append(this.saltSource);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pSaltSourceData: ");
      buffer.append(Functions.toHexString(this.pSaltSourceData));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulSaltSourceDataLen: ");
      buffer.append(this.pSaltSourceData.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("iterations: ");
      buffer.append(this.iterations);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("prf: ");
      buffer.append(this.prf);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pPrfData: ");
      buffer.append(Functions.toHexString(this.pPrfData));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulPrfDataLen: ");
      buffer.append(this.pPrfData.length);
      return buffer.toString();
   }
}
