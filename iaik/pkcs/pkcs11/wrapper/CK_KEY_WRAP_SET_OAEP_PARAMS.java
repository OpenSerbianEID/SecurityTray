package iaik.pkcs.pkcs11.wrapper;

public class CK_KEY_WRAP_SET_OAEP_PARAMS {
   public byte bBC;
   public byte[] pX;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("bBC: ");
      buffer.append(this.bBC);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pX: ");
      buffer.append(Functions.toBinaryString(this.pX));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulXLen: ");
      buffer.append(this.pX.length);
      return buffer.toString();
   }
}
