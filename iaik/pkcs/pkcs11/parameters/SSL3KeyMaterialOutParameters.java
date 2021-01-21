package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.objects.SecretKey;
import iaik.pkcs.pkcs11.wrapper.CK_SSL3_KEY_MAT_OUT;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class SSL3KeyMaterialOutParameters implements Parameters {
   protected SecretKey clientMacSecret_;
   protected SecretKey serverMacSecret_;
   protected SecretKey clientKey_;
   protected SecretKey serverKey_;
   protected byte[] clientIV_;
   protected byte[] serverIV_;

   public SSL3KeyMaterialOutParameters(byte[] clientIV, byte[] serverIV) {
      if (clientIV == null) {
         throw new NullPointerException("Argument \"clientIV\" must not be null.");
      } else if (serverIV == null) {
         throw new NullPointerException("Argument \"serverIV\" must not be null.");
      } else {
         this.clientIV_ = clientIV;
         this.serverIV_ = serverIV;
      }
   }

   public Object clone() {
      try {
         SSL3KeyMaterialOutParameters clone = (SSL3KeyMaterialOutParameters)super.clone();
         clone.clientMacSecret_ = (SecretKey)this.clientMacSecret_.clone();
         clone.serverMacSecret_ = (SecretKey)this.serverMacSecret_.clone();
         clone.clientKey_ = (SecretKey)this.clientKey_.clone();
         clone.serverKey_ = (SecretKey)this.serverKey_.clone();
         clone.clientIV_ = (byte[])((byte[])this.clientIV_.clone());
         clone.serverIV_ = (byte[])((byte[])this.serverIV_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      CK_SSL3_KEY_MAT_OUT params = new CK_SSL3_KEY_MAT_OUT();
      params.hClientMacSecret = this.clientMacSecret_ != null ? this.clientMacSecret_.getObjectHandle() : 0L;
      params.hServerMacSecret = this.serverMacSecret_ != null ? this.serverMacSecret_.getObjectHandle() : 0L;
      params.hClientKey = this.clientKey_ != null ? this.clientKey_.getObjectHandle() : 0L;
      params.hServerKey = this.serverKey_ != null ? this.serverKey_.getObjectHandle() : 0L;
      params.pIVClient = this.clientIV_;
      params.pIVServer = this.serverIV_;
      return params;
   }

   public void setPKCS11ParamsObject(CK_SSL3_KEY_MAT_OUT input, Session session) throws TokenException {
      this.clientMacSecret_ = (SecretKey)iaik.pkcs.pkcs11.objects.Object.getInstance(session, input.hClientMacSecret);
      this.serverMacSecret_ = (SecretKey)iaik.pkcs.pkcs11.objects.Object.getInstance(session, input.hServerMacSecret);
      this.clientKey_ = (SecretKey)iaik.pkcs.pkcs11.objects.Object.getInstance(session, input.hClientKey);
      this.serverKey_ = (SecretKey)iaik.pkcs.pkcs11.objects.Object.getInstance(session, input.hServerKey);
      this.clientIV_ = input.pIVClient;
      this.serverIV_ = input.pIVServer;
   }

   public SecretKey getClientMacSecret() {
      return this.clientMacSecret_;
   }

   public SecretKey getServerMacSecret() {
      return this.serverMacSecret_;
   }

   public SecretKey getClientSecret() {
      return this.clientKey_;
   }

   public SecretKey getServerSecret() {
      return this.serverKey_;
   }

   public byte[] getClientIV() {
      return this.clientIV_;
   }

   public byte[] getServerIV() {
      return this.serverIV_;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Client MAC Secret key: ");
      buffer.append(Constants.NEWLINE);
      buffer.append(this.clientMacSecret_);
      buffer.append(Constants.NEWLINE);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Server MAC Secret key: ");
      buffer.append(Constants.NEWLINE);
      buffer.append(this.serverMacSecret_);
      buffer.append(Constants.NEWLINE);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Client Secret key: ");
      buffer.append(Constants.NEWLINE);
      buffer.append(this.clientKey_);
      buffer.append(Constants.NEWLINE);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Server Secret key: ");
      buffer.append(Constants.NEWLINE);
      buffer.append(this.serverKey_);
      buffer.append(Constants.NEWLINE);
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Client Initializatin Vector (hex): ");
      buffer.append(Functions.toHexString(this.clientIV_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Server Initializatin Vector (hex): ");
      buffer.append(Functions.toHexString(this.serverIV_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof SSL3KeyMaterialOutParameters) {
         SSL3KeyMaterialOutParameters other = (SSL3KeyMaterialOutParameters)otherObject;
         equal = this == other || (this.clientMacSecret_ == null && other.clientMacSecret_ == null || this.clientMacSecret_ != null && this.clientMacSecret_.equals(other.clientMacSecret_)) && (this.serverMacSecret_ == null && other.serverMacSecret_ == null || this.serverMacSecret_ != null && this.serverMacSecret_.equals(other.serverMacSecret_)) && (this.clientKey_ == null && other.clientKey_ == null || this.clientKey_ != null && this.clientKey_.equals(other.clientKey_)) && (this.serverKey_ == null && other.serverKey_ == null || this.serverKey_ != null && this.serverKey_.equals(other.serverKey_)) && Functions.equals(this.clientIV_, other.clientIV_) && Functions.equals(this.serverIV_, other.serverIV_);
      }

      return equal;
   }

   public int hashCode() {
      return (this.clientMacSecret_ != null ? this.clientMacSecret_.hashCode() : 0) ^ (this.serverMacSecret_ != null ? this.serverMacSecret_.hashCode() : 0) ^ (this.clientKey_ != null ? this.clientKey_.hashCode() : 0) ^ (this.serverKey_ != null ? this.serverKey_.hashCode() : 0);
   }
}
