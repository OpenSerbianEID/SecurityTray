package Utils;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLDocUtils {
   public static Document stringToDocument(String xmlIn) throws Exception {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      Document doc = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(xmlIn)));
      return doc;
   }

   public static String documentToString(Document doc) throws Exception {
      ByteArrayOutputStream res = new ByteArrayOutputStream();
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer trans = tf.newTransformer();
      trans.setOutputProperty("omit-xml-declaration", "yes");
      trans.transform(new DOMSource(doc), new StreamResult(res));
      return res.toString("UTF-8");
   }
}
