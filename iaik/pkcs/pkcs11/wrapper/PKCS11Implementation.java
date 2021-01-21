package iaik.pkcs.pkcs11.wrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PKCS11Implementation implements PKCS11 {
   private static final String PKCS11_WRAPPER = "pkcs11wrapper";
   protected static boolean linkedAndInitialized_;
   protected String pkcs11ModulePath_;

   protected static synchronized native void initializeLibrary();

   protected static synchronized native void finalizeLibrary();

   public static synchronized void ensureLinkedAndInitialized() {
      if (!linkedAndInitialized_) {
         System.loadLibrary("pkcs11wrapper");
         initializeLibrary();
         linkedAndInitialized_ = true;
      }

   }

   public static synchronized void ensureLinkedAndInitialized(String pkcs11WrapperPath) throws IOException {
      if (!linkedAndInitialized_) {
         try {
            loadJarDll(pkcs11WrapperPath);
         } catch (Exception var2) {
         }

         initializeLibrary();
         linkedAndInitialized_ = true;
      }

   }

   public static void loadJarDll(String pkcs11WrapperPath) throws IOException {
      InputStream in = new FileInputStream(pkcs11WrapperPath);
      Throwable var3 = null;

      File temp;
      try {
         byte[] buffer = new byte[1024];
         int read = true;
         temp = File.createTempFile(pkcs11WrapperPath, "");
         FileOutputStream fos = new FileOutputStream(temp);

         while(true) {
            int read;
            if ((read = in.read(buffer)) == -1) {
               fos.close();
               break;
            }

            fos.write(buffer, 0, read);
         }
      } catch (Throwable var14) {
         var3 = var14;
         throw var14;
      } finally {
         if (in != null) {
            if (var3 != null) {
               try {
                  in.close();
               } catch (Throwable var13) {
                  var3.addSuppressed(var13);
               }
            } else {
               in.close();
            }
         }

      }

      System.load(temp.getAbsolutePath());
   }

   public static synchronized void ensureUnlinkedAndFinalized() {
      if (linkedAndInitialized_) {
         finalizeLibrary();
         linkedAndInitialized_ = false;
      }

   }

   PKCS11Implementation(String pkcs11ModulePath) throws IOException {
      ensureLinkedAndInitialized();
      this.connect(pkcs11ModulePath);
      this.pkcs11ModulePath_ = pkcs11ModulePath;
   }

   PKCS11Implementation(String pkcs11ModulePath, String pkcs11WrapperPath) throws IOException {
      ensureLinkedAndInitialized(pkcs11WrapperPath);
      this.connect(pkcs11ModulePath);
      this.pkcs11ModulePath_ = pkcs11ModulePath;
   }

   protected synchronized native void connect(String var1) throws IOException;

   protected synchronized native void disconnect();

   public native void C_Initialize(Object var1) throws PKCS11Exception;

   public native void C_Finalize(Object var1) throws PKCS11Exception;

   public native CK_INFO C_GetInfo() throws PKCS11Exception;

   public native long[] C_GetSlotList(boolean var1) throws PKCS11Exception;

   public native CK_SLOT_INFO C_GetSlotInfo(long var1) throws PKCS11Exception;

   public native CK_TOKEN_INFO C_GetTokenInfo(long var1) throws PKCS11Exception;

   public native long[] C_GetMechanismList(long var1) throws PKCS11Exception;

   public native CK_MECHANISM_INFO C_GetMechanismInfo(long var1, long var3) throws PKCS11Exception;

   public native void C_InitToken(long var1, char[] var3, char[] var4) throws PKCS11Exception;

   public native void C_InitPIN(long var1, char[] var3) throws PKCS11Exception;

   public native void C_SetPIN(long var1, char[] var3, char[] var4) throws PKCS11Exception;

   public native long C_OpenSession(long var1, long var3, Object var5, CK_NOTIFY var6) throws PKCS11Exception;

   public native void C_CloseSession(long var1) throws PKCS11Exception;

   public native void C_CloseAllSessions(long var1) throws PKCS11Exception;

   public native CK_SESSION_INFO C_GetSessionInfo(long var1) throws PKCS11Exception;

   public native byte[] C_GetOperationState(long var1) throws PKCS11Exception;

   public native void C_SetOperationState(long var1, byte[] var3, long var4, long var6) throws PKCS11Exception;

   public native void C_Login(long var1, long var3, char[] var5) throws PKCS11Exception;

   public native void C_Logout(long var1) throws PKCS11Exception;

   public native long C_CreateObject(long var1, CK_ATTRIBUTE[] var3) throws PKCS11Exception;

   public native long C_CopyObject(long var1, long var3, CK_ATTRIBUTE[] var5) throws PKCS11Exception;

   public native void C_DestroyObject(long var1, long var3) throws PKCS11Exception;

   public native long C_GetObjectSize(long var1, long var3) throws PKCS11Exception;

   public native void C_GetAttributeValue(long var1, long var3, CK_ATTRIBUTE[] var5) throws PKCS11Exception;

   public native void C_SetAttributeValue(long var1, long var3, CK_ATTRIBUTE[] var5) throws PKCS11Exception;

   public native void C_FindObjectsInit(long var1, CK_ATTRIBUTE[] var3) throws PKCS11Exception;

   public native long[] C_FindObjects(long var1, long var3) throws PKCS11Exception;

   public native void C_FindObjectsFinal(long var1) throws PKCS11Exception;

   public native void C_EncryptInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   public native byte[] C_Encrypt(long var1, byte[] var3) throws PKCS11Exception;

   public native byte[] C_EncryptUpdate(long var1, byte[] var3) throws PKCS11Exception;

   public native byte[] C_EncryptFinal(long var1) throws PKCS11Exception;

   public native void C_DecryptInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   public native byte[] C_Decrypt(long var1, byte[] var3) throws PKCS11Exception;

   public native byte[] C_DecryptUpdate(long var1, byte[] var3) throws PKCS11Exception;

   public native byte[] C_DecryptFinal(long var1) throws PKCS11Exception;

   public native void C_DigestInit(long var1, CK_MECHANISM var3) throws PKCS11Exception;

   public native byte[] C_Digest(long var1, byte[] var3) throws PKCS11Exception;

   public native void C_DigestUpdate(long var1, byte[] var3) throws PKCS11Exception;

   public native void C_DigestKey(long var1, long var3) throws PKCS11Exception;

   public native byte[] C_DigestFinal(long var1) throws PKCS11Exception;

   public native void C_SignInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   public native byte[] C_Sign(long var1, byte[] var3) throws PKCS11Exception;

   public native void C_SignUpdate(long var1, byte[] var3) throws PKCS11Exception;

   public native byte[] C_SignFinal(long var1) throws PKCS11Exception;

   public native void C_SignRecoverInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   public native byte[] C_SignRecover(long var1, byte[] var3) throws PKCS11Exception;

   public native void C_VerifyInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   public native void C_Verify(long var1, byte[] var3, byte[] var4) throws PKCS11Exception;

   public native void C_VerifyUpdate(long var1, byte[] var3) throws PKCS11Exception;

   public native void C_VerifyFinal(long var1, byte[] var3) throws PKCS11Exception;

   public native void C_VerifyRecoverInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   public native byte[] C_VerifyRecover(long var1, byte[] var3) throws PKCS11Exception;

   public native byte[] C_DigestEncryptUpdate(long var1, byte[] var3) throws PKCS11Exception;

   public native byte[] C_DecryptDigestUpdate(long var1, byte[] var3) throws PKCS11Exception;

   public native byte[] C_SignEncryptUpdate(long var1, byte[] var3) throws PKCS11Exception;

   public native byte[] C_DecryptVerifyUpdate(long var1, byte[] var3) throws PKCS11Exception;

   public native long C_GenerateKey(long var1, CK_MECHANISM var3, CK_ATTRIBUTE[] var4) throws PKCS11Exception;

   public native long[] C_GenerateKeyPair(long var1, CK_MECHANISM var3, CK_ATTRIBUTE[] var4, CK_ATTRIBUTE[] var5) throws PKCS11Exception;

   public native byte[] C_WrapKey(long var1, CK_MECHANISM var3, long var4, long var6) throws PKCS11Exception;

   public native long C_UnwrapKey(long var1, CK_MECHANISM var3, long var4, byte[] var6, CK_ATTRIBUTE[] var7) throws PKCS11Exception;

   public native long C_DeriveKey(long var1, CK_MECHANISM var3, long var4, CK_ATTRIBUTE[] var6) throws PKCS11Exception;

   public native void C_SeedRandom(long var1, byte[] var3) throws PKCS11Exception;

   public native void C_GenerateRandom(long var1, byte[] var3) throws PKCS11Exception;

   public native void C_GetFunctionStatus(long var1) throws PKCS11Exception;

   public native void C_CancelFunction(long var1) throws PKCS11Exception;

   public native long C_WaitForSlotEvent(long var1, Object var3) throws PKCS11Exception;

   public boolean equals(Object otherObject) {
      boolean equal;
      if (this == otherObject) {
         equal = true;
      } else if (otherObject instanceof PKCS11Implementation) {
         PKCS11Implementation other = (PKCS11Implementation)otherObject;
         if (this.pkcs11ModulePath_.equals(other.pkcs11ModulePath_)) {
            equal = true;
         } else {
            try {
               File thisLibarayFile = new File(this.pkcs11ModulePath_);
               File otherLibaryFile = new File(other.pkcs11ModulePath_);
               if (thisLibarayFile.getCanonicalPath().equals(otherLibaryFile.getCanonicalPath())) {
                  equal = true;
               } else {
                  equal = false;
               }
            } catch (IOException var6) {
               var6.printStackTrace();
               equal = false;
            }
         }
      } else {
         equal = false;
      }

      return equal;
   }

   public int hashCode() {
      int hashCode;
      try {
         File thisLibarayFile = new File(this.pkcs11ModulePath_);
         hashCode = thisLibarayFile.getCanonicalPath().hashCode();
      } catch (IOException var3) {
         var3.printStackTrace();
         hashCode = this.pkcs11ModulePath_.hashCode();
      }

      return hashCode;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("Module Name: ");
      buffer.append(this.pkcs11ModulePath_);
      return buffer.toString();
   }

   public void finalize() throws Throwable {
      this.disconnect();
      super.finalize();
   }
}
