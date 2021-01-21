package iaik.pkcs.pkcs11.wrapper;

public class CK_SSL3_RANDOM_DATA {
   public byte[] pClientRandom;
   public byte[] pServerRandom;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("pClientRandom: ");
      buffer.append(Functions.toHexString(this.pClientRandom));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulClientRandomLen: ");
      buffer.append(this.pClientRandom.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pServerRandom: ");
      buffer.append(Functions.toHexString(this.pServerRandom));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulServerRandomLen: ");
      buffer.append(this.pServerRandom.length);
      return buffer.toString();
   }
}
