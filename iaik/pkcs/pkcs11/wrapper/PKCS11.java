package iaik.pkcs.pkcs11.wrapper;

public interface PKCS11 {
   void C_Initialize(Object var1) throws PKCS11Exception;

   void C_Finalize(Object var1) throws PKCS11Exception;

   CK_INFO C_GetInfo() throws PKCS11Exception;

   long[] C_GetSlotList(boolean var1) throws PKCS11Exception;

   CK_SLOT_INFO C_GetSlotInfo(long var1) throws PKCS11Exception;

   CK_TOKEN_INFO C_GetTokenInfo(long var1) throws PKCS11Exception;

   long[] C_GetMechanismList(long var1) throws PKCS11Exception;

   CK_MECHANISM_INFO C_GetMechanismInfo(long var1, long var3) throws PKCS11Exception;

   void C_InitToken(long var1, char[] var3, char[] var4) throws PKCS11Exception;

   void C_InitPIN(long var1, char[] var3) throws PKCS11Exception;

   void C_SetPIN(long var1, char[] var3, char[] var4) throws PKCS11Exception;

   long C_OpenSession(long var1, long var3, Object var5, CK_NOTIFY var6) throws PKCS11Exception;

   void C_CloseSession(long var1) throws PKCS11Exception;

   void C_CloseAllSessions(long var1) throws PKCS11Exception;

   CK_SESSION_INFO C_GetSessionInfo(long var1) throws PKCS11Exception;

   byte[] C_GetOperationState(long var1) throws PKCS11Exception;

   void C_SetOperationState(long var1, byte[] var3, long var4, long var6) throws PKCS11Exception;

   void C_Login(long var1, long var3, char[] var5) throws PKCS11Exception;

   void C_Logout(long var1) throws PKCS11Exception;

   long C_CreateObject(long var1, CK_ATTRIBUTE[] var3) throws PKCS11Exception;

   long C_CopyObject(long var1, long var3, CK_ATTRIBUTE[] var5) throws PKCS11Exception;

   void C_DestroyObject(long var1, long var3) throws PKCS11Exception;

   long C_GetObjectSize(long var1, long var3) throws PKCS11Exception;

   void C_GetAttributeValue(long var1, long var3, CK_ATTRIBUTE[] var5) throws PKCS11Exception;

   void C_SetAttributeValue(long var1, long var3, CK_ATTRIBUTE[] var5) throws PKCS11Exception;

   void C_FindObjectsInit(long var1, CK_ATTRIBUTE[] var3) throws PKCS11Exception;

   long[] C_FindObjects(long var1, long var3) throws PKCS11Exception;

   void C_FindObjectsFinal(long var1) throws PKCS11Exception;

   void C_EncryptInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   byte[] C_Encrypt(long var1, byte[] var3) throws PKCS11Exception;

   byte[] C_EncryptUpdate(long var1, byte[] var3) throws PKCS11Exception;

   byte[] C_EncryptFinal(long var1) throws PKCS11Exception;

   void C_DecryptInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   byte[] C_Decrypt(long var1, byte[] var3) throws PKCS11Exception;

   byte[] C_DecryptUpdate(long var1, byte[] var3) throws PKCS11Exception;

   byte[] C_DecryptFinal(long var1) throws PKCS11Exception;

   void C_DigestInit(long var1, CK_MECHANISM var3) throws PKCS11Exception;

   byte[] C_Digest(long var1, byte[] var3) throws PKCS11Exception;

   void C_DigestUpdate(long var1, byte[] var3) throws PKCS11Exception;

   void C_DigestKey(long var1, long var3) throws PKCS11Exception;

   byte[] C_DigestFinal(long var1) throws PKCS11Exception;

   void C_SignInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   byte[] C_Sign(long var1, byte[] var3) throws PKCS11Exception;

   void C_SignUpdate(long var1, byte[] var3) throws PKCS11Exception;

   byte[] C_SignFinal(long var1) throws PKCS11Exception;

   void C_SignRecoverInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   byte[] C_SignRecover(long var1, byte[] var3) throws PKCS11Exception;

   void C_VerifyInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   void C_Verify(long var1, byte[] var3, byte[] var4) throws PKCS11Exception;

   void C_VerifyUpdate(long var1, byte[] var3) throws PKCS11Exception;

   void C_VerifyFinal(long var1, byte[] var3) throws PKCS11Exception;

   void C_VerifyRecoverInit(long var1, CK_MECHANISM var3, long var4) throws PKCS11Exception;

   byte[] C_VerifyRecover(long var1, byte[] var3) throws PKCS11Exception;

   byte[] C_DigestEncryptUpdate(long var1, byte[] var3) throws PKCS11Exception;

   byte[] C_DecryptDigestUpdate(long var1, byte[] var3) throws PKCS11Exception;

   byte[] C_SignEncryptUpdate(long var1, byte[] var3) throws PKCS11Exception;

   byte[] C_DecryptVerifyUpdate(long var1, byte[] var3) throws PKCS11Exception;

   long C_GenerateKey(long var1, CK_MECHANISM var3, CK_ATTRIBUTE[] var4) throws PKCS11Exception;

   long[] C_GenerateKeyPair(long var1, CK_MECHANISM var3, CK_ATTRIBUTE[] var4, CK_ATTRIBUTE[] var5) throws PKCS11Exception;

   byte[] C_WrapKey(long var1, CK_MECHANISM var3, long var4, long var6) throws PKCS11Exception;

   long C_UnwrapKey(long var1, CK_MECHANISM var3, long var4, byte[] var6, CK_ATTRIBUTE[] var7) throws PKCS11Exception;

   long C_DeriveKey(long var1, CK_MECHANISM var3, long var4, CK_ATTRIBUTE[] var6) throws PKCS11Exception;

   void C_SeedRandom(long var1, byte[] var3) throws PKCS11Exception;

   void C_GenerateRandom(long var1, byte[] var3) throws PKCS11Exception;

   void C_GetFunctionStatus(long var1) throws PKCS11Exception;

   void C_CancelFunction(long var1) throws PKCS11Exception;

   long C_WaitForSlotEvent(long var1, Object var3) throws PKCS11Exception;

   void finalize() throws Throwable;
}
