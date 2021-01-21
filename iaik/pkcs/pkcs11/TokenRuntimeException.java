package iaik.pkcs.pkcs11;

public class TokenRuntimeException extends RuntimeException {
   protected Exception encapsulatedException_;

   public TokenRuntimeException() {
   }

   public TokenRuntimeException(String message) {
      super(message);
   }

   public TokenRuntimeException(Exception encapsulatedException) {
      this.encapsulatedException_ = encapsulatedException;
   }

   public TokenRuntimeException(String message, Exception encapsulatedException) {
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