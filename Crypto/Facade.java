package Crypto;

import Utils.Base64Utils;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Facade {
   static SessionContext sesija = new SessionContext();
   static final Logger _log = LoggerFactory.getLogger(Facade.class);

   public boolean loggedIn() {
      return sesija.loggedIn;
   }

   public String ClientGetCertID() throws Exception {
      String rv = null;

      try {
         rv = (String)AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               String retVal = null;
               if (Facade.sesija.loggedIn) {
                  try {
                     retVal = Facade.sesija.ClientGetCertID();
                     return retVal;
                  } catch (Exception var3) {
                     Facade._log.error(var3.getMessage());
                     retVal = "false" + var3.getMessage();
                     return retVal;
                  }
               } else {
                  retVal = "false" + ResourceBundle.getBundle("UI/Bundle").getString("YOU'RE NOT LOGGED IN.");
                  Facade._log.error("Korisnik nije ulogovan");
                  return retVal;
               }
            }
         });
         if ("false".equals(rv.substring(0, 5))) {
            throw new Exception(rv.substring(5));
         } else {
            return rv;
         }
      } catch (Exception var3) {
         _log.error(var3.getMessage());
         throw var3;
      }
   }

   public boolean CardReady() {
      return sesija.loggedIn ? sesija.CardPresent() : false;
   }

   public boolean CardPresent() {
      return sesija.loggedIn ? sesija.CardPresent() : true;
   }

   public LogInResult logIn(char[] pin, String cardReader, int odabraniCitacIndex, String url) throws Exception {
      sesija.logOut();
      return sesija.LogIn(pin, cardReader, odabraniCitacIndex, url);
   }

   public void logOut() throws Exception {
      sesija.logOut();
   }

   public String signXmlEnv(final String inputXmlString, final String inputSignode, final String inputSignerId) throws Exception {
      String rv = null;

      try {
         rv = (String)AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               String retVal = null;
               if (Facade.sesija.loggedIn) {
                  try {
                     retVal = CryptoFunctions.signEnveloped(Facade.sesija.session, Facade.sesija.signatureKey, Facade.sesija.cardATR, inputXmlString, inputSignode, inputSignerId, Facade.sesija.signingCertificate, Facade.sesija.privateKey, Facade.sesija.pkcs11, Facade.sesija.WatchDataToken);
                     return retVal;
                  } catch (Exception var3) {
                     Facade._log.error(var3.getMessage());
                     retVal = "false" + var3.getMessage();
                     return retVal;
                  }
               } else {
                  retVal = "false" + ResourceBundle.getBundle("UI/Bundle").getString("YOU'RE NOT LOGGED IN.");
                  Facade._log.error("Korisnik nije ulogovan");
                  return retVal;
               }
            }
         });
         if ("false".equals(rv.substring(0, 5))) {
            throw new Exception(rv.substring(5));
         } else {
            return rv;
         }
      } catch (Exception var6) {
         _log.error(var6.getMessage());
         throw var6;
      }
   }

   public boolean verifyXmlEnv(final String inputXmlString) throws Exception {
      String rv = null;

      try {
         rv = (String)AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               String retVal = null;
               if (Facade.sesija.loggedIn) {
                  try {
                     retVal = String.valueOf(CryptoFunctions.verifyEnveloped(inputXmlString, Facade.sesija.serverCertificate));
                     return retVal;
                  } catch (Exception var3) {
                     Facade._log.error(var3.getMessage());
                     retVal = var3.getMessage();
                     return retVal;
                  }
               } else {
                  retVal = ResourceBundle.getBundle("UI/Bundle").getString("YOU'RE NOT LOGGED IN.");
                  return retVal;
               }
            }
         });
         if (Boolean.valueOf(rv)) {
            return Boolean.valueOf(rv);
         } else if ("false".equals(rv)) {
            return Boolean.valueOf(rv);
         } else {
            throw new Exception(rv);
         }
      } catch (Exception var4) {
         _log.error(var4.getMessage());
         throw var4;
      }
   }

   public String getLoginInfo(String ticket, String type) throws Exception {
      if (sesija.loggedIn) {
         return sesija.getLoginInfo(ticket, sesija.pkcs11, (String)null, type);
      } else {
         throw new Exception(ResourceBundle.getBundle("UI/Bundle").getString("YOU'RE NOT LOGGED IN."));
      }
   }

   public static Object[] getReaders() throws Exception {
      ArrayList readers = new ArrayList();
      TerminalFactory factory = TerminalFactory.getDefault();
      List terminals = factory.terminals().list();
      Iterator i = terminals.iterator();

      while(i.hasNext()) {
         readers.add(((CardTerminal)i.next()).getName());
      }

      return readers.toArray();
   }

   public void setObject(String key, Object value) throws Exception {
      if (sesija.loggedIn) {
         sesija.setObject(key, value);
      } else {
         throw new Exception(ResourceBundle.getBundle("UI/Bundle").getString("YOU'RE NOT LOGGED IN."));
      }
   }

   public Object getObject(String key) throws Exception {
      if (sesija.loggedIn) {
         return sesija.getObject(key);
      } else {
         throw new Exception(ResourceBundle.getBundle("UI/Bundle").getString("YOU'RE NOT LOGGED IN."));
      }
   }

   public String StringToBase64(String inputBase64String) throws Exception {
      try {
         return Base64Utils.base64Encode(inputBase64String.getBytes());
      } catch (Exception var3) {
         _log.error(var3.getMessage());
         throw var3;
      }
   }
}
