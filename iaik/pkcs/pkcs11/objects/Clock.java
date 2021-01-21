package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;
import java.io.UnsupportedEncodingException;

public class Clock extends HardwareFeature {
   protected ByteArrayAttribute value_;

   public Clock() {
      this.hardwareFeatureType_.setLongValue(HardwareFeature.FeatureType.CLOCK);
   }

   protected Clock(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.hardwareFeatureType_.setLongValue(HardwareFeature.FeatureType.CLOCK);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new Clock(session, objectHandle);
   }

   protected static void putAttributesInTable(Clock object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.VALUE, object.value_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.value_ = new ByteArrayAttribute(Attribute.VALUE);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      Clock clone = (Clock)super.clone();
      clone.value_ = (ByteArrayAttribute)this.value_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof Clock) {
         Clock other = (Clock)otherObject;
         equal = this == other || super.equals(other) && this.value_.equals(other.value_);
      }

      return equal;
   }

   public ByteArrayAttribute getValue() {
      return this.value_;
   }

   public int hashCode() {
      return this.value_.hashCode();
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValue(session, this.objectHandle_, this.value_);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(256);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Value: ");

      try {
         buffer.append(new String(this.value_.getByteArrayValue(), "ASCII"));
      } catch (UnsupportedEncodingException var3) {
         buffer.append(new String(this.value_.getByteArrayValue()));
      }

      return buffer.toString();
   }
}
