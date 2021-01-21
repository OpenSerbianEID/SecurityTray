package iaik.pkcs.pkcs11.objects;

public class CertificateTypeAttribute extends LongAttribute {
   public CertificateTypeAttribute() {
      super(Attribute.CERTIFICATE_TYPE);
   }

   protected String getValueString() {
      String valueString;
      if (this.ckAttribute_ != null && this.ckAttribute_.pValue != null) {
         valueString = Certificate.getCertificateTypeName((Long)this.ckAttribute_.pValue);
      } else {
         valueString = "<NULL_PTR>";
      }

      return valueString;
   }
}
