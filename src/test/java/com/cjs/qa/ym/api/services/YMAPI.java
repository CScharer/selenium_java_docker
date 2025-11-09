package com.cjs.qa.ym.api.services;

import com.cjs.qa.ym.api.namespace.AuthNamespace;
import com.cjs.qa.ym.api.namespace.ConvertNamespace;
import com.cjs.qa.ym.api.namespace.EventsNamespace;
import com.cjs.qa.ym.api.namespace.FeedsNamespace;
import com.cjs.qa.ym.api.namespace.MemberNamespace;
import com.cjs.qa.ym.api.namespace.MembersNamespace;
import com.cjs.qa.ym.api.namespace.PeopleNamespace;
import com.cjs.qa.ym.api.namespace.Sa_AuthNamespace;
import com.cjs.qa.ym.api.namespace.Sa_CertificationsNamespace;
import com.cjs.qa.ym.api.namespace.Sa_CommerceNamespace;
import com.cjs.qa.ym.api.namespace.Sa_EventsNamespace;
import com.cjs.qa.ym.api.namespace.Sa_ExportNamespace;
import com.cjs.qa.ym.api.namespace.Sa_FinanceNamespace;
import com.cjs.qa.ym.api.namespace.Sa_GroupsNamespace;
import com.cjs.qa.ym.api.namespace.Sa_MemberNamespace;
import com.cjs.qa.ym.api.namespace.Sa_MembersNamespace;
import com.cjs.qa.ym.api.namespace.Sa_NonMembersNamespace;
import com.cjs.qa.ym.api.namespace.Sa_PeopleNamespace;
import com.cjs.qa.ym.api.namespace.SessionNamespace;

public class YMAPI {
  public YMAPI() throws Throwable {
    AuthNamespace = new AuthNamespace();
    ConvertNamespace = new ConvertNamespace();
    EventsNamespace = new EventsNamespace();
    FeedsNamespace = new FeedsNamespace();
    MemberNamespace = new MemberNamespace();
    MembersNamespace = new MembersNamespace();
    PeopleNamespace = new PeopleNamespace();
    Sa_AuthNamespace = new Sa_AuthNamespace();
    Sa_CertificationsNamespace = new Sa_CertificationsNamespace();
    Sa_CommerceNamespace = new Sa_CommerceNamespace();
    Sa_EventsNamespace = new Sa_EventsNamespace();
    Sa_ExportNamespace = new Sa_ExportNamespace();
    Sa_FinanceNamespace = new Sa_FinanceNamespace();
    Sa_GroupsNamespace = new Sa_GroupsNamespace();
    Sa_MemberNamespace = new Sa_MemberNamespace();
    Sa_MembersNamespace = new Sa_MembersNamespace();
    Sa_NonMembersNamespace = new Sa_NonMembersNamespace();
    Sa_PeopleNamespace = new Sa_PeopleNamespace();
    SessionNamespace = new SessionNamespace();
  }

  public AuthNamespace AuthNamespace;
  public ConvertNamespace ConvertNamespace;
  public EventsNamespace EventsNamespace;
  public FeedsNamespace FeedsNamespace;
  public MemberNamespace MemberNamespace;
  public MembersNamespace MembersNamespace;
  public PeopleNamespace PeopleNamespace;
  public Sa_AuthNamespace Sa_AuthNamespace;
  public Sa_CertificationsNamespace Sa_CertificationsNamespace;
  public Sa_CommerceNamespace Sa_CommerceNamespace;
  public Sa_EventsNamespace Sa_EventsNamespace;
  public Sa_ExportNamespace Sa_ExportNamespace;
  public Sa_FinanceNamespace Sa_FinanceNamespace;
  public Sa_GroupsNamespace Sa_GroupsNamespace;
  public Sa_MemberNamespace Sa_MemberNamespace;
  public Sa_MembersNamespace Sa_MembersNamespace;
  public Sa_NonMembersNamespace Sa_NonMembersNamespace;
  public Sa_PeopleNamespace Sa_PeopleNamespace;
  public SessionNamespace SessionNamespace;

  public static final String LABEL_CALL_METHOD_PREFIX = "<Call Method=";
  public static final String LABEL_CALL_METHOD_SUFFIX = "</Call>";
}
