package iaik.pkcs.pkcs11.wrapper;

public class CK_X9_42_DH1_DERIVE_PARAMS {
   public long kdf;
   public byte[] pOtherInfo;
   public byte[] pPublicData;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("kdf: 0x");
      buffer.append(Functions.toFullHexString(this.kdf));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pOtherInfoLen: ");
      buffer.append(this.pOtherInfo.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pOtherInfo: ");
      buffer.append(Functions.toHexString(this.pOtherInfo));
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
