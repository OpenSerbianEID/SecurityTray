package iaik.pkcs.pkcs11.wrapper;

public class CK_RC5_CBC_PARAMS {
   public long ulWordsize;
   public long ulRounds;
   public byte[] pIv;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("ulWordsize: ");
      buffer.append(this.ulWordsize);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulRounds: ");
      buffer.append(this.ulRounds);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pIv: ");
      buffer.append(Functions.toHexString(this.pIv));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulIv: ");
      buffer.append(this.pIv.length);
      return buffer.toString();
   }
}
