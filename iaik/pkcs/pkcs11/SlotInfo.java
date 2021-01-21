package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.CK_SLOT_INFO;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class SlotInfo {
   protected String slotDescription_;
   protected String manufacturerID_;
   protected Version hardwareVersion_;
   protected Version firmwareVersion_;
   protected boolean tokenPresent_;
   protected boolean removableDevice_;
   protected boolean hwSlot_;

   protected SlotInfo(CK_SLOT_INFO ckSlotInfo) {
      if (ckSlotInfo == null) {
         throw new NullPointerException("Argument \"ckSlotInfo\" must not be null.");
      } else {
         this.slotDescription_ = new String(ckSlotInfo.slotDescription);
         this.manufacturerID_ = new String(ckSlotInfo.manufacturerID);
         this.hardwareVersion_ = new Version(ckSlotInfo.hardwareVersion);
         this.firmwareVersion_ = new Version(ckSlotInfo.firmwareVersion);
         this.tokenPresent_ = (ckSlotInfo.flags & 1L) != 0L;
         this.removableDevice_ = (ckSlotInfo.flags & 2L) != 0L;
         this.hwSlot_ = (ckSlotInfo.flags & 4L) != 0L;
      }
   }

   public String getSlotDescription() {
      return this.slotDescription_;
   }

   public String getManufacturerID() {
      return this.manufacturerID_;
   }

   public Version getHardwareVersion() {
      return this.hardwareVersion_;
   }

   public Version getFirmwareVersion() {
      return this.firmwareVersion_;
   }

   public boolean isTokenPresent() {
      return this.tokenPresent_;
   }

   public boolean isRemovableDevice() {
      return this.removableDevice_;
   }

   public boolean isHwSlot() {
      return this.hwSlot_;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("Slot Description: ");
      buffer.append(this.slotDescription_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Manufacturer ID: ");
      buffer.append(this.manufacturerID_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Hardware Version: ");
      buffer.append(this.hardwareVersion_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Firmware Version: ");
      buffer.append(this.firmwareVersion_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Token present: ");
      buffer.append(this.tokenPresent_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Removable Device: ");
      buffer.append(this.removableDevice_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Hardware Slot: ");
      buffer.append(this.hwSlot_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof SlotInfo) {
         SlotInfo other = (SlotInfo)otherObject;
         equal = this == other || this.slotDescription_.equals(other.slotDescription_) && this.manufacturerID_.equals(other.manufacturerID_) && this.hardwareVersion_.equals(other.hardwareVersion_) && this.firmwareVersion_.equals(other.firmwareVersion_) && this.tokenPresent_ == other.tokenPresent_ && this.removableDevice_ == other.removableDevice_ && this.hwSlot_ == other.hwSlot_;
      }

      return equal;
   }

   public int hashCode() {
      return this.slotDescription_.hashCode() ^ this.manufacturerID_.hashCode() ^ this.hardwareVersion_.hashCode() ^ this.firmwareVersion_.hashCode();
   }
}
