package saga.tray;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JettyServerStarter {
   Handler hendler = new Handler();
   Server server = new Server();
   static final Logger _log = LoggerFactory.getLogger(JettyServerStarter.class);

   public JettyServerStarter() throws IOException {
      HttpConfiguration http_config = new HttpConfiguration();
      http_config.setSecureScheme("https");
      http_config.setSecurePort(9877);
      http_config.setOutputBufferSize(32768);
      http_config.setRequestHeaderSize(8192);
      http_config.setResponseHeaderSize(8192);
      http_config.setSendServerVersion(true);
      http_config.setSendDateHeader(false);
      Properties p = new Properties();
      p.load(new FileInputStream(System.getProperty("user.dir") + "//lib//SSLKeyStore.ini"));
      SslContextFactory sslContextFactory = new SslContextFactory();
      sslContextFactory.setKeyStoreType("JKS");
      sslContextFactory.setKeyStorePath(System.getProperty("user.dir") + "//lib//SSLKeyStore.jks");
      sslContextFactory.setKeyStorePassword(p.getProperty("PASSWORD"));
      sslContextFactory.setKeyManagerPassword(p.getProperty("PASSWORD"));
      sslContextFactory.setTrustStorePath(System.getProperty("user.dir") + "//lib//SSLKeyStore.jks");
      sslContextFactory.setTrustStorePassword(p.getProperty("PASSWORD"));
      sslContextFactory.setExcludeCipherSuites(new String[]{"^.*_(MD5|SHA|SHA1)$"});
      HttpConfiguration https_config = new HttpConfiguration(http_config);
      https_config.addCustomizer(new SecureRequestCustomizer());
      ServerConnector sslConnector = new ServerConnector(this.server, new ConnectionFactory[]{new SslConnectionFactory(sslContextFactory, "http/1.1"), new HttpConnectionFactory(https_config)});
      sslConnector.setPort(9877);
      sslConnector.setHost("localhost");
      this.server.addConnector(sslConnector);
      this.server.setStopAtShutdown(true);
   }

   void start() throws InterruptedException, Exception {
      if (!this.server.isRunning()) {
         this.server.setHandler(this.hendler);
         this.server.start();
      }

   }

   void stop() throws InterruptedException, Exception {
      this.server.stop();
   }
}
