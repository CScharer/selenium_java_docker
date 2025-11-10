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
  private AuthNamespace authNamespace;
  private ConvertNamespace convertNamespace;
  private EventsNamespace eventsNamespace;
  private FeedsNamespace feedsNamespace;
  private MemberNamespace memberNamespace;
  private MembersNamespace membersNamespace;
  private PeopleNamespace peopleNamespace;
  private Sa_AuthNamespace saAuthNamespace;
  private Sa_CertificationsNamespace saCertificationsNamespace;
  private Sa_CommerceNamespace saCommerceNamespace;
  private Sa_EventsNamespace saEventsNamespace;
  private Sa_ExportNamespace saExportNamespace;
  private Sa_FinanceNamespace saFinanceNamespace;
  private Sa_GroupsNamespace saGroupsNamespace;
  private Sa_MemberNamespace saMemberNamespace;
  private Sa_MembersNamespace saMembersNamespace;
  private Sa_NonMembersNamespace saNonMembersNamespace;
  private Sa_PeopleNamespace saPeopleNamespace;
  private SessionNamespace sessionNamespace;

  public AuthNamespace getAuthNamespace() {
    return authNamespace;
  }

  public ConvertNamespace getConvertNamespace() {
    return convertNamespace;
  }

  public EventsNamespace getEventsNamespace() {
    return eventsNamespace;
  }

  public FeedsNamespace getFeedsNamespace() {
    return feedsNamespace;
  }

  public MemberNamespace getMemberNamespace() {
    return memberNamespace;
  }

  public MembersNamespace getMembersNamespace() {
    return membersNamespace;
  }

  public PeopleNamespace getPeopleNamespace() {
    return peopleNamespace;
  }

  public Sa_AuthNamespace getSaAuthNamespace() {
    return saAuthNamespace;
  }

  public Sa_CertificationsNamespace getSaCertificationsNamespace() {
    return saCertificationsNamespace;
  }

  public Sa_CommerceNamespace getSaCommerceNamespace() {
    return saCommerceNamespace;
  }

  public Sa_EventsNamespace getSaEventsNamespace() {
    return saEventsNamespace;
  }

  public Sa_ExportNamespace getSaExportNamespace() {
    return saExportNamespace;
  }

  public Sa_FinanceNamespace getSaFinanceNamespace() {
    return saFinanceNamespace;
  }

  public Sa_GroupsNamespace getSaGroupsNamespace() {
    return saGroupsNamespace;
  }

  public Sa_MemberNamespace getSaMemberNamespace() {
    return saMemberNamespace;
  }

  public Sa_MembersNamespace getSaMembersNamespace() {
    return saMembersNamespace;
  }

  public Sa_NonMembersNamespace getSaNonMembersNamespace() {
    return saNonMembersNamespace;
  }

  public Sa_PeopleNamespace getSaPeopleNamespace() {
    return saPeopleNamespace;
  }

  public SessionNamespace getSessionNamespace() {
    return sessionNamespace;
  }

  public YMAPI() throws Throwable {
    authNamespace = new AuthNamespace();
    convertNamespace = new ConvertNamespace();
    eventsNamespace = new EventsNamespace();
    feedsNamespace = new FeedsNamespace();
    memberNamespace = new MemberNamespace();
    membersNamespace = new MembersNamespace();
    peopleNamespace = new PeopleNamespace();
    saAuthNamespace = new Sa_AuthNamespace();
    saCertificationsNamespace = new Sa_CertificationsNamespace();
    saCommerceNamespace = new Sa_CommerceNamespace();
    saEventsNamespace = new Sa_EventsNamespace();
    saExportNamespace = new Sa_ExportNamespace();
    saFinanceNamespace = new Sa_FinanceNamespace();
    saGroupsNamespace = new Sa_GroupsNamespace();
    saMemberNamespace = new Sa_MemberNamespace();
    saMembersNamespace = new Sa_MembersNamespace();
    saNonMembersNamespace = new Sa_NonMembersNamespace();
    saPeopleNamespace = new Sa_PeopleNamespace();
    sessionNamespace = new SessionNamespace();
  }

  public static final String LABEL_CALL_METHOD_PREFIX = "<Call Method=";
  public static final String LABEL_CALL_METHOD_SUFFIX = "</Call>";
}
