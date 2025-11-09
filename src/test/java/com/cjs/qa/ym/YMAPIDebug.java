package com.cjs.qa.ym;

import com.cjs.qa.core.Environment;
import com.cjs.qa.core.security.EPasswords;
import com.cjs.qa.utilities.CJSConstants;
import com.cjs.qa.utilities.DateHelpers;
import com.cjs.qa.vivit.VivitEnvironment;
import com.cjs.qa.ym.api.services.YMAPI;
import java.util.HashMap;
import java.util.Map;

public class YMAPIDebug {
  public YMAPIDebug() {}

  public void runTests() throws Throwable {
    final String dateTimeCurrent =
        DateHelpers.getCurrentDateTime(DateHelpers.FORMAT_US_STANDARD_DATE_TIME);
    final String ymInceptionDate = DateHelpers.getCurrentDateTime("2007-07-01 00:00:00");
    Environment.sysOut("dateTimeFormat:[" + DateHelpers.FORMAT_US_STANDARD_DATE_TIME + "]");
    Environment.sysOut("dateTimeCurrent:[" + dateTimeCurrent + "]");
    Environment.sysOut("ymInceptionDate:[" + ymInceptionDate + "]");
    final Map<String, String> mapCScharer = new HashMap<>();
    mapCScharer.put("userID", CJSConstants.USERID_VIVIT);
    mapCScharer.put("password", EPasswords.VIVIT.getValue());
    mapCScharer.put("webSiteMemberID", "10969660");
    mapCScharer.put("apiGUID", "9B22C96F-4EFF-4D73-A2E3-09BB462687E3");
    mapCScharer.put("passwordHash", "b2e2c25251a62b7c26ad52f50f285bd8c7dac60e");
    mapCScharer.put("emailAddress", CJSConstants.EMAIL_ADDRESS_VIVIT);
    mapCScharer.put("vivitURL", VivitEnvironment.URL_LOGIN);
    Environment.sysOut("mapCScharer:[" + mapCScharer + "]");
    // YMAPI Instantiate
    final YMAPI ymAPI = new YMAPI();
    Map<String, String> mapResults = new HashMap<>();
    Environment.sysOut("mapResults:]" + mapResults.toString() + "]");
    // Auth Namespace
    mapResults =
        ymAPI.AuthNamespace.authenticate(mapCScharer.get("userID"), mapCScharer.get("password"));
    mapResults =
        ymAPI.AuthNamespace.createToken(
            mapCScharer.get("userID"),
            mapCScharer.get("password"),
            true,
            mapCScharer.get("vivitURL"));
    // Convert Namespace
    mapResults = ymAPI.ConvertNamespace.toEasternTime(dateTimeCurrent, "-5");
    // Events Namespace
    mapResults = ymAPI.EventsNamespace.allSearch("Symposium", 100, 1);
    mapResults = ymAPI.EventsNamespace.eventAttendeesGet(0);
    mapResults = ymAPI.EventsNamespace.eventGet(0);
    // Feeds Namespace
    mapResults = ymAPI.FeedsNamespace.feedGet("NOW", "25", "1");
    mapResults = ymAPI.FeedsNamespace.get();
    // Member Namespace
    mapResults = ymAPI.MemberNamespace.certificationsGet(true);
    mapResults =
        ymAPI.MemberNamespace.certificationsJournalGet(
            true, "startDate", "entryID", "certificationID", 100, 1);
    mapResults = ymAPI.MemberNamespace.commerceStoreGetOrderIDs("timestamp", "status");
    mapResults = ymAPI.MemberNamespace.commerceStoreOrderGet("invoiceID");
    mapResults =
        ymAPI.MemberNamespace.connectionApprove(
            Integer.valueOf(mapCScharer.get("webSiteMemberID")), true);
    mapResults = ymAPI.MemberNamespace.isAuthenticated();
    mapResults = ymAPI.MemberNamespace.mediaGalleryUpload("albumID", "caption", true, true);
    mapResults = ymAPI.MemberNamespace.messagesGetInbox(100, 1);
    mapResults = ymAPI.MemberNamespace.messagesGetSent(100, 1);
    mapResults = ymAPI.MemberNamespace.messagesMessageRead("messageID");
    mapResults = ymAPI.MemberNamespace.messagesMessageSend("iD", "subject", "body");
    mapResults =
        ymAPI.MemberNamespace.passwordInitializeReset(
            mapCScharer.get("userID"), mapCScharer.get("emailAddress"));
    mapResults =
        ymAPI.MemberNamespace.passwordUpdate(
            "resetToken", mapCScharer.get("password"), mapCScharer.get("password"));
    mapResults = ymAPI.MemberNamespace.profileGet();
    mapResults = ymAPI.MemberNamespace.profileGetMini();
    mapResults = ymAPI.MemberNamespace.wallPost("iD", "postText");
    // Members Namespace
    mapResults = ymAPI.MembersNamespace.connectionsCategoriesGet("iD");
    mapResults = ymAPI.MembersNamespace.connectionsGet("iD", "categoryID", 100, 1);
    mapResults = ymAPI.MembersNamespace.mediaGalleryAlbumsGet("iD");
    mapResults = ymAPI.MembersNamespace.mediaGalleryGet("iD", "albumID", 100, 1);
    mapResults = ymAPI.MembersNamespace.mediaGalleryItemGet("iD", "itemID");
    mapResults = ymAPI.MembersNamespace.wallGet("iD", 100, 1);
    // People Namespace
    mapResults = ymAPI.PeopleNamespace.allSearch("Scharer", "25", "1");
    mapResults = ymAPI.PeopleNamespace.profileGet(mapCScharer.get("apiGUID"));
    // Namespace
    mapResults =
        ymAPI.Sa_AuthNamespace.authenticate(
            mapCScharer.get("userID"),
            mapCScharer.get("password"),
            mapCScharer.get("passwordHash"));
    // Sa.Certifications Namespace
    mapResults = ymAPI.Sa_CertificationsNamespace.allGet(true);
    mapResults = ymAPI.Sa_CertificationsNamespace.creditTypesAllGet();
    // Sa.Commerce Namespace
    mapResults = ymAPI.Sa_CommerceNamespace.storeOrderGet("invoiceID");
    mapResults = ymAPI.Sa_CommerceNamespace.productGet("productID");
    mapResults = ymAPI.Sa_CommerceNamespace.productsAllGetIDs("productName", "productType");
    // Sa.Events Namespace
    mapResults =
        ymAPI.Sa_EventsNamespace.allGetIDs(
            ymInceptionDate, dateTimeCurrent, "name", null, ymInceptionDate);
    mapResults = ymAPI.Sa_EventsNamespace.eventGet(0);
    mapResults = ymAPI.Sa_EventsNamespace.eventRegistrationGet("registrationID", "badgeNumber");
    mapResults = ymAPI.Sa_EventsNamespace.eventRegistrationsGetIDs(0, null);
    // Sa.Export Namespace
    mapResults = ymAPI.Sa_ExportNamespace.allInvoiceItems(false, ymInceptionDate);
    mapResults = ymAPI.Sa_ExportNamespace.careerOpenings(false, ymInceptionDate);
    mapResults = ymAPI.Sa_ExportNamespace.donationsTransactions(false, ymInceptionDate);
    mapResults = ymAPI.Sa_ExportNamespace.donationsInvoiceItems(false, ymInceptionDate);
    mapResults = ymAPI.Sa_ExportNamespace.duesTransactions(false, ymInceptionDate);
    mapResults = ymAPI.Sa_ExportNamespace.duesInvoiceItems(false, ymInceptionDate);
    mapResults =
        ymAPI.Sa_ExportNamespace.eventRegistrations(
            false, "eventID", "sessionIDs", "productID", "All", "lastName", true);
    mapResults = ymAPI.Sa_ExportNamespace.financeBatch(false, "batchID");
    mapResults = ymAPI.Sa_ExportNamespace.members(false, true, ymInceptionDate);
    mapResults = ymAPI.Sa_ExportNamespace.exportDataMembers(ymInceptionDate);
    mapResults = ymAPI.Sa_ExportNamespace.membersGroups(false, ymInceptionDate);
    mapResults = ymAPI.Sa_ExportNamespace.exportDataMemberGroups(ymInceptionDate);
    mapResults = ymAPI.Sa_ExportNamespace.storeOrders(false, ymInceptionDate);
    mapResults = ymAPI.Sa_ExportNamespace.storeInvoiceItems(false, ymInceptionDate);
    mapResults = ymAPI.Sa_ExportNamespace.status("exportID");
    // Sa.Finance Namespace
    mapResults = ymAPI.Sa_FinanceNamespace.batchCreate("", dateTimeCurrent, false);
    mapResults = ymAPI.Sa_FinanceNamespace.batchesGet(ymInceptionDate, "startBatchID");
    mapResults =
        ymAPI.Sa_FinanceNamespace.invoicePaymentCreate(
            "invoiceNo",
            "amount",
            "type",
            "refNo",
            dateTimeCurrent,
            "payerName",
            "payerEmail",
            "billOrganization",
            false,
            false,
            false,
            "billAddress1",
            "billAddress2",
            "billCity",
            "billLocation",
            "billPostalCode",
            "billCountry",
            "billPhone",
            "paymentNotes",
            "internalNotes");
    // Sa.Groups Namespace
    mapResults =
        ymAPI.Sa_GroupsNamespace.groupCreate(
            "groupCode",
            "typeID",
            "name",
            false,
            true,
            "accessibility",
            "membership",
            "messaging",
            false,
            false,
            true,
            false,
            "emailApproval",
            "emailOptionsAdmin",
            "emailOptionsMember",
            "shortDescription",
            "welcomeContent");
    mapResults =
        ymAPI.Sa_GroupsNamespace.groupGetMembershipLog("groupID", ymInceptionDate, "itemID");
    mapResults =
        ymAPI.Sa_GroupsNamespace.groupUpdate(
            "groupID",
            "groupCode",
            "typeID",
            "name",
            false,
            true,
            "accessibility",
            "membership",
            "messaging",
            false,
            false,
            true,
            false,
            "emailApproval",
            "emailOptionsAdmin",
            "emailOptionsMember",
            "shortDescription",
            "welcomeContent");
    mapResults = ymAPI.Sa_GroupsNamespace.groupTypesGet();
    // Sa.Member Namespace
    mapResults = ymAPI.Sa_MemberNamespace.certificationsGet("iD", true);
    mapResults =
        ymAPI.Sa_MemberNamespace.certificationsJournalGet(
            "iD", true, ymInceptionDate, "entryID", "certificationID", 100, 1);
    // Sa.Members Namespace
    mapResults = ymAPI.Sa_MembersNamespace.allGetIDs(ymInceptionDate, 0, "");
    mapResults = ymAPI.Sa_MembersNamespace.allMemberTypesGet();
    mapResults = ymAPI.Sa_MembersNamespace.allRecentActivity();
    mapResults =
        ymAPI.Sa_MembersNamespace.certificationsJournalEntryCreate(
            "iD",
            "cEUsEarned",
            "",
            "certificationID",
            "certificationName",
            "description",
            "",
            "scorePercent",
            "creditTypeCode");
    mapResults = ymAPI.Sa_MembersNamespace.commerceStoreGetOrderIDs("iD", ymInceptionDate, "Open");
    mapResults = ymAPI.Sa_MembersNamespace.eventsEventRegistrationGet("eventID", "iD");
    mapResults =
        ymAPI.Sa_MembersNamespace.groupsAdd(
            "iD", "groupCode", (Boolean) null, (Boolean) null, (Boolean) null, "");
    mapResults =
        ymAPI.Sa_MembersNamespace.groupsRemove(
            "iD", "groupCode", (Boolean) null, (Boolean) null, "");
    mapResults = ymAPI.Sa_MembersNamespace.profileCreate(true);
    mapResults = ymAPI.Sa_MembersNamespace.referralsGet("iD", ymInceptionDate);
    mapResults = ymAPI.Sa_MembersNamespace.subAccountsGet("iD", ymInceptionDate);
    // Sa.NonMembers Namespace
    mapResults = ymAPI.Sa_NonMembersNamespace.allGetIDs(ymInceptionDate, 0, "");
    mapResults = ymAPI.Sa_NonMembersNamespace.profileCreate();
    // Sa.People Namespace
    mapResults = ymAPI.Sa_PeopleNamespace.allGetIDs(ymInceptionDate, 0, "");
    mapResults =
        ymAPI.Sa_PeopleNamespace.profileFindID("importID", "constituentID", 0, "username", "email");
    mapResults = ymAPI.Sa_PeopleNamespace.profileGet("iD");
    mapResults = ymAPI.Sa_PeopleNamespace.profileGroupsGet("iD");
    mapResults = ymAPI.Sa_PeopleNamespace.profileUpdate("iD", "");
    // Session Namespace
    mapResults = ymAPI.SessionNamespace.create(true);
    mapResults = ymAPI.SessionNamespace.ping();
    mapResults = ymAPI.SessionNamespace.abandon();
  }
}
