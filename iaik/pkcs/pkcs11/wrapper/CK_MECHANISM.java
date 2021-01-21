package iaik.pkcs.pkcs11.wrapper;

public class CK_MECHANISM {
   public long mechanism;
   public Object pParameter;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("mechanism: ");
      buffer.append(this.mechanism);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("pParameter: ");
      buffer.append(this.pParameter.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulParameterLen: ??");
      return buffer.toString();
   }
}
