package iaik.pkcs.pkcs11.wrapper;

import iaik.pkcs.pkcs11.TokenRuntimeException;

public class PKCS11RuntimeException extends TokenRuntimeException {
   public PKCS11RuntimeException() {
   }

   public PKCS11RuntimeException(String message) {
      super(message);
   }

   public PKCS11RuntimeException(Exception encapsulatedException) {
      super(encapsulatedException);
   }

   public PKCS11RuntimeException(String message, Exception encapsulatedException) {
      super(message, encapsulatedException);
   }
}
