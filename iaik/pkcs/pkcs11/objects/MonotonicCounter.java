package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.wrapper.Constants;

public class MonotonicCounter extends HardwareFeature {
   protected BooleanAttribute resetOnInit_;
   protected BooleanAttribute hasReset_;
   protected ByteArrayAttribute value_;

   public MonotonicCounter() {
      this.hardwareFeatureType_.setLongValue(HardwareFeature.FeatureType.MONOTONIC_COUNTER);
   }

   protected MonotonicCounter(Session session, long objectHandle) throws TokenException {
      super(session, objectHandle);
      this.hardwareFeatureType_.setLongValue(HardwareFeature.FeatureType.MONOTONIC_COUNTER);
   }

   public static Object getInstance(Session session, long objectHandle) throws TokenException {
      return new MonotonicCounter(session, objectHandle);
   }

   protected static void putAttributesInTable(MonotonicCounter object) {
      if (object == null) {
         throw new NullPointerException("Argument \"object\" must not be null.");
      } else {
         object.attributeTable_.put(Attribute.RESET_ON_INIT, object.resetOnInit_);
         object.attributeTable_.put(Attribute.HAS_RESET, object.hasReset_);
         object.attributeTable_.put(Attribute.VALUE, object.value_);
      }
   }

   protected void allocateAttributes() {
      super.allocateAttributes();
      this.resetOnInit_ = new BooleanAttribute(Attribute.RESET_ON_INIT);
      this.hasReset_ = new BooleanAttribute(Attribute.HAS_RESET);
      this.value_ = new ByteArrayAttribute(Attribute.VALUE);
      putAttributesInTable(this);
   }

   public java.lang.Object clone() {
      MonotonicCounter clone = (MonotonicCounter)super.clone();
      clone.resetOnInit_ = (BooleanAttribute)this.resetOnInit_.clone();
      clone.hasReset_ = (BooleanAttribute)this.hasReset_.clone();
      clone.value_ = (ByteArrayAttribute)this.value_.clone();
      putAttributesInTable(clone);
      return clone;
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof MonotonicCounter) {
         MonotonicCounter other = (MonotonicCounter)otherObject;
         equal = this == other || super.equals(other) && this.resetOnInit_.equals(other.resetOnInit_) && this.hasReset_.equals(other.hasReset_) && this.value_.equals(other.value_);
      }

      return equal;
   }

   public BooleanAttribute getHasReset() {
      return this.hasReset_;
   }

   public BooleanAttribute isResetOnInit() {
      return this.resetOnInit_;
   }

   public ByteArrayAttribute getValue() {
      return this.value_;
   }

   public int hashCode() {
      return this.resetOnInit_.hashCode() ^ this.hasReset_.hashCode() ^ this.value_.hashCode();
   }

   public void readAttributes(Session session) throws TokenException {
      super.readAttributes(session);
      Object.getAttributeValues(session, this.objectHandle_, new Attribute[]{this.resetOnInit_, this.hasReset_, this.value_});
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(256);
      buffer.append(super.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Reset on Initialization: ");
      buffer.append(this.resetOnInit_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Has been reset: ");
      buffer.append(this.hasReset_.toString());
      buffer.append(Constants.NEWLINE);
      buffer.append("  ");
      buffer.append("Value (hex): ");
      buffer.append(this.value_.toString());
      return buffer.toString();
   }
}
