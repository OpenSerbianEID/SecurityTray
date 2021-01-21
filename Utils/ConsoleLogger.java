package Utils;

import java.util.logging.Logger;

public class ConsoleLogger {
   private Logger logger = Logger.getLogger("appletLog");
   private static ConsoleLogger instance;

   public static ConsoleLogger getInstance() {
      if (instance == null) {
         instance = new ConsoleLogger();
      }

      return instance;
   }

   public void logToConsole(String s) {
      this.logger.info(s);
   }
}
