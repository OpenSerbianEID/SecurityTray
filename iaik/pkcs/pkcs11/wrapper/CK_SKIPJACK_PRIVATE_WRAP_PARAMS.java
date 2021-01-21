package iaik.pkcs.pkcs11.wrapper;

public class CK_SKIPJACK_PRIVATE_WRAP_PARAMS {
   public byte[] pPassword;
   public byte[] pPublicData;
   public byte[] pRandomA;
   public byte[] pPrimeP;
   public byte[] pBaseG;
   public byte[] pSubprimeQ;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("ulPasswordLen: ");
      buffer.append(this.pPassword.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pPassword: ");
      buffer.append(Functions.toHexString(this.pPassword));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulPublicDataLen: ");
      buffer.append(this.pPublicData.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pPublicData: ");
      buffer.append(Functions.toHexString(this.pPublicData));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulPAndGLen: ");
      buffer.append(this.pPrimeP.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulQLen: ");
      buffer.append(this.pSubprimeQ.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulRandomLen: ");
      buffer.append(this.pRandomA.length);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pRandomA: ");
      buffer.append(Functions.toHexString(this.pRandomA));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pPrimeP: ");
      buffer.append(Functions.toHexString(this.pPrimeP));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pBaseG: ");
      buffer.append(Functions.toHexString(this.pBaseG));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pSubprimeQ: ");
      buffer.append(Functions.toHexString(this.pSubprimeQ));
      return buffer.toString();
   }
}
