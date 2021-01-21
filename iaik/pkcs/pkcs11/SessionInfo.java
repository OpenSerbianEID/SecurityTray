package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.wrapper.CK_SESSION_INFO;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class SessionInfo implements Cloneable {
   protected long slotID_;
   protected State state_;
   protected long deviceError_;
   protected boolean rwSession_;
   protected boolean serialSession_;

   protected SessionInfo(CK_SESSION_INFO ckSessionInfo) {
      if (ckSessionInfo == null) {
         throw new NullPointerException("Argument \"ckSessionInfo\" must not be null.");
      } else {
         this.slotID_ = ckSessionInfo.slotID;
         this.state_ = new State(ckSessionInfo.state);
         this.deviceError_ = ckSessionInfo.ulDeviceError;
         this.rwSession_ = (ckSessionInfo.flags & 2L) != 0L;
         this.serialSession_ = (ckSessionInfo.flags & 4L) != 0L;
      }
   }

   public Object clone() {
      try {
         SessionInfo clone = (SessionInfo)super.clone();
         clone.state_ = (State)this.state_.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public State getState() {
      return this.state_;
   }

   public long getDeviceError() {
      return this.deviceError_;
   }

   public boolean isRwSession() {
      return this.rwSession_;
   }

   public boolean isSerialSession() {
      return this.serialSession_;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("State: ");
      buffer.append(this.state_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Device Error: 0x");
      buffer.append(Functions.toHexString(this.deviceError_));
      buffer.append(Constants.NEWLINE);
      buffer.append("Read/Write Session: ");
      buffer.append(this.rwSession_);
      buffer.append(Constants.NEWLINE);
      buffer.append("Serial Session: ");
      buffer.append(this.serialSession_);
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof SessionInfo) {
         SessionInfo other = (SessionInfo)otherObject;
         equal = this == other || this.slotID_ == other.slotID_ && this.state_.equals(other.state_) && this.deviceError_ == other.deviceError_ && this.rwSession_ == other.rwSession_ && this.serialSession_ == other.serialSession_;
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.slotID_ ^ this.state_.hashCode() ^ (int)this.deviceError_;
   }
}
