package iaik.pkcs.pkcs11;

import iaik.pkcs.pkcs11.objects.Key;
import iaik.pkcs.pkcs11.objects.KeyPair;
import iaik.pkcs.pkcs11.objects.PrivateKey;
import iaik.pkcs.pkcs11.objects.PublicKey;
import iaik.pkcs.pkcs11.objects.SecretKey;
import iaik.pkcs.pkcs11.parameters.Parameters;
import iaik.pkcs.pkcs11.parameters.SSL3KeyMaterialParameters;
import iaik.pkcs.pkcs11.parameters.SSL3MasterKeyDeriveParameters;
import iaik.pkcs.pkcs11.wrapper.CK_ATTRIBUTE;
import iaik.pkcs.pkcs11.wrapper.CK_MECHANISM;
import iaik.pkcs.pkcs11.wrapper.CK_SESSION_INFO;
import iaik.pkcs.pkcs11.wrapper.CK_SSL3_KEY_MAT_PARAMS;
import iaik.pkcs.pkcs11.wrapper.CK_SSL3_MASTER_KEY_DERIVE_PARAMS;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.Functions;
import iaik.pkcs.pkcs11.wrapper.PKCS11;
import java.util.Vector;

public class Session {
   protected Module module_;
   protected PKCS11 pkcs11Module_;
   protected long sessionHandle_;
   protected Token token_;

   protected Session(Token token, long sessionHandle) {
      if (token == null) {
         throw new NullPointerException("Argument \"token\" must not be null.");
      } else {
         this.token_ = token;
         this.module_ = this.token_.getSlot().getModule();
         this.pkcs11Module_ = this.module_.getPKCS11Module();
         this.sessionHandle_ = sessionHandle;
      }
   }

   public void initPIN(char[] pin) throws TokenException {
      this.pkcs11Module_.C_InitPIN(this.sessionHandle_, pin);
   }

   public void setPIN(char[] oldPin, char[] newPin) throws TokenException {
      this.pkcs11Module_.C_SetPIN(this.sessionHandle_, oldPin, newPin);
   }

   public void closeSession() throws TokenException {
      this.pkcs11Module_.C_CloseSession(this.sessionHandle_);
   }

   public boolean equals(Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Session) {
         Session other = (Session)otherObject;
         equal = this == other || this.sessionHandle_ == other.sessionHandle_ && this.token_.equals(other.token_);
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.sessionHandle_;
   }

   public long getSessionHandle() {
      return this.sessionHandle_;
   }

   public SessionInfo getSessionInfo() throws TokenException {
      CK_SESSION_INFO ckSessionInfo = this.pkcs11Module_.C_GetSessionInfo(this.sessionHandle_);
      return new SessionInfo(ckSessionInfo);
   }

   public Module getModule() {
      return this.module_;
   }

   public Token getToken() {
      return this.token_;
   }

   public byte[] getOperationState() throws TokenException {
      return this.pkcs11Module_.C_GetOperationState(this.sessionHandle_);
   }

   public void setOperationState(byte[] operationState, Key encryptionKey, Key authenticationKey) throws TokenException {
      this.pkcs11Module_.C_SetOperationState(this.sessionHandle_, operationState, encryptionKey.getObjectHandle(), authenticationKey.getObjectHandle());
   }

   public void login(boolean userType, char[] pin) throws TokenException {
      long lUserType = !userType ? 0L : 1L;
      this.pkcs11Module_.C_Login(this.sessionHandle_, lUserType, pin);
   }

   public void logout() throws TokenException {
      this.pkcs11Module_.C_Logout(this.sessionHandle_);
   }

   public iaik.pkcs.pkcs11.objects.Object createObject(iaik.pkcs.pkcs11.objects.Object templateObject) throws TokenException {
      CK_ATTRIBUTE[] ckAttributes = iaik.pkcs.pkcs11.objects.Object.getSetAttributes(templateObject);
      long objectHandle = this.pkcs11Module_.C_CreateObject(this.sessionHandle_, ckAttributes);
      return iaik.pkcs.pkcs11.objects.Object.getInstance(this, objectHandle);
   }

   public iaik.pkcs.pkcs11.objects.Object copyObject(iaik.pkcs.pkcs11.objects.Object sourceObject, iaik.pkcs.pkcs11.objects.Object templateObject) throws TokenException {
      long sourceObjectHandle = sourceObject.getObjectHandle();
      CK_ATTRIBUTE[] ckAttributes = iaik.pkcs.pkcs11.objects.Object.getSetAttributes(templateObject);
      long newObjectHandle = this.pkcs11Module_.C_CopyObject(this.sessionHandle_, sourceObjectHandle, ckAttributes);
      return iaik.pkcs.pkcs11.objects.Object.getInstance(this, newObjectHandle);
   }

   public void setAttributeValues(iaik.pkcs.pkcs11.objects.Object objectToUpdate, iaik.pkcs.pkcs11.objects.Object templateObject) throws TokenException {
      long objectToUpdateHandle = objectToUpdate.getObjectHandle();
      CK_ATTRIBUTE[] ckAttributesTemplates = iaik.pkcs.pkcs11.objects.Object.getSetAttributes(templateObject);
      this.pkcs11Module_.C_SetAttributeValue(this.sessionHandle_, objectToUpdateHandle, ckAttributesTemplates);
   }

   public iaik.pkcs.pkcs11.objects.Object getAttributeValues(iaik.pkcs.pkcs11.objects.Object objectToRead) throws TokenException {
      long objectHandle = objectToRead.getObjectHandle();
      return iaik.pkcs.pkcs11.objects.Object.getInstance(this, objectHandle);
   }

   public void destroyObject(iaik.pkcs.pkcs11.objects.Object object) throws TokenException {
      long objectHandle = object.getObjectHandle();
      this.pkcs11Module_.C_DestroyObject(this.sessionHandle_, objectHandle);
   }

   public long getObjectSize(iaik.pkcs.pkcs11.objects.Object object) throws TokenException {
      long objectHandle = object.getObjectHandle();
      return this.pkcs11Module_.C_GetObjectSize(this.sessionHandle_, objectHandle);
   }

   public void findObjectsInit(iaik.pkcs.pkcs11.objects.Object templateObject) throws TokenException {
      CK_ATTRIBUTE[] ckAttributes = iaik.pkcs.pkcs11.objects.Object.getSetAttributes(templateObject);
      this.pkcs11Module_.C_FindObjectsInit(this.sessionHandle_, ckAttributes);
   }

   public iaik.pkcs.pkcs11.objects.Object[] findObjects(int maxObjectCount) throws TokenException {
      Vector foundObjects = new Vector();
      long[] objectHandles = this.pkcs11Module_.C_FindObjects(this.sessionHandle_, (long)maxObjectCount);

      try {
         for(int i = 0; i < objectHandles.length; ++i) {
            iaik.pkcs.pkcs11.objects.Object object = iaik.pkcs.pkcs11.objects.Object.getInstance(this, objectHandles[i]);
            foundObjects.addElement(object);
         }

         iaik.pkcs.pkcs11.objects.Object[] objectArray = new iaik.pkcs.pkcs11.objects.Object[foundObjects.size()];
         foundObjects.copyInto(objectArray);
         return objectArray;
      } catch (TokenException var6) {
         throw new TokenException(var6);
      }
   }

   public void findObjectsFinal() throws TokenException {
      this.pkcs11Module_.C_FindObjectsFinal(this.sessionHandle_);
   }

   public void encryptInit(Mechanism mechanism, Key key) throws TokenException {
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      this.pkcs11Module_.C_EncryptInit(this.sessionHandle_, ckMechanism, key.getObjectHandle());
   }

   public byte[] encrypt(byte[] data) throws TokenException {
      return this.pkcs11Module_.C_Encrypt(this.sessionHandle_, data);
   }

   public byte[] encryptUpdate(byte[] part) throws TokenException {
      return this.pkcs11Module_.C_EncryptUpdate(this.sessionHandle_, part);
   }

   public byte[] encryptFinal() throws TokenException {
      return this.pkcs11Module_.C_EncryptFinal(this.sessionHandle_);
   }

   public void decryptInit(Mechanism mechanism, Key key) throws TokenException {
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      this.pkcs11Module_.C_DecryptInit(this.sessionHandle_, ckMechanism, key.getObjectHandle());
   }

   public byte[] decrypt(byte[] data) throws TokenException {
      return this.pkcs11Module_.C_Decrypt(this.sessionHandle_, data);
   }

   public byte[] decryptUpdate(byte[] encryptedPart) throws TokenException {
      return this.pkcs11Module_.C_DecryptUpdate(this.sessionHandle_, encryptedPart);
   }

   public byte[] decryptFinal() throws TokenException {
      return this.pkcs11Module_.C_DecryptFinal(this.sessionHandle_);
   }

   public void digestInit(Mechanism mechanism) throws TokenException {
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      this.pkcs11Module_.C_DigestInit(this.sessionHandle_, ckMechanism);
   }

   public byte[] digest(byte[] data) throws TokenException {
      return this.pkcs11Module_.C_Digest(this.sessionHandle_, data);
   }

   public void digestUpdate(byte[] part) throws TokenException {
      this.pkcs11Module_.C_DigestUpdate(this.sessionHandle_, part);
   }

   public void digestKey(SecretKey key) throws TokenException {
      this.pkcs11Module_.C_DigestKey(this.sessionHandle_, key.getObjectHandle());
   }

   public byte[] digestFinal() throws TokenException {
      return this.pkcs11Module_.C_DigestFinal(this.sessionHandle_);
   }

   public void signInit(Mechanism mechanism, Key key) throws TokenException {
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      this.pkcs11Module_.C_SignInit(this.sessionHandle_, ckMechanism, key.getObjectHandle());
   }

   public byte[] sign(byte[] data) throws TokenException {
      return this.pkcs11Module_.C_Sign(this.sessionHandle_, data);
   }

   public void signUpdate(byte[] part) throws TokenException {
      this.pkcs11Module_.C_SignUpdate(this.sessionHandle_, part);
   }

   public byte[] signFinal() throws TokenException {
      return this.pkcs11Module_.C_SignFinal(this.sessionHandle_);
   }

   public void signRecoverInit(Mechanism mechanism, Key key) throws TokenException {
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      this.pkcs11Module_.C_SignRecoverInit(this.sessionHandle_, ckMechanism, key.getObjectHandle());
   }

   public byte[] signRecover(byte[] data) throws TokenException {
      return this.pkcs11Module_.C_SignRecover(this.sessionHandle_, data);
   }

   public void verifyInit(Mechanism mechanism, Key key) throws TokenException {
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      this.pkcs11Module_.C_VerifyInit(this.sessionHandle_, ckMechanism, key.getObjectHandle());
   }

   public void verify(byte[] data, byte[] signature) throws TokenException {
      this.pkcs11Module_.C_Verify(this.sessionHandle_, data, signature);
   }

   public void verifyUpdate(byte[] part) throws TokenException {
      this.pkcs11Module_.C_VerifyUpdate(this.sessionHandle_, part);
   }

   public void verifyFinal(byte[] signature) throws TokenException {
      this.pkcs11Module_.C_VerifyFinal(this.sessionHandle_, signature);
   }

   public void verifyRecoverInit(Mechanism mechanism, Key key) throws TokenException {
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      this.pkcs11Module_.C_VerifyRecoverInit(this.sessionHandle_, ckMechanism, key.getObjectHandle());
   }

   public byte[] verifyRecover(byte[] signature) throws TokenException {
      return this.pkcs11Module_.C_VerifyRecover(this.sessionHandle_, signature);
   }

   public byte[] digestEncryptedUpdate(byte[] part) throws TokenException {
      return this.pkcs11Module_.C_DigestEncryptUpdate(this.sessionHandle_, part);
   }

   public byte[] decryptDigestUpdate(byte[] part) throws TokenException {
      return this.pkcs11Module_.C_DecryptDigestUpdate(this.sessionHandle_, part);
   }

   public byte[] signEncryptUpdate(byte[] part) throws TokenException {
      return this.pkcs11Module_.C_SignEncryptUpdate(this.sessionHandle_, part);
   }

   public byte[] decryptVerifyUpdate(byte[] encryptedPart) throws TokenException {
      return this.pkcs11Module_.C_DecryptVerifyUpdate(this.sessionHandle_, encryptedPart);
   }

   public iaik.pkcs.pkcs11.objects.Object generateKey(Mechanism mechanism, iaik.pkcs.pkcs11.objects.Object template) throws TokenException {
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      CK_ATTRIBUTE[] ckAttributes = iaik.pkcs.pkcs11.objects.Object.getSetAttributes(template);
      long objectHandle = this.pkcs11Module_.C_GenerateKey(this.sessionHandle_, ckMechanism, ckAttributes);
      return iaik.pkcs.pkcs11.objects.Object.getInstance(this, objectHandle);
   }

   public KeyPair generateKeyPair(Mechanism mechanism, iaik.pkcs.pkcs11.objects.Object publicKeyTemplate, iaik.pkcs.pkcs11.objects.Object privateKeyTemplate) throws TokenException {
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      CK_ATTRIBUTE[] ckPublicKeyAttributes = iaik.pkcs.pkcs11.objects.Object.getSetAttributes(publicKeyTemplate);
      CK_ATTRIBUTE[] ckPrivateKeyAttributes = iaik.pkcs.pkcs11.objects.Object.getSetAttributes(privateKeyTemplate);
      long[] objectHandles = this.pkcs11Module_.C_GenerateKeyPair(this.sessionHandle_, ckMechanism, ckPublicKeyAttributes, ckPrivateKeyAttributes);
      PublicKey publicKey = (PublicKey)iaik.pkcs.pkcs11.objects.Object.getInstance(this, objectHandles[0]);
      PrivateKey privateKey = (PrivateKey)iaik.pkcs.pkcs11.objects.Object.getInstance(this, objectHandles[1]);
      return new KeyPair(publicKey, privateKey);
   }

   public byte[] wrapKey(Mechanism mechanism, Key wrappingKey, Key key) throws TokenException {
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      return this.pkcs11Module_.C_WrapKey(this.sessionHandle_, ckMechanism, wrappingKey.getObjectHandle(), key.getObjectHandle());
   }

   public Key unwrapKey(Mechanism mechanism, Key unwrappingKey, byte[] wrappedKey, iaik.pkcs.pkcs11.objects.Object keyTemplate) throws TokenException {
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      CK_ATTRIBUTE[] ckAttributes = iaik.pkcs.pkcs11.objects.Object.getSetAttributes(keyTemplate);
      long objectHandle = this.pkcs11Module_.C_UnwrapKey(this.sessionHandle_, ckMechanism, unwrappingKey.getObjectHandle(), wrappedKey, ckAttributes);
      return (Key)iaik.pkcs.pkcs11.objects.Object.getInstance(this, objectHandle);
   }

   public Key deriveKey(Mechanism mechanism, Key baseKey, Key template) throws TokenException {
      Key derivedKey = null;
      CK_MECHANISM ckMechanism = new CK_MECHANISM();
      ckMechanism.mechanism = mechanism.getMechanismCode();
      Parameters parameters = mechanism.getParameters();
      ckMechanism.pParameter = parameters != null ? parameters.getPKCS11ParamsObject() : null;
      CK_ATTRIBUTE[] ckAttributes = iaik.pkcs.pkcs11.objects.Object.getSetAttributes(template);
      long objectHandle = this.pkcs11Module_.C_DeriveKey(this.sessionHandle_, ckMechanism, baseKey.getObjectHandle(), ckAttributes);
      if ((ckMechanism.mechanism == 881L || ckMechanism.mechanism == 885L) && parameters instanceof SSL3MasterKeyDeriveParameters) {
         ((SSL3MasterKeyDeriveParameters)parameters).getVersion().setPKCS11ParamsObject(((CK_SSL3_MASTER_KEY_DERIVE_PARAMS)((CK_SSL3_MASTER_KEY_DERIVE_PARAMS)ckMechanism.pParameter)).pVersion);
         derivedKey = (Key)iaik.pkcs.pkcs11.objects.Object.getInstance(this, objectHandle);
      } else if ((ckMechanism.mechanism == 882L || ckMechanism.mechanism == 886L) && parameters instanceof SSL3KeyMaterialParameters) {
         ((SSL3KeyMaterialParameters)parameters).getReturnedKeyMaterial().setPKCS11ParamsObject(((CK_SSL3_KEY_MAT_PARAMS)((CK_SSL3_KEY_MAT_PARAMS)ckMechanism.pParameter)).pReturnedKeyMaterial, this);
         derivedKey = null;
      } else {
         derivedKey = (Key)iaik.pkcs.pkcs11.objects.Object.getInstance(this, objectHandle);
      }

      return derivedKey;
   }

   public void seedRandom(byte[] seed) throws TokenException {
      this.pkcs11Module_.C_SeedRandom(this.sessionHandle_, seed);
   }

   public byte[] generateRandom(int numberOfBytesToGenerate) throws TokenException {
      byte[] randomBytesBuffer = new byte[numberOfBytesToGenerate];
      this.pkcs11Module_.C_GenerateRandom(this.sessionHandle_, randomBytesBuffer);
      return randomBytesBuffer;
   }

   public void getFunctionStatus() throws TokenException {
      this.pkcs11Module_.C_GetFunctionStatus(this.sessionHandle_);
   }

   public void cancelFunction() throws TokenException {
      this.pkcs11Module_.C_CancelFunction(this.sessionHandle_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("Session Handle: ");
      buffer.append("0x");
      buffer.append(Functions.toHexString(this.sessionHandle_));
      buffer.append(Constants.NEWLINE);
      buffer.append("Token: ");
      buffer.append(this.token_.toString());
      return buffer.toString();
   }

   public interface UserType {
      boolean SO = false;
      boolean USER = true;
   }
}
