package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.Version;
import iaik.pkcs.pkcs11.wrapper.CK_VERSION;

public class VersionParameters extends Version implements Parameters {
   public VersionParameters() {
   }

   public VersionParameters(byte major, byte minor) {
      this.major_ = major;
      this.minor_ = minor;
   }

   public Object getPKCS11ParamsObject() {
      CK_VERSION params = new CK_VERSION();
      params.major = this.major_;
      params.minor = this.minor_;
      return params;
   }

   public void setPKCS11ParamsObject(CK_VERSION input) {
      this.major_ = input.major;
      this.minor_ = input.minor;
   }

   public void setMajor(byte major) {
      this.major_ = major;
   }

   public void setMinor(byte minor) {
      this.minor_ = minor;
   }
}
