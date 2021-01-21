package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class ECDSAPublicKey extends PublicKey {
   protected ByteArrayAttribute ecdsaParams_;
   protected ByteArrayAttribute ecPoint_;

   public ECDSAPublicKey() {
      this.keyType_.setLongValue(Key.KeyType.ECDSA);
   }

   protected ECDSAPublicKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.keyType_.setLongValue(Key.KeyType.ECDSA);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new ECDSAPublicKey(session, objectHandle);
   }

   protected static void putAttributesInTable(ECDSAPublicKey object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.ECDSA_PARAMS, object.ecdsaParams_);
         object.attributeTable_.put(Attribute.EC_POINT, object.ecPoint_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.ecdsaParams_ = new ByteArrayAttribute(Attribute.ECDSA_PARAMS);
      this.ecPoint_ = new ByteArrayAttribute(Attribute.EC_POINT);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      ECDSAPublicKey clone = (ECDSAPublicKey)super.clone();
      clone.ecdsaParams_ = (ByteArrayAttribute)this.ecdsaParams_.clone();
      clone.ecPoint_ = (ByteArrayAttribute)this.ecPoint_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof ECDSAPublicKey) {
         ECDSAPublicKey other = (ECDSAPublicKey)otherObject;
         equal = this == other || super.equals(other) && this.ecdsaParams_.equals(other.ecdsaParams_) && this.ecPoint_.equals(other.ecPoint_);
      }

      return equal;
   }

   public ByteArrayAttribute getEcdsaParams() {
      return this.ecdsaParams_;
   }

   public ByteArrayAttribute getEcPoint() {
      return this.ecPoint_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.ecdsaParams_, this.ecPoint_});
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
      buffer.append("EC Point (DER, hex): ");
      buffer.append(this.ecPoint_.toString());
      return buffer.toString();
   }
}
