package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.CK_CREATEMUTEX;
import iaik.pkcs.pkcs11.wrapper.CK_C_INITIALIZE_ARGS;
import iaik.pkcs.pkcs11.wrapper.CK_DESTROYMUTEX;
import iaik.pkcs.pkcs11.wrapper.CK_INFO;
import iaik.pkcs.pkcs11.wrapper.CK_LOCKMUTEX;
import iaik.pkcs.pkcs11.wrapper.CK_UNLOCKMUTEX;
import iaik.pkcs.pkcs11.wrapper.PKCS11;
import iaik.pkcs.pkcs11.wrapper.PKCS11Connector;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;
import java.io.IOException;

public class Module {
   protected PKCS11 pkcs11Module_;

   protected Module(PKCS11 pkcs11Module) {
      if (pkcs11Module == null) {
         throw new NullPointerException("Argument \"pkcs11Module\" must not be null.");
      } else {
         this.pkcs11Module_ = pkcs11Module;
      }
   }

   public static Module getInstance(String pkcs11ModuleName) throws IOException {
      if (pkcs11ModuleName == null) {
         throw new NullPointerException("Argument \"pkcs11ModuleName\" must not be null.");
      } else {
         PKCS11 pkcs11Module = PKCS11Connector.connectToPKCS11Module(pkcs11ModuleName);
         return new Module(pkcs11Module);
      }
   }

   public static Module getInstance(String pkcs11ModuleName, String pkcs11WrapperPath) throws IOException {
      if (pkcs11ModuleName == null) {
         throw new NullPointerException("Argument \"pkcs11ModuleName\" must not be null.");
      } else {
         PKCS11 pkcs11Module = PKCS11Connector.connectToPKCS11Module(pkcs11ModuleName, pkcs11WrapperPath);
         return new Module(pkcs11Module);
      }
   }

   public Info getInfo() throws TokenException {
      CK_INFO ckInfo = this.pkcs11Module_.C_GetInfo();
      return new Info(ckInfo);
   }

   public void initialize(InitializeArgs initArgs) throws TokenException {
      CK_C_INITIALIZE_ARGS wrapperInitArgs = null;
      if (initArgs != null) {
         final MutexHandler mutexHandler = initArgs.getMutexHandler();
         wrapperInitArgs = new CK_C_INITIALIZE_ARGS();
         if (mutexHandler != null) {
            wrapperInitArgs.CreateMutex = new CK_CREATEMUTEX() {
               public Object CK_CREATEMUTEX() throws PKCS11Exception {
                  return mutexHandler.createMutex();
               }
            };
            wrapperInitArgs.DestroyMutex = new CK_DESTROYMUTEX() {
               public void CK_DESTROYMUTEX(Object pMutex) throws PKCS11Exception {
                  mutexHandler.destroyMutex(pMutex);
               }
            };
            wrapperInitArgs.LockMutex = new CK_LOCKMUTEX() {
               public void CK_LOCKMUTEX(Object pMutex) throws PKCS11Exception {
                  mutexHandler.lockMutex(pMutex);
               }
            };
            wrapperInitArgs.UnlockMutex = new CK_UNLOCKMUTEX() {
               public void CK_UNLOCKMUTEX(Object pMutex) throws PKCS11Exception {
                  mutexHandler.unlockMutex(pMutex);
               }
            };
         } else {
            wrapperInitArgs.CreateMutex = null;
            wrapperInitArgs.DestroyMutex = null;
            wrapperInitArgs.LockMutex = null;
            wrapperInitArgs.UnlockMutex = null;
         }

         if (initArgs.isLibraryCantCreateOsThreads()) {
            wrapperInitArgs.flags |= 1L;
         }

         if (initArgs.isOsLockingOk()) {
            wrapperInitArgs.flags |= 2L;
         }

         wrapperInitArgs.pReserved = initArgs.getReserved();
      }

      this.pkcs11Module_.C_Initialize(wrapperInitArgs);
   }

   public void finalize(Object args) throws TokenException {
      this.pkcs11Module_.C_Finalize(args);
   }

   public Slot[] getSlotList(boolean tokenPresent) throws TokenException {
      long[] slotIDs = this.pkcs11Module_.C_GetSlotList(tokenPresent);
      Slot[] slots = new Slot[slotIDs.length];

      for(int i = 0; i < slots.length; ++i) {
         slots[i] = new Slot(this, slotIDs[i]);
      }

      return slots;
   }

   public Slot waitForSlotEvent(boolean dontBlock, Object reserved) throws TokenException {
      long flags = dontBlock ? 1L : 0L;
      long slotID = this.pkcs11Module_.C_WaitForSlotEvent(flags, reserved);
      return new Slot(this, slotID);
   }

   public PKCS11 getPKCS11Module() {
      return this.pkcs11Module_;
   }

   public String toString() {
      return this.pkcs11Module_ != null ? this.pkcs11Module_.toString() : null;
   }

   public void finalize() throws Throwable {
      this.pkcs11Module_.finalize();
      super.finalize();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Module) {
         Module other = (Module)otherObject;
         equal = this == other || this.pkcs11Module_.equals(other.pkcs11Module_);
      }

      return equal;
   }

   public int hashCode() {
      return this.pkcs11Module_.hashCode();
   }

   public interface WaitingBehavior {
      boolean BLOCK = false;
      boolean DONT_BLOCK = true;
   }

   public interface SlotRequirement {
      boolean ALL_SLOTS = false;
      boolean TOKEN_PRESENT = true;
   }
}
