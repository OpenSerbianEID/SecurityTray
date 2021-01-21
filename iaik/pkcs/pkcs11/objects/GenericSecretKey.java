package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class GenericSecretKey extends SecretKey {
   protected ByteArrayAttribute value_;
   protected LongAttribute valueLen_;

   public GenericSecretKey() {
      this.keyType_.setLongValue(Key.KeyType.GENERIC_SECRET);
   }

   protected GenericSecretKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.keyType_.setLongValue(Key.KeyType.GENERIC_SECRET);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new GenericSecretKey(session, objectHandle);
   }

   protected static void putAttributesInTable(GenericSecretKey object) {
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
      GenericSecretKey clone = (GenericSecretKey)super.clone();
      clone.value_ = (ByteArrayAttribute)this.value_.clone();
      clone.valueLen_ = (LongAttribute)this.valueLen_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof GenericSecretKey) {
         GenericSecretKey other = (GenericSecretKey)otherObject;
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
