package iaik.pkcs.pkcs11.parameters;

import iaik.pkcs.pkcs11.wrapper.CK_DES_CBC_ENCRYPT_DATA_PARAMS;

public class DesCbcEncryptDataParameters extends CbcEncryptDataParameters {
   public DesCbcEncryptDataParameters(byte[] iv, byte[] data) {
      super(8, iv, data);
   }

   public Object getPKCS11ParamsObject() {
      CK_DES_CBC_ENCRYPT_DATA_PARAMS params = new CK_DES_CBC_ENCRYPT_DATA_PARAMS();
      params.iv = this.iv_;
      params.pData = this.data_;
      return params;
   }
}
