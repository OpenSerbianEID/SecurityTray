package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class BatonSecretKey extends SecretKey {
   protected ByteArrayAttribute value_;

   public BatonSecretKey() {
      this.keyType_.setLongValue(Key.KeyType.BATON);
   }

   protected BatonSecretKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.keyType_.setLongValue(Key.KeyType.BATON);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new BatonSecretKey(session, objectHandle);
   }

   protected static void putAttributesInTable(BatonSecretKey object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.VALUE, object.value_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.value_ = new ByteArrayAttribute(Attribute.VALUE);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      BatonSecretKey clone = (BatonSecretKey)super.clone();
      clone.value_ = (ByteArrayAttribute)this.value_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof BatonSecretKey) {
         BatonSecretKey other = (BatonSecretKey)otherObject;
         equal = this == other || super.equals(other) && this.value_.equals(other.value_);
      }

      return equal;
   }

   public ByteArrayAttribute getValue() {
      return this.value_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValue(session, this.objectHandle_, this.value_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(1024);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Value (hex): ");
      buffer.append(this.value_.toString());
      return buffer.toString();
   }
}