package com.cjs.qa.ym.xml.objects;

import com.cjs.qa.core.Environment;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.FSO;
import com.cjs.qa.utilities.XML;
import java.io.File;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;

public class Marshall {
  private final String filePathNameXML = "C:\\Workspace\\Data\\Vivit\\Data\\Events\\temp.xml";
  private final String filePathNameNewXML = "C:\\Workspace\\Data\\Vivit\\Data\\Events\\tempNew.xml";

  @Test
  public void testAll() throws JAXBException {
    testEventsAllSearch();
    testEventsEventsGet();
    testSaEventsEventRegistrationGet();
  }

  @Test
  public void testEventsAllSearch() throws JAXBException {
    String filePathName = "C:\\Workspace\\Data\\Vivit\\Data\\Events\\101.xml";
    YourMembershipResponse yourMembershipResponse =
        yourMembershipResponseUnmarshall(filePathName);
    Environment.sysOut(yourMembershipResponse.toString());
    Environment.sysOut(yourMembershipResponse.getEventsAllSearch().toString());
    Environment.sysOut(yourMembershipResponse.getEventsAllSearch().getResults().toString());
    for (int eventsIndex = 0;
        eventsIndex < yourMembershipResponse.getEventsAllSearch().getResults().getItem().size();
        eventsIndex++) {
      Item item =
          yourMembershipResponse.getEventsAllSearch().getResults().getItem().get(eventsIndex);
      Environment.sysOut("Item Index (" + eventsIndex + ")");
      Environment.sysOut(item.toString());
    }
    yourMembershipResponseMarshall(yourMembershipResponse, filePathNameNewXML);
  }

  @Test
  public void testEventsEventsGet() throws JAXBException {
    String filePathName = "C:\\Workspace\\Data\\Vivit\\Data\\Events\\EventInformation\\1209865.xml";
    YourMembershipResponse yourMembershipResponse =
        yourMembershipResponseUnmarshall(filePathName);
    Environment.sysOut(yourMembershipResponse.toString());
    Environment.sysOut(yourMembershipResponse.getEventsEventGet().toString());
    yourMembershipResponseMarshall(yourMembershipResponse, filePathNameNewXML);
  }

  @Test
  public void testSaEventsEventRegistrationGet() throws JAXBException {
    String filePathName =
        "C:\\Workspace\\Data\\Vivit\\Data\\Events\\Registration\\49170B25-A49F-46F7-98CB-3DA7042D4BB1.xml";
    YourMembershipResponse yourMembershipResponse =
        yourMembershipResponseUnmarshall(filePathName);
    Environment.sysOut(yourMembershipResponse.getSaEventsEventRegistrationGet().toString());
    yourMembershipResponseMarshall(yourMembershipResponse, filePathNameNewXML);
    Environment.sysOut("TESTING XML CREATION" + Constants.NEWLINE + xml);
    System.out.println(
        "Getting the Phone Number:"
            + yourMembershipResponse
                .getSaEventsEventRegistrationGet()
                .getDataSet()
                .getPhone()
                .getValue());
  }

  public YourMembershipResponse yourMembershipResponseUnmarshall(String filePathName)
      throws JAXBException {
    createFixedXMLFile(filePathName);
    JAXBContext jaxbContext = JAXBContext.newInstance(YourMembershipResponse.class);
    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    File file = new File(filePathNameXML);
    YourMembershipResponse yourMembershipResponse =
        (YourMembershipResponse) unmarshaller.unmarshal(file);
    return yourMembershipResponse;
  }

  public String yourMembershipResponseMarshall(
      YourMembershipResponse yourMembershipResponse, String filePathName) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(YourMembershipResponse.class);
    Marshaller marshaller = jaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    String xml = null;
    if (filePathName != null) {
      marshaller.marshal(yourMembershipResponse, new File(filePathName));
    }
    //        xml = FSO.fileReadAll(filePathName);
    //        marshaller.marshal(yourMembershipResponse, System.out);
    StringWriter stringWriter = new StringWriter();
    marshaller.marshal(yourMembershipResponse, stringWriter);
    xml = stringWriter.toString();
    return xml;
  }

  private void createFixedXMLFile(String filePathName) {
    String fixedXML = FSO.fileReadAll(filePathName);
    fixedXML = fixedXML.replace("Cp1252", XML.ENCODING);
    fixedXML = fixedXML.replace(XML.HEADING_INFO, XML.HEADING_INFO + Constants.NEWLINE);
    FSO.fileWrite(filePathNameXML, fixedXML, false);
  }
}
