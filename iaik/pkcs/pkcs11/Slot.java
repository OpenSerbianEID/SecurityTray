package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.CK_SLOT_INFO;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class Slot {
   protected Module module_;
   protected long slotID_;

   protected Slot(Module module, long slotID) {
      if (module == null) {
         throw new NullPointerException("Argument \"module\" must not be null.");
      } else {
         this.module_ = module;
         this.slotID_ = slotID;
      }
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Slot) {
         Slot other = (Slot)otherObject;
         equal = this == other || this.slotID_ == other.slotID_ && this.module_.equals(other.module_);
      }

      return equal;
   }

   public Module getModule() {
      return this.module_;
   }

   public long getSlotID() {
      return this.slotID_;
   }

   public SlotInfo getSlotInfo() throws TokenException {
      CK_SLOT_INFO ckSlotInfo = this.module_.getPKCS11Module().C_GetSlotInfo(this.slotID_);
      return new SlotInfo(ckSlotInfo);
   }

   public Token getToken() throws TokenException {
      Token token = null;
      if (this.getSlotInfo().isTokenPresent()) {
         token = new Token(this);
      }

      return token;
   }

   public int hashCode() {
      return (int)this.slotID_;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("Slot ID: ");
      buffer.append("0x");
      buffer.append(Functions.toHexString(this.slotID_));
      buffer.append(Constants.NEWLINE);
      buffer.append("Module: ");
      buffer.append(this.module_.toString());
      return buffer.toString();
   }
}
