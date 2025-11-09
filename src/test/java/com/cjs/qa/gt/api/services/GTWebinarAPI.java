package com.cjs.qa.gt.api.services;

import com.cjs.qa.gt.api.namespace.webinar.AuthNamespace;
import com.cjs.qa.gt.api.namespace.webinar.RegistrantsNamespace;
import com.cjs.qa.gt.api.namespace.webinar.WebinarsNamespace;

public class GTWebinarAPI {
  public GTWebinarAPI() throws Throwable {
    AuthNamespace = new AuthNamespace();
    WebinarsNamespace = new WebinarsNamespace();
    RegistrantsNamespace = new RegistrantsNamespace();
  }

  public AuthNamespace AuthNamespace;
  public WebinarsNamespace WebinarsNamespace;
  public RegistrantsNamespace RegistrantsNamespace;
}
