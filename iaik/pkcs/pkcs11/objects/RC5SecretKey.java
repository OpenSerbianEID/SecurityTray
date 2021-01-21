package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class RC5SecretKey extends SecretKey {
   protected ByteArrayAttribute value_;
   protected LongAttribute valueLen_;

   public RC5SecretKey() {
      this.keyType_.setLongValue(Key.KeyType.RC5);
   }

   protected RC5SecretKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.keyType_.setLongValue(Key.KeyType.RC5);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new RC5SecretKey(session, objectHandle);
   }

   protected static void putAttributesInTable(RC5SecretKey object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.VALUE, object.value_);
         object.attributeTable_.put(Attribute.VALUE_LEN, object.valueLen_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.value_ = new ByteArrayAttribute(Attribute.VALUE);
      this.valueLen_ = new LongAttribute(Attribute.VALUE_LEN);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      RC5SecretKey clone = (RC5SecretKey)super.clone();
      clone.value_ = (ByteArrayAttribute)this.value_.clone();
      clone.valueLen_ = (LongAttribute)this.valueLen_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RC5SecretKey) {
         RC5SecretKey other = (RC5SecretKey)otherObject;
         equal = this == other || super.equals(other) && this.value_.equals(other.value_) && this.valueLen_.equals(other.valueLen_);
      }

      return equal;
   }

   public ByteArrayAttribute getValue() {
      return this.value_;
   }

   public LongAttribute getValueLen() {
      return this.valueLen_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValue(session, this.objectHandle_, this.value_);
      Object.getAttributeValue(session, this.objectHandle_, this.valueLen_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(1024);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Value (hex): ");
      buffer.append(this.value_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Value Length (dec): ");
      buffer.append(this.valueLen_.toString(10));
      return buffer.toString();
   }
}
