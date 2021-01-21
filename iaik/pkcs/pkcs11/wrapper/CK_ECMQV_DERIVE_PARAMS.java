package iaik.pkcs.pkcs11.wrapper;

public class CK_ECMQV_DERIVE_PARAMS {
   public long kdf;
   public byte[] pSharedData;
   public byte[] pPublicData;
   public long ulPrivateDataLen;
   public long hPrivateData;
   public byte[] pPublicData2;
   public long publicKey;

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
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pPrivateDataLen: ");
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
      buffer.append("pPublicDat2a: ");
      buffer.append(Functions.toHexString(this.pPublicData2));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("publicKey: ");
      buffer.append(this.publicKey);
      return buffer.toString();
   }
}