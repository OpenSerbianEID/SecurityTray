package iaik.pkcs.pkcs11.wrapper;

public class CK_X9_42_DH2_DERIVE_PARAMS {
   public long kdf;
   public byte[] pOtherInfo;
   public byte[] pPublicData;
   public long ulPrivateDataLen;
   public long hPrivateData;
   public byte[] pPublicData2;

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
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulPrivateDataLen: ");
      buffer.append(this.ulPrivateDataLen);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("hPrivateData: ");
      buffer.append(this.hPrivateData);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pPublicDataLen2: ");
      buffer.append(this.pPublicData2.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pPublicData2: ");
      buffer.append(Functions.toHexString(this.pPublicData2));
      return buffer.toString();
   }
}
