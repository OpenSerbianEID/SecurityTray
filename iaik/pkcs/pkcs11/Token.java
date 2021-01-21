package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.CK_MECHANISM_INFO;
import iaik.pkcs.pkcs11.wrapper.CK_NOTIFY;
import iaik.pkcs.pkcs11.wrapper.CK_TOKEN_INFO;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

public class Token {
   protected Slot slot_;

   protected Token(Slot slot) {
      if (slot == null) {
         throw new NullPointerException("Argument \"slot\" must not be null.");
      } else {
         this.slot_ = slot;
      }
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Token) {
         Token other = (Token)otherObject;
         equal = this == other || this.slot_.equals(other.slot_);
      }

      return equal;
   }

   public Slot getSlot() {
      return this.slot_;
   }

   public long getTokenID() {
      return this.slot_.getSlotID();
   }

   public TokenInfo getTokenInfo() throws TokenException {
      CK_TOKEN_INFO ckTokenInfo = this.slot_.getModule().getPKCS11Module().C_GetTokenInfo(this.slot_.getSlotID());
      return new TokenInfo(ckTokenInfo);
   }

   public Mechanism[] getMechanismList() throws TokenException {
      long[] mechanismIdList = this.slot_.getModule().getPKCS11Module().C_GetMechanismList(this.slot_.getSlotID());
      Mechanism[] mechanisms = new Mechanism[mechanismIdList.length];

      for(int i = 0; i < mechanisms.length; ++i) {
         mechanisms[i] = new Mechanism(mechanismIdList[i]);
      }

      return mechanisms;
   }

   public MechanismInfo getMechanismInfo(Mechanism mechanism) throws TokenException {
      long mechanismCode = mechanism.getMechanismCode();
      CK_MECHANISM_INFO ckMechanismInfo = this.slot_.getModule().getPKCS11Module().C_GetMechanismInfo(this.slot_.getSlotID(), mechanismCode);
      return new MechanismInfo(ckMechanismInfo);
   }

   public int hashCode() {
      return this.slot_.hashCode();
   }

   public void initToken(char[] pin, String label) throws TokenException {
      char[] labelChars = Util.toPaddedCharArray(label, 32, ' ');
      this.slot_.getModule().getPKCS11Module().C_InitToken(this.slot_.getSlotID(), pin, labelChars);
   }

   public Session openSession(boolean serialSession, boolean rwSession, Object application, final Notify notify) throws TokenException {
      long flags = 0L;
      flags |= serialSession ? 4L : 0L;
      flags |= rwSession ? 2L : 0L;
      final Session newSession = new Session(this, -1L);
      CK_NOTIFY ckNotify = null;
      if (notify != null) {
         ckNotify = new CK_NOTIFY() {
            public void CK_NOTIFY(long hSession, long event, Object pApplication) throws PKCS11Exception {
               boolean surrender = (event & 0L) != 0L;
               notify.notify(newSession, surrender, pApplication);
            }
         };
      }

      long sessionHandle = this.slot_.getModule().getPKCS11Module().C_OpenSession(this.slot_.getSlotID(), flags, application, ckNotify);
      newSession.sessionHandle_ = sessionHandle;
      return newSession;
   }

   public void closeAllSessions() throws TokenException {
      this.slot_.getModule().getPKCS11Module().C_CloseAllSessions(this.slot_.getSlotID());
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("Token in Slot: ");
      buffer.append(this.slot_.toString());
      return buffer.toString();
   }

   public interface SessionReadWriteBehavior {
      boolean RO_SESSION = false;
      boolean RW_SESSION = true;
   }

   public interface SessionType {
      boolean PARALLEL_SESSION = false;
      boolean SERIAL_SESSION = true;
   }
}
