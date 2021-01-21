package iaik.pkcs.pkcs11.objects;

public class LongAttribute extends Attribute {
   LongAttribute() {
   }

   public LongAttribute(Long type) {
      super(type);
   }

   public void setLongValue(Long value) {
      this.ckAttribute_.pValue = value;
      this.present_ = true;
   }

   public Long getLongValue() {
      return (Long)this.ckAttribute_.pValue;
   }

   protected String getValueString(int radix) {
      String valueString;
      if (this.ckAttribute_ != null && this.ckAttribute_.pValue != null) {
         valueString = Long.toString((Long)this.ckAttribute_.pValue, radix);
      } else {
         valueString = "<NULL_PTR>";
      }

      return valueString;
   }

   public String toString(int radix) {
      StringBuffer buffer = new StringBuffer(32);
      if (this.present_) {
         if (this.sensitive_) {
            buffer.append("<Value is sensitive>");
         } else {
            buffer.append(this.getValueString(radix));
         }
      } else {
         buffer.append("<Attribute not present>");
      }

      return buffer.toString();
   }
}
