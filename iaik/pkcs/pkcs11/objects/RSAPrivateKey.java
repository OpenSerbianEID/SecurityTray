package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class RSAPrivateKey extends PrivateKey {
   protected ByteArrayAttribute modulus_;
   protected ByteArrayAttribute publicExponent_;
   protected ByteArrayAttribute privateExponent_;
   protected ByteArrayAttribute prime1_;
   protected ByteArrayAttribute prime2_;
   protected ByteArrayAttribute exponent1_;
   protected ByteArrayAttribute exponent2_;
   protected ByteArrayAttribute coefficient_;

   public RSAPrivateKey() {
      this.keyType_.setLongValue(Key.KeyType.RSA);
   }

   protected RSAPrivateKey(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.keyType_.setLongValue(Key.KeyType.RSA);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new RSAPrivateKey(session, objectHandle);
   }

   protected static void putAttributesInTable(RSAPrivateKey object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.MODULUS, object.modulus_);
         object.attributeTable_.put(Attribute.PUBLIC_EXPONENT, object.publicExponent_);
         object.attributeTable_.put(Attribute.PRIVATE_EXPONENT, object.privateExponent_);
         object.attributeTable_.put(Attribute.PRIME_1, object.prime1_);
         object.attributeTable_.put(Attribute.PRIME_2, object.prime2_);
         object.attributeTable_.put(Attribute.EXPONENT_1, object.exponent1_);
         object.attributeTable_.put(Attribute.EXPONENT_2, object.exponent2_);
         object.attributeTable_.put(Attribute.COEFFICIENT, object.coefficient_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.modulus_ = new ByteArrayAttribute(Attribute.MODULUS);
      this.publicExponent_ = new ByteArrayAttribute(Attribute.PUBLIC_EXPONENT);
      this.privateExponent_ = new ByteArrayAttribute(Attribute.PRIVATE_EXPONENT);
      this.prime1_ = new ByteArrayAttribute(Attribute.PRIME_1);
      this.prime2_ = new ByteArrayAttribute(Attribute.PRIME_2);
      this.exponent1_ = new ByteArrayAttribute(Attribute.EXPONENT_1);
      this.exponent2_ = new ByteArrayAttribute(Attribute.EXPONENT_2);
      this.coefficient_ = new ByteArrayAttribute(Attribute.COEFFICIENT);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      RSAPrivateKey clone = (RSAPrivateKey)super.clone();
      clone.modulus_ = (ByteArrayAttribute)this.modulus_.clone();
      clone.publicExponent_ = (ByteArrayAttribute)this.publicExponent_.clone();
      clone.privateExponent_ = (ByteArrayAttribute)this.privateExponent_.clone();
      clone.prime1_ = (ByteArrayAttribute)this.prime1_.clone();
      clone.prime2_ = (ByteArrayAttribute)this.prime2_.clone();
      clone.exponent1_ = (ByteArrayAttribute)this.exponent1_.clone();
      clone.exponent2_ = (ByteArrayAttribute)this.exponent2_.clone();
      clone.coefficient_ = (ByteArrayAttribute)this.coefficient_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof RSAPrivateKey) {
         RSAPrivateKey other = (RSAPrivateKey)otherObject;
         equal = this == other || super.equals(other) && this.modulus_.equals(other.modulus_) && this.publicExponent_.equals(other.publicExponent_) && this.privateExponent_.equals(other.privateExponent_) && this.prime1_.equals(other.prime1_) && this.prime2_.equals(other.prime2_) && this.exponent1_.equals(other.exponent1_) && this.exponent2_.equals(other.exponent2_) && this.coefficient_.equals(other.coefficient_);
      }

      return equal;
   }

   public ByteArrayAttribute getModulus() {
      return this.modulus_;
   }

   public ByteArrayAttribute getPublicExponent() {
      return this.publicExponent_;
   }

   public ByteArrayAttribute getPrivateExponent() {
      return this.privateExponent_;
   }

   public ByteArrayAttribute getPrime1() {
      return this.prime1_;
   }

   public ByteArrayAttribute getPrime2() {
      return this.prime2_;
   }

   public ByteArrayAttribute getExponent1() {
      return this.exponent1_;
   }

   public ByteArrayAttribute getExponent2() {
      return this.exponent2_;
   }

   public ByteArrayAttribute getCoefficient() {
      return this.coefficient_;
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.modulus_, this.publicExponent_});
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.privateExponent_, this.prime1_, this.prime2_, this.exponent1_, this.exponent2_, this.coefficient_});
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
      buffer.append("Private Exponent (hex): ");
      buffer.append(this.privateExponent_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Prime 1 (hex): ");
      buffer.append(this.prime1_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Prime 2 (hex): ");
      buffer.append(this.prime2_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Exponent 1 (hex): ");
      buffer.append(this.exponent1_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Exponent 2 (hex): ");
      buffer.append(this.exponent2_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Coefficient (hex): ");
      buffer.append(this.coefficient_.toString());
      return buffer.toString();
   }
}
