package com.cjs.qa.bts;

import com.cjs.qa.bts.pages.AddNewAddressClientPage;
import com.cjs.qa.bts.pages.AddNewPolicyPage;
import com.cjs.qa.bts.pages.AddNewProductPage;
import com.cjs.qa.bts.pages.AdminFunctionsPage;
import com.cjs.qa.bts.pages.BI_AdvantagePage;
import com.cjs.qa.bts.pages.BI_AgencyInformationPage;
import com.cjs.qa.bts.pages.BI_BasicInfoPage;
import com.cjs.qa.bts.pages.BI_BillingInformationPage;
import com.cjs.qa.bts.pages.BI_BillingPage;
import com.cjs.qa.bts.pages.BI_ButtonsPage;
import com.cjs.qa.bts.pages.BI_LeftNav;
import com.cjs.qa.bts.pages.BI_MiscellaneousInformationPage;
import com.cjs.qa.bts.pages.BI_RightNav;
import com.cjs.qa.bts.pages.BI_TerrorismPage;
import com.cjs.qa.bts.pages.BI_UnderwritingPage;
import com.cjs.qa.bts.pages.CPLResultsPage;
import com.cjs.qa.bts.pages.CopyPolicyPage;
import com.cjs.qa.bts.pages.IssuePage;
import com.cjs.qa.bts.pages.IssuePageLeftNav;
import com.cjs.qa.bts.pages.LoginPage;
import com.cjs.qa.bts.pages.ManageProductsPage;
import com.cjs.qa.bts.pages.ManageProductsRightNav;
import com.cjs.qa.bts.pages.NamedInsuredsPage;
import com.cjs.qa.bts.pages.PolicyIssuancePage;
import com.cjs.qa.bts.pages.PremiumSummaryPage;
import com.cjs.qa.bts.pages.PremiumSummaryRightNav;
import com.cjs.qa.bts.pages.RateLeftNavPage;
import com.cjs.qa.bts.pages.RatePage;
import com.cjs.qa.bts.pages.RateRightNav;
import com.cjs.qa.bts.pages.ReferenceMaterialsPage;
import com.cjs.qa.bts.pages.RequestPrintPage;
import com.cjs.qa.bts.pages.RequestPrintRightNav;
import com.cjs.qa.bts.pages.SearchPage;
import com.cjs.qa.bts.pages.SearchResultsPage;
import com.cjs.qa.bts.pages.SearchRightNav;
import com.cjs.qa.bts.pages.SidbarAdminFunctionsPage;
import com.cjs.qa.bts.pages.SidbarReferenceMaterialsPage;
import com.cjs.qa.bts.pages.Sidebar;
import com.cjs.qa.bts.pages._BTSCrosswordChallengePage;
import com.cjs.qa.core.Environment;
import com.cjs.qa.utilities.SoftAssert;
import org.openqa.selenium.WebDriver;

public class BTS {
  private SoftAssert softAssert;
  private _BTSCrosswordChallengePage btsCrosswordChallengePage;
  private AddNewAddressClientPage addNewAddressClientPage;
  private AddNewPolicyPage addNewPolicyPage;
  private AddNewProductPage addNewProductPage;
  private AdminFunctionsPage adminFunctionsPage;
  private BI_AdvantagePage biAdvantagePage;
  private BI_AgencyInformationPage biAgencyInformationPage;
  private BI_BasicInfoPage biBasicInfoPage;
  private BI_BillingInformationPage biBillingInformationPage;
  private BI_BillingPage biBillingPage;
  private BI_ButtonsPage biButtonsPage;
  private BI_LeftNav biLeftNav;
  private BI_MiscellaneousInformationPage biMiscellaneousInformationPage;
  private BI_RightNav biRightNav;
  private BI_TerrorismPage biTerrorismPage;
  private BI_UnderwritingPage biUnderwritingPage;
  private CopyPolicyPage copyPolicyPage;
  private CPLResultsPage cplResultsPage;
  private IssuePage issuePage;
  private IssuePageLeftNav issuePageLeftNav;
  private LoginPage loginPage;
  private ManageProductsPage manageProductsPage;
  private ManageProductsRightNav manageProductsRightNav;
  private NamedInsuredsPage namedInsuredsPage;
  private PolicyIssuancePage policyIssuancePage;
  private PremiumSummaryPage premiumSummaryPage;
  private PremiumSummaryRightNav premiumSummaryRightNav;
  private RatePage ratePage;
  private RateLeftNavPage rateLeftNavPage;
  private RateRightNav rateRightNav;
  private ReferenceMaterialsPage referenceMaterialsPage;
  private RequestPrintPage requestPrintPage;
  private RequestPrintRightNav requestPrintRightNav;
  private SearchPage searchPage;
  private SearchResultsPage searchResultsPage;
  private SearchRightNav searchRightNav;
  private Sidebar sidebar;
  private SidbarAdminFunctionsPage sidbarAdminFunctionsPage;
  private SidbarReferenceMaterialsPage sidbarReferenceMaterialsPage;

  public SoftAssert getSoftAssert() {
    return softAssert;
  }

  public _BTSCrosswordChallengePage getBtsCrosswordChallengePage() {
    return btsCrosswordChallengePage;
  }

  public AddNewAddressClientPage getAddNewAddressClientPage() {
    return addNewAddressClientPage;
  }

  public AddNewPolicyPage getAddNewPolicyPage() {
    return addNewPolicyPage;
  }

  public AddNewProductPage getAddNewProductPage() {
    return addNewProductPage;
  }

  public AdminFunctionsPage getAdminFunctionsPage() {
    return adminFunctionsPage;
  }

  public BI_AdvantagePage getBiAdvantagePage() {
    return biAdvantagePage;
  }

  public BI_AgencyInformationPage getBiAgencyInformationPage() {
    return biAgencyInformationPage;
  }

  public BI_BasicInfoPage getBiBasicInfoPage() {
    return biBasicInfoPage;
  }

  public BI_BillingInformationPage getBiBillingInformationPage() {
    return biBillingInformationPage;
  }

  public BI_BillingPage getBiBillingPage() {
    return biBillingPage;
  }

  public BI_ButtonsPage getBiButtonsPage() {
    return biButtonsPage;
  }

  public BI_LeftNav getBiLeftNav() {
    return biLeftNav;
  }

  public BI_MiscellaneousInformationPage getBiMiscellaneousInformationPage() {
    return biMiscellaneousInformationPage;
  }

  public BI_RightNav getBiRightNav() {
    return biRightNav;
  }

  public BI_TerrorismPage getBiTerrorismPage() {
    return biTerrorismPage;
  }

  public BI_UnderwritingPage getBiUnderwritingPage() {
    return biUnderwritingPage;
  }

  public CopyPolicyPage getCopyPolicyPage() {
    return copyPolicyPage;
  }

  public CPLResultsPage getCplResultsPage() {
    return cplResultsPage;
  }

  public IssuePage getIssuePage() {
    return issuePage;
  }

  public IssuePageLeftNav getIssuePageLeftNav() {
    return issuePageLeftNav;
  }

  public LoginPage getLoginPage() {
    return loginPage;
  }

  public ManageProductsPage getManageProductsPage() {
    return manageProductsPage;
  }

  public ManageProductsRightNav getManageProductsRightNav() {
    return manageProductsRightNav;
  }

  public NamedInsuredsPage getNamedInsuredsPage() {
    return namedInsuredsPage;
  }

  public PolicyIssuancePage getPolicyIssuancePage() {
    return policyIssuancePage;
  }

  public PremiumSummaryPage getPremiumSummaryPage() {
    return premiumSummaryPage;
  }

  public PremiumSummaryRightNav getPremiumSummaryRightNav() {
    return premiumSummaryRightNav;
  }

  public RatePage getRatePage() {
    return ratePage;
  }

  public RateLeftNavPage getRateLeftNavPage() {
    return rateLeftNavPage;
  }

  public RateRightNav getRateRightNav() {
    return rateRightNav;
  }

  public ReferenceMaterialsPage getReferenceMaterialsPage() {
    return referenceMaterialsPage;
  }

  public RequestPrintPage getRequestPrintPage() {
    return requestPrintPage;
  }

  public RequestPrintRightNav getRequestPrintRightNav() {
    return requestPrintRightNav;
  }

  public SearchPage getSearchPage() {
    return searchPage;
  }

  public SearchResultsPage getSearchResultsPage() {
    return searchResultsPage;
  }

  public SearchRightNav getSearchRightNav() {
    return searchRightNav;
  }

  public Sidebar getSidebar() {
    return sidebar;
  }

  public SidbarAdminFunctionsPage getSidbarAdminFunctionsPage() {
    return sidbarAdminFunctionsPage;
  }

  public SidbarReferenceMaterialsPage getSidbarReferenceMaterialsPage() {
    return sidbarReferenceMaterialsPage;
  }

  public BTS(WebDriver webDriver) {
    softAssert = new SoftAssert();
    btsCrosswordChallengePage = new _BTSCrosswordChallengePage(webDriver);
    addNewAddressClientPage = new AddNewAddressClientPage(webDriver);
    addNewPolicyPage = new AddNewPolicyPage(webDriver);
    addNewProductPage = new AddNewProductPage(webDriver);
    adminFunctionsPage = new AdminFunctionsPage(webDriver);
    biAdvantagePage = new BI_AdvantagePage(webDriver);
    biAgencyInformationPage = new BI_AgencyInformationPage(webDriver);
    biBasicInfoPage = new BI_BasicInfoPage(webDriver);
    biBillingInformationPage = new BI_BillingInformationPage(webDriver);
    biBillingPage = new BI_BillingPage(webDriver);
    biButtonsPage = new BI_ButtonsPage(webDriver);
    biLeftNav = new BI_LeftNav(webDriver);
    biMiscellaneousInformationPage = new BI_MiscellaneousInformationPage(webDriver);
    biRightNav = new BI_RightNav(webDriver);
    biTerrorismPage = new BI_TerrorismPage(webDriver);
    biUnderwritingPage = new BI_UnderwritingPage(webDriver);
    copyPolicyPage = new CopyPolicyPage(webDriver);
    cplResultsPage = new CPLResultsPage(webDriver);
    issuePage = new IssuePage(webDriver);
    issuePageLeftNav = new IssuePageLeftNav(webDriver);
    loginPage = new LoginPage(webDriver);
    manageProductsPage = new ManageProductsPage(webDriver);
    manageProductsRightNav = new ManageProductsRightNav(webDriver);
    namedInsuredsPage = new NamedInsuredsPage(webDriver);
    policyIssuancePage = new PolicyIssuancePage(webDriver);
    premiumSummaryPage = new PremiumSummaryPage(webDriver);
    premiumSummaryRightNav = new PremiumSummaryRightNav(webDriver);
    ratePage = new RatePage(webDriver);
    rateLeftNavPage = new RateLeftNavPage(webDriver);
    rateRightNav = new RateRightNav(webDriver);
    referenceMaterialsPage = new ReferenceMaterialsPage(webDriver);
    requestPrintPage = new RequestPrintPage(webDriver);
    requestPrintRightNav = new RequestPrintRightNav(webDriver);
    searchPage = new SearchPage(webDriver);
    searchResultsPage = new SearchResultsPage(webDriver);
    searchRightNav = new SearchRightNav(webDriver);
    sidebar = new Sidebar(webDriver);
    sidbarAdminFunctionsPage = new SidbarAdminFunctionsPage(webDriver);
    sidbarReferenceMaterialsPage = new SidbarReferenceMaterialsPage(webDriver);
    webDriver
        .manage()
        .timeouts()
        .pageLoadTimeout(java.time.Duration.ofSeconds(Environment.getTimeOutPage()));
  }
}
