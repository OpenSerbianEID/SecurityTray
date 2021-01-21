package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class ECDSAPrivateKey extends PrivateKey {
   protected ByteArrayAttribute ecdsaParams_;
   protected ByteArrayAttribute value_;

   public ECDSAPrivateKey() {
      this.keyType_.setLongValue(Key.KeyType.ECDSA);
   }

   protected ECDSAPrivateKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.keyType_.setLongValue(Key.KeyType.ECDSA);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new ECDSAPrivateKey(session, objectHandle);
   }

   protected static void putAttributesInTable(ECDSAPrivateKey object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.ECDSA_PARAMS, object.ecdsaParams_);
         object.attributeTable_.put(Attribute.VALUE, object.value_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.ecdsaParams_ = new ByteArrayAttribute(Attribute.ECDSA_PARAMS);
      this.value_ = new ByteArrayAttribute(Attribute.VALUE);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      ECDSAPrivateKey clone = (ECDSAPrivateKey)super.clone();
      clone.ecdsaParams_ = (ByteArrayAttribute)this.ecdsaParams_.clone();
      clone.value_ = (ByteArrayAttribute)this.value_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof ECDSAPrivateKey) {
         ECDSAPrivateKey other = (ECDSAPrivateKey)otherObject;
         equal = this == other || super.equals(other) && this.ecdsaParams_.equals(other.ecdsaParams_) && this.value_.equals(other.value_);
      }

      return equal;
   }

   public ByteArrayAttribute getEcdsaParams() {
      return this.ecdsaParams_;
   }

   public ByteArrayAttribute getValue() {
      return this.value_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValue(session, this.objectHandle_, this.ecdsaParams_);
      Object.getAttributeValue(session, this.objectHandle_, this.value_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(1024);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ECDSA Params (DER, hex): ");
      buffer.append(this.ecdsaParams_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Private Value d (hex): ");
      buffer.append(this.value_.toString());
      return buffer.toString();
   }
}
