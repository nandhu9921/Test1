package com.Well.ReusableMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class ReusableMethodEquity extends BaseClass {
	String PortfolioAndRatingLocAccDocumentTable ="PortfolioAndRatingLocAccDocumentTable";
	String toolTipMessage ="You do not have permission to delete this document";
	
	public void RegisterEquity(String SheetName, int rowNum, String DataValidate, String ProjectName) throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL certified account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar","WELLEquityNavBar");
		CommonMethod.RobustclickElementVisible("WELLEquityNavBar","WERstartNewProject");
		testlog.info("When User clicks on WELL Equity from top menu under Projects");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERstartNewProject", 0);
		CommonMethod.RobustclickElementVisible("WERstartNewProject","WEREnrollOption");
		testlog.info("And User clicks on Start project button");
		CommonMethod.RobustclickElementVisible("WEREnrollOption", "WERenrollbtn");
		testlog.info("And User clicks on EnrollNow button");
		CommonMethod.RobustclickElementVisible("WERenrollbtn", "WPRWEROrgContinebtn");
		testlog.info("And User clicks on Enroll button");
		String AccountName = ProjectName + CommonMethod.randomNumber(8000000);
		testlog.info("ProjectName: " + AccountName);
		data.setCellData(SheetName, "projectName", rowNum, AccountName);
		if (DataValidate.equalsIgnoreCase("true")) {
		CommonMethod.RobustclickElementVisible("WPRWEROrgContinebtn", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Organization is required.", "Organization Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Organization Industry is required.", "Organization Industry Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Consultant is required.", "Consultant Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Country is required.", "Country Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Street is required.", "Street Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("City is required.", "City Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Postal Code is required.", "Postal Code Error Mismatch");
		testlog.info("And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		}
		CommonMethod.verifyDropdownValues("HsrWprEditOrgIndustry", "OrganizationIndustry");
//		CommonMethod.verifyDropdownValues("WPRConsultant", "Consultant");
		CommonMethod.verifyDropdownValues("WPRExamOwnerCountry", "Country");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WEREnrollName",0);
		CommonMethod.sendKeys("WEREnrollName", AccountName);
		testlog.info("And User enter data to EnrollName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WEROwnerInfocbx", 0);
		CommonMethod.ClickCheckbox("WEROwnerInfocbx");
		testlog.info("And User checks the enroll checkbox");
		String OwnerName = USfaker.address().firstName();
		CommonMethod.sendKeys("OwnerName", OwnerName);
		rc.SelectOwnerOrg(SheetName, rowNum);
		data.setCellData(SheetName, "OrgName", rowNum, CommonMethod.getText("OrgName"));
		testlog.info("OrganizationName: " + data.getCellData(SheetName, "OrgName", rowNum));
		CommonMethod.selectdropdownrandom("OrgIndustry");
		data.setCellData(SheetName, "OrgIndustry", rowNum, CommonMethod.getSelectedDropdownValue("OrgIndustry"));
		rc.SelectEnterpriseProviders(SheetName,rowNum);
		CommonMethod.selectdropdownValue("WEROwnerCountry", "US");
		testlog.info("Country: " + CommonMethod.getSelectedDropdownAttribute("WEROwnerCountry"));
		CommonMethod.selectdropdownrandom("StateName");
		String ProjectAddress = USfaker.address().streetAddress();
		String ProjectCity = USfaker.address().cityName();
		String PostalCode = USfaker.address().zipCode();
		CommonMethod.sendKeys("StreetName", ProjectAddress);
		CommonMethod.sendKeys("CityName", ProjectCity);
		CommonMethod.sendKeys("PostalCode", PostalCode);
		data.setCellData(SheetName, "Country", rowNum, CommonMethod.getSelectedDropdownAttribute("WEROwnerCountry"));
		data.setCellData(SheetName, "State", rowNum, CommonMethod.getattributeValue("StateName"));
		data.setCellData(SheetName, "Street", rowNum, CommonMethod.getattributeValue("StreetName"));
		data.setCellData(SheetName, "City", rowNum, CommonMethod.getattributeValue("CityName"));
		data.setCellData(SheetName, "PostalCode", rowNum, CommonMethod.getattributeValue("PostalCode"));
		testlog.info("And User enter data to Owner Country, State, Street address, City and Postal Code fields");
		testlog.info("Organization Address: " + ProjectAddress);
		testlog.info("Organization City: " + ProjectCity);
		testlog.info("Organization Postalcode: " + PostalCode);
		CommonMethod.VerifyRadioOrCheckboxSelcted("WEROwnercbx");
		CommonMethod.ClickCheckbox("WEROwnercbx");
		CommonMethod.VerifyRadioOrCheckboxSelcted("Hsrbilladdcheckbox");
		CommonMethod.ClickCheckbox("Hsrbilladdcheckbox");
		testlog.info("And User checks the Billing checkbox");
		CommonMethod.scrolldowntoElement("WEROwnercbx");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRWEROrgContinebtn",0);
		testlog.info("And User clicks on continue button");
		CommonMethod.JavascriptClickElement("WPRWEROrgContinebtn");
		if (DataValidate.equalsIgnoreCase("true")) {
		CommonMethod.RobustclickElementVisible("WPRBackToHomepagebutton", "WPRWEROrgContinebtn");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("WEREnrollName"),ProjectName, "Enroll Name Error Mismatch");
		CommonMethod.VerifyRadioOrCheckboxSelcted("WEROwnerInfocbx");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("OwnerName"),OwnerName, "Owner Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("OrgName"),data.getCellData(SheetName, "OrgName", rowNum), "Org Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("OrgIndustry"),data.getCellData(SheetName, "OrgIndustry", rowNum), "Org Industry Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownAttribute("WEROwnerCountry"),data.getCellData(SheetName, "Country", rowNum), "Country Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("StateName"),data.getCellData(SheetName, "State", rowNum), "State Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("StreetName"),data.getCellData(SheetName, "Street", rowNum), "Street Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("CityName"),data.getCellData(SheetName, "City", rowNum), "City Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PostalCode"),data.getCellData(SheetName, "PostalCode", rowNum), "Postal code Error Mismatch");
		CommonMethod.VerifyRadioOrCheckboxSelcted("WEROwnercbx");
		CommonMethod.JavascriptClickElement("WPRWEROrgContinebtn");
		testlog.info("And User verifies the added details about you by clicking on back button");
		}
		Thread.sleep(2000);
		CommonMethod.scrollUp();
		if (DataValidate.equalsIgnoreCase("true")) {
		CommonMethod.RobustclickElementVisible("WEROwnerRegContinuebtn","MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("On behalf of owner is required.", "Owner CheckBox Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Is the Owner organization an IWBI member?* is required.", "Owner Organization Name Error Mismatch");
		testlog.info("And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERBehalfCbx", 0);
		CommonMethod.ClickCheckbox("WERBehalfCbx");
		testlog.info("And User checks the Behalf checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERSelectMember", 0);
		CommonMethod.selectdropdownVisibletext("WERSelectMember", "No");
		testlog.info("And User select Iwbi member");
		data.setCellData(SheetName, "WERSelectMember", rowNum,
	    CommonMethod.getSelectedDropdownValue("WERSelectMember"));
		CommonMethod.JavascriptClickElement("WEROwnerRegContinuebtn");
		Thread.sleep(2000);
		CommonMethod.scrollUp();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERlocations", 0);
		CommonMethod.sendKeys("WERlocations", "10");
		testlog.info("And User enter data to location");
		data.setCellData(SheetName, "WERlocations", rowNum, CommonMethod.getattributeValue("WERlocations"));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect", 0);
		CommonMethod.RobustclickElementVisible("Multiselect","HsrWPRlocationsSpaceOption");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWPRlocationsSpaceOption", 0);
		CommonMethod.RobustclickElementVisible("HsrWPRlocationsSpaceOption","Locationsize");
		testlog.info("And User select the space type");
		Thread.sleep(1000);
		CommonMethod.scrollDown();
		String Area = CommonMethod.randomNumberBetweenRanges(100, 50000);
		testlog.info("Locationsize: " + Area);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Locationsize", 0);
		CommonMethod.clearAndSendKey("Locationsize", Area);
		testlog.info("And User enter the Location Size");
		data.setCellData(SheetName, "WERlocationsize", rowNum, CommonMethod.getattributeValue("Locationsize"));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WEROwnerRegContinuebtn", 0);
		CommonMethod.JavascriptClickElement("WEROwnerRegContinuebtn");
		testlog.info("And User clicks on continue button");
		if (CommonMethod.isElementsExist("HsrWPRYesMyOrganizationCbx", 10)) {
			CommonMethod.ClickCheckbox("HsrWPRYesMyOrganizationCbx");
			testlog.info("And User checks the MyOrganization checkbox");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRWERReviewContinuebutton", 0);
		CommonMethod.JavascriptClickElement("WPRWERReviewContinuebutton");
		testlog.info("And User clicks on continue button");
		if (CommonMethod.isElementsExist("WERProgramFeePublicrbtn", 10)) {
			CommonMethod.ClickCheckbox("WERProgramFeePublicrbtn");
			testlog.info("And User checks the ProgramFee checkbox");
		}
		CommonMethod.scrollDown();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERAcknowledecbx", 0);
		CommonMethod.ClickCheckbox("WERAcknowledecbx");
		testlog.info("And User checks the Acknowledge checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRWERtermContinuebutton", 0);
		CommonMethod.RobustclickElementVisible("WPRWERtermContinuebutton", "BillingLanding");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.info("Then User will be redirected to BillingLanding page");
		testlog.pass("**Verifies the Registration successful**");
	}

	public void StoreIdEquity(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("StoreId", 0);
		String getId = CommonMethod.getText("StoreId");
		String[] stringArray = getId.split(": ");
		String getWprId = stringArray[1].trim();
		testlog.info("Equity Id: " + getWprId);
		data.setCellData(SheetName, "ProjectID", rowNum, getWprId);
		testlog.info("Equity ID: " + getWprId);
		testlog.info("When User storing Equity ID in excel");
		testlog.pass("**Stored the Registered id  into excel successfully**");
	}
	
	public void SearchEquityByID(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar","WELLEquityNavBar");
		CommonMethod.RobustclickElementVisible("WELLEquityNavBar","RatingIdClick");
		CommonMethod.verifyDropdownValues("HSRTypeDropdown", "HsrType");
		testlog.info("When User clicks on WELL PerformanceRating from top menu under Projects");
		String werId = data.getCellData(SheetName, "ProjectID", rowNum);
		testlog.info("Equity ID: " + werId);
		CommonMethod.WaitUntilClickble("WPRWERId", 60).sendKeys(werId);
		testlog.info("And User enter data to WerId");
		CommonMethod.RobustclickElementVisible("ApplyButton","SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int var = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var), "1", "Equity Search failed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RatingIdClick"), werId,
				"Project name doesn't matches in search");
		CommonMethod.RobustclickElementVisible("RatingIdClick","WellV2DashboardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		
		testlog.info("And User clicks on WerAdminId");
		testlog.info("Then User verifies WerId in search filter");
		testlog.pass("**Verifies the Search Equity ByID successfully**");
	}
	
	public void ValidDashboardWerField(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.WaitUntilPresence("DocumentLibraryTab", 30);
		CommonMethod.WaitUntilPresence("ScorecardTab", 30);
		CommonMethod.WaitUntilPresence("ReviewTab", 30);
		CommonMethod.WaitUntilPresence("V2ProjectSupportButton", 30);
		CommonMethod.WaitUntilPresence("AlternativesTab", 30);
		CommonMethod.WaitUntilPresence("LocationsTab", 30);
		CommonMethod.WaitUntilPresence("ProfileTab", 30);
		CommonMethod.WaitUntilPresence("BiilingTab", 30);
		CommonMethod.WaitUntilPresence("TeamTab", 30);
		CommonMethod.WaitUntilPresence("PromotionTab", 30);
		CommonMethod.WaitUntilPresence("EditTab", 30);
		testlog.info("Then User verifies the Side Navigation Tab");
		testlog.pass("**Verifies Dashboard fields and SideBar Navigation tab successfully **");
}
	
	public void SearchEquityByRegisterStatus(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar","WELLEquityNavBar");
		CommonMethod.RobustclickElementVisible("WELLEquityNavBar","RatingIdClick");
		testlog.info("When User clicks on WELL Equity from top menu under Projects");
		String werId = data.getCellData(SheetName, "ProjectID", rowNum);
		testlog.info("Equity ID: " + werId);
		CommonMethod.WaitUntilClickble("WPRWERId", 120).sendKeys(werId);
		testlog.info("And User enter data to WprId");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplyButton", 0);
		CommonMethod.RobustclickElementVisible("ApplyButton","SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		int var = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(var),"1","Equity Search failed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RatingIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");
		String status = CommonMethod.getText("HsrWprStatusResultList");
		testlog.info("Status: " +status);
		CommonMethod.negativesoftassertFieldValid(status,"REGISTERED","Performance Search failed");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingIdClick", 0);
		CommonMethod.RobustclickElementVisible("RatingIdClick","WellV2DashboardTab");
		testlog.info("And User clicks on WERId button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.info("Then User verifies WERStatus in search filter");
		testlog.pass("**Verifies the Search Equity ByID successfully**");
	}
	
	public void WerProjectFieldValidationTest(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab","HsrWprOrganizationInformation");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprOrganizationInformation", 0);
		CommonMethod.RobustclickElementVisible("HsrWprOrganizationInformation","HsrWprEditProjectName");
		testlog.info("And User clicks on OrganizationInformation tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprEditProjectName", 0);
		testlog.info("projectName: " + data.getCellData(SheetName, "projectName", rowNum));
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getattributeValue("HsrWprEditProjectName"),
				data.getCellData(SheetName, "projectName", rowNum), "Project Name doesn't match");
		testlog.info("WPRlocationsize: " + data.getCellData(SheetName, "Locationsize", rowNum));
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getattributeValue("HsrWprEditArea").replace("sq ft", "").replace(",", "").trim(),
				data.getCellData(SheetName, "WERlocationsize", rowNum), "Area doesn't match");
		testlog.info("WPRlocations: " + data.getCellData(SheetName, "WPRlocations", rowNum));
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getattributeValue("HsrWprEditLocation"),
				data.getCellData(SheetName, "WERlocations", rowNum), "Location count doesn't match");
		testlog.info("OrgName: " + data.getCellData(SheetName, "OrgName", rowNum));
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("OrgName"),
				data.getCellData(SheetName, "OrgName", rowNum), "OrgName doesn't match");
		testlog.info("OrgIndustry: " + data.getCellData(SheetName, "OrgIndustry", rowNum));
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getSelectedDropdownValue("HsrWprEditOrgIndustry"),
				data.getCellData(SheetName, "OrgIndustry", rowNum), "OrgIndustry doesn't match");
		testlog.info("Country: " + CommonMethod.getSelectedDropdownAttribute("HsrWprEditCountry"));
		  CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getSelectedDropdownAttribute(
		  "HsrWprEditCountry"), data.getCellData(SheetName, "Country", rowNum),
		  "Country doesn't match");
		 testlog.info("State: " + data.getCellData(SheetName, "State", rowNum));
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getSelectedDropdownValue("HsrWprEditState"),
				data.getCellData(SheetName, "State", rowNum), "State Name doesn't match");
		testlog.info("Street: " + data.getCellData(SheetName, "Street", rowNum));
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getattributeValue("HsrWprEditStreet"),
				data.getCellData(SheetName, "Street", rowNum), "Street Name doesn't match");
		testlog.info("City: " + data.getCellData(SheetName, "City", rowNum));
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getattributeValue("HsrWprEditCity"),
				data.getCellData(SheetName, "City", rowNum), "City doesn't match");
		testlog.info("PostalCode: " + data.getCellData(SheetName, "PostalCode", rowNum));
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getattributeValue("HsrWprPostalCode"),
				data.getCellData(SheetName, "PostalCode", rowNum), "PostalCode doesn't match");
        testlog.info("Then User verifies the added details from excel");
        testlog.pass("**Verifies the WELL Equity Field Validation successfully**");
	}
	
	public void CompleteScorecardWerById(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTab", 0);
		CommonMethod.JavascriptClickElement("ScorecardTab");
		testlog.info("And User clicks on ScorecardTab");
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseMaybe", 0);
		CommonMethod.RobustclickElementVisible("CommonScorecardPurseMaybe", "Confirmbtn");
		rc.confirmPursing();
		CommonMethod.RobustclickElementVisible("CommonScorecardPurseMaybe", "Confirmbtn");
		rc.confirmPursing();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRCloseIcon", 0);
		performance.ScorecardfillHSRWPR(21, 22, 49, 28, "CommonScorecardPurseYes", "CommonScorecardPurseNo");	
		testlog.info("And User clicks on 15 Yes button");
		testlog.info("And User clicks on 12 No button");
		testlog.info("Then User verifies Upload Document toast message");
		testlog.pass("**Verifies the 21 Purse Yes Scorecard Equity successfully**");
	}
	
	public void NASubmitReviewAsTeam() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab","WPRReviewSubmitbtn");
		testlog.info("When User clicks on Review button");
		CommonMethod.RobustclickElementVisible("WPRReviewSubmitbtn","WPRReviewProjectPhase");
		testlog.info("And User clicks on Submit DocReview button");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRReviewSubmitDocbtn");
		CommonMethod.assertisElementPresentFalse("WPRReviewSubmitDocbtn","ReviewSubmitDocbtn is present");
		CommonMethod.refreshBrowser();
	}

	
	public void WERSubmitReview(String SheetName, int rowNum, String ReviewName) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab","WPRReviewSubmitbtn");
		testlog.info("When User clicks on Review button");
		CommonMethod.RobustclickElementVisible("WPRReviewSubmitbtn","WPRReviewProjectPhase");
		testlog.info("And User clicks on Submit DocReview button");		
		if (SheetName.equalsIgnoreCase("Wer") && (TestCaseName.contains("FinalSubmitReview") && (TestNGTestName.contains("Main")))) {
		CommonMethod.verifyDropdownValues("WPRReviewProjectPhase", "EquityFinalReviewPhase");
	    }
		if (SheetName.equalsIgnoreCase("Wer") && (TestCaseName.contains("CurativeSubmitReview") && (TestNGTestName.contains("Main")))) {
		CommonMethod.verifyDropdownValues("WPRReviewProjectPhase", "EquityCurativeReviewPhase");
	    }
		if (SheetName.equalsIgnoreCase("Wer") && (TestCaseName.contains("PreliminarySubmitReview") && (TestNGTestName.contains("Main")))) {
		CommonMethod.verifyDropdownValues("WPRReviewProjectPhase", "EquityReviewPhase");
	    }		
		CommonMethod.selectdropdownVisibletext("WPRReviewProjectPhase", ReviewName);
		testlog.info("And User select phase review");
		CommonMethod.WaitUntilClickble("WPRReviewComment", 60).sendKeys(ReviewName);
		testlog.info("And User enter data to Comment field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewSubmitDocbtn", 0);
		CommonMethod.RobustclickElementVisible("WPRReviewSubmitDocbtn","ReviewViewButton");
		testlog.info("And User clicks on  Submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
		testlog.info("Then User will be redirected to Review list page");
		testlog.pass("**Submitted Preliminary Precertification Review successfully**");
	}
	public void WERCompleteReview(String SheetName, int rowNum, String ReviewName) throws IOException, InterruptedException {
		/*
		 * Admin Review
		 */
		AdminSearchWer(SheetName, rowNum);
		hsr.CompleteReturnReview(SheetName, rowNum, ReviewName);
	}
	
	public void validateTeamsWER(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar","WELLEquityNavBar");
		CommonMethod.RobustclickElementVisible("WELLEquityNavBar","RatingIdClick");
		String werId = data.getCellData(SheetName, "ProjectID", rowNum);
		testlog.info("Equity ID: " + werId);
		CommonMethod.WaitUntilClickble("WPRWERId", 60).sendKeys(werId);
		CommonMethod.RobustclickElementVisible("ApplyButton","SearchResultIDVerify");
		int var = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(var),"1","Equity Search failed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RatingIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");
		testlog.pass("**Verifies the Search Equity ByID can access the Project successfully**");
	}
	
	public void SearchEquityByAchievedStatus(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard Page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar","WELLEquityNavBar");
		testlog.info("When User clicks on ProjectNavBar");
		CommonMethod.RobustclickElementVisible("WELLEquityNavBar","RatingIdClick");
		testlog.info("And User clicks on WELLPerformanceRatingNavBar");
		String werId = data.getCellData(SheetName, "ProjectID", rowNum);
		testlog.info("Equity ID: " + werId);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRWERId", 0);
		CommonMethod.clearAndSendKey("WPRWERId", werId);
		testlog.info("And User enters to WERId field");
		CommonMethod.RobustclickElementVisible("ApplyButton","SearchResultIDVerify");
		testlog.info("And User clicks on apply button");
		int var = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(var),"1","Equity Search failed");
		int ProjectCount = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount),"1","Performance Search failed");
		CommonMethod.sendKeys("HsrNameList", data.getCellData(SheetName, "projectName", rowNum));
		CommonMethod.RobustclickElementVisible("ApplyButton","SearchResultIDVerify");
		int ProjectCount1 = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount1),"1","Performance Search Count failed");
		CommonMethod.assertcontainsmessage("RatingIdClick", data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");
		String status = CommonMethod.getText("HsrWprStatusResultList");
		testlog.info("Status: " +status);
		CommonMethod.negativesoftassertFieldValid(status,"ACHIEVED","Performance Search failed");
		rc.ValidateDateInList();
		testlog.info("Then User verifies Project Acheieved Status");
		testlog.pass("**Verifies the Search Equity Achieved Status successfully**");
	}
	
	public void AdminSearchWer(String SheetName, int rowNum) throws IOException, InterruptedException {
		/*
		 * Admin Review
		 */
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar","AdminWELLEquityNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminWELLEquityNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminWELLEquityNavBar", "SearchByID");
		testlog.info("When User clicks on WELL Equity from top menu under Projects");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchByID", 0);
		CommonMethod.WaitUntilClickble("SearchByID", 60)
				.sendKeys(data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter data to WerId");
		CommonMethod.RobustclickElementVisible("ApplyButton","AdminIdClick");
		testlog.info("And User clicks on Apply button");
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("AdminIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");
		CommonMethod.RobustclickElementVisible("AdminIdClick","WellV2DashboardTab");
		
		testlog.info("And User clicks on WprAdminId");
		
		}
	
	public void ValidateDocumentInDocumentLibrary(String SheetName, int rowNum, String Access)
			throws Exception {
		/*
		 * For Document tab
		 */

		if (Access.equalsIgnoreCase("true")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
				CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink",
						PortfolioAndRatingLocAccDocumentTable);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
				CommonMethod.scrolldowntoElement(PortfolioAndRatingLocAccDocumentTable);
				rc.deleteButtonTooltipMessage();
				Thread.sleep(2000);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingDocRestrictDeleteAccesspopup", 0);
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getattributeValueByTextContent("RatingDocRestrictDeleteAccesspopup"),
						toolTipMessage, "Delete Restriction Error Mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CancelTooltipPopUpInDocument", 0);
			CommonMethod.RobustclickElementVisible("CancelTooltipPopUpInDocument", PortfolioAndRatingLocAccDocumentTable);
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
			CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink",
					PortfolioAndRatingLocAccDocumentTable);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
			CommonMethod.scrolldowntoElement(PortfolioAndRatingLocAccDocumentTable);
			rc.documentTableDeleteButton();

		}
	}
	
	public void ValidatePTAInsideScorecard(String SheetName, int rowNum, String FeatureName, String Access)
			throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTab", 0);
		CommonMethod.JavascriptClickElement("ScorecardTab");
		testlog.info("And User clicks on ScorecardTab");
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplicableVersion", 0);
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		List<WebElement> Feature = CommonMethod.findElements("V2ProjectWPRPFeature");
		testlog.info("Fetching total no. of credits on page");
		System.out.println("FeatureName: " + FeatureName);
		testlog.info("TaskName : " + FeatureName);
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						PortfolioAndRatingLocAccDocumentTable);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadbtn");
				Thread.sleep(2000);
				if (Access.equalsIgnoreCase("true")) {
					rc.documentTableDeleteButton();

				} else {
				rc.deleteButtonTooltipMessage();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingDocRestrictDeleteAccesspopup", 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("RatingDocRestrictDeleteAccesspopup"),toolTipMessage,
								"Delete Restriction Error Mismatch");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CancelTooltipPopUpInScorecard", 0);
				CommonMethod.RobustclickElementVisible("CancelTooltipPopUpInScorecard", "PtDeleteIcon");
				}
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilPresence("ApplicableVersion", Scorecardtimeout);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
	}
	
	public void locationDisable() throws Exception {
		rc.locationNavigate();
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioLocationsImportButton");
		CommonMethod.negativeAssertElementNotPresentFalse("PortfolioLocationsImportButton","Import location is present");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("DisabledAddButton",1);
		CommonMethod.negativeAssertElementPresentTrue("DisabledAddButton","Add location is not present");
	}
	
	public void WERPurseScorecard(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTab", 0);
		CommonMethod.RobustclickElementVisible("ScorecardTab", "ApplicableVersion");
		testlog.info("And User clicks on ScorecardTab");
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplicableVersion", 0);
		// MayBe
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERPurseSelectMaybebtn", 0);
		CommonMethod.JavascriptClickElement("WERPurseSelectMaybebtn");
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 30)) {
			CommonMethod.WaitUntilVisibility("PortfolioScorecardPursueToast", 120);
			CommonMethod.WaitUntilInVisibility("PortfolioScorecardPursueToast", 60);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERPurseSelectMaybebtn1", 0);
		CommonMethod.JavascriptClickElement("WERPurseSelectMaybebtn1");
		rc.confirmPursing();
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 20)) {
			CommonMethod.WaitUntilVisibility("PortfolioScorecardPursueToast", 120);
			CommonMethod.WaitUntilInVisibility("PortfolioScorecardPursueToast", 60);
		}
		// No
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERPurseSelectNobtn", 0);
		CommonMethod.JavascriptClickElement("WERPurseSelectNobtn");
		if (CommonMethod.isElementsExist("DatePickerConfirmButton", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerConfirmButton", 0);
			CommonMethod.RobustclickElementVisible("DatePickerConfirmButton", "PortfolioScorecardPursueToast");
		}
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 20)) {
			CommonMethod.WaitUntilVisibility("PortfolioScorecardPursueToast", 120);
			CommonMethod.WaitUntilInVisibility("PortfolioScorecardPursueToast", 60);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERPurseSelectNo1btn", 0);
		CommonMethod.JavascriptClickElement("WERPurseSelectNo1btn");
		if (CommonMethod.isElementsExist("WERScoreCardConfirmbtn", 15)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERScoreCardConfirmbtn", 0);
			CommonMethod.RobustclickElementVisible("WERScoreCardConfirmbtn", "PortfolioScorecardPursueToast");
		}
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 20)) {
			CommonMethod.WaitUntilVisibility("PortfolioScorecardPursueToast", 120);
			CommonMethod.WaitUntilInVisibility("PortfolioScorecardPursueToast", 60);
		}
		testlog.pass("**Verifies the 15 Purse Yes Scorecard Performance successfully**");
	}
	
	public void ValidatePostReviewInScorecardFeature(String SheetName, int rowNum, String FeatureName, String Commodity) throws Exception {
		
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
		Feature = CommonMethod.findElements("RatingFeatureName");
		boolean flag = false;
		testlog.info("Fetching total no. of credits on page");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRVerficationTab", 0);
				CommonMethod.RobustclickElementVisible("WPRVerficationTab", "PortfolioScorecardPurseLocCount");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPurseLocCount", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
				if (!CommonMethod.isElementsExist("ScorecardTablePendingStatus", 20)) {
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardPurseLocCount"),
						"6 Achieved", "Feature Achieved location Count doesn't match");
				CommonMethod.RobustclickElementVisible("PortfolioTaskListEditLocation", "WERAssignLocationsModalStatus");
				List<WebElement> getStatus = CommonMethod.findElements("WERAssignLocationsModalStatus");
				testlog.info("Fetching Data from Upload Table");
                for(int i=0; i<getStatus.size(); i++) {
					String getEachStatus = getStatus.get(i).getText();
					CommonMethod.negativesoftassertFieldValid(getEachStatus, "Achieved", "Status Achieved doesn't matched");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
				CommonMethod.Robustclick("AssignLocCloseIcon");	
				CommonMethod.scrollDown();
				ValidatePostReview(SheetName, rowNum, FeatureName, Commodity);
				}
				else {
					CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardPurseLocCount"),
							"6 Pursuing", "Feature Achieved location Count doesn't match");
					CommonMethod.RobustclickElementVisible("PortfolioTaskListEditLocation", "WERAssignLocationsModalStatus");
					List<WebElement> getStatus = CommonMethod.findElements("WERAssignLocationsModalStatus");
					testlog.info("Fetching Data from Upload Table");
	                for(int i=0; i<getStatus.size(); i++) {
						String getEachStatus = getStatus.get(i).getText();
						CommonMethod.negativesoftassertFieldValid(getEachStatus, "Pursuing", "Status Achieved doesn't matched");
					}
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
					CommonMethod.Robustclick("AssignLocCloseIcon");	
					CommonMethod.scrollDown();
					CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioScoreCardValidateEnabledUploadButton",1);
					CommonMethod.negativeAssertElementPresentTrue("PortfolioScoreCardValidateEnabledUploadButton","Enabled UploadButton is not present");
					ValidatePostReview(SheetName, rowNum, FeatureName, Commodity);
				}
				CommonMethod.JavascriptClickElement(ele);
				testlog.pass("**Validate Post Review successfully**");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
	}
	
	public void ValidatePostReviewInScorecardAudit(String SheetName, int rowNum, String FeatureName, String Commodity) throws Exception {
		
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
		Feature = CommonMethod.findElements("RatingFeatureName");
		boolean flag = false;
		testlog.info("Fetching total no. of credits on page");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRVerficationTab", 0);
				CommonMethod.RobustclickElementVisible("WPRVerficationTab", "PortfolioScorecardAuditPurseLocCount");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardAuditPurseLocCount", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
				if (!CommonMethod.isElementsExist("ScorecardTablePendingStatus", 20)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardAuditAcheiveLocCount", 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardAuditAcheiveLocCount"),
						"2 Achieved", "Audit Achieved location Count doesn't match");			
				CommonMethod.RobustclickElementVisible("PortfolioTaskListEditLocation", "WERAssignLocationsModalStatus");
				List<WebElement> getStatus = CommonMethod.findElements("WERAssignLocationsModalStatus");
				testlog.info("Fetching Data from Upload Table");
				for(int i=0; i<getStatus.size(); i++) {
					String getEachStatus = getStatus.get(i).getText();
					CommonMethod.negativesoftassertFieldValid(getEachStatus, "Achieved", "Status Achieved doesn't matched");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
				CommonMethod.Robustclick("AssignLocCloseIcon");	
				CommonMethod.scrollDown();
				ValidatePostReview(SheetName, rowNum, FeatureName, Commodity);
				}
				else {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardAuditPurseLocCount", 0);
					CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardAuditPurseLocCount"),
							"6 Pursuing", "Audit Achieved location Count doesn't match");			
					CommonMethod.RobustclickElementVisible("PortfolioTaskListEditLocation", "WPRAssignLocCbx");
					List<WebElement> getStatus = CommonMethod.findElements("WERAssignLocationsModalStatus");
					testlog.info("Fetching Data from Upload Table");
					for(int i=0; i<getStatus.size(); i++) {
						String getEachStatus = getStatus.get(i).getText();
						CommonMethod.negativesoftassertFieldValid(getEachStatus, "Pursuing", "Status Achieved doesn't matched");
					}
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
					CommonMethod.Robustclick("AssignLocCloseIcon");	
					CommonMethod.scrollDown();
					ValidatePostReview(SheetName, rowNum, FeatureName, Commodity);
				}
				CommonMethod.JavascriptClickElement(ele);
				testlog.pass("**Validate Post Review successfully**");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
	}
	

    public void ValidatePostReview(String SheetName, int rowNum, String FeatureName, String Commodity) throws Exception {	 
	pathprms.put("Status", "Reviewed");
	generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity,false, false, false, false, false, true, true, false);
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERScorecardTableOptions", 0);
	List<String> OptionsText = new ArrayList<>();
	OptionsText.add("Fulfills Part");
	OptionsText.add("Approved");
	OptionsText.add("Pending");
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WERScorecardTableOptions"), 
	OptionsText, "Feature OR Audit Table Options Text doesn't match");
	testlog.info("And User verifies Options Text In Document Table List");
	rc.documentTableEditButton();
	CommonMethod.negativeAssertElementPresentTrue("ScorecardUploadEditDocumentLink", "Remove button is invisible");
	testlog.info("And User verifies Document link");
	List<String> Featurestatus = new ArrayList<>();
	Featurestatus.add("Fulfills Part");
	Featurestatus.add("Approved");
	Featurestatus.add("Pending");
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardReviewStatusInAddPartLink"), 
	Featurestatus, "Feature Review Status In AddPart Link list doesn't match");
	testlog.info("And User verifies Review Status In AddPart Link");
  }
    
    public void ValidatePostReviewArchiveDocumentFunctionalityInScorecard(String SheetName, int rowNum, String featureName, String Commodity) throws Exception {
    	String featureXpath;
    	if(Commodity.equalsIgnoreCase("Portfolio")) {
    		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardFeature", 0);
    		featureXpath ="PortfolioScoreCardFeature";
    	}
    	else {
    		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
    		featureXpath ="RatingFeatureName";
    	}
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(featureXpath, 0);
    	testlog.info("Given User is on Scorecard page");
    	boolean flag = false;
		List<WebElement> Feature = CommonMethod.findElements(featureXpath);
		testlog.info("Fetching total no. of credits on page");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						"PortfolioTaskListEditLocation");
				testlog.info("And User clicks on VerificationTab");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
				testlog.info("And User will be redirected to Document Upload Table page");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);	
				CommonMethod.moveToElement("PortfolioAndRatingDeleteEditMenu");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardArchiveMenuBtn", 0);
				CommonMethod.JavascriptClickElement("PortfolioScorecardArchiveMenuBtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardArchiveBtn", 0);
				CommonMethod.JavascriptClickElement("PortfolioScorecardArchiveBtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardArchivedText", 1);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardShowArchivedDocumentsToggle", 0);
				CommonMethod.JavascriptClickElement("PortfolioScorecardShowArchivedDocumentsToggle");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardArchivedText", 0);
				CommonMethod.ScrollUpToElement("PortfolioScorecardArchivedText");
				String ArchivedText = CommonMethod.getattributeValueByTextContent("PortfolioScorecardArchivedText");
				CommonMethod.negativesoftassertFieldValid("Archived", ArchivedText,
						"Archived Document Status does not matched");			
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioArchivedDocActionMenu", 0);
				CommonMethod.assertisElementPresentTrue("PortfolioArchivedDocActionMenu", "Archived Document Action Menu icon is enabled ");
				CommonMethod.JavascriptClickElement(ele);
				testlog.pass("**Validate Post Review successfully**");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
    }

    public void ValidatePostReviewArchiveDocumentFunctionalityInDocumentsLibrary(String SheetName, int rowNum, String Commodity, String VerificationMethod) throws Exception {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocumentFilterOption", 0);
		CommonMethod.RobustclickElementVisible("WPRDocumentFilterOption","PortfolioAndRatingLocAccDocumentTable");
		if(VerificationMethod.equalsIgnoreCase("Feature")) {			
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentsFeatureMenuIcon", 0);
		CommonMethod.moveToElement("PortfolioAndRatingDeleteEditMenu");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentsMenuFeatureArchiveBtn", 0);
		CommonMethod.JavascriptClickElement("PortfolioDocumentsMenuFeatureArchiveBtn");
	    }
		if(VerificationMethod.equalsIgnoreCase("Audit")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentsAuditMenuIcon", 0);
		CommonMethod.moveToElement("PortfolioDocumentsAuditMenuIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentsMenuAuditArchiveBtn", 0);
		CommonMethod.JavascriptClickElement("PortfolioDocumentsMenuAuditArchiveBtn");
		}
		if(VerificationMethod.equalsIgnoreCase("Alternative")) {
			if (CommonMethod.isElementsExist("PortfolioDocumentsAlternativeMenuIcon", 20)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentsAlternativeMenuIcon", 0);
				CommonMethod.moveToElement("PortfolioDocumentsAlternativeMenuIcon");
				//CommonMethod.RobustclickElementVisible("PortfolioDocumentsAlternativeMenuIcon", "PortfolioDocumentsMenuAlternativeArchiveBtn");	
		
			}
			else {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioV2ProjectDocumentScorecardNextPagnition", 0);
				CommonMethod.RobustclickElementVisible("PortfolioV2ProjectDocumentScorecardNextPagnition",
						"PortfolioDocumentsAlternativeMenuIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentsAlternativeMenuIcon", 0);
				CommonMethod.RobustclickElementVisible("PortfolioDocumentsAlternativeMenuIcon", "PortfolioDocumentsMenuAlternativeArchiveBtn");	
			}	
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTableArchiveIcon", 0);
			CommonMethod.RobustclickElementVisible("ScorecardTableArchiveIcon","PortfolioScorecardArchiveBtn");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardArchiveBtn", 0);
		CommonMethod.JavascriptClickElement("PortfolioScorecardArchiveBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardArchivedText", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
		CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardShowArchivedDocumentsToggle", 0);
		CommonMethod.JavascriptClickElement("PortfolioScorecardShowArchivedDocumentsToggle");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERDocumentTablePageSevenNav", 0);
		CommonMethod.scrolldowntoElement("PortfolioAndRatingLocAccDocumentTable");
		CommonMethod.click("WERDocumentTablePageSevenNav");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardArchivedText", 0);
		CommonMethod.ScrollUpToElement("PortfolioScorecardArchivedText");
		String ArchivedText = CommonMethod.getattributeValueByTextContent("PortfolioScorecardArchivedText");
		CommonMethod.negativesoftassertFieldValid("Archived", ArchivedText,
				"Archived Document Status does not matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioArchivedDocActionMenu", 0);
		CommonMethod.assertisElementPresentTrue("PortfolioArchivedDocActionMenu", "Archived Document Action Menu icon is enabled ");
		CommonMethod.refreshBrowser();
      }
	
	public void purseYesToMaybeValidFromScorecard(String SheetName, int rowNum, String Commodity) throws Exception {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERScorecardPurseYesToMaybeSelectedForCDAAP", 0);
		CommonMethod.JavascriptClickElement("WERScorecardPurseYesToMaybeSelectedForCDAAP");
		testlog.info("And User click on Purse Maybe");
		rc.confirmPursing();
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WERScorecardPurseYesToMaybeSelectedForCDAAP", 1);
		CommonMethod.assertisElementPresentTrue("WERScorecardPurseYesToMaybeSelectedForCDAAP", "PurseMaybe is not selected");
		testlog.info("And User verifies the Selected Purse Maybe");
		testlog.info("Then User verifies the Pursue status message");
		testlog.pass("**Verifies the Pursue Maybe status successful**");
	}
	
	public void purseYesToNoValidFromScorecard(String SheetName, int rowNum, String Commodity) throws Exception {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERScorecardPurseYesToNoSelectedForIID", 0);
		CommonMethod.JavascriptClickElement("WERScorecardPurseYesToNoSelectedForIID");
		testlog.info("And User click on Purse No");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Confirmbtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPurseYesToNoCancel", 0);
		testlog.info("And User verifies confirm button");
		testlog.info("And User verifies cancel button");
		// cancel
		CommonMethod.Robustclick("PortfolioScorecardPurseYesToNoCancel");
		// Confirm
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERScorecardPurseYesToNoSelectedForIID", 0);
		CommonMethod.Robustclick("WERScorecardPurseYesToNoSelectedForIID");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Confirmbtn", 0);
		CommonMethod.Robustclick("Confirmbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WERScorecardPurseYesToNoSelectedForIID", 1);
		CommonMethod.assertisElementPresentTrue("WERScorecardPurseYesToNoSelectedForIID", "PurseMaybe is not selected");
		testlog.info("And User verifies the Selected Purse Maybe");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPursueToast", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioScorecardPursueToast"),
				"Pursue status changed!", "Purse Status message Doesn't match");
		testlog.info("Then User verifies the Pursue status message");
		testlog.pass("**Verifies the Pursue Maybe status successful**");
	}
	
	public void SearchPerformanceByIDAndCompleteTheForm(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar","WELLEquityNavBar");
		CommonMethod.RobustclickElementVisible("WELLEquityNavBar","RatingIdClick");
		CommonMethod.verifyDropdownValues("HSRTypeDropdown", "HsrType");
		testlog.info("When User clicks on WELL PerformanceRating from top menu under Projects");
		String werId = data.getCellData(SheetName, "ProjectID", rowNum);
		testlog.info("Equity ID: " + werId);
		CommonMethod.WaitUntilClickble("WPRWERId", 60).sendKeys(werId);
		testlog.info("And User enter data to WerId");
		CommonMethod.RobustclickElementVisible("ApplyButton","SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int var = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var), "1", "Equity Search failed");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingIdClick", 0);
		CommonMethod.RobustclickElementVisible("RatingIdClick","Locationsize");
		String Area = CommonMethod.randomNumberBetweenRanges(100, 50000);
		testlog.info("Locationsize: " + Area);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Locationsize", 0);
		CommonMethod.clearAndSendKey("Locationsize", Area);
		testlog.info("And User enter the Location Size");
		data.setCellData(SheetName, "WERlocationsize", rowNum, CommonMethod.getattributeValue("Locationsize"));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WEROwnerRegContinuebtn", 0);
		CommonMethod.JavascriptClickElement("WEROwnerRegContinuebtn");
		testlog.info("And User clicks on continue button");
		if (CommonMethod.isElementsExist("HsrWPRYesMyOrganizationCbx", 10)) {
			CommonMethod.ClickCheckbox("HsrWPRYesMyOrganizationCbx");
			testlog.info("And User checks the MyOrganization checkbox");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRWERReviewContinuebutton", 0);
		CommonMethod.JavascriptClickElement("WPRWERReviewContinuebutton");
		testlog.info("And User clicks on continue button");
		if (CommonMethod.isElementsExist("WERProgramFeePublicrbtn", 10)) {
			CommonMethod.ClickCheckbox("WERProgramFeePublicrbtn");
			testlog.info("And User checks the ProgramFee checkbox");
		}
		CommonMethod.scrollDown();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERAcknowledecbx", 0);
		CommonMethod.ClickCheckbox("WERAcknowledecbx");
		testlog.info("And User checks the Acknowledge checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRWERtermContinuebutton", 0);
		CommonMethod.RobustclickElementVisible("WPRWERtermContinuebutton", "BillingLanding");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.info("Then User will be redirected to BillingLanding page");
		testlog.pass("**Verifies the Registration successful**");
	}
	
	public void ScoreCardDocumentCopyAndStoreOldDocumentIdInExcel(String FeatureName, String SheetName, int rowNum, String Commodity,
			Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
			Boolean NoteRequired, Boolean IntentCheckboxRequired) throws Exception {
		
		List<WebElement> Feature=CommonMethod.findElements("RatingFeatureName");
		boolean flag = false;
		testlog.info("Fetching total no. of credits on page");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRVerficationTab", 0);
				CommonMethod.RobustclickElementVisible("WPRVerficationTab", "Uploadbutton");
				CommonMethod.scrolldowntoElement("Uploadbutton");				
				rc.RemoveSpaceAndValidate("WERDocumentIdText", "Document ID:"); 
				rc.RemoveSpaceAndValidate("WERDocumentIdCopyBtn", "Copy");					
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERDocumentIdText", 0);
				String getDocumentId = CommonMethod.getattributeValueByTextContent("WERDocumentIdText");
				getDocumentId= getDocumentId.replaceAll("\\s+", " ").trim();
				String[] splitFromColon = getDocumentId.split(":");
				String getFromColon = splitFromColon[1];	
				String getDocumentID = getFromColon.substring(1, 7);
				if(SheetName.equalsIgnoreCase("Wer")) {
				data.setCellData(SheetName, "WerOldDocumentId", rowNum, getDocumentID);
			    }
				else if(SheetName.equalsIgnoreCase("Wpr")) {
				data.setCellData(SheetName, "WprOldDocumentId", rowNum, getDocumentID);	
				}
				System.out.println("SplitId: "+getDocumentID);					
				CommonMethod.RobustclickElementVisible("WERDocumentIdCopyBtn", "HsrDocumentIdCopiedToaster");
				if(CommonMethod.isElementsExist("getToasterMessage", 20)) {
				rc.RemoveSpaceAndValidate("getToasterMessage", "Document ID copied!");
			    }
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
	
	public void EquityValidateOldDocumentId(String FeatureName, String SheetName, int rowNum, String Commodity,
			String FileName, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
			Boolean NoteRequired, Boolean IntentCheckboxRequired) throws Exception {
		
		List<WebElement> Feature=CommonMethod.findElements("RatingFeatureName");
		boolean flag = false;
		testlog.info("Fetching total no. of credits on page");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRVerficationTab", 0);
				CommonMethod.RobustclickElementVisible("WPRVerficationTab", "WPRAddOption");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOption", 0);
				CommonMethod.JavascriptClickElement("WPRAddOption");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionbtn", 0);
				CommonMethod.RobustclickElementVisible("WPRAddOptionbtn", "ScorecardAddedOption");
				testlog.info("And User clicks on save button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAddedOption", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionCloseIcon", 0);
				CommonMethod.Robustclick("WPRAddOptionCloseIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRAddOptionCloseIcon", 1);
				generic.assignLocationGeneric(Commodity, true, false, false, false, false);
				/** Upload Document for Tasks */
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTaskListEditLocation", 0);
				CommonMethod.scrolldowntoElement("PortfolioTaskListEditLocation");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.JavascriptClickElement("Uploadbutton");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddNote", 0);
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationAddNote");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrDocumentIdTextBox", 0);
				if(SheetName.equalsIgnoreCase("Wer")) {
			        String getDocumentID = data.getCellData(SheetName, "WerOldDocumentId", rowNum);
			        System.out.println("My DocumentID: "+getDocumentID);
					CommonMethod.sendKeys("HsrDocumentIdTextBox", getDocumentID);
			    }
				else if(SheetName.equalsIgnoreCase("Wpr")) {
			        String getDocumentID = data.getCellData(SheetName, "WprOldDocumentId", rowNum);
			        System.out.println("My DocumentID: "+getDocumentID);
				    CommonMethod.sendKeys("HsrDocumentIdTextBox", getDocumentID);
				}			
		        rc.ScorecardUploadSaveButton();			        
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocFirstChildCbx", 0);
				CommonMethod.ClickCheckbox("WPRAssignLocFirstChildCbx");
				rc.ScorecardConfirmLocUploadSaveButton();				        				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.scrolldowntoElement("Uploadbutton");
				pathprms.put("Status", "Ready For Review");
				generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, false, false, false,
						false, true, true, false);
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
}