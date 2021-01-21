package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

public interface MutexHandler {
   Object createMutex() throws PKCS11Exception;

   void destroyMutex(Object var1) throws PKCS11Exception;

   void lockMutex(Object var1) throws PKCS11Exception;

   void unlockMutex(Object var1) throws PKCS11Exception;
}
