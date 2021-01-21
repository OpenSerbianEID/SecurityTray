package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

public interface Notify {
   long CANCEL = 1L;

   void notify(Session var1, boolean var2, Object var3) throws PKCS11Exception;
}
