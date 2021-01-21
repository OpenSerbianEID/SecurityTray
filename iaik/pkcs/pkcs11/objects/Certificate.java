package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

public class Certificate extends Storage {
   protected static Certificate.VendorDefinedCertificateBuilder vendorCertificateBuilder_;
   protected CertificateTypeAttribute certificateType_;
   protected BooleanAttribute trusted_;
   protected LongAttribute certificateCategory_;
   protected ByteArrayAttribute checkValue_;
   protected DateAttribute startDate_;
   protected DateAttribute endDate_;

   public Certificate() {
      this.objectClass_.setLongValue(Object.ObjectClass.CERTIFICATE);
   }

   protected Certificate(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.objectClass_.setLongValue(Object.ObjectClass.CERTIFICATE);
   }

   public static String getCertificateTypeName(Long certificateType) {
      if (certificateType == null) {
         throw new NullPointerException("Argument \"certificateType\" must not be null.");
      } else {
         String certificateTypeName;
         if (certificateType.equals(Certificate.CertificateType.X_509_PUBLIC_KEY)) {
            certificateTypeName = "X.509 Public Key";
         } else if (certificateType.equals(Certificate.CertificateType.X_509_ATTRIBUTE)) {
            certificateTypeName = "X.509 Attribute";
         } else if ((certificateType & Certificate.CertificateType.VENDOR_DEFINED) != 0L) {
            certificateTypeName = "Vendor Defined";
         } else {
            certificateTypeName = "<unknown>";
         }

         return certificateTypeName;
      }
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         CertificateTypeAttribute certificateTypeAttribute = new CertificateTypeAttribute();
         getAttributeValue(session, objectHandle, certificateTypeAttribute);
         Long certificateType = certificateTypeAttribute.getLongValue();
         Object newObject;
         if (certificateTypeAttribute.isPresent() && certificateType != null) {
            if (certificateType.equals(Certificate.CertificateType.X_509_PUBLIC_KEY)) {
               newObject = X509PublicKeyCertificate.getInstance(session, objectHandle);
            } else if (certificateType.equals(Certificate.CertificateType.X_509_ATTRIBUTE)) {
               newObject = X509AttributeCertificate.getInstance(session, objectHandle);
            } else if (certificateType.equals(Certificate.CertificateType.WTLS)) {
               newObject = WTLSCertificate.getInstance(session, objectHandle);
            } else if ((certificateType & Certificate.CertificateType.VENDOR_DEFINED) != 0L) {
               newObject = getUnknownCertificate(session, objectHandle);
            } else {
               newObject = getUnknownCertificate(session, objectHandle);
            }
         } else {
            newObject = getUnknownCertificate(session, objectHandle);
         }

         return newObject;
      }
   }

   protected static Object getUnknownCertificate(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         java.lang.Object newObject;
         if (vendorCertificateBuilder_ != null) {
            try {
               newObject = vendorCertificateBuilder_.build(session, objectHandle);
            } catch (PKCS11Exception var5) {
               newObject = new Certificate(session, objectHandle);
            }
         } else {
            newObject = new Certificate(session, objectHandle);
         }

         return (Object)newObject;
      }
   }

   public static void setVendorDefinedCertificateBuilder(Certificate.VendorDefinedCertificateBuilder builder) {
      vendorCertificateBuilder_ = builder;
   }

   public static Certificate.VendorDefinedCertificateBuilder getVendorDefinedCertificateBuilder() {
      return vendorCertificateBuilder_;
   }

   protected static void putAttributesInTable(Certificate object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.CERTIFICATE_TYPE, object.certificateType_);
         object.attributeTable_.put(Attribute.TRUSTED, object.trusted_);
         object.attributeTable_.put(Attribute.CERTIFICATE_CATEGORY, object.certificateCategory_);
         object.attributeTable_.put(Attribute.CHECK_VALUE, object.checkValue_);
         object.attributeTable_.put(Attribute.START_DATE, object.startDate_);
         object.attributeTable_.put(Attribute.END_DATE, object.endDate_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.certificateType_ = new CertificateTypeAttribute();
      this.trusted_ = new BooleanAttribute(Attribute.TRUSTED);
      this.certificateCategory_ = new LongAttribute(Attribute.CERTIFICATE_CATEGORY);
      this.checkValue_ = new ByteArrayAttribute(Attribute.CHECK_VALUE);
      this.startDate_ = new DateAttribute(Attribute.START_DATE);
      this.endDate_ = new DateAttribute(Attribute.END_DATE);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      Certificate clone = (Certificate)super.clone();
      clone.certificateType_ = (CertificateTypeAttribute)this.certificateType_.clone();
      clone.trusted_ = (BooleanAttribute)this.trusted_.clone();
      clone.certificateCategory_ = (LongAttribute)this.certificateCategory_.clone();
      clone.checkValue_ = (ByteArrayAttribute)this.checkValue_.clone();
      clone.startDate_ = (DateAttribute)this.startDate_.clone();
      clone.endDate_ = (DateAttribute)this.endDate_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Certificate) {
         Certificate other = (Certificate)otherObject;
         equal = this == other || super.equals(other) && this.certificateType_.equals(other.certificateType_) && this.trusted_.equals(other.trusted_) && this.certificateCategory_.equals(other.certificateCategory_) && this.checkValue_.equals(other.checkValue_) && this.startDate_.equals(other.startDate_) && this.endDate_.equals(other.endDate_);
      }

      return equal;
   }

   public LongAttribute getCertificateType() {
      return this.certificateType_;
   }

   public BooleanAttribute getTrusted() {
      return this.trusted_;
   }

   public LongAttribute getCertificateCategory() {
      return this.certificateCategory_;
   }

   public ByteArrayAttribute getCheckValue() {
      return this.checkValue_;
   }

   public DateAttribute getStartDate() {
      return this.startDate_;
   }

   public DateAttribute getEndDate() {
      return this.endDate_;
   }

   public int hashCode() {
      return this.certificateType_.hashCode();
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.trusted_, this.certificateCategory_, this.checkValue_, this.startDate_, this.endDate_});
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(128);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Certificate Type: ");
      if (this.certificateType_ != null) {
         buffer.append(this.certificateType_.toString());
      } else {
         buffer.append("<unavailable>");
      }

      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Trusted: ");
      buffer.append(this.trusted_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Certificate Category: ");
      buffer.append(this.certificateCategory_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Check Value: ");
      buffer.append(this.checkValue_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Start Date: ");
      buffer.append(this.startDate_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("End Date: ");
      buffer.append(this.endDate_.toString());
      return buffer.toString();
   }

   public interface VendorDefinedCertificateBuilder {
      Object build(Session var1, long var2) throws PKCS11Exception;
   }

   public interface CertificateType {
      Long X_509_PUBLIC_KEY = new Long(0L);
      Long X_509_ATTRIBUTE = new Long(1L);
      Long WTLS = new Long(2L);
      Long VENDOR_DEFINED = new Long(2147483648L);
   }
}
