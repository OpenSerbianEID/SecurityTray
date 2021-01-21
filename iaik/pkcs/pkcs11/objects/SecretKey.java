package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

public class SecretKey extends Key {
   protected BooleanAttribute sensitive_;
   protected BooleanAttribute encrypt_;
   protected BooleanAttribute decrypt_;
   protected BooleanAttribute sign_;
   protected BooleanAttribute verify_;
   protected BooleanAttribute wrap_;
   protected BooleanAttribute unwrap_;
   protected BooleanAttribute extractable_;
   protected BooleanAttribute alwaysSensitive_;
   protected BooleanAttribute neverExtractable_;
   protected ByteArrayAttribute checkValue_;
   protected BooleanAttribute wrapWithTrusted_;
   protected BooleanAttribute trusted_;
   protected AttributeArray wrapTemplate_;
   protected AttributeArray unwrapTemplate_;

   public SecretKey() {
      this.objectClass_.setLongValue(Object.ObjectClass.SECRET_KEY);
   }

   protected SecretKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.objectClass_.setLongValue(Object.ObjectClass.SECRET_KEY);
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
            if (keyType.equals(Key.KeyType.DES)) {
               newObject = DESSecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.DES2)) {
               newObject = DES2SecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.DES3)) {
               newObject = DES3SecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.AES)) {
               newObject = AESSecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.RC2)) {
               newObject = RC2SecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.RC4)) {
               newObject = RC4SecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.RC5)) {
               newObject = RC5SecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.IDEA)) {
               newObject = IDEASecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.GENERIC_SECRET)) {
               newObject = GenericSecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.CAST)) {
               newObject = CASTSecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.CAST3)) {
               newObject = CAST3SecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.CAST5)) {
               newObject = CAST5SecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.CAST128)) {
               newObject = CAST128SecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.BLOWFISH)) {
               newObject = BlowfishSecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.TWOFISH)) {
               newObject = TwofishSecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.SKIPJACK)) {
               newObject = SkipJackSecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.BATON)) {
               newObject = BatonSecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.JUNIPER)) {
               newObject = JuniperSecretKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.CDMF)) {
               newObject = CDMFSecretKey.getInstance(session, objectHandle);
            } else if ((keyType & Key.KeyType.VENDOR_DEFINED) != 0L) {
               newObject = getUnknownSecretKey(session, objectHandle);
            } else {
               newObject = getUnknownSecretKey(session, objectHandle);
            }
         } else {
            newObject = getUnknownSecretKey(session, objectHandle);
         }

         return newObject;
      }
   }

   protected static Object getUnknownSecretKey(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         java.lang.Object newObject;
         if (Key.vendorKeyBuilder_ != null) {
            try {
               newObject = Key.vendorKeyBuilder_.build(session, objectHandle);
            } catch (PKCS11Exception var5) {
               newObject = new SecretKey(session, objectHandle);
            }
         } else {
            newObject = new SecretKey(session, objectHandle);
         }

         return (Object)newObject;
      }
   }

   protected static void putAttributesInTable(SecretKey object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.SENSITIVE, object.sensitive_);
         object.attributeTable_.put(Attribute.ENCRYPT, object.encrypt_);
         object.attributeTable_.put(Attribute.DECRYPT, object.decrypt_);
         object.attributeTable_.put(Attribute.SIGN, object.sign_);
         object.attributeTable_.put(Attribute.VERIFY, object.verify_);
         object.attributeTable_.put(Attribute.WRAP, object.wrap_);
         object.attributeTable_.put(Attribute.UNWRAP, object.unwrap_);
         object.attributeTable_.put(Attribute.EXTRACTABLE, object.extractable_);
         object.attributeTable_.put(Attribute.ALWAYS_SENSITIVE, object.alwaysSensitive_);
         object.attributeTable_.put(Attribute.NEVER_EXTRACTABLE, object.neverExtractable_);
         object.attributeTable_.put(Attribute.CHECK_VALUE, object.checkValue_);
         object.attributeTable_.put(Attribute.WRAP_WITH_TRUSTED, object.wrapWithTrusted_);
         object.attributeTable_.put(Attribute.TRUSTED, object.trusted_);
         object.attributeTable_.put(Attribute.WRAP_TEMPLATE, object.wrapTemplate_);
         object.attributeTable_.put(Attribute.UNWRAP_TEMPLATE, object.unwrapTemplate_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.sensitive_ = new BooleanAttribute(Attribute.SENSITIVE);
      this.encrypt_ = new BooleanAttribute(Attribute.ENCRYPT);
      this.decrypt_ = new BooleanAttribute(Attribute.DECRYPT);
      this.sign_ = new BooleanAttribute(Attribute.SIGN);
      this.verify_ = new BooleanAttribute(Attribute.VERIFY);
      this.wrap_ = new BooleanAttribute(Attribute.WRAP);
      this.unwrap_ = new BooleanAttribute(Attribute.UNWRAP);
      this.extractable_ = new BooleanAttribute(Attribute.EXTRACTABLE);
      this.alwaysSensitive_ = new BooleanAttribute(Attribute.ALWAYS_SENSITIVE);
      this.neverExtractable_ = new BooleanAttribute(Attribute.NEVER_EXTRACTABLE);
      this.checkValue_ = new ByteArrayAttribute(Attribute.CHECK_VALUE);
      this.wrapWithTrusted_ = new BooleanAttribute(Attribute.WRAP_WITH_TRUSTED);
      this.trusted_ = new BooleanAttribute(Attribute.TRUSTED);
      this.wrapTemplate_ = new AttributeArray(Attribute.WRAP_TEMPLATE);
      this.unwrapTemplate_ = new AttributeArray(Attribute.UNWRAP_TEMPLATE);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      SecretKey clone = (SecretKey)super.clone();
      clone.sensitive_ = (BooleanAttribute)this.sensitive_.clone();
      clone.encrypt_ = (BooleanAttribute)this.encrypt_.clone();
      clone.decrypt_ = (BooleanAttribute)this.decrypt_.clone();
      clone.sign_ = (BooleanAttribute)this.sign_.clone();
      clone.verify_ = (BooleanAttribute)this.verify_.clone();
      clone.wrap_ = (BooleanAttribute)this.wrap_.clone();
      clone.unwrap_ = (BooleanAttribute)this.unwrap_.clone();
      clone.extractable_ = (BooleanAttribute)this.extractable_.clone();
      clone.alwaysSensitive_ = (BooleanAttribute)this.alwaysSensitive_.clone();
      clone.neverExtractable_ = (BooleanAttribute)this.neverExtractable_.clone();
      clone.checkValue_ = (ByteArrayAttribute)this.checkValue_.clone();
      clone.wrapWithTrusted_ = (BooleanAttribute)this.wrapWithTrusted_.clone();
      clone.trusted_ = (BooleanAttribute)this.trusted_.clone();
      clone.wrapTemplate_ = (AttributeArray)this.wrapTemplate_.clone();
      clone.unwrapTemplate_ = (AttributeArray)this.unwrapTemplate_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof SecretKey) {
         SecretKey other = (SecretKey)otherObject;
         equal = this == other || super.equals(other) && this.sensitive_.equals(other.sensitive_) && this.encrypt_.equals(other.encrypt_) && this.decrypt_.equals(other.decrypt_) && this.sign_.equals(other.sign_) && this.verify_.equals(other.verify_) && this.wrap_.equals(other.wrap_) && this.unwrap_.equals(other.unwrap_) && this.extractable_.equals(other.extractable_) && this.alwaysSensitive_.equals(other.alwaysSensitive_) && this.neverExtractable_.equals(other.neverExtractable_) && this.checkValue_.equals(other.checkValue_) && this.wrapWithTrusted_.equals(other.wrapWithTrusted_) && this.trusted_.equals(other.trusted_) && this.wrapTemplate_.equals(other.wrapTemplate_) && this.unwrapTemplate_.equals(other.unwrapTemplate_);
      }

      return equal;
   }

   public BooleanAttribute getSensitive() {
      return this.sensitive_;
   }

   public BooleanAttribute getEncrypt() {
      return this.encrypt_;
   }

   public BooleanAttribute getVerify() {
      return this.verify_;
   }

   public BooleanAttribute getDecrypt() {
      return this.decrypt_;
   }

   public BooleanAttribute getSign() {
      return this.sign_;
   }

   public BooleanAttribute getWrap() {
      return this.wrap_;
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

   public ByteArrayAttribute getCheckValue() {
      return this.checkValue_;
   }

   public BooleanAttribute getWrapWithTrusted() {
      return this.wrapWithTrusted_;
   }

   public BooleanAttribute getTrusted() {
      return this.trusted_;
   }

   public AttributeArray getWrapTemplate() {
      return this.wrapTemplate_;
   }

   public AttributeArray getUnwrapTemplate() {
      return this.unwrapTemplate_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.sensitive_, this.encrypt_, this.decrypt_, this.sign_, this.verify_, this.wrap_, this.unwrap_, this.extractable_, this.alwaysSensitive_, this.neverExtractable_, this.checkValue_, this.wrapWithTrusted_, this.trusted_});
      Object.getAttributeValue(session, this.objectHandle_, this.wrapTemplate_);
      Object.getAttributeValue(session, this.objectHandle_, this.unwrapTemplate_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(1024);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Sensitive: ");
      buffer.append(this.sensitive_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Encrypt: ");
      buffer.append(this.encrypt_.toString());
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
      buffer.append("Verify: ");
      buffer.append(this.verify_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Wrap: ");
      buffer.append(this.wrap_.toString());
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
      buffer.append("Check Value: ");
      buffer.append(this.checkValue_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Wrap With Trusted: ");
      buffer.append(this.wrapWithTrusted_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Trusted: ");
      buffer.append(this.trusted_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Wrap Template: ");
      buffer.append(this.wrapTemplate_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Unwrap Template: ");
      buffer.append(this.unwrapTemplate_.toString());
      return buffer.toString();
   }
}
