package iaik.pkcs.pkcs11.wrapper.util;

import iaik.pkcs.pkcs11.Module;
import iaik.pkcs.pkcs11.Notify;
import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.Slot;
import iaik.pkcs.pkcs11.Token;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.TokenInfo;
import iaik.pkcs.pkcs11.objects.Key;
import iaik.pkcs.pkcs11.objects.PrivateKey;
import iaik.pkcs.pkcs11.objects.RSAPrivateKey;
import iaik.pkcs.pkcs11.objects.X509PublicKeyCertificate;
import iaik.pkcs.pkcs11.wrapper.adapters.KeyAndCertificate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Util {
   protected static Hashtable mechansimCodes_;

   public static Long mechanismCodeToString(String mechansimName) {
      if (mechansimName == null) {
         throw new NullPointerException("Argument \"mechansimName\" must not be null.");
      } else {
         Long mechanismCode;
         if (mechansimName.startsWith("0x")) {
            mechanismCode = new Long(Long.parseLong(mechansimName, 16));
         } else {
            if (mechansimCodes_ == null) {
               mechansimCodes_ = new Hashtable(160);
               mechansimCodes_.put("CKM_RSA_PKCS_KEY_PAIR_GEN", new Long(0L));
               mechansimCodes_.put("CKM_RSA_PKCS", new Long(1L));
               mechansimCodes_.put("CKM_RSA_9796", new Long(2L));
               mechansimCodes_.put("CKM_RSA_X_509", new Long(3L));
               mechansimCodes_.put("CKM_MD2_RSA_PKCS", new Long(4L));
               mechansimCodes_.put("CKM_MD5_RSA_PKCS", new Long(5L));
               mechansimCodes_.put("CKM_SHA1_RSA_PKCS", new Long(6L));
               mechansimCodes_.put("CKM_RIPEMD128_RSA_PKCS", new Long(7L));
               mechansimCodes_.put("CKM_RIPEMD160_RSA_PKCS", new Long(8L));
               mechansimCodes_.put("CKM_RSA_PKCS_OAEP", new Long(9L));
               mechansimCodes_.put("CKM_DSA_KEY_PAIR_GEN", new Long(16L));
               mechansimCodes_.put("CKM_DSA", new Long(17L));
               mechansimCodes_.put("CKM_DSA_SHA1", new Long(18L));
               mechansimCodes_.put("CKM_DH_PKCS_KEY_PAIR_GEN", new Long(32L));
               mechansimCodes_.put("CKM_DH_PKCS_DERIVE", new Long(33L));
               mechansimCodes_.put("CKM_RC2_KEY_GEN", new Long(256L));
               mechansimCodes_.put("CKM_RC2_ECB", new Long(257L));
               mechansimCodes_.put("CKM_RC2_CBC", new Long(258L));
               mechansimCodes_.put("CKM_RC2_MAC", new Long(259L));
               mechansimCodes_.put("CKM_RC2_MAC_GENERAL", new Long(260L));
               mechansimCodes_.put("CKM_RC2_CBC_PAD", new Long(261L));
               mechansimCodes_.put("CKM_RC4_KEY_GEN", new Long(272L));
               mechansimCodes_.put("CKM_RC4", new Long(273L));
               mechansimCodes_.put("CKM_DES_KEY_GEN", new Long(288L));
               mechansimCodes_.put("CKM_DES_ECB", new Long(289L));
               mechansimCodes_.put("CKM_DES_CBC", new Long(290L));
               mechansimCodes_.put("CKM_DES_MAC", new Long(291L));
               mechansimCodes_.put("CKM_DES_MAC_GENERAL", new Long(292L));
               mechansimCodes_.put("CKM_DES_CBC_PAD", new Long(293L));
               mechansimCodes_.put("CKM_DES2_KEY_GEN", new Long(304L));
               mechansimCodes_.put("CKM_DES3_KEY_GEN", new Long(305L));
               mechansimCodes_.put("CKM_DES3_ECB", new Long(306L));
               mechansimCodes_.put("CKM_DES3_CBC", new Long(307L));
               mechansimCodes_.put("CKM_DES3_MAC", new Long(308L));
               mechansimCodes_.put("CKM_DES3_MAC_GENERAL", new Long(309L));
               mechansimCodes_.put("CKM_DES3_CBC_PAD", new Long(310L));
               mechansimCodes_.put("CKM_CDMF_KEY_GEN", new Long(320L));
               mechansimCodes_.put("CKM_CDMF_ECB", new Long(321L));
               mechansimCodes_.put("CKM_CDMF_CBC", new Long(322L));
               mechansimCodes_.put("CKM_CDMF_MAC", new Long(323L));
               mechansimCodes_.put("CKM_CDMF_MAC_GENERAL", new Long(324L));
               mechansimCodes_.put("CKM_CDMF_CBC_PAD", new Long(325L));
               mechansimCodes_.put("CKM_MD2", new Long(512L));
               mechansimCodes_.put("CKM_MD2_HMAC", new Long(513L));
               mechansimCodes_.put("CKM_MD2_HMAC_GENERAL", new Long(514L));
               mechansimCodes_.put("CKM_MD5", new Long(528L));
               mechansimCodes_.put("CKM_MD5_HMAC", new Long(529L));
               mechansimCodes_.put("CKM_MD5_HMAC_GENERAL", new Long(530L));
               mechansimCodes_.put("CKM_SHA_1", new Long(544L));
               mechansimCodes_.put("CKM_SHA_1_HMAC", new Long(545L));
               mechansimCodes_.put("CKM_SHA_1_HMAC_GENERAL", new Long(546L));
               mechansimCodes_.put("CKM_RIPEMD128", new Long(560L));
               mechansimCodes_.put("CKM_RIPEMD128_HMAC", new Long(561L));
               mechansimCodes_.put("CKM_RIPEMD128_HMAC_GENERAL", new Long(562L));
               mechansimCodes_.put("CKM_RIPEMD160", new Long(576L));
               mechansimCodes_.put("CKM_RIPEMD160_HMAC", new Long(577L));
               mechansimCodes_.put("CKM_RIPEMD160_HMAC_GENERAL", new Long(578L));
               mechansimCodes_.put("CKM_CAST_KEY_GEN", new Long(768L));
               mechansimCodes_.put("CKM_CAST_ECB", new Long(769L));
               mechansimCodes_.put("CKM_CAST_CBC", new Long(770L));
               mechansimCodes_.put("CKM_CAST_MAC", new Long(771L));
               mechansimCodes_.put("CKM_CAST_MAC_GENERAL", new Long(772L));
               mechansimCodes_.put("CKM_CAST_CBC_PAD", new Long(773L));
               mechansimCodes_.put("CKM_CAST3_KEY_GEN", new Long(784L));
               mechansimCodes_.put("CKM_CAST3_ECB", new Long(785L));
               mechansimCodes_.put("CKM_CAST3_CBC", new Long(786L));
               mechansimCodes_.put("CKM_CAST3_MAC", new Long(787L));
               mechansimCodes_.put("CKM_CAST3_MAC_GENERAL", new Long(788L));
               mechansimCodes_.put("CKM_CAST3_CBC_PAD", new Long(789L));
               mechansimCodes_.put("CKM_CAST5_KEY_GEN", new Long(800L));
               mechansimCodes_.put("CKM_CAST128_KEY_GEN", new Long(800L));
               mechansimCodes_.put("CKM_CAST5_ECB", new Long(801L));
               mechansimCodes_.put("CKM_CAST128_ECB", new Long(801L));
               mechansimCodes_.put("CKM_CAST5_CBC", new Long(802L));
               mechansimCodes_.put("CKM_CAST128_CBC", new Long(802L));
               mechansimCodes_.put("CKM_CAST5_MAC", new Long(803L));
               mechansimCodes_.put("CKM_CAST128_MAC", new Long(803L));
               mechansimCodes_.put("CKM_CAST5_MAC_GENERAL", new Long(804L));
               mechansimCodes_.put("CKM_CAST128_MAC_GENERAL", new Long(804L));
               mechansimCodes_.put("CKM_CAST5_CBC_PAD", new Long(805L));
               mechansimCodes_.put("CKM_CAST128_CBC_PAD", new Long(805L));
               mechansimCodes_.put("CKM_RC5_KEY_GEN", new Long(816L));
               mechansimCodes_.put("CKM_RC5_ECB", new Long(817L));
               mechansimCodes_.put("CKM_RC5_CBC", new Long(818L));
               mechansimCodes_.put("CKM_RC5_MAC", new Long(819L));
               mechansimCodes_.put("CKM_RC5_MAC_GENERAL", new Long(820L));
               mechansimCodes_.put("CKM_RC5_CBC_PAD", new Long(821L));
               mechansimCodes_.put("CKM_IDEA_KEY_GEN", new Long(832L));
               mechansimCodes_.put("CKM_IDEA_ECB", new Long(833L));
               mechansimCodes_.put("CKM_IDEA_CBC", new Long(834L));
               mechansimCodes_.put("CKM_IDEA_MAC", new Long(835L));
               mechansimCodes_.put("CKM_IDEA_MAC_GENERAL", new Long(836L));
               mechansimCodes_.put("CKM_IDEA_CBC_PAD", new Long(837L));
               mechansimCodes_.put("CKM_GENERIC_SECRET_KEY_GEN", new Long(848L));
               mechansimCodes_.put("CKM_CONCATENATE_BASE_AND_KEY", new Long(864L));
               mechansimCodes_.put("CKM_CONCATENATE_BASE_AND_DATA", new Long(866L));
               mechansimCodes_.put("CKM_CONCATENATE_DATA_AND_BASE", new Long(867L));
               mechansimCodes_.put("CKM_XOR_BASE_AND_DATA", new Long(868L));
               mechansimCodes_.put("CKM_EXTRACT_KEY_FROM_KEY", new Long(869L));
               mechansimCodes_.put("CKM_SSL3_PRE_MASTER_KEY_GEN", new Long(880L));
               mechansimCodes_.put("CKM_SSL3_MASTER_KEY_DERIVE", new Long(881L));
               mechansimCodes_.put("CKM_SSL3_KEY_AND_MAC_DERIVE", new Long(882L));
               mechansimCodes_.put("CKM_SSL3_MD5_MAC", new Long(896L));
               mechansimCodes_.put("CKM_SSL3_SHA1_MAC", new Long(897L));
               mechansimCodes_.put("CKM_MD5_KEY_DERIVATION", new Long(912L));
               mechansimCodes_.put("CKM_MD2_KEY_DERIVATION", new Long(913L));
               mechansimCodes_.put("CKM_SHA1_KEY_DERIVATION", new Long(914L));
               mechansimCodes_.put("CKM_PBE_MD2_DES_CBC", new Long(928L));
               mechansimCodes_.put("CKM_PBE_MD5_DES_CBC", new Long(929L));
               mechansimCodes_.put("CKM_PBE_MD5_CAST_CBC", new Long(930L));
               mechansimCodes_.put("CKM_PBE_MD5_CAST3_CBC", new Long(931L));
               mechansimCodes_.put("CKM_PBE_MD5_CAST5_CBC", new Long(932L));
               mechansimCodes_.put("CKM_PBE_MD5_CAST128_CBC", new Long(932L));
               mechansimCodes_.put("CKM_PBE_SHA1_CAST5_CBC", new Long(933L));
               mechansimCodes_.put("CKM_PBE_SHA1_CAST128_CBC", new Long(933L));
               mechansimCodes_.put("CKM_PBE_SHA1_RC4_128", new Long(934L));
               mechansimCodes_.put("CKM_PBE_SHA1_RC4_40", new Long(935L));
               mechansimCodes_.put("CKM_PBE_SHA1_DES3_EDE_CBC", new Long(936L));
               mechansimCodes_.put("CKM_PBE_SHA1_DES2_EDE_CBC", new Long(937L));
               mechansimCodes_.put("CKM_PBE_SHA1_RC2_128_CBC", new Long(938L));
               mechansimCodes_.put("CKM_PBE_SHA1_RC2_40_CBC", new Long(939L));
               mechansimCodes_.put("CKM_PKCS5_PBKD2", new Long(944L));
               mechansimCodes_.put("CKM_PBA_SHA1_WITH_SHA1_HMAC", new Long(960L));
               mechansimCodes_.put("CKM_KEY_WRAP_LYNKS", new Long(1024L));
               mechansimCodes_.put("CKM_KEY_WRAP_SET_OAEP", new Long(1025L));
               mechansimCodes_.put("CKM_SKIPJACK_KEY_GEN", new Long(4096L));
               mechansimCodes_.put("CKM_SKIPJACK_ECB64", new Long(4097L));
               mechansimCodes_.put("CKM_SKIPJACK_CBC64", new Long(4098L));
               mechansimCodes_.put("CKM_SKIPJACK_OFB64", new Long(4099L));
               mechansimCodes_.put("CKM_SKIPJACK_CFB64", new Long(4100L));
               mechansimCodes_.put("CKM_SKIPJACK_CFB32", new Long(4101L));
               mechansimCodes_.put("CKM_SKIPJACK_CFB16", new Long(4102L));
               mechansimCodes_.put("CKM_SKIPJACK_CFB8", new Long(4103L));
               mechansimCodes_.put("CKM_SKIPJACK_WRAP", new Long(4104L));
               mechansimCodes_.put("CKM_SKIPJACK_PRIVATE_WRAP", new Long(4105L));
               mechansimCodes_.put("CKM_SKIPJACK_RELAYX", new Long(4106L));
               mechansimCodes_.put("CKM_KEA_KEY_PAIR_GEN", new Long(4112L));
               mechansimCodes_.put("CKM_KEA_KEY_DERIVE", new Long(4113L));
               mechansimCodes_.put("CKM_FORTEZZA_TIMESTAMP", new Long(4128L));
               mechansimCodes_.put("CKM_BATON_KEY_GEN", new Long(4144L));
               mechansimCodes_.put("CKM_BATON_ECB128", new Long(4145L));
               mechansimCodes_.put("CKM_BATON_ECB96", new Long(4146L));
               mechansimCodes_.put("CKM_BATON_CBC128", new Long(4147L));
               mechansimCodes_.put("CKM_BATON_COUNTER", new Long(4148L));
               mechansimCodes_.put("CKM_BATON_SHUFFLE", new Long(4149L));
               mechansimCodes_.put("CKM_BATON_WRAP", new Long(4150L));
               mechansimCodes_.put("CKM_ECDSA_KEY_PAIR_GEN", new Long(4160L));
               mechansimCodes_.put("CKM_ECDSA", new Long(4161L));
               mechansimCodes_.put("CKM_ECDSA_SHA1", new Long(4162L));
               mechansimCodes_.put("CKM_JUNIPER_KEY_GEN", new Long(4192L));
               mechansimCodes_.put("CKM_JUNIPER_ECB128", new Long(4193L));
               mechansimCodes_.put("CKM_JUNIPER_CBC128", new Long(4194L));
               mechansimCodes_.put("CKM_JUNIPER_COUNTER", new Long(4195L));
               mechansimCodes_.put("CKM_JUNIPER_SHUFFLE", new Long(4196L));
               mechansimCodes_.put("CKM_JUNIPER_WRAP", new Long(4197L));
               mechansimCodes_.put("CKM_FASTHASH", new Long(4208L));
               mechansimCodes_.put("CKM_VENDOR_DEFINED", new Long(-2147483648L));
            }

            mechanismCode = (Long)mechansimCodes_.get(mechansimName);
         }

         return mechanismCode;
      }
   }

   public static Token selectToken(Module pkcs11Module, PrintWriter output, BufferedReader input) throws TokenException, IOException {
      return selectToken(pkcs11Module, output, input, (String)null);
   }

   public static Token selectToken(Module pkcs11Module, PrintWriter output, BufferedReader input, String slot) throws TokenException, IOException {
      if (pkcs11Module == null) {
         throw new NullPointerException("Argument \"pkcs11Module\" must not be null.");
      } else if (output == null) {
         throw new NullPointerException("Argument \"output\" must not be null.");
      } else if (input == null) {
         throw new NullPointerException("Argument \"input\" must not be null.");
      } else {
         output.println("################################################################################");
         output.println("getting list of all tokens");
         Slot[] slotsWithToken = pkcs11Module.getSlotList(true);
         Token[] tokens = new Token[slotsWithToken.length];
         Hashtable tokenIDtoToken = new Hashtable(tokens.length);

         TokenInfo selectedTokenID;
         for(int i = 0; i < slotsWithToken.length; ++i) {
            output.println("________________________________________________________________________________");
            tokens[i] = slotsWithToken[i].getToken();
            selectedTokenID = tokens[i].getTokenInfo();
            long tokenID = tokens[i].getTokenID();
            tokenIDtoToken.put(new Long(tokenID), tokens[i]);
            output.println("Token ID: " + tokenID);
            output.println(selectedTokenID);
            output.println("________________________________________________________________________________");
         }

         output.println("################################################################################");
         output.println("################################################################################");
         Token token = null;
         selectedTokenID = null;
         if (tokens.length == 0) {
            output.println("There is no slot with a present token.");
         } else if (tokens.length == 1) {
            output.println("Taking token with ID: " + tokens[0].getTokenID());
            new Long(tokens[0].getTokenID());
            token = tokens[0];
         } else {
            boolean gotTokenID = false;

            while(!gotTokenID) {
               output.print("Enter the ID of the token to use or 'x' to exit: ");
               output.flush();
               String tokenIDstring;
               if (null != slot) {
                  tokenIDstring = slot;
                  output.print(slot + "\n");
               } else {
                  tokenIDstring = input.readLine();
               }

               if (tokenIDstring.equalsIgnoreCase("x")) {
                  break;
               }

               try {
                  Long selectedTokenID = new Long(tokenIDstring);
                  token = (Token)tokenIDtoToken.get(selectedTokenID);
                  if (token != null) {
                     gotTokenID = true;
                  } else {
                     output.println("A token with the entered ID \"" + tokenIDstring + "\" does not exist. Try again.");
                  }
               } catch (NumberFormatException var12) {
                  output.println("The entered ID \"" + tokenIDstring + "\" is invalid. Try again.");
               }
            }
         }

         output.println("################################################################################");
         return token;
      }
   }

   public static Session openAuthorizedSession(Token token, boolean rwSession, PrintWriter output, BufferedReader input) throws TokenException, IOException {
      return openAuthorizedSession(token, rwSession, output, input, (String)null);
   }

   public static Session openAuthorizedSession(Token token, boolean rwSession, PrintWriter output, BufferedReader input, String pin) throws TokenException, IOException {
      if (token == null) {
         throw new NullPointerException("Argument \"token\" must not be null.");
      } else if (output == null) {
         throw new NullPointerException("Argument \"output\" must not be null.");
      } else if (input == null) {
         throw new NullPointerException("Argument \"input\" must not be null.");
      } else {
         output.println("################################################################################");
         output.println("opening session");
         Session session = token.openSession(true, rwSession, (Object)null, (Notify)null);
         TokenInfo tokenInfo = token.getTokenInfo();
         if (tokenInfo.isLoginRequired()) {
            if (tokenInfo.isProtectedAuthenticationPath()) {
               output.print("Please enter the user-PIN at the PIN-pad of your reader.");
               output.flush();
               session.login(true, (char[])null);
            } else {
               output.print("Enter user-PIN and press [return key]: ");
               output.flush();
               String userPINString;
               if (null != pin) {
                  userPINString = pin;
                  output.println(pin);
               } else {
                  userPINString = input.readLine();
               }

               session.login(true, userPINString.toCharArray());
            }
         }

         output.println("################################################################################");
         return session;
      }
   }

   public static KeyAndCertificate selectKeyAndCertificate(Session session, Key keyTemplate, PrintWriter output, BufferedReader input) throws TokenException, IOException {
      return selectKeyAndCertificate(session, keyTemplate, output, input, false);
   }

   public static KeyAndCertificate selectKeyAndCertificate(Session session, Key keyTemplate, PrintWriter output, BufferedReader input, boolean pickFirstSuitable) throws TokenException, IOException {
      if (session == null) {
         throw new NullPointerException("Argument \"session\" must not be null.");
      } else if (keyTemplate == null) {
         throw new NullPointerException("Argument \"keyTemplate\" must not be null.");
      } else if (output == null) {
         throw new NullPointerException("Argument \"output\" must not be null.");
      } else if (input == null) {
         throw new NullPointerException("Argument \"input\" must not be null.");
      } else {
         String botObjectHandle = null;
         output.println("################################################################################");
         output.println("searching for keys");
         Vector keyList = new Vector(4);
         session.findObjectsInit(keyTemplate);

         iaik.pkcs.pkcs11.objects.Object[] matchingKeys;
         while((matchingKeys = session.findObjects(1)).length > 0) {
            keyList.addElement(matchingKeys[0]);
         }

         session.findObjectsFinal();
         Hashtable keyToCertificateTable = new Hashtable(4);

         for(Enumeration keyListEnumeration = keyList.elements(); keyListEnumeration.hasMoreElements(); session.findObjectsFinal()) {
            PrivateKey signatureKey = (PrivateKey)keyListEnumeration.nextElement();
            byte[] keyID = signatureKey.getId().getByteArrayValue();
            X509PublicKeyCertificate certificateTemplate = new X509PublicKeyCertificate();
            certificateTemplate.getId().setByteArrayValue(keyID);
            session.findObjectsInit(certificateTemplate);
            iaik.pkcs.pkcs11.objects.Object[] correspondingCertificates = session.findObjects(1);
            if (correspondingCertificates.length > 0) {
               keyToCertificateTable.put(signatureKey, correspondingCertificates[0]);
            }
         }

         Key selectedKey = null;
         X509PublicKeyCertificate correspondingCertificate = null;
         if (keyList.size() == 0) {
            output.println("Found NO matching key that can be used.");
         } else if (keyList.size() == 1) {
            selectedKey = (Key)keyList.elementAt(0);
            botObjectHandle = String.valueOf(((Key)selectedKey).getObjectHandle());
            correspondingCertificate = (X509PublicKeyCertificate)keyToCertificateTable.get(selectedKey);
            String correspondingCertificateString = toString(correspondingCertificate);
            output.println("Found just one private RSA signing key. This key will be used:");
            output.println(selectedKey);
            output.println("--------------------------------------------------------------------------------");
            output.println("The certificate for this key is:");
            output.println(correspondingCertificateString != null ? correspondingCertificateString : "<no certificate found>");
         } else {
            output.println("found these private RSA signing keys:");
            Hashtable objectHandleToObjectMap = new Hashtable(keyList.size());
            Enumeration keyListEnumeration2 = keyList.elements();

            while(keyListEnumeration2.hasMoreElements()) {
               iaik.pkcs.pkcs11.objects.Object signatureKey = (iaik.pkcs.pkcs11.objects.Object)keyListEnumeration2.nextElement();
               long objectHandle = signatureKey.getObjectHandle();
               objectHandleToObjectMap.put(new Long(objectHandle), signatureKey);
               correspondingCertificate = (X509PublicKeyCertificate)keyToCertificateTable.get(signatureKey);
               if (null == botObjectHandle && null != correspondingCertificate) {
                  botObjectHandle = String.valueOf(objectHandle);
               }

               String correspondingCertificateString = toString(correspondingCertificate);
               output.println("________________________________________________________________________________");
               output.println("RSA signature key with handle: " + objectHandle);
               output.println(signatureKey);
               output.println("--------------------------------------------------------------------------------");
               output.println("The certificate for this key is: ");
               output.println(correspondingCertificateString != null ? correspondingCertificateString : "<no certificate found>");
               output.println("________________________________________________________________________________");
            }

            boolean gotObjectHandle = false;

            while(!gotObjectHandle) {
               output.print("Enter the handle of the key to use for signing or 'x' to exit: ");
               output.flush();
               String objectHandleString;
               if (pickFirstSuitable) {
                  objectHandleString = botObjectHandle;
                  output.println(botObjectHandle);
               } else {
                  objectHandleString = input.readLine();
               }

               if (objectHandleString.equalsIgnoreCase("x")) {
                  break;
               }

               try {
                  Long selectedObjectHandle = new Long(objectHandleString);
                  selectedKey = (RSAPrivateKey)objectHandleToObjectMap.get(selectedObjectHandle);
                  if (selectedKey != null) {
                     correspondingCertificate = (X509PublicKeyCertificate)keyToCertificateTable.get(selectedKey);
                     gotObjectHandle = true;
                  } else {
                     output.println("An object with the handle \"" + objectHandleString + "\" does not exist. Try again.");
                  }
               } catch (NumberFormatException var18) {
                  output.println("The entered handle \"" + objectHandleString + "\" is invalid. Try again.");
               }
            }
         }

         output.println("################################################################################");
         return selectedKey != null ? new KeyAndCertificate((Key)selectedKey, correspondingCertificate) : null;
      }
   }

   public static String toString(X509PublicKeyCertificate certificate) {
      String certificateString = null;
      if (certificate != null) {
      }

      return (String)certificateString;
   }
}
