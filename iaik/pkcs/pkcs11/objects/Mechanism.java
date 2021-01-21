package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class Mechanism extends Object {
   protected LongAttribute mechanismType_;

   public Mechanism() {
   }

   protected Mechanism(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
   }

   protected static void putAttributesInTable(Mechanism object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.MECHANISM_TYPE, object.mechanismType_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.mechanismType_ = new LongAttribute(Attribute.MECHANISM_TYPE);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      Mechanism clone = (Mechanism)super.clone();
      clone.mechanismType_ = (LongAttribute)this.mechanismType_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      if (!(otherObject instanceof Mechanism)) {
         return false;
      } else {
         Mechanism other = (Mechanism)otherObject;
         return this == other || super.equals(other) && this.mechanismType_.equals(other.mechanismType_);
      }
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValue(session, this.objectHandle_, this.mechanismType_);
   }

   public LongAttribute getMechanismType() {
      return this.mechanismType_;
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(32);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Mechanism Type: ");
      buffer.append(this.mechanismType_.toString());
      return buffer.toString();
   }

   public int hashCode() {
      return this.mechanismType_.hashCode();
   }
}
