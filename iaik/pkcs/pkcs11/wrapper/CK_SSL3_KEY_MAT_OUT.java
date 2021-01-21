package iaik.pkcs.pkcs11.wrapper;

public class CK_SSL3_KEY_MAT_OUT {
   public long hClientMacSecret;
   public long hServerMacSecret;
   public long hClientKey;
   public long hServerKey;
   public byte[] pIVClient;
   public byte[] pIVServer;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("hClientMacSecret: ");
      buffer.append(this.hClientMacSecret);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("hServerMacSecret: ");
      buffer.append(this.hServerMacSecret);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("hClientKey: ");
      buffer.append(this.hClientKey);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("hServerKey: ");
      buffer.append(this.hServerKey);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pIVClient: ");
      buffer.append(Functions.toHexString(this.pIVClient));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pIVServer: ");
      buffer.append(Functions.toHexString(this.pIVServer));
      return buffer.toString();
   }
}
