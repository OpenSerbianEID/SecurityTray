package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.TokenRuntimeException;
import iaik.pkcs.pkcs11.wrapper.CK_ATTRIBUTE;
import java.util.Hashtable;

public class Attribute implements Cloneable {
   public static final Long CLASS = new Long(0L);
   public static final Long TOKEN = new Long(1L);
   public static final Long PRIVATE = new Long(2L);
   public static final Long LABEL = new Long(3L);
   public static final Long APPLICATION = new Long(16L);
   public static final Long VALUE = new Long(17L);
   public static final Long OBJECT_ID = new Long(18L);
   public static final Long CERTIFICATE_TYPE = new Long(128L);
   public static final Long ISSUER = new Long(129L);
   public static final Long SERIAL_NUMBER = new Long(130L);
   public static final Long URL = new Long(137L);
   public static final Long HASH_OF_SUBJECT_PUBLIC_KEY = new Long(138L);
   public static final Long HASH_OF_ISSUER_PUBLIC_KEY = new Long(139L);
   public static final Long JAVA_MIDP_SECURITY_DOMAIN = new Long(136L);
   public static final Long AC_ISSUER = new Long(131L);
   public static final Long OWNER = new Long(132L);
   public static final Long ATTR_TYPES = new Long(133L);
   public static final Long TRUSTED = new Long(134L);
   public static final Long KEY_TYPE = new Long(256L);
   public static final Long SUBJECT = new Long(257L);
   public static final Long ID = new Long(258L);
   public static final Long CHECK_VALUE = new Long(144L);
   public static final Long CERTIFICATE_CATEGORY = new Long(135L);
   public static final Long SENSITIVE = new Long(259L);
   public static final Long ENCRYPT = new Long(260L);
   public static final Long DECRYPT = new Long(261L);
   public static final Long WRAP = new Long(262L);
   public static final Long WRAP_TEMPLATE = new Long(1073742353L);
   public static final Long UNWRAP = new Long(263L);
   public static final Long UNWRAP_TEMPLATE = new Long(1073742354L);
   public static final Long SIGN = new Long(264L);
   public static final Long SIGN_RECOVER = new Long(265L);
   public static final Long VERIFY = new Long(266L);
   public static final Long VERIFY_RECOVER = new Long(267L);
   public static final Long DERIVE = new Long(268L);
   public static final Long START_DATE = new Long(272L);
   public static final Long END_DATE = new Long(273L);
   public static final Long MECHANISM_TYPE = new Long(1280L);
   public static final Long MODULUS = new Long(288L);
   public static final Long MODULUS_BITS = new Long(289L);
   public static final Long PUBLIC_EXPONENT = new Long(290L);
   public static final Long PRIVATE_EXPONENT = new Long(291L);
   public static final Long PRIME_1 = new Long(292L);
   public static final Long PRIME_2 = new Long(293L);
   public static final Long EXPONENT_1 = new Long(294L);
   public static final Long EXPONENT_2 = new Long(295L);
   public static final Long COEFFICIENT = new Long(296L);
   public static final Long PRIME = new Long(304L);
   public static final Long SUBPRIME = new Long(305L);
   public static final Long BASE = new Long(306L);
   public static final Long PRIME_BITS = new Long(307L);
   public static final Long SUB_PRIME_BITS = new Long(308L);
   public static final Long VALUE_BITS = new Long(352L);
   public static final Long VALUE_LEN = new Long(353L);
   public static final Long EXTRACTABLE = new Long(354L);
   public static final Long LOCAL = new Long(355L);
   public static final Long NEVER_EXTRACTABLE = new Long(356L);
   public static final Long WRAP_WITH_TRUSTED = new Long(528L);
   public static final Long ALWAYS_SENSITIVE = new Long(357L);
   public static final Long ALWAYS_AUTHENTICATE = new Long(514L);
   public static final Long KEY_GEN_MECHANISM = new Long(358L);
   public static final Long ALLOWED_MECHANISMS = new Long(1073743360L);
   public static final Long MODIFIABLE = new Long(368L);
   public static final Long ECDSA_PARAMS = new Long(384L);
   public static final Long EC_PARAMS = new Long(384L);
   public static final Long EC_POINT = new Long(385L);
   public static final Long SECONDARY_AUTH = new Long(512L);
   public static final Long AUTH_PIN_FLAGS = new Long(513L);
   public static final Long HW_FEATURE_TYPE = new Long(768L);
   public static final Long RESET_ON_INIT = new Long(769L);
   public static final Long HAS_RESET = new Long(770L);
   public static final Long VENDOR_DEFINED = new Long(2147483648L);
   public static final Long PIXEL_X = new Long(1024L);
   public static final Long PIXEL_Y = new Long(1025L);
   public static final Long RESOLUTION = new Long(1026L);
   public static final Long CHAR_ROWS = new Long(1027L);
   public static final Long CHAR_COLUMNS = new Long(1028L);
   public static final Long COLOR = new Long(1029L);
   public static final Long BITS_PER_PIXEL = new Long(1030L);
   public static final Long CHAR_SETS = new Long(1152L);
   public static final Long ENCODING_METHODS = new Long(1153L);
   public static final Long MIME_TYPES = new Long(1154L);
   protected static Hashtable attributeNames_;
   protected static Hashtable attributeClasses_;
   protected boolean present_;
   protected boolean sensitive_;
   protected CK_ATTRIBUTE ckAttribute_;

   protected Attribute() {
   }

   protected Attribute(Long type) {
      if (type == null) {
         throw new NullPointerException("Argument \"type\" must not be null.");
      } else {
         this.present_ = false;
         this.sensitive_ = false;
         this.ckAttribute_ = new CK_ATTRIBUTE();
         this.ckAttribute_.type = type;
      }
   }

   protected static synchronized String getAttributeName(Long type) {
      if (type == null) {
         throw new NullPointerException("Argument \"type\" must not be null.");
      } else {
         if (attributeNames_ == null) {
            attributeNames_ = new Hashtable(85);
            attributeNames_.put(CLASS, "Class");
            attributeNames_.put(TOKEN, "Token");
            attributeNames_.put(PRIVATE, "Private");
            attributeNames_.put(LABEL, "Label");
            attributeNames_.put(APPLICATION, "Application");
            attributeNames_.put(VALUE, "Value");
            attributeNames_.put(OBJECT_ID, "Object ID");
            attributeNames_.put(CERTIFICATE_TYPE, "Certificate Type");
            attributeNames_.put(ISSUER, "Issuer");
            attributeNames_.put(SERIAL_NUMBER, "Serial Number");
            attributeNames_.put(URL, "URL");
            attributeNames_.put(HASH_OF_SUBJECT_PUBLIC_KEY, "Hash Of Subject Public Key");
            attributeNames_.put(HASH_OF_ISSUER_PUBLIC_KEY, "Hash Of Issuer Public Key");
            attributeNames_.put(JAVA_MIDP_SECURITY_DOMAIN, "Java MIDP Security Domain");
            attributeNames_.put(AC_ISSUER, "AC Issuer");
            attributeNames_.put(OWNER, "Owner");
            attributeNames_.put(ATTR_TYPES, "Attribute Types");
            attributeNames_.put(TRUSTED, "Trusted");
            attributeNames_.put(KEY_TYPE, "Key Type");
            attributeNames_.put(SUBJECT, "Subject");
            attributeNames_.put(ID, "ID");
            attributeNames_.put(CHECK_VALUE, "Check Value");
            attributeNames_.put(CERTIFICATE_CATEGORY, "Certificate Category");
            attributeNames_.put(SENSITIVE, "Sensitive");
            attributeNames_.put(ENCRYPT, "Encrypt");
            attributeNames_.put(DECRYPT, "Decrypt");
            attributeNames_.put(WRAP, "Wrap");
            attributeNames_.put(UNWRAP, "Unwrap");
            attributeNames_.put(WRAP_TEMPLATE, "Wrap Template");
            attributeNames_.put(UNWRAP_TEMPLATE, "Unwrap Template");
            attributeNames_.put(SIGN, "Sign");
            attributeNames_.put(SIGN_RECOVER, "Sign Recover");
            attributeNames_.put(VERIFY, "Verify");
            attributeNames_.put(VERIFY_RECOVER, "Verify Recover");
            attributeNames_.put(DERIVE, "Derive");
            attributeNames_.put(START_DATE, "Start Date");
            attributeNames_.put(END_DATE, "End Date");
            attributeNames_.put(MODULUS, "Modulus");
            attributeNames_.put(MODULUS_BITS, "Modulus Bits");
            attributeNames_.put(PUBLIC_EXPONENT, "Public Exponent");
            attributeNames_.put(PRIVATE_EXPONENT, "Private Exponent");
            attributeNames_.put(PRIME_1, "Prime 1");
            attributeNames_.put(PRIME_2, "Prime 2");
            attributeNames_.put(EXPONENT_1, "Exponent 1");
            attributeNames_.put(EXPONENT_2, "Exponent 2");
            attributeNames_.put(COEFFICIENT, "Coefficient");
            attributeNames_.put(PRIME, "Prime");
            attributeNames_.put(SUBPRIME, "Subprime");
            attributeNames_.put(BASE, "Base");
            attributeNames_.put(PRIME_BITS, "Prime Pits");
            attributeNames_.put(SUB_PRIME_BITS, "Subprime Bits");
            attributeNames_.put(VALUE_BITS, "Value Bits");
            attributeNames_.put(VALUE_LEN, "Value Length");
            attributeNames_.put(EXTRACTABLE, "Extractable");
            attributeNames_.put(LOCAL, "Local");
            attributeNames_.put(NEVER_EXTRACTABLE, "Never Extractable");
            attributeNames_.put(WRAP_WITH_TRUSTED, "Wrap With Trusted");
            attributeNames_.put(ALWAYS_SENSITIVE, "Always Sensitive");
            attributeNames_.put(ALWAYS_AUTHENTICATE, "Always Authenticate");
            attributeNames_.put(KEY_GEN_MECHANISM, "Key Generation Mechanism");
            attributeNames_.put(ALLOWED_MECHANISMS, "Allowed Mechanisms");
            attributeNames_.put(MODIFIABLE, "Modifiable");
            attributeNames_.put(ECDSA_PARAMS, "ECDSA Parameters");
            attributeNames_.put(EC_PARAMS, "EC Parameters");
            attributeNames_.put(EC_POINT, "EC Point");
            attributeNames_.put(SECONDARY_AUTH, "Secondary Authentication");
            attributeNames_.put(AUTH_PIN_FLAGS, "Authentication PIN Flags");
            attributeNames_.put(HW_FEATURE_TYPE, "Hardware Feature Type");
            attributeNames_.put(RESET_ON_INIT, "Reset on Initialization");
            attributeNames_.put(HAS_RESET, "Has been reset");
            attributeNames_.put(VENDOR_DEFINED, "Vendor Defined");
         }

         String name;
         StringBuffer nameBuffer;
         if ((type & VENDOR_DEFINED) != 0L) {
            nameBuffer = new StringBuffer(36);
            nameBuffer.append("VENDOR_DEFINED [0x");
            nameBuffer.append(Long.toHexString(type));
            nameBuffer.append(']');
            name = nameBuffer.toString();
         } else {
            name = (String)attributeNames_.get(type);
            if (name == null) {
               nameBuffer = new StringBuffer(25);
               nameBuffer.append("[0x");
               nameBuffer.append(Long.toHexString(type));
               nameBuffer.append(']');
               name = nameBuffer.toString();
            }
         }

         return name;
      }
   }

   protected static synchronized Class getAttributeClass(Long type) {
      if (type == null) {
         throw new NullPointerException("Argument \"type\" must not be null.");
      } else {
         if (attributeClasses_ == null) {
            attributeClasses_ = new Hashtable(85);
            attributeClasses_.put(CLASS, ObjectClassAttribute.class);
            attributeClasses_.put(TOKEN, BooleanAttribute.class);
            attributeClasses_.put(PRIVATE, BooleanAttribute.class);
            attributeClasses_.put(LABEL, CharArrayAttribute.class);
            attributeClasses_.put(APPLICATION, CharArrayAttribute.class);
            attributeClasses_.put(VALUE, ByteArrayAttribute.class);
            attributeClasses_.put(OBJECT_ID, ByteArrayAttribute.class);
            attributeClasses_.put(CERTIFICATE_TYPE, CertificateTypeAttribute.class);
            attributeClasses_.put(ISSUER, ByteArrayAttribute.class);
            attributeClasses_.put(SERIAL_NUMBER, ByteArrayAttribute.class);
            attributeClasses_.put(URL, CharArrayAttribute.class);
            attributeClasses_.put(HASH_OF_SUBJECT_PUBLIC_KEY, ByteArrayAttribute.class);
            attributeClasses_.put(HASH_OF_ISSUER_PUBLIC_KEY, ByteArrayAttribute.class);
            attributeClasses_.put(JAVA_MIDP_SECURITY_DOMAIN, LongAttribute.class);
            attributeClasses_.put(AC_ISSUER, ByteArrayAttribute.class);
            attributeClasses_.put(OWNER, ByteArrayAttribute.class);
            attributeClasses_.put(ATTR_TYPES, ByteArrayAttribute.class);
            attributeClasses_.put(TRUSTED, BooleanAttribute.class);
            attributeClasses_.put(KEY_TYPE, KeyTypeAttribute.class);
            attributeClasses_.put(SUBJECT, ByteArrayAttribute.class);
            attributeClasses_.put(ID, ByteArrayAttribute.class);
            attributeClasses_.put(CHECK_VALUE, ByteArrayAttribute.class);
            attributeClasses_.put(CERTIFICATE_CATEGORY, LongAttribute.class);
            attributeClasses_.put(SENSITIVE, BooleanAttribute.class);
            attributeClasses_.put(ENCRYPT, BooleanAttribute.class);
            attributeClasses_.put(DECRYPT, BooleanAttribute.class);
            attributeClasses_.put(WRAP, BooleanAttribute.class);
            attributeClasses_.put(UNWRAP, BooleanAttribute.class);
            attributeClasses_.put(WRAP_TEMPLATE, AttributeArray.class);
            attributeClasses_.put(UNWRAP_TEMPLATE, AttributeArray.class);
            attributeClasses_.put(SIGN, BooleanAttribute.class);
            attributeClasses_.put(SIGN_RECOVER, BooleanAttribute.class);
            attributeClasses_.put(VERIFY, BooleanAttribute.class);
            attributeClasses_.put(VERIFY_RECOVER, BooleanAttribute.class);
            attributeClasses_.put(DERIVE, BooleanAttribute.class);
            attributeClasses_.put(START_DATE, DateAttribute.class);
            attributeClasses_.put(END_DATE, DateAttribute.class);
            attributeClasses_.put(MODULUS, ByteArrayAttribute.class);
            attributeClasses_.put(MODULUS_BITS, LongAttribute.class);
            attributeClasses_.put(PUBLIC_EXPONENT, ByteArrayAttribute.class);
            attributeClasses_.put(PRIVATE_EXPONENT, ByteArrayAttribute.class);
            attributeClasses_.put(PRIME_1, ByteArrayAttribute.class);
            attributeClasses_.put(PRIME_2, ByteArrayAttribute.class);
            attributeClasses_.put(EXPONENT_1, ByteArrayAttribute.class);
            attributeClasses_.put(EXPONENT_2, ByteArrayAttribute.class);
            attributeClasses_.put(COEFFICIENT, ByteArrayAttribute.class);
            attributeClasses_.put(PRIME, ByteArrayAttribute.class);
            attributeClasses_.put(SUBPRIME, ByteArrayAttribute.class);
            attributeClasses_.put(BASE, ByteArrayAttribute.class);
            attributeClasses_.put(PRIME_BITS, LongAttribute.class);
            attributeClasses_.put(SUB_PRIME_BITS, LongAttribute.class);
            attributeClasses_.put(VALUE_BITS, LongAttribute.class);
            attributeClasses_.put(VALUE_LEN, LongAttribute.class);
            attributeClasses_.put(EXTRACTABLE, BooleanAttribute.class);
            attributeClasses_.put(LOCAL, BooleanAttribute.class);
            attributeClasses_.put(NEVER_EXTRACTABLE, BooleanAttribute.class);
            attributeClasses_.put(WRAP_WITH_TRUSTED, BooleanAttribute.class);
            attributeClasses_.put(ALWAYS_SENSITIVE, BooleanAttribute.class);
            attributeClasses_.put(ALWAYS_AUTHENTICATE, BooleanAttribute.class);
            attributeClasses_.put(KEY_GEN_MECHANISM, MechanismAttribute.class);
            attributeClasses_.put(ALLOWED_MECHANISMS, MechanismArrayAttribute.class);
            attributeClasses_.put(MODIFIABLE, BooleanAttribute.class);
            attributeClasses_.put(ECDSA_PARAMS, ByteArrayAttribute.class);
            attributeClasses_.put(EC_PARAMS, ByteArrayAttribute.class);
            attributeClasses_.put(EC_POINT, ByteArrayAttribute.class);
            attributeClasses_.put(SECONDARY_AUTH, BooleanAttribute.class);
            attributeClasses_.put(AUTH_PIN_FLAGS, LongAttribute.class);
            attributeClasses_.put(HW_FEATURE_TYPE, HardwareFeatureTypeAttribute.class);
            attributeClasses_.put(RESET_ON_INIT, BooleanAttribute.class);
            attributeClasses_.put(HAS_RESET, BooleanAttribute.class);
         }

         Class implementation = (Class)attributeClasses_.get(type);
         return implementation;
      }
   }

   public java.lang.Object clone() {
      try {
         Attribute clone = (Attribute)super.clone();
         clone.ckAttribute_ = (CK_ATTRIBUTE)this.ckAttribute_.clone();
         return clone;
      } catch (CloneNotSupportedException var3) {
         throw new TokenRuntimeException("An unexpected clone exception occurred.", var3);
      }
   }

   public void setPresent(boolean present) {
      this.present_ = present;
   }

   public void setSensitive(boolean sensitive) {
      this.sensitive_ = sensitive;
   }

   protected void setCkAttribute(CK_ATTRIBUTE ckAttribute) {
      if (ckAttribute == null) {
         throw new NullPointerException("Argument \"ckAttribute\" must not be null.");
      } else {
         this.ckAttribute_ = ckAttribute;
      }
   }

   public boolean isPresent() {
      return this.present_;
   }

   public boolean isSensitive() {
      return this.sensitive_;
   }

   protected CK_ATTRIBUTE getCkAttribute() {
      return this.ckAttribute_;
   }

   protected String getValueString() {
      String valueString;
      if (this.ckAttribute_ != null && this.ckAttribute_.pValue != null) {
         valueString = this.ckAttribute_.pValue.toString();
      } else {
         valueString = "<NULL_PTR>";
      }

      return valueString;
   }

   public String toString() {
      return this.toString(false);
   }

   public String toString(boolean withName) {
      StringBuffer buffer = new StringBuffer(32);
      if (withName) {
         String typeName = getAttributeName(new Long(this.ckAttribute_.type));
         buffer.append(typeName);
         buffer.append(": ");
      }

      if (this.present_) {
         if (this.sensitive_) {
            buffer.append("<Value is sensitive>");
         } else {
            buffer.append(this.getValueString());
         }
      } else {
         buffer.append("<Attribute not present>");
      }

      return buffer.toString();
   }

   protected void setType(Long type) {
      if (type == null) {
         throw new NullPointerException("Argument \"type\" must not be null.");
      } else {
         this.ckAttribute_.type = type;
      }
   }

   protected Long getType() {
      return new Long(this.ckAttribute_.type);
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Attribute) {
         Attribute other = (Attribute)otherObject;
         equal = this == other || !this.present_ && !other.present_ || this.present_ && other.present_ && this.sensitive_ == other.sensitive_ && this.ckAttribute_.type == other.ckAttribute_.type && (this.ckAttribute_.pValue == other.ckAttribute_.pValue || this.ckAttribute_.pValue != null && this.ckAttribute_.pValue.equals(other.ckAttribute_.pValue));
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.ckAttribute_.type ^ (this.ckAttribute_.pValue != null ? this.ckAttribute_.pValue.hashCode() : 0);
   }
}
