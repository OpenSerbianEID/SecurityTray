package iaik.pkcs.pkcs11.wrapper;

public class CK_RC2_CBC_PARAMS {
   public long ulEffectiveBits;
   public byte[] iv;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("ulEffectiveBits: ");
      buffer.append(this.ulEffectiveBits);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("iv: ");
      buffer.append(Functions.toHexString(this.iv));
      return buffer.toString();
   }
}
