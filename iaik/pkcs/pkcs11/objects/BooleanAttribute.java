package iaik.pkcs.pkcs11.objects;

public class BooleanAttribute extends Attribute {
   BooleanAttribute() {
   }

   public BooleanAttribute(Long type) {
      super(type);
   }

   public void setBooleanValue(Boolean value) {
      this.ckAttribute_.pValue = value;
      this.present_ = true;
   }

   public Boolean getBooleanValue() {
      return (Boolean)this.ckAttribute_.pValue;
   }
}
