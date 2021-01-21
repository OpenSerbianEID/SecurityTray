package saga.tray;

import UI.SCApplet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Handler extends AbstractHandler {
   static final Logger _log = LoggerFactory.getLogger(Handler.class);

   public void handle(String target, Request request, HttpServletRequest hsr, HttpServletResponse response) throws IOException, ServletException {
      if (!"0:0:0:0:0:0:0:1".equals(request.getRemoteHost()) && !"127.0.0.1".equals(request.getRemoteHost())) {
         response.setContentType("text/html;charset=utf-8");
         PrintWriter writer = response.getWriter();
         writer.write("Please connect from localhost");
         writer.close();
      } else {
         response.setHeader("Access-Control-Allow-Origin", "*");
         response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST");
         response.setHeader("Access-Control-Allow-Headers", "Content-Type");
         if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(200);
            response.getWriter().close();
         } else {
            response.setContentType("text/plain;charset=utf-8");
            response.setCharacterEncoding("utf-8");

            try {
               byte var18 = -1;
               switch(target.hashCode()) {
               case -1705639268:
                  if (target.equals("/getTicketNoChallangeNumber")) {
                     var18 = 4;
                  }
                  break;
               case -1610977368:
                  if (target.equals("/https://localhost:9877/sign")) {
                     var18 = 3;
                  }
                  break;
               case -881821961:
                  if (target.equals("/https://localhost:9877/getTicket")) {
                     var18 = 1;
                  }
                  break;
               case -57839912:
                  if (target.equals("/https://localhost:9877/getTicketNoChallangeNumber")) {
                     var18 = 5;
                  }
                  break;
               case 46935660:
                  if (target.equals("/sign")) {
                     var18 = 2;
                  }
                  break;
               case 175854259:
                  if (target.equals("/getTicket")) {
                     var18 = 0;
                  }
               }

               String error2;
               String ticketNoChallangeNumber;
               PrintWriter writer;
               switch(var18) {
               case 0:
               case 1:
                  SCApplet appletForGetTicket;
                  String error;
                  do {
                     appletForGetTicket = new SCApplet();
                     appletForGetTicket.logIn("sr-Latn-CS", false);
                     error = appletForGetTicket.getErrDesc();
                     if (error.contains("Application terminated by user.")) {
                        appletForGetTicket.destroy();
                        _log.error(error);
                        response.sendError(500, error);
                        break;
                     }
                  } while(!appletForGetTicket.loggedIn());

                  if (appletForGetTicket.loggedIn()) {
                     ServletInputStream ticketStream = request.getInputStream();
                     error2 = appletForGetTicket.getTicket(getStringFromInputStream(ticketStream));
                     appletForGetTicket.logOut();
                     appletForGetTicket.destroy();
                     PrintWriter ticketWriter = response.getWriter();
                     response.setStatus(200);
                     ticketWriter.write(error2);
                     ticketWriter.close();
                  } else {
                     appletForGetTicket.destroy();
                     _log.error(error);
                     response.sendError(500, error);
                  }
                  break;
               case 2:
               case 3:
                  SCApplet appletForSign;
                  do {
                     appletForSign = new SCApplet();
                     appletForSign.logIn("sr-Latn-CS", false);
                     error2 = appletForSign.getErrDesc();
                     if (error2.contains("Application terminated by user.")) {
                        appletForSign.destroy();
                        response.sendError(500, error2);
                        break;
                     }
                  } while(!appletForSign.loggedIn());

                  if (appletForSign.loggedIn()) {
                     String data = request.getParameter("dataToSign");
                     ServletInputStream signStream = request.getInputStream();
                     ticketNoChallangeNumber = appletForSign.signXmlEnv(getStringFromInputStream(signStream), "", "");
                     appletForSign.logOut();
                     appletForSign.destroy();
                     writer = response.getWriter();
                     response.setStatus(200);
                     writer.write(ticketNoChallangeNumber);
                     writer.close();
                  } else {
                     appletForSign.destroy();
                     _log.error(error2);
                     response.sendError(500, error2);
                  }
                  break;
               case 4:
               case 5:
                  SCApplet appletForGetTicketNoChallangeNumber;
                  String error3;
                  do {
                     appletForGetTicketNoChallangeNumber = new SCApplet();
                     appletForGetTicketNoChallangeNumber.logIn("sr-Latn-CS", false);
                     error3 = appletForGetTicketNoChallangeNumber.getErrDesc();
                     if (error3.contains("Application terminated by user.")) {
                        appletForGetTicketNoChallangeNumber.destroy();
                        response.sendError(500, error3);
                        break;
                     }
                  } while(!appletForGetTicketNoChallangeNumber.loggedIn());

                  if (appletForGetTicketNoChallangeNumber.loggedIn()) {
                     ticketNoChallangeNumber = appletForGetTicketNoChallangeNumber.getTicket();
                     appletForGetTicketNoChallangeNumber.logOut();
                     appletForGetTicketNoChallangeNumber.destroy();
                     writer = response.getWriter();
                     response.setStatus(200);
                     writer.write(ticketNoChallangeNumber);
                     writer.close();
                  } else {
                     appletForGetTicketNoChallangeNumber.destroy();
                     _log.error(error3);
                     response.sendError(500, error3);
                  }
                  break;
               default:
                  ticketNoChallangeNumber = String.format("Method %s is not supported!", target);
                  _log.error(ticketNoChallangeNumber);
               }
            } catch (Exception var16) {
               Exception ex = var16;
               _log.error(var16.getLocalizedMessage().toUpperCase());

               try {
                  response.sendError(500);
                  response.setContentType("text/plain;charset=utf-8");
                  PrintWriter writer = response.getWriter();
                  ex.printStackTrace(writer);
                  writer.close();
               } catch (IOException var15) {
                  _log.error(var15.getLocalizedMessage().toUpperCase());
               }
            }

         }
      }
   }

   private static String getStringFromInputStream(InputStream is) throws IOException {
      BufferedReader br = null;
      StringBuilder sb = new StringBuilder();

      try {
         br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

         String line;
         while((line = br.readLine()) != null) {
            sb.append(line);
         }
      } catch (IOException var13) {
         _log.error(var13.getLocalizedMessage().toUpperCase());
      } finally {
         if (br != null) {
            try {
               br.close();
            } catch (IOException var12) {
               _log.error(var12.getLocalizedMessage().toUpperCase());
            }
         }

      }

      return sb.toString();
   }
}
