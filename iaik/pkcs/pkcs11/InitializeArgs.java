package iaik.pkcs.pkcs11;

public interface InitializeArgs {
   MutexHandler getMutexHandler();

   boolean isLibraryCantCreateOsThreads();

   boolean isOsLockingOk();

   Object getReserved();
}
