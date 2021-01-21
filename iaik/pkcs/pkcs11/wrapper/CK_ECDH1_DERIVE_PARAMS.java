package iaik.pkcs.pkcs11.wrapper;

public class CK_ECDH1_DERIVE_PARAMS {
   public long kdf;
   public byte[] pSharedData;
   public byte[] pPublicData;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("kdf: 0x");
      buffer.append(Functions.toFullHexString(this.kdf));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pSharedDataLen: ");
      buffer.append(this.pSharedData.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pSharedData: ");
      buffer.append(Functions.toHexString(this.pSharedData));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pPublicDataLen: ");
      buffer.append(this.pPublicData.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pPublicData: ");
      buffer.append(Functions.toHexString(this.pPublicData));
      return buffer.toString();
   }
}
