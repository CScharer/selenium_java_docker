package com.cjs.qa.core.api;

import com.cjs.qa.bts.policy.Policy;
import com.cjs.qa.core.Environment;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.FSO;
import com.cjs.qa.utilities.IExtension;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.CDATASection;

public class WebService {
  SOAPConnectionFactory soapConnectionFactory = null;
  SOAPConnection soapConnection = null;
  String url = null;
  public static String xml = null;
  public static String requestName = null;
  public static String responseName = null;
  public static String fileRequest = Constants.PATH_FILES_XML + "request" + IExtension.XML;
  public static String fileResponse = Constants.PATH_FILES_XML + "response" + IExtension.XML;

  public WebService() {
    try {
      soapConnectionFactory = SOAPConnectionFactory.newInstance();
      soapConnection = soapConnectionFactory.createConnection();
      url = null;
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
  }

  public static Map<String, String> getAPIJSONResponse(
      String requestMethod, String url, String apiRequest) {
    final Map<String, String> map = new HashMap<>();
    map.put("requestMethod", requestMethod);
    map.put("url", url);
    map.put("apiRequest", apiRequest);
    if (Environment.isLogAPI()) {
      Environment.sysOut(
          "getAPIJSONResponse Parameters:"
              + Constants.NL
              + Constants.TAB
              + "requestMethod:["
              + requestMethod
              + "]"
              + Constants.NL
              + Constants.TAB
              + "url:["
              + url
              + "]"
              + Constants.NL
              + Constants.TAB
              + "apiRequest["
              + apiRequest
              + "]");
    }
    int responseCode = -1;
    HttpURLConnection httpURLConnection = null;
    String json = "";
    String line = "";
    try {
      final URL oURL = new URL(url);
      httpURLConnection = (HttpURLConnection) oURL.openConnection();
      httpURLConnection.setDoOutput(true);
      httpURLConnection.setInstanceFollowRedirects(false);
      if (!requestMethod.equals("")) {
        httpURLConnection.setRequestMethod(requestMethod);
      }
      httpURLConnection.setRequestProperty("Content-Type", "application/json");
      httpURLConnection.setRequestProperty("Accept", "application/json");
      httpURLConnection.setRequestProperty("charset", StandardCharsets.UTF_8.toString());
      httpURLConnection.setRequestProperty(
          "Content-Length", "" + Integer.toString(apiRequest.getBytes().length));
      httpURLConnection.setUseCaches(false);
      final DataOutputStream dataOutputStream =
          new DataOutputStream(httpURLConnection.getOutputStream());
      dataOutputStream.writeBytes(apiRequest);
      dataOutputStream.flush();
      responseCode = httpURLConnection.getResponseCode();
      map.put("responseCode", String.valueOf(responseCode));
      final String responseMessage = String.valueOf(httpURLConnection.getResponseMessage());
      map.put("responseMessage", responseMessage);
      if (responseCode == HttpURLConnection.HTTP_OK) {
        final BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        while ((line = bufferedReader.readLine()) != null) {
          json += line;
        }
        bufferedReader.close();
        map.put("json", json);
      }
      dataOutputStream.close();
      httpURLConnection.disconnect();
      if (responseCode != HttpURLConnection.HTTP_OK) {
        Environment.sysOut("responseCode:[" + responseCode + "]");
        Environment.sysOut("responseMessage:[" + responseMessage + "]");
      }
      dataOutputStream.close();
      httpURLConnection.disconnect();
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
    // if (!json.contains(APIConstants.API_JSON_SUCCESS))
    // {
    // Assert.fail(json);
    // }
    return map;
  }

  public static String getAPIJSONResponse(
      String string, String requestURL, String apiRequest, String ssoUserTokenId) {
    // TODO Auto-generated method stub
    return null;
  }

  public static String getAPIXMLRequest(Policy policy, SOAPMessage soapMessage) {
    try {
      xml = getSOAPMessageValue(soapMessage, false);
      fileRequest = Constants.PATH_FILES_XML + policy.policy + "request" + IExtension.XML;
      final String fileTemp =
          fileRequest.replace(
              "request",
              policy.dateTimeStamp
                  + "_"
                  + Environment.getComputerName()
                  + "_"
                  + Environment.CURRENT_USER
                  + "_"
                  + policy.policy
                  + "_"
                  + "request");
      // FSO.fileWrite(fileRequest, xml, false);
      FSO.fileWrite(fileTemp, xml, false);
      // requestName = XML.getBodyFirstChildName(fileRequest);
      // requestName = XML.getBodyFirstChildName(fileTemp);
      final String fileOut =
          fileRequest.replace(
              "request",
              policy.dateTimeStamp
                  + "_"
                  + policy.computerName
                  + "_"
                  + policy.userName
                  + "_"
                  + policy.policy
                  + "_"
                  + requestName
                  + "_request");
      FSO.fileWrite(fileOut, xml, false);
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
    return xml;
  }

  public static Map<String, String> getAPIXMLResponse(String url, String apiRequest) {
    final Map<String, String> map = new HashMap<>();
    try {
      final SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
      final SOAPConnection soapConnection = soapConnectionFactory.createConnection();
      final SOAPMessage soapMessage = soapConnection.call(createSOAPRequest(url), url);
      // Process the SOAP Response
      getSOAPMessageValue(soapMessage, false);
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
    return map;
  }

  public static String getAPIXMLResponse(Policy policy, String url, SOAPMessage soapMessage) {
    getAPIXMLRequest(policy, soapMessage);
    xml = null;
    try {
      writeSOAPMessageToOutputStream(soapMessage);
      final SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
      final SOAPConnection soapConnection = soapConnectionFactory.createConnection();
      final SOAPMessage soapMessageResponse = soapConnection.call(soapMessage, url);
      // Process the SOAP Response
      xml = getSOAPMessageValue(soapMessageResponse, false);
      fileResponse = Constants.PATH_FILES_XML + policy.policy + "response" + IExtension.XML;
      final String fileTemp =
          fileResponse.replace(
              "response",
              policy.dateTimeStamp
                  + "_"
                  + Environment.getComputerName()
                  + "_"
                  + Environment.CURRENT_USER
                  + "_"
                  + policy.policy
                  + "_"
                  + "response");
      // FSO.fileWrite(fileResponse, xml, false);
      FSO.fileWrite(fileTemp, xml, false);
      // responseName = XML.getBodyFirstChildName(fileResponse);
      // responseName = XML.getBodyFirstChildName(fileTemp);
      final String fileOut =
          fileResponse.replace(
              "response",
              policy.dateTimeStamp
                  + "_"
                  + Environment.getComputerName()
                  + "_"
                  + Environment.CURRENT_USER
                  + "_"
                  + policy.policy
                  + "_"
                  + responseName
                  + "_response");
      FSO.fileWrite(fileOut, xml, false);
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
    return xml;
  }

  public static Map<String, String> getAPIXMLResponse(
      String requestMethod, String url, String apiRequest) {
    final Map<String, String> map = new HashMap<>();
    map.put("requestMethod", requestMethod);
    map.put("url", url);
    map.put("apiRequest", apiRequest);
    if (Environment.isLogAPI()) {
      Environment.sysOut(
          "getAPIXMLResponse Parameters:"
              + Constants.NL
              + Constants.TAB
              + "requestMethod:["
              + requestMethod
              + "]"
              + Constants.NL
              + Constants.TAB
              + "url:["
              + url
              + "]"
              + Constants.NL
              + Constants.TAB
              + "apiRequest["
              + apiRequest
              + "]");
    }
    int responseCode = -1;
    HttpURLConnection httpURLConnection = null;
    String xml = "";
    String line = "";
    try {
      final URL oURL = new URL(url);
      httpURLConnection = (HttpURLConnection) oURL.openConnection();
      httpURLConnection.setDoOutput(true);
      httpURLConnection.setInstanceFollowRedirects(false);
      if (!requestMethod.equals("")) {
        httpURLConnection.setRequestMethod(requestMethod);
      }
      // httpURLConnection.setRequestProperty("Content-Type",
      // "text/xml");
      httpURLConnection.setRequestProperty("Accept", "application/xml");
      httpURLConnection.setRequestProperty("charset", StandardCharsets.UTF_8.toString());
      httpURLConnection.setRequestProperty(
          "Content-Length", "" + Integer.toString(apiRequest.getBytes().length));
      httpURLConnection.setUseCaches(false);
      final DataOutputStream dataOutputStream =
          new DataOutputStream(httpURLConnection.getOutputStream());
      dataOutputStream.writeBytes(apiRequest);
      dataOutputStream.flush();
      responseCode = httpURLConnection.getResponseCode();
      map.put("responseCode", String.valueOf(responseCode));
      final String responseMessage = String.valueOf(httpURLConnection.getResponseMessage());
      map.put("responseMessage", responseMessage);
      if (responseCode == HttpURLConnection.HTTP_OK) {
        final BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        while ((line = bufferedReader.readLine()) != null) {
          xml += line;
        }
        bufferedReader.close();
        map.put("xml", xml);
      }
      dataOutputStream.close();
      httpURLConnection.disconnect();
      if (responseCode != HttpURLConnection.HTTP_OK) {
        Environment.sysOut("responseCode:[" + responseCode + "]");
        Environment.sysOut("responseMessage:[" + responseMessage + "]");
      }
      dataOutputStream.close();
      httpURLConnection.disconnect();
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
    // if (responseCode != HttpURLConnection.HTTP_OK)
    // {
    // Assert.fail(xml);
    // }
    return map;
  }

  public static void writeSOAPMessageToOutputStream(SOAPMessage soapMessage) {
    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      soapMessage.writeTo(byteArrayOutputStream);
      final String soapMessageString = byteArrayOutputStream.toString();
      Environment.sysOut(getSOAPMessageValue(soapMessage, true));
    } catch (SOAPException | IOException e) {
      Environment.sysOut(e);
    }
  }

  public SOAPMessage createSOAPRequestFromString(String apiRequest) {
    SOAPMessage soapMessage = null;
    try {
      final InputStream is = new ByteArrayInputStream(apiRequest.getBytes());
      soapMessage = MessageFactory.newInstance().createMessage(null, is);
      soapMessage.removeAllAttachments();
      final SOAPPart part = soapMessage.getSOAPPart();
      part.detachNode();
      final SOAPEnvelope env = part.getEnvelope();
      env.detachNode();
      final SOAPBody soapBody = env.getBody();
      soapBody.detachNode();
      // SOAPHeader head = env.getHeader();
      // head.detachNode();
      // writeSOAPMessageToOutputStream(soapMessage);
      soapMessage.saveChanges();
      /* Print the request message */
      System.out.print("Request SOAP Message = ");
      // writeSOAPMessageToOutputStream(soapMessage);
      System.out.println();
      return soapMessage;
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
    return soapMessage;
  }

  public static SOAPMessage createSOAPRequest(String url) {
    SOAPMessage soapMessage = null;
    try {
      final MessageFactory messageFactory = MessageFactory.newInstance();
      soapMessage = messageFactory.createMessage();
      final SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
      soapEnvelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");
      soapEnvelope.setPrefix("soapenv");
      soapEnvelope.setAttribute("xmlns:com", url);
      final SOAPBody soapBody = soapEnvelope.getBody();
      soapBody.setPrefix("soapenv");
      final SOAPElement soapElement1 =
          soapBody.addChildElement("getPolicyPremiumSummaryXML", "com", url);
      // soapElement1.setPrefix("com");
      final SOAPElement soapElement2 = soapElement1.addChildElement("basicXML", "com", url);
      final CDATASection cdata =
          soapElement2
              .getOwnerDocument()
              .createCDATASection(
                  "<TFGPSBasicXMLDO><!--<companyNumber>069</companyNumber>-->"
                      + "<policyNumber>6022713</policyNumber><policyTermNumber>20</policyTermNumber>"
                      + "<!--<tranEffectiveDate>03/01/2018</tranEffectiveDate>--></TFGPSBasicXMLDO>");
      soapElement2.appendChild(cdata);
      // soapElement2.addTextNode(" <![CDATA[");
      // final SOAPElement soapElement3 =
      // soapElement2.addChildElement("TFGPSBasicXMLDO");
      // final SOAPElement soapElement4 =
      // soapElement3.addChildElement("companyNumber");
      // soapElement4.setValue("069");
      // final SOAPElement soapElement6 =
      // soapElement3.addChildElement("policyNumber");
      // soapElement6.setValue("6022713");
      // final SOAPElement soapElement7 =
      // soapElement3.addChildElement("policyTermNumber");
      // soapElement7.setValue("20");
      //// final SOAPElement soapElement8 =
      // soapElement3.addChildElement("tranEffectiveDate");
      //// soapElement8.setValue("");
      // soapElement2.addTextNode("]]>");
      // If next line is removed or commented out or the SOAPMessage
      // will be null.
      // writeSOAPMessageToOutputStream(soapMessage);
      // Environment.sysOut(Constants.NL + "Soap Request: " + Constants.NL
      // + Constants.NL + Constants.NL + getMsgAsString(soapMessage));
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
    return soapMessage;
  }

  public static String xmlReplaceSpecialCharacters(String xml, boolean revert) {
    final Map<String, String> characters = new HashMap<>();
    characters.put("&lt;", "");
    characters.put("&lt;", "<"); // less than
    characters.put("&gt;", ">"); // greater than
    characters.put("&amp;", "&"); // ampersand
    characters.put("&apos;", "'"); // apostrophe
    characters.put("&quot;", Constants.QUOTE_DOUBLE); // quotation mark
    characters.put("&#xD;", ""); // &#xD;
    for (final String character : characters.keySet()) {
      String replacee = null;
      String replacer = null;
      if (!revert) {
        replacee = character;
        replacer = characters.get(character);
      } else {
        replacee = characters.get(character);
        replacer = character;
      }
      if (xml.contains(replacee)) {
        xml = xml.replaceAll(replacee, replacer);
      }
    }
    return xml;
  }

  public static String getSOAPMessageValue(SOAPMessage soapMessage, boolean maskPassword) {
    xml = null;
    try {
      final TransformerFactory transformerFactory = TransformerFactory.newInstance();
      final Transformer transformer = transformerFactory.newTransformer();
      // transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      // transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
      // "2");
      final Source source = soapMessage.getSOAPPart().getContent();
      final StringWriter stringWriter = new StringWriter();
      // final StreamResult streamResult = new
      // StreamResult(System.out);
      final StreamResult streamResult = new StreamResult(stringWriter);
      transformer.transform(source, streamResult);
      final StringBuffer stringBuffer = stringWriter.getBuffer();
      xml = stringBuffer.toString();
      xml = xmlReplaceSpecialCharacters(xml, false);
      if (maskPassword) {
        String prePW = "<password>";
        String postPW = "</password>";
        if (xml.contains(prePW)) {
          final String prePassword = xml.substring(0, (xml.indexOf(prePW) + prePW.length()));
          final String postPassword = xml.substring(xml.indexOf(postPW), xml.length());
          xml = prePassword + "**********" + postPassword;
        }
        prePW = "<fnetPassword>";
        postPW = "</fnetPassword>";
        if (xml.contains(prePW)) {
          final String prePassword = xml.substring(0, (xml.indexOf(prePW) + prePW.length()));
          final String postPassword = xml.substring(xml.indexOf(postPW), xml.length());
          xml = prePassword + "**********" + postPassword;
        }
      }
      // Environment.sysOut("response:[" + xml + "]");
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
    return xml;
  }
}
