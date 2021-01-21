package iaik.pkcs.pkcs11.objects;

public class HardwareFeatureTypeAttribute extends LongAttribute {
   public HardwareFeatureTypeAttribute() {
      super(Attribute.HW_FEATURE_TYPE);
   }

   protected String getValueString() {
      String valueString;
      if (this.ckAttribute_ != null && this.ckAttribute_.pValue != null) {
         valueString = HardwareFeature.getHardwareFeatureTypeName((Long)this.ckAttribute_.pValue);
      } else {
         valueString = "<NULL_PTR>";
      }

      return valueString;
   }
}
