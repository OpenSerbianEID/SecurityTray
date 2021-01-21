package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.wrapper.CK_AES_CBC_ENCRYPT_DATA_PARAMS;

public class AesCbcEncryptDataParameters extends CbcEncryptDataParameters {
   public AesCbcEncryptDataParameters(byte[] iv, byte[] data) {
      super(16, iv, data);
   }

   public Object getPKCS11ParamsObject() {
      CK_AES_CBC_ENCRYPT_DATA_PARAMS params = new CK_AES_CBC_ENCRYPT_DATA_PARAMS();
      params.iv = this.iv_;
      params.pData = this.data_;
      return params;
   }
}
