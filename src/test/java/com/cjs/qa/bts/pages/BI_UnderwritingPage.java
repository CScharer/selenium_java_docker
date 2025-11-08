package com.cjs.qa.bts.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.core.Environment;
import com.cjs.qa.selenium.ISelenium;
import com.cjs.qa.selenium.Page;

import io.cucumber.datatable.DataTable;

public class BI_UnderwritingPage extends Page
{
	public BI_UnderwritingPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	// DECLARATIONS
	private final By	editSICCode												= By.id("SIC_CD");
	private final By	buttonSICCodeLookup										= By.id("SIC_CD-lookup");
	private final By	DropdownRatingTier										= By.id("RTG_TIER");
	private final By	DropdownPrimaryClassCode								= By.id("PRIM_CLS");
	private final By	editBusinessGroup										= By.id("BUS_GRP");
	private final By	DropdownBusinessGroupLookup								= By.id("BUS_GRP-lookup");
	private final By	DropdownProfitCenter									= By.id("PCC");
	private final By	DropdownNationalAccountsPolicyOrigination				= By.id("NTNL_ACCT");
	private final By	DropdownSpecialUnderwritingProgram						= By.id("POL_PRG");
	private final By	editDunsNumber											= By.id("DUNS_NUM1");
	private final By	DropdownStarAccount										= By.id("STAR_ACCT");
	private final By	editOtherLegalEntity									= By.id("ETY_OTH_DESC1");
	private final By	DropdownSourceOfBusiness								= By.id("SRC_BUS");
	private final By	DropdownPrimaryPolicySymbol								= By.id("PRIM_POL_SYM");
	private final By	CheckboxPolicyMinimumBypassed							= By.id("MIN_PREM");
	private final By	editPolicyMinimumPremiumBypassedCommercialAuto			= By.id("AUTO_MIN_PREM");
	private final By	CheckboxPublicUtilitiesCommissionFiling					= By.id("PUB_UTIL_COMM_FIL");
	private final By	DropdownSafetyDividendGroup								= By.id("SAFE_GRP");
	private final By	editUnemploymentNumber									= By.id("UIAN");
	private final By	CheckboxFacultiveReinsuranceApplies						= By.id("FAC_REINS_IND");
	private final By	CheckboxExemptCommercialAccount							= By.id("EXMPT_COM_ACCT");
	private final By	editAgencysInsuredIdentification						= By.id("AGCY_INSD_ID");
	private final By	DropdownNumberOfDaysQuoteIsValid						= By.id("NUM_DAYS1");
	private final By	CheckboxPackageMod										= By.id("ASSGN_PKG_MOD_POL_LVL");
	private final By	editDateQuoteReceivedByCompany							= By.id("CO_RCVD_DT");
	private final By	editDateUnderwritingReviewCompleted						= By.id("UDWR_RVW_DT");
	private final By	DropdownRolloverPriorCarrierCode						= By.id("ROLL_PRI_CARR_CD");
	private final By	CheckboxMoPepNonCancel									= By.id("NON_CANC_GUAR");
	private final By	DropdownPolicyConsideredForTransferToSmallBusiness		= By.id("SM_BUS_UNT_CONV");
	private final By	editPolicyNumberForAuditCrossReference					= By.id("AUDT_CRS_REF_POL_NUM");
	private final By	editOriginalAgencyCode									= By.id("ORIG_AGCY_CD");
	private final By	editPremiumFromAplusConvertedPolicy						= By.id("APLUS_PREM");
	private final By	editRenewalOfPolicyNumber								= By.id("RNL_POL_NUM");
	private final By	DropdownDoesEmploymentPracticesLiabilityInsuranceApply	= By.id("EPLI_IND");
	private final By	editEnterEmploymentPracticesLiabilityRetroactiveDate	= By.id("EPLI_RETRO_DT");
	private final By	CheckboxRerateEmploymentPracticesLiabilityOnAmmendment	= By.id("EPLI_RRT_IND");
	private final By	editKentuckyCollectionFeeTotalPolicyPremium				= By.id("KY_CLC_FEE_TOPREM");
	private final By	editKentuckyTaxTotalPolicyPremium						= By.id("KY_TAX_TOPREM");
	private final By	editTotalEmploymentPracticesLiabilityPremium			= By.id("EPLI_TOPREM");
	private final By	editTotalEPLISupplementalExtendedReportingPremium		= By.id("EPLI_RPTNG_TOPREM");
	private final By	CheckboxRapidRenewal									= By.id("RPD_RNL");
	private final By	editSupportingPolicies									= By.id("SUP_POL");
	private final By	buttonFrame												= By.id("underwriting-title");
	public final String	PAGE_TITLE												= "BI_UnderwritingPage";

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
	}

	// METHODS GET
	public String getEditSICCode()
	{
		return getEdit(editSICCode);
	}

	public String getDropdownRatingTier()
	{
		return getDropdown(DropdownRatingTier);
	}

	public String getDropdownPrimaryClassCode()
	{
		return getDropdown(DropdownPrimaryClassCode);
	}

	public String getEditBusinessGroup()
	{
		return getEdit(editBusinessGroup);
	}

	public String getDropdownBusinessGroupLookup()
	{
		return getDropdown(DropdownBusinessGroupLookup);
	}

	public String getDropdownProfitCenter()
	{
		return getDropdown(DropdownProfitCenter);
	}

	public String getDropdownNationalAccountsPolicyOrigination()
	{
		return getDropdown(DropdownNationalAccountsPolicyOrigination);
	}

	public String getDropdownSpecialUnderwritingProgram()
	{
		return getDropdown(DropdownSpecialUnderwritingProgram);
	}

	public String getEditDunsNumber()
	{
		return getEdit(editDunsNumber);
	}

	public String getDropdownStarAccount()
	{
		return getDropdown(DropdownStarAccount);
	}

	public String getEditOtherLegalEntity()
	{
		return getEdit(editOtherLegalEntity);
	}

	public String getDropdownSourceOfBusiness()
	{
		return getDropdown(DropdownSourceOfBusiness);
	}

	public String getDropdownPrimaryPolicySymbol()
	{
		return getDropdown(DropdownPrimaryPolicySymbol);
	}

	public String getCheckboxPolicyMinimumBypassed()
	{
		return getCheckbox(CheckboxPolicyMinimumBypassed);
	}

	public String getEditPolicyMinimumPremiumBypassedCommercialAuto()
	{
		return getEdit(editPolicyMinimumPremiumBypassedCommercialAuto);
	}

	public String getCheckboxPublicUtilitiesCommissionFiling()
	{
		return getCheckbox(CheckboxPublicUtilitiesCommissionFiling);
	}

	public String getDropdownSafetyDividendGroup()
	{
		return getDropdown(DropdownSafetyDividendGroup);
	}

	public String getEditUnemploymentNumber()
	{
		return getEdit(editUnemploymentNumber);
	}

	public String getCheckboxFacultiveReinsuranceApplies()
	{
		return getCheckbox(CheckboxFacultiveReinsuranceApplies);
	}

	public String getCheckboxExemptCommercialAccount()
	{
		return getCheckbox(CheckboxExemptCommercialAccount);
	}

	public String getEditAgencysInsuredIdentification()
	{
		return getEdit(editAgencysInsuredIdentification);
	}

	public String getDropdownNumberOfDaysQuoteIsValid()
	{
		return getDropdown(DropdownNumberOfDaysQuoteIsValid);
	}

	public String getCheckboxPackageMod()
	{
		return getCheckbox(CheckboxPackageMod);
	}

	public String getEditDateQuoteReceivedByCompany()
	{
		return getEdit(editDateQuoteReceivedByCompany);
	}

	public String getEditDateUnderwritingReviewCompleted()
	{
		return getEdit(editDateUnderwritingReviewCompleted);
	}

	public String getDropdownRolloverPriorCarrierCode()
	{
		return getDropdown(DropdownRolloverPriorCarrierCode);
	}

	public String getCheckboxMoPepNonCancel()
	{
		return getCheckbox(CheckboxMoPepNonCancel);
	}

	public String getDropdownPolicyConsideredForTransferToSmallBusiness()
	{
		return getDropdown(DropdownPolicyConsideredForTransferToSmallBusiness);
	}

	public String getEditPolicyNumberForAuditCrossReference()
	{
		return getEdit(editPolicyNumberForAuditCrossReference);
	}

	public String getEditOriginalAgencyCode()
	{
		return getEdit(editOriginalAgencyCode);
	}

	public String getEditPremiumFromAplusConvertedPolicy()
	{
		return getEdit(editPremiumFromAplusConvertedPolicy);
	}

	public String getEditRenewalOfPolicyNumber()
	{
		return getEdit(editRenewalOfPolicyNumber);
	}

	public String getDropdownDoesEmploymentPracticesLiabilityInsuranceApply()
	{
		return getDropdown(DropdownDoesEmploymentPracticesLiabilityInsuranceApply);
	}

	public String getEditEnterEmploymentPracticesLiabilityRetroactiveDate()
	{
		return getEdit(editEnterEmploymentPracticesLiabilityRetroactiveDate);
	}

	public String getCheckboxRerateEmploymentPracticesLiabilityOnAmmendment()
	{
		return getCheckbox(CheckboxRerateEmploymentPracticesLiabilityOnAmmendment);
	}

	public String getEditKentuckyCollectionFeeTotalPolicyPremium()
	{
		return getEdit(editKentuckyCollectionFeeTotalPolicyPremium);
	}

	public String getEditKentuckyTaxTotalPolicyPremium()
	{
		return getEdit(editKentuckyTaxTotalPolicyPremium);
	}

	public String getEditTotalEmploymentPracticesLiabilityPremium()
	{
		return getEdit(editTotalEmploymentPracticesLiabilityPremium);
	}

	public String getEditTotalEPLISupplementalExtendedReportingPremium()
	{
		return getEdit(editTotalEPLISupplementalExtendedReportingPremium);
	}

	public String getCheckboxRapidRenewal()
	{
		return getCheckbox(CheckboxRapidRenewal);
	}

	public String getEditSupportingPolicies()
	{
		return getEdit(editSupportingPolicies);
	}

	// METHODS SET
	public void setEditSICCode(String value)
	{
		setEdit(editSICCode, value);
	}

	public void clickButtonSICCodeLookup()
	{
		clickObject(buttonSICCodeLookup);
	}

	public void selectDropdownRatingTier(String value)
	{
		selectDropdown(DropdownRatingTier, value);
	}

	public void selectDropdownPrimaryClassCode(String value)
	{
		selectDropdown(DropdownPrimaryClassCode, value);
	}

	public void setEditBusinessGroup(String value)
	{
		setEdit(editBusinessGroup, value);
	}

	public void selectDropdownBusinessGroupLookup(String value)
	{
		selectDropdown(DropdownBusinessGroupLookup, value);
	}

	public void selectDropdownProfitCenter(String value)
	{
		selectDropdown(DropdownProfitCenter, value);
	}

	public void selectDropdownNationalAccountsPolicyOrigination(String value)
	{
		selectDropdown(DropdownNationalAccountsPolicyOrigination, value);
	}

	public void selectDropdownSpecialUnderwritingProgram(String value)
	{
		selectDropdown(DropdownSpecialUnderwritingProgram, value);
	}

	public void setEditDunsNumber(String value)
	{
		setEdit(editDunsNumber, value);
	}

	public void selectDropdownStarAccount(String value)
	{
		selectDropdown(DropdownStarAccount, value);
	}

	public void setEditOtherLegalEntity(String value)
	{
		setEdit(editOtherLegalEntity, value);
	}

	public void selectDropdownSourceOfBusiness(String value)
	{
		selectDropdown(DropdownSourceOfBusiness, value);
	}

	public void selectDropdownPrimaryPolicySymbol(String value)
	{
		selectDropdown(DropdownPrimaryPolicySymbol, value);
	}

	public void toggleCheckboxPolicyMinimumBypassed()
	{
		toggleCheckbox(CheckboxPolicyMinimumBypassed);
	}

	public void setCheckboxPolicyMinimumBypassed(String value)
	{
		setCheckbox(CheckboxPolicyMinimumBypassed, value);
	}

	public void setEditPolicyMinimumPremiumBypassedCommercialAuto(String value)
	{
		setEdit(editPolicyMinimumPremiumBypassedCommercialAuto, value);
	}

	public void toggleCheckboxPublicUtilitiesCommissionFiling()
	{
		toggleCheckbox(CheckboxPublicUtilitiesCommissionFiling);
	}

	public void setCheckboxPublicUtilitiesCommissionFiling(String value)
	{
		setCheckbox(CheckboxPublicUtilitiesCommissionFiling, value);
	}

	public void selectDropdownSafetyDividendGroup(String value)
	{
		selectDropdown(DropdownSafetyDividendGroup, value);
	}

	public void setEditUnemploymentNumber(String value)
	{
		setEdit(editUnemploymentNumber, value);
	}

	public void toggleCheckboxFacultiveReinsuranceApplies()
	{
		toggleCheckbox(CheckboxFacultiveReinsuranceApplies);
	}

	public void setCheckboxFacultiveReinsuranceApplies(String value)
	{
		setCheckbox(CheckboxFacultiveReinsuranceApplies, value);
	}

	public void toggleCheckboxExemptCommercialAccount()
	{
		toggleCheckbox(CheckboxExemptCommercialAccount);
	}

	public void setCheckboxExemptCommercialAccount(String value)
	{
		setCheckbox(CheckboxExemptCommercialAccount, value);
	}

	public void setEditAgencysInsuredIdentification(String value)
	{
		setEdit(editAgencysInsuredIdentification, value);
	}

	public void selectDropdownNumberOfDaysQuoteIsValid(String value)
	{
		selectDropdown(DropdownNumberOfDaysQuoteIsValid, value);
	}

	public void toggleCheckboxPackageMod()
	{
		toggleCheckbox(CheckboxPackageMod);
	}

	public void setCheckboxPackageMod(String value)
	{
		setCheckbox(CheckboxPackageMod, value);
	}

	public void setEditDateQuoteReceivedByCompany(String value)
	{
		setEdit(editDateQuoteReceivedByCompany, value);
	}

	public void setEditDateUnderwritingReviewCompleted(String value)
	{
		setEdit(editDateUnderwritingReviewCompleted, value);
	}

	public void selectDropdownRolloverPriorCarrierCode(String value)
	{
		selectDropdown(DropdownRolloverPriorCarrierCode, value);
	}

	public void toggleCheckboxMoPepNonCancel()
	{
		toggleCheckbox(CheckboxMoPepNonCancel);
	}

	public void setCheckboxMoPepNonCancel(String value)
	{
		setCheckbox(CheckboxMoPepNonCancel, value);
	}

	public void selectDropdownPolicyConsideredForTransferToSmallBusiness(String value)
	{
		selectDropdown(DropdownPolicyConsideredForTransferToSmallBusiness, value);
	}

	public void setEditPolicyNumberForAuditCrossReference(String value)
	{
		setEdit(editPolicyNumberForAuditCrossReference, value);
	}

	public void setEditOriginalAgencyCode(String value)
	{
		setEdit(editOriginalAgencyCode, value);
	}

	public void setEditPremiumFromAplusConvertedPolicy(String value)
	{
		setEdit(editPremiumFromAplusConvertedPolicy, value);
	}

	public void setEditRenewalOfPolicyNumber(String value)
	{
		setEdit(editRenewalOfPolicyNumber, value);
	}

	public void selectDropdownDoesEmploymentPracticesLiabilityInsuranceApply(String value)
	{
		selectDropdown(DropdownDoesEmploymentPracticesLiabilityInsuranceApply, value);
	}

	public void setEditEnterEmploymentPracticesLiabilityRetroactiveDate(String value)
	{
		setEdit(editEnterEmploymentPracticesLiabilityRetroactiveDate, value);
	}

	public void toggleCheckboxRerateEmploymentPracticesLiabilityOnAmmendment()
	{
		toggleCheckbox(CheckboxRerateEmploymentPracticesLiabilityOnAmmendment);
	}

	public void setCheckboxRerateEmploymentPracticesLiabilityOnAmmendment(String value)
	{
		setCheckbox(CheckboxRerateEmploymentPracticesLiabilityOnAmmendment, value);
	}

	public void setEditKentuckyCollectionFeeTotalPolicyPremium(String value)
	{
		setEdit(editKentuckyCollectionFeeTotalPolicyPremium, value);
	}

	public void setEditKentuckyTaxTotalPolicyPremium(String value)
	{
		setEdit(editKentuckyTaxTotalPolicyPremium, value);
	}

	public void setEditTotalEmploymentPracticesLiabilityPremium(String value)
	{
		setEdit(editTotalEmploymentPracticesLiabilityPremium, value);
	}

	public void setEditTotalEPLISupplementalExtendedReportingPremium(String value)
	{
		setEdit(editTotalEPLISupplementalExtendedReportingPremium, value);
	}

	public void toggleCheckboxRapidRenewal()
	{
		toggleCheckbox(CheckboxRapidRenewal);
	}

	public void setCheckboxRapidRenewal(String value)
	{
		setCheckbox(CheckboxRapidRenewal, value);
	}

	public void setEditSupportingPolicies(String value)
	{
		setEdit(editSupportingPolicies, value);
	}

	public void toggleFrame()
	{
		clickObject(buttonFrame);
	}

	// SWITCHES POPULATE
	public void populatePage(DataTable table)
	{
		final List<List<String>> data = table.asLists();
		for (final List<?> item : data)
		{
			final String field = (String) item.get(0);
			final String value = (String) item.get(1);
			if (!value.equals(""))
			{
				if (Environment.isLogAll())
				{
					Environment.sysOut("({Field}" + field + ", {Value}" + value + ");");
				}
				switch (field.toLowerCase())
				{
					case "sic code":
						setEditSICCode(value);
						break;
					case "rating tier":
						selectDropdownRatingTier(value);
						break;
					case "primary class code":
						selectDropdownPrimaryClassCode(value);
						break;
					case "business group":
						setEditBusinessGroup(value);
						break;
					case "business group lookup":
						selectDropdownBusinessGroupLookup(value);
						break;
					case "profit center":
						selectDropdownProfitCenter(value);
						break;
					case "national accounts policy origination":
						selectDropdownNationalAccountsPolicyOrigination(value);
						break;
					case "special underwriting program":
						selectDropdownSpecialUnderwritingProgram(value);
						break;
					case "duns number":
						setEditDunsNumber(value);
						break;
					case "star account":
						selectDropdownStarAccount(value);
						break;
					case "other legal entity":
						setEditOtherLegalEntity(value);
						break;
					case "source of business":
						selectDropdownSourceOfBusiness(value);
						break;
					case "primary policy symbol":
						selectDropdownPrimaryPolicySymbol(value);
						break;
					case "policy minimum bypassed":
						setCheckboxPolicyMinimumBypassed(value);
						break;
					case "policyminimumpremiumbypassedcommercialauto":
						setEditPolicyMinimumPremiumBypassedCommercialAuto(value);
						break;
					case "public utilities commission filing":
						setCheckboxPublicUtilitiesCommissionFiling(value);
						break;
					case "safety dividend group":
						selectDropdownSafetyDividendGroup(value);
						break;
					case "unemployment number":
						setEditUnemploymentNumber(value);
						break;
					case "facultive reinsurance applies":
						setCheckboxFacultiveReinsuranceApplies(value);
						break;
					case "exempt commercial account":
						setCheckboxExemptCommercialAccount(value);
						break;
					case "agencys insured identification":
						setEditAgencysInsuredIdentification(value);
						break;
					case "number of days quote is valid":
						selectDropdownNumberOfDaysQuoteIsValid(value);
						break;
					case "package mod":
						setCheckboxPackageMod(value);
						break;
					case "date quote received by company":
						setEditDateQuoteReceivedByCompany(value);
						break;
					case "date underwriting review completed":
						setEditDateUnderwritingReviewCompleted(value);
						break;
					case "rollover prior carrier code":
						selectDropdownRolloverPriorCarrierCode(value);
						break;
					case "mo pep non cancel":
						setCheckboxMoPepNonCancel(value);
						break;
					case "policy considered for transfer to small business":
						selectDropdownPolicyConsideredForTransferToSmallBusiness(value);
						break;
					case "policy number for audit cross reference":
						setEditPolicyNumberForAuditCrossReference(value);
						break;
					case "original agency code":
						setEditOriginalAgencyCode(value);
						break;
					case "premium from aplus converted policy":
						setEditPremiumFromAplusConvertedPolicy(value);
						break;
					case "renewal of policy number":
						setEditRenewalOfPolicyNumber(value);
						break;
					case "does employment practices liability insurance apply":
						selectDropdownDoesEmploymentPracticesLiabilityInsuranceApply(value);
						break;
					case "enter employment practices liability retroactive date":
						setEditEnterEmploymentPracticesLiabilityRetroactiveDate(value);
						break;
					case "rerate employment practices liability on ammendment":
						setCheckboxRerateEmploymentPracticesLiabilityOnAmmendment(value);
						break;
					case "kentucky collection fee total policy premium":
						setEditKentuckyCollectionFeeTotalPolicyPremium(value);
						break;
					case "kentucky tax total policy premium":
						setEditKentuckyTaxTotalPolicyPremium(value);
						break;
					case "total employment practices liability premium":
						setEditTotalEmploymentPracticesLiabilityPremium(value);
						break;
					case "total epli supplemental extended reporting premium":
						setEditTotalEPLISupplementalExtendedReportingPremium(value);
						break;
					case "rapid renewal":
						setCheckboxRapidRenewal(value);
						break;
					case "supporting policies":
						setEditSupportingPolicies(value);
						break;
					default:
						Environment.sysOut("[" + field + "]" + ISelenium.FIELD_NOT_CODED);
						break;
				}
			}
		}
	}

	// SWITCHES VALIDATE
	public void validatePage(DataTable table)
	{
		final Map<String, String> expected = new HashMap<>();
		final Map<String, String> actual = new HashMap<>();
		final List<List<String>> data = table.asLists();
		for (final List<?> item : data)
		{
			final String field = (String) item.get(0);
			String value = (String) item.get(1);
			if (!value.equals(""))
			{
				expected.put(field, value);
				switch (field.toLowerCase())
				{
					case "sic code":
						value = getEditSICCode();
						break;
					case "rating tier":
						value = getDropdownRatingTier();
						break;
					case "primary class code":
						value = getDropdownPrimaryClassCode();
						break;
					case "business group":
						value = getEditBusinessGroup();
						break;
					case "business group lookup":
						value = getDropdownBusinessGroupLookup();
						break;
					case "profit center":
						value = getDropdownProfitCenter();
						break;
					case "national accounts policy origination":
						value = getDropdownNationalAccountsPolicyOrigination();
						break;
					case "special underwriting program":
						value = getDropdownSpecialUnderwritingProgram();
						break;
					case "duns number":
						value = getEditDunsNumber();
						break;
					case "star account":
						value = getDropdownStarAccount();
						break;
					case "other legal entity":
						value = getEditOtherLegalEntity();
						break;
					case "source of business":
						value = getDropdownSourceOfBusiness();
						break;
					case "primary policy symbol":
						value = getDropdownPrimaryPolicySymbol();
						break;
					case "policy minimum bypassed":
						value = getCheckboxPolicyMinimumBypassed();
						break;
					case "policyminimumpremiumbypassedcommercialauto":
						value = getEditPolicyMinimumPremiumBypassedCommercialAuto();
						break;
					case "public utilities commission filing":
						value = getCheckboxPublicUtilitiesCommissionFiling();
						break;
					case "safety dividend group":
						value = getDropdownSafetyDividendGroup();
						break;
					case "unemployment number":
						value = getEditUnemploymentNumber();
						break;
					case "facultive reinsurance applies":
						value = getCheckboxFacultiveReinsuranceApplies();
						break;
					case "exempt commercial account":
						value = getCheckboxExemptCommercialAccount();
						break;
					case "agencys insured identification":
						value = getEditAgencysInsuredIdentification();
						break;
					case "number of days quote is valid":
						value = getDropdownNumberOfDaysQuoteIsValid();
						break;
					case "package mod":
						value = getCheckboxPackageMod();
						break;
					case "date quote received by company":
						value = getEditDateQuoteReceivedByCompany();
						break;
					case "date underwriting review completed":
						value = getEditDateUnderwritingReviewCompleted();
						break;
					case "rollover prior carrier code":
						value = getDropdownRolloverPriorCarrierCode();
						break;
					case "mo pep non cancel":
						value = getCheckboxMoPepNonCancel();
						break;
					case "policy considered for transfer to small business":
						value = getDropdownPolicyConsideredForTransferToSmallBusiness();
						break;
					case "policy number for audit cross reference":
						value = getEditPolicyNumberForAuditCrossReference();
						break;
					case "original agency code":
						value = getEditOriginalAgencyCode();
						break;
					case "premium from aplus converted policy":
						value = getEditPremiumFromAplusConvertedPolicy();
						break;
					case "renewal of policy number":
						value = getEditRenewalOfPolicyNumber();
						break;
					case "does employment practices liability insurance apply":
						value = getDropdownDoesEmploymentPracticesLiabilityInsuranceApply();
						break;
					case "enter employment practices liability retroactive date":
						value = getEditEnterEmploymentPracticesLiabilityRetroactiveDate();
						break;
					case "rerate employment practices liability on ammendment":
						value = getCheckboxRerateEmploymentPracticesLiabilityOnAmmendment();
						break;
					case "kentucky collection fee total policy premium":
						value = getEditKentuckyCollectionFeeTotalPolicyPremium();
						break;
					case "kentucky tax total policy premium":
						value = getEditKentuckyTaxTotalPolicyPremium();
						break;
					case "total employment practices liability premium":
						value = getEditTotalEmploymentPracticesLiabilityPremium();
						break;
					case "total epli supplemental extended reporting premium":
						value = getEditTotalEPLISupplementalExtendedReportingPremium();
						break;
					case "rapid renewal":
						value = getCheckboxRapidRenewal();
						break;
					case "supporting policies":
						value = getEditSupportingPolicies();
						break;
					default:
						value = "[" + field + "]" + ISelenium.FIELD_NOT_CODED;
						Environment.sysOut(value);
						break;
				}
				actual.put(field, value);
			}
		}
		Assert.assertSame(PAGE_TITLE + " validatePage", expected, actual);
	}
}