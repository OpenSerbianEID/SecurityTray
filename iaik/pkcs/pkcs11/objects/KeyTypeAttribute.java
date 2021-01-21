package iaik.pkcs.pkcs11.objects;

public class KeyTypeAttribute extends LongAttribute {
   public KeyTypeAttribute() {
      super(Attribute.KEY_TYPE);
   }

   protected String getValueString() {
      String valueString;
      if (this.ckAttribute_ != null && this.ckAttribute_.pValue != null) {
         valueString = Key.getKeyTypeName((Long)this.ckAttribute_.pValue);
      } else {
         valueString = "<NULL_PTR>";
      }

      return valueString;
   }
}
