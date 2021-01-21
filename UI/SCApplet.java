package UI;

import Basic.BasicFunctions;
import Crypto.Facade;
import Crypto.LogInResult;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import javax.smartcardio.CardNotPresentException;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SCApplet extends Applet {
   static Facade f = new Facade();
   private Button mLogInButton;
   private Button mLogOutButton;
   private static String errDesc;
   public String url;
   public String vreme;
   public String ipAddress;
   static final Logger _log = LoggerFactory.getLogger(SCApplet.class);

   public void init() {
      Dimension appletSize = this.getSize();
      this.url = this.getCodeBase().toString();
      this.url = this.url.substring(0, this.url.length() - "faces/".length());
      this.vreme = this.getTime();
      this.ipAddress = this.getIPAddress();
      if (this.getParameter("test") == null) {
         this.mLogInButton = new Button("LogIn");
         this.mLogInButton.setLocation(0, 0);
         this.mLogInButton.setSize(appletSize.width / 2, appletSize.height);
         this.mLogInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String a;
               String var4;
               try {
                  try {
                     boolean uspeh = SCApplet.this.logIn("sr-Latn-CS", false);
                     a = "";
                  } catch (Exception var6) {
                     a = var6.getMessage();
                     var4 = "";
                  }

                  try {
                     String popunjeniTiket = SCApplet.this.getTicket();
                     a = "";
                  } catch (Exception var5) {
                     a = var5.getMessage();
                     var4 = "";
                  }
               } catch (Exception var7) {
                  JOptionPane.showMessageDialog((Component)null, var7.getMessage());
                  a = SCApplet.this.getErrDesc();
                  var4 = "";
               }

            }
         });
         this.setLayout((LayoutManager)null);
         this.add(this.mLogInButton);
         this.mLogOutButton = new Button("Potpisi");
         this.mLogOutButton.setLocation(appletSize.width / 2, 0);
         this.mLogOutButton.setSize(appletSize.width / 2, appletSize.height);
         this.mLogOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
         });
         this.setLayout((LayoutManager)null);
         this.add(this.mLogOutButton);
      }

   }

   private String getIPAddress() {
      InetAddress addr = null;

      try {
         addr = InetAddress.getLocalHost();
      } catch (UnknownHostException var3) {
         _log.error(var3.getMessage());
      }

      this.ipAddress = addr.getHostAddress();
      return this.ipAddress;
   }

   private String getTime() {
      Date now = Calendar.getInstance().getTime();
      SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
      this.vreme = formatter.format(now.getTime()).toString();
      return this.vreme;
   }

   public boolean logIn(final String culture, final boolean administration) throws Exception {
      errDesc = "";

      try {
         boolean res = (Boolean)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Object run() throws Exception {
               String language = "sr-Latn";
               String country = "CS";
               if (culture.lastIndexOf(45) > 0) {
                  language = culture.substring(0, culture.lastIndexOf(45));
                  country = culture.substring(culture.lastIndexOf(45) + 1);
               }

               Locale.setDefault(new Locale(language, country));
               PINJDialog pin = new PINJDialog((Frame)null, true, administration);
               if (pin.unetPin) {
                  LogInResult result = SCApplet.f.logIn(pin.PIN, pin.odabraniCitac, pin.odabraniCitacIndex, SCApplet.this.url);
                  SCApplet.errDesc = result.getError();
                  return result.getIsLogged();
               } else {
                  SCApplet.errDesc = "Application terminated by user.";
                  return false;
               }
            }
         });
         return res;
      } catch (PrivilegedActionException var5) {
         _log.error(var5.getMessage());
         if (var5.getException().getClass() == CardNotPresentException.class) {
            JOptionPane.showMessageDialog((Component)null, ResourceBundle.getBundle("UI/Bundle").getString("NO_CARD_PRESENT."));
         } else {
            JOptionPane.showMessageDialog((Component)null, var5.getException().getMessage());
         }

         return false;
      }
   }

   public boolean loggedIn() throws Exception {
      errDesc = "";

      try {
         return f.loggedIn();
      } catch (Exception var2) {
         _log.error(var2.getMessage());
         errDesc = var2.getMessage();
         throw var2;
      }
   }

   public String ClientGetCertID() throws Exception {
      errDesc = "";

      try {
         return f.ClientGetCertID();
      } catch (Exception var2) {
         _log.error(var2.getMessage());
         errDesc = var2.getMessage();
         throw var2;
      }
   }

   public boolean CardReady() {
      errDesc = "";

      try {
         boolean res = (Boolean)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Object run() throws Exception {
               return SCApplet.f.CardReady();
            }
         });
         return res;
      } catch (PrivilegedActionException var3) {
         _log.error(var3.getMessage());
         if (var3.getMessage() != null) {
            errDesc = var3.getMessage();
         } else {
            errDesc = var3.getException().getMessage();
         }

         return false;
      }
   }

   public boolean CardPresent() {
      errDesc = "";

      try {
         boolean res = (Boolean)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Object run() throws Exception {
               return SCApplet.f.CardPresent();
            }
         });
         return res;
      } catch (PrivilegedActionException var3) {
         _log.error(var3.getMessage());
         if (var3.getMessage() != null) {
            errDesc = var3.getMessage();
         } else {
            errDesc = var3.getException().getMessage();
         }

         return false;
      }
   }

   public boolean logOut() throws Exception {
      errDesc = "";

      try {
         AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Object run() throws Exception {
               SCApplet.f.logOut();
               return null;
            }
         });
         return true;
      } catch (PrivilegedActionException var2) {
         _log.error(var2.getMessage());
         if (var2.getMessage() != null) {
            errDesc = var2.getMessage();
         } else {
            errDesc = var2.getException().getMessage();
         }

         throw var2.getException();
      }
   }

   public String signXmlEnv(String inputXmlString, String inputSignode, String inputSignerId) throws Exception {
      errDesc = "";

      try {
         return f.signXmlEnv(inputXmlString, inputSignode, inputSignerId);
      } catch (Exception var5) {
         _log.error(var5.getMessage());
         errDesc = var5.getMessage();
         throw var5;
      }
   }

   public Object[] getReaders() throws Exception {
      errDesc = "";

      try {
         Object[] res = (Object[])((Object[])AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Object run() throws Exception {
               return Facade.getReaders();
            }
         }));
         return res;
      } catch (PrivilegedActionException var3) {
         _log.error(var3.getMessage());
         if (var3.getMessage() != null) {
            errDesc = var3.getMessage();
         } else {
            errDesc = var3.getException().getMessage();
         }

         throw var3.getException();
      }
   }

   public boolean verifyXmlEnv(String inputXmlString) throws Exception {
      errDesc = "";

      try {
         return f.verifyXmlEnv(inputXmlString);
      } catch (Exception var3) {
         _log.error(var3.getMessage());
         errDesc = var3.getMessage();
         throw var3;
      }
   }

   public String getLoginInfo(String ticket) throws Exception {
      try {
         return this.getLoginInfo(ticket, "Certificate");
      } catch (Exception var3) {
         _log.error(var3.getMessage());
         errDesc = var3.getMessage();
         throw var3;
      }
   }

   public String getLoginInfo(String ticket, String type) throws Exception {
      errDesc = "";

      try {
         return f.getLoginInfo(ticket, type);
      } catch (Exception var4) {
         _log.error(var4.getMessage());
         errDesc = var4.getMessage();
         throw var4;
      }
   }

   public static String getHash(String pass) throws Exception {
      errDesc = "";

      try {
         return BasicFunctions.getHash(pass);
      } catch (Exception var2) {
         _log.error(var2.getMessage());
         errDesc = var2.getMessage();
         throw var2;
      }
   }

   public void setObject(String key, Object value) throws Exception {
      errDesc = "";

      try {
         f.setObject(key, value);
      } catch (Exception var4) {
         _log.error(var4.getMessage());
         errDesc = var4.getMessage();
         throw var4;
      }
   }

   public Object getObject(String key) throws Exception {
      errDesc = "";

      try {
         return f.getObject(key);
      } catch (Exception var3) {
         _log.error(var3.getMessage());
         errDesc = var3.getMessage();
         throw var3;
      }
   }

   public String getVersion() {
      return "2";
   }

   public String getErrDesc() {
      try {
         return errDesc.replaceAll("java.lang.Exception:", (String)null).trim();
      } catch (Exception var2) {
         _log.error(var2.getMessage());
         return "";
      }
   }

   public boolean setLanguage(final String culture) throws Exception {
      errDesc = "";

      try {
         boolean res = (Boolean)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Object run() throws Exception {
               String language = "sr-Latn";
               String country = "CS";
               if (culture.lastIndexOf(45) > 0) {
                  language = culture.substring(0, culture.lastIndexOf(45));
                  country = culture.substring(culture.lastIndexOf(45) + 1);
               }

               Locale.setDefault(new Locale(language, country));
               return true;
            }
         });
         return res;
      } catch (PrivilegedActionException var4) {
         _log.error(var4.getMessage());
         return false;
      }
   }

   public String getTicket(String id) throws Exception {
      String nalog = "<Ticket><TicketHeader Id=\"" + id + "\"" + " Type=\"CRIS\"><InfoData><TimeStamp>" + this.getTime() + "</TimeStamp><IPAddress>" + this.getIPAddress() + "</IPAddress></InfoData></TicketHeader></Ticket>";
      errDesc = "";

      try {
         return f.signXmlEnv(nalog, id, "idSignature");
      } catch (Exception var4) {
         _log.error(var4.getMessage());
         errDesc = var4.getMessage();
         throw var4;
      }
   }

   public String getTicket() throws Exception {
      Random rnd = new Random();
      rnd.setSeed(Calendar.getInstance().getTimeInMillis());
      int lntTicketID = rnd.nextInt();
      String ticketID = String.format("%08X", lntTicketID);
      return this.getTicket(ticketID);
   }

   public String StringToBase64(String input64String) throws Exception {
      errDesc = "";

      try {
         String retVal = f.StringToBase64(input64String);
         return retVal;
      } catch (Exception var3) {
         _log.error(var3.getMessage());
         errDesc = var3.getMessage();
         throw var3;
      }
   }

   public String getOrder() throws Exception {
      errDesc = "";

      try {
         String zNalogZaPotpisivanje = "<CRTransaction><m-obrazac-ustanove><podaci-osiguranik><jmbg>5555555555559</jmbg><prezime>OsiguranikIme</prezime><ime>OsiguranikPrezime</ime><pol>1</pol><datum-rodjenja>1996-02-28T00:00:00</datum-rodjenja><ime-roditelja>OsiguranikRoditelj</ime-roditelja><adresa-boravista><sif-opstina>70254</sif-opstina><sif-ptt-broj>11000</sif-ptt-broj><sif-mesto>4262</sif-mesto><sif-ulica>2336</sif-ulica><broj>5</broj><stan>5</stan></adresa-boravista><sif-drzavljanstvo>688</sif-drzavljanstvo><sif-zanimanje-skolovanje>307101</sif-zanimanje-skolovanje><nosilac-osiguranja>1</nosilac-osiguranja></podaci-osiguranik><podaci-osiguranje><datum-pocetka-osiguranja>2012-12-24T00:00:00</datum-pocetka-osiguranja><sif-osnov-siguranja>445</sif-osnov-siguranja><sif-posebni-podaci-o-osiguraniku>1</sif-posebni-podaci-o-osiguraniku><!--Optional:--><sif-korisnik-prava-pio>1</sif-korisnik-prava-pio><ugovorena-zarada>1</ugovorena-zarada><rok-trajanja-ugovora>1</rok-trajanja-ugovora></podaci-osiguranje><podaci-obveznik><naziv-opd>Obveznik</naziv-opd><adresa-opd><sif-opstina>70254</sif-opstina><sif-ptt-broj>11030</sif-ptt-broj><sif-mesto>4262</sif-mesto><sif-ulica>2973</sif-ulica><broj>1</broj></adresa-opd><sif-delatnost>6910</sif-delatnost><maticni-broj>55555555</maticni-broj><pib>555555555</pib><cr-broj></cr-broj><email>registracijacroso@gmail.com</email></podaci-obveznik><podaci-audit><vrsta-prijave>1</vrsta-prijave><datum-prijave>2012-12-24T00:00:00</datum-prijave><datum-podnosenja-prijave>2012-12-24T00:00:00</datum-podnosenja-prijave><source>4</source><podneti-dokazi><sif-podneti-dokaz>DKZ038</sif-podneti-dokaz></podneti-dokazi></podaci-audit></m-obrazac-ustanove><AuthorizationData><TipKorisnika>SALTER</TipKorisnika><IdKorisnika>1212952660132</IdKorisnika></AuthorizationData></CRTransaction>";
         String retVal = f.signXmlEnv(zNalogZaPotpisivanje, "", "");
         return retVal;
      } catch (Exception var3) {
         _log.error(var3.getMessage());
         errDesc = var3.getMessage();
         throw var3;
      }
   }
}
