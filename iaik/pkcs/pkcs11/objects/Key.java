package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;
import java.util.Hashtable;

public class Key extends Storage {
   protected static Key.VendorDefinedKeyBuilder vendorKeyBuilder_;
   protected static Hashtable keyTypeNames_;
   protected KeyTypeAttribute keyType_;
   protected ByteArrayAttribute id_;
   protected DateAttribute startDate_;
   protected DateAttribute endDate_;
   protected BooleanAttribute derive_;
   protected BooleanAttribute local_;
   protected MechanismAttribute keyGenMechanism_;
   protected MechanismArrayAttribute allowedMechanisms_;

   public Key() {
   }

   protected Key(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
   }

   public static void setVendorDefinedKeyBuilder(Key.VendorDefinedKeyBuilder builder) {
      vendorKeyBuilder_ = builder;
   }

   public static Key.VendorDefinedKeyBuilder getVendorDefinedKeyBuilder() {
      return vendorKeyBuilder_;
   }

   public static String getKeyTypeName(Long keyType) {
      if (keyType == null) {
         throw new NullPointerException("Argument \"keyType\" must not be null.");
      } else {
         String keyTypeName;
         if ((keyType & 2147483648L) != 0L) {
            keyTypeName = "Vendor Defined";
         } else {
            if (keyTypeNames_ == null) {
               Hashtable keyTypeNames = new Hashtable(24);
               keyTypeNames.put(Key.KeyType.RSA, "RSA");
               keyTypeNames.put(Key.KeyType.DSA, "DSA");
               keyTypeNames.put(Key.KeyType.DH, "DH");
               keyTypeNames.put(Key.KeyType.ECDSA, "ECDSA");
               keyTypeNames.put(Key.KeyType.EC, "EC");
               keyTypeNames.put(Key.KeyType.X9_42_DH, "X9_42_DH");
               keyTypeNames.put(Key.KeyType.KEA, "KEA");
               keyTypeNames.put(Key.KeyType.GENERIC_SECRET, "GENERIC_SECRET");
               keyTypeNames.put(Key.KeyType.RC2, "RC2");
               keyTypeNames.put(Key.KeyType.RC4, "RC4");
               keyTypeNames.put(Key.KeyType.DES, "DES");
               keyTypeNames.put(Key.KeyType.DES2, "DES2");
               keyTypeNames.put(Key.KeyType.DES3, "DES3");
               keyTypeNames.put(Key.KeyType.CAST, "CAST");
               keyTypeNames.put(Key.KeyType.CAST3, "CAST3");
               keyTypeNames.put(Key.KeyType.CAST5, "CAST5");
               keyTypeNames.put(Key.KeyType.CAST128, "CAST128");
               keyTypeNames.put(Key.KeyType.RC5, "RC5");
               keyTypeNames.put(Key.KeyType.IDEA, "IDEA");
               keyTypeNames.put(Key.KeyType.SKIPJACK, "SKIPJACK");
               keyTypeNames.put(Key.KeyType.BATON, "BATON");
               keyTypeNames.put(Key.KeyType.JUNIPER, "JUNIPER");
               keyTypeNames.put(Key.KeyType.CDMF, "CDMF");
               keyTypeNames.put(Key.KeyType.AES, "AES");
               keyTypeNames.put(Key.KeyType.BLOWFISH, "BLOWFISH");
               keyTypeNames.put(Key.KeyType.TWOFISH, "TWOFISH");
               keyTypeNames_ = keyTypeNames;
            }

            keyTypeName = (String)keyTypeNames_.get(keyType);
            if (keyTypeName == null) {
               keyTypeName = "<unknown>";
            }
         }

         return keyTypeName;
      }
   }

   protected static void putAttributesInTable(Key object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.KEY_TYPE, object.keyType_);
         object.attributeTable_.put(Attribute.ID, object.id_);
         object.attributeTable_.put(Attribute.START_DATE, object.startDate_);
         object.attributeTable_.put(Attribute.END_DATE, object.endDate_);
         object.attributeTable_.put(Attribute.DERIVE, object.derive_);
         object.attributeTable_.put(Attribute.LOCAL, object.local_);
         object.attributeTable_.put(Attribute.KEY_GEN_MECHANISM, object.keyGenMechanism_);
         object.attributeTable_.put(Attribute.ALLOWED_MECHANISMS, object.allowedMechanisms_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.keyType_ = new KeyTypeAttribute();
      this.id_ = new ByteArrayAttribute(Attribute.ID);
      this.startDate_ = new DateAttribute(Attribute.START_DATE);
      this.endDate_ = new DateAttribute(Attribute.END_DATE);
      this.derive_ = new BooleanAttribute(Attribute.DERIVE);
      this.local_ = new BooleanAttribute(Attribute.LOCAL);
      this.keyGenMechanism_ = new MechanismAttribute(Attribute.KEY_GEN_MECHANISM);
      this.allowedMechanisms_ = new MechanismArrayAttribute(Attribute.ALLOWED_MECHANISMS);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      Key clone = (Key)super.clone();
      clone.keyType_ = (KeyTypeAttribute)this.keyType_.clone();
      clone.id_ = (ByteArrayAttribute)this.id_.clone();
      clone.startDate_ = (DateAttribute)this.startDate_.clone();
      clone.endDate_ = (DateAttribute)this.endDate_.clone();
      clone.derive_ = (BooleanAttribute)this.derive_.clone();
      clone.local_ = (BooleanAttribute)this.local_.clone();
      clone.keyGenMechanism_ = (MechanismAttribute)this.keyGenMechanism_.clone();
      clone.allowedMechanisms_ = (MechanismArrayAttribute)this.allowedMechanisms_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Key) {
         Key other = (Key)otherObject;
         equal = this == other || super.equals(other) && this.keyType_.equals(other.keyType_) && this.id_.equals(other.id_) && this.startDate_.equals(other.startDate_) && this.endDate_.equals(other.endDate_) && this.derive_.equals(other.derive_) && this.local_.equals(other.local_) && this.keyGenMechanism_.equals(other.keyGenMechanism_) && this.allowedMechanisms_.equals(other.allowedMechanisms_);
      }

      return equal;
   }

   public LongAttribute getKeyType() {
      return this.keyType_;
   }

   public ByteArrayAttribute getId() {
      return this.id_;
   }

   public DateAttribute getStartDate() {
      return this.startDate_;
   }

   public DateAttribute getEndDate() {
      return this.endDate_;
   }

   public BooleanAttribute getDerive() {
      return this.derive_;
   }

   public BooleanAttribute getLocal() {
      return this.local_;
   }

   public MechanismAttribute getKeyGenMechanism() {
      return this.keyGenMechanism_;
   }

   public MechanismArrayAttribute getAllowedMechanisms() {
      return this.allowedMechanisms_;
   }

   public int hashCode() {
      return this.keyType_.hashCode() ^ this.id_.hashCode();
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.id_, this.startDate_, this.endDate_, this.derive_, this.local_, this.keyGenMechanism_});
      Object.getAttributeValue(session, this.objectHandle_, this.allowedMechanisms_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(256);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Key Type: ");
      if (this.keyType_ != null) {
         buffer.append(this.keyType_.toString());
      } else {
         buffer.append("<unavailable>");
      }

      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("ID: ");
      buffer.append(this.id_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Start Date: ");
      buffer.append(this.startDate_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("End Date: ");
      buffer.append(this.endDate_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Derive: ");
      buffer.append(this.derive_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Local: ");
      buffer.append(this.local_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Key Generation Mechanism: ");
      buffer.append(this.keyGenMechanism_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Allowed Mechanisms: ");
      buffer.append(this.allowedMechanisms_.toString());
      return buffer.toString();
   }

   public interface VendorDefinedKeyBuilder {
      Object build(Session var1, long var2) throws PKCS11Exception;
   }

   public interface KeyType {
      Long RSA = new Long(0L);
      Long DSA = new Long(1L);
      Long DH = new Long(2L);
      Long ECDSA = new Long(3L);
      Long EC = new Long(3L);
      Long X9_42_DH = new Long(4L);
      Long KEA = new Long(5L);
      Long GENERIC_SECRET = new Long(16L);
      Long RC2 = new Long(17L);
      Long RC4 = new Long(18L);
      Long DES = new Long(19L);
      Long DES2 = new Long(20L);
      Long DES3 = new Long(21L);
      Long CAST = new Long(22L);
      Long CAST3 = new Long(23L);
      Long CAST5 = new Long(24L);
      Long CAST128 = new Long(24L);
      Long RC5 = new Long(25L);
      Long IDEA = new Long(26L);
      Long SKIPJACK = new Long(27L);
      Long BATON = new Long(28L);
      Long JUNIPER = new Long(29L);
      Long CDMF = new Long(30L);
      Long AES = new Long(31L);
      Long BLOWFISH = new Long(32L);
      Long TWOFISH = new Long(33L);
      Long VENDOR_DEFINED = new Long(2147483648L);
   }
}
