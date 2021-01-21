package iaik.pkcs.pkcs11.wrapper.adapters;

import iaik.pkcs.pkcs11.objects.Key;
import iaik.pkcs.pkcs11.objects.X509PublicKeyCertificate;

public class KeyAndCertificate {
   protected Key key_;
   protected X509PublicKeyCertificate certificate_;

   public KeyAndCertificate(Key key, X509PublicKeyCertificate certificate) {
      this.key_ = key;
      this.certificate_ = certificate;
   }

   public X509PublicKeyCertificate getCertificate() {
      return this.certificate_;
   }

   public Key getKey() {
      return this.key_;
   }

   public void setCertificate(X509PublicKeyCertificate certificate) {
      this.certificate_ = certificate;
   }

   public void setKey(Key key) {
      this.key_ = key;
   }
}
