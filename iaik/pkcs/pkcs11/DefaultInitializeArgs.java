package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.Constants;

public class DefaultInitializeArgs implements InitializeArgs {
   protected MutexHandler mutexHandler_;
   protected boolean libraryCantCreateOsThreads_;
   protected boolean osLockingOk_;
   protected Object reserved_;

   public DefaultInitializeArgs() {
      this.mutexHandler_ = null;
      this.libraryCantCreateOsThreads_ = false;
      this.osLockingOk_ = true;
      this.reserved_ = null;
   }

   public DefaultInitializeArgs(MutexHandler mutexHandler, boolean libraryCantCreateOsThreads, boolean osLockingOk) {
      this.mutexHandler_ = mutexHandler;
      this.libraryCantCreateOsThreads_ = libraryCantCreateOsThreads;
      this.osLockingOk_ = osLockingOk;
      this.reserved_ = null;
   }

   public MutexHandler getMutexHandler() {
      return this.mutexHandler_;
   }

   public boolean isLibraryCantCreateOsThreads() {
      return this.libraryCantCreateOsThreads_;
   }

   public boolean isOsLockingOk() {
      return this.osLockingOk_;
   }

   public Object getReserved() {
      return this.reserved_;
   }

   public void setMutexHandler(MutexHandler mutexHandler) {
      this.mutexHandler_ = mutexHandler;
   }

   public void setLibraryCantCreateOsThreads(boolean libraryCantCreateOsThreads) {
      this.libraryCantCreateOsThreads_ = libraryCantCreateOsThreads;
   }

   public void setOsLockingOk(boolean osLockingOk) {
      this.osLockingOk_ = osLockingOk;
   }

   public void setReserved(Object reserved) {
      this.reserved_ = reserved;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("Mutex Handler: ");
      buffer.append(this.mutexHandler_ != null ? "present" : "not present");
      buffer.append(Constants.NEWLINE);
      buffer.append("Library can't create OS-Threads: ");
      buffer.append(this.libraryCantCreateOsThreads_);
      buffer.append(Constants.NEWLINE);
      buffer.append("OS-Locking OK: ");
      buffer.append(this.osLockingOk_);
      buffer.append(Constants.NEWLINE);
      buffer.append("The reserved parameter is: ");
      buffer.append(this.reserved_ != null ? this.reserved_.toString() : "null");
      return buffer.toString();
   }
}
