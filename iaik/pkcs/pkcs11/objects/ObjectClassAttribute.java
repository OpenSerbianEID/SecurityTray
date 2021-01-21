package iaik.pkcs.pkcs11.objects;

public class ObjectClassAttribute extends LongAttribute {
   public ObjectClassAttribute() {
      super(Attribute.CLASS);
   }

   protected String getValueString() {
      String valueString;
      if (this.ckAttribute_ != null && this.ckAttribute_.pValue != null) {
         valueString = Object.getObjectClassName((Long)this.ckAttribute_.pValue);
      } else {
         valueString = "<NULL_PTR>";
      }

      return valueString;
   }
}
