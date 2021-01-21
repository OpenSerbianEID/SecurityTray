package Crypto;

import Utils.Base64Utils;
import Utils.XMLDocUtils;
import com.sun.org.apache.xml.internal.security.Init;
import iaik.pkcs.pkcs11.Mechanism;
import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.objects.RSAPrivateKey;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureProperties;
import javax.xml.crypto.dsig.SignatureProperty;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class CryptoFunctions {
   static SessionContext sesija;
   static final Logger _log;

   static String signEnveloped(Session session, RSAPrivateKey signatureKey, String cATR, String xmlIn, String signode, String signerId, X509Certificate signingCertificate, PrivateKey privateKey, boolean pkcs11, Boolean WatchDataToken) throws Exception {
      Document doc = XMLDocUtils.stringToDocument(xmlIn);
      doc = signEnveloped(session, signatureKey, cATR, doc, signode, signerId, signingCertificate, privateKey, false, pkcs11, WatchDataToken);
      return XMLDocUtils.documentToString(doc);
   }

   static Document signEnveloped(Session session, RSAPrivateKey signatureKey, String cATR, Document doc, String signode, String signerId, X509Certificate signingCertificate, PrivateKey privateKey, boolean ticket, boolean pkcs11, Boolean WatchDataToken) throws Exception {
      signode = "";
      XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
      List r = new ArrayList();
      List o = null;
      KeyInfo ki = null;
      Element timeStamp;
      Element signatureValueElement;
      if (!ticket) {
         KeyInfoFactory kif = fac.getKeyInfoFactory();
         X509Data x509data = kif.newX509Data(Collections.singletonList(signingCertificate));
         ki = kif.newKeyInfo(Collections.singletonList(x509data));
         Date now = Calendar.getInstance().getTime();
         String ID_FORMAT = "yyyy-MM-dd HH:mm:ss";
         SimpleDateFormat sif = new SimpleDateFormat(ID_FORMAT);
         String idTime = sif.format(now);
         timeStamp = doc.createElement("TimeStamp");
         timeStamp.appendChild(doc.createTextNode(idTime));
         timeStamp.setAttribute("xmlns", "http://www.saga.sr/#signatureProperties");
         SignatureProperty p1 = fac.newSignatureProperty(Collections.singletonList(new DOMStructure(timeStamp)), "#" + signerId, (String)null);
         signatureValueElement = doc.createElement("CertificateSerialNumber");
         signatureValueElement.appendChild(doc.createTextNode(String.format("%08X", signingCertificate.getSerialNumber())));
         signatureValueElement.setAttribute("xmlns", "http://www.saga.sr/#signatureProperties");
         SignatureProperty p2 = fac.newSignatureProperty(Collections.singletonList(new DOMStructure(signatureValueElement)), "#" + signerId, (String)null);
         Element signedBy = doc.createElement("SignedBy");
         signedBy.appendChild(doc.createTextNode(signingCertificate.getSubjectDN().toString()));
         signedBy.setAttribute("xmlns", "http://www.saga.sr/#signatureProperties");
         SignatureProperty p3 = fac.newSignatureProperty(Collections.singletonList(new DOMStructure(signedBy)), "#" + signerId, (String)null);
         List l = new ArrayList();
         l.add(p1);
         l.add(p2);
         l.add(p3);
         Random rnd = new Random();
         rnd.setSeed(Calendar.getInstance().getTimeInMillis());
         int ln = rnd.nextInt();
         String PropId = "SignatureProperties_" + String.format("%08X", ln);
         SignatureProperties properties = fac.newSignatureProperties(l, PropId);
         XMLObject object = fac.newXMLObject(Collections.singletonList(properties), (String)null, (String)null, (String)null);
         o = Collections.singletonList(object);
         Reference ref1 = fac.newReference("#" + PropId, fac.newDigestMethod("http://www.w3.org/2000/09/xmldsig#sha1", (DigestMethodParameterSpec)null), Collections.singletonList(fac.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature", (TransformParameterSpec)null)), "http://www.w3.org/2000/09/xmldsig#SignatureProperties", (String)null);
         r.add(ref1);
      }

      if (!signode.equals("")) {
         signode = "#" + signode;
      }

      Reference ref2 = fac.newReference(signode, fac.newDigestMethod("http://www.w3.org/2000/09/xmldsig#sha1", (DigestMethodParameterSpec)null), Collections.singletonList(fac.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature", (TransformParameterSpec)null)), (String)null, (String)null);
      r.add(ref2);
      SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod("http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments", (C14NMethodParameterSpec)null), fac.newSignatureMethod("http://www.w3.org/2000/09/xmldsig#rsa-sha1", (SignatureMethodParameterSpec)null), r);
      if (signerId != null && signerId.equals("")) {
         signerId = null;
      }

      XMLSignature signature = fac.newXMLSignature(si, ki, o, signerId, (String)null);
      DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());
      signature.sign(dsc);
      if (!SessionContext.isCD) {
         InputStream is = signature.getSignedInfo().getCanonicalizedData();
         byte[] bcan = new byte[is.available()];
         is.read(bcan);
         if (pkcs11) {
            timeStamp = null;

            try {
               session.signInit(Mechanism.get(6L), signatureKey);
               byte[] cardSign = session.sign(bcan);
               NodeList nl = doc.getElementsByTagName("SignatureValue");
               signatureValueElement = (Element)nl.item(nl.getLength() - 1);
               signatureValueElement.setTextContent(Base64Utils.base64Encode(cardSign));
            } catch (Throwable var35) {
               _log.warn("Pokusao sa CKM_SHA1_RSA_PKCS i dogodila se sledeca greska: " + var35.getMessage());
               MessageDigest md = MessageDigest.getInstance("SHA-1");
               byte[] hash = md.digest(bcan);

               try {
                  session.signInit(Mechanism.get(1L), signatureKey);
                  byte[] cardSign1 = session.sign(hash);
                  NodeList nl = doc.getElementsByTagName("SignatureValue");
                  signatureValueElement = (Element)nl.item(nl.getLength() - 1);
                  signatureValueElement.setTextContent(Base64Utils.base64Encode(cardSign1));
               } catch (Throwable var34) {
                  _log.error(var34.getMessage());
                  String errorMessage;
                  if (var34.getMessage().equalsIgnoreCase("CKR_SESSION_HANDLE_INVALID")) {
                     sesija.logOut();
                     errorMessage = ResourceBundle.getBundle("UI/Bundle").getString("_-_THE_SMART_CARD_IS_NOT_INSERTED.\\N");
                     throw new Exception(errorMessage);
                  }

                  if (var34.getMessage().equalsIgnoreCase("CKR_OBJECT_HANDLE_INVALID")) {
                     sesija.logOut();
                     errorMessage = ResourceBundle.getBundle("UI/Bundle").getString(var35.getMessage());
                     throw new Exception(errorMessage);
                  }
               }
            }
         }
      }

      return doc;
   }

   static boolean verifyEnveloped(String xmlIn, X509Certificate serverCertificate) throws Exception {
      if (serverCertificate == null) {
         throw new Exception("Can't find server certificate.");
      } else {
         X509Certificate signerCertificate = getSignerCertificate(xmlIn);
         Document doc = XMLDocUtils.stringToDocument(xmlIn);
         return verifyEnveloped(doc, signerCertificate);
      }
   }

   static boolean verifyEnveloped(Document doc, X509Certificate signerCertificate) throws Exception {
      NodeList nl = doc.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature");
      if (nl.getLength() == 0) {
         throw new Exception("Cannot find Signature element");
      } else {
         XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
         DOMValidateContext valContext = new DOMValidateContext(signerCertificate.getPublicKey(), nl.item(0));
         XMLSignature signature = fac.unmarshalXMLSignature(valContext);
         boolean coreValidity = signature.validate(valContext);
         if (!coreValidity) {
            System.err.println("Signature failed core validation");
            boolean sv = signature.getSignatureValue().validate(valContext);
            System.out.println("signature validation status: " + sv);
            Iterator i = signature.getSignedInfo().getReferences().iterator();

            for(int j = 0; i.hasNext(); ++j) {
               boolean refValid = ((Reference)i.next()).validate(valContext);
               System.out.println("ref[" + j + "] validity status: " + refValid);
            }
         } else {
            System.out.println("Signature passed core validation");
         }

         return coreValidity;
      }
   }

   static X509Certificate getSignerCertificate(String ticket) throws Exception {
      Document doc = XMLDocUtils.stringToDocument(ticket);
      Node AC = doc.getElementsByTagName("X509Certificate").item(0);
      CertificateFactory cf = CertificateFactory.getInstance("X.509");
      InputStream is = new ByteArrayInputStream(Base64Utils.base64Decode(AC.getTextContent().replaceAll("\\n", "")));
      return (X509Certificate)cf.generateCertificate(is);
   }

   static {
      Init.init();
      _log = LoggerFactory.getLogger(CryptoFunctions.class);
   }
}
