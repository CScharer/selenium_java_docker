package com.cjs.qa.ym.api.dataobjects;

import com.cjs.qa.core.QAException;
import com.cjs.qa.soap.SOAP;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import java.util.Iterator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.w3c.dom.Node;

public class UnmarshallYourMembership_Response {
  public YourMembership_Response getFromFile(String fileName) throws QAException {
    final SOAP soap = new SOAP();
    SOAPMessage soapMessage;
    try {
      soapMessage = soap.getSoapMessageFromFile(fileName);
    } catch (Exception e) {
      throw new QAException("Error Getting XML From File.", e);
    }
    return get(soapMessage);
  }

  public YourMembership_Response getFromString(String xml) throws QAException {
    final SOAP soap = new SOAP();
    SOAPMessage soapMessage;
    try {
      soapMessage = soap.getSoapMessageFromString(xml);
    } catch (Exception e) {
      throw new QAException("Error Getting XML From String.", e);
    }
    return get(soapMessage);
  }

  @SuppressWarnings("unchecked")
  private YourMembership_Response get(SOAPMessage soapMessage) throws QAException {
    YourMembership_Response yourMembership_Response = null;
    try {
      for (final Iterator<jakarta.xml.soap.Node> bodyIterator =
              soapMessage.getSOAPBody().getChildElements();
          bodyIterator.hasNext(); ) {
        final SOAPElement soapBody = (SOAPElement) bodyIterator.next();
        for (final Iterator<jakarta.xml.soap.Node> dataIterator = soapBody.getChildElements();
            dataIterator.hasNext(); ) {
          final SOAPElement dataElement = (SOAPElement) dataIterator.next();
          final Node node = dataElement.getFirstChild();
          JAXBContext context;
          try {
            context = JAXBContext.newInstance(YourMembership_Response.class);
          } catch (final JAXBException e) {
            throw new QAException(
                "Error Creating (context ="
                    + " JAXBContext.newInstance(YourMembership_Response.class);).",
                e);
          }
          Unmarshaller unmarshaller;
          try {
            unmarshaller = context.createUnmarshaller();
          } catch (final JAXBException e) {
            throw new QAException(
                "Error Creating (unmarshaller = context.createUnmarshaller();).", e);
          }
          try {
            yourMembership_Response = (YourMembership_Response) unmarshaller.unmarshal(node);
          } catch (final JAXBException e) {
            throw new QAException(
                "Error Creating (yourMembership_Response ="
                    + " (YourMembership_Response) unmarshaller.unmarshal(node);).",
                e);
          }
        }
      }
    } catch (final SOAPException e) {
      throw new QAException(
          "Error In (for (final Iterator<SOAPElement> bodyIterator ="
              + " soapMessage.getSOAPBody().getChildElements();"
              + " bodyIterator.hasNext();)).",
          e);
    }
    return yourMembership_Response;
  }
}
