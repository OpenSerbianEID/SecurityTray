package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class X509AttributeCertificate extends Certificate {
   protected ByteArrayAttribute owner_;
   protected ByteArrayAttribute acIssuer_;
   protected ByteArrayAttribute serialNumber_;
   protected ByteArrayAttribute attrTypes_;
   protected ByteArrayAttribute value_;

   public X509AttributeCertificate() {
      this.certificateType_.setLongValue(Certificate.CertificateType.X_509_ATTRIBUTE);
   }

   protected X509AttributeCertificate(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.certificateType_.setLongValue(Certificate.CertificateType.X_509_ATTRIBUTE);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new X509AttributeCertificate(session, objectHandle);
   }

   protected static void putAttributesInTable(X509AttributeCertificate object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.OWNER, object.owner_);
         object.attributeTable_.put(Attribute.AC_ISSUER, object.acIssuer_);
         object.attributeTable_.put(Attribute.SERIAL_NUMBER, object.serialNumber_);
         object.attributeTable_.put(Attribute.ATTR_TYPES, object.attrTypes_);
         object.attributeTable_.put(Attribute.VALUE, object.value_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.owner_ = new ByteArrayAttribute(Attribute.OWNER);
      this.acIssuer_ = new ByteArrayAttribute(Attribute.AC_ISSUER);
      this.serialNumber_ = new ByteArrayAttribute(Attribute.SERIAL_NUMBER);
      this.attrTypes_ = new ByteArrayAttribute(Attribute.ATTR_TYPES);
      this.value_ = new ByteArrayAttribute(Attribute.VALUE);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      X509AttributeCertificate clone = (X509AttributeCertificate)super.clone();
      clone.owner_ = (ByteArrayAttribute)this.owner_.clone();
      clone.acIssuer_ = (ByteArrayAttribute)this.acIssuer_.clone();
      clone.serialNumber_ = (ByteArrayAttribute)this.serialNumber_.clone();
      clone.attrTypes_ = (ByteArrayAttribute)this.attrTypes_.clone();
      clone.value_ = (ByteArrayAttribute)this.value_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof X509AttributeCertificate) {
         X509AttributeCertificate other = (X509AttributeCertificate)otherObject;
         equal = this == other || super.equals(other) && this.owner_.equals(other.owner_) && this.acIssuer_.equals(other.acIssuer_) && this.serialNumber_.equals(other.serialNumber_) && this.attrTypes_.equals(other.attrTypes_) && this.value_.equals(other.value_);
      }

      return equal;
   }

   public ByteArrayAttribute getOwner() {
      return this.owner_;
   }

   public ByteArrayAttribute getAcIssuer() {
      return this.acIssuer_;
   }

   public ByteArrayAttribute getSerialNumber() {
      return this.serialNumber_;
   }

   public ByteArrayAttribute getAttrTypes() {
      return this.attrTypes_;
   }

   public ByteArrayAttribute getValue() {
      return this.value_;
   }

   public int hashCode() {
      return this.acIssuer_.hashCode() ^ this.serialNumber_.hashCode();
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.owner_, this.acIssuer_, this.serialNumber_, this.attrTypes_, this.value_});
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(256);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Owner (DER, hex): ");
      buffer.append(this.owner_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Attribute Certificate Issuer (DER, hex): ");
      buffer.append(this.acIssuer_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Serial Number (DER, hex): ");
      buffer.append(this.serialNumber_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Attribute Types (BER, hex): ");
      buffer.append(this.attrTypes_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Value (BER, hex): ");
      buffer.append(this.value_.toString());
      return buffer.toString();
   }
}
