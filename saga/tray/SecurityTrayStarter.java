package saga.tray;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityTrayStarter {
   static final Logger _log = LoggerFactory.getLogger(SecurityTrayStarter.class);
   private JettyServerStarter server;

   public SecurityTrayStarter() throws IOException {
      try {
         this.server = new JettyServerStarter();
      } catch (IOException var3) {
         _log.error(var3.getLocalizedMessage());
      }

      try {
         this.server.start();
      } catch (Exception var2) {
         _log.error("Security Tray application closed. Application is already running or: " + var2.getMessage());
         JOptionPane.showMessageDialog((Component)null, "Application is already running or : " + var2.getMessage(), "Greska", 2);
         System.exit(0);
      }

   }

   public void trayStarter() throws HeadlessException, Exception {
      SystemTray systemTray = SystemTray.getSystemTray();
      Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("filevault_icon.png"));
      PopupMenu trayPopupMenu = new PopupMenu();
      MenuItem action = new MenuItem("Start Service");
      action.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            SecurityTrayStarter.this.startWebService();
         }
      });
      MenuItem stop = new MenuItem("Stop Service");
      stop.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            SecurityTrayStarter.this.stopWebService();
         }
      });
      MenuItem close = new MenuItem("Close");
      close.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            SecurityTrayStarter.this.stopWebService();
            System.exit(0);
         }
      });
      trayPopupMenu.add(close);
      TrayIcon trayIcon = new TrayIcon(image, "XML Signer", trayPopupMenu);
      trayIcon.setImageAutoSize(true);
      systemTray.add(trayIcon);
   }

   private void startWebService() {
      try {
         this.server.start();
      } catch (Exception var2) {
         JOptionPane.showMessageDialog((Component)null, "Error occurred while starting Jetty server. Error: " + var2.getMessage(), "Greska", 2);
         _log.error("Error occurred while starting Jetty server. Error: " + var2.getLocalizedMessage());
      }

   }

   private void stopWebService() {
      try {
         this.server.stop();
      } catch (Exception var2) {
         _log.error("Error occurred while stopping Jetty server. Error: " + var2.getLocalizedMessage());
      }

   }
}
