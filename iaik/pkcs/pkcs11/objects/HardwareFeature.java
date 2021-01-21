package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

public class HardwareFeature extends Object {
   protected static HardwareFeature.VendorDefinedHardwareFeatureBuilder vendorHardwareFeatureBuilder_;
   protected HardwareFeatureTypeAttribute hardwareFeatureType_;

   public HardwareFeature() {
      this.objectClass_.setLongValue(Object.ObjectClass.HW_FEATURE);
   }

   protected HardwareFeature(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.objectClass_.setLongValue(Object.ObjectClass.HW_FEATURE);
   }

   public static String getHardwareFeatureTypeName(Long hardwareFeatureType) {
      if (hardwareFeatureType == null) {
         throw new NullPointerException("Argument \"hardwareFeatureType\" must not be null.");
      } else {
         String hardwareFeatureTypeName;
         if (hardwareFeatureType.equals(HardwareFeature.FeatureType.MONOTONIC_COUNTER)) {
            hardwareFeatureTypeName = "Monotonic Counter";
         } else if (hardwareFeatureType.equals(HardwareFeature.FeatureType.CLOCK)) {
            hardwareFeatureTypeName = "Clock";
         } else if (hardwareFeatureType.equals(HardwareFeature.FeatureType.USER_INTERFACE)) {
            hardwareFeatureTypeName = "User Interface";
         } else if ((hardwareFeatureType & HardwareFeature.FeatureType.VENDOR_DEFINED) != 0L) {
            hardwareFeatureTypeName = "Vendor Defined";
         } else {
            hardwareFeatureTypeName = "<unknown>";
         }

         return hardwareFeatureTypeName;
      }
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         HardwareFeatureTypeAttribute hardwareFeatureTypeAttribute = new HardwareFeatureTypeAttribute();
         getAttributeValue(session, objectHandle, hardwareFeatureTypeAttribute);
         Long hardwareFeatureType = hardwareFeatureTypeAttribute.getLongValue();
         Object newObject;
         if (hardwareFeatureTypeAttribute.isPresent() && hardwareFeatureType != null) {
            if (hardwareFeatureType.equals(HardwareFeature.FeatureType.MONOTONIC_COUNTER)) {
               newObject = MonotonicCounter.getInstance(session, objectHandle);
            } else if (hardwareFeatureType.equals(HardwareFeature.FeatureType.CLOCK)) {
               newObject = Clock.getInstance(session, objectHandle);
            } else if (hardwareFeatureType.equals(HardwareFeature.FeatureType.USER_INTERFACE)) {
               newObject = getUnknownHardwareFeature(session, objectHandle);
            } else if ((hardwareFeatureType & HardwareFeature.FeatureType.VENDOR_DEFINED) != 0L) {
               newObject = getUnknownHardwareFeature(session, objectHandle);
            } else {
               newObject = getUnknownHardwareFeature(session, objectHandle);
            }
         } else {
            newObject = getUnknownHardwareFeature(session, objectHandle);
         }

         return newObject;
      }
   }

   protected static Object getUnknownHardwareFeature(Session session, long objectHandle) throws TokenException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else {
         java.lang.Object newObject;
         if (vendorHardwareFeatureBuilder_ != null) {
            try {
               newObject = vendorHardwareFeatureBuilder_.build(session, objectHandle);
            } catch (PKCS11Exception var5) {
               newObject = new HardwareFeature(session, objectHandle);
            }
         } else {
            newObject = new HardwareFeature(session, objectHandle);
         }

         return (Object)newObject;
      }
   }

   public static void setVendorDefinedHardwareFeatureBuilder(HardwareFeature.VendorDefinedHardwareFeatureBuilder builder) {
      vendorHardwareFeatureBuilder_ = builder;
   }

   public static HardwareFeature.VendorDefinedHardwareFeatureBuilder getVendorDefinedHardwareFeatureBuilder() {
      return vendorHardwareFeatureBuilder_;
   }

   protected static void putAttributesInTable(HardwareFeature object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.HW_FEATURE_TYPE, object.hardwareFeatureType_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.hardwareFeatureType_ = new HardwareFeatureTypeAttribute();
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      HardwareFeature clone = (HardwareFeature)super.clone();
      clone.hardwareFeatureType_ = (HardwareFeatureTypeAttribute)this.hardwareFeatureType_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof HardwareFeature) {
         HardwareFeature other = (HardwareFeature)otherObject;
         equal = this == other || super.equals(other) && this.hardwareFeatureType_.equals(other.hardwareFeatureType_);
      }

      return equal;
   }

   public LongAttribute getHardwareFeatureType() {
      return this.hardwareFeatureType_;
   }

   public int hashCode() {
      return this.hardwareFeatureType_.hashCode();
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(128);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Hardware Feature Type: ");
      if (this.hardwareFeatureType_ != null) {
         buffer.append(this.hardwareFeatureType_.toString());
      } else {
         buffer.append("<unavailable>");
      }

      return buffer.toString();
   }

   public interface VendorDefinedHardwareFeatureBuilder {
      Object build(Session var1, long var2) throws PKCS11Exception;
   }

   public interface FeatureType {
      Long MONOTONIC_COUNTER = new Long(1L);
      Long CLOCK = new Long(2L);
      Long USER_INTERFACE = new Long(3L);
      Long VENDOR_DEFINED = new Long(2147483648L);
   }
}
