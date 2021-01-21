package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

public class PublicKey extends Key {
   protected ByteArrayAttribute subject_;
   protected BooleanAttribute encrypt_;
   protected BooleanAttribute verify_;
   protected BooleanAttribute verifyRecover_;
   protected BooleanAttribute wrap_;
   protected BooleanAttribute trusted_;
   protected AttributeArray wrapTemplate_;

   public PublicKey() {
      this.objectClass_.setLongValue(Object.ObjectClass.PUBLIC_KEY);
   }

   protected PublicKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.objectClass_.setLongValue(Object.ObjectClass.PUBLIC_KEY);
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
               newObject = RSAPublicKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.DSA)) {
               newObject = DSAPublicKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.ECDSA)) {
               newObject = ECDSAPublicKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.DH)) {
               newObject = DHPublicKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.KEA)) {
               newObject = KEAPublicKey.getInstance(session, objectHandle);
            } else if (keyType.equals(Key.KeyType.X9_42_DH)) {
               newObject = X942DHPublicKey.getInstance(session, objectHandle);
            } else if ((keyType & Key.KeyType.VENDOR_DEFINED) != 0L) {
               newObject = getUnknownPublicKey(session, objectHandle);
            } else {
               newObject = getUnknownPublicKey(session, objectHandle);
            }
         } else {
            newObject = getUnknownPublicKey(session, objectHandle);
         }

         return newObject;
      }
   }

   protected static Object getUnknownPublicKey(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         java.lang.Object newObject;
         if (Key.vendorKeyBuilder_ != null) {
            try {
               newObject = Key.vendorKeyBuilder_.build(session, objectHandle);
            } catch (PKCS11Exception var5) {
               newObject = new PublicKey(session, objectHandle);
            }
         } else {
            newObject = new PublicKey(session, objectHandle);
         }

         return (Object)newObject;
      }
   }

   protected static void putAttributesInTable(PublicKey object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.SUBJECT, object.subject_);
         object.attributeTable_.put(Attribute.ENCRYPT, object.encrypt_);
         object.attributeTable_.put(Attribute.VERIFY, object.verify_);
         object.attributeTable_.put(Attribute.VERIFY_RECOVER, object.verifyRecover_);
         object.attributeTable_.put(Attribute.WRAP, object.wrap_);
         object.attributeTable_.put(Attribute.TRUSTED, object.trusted_);
         object.attributeTable_.put(Attribute.WRAP_TEMPLATE, object.wrapTemplate_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.subject_ = new ByteArrayAttribute(Attribute.SUBJECT);
      this.encrypt_ = new BooleanAttribute(Attribute.ENCRYPT);
      this.verify_ = new BooleanAttribute(Attribute.VERIFY);
      this.verifyRecover_ = new BooleanAttribute(Attribute.VERIFY_RECOVER);
      this.wrap_ = new BooleanAttribute(Attribute.WRAP);
      this.trusted_ = new BooleanAttribute(Attribute.TRUSTED);
      this.wrapTemplate_ = new AttributeArray(Attribute.WRAP_TEMPLATE);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      PublicKey clone = (PublicKey)super.clone();
      clone.subject_ = (ByteArrayAttribute)this.subject_.clone();
      clone.encrypt_ = (BooleanAttribute)this.encrypt_.clone();
      clone.verify_ = (BooleanAttribute)this.verify_.clone();
      clone.verifyRecover_ = (BooleanAttribute)this.verifyRecover_.clone();
      clone.wrap_ = (BooleanAttribute)this.wrap_.clone();
      clone.trusted_ = (BooleanAttribute)this.trusted_.clone();
      clone.wrapTemplate_ = (AttributeArray)this.wrapTemplate_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof PublicKey) {
         PublicKey other = (PublicKey)otherObject;
         equal = this == other || super.equals(other) && this.subject_.equals(other.subject_) && this.encrypt_.equals(other.encrypt_) && this.verify_.equals(other.verify_) && this.verifyRecover_.equals(other.verifyRecover_) && this.wrap_.equals(other.wrap_) && this.trusted_.equals(other.trusted_) && this.wrapTemplate_.equals(other.wrapTemplate_);
      }

      return equal;
   }

   public ByteArrayAttribute getSubject() {
      return this.subject_;
   }

   public BooleanAttribute getEncrypt() {
      return this.encrypt_;
   }

   public BooleanAttribute getVerify() {
      return this.verify_;
   }

   public BooleanAttribute getVerifyRecover() {
      return this.verifyRecover_;
   }

   public BooleanAttribute getWrap() {
      return this.wrap_;
   }

   public BooleanAttribute getTrusted() {
      return this.trusted_;
   }

   public AttributeArray getWrapTemplate() {
      return this.wrapTemplate_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.subject_, this.encrypt_, this.verify_, this.verifyRecover_, this.wrap_, this.trusted_});
      Object.getAttributeValue(session, this.objectHandle_, this.wrapTemplate_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(256);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Subject (DER, hex): ");
      buffer.append(this.subject_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Encrypt: ");
      buffer.append(this.encrypt_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Verify: ");
      buffer.append(this.verify_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Verify Recover: ");
      buffer.append(this.verifyRecover_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Wrap: ");
      buffer.append(this.wrap_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Trusted: ");
      buffer.append(this.trusted_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Wrap Template: ");
      buffer.append(this.wrapTemplate_.toString());
      return buffer.toString();
   }
}
