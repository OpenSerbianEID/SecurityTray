package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class X509PublicKeyCertificate extends Certificate {
   protected ByteArrayAttribute subject_;
   protected ByteArrayAttribute id_;
   protected ByteArrayAttribute issuer_;
   protected ByteArrayAttribute serialNumber_;
   protected ByteArrayAttribute value_;
   protected CharArrayAttribute url_;
   protected ByteArrayAttribute hashOfSubjectPublicKey_;
   protected ByteArrayAttribute hashOfIssuerPublicKey_;
   protected LongAttribute javaMidpSecurityDomain_;

   public X509PublicKeyCertificate() {
      this.certificateType_.setLongValue(Certificate.CertificateType.X_509_PUBLIC_KEY);
   }

   protected X509PublicKeyCertificate(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.certificateType_.setLongValue(Certificate.CertificateType.X_509_PUBLIC_KEY);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new X509PublicKeyCertificate(session, objectHandle);
   }

   protected static void putAttributesInTable(X509PublicKeyCertificate object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.SUBJECT, object.subject_);
         object.attributeTable_.put(Attribute.ID, object.id_);
         object.attributeTable_.put(Attribute.ISSUER, object.issuer_);
         object.attributeTable_.put(Attribute.SERIAL_NUMBER, object.serialNumber_);
         object.attributeTable_.put(Attribute.VALUE, object.value_);
         object.attributeTable_.put(Attribute.URL, object.url_);
         object.attributeTable_.put(Attribute.HASH_OF_SUBJECT_PUBLIC_KEY, object.hashOfSubjectPublicKey_);
         object.attributeTable_.put(Attribute.HASH_OF_ISSUER_PUBLIC_KEY, object.hashOfIssuerPublicKey_);
         object.attributeTable_.put(Attribute.JAVA_MIDP_SECURITY_DOMAIN, object.javaMidpSecurityDomain_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.subject_ = new ByteArrayAttribute(Attribute.SUBJECT);
      this.id_ = new ByteArrayAttribute(Attribute.ID);
      this.issuer_ = new ByteArrayAttribute(Attribute.ISSUER);
      this.serialNumber_ = new ByteArrayAttribute(Attribute.SERIAL_NUMBER);
      this.value_ = new ByteArrayAttribute(Attribute.VALUE);
      this.url_ = new CharArrayAttribute(Attribute.URL);
      this.hashOfSubjectPublicKey_ = new ByteArrayAttribute(Attribute.HASH_OF_SUBJECT_PUBLIC_KEY);
      this.hashOfIssuerPublicKey_ = new ByteArrayAttribute(Attribute.HASH_OF_ISSUER_PUBLIC_KEY);
      this.javaMidpSecurityDomain_ = new LongAttribute(Attribute.JAVA_MIDP_SECURITY_DOMAIN);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      X509PublicKeyCertificate clone = (X509PublicKeyCertificate)super.clone();
      clone.subject_ = (ByteArrayAttribute)this.subject_.clone();
      clone.id_ = (ByteArrayAttribute)this.id_.clone();
      clone.issuer_ = (ByteArrayAttribute)this.issuer_.clone();
      clone.serialNumber_ = (ByteArrayAttribute)this.serialNumber_.clone();
      clone.value_ = (ByteArrayAttribute)this.value_.clone();
      clone.url_ = (CharArrayAttribute)this.url_.clone();
      clone.hashOfSubjectPublicKey_ = (ByteArrayAttribute)this.hashOfSubjectPublicKey_.clone();
      clone.hashOfIssuerPublicKey_ = (ByteArrayAttribute)this.hashOfIssuerPublicKey_.clone();
      clone.javaMidpSecurityDomain_ = (LongAttribute)this.javaMidpSecurityDomain_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof X509PublicKeyCertificate) {
         X509PublicKeyCertificate other = (X509PublicKeyCertificate)otherObject;
         equal = this == other || super.equals(other) && this.subject_.equals(other.subject_) && this.id_.equals(other.id_) && this.issuer_.equals(other.issuer_) && this.serialNumber_.equals(other.serialNumber_) && this.value_.equals(other.value_) && this.url_.equals(other.url_) && this.hashOfSubjectPublicKey_.equals(other.hashOfSubjectPublicKey_) && this.hashOfIssuerPublicKey_.equals(other.hashOfIssuerPublicKey_) && this.javaMidpSecurityDomain_.equals(other.javaMidpSecurityDomain_);
      }

      return equal;
   }

   public ByteArrayAttribute getSubject() {
      return this.subject_;
   }

   public ByteArrayAttribute getId() {
      return this.id_;
   }

   public ByteArrayAttribute getIssuer() {
      return this.issuer_;
   }

   public ByteArrayAttribute getSerialNumber() {
      return this.serialNumber_;
   }

   public ByteArrayAttribute getValue() {
      return this.value_;
   }

   public CharArrayAttribute getUrl() {
      return this.url_;
   }

   public ByteArrayAttribute getHashOfSubjectPublicKey() {
      return this.hashOfSubjectPublicKey_;
   }

   public ByteArrayAttribute getHashOfIssuerPublicKey() {
      return this.hashOfIssuerPublicKey_;
   }

   public LongAttribute getJavaMidpSecurityDomain() {
      return this.javaMidpSecurityDomain_;
   }

   public int hashCode() {
      return this.issuer_.hashCode() ^ this.serialNumber_.hashCode();
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.subject_, this.id_, this.issuer_, this.serialNumber_, this.value_});
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.url_, this.hashOfSubjectPublicKey_, this.hashOfIssuerPublicKey_, this.javaMidpSecurityDomain_});
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(256);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Subject (DER, hex): ");
      buffer.append(this.subject_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ID (hex): ");
      buffer.append(this.id_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Issuer (DER, hex): ");
      buffer.append(this.issuer_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Serial Number (DER, hex): ");
      buffer.append(this.serialNumber_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Value (BER, hex): ");
      buffer.append(this.value_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("URL: ");
      buffer.append(this.url_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Hash Of Subject Public Key: ");
      buffer.append(this.hashOfSubjectPublicKey_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Hash Of Issuer Public Key: ");
      buffer.append(this.hashOfIssuerPublicKey_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Java MIDP Security Domain: ");
      buffer.append(this.javaMidpSecurityDomain_.toString());
      return buffer.toString();
   }
}
