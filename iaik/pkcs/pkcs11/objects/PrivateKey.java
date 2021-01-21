package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

public class PrivateKey extends Key {
   protected ByteArrayAttribute subject_;
   protected BooleanAttribute sensitive_;
   protected BooleanAttribute secondaryAuth_;
   protected LongAttribute authPinFlags_;
   protected BooleanAttribute decrypt_;
   protected BooleanAttribute sign_;
   protected BooleanAttribute signRecover_;
   protected BooleanAttribute unwrap_;
   protected BooleanAttribute extractable_;
   protected BooleanAttribute alwaysSensitive_;
   protected BooleanAttribute neverExtractable_;
   protected BooleanAttribute wrapWithTrusted_;
   protected AttributeArray unwrapTemplate_;
   protected BooleanAttribute alwaysAuthenticate_;

   public PrivateKey() {
      this.objectClass_.setLongValue(Object.ObjectClass.PRIVATE_KEY);
   }

   protected PrivateKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.objectClass_.setLongValue(Object.ObjectClass.PRIVATE_KEY);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         KeyTypeAttribute keyTypeAttribute = new KeyTypeAttribute();
         getAttributeValue(session, objectHandle, keyTypeAttribute);
         Long keyType = keyTypeAttribute.getLongValue();
         Object newObject;
         if (keyTypeAttribute.isPresent() && keyType != null) {
            if (keyType.equals(Key.KeyType.RSA)) {
               newObject = RSAPrivateKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.DSA)) {
               newObject = DSAPrivateKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.ECDSA)) {
               newObject = ECDSAPrivateKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.DH)) {
               newObject = DHPrivateKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.KEA)) {
               newObject = KEAPrivateKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.X9_42_DH)) {
               newObject = X942DHPrivateKey.getInstance(session, objectHandle);
            } else if ((keyType & Key.KeyType.VENDOR_DEFINED) != 0L) {
               newObject = getUnknownPrivateKey(session, objectHandle);
            } else {
               newObject = getUnknownPrivateKey(session, objectHandle);
            }
         } else {
            newObject = getUnknownPrivateKey(session, objectHandle);
         }

         return newObject;
      }
   }

   protected static Object getUnknownPrivateKey(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         java.lang.Object newObject;
         if (Key.vendorKeyBuilder_ != null) {
            try {
               newObject = Key.vendorKeyBuilder_.build(session, objectHandle);
            } catch (PKCS11Exception var5) {
               newObject = new PrivateKey(session, objectHandle);
            }
         } else {
            newObject = new PrivateKey(session, objectHandle);
         }

         return (Object)newObject;
      }
   }

   protected static void putAttributesInTable(PrivateKey object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.SUBJECT, object.subject_);
         object.attributeTable_.put(Attribute.SENSITIVE, object.sensitive_);
         object.attributeTable_.put(Attribute.SECONDARY_AUTH, object.secondaryAuth_);
         object.attributeTable_.put(Attribute.AUTH_PIN_FLAGS, object.authPinFlags_);
         object.attributeTable_.put(Attribute.DECRYPT, object.decrypt_);
         object.attributeTable_.put(Attribute.SIGN, object.sign_);
         object.attributeTable_.put(Attribute.SIGN_RECOVER, object.signRecover_);
         object.attributeTable_.put(Attribute.UNWRAP, object.unwrap_);
         object.attributeTable_.put(Attribute.EXTRACTABLE, object.extractable_);
         object.attributeTable_.put(Attribute.ALWAYS_SENSITIVE, object.alwaysSensitive_);
         object.attributeTable_.put(Attribute.NEVER_EXTRACTABLE, object.neverExtractable_);
         object.attributeTable_.put(Attribute.WRAP_WITH_TRUSTED, object.wrapWithTrusted_);
         object.attributeTable_.put(Attribute.UNWRAP_TEMPLATE, object.unwrapTemplate_);
         object.attributeTable_.put(Attribute.ALWAYS_AUTHENTICATE, object.alwaysAuthenticate_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.subject_ = new ByteArrayAttribute(Attribute.SUBJECT);
      this.sensitive_ = new BooleanAttribute(Attribute.SENSITIVE);
      this.secondaryAuth_ = new BooleanAttribute(Attribute.SECONDARY_AUTH);
      this.authPinFlags_ = new LongAttribute(Attribute.AUTH_PIN_FLAGS);
      this.decrypt_ = new BooleanAttribute(Attribute.DECRYPT);
      this.sign_ = new BooleanAttribute(Attribute.SIGN);
      this.signRecover_ = new BooleanAttribute(Attribute.SIGN_RECOVER);
      this.unwrap_ = new BooleanAttribute(Attribute.UNWRAP);
      this.extractable_ = new BooleanAttribute(Attribute.EXTRACTABLE);
      this.alwaysSensitive_ = new BooleanAttribute(Attribute.ALWAYS_SENSITIVE);
      this.neverExtractable_ = new BooleanAttribute(Attribute.NEVER_EXTRACTABLE);
      this.wrapWithTrusted_ = new BooleanAttribute(Attribute.WRAP_WITH_TRUSTED);
      this.unwrapTemplate_ = new AttributeArray(Attribute.UNWRAP_TEMPLATE);
      this.alwaysAuthenticate_ = new BooleanAttribute(Attribute.ALWAYS_AUTHENTICATE);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      PrivateKey clone = (PrivateKey)super.clone();
      clone.subject_ = (ByteArrayAttribute)this.subject_.clone();
      clone.sensitive_ = (BooleanAttribute)this.sensitive_.clone();
      clone.secondaryAuth_ = (BooleanAttribute)this.secondaryAuth_.clone();
      clone.authPinFlags_ = (LongAttribute)this.authPinFlags_.clone();
      clone.decrypt_ = (BooleanAttribute)this.decrypt_.clone();
      clone.sign_ = (BooleanAttribute)this.sign_.clone();
      clone.signRecover_ = (BooleanAttribute)this.signRecover_.clone();
      clone.unwrap_ = (BooleanAttribute)this.unwrap_.clone();
      clone.extractable_ = (BooleanAttribute)this.extractable_.clone();
      clone.alwaysSensitive_ = (BooleanAttribute)this.alwaysSensitive_.clone();
      clone.neverExtractable_ = (BooleanAttribute)this.neverExtractable_.clone();
      clone.wrapWithTrusted_ = (BooleanAttribute)this.wrapWithTrusted_.clone();
      clone.unwrapTemplate_ = (AttributeArray)this.unwrapTemplate_.clone();
      clone.alwaysAuthenticate_ = (BooleanAttribute)this.alwaysAuthenticate_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof PrivateKey) {
         PrivateKey other = (PrivateKey)otherObject;
         equal = this == other || super.equals(other) && this.subject_.equals(other.subject_) && this.sensitive_.equals(other.sensitive_) && this.secondaryAuth_.equals(other.secondaryAuth_) && this.authPinFlags_.equals(other.authPinFlags_) && this.decrypt_.equals(other.decrypt_) && this.sign_.equals(other.sign_) && this.signRecover_.equals(other.signRecover_) && this.unwrap_.equals(other.unwrap_) && this.extractable_.equals(other.extractable_) && this.alwaysSensitive_.equals(other.alwaysSensitive_) && this.neverExtractable_.equals(other.neverExtractable_) && this.wrapWithTrusted_.equals(other.wrapWithTrusted_) && this.unwrapTemplate_.equals(other.unwrapTemplate_) && this.alwaysAuthenticate_.equals(other.alwaysAuthenticate_);
      }

      return equal;
   }

   public ByteArrayAttribute getSubject() {
      return this.subject_;
   }

   public BooleanAttribute getSensitive() {
      return this.sensitive_;
   }

   public BooleanAttribute getSecondaryAuth() {
      return this.secondaryAuth_;
   }

   public LongAttribute getAuthPinFlags() {
      return this.authPinFlags_;
   }

   public BooleanAttribute getDecrypt() {
      return this.decrypt_;
   }

   public BooleanAttribute getSign() {
      return this.sign_;
   }

   public BooleanAttribute getSignRecover() {
      return this.signRecover_;
   }

   public BooleanAttribute getUnwrap() {
      return this.unwrap_;
   }

   public BooleanAttribute getExtractable() {
      return this.extractable_;
   }

   public BooleanAttribute getAlwaysSensitive() {
      return this.alwaysSensitive_;
   }

   public BooleanAttribute getNeverExtractable() {
      return this.neverExtractable_;
   }

   public BooleanAttribute getWrapWithTrusted() {
      return this.wrapWithTrusted_;
   }

   public AttributeArray getUnwrapTemplate() {
      return this.unwrapTemplate_;
   }

   public BooleanAttribute getAlwaysAuthenticate() {
      return this.alwaysAuthenticate_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.subject_, this.sensitive_, this.secondaryAuth_, this.authPinFlags_, this.decrypt_, this.sign_, this.signRecover_, this.unwrap_, this.extractable_, this.alwaysSensitive_, this.neverExtractable_, this.wrapWithTrusted_, this.alwaysAuthenticate_});
      Object.getAttributeValue(session, this.objectHandle_, this.unwrapTemplate_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(1024);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Subject (DER, hex): ");
      buffer.append(this.subject_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Sensitive: ");
      buffer.append(this.sensitive_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Secondary Authentication: ");
      buffer.append(this.secondaryAuth_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Secondary Authentication PIN Flags: ");
      if (this.authPinFlags_.isPresent() && !this.authPinFlags_.isSensitive() && this.authPinFlags_.getLongValue() != null) {
         long authFlagsValue = this.authPinFlags_.getLongValue();
         buffer.append(Constants.NEWLINE);
         buffer.append("  ");
         buffer.append("  ");
         buffer.append("User PIN-Count low: ");
         buffer.append((authFlagsValue & 65536L) != 0L);
         buffer.append(Constants.NEWLINE);
         buffer.append("  ");
         buffer.append("  ");
         buffer.append("User PIN final Try: ");
         buffer.append((authFlagsValue & 131072L) != 0L);
         buffer.append(Constants.NEWLINE);
         buffer.append("  ");
         buffer.append("  ");
         buffer.append("User PIN locked: ");
         buffer.append((authFlagsValue & 262144L) != 0L);
         buffer.append(Constants.NEWLINE);
         buffer.append("  ");
         buffer.append("  ");
         buffer.append("User PIN to be changed: ");
         buffer.append((authFlagsValue & 524288L) != 0L);
      } else {
         buffer.append(this.authPinFlags_.toString());
      }

      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Decrypt: ");
      buffer.append(this.decrypt_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Sign: ");
      buffer.append(this.sign_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Sign Recover: ");
      buffer.append(this.signRecover_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Unwrap: ");
      buffer.append(this.unwrap_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Extractable: ");
      buffer.append(this.extractable_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Always Sensitive: ");
      buffer.append(this.alwaysSensitive_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Never Extractable: ");
      buffer.append(this.neverExtractable_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Wrap With Trusted: ");
      buffer.append(this.wrapWithTrusted_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Unwrap Template: ");
      buffer.append(this.unwrapTemplate_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Always Authenticate: ");
      buffer.append(this.alwaysAuthenticate_.toString());
      return buffer.toString();
   }
}
