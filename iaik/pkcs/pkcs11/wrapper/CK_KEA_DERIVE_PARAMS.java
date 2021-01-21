package iaik.pkcs.pkcs11.wrapper;

public class CK_KEA_DERIVE_PARAMS {
   public boolean isSender;
   public byte[] pRandomA;
   public byte[] pRandomB;
   public byte[] pPublicData;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("isSender: ");
      buffer.append(this.isSender);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulRandomLen: ");
      buffer.append(this.pRandomA.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pRandomA: ");
      buffer.append(Functions.toHexString(this.pRandomA));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pRandomB: ");
      buffer.append(Functions.toHexString(this.pRandomB));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulPublicDataLen: ");
      buffer.append(this.pPublicData.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pPublicData: ");
      buffer.append(Functions.toHexString(this.pPublicData));
      return buffer.toString();
   }
}
