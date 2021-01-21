package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_KEY_WRAP_SET_OAEP_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class KeyWrapSetOaepParameters implements Parameters {
   protected byte blockContents_;
   protected byte[] x_;

   public KeyWrapSetOaepParameters(byte blockContents, byte[] x) {
      this.blockContents_ = blockContents;
      this.x_ = x;
   }

   public Object clone() {
      try {
         KeyWrapSetOaepParameters clone = (KeyWrapSetOaepParameters)super.clone();
         clone.x_ = (byte[])((byte[])this.x_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      CK_KEY_WRAP_SET_OAEP_PARAMS params = new CK_KEY_WRAP_SET_OAEP_PARAMS();
      params.bBC = this.blockContents_;
      params.pX = this.x_;
      return params;
   }

   public byte getBlockContents() {
      return this.blockContents_;
   }

   public byte[] getX() {
      return this.x_;
   }

   public void setBlockContents(byte blockContents) {
      this.blockContents_ = blockContents;
   }

   public void setX(byte[] x) {
      this.x_ = x;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Block Contents Byte (hex): ");
      buffer.append(Functions.toHexString((long)this.blockContents_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("X (hex): ");
      buffer.append(Functions.toHexString(this.x_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof KeyWrapSetOaepParameters) {
         KeyWrapSetOaepParameters other = (KeyWrapSetOaepParameters)otherObject;
         equal = this == other || this.blockContents_ == other.blockContents_ && Functions.equals(this.x_, other.x_);
      }

      return equal;
   }

   public int hashCode() {
      return this.blockContents_ ^ Functions.hashCode(this.x_);
   }
}
