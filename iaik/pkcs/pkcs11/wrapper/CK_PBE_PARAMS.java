package iaik.pkcs.pkcs11.wrapper;

public class CK_PBE_PARAMS {
   public char[] pInitVector;
   public char[] pPassword;
   public char[] pSalt;
   public long ulIteration;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("pInitVector: ");
      buffer.append(this.pInitVector);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulPasswordLen: ");
      buffer.append(this.pPassword.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pPassword: ");
      buffer.append(this.pPassword);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulSaltLen: ");
      buffer.append(this.pSalt.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pSalt: ");
      buffer.append(this.pSalt);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulIteration: ");
      buffer.append(this.ulIteration);
      return buffer.toString();
   }
}
