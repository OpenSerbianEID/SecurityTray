package saga.tray;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
   FileHandler fh;
   Logger logger;
   private static Logger instance = null;

   protected AppLogger() throws IOException {
   }

   public static Logger getInstance() throws IOException {
      if (instance == null) {
         int logFileSize = 5242880;
         instance = Logger.getLogger("MyLog");
         String pathToLogFile = System.getProperty("user.home") + "//SecurityTrayLog";
         File logFile = new File(pathToLogFile);
         if (!logFile.exists()) {
            logFile.mkdir();
         }

         pathToLogFile = logFile.getAbsolutePath() + "//Log.log";
         FileHandler fh = new FileHandler(pathToLogFile, logFileSize, 1, true);
         instance.addHandler(fh);
         SimpleFormatter formatter = new SimpleFormatter();
         fh.setFormatter(formatter);
      }

      return instance;
   }
}
