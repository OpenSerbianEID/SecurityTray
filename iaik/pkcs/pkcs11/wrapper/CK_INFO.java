package iaik.pkcs.pkcs11.wrapper;

public class CK_INFO {
   public CK_VERSION cryptokiVersion;
   public char[] manufacturerID;
   public long flags;
   public char[] libraryDescription;
   public CK_VERSION libraryVersion;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("cryptokiVersion: ");
      buffer.append(this.cryptokiVersion.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("manufacturerID: ");
      buffer.append(new String(this.manufacturerID));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("flags: ");
      buffer.append(Functions.toBinaryString(this.flags));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("libraryDescription: ");
      buffer.append(new String(this.libraryDescription));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("libraryVersion: ");
      buffer.append(this.libraryVersion.toString());
      return buffer.toString();
   }
}
