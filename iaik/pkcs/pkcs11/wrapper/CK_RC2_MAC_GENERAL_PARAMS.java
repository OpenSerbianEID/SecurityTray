package iaik.pkcs.pkcs11.wrapper;

public class CK_RC2_MAC_GENERAL_PARAMS {
   public long ulEffectiveBits;
   public long ulMacLength;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("ulEffectiveBits: ");
      buffer.append(this.ulEffectiveBits);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulEffectiveBits: ");
      buffer.append(this.ulEffectiveBits);
      return buffer.toString();
   }
}
