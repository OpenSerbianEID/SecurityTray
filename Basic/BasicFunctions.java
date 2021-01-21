package Basic;

import Utils.Base64Utils;
import java.security.MessageDigest;

public class BasicFunctions {
   public static String getHash(String pass) throws Exception {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      byte[] passHash = md.digest(pass.getBytes());
      return Base64Utils.base64Encode(passHash);
   }

   public static String getHash(byte[] pass) throws Exception {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      byte[] passHash = md.digest(pass);
      return Base64Utils.base64Encode(passHash);
   }
}
