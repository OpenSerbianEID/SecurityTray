package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

public class DefaultMutexHandler implements MutexHandler {
   public Object createMutex() throws PKCS11Exception {
      return new DefaultMutexHandler.Mutex();
   }

   public void destroyMutex(Object mutex) throws PKCS11Exception {
   }

   public void lockMutex(Object mutex) throws PKCS11Exception {
      try {
         DefaultMutexHandler.Mutex castedMutex = (DefaultMutexHandler.Mutex)mutex;

         while(true) {
            try {
               castedMutex.lock();
               return;
            } catch (InterruptedException var4) {
            }
         }
      } catch (ClassCastException var5) {
         throw new PKCS11Exception(416L);
      }
   }

   public void unlockMutex(Object mutex) throws PKCS11Exception {
      try {
         DefaultMutexHandler.Mutex castedMutex = (DefaultMutexHandler.Mutex)mutex;
         castedMutex.unlock();
      } catch (ClassCastException var3) {
         throw new PKCS11Exception(416L);
      }
   }

   class Mutex {
      protected boolean locked_ = false;

      public Mutex() {
      }

      public synchronized void lock() throws InterruptedException {
         if (Thread.interrupted()) {
            throw new InterruptedException();
         } else {
            try {
               while(this.locked_) {
                  this.wait();
               }

               this.locked_ = true;
            } catch (InterruptedException var2) {
               this.notify();
               throw var2;
            }
         }
      }

      public synchronized boolean unlock() {
         boolean previousState = this.locked_;
         this.locked_ = false;
         this.notify();
         return previousState;
      }

      public String toString() {
         return this.locked_ ? "locked" : "unlocked";
      }
   }
}
