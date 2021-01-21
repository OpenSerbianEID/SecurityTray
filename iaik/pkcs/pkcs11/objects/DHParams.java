package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class DHParams extends DomainParameters {
   protected ByteArrayAttribute prime_;
   protected ByteArrayAttribute base_;
   protected LongAttribute primeBits_;

   public DHParams() {
      this.keyType_.setLongValue(Key.KeyType.DH);
   }

   protected DHParams(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.keyType_.setLongValue(Key.KeyType.DH);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new DHParams(session, objectHandle);
   }

   protected static void putAttributesInTable(DHParams object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.PRIME, object.prime_);
         object.attributeTable_.put(Attribute.BASE, object.base_);
         object.attributeTable_.put(Attribute.PRIME_BITS, object.primeBits_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.prime_ = new ByteArrayAttribute(Attribute.PRIME);
      this.base_ = new ByteArrayAttribute(Attribute.BASE);
      this.primeBits_ = new LongAttribute(Attribute.PRIME_BITS);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      DHParams clone = (DHParams)super.clone();
      clone.prime_ = (ByteArrayAttribute)this.prime_.clone();
      clone.base_ = (ByteArrayAttribute)this.base_.clone();
      clone.primeBits_ = (LongAttribute)this.primeBits_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof DHParams) {
         DHParams other = (DHParams)otherObject;
         equal = this == other || super.equals(other) && this.prime_.equals(other.prime_) && this.base_.equals(other.base_) && this.primeBits_.equals(other.primeBits_);
      }

      return equal;
   }

   public ByteArrayAttribute getPrime() {
      return this.prime_;
   }

   public ByteArrayAttribute getBase() {
      return this.base_;
   }

   public LongAttribute getPrimeBits() {
      return this.primeBits_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.prime_, this.base_, this.primeBits_});
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
      buffer.append("Prime Bits (dec): ");
      buffer.append(this.primeBits_.toString(10));
      return buffer.toString();
   }
}
