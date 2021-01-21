package iaik.pkcs.pkcs11.wrapper;

public class CK_SESSION_INFO {
   public long slotID;
   public long state;
   public long flags;
   public long ulDeviceError;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("slotID: ");
      buffer.append(String.valueOf(this.slotID));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("state: ");
      buffer.append(Functions.sessionStateToString(this.state));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("flags: ");
      buffer.append(Functions.sessionInfoFlagsToString(this.flags));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ulDeviceError: ");
      buffer.append(Functions.toHexString(this.ulDeviceError));
      return buffer.toString();
   }
}
