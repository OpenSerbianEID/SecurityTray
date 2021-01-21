package iaik.pkcs.pkcs11.wrapper;

public class CK_SLOT_INFO {
   public char[] slotDescription;
   public char[] manufacturerID;
   public long flags;
   public CK_VERSION hardwareVersion;
   public CK_VERSION firmwareVersion;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("slotDescription: ");
      buffer.append(new String(this.slotDescription));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("manufacturerID: ");
      buffer.append(new String(this.manufacturerID));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("flags: ");
      buffer.append(Functions.slotInfoFlagsToString(this.flags));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("hardwareVersion: ");
      buffer.append(this.hardwareVersion.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("firmwareVersion: ");
      buffer.append(this.firmwareVersion.toString());
      return buffer.toString();
   }
}
