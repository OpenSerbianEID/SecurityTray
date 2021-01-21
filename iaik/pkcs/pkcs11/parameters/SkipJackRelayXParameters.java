package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_SKIPJACK_RELAYX_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class SkipJackRelayXParameters implements Parameters {
   protected byte[] oldWrappedX_;
   protected byte[] oldPassword_;
   protected byte[] oldPublicData_;
   protected byte[] oldRandomA_;
   protected byte[] newPassword_;
   protected byte[] newPublicData_;
   protected byte[] newRandomA_;

   public SkipJackRelayXParameters(byte[] oldWrappedX, byte[] oldPassword, byte[] oldPublicData, byte[] oldRandomA, byte[] newPassword, byte[] newPublicData, byte[] newRandomA) {
      if (oldWrappedX == null) {
         throw new NullPointerException("Argument \"oldWrappedX\" must not be null.");
      } else if (oldPassword == null) {
         throw new NullPointerException("Argument \"oldPassword\" must not be null.");
      } else if (oldPublicData == null) {
         throw new NullPointerException("Argument \"oldPublicData\" must not be null.");
      } else if (oldRandomA == null) {
         throw new NullPointerException("Argument \"oldRandomA\" must not be null.");
      } else if (newPassword == null) {
         throw new NullPointerException("Argument \"newPassword\" must not be null.");
      } else if (newPublicData == null) {
         throw new NullPointerException("Argument \"newPublicData\" must not be null.");
      } else if (newRandomA == null) {
         throw new NullPointerException("Argument \"newRandomA\" must not be null.");
      } else {
         this.oldWrappedX_ = oldWrappedX;
         this.oldPassword_ = oldPassword;
         this.oldPublicData_ = oldPublicData;
         this.oldRandomA_ = oldRandomA;
         this.newPassword_ = newPassword;
         this.newPublicData_ = newPublicData;
         this.newRandomA_ = newRandomA;
      }
   }

   public Object clone() {
      try {
         SkipJackRelayXParameters clone = (SkipJackRelayXParameters)super.clone();
         clone.oldWrappedX_ = (byte[])((byte[])this.oldWrappedX_.clone());
         clone.oldPassword_ = (byte[])((byte[])this.oldPassword_.clone());
         clone.oldPublicData_ = (byte[])((byte[])this.oldPublicData_.clone());
         clone.oldRandomA_ = (byte[])((byte[])this.oldRandomA_.clone());
         clone.newPassword_ = (byte[])((byte[])this.newPassword_.clone());
         clone.newPublicData_ = (byte[])((byte[])this.newPublicData_.clone());
         clone.newRandomA_ = (byte[])((byte[])this.newRandomA_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      CK_SKIPJACK_RELAYX_PARAMS params = new CK_SKIPJACK_RELAYX_PARAMS();
      params.pOldWrappedX = this.oldWrappedX_;
      params.pOldPassword = this.oldPassword_;
      params.pOldPublicData = this.oldPublicData_;
      params.pOldRandomA = this.oldRandomA_;
      params.pNewPassword = this.newPassword_;
      params.pNewPublicData = this.newPublicData_;
      params.pNewRandomA = this.newRandomA_;
      return params;
   }

   public byte[] getOldWrappedX() {
      return this.oldWrappedX_;
   }

   public byte[] getOldPassword() {
      return this.oldPassword_;
   }

   public byte[] getOldPublicData() {
      return this.oldPublicData_;
   }

   public byte[] getOldRandomA() {
      return this.oldRandomA_;
   }

   public byte[] getNewPassword() {
      return this.newPassword_;
   }

   public byte[] getNewPublicData() {
      return this.newPublicData_;
   }

   public byte[] getNewRandomA() {
      return this.newRandomA_;
   }

   public void setOldWrappedX(byte[] oldWrappedX) {
      if (oldWrappedX == null) {
         throw new NullPointerException("Argument \"oldWrappedX\" must not be null.");
      } else {
         this.oldWrappedX_ = oldWrappedX;
      }
   }

   public void setOldPassword(byte[] oldPassword) {
      if (oldPassword == null) {
         throw new NullPointerException("Argument \"oldPassword\" must not be null.");
      } else {
         this.oldPassword_ = oldPassword;
      }
   }

   public void setOldPublicData(byte[] oldPublicData) {
      if (oldPublicData == null) {
         throw new NullPointerException("Argument \"oldPublicData\" must not be null.");
      } else {
         this.oldPublicData_ = oldPublicData;
      }
   }

   public void setOldRandomA(byte[] oldRandomA) {
      if (oldRandomA == null) {
         throw new NullPointerException("Argument \"oldRandomA\" must not be null.");
      } else {
         this.oldRandomA_ = oldRandomA;
      }
   }

   public void setNewPassword(byte[] newPassword) {
      if (newPassword == null) {
         throw new NullPointerException("Argument \"newPassword\" must not be null.");
      } else {
         this.newPassword_ = newPassword;
      }
   }

   public void setNewPublicData(byte[] newPublicData) {
      if (newPublicData == null) {
         throw new NullPointerException("Argument \"newPublicData\" must not be null.");
      } else {
         this.newPublicData_ = newPublicData;
      }
   }

   public void setNewRandomA(byte[] newRandomA) {
      if (newRandomA == null) {
         throw new NullPointerException("Argument \"newRandomA\" must not be null.");
      } else {
         this.newRandomA_ = newRandomA;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Old Wrapped Key (hex): ");
      buffer.append(Functions.toHexString(this.oldWrappedX_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Old Passord (hex): ");
      buffer.append(Functions.toHexString(this.oldPassword_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Old Public Data (hex): ");
      buffer.append(Functions.toHexString(this.oldPublicData_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Old Random Data A (hex): ");
      buffer.append(Functions.toHexString(this.oldRandomA_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("New Passord (hex): ");
      buffer.append(Functions.toHexString(this.newPassword_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("New Public Data (hex): ");
      buffer.append(Functions.toHexString(this.newPublicData_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("New Random Data A (hex): ");
      buffer.append(Functions.toHexString(this.newRandomA_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof SkipJackRelayXParameters) {
         SkipJackRelayXParameters other = (SkipJackRelayXParameters)otherObject;
         equal = this == other || Functions.equals(this.oldWrappedX_, other.oldWrappedX_) && Functions.equals(this.oldPassword_, other.oldPassword_) && Functions.equals(this.oldPublicData_, other.oldPublicData_) && Functions.equals(this.oldRandomA_, other.oldRandomA_) && Functions.equals(this.newPassword_, other.newPassword_) && Functions.equals(this.newPublicData_, other.newPublicData_) && Functions.equals(this.newRandomA_, other.newRandomA_);
      }

      return equal;
   }

   public int hashCode() {
      return Functions.hashCode(this.oldWrappedX_) ^ Functions.hashCode(this.oldPassword_) ^ Functions.hashCode(this.oldPublicData_) ^ Functions.hashCode(this.oldRandomA_) ^ Functions.hashCode(this.newPassword_) ^ Functions.hashCode(this.newPublicData_) ^ Functions.hashCode(this.newRandomA_);
   }
}
