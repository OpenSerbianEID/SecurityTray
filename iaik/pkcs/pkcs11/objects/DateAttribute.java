package iaik.pkcs.pkcs11.objects;

import iaik.pkcs.pkcs11.Util;
import iaik.pkcs.pkcs11.wrapper.CK_DATE;
import iaik.pkcs.pkcs11.wrapper.Functions;
import java.util.Date;

public class DateAttribute extends Attribute {
   DateAttribute() {
   }

   public DateAttribute(Long type) {
      super(type);
   }

   public void setDateValue(Date value) {
      this.ckAttribute_.pValue = Util.convertToCkDate(value);
      this.present_ = true;
   }

   public Date getDateValue() {
      return Util.convertToDate((CK_DATE)this.ckAttribute_.pValue);
   }

   public boolean equals(java.lang.Object otherObject) {
      boolean equal = false;
      if (otherObject instanceof DateAttribute) {
         DateAttribute other = (DateAttribute)otherObject;
         equal = this == other || !this.present_ && !other.present_ || this.present_ && other.present_ && this.sensitive_ == other.sensitive_ && this.ckAttribute_.type == other.ckAttribute_.type && Functions.equals((CK_DATE)this.ckAttribute_.pValue, (CK_DATE)other.ckAttribute_.pValue);
      }

      return equal;
   }

   public int hashCode() {
      return (int)this.ckAttribute_.type ^ (this.ckAttribute_.pValue != null ? Functions.hashCode((CK_DATE)this.ckAttribute_.pValue) : 0);
   }
}
