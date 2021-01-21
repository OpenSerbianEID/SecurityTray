package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class Data extends Storage {
   protected CharArrayAttribute application_;
   protected ByteArrayAttribute objectID_;
   protected ByteArrayAttribute value_;

   public Data() {
      this.objectClass_.setLongValue(Object.ObjectClass.DATA);
   }

   protected Data(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.objectClass_.setLongValue(Object.ObjectClass.DATA);
   }

   protected static void putAttributesInTable(Data object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.APPLICATION, object.application_);
         object.attributeTable_.put(Attribute.OBJECT_ID, object.objectID_);
         object.attributeTable_.put(Attribute.VALUE, object.value_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.application_ = new CharArrayAttribute(Attribute.APPLICATION);
      this.objectID_ = new ByteArrayAttribute(Attribute.OBJECT_ID);
      this.value_ = new ByteArrayAttribute(Attribute.VALUE);
      putAttributesInTable(this);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new Data(session, objectHandle);
   }

   public java.lang.Object clone() {
      Data clone = (Data)super.clone();
      clone.application_ = (CharArrayAttribute)this.application_.clone();
      clone.objectID_ = (ByteArrayAttribute)this.objectID_.clone();
      clone.value_ = (ByteArrayAttribute)this.value_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Data) {
         Data other = (Data)otherObject;
         equal = this == other || super.equals(other) && this.application_.equals(other.application_) && this.objectID_.equals(other.objectID_) && this.value_.equals(other.value_);
      }

      return equal;
   }

   public CharArrayAttribute getApplication() {
      return this.application_;
   }

   public ByteArrayAttribute getObjectID() {
      return this.objectID_;
   }

   public ByteArrayAttribute getValue() {
      return this.value_;
   }

   public int hashCode() {
      return this.application_.hashCode() ^ this.objectID_.hashCode() ^ this.value_.hashCode();
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValue(session, this.objectHandle_, this.application_);
      Object.getAttributeValue(session, this.objectHandle_, this.objectID_);
      Object.getAttributeValue(session, this.objectHandle_, this.value_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(128);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Application: ");
      buffer.append(this.application_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Object ID (DER, hex): ");
      buffer.append(this.objectID_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Value (hex): ");
      buffer.append(this.value_.toString());
      return buffer.toString();
   }
}
