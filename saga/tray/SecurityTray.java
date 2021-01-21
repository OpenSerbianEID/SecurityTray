package saga.tray;

import java.awt.Component;
import java.awt.SystemTray;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityTray {
   static Logger _log = null;

   public static void main(String[] args) throws Exception {
      CreateAndSetLoggerFolder();
      startSecTray();
   }

   public static void startSecTray() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException, Exception {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      SecurityTrayStarter tray = new SecurityTrayStarter();
      if (!SystemTray.isSupported()) {
         JOptionPane.showMessageDialog((Component)null, "System tray is not supported !!!", "Greska", 2);
      } else {
         tray.trayStarter();
      }
   }

   private static void CreateAndSetLoggerFolder() {
      String pathToLogFile = System.getProperty("user.home") + System.getProperty("file.separator") + "SecurityTrayLog";
      File logFile = new File(pathToLogFile);
      if (!logFile.exists()) {
         logFile.mkdir();
      }

      System.setProperty("USER_HOME", pathToLogFile);
      _log = LoggerFactory.getLogger(SecurityTray.class);
   }
}
