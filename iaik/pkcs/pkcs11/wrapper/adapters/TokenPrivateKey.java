package iaik.pkcs.pkcs11.wrapper.adapters;

import java.security.PrivateKey;

public class TokenPrivateKey implements PrivateKey {
   private static final long serialVersionUID = 6595009029121137246L;
   protected iaik.pkcs.pkcs11.objects.PrivateKey tokenPrivateKey_;

   public TokenPrivateKey(iaik.pkcs.pkcs11.objects.PrivateKey tokenPrivateKey) {
      this.tokenPrivateKey_ = tokenPrivateKey;
   }

   public String getAlgorithm() {
      return null;
   }

   public String getFormat() {
      return null;
   }

   public byte[] getEncoded() {
      return null;
   }

   public iaik.pkcs.pkcs11.objects.PrivateKey getTokenPrivateKey() {
      return this.tokenPrivateKey_;
   }
}
