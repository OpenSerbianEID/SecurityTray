package iaik.pkcs.pkcs11.wrapper;

public class CK_SSL3_MASTER_KEY_DERIVE_PARAMS {
   public CK_SSL3_RANDOM_DATA RandomInfo;
   public CK_VERSION pVersion;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("RandomInfo: ");
      buffer.append(this.RandomInfo);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pVersion: ");
      buffer.append(this.pVersion);
      return buffer.toString();
   }
}
