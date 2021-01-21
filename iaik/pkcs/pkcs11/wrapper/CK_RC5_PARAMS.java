package iaik.pkcs.pkcs11.wrapper;

public class CK_RC5_PARAMS {
   public long ulWordsize;
   public long ulRounds;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("ulWordsize: ");
      buffer.append(this.ulWordsize);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulRounds: ");
      buffer.append(this.ulRounds);
      return buffer.toString();
   }
}
