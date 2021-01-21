package iaik.pkcs.pkcs11.wrapper;

public class CK_C_INITIALIZE_ARGS {
   public CK_CREATEMUTEX CreateMutex;
   public CK_DESTROYMUTEX DestroyMutex;
   public CK_LOCKMUTEX LockMutex;
   public CK_UNLOCKMUTEX UnlockMutex;
   public long flags;
   public Object pReserved;
}
