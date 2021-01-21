package Crypto;

import Utils.Base64Utils;
import Utils.XMLDocUtils;
import iaik.pkcs.pkcs11.InitializeArgs;
import iaik.pkcs.pkcs11.Mechanism;
import iaik.pkcs.pkcs11.Module;
import iaik.pkcs.pkcs11.Notify;
import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.Slot;
import iaik.pkcs.pkcs11.SlotInfo;
import iaik.pkcs.pkcs11.Token;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.TokenInfo;
import iaik.pkcs.pkcs11.objects.RSAPrivateKey;
import iaik.pkcs.pkcs11.objects.X509PublicKeyCertificate;
import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.TerminalFactory;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

class SessionContext {
   private byte[] SELECT = new byte[]{0, -92, 4, 0, 9, 116, 105, 99, 107, 101, 116, 105, 110, 103, 0};
   private byte[] INS_INC = new byte[]{0, 1, 0, 0};
   private static byte[] INS_DEC = new byte[]{0, 2, 0, 0};
   private static byte[] INS_READ = new byte[]{0, 3, 0, 0, 1};
   String loginType;
   X509Certificate signingCertificate;
   X509Certificate serverCertificate;
   PrivateKey privateKey;
   boolean loggedIn = false;
   boolean pkcs11 = false;
   String reader;
   int odabraniCitacIndex;
   char[] pin;
   String emptyTicket;
   Module pkcs11Module_;
   Session session;
   KeyFactory keyFactory;
   byte[] privateKeyBytes = null;
   String base64PrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI1sAnwbl/bC3jS931sI56FyWR7kUko8iLj+T1ED+IGMpw9h6p0LVcnqogPjfMfHZ3qtc5jOLRkqxY+y/HR7yVLtBfPd5nWRk4VlbL2Scvoau62hKWT5ezWu6ij5w/rhmef5IMpX+GMmL4SiP/tBd/Akl24t/DxDy5mzdf3inoRDAgMBAAECgYBX6ruot+bC6uYE170Q9Po6rcGlqL9Xk0ZT6xUYAmXs86mvsGmP88oDWrAZxsR91gMIiugAKLx0lth2uwiiyYmf7QVQkRCkLeKYFFAMWFNpDvPapNNUFthK2UUTtPzR8IHfDCwaWFl5Bt8NfCUapcKJWcfXdXleT6cr9SXCJpRq4QJBAMgf60hgol16w9E0+p9w2Z/w/qWKqJaP45NZX8Q3SbFDGD2ncBYxkI+2sLD2gEcwGOSaVpMcDGrphYO1wKj4/asCQQC06EKtqzDsjG8/Y/rx52neM97ySHVerolhLIveq+CYaBs1cORNI2TqF26rydibq3BgHVsucYY2hbCHdP45qwvJAkEAn0IXgT+1tmKOFpzsnZm29jtYyvBvfXIAjzBer823zWMxlU78Og0zl8qxDgL8zqoOBU51exilISe5YrlvGokbhQJBAJ1sAyHm90LQjboGwYchTcHbN2Vz4haLIObbgbc5r85PwWWGoFPzucvuqhJ5KbGExNwOj5rhlN37BftJtjPugOkCQDtT6gooqAdoZu5nRJIYDjwswdUKZxPtQtWZunLqA7s72ra/UwwcQqgPgTE73wmSNGVwZeomo57158KaoyymLhA=";
   String manufacturerID;
   String cardATR;
   RSAPrivateKey signatureKey;
   int _odabraniCitacIndex;
   Boolean WatchDataToken = false;
   static Boolean isCD = false;
   static Boolean isIDCard = false;
   private static Hashtable dataContext = new Hashtable();
   static final Logger _log = LoggerFactory.getLogger(SessionContext.class);

   public String arrayToHex(byte[] data) {
      StringBuffer sb = new StringBuffer();

      for(int i = 0; i < data.length; ++i) {
         String bs = Integer.toHexString(data[i] & 255);
         if (bs.length() == 1) {
            sb.append(0);
         }

         sb.append(bs);
      }

      return sb.toString();
   }

   public SessionContext() {
   }

   LogInResult LogIn(char[] aPinCode, String cardReader, int OdabraniCitacIndex, String url) throws Exception {
      LogInResult result = new LogInResult();
      String crlURLAddress = null;
      String pkcs11WrapperPath = System.getProperty("user.dir") + "\\lib\\";
      String pkcs11WrapperName = "pkcs11wrapper_";
      if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("64")) {
         pkcs11WrapperName = pkcs11WrapperName + "64.dll";
      } else {
         pkcs11WrapperName = pkcs11WrapperName + "32.dll";
      }

      url = url + "/" + pkcs11WrapperName;
      String pkcs11Wrapper_ = pkcs11WrapperPath + pkcs11WrapperName;
      if (!(new File(pkcs11Wrapper_)).exists()) {
         File file = new File(pkcs11Wrapper_);
         this.download(url, file);
      }

      isIDCard = false;
      isCD = false;
      String os = System.getProperty("os.name").toLowerCase();
      String programFiles = System.getenv("programfiles");
      Boolean isCard = true;
      this._odabraniCitacIndex = OdabraniCitacIndex;
      TerminalFactory tf = TerminalFactory.getDefault();
      CardTerminals ct = tf.terminals();

      try {
         List l = ct.list();
         int cardReadersNumber = l.size();
         CardTerminal c = (CardTerminal)l.get(OdabraniCitacIndex);
         if (!c.isCardPresent()) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("_-_THE_SMART_CARD_IS_NOT_INSERTED.\\N"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString("_-_THE_SMART_CARD_IS_NOT_INSERTED.\\N"));
            _log.error(ResourceBundle.getBundle("UI/Bundle").getString("_-_THE_SMART_CARD_IS_NOT_INSERTED.\\N"));
            return result;
         }

         Card card = c.connect("*");
         this.cardATR = this.arrayToHex(card.getATR().getBytes());
         CardChannel ch = card.getBasicChannel();
         card.disconnect(false);
      } catch (Exception var43) {
         _log.error(var43.getMessage());
         if (var43.getMessage().equalsIgnoreCase("connect() failed")) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("CKR_Connection_Failed"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString("CKR_Connection_Failed"));
            return result;
         }

         JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString(var43.getMessage()), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
         result.setIsLogged(false);
         result.setError(ResourceBundle.getBundle("UI/Bundle").getString(var43.getMessage()));
         return result;
      }

      this.keyFactory = KeyFactory.getInstance("RSA");
      this.privateKeyBytes = Base64Utils.base64Decode(this.base64PrivateKey);
      this.privateKey = this.keyFactory.generatePrivate(new PKCS8EncodedKeySpec(this.privateKeyBytes));
      this.pkcs11 = true;
      String systemDrive = System.getProperty("user.home").substring(0, 1);
      this.signingCertificate = null;
      this.signatureKey = null;
      String moduleName = null;
      boolean oldPKS_or_MUP = false;

      try {
         if (os.indexOf("win") >= 0) {
            if (this.cardATR.equalsIgnoreCase("3bb918008131fe9e8073ff614083000000df")) {
               crlURLAddress = "http://ca.mup.gov.rs/MUPCAGradjani.crl";
               isIDCard = true;
               this.loginType = "IDCard";
               if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("64")) {
                  if (!(new File(programFiles + "\\MUP RS\\Republic of Serbia ID Card Middleware\\rsidp11_x64.dll")).exists()) {
                     moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\MUP RS\\Republic of Serbia ID Card Middleware\\rsidp11_x64.dll";
                  } else {
                     moduleName = programFiles + "\\MUP RS\\Republic of Serbia ID Card Middleware\\rsidp11_x64.dll";
                  }

                  oldPKS_or_MUP = true;
               } else if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("32")) {
                  if (!(new File(programFiles + "\\MUP RS\\Republic of Serbia ID Card Middleware\\rsidp11_x86.dll")).exists()) {
                     moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\MUP RS\\Republic of Serbia ID Card Middleware\\rsidp11_x86.dll";
                  } else {
                     moduleName = programFiles + "\\MUP RS\\Republic of Serbia ID Card Middleware\\rsidp11_x86.dll";
                  }

                  oldPKS_or_MUP = true;
               }
            } else if (this.cardATR.equalsIgnoreCase("3bff9400008131804380318065b0850201f3120fff82900079")) {
               crlURLAddress = "http://ca.mup.gov.rs/MUPCAGradjani.crl";
               isIDCard = true;
               this.loginType = "IDCard";
               if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("64")) {
                  if ((new File(programFiles + "\\TrustEdgeID\\netsetpkcs11_x64.dll")).exists()) {
                     moduleName = programFiles + "\\TrustEdgeID\\netsetpkcs11_x64.dll";
                  } else if ((new File(programFiles.substring(0, programFiles.length() - 6) + "\\TrustEdgeID\\netsetpkcs11_x64.dll")).exists()) {
                     moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\TrustEdgeID\\netsetpkcs11_x64.dll";
                  } else if ((new File(programFiles + "\\NetSeT\\TrustEdgeID\\netsetpkcs11_x64.dll")).exists()) {
                     moduleName = programFiles + "\\NetSeT\\TrustEdgeID\\netsetpkcs11_x64.dll";
                  } else if ((new File(programFiles.substring(0, programFiles.length() - 6) + "\\NetSeT\\TrustEdgeID\\netsetpkcs11_x64.dll")).exists()) {
                     moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\NetSeT\\TrustEdgeID\\netsetpkcs11_x64.dll";
                  } else {
                     moduleName = programFiles + "\\x.dll";
                  }
               } else if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("32")) {
                  if ((new File(programFiles + "\\TrustEdgeID\\netsetpkcs11_x86.dll")).exists()) {
                     moduleName = programFiles + "\\TrustEdgeID\\netsetpkcs11_x86.dll";
                  } else if ((new File(programFiles.substring(0, programFiles.length() - 6) + "\\TrustEdgeID\\netsetpkcs11_x86.dll")).exists()) {
                     moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\TrustEdgeID\\netsetpkcs11_x86.dll";
                  } else if ((new File(programFiles + "\\NetSeT\\TrustEdgeID\\netsetpkcs11_x86.dll")).exists()) {
                     moduleName = programFiles + "\\NetSeT\\TrustEdgeID\\netsetpkcs11_x86.dll";
                  } else if ((new File(programFiles.substring(0, programFiles.length() - 6) + "\\NetSeT\\TrustEdgeID\\netsetpkcs11_x86.dll")).exists()) {
                     moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\NetSeT\\TrustEdgeID\\netsetpkcs11_x86.dll";
                  } else {
                     moduleName = programFiles + "\\x.dll";
                  }
               }
            } else if (!this.cardATR.equalsIgnoreCase("3bf81300008131fe454a434f5076323431b7") && !this.cardATR.equalsIgnoreCase("3BFA1300008131FE454A434F5032315632333191")) {
               if (!this.cardATR.equalsIgnoreCase("3B7D94000080318065B0831100C883009000") && !this.cardATR.equalsIgnoreCase("3b7d94000080318065b08311c0a983009000") && !this.cardATR.equalsIgnoreCase("3BF81300008131FE454A434F5076323431B7") && !this.cardATR.equalsIgnoreCase("3BE900008131FE454A434F503231563232A1") && !this.cardATR.equalsIgnoreCase("3BEA00008131FE454A33413038305632343180")) {
                  if (this.cardATR.equalsIgnoreCase("3bfa1800ff8131fe454a434f5032315632333165")) {
                     this.loginType = "Certificate";
                     if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("32")) {
                        moduleName = systemDrive + ":/Windows/System32/aetpkss1.dll";
                     } else {
                        moduleName = systemDrive + ":/Windows/System32/aetpkss1.dll";
                     }
                  } else if (this.cardATR.equalsIgnoreCase("3b7f96000080318065b084413df6120ffe829000")) {
                     this.loginType = "Certificate";
                     if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("64")) {
                        if ((new File("C:\\ESSQCA\\pkcs11\\IDPrimePKCS1164.dll")).exists()) {
                           moduleName = "C:\\ESSQCA\\pkcs11\\IDPrimePKCS1164.dll";
                        } else if (!(new File(programFiles + "\\Gemalto\\IDGo 800 PKCS#11\\IDPrimePKCS1164.dll")).exists()) {
                           moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\Gemalto\\IDGo 800 PKCS#11\\IDPrimePKCS1164.dll";
                        } else {
                           moduleName = programFiles + "\\Gemalto\\IDGo 800 PKCS#11\\IDPrimePKCS1164.dll";
                        }
                     } else if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("32")) {
                        if ((new File("C:\\ESSQCA\\pkcs11\\IDPrimePKCS11.dll")).exists()) {
                           moduleName = "C:\\ESSQCA\\pkcs11\\IDPrimePKCS11.dll";
                        } else if (!(new File(programFiles + "\\Gemalto\\IDGo 800 PKCS#11\\IDPrimePKCS11.dll")).exists()) {
                           moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\Gemalto\\IDGo 800 PKCS#11\\IDPrimePKCS11.dll";
                        } else {
                           moduleName = programFiles + "\\Gemalto\\IDGo 800 PKCS#11\\IDPrimePKCS11.dll";
                        }
                     }
                  } else if (!this.cardATR.equalsIgnoreCase("3b7d96000080318065b0831100c883009000") && !this.cardATR.equalsIgnoreCase("3b7d96000080318065b0830201f383009000") && !this.cardATR.equalsIgnoreCase("3B7F96000080318065B0850300EF1202C1829000")) {
                     this.loginType = "Certificate";
                     if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("32")) {
                        moduleName = systemDrive + ":/Windows/System32/aetpkss1.dll";
                     } else {
                        moduleName = systemDrive + ":/Windows/System32/aetpkss1.dll";
                     }
                  } else {
                     this.loginType = "Certificate";
                     if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("64")) {
                        if (!(new File(programFiles + "\\Personal\\bin64\\personal64.dll")).exists()) {
                           moduleName = programFiles + " (x86)" + "\\Personal\\bin64\\personal64.dll";
                        } else {
                           moduleName = programFiles + "\\Personal\\bin64\\personal64.dll";
                        }
                     } else if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("32")) {
                        if (!(new File(programFiles + "\\Personal\\bin\\personal.dll")).exists()) {
                           moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\Personal\\bin\\personal.dll";
                        } else {
                           moduleName = programFiles + "\\Personal\\bin\\personal.dll";
                        }
                     }
                  }
               } else {
                  this.loginType = "Certificate";
                  String nstsPkg = "\\NetSeT\\nstsignpkcs11.dll";
                  moduleName = String.format("%s%s", programFiles, nstsPkg);
                  oldPKS_or_MUP = true;
               }
            } else {
               this.loginType = "Certificate";
               if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("64")) {
                  if ((new File(programFiles + "\\NetSeT\\TrustEdgeID PKS\\netsetpkcs11_x64.dll")).exists()) {
                     moduleName = programFiles + "\\NetSeT\\TrustEdgeID PKS\\netsetpkcs11_x64.dll";
                  } else if ((new File(programFiles.substring(0, programFiles.length() - 6) + "\\NetSeT\\TrustEdgeID PKS\\netsetpkcs11_x64.dll")).exists()) {
                     moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\NetSeT\\TrustEdgeID PKS\\netsetpkcs11_x64.dll";
                  } else if ((new File(programFiles + "\\TrustEdgeID\\netsetpkcs11_x64.dll")).exists()) {
                     moduleName = programFiles + "\\TrustEdgeID\\netsetpkcs11_x64.dll";
                  } else if ((new File(programFiles.substring(0, programFiles.length() - 6) + "\\TrustEdgeID\\netsetpkcs11_x64.dll")).exists()) {
                     moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\TrustEdgeID\\netsetpkcs11_x64.dll";
                  } else {
                     moduleName = programFiles + "\\x.dll";
                  }
               } else if (System.getProperty("sun.arch.data.model").equalsIgnoreCase("32")) {
                  if ((new File(programFiles + "\\NetSeT\\TrustEdgeID PKS\\netsetpkcs11_x86.dll")).exists()) {
                     moduleName = programFiles + "\\NetSeT\\TrustEdgeID PKS\\netsetpkcs11_x86.dll";
                  } else if ((new File(programFiles.substring(0, programFiles.length() - 6) + "\\NetSeT\\TrustEdgeID PKS\\netsetpkcs11_x86.dll")).exists()) {
                     moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\NetSeT\\TrustEdgeID PKS\\netsetpkcs11_x86.dll";
                  } else if ((new File(programFiles + "\\TrustEdgeID\\netsetpkcs11_x86.dll")).exists()) {
                     moduleName = programFiles + "\\TrustEdgeID\\netsetpkcs11_x86.dll";
                  } else if ((new File(programFiles.substring(0, programFiles.length() - 6) + "\\TrustEdgeID\\netsetpkcs11_x86.dll")).exists()) {
                     moduleName = programFiles.substring(0, programFiles.length() - 6) + "\\TrustEdgeID\\netsetpkcs11_x86.dll";
                  } else {
                     moduleName = programFiles + "\\x.dll";
                  }
               }
            }
         }

         if (moduleName == null) {
            result.setIsLogged(false);
            _log.error("PKCS11 driveri nisu pronadjeni!");
            result.setError("PKCS11 driveri nisu pronadjeni!");
            return result;
         }

         this.pkcs11Module_ = Module.getInstance(moduleName, pkcs11Wrapper_);

         try {
            this.pkcs11Module_.initialize((InitializeArgs)null);
         } catch (Exception var37) {
            _log.error(var37.getMessage());
            this.pkcs11Module_.finalize((Object)null);
            this.pkcs11Module_.initialize((InitializeArgs)null);
         }

         Slot[] slots = this.pkcs11Module_.getSlotList(false);
         Slot selectedSlot;
         if (isCard) {
            for(int i = 0; i < slots.length; ++i) {
               SlotInfo slInfo;
               try {
                  slInfo = slots[i].getSlotInfo();
               } catch (Exception var39) {
                  _log.error(var39.getMessage());
                  continue;
               }

               if (slInfo.getSlotDescription().trim().equals(cardReader)) {
                  OdabraniCitacIndex = i;
                  break;
               }
            }

            selectedSlot = slots[OdabraniCitacIndex];
         } else {
            selectedSlot = slots[0];
         }

         Boolean isTokenPresent = selectedSlot.getSlotInfo().isTokenPresent();
         if (!isTokenPresent && isCard && !this.cardATR.equalsIgnoreCase("3b1696417374726964") && !isIDCard) {
            result.setIsLogged(false);
            result.setError("");
            return result;
         }

         Token token = selectedSlot.getToken();
         this.session = token.openSession(true, true, (Object)null, (Notify)null);
         TokenInfo tokenInfo = token.getTokenInfo();
         String tokenLabel = tokenInfo.getLabel().trim();
         String tokenSerial = tokenInfo.getSerialNumber();
         this.manufacturerID = tokenInfo.getManufacturerID().trim();

         try {
            if (tokenInfo.isLoginRequired()) {
               if (tokenInfo.isProtectedAuthenticationPath()) {
                  this.session.login(true, (char[])null);
               } else if (aPinCode.length > 0) {
                  if (!isIDCard && this.manufacturerID.equals("NetSeT Global Solutions") && aPinCode.length > 8) {
                     JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("CKR_PIN_INCORRECT"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
                     result.setIsLogged(false);
                     result.setError(ResourceBundle.getBundle("UI/Bundle").getString("CKR_PIN_INCORRECT"));
                     _log.error(ResourceBundle.getBundle("UI/Bundle").getString("CKR_PIN_INCORRECT"));
                     return result;
                  }

                  this.session.login(true, aPinCode);
               }
            }
         } catch (Exception var38) {
            _log.error(var38.getMessage());
            if (!var38.getMessage().contains("CKR_USER_ALREADY_LOGGED_IN")) {
               if (var38.getMessage().contains("CKR_PIN_LEN_RANGE")) {
                  JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("CKR_PIN_INCORRECT"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
                  result.setIsLogged(false);
                  result.setError(ResourceBundle.getBundle("UI/Bundle").getString("CKR_PIN_INCORRECT"));
                  _log.error(ResourceBundle.getBundle("UI/Bundle").getString("CKR_PIN_INCORRECT"));
                  return result;
               }

               throw var38;
            }
         }

         X509PublicKeyCertificate tokencert = null;
         this.session.findObjectsInit(new X509PublicKeyCertificate());
         int nbObj = 1;
         if (oldPKS_or_MUP) {
            nbObj = 2;
         }

         Object[] objects = this.session.findObjects(nbObj);

         for(int i = 0; i < objects.length; ++i) {
            tokencert = (X509PublicKeyCertificate)objects[i];
            byte[] encodedCertificate = tokencert.getValue().getByteArrayValue();
            ByteArrayInputStream bis = new ByteArrayInputStream(encodedCertificate);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            this.signingCertificate = (X509Certificate)cf.generateCertificate(bis);
         }

         this.session.findObjectsFinal();
         if (this.signingCertificate == null) {
            this.session.logout();
            this.session.closeSession();
            this.pkcs11Module_.finalize((Object)null);
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("CKR_ValidCertificateNotFound"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            _log.error(ResourceBundle.getBundle("UI/Bundle").getString("CKR_ValidCertificateNotFound"));
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString("CKR_ValidCertificateNotFound"));
            return result;
         }

         RSAPrivateKey template = new RSAPrivateKey();
         template.getId().setByteArrayValue(tokencert.getId().getByteArrayValue());
         this.session.findObjectsInit(template);
         Object[] objects_ = this.session.findObjects(nbObj);
         if (objects_ != null && objects_.length > 0) {
            if (objects_.length == 1) {
               this.signatureKey = (RSAPrivateKey)objects_[0];
            } else {
               this.signatureKey = (RSAPrivateKey)objects_[1];
            }
         }

         this.session.findObjectsFinal();
         if (this.signingCertificate.getNotAfter().before(new Date())) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("CERT_HAS_BEEN_EXPIRED"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            _log.error(ResourceBundle.getBundle("UI/Bundle").getString("CERT_HAS_BEEN_EXPIRED"));
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString("CERT_HAS_BEEN_EXPIRED"));
            return result;
         }

         String var52 = "bb";
      } catch (TokenException var40) {
         _log.error(var40.getMessage());
         if (var40.getMessage().equalsIgnoreCase("CKR_PIN_INCORRECT")) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString(var40.getMessage()), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString(var40.getMessage()));
            return result;
         }

         if (var40.getMessage().equalsIgnoreCase("CKR_PIN_LOCKED")) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString(var40.getMessage()), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString(var40.getMessage()));
            return result;
         }

         if (var40.getMessage().equalsIgnoreCase("CKR_DEVICE_ERROR") && this.cardATR.equalsIgnoreCase("3bf81300008131fe454a434f5076323431b7")) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString(var40.getMessage()), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString(var40.getMessage()));
            return result;
         }

         if (var40.getMessage().equalsIgnoreCase("CKR_DEVICE_ERROR") && this.cardATR.equalsIgnoreCase("3bff9400008131804380318065b0850201f3120fff82900079")) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString(var40.getMessage() + "_2"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString(var40.getMessage()));
            return result;
         }

         JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString(var40.toString()), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
         result.setIsLogged(false);
         result.setError(ResourceBundle.getBundle("UI/Bundle").getString(var40.toString()));
         return result;
      } catch (Exception var41) {
         Exception ex = var41;
         _log.error(var41.getMessage());
         if (var41.getMessage().startsWith("%1 is not a valid")) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("CKR_Incompatible_Driver_Card"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString("CKR_Incompatible_Driver_Card"));
            return result;
         }

         if (var41.getMessage().contains("The specified module could not be found.")) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("CKR_PKCS11ProviderNotFound"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString("CKR_PKCS11ProviderNotFound"));
            return result;
         }

         try {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString(ex.getMessage()), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString(ex.getMessage()));
            return result;
         } catch (Exception var36) {
            _log.error(var36.getMessage());
            JOptionPane.showMessageDialog((Component)null, var41.getMessage(), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(var41.getMessage());
            return result;
         }
      } catch (Throwable var42) {
         _log.error(var42.getMessage());
         if (var42.getMessage().contains("Can't load library:") || var42.getMessage().equalsIgnoreCase("no pkcs11wrapper in java.library.path")) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("CKR_PKCS11WrapperNotFound"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString("CKR_PKCS11WrapperNotFound"));
            return result;
         }

         if (var42.getMessage().contains("Can't load")) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("CKR_Incompatible_Driver_Card"), ResourceBundle.getBundle("UI/Bundle").getString("ERROR"), 0);
            result.setIsLogged(false);
            result.setError(ResourceBundle.getBundle("UI/Bundle").getString("CKR_Incompatible_Driver_Card"));
            return result;
         }
      }

      this.loggedIn = true;
      this.reader = cardReader;
      this.odabraniCitacIndex = OdabraniCitacIndex;
      this.pin = aPinCode;
      result.setIsLogged(true);
      result.setError("");
      return result;
   }

   String getLoginInfo(String ticket, boolean pkcs11, String history, String type) throws Exception {
      this.emptyTicket = ticket;
      XPathFactory factory = XPathFactory.newInstance();
      XPath xpath = factory.newXPath();
      Document doc = XMLDocUtils.stringToDocument(ticket);
      Node AC = doc.getElementsByTagName("ApplicationCertificate").item(0);
      CertificateFactory cf = CertificateFactory.getInstance("X.509");
      InputStream is = new ByteArrayInputStream(Base64Utils.base64Decode(AC.getTextContent()));
      this.serverCertificate = (X509Certificate)cf.generateCertificate(is);
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      Document auth = dbf.newDocumentBuilder().newDocument();
      Element authentication = auth.createElement("Authentication");
      auth.appendChild(authentication);
      authentication.setAttribute("type", this.loginType);
      Element user = auth.createElement("User");
      authentication.appendChild(user);
      if (history != null) {
         Element historypin = auth.createElement("HistoryPin");
         authentication.appendChild(historypin);
         historypin.setTextContent(history);
      }

      String principal = this.signingCertificate.getSubjectX500Principal().getName();
      String cn = "";

      int start;
      try {
         start = principal.indexOf("CN=");
         if (start >= 0) {
            start = principal.indexOf(",", principal.indexOf("CN="));
            if (start < 0) {
               start = principal.length();
            }

            cn = principal.substring(start + 3, start);
         }
      } catch (Exception var24) {
         _log.error(var24.getMessage());
      }

      String var26 = "";

      try {
         start = principal.indexOf("O=");
         if (start >= 0) {
            int end = principal.indexOf(",", principal.indexOf("O="));
            if (end < 0) {
               end = principal.length();
            }

            principal.substring(start + 2, end);
         }
      } catch (Exception var23) {
         _log.error(var23.getMessage());
      }

      user.setAttribute("id", cn);
      Element x509Certificate = auth.createElement("X509Certificate");
      authentication.appendChild(x509Certificate);
      x509Certificate.setTextContent(Base64Utils.base64Encode(this.signingCertificate.getEncoded()));
      XPathExpression expr = xpath.compile("//Ticket/TicketHeader/@Id");
      String id = (String)expr.evaluate(doc, XPathConstants.STRING);
      Element ticketID = auth.createElement("TicketID");
      authentication.appendChild(ticketID);
      ticketID.setTextContent(id);
      CryptoFunctions.sesija = this;
      auth = CryptoFunctions.signEnveloped(this.session, this.signatureKey, this.cardATR, auth, "", (String)null, this.signingCertificate, this.privateKey, true, pkcs11, this.WatchDataToken);
      doc.getDocumentElement().appendChild(doc.importNode(auth.getDocumentElement(), true));
      String res = XMLDocUtils.documentToString(doc);
      return res;
   }

   void logOut() throws Exception {
      dataContext.clear();
      this.signingCertificate = null;
      this.pin = null;
      this.privateKey = null;
      this.signatureKey = null;
      this.WatchDataToken = false;
      if (this.pkcs11) {
         if (this.loggedIn) {
            this.loggedIn = false;
            if (!isIDCard) {
               try {
                  this.session.logout();
               } catch (Exception var6) {
                  _log.error(var6.getMessage());
               }

               try {
                  this.session.closeSession();
               } catch (Exception var5) {
                  _log.error(var5.getMessage());
                  String zzzz = var5.getMessage();
                  String var3 = "";
               }
            }
         }

         try {
            this.pkcs11Module_.finalize((Object)null);
         } catch (Exception var4) {
         }
      } else if (this.loggedIn) {
         this.loggedIn = false;
      }

   }

   String ClientGetCertID() {
      return this.signingCertificate.getSerialNumber().toString();
   }

   boolean CardPresent() {
      try {
         if (this.pkcs11) {
            TerminalFactory tf = TerminalFactory.getDefault();
            CardTerminals ct = tf.terminals();

            try {
               List l = ct.list();
               CardTerminal c = (CardTerminal)l.get(this._odabraniCitacIndex);
               if (c.isCardPresent()) {
                  Card card = c.connect("*");
                  this.cardATR = this.arrayToHex(card.getATR().getBytes());
                  CardChannel var6 = card.getBasicChannel();
               } else if (!this.WatchDataToken) {
                  this.session.logout();
                  return false;
               }
            } catch (Exception var8) {
               _log.error(var8.getMessage());
               this.session.logout();
               return false;
            }

            try {
               byte[] SELECT = new byte[]{0, -92, 4, 0, 9, 116, 105, 99, 107, 101, 116, 105, 110, 103, 0};
               if (!this.WatchDataToken && this.cardATR.equalsIgnoreCase("3bb918008131fe9e8073ff614083000000df")) {
                  this.session.signInit(Mechanism.get(6L), this.signatureKey);
               } else {
                  this.session.signInit(Mechanism.get(1L), this.signatureKey);
               }

               this.session.sign(SELECT);
            } catch (Exception var9) {
               _log.error(var9.getMessage());
               this.session.logout();
               return false;
            }

            return true;
         } else {
            return false;
         }
      } catch (Exception var10) {
         _log.error(var10.getMessage());
         return false;
      }
   }

   public void setObject(String key, Object value) {
      dataContext.put(key, value);
   }

   public Object getObject(String key) {
      return dataContext.get(key);
   }

   private void download(String link, File localFile) throws Exception {
      System.out.println("Downloading " + link);
      URL url = new URL(link);
      InputStream is = url.openStream();
      FileOutputStream fos = new FileOutputStream(localFile);
      this.copyStream(new BufferedInputStream(is), fos);
      fos.flush();
      fos.close();
      is.close();
   }

   private void copyStream(InputStream in, OutputStream out) throws IOException {
      byte[] buffer = new byte[1024];
      boolean var4 = false;

      int ix;
      while((ix = in.read(buffer)) > 0) {
         out.write(buffer, 0, ix);
      }

   }
}
