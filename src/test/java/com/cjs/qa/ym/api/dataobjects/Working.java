package com.cjs.qa.ym.api.dataobjects;

import com.cjs.qa.core.Environment;
import org.junit.Test;

public class Working extends Environment {
  @Test
  public void verification() throws Throwable {
    // YourMembershipResponse yourMembership_Response = new
    // UnmarshallEventResponse()
    // yourMembership_Response.getResults().getResultTotal();
    //
    // C:\Workspace\Data\Vivit\Data\20190316\YM\Events\1.xml
    String fileName = "C:\\Workspace\\Data\\Vivit\\Data\\20190316\\YM\\Events\\1.xml";
    final UnmarshallYourMembershipResponse unmarshallYourMembershipResponse =
        new UnmarshallYourMembershipResponse();
    final YourMembershipResponse yourMembership_Response =
        unmarshallYourMembershipResponse.getFromFile(fileName);
    sysOut(yourMembership_Response.getErrCode());
    Results results = yourMembership_Response.getResults();
    int eventCount = results.getResultTotal();
    sysOut("eventCount:[" + eventCount + "]");
  }
}
