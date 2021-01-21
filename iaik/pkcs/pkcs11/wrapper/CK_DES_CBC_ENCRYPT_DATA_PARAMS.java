package iaik.pkcs.pkcs11.wrapper;

public class CK_DES_CBC_ENCRYPT_DATA_PARAMS {
   public byte[] iv;
   public byte[] pData;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("iv: ");
      buffer.append(Functions.toHexString(this.iv));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pData: ");
      buffer.append(Functions.toHexString(this.pData));
      return buffer.toString();
   }
}
