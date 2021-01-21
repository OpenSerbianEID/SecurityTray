package iaik.pkcs.pkcs11.wrapper;

public class CK_VERSION {
   public byte major;
   public byte minor;

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(this.major);
      buffer.append('.');
      if (this.minor < 10) {
         buffer.append('0');
      }

      buffer.append(this.minor);
      return buffer.toString();
   }
}
