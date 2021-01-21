package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class DHPublicKey extends PublicKey {
   protected ByteArrayAttribute prime_;
   protected ByteArrayAttribute base_;
   protected ByteArrayAttribute value_;

   public DHPublicKey() {
      this.keyType_.setLongValue(Key.KeyType.DH);
   }

   protected DHPublicKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.keyType_.setLongValue(Key.KeyType.DH);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new DHPublicKey(session, objectHandle);
   }

   protected static void putAttributesInTable(DHPublicKey object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.PRIME, object.prime_);
         object.attributeTable_.put(Attribute.BASE, object.base_);
         object.attributeTable_.put(Attribute.VALUE, object.value_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.prime_ = new ByteArrayAttribute(Attribute.PRIME);
      this.base_ = new ByteArrayAttribute(Attribute.BASE);
      this.value_ = new ByteArrayAttribute(Attribute.VALUE);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      DHPublicKey clone = (DHPublicKey)super.clone();
      clone.prime_ = (ByteArrayAttribute)this.prime_.clone();
      clone.base_ = (ByteArrayAttribute)this.base_.clone();
      clone.value_ = (ByteArrayAttribute)this.value_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof DHPublicKey) {
         DHPublicKey other = (DHPublicKey)otherObject;
         equal = this == other || super.equals(other) && this.prime_.equals(other.prime_) && this.base_.equals(other.base_) && this.value_.equals(other.value_);
      }

      return equal;
   }

   public ByteArrayAttribute getPrime() {
      return this.prime_;
   }

   public ByteArrayAttribute getBase() {
      return this.base_;
   }

   public ByteArrayAttribute getValue() {
      return this.value_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.prime_, this.base_, this.value_});
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(1024);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Prime (hex): ");
      buffer.append(this.prime_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Base (hex): ");
      buffer.append(this.base_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Value (hex): ");
      buffer.append(this.value_.toString());
      return buffer.toString();
   }
}
