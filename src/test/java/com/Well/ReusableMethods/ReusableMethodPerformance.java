package com.Well.ReusableMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class ReusableMethodPerformance extends BaseClass {
	String PortfolioAndRatingLocAccDocumentTable ="PortfolioAndRatingLocAccDocumentTable";
	
	public void RegisterPerformance(String SheetName, int rowNum, String DataValidate, String ProjectName)
			throws IOException, InterruptedException {
		
		testlog.info("Given User logged in to the WELL certified account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLPerformanceRatingNavBar");
		CommonMethod.RobustclickElementVisible("WELLPerformanceRatingNavBar", "WPRstartNewProject");
		testlog.info("When User clicks on WELL PerformanceRating from top menu under Projects");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRstartNewProject", 0);
		CommonMethod.RobustclickElementVisible("WPRstartNewProject", "WPREnrollOption");
		testlog.info("And User clicks on Start project button");
		CommonMethod.RobustclickElementVisible("WPREnrollOption", "WPRenrollbtn");
		testlog.info("And User clicks on EnrollNow button");
		CommonMethod.RobustclickElementVisible("WPRenrollbtn", "WPRWEROrgContinebtn");
		testlog.info("And User clicks on Enroll button");
		String AccountName = ProjectName + CommonMethod.randomNumber(8000000);
		testlog.info("ProjectName: " + AccountName);
		data.setCellData(SheetName, "projectName", rowNum, AccountName);
		if (DataValidate.equalsIgnoreCase("true")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRWEROrgContinebtn", 0);
		CommonMethod.RobustclickElementVisible("WPRWEROrgContinebtn", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Organization is required.", "Organization Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner name is required.", "Owner Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Organization Industry is required.",
				"Organization Industry Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Consultant is required.", "Consultant Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Country is required.", "Country Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Street is required.", "Street Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("City is required.", "City Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Postal Code is required.", "Postal Code Error Mismatch");
		testlog.info(
				"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		}
		CommonMethod.verifyDropdownValues("HsrWprEditOrgIndustry", "OrganizationIndustry");
//		CommonMethod.verifyDropdownValues("WPRConsultant", "Consultant");
		CommonMethod.verifyDropdownValues("WPRExamOwnerCountry", "Country");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPROrgName", 0);
		CommonMethod.sendKeys("WPROrgName", AccountName);
		testlog.info("And User enter data to EnrollName");
		rc.SelectOwnerOrg(SheetName, rowNum);
		data.setCellData(SheetName, "OrgName", rowNum, CommonMethod.getText("OrgName"));
		testlog.info("OrganizationName: " + data.getCellData(SheetName, "OrgName", rowNum));
		String Ownername = USfaker.address().firstName();
		CommonMethod.WaitUntilPresence("WPROwnerName", 30);
		CommonMethod.sendKeys("WPROwnerName", Ownername);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("WPROwnerName"));
		CommonMethod.selectdropdownrandom("OrgIndustry");
		data.setCellData(SheetName, "OrgIndustry", rowNum, CommonMethod.getSelectedDropdownValue("OrgIndustry"));
		 rc.SelectEnterpriseProviders(SheetName,rowNum);
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WEROwnercbx", 0);
			CommonMethod.ClickCheckbox("WEROwnercbx");
			CommonMethod.VerifyRadioOrCheckboxSelcted("Hsrbilladdcheckbox");
			CommonMethod.ClickCheckbox("Hsrbilladdcheckbox");
			testlog.info("And User checks the Billing checkbox");
			CommonMethod.ClickCheckbox("WPROwnerInfocbx");
			testlog.info("And User checks the enroll checkbox");
		if(DataValidate.equalsIgnoreCase("MainlandChina")) {
		CommonMethod.selectdropdownValue("WPRExamOwnerCountry", "CN");
		CommonMethod.selectdropdownVisibletext("WPRExamOwnerState", "Beijing");	
	    } else {
		CommonMethod.selectdropdownValue("WPRExamOwnerCountry", "US");
		testlog.info("Country: " + CommonMethod.getSelectedDropdownAttribute("WPRExamOwnerCountry"));
		CommonMethod.selectdropdownrandom("WPRExamOwnerState");
	    }
		String ProjectAddress = USfaker.address().streetAddress();
		String ProjectCity = USfaker.address().cityName();
		String PostalCode = USfaker.address().zipCode();
		CommonMethod.sendKeys("WPRExamOrgAddress", ProjectAddress);
		CommonMethod.sendKeys("WPRExamOrgCity", ProjectCity);
		CommonMethod.sendKeys("WPRExamOrgPostalcode", PostalCode);
		data.setCellData(SheetName, "Country", rowNum,
				CommonMethod.getSelectedDropdownAttribute("WPRExamOwnerCountry"));
		data.setCellData(SheetName, "State", rowNum, CommonMethod.getattributeValue("WPRExamOwnerState"));
		data.setCellData(SheetName, "Street", rowNum, CommonMethod.getattributeValue("WPRExamOrgAddress"));
		data.setCellData(SheetName, "City", rowNum, CommonMethod.getattributeValue("WPRExamOrgCity"));
		data.setCellData(SheetName, "PostalCode", rowNum, CommonMethod.getattributeValue("WPRExamOrgPostalcode"));
		testlog.info("And User enter data to Owner Country, State, Street address, City and Postal Code fields");
		testlog.info("Organization Address: " + ProjectAddress);
		testlog.info("Organization City: " + ProjectCity);
		testlog.info("Organization Postalcode: " + PostalCode);
		CommonMethod.ClickCheckbox("WEROwnercbx");
		testlog.info("And User checks the Owner checkbox");
		CommonMethod.scrollDown();
		CommonMethod.RobustclickElementVisible("WPRWEROrgContinebtn", "WPROwnerRegContinuebtn");
		testlog.info("And User clicks on continue button");
		if (DataValidate.equalsIgnoreCase("true")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BackButton2", 0);
			CommonMethod.RobustclickElementVisible("BackButton2", "WPRWEROrgContinebtn");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("WPROrgName"), ProjectName,
					"ProjectName Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("OrgName"),
					data.getCellData(SheetName, "OrgName", rowNum), "Org Name Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("OrgIndustry"),
					data.getCellData(SheetName, "OrgIndustry", rowNum), "Org Industry Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownAttribute("WPRExamOwnerCountry"),
					data.getCellData(SheetName, "Country", rowNum), "Country Name Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("WPRExamOwnerState"),
					data.getCellData(SheetName, "State", rowNum), "State Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("WPRExamOrgAddress"),
					data.getCellData(SheetName, "Street", rowNum), "Street Name Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("WPRExamOrgCity"),
					data.getCellData(SheetName, "City", rowNum), "City Name Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("WPRExamOrgPostalcode"),
					data.getCellData(SheetName, "PostalCode", rowNum), "Postal code Error Mismatch");
			testlog.info("And User verifies the added details about you by clicking on back button");
			CommonMethod.VerifyRadioOrCheckboxSelcted("WEROwnercbx");
			CommonMethod.VerifyRadioOrCheckboxSelcted("Hsrbilladdcheckbox");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRWEROrgContinebtn", 0);
			CommonMethod.JavascriptClickElement("WPRWEROrgContinebtn");
			testlog.info("And User clicks on continue button");
			testlog.info("And User verifies the added details about you by clicking on back button");
		}
		Thread.sleep(2000);
		CommonMethod.scrollUp();
		if (DataValidate.equalsIgnoreCase("true")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPROwnerRegContinuebtn", 0);
		CommonMethod.RobustclickElementVisible("WPROwnerRegContinuebtn", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("On behalf of owner is required.", "Owner CheckBox Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Is the Owner organization an IWBI member?* is required.",
				"Owner Organization Name Error Mismatch");
		testlog.info(
				"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		}
		CommonMethod.ClickCheckbox("WPRBehalfCbx");
		testlog.info("And User checks the Behalf checkbox");
		CommonMethod.selectdropdownVisibletext("WPRSelectMember", "No");
		testlog.info("And User select Iwbi member");
		data.setCellData(SheetName, "WPRSelectMember", rowNum,
				CommonMethod.getSelectedDropdownValue("WPRSelectMember"));
		CommonMethod.JavascriptClickElement("WPROwnerRegContinuebtn");
		Thread.sleep(2000);
		CommonMethod.scrollUp();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRlocations", 0);
		CommonMethod.sendKeys("WPRlocations", "10");
		testlog.info("And User enter data to location");
		data.setCellData(SheetName, "WPRlocations", rowNum, CommonMethod.getattributeValue("WPRlocations"));
		CommonMethod.RobustclickElementVisible("Multiselect", "HsrWPRlocationsSpaceOption");
		CommonMethod.RobustclickElementVisible("HsrWPRlocationsSpaceOption", "Locationsize");
		testlog.info("And User select the space type");
		Thread.sleep(1000);
		CommonMethod.scrollDown();
		String Area = CommonMethod.randomNumberBetweenRanges(100, 50000);
		testlog.info("Locationsize: " + Area);
		CommonMethod.clearAndSendKey("Locationsize", Area);
		testlog.info("And User enter the Location Size");
		data.setCellData(SheetName, "Locationsize", rowNum, CommonMethod.getattributeValue("Locationsize"));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPROwnerRegContinuebtn", 0);
		CommonMethod.JavascriptClickElement("WPROwnerRegContinuebtn");
		testlog.info("And User clicks on continue button");
		if (DataValidate.equalsIgnoreCase("true")) {
			CommonMethod.RobustclickElementVisible("BackButton", "WPROwnerRegContinuebtn");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("WPRlocations"),
					data.getCellData(SheetName, "WPRlocations", rowNum), "Location Value Error Mismatch");
			// CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("Multiselect"),data.getCellData(SheetName,
			// "WPRSelectMember", rowNum), "Location Value Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("Locationsize"),
					data.getCellData(SheetName, "Locationsize", rowNum), "Area Size Error Mismatch");
			CommonMethod.JavascriptClickElement("WPROwnerRegContinuebtn");
			testlog.info("And User verifies the added details about you by clicking on back button");
		}
		if (CommonMethod.isElementsExist("HsrWPRYesMyOrganizationCbx", 5)) {
			CommonMethod.RobustclickElementVisible("WPRWERReviewContinuebutton", "MandatoryFieldErrorMessage");
			testlog.info("And User clicks on continue button");
			CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
			CommonMethod.negativesoftassertPageSource(
					"Yes, my organization meets the criteria of the listed discount category.* is required.",
					"My Organization CheckBox Error Name");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWPRYesMyOrganizationCbx", 0);
			CommonMethod.ClickCheckbox("HsrWPRYesMyOrganizationCbx");
			testlog.info("And User checks the MyOrganization checkbox");
		}
		CommonMethod.RobustclickElementVisible("WPRWERReviewContinuebutton", "WPRWERtermContinuebutton");
		if (CommonMethod.isElementsExist("WPRProgramFeePublicrbtn", 10)) {
			CommonMethod.ClickCheckbox("WPRProgramFeePublicrbtn");
			testlog.info("And User checks the Program Fee Public checkbox");
		}
		CommonMethod.scrollDown();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAcknowledecbx", 0);
		CommonMethod.ClickCheckbox("WPRAcknowledecbx");
		testlog.info("And User checks the Acknowledge checkbox");
		CommonMethod.RobustclickElementVisible("WPRWERtermContinuebutton", "BillingLanding");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.info("Then User will be redirected to BillingLanding page");
		testlog.pass("**Verifies the Registration successful**");
	}

	public void StoreIdPerformance(String SheetName, int rowNum) throws IOException, InterruptedException {		
		if (CommonMethod.isElementsExist("HsrIframe", 5)) {
			CommonMethod.WaitUntilPresence("HsrIframe", 180);
			CommonMethod.switchToFrame("HsrIframe");
			CommonMethod.WaitUntilPresence("HsrCloseCard", 60);
			CommonMethod.Robustclick("HsrCloseCard");
			CommonMethod.switchToParentFrame();
		}
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("StoreId", 0);
		String getId = CommonMethod.getText("StoreId");
		String[] stringArray = getId.split(": ");
		String getWprId = stringArray[1].trim();
		testlog.info("Performance Id: " + getWprId);
		data.setCellData(SheetName, "ProjectID", rowNum, getWprId);
		testlog.info("Performance ID: " + getWprId);
		testlog.info("When User storing Performance ID in excel");
		testlog.pass("**Stored the Registered id  into excel successfully**");
	}

	public void SearchPerformanceByID(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLPerformanceRatingNavBar");
		CommonMethod.WaitUntilVisibility("WELLPerformanceRatingNavBar", 300);
		CommonMethod.RobustclickElementVisible("WELLPerformanceRatingNavBar", "RatingIdClick");
		CommonMethod.verifyDropdownValues("HSRTypeDropdown", "HsrType");
		testlog.info("When User clicks on WELL PerformanceRating from top menu under Projects");
		String wprId = data.getCellData(SheetName, "ProjectID", rowNum);
		testlog.info("Performance ID: " + wprId);
		CommonMethod.WaitUntilClickble("WPRWERId", 60).sendKeys(wprId);
		testlog.info("And User enter data to WprId");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int ProjectCount = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount), "1", "HealthSafety Search failed");
		CommonMethod.sendKeys("HsrNameList", data.getCellData(SheetName, "projectName", rowNum));
		testlog.info("And User enter data to WprName");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int ProjectNameCount = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectNameCount), "1", "Portfolio Search failed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RatingIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project Id doesn't matches in search");
		CommonMethod.RobustclickElementVisible("RatingIdClick", "WellV2DashboardTab");
		testlog.info("And User clicks on WprAdminId");
		testlog.info("Then User verifies WprId in search filter");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		
		testlog.pass("**Verifies the Search Performance ByID successfully**");
	}
	
	public void SearchPerformanceProjectId(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLPerformanceRatingNavBar");
		CommonMethod.WaitUntilVisibility("WELLPerformanceRatingNavBar", 300);
		CommonMethod.RobustclickElementVisible("WELLPerformanceRatingNavBar", "RatingIdClick");
		CommonMethod.verifyDropdownValues("HSRTypeDropdown", "HsrType");
		testlog.info("When User clicks on WELL PerformanceRating from top menu under Projects");
		String wprId = data.getCellData(SheetName, "ProjectID", rowNum);
		testlog.info("Performance ID: " + wprId);
		CommonMethod.WaitUntilClickble("WPRWERId", 60).sendKeys(wprId);
		testlog.info("And User enter data to WprId");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int ProjectCount = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount), "1", "HealthSafety Search failed");
		CommonMethod.sendKeys("HsrNameList", data.getCellData(SheetName, "projectName", rowNum));
		testlog.info("And User enter data to WprName");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int ProjectNameCount = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectNameCount), "1", "Portfolio Search failed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RatingIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project Id doesn't matches in search");
	}
	
	public void ClickPerformanceProjectId(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingIdClick", 0);
		CommonMethod.RobustclickElementVisible("RatingIdClick", "WellV2DashboardTab");
		testlog.info("And User clicks on WprAdminId");
		testlog.info("Then User verifies WprId in search filter");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.pass("**Verifies the Search Performance ByID successfully**");
		
	}

	public void SearchPerformanceFilterStatus(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLPerformanceRatingNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WELLPerformanceRatingNavBar", 0);
		CommonMethod.RobustclickElementVisible("WELLPerformanceRatingNavBar", "RatingIdClick");
		testlog.info("When User clicks on WELL PerformanceRating from top menu under Projects");
		String wprId = data.getCellData(SheetName, "ProjectID", rowNum);
		testlog.info("Performance ID: " + wprId);
		CommonMethod.WaitUntilClickble("WPRWERId", 60).sendKeys(wprId);
		testlog.info("And User enter data to WprId");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int ProjectNameCount = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectNameCount), "1", "HealthSafety Search failed");
		CommonMethod.sendKeys("HsrNameList", data.getCellData(SheetName, "HsrName", rowNum));
		testlog.info("And User enter data to WprName");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int ProjectCount = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount), "1", "Portfolio Search failed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RatingIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project Id doesn't matches in search");
		if(TestCaseName.contains("PerformanceMarkExpiredAndValidateDashboardBeforeReActivate")) {
		ValidatePerformanceReActivateBtnAndExpiredFilter(); 
		} else {
		String status = CommonMethod.getText("HsrWprStatusResultList");
		testlog.info("Status: " + status);
		CommonMethod.negativesoftassertFieldValid(status, "REGISTERED", "Performance Search failed");
		}
		CommonMethod.RobustclickElementVisible("RatingIdClick", "WellV2DashboardTab");
		testlog.info("And User clicks on WPRId button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.info("Then User verifies WPRStatus in search filter");
		testlog.pass("**Verifies the Search Performance ByID successfully**");
		
	}

	public void ValidDashboardWprField(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentLibraryTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSupportButton", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AlternativesTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BiilingTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TeamTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PromotionTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		testlog.info("Then User verifies the Side Navigation Tab");
		testlog.pass("**Verifies Dashboard fields and SideBar Navigation tab successfully **");
	}

	public void ScorecardfillHSRWPR(int YesEnd, int NoStart, int NoEnd, int DifferencePlusOne, String purseYes,
			String purseNo) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(purseYes, 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(purseNo, 0);
		List<WebElement> YesButton;
		List<WebElement> NoButton;
		Boolean flag = false;
		YesButton = CommonMethod.findElements(purseYes);
		for (int i = 1; i <= YesEnd; i++) {
			long startTime = System.currentTimeMillis();
			int RemainingYes = YesButton.size();
			do {
				if(!((System.currentTimeMillis() - startTime) < 60000)) {
					flag = true;
					System.out.println("Build Stuck");
					break;
				}
				CommonMethod.WaitUntilClickble(purseYes, 60);
				CommonMethod.JavascriptClickElement(purseYes);
				if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 10)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
					}
				Thread.sleep(2000);
				YesButton = CommonMethod.findElements(purseYes);
			} while ((YesButton.size() == RemainingYes));
			RemainingYes--;
			if(flag) {
				System.out.println("Build Exit");
				break;
			}
		}
		NoButton = CommonMethod.findElements(purseNo);
		int j = DifferencePlusOne;
		for (int i = NoStart; i <= NoEnd; i++) {
			long startTime = System.currentTimeMillis();
			int RemainingNo = NoButton.size();
			do {
				if(!((System.currentTimeMillis() - startTime) < 60000)) {
					flag = true;
					System.out.println("Build Stuck");
					break;
				}
				CommonMethod.WaitUntilClickble(NoButton.get(RemainingNo - j), 30);
				CommonMethod.JavascriptClickElement(NoButton.get(RemainingNo - j));
				if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 10)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
				}
				Thread.sleep(1000);
				NoButton = CommonMethod.findElements(purseNo);
			} while ((NoButton.size() == RemainingNo));
			RemainingNo--;
			j--;
			if(flag) {
				System.out.println("Build Exit");
				break;
			}
		}
	}

	public void VerifyReviewErrorMessageByMinScorecardPurseYes() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTab", 0);
		CommonMethod.JavascriptClickElement("ScorecardTab");
		testlog.info("And User clicks on ScorecardTab");
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplicableVersion", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseYes", 0);
		CommonMethod.RobustclickElementVisible("CommonScorecardPurseYes", "PortfolioScorecardPursueToast");
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
			}
		CommonMethod.RobustclickElementVisible("CommonScorecardPurseYes", "PortfolioScorecardPursueToast");
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
			}
		// Verify Review ErrorMessage ByMinScorecardPurseYes
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "WPRReviewSubmitbtn");
		testlog.info("When User clicks on Review button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewSubmitbtn", 0);
		CommonMethod.RobustclickElementVisible("WPRReviewSubmitbtn", "WPRReviewProjectPhase");
		testlog.info("And User clicks on Submit DocReview button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidReviewErrorMessage1", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ValidReviewErrorMessage1"),
				"The minimum point requirement is not met, please ensure",
				"Review Mark Error message doesn't match");
		testlog.info("And User verifies Review ErrorMessage By Minimum selecting ScorecardPurseYes");
	}

	public void CompleteScorecardWprById(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewSubmitbtn", 0);
		CommonMethod.WaitUntilVisibility("ScorecardTab", 300);
		CommonMethod.RobustclickElementVisible("ScorecardTab", "ApplicableVersion");
		testlog.info("And User clicks on ScorecardTab");
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseMaybe", 0);
		CommonMethod.RobustclickElementVisible("CommonScorecardPurseMaybe", "Confirmbtn");
		rc.confirmPursing();
		CommonMethod.RobustclickElementVisible("CommonScorecardPurseMaybe", "Confirmbtn");
		rc.confirmPursing();
		CommonMethod.WaitUntilInVisibility("WPRCloseIcon", 60);
		ScorecardfillHSRWPR(21, 22, 38, 17, "CommonScorecardPurseYes", "CommonScorecardPurseNo");
		testlog.info("And User clicks on 21 Yes button");
		testlog.info("And User clicks on 17 No button");
		testlog.info("Then User verifies Upload Document toast message");
		testlog.pass("**Verifies the 15 Purse Yes Scorecard Performance successfully**");
	}

	public void uploadDocumentInFeature(int LastFeatureNumber, String Commodity) throws Exception {
		List<WebElement> Feature;
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPFeature", 0);
		Feature = CommonMethod.findElements("V2ProjectWPRPFeature");
		Feature = Feature.subList(0, LastFeatureNumber);
		CommonMethod.scrolldowntoElement("ApplicableVersion");
		for (WebElement f : Feature) {
			CommonMethod.WaitUntilClickble(f, 120);
			CommonMethod.JavascriptClickElement(f);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRVerficationTab", 0);
			CommonMethod.RobustclickElementVisible("WPRVerficationTab", "WPRAddOption");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOption", 0);
			CommonMethod.JavascriptClickElement("WPRAddOption");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionbtn", 0);
			CommonMethod.Robustclick("WPRAddOptionbtn");
			Thread.sleep(2000);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionCloseIcon", 0);
			CommonMethod.Robustclick("WPRAddOptionCloseIcon");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRAddOptionCloseIcon", 1);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocbtn", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignbtn", 0);
			generic.assignLocationGeneric(Commodity, false, false, true, false, false);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRUploadDocTaskbtn", 0);
			List<WebElement> UploadButton;
			Thread.sleep(2000);
			UploadButton = CommonMethod.findElements("WPRScorecardUploadTaskDocument");
			for (WebElement uploadButton : UploadButton) {
				CommonMethod.JavascriptClickElement(uploadButton);
				CommonMethod.uploadFile("WPRDocUpload", FeaturefileUpload, "UploadFileVerifyScorecard");
						rc.ScorecardUploadSaveButton();
						rc.ScorecardConfirmLocUploadSaveButton();
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("UploadDocumentModalCommon", 1);
				if (CommonMethod.isElementsExist("DocumentAddedToasterMessage", 5)) {
					CommonMethod.WaitUntilInVisibility("DocumentAddedToasterMessage", 120);
				}
				CommonMethod.WaitUntilPresence(PortfolioAndRatingLocAccDocumentTable, 120);
			}
			CommonMethod.scrolldowntoElement("ApplicableVersion");
			CommonMethod.JavascriptClickElement(f);
		}
	}

	public void UploadWPRDocForFeature(String Commodity) throws Exception {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplicableVersion", 0);
		uploadDocumentInFeature(21, Commodity);
		testlog.info("When User clicks on Feature");
		testlog.info("And User clicks on Verification tab");
		testlog.info("And User clicks on Add Option button");
		testlog.info("And User clicks on Add button");
		testlog.info("And User clicks on Close icon");
		testlog.info("And User clicks on Assign button");
		testlog.info("And User checks on Assign location");
		testlog.info("And User clicks on Save button");
		testlog.info("And User clicks on Upload button");
		testlog.info("And User upload Scorecard Document");
		testlog.info("And User clicks on Save button");
		testlog.info("Then User verifies popup message");
		testlog.info("And User verifies Upload Document Table");
		testlog.info("And User verifies green colour for Completed task");
		testlog.pass("**Upload 21 Scorecard Documents successfully**");
	}

	public void SubmitWPRReview(String SheetName, int rowNum, String ReviewName)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "WPRReviewSubmitbtn");
		testlog.info("When User clicks on Review button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewSubmitbtn", 0);
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewSubmitbtn", 0);
		CommonMethod.RobustclickElementVisible("WPRReviewSubmitbtn", "WPRReviewProjectPhase");
		testlog.info("And User clicks on Submit DocReview button");
		CommonMethod.WaitUntilInVisibility("ReviewSubmitDocValid", 120);
		testlog.info("Vaildated scorecard is incomplete message");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewModelSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("ReviewModelSubmitButton", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Doc-submit-phase is required.", "Phase Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource(
				"Please provide your comments below to notify the IWBI team is required.",
				"Comment Name Error Mismatch");
		testlog.info(
				"And User clicks on SubmitDocReview button without entering the mandatory fields and verifies error meassage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewProjectPhase", 0);
		testlog.info("ReviewName: " + ReviewName);
		if (SheetName.equalsIgnoreCase("Wpr") && (TestCaseName.contains("FinalSubmitReview") && (TestNGTestName.contains("Main")))) {
		CommonMethod.verifyDropdownValues("WPRReviewProjectPhase", "PerformanceFinalReviewPhase");
	    }
		if (SheetName.equalsIgnoreCase("Wpr") && (TestCaseName.contains("CurativeSubmitReview") && (TestNGTestName.contains("Main")))) {
		CommonMethod.verifyDropdownValues("WPRReviewProjectPhase", "PerformanceCurativeReviewPhase");
	    }
		if (SheetName.equalsIgnoreCase("Wpr") && (TestCaseName.contains("SubmitPreliminaryReview") && (TestNGTestName.contains("Main")))) {
		CommonMethod.verifyDropdownValues("WPRReviewProjectPhase", "PerformanceReviewPhase");
	    }
		CommonMethod.selectdropdownValue("WPRReviewProjectPhase", ReviewName);
		testlog.info("And User select phase review");
		CommonMethod.WaitUntilClickble("WPRReviewComment", 60).sendKeys(ReviewName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewModelSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("ReviewModelSubmitButton", "WFAdminListTr");
		testlog.info("And User clicks on  Submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WFAdminListTr", 0);
		testlog.info("Then User will be redirected to Review list page");
		testlog.pass("**Submitted Performance Review successfully**");
	}

	public void ClickBilling() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BiilingTab", 0);
		CommonMethod.RobustclickElementVisible("BiilingTab", "BillingProjectInvoice");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingProjectInvoice", 0);
		CommonMethod.RobustclickElementVisible("BillingProjectInvoice", "BillingCurativeAction");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingCurativeAction", 0);
		CommonMethod.RobustclickElementVisible("BillingCurativeAction", "V2ProjectPreBillingPayNowButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPreBillingPayNowButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectPreBillingPayNowButton", "BillingLanding");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.pass("**Nagavited to Billing successfully**");
	}

	public void CompleteWPRReview(String SheetName, int rowNum, String ReviewName)
			throws IOException, InterruptedException {
		AdminWprSearch(SheetName, rowNum);
		hsr.CompleteReturnReview(SheetName, rowNum, ReviewName);
	}

	public void WprProjectFieldValidationTest(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "HsrWprOrganizationInformation");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprOrganizationInformation", 0);
		CommonMethod.RobustclickElementVisible("HsrWprOrganizationInformation", "HsrWprEditProjectName");
		testlog.info("And User clicks on OrganizationInformation tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprEditProjectName", 0);
		testlog.info("projectName: " + data.getCellData(SheetName, "projectName", rowNum));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("HsrWprEditProjectName"),
				data.getCellData(SheetName, "projectName", rowNum), "Project Name doesn't match");
		testlog.info("WPRlocationsize: " + data.getCellData(SheetName, "Locationsize", rowNum));
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValue("HsrWprEditArea").replace("sq ft", "").replace(",", "").trim(),
				data.getCellData(SheetName, "Locationsize", rowNum), "Area doesn't match");
		testlog.info("WPRlocations: " + data.getCellData(SheetName, "WPRlocations", rowNum));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("HsrWprEditLocation"),
				data.getCellData(SheetName, "WPRlocations", rowNum), "Location count doesn't match");
		testlog.info("OrgName: " + data.getCellData(SheetName, "OrgName", rowNum));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("OrgName"),
				data.getCellData(SheetName, "OrgName", rowNum), "OrgName doesn't match");
		testlog.info("OrgIndustry: " + data.getCellData(SheetName, "OrgIndustry", rowNum));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("HsrWprEditOrgIndustry"),
				data.getCellData(SheetName, "OrgIndustry", rowNum), "OrgIndustry doesn't match");
		testlog.info("Country: " + CommonMethod.getSelectedDropdownAttribute("HsrWprEditCountry"));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownAttribute("HsrWprEditCountry"),
				data.getCellData(SheetName, "Country", rowNum), "Country doesn't match");
		testlog.info("State: " + data.getCellData(SheetName, "State", rowNum));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("HsrWprEditState"),
				data.getCellData(SheetName, "State", rowNum), "State Name doesn't match");
		testlog.info("Street: " + data.getCellData(SheetName, "Street", rowNum));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("HsrWprEditStreet"),
				data.getCellData(SheetName, "Street", rowNum), "Street Name doesn't match");
		testlog.info("City: " + data.getCellData(SheetName, "City", rowNum));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("HsrWprEditCity"),
				data.getCellData(SheetName, "City", rowNum), "City doesn't match");
		testlog.info("PostalCode: " + data.getCellData(SheetName, "PostalCode", rowNum));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("HsrWprPostalCode"),
				data.getCellData(SheetName, "PostalCode", rowNum), "PostalCode doesn't match");
		testlog.info("Then User verifies the added details from excel");
		testlog.pass("**Verifies the Wpr Field Validation successfully**");
	}

	public void validateTeamsWPR(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLPerformanceRatingNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WELLPerformanceRatingNavBar", 0);
		CommonMethod.RobustclickElementVisible("WELLPerformanceRatingNavBar", "RatingIdClick");
		String wprId = data.getCellData(SheetName, "ProjectID", rowNum);
		testlog.info("Performance ID: " + wprId);
		CommonMethod.WaitUntilClickble("WPRWERId", 60).sendKeys(wprId);
		CommonMethod.RobustclickElementVisible("ApplyButton", "WELLPerformanceRatingNavBar");
		int var = CommonMethod.WaitUntilNumberOfElementToBePresent("WELLPerformanceRatingNavBar", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(var), "1", "Portfolio Search failed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RatingIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");
		testlog.info("And User verifies able to access the invited project");
		testlog.pass("**Verifies user able to access the invited project**");
	}

	public void clikOnDocumentLibrary() throws InterruptedException, IOException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentLibraryTab", 0);
		CommonMethod.RobustclickElementVisible("DocumentLibraryTab", "Uploadbutton");
		testlog.info("When User clicks on DocumentLibraryTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
	}

	public void searchFilterScoreCard(String FeatureName) throws IOException, InterruptedException {
		testlog.info("Given User is on Scorecard Page");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardSearchBox", 0);
		CommonMethod.sendKeys("V2ProjectScoreCardSearchBox", FeatureName);
		Thread.sleep(2000);
		CommonMethod.sendKeyEnter("V2ProjectScoreCardSearchBox");
		testlog.info("Given User enters is on SearchBox filter field");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectWPRPFeature", 1);
		testlog.info(FeatureName + ": " + FeatureName);
		testlog.info("Then User will be redirected to Scorecard list page");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectWPRPFeature"), FeatureName, "SearchFilter Feature name doesn't match");
		CommonMethod.negativesoftassertFieldValid(Integer.toString(CommonMethod.ElementSize("V2ProjectWPRPFeature")), "1",
				"searchFilter doesn't match");
		testlog.info("And User verifies Scorecard list Count");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardSearchBox", 0);
		testlog.pass("**Verifies Search filter successfully**");
	}

	public void verifyScoreCardFilter(String SheetName, String filterName, String expectedResult, int filterIndex,
			int checkboxIndex) throws IOException, InterruptedException {
		testlog.info("Given User is on Scorecard Page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "V2ProjectScorecardApplybutton");
		testlog.info("When User clicks on Filter button");
		CommonMethod.clickOnListWebelementFromIndex("V2ProjectScoreCardFilterOption", filterIndex);
		CommonMethod.clickListWebelementFromIndex("V2ProjectScoreCardFilterOptionCheckBox", checkboxIndex);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardApplybutton", "V2ProjectScoreCardFilterButton");
		if (filterName.equalsIgnoreCase("Response")) {
			CommonMethod.WaitUntilPresence("WPRValidPurseYes", 60);
			int YesFeature = CommonMethod.ElementSize("WPRValidPurseYes");
			String actualYesFeatureCount = Integer.toString(YesFeature);
			testlog.info(filterName + ": " + actualYesFeatureCount);
			CommonMethod.negativesoftassertFieldValidEquals(actualYesFeatureCount, expectedResult, "YesPurseCount doesn't match");
		}
		if (filterName.equalsIgnoreCase("Verification") || filterName.equalsIgnoreCase("Document Scale")) {
			CommonMethod.WaitUntilInVisibility("WPRValidTotalFeature", 120);
			CommonMethod.WaitUntilPresence("WPRValidVerification", 60);
			int FeatureCount = CommonMethod.ElementSize("V2ProjectWPRPFeature");
			String actualYesFeatureCount = Integer.toString(FeatureCount);
			testlog.info(filterName + ": " + actualYesFeatureCount);
			CommonMethod.negativesoftassertFieldValidEquals(actualYesFeatureCount, expectedResult, "Feature Count doesn't match");
		}
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
		testlog.pass("**Verifies filter " + filterName + " options successfully**");
	}

	public void SearchPerformanceByStatus(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard Page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLPerformanceRatingNavBar");
		testlog.info("When User clicks on ProjectNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WELLPerformanceRatingNavBar", 0);
		CommonMethod.RobustclickElementVisible("WELLPerformanceRatingNavBar", "RatingIdClick");
		testlog.info("And User clicks on WELLPerformanceRatingNavBar");
		String wprId = data.getCellData(SheetName, "ProjectID", rowNum);
		testlog.info("Performance ID: " + wprId);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRWERId", 0);
		CommonMethod.clearAndSendKey("WPRWERId", wprId);
		testlog.info("And User enters to WPRId field");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		testlog.info("And User clicks on apply button");
		int ProjectCount = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1)
				.size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount), "1", "Performance Search failed");
		CommonMethod.sendKeys("HsrNameList", data.getCellData(SheetName, "projectName", rowNum));
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		int var = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		 CommonMethod.negativesoftassertFieldValid(String.valueOf(var), "1", "Performance Search Count failed");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		int ProjectCount1 = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1)
				.size();
		 CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount1), "1",
				"Performance Search Count failed");
		 CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("RatingIdClick"), data.getCellData(SheetName, "projectID", rowNum),
				"Project name doesn't matches in search");
		String status = CommonMethod.getText("HsrWprStatusResultList");
		testlog.info("Status: " + status);
		 CommonMethod.negativesoftassertFieldValid(status, "ACHIEVED", "Performance Search status failed");
		 rc.ValidateDateInList();
		CommonMethod.RobustclickElementVisible("RatingIdClick", "WellV2DashboardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		
		testlog.info("Then User verifies Project Acheieved Status");
		testlog.info("Then User verifies Project Date");
		testlog.pass("**Verifies the Search Performance Status successfully**");
	}

	public void UpdateReview(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLPerformanceNavBar");
		CommonMethod.WaitUntilVisibility("AdminWELLPerformanceNavBar", 120);
		CommonMethod.RobustclickElementVisible("AdminWELLPerformanceNavBar", "SearchByID");
		CommonMethod.WaitUntilClickble("SearchByID", 60)
				.sendKeys(data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.RobustclickElementVisible("ApplyButton", "AdminIdClick");
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("AdminIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");
		CommonMethod.RobustclickElementVisible("AdminIdClick", "WellV2DashboardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "ReviewViewButton");
		CommonMethod.RobustclickElementVisible("V2ProjectReviewViewLink", "V2ProjectReviewEditButton");
		CommonMethod.RobustclickElementVisible("V2ProjectReviewEditButton",
				"V2ProjectReviewNeedClarificationRadioButton");
		CommonMethod.ClickCheckbox("V2ProjectReviewNeedClarificationRadioButton");
		CommonMethod.sendKeys("V2ProjectReviewMidReviewClarificationNote", "Test");
		CommonMethod.RobustclickElementVisible("V2ProjectReviewUpdate", "V2ProjectReviewRequiredClarification");
	}

	public void ReSubmitReview(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectReviewViewLink");
		CommonMethod.RobustclickElementVisible("V2ProjectReviewViewLink", "V2ProjectReviewReSubmitButton");
		CommonMethod.RobustclickElementVisible("V2ProjectReviewReSubmitButton", "V2ProjectReviewCommentTextArea");
		CommonMethod.sendKeys("V2ProjectReviewCommentTextArea", "Test");
		CommonMethod.Robustclick("SubmitButton");
	}

	public void AdminWprSearch(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLPerformanceNavBar");
		// CommonMethod.WaitUntilVisibility("AdminWELLPerformanceNavBar", 120);
		CommonMethod.RobustclickElementVisible("AdminWELLPerformanceNavBar", "SearchByID");
		testlog.info("When User clicks on WELL Performance from top menu under Projects");
		CommonMethod.WaitUntilClickble("SearchByID", 120)
				.sendKeys(data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter data to WprId");
		CommonMethod.RobustclickElementVisible("ApplyButton", "AdminIdClick");
		testlog.info("And User clicks on Apply button");
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("AdminIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");
		CommonMethod.RobustclickElementVisible("AdminIdClick", "WellV2DashboardTab");
		testlog.info("And User clicks on WprAdminId"); 

	}
	
	public void AdminSearchPerformanceFilterByStatus(String SheetName, int rowNum, String Status) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLPerformanceNavBar");
		// CommonMethod.WaitUntilVisibility("AdminWELLPerformanceNavBar", 120);
		CommonMethod.RobustclickElementVisible("AdminWELLPerformanceNavBar", "SearchByID");
		testlog.info("When User clicks on WELL Performance from top menu under Projects");
		CommonMethod.WaitUntilClickble("SearchByID", 120)
				.sendKeys(data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter data to WprId");		
		CommonMethod.RobustclickElementVisible("ApplyButton", "AdminIdClick");
		testlog.info("And User clicks on Apply button");
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("AdminIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAdminFilterStatus", 0);
		String actualText=CommonMethod.getattributeValueByTextContent("WPRAdminFilterStatus");
		actualText= actualText.replaceAll("\\s+", " ").trim();
		System.out.println(actualText);		
		CommonMethod.negativesoftassertFieldValidEquals(actualText.toLowerCase(), Status.toLowerCase(),
				"Authenticted User Status Search doesn't match");		
		CommonMethod.RobustclickElementVisible("AdminIdClick", "WellV2DashboardTab");
		testlog.info("And User clicks on WprAdminId"); 
		
	}

	public void Editlocation(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on location tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
		CommonMethod.RobustclickElementVisible("Overviewtab", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 3);
		testlog.info("And User clicks on overview tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ClickOnAddedSingleProjectId", 0);
		CommonMethod.RobustclickElementVisible("ClickOnAddedSingleProjectId", "VerifyEditlocationslider");
		testlog.info("And User clicks on edit location button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationOwnershipDisable", 0);
		CommonMethod.assertisElementPresentTrue("PortfolioLocationOwnershipDisable", "Ownership is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioLocationCountryDisable", "Country is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioLocationStateDisable", "State is enabled");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSpaceType", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioLocationSpaceType", "Hospitality");
		testlog.info("when User update space type as Hospitality spaces");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationOwnershipDisable", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectscorecarddocuploadsubmit", 0);
		CommonMethod.Robustclick("V2Projectscorecarddocuploadsubmit", "PortfolioLocationSpaceType");
		testlog.info("then User clicks on update button");
		if (CommonMethod.isElementsExist("Toastermessage", 15)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("Toastermessage", 1);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ClickOnAddedSingleProjectId", 0);
		CommonMethod.RobustclickElementVisible("ClickOnAddedSingleProjectId", "VerifyEditlocationslider");
		testlog.info("then User again clicks on edit location");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSpaceType", 0);
		Thread.sleep(2000);
		 CommonMethod.WaitUntilNumberOfElementTextToBePresentWithException("PortfolioLocationSpaceType","Hospitality", 500);
		CommonMethod.verifySelectedDropdownText("PortfolioLocationSpaceType", "Hospitality","Update Spacetype doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectscorecarddocuploadsubmit", 0);
		CommonMethod.Robustclick("V2Projectscorecarddocuploadsubmit", "PortfolioLocationSpaceType");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioLocationSpaceType", 1);
		testlog.info("And User verify updated space type");
		testlog.pass("And User verify single location edit successfully**");
	}

	public void locationFilters(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationLanding", 0);
		testlog.info("Given User is on LocationsTab");
		/*
		 * space type filter
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "Applybutton");
		testlog.info("when User click on Filter button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Warehousecheckbox", 0);
		CommonMethod.ClickCheckbox("Warehousecheckbox");
		testlog.info("And User select Warehouse checkbox ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Applybutton", 0);
		CommonMethod.Robustclick("Applybutton");
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 5);
		int rowCount = CommonMethod.ElementSize("PortfolioLocListTr");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(rowCount), "5", "space type filter");
		testlog.info("And User verify space type filter ");
		resetFilter();
		testlog.info("And User verify Reset filter");

		/*
		 * Ownership type filter
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "OwnershipWellCore");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnershipWellCore", 0);
		CommonMethod.ClickCheckbox("OwnershipWellCore");
		testlog.info("And User select OwnershipSpaceLease checkbox ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Applybutton", 0);
		CommonMethod.Robustclick("Applybutton");
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTable", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 1);
		int rowCountOwnership = CommonMethod.ElementSize("PortfolioLocListTr");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(rowCountOwnership), "1","Ownership type filter");
		testlog.info("And User verify Ownership type filter ");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		testlog.pass("And User verify locations filter successfully**");	
	}
	
	public void resetFilter() throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "OwnershipWellCore");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ResetButton", 0);
		CommonMethod.Robustclick("ResetButton");
	}

	public void updateProfile(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRLocationViewlink", 0);
		CommonMethod.RobustclickElementVisible("WPRLocationViewlink", "V2ProjectProjectBio");
		testlog.info("And User clicks on View link");
		CommonMethod.WaitUntilVisibility("V2ProjectProjectBio", 60);
		CommonMethod.clearAndSendKey("V2ProjectProjectBio", "Project bio testing");
		testlog.info("And User enter data to ProjectBio field");
		CommonMethod.WaitUntilVisibility("V2ProjectSave", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectSave", "V2ProjectProfileUpdatedToastMessage");
		testlog.info("And User clicks on Save button");
		CommonMethod.WaitUntilVisibility("V2ProjectProfileUpdatedToastMessage", 60);
		CommonMethod.softAssertEqualsMessage(CommonMethod.getText("V2ProjectProfileUpdatedToastMessage"),
				"Updated Profile!", "Verified profile updated toast message");
		testlog.info("Then User verifies Profile updated successfully toast message");
		CommonMethod.WaitUntilVisibility("WPRLocationViewlink", 180);
		CommonMethod.RobustclickElementVisible("WPRLocationViewlink", "V2ProjectProjectBio");
		CommonMethod.WaitUntilVisibility("WPRLocationwellcertification", 60);
		CommonMethod.RobustclickElementVisible("WPRLocationwellcertification", "WPRProjectDecideInputfield");
		CommonMethod.WaitUntilVisibility("WPRProjectDecideInputfield", 60);
		CommonMethod.clearAndSendKey("WPRProjectDecideInputfield", "project decide testing");
		testlog.info("And User enter data to project decide field");
		CommonMethod.WaitUntilVisibility("WPRWELLAlignInputfield", 60);
		CommonMethod.clearAndSendKey("WPRWELLAlignInputfield", "well align testing");
		testlog.info("And User enter data to well align testing field");
		CommonMethod.RobustclickElementVisible("WPRSaveprofilebutton", "V2ProjectProfileUpdatedToastMessage");
		testlog.info("And User clicks on Save profile button");
		CommonMethod.WaitUntilVisibility("V2ProjectProfileUpdatedToastMessage", 60);
		CommonMethod.softAssertEqualsMessage(CommonMethod.getText("V2ProjectProfileUpdatedToastMessage"),
				"Updated Profile!", "Verified profile updated toast message");
		testlog.info("user click on close panel");
		CommonMethod.WaitUntilVisibility("WPRLocationViewlink", 60);
		CommonMethod.RobustclickElementVisible("WPRLocationViewlink", "V2ProjectProjectBio");
		CommonMethod.softAssertEqualsMessage(CommonMethod.getText("V2ProjectProjectBio"), "Project bio testing",
				"Verified project bio message");
		CommonMethod.WaitUntilVisibility("WPRLocationwellcertification", 60);
		CommonMethod.RobustclickElementVisible("WPRLocationwellcertification", "WPRProjectDecideInputfield");
		CommonMethod.softAssertEqualsMessage(CommonMethod.getText("WPRProjectDecideInputfield"),
				"project decide testing", "Verified project decide message");
		CommonMethod.softAssertEqualsMessage(CommonMethod.getText("WPRWELLAlignInputfield"), "well align testing",
				"Verified enter well align message");
		CommonMethod.WaitUntilVisibility("WPRSaveprofilebutton", 120);
		CommonMethod.RobustclickElementVisible("WPRSaveprofilebutton", "V2ProjectProfileUpdatedToastMessage");
		softAssert.assertAll();
	}

	public void EditTeamMemberRole(String SheetName, int rowNum, String WPRTeamRoleDropdown, String Updatebtn) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTeamEditButton", 0);
		CommonMethod.JavascriptClickElement("WPRTeamEditButton");
		testlog.info("When user click on edit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(WPRTeamRoleDropdown, 0);
		CommonMethod.selectdropdownVisibletext(WPRTeamRoleDropdown, "Architect");
		testlog.info("And User select the Role");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(Updatebtn, 0);
		CommonMethod.JavascriptClickElement(Updatebtn);
		testlog.info("And User clicks on Invite button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTeamProjectRole", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRTeamProjectRole"), "Architect",
				"Project role does not matched");
	}


	public void promotionCardCountBeforeAchieved(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PromotionCardContainer", 1);
		int countCard = CommonMethod.ElementSize("PromotionCardContainer");
		boolean flag = true;
		if (countCard < 1) {
			flag = false;
		}
		CommonMethod.negativesoftassertFieldValid(Boolean.toString(flag),"true", "Before achieved card count doesn't match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRIWBIBranding"), "IWBI branding guidelines",
				"IWBI card does not matched");
	}

	public void UpdateWPREditAdminStatus(String SheetName, int rowNum) throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		testlog.info("And User clicks on Edit tab button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAdminFieldsButton", "AdminFieldsText");
		testlog.info("And User clicks on Edit tab button");
		CommonMethod.WaitUntilVisibility("EditTab", 60);
		CommonMethod.ClickCheckbox("EditAchievedStatus");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectSaveChangesButton", "WPRDashboardButton");
		testlog.info("And User clicks on Apply button");

	}

	public void promotionCardCountAfterAchieved(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PromotionTab", 0);
		CommonMethod.RobustclickElementVisible("PromotionTab", "PromotionCardContainer");
		rc.ValidCardCount("PromotionCardContainer");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRPromotionCursorPointer", 0);
		CommonMethod.RobustclickElementVisible("WPRPromotionCursorPointer", "WPRPromotionVerifyPopup");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRPromotionVerifyPopup"),
				"Brand guidelines & seal usage requirements", "IWBI card does not matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRPromotionPopupcloseicon", 0);
		CommonMethod.RobustclickElementVisible("WPRPromotionPopupcloseicon", "WPRPromotionLooksealCard");
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRPromotionLooksealCard"), "Look for the seal",
				"IWBI card does not matched");
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRPromotionDownloadmarksCard"),
				"Download digital marks", "IWBI card does not matched");
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRPromotionmarketingteamCard"),
				"Digital mark guidelines for marketing teams", "IWBI card does not matched");
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRPromotionPerformanceStoryCard"),
				"Tell your WELL Performance Story", "IWBI card does not matched");
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRPromotionPromoteOnsiteCard"),
				"Promote Onsite", "IWBI card does not matched");
	}

	public void promotionCardCount(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PromotionTab", 0);
		CommonMethod.RobustclickElementVisible("PromotionTab", "WPRPromotionWellScoreCards");
		int countCard = CommonMethod.ElementSize("WPRPromotionWellScoreCards");
		boolean flag = true;
		if (countCard != 12) {
			flag = false;
		}
		CommonMethod.assertTruebooleanCondition(flag, "Card count  doesn't match");
	}

	public void taskFilter(String documentName, String fileCount) throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTaskTab", 0);
		CommonMethod.RobustclickElementVisible("WPRTaskTab", "V2ProjectScoreCardFilterButton");
		performance.verifyTaskFilter("Verification");
		performance.verifyTaskFilter("Action area");

	}

	public void verifyTaskFilter(String filterName) throws IOException, InterruptedException {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "V2ProjectScorecardApplybutton");
		testlog.info("When User clicks on Filter button");
		if (filterName.equalsIgnoreCase("Verification")) {
			verifyVerificationFilter();
		}
		if (filterName.equalsIgnoreCase("Action area")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardVerificationFilter", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectScorecardVerificationFilter",
					"V2ProjectScorecardOnsitePhotographsFilter");
			testlog.info("And User clicks on verification Option to close the option");
			CommonMethod.WaitUntilPresence("WPRTaskActionArea", 180);
			CommonMethod.RobustclickElementVisible("WPRTaskActionArea", "WPRTaskIndoorAirQualityCbx");
			testlog.info("And User clicks on Response Filter option");
			CommonMethod.WaitUntilPresence("WPRTaskIndoorAirQualityCbx", 180);
			CommonMethod.RobustclickElementVisible("WPRTaskIndoorAirQualityCbx", "V2ProjectScorecardApplybutton");
			testlog.info("And User check the Yes checkbox");
			CommonMethod.WaitUntilPresence("V2ProjectScorecardApplybutton", 180);
			CommonMethod.RobustclickElementVisible("V2ProjectScorecardApplybutton", "WPRTaskVerifyIndoorAirQuality");
			testlog.info("And User clicks on Apply button");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRTaskVerifyIndoorAirQuality"),
					"Indoor Air Quality", "Indoor Air Quality doesn't match");
			testlog.info("Then User verifies verification filter");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocumentClearButton", 0);
		CommonMethod.Robustclick("WPRDocumentClearButton", "WPRDocumentClearButton");
		testlog.info("And User click on the clear button");
		softAssert.assertAll();
		testlog.pass("**Verifies filter " + filterName + " options successfully**");
	}

	public void verifyVerificationFilter() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardVerificationFilter", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardVerificationFilter",
				"V2ProjectScorecardOnsitePhotographsFilter");
		testlog.info("And User clicks on Verification Option");
		CommonMethod.WaitUntilPresence("V2ProjectScorecardOnsitePhotographsFilter", 180);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardOnsitePhotographsFilter",
				"V2ProjectScorecardApplybutton");
		testlog.info("And User check the Photographs checkbox");
		CommonMethod.WaitUntilPresence("V2ProjectScorecardApplybutton", 180);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardApplybutton", "WPRDocumentVerifyPhotographs");
		testlog.info("And User clicks on Apply Filter button");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRDocumentVerifyPhotographs"),
				"On-site Photographs", "On-site Photographs doesn't match");
		testlog.info("Then User verifies verification filter");
	}

	public void documentFilter(String Commodity, String optionXpath,String optionCheckboxXpath, String rowCountXpath,String expectedResult, String ScorecardCondition) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", "WPRDocumentPaginationsecond");
		testlog.info("When User click on Document button");
		CommonMethod.scrolldowntoElement("PortfolioAndRatingLocAccDocumentTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocumentFilterOption", 0);
		CommonMethod.RobustclickElementVisible("WPRDocumentFilterOption", "V2ProjectScoreCardFilterButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "V2ProjectScorecardApplybutton");
		generic.filterGeneric(Commodity, optionXpath,optionCheckboxXpath, rowCountXpath,expectedResult, ScorecardCondition);
	}

	public void verifySearchLocationFilter(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRLocationSearchBox", 0);
		CommonMethod.click("WPRLocationSearchBox");
		testlog.info("Given user click in search filter box");
		CommonMethod.WaitUntilPresence("WPRLocationWellLocation1", 120);
		CommonMethod.click("WPRLocationWellLocation1");
		testlog.info("When user click location name in seach filter box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TaskFullFilledTab", 0);
		CommonMethod.JavascriptClickElement("TaskFullFilledTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTaskFeatureCount", 0);
		rc.validateFeatureCount(CommonMethod.ElementSize("WPRTaskFeatureCount"), 35,
				"Search Location Filter Count");
		testlog.info("Then User verifies location filter");
		testlog.pass("**Verifies Document Library task location Count successfully**");
	}

	public void uploadLocationSingleFeature(String featureNameLocator, String Commodity) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(featureNameLocator, 0);
		CommonMethod.JavascriptClickElement(featureNameLocator);
		CommonMethod.WaitUntilVisibility("WPRVerficationTab", 60);
		CommonMethod.RobustclickElementVisible("WPRVerficationTab", "WPRAddOption");
		CommonMethod.WaitUntilVisibility("WPRAddOption", 60);
		CommonMethod.JavascriptClickElement("WPRAddOption");
		CommonMethod.WaitUntilVisibility("WPRAddOptionbtn", 60);
		CommonMethod.Robustclick("WPRAddOptionbtn");
		CommonMethod.WaitUntilPresence("WPRAddOptionCloseIcon", 120);
		CommonMethod.Robustclick("WPRAddOptionCloseIcon");
		CommonMethod.WaitUntilVisibility("WPRAssignLocbtn", 60);
		Thread.sleep(1000);
		generic.assignLocationGeneric(Commodity, false, false, true, false, false);
	}
	public void uploadDocumentSingleFeature(String UploadsLocator) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRLocationUploadButton", 0);
		CommonMethod.WaitUntilClickble("WPRLocationUploadButton", 120);
		List<WebElement> UploadButton;
		UploadButton = CommonMethod.findElements(UploadsLocator);
		for (WebElement uploadButton : UploadButton) {
			CommonMethod.JavascriptClickElement(uploadButton);
			CommonMethod.uploadFile("WPRDocUpload", FeaturefileUpload, "UploadFileVerifyScorecard");
			rc.ScorecardUploadSaveButton();
			rc.ScorecardConfirmLocUploadSaveButton();
		rc.uploadDocumentToastMessage();
		}
	}
	public void verifyUploadDocumentInTask(String SheetName, int rowNum, String Commodity, String TaskUploadXpath, String LocationCount, String PortfolioDocumentTaskUploadLocationCount) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTaskTab", 0);
		CommonMethod.RobustclickElementVisible("WPRTaskTab", "TaskFullFilledTab");
		CommonMethod.scrolldowntoElement("WPRTaskTab");
		uploadDocumentSingleFeature(TaskUploadXpath);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTaskTab", 0);
		CommonMethod.RobustclickElementVisible("WPRTaskTab", "TaskFullFilledTab");
		CommonMethod.scrolldowntoElement("WPRTaskTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TaskFullFilledTab", 0);
		CommonMethod.click("TaskFullFilledTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioDocumentTaskUploadLocationCount, 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText(PortfolioDocumentTaskUploadLocationCount),LocationCount , "Task Assign Location Count doesn't match");
	}
	
	public void DocumentPagination() throws IOException, InterruptedException {
		testlog.info("Given User is on Document Library Page");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", "WPRDocumentPaginationsecond");
		testlog.info("When User click on Document button");
		CommonMethod.scrolldowntoElement("PortfolioAndRatingLocAccDocumentTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAndRatingLocAccDocumentTableTr", 10);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocumentPaginationsecond", 0);
		CommonMethod.Robustclick("WPRDocumentPaginationsecond");
		testlog.info("And User clicks on pagination number");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRPaginationNumberText", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAndRatingLocAccDocumentTableTr", 10);
		testlog.info("pagination number: "+CommonMethod.getText("WPRPaginationNumberText"));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRPaginationNumberText"), "11","Document Library Pagition count Doesn't match");
		testlog.info("Then User verifies the pagition Count");
	}
	
	public void clickOnReviewTab()
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "ReviewViewButton");
		testlog.info("And User clicks on Review button");
	}
			
	public void WPRPreliminaryReviewViewBtn() throws IOException, InterruptedException {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRPreliminaryReviewViewBtn", 0);
				CommonMethod.RobustclickElementVisible("WPRPreliminaryReviewViewBtn", "ReviewedStatus");
				testlog.info("And User clicks on Review button");
				CommonMethod.scrollUp();
			}
	
	public void verifyEditMRCReview(String SheetName, int rowNum)throws IOException, InterruptedException {
		testlog.info("And User clicks on view button");
		if (TestCaseName.equalsIgnoreCase("V2_TC_13A_01_EditMRCReviewPreliminary")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMrcReviewviewButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectMrcReviewviewButton", "ReviewAdminActionsButton");
		v2project.ReviewAdminActionsButton();
		}
		else if(TestCaseName.equalsIgnoreCase("V2_TC_11A_01_EditMRCReviewPreliminary")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
			CommonMethod.RobustclickElementVisible("ReviewViewButton", "ReviewAdminActionsButton");
			v2project.ReviewAdminActionsButton();
		}
		else {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
		CommonMethod.RobustclickElementVisible("ReviewViewButton", "ReviewAdminActionsButton");
		v2project.ReviewAdminActionsButton();
		}
		testlog.info("And User clicks on edit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewEditButton", 0);
		CommonMethod.RobustclickElementVisible("WPRReviewEditButton", "WPRReviewNeedClarificationRadioBtn");
		testlog.info("And User clicks on need clarification radio button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewNeedClarificationRadioBtn", 0);
		CommonMethod.ClickCheckbox("WPRReviewNeedClarificationRadioBtn");
		testlog.info("And User enter comment in mid review textbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewMidReviewTextArea", 0);
		CommonMethod.sendKeys("WPRReviewMidReviewTextArea", "MRC Test");
		testlog.info("And User clicks on update button");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("EditAchievedStatus");
		CommonMethod.negativeAssertElementNotPresentFalse("EditAchievedStatus", "EditAchieved element is present");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateButton", 0);
		CommonMethod.Robustclick("UpdateButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("UpdateButton", 1);
		testlog.info("Then user verify the review status and resubmit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectReviewReSubmitButton", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectReviewRequiredClarification", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectReviewRequiredClarification"), "REQUIRE CLARIFICATION","REQUIRE CLARIFICATION doesn't match");
		testlog.info("Then user verify the review status");
		testlog.pass("**Verifies edit mrc review for preliminary successfully **");
	}
	
	public void verifyAddMRCCommentReview(String SheetName, int rowNum)throws IOException, InterruptedException {
		testlog.info("Then user verify the status in review table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewTableStatus", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRReviewTableStatus"), "REQUIRE CLARIFICATION","REQUIRE CLARIFICATION doesn't match");
		testlog.info("And User clicks on view button");
		if (TestCaseName.equalsIgnoreCase("V2_TC_13A_02_AddMRCCommentReviewPreliminary")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMrcReviewviewButton", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectMrcReviewviewButton", "ReviewAdminActionsButton");
			}
			else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
			CommonMethod.RobustclickElementVisible("ReviewViewButton", "WPRReviewEditButton");
			}
		testlog.info("Then user verify the review status and resubmit review button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectReviewReSubmitButton", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectReviewRequiredClarification"), "REQUIRE CLARIFICATION","REQUIRE CLARIFICATION doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewAuthReviewComment", 0);
		CommonMethod.sendKeys("WPRReviewAuthReviewComment", "Resubmit Test");
		testlog.info("And User enter comment in the comment box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocUpload", 0);
		CommonMethod.uploadFile("WPRDocUpload", FeaturefileUpload, "UploadFileVerifyScorecard");
		testlog.info("And User upload the documents");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewAddCommentButton", 0);
		CommonMethod.RobustclickElementVisible("WPRReviewAddCommentButton", "WPRReviewVerifyComment");
		testlog.info("And User clicks on add comment button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewVerifyComment", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRReviewVerifyComment"), "Feature","Feature document doesn't match");
		testlog.info("Then user verify comment and upload document name");
		testlog.pass("**Verifies add mrc comment review for preliminary successfully **");
	}
	
	public void clickViewButtonInReview()throws IOException, InterruptedException {
		testlog.info("And User clicks on view button");
		if (TestCaseName.equalsIgnoreCase("V2_TC_13A_02_AddMRCCommentReviewPreliminary") || TestCaseName.equalsIgnoreCase("V2_TC_13A_04_CompletePreliminaryReview")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMrcReviewviewButton", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectMrcReviewviewButton", "V2ProjectReviewReSubmitButton");
			}
			else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
			CommonMethod.RobustclickElementVisible("ReviewViewButton", "V2ProjectReviewReSubmitButton");
			}
	}
	
	public void verifyReSubmitReview(String SheetName, int rowNum)throws IOException, InterruptedException {
		if(SheetName.equalsIgnoreCase("Wpr")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "ReviewViewButton");
		testlog.info("And User clicks on Review button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
		CommonMethod.RobustclickElementVisible("ReviewViewButton", "WPRReviewEditButton");
		testlog.info("then User clicks on view button");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectReviewReSubmitButton", 0);
		CommonMethod.JavascriptClickElement("V2ProjectReviewReSubmitButton");
		testlog.info("And User click on resubmit review button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewReSubmitCommentBox", 0);
		CommonMethod.sendKeys("WPRReviewReSubmitCommentBox", "Resubmit Review Test");
		testlog.info("And User enter comment in the comment box on the resubmit popup");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectscorecarddocuploadsubmit", 0);
		CommonMethod.RobustclickElementVisible("V2Projectscorecarddocuploadsubmit", "WPRReviewInProgressStatus");
		testlog.info("And User clicks on the submit button on the resubmit popup");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRReviewInProgressStatus"), "IN PROGRESS","ReSubmit Review doesn't resubmit");
		CommonMethod.WaitUntilInVisibility("V2ProjectReviewReSubmitButton", 60);
		testlog.info("Then User verify status and resubmit button on the review page after resubmit process");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewBackButton", 0);
		CommonMethod.JavascriptClickElement("WPRReviewBackButton");
		testlog.info("And User clicks on the back button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewInProgressTableStatus", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRReviewInProgressTableStatus"), "IN PROGRESS"," status doesn't match");
		testlog.info("Then User verify the review table status after resubmit process");
		testlog.pass("**Verifies resubmit review for preliminary successfully **");
	}
	
	public void deleteAddedTeamMember(String SheetName, int rowNum) throws IOException, InterruptedException {
		Thread.sleep(2000);
		CommonMethod.refreshBrowser();
		testlog.info("Given User is on Team model page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTeamDeleteButton", 0);
		CommonMethod.RobustclickElementVisible("WPRTeamDeleteButton", "V2ProjectAddMemberbtn");
		testlog.info("When User clicks on Delete Icon");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRTeamDeleteButton", 1);
		testlog.info("Then User will be redirected to Team list page");
		testlog.pass("**Created Team member successfully**");
	}
	
	public void importReviewUpload() throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewUploadButton", 0);
	CommonMethod.RobustclickElementVisible("PortfolioAdminReviewUploadButton","DocumentsUpload");
	testlog.info("When User clicks on Upload button");
	CommonMethod.uploadFile("DocumentsUpload", FeaturefileUpload, "UploadFileVerifyScorecard");
	testlog.info("And User Upload Feature Document");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewModelUploadButton", 0);
	CommonMethod.RobustclickElementVisible("PortfolioAdminReviewModelUploadButton",
			"PortfolioAdminReviewValidDocumentType");
    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRImportDocLog", 0);
    CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("WPRImportDocLog"), "FeatureFile",
		"DocumentType Upload doesn't Match");
	}
	
	public void AdminReviewImportButton() throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportButton", 0);
	CommonMethod.JavascriptClickElement("PortfolioAdminReviewImportButton");
	}
	
	public void ValidateScorecardLockScreenBeforeRenewal(String featureName, String SheetName, int rowNum, String ProjectType, String Commodity, String FileName, boolean PartNameRequired,
			boolean VerificationMethodValidationRequired, boolean NoteRequired, boolean IntentCheckboxRequired) throws Exception {
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledYesResponseButton", 1);
	CommonMethod.assertisElementPresentTrue("WPRDisabledYesResponseButton", "Yes response button is enabled ");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledYesResponseButtonTooltip", 1);
	CommonMethod.assertisElementPresentTrue("WPRDisabledYesResponseButtonTooltip", "Yes response button's Tooltip is not showing ");	
	List<WebElement> Feature;
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
	Feature = CommonMethod.findElements("RatingFeatureName");
	boolean flag=false;
	for (WebElement ele : Feature) {
		String Creditname = ele.getText();
		Creditname = Creditname.replaceAll("\\.", "");
		if (Creditname.equalsIgnoreCase(featureName)) {
			flag=true;
			CommonMethod.JavascriptClickElement(ele);
			testlog.info("When User clicks on Feature");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
			CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
					"PortfolioScoreCardAddOptionbutton");
			testlog.info("And User clicks on VerificationTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledAddOptionsButton", 1);
			CommonMethod.negativeAssertElementPresentTrue("WPRDisabledAddOptionsButton", "Add Options button is enabled ");			
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledAddOptionsButtonTooltip", 1);
			CommonMethod.negativeAssertElementPresentTrue("WPRDisabledAddOptionsButtonTooltip", "Add Options button Tooltip is not showing ");	
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledEditLocations", 1);
			CommonMethod.negativeAssertElementPresentTrue("WPRDisabledEditLocations", "Edit Locations is enabled ");
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledEditLocationsTooltip", 1);
			CommonMethod.negativeAssertElementPresentTrue("WPRDisabledEditLocationsTooltip", "Edit Locations Tooltip is not showing ");			
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledUploadButton", 1);
			CommonMethod.negativeAssertElementPresentTrue("WPRDisabledUploadButton", "Upload Button is enabled ");			
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledUploadButtonTooltip", 1);
			CommonMethod.negativeAssertElementPresentTrue("WPRDisabledUploadButtonTooltip", "Upload Button Tooltip is not showing ");			
			ValidateLockScreenBeforeRenewal();
		CommonMethod.JavascriptClickElement(ele);
		break;
		}
	}
	CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
		
	}
	
	public void ValidateDocumentsLibraryLockScreenBeforeRenewal() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTaskAllTab", 0);
		CommonMethod.RobustclickElementVisible("WPRTaskAllTab", "WPRDisabledUploadButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAllDisabledUploadButton", 0);
		int allDisabledUploadButton = CommonMethod.ElementSize("WPRAllDisabledUploadButton");
		String actualAllDisabledUploadButton = Integer.toString(allDisabledUploadButton);
		List<String> UploadButtonCount = new ArrayList<>();
		UploadButtonCount.add("56");
		UploadButtonCount.add("57");
		UploadButtonCount.add("58");
		UploadButtonCount.add("59");
		CommonMethod.negativesoftassertFieldValid(actualAllDisabledUploadButton, UploadButtonCount, "Disabled Upload Button count does not matched ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAllDisabledUploadButtonTooltip", 0);		
		int allDisabledUploadButtonTooltip = CommonMethod.ElementSize("WPRAllDisabledUploadButtonTooltip");
		String actualAllDisabledUploadButtonTooltip = Integer.toString(allDisabledUploadButtonTooltip);
		List<String> UploadButton = new ArrayList<>();
		UploadButton.add("56");
		UploadButton.add("57");
		UploadButton.add("58");
		UploadButton.add("59");
		CommonMethod.negativesoftassertFieldValid(actualAllDisabledUploadButtonTooltip, UploadButton, "Disabled Upload Button Tooltip count does not matched ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", "WPRDisabledDocumentListEditButton");
		ValidateLockScreenBeforeRenewal();
	}
	
	public void ValidateLockScreenBeforeRenewal() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledDocumentListEditButton", 1);
		CommonMethod.negativeAssertElementPresentTrue("WPRDisabledDocumentListEditButton", "Document List Edit Button is enabled ");			
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledDocumentListEditButtonTooltip", 1);
		CommonMethod.negativeAssertElementPresentTrue("WPRDisabledDocumentListEditButtonTooltip", "Document List Edit Button Tooltip is not showing ");			
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledDocumentListReplaceButton", 1);
		CommonMethod.negativeAssertElementPresentTrue("WPRDisabledDocumentListReplaceButton", "Document List Replace Button is enabled ");			
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledDocumentListReplaceButtonTooltip", 1);
		CommonMethod.negativeAssertElementPresentTrue("WPRDisabledDocumentListReplaceButtonTooltip", "Document List Replace Button Tooltip is not showing ");			
	}
	
	public void ValidateScorecardAfterRenewal(String featureName, String SheetName, int rowNum, String ProjectType, String Commodity, String FileName, boolean PartNameRequired,
			boolean VerificationMethodValidationRequired, boolean NoteRequired, boolean IntentCheckboxRequired) throws Exception {
	CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledYesResponseButton");
	CommonMethod.negativeAssertElementNotPresentFalse("WPRDisabledYesResponseButton", "Yes response button is disabled ");	
	List<WebElement> Feature;
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
	Feature = CommonMethod.findElements("RatingFeatureName");
	boolean flag=false;
	for (WebElement ele : Feature) {
		String Creditname = ele.getText();
		Creditname = Creditname.replaceAll("\\.", "");
		if (Creditname.equalsIgnoreCase(featureName)) {
			flag=true;
			CommonMethod.JavascriptClickElement(ele);
			testlog.info("When User clicks on Feature");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
			CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
					"PortfolioScoreCardAddOptionbutton");
			testlog.info("And User clicks on VerificationTab");
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledAddOptionsButton");
			CommonMethod.negativeAssertElementNotPresentFalse("WPRDisabledAddOptionsButton", "Add Options Button is disabled ");						
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledEditLocations");
			CommonMethod.negativeAssertElementNotPresentFalse("WPRDisabledEditLocations", "Edit Locations is disabled ");				
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledDocumentListEditButton");
			CommonMethod.negativeAssertElementNotPresentFalse("WPRDisabledDocumentListEditButton", "Document List Edit Button is disabled ");						
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledDocumentListReplaceButton");
			CommonMethod.negativeAssertElementNotPresentFalse("WPRDisabledDocumentListReplaceButton", "Document List Replace Button is disabled ");			
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledDocumentListDeleteButton");
			CommonMethod.negativeAssertElementNotPresentFalse("WPRDisabledDocumentListDeleteButton", "Document List Delete Button is disabled ");						
		CommonMethod.JavascriptClickElement(ele);
		break;
		}
	}
	CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
		
	}
	
	public void ValidateDocumentsLibraryLockScreenAfterRenewal() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTaskAllTabOne", 0);
		CommonMethod.RobustclickElementVisible("WPRTaskAllTabOne", "WPRDisabledUploadButton");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
		int allEnabledUploadButton = CommonMethod.ElementSize("PortfolioScoreCardVerificationUploadbtn");
		String actualAllEnabledUploadButton = Integer.toString(allEnabledUploadButton);
		List<String> UploadButtonCount = new ArrayList<>();
		UploadButtonCount.add("56");
		UploadButtonCount.add("57");
		UploadButtonCount.add("58");
		UploadButtonCount.add("59");
		CommonMethod.negativesoftassertFieldValid(actualAllEnabledUploadButton, UploadButtonCount, "Enabled Upload Button count is not present ");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", "WPRDisabledDocumentListEditButton");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledDocumentListEditButton");
		CommonMethod.negativeAssertElementNotPresentFalse("WPRDisabledDocumentListEditButton", "Document List Edit Button is disabled ");						
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledDocumentListReplaceButton");
		CommonMethod.negativeAssertElementNotPresentFalse("WPRDisabledDocumentListReplaceButton", "Document List Replace Button is disabled ");			
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledDocumentListDeleteButton");
		CommonMethod.negativeAssertElementNotPresentFalse("WPRDisabledDocumentListDeleteButton", "Document List Delete Button is disabled ");
	}
	
	public void ValidateLocationsTabAfterRenewal() throws IOException, InterruptedException {
		rc.RemoveSpaceAndValidate("RenewalLocationContentValid", "During renewal, to update the location list after the list has been confirmed, please contact us via the Support tab.");		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("DisabledImportButton");
		CommonMethod.negativeAssertElementNotPresentFalse("DisabledImportButton", "Import Button is disabled ");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("DisabledAddButton");
		CommonMethod.negativeAssertElementNotPresentFalse("DisabledAddButton", "Add Button is disabled ");
	}
	
	public void ValidateExpiringSoonStatusInScorecardAndDocumentLib(String featureName, String SheetName, int rowNum, String ProjectType, String Commodity, String FileName, boolean PartNameRequired,
			boolean VerificationMethodValidationRequired, boolean NoteRequired, boolean IntentCheckboxRequired, String Status) throws Exception {
	List<WebElement> Feature;
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
	Feature = CommonMethod.findElements("RatingFeatureName");
	boolean flag=false;
	for (WebElement ele : Feature) {
		String Creditname = ele.getText();
		Creditname = Creditname.replaceAll("\\.", "");
		if (Creditname.equalsIgnoreCase(featureName)) {
			flag=true;
			CommonMethod.JavascriptClickElement(ele);
			testlog.info("When User clicks on Feature");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
			CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
					"PortfolioScoreCardAddOptionbutton");
			testlog.info("And User clicks on VerificationTab");
			CommonMethod.scrolldowntoElement("Uploadbutton");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERTableDocumentsListStatus", 0);
			String actualExpringSoonStatus = CommonMethod.getattributeValueByTextContent("WERTableDocumentsListStatus");
			CommonMethod.negativesoftassertFieldValid(actualExpringSoonStatus.toLowerCase(), Status.toLowerCase(), "Status does not matched in document Table list ");		
		CommonMethod.JavascriptClickElement(ele);
		break;
		}
	}
	CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
	rc.clickDocument();	
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
	CommonMethod.JavascriptClickElement("PortfolioDocumentListLink");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERTableDocumentsListStatus", 0);
	String actualExpringSoonStatus = CommonMethod.getattributeValueByTextContent("WERTableDocumentsListStatus");
	CommonMethod.negativesoftassertFieldValid(actualExpringSoonStatus.toLowerCase(), Status.toLowerCase(), "Status does not matched in document Table list ");
	}
	
	public void editAdminAwardDate(String AchievedStatus,String saveAchievementXpath) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		testlog.info("When User clicks on EditTab");
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(AchievedStatus, 0);
		CommonMethod.ClickCheckbox(AchievedStatus); 	
		if(TestCaseName.contains("EquityChangeDateAsAdminAndValidateDashboard")) {			
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERAwardDateTextBox", 0);
		CommonMethod.RobustclickElementVisible("WERAwardDateTextBox", "DatePickerOkButton");
		} else {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AwardedDate", 0);
		CommonMethod.RobustclickElementVisible("AwardedDate", "DatePickerOkButton");	 
		}
		if(TestCaseName.contains("PerformanceMarkExpiredAndValidateDashboardBeforeReActivate")) {	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectnextYearbtn", 0);
		CommonMethod.Robustclick("V2ProjectnextYearbtn", "V2ProjectDatePopupWeekday");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRSelectDate", 0);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("WPRSelectDate", "DatePickerOkButton");
		Thread.sleep(1000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.Robustclick("DatePickerOkButton");
		} else {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectnextYearbtn", 0);
		CommonMethod.Robustclick("V2ProjectnextYearbtn", "V2ProjectDatePopupWeekday");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRSelectDate", 0);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("WPRSelectDate", "DatePickerOkButton");
		Thread.sleep(1000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectnextMonthbtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectnextMonthbtn");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSelectNextMonth", 0);
		CommonMethod.JavascriptClickElement("V2ProjectSelectNextMonth");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.Robustclick("DatePickerOkButton");	
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DatePickerOkButton", 1);
		rc.saveAchievementAdminTab(saveAchievementXpath);
	}
	
	public void ValidateDashboardRenewalTextAndButtons(String RenewalDashboardTextLocator, String ExpectedRenewalDashboardText,
			String RenewalDashboardSecondTextLocator, String ExpectedRenewalDashboardSecondText,
			String V2RenewalButtonLocator, String expectedRenewalButtonName) throws IOException, InterruptedException {
			Thread.sleep(5000);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(RenewalDashboardTextLocator, 0);
			String actualRenewalDashboardText = CommonMethod.getattributeValueByTextContent(RenewalDashboardTextLocator);
			CommonMethod.negativesoftassertFieldValidEquals(actualRenewalDashboardText, ExpectedRenewalDashboardText, "Dashboard Renewal Text does not matched ");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(RenewalDashboardSecondTextLocator, 0);
			String actualRenewalDashboardSecondText = CommonMethod.getattributeValueByTextContent(RenewalDashboardSecondTextLocator);
			CommonMethod.negativesoftassertFieldValidEquals(actualRenewalDashboardSecondText, ExpectedRenewalDashboardSecondText, "Dashboard Renewal Second Text does not matched ");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(V2RenewalButtonLocator, 0);
			String actualRenewalButtonName = CommonMethod.getattributeValueByTextContent(V2RenewalButtonLocator);
			CommonMethod.negativesoftassertFieldValidEquals(actualRenewalButtonName, expectedRenewalButtonName, "Renewal button name does not matched ");
			}
	
	public void ValidateDashboardHeaderTextAndCards() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalWprValidateHeaderOnDashboard", 0);
		String actualHeaderOnDashboard=CommonMethod.getattributeValueByTextContent("RenewalWprValidateHeaderOnDashboard");
		actualHeaderOnDashboard= actualHeaderOnDashboard.replaceAll("\\s+", " ").trim();
		System.out.println(actualHeaderOnDashboard);
		CommonMethod.negativesoftassertFieldValid(actualHeaderOnDashboard, "Renew your WELL Performance Rating achievement!", "Renew your WELL Performance Rating achievement!: Text does not matched ");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalWprValidateDashboardText", 0);
		String actualDashboardText=CommonMethod.getattributeValueByTextContent("RenewalWprValidateDashboardText");
		actualDashboardText= actualDashboardText.replaceAll("\\s+", " ").trim();
		System.out.println(actualDashboardText);
		CommonMethod.negativesoftassertFieldValid(actualDashboardText, "You've now joined a community of organizations shifting the way", "You've now joined a community of organizations shifting the way...: Text does not matched ");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalWprValidateDashboardAccessRenewalGuidanceCard", 0);
		String actualDashboardCard=CommonMethod.getattributeValueByTextContent("RenewalWprValidateDashboardAccessRenewalGuidanceCard");
		actualDashboardCard= actualDashboardCard.replaceAll("\\s+", " ").trim();
		System.out.println(actualDashboardCard);
		CommonMethod.negativesoftassertFieldValid(actualDashboardCard, "Access Renewal Guidance", "Access Renewal Guidance: Card is not present ");		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("CommunicateYourAccomplishment");
		CommonMethod.assertisElementPresentFalse("CommunicateYourAccomplishment", "Communicate your accomplishment:Card is present ");		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("YourOrganizationalProfile");
		CommonMethod.assertisElementPresentFalse("YourOrganizationalProfile", "Update your organizational profile:Card is present ");
	} 
	
	public void MarkWPRAsAchievedAndValidatePostAchievementDashboard() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		testlog.info("When User clicks on EditTab");	
		rc.navigateAchievementAdminTab();
		CommonMethod.scrolldowntoElement("AwardedDate");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMarkRenewalAsAchieved", 0);
		CommonMethod.ClickCheckbox("V2ProjectMarkRenewalAsAchieved"); 
	    if(CommonMethod.isElementsExist("V2ProjectRenewalClearBtn", 10))
	    {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRenewalClearBtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectRenewalClearBtn");
	    }
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRenewalDateField", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectRenewalDateField", "DatePickerOkButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "V2ProjectMarkRenewalAsAchieved");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMarkRenewalAsSaved", 0);
		CommonMethod.Robustclick("V2ProjectMarkRenewalAsSaved");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalWprValidateHeaderOnDashboard", 0);
		String actualDashboardHeaderText=CommonMethod.getattributeValueByTextContent("RenewalWprValidateHeaderOnDashboard");
		actualDashboardHeaderText= actualDashboardHeaderText.replaceAll("\\s+", " ").trim();
		System.out.println(actualDashboardHeaderText);
		CommonMethod.negativesoftassertFieldValid(actualDashboardHeaderText, "Congratulations on earning the WELL Performance Rating seal!", "Congratulations on earning the WELL Performance Rating seal! text does not matched ");       
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalWprValidateDashboardText", 0);
		String actualDashboardText=CommonMethod.getattributeValueByTextContent("RenewalWprValidateDashboardText");
		actualDashboardText= actualDashboardText.replaceAll("\\s+", " ").trim();
		System.out.println(actualDashboardText);
		CommonMethod.negativesoftassertFieldValid(actualDashboardText, "You've now joined a community of organizations shifting the way they do business for the better", "You've now joined a community of organizations shifting the way they do business for the better text does not matched ");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommunicateYourAccomplishment", 0);
		String actualDashboardAccomplishmentCard=CommonMethod.getattributeValueByTextContent("CommunicateYourAccomplishment");
		actualDashboardAccomplishmentCard= actualDashboardAccomplishmentCard.replaceAll("\\s+", " ").trim();
		System.out.println(actualDashboardAccomplishmentCard);
		CommonMethod.negativesoftassertFieldValid(actualDashboardAccomplishmentCard,  "Communicate your accomplishment", "Communicate your accomplishment: card is not present ");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("YourOrganizationalProfile", 0);
		String actualDashboardOrganizationalProfileCard=CommonMethod.getattributeValueByTextContent("YourOrganizationalProfile");
		actualDashboardOrganizationalProfileCard= actualDashboardOrganizationalProfileCard.replaceAll("\\s+", " ").trim();
		System.out.println(actualDashboardOrganizationalProfileCard);
		CommonMethod.negativesoftassertFieldValid(actualDashboardOrganizationalProfileCard,  "Update your organizational profile", "Update your organizational profile: card is not present ");
	}
	
	public void ValidateDocumentsExpiryDateOnMarkingNotAchievedToAchieved() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		testlog.info("When User clicks on EditTab");	
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditNotAchievedStatus", 0);
		CommonMethod.ClickCheckbox("EditNotAchievedStatus");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERAchievedSaveBtn", 0);
		CommonMethod.Robustclick("WERAchievedSaveBtn");			
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		testlog.info("When User clicks on EditTab");	
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditAchievedStatus", 0);
		CommonMethod.ClickCheckbox("EditAchievedStatus");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AwardedDate", 0);
		CommonMethod.RobustclickElementVisible("AwardedDate", "DatePickerOkButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.Robustclick("DatePickerOkButton");
		testlog.info("And User select Date"); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERAchievedSaveBtn", 0);
		CommonMethod.Robustclick("WERAchievedSaveBtn");
		rc.clickDocument();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
		CommonMethod.JavascriptClickElement("PortfolioDocumentListLink");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateDocumentsDate", 0);
		String getDocumentsDate = CommonMethod.getattributeValueByTextContent("ValidateDocumentsDate");
        String[] splitDocumentsDate = getDocumentsDate.split(":");
        String actualDocumentsDate = splitDocumentsDate[1].replaceAll("\\s+", " ").trim();
        System.out.println("Actual Documents Date: " + actualDocumentsDate);
		CommonMethod.negativesoftassertFieldValid(actualDocumentsDate, CommonMethod.ValidateDateYearByDayMonthYearFormat(), "Documents expiry date mismatch ");
	}
	
	public void ValidateScorecardDocsExpiryDateOnMarkingNotAchievedToAchieved(String featureName, String SheetName, int rowNum, String ProjectType, String Commodity, String FileName, boolean PartNameRequired,
			boolean VerificationMethodValidationRequired, boolean NoteRequired, boolean IntentCheckboxRequired) throws Exception {
	List<WebElement> Feature;
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWERPartFeature", 0);
	Feature = CommonMethod.findElements("V2ProjectWERPartFeature");
	boolean flag=false;
	for (WebElement ele : Feature) {
		String Creditname = ele.getText();
		Creditname = Creditname.replaceAll("\\.", "");
		if (Creditname.equalsIgnoreCase(featureName)) {
			flag=true;
			CommonMethod.JavascriptClickElement(ele);
			testlog.info("When User clicks on Feature");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
			CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
					"PortfolioScoreCardAddOptionbutton");
			testlog.info("And User clicks on VerificationTab");
			CommonMethod.scrolldowntoElement("WPRDisabledUploadButton");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateDocumentsDate", 0);
			String getDocumentsDate = CommonMethod.getattributeValueByTextContent("ValidateDocumentsDate");
	        String[] splitDocumentsDate = getDocumentsDate.split(":");
	        String actualDocumentsDate = splitDocumentsDate[1].replaceAll("\\s+", " ").trim();
	        System.out.println("Actual Documents Date: " + actualDocumentsDate);
			CommonMethod.negativesoftassertFieldValid(actualDocumentsDate, CommonMethod.ValidateDateYearByDayMonthYearFormat(), "Documents expiry date mismatch ");			
		CommonMethod.JavascriptClickElement(ele);
		break;
		}
	}
	CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
	}
	
	public void ValidRenewExpire() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringLOAStatus",0);
    	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ExpiringLOAStatus"),"Expiring Soon","ExpiringLOAStatus is not present");
        CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ExpiringLOAValidFile",1);
        CommonMethod.negativeAssertElementPresentTrue("ExpiringLOAValidFile","Upload File is not present");
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringVerificationName",0);
    	if (TestCaseName.contains("ValidRenewExpireLOAInScorecard")) {
    		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ExpiringVerificationName"),"Policy and/or Operations Schedule","Expiring VerificationName is not present");
    	}
    	else {
    	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ExpiringVerificationName"),"Letter of Assurance – Engineer","Expiring VerificationName is not present");
    	}
    	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ExpiringOptionsLocationAssign"),"1 assigned","Expiring OptionsLocationAssign is not present");
    	if (!TestCaseName.contains("ValidRenewExpireLOA")) {
    	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ExpiringLocLocationAssign"),"1 assigned","Expiring LocLocationAssign is not present");
    	}
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringLOAStatus",0);
    	CommonMethod.ClickCheckbox("ExpiringYes");
    	CommonMethod.VerifyRadioOrCheckboxSelcted("ExpiringYes", "ExpiringYes");
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringUsername",0);
    	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ExpiringUsername"),"UI Automation","Expiring LocLocationAssign is not present");
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringDate",0);
    	String ExpiringDate =CommonMethod.getattributeValueByTextContent("ExpiringDate").replaceAll("\\s+", " ");
    	String[] splits = ExpiringDate.split("UI Automation on");
		String ExpiringDateSplit = splits[1].trim();
    	CommonMethod.negativesoftassertFieldValid(ExpiringDateSplit,CommonMethod.ValidateDate(),"Expiring LocLocationAssign is not present");
    	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ExpiringReplaceLink",1);
    	CommonMethod.negativeAssertElementPresentTrue("ExpiringReplaceLink","Expiring ReplaceLink is not present");
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringConfirmbtn", 0);
		CommonMethod.Robustclick("ExpiringConfirmbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ExpiringConfirmbtn",1);
		if (TestCaseName.contains("ValidRenewExpireLOAInScorecard")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ExpiringResubmitbtn",1);
	    	CommonMethod.negativeAssertElementPresentTrue("V2ExpiringResubmitbtn","V2ExpiringResubmitbtn is not present");
		}
		else {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ExpiringResubmitbtn",1);
    	CommonMethod.negativeAssertElementPresentTrue("ExpiringResubmitbtn","ExpiringResubmitbtn is not present");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringResubmitConfirmDate",0);
    	String confirmDate =CommonMethod.getattributeValueByTextContent("ExpiringResubmitConfirmDate").replaceAll("\\s+", " ");
    	String[] splits1 = confirmDate.split(":");
		String ConfirmDateSplit = splits1[1].trim();
    	CommonMethod.negativesoftassertFieldValid(ConfirmDateSplit,CommonMethod.ValidateDateYearByDayMonthFormat(),"ConfirmDate is not present");
	}
	
    public void VerificationFilter(String FilterOption) throws IOException, InterruptedException {
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton","V2ProjectScorecardVerificationFilter");
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardVerificationFilter", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardVerificationFilter",FilterOption);
		testlog.info("And User clicks on Verification Option");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(FilterOption, 0);
		CommonMethod.ClickCheckbox(FilterOption);
		testlog.info("And User check the Photographs checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardApplybutton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardApplybutton","WPRDocumentClearButton");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
    }
	
    public void ExpiringPerformanceTestDisabledToaster() throws IOException, InterruptedException {
   
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
    	CommonMethod.scrolldowntoElement(PortfolioAndRatingLocAccDocumentTable);
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
    	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ExpiringPerformanceTestDisabled",1);
    	CommonMethod.negativeAssertElementPresentTrue("ExpiringPerformanceTestDisabled","Disabled Renew button Performance is not present");
    	CommonMethod.RobustclickElementVisible("ExpiringPerformanceTestDisabled","ExpiringPerformanceTestDisabledToaster");		
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringPerformanceTestDisabledToaster", 0);
    	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ExpiringPerformanceTestDisabledToaster"), "This document cannot be renewed.", "Disabled Renew button text does not matched ");       
		testlog.pass("**Verifies the Expiring PerformanceTest Disabled Renew button and Toaster message from Document list successful**");
    }
    
    public void ExpiringOngoingDisabledToaster() throws IOException, InterruptedException {
    	
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
    	CommonMethod.scrolldowntoElement(PortfolioAndRatingLocAccDocumentTable);
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
    	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ExpiringOngoingDisabled",1);
    	CommonMethod.negativeAssertElementPresentTrue("ExpiringOngoingDisabled","Disabled Renew button Ongoing is not present");
    	CommonMethod.RobustclickElementVisible("ExpiringOngoingDisabled","ExpiringPerformanceTestDisabledToaster");		
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringPerformanceTestDisabledToaster", 0);
    	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ExpiringPerformanceTestDisabledToaster"), "This document cannot be renewed.", "Disabled Renew button text does not matched ");       
    	testlog.pass("**Verifies the Expiring Ongoing Disabled Renew button and Toaster message from Document list successful**");
    	
    }
    
 public void ExpiringOngoingDisabledToasterClearFilter() throws IOException, InterruptedException {
    	
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocumentClearButton", 0);
		CommonMethod.Robustclick("WPRDocumentClearButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRDocumentClearButton", 1);
		testlog.info("And User click on the clear button");
    	
    }
    
    public void ValidRenewExpireInDocumentLibrary() throws IOException, InterruptedException {
    	VerificationFilter("ExpiringLOAFilterOption");
    	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ExpiringLOAEnabled",1);
    	CommonMethod.negativeAssertElementPresentTrue("ExpiringLOAEnabled","Enabled Renew button is not present");
    	CommonMethod.RobustclickElementVisible("ExpiringLOAEnabled", "ExpiringLOAStatus");
    	ValidRenewExpire();
    	testlog.pass("**Verifies the Validate the Renew page from Document list successful**");
    	
    }

   public void ValidRenewExpireInScorecard() throws IOException, InterruptedException {
	   ValidRenewExpire();
   	testlog.pass("**Verifies the Validate the Renew page from Scorecard list successful**");
   } 
   
   public void ValidateReviewTabWarningMsgAndClickNoInScorecard(String ErrorMsgLocator,
   String expectedWarningMsg, String ScorecardValidationRequired) throws IOException, InterruptedException {
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		 CommonMethod.RobustclickElementVisible("ReviewTab", "WPRReviewSubmitbtn");
		 testlog.info("When User clicks on Review button");
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewSubmitbtn", 0);
		 CommonMethod.RobustclickElementVisible("WPRReviewSubmitbtn", "WPRReviewProjectPhase");
		 testlog.info("And User clicks on Submit DocReview button");
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(ErrorMsgLocator, 0);	 
		 String actualReviewMaybeErrorMsg=CommonMethod.getattributeValueByTextContent(ErrorMsgLocator);
		 actualReviewMaybeErrorMsg= actualReviewMaybeErrorMsg.replaceAll("\\s+", " ").trim();
		 System.out.println(actualReviewMaybeErrorMsg);
		 CommonMethod.negativesoftassertFieldValid(actualReviewMaybeErrorMsg, expectedWarningMsg, "Review Tab Warning message doesn't matched ");
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectReviewTabModalCrossIcon", 0);
		 CommonMethod.RobustclickElementVisible("V2ProjectReviewTabModalCrossIcon", "WPRReviewSubmitbtn");
		 if(ScorecardValidationRequired.equalsIgnoreCase("Yes")) {
		 rc.clickScorecard();
		 CommonMethod.scrollDown();
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRInnovationVClickNo", 0);
		 CommonMethod.JavascriptClickElement("WPRInnovationVClickNo"); 
		 if(CommonMethod.isElementsExist("DatePickerConfirmButton", 10)) {
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerConfirmButton", 0); 
		 CommonMethod.Robustclick("DatePickerConfirmButton");
		 }
		 }   
   }
   
   public void clickMayBeAndValidWarningMessageInReview() throws IOException, InterruptedException {
	 rc.clickScorecard();
	 CommonMethod.scrollDown();
	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRInnovationV", 0);
	 CommonMethod.JavascriptClickElement("WPRInnovationV"); 
	 if(CommonMethod.isElementsExist("DatePickerConfirmButton", 10)) {
	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerConfirmButton", 0); 
	 CommonMethod.Robustclick("DatePickerConfirmButton");
	 }
	 ValidateReviewTabWarningMsgAndClickNoInScorecard("WPRReviewWarningMsg", "Please ensure all features are marked as either a YES or NO and then resubmit.", "Yes");
   }
   
   public void AssignLocationInScorecardDocumentAndValidWarningMessageInReviewForPendingDocuments(String FeatureName, String SheetName, int rowNum, String Commodity,
			String FileName, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
			Boolean NoteRequired, Boolean IntentCheckboxRequired) throws Exception {
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
			Feature = CommonMethod.findElements("V2ProjectWPRPFeature");
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
				if (FileName.contains("Audit") || FileName.contains("Feature")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionbtn", 0);
					CommonMethod.RobustclickElementVisible("WPRAddOptionbtn", "ScorecardAddedOption");
					testlog.info("And User clicks on save button");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAddedOption", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionCloseIcon", 0);
				CommonMethod.Robustclick("WPRAddOptionCloseIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRAddOptionCloseIcon", 1);
				generic.assignLocationGeneric(Commodity, true, false, false, false, false);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
		ValidateReviewTabWarningMsgAndClickNoInScorecard("WPRReviewWarningMsg", "Please ensure you have fulfilled all the pending tasks in the Documents Library tab.", "Yes");
   }
   
public void ValidRenewExpireInScorecard(String FeatureName) throws IOException, InterruptedException {
	   testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
		List<WebElement> Feature = CommonMethod.findElements("RatingFeatureName");
		testlog.info("Fetching total no. of credits on page");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("And User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						PortfolioAndRatingLocAccDocumentTable);
				testlog.info("And User clicks on VerificationTab");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.scrolldowntoElement("Uploadbutton");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
				testlog.info("And User will be redirected to Document Upload Table page");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ExpiringLOAEnabled",1);
		    	CommonMethod.negativeAssertElementPresentTrue("ExpiringLOAEnabled","Disabled Renew button is not present");
		    	CommonMethod.RobustclickElementVisible("ExpiringLOAEnabled", "ExpiringLOAStatus");
	            ValidRenewExpire();
         	testlog.pass("**Verifies the Validate the Renew page from Scorecard list successful**");
         	CommonMethod.JavascriptClickElement(ele);
     		break;
     		}
     	}
     	CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
     	}

public void ValidRenewExpireInScorecard(String FeatureName, String DisableType) throws IOException, InterruptedException {
	   testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
		List<WebElement> Feature = CommonMethod.findElements("RatingFeatureName");
		testlog.info("Fetching total no. of credits on page");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("And User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						PortfolioAndRatingLocAccDocumentTable);
				testlog.info("And User clicks on VerificationTab");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.scrolldowntoElement("Uploadbutton");
				if(DisableType.equalsIgnoreCase("Performance")) {
				ExpiringPerformanceTestDisabledToaster();
				}
				if(DisableType.equalsIgnoreCase("Ongoing")) {
			    ExpiringOngoingDisabledToaster();
				}
      	testlog.pass("**Verifies the Validate the Renew page from Scorecard list successful**");
      	CommonMethod.JavascriptClickElement(ele);
  		break;
  		}
  	}
  	CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
  	}
   
   public void ValidateExpireRemoveMessage() throws IOException, InterruptedException {
	   
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringAttentionLink", 0);
   	CommonMethod.JavascriptClickElement("ExpiringAttentionValid");
   	CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ExpiringAttentionValid",1);
   	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringRenewReplace", 0);
   	String[] ExpireMessage = { "Expiring Soon", "Expired", "Renew a document","Replace a document","Archive a document" };
   	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringRemoveRenewMessage", 0);
	List<WebElement> ExpireRemoveMessage = CommonMethod.findElements("ExpiringRemoveRenewMessage");
	int i = 0;
	
	for (WebElement s : ExpireRemoveMessage) {
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText(s), ExpireMessage[i],
				"Expire RemoveMessage mismatch: " + ExpireMessage[i]);
		i++;
		if(i < 5) {
			break;
	}
   }
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ExpiringRenewReplace",1);
	CommonMethod.negativeAssertElementPresentTrue("ExpiringRenewReplace","Disabled Renew button is not present");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringAttentionLink", 0);
	CommonMethod.JavascriptClickElement("ExpiringAttentionLink");
   }
   
   public void RenewalTeamValid() throws IOException, InterruptedException {
	   if (CommonMethod.isElementsExist("RenewalTeamValid", 20)) {
		   CommonMethod.JavascriptClickElement("RenewalTeamValid"); 
	   }
	   CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationTableTr", 2);
   }   
   
   public void ValidateDashboard() throws IOException, InterruptedException {
	if (CommonMethod.isElementsExist("ScorecardBannerClose", 10)) {
	    CommonMethod.Robustclick("ScorecardBannerClose");
	}
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2RenewalButton",1);
	CommonMethod.negativeAssertElementPresentTrue("V2RenewalButton","Renew now button is not present ");  
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WERSealImgValidate",1);
	CommonMethod.negativeAssertElementPresentTrue("WERSealImgValidate","Seal is not present "); 
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectDashboardHsrSealDate",1);
	CommonMethod.negativeAssertElementPresentTrue("V2ProjectDashboardHsrSealDate","Seal Date is not present "); 
   }
   
	public void ValidateDisabledButtonsInLocationsTab() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "PortfolioLocationLanding");
		testlog.info("When User clicks on LocationsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationLanding", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.RobustclickElementVisible("AddButton", "DisabledAddLocationButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("DisabledAddLocationButton", 1);
		CommonMethod.assertisElementPresentTrue("DisabledAddLocationButton", "Disabled Add Location Button is enabled ");		
		CommonMethod.moveToElement("AddLocationButton");
		rc.RemoveSpaceAndValidate("AddLocationBtnTooltip", "To add/remove locations contact us via the Support tab");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationsImportButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationsImportButton", "PortfolioUploadLocationButton");			
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadLocationButton", 0);
		CommonMethod.uploadFile("PortfolioUploadLocationButton", ImportfilePortfolioLocationRenewal, "UploadFileVerifyScorecard");
		testlog.info("And User upload Excel Location List Document");			
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("DisabledNextButton", 1);
		CommonMethod.assertisElementPresentTrue("DisabledNextButton", "Disabled Next Button is enabled ");
	}
	
	public void ScorecardfillHSRWPRPurseNo(int NoStart, int NoEnd, int DifferencePlusOne,String purseNo) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(purseNo, 0);
		List<WebElement> NoButton;
		Boolean flag = false;
	
		NoButton = CommonMethod.findElements(purseNo);
		int j = DifferencePlusOne;
		for (int i = NoStart; i <= NoEnd; i++) {
			long startTime = System.currentTimeMillis();
			int RemainingNo = NoButton.size();
			do {
				if(!((System.currentTimeMillis() - startTime) < 60000)) {
					flag = true;
					System.out.println("Build Stuck");
					break;
				}
				CommonMethod.WaitUntilClickble(NoButton.get(RemainingNo - j), 30);
				CommonMethod.JavascriptClickElement(NoButton.get(RemainingNo - j));
				if (CommonMethod.isElementsExist("DatePickerConfirmButton", 10)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerConfirmButton", 0);
					CommonMethod.RobustclickElementVisible("DatePickerConfirmButton", "PortfolioScorecardPursueToast");
				}
				if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 10)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
				}
				Thread.sleep(1000);
				NoButton = CommonMethod.findElements(purseNo);
			} while ((NoButton.size() == RemainingNo));
			RemainingNo--;
			j--;
			if(flag) {
				System.out.println("Build Exit");
				break;
			}
		}
	}
	
 public void PerformanceValidateDashboardBeforeReActivate() throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringAttentionLink", 0);
	String actualText=CommonMethod.getattributeValueByTextContent("ExpiringAttentionLink");
	actualText= actualText.replaceAll("\\s+", " ").trim();
	System.out.println(actualText);
	CommonMethod.negativesoftassertFieldValid(actualText.toLowerCase(), "attention needed", "attention needed text does not matched ");	
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickHereLink", 0);
	CommonMethod.JavascriptClickElement("V2ProjectClickHereLink");
	CommonMethod.switchToChildTab();
	String actualURL = CommonMethod.getCurrentUrl();
	CommonMethod.negativesoftassertFieldValid(actualURL, "renewal-guide", "URL does not matched ");
	CommonMethod.switchToParentTab();
	}
 
 public void PerformanceValidateReActivateDashboard() throws IOException, InterruptedException {
	 rc.RemoveSpaceAndValidate("RenewalDashboardText", "Reactivate your account");	
	 rc.RemoveSpaceAndValidate("RenewalDashboardSecondText", "Your WELL Rating has expired. You can reactivate your rating renewal process by paying a reactivation fee.");
	 CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ReActivateButton", 1);
	 CommonMethod.assertisElementPresentTrue("ReActivateButton", "ReActivate button is not present ");
	}
 
 public void ValidatePerformanceReActivateBtnAndExpiredFilter() throws IOException, InterruptedException {
	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprStatusResultList", 0);
	 String actualStatus = CommonMethod.getattributeValueByTextContent("HsrWprStatusResultList");
	 actualStatus= actualStatus.replaceAll("\\s+", " ").trim().toLowerCase();
	 System.out.println(actualStatus);	 
	 CommonMethod.negativesoftassertFieldValid(actualStatus, "expired", "expired status does not matched in performance search ");
	 rc.RemoveSpaceAndValidate("ReactivateNowButton", "Reactivate Now");
	}
 
  public void ClickReactivateButton() throws IOException, InterruptedException {
	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReActivateButton", 0);
	 CommonMethod.RobustclickElementVisible("ReActivateButton", "Acknowledge");
  }
  
  public void PerformanceValidateConfirmYourRenewalFeesPage() throws IOException, InterruptedException {
	 rc.ClickBilling();
	 rc.RemoveSpaceAndValidate("ValidateReactivationFeeText", "Reactivation fee");
	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateReactivationFeeAmount", 0);
	 String actualReactivationFeeAmount = CommonMethod.getattributeValueByTextContent("ValidateReactivationFeeAmount");
	 actualReactivationFeeAmount= actualReactivationFeeAmount.replaceAll("\\s+", " ").trim();
	 System.out.println(actualReactivationFeeAmount);
	 CommonMethod.negativesoftassertFieldValidEquals(actualReactivationFeeAmount, "$1,000", "Reactivation fee: $1,000 does not matched ");
  }
  
  public void PerformanceValidateReactivationBilling() throws IOException, InterruptedException {
	testlog.info("Given User is on Dashboard page");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BiilingTab", 0);
	CommonMethod.RobustclickElementVisible("BiilingTab", "ReactivateInvoice");
	testlog.info("When User clicks on BiilingTab"); 
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ReactivateInvoice", 1);
	CommonMethod.assertisElementPresentTrue("ReactivateInvoice", "WELL Performance Rating Reactivate Invoice is not present ");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RenewalInvoice", 1);
	CommonMethod.assertisElementPresentTrue("RenewalInvoice", "WELL Performance Rating Enrollment renewal Invoice is not present ");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingProjectInvoice", 0);
	CommonMethod.RobustclickElementVisible("BillingProjectInvoice", "BillingProjectCreateReactivationInvoiceBtn");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("BillingProjectCreateReactivationInvoiceBtn", 1);
	CommonMethod.assertisElementPresentTrue("BillingProjectCreateReactivationInvoiceBtn", "Create Reactivation Invoice button is not present ");
  }
  
  public void ClickAndValidateViewInvoices() throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
	CommonMethod.RobustclickElementVisible("ReviewViewButton", "ReviewAdminActionsButton");
    v2project.ReviewAdminActionsButton();
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewReturnButton", 0);
	CommonMethod.RobustclickElementVisible("ReviewReturnButton", "ReviewReturnSubmit"); 	  
	CommonMethod.scrolldowntoElement("ReviewViewInvoicesBtnInReturnReview");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewInvoicesBtnInReturnReview", 0);
	CommonMethod.RobustclickElementVisible("ReviewViewInvoicesBtnInReturnReview", "ReviewValidateDescriptionInViewInvoices");
	rc.RemoveSpaceAndValidate("ReviewValidateDescriptionInViewInvoices", "TEST DESCRIPTION");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewValidateAmountInViewInvoices", 0);	
	String actualViewInvoiceAmount=CommonMethod.getattributeValueByTextContent("ReviewValidateAmountInViewInvoices");
	actualViewInvoiceAmount= actualViewInvoiceAmount.replaceAll("\\s+", " ").trim();
	System.out.println(actualViewInvoiceAmount);	
	CommonMethod.negativesoftassertFieldValidEquals(actualViewInvoiceAmount, "$3,250", "Amount does not matched ");
	rc.RemoveSpaceAndValidate("ReviewValidatePaymentStatusInViewInvoices", "paid");	
	CommonMethod.refreshBrowser();
  }
  
  public void EditSubsetAndValidateFilters(String SheetName, int rowNum) throws Exception {		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Subsettab", 0);
	    CommonMethod.JavascriptClickElement("Subsettab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableEditIcon", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAndRatingLocAccDocumentTableEditIcon","PortfolioSubsetNameTextBox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetNameTextBox", 0);
		CommonMethod.clearAndSendKey("PortfolioSubsetNameTextBox", "I am Updated Subset Name Text Box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDescriptionTextBox", 0);
		CommonMethod.clearAndSendKey("PortfolioDescriptionTextBox", "I am Updated Optional Description Text Box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetSaveAndContinueBtnFirstPage", 0);
		CommonMethod.Robustclick("PortfolioSubsetSaveAndContinueBtnFirstPage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFiltersPilot", 0);
		CommonMethod.JavascriptClickElement("PortfolioScorecardFiltersPilot");
		portfolio.SubsetClearFilter();

		// Validate Countries Filter
		pfu.validAssignLocationCountryFilter("India");
		pfu.validAssignLocationFilter("5", "Country");

		// Validate SpaceType Filter
		pfu.validAssignLocationSpaceTypeFilter("Warehouses");
		pfu.validAssignLocationFilter("5", "SpaceType");

		// Validate OwnershipType Filter
		pfu.validAssignLocationOwnershipTypeFilter("Tenancy");
		pfu.validAssignLocationFilter("5", "OwnershipType");

		// Validate OccupancySizeRange Filter
		pfu.validSubsetOccupancySizeRangeFilter("10,000 - 49,999 ft²");
		pfu.validAssignLocationFilter("6", "OccupancySizeRange");

		// Validate ConstructionStatus Filter
		pfu.validAssignLocationConstructionStatusFilter("Not under construction/renovation");
		pfu.validAssignLocationFilter("6", "ConstructionStatus"); 

		// Validate LocationStatus Filter
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetLocationStatusDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioSubsetLocationStatusDropdown", "Not assigned");
		portfolio.SubsetApplyFilter();
		pfu.validAssignLocationFilter("6", "Status");
		portfolio.SubsetCloseModal();

		// Validate SearchLocation Textbox
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditSearchLocation", 0);
		CommonMethod.sendKeys("PortfolioEditSearchLocation", "location 01");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetLocationOne", 0);
		CommonMethod.assertisElementPresentTrue("PortfolioSubsetLocationOne", "Location does not matched");

		// Validate Save and Continue
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetLocationOneCheckbox", 0);
		CommonMethod.ClickCheckbox("PortfolioSubsetLocationOneCheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetSaveAndContinueBtnSecondPage", 0);
		CommonMethod.JavascriptClickElement("PortfolioSubsetSaveAndContinueBtnSecondPage");

		// Click on Save and Exit
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSaveAndExitBtn", 0);
		CommonMethod.Robustclick("PortfolioSaveAndExitBtn");

		// Validate Updated Subset Table Name
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableName", 0);
		String actualUpdatedSubsetTableName = CommonMethod.getattributeValueByTextContent("PortfolioSubsetTableName")
				.trim();
		CommonMethod.negativesoftassertFieldValid(actualUpdatedSubsetTableName, "I am Updated Subset Name Text Box",
				"I am Updated Subset Name Text Box: Text does not matched ");

		// Validate Updated Subset Table Locations Count
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableLocationsCount", 0);
		Thread.sleep(2000);
		String actualUpdatedSubsetTableLocationsCount = CommonMethod
				.getattributeValueByTextContent("PortfolioSubsetTableLocationsCount").trim();
		CommonMethod.negativesoftassertFieldValid(actualUpdatedSubsetTableLocationsCount, "1",
				"Table Locations Count does not matched ");
	}
  
 public void PerformanceDeleteSubset() throws Exception {		
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubsetDeleteIcon", 0);
	CommonMethod.JavascriptClickElement("SubsetDeleteIcon");
	if(CommonMethod.isElementsExist("DeletePopUp", 10)) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DeletePopUp", 0);
		CommonMethod.Robustclick("DeletePopUp");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DeletePopUp", 1);
	}	
	CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("SubsetDeleteIcon", 1);
	CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("SubsetDeleteIcon");
	CommonMethod.negativeAssertElementNotPresentFalse("SubsetDeleteIcon","Subset delete icon is present");
	CommonMethod.refreshBrowser();
	}
 
	public void SearchPerformanceFilterByStatus(String SheetName, int rowNum, String Status)
			throws IOException, InterruptedException {
		testlog.info("StatusList: " + Status);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRFilterStatus", 0);
		String actualText=CommonMethod.getattributeValueByTextContent("WPRFilterStatus");
		actualText= actualText.replaceAll("\\s+", " ").trim();
		System.out.println(actualText);		
		CommonMethod.negativesoftassertFieldValidEquals(actualText.toLowerCase(), Status.toLowerCase(),
				"Authenticted User Status Search doesn't match");		
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int var = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var), "1", "Authenticted User WPR Search failed");
		testlog.info("Then Authenticted User verifies Search filter V2Project " + Status.toLowerCase());
		testlog.pass("**Verifies the Search V2Project By StatusName successfully for Authenticted User**");
	}
	

	public void ValidateUpdateForProjectsWithLocationsInChinaModalDuringRegistration(String SheetName, int rowNum, String DataValidate, String ProjectName) throws IOException, InterruptedException {		
		testlog.info("Given User logged in to the WELL certified account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLPerformanceRatingNavBar");
		CommonMethod.RobustclickElementVisible("WELLPerformanceRatingNavBar", "WPRstartNewProject");
		testlog.info("When User clicks on WELL PerformanceRating from top menu under Projects");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRstartNewProject", 0);
		CommonMethod.RobustclickElementVisible("WPRstartNewProject", "WPREnrollOption");
		testlog.info("And User clicks on Start project button");
		CommonMethod.RobustclickElementVisible("WPREnrollOption", "WPRenrollbtn");
		testlog.info("And User clicks on EnrollNow button");
		CommonMethod.RobustclickElementVisible("WPRenrollbtn", "WPRWEROrgContinebtn");
		testlog.info("And User clicks on Enroll button");
		String AccountName = ProjectName + CommonMethod.randomNumber(8000000);
		testlog.info("ProjectName: " + AccountName);
		data.setCellData(SheetName, "projectName", rowNum, AccountName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPROrgName", 0);
		CommonMethod.sendKeys("WPROrgName", AccountName);
		testlog.info("And User enter data to EnrollName");
		rc.SelectOwnerOrg(SheetName, rowNum);
		data.setCellData(SheetName, "OrgName", rowNum, CommonMethod.getText("OrgName"));
		testlog.info("OrganizationName: " + data.getCellData(SheetName, "OrgName", rowNum));
		String Ownername = USfaker.address().firstName();
		CommonMethod.WaitUntilPresence("WPROwnerName", 30);
		CommonMethod.sendKeys("WPROwnerName", Ownername);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("WPROwnerName"));
		CommonMethod.selectdropdownrandom("OrgIndustry");
		data.setCellData(SheetName, "OrgIndustry", rowNum, CommonMethod.getSelectedDropdownValue("OrgIndustry"));
		 rc.SelectEnterpriseProviders(SheetName,rowNum);
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WEROwnercbx", 0);
			CommonMethod.ClickCheckbox("WEROwnercbx");
			CommonMethod.VerifyRadioOrCheckboxSelcted("Hsrbilladdcheckbox");
			CommonMethod.ClickCheckbox("Hsrbilladdcheckbox");
			testlog.info("And User checks the Billing checkbox");
			CommonMethod.ClickCheckbox("WPROwnerInfocbx");
			testlog.info("And User checks the enroll checkbox");
		if(DataValidate.equalsIgnoreCase("MainlandChina")) {
		CommonMethod.selectdropdownValue("WPRExamOwnerCountry", "CN");
		CommonMethod.selectdropdownVisibletext("WPRExamOwnerState", "Beijing");	
		}
		String ProjectAddress = USfaker.address().streetAddress();
		String ProjectCity = USfaker.address().cityName();
		String PostalCode = USfaker.address().zipCode();
		CommonMethod.sendKeys("WPRExamOrgAddress", ProjectAddress);
		CommonMethod.sendKeys("WPRExamOrgCity", ProjectCity);
		CommonMethod.sendKeys("WPRExamOrgPostalcode", PostalCode);
		data.setCellData(SheetName, "Country", rowNum,
				CommonMethod.getSelectedDropdownAttribute("WPRExamOwnerCountry"));
		data.setCellData(SheetName, "State", rowNum, CommonMethod.getattributeValue("WPRExamOwnerState"));
		data.setCellData(SheetName, "Street", rowNum, CommonMethod.getattributeValue("WPRExamOrgAddress"));
		data.setCellData(SheetName, "City", rowNum, CommonMethod.getattributeValue("WPRExamOrgCity"));
		data.setCellData(SheetName, "PostalCode", rowNum, CommonMethod.getattributeValue("WPRExamOrgPostalcode"));
		testlog.info("And User enter data to Owner Country, State, Street address, City and Postal Code fields");
		testlog.info("Organization Address: " + ProjectAddress);
		testlog.info("Organization City: " + ProjectCity);
		testlog.info("Organization Postalcode: " + PostalCode);
		CommonMethod.ClickCheckbox("WEROwnercbx");
		testlog.info("And User checks the Owner checkbox");
		CommonMethod.scrollDown();
		CommonMethod.RobustclickElementVisible("WPRWEROrgContinebtn", "WPROwnerRegContinuebtn");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRGetUpdatePojectText", 0);
		rc.RemoveSpaceAndValidate("WPRGetUpdatePojectText", "Update for projects with locations in China");
	}
	
	public void SearchPerformanceByIDAndCompleteTheForm(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLPerformanceRatingNavBar");
		CommonMethod.WaitUntilVisibility("WELLPerformanceRatingNavBar", 300);
		CommonMethod.RobustclickElementVisible("WELLPerformanceRatingNavBar", "RatingIdClick");
		CommonMethod.verifyDropdownValues("HSRTypeDropdown", "HsrType");
		testlog.info("When User clicks on WELL PerformanceRating from top menu under Projects");
		String wprId = data.getCellData(SheetName, "ProjectID", rowNum);
		testlog.info("Performance ID: " + wprId);
		CommonMethod.WaitUntilClickble("WPRWERId", 60).sendKeys(wprId);
		testlog.info("And User enter data to WprId");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int ProjectCount = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount), "1", "HealthSafety Search failed");
		CommonMethod.sendKeys("HsrNameList", data.getCellData(SheetName, "projectName", rowNum));
		testlog.info("And User enter data to WprName");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int ProjectNameCount = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectNameCount), "1", "Portfolio Search failed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RatingIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project Id doesn't matches in search");
		CommonMethod.Robustclick("RatingIdClick");;
		String Area = CommonMethod.randomNumberBetweenRanges(100, 50000);
		testlog.info("Locationsize: " + Area);
		CommonMethod.clearAndSendKey("Locationsize", Area);
		testlog.info("And User enter the Location Size");
		data.setCellData(SheetName, "Locationsize", rowNum, CommonMethod.getattributeValue("Locationsize"));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPROwnerRegContinuebtn", 0);
		CommonMethod.JavascriptClickElement("WPROwnerRegContinuebtn");
		testlog.info("And User clicks on continue button");
		if (CommonMethod.isElementsExist("HsrWPRYesMyOrganizationCbx", 5)) {
			CommonMethod.RobustclickElementVisible("WPRWERReviewContinuebutton", "MandatoryFieldErrorMessage");
			testlog.info("And User clicks on continue button");
			CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
			CommonMethod.negativesoftassertPageSource(
					"Yes, my organization meets the criteria of the listed discount category.* is required.",
					"My Organization CheckBox Error Name");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWPRYesMyOrganizationCbx", 0);
			CommonMethod.ClickCheckbox("HsrWPRYesMyOrganizationCbx");
			testlog.info("And User checks the MyOrganization checkbox");
		}
		CommonMethod.RobustclickElementVisible("WPRWERReviewContinuebutton", "WPRWERtermContinuebutton");
		if (CommonMethod.isElementsExist("WPRProgramFeePublicrbtn", 10)) {
			CommonMethod.ClickCheckbox("WPRProgramFeePublicrbtn");
			testlog.info("And User checks the Program Fee Public checkbox");
		}
		CommonMethod.scrollDown();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAcknowledecbx", 0);
		CommonMethod.ClickCheckbox("WPRAcknowledecbx");
		testlog.info("And User checks the Acknowledge checkbox");
		CommonMethod.RobustclickElementVisible("WPRWERtermContinuebutton", "BillingLanding");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.info("Then User will be redirected to BillingLanding page");
		testlog.pass("**Verifies the Registration successful**");		
	}
		
public void promotionCardValidation() throws Exception {
	testlog.info("Given User is on Dashboard page");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PromotionTab", 0);
	CommonMethod.RobustclickElementVisible("PromotionTab", "WPRPromotionCardCount");
	testlog.info("When User clicks on PromotionTab");
	rc.ValidCardCount("WPRPromotionCardCount");
	testlog.info("Then User verifies Promotion card count");
	testlog.pass("**Verify card count successfully**");
}

    public void StoreIdMainlandChinaProjectPerformance(String SheetName, int rowNum) throws IOException, InterruptedException {
	 String currentUrl = CommonMethod.getCurrentUrl();	
	 System.out.println(currentUrl);
	 String[] parts = currentUrl.split("/");
	 String WprId = parts[parts.length - 1];
	 System.out.println("WprId"+WprId);
	 data.setCellData(SheetName, "ProjectID", rowNum, WprId);
    }
}