package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class DHPrivateKey extends PrivateKey {
   protected ByteArrayAttribute prime_;
   protected ByteArrayAttribute base_;
   protected ByteArrayAttribute value_;
   protected LongAttribute valueBits_;

   public DHPrivateKey() {
      this.keyType_.setLongValue(Key.KeyType.DH);
   }

   protected DHPrivateKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.keyType_.setLongValue(Key.KeyType.DH);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new DHPrivateKey(session, objectHandle);
   }

   protected static void putAttributesInTable(DHPrivateKey object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.PRIME, object.prime_);
         object.attributeTable_.put(Attribute.BASE, object.base_);
         object.attributeTable_.put(Attribute.VALUE, object.value_);
         object.attributeTable_.put(Attribute.VALUE_BITS, object.valueBits_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.prime_ = new ByteArrayAttribute(Attribute.PRIME);
      this.base_ = new ByteArrayAttribute(Attribute.BASE);
      this.value_ = new ByteArrayAttribute(Attribute.VALUE);
      this.valueBits_ = new LongAttribute(Attribute.VALUE_BITS);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      DHPrivateKey clone = (DHPrivateKey)super.clone();
      clone.prime_ = (ByteArrayAttribute)this.prime_.clone();
      clone.base_ = (ByteArrayAttribute)this.base_.clone();
      clone.value_ = (ByteArrayAttribute)this.value_.clone();
      clone.valueBits_ = (LongAttribute)this.valueBits_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof DHPrivateKey) {
         DHPrivateKey other = (DHPrivateKey)otherObject;
         equal = this == other || super.equals(other) && this.prime_.equals(other.prime_) && this.base_.equals(other.base_) && this.value_.equals(other.value_) && this.valueBits_.equals(other.valueBits_);
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

   public LongAttribute getValueBits() {
      return this.valueBits_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.prime_, this.base_, this.valueBits_});
      Object.getAttributeValue(session, this.objectHandle_, this.value_);
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
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Value Bits (dec): ");
      buffer.append(this.valueBits_.toString(10));
      return buffer.toString();
   }
}
