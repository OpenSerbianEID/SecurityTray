package iaik.pkcs.pkcs11;

public class TokenException extends Exception {
   protected Exception encapsulatedException_;

   public TokenException() {
   }

   public TokenException(String message) {
      super(message);
   }

   public TokenException(Exception encapsulatedException) {
      this.encapsulatedException_ = encapsulatedException;
   }

   public TokenException(String message, Exception encapsulatedException) {
      super(message);
      this.encapsulatedException_ = encapsulatedException;
   }

   public Exception getEncapsulatedException() {
      return this.encapsulatedException_;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(super.toString());
      if (this.encapsulatedException_ != null) {
         buffer.append(", Encasulated Exception: ");
         buffer.append(this.encapsulatedException_.toString());
      }

      return buffer.toString();
   }
}
