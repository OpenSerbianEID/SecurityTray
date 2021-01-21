package iaik.pkcs.pkcs11.wrapper;

import java.io.IOException;

public class PKCS11Connector {
   protected PKCS11Connector() {
   }

   public static PKCS11 connectToPKCS11Module(String pkcs11ModulePath) throws IOException {
      return new PKCS11Implementation(pkcs11ModulePath);
   }

   public static PKCS11 connectToPKCS11Module(String pkcs11ModulePath, String pkcs11WrapperPath) throws IOException {
      return new PKCS11Implementation(pkcs11ModulePath, pkcs11WrapperPath);
   }
}
