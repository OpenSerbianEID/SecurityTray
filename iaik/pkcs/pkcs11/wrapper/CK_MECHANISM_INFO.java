package iaik.pkcs.pkcs11.wrapper;

public class CK_MECHANISM_INFO {
   public long ulMinKeySize;
   public long ulMaxKeySize;
   public long flags;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("ulMinKeySize: ");
      buffer.append(String.valueOf(this.ulMinKeySize));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulMaxKeySize: ");
      buffer.append(String.valueOf(this.ulMaxKeySize));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("flags: ");
      buffer.append(String.valueOf(this.flags));
      buffer.append(" = ");
      buffer.append(Functions.mechanismInfoFlagsToString(this.flags));
      return buffer.toString();
   }
}
