package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_SSL3_RANDOM_DATA;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;

public class SSL3RandomDataParameters implements Parameters {
   protected byte[] clientRandom_;
   protected byte[] serverRandom_;

   public SSL3RandomDataParameters(byte[] clientRandom, byte[] serverRandom) {
      if (clientRandom == null) {
         throw new NullPointerException("Argument \"clientRandom\" must not be null.");
      } else if (serverRandom == null) {
         throw new NullPointerException("Argument \"serverRandom\" must not be null.");
      } else {
         this.clientRandom_ = clientRandom;
         this.serverRandom_ = serverRandom;
      }
   }

   public Object clone() {
      try {
         SSL3RandomDataParameters clone = (SSL3RandomDataParameters)super.clone();
         clone.clientRandom_ = (byte[])((byte[])this.clientRandom_.clone());
         clone.serverRandom_ = (byte[])((byte[])this.serverRandom_.clone());
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public Object getPKCS11ParamsObject() {
      CK_SSL3_RANDOM_DATA params = new CK_SSL3_RANDOM_DATA();
      params.pClientRandom = this.clientRandom_;
      params.pServerRandom = this.serverRandom_;
      return params;
   }

   public byte[] getClientRandom() {
      return this.clientRandom_;
   }

   public byte[] getServerRandom() {
      return this.serverRandom_;
   }

   public void setClientRandom(byte[] clientRandom) {
      if (clientRandom == null) {
         throw new NullPointerException("Argument \"clientRandom\" must not be null.");
      } else {
         this.clientRandom_ = clientRandom;
      }
   }

   public void setServerRandom(byte[] serverRandom) {
      if (serverRandom == null) {
         throw new NullPointerException("Argument \"serverRandom\" must not be null.");
      } else {
         this.serverRandom_ = serverRandom;
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("  ");
      buffer.append("Client Random (hex): ");
      buffer.append(Functions.toHexString(this.clientRandom_));
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Server Random (hex): ");
      buffer.append(Functions.toHexString(this.serverRandom_));
      return buffer.toString();
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof SSL3RandomDataParameters) {
         SSL3RandomDataParameters other = (SSL3RandomDataParameters)otherObject;
         equal = this == other || Functions.equals(this.clientRandom_, other.clientRandom_) && Functions.equals(this.serverRandom_, other.serverRandom_);
      }

      return equal;
   }

   public int hashCode() {
      return Functions.hashCode(this.clientRandom_) ^ Functions.hashCode(this.serverRandom_);
   }
}
