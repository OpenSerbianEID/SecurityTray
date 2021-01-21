package iaik.pkcs.pkcs11.wrapper;

public class CK_SSL3_KEY_MAT_PARAMS {
   public long ulMacSizeInBits;
   public long ulKeySizeInBits;
   public long ulIVSizeInBits;
   public boolean bIsExport;
   public CK_SSL3_RANDOM_DATA RandomInfo;
   public CK_SSL3_KEY_MAT_OUT pReturnedKeyMaterial;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("ulMacSizeInBits: ");
      buffer.append(this.ulMacSizeInBits);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulKeySizeInBits: ");
      buffer.append(this.ulKeySizeInBits);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulIVSizeInBits: ");
      buffer.append(this.ulIVSizeInBits);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("bIsExport: ");
      buffer.append(this.bIsExport);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("RandomInfo: ");
      buffer.append(this.RandomInfo);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pReturnedKeyMaterial: ");
      buffer.append(this.pReturnedKeyMaterial);
      return buffer.toString();
   }
}
