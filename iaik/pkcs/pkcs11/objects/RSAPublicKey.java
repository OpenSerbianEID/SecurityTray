package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class RSAPublicKey extends PublicKey {
   protected ByteArrayAttribute modulus_;
   protected ByteArrayAttribute publicExponent_;
   protected LongAttribute modulusBits_;

   public RSAPublicKey() {
      this.keyType_.setLongValue(Key.KeyType.RSA);
   }

   protected RSAPublicKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.keyType_.setLongValue(Key.KeyType.RSA);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new RSAPublicKey(session, objectHandle);
   }

   protected static void putAttributesInTable(RSAPublicKey object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.MODULUS, object.modulus_);
         object.attributeTable_.put(Attribute.PUBLIC_EXPONENT, object.publicExponent_);
         object.attributeTable_.put(Attribute.MODULUS_BITS, object.modulusBits_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.modulus_ = new ByteArrayAttribute(Attribute.MODULUS);
      this.publicExponent_ = new ByteArrayAttribute(Attribute.PUBLIC_EXPONENT);
      this.modulusBits_ = new LongAttribute(Attribute.MODULUS_BITS);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      RSAPublicKey clone = (RSAPublicKey)super.clone();
      clone.modulus_ = (ByteArrayAttribute)this.modulus_.clone();
      clone.publicExponent_ = (ByteArrayAttribute)this.publicExponent_.clone();
      clone.modulusBits_ = (LongAttribute)this.modulusBits_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RSAPublicKey) {
         RSAPublicKey other = (RSAPublicKey)otherObject;
         equal = this == other || super.equals(other) && this.modulus_.equals(other.modulus_) && this.publicExponent_.equals(other.publicExponent_) && this.modulusBits_.equals(other.modulusBits_);
      }

      return equal;
   }

   public ByteArrayAttribute getModulus() {
      return this.modulus_;
   }

   public ByteArrayAttribute getPublicExponent() {
      return this.publicExponent_;
   }

   public LongAttribute getModulusBits() {
      return this.modulusBits_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.modulus_, this.publicExponent_, this.modulusBits_});
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(1024);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Modulus (hex): ");
      buffer.append(this.modulus_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Public Exponent (hex): ");
      buffer.append(this.publicExponent_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Modulus Bits (dec): ");
      buffer.append(this.modulusBits_.toString(10));
      return buffer.toString();
   }
}
