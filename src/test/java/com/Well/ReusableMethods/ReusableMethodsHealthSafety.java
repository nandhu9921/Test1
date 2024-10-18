package com.Well.ReusableMethods;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class ReusableMethodsHealthSafety extends BaseClass {

	public void RegisterHealthSafety(String SheetName, int rowNum, String DataValidate, String ProjectName)
			throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL certified account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLHealthSafetyNavBar");
		CommonMethod.RobustclickElementVisible("WELLHealthSafetyNavBar", "HsrWellhealthstartprojectbtn");
		testlog.info("When User clicks on WELL HealthSafety from top menu under Projects");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWellhealthstartprojectbtn", 0);
		CommonMethod.RobustclickElementVisible("HsrWellhealthstartprojectbtn", "HsrEnrollnowbtn");
		testlog.info("And User clicks on Start project button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrEnrollnowbtn", 0);
		CommonMethod.RobustclickElementVisible("HsrEnrollnowbtn", "HsrEnrollbtn");
		testlog.info("And User clicks on EnrollNow button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrEnrollbtn", 0);
		CommonMethod.RobustclickElementVisible("HsrEnrollbtn", "Hsrenrollcontinuebtn");
		testlog.info("And User clicks on Enroll button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Hsrenrollcontinuebtn", 0);
		CommonMethod.RobustclickElementVisible("Hsrenrollcontinuebtn", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Organization is required.", "Organization Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner name is required.", "Owner Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Organization is required.", "Organization Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Organization Industry is required.",
				"Organization Industry Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Consultant is required.",
				"Consultant Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Country is required.", "Country Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Street is required.", "Street Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("City is required.", "City Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Postal Code is required.", "Postal Code Error Mismatch");
		testlog.info(
				"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		String erollName = ProjectName +CommonMethod.randomNumber(8000000);
		CommonMethod.sendKeys("HsrenrollName", erollName);
		data.setCellData(SheetName, "projectName", rowNum, CommonMethod.getattributeValue("HsrenrollName"));
		testlog.info("And User enter data to EnrollName");
		testlog.info("HsrName: " + data.getCellData(SheetName, "projectName", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Hsrenrollcheckbox", 0);
		CommonMethod.ClickCheckbox("Hsrenrollcheckbox");
		testlog.info("And User checks the enroll checkbox");
		String Ownername = USfaker.address().firstName();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPROwnerName", 0);
		CommonMethod.sendKeys("WPROwnerName", Ownername);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("WPROwnerName"));
		rc.SelectOwnerOrg(SheetName, rowNum);
		data.setCellData(SheetName, "OrgName", rowNum, CommonMethod.getText("OrgName"));
		CommonMethod.verifyDropdownValues("HsrWprEditOrgIndustry", "OrganizationIndustry");
		CommonMethod.verifyDropdownValues("HsrSectorDiscounts", "HsrSectorDiscounts");
		CommonMethod.verifyDropdownValues("WPRExamOwnerCountry", "Country");
		CommonMethod.selectdropdownrandom("OrgIndustry");
		testlog.info("And User select OrgIndustry");
		rc.SelectEnterpriseProviders(SheetName, rowNum);
		data.setCellData(SheetName, "OrgIndustry", rowNum, CommonMethod.getSelectedDropdownValue("OrgIndustry"));
		CommonMethod.selectdropdownValue("CountryDropdown", "US");
		data.setCellData(SheetName, "Country", rowNum, CommonMethod.getSelectedDropdownAttribute("CountryDropdown"));
		CommonMethod.selectdropdownrandom("ProjectState");
		testlog.info("And User enter data to Owner Country, State, Street address, City and Postal Code fields");
		testlog.info("Country: " + CommonMethod.getSelectedDropdownAttribute("CountryDropdown"));
		testlog.info("OwnerName: " + data.getCellData(SheetName, "OwnerName", rowNum));
		testlog.info("OrganizationName: " + data.getCellData(SheetName, "OrgName", rowNum));
		testlog.info("State: " + CommonMethod.getSelectedDropdownAttribute("ProjectState"));
		data.setCellData(SheetName, "State", rowNum, CommonMethod.getSelectedDropdownValue("ProjectState"));
		String ProjectAddress = USfaker.address().streetAddress();
		String ProjectCity = USfaker.address().cityName();
		String PostalCode = USfaker.address().zipCode();
		CommonMethod.sendKeys("StreetWithId", ProjectAddress);
		data.setCellData(SheetName, "Street", rowNum, CommonMethod.getattributeValue("StreetWithId"));
		testlog.info("Hsrenrollstreet: " + data.getCellData(SheetName, "Street", rowNum));
		CommonMethod.sendKeys("CityWithId", ProjectCity);
		data.setCellData(SheetName, "City", rowNum, CommonMethod.getattributeValue("CityWithId"));
		testlog.info("City: " + data.getCellData(SheetName, "City", rowNum));
		CommonMethod.sendKeys("PostalWithId", PostalCode);
		data.setCellData(SheetName, "PostalCode", rowNum, CommonMethod.getattributeValue("PostalWithId"));
		testlog.info("PostalCode: " + data.getCellData(SheetName, "PostalCode", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WEROwnercbx", 0);
		CommonMethod.ClickCheckbox("WEROwnercbx");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Hsrbilladdcheckbox", 0);
		CommonMethod.VerifyRadioOrCheckboxSelcted("Hsrbilladdcheckbox");
		CommonMethod.ClickCheckbox("Hsrbilladdcheckbox");
		testlog.info("And User checks the Billing checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Hsrenrollcontinuebtn", 0);
		CommonMethod.JavascriptClickElement("Hsrenrollcontinuebtn");
		testlog.info("And User clicks on continue button");
		if (DataValidate.equalsIgnoreCase("true")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BackButton2", 0);
			CommonMethod.RobustclickElementVisible("BackButton2", "Hsrenrollcontinuebtn");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("HsrenrollName"), erollName,
					"HSR Enroll Name Error Mismatch");
			CommonMethod.VerifyRadioOrCheckboxSelcted("Hsrenrollcheckbox");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("OrgName"),
					data.getCellData(SheetName, "OrgName", rowNum), "Org Name Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("OrgIndustry"),
					data.getCellData(SheetName, "OrgIndustry", rowNum), "Org Industry Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownAttribute("CountryDropdown"),
					data.getCellData(SheetName, "Country", rowNum), "Country Name Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("ProjectState"),
					data.getCellData(SheetName, "State", rowNum), "State Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("StreetWithId"),
					data.getCellData(SheetName, "Street", rowNum), "Street Name Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("CityWithId"),
					data.getCellData(SheetName, "City", rowNum), "City Name Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PostalWithId"),
					data.getCellData(SheetName, "PostalCode", rowNum), "Postal code Error Mismatch");
			CommonMethod.VerifyRadioOrCheckboxSelcted("Hsrbilladdcheckbox");
			testlog.info("And User verifies the added details about you by clicking on back button");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Hsrenrollcontinuebtn", 0);
			CommonMethod.JavascriptClickElement("Hsrenrollcontinuebtn");
		}
		if (DataValidate.equalsIgnoreCase("true")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrRegcontinuebtn", 0);
		CommonMethod.RobustclickElementVisible("HsrRegcontinuebtn", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MandatoryFieldErrorMessage", 0);
		CommonMethod.negativesoftassertPageSource("On behalf of owner is required.", "Owner CheckBox Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Is the Owner organization an IWBI member?* is required.",
				"Owner Organization Name Error Mismatch");
		testlog.info(
				"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Hsrregcheckbox", 0);
		CommonMethod.ClickCheckbox("Hsrregcheckbox");
		testlog.info("And User checks the terms and conditions checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrIwbimemberdropdown", 0);
		CommonMethod.selectdropdownVisibletext("HsrIwbimemberdropdown", "No");
		testlog.info("And User select Iwbi member");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrRegcontinuebtn", 0);
		CommonMethod.RobustclickElementVisible("HsrRegcontinuebtn", "HsrEnrollButton");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrEnrollButton", 0);
		CommonMethod.RobustclickElementVisible("HsrEnrollButton", "Hsrlocations");
		CommonMethod.scrollUp();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Hsrlocations", 0);
		CommonMethod.sendKeys("Hsrlocations", "10");
		data.setCellData(SheetName, "Location", rowNum, CommonMethod.getattributeValue("Hsrlocations"));
		testlog.info("And User enter data to location");
		testlog.info("Hsrlocations: " + data.getCellData(SheetName, "Location", rowNum));
		rc.ValidateSpaceTypeDropdown("HSRSpaceTypeCount" ,"28");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect", 0);
		CommonMethod.RobustclickElementVisible("Multiselect", "HsrWPRlocationsSpaceOption");
		Thread.sleep(2000);
		CommonMethod.RobustclickElementVisible("HsrWPRlocationsSpaceOption", "Hsrlocationsize");
		data.setCellData(SheetName, "SpaceTypes", rowNum, CommonMethod.getText("Multiselect"));
		testlog.info("And User select the space type");
		testlog.info("Location SpaceType: " + data.getCellData(SheetName, "SpaceTypes", rowNum));
		String Area = CommonMethod.randomNumberBetweenRanges(100, 50000);
		CommonMethod.clearAndSendKey("Hsrlocationsize", Area);
		data.setCellData(SheetName, "Area", rowNum, CommonMethod.getattributeValue("Hsrlocationsize"));
		testlog.info("Hsrlocations: " + data.getCellData(SheetName, "Area", rowNum));
		testlog.info("And User enter the Location Size");
		CommonMethod.WaitUntilVisibility("HsrLocContinuebutton", 120);
		CommonMethod.RobustclickElementVisible("HsrLocContinuebutton", "HsrDiscountBackButton");
		testlog.info("And User clicks on continue button");
		if (DataValidate.equalsIgnoreCase("true")) {
			CommonMethod.RobustclickElementVisible("HsrDiscountBackButton", "Hsrlocations");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Hsrlocations", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("Hsrlocations"),
					data.getCellData(SheetName, "Location", rowNum), "Location Count Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("Multiselect"),
					data.getCellData(SheetName, "SpaceTypes", rowNum), "SpaceType Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("Hsrlocationsize"),
					data.getCellData(SheetName, "Area", rowNum), "Area Size Error Mismatch");
			testlog.info("And User verifies the added details about you by clicking on back button");
			CommonMethod.RobustclickElementVisible("HsrLocContinuebutton", "HsrDiscountBackButton");
			testlog.info("And User clicks on continue button");
		}
		if (CommonMethod.isElementsExist("HsrWPRYesMyOrganizationCbx", 10)) {
			CommonMethod.RobustclickElementVisible("HsrProgramFeeContinuebtn", "HsrWPRYesMyOrganizationCbx");
			CommonMethod.negativesoftassertPageSource(
					"Yes, my organization meets the criteria of the listed discount category.* is required.",
					"My Organization CheckBox Error Name");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWPRYesMyOrganizationCbx", 0);
			CommonMethod.ClickCheckbox("HsrWPRYesMyOrganizationCbx");
			testlog.info("And User checks the MyOrganization checkbox");
		}
		CommonMethod.RobustclickElementVisible("HsrProgramFeeContinuebtn", "HsrProgramFeePublicrbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrProgramFeePublicrbtn", 0);
		CommonMethod.scrolldowntoElement("HsrProgramFeePublicrbtn");
		if (SheetName.equalsIgnoreCase("Hsr")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrProgramFeePublicrbtn", 0);
			CommonMethod.VerifyRadioOrCheckboxSelcted("HsrProgramFeePublicrbtn","PublicYes");
			CommonMethod.ClickCheckbox("HsrProgramFeePublicrbtn");
			testlog.info("And User check on Program Fee Private checkboxes");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrAcknowledecbx", 0);
		CommonMethod.ClickCheckbox("HsrAcknowledecbx");
		testlog.info("And User check on Acknowlede checkboxes");
		CommonMethod.RobustclickElementVisible("HsrReviewbtn", "BillingLanding");
		testlog.info("And User clicks on Review button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.info("Then User will be redirected to Billing invoice page");
		testlog.pass("**Verifies the Registration successful**");
	}


	public void StoreIdHealthSafety(String SheetName, int rowNum) throws IOException, InterruptedException {
		if (CommonMethod.isElementsExist("HsrIframe", 5)) {
			CommonMethod.switchToFrame("HsrIframe");
			CommonMethod.WaitUntilPresence("HsrCloseCard", 60);
			CommonMethod.Robustclick("HsrCloseCard");
			CommonMethod.switchToParentFrame();
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.info("Given User is on Dashboard page");
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("StoreId", 0);
		String getId = CommonMethod.getText("StoreId");
		String[] stringArray = getId.split(": ");
		String getHsrId = stringArray[1].trim();
		data.setCellData(SheetName, "ProjectID", rowNum, getHsrId);
		testlog.info("HealthSafety Id:" + getHsrId);
		testlog.info("When User storing HsrId in excel");
		testlog.pass("**Stored the Registered id  in excel successfully**");
	}

	public void SearchHealthSafetyByID(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLHealthSafetyNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WELLHealthSafetyNavBar", 0);
		CommonMethod.RobustclickElementVisible("WELLHealthSafetyNavBar", "HsrIdSearch");
		testlog.info("When User clicks on WELL Healthsafety from top menu under Projects");
		CommonMethod.verifyDropdownValues("HSRTypeDropdown", "HsrType");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrIdSearch", 0);
		CommonMethod.RobustclickElementVisible("HsrIdSearch", "HsrIdSearch");
		testlog.info("HealthSafety ID:" + data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.sendKeys("HsrIdSearch", data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter data to HsrId");
		CommonMethod.RobustclickElementVisible("HsrapplySearch", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		int ProjectCount = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount), "1", "HealthSafety Search failed");
		CommonMethod.sendKeys("HsrNameList", data.getCellData(SheetName, "projectName", rowNum));
		testlog.info("And User enter data to HsrName");
		CommonMethod.RobustclickElementVisible("HsrapplySearch", "SearchResultIDVerify");
		int ProjectCount1 = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount1), "1", "HealthSafety Search failed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RatingIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");
		CommonMethod.RobustclickElementVisible("RatingIdClick", "WellV2DashboardTab");
		testlog.info("And User clicks on HsrAdminId");
		testlog.info("Then User verifies HsrId in search filter");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		
		testlog.info("And User will be redirected to Dashboard page");
		testlog.pass("**Verifies the Search HealthSafety ByID successfully**");
	}

	public void SearchFilterRegisteredStatus(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLHealthSafetyNavBar");
		CommonMethod.WaitUntilVisibility("WELLHealthSafetyNavBar", 300);
		CommonMethod.RobustclickElementVisible("WELLHealthSafetyNavBar", "HsrIdSearch");
		testlog.info("When User clicks on WELL Healthsafety from top menu under Projects");
		CommonMethod.WaitUntilVisibility("HsrIdSearch", 300);
		CommonMethod.RobustclickElementVisible("HsrIdSearch", "HsrIdSearch");
		testlog.info("HealthSafety ID:" + data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.sendKeys("HsrIdSearch", data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter data to HsrId");
		CommonMethod.RobustclickElementVisible("HsrapplySearch", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		int ProjectCount = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount), "1", "HealthSafety Search failed");
		CommonMethod.sendKeys("HsrNameList", data.getCellData(SheetName, "projectName", rowNum));
		CommonMethod.RobustclickElementVisible("HsrapplySearch", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		int ProjectCount1 = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount1), "1", "HealthSafety Search failed");
		CommonMethod.assertcontainsmessage("RatingIdClick", data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");
		String status = CommonMethod.getText("HsrWprStatusResultList");
		testlog.info("Status: " + status);
		CommonMethod.negativesoftassertFieldValid(status, "REGISTERED", "HealthSafety Search failed");
		testlog.info("Then User verifies HsrStatus in search filter");
		CommonMethod.RobustclickElementVisible("RatingIdClick", "WellV2DashboardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.info("And User will be redirected to Dashboard page");
		testlog.pass("**Verifies the Search filter status successfully**");
		
	}
	
	

	public void ScorecardfillHSRWPR(int YesEnd, int NoStart, int NoEnd, int DifferencePlusOne, String purseYes,
			String purseNo) throws IOException, InterruptedException {
		List<WebElement> YesButton;
		List<WebElement> NoButton;
		Boolean flag = false;

		NoButton = CommonMethod.findElements(purseNo);
		System.out.println("NoButton: "+NoButton);
		int j = DifferencePlusOne;
		for (int i = NoStart; i <= NoEnd; i++) {
			long startTime = System.currentTimeMillis();
			int RemainingNo = NoButton.size();
			do {
				if (!((System.currentTimeMillis() - startTime) < 60000)) {
					flag = true;
					System.out.println("Build Stuck");
					break;
				}
				CommonMethod.WaitUntilClickble(NoButton.get(RemainingNo - j), 30);
				CommonMethod.JavascriptClickElement(NoButton.get(RemainingNo - j));
				if (CommonMethod.isElementsExist("WPRCloseIcon", 5)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRCloseIcon", 1);
				}
				Thread.sleep(1000);
				NoButton = CommonMethod.findElements(purseNo);
			} while ((NoButton.size() == RemainingNo));
			RemainingNo--;
			j--;
			if (flag) {
				System.out.println("Build Exit");
				break;
			}
		}

		YesButton = CommonMethod.findElements(purseYes);
		for (int i = 1; i <= YesEnd; i++) {
			long startTime = System.currentTimeMillis();
			int RemainingYes = YesButton.size();
			do {
				if (!((System.currentTimeMillis() - startTime) < 60000)) {
					flag = true;
					System.out.println("Build Stuck");
					break;
				}
				CommonMethod.WaitUntilClickble(purseYes, 60);
				CommonMethod.JavascriptClickElement(purseYes);
				if (CommonMethod.isElementsExist("WPRCloseIcon", 5)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRCloseIcon", 1);
				}
				Thread.sleep(2000);
				YesButton = CommonMethod.findElements(purseYes);
			} while ((YesButton.size() == RemainingYes));
			RemainingYes--;
			if (flag) {
				System.out.println("Build Exit");
				break;
			}
		}
	}

	
	public void NavigateScorecard() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTab", 0);
		CommonMethod.RobustclickElementVisible("ScorecardTab", "ApplicableVersion");
		testlog.info("And User clicks on ScorecardTab");
		rc.ScorecardLoading();
	}

	public void CompleteScorecardHsrById(String SheetName, int rowNum) throws IOException, InterruptedException {
		
		if (!TestNGTestName.contains("NonEnhanced")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseMaybe", 0);
			CommonMethod.JavascriptClickElement("CommonScorecardPurseMaybe");
			rc.confirmPursing();
			CommonMethod.JavascriptClickElement("CommonScorecardPurseMaybe");
			rc.confirmPursing();
			ScorecardfillHSRWPR(15, 1, 27, 27, "CommonScorecardPurseYes", "CommonScorecardPurseNo");
		}
		else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseMaybe1", 0);
			CommonMethod.JavascriptClickElement("CommonScorecardPurseMaybe1");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrEhancePurseNo", 0);
			CommonMethod.Robustclick("HsrEhancePurseNo");
			ScorecardfillHSRWPR(15, 1, 31, 31, "CommonScorecardPurseYes", "CommonScorecardPurseNo");
		}
		testlog.info("And User clicks on 15 Yes button");
		testlog.info("And User clicks on 12 No button");
		testlog.info("Then User verifies Upload Document toast message");
		testlog.pass("**Verifies the 15 Purse Yes Scorecard HealthSafety in V2Project successfully**");
	}
	
	
	public void CommonBulkUploadScorecardDocument(int LastFeatureNumber, String SheetName, int rowNum, String Commodity,String FileName,Boolean PartNameRequired, Boolean VerificationMethodValidationRequired, Boolean NoteRequired,  Boolean IntentCheckboxRequired) throws Exception {
		testlog.info("Given User is on Scorecard page");
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
		List<WebElement> Feature;
		Feature = CommonMethod.findElements("RatingFeatureName");
		Feature = Feature.subList(0, LastFeatureNumber);
		CommonMethod.scrolldowntoElement("ApplicableVersion");
		for (WebElement f : Feature) {
			CommonMethod.WaitUntilClickble(f, 120);
			CommonMethod.JavascriptClickElement(f);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRVerficationTab", 0);
			CommonMethod.RobustclickElementVisible("WPRVerficationTab", "WPRAddOption");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOption", 0);
			CommonMethod.JavascriptClickElement("WPRAddOption");
			if (CommonMethod.isElementsExist("MultiplePart", 10)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAllAddOptionbtn", 0);
				CommonMethod.Robustclick("WPRAllAddOptionbtn");
			}
			else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionbtn", 0);
			CommonMethod.JavascriptClickElement("WPRAddOptionbtn");
			rc.OptionAddedToasterMessage();
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAddedOption", 0);
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionCloseIcon", 0);
			CommonMethod.Robustclick("WPRAddOptionCloseIcon");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRAddOptionCloseIcon", 1);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocbtn", 0);
			generic.assignLocationGeneric(Commodity, false, false, true, false, false);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UploadTaskDocument", 0);
			boolean flag = true;
		     	int i= 1;
				int RemainingNo = CommonMethod.ElementSize("UploadTaskDocument");
				long startTime = System.currentTimeMillis();
				do {
						if ((System.currentTimeMillis() - startTime) > 800000) {
							flag = false;
							break;
						}
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WprUploadTaskDocument", 0);
						CommonMethod.JavascriptClickElement("WprUploadTaskDocument");
						generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired, VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
				     	rc.ScorecardConfirmLocBulkUploadSaveButton();
				    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTable", 0);
						testlog.info("And User will be redirected to Document Upload Table page");
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
				     	Thread.sleep(2000);
				     	RemainingNo = CommonMethod.ElementSize("UploadTaskDocument");
				} while (i <= RemainingNo);
			CommonMethod.assertTruebooleanCondition(flag,"Uploading exceeds the timeout");
			CommonMethod.scrolldowntoElement("ApplicableVersion");
			CommonMethod.JavascriptClickElement(f);
		}
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
		testlog.pass("**Upload " +LastFeatureNumber+ "Scorecard Documents successfully**");
	}

	public void CommonSingleUploadScorecardDocument(int LastFeatureNumber, String SheetName, int rowNum, String Commodity,String FileName,Boolean PartNameRequired, Boolean VerificationMethodValidationRequired, Boolean NoteRequired,  Boolean IntentCheckboxRequired) throws Exception {
		testlog.info("Given User is on Scorecard page");
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPFeature", 1);
		List<WebElement> Feature;
		Feature = CommonMethod.findElements("V2ProjectWPRPFeature");
		Feature = Feature.subList(0, LastFeatureNumber);
		CommonMethod.scrolldowntoElement("ApplicableVersion");
		for (WebElement f : Feature) {
			CommonMethod.WaitUntilClickble(f, 120);
			CommonMethod.JavascriptClickElement(f);
			CommonMethod.WaitUntilVisibility("WPRVerficationTab", 60);
			CommonMethod.JavascriptClickElement("WPRVerficationTab");
			
			if (FileName.contains("Audit") || FileName.contains("Feature")) {
				 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOption", 0);
				CommonMethod.JavascriptClickElement("WPRAddOption");
				 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionbtn", 0);
				CommonMethod.Robustclick("WPRAddOptionbtn");
				testlog.info("And User clicks on save button");  
			} 	
			
			if (FileName.contains("Alternative")) {
            	  CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecrdAlterntives", 0);
					CommonMethod.JavascriptClickElement("ScorecrdAlterntives");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAlternativeAddBtn", 0);
					CommonMethod.JavascriptClickElement("ScorecardAlternativeAddBtn");
					testlog.info("And User clicks on save button");  
					}
			
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionCloseIcon", 0);
			CommonMethod.Robustclick("WPRAddOptionCloseIcon");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRAddOptionCloseIcon", 1);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocbtn", 0);
			generic.assignLocationGeneric(Commodity, false, false, true, false, false);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditLocationButton", 0);
			testlog.info("Then User verifies Edit location button");
			testlog.info("And User clicks on Assign button");
			testlog.info("And User checks the Assign Location checkbox");
			testlog.info("And User clicks on Save button");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRUploadDocTaskbtn", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRScorecardUploadTaskDocument", 0);
			List<WebElement> UploadButton;
			UploadButton = CommonMethod.findElements("WPRScorecardUploadTaskDocument");
			for (WebElement uploadButton : UploadButton) {
				CommonMethod.JavascriptClickElement(uploadButton);
				generic.uploadModalCompleteGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired, VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
			}
			CommonMethod.scrolldowntoElement("ApplicableVersion");
			CommonMethod.JavascriptClickElement(f);
		}
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
		testlog.pass("**Upload " +LastFeatureNumber+ "Scorecard Documents successfully**");
	}


	public void SubmitHsrReview(String SheetName, int rowNum, String ReviewName)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "HsrSubmitReview");
		testlog.info("When User clicks on Review tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrSubmitReview", 0);
		CommonMethod.RobustclickElementVisible("HsrSubmitReview", "ReviewModelSubmitButton");
		testlog.info("And User clicks on HsrSubmit DocReview button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewModelSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("ReviewModelSubmitButton", "HsrCommentReview");
		CommonMethod.negativesoftassertPageSource(
				"Please provide your comments below to notify the IWBI team is required.",
				"Comment Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Doc-submit-phase is required.", "Phase Name Error Mismatch");
		testlog.info(
				"And User clicks on SubmitDocReview button without entering the mandatory fields and verifies error meassage");
		CommonMethod.WaitUntilVisibility("HsrCommentReview", 60).sendKeys(ReviewName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrSelectedProjectPhaseReview", 0);
		if (SheetName.equalsIgnoreCase("Hsr") && (TestCaseName.contains("FinalSubmitReview") && (TestNGTestName.contains("Main")))) {
		CommonMethod.verifyDropdownValues("HsrSelectedProjectPhaseReview", "HsrFinalReviewPhase");
	    }
		if (SheetName.equalsIgnoreCase("Hsr") && (TestCaseName.contains("CurativeSubmitReview") && (TestNGTestName.contains("Main")))) {
		CommonMethod.verifyDropdownValues("HsrSelectedProjectPhaseReview", "HsrCurativePhase");
	    }
		if (SheetName.equalsIgnoreCase("Hsr") && (TestCaseName.contains("PreliminarySubmitReview") && (TestNGTestName.contains("Main")))) {
		CommonMethod.verifyDropdownValues("HsrSelectedProjectPhaseReview", "HsrReviewPhase");
	    }		
		CommonMethod.selectdropdownVisibletext("HsrSelectedProjectPhaseReview", ReviewName);
		testlog.info("And User select phase review");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewModelSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("ReviewModelSubmitButton", "ReviewViewButton");
		testlog.info("And User clicks on HsrSubmit DocReview button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
		testlog.info("Then User will be redirected to Review list page");
		testlog.pass("**Submitted Health-Safety Review successfully**");
	}
	
	public void validateSelectReviewComment(String SheetName, int rowNum,String ReviewName) throws IOException, InterruptedException {
		if(ReviewName.contains("Preliminary")) {
			if (TestNGTestName.contains("NonEnhanced")) {
				selectReviewComment(SheetName, rowNum, "Not Achieved", "NEPreliminaryCommentNotAchieved", ReviewName);
				selectReviewComment(SheetName, rowNum, "Achieved With Pending Features", "NEPreliminaryCommentAchievedWithPending", ReviewName);
				selectReviewComment(SheetName, rowNum, "Achieved", "NEPreliminaryCommentAchieved", ReviewName);
			}
			else {
			selectReviewComment(SheetName, rowNum, "Not Achieved", "PreliminaryCommentNotAchieved", ReviewName);
			selectReviewComment(SheetName, rowNum, "Achieved With Pending Features", "PreliminaryCommentAchievedWithPending", ReviewName);
			selectReviewComment(SheetName, rowNum, "Achieved", "PreliminaryCommentAchieved", ReviewName);
			}
			}
			if(ReviewName.contains("Final")) {
				selectReviewComment(SheetName, rowNum, "Not Achieved", "FinalCommentNotAchieved", ReviewName);
				selectReviewComment(SheetName, rowNum, "Achieved", "FinalCommentAchieved", ReviewName);
			}
			if(TestNGTestName.contains("HSR-Main") && ReviewName.contains("Curative")) {
				selectReviewComment(SheetName, rowNum, "Not Achieved", "CurativeCommentNotAchieved", ReviewName);
				selectReviewComment(SheetName, rowNum, "Achieved", "CurativeCommentAchieved", ReviewName);
			}
				if (TestNGTestName.contains("NonEnhanced") && ReviewName.contains("Curative")) {
					selectReviewComment(SheetName, rowNum, "Not Achieved", "NECurativeCommentNotAchieved", ReviewName);
					selectReviewComment(SheetName, rowNum, "Achieved", "NECurativeCommentAchieved", ReviewName);
				}
		}
		
		public void selectReviewComment(String SheetName, int rowNum, String Status, String CommentMessage, String ReviewType) throws IOException, InterruptedException {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommentSuggestions", 0);
			CommonMethod.selectdropdownVisibletext("CommentSuggestions", Status);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewCommentNarrow", 0);
			String actualText = CommonMethod.getattributeValueByTextContent("ReviewCommentNarrow");
			String expectText = data.getCellData(SheetName, CommentMessage, rowNum);
			 CommonMethod.negativesoftassertFieldValid(actualText,expectText ,ReviewType+" " +Status
						+" Review Comment doesn't match");
		}
		
		public void valideCompletedReviewComment(String SheetName, int rowNum,String ReviewName) throws IOException, InterruptedException {
		if(ReviewName.contains("Preliminary")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewerComments", 0);
			String Textbox1 = CommonMethod.getattributeValueByTextContent("ReviewerComments");
			CommonMethod.negativesoftassertFieldValid(Textbox1, data.getCellData(SheetName, "PreliminaryCommentAchieved", rowNum),"Reviewer Comments doesn't match");
		    CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ReviewerDocuments").replaceAll("\\s+", " ").trim(), "Favicon","Reviewer Documents doesn't match");
		    CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ReviewedOn").replaceAll("\\s+", " ").trim(), CommonMethod.ValidateDate(),"ReviewedOn doesn't match");
			}
			if(ReviewName.contains("Final")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FinalReviewerComments", 0);
				String Textbox1 = CommonMethod.getattributeValueByTextContent("FinalReviewerComments");
				CommonMethod.negativesoftassertFieldValid(Textbox1, data.getCellData(SheetName, "FinalCommentAchieved", rowNum),"Reviewer Comments doesn't match");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("FinalReviewerDocuments").replaceAll("\\s+", " ").trim(), "Favicon" ,"Reviewer Documents doesn't match");
			    CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ReviewedOn").replaceAll("\\s+", " ").trim(), CommonMethod.ValidateDate(),"Reviewed On date doesn't match");
			}
		}
	

	public void CompleteHsrReview(String SheetName, int rowNum, String ReviewName)
			throws IOException, InterruptedException {
		/*
		 * Admin Review
		 */
			AdminHsrSearch(SheetName, rowNum);
			CompleteReturnReview(SheetName, rowNum, ReviewName);
	}
	
	public void CompleteReturnReview(String SheetName, int rowNum, String ReviewName)
			throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
	CommonMethod.RobustclickElementVisible("ReviewTab", "ReviewViewButton");
	CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("HsrSubmitReview");
	 CommonMethod.assertisElementPresentFalse("HsrSubmitReview","HsrSubmitReview is visible");
	testlog.info("And User clicks on Review tab");
	if(TestNGTestName.contains("Renewal") && TestCaseName.equalsIgnoreCase("Performance_TC_09_06_CompletePreliminaryReview")) 
		if(!TestNGTestName.contains("MulipleNoLocation")) {
	performance.ClickAndValidateViewInvoices();	
	}
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
	if (TestNGTestName.contains("NonEnhanced")) {
		CommonMethod.RobustclickElementVisible("ReviewViewButton", "ReviewReturnButton");
	}
	else {
		CommonMethod.RobustclickElementVisible("ReviewViewButton", "ReviewAdminActionsButton");
		v2project.ReviewAdminActionsButton();
	}
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewReturnButton", 0);
	CommonMethod.RobustclickElementVisible("ReviewReturnButton", "ReviewReturnSubmit");
	testlog.info("And User clicks on Return button");
	testlog.info("And User Upload Review Document");
	if (ReviewName.contains("Curative")) {
		if(TestNGTestName.contains("HSR")) {
		validateSelectReviewComment(SheetName, rowNum, ReviewName);
	}
	else {
		CommonMethod.WaitUntilClickble("ReturnComment", 60).sendKeys(ReviewName);
		testlog.info("And User enter data to Comment field");
	}
	}
	else {
		validateSelectReviewComment(SheetName, rowNum, ReviewName);
	}
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReturnReviewDate", 0);
	CommonMethod.RobustclickElementVisible("ReturnReviewDate", "DatePickerOkButton");
	CommonMethod.Robustclick("DatePickerOkButton");
	testlog.info("And User select Review Date");
	CommonMethod.scrollDown();
	Thread.sleep(1000);
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditAchievedStatus", 0);
	CommonMethod.ClickCheckbox("EditAchievedStatus");
	testlog.info("And User checks the AchievedStatus checkbox");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AwardedDate", 0);
	CommonMethod.RobustclickElementVisible("AwardedDate","DatePickerOkButton");
	CommonMethod.Robustclick("DatePickerOkButton");
	CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DatePickerOkButton", 1);
	testlog.info("And User select Awarded Date");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewPaymentstatusLabel", 0);
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ReviewPaymentstatusLabel"), "Paid", "Review Paymentstatus Label doesn't match");
	testlog.info("And User verifies Paymentstatus label");
	CommonMethod.uploadMultipleFile("DocumentsUpload", SampleJpgfile, ImportReviewUpload, "MultipeUploadDeleteicon", 2,
			"MultipeUploadEnableButtonDeleteLink");
	CommonMethod.Robustclick("ReviewReturnSubmit");
	CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ReviewReturnSubmit", 1);
	testlog.info("And User clicks on Submit button");
	Thread.sleep(2000);
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewedStatus", 0);
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ReviewedStatus"), "REVIEWED", "Verified Review status");
	testlog.info("Then User verifies Reviewed Status");
	valideCompletedReviewComment(SheetName, rowNum, ReviewName);
	testlog.pass("**Completed Return Review successfully**");
	}
	
	public void ReviewEditButtonNE() throws Exception {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewEditButton", 0);
	CommonMethod.RobustclickElementVisible("WPRReviewEditButton", "EditAchievedStatus");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditAchievedStatus", 0);
	CommonMethod.ClickCheckbox("EditAchievedStatus");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateButton", 0);
	CommonMethod.Robustclick("UpdateButton");
	CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("UpdateButton", 1);
	}
	
	public void HsrProjectFieldValidationTest(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "HsrWprOrganizationInformation");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprOrganizationInformation", 0);
		CommonMethod.RobustclickElementVisible("HsrWprOrganizationInformation", "HsrWprEditProjectName");
		testlog.info("And User clicks on OrganizationInformation tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprEditProjectName", 0);
		testlog.info("HsrName: " + data.getCellData(SheetName, "projectName", rowNum));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("HsrWprEditProjectName"),
				data.getCellData(SheetName, "projectName", rowNum), "Project Name doesn't match");
		testlog.info("Area: " + data.getCellData(SheetName, "Area", rowNum));
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValue("HsrWprEditArea").replace("sq ft", "").replace(",", "").trim(),
				data.getCellData(SheetName, "Area", rowNum), "Area doesn't match");
		testlog.info("Location: " + data.getCellData(SheetName, "Location", rowNum));
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("HsrWprEditLocation"),
				data.getCellData(SheetName, "Location", rowNum), "Location count doesn't match");
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
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("HsrWprEditState"),
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
		testlog.pass("**Verifies the Hsr Field Validation successfully**");
	}

	public void validateTeamsHSR(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLHealthSafetyNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WELLHealthSafetyNavBar", 0);
		CommonMethod.RobustclickElementVisible("WELLHealthSafetyNavBar", "HsrIdSearch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrIdSearch", 0);
		CommonMethod.RobustclickElementVisible("HsrIdSearch", "HsrapplySearch");
		testlog.info("HealthSafety ID:" + data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.sendKeys("HsrIdSearch", data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.RobustclickElementVisible("HsrapplySearch", "SearchResultIDVerify");
		int var = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(var), "1", "HealthSafety Search failed");
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
	}

	public void verifyScoreCardFilterRating(String Commodity, String optionXpath,String optionCheckboxXpath, String rowCountXpath,String expectedResult, String ScorecardCondition)
			throws Exception {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "V2ProjectScorecardApplybutton");
		testlog.info("When User clicks on Filter button");
		generic.filterGeneric(Commodity,optionXpath,optionCheckboxXpath, rowCountXpath,expectedResult,ScorecardCondition);
	}

	public void SearchHealthSafetyByStatus(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on HealthSafety Project List");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLHealthSafetyNavBar");
		testlog.info("When User clicks on ProjectNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WELLHealthSafetyNavBar", 0);
		CommonMethod.RobustclickElementVisible("WELLHealthSafetyNavBar", "HsrIdSearch");
		testlog.info("And User clicks on WELLHealthSafetyNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrIdSearch", 0);
		CommonMethod.RobustclickElementVisible("HsrIdSearch", "HsrIdSearch");
		testlog.info("HealthSafety ID:" + data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrIdSearch", 0);
		CommonMethod.clearAndSendKey("HsrIdSearch", data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enters to HsrIdSearch field");
		CommonMethod.RobustclickElementVisible("HsrapplySearch", "SearchResultIDVerify");
		testlog.info("And User clicks on apply button");
		String status = CommonMethod.getText("HsrWprStatusResultList");
		testlog.info("Status: " + status);
		CommonMethod.negativesoftassertFieldValid(status, "ACHIEVED", "HealthSafety Search failed");
		int ProjectCount = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount), "1", "HealthSafety Search failed");
		rc.ValidateDateInList();
		testlog.info("Then User verifies Project Status");
		testlog.pass("**Verifies search filter status successfully**");
	}

	public void searchFilterDocumentHSR(String documentName, String filterOption, String fileCount)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		if (filterOption.equalsIgnoreCase("Audit")) {
			CommonMethod.RobustclickElementVisible("HsrAuditLinkTab", "V2ProjectDecumentSearchBox");
			testlog.info("And User clicks on AuditLinkTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDecumentSearchBox", 0);
		}
		if (filterOption.equalsIgnoreCase("General")) {
			CommonMethod.RobustclickElementVisible("HsrGeneralLink", "V2ProjectDecumentSearchBox");
			testlog.info("And User clicks on GeneralLinkTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDecumentSearchBox", 0);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDecumentSearchBox", 0);
		CommonMethod.clearAndSendKey("V2ProjectDecumentSearchBox", documentName);
		testlog.info("And User enter data to SearchBox field");
		CommonMethod.RobustclickElementVisible("PortfolioCreateAccountSubmit", "V2ProjectDecumentSearchBox");
		testlog.info("And User clicks on Search Button");
		if (filterOption.equalsIgnoreCase("General")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRDocumentGeneralFileCount", 0);
			int V2ProjectScoreDocCount = CommonMethod.ElementSize("HSRDocumentGeneralFileCount");
			String V2ProjectDocCounts = Integer.toString(V2ProjectScoreDocCount);
			CommonMethod.negativesoftassertFieldValid(V2ProjectDocCounts, fileCount,"Document General FileCount doesn't match");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PagnitionResultCount", 0);
			int V2ProjectScoreDocCount = CommonMethod.ElementSize("PagnitionResultCount");
			String V2ProjectDocCounts = Integer.toString(V2ProjectScoreDocCount);
			CommonMethod.negativesoftassertFieldValid(V2ProjectDocCounts, fileCount,"Document General FileCount doesn't match");
		}
		testlog.info("Then User verifies Document List");
		testlog.pass("**Verifies search filter successfully **");
	}

	public void ValidDashboardHsrField() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.info("Then User verifies SideBar Navigation tab");
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
		testlog.pass("**Verifies Dashboard fields and SideBar Navigation tab successfully **");
	}

	public void UpdateReviewHSR(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL Admin certified account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLHealthsafetyNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminWELLHealthsafetyNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminWELLHealthsafetyNavBar", "SearchByID");
		CommonMethod.WaitUntilClickble("SearchByID", 60)
				.sendKeys(data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.RobustclickElementVisible("ApplyButton", "HsrAdminIdClick");
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("HsrAdminIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");
		CommonMethod.RobustclickElementVisible("HsrAdminIdClick", "WellV2DashboardTab");
		CommonMethod.WaitUntilVisibility("WellV2DashboardTab", 300);
		CommonMethod.RobustclickElementVisible("ReviewTab", "ReviewViewButton");
		CommonMethod.RobustclickElementVisible("V2ProjectReviewViewLink", "V2ProjectReviewEditButton");
		CommonMethod.RobustclickElementVisible("V2ProjectReviewEditButton",
				"V2ProjectReviewNeedClarificationRadioButton");
		CommonMethod.ClickCheckbox("V2ProjectReviewNeedClarificationRadioButton");
		CommonMethod.sendKeys("V2ProjectReviewMidReviewClarificationNote", "Test");
		CommonMethod.RobustclickElementVisible("V2ProjectReviewUpdate", "V2ProjectReviewRequiredClarification");
	}

	public void ReSubmitReviewHSR(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectReviewViewLink");
		CommonMethod.RobustclickElementVisible("V2ProjectReviewViewLink", "V2ProjectReviewReSubmitButton");
		CommonMethod.RobustclickElementVisible("V2ProjectReviewReSubmitButton", "V2ProjectReviewCommentTextArea");
		CommonMethod.sendKeys("V2ProjectReviewCommentTextArea", "Test");
		CommonMethod.Robustclick("SubmitButton");
	}

	public void AdminHsrSearch(String SheetName, int rowNum) throws IOException, InterruptedException {
		/*
		 * Admin Review
		 */
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLHealthsafetyNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminWELLHealthsafetyNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminWELLHealthsafetyNavBar", "SearchByID");
		testlog.info("When User clicks on WELL HealthSafety from top menu under Projects");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchByID", 0);
		CommonMethod.RobustclickElementVisible("SearchByID", "ApplyButton");
		CommonMethod.sendKeys("SearchByID", data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter data to HsrId");
		CommonMethod.RobustclickElementVisible("ApplyButton", "HsrAdminIdClick");
		testlog.info("And User clicks on Apply button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("HsrAdminIdClick"), data.getCellData(SheetName, "ProjectID", rowNum),
				"Project name doesn't matches in search");
		CommonMethod.RobustclickElementVisible("HsrAdminIdClick", "ReviewTab");
		testlog.info("And User clicks on HsrAdminId");
	}

	public void VerifyReviewErrorMessageByMinScorecardPurseYes() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTab", 0);
		CommonMethod.RobustclickElementVisible("ScorecardTab", "ApplicableVersion");
		testlog.info("And User clicks on ScorecardTab");
		rc.ScorecardLoading();
		if (!TestNGTestName.contains("NonEnhanced")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseYes", 0);
		CommonMethod.click("CommonScorecardPurseYes1");
		if (CommonMethod.isElementsExist("WPRCloseIcon", 10)) {
			CommonMethod.WaitUntilVisibility("WPRCloseIcon", 120);
			CommonMethod.WaitUntilInVisibility("WPRCloseIcon", 60);
		}
		CommonMethod.click("CommonScorecardPurseYes1");
		if (CommonMethod.isElementsExist("WPRCloseIcon", 20)) {
			CommonMethod.WaitUntilVisibility("WPRCloseIcon", 120);
			CommonMethod.WaitUntilInVisibility("WPRCloseIcon", 60);
		}
		}
		else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseYes", 0);
			CommonMethod.click("CommonScorecardPurseYes");
		}
		// Verify Review ErrorMessage ByMinScorecardPurseYes
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "HsrSubmitReview");
		testlog.info("When User clicks on Review button");
		CommonMethod.WaitUntilPresence("HsrSubmitReview", 180);
		CommonMethod.RobustclickElementVisible("HsrSubmitReview", "ValidReviewErrorMessage1");
		testlog.info("And User clicks on HsrSubmit DocReview button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidReviewErrorMessage1", 0);
		if (TestNGTestName.contains("NonEnhanced")) {
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ValidReviewErrorMessage1"),
				"Oops! It looks like your scorecard is incomplete.", "Review Mark Error message doesn't match");
		}
		else {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ValidReviewErrorMessage1"),
					"The minimum point requirement is not met, please ensure", "Review Mark Error message doesn't match");
		}
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrSubmitReview", 0);
		testlog.info("And User verifies Review ErrorMessage By Minimum selecting ScorecardPurseYes");
	}

	public void RegisterHealthSafetySF(String SheetName, int rowNum, String Country, String ProjectName) throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL certified account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLHealthSafetyNavBar");
		CommonMethod.RobustclickElementVisible("WELLHealthSafetyNavBar", "HsrWellhealthstartprojectbtn");
		testlog.info("When User clicks on WELL HealthSafety from top menu under Projects");
		CommonMethod.RobustclickElementVisible("HsrWellhealthstartprojectbtn", "HsrEnrollnowbtn");
		testlog.info("And User clicks on Start project button");
		CommonMethod.RobustclickElementVisible("HsrEnrollnowbtn", "HsrEnrollbtn");
		testlog.info("And User clicks on EnrollNow button");
		CommonMethod.RobustclickElementVisible("HsrEnrollbtn", "Hsrenrollcontinuebtn");
		testlog.info("And User clicks on Enroll button");
		String erollName = ProjectName +CommonMethod.randomNumber(8000000);
		CommonMethod.sendKeys("HsrenrollName", erollName);
		data.setCellData(SheetName, "projectName", rowNum, CommonMethod.getattributeValue("HsrenrollName"));
		testlog.info("And User enter data to EnrollName");
		testlog.info("HsrName: " + data.getCellData(SheetName, "projectName", rowNum));
		CommonMethod.ClickCheckbox("Hsrenrollcheckbox");
		testlog.info("And User checks the enroll checkbox");
		String Ownername = USfaker.address().firstName();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPROwnerName", 0);
		CommonMethod.sendKeys("WPROwnerName", Ownername);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("WPROwnerName"));
		rc.SelectOwnerOrg(SheetName, rowNum);
		data.setCellData(SheetName, "OrgName", rowNum, CommonMethod.getText("OrgName"));
		CommonMethod.selectdropdownrandom("OrgIndustry");
		testlog.info("And User select OrgIndustry");
		data.setCellData(SheetName, "OrgIndustry", rowNum, CommonMethod.getSelectedDropdownValue("OrgIndustry"));
		CommonMethod.selectdropdownValue("CountryDropdown", Country);
		rc.SelectEnterpriseProviders(SheetName, rowNum);
		data.setCellData(SheetName, "Country", rowNum, CommonMethod.getSelectedDropdownAttribute("CountryDropdown"));
		CommonMethod.selectdropdownVisibletext("ProjectState","Gansu");
		testlog.info("And User enter data to Owner Country, State, Street address, City and Postal Code fields");
		testlog.info("Country: " + CommonMethod.getSelectedDropdownAttribute("CountryDropdown"));
		testlog.info("OwnerName: " + data.getCellData(SheetName, "OwnerName", rowNum));
		testlog.info("OrganizationName: " + data.getCellData(SheetName, "OrgName", rowNum));
		testlog.info("State: " + CommonMethod.getSelectedDropdownAttribute("ProjectState"));
		data.setCellData(SheetName, "State", rowNum, CommonMethod.getSelectedDropdownValue("ProjectState"));
		String ProjectAddress = USfaker.address().streetAddress();
		String ProjectCity = USfaker.address().cityName();
		String PostalCode = USfaker.address().zipCode();
		CommonMethod.sendKeys("StreetWithId", ProjectAddress);
		data.setCellData(SheetName, "Street", rowNum, CommonMethod.getattributeValue("StreetWithId"));
		testlog.info("Hsrenrollstreet: " + data.getCellData(SheetName, "Street", rowNum));
		CommonMethod.sendKeys("CityWithId", ProjectCity);
		data.setCellData(SheetName, "City", rowNum, CommonMethod.getattributeValue("CityWithId"));
		testlog.info("City: " + data.getCellData(SheetName, "City", rowNum));
		CommonMethod.sendKeys("PostalWithId", PostalCode);
		data.setCellData(SheetName, "PostalCode", rowNum, CommonMethod.getattributeValue("PostalWithId"));
		testlog.info("PostalCode: " + data.getCellData(SheetName, "PostalCode", rowNum));
		CommonMethod.ClickCheckbox("Hsrbilladdcheckbox");
		testlog.info("And User checks the Billing checkbox");
		CommonMethod.RobustclickElementVisible("Hsrenrollcontinuebtn", "ChinaProjectUpdate");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ChinaProjectUpdate",1);
		CommonMethod.negativeAssertElementPresentTrue("ChinaProjectUpdate","China Project Update is not present");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Hsrregcheckbox", 0);
		CommonMethod.Robustclick("Closepanelicon");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("Closepanelicon", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectState", 0);
		CommonMethod.selectdropdownVisibletext("ProjectState","Macao");
		CommonMethod.RobustclickElementVisible("Hsrenrollcontinuebtn", "HsrRegcontinuebtn");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Hsrregcheckbox", 0);
		CommonMethod.ClickCheckbox("Hsrregcheckbox");
		testlog.info("And User checks the terms and conditions checkbox");
		CommonMethod.selectdropdownVisibletext("HsrIwbimemberdropdown", "No");
		testlog.info("And User select Iwbi member");
		CommonMethod.RobustclickElementVisible("HsrRegcontinuebtn", "HsrEnrollButton");
		testlog.info("And User clicks on continue button");
		Thread.sleep(20000);
		String projectId = CommonMethod.getCurrentUrl();
		System.out.println("projectId: " + projectId);
		String[] getHsrId = projectId.split("/");
		System.out.println("Id: " + getHsrId[6]);
		data.setCellData(SheetName, "ProjectID", rowNum, getHsrId[6]);
		testlog.pass("**Verifies the Registration successful**");
	}

	public void EnrollHealthSafetySF(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL certified account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Hsrlocations", 0);
		CommonMethod.sendKeys("Hsrlocations", "10");
		data.setCellData(SheetName, "Location", rowNum, CommonMethod.getattributeValue("Hsrlocations"));
		testlog.info("And User enter data to location");
		testlog.info("Hsrlocations: " + data.getCellData(SheetName, "Location", rowNum));
		CommonMethod.RobustclickElementVisible("Multiselect", "HsrWPRlocationsSpaceOption");
		Thread.sleep(2000);
		CommonMethod.RobustclickElementVisible("HsrWPRlocationsSpaceOption", "Hsrlocationsize");
		data.setCellData(SheetName, "SpaceTypes", rowNum, CommonMethod.getText("Multiselect"));
		testlog.info("And User select the space type");
		testlog.info("Location SpaceType: " + data.getCellData(SheetName, "SpaceTypes", rowNum));
		String Area = CommonMethod.randomNumberBetweenRanges(100, 50000);
		CommonMethod.clearAndSendKey("Hsrlocationsize", Area);
		data.setCellData(SheetName, "Area", rowNum, CommonMethod.getattributeValue("Hsrlocationsize"));
		testlog.info("Hsrlocations: " + data.getCellData(SheetName, "Area", rowNum));
		testlog.info("And User enter the Location Size");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrLocContinuebutton", 0);
		CommonMethod.RobustclickElementVisible("HsrLocContinuebutton", "HsrDiscountBackButton");
		testlog.info("And User clicks on continue button");
		testlog.pass("**Verifies the Enroll successful**");
	}

	public void AgreementHealthSafetySF(String SheetName, int rowNum) throws IOException, InterruptedException {

		if (CommonMethod.isElementsExist("HsrWPRYesMyOrganizationCbx", 10)) {
			CommonMethod.RobustclickElementVisible("HsrProgramFeeContinuebtn", "HsrWPRYesMyOrganizationCbx");
			CommonMethod.negativesoftassertPageSource(
					"Yes, my organization meets the criteria of the listed discount category.* is required.",
					"My Organization CheckBox Error Name");
			CommonMethod.WaitUntilClickble("HsrWPRYesMyOrganizationCbx", 60);
			CommonMethod.ClickCheckbox("HsrWPRYesMyOrganizationCbx");
			testlog.info("And User checks the MyOrganization checkbox");
		}
		CommonMethod.RobustclickElementVisible("HsrProgramFeeContinuebtn", "HsrProgramFeePublicrbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrProgramFeePublicrbtn", 0);
		CommonMethod.scrolldowntoElement("HsrProgramFeePublicrbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrProgramFeePublicrbtn", 0);
		CommonMethod.ClickCheckbox("HsrProgramFeePublicrbtn");
		testlog.info("And User check on Program Fee Public checkboxes");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrAcknowledecbx", 0);
		CommonMethod.ClickCheckbox("HsrAcknowledecbx");
		testlog.info("And User check on Acknowlede checkboxes");
		CommonMethod.RobustclickElementVisible("HsrReviewbtn", "BillingLanding");
		testlog.info("And User clicks on Review button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.info("Then User will be redirected to Billing invoice page");
		testlog.pass("**Verifies the Agreement successful**");
	}

	public void hsrSearchFilterRegisteredStatus(String SheetName, int rowNum, String Status)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLHealthSafetyNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WELLHealthSafetyNavBar", 0);
		CommonMethod.RobustclickElementVisible("WELLHealthSafetyNavBar", "HsrIdSearch");
		testlog.info("When User clicks on WELL Healthsafety from top menu under Projects");
		CommonMethod.WaitUntilVisibility("HsrIdSearch", 300);
		CommonMethod.RobustclickElementVisible("HsrIdSearch", "HsrIdSearch");
		testlog.info("HealthSafety ID:" + data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.sendKeys("HsrIdSearch", data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter data to HsrId");
		CommonMethod.RobustclickElementVisible("HsrapplySearch", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingIdClick", 0);
		int ProjectCount = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount), "1", "HealthSafety Search failed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RatingIdClick"),
				data.getCellData(SheetName, "ProjectID", rowNum), "Project Id doesn't matches in search");
		String status = CommonMethod.getText("HsrWprStatusResultList");
		testlog.info("Status: " + status);
		CommonMethod.negativesoftassertFieldValid(status, Status, "HealthSafety Search failed");
		testlog.info("Then User verifies HsrStatus in search filter");
		CommonMethod.Robustclick("RatingIdClick", "HsrapplySearch");
		testlog.info("And User will be redirected to Dashboard page");
		testlog.pass("**Verifies the Search filter status successfully**");
	}
	
	public void OrganizationInformationPrivateToPublic() throws Exception {
		testlog.info("Given User is on Dashboard page");
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "HSROrgInfoButton");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSROrgInfoButton", 0);
		CommonMethod.RobustclickElementVisible("HSROrgInfoButton", "HsrProgramFeePublicrbtn");
			testlog.info("And User clicks on Organization Information Button");
			 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrProgramFeePublicrbtn", 0);
				CommonMethod.ClickCheckbox("HsrProgramFeePublicrbtn");
				 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectSaveChangesButton", "WellV2DashboardTab");
				 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		}
	
	@SuppressWarnings("static-access")
	public void LocationProjectStatusValidation(String Status)throws IOException, InterruptedException {
		testlog.info("Given User is on Location page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
	List<String> val = CommonMethod.fetchTableData("table");
	if(Status.equalsIgnoreCase("NO")) {
	 pfu.ValidTableDataUpdateLocation(val, "WELL at scale Test location 01", 4, Status);
		pfu.ValidTableDataUpdateLocation(val, "WELL at scale Test location 02", 16, Status);
		pfu.ValidTableDataUpdateLocation(val, "WELL at scale Test location 03", 28, Status);
		pfu.ValidTableDataUpdateLocation(val, "WELL at scale Test location 04", 40, Status);
		pfu.ValidTableDataUpdateLocation(val, "WELL at scale Test location 05", 52, Status);
		testlog.info("Then User verifies Public Project Status");
		testlog.pass("**Verifies the Public Project Status successful**");
		}
	else {
		    pfu.ValidTableDataUpdateLocation(val, "WELL at scale Test location 01", 4, Status);
			pfu.ValidTableDataUpdateLocation(val, "WELL at scale Test location 02", 17, Status);
			pfu.ValidTableDataUpdateLocation(val, "WELL at scale Test location 03", 30, Status);
			pfu.ValidTableDataUpdateLocation(val, "WELL at scale Test location 04", 43, Status);
			pfu.ValidTableDataUpdateLocation(val, "WELL at scale Test location 05", 56, Status);
			testlog.info("Then User verifies Converted Public Project Status");
			testlog.pass("**Verifies the Converted Public Project Status successful**");
	}
}
	
	public void UpdateLocationPrivateToPublic()throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "PortfolioLocationLanding");
		testlog.info("When User clicks on LocationsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditLocation", 0);
		List<WebElement> EditLoc;
		EditLoc = CommonMethod.findElements("EditLocation");
		for (WebElement f : EditLoc) {
			CommonMethod.JavascriptClickElement(f);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditLocationPublic", 0);
			CommonMethod.ClickCheckbox("EditLocationPublic");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitButton", 0);
			CommonMethod.RobustclickElementVisible("SubmitButton", "PagnitionResultCount");
	}
	}
	public void ValidationSealAndDate()throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
	CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "SealImgValidate");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SealImgValidate", 0);
	CommonMethod.assertisElementPresentTrue("SealImgValidate", "Seal Image is not visble");
	testlog.info("Then User verifies seal from and to date");
	String SealDate =CommonMethod.getText("SealDateValidate");
	String[] Date = SealDate.split("to");
	String fromDate = Date[0].split("from")[1].trim();
	String toDate =Date[1].trim();
	CommonMethod.negativesoftassertFieldValid(fromDate,CommonMethod.ValidateDate(),"Seal Current Year Date Mismatch");
	CommonMethod.negativesoftassertFieldValid(toDate,CommonMethod.ValidateDateYear(),"Seal Next Year Date Mismatch");
	testlog.pass("**Verifies the Seal Date successful**");
	}
	
	public void uploadDocumentInFeatureNonEnhance(int LastFeatureNumber) throws IOException, InterruptedException {
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
		Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		Feature = Feature.subList(0, LastFeatureNumber);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplicableVersion", 0);
		CommonMethod.scrolldowntoElement("ApplicableVersion");
		for (WebElement f : Feature) {
			CommonMethod.JavascriptClickElement(f);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPDocIcon", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectWPRPDocIcon", "HsrSelectTypeDoc");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrSelectTypeDoc", 0);
			CommonMethod.selectdropdownIndex("HsrSelectTypeDoc", 1);
			if (CommonMethod.isElementsExist("HsrLocationrtn", 3)) {
				CommonMethod.WaitUntilClickble("HsrLocationrtn", 10);
				CommonMethod.ClickCheckbox("HsrLocationrtn");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRVerificationMethod", 0);
			CommonMethod.selectdropdownIndex("V2ProjectWPRVerificationMethod", 1);
			CommonMethod.scrolldowntoElement("V2ProjectWPRVerificationMethod");
			if (CommonMethod.isElementsExist("HsrSelectLoc", 3)) {
				CommonMethod.WaitUntilClickble("HsrSelectLoc", 10);
				CommonMethod.selectdropdownIndex("HsrSelectLoc", 1);
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUpload", 0);
			CommonMethod.uploadFile("V2ProjectDocUpload", FeaturefileUpload,"UploadFileVerifyScorecard");
			Thread.sleep(2000);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrUploadDocFeature", 0);
			CommonMethod.Robustclick("HsrUploadDocFeature");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecrdEhanceDeleteIcon", 0);
			CommonMethod.scrolldowntoElement("ApplicableVersion");
			CommonMethod.JavascriptClickElement(f);
		}
	}
	
	public void validateGeneralUploadDocumentNonEnhance() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrNonEhAddDoc", 0);
		CommonMethod.Robustclick("HsrNonEhAddDoc");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrDocType", 0);
		CommonMethod.RobustclickElementVisible("HsrDocumentUploadbtn", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("DocumentType is required.", "Acount Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Attach Document(s): is required.", "Attach Document Error Mismatch");
		CommonMethod.selectdropdownValue("HsrDocType", "general");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrType", 0);
		CommonMethod.selectdropdownValue("HsrType", "Project overview");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocUpload", 0);
		CommonMethod.uploadFile("WPRDocUpload", GeneralfileUpload,"UploadFileVerifyScorecard");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrReasonnarration", 0);
		CommonMethod.sendKeys("HsrReasonnarration", "Submitting Document");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrDocumentUploadbtn", 0);
		CommonMethod.RobustclickElementVisible("HsrDocumentUploadbtn", "PortfolioV2ProjectGeneralDoc");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioV2ProjectGeneralDoc", 0);
		CommonMethod.RobustclickElementVisible("PortfolioV2ProjectGeneralDoc", "HsrGeneralTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrGeneralTable", 0);
		CommonMethod.scrolldowntoElement("PortfolioV2ProjectGeneralDoc");
		List<String> val = CommonMethod.fetchTableData("HsrGeneralTable");
		CommonMethod.negativesoftassertFieldValid(val.get(0), "PROJECT OVERVIEW", "Document table data mismatch");
		testlog.pass("**Upload General Document successfully**");
	}

	public void validateLegalUploadDocumentNonEnhance() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrNonEhAddDoc", 0);
		CommonMethod.Robustclick("HsrNonEhAddDoc");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrDocType", 0);
		CommonMethod.selectdropdownValue("HsrDocType", "legal");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrType", 0);
		CommonMethod.selectdropdownValue("HsrType", "Signed certification agreement");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocUpload", 0);
		CommonMethod.uploadFile("WPRDocUpload", LegalfileUpload,"UploadFileVerifyScorecard");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrReasonnarration", 0);
		CommonMethod.sendKeys("HsrReasonnarration", "Submitting Document");
		CommonMethod.RobustclickElementVisible("HsrDocumentUploadbtn", "PortfolioV2ProjectGeneralDoc");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioV2ProjectGeneralDoc", 0);
		CommonMethod.RobustclickElementVisible("PortfolioV2ProjectGeneralDoc", "HsrGeneralTable");
		CommonMethod.scrolldowntoElement("PortfolioV2ProjectGeneralDoc");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrGeneralTable", 0);
		List<String> val = CommonMethod.fetchTableData("HsrGeneralTable");
		CommonMethod.negativesoftassertFieldValid(val.get(0), "SIGNED CERTIFICATION AGREEMENT",
				"Document table data mismatch");
		testlog.pass("**Upload Legal Document successfully**");
	}

	public void validateAuditUploadDocumentNonEnhance() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrNonEhAddDoc", 0);
		CommonMethod.Robustclick("HsrNonEhAddDoc");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrDocType", 0);
		CommonMethod.selectdropdownValue("HsrDocType", "audit");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrLocationRadiobutton", 0);
		CommonMethod.ClickCheckbox("HsrLocationRadiobutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrVerificationMethod", 0);
		CommonMethod.selectdropdownVisibletext("HsrVerificationMethod", "Technical Document (Audited)");
		CommonMethod.scrolldowntoElement("HsrVerificationMethod");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect1", 0);
		CommonMethod.RobustclickElementVisible("Multiselect1", "SelectOwnerOrg");
		CommonMethod.WaitUntilClickble("OwnerOrgClick", 10);
		CommonMethod.RobustclickElementVisible("OwnerOrgClick", "OwnerOrg");
		CommonMethod.sendKeys("OwnerOrg", "I");
		CommonMethod.WaitUntilClickble("SelectOwnerOrgDyn", 10);
		CommonMethod.SelectRandomfromList("SelectOwnerOrgDyn", 1, 2).click();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrLocationVerificationDoc", 0);
		CommonMethod.selectdropdownrandom("HsrLocationVerificationDoc");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocUpload", 0);
		CommonMethod.uploadFile("WPRDocUpload", AuditfileUpload, "UploadFileVerifyScorecard");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrDocumentUploadbtn", 0);
		CommonMethod.RobustclickElementVisible("HsrDocumentUploadbtn", "HsrAuditLinkTab");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrAuditLinkTab", 0);
		CommonMethod.RobustclickElementVisible("HsrAuditLinkTab", "HsrAuditTable");
		CommonMethod.scrolldowntoElement("HsrAuditLinkTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrAuditTable", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrAuditTableTr", 10);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrAuditSI1Valid", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("HsrAuditSI1Valid"), "Audit Document", "Document table data mismatch");
		testlog.pass("**Upload Audit Document successfully**");
	}

	public void validateFeatureUploadDocumentNonEnhance() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrNonEhAddDoc", 0);
		CommonMethod.Robustclick("HsrNonEhAddDoc");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrDocType", 0);
		CommonMethod.selectdropdownValue("HsrDocType", "feature");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrLocationRadiobutton", 0);
		CommonMethod.ClickCheckbox("HsrLocationRadiobutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrVerificationMethod", 0);
		CommonMethod.selectdropdownVisibletext("HsrVerificationMethod", "Policy and/or Operations Schedule");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect1", 0);
		CommonMethod.RobustclickElementVisible("Multiselect1", "SelectOwnerOrg");
		CommonMethod.WaitUntilClickble("OwnerOrgClick", 10);
		CommonMethod.RobustclickElementVisible("OwnerOrgClick", "OwnerOrg");
		CommonMethod.sendKeys("OwnerOrg", "R");
		CommonMethod.WaitUntilClickble("SelectOwnerOrgDyn", 10);
		CommonMethod.SelectRandomfromList("SelectOwnerOrgDyn", 1, 2).click();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrLocationVerificationDoc", 0);
		CommonMethod.selectdropdownrandom("HsrLocationVerificationDoc");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocUpload", 0);
		CommonMethod.uploadFile("WPRDocUpload", FeaturefileUpload, "UploadFileVerifyScorecard");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrDocumentUploadbtn", 0);
		CommonMethod.RobustclickElementVisible("HsrDocumentUploadbtn", "HsrLibraryLinkTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrLibraryLinkTab", 0);
		CommonMethod.RobustclickElementVisible("HsrLibraryLinkTab", "HsrAuditTable");
		CommonMethod.scrolldowntoElement("HsrLibraryLinkTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrAuditTable", 0);
		List<String> val = CommonMethod.fetchTableData("HsrAuditTable");
		CommonMethod.negativesoftassertFieldValid(val.get(3), "Policy and/or Operations Schedule", "Document table data mismatch");
		testlog.pass("**Upload Feature Document successfully**");
	}
	
	public void clikOnDocumentLibraryEh() throws InterruptedException, IOException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilPresence("DocumentLibraryTab", 300);
		CommonMethod.RobustclickElementVisible("DocumentLibraryTab", "HsrNonEhAddDoc");
		testlog.info("When User clicks on DocumentLibraryTab");
	}
	
	public void ClickAchievementTab()throws IOException, InterruptedException {
    testlog.info("Given User is on Achievements Tab");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AchievementsTab", 0);
	CommonMethod.RobustclickElementVisible("AchievementsTab", "HsrAchievementsTextInAchievementsTab");
    }
	
	public void validateAchievementDocumentToaster(String ClickDownloadDocLocator, String DownloadedDocMsgLocator,
	 String expectedMessage)throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(ClickDownloadDocLocator, 0);
		CommonMethod.RobustclickElementVisible(ClickDownloadDocLocator, DownloadedDocMsgLocator);
		testlog.info("Then User Downloaded The Documents and Validated the Text");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(DownloadedDocMsgLocator, 0);
		rc.RemoveSpaceAndValidate(DownloadedDocMsgLocator, expectedMessage);
	}
	
	public void validateDownloadedAchievementDocument(String documentObjectLocator, String expectedURL)throws IOException, InterruptedException {
		
		CommonMethod.ClearDownloadFile();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(documentObjectLocator, 0);
		if(documentObjectLocator.equalsIgnoreCase("HsrDownloadAchievmentDocsRatingLetter") 
		|| documentObjectLocator.equalsIgnoreCase("HsrDownloadAchievmentDocsRatingCertificate")
		|| (documentObjectLocator.equalsIgnoreCase("LocationsAchievmentDownloadLetterOfAchievement")
		|| documentObjectLocator.equalsIgnoreCase("PortfolioDownloadLetterOfAchievementCertificate")
	    || documentObjectLocator.equalsIgnoreCase("LocationDownloadPromotionWellTookKit")
	    || documentObjectLocator.equalsIgnoreCase("LocationDownloadPromotionWellBrandingGuidelines")
	    || documentObjectLocator.equalsIgnoreCase("LocationDownloadPromotionWellCollateral") 
		|| documentObjectLocator.equalsIgnoreCase("PortfolioDownloadAchievmentDocsRatingCertificate"))) {
			CommonMethod.click(documentObjectLocator);
			testlog.info("When User Clicks on Download Document Icon");
			Thread.sleep(3000);	
			CommonMethod.switchToChildTab();
			Thread.sleep(3000);
			String childUrl = CommonMethod.getCurrentUrl();
			CommonMethod.negativesoftassertFieldValid(childUrl, expectedURL, "URL does not matched");		
		}
		if(documentObjectLocator.equalsIgnoreCase("HsrDownloadAchievmentDocsWELLHealthSafetySeal")
		|| (documentObjectLocator.equalsIgnoreCase("PortfolioDownloadAchievmentDocsWELLHealthSafetySeal")
		|| (documentObjectLocator.equalsIgnoreCase("LocationsAchievmentDownloadDigitalSeal")
		|| (documentObjectLocator.equalsIgnoreCase("LocationsAchievmentDownloadCertificate"))))) {
		CommonMethod.click(documentObjectLocator);
		testlog.info("When User Clicks on Download Document Icon");
		Thread.sleep(3000);
		boolean fileExistsReturnValue = CommonMethod.isFileDownloaded();
		testlog.info("Then User verifies Downloaded file");
		String fileExists = Boolean.toString(fileExistsReturnValue);
		CommonMethod.negativesoftassertFieldValid(fileExists, "true", "Downloaded Billing Receipt file doesn't Exist");
		testlog.pass("**Verifies Download Billing Receipt And Validate successfully**");
		}
		CommonMethod.switchToParentTab();	
	}
	
	public void HsrMarkAsYes(String SheetName, int rowNum, String FeatureName) throws Exception {
		
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 10);
		List<WebElement> Feature;
		boolean flag = false;
		Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				System.out.println("FeatureName: " + FeatureName);
				CommonMethod.WaitUntilClickble(ele, 120);
				CommonMethod.JavascriptClickElement(ele);
				flag = true;
				testlog.info("When User clicks on Feature");
				CommonMethod.scrolldowntoElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1"); 
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioAccLocScoreCardSelectUploadButton");
				CommonMethod.assertisElementPresentFalse("PortfolioAccLocScoreCardSelectUploadButton", "Upload button element present");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1"); 
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectHsrOptnRenewalYesTick",1);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalScorecardDocumentStillCurrentMsg", 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectHsrOptnRenewalScorecardDocumentStillCurrentMsg"), "Are these documents still current",
						"Renewal Scorecard Current document doesn't match");
				CommonMethod.assertisElementPresentTrue("V2ProjectHsrOptnRenewalYesTick", "Renewal YesTick element doesn't present");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectHsrOptnRenewalNoTick", 1);
				CommonMethod.assertisElementPresentTrue("V2ProjectHsrOptnRenewalNoTick", "Renewal NoTick element doesn't present");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectHsrOptnUploadDocDeleteIconRenewal", 1);
				CommonMethod.assertisElementPresentTrue("V2ProjectHsrOptnUploadDocDeleteIconRenewal", "Renewal DeleteDisableIcon element doesn't present");
				CommonMethod.Robustclick("V2ProjectHsrOptnRenewalYesTick");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrRenewalScorecardDocumentWarningmessage", 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("HsrRenewalScorecardDocumentWarningmessage"), "Thank you for the confirmation!",
						"Renewal Warningmessage doesn't match");
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectHsrOptnRenewalYesTick");
				CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalYesTick", "YesTick icon element present");
				CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalNoTick", "NoTick icon element present");
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectHsrOptnRenewalValidateDocs");
				CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalValidateDocs", "Document is disabled ");				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalValidateToolTip", 0);
				String actualToolTipText=CommonMethod.getattributeValueByTextContent("V2ProjectHsrOptnRenewalValidateToolTip");
				actualToolTipText= actualToolTipText.replaceAll("\\s+", " ").trim();
				System.out.println(actualToolTipText);
				CommonMethod.negativesoftassertFieldValid(actualToolTipText, "document cannot be deleted as it has been reviewed", "Delete icon ToolTip Text does not matched");				
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("HsrRenewalScorecardDocumentSC3WarningIcon");
				CommonMethod.assertisElementPresentFalse("HsrRenewalScorecardDocumentSC3WarningIcon", "Renewal Warning Paper icon element present");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
}
	
public void HsrMarkAsNo(String SheetName, int rowNum, String FeatureName) throws IOException, InterruptedException {
		
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 10);
		List<WebElement> Feature;
		boolean flag = false;
		Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				System.out.println("FeatureName: " + FeatureName);
				CommonMethod.WaitUntilClickble(ele, 120);
				CommonMethod.JavascriptClickElement(ele);
				flag = true;
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1"); 
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalYesTick", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalNoTick", 0);
				CommonMethod.Robustclick("V2ProjectHsrOptnRenewalNoTick");
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectHsrOptnRenewalYesTick");
				CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalYesTick", "YesTick icon element present");
				CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalNoTick", "NoTick icon element present");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrRenewalScorecardDocumentWarningmessage", 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("HsrRenewalScorecardDocumentWarningmessage"), "Thank you for the confirmation!",
						"Renewal Warningmessage doesn't match");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectHsrOptnUploadDocDeleteIconRenewal", 1);
				CommonMethod.assertisElementPresentTrue("V2ProjectHsrOptnUploadDocDeleteIconRenewal", "Renewal DeleteDisableIcon element doesn't present");				
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectHsrOptnRenewalValidateDocs", 1);
				CommonMethod.negativeAssertElementPresentTrue("V2ProjectHsrOptnRenewalValidateDocs", "Document is enabled ");				
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("HsrRenewalScorecardDocumentSC4WarningIcon", 1);
				CommonMethod.negativeAssertElementPresentTrue("HsrRenewalScorecardDocumentSC4WarningIcon", "Renewal Warning Paper icon element present");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
    }

       public void purseYesValidFromScorecard() throws IOException, InterruptedException {
    	 
	    testlog.info("Given User on Scorecard page");
	    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecardPurseYesSelect", 0);
	    CommonMethod.click("HSRScorecardPurseYesSelect");
	    testlog.info("And User click on Purse Yes");
	    CommonMethod.WaitUntilNumberOfElementToBePresentWithException("HSRScorecardPurseYesSelected", 1);
	    CommonMethod.assertisElementPresentTrue("HSRScorecardPurseYesSelected", "PurseYes is not selected");
	    testlog.info("And User verifies the Selected Purse Yes");
	    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPursueToast", 0);
	    String pursueToastText = CommonMethod.getattributeValueByTextContent("PortfolioScorecardPursueToast");
	    CommonMethod.negativesoftassertFieldValid(pursueToastText,
			"Pursue status changed!", "Purse Status message Doesn't match");
	    testlog.info("Then User verifies the Pursue status message");
	    testlog.pass("**Verifies the Pursue Yes status successful**");
      }
       
   	public void purseNoValidFromScorecard() throws IOException, InterruptedException {
   		
   		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecardPurseNoSelect", 0);
		CommonMethod.click("HSRScorecardPurseNoSelect");
		testlog.info("And User click on Purse No");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("HSRScorecardPurseNoSelected", 1);
		CommonMethod.assertisElementPresentTrue("HSRScorecardPurseNoSelected", "PurseNo is not selected");
		testlog.info("And User verifies the Selected Purse No");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPursueToast", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioScorecardPursueToast"),
				"Pursue status changed!", "Purse Status message Doesn't match");
		testlog.info("Then User verifies the Pursue status message");
		testlog.pass("**Verifies the Pursue No status successful**");

	}
   	
	public void purseMaybeValidFromScorecard() throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecardPurseMayBeSelect", 0);
		CommonMethod.click("HSRScorecardPurseMayBeSelect");
		testlog.info("And User click on Purse Maybe");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("HSRScorecardPurseMayBeSelected", 1);
		CommonMethod.assertisElementPresentTrue("HSRScorecardPurseMayBeSelected", "PurseMaybe is not selected");
		testlog.info("And User verifies the Selected Purse Maybe");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPursueToast", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioScorecardPursueToast"),
				"Pursue status changed!", "Purse Status message Doesn't match");
		testlog.info("Then User verifies the Pursue status message");
		testlog.pass("**Verifies the Pursue Maybe status successful**");

	}
	
	public void purseYesToNoValidFromScorecard() throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecardPurseYesToNoSelect", 0);
		CommonMethod.Robustclick("HSRScorecardPurseYesToNoSelect");
		testlog.info("And User click on Purse No");
        CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Confirmbtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPurseYesToNoCancel", 0);
		testlog.info("And User verifies confirm button");
		testlog.info("And User verifies cancel button");
		// cancel
		CommonMethod.Robustclick("PortfolioScorecardPurseYesToNoCancel");
		// Confirm
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecardPurseYesToNoSelect", 0);
		CommonMethod.Robustclick("HSRScorecardPurseYesToNoSelect");
		rc.confirmPursing();
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("HSRScorecardPurseYesToNoSelected", 1);
		CommonMethod.assertisElementPresentTrue("HSRScorecardPurseYesToNoSelected", "PurseNo is not selected");
		testlog.pass("**Verifies the Pursue No status successful**");
	}
	
	public void purseMaybeToNoValidFromScorecard() throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecardPurseMaybeToNoSelect", 0);
		CommonMethod.click("HSRScorecardPurseMaybeToNoSelect");
		testlog.info("And User click on Purse No");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("HSRScorecardPurseMaybeToNoSelected", 1);
		CommonMethod.assertisElementPresentTrue("HSRScorecardPurseMaybeToNoSelected", "PurseNo is not selected");
		testlog.info("And User verifies the Selected Purse No");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPursueToast", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioScorecardPursueToast"),
				"Pursue status changed!", "Purse Status message Doesn't match");
		testlog.info("Then User verifies the Pursue status message");
		testlog.pass("**Verifies the Pursue No status successful**");
	}
	
	public void purseYesToMaybeValidFromScorecard() throws IOException, InterruptedException {
		
	    testlog.info("Given User on Scorecard page");
	    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecardPurseYesSelectForFeaturePHR", 0);
	    CommonMethod.JavascriptClickElement("HSRScorecardPurseYesSelectForFeaturePHR");
	    testlog.info("And User click on Purse Yes");
	    CommonMethod.WaitUntilNumberOfElementToBePresentWithException("HSRScorecardPurseYesSelectedForFeaturePHR", 1);
	    CommonMethod.assertisElementPresentTrue("HSRScorecardPurseYesSelectedForFeaturePHR", "PurseYes is not selected");
	    testlog.info("And User verifies the Selected Purse Yes");
	    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPursueToast", 0);
	    String pursueToastText = CommonMethod.getattributeValueByTextContent("PortfolioScorecardPursueToast");
	    CommonMethod.negativesoftassertFieldValid(pursueToastText,
			"Pursue status changed!", "Purse Status message Doesn't match");
	    testlog.info("Then User verifies the Pursue status message");
	    testlog.pass("**Verifies the Pursue Yes status successful**");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecardPurseYesToMaybeSelect", 0);
		CommonMethod.JavascriptClickElement("HSRScorecardPurseYesToMaybeSelect");
		testlog.info("And User click on Purse Maybe");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Confirmbtn", 0);
		CommonMethod.Robustclick("Confirmbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("HSRScorecardPurseYesToMaybeSelected", 1);
		CommonMethod.assertisElementPresentTrue("HSRScorecardPurseYesToMaybeSelected", "PurseMaybe is not selected");
		testlog.info("And User verifies the Selected Purse Maybe");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPursueToast", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioScorecardPursueToast"),
				"Pursue status changed!", "Purse Status message Doesn't match");
		testlog.info("Then User verifies the Pursue status message");
		testlog.pass("**Verifies the Pursue Maybe status successful**");
	}

public void uploadDocumentInFeatureNonEnhance(String  FeatureName) throws IOException, InterruptedException {
	List<WebElement> Feature;
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
	Feature = CommonMethod.findElements("V2projectRatingFeatureName");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplicableVersion", 0);
	CommonMethod.scrolldowntoElement("ApplicableVersion");
	testlog.info("Fetching total no. of credits on page");
	boolean flag = false;
	for (WebElement ele : Feature) {
		String Creditname = ele.getText();
		Creditname = Creditname.replaceAll("\\.", "");
		if (Creditname.equalsIgnoreCase(FeatureName)) {
			flag = true;
		CommonMethod.JavascriptClickElement(ele);
		if (TestCaseName.equalsIgnoreCase("Healthsafety_TC_20_10_HsrOngoingDataReport")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("HsrRenewalScorecardDocumentSA4WarningIcon",1);
			CommonMethod.assertisElementPresentTrue("HsrRenewalScorecardDocumentSA4WarningIcon", "Renewal OngoingDataReport Warning Paper icon element present");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrRenewalScorecardPOngoingWarningMessage", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("HsrRenewalScorecardPOngoingWarningMessage"), "Updated ongoing data report is required for renewal",
				"Renewal Scorecard Current document doesn't match");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPDocIcon", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectWPRPDocIcon", "HsrSelectTypeDoc");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrSelectTypeDoc", 0);
		CommonMethod.selectdropdownIndex("HsrSelectTypeDoc", 1);
		if (CommonMethod.isElementsExist("HsrLocationrtn", 3)) {
			CommonMethod.WaitUntilClickble("HsrLocationrtn", 10);
			CommonMethod.ClickCheckbox("HsrLocationrtn");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRVerificationMethod", 0);
		CommonMethod.selectdropdownIndex("V2ProjectWPRVerificationMethod", 1);
		CommonMethod.scrolldowntoElement("V2ProjectWPRVerificationMethod");
		if (CommonMethod.isElementsExist("HsrSelectLoc", 3)) {
			CommonMethod.WaitUntilClickble("HsrSelectLoc", 10);
			CommonMethod.selectdropdownIndex("HsrSelectLoc", 1);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUpload", 0);
		CommonMethod.uploadFile("V2ProjectDocUpload", FeaturefileUpload,"UploadFileVerifyScorecard");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrUploadDocFeature", 0);
		CommonMethod.Robustclick("HsrUploadDocFeature");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecrdEhanceDeleteIcon", 0);
		CommonMethod.scrolldowntoElement("ApplicableVersion");
		CommonMethod.JavascriptClickElement(ele);
		break;
		}
	}
	CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
	testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
}

public void warningHsrOngoingDataReport() throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioAccLocScoreCardSelectUploadButton",1);
	CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("HsrRenewalScorecardDocumentSA4WarningIcon");
	CommonMethod.assertisElementPresentFalse("HsrRenewalScorecardDocumentSA4WarningIcon", "Renewal OngoingDataReport Warning Paper icon element present");
}

public void warningMessageHsrMarkNo() throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioAccLocScoreCardSelectUploadButton",1);
	CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("HsrRenewalScorecardDocumentSC4WarningIcon");
	CommonMethod.assertisElementPresentFalse("HsrRenewalScorecardDocumentSC4WarningIcon", "Renewal Warning No Paper icon element present");
	
}

public void purseYes() throws IOException, InterruptedException {
CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrRenewalScorecardPurseYes", 0);
CommonMethod.Robustclick("HsrRenewalScorecardPurseYes");
}

public void ScorecardSaveAndExitFlow(String featureName, String SheetName, int rowNum, String Commodity)
		throws Exception {
	testlog.info("Given User is on Scorecard page");
	List<WebElement> Feature;
	Feature = CommonMethod.findElements("HSRScorecardFeature");
	boolean flag = false;
	testlog.info("Fetching total no. of credits on page");
	for (WebElement ele : Feature) {
		String Creditname = ele.getText();
		Creditname = Creditname.replaceAll("\\.", "");
		if (Creditname.equalsIgnoreCase(featureName)) {
			flag = true;
			CommonMethod.scrolldowntoElement("ApplicableVersion");
			CommonMethod.JavascriptClickElement(ele);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRVerficationTab", 0);
			CommonMethod.RobustclickElementVisible("WPRVerficationTab", "PortfolioScoreCardAddOptionbutton");
			testlog.info("And User clicks on VerificationTab");
			CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
			CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddOptionbutton",
					"PortfolioScoreCardAddButton");
			testlog.info("And User clicks on AddOption button");
			CommonMethod.clickOnListWebelementFromIndex("PortfolioScoreCardAddButton", 0);
			testlog.info("And User clicks on Add button");
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AddOptionValid");
			CommonMethod.assertisNotElementPresent("AddOptionValid", "Added option button is visible");
			testlog.info("User verifies Remove button");
			CommonMethod.WaitUntilVisibility("PortfolioScoreCardVerificationCloseicon", 10);
			CommonMethod.Robustclick("PortfolioScoreCardVerificationCloseicon");
			testlog.info("And User clicks on Closeicon");
			generic.assignLocationGeneric(Commodity, false, false, true, false, false);
			/** Upload Document for Tasks */
			CommonMethod.scrollDown();
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn",
					0);
			CommonMethod.JavascriptClickElement("PortfolioScoreCardVerificationUploadbtn");
			generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, SampleJpgfile, false, false, false, false);
			ValidateScorecardUploadButtonCountAndTable(SheetName, rowNum, Commodity);
			CommonMethod.JavascriptClickElement(ele);
		}
	   }
	 }
	
	public void ValidateScorecardUploadButtonCountAndTable(String SheetName, int rowNum, String Commodity) throws Exception {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
		CommonMethod.Robustclick("AssignLocCloseIcon");
		CommonMethod.assertisElementPresentTrue("HSRScorecardUploadedDocLocationCount", "5/5 Locations count does not matched");
		CommonMethod.scrolldowntoElement("PortfolioScorecardFeatureTableCount");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureTableCount", 0);
		String expectedFeatureTableCount = CommonMethod.getattributeValueByTextContent("PortfolioScorecardFeatureTableCount");
		CommonMethod.negativesoftassertFieldValid(expectedFeatureTableCount, "1", "Table count 1 does not matched");
		testlog.info("Validate Scorecard Upload Button Count And Table Cases has been Successfully Passed");
	}
	
	public void ValidateScorecardConfirmationModalFromPurseYesToNoAfterAddingOptions(String featureName, String SheetName, int rowNum, String Commodity) throws Exception {
		
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
		Feature = CommonMethod.findElements("HSRScorecardFeature");
		boolean flag = false;
		testlog.info("Fetching total no. of credits on page");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRVerficationTab", 0);
				CommonMethod.RobustclickElementVisible("WPRVerficationTab", "PortfolioScoreCardAddOptionbutton");
				testlog.info("And User clicks on VerificationTab");
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRSelectScorecardPurseYesToNoAfterAddedOptions", 0);
		CommonMethod.ScrollUpToElement("HSRSelectScorecardPurseYesToNoAfterAddedOptions");
		CommonMethod.JavascriptClickElement("HSRSelectScorecardPurseYesToNoAfterAddedOptions");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Confirmbtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPurseYesToNoCancel", 0);
		testlog.info("And User verifies confirm button");
		testlog.info("And User verifies cancel button");
		// cancel
		CommonMethod.JavascriptClickElement("PortfolioScorecardPurseYesToNoCancel");
		// Confirm
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRSelectScorecardPurseYesToNoAfterAddedOptions", 0);
		CommonMethod.JavascriptClickElement("HSRSelectScorecardPurseYesToNoAfterAddedOptions");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Confirmbtn", 0);
		CommonMethod.JavascriptClickElement("Confirmbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPursueToast", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioScorecardPursueToast"),
				"Pursue status changed!", "Purse Status message Doesn't match");
		testlog.info("And User verifies the Pursue status message");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
		CommonMethod.assertisElementPresentTrue("PortfolioScoreCardAddOptionbutton", "Add options button is not visible");
		CommonMethod.JavascriptClickElement(ele);
			}
		}
	}
	
	public void ValidateTaskCompletionOnAddingOptionFromDocEdit(String SheetName, int rowNum, String ProjectTypeVerification, String ProjectType,
			String Commodity, String FileName, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
			Boolean NoteRequired, Boolean IntentCheckboxRequired, String featureName) throws Exception {
		
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
		if (Commodity.contains("Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardFeature", 0);
			Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
			Feature = CommonMethod.findElements("RatingFeatureName");
		}
		boolean flag = false;
		testlog.info("Fetching total no. of credits on page");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRVerficationTab", 0);
				CommonMethod.click("WPRVerficationTab");	
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddOptionbutton", "PortfolioScoreCardAddButtonOne");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddButtonOne", 0);
		CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddButtonOne", "ScorecardAddedOption");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationCloseicon", 0);
		CommonMethod.Robustclick("PortfolioScoreCardVerificationCloseicon");
		AssignLocations();
		CommonMethod.scrollDown();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioHsrOptnUploadButtonOptionOne", 0);
		CommonMethod.JavascriptClickElement("PortfolioHsrOptnUploadButtonOptionOne");
		generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired,
				VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndExitbtn", 0);
		CommonMethod.Robustclick("SaveAndExitbtn");  
		CommonMethod.scrolldowntoElement("PortfolioHsrOptnUploadButtonOptionOne");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);
		CommonMethod.click("PortfolioAndRatingDeleteEditMenu");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableEditIcon", 0);
		CommonMethod.JavascriptClickElement("PortfolioAndRatingLocAccDocumentTableEditIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadAddfeature", 0);
		CommonMethod.scrolldowntoElement("PortfolioScorecardUploadOptionName");
		CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
				"PortfolioScoreCardVerificationSelectFeature");
		testlog.info("And User clicks on Add feature"); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardPartDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioScoreCardPartDropdown", "SA4");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardSelectSpaceTypeFromDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioScoreCardSelectSpaceTypeFromDropdown", "All Spaces");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardSelectOptionFromDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioScoreCardSelectOptionFromDropdown", "Option 2 Assess Chemical and Biological Water Quality");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPart", 0);
		CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPart",
				"PortfolioScoreCardVerificationUploadAddfeature");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndExitbtnUploadPage", 0);
		CommonMethod.Robustclick("SaveAndExitbtnUploadPage"); 
		CommonMethod.scrolldowntoElement("PortfolioHsrOptnUploadButtonOptionOne");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTablePartIdOne", 0);
		String actualTablePartIdOne = CommonMethod.getattributeValueByTextContent("PortfolioTablePartIdOne").trim();
		CommonMethod.negativesoftassertFieldValid(actualTablePartIdOne, "SA4", "SA4 text does't matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTableOptionsOne", 0);
		String actualTableOptionsOne = CommonMethod.getattributeValueByTextContent("PortfolioTableOptionsOne").trim();
		CommonMethod.negativesoftassertFieldValid(actualTableOptionsOne, "Option 1", "Option 1 text does't matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTablePartIdTwo", 0);
		String actualTablePartIdTwo = CommonMethod.getattributeValueByTextContent("PortfolioTablePartIdTwo").trim();
		CommonMethod.negativesoftassertFieldValid(actualTablePartIdTwo, "SA4", "SA4 text does't matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTableOptionsTwo", 0);
		String actualTableOptionsTwo = CommonMethod.getattributeValueByTextContent("PortfolioTableOptionsTwo").trim();
		CommonMethod.negativesoftassertFieldValid(actualTableOptionsTwo, "Option 2", "Option 2 text does't matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationCountInOptions", 0);
		String actualUploadedDocLocationCount = CommonMethod.getattributeValueByTextContent("LocationCountInOptions").trim();
		CommonMethod.negativesoftassertFieldValidEquals(actualUploadedDocLocationCount, "2/2 Locations", "2/2 Locations count does't matched");	
		CommonMethod.JavascriptClickElement(ele);
		break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + featureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
	
	public void AssignLocations() throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocbtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Assignbutton", 0);
		Thread.sleep(1000);
		CommonMethod.JavascriptClickElement("Assignbutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCheckedLocationOne", 0);
		CommonMethod.ClickCheckbox("PortfolioCheckedLocationOne");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCheckedLocationTwo", 0);
		CommonMethod.ClickCheckbox("PortfolioCheckedLocationTwo");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveChangesButton", 0);
		CommonMethod.JavascriptClickElement("SaveChangesButton");  
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
		CommonMethod.Robustclick("AssignLocCloseIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
	}
	
	public void MarkAndValidateHsrNaStatus(String SheetName, int rowNum) throws IOException, InterruptedException {
	
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		testlog.info("When User clicks on EditTab");
	    rc.navigateAchievementAdminTab();
		testlog.info("And User clicks on AdminFields Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditAchievedStatus", 0);
		CommonMethod.VerifyRadioOrCheckboxSelcted("EditAchievedStatus");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditNAStatus", 0);
		CommonMethod.ClickCheckbox("EditNAStatus");
		CommonMethod.VerifyRadioOrCheckboxSelcted("EditNAStatus");
        rc.saveAchievementAdminTab("HsrAchievementAdminTab");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("SealImgValidate");
		CommonMethod.assertisElementPresentFalse("SealImgValidate", "Seal image is present");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("SealDateValidate");
		CommonMethod.assertisElementPresentFalse("SealDateValidate", "Seal date is present");
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		testlog.info("When User clicks on EditTab");
	    rc.navigateAchievementAdminTab();
	    CommonMethod.VerifyRadioOrCheckboxSelcted("EditNAStatus");
		rc.saveAchievementAdminTab("HsrAchievementAdminTab");
	}
	
		  public void LoginWithSuperAdminAndSaveDocIdInExcel(String featureName, String SheetName, int rowNum, String ProjectType, String Commodity, String FileName, boolean PartNameRequired,
					boolean VerificationMethodValidationRequired, boolean NoteRequired, boolean IntentCheckboxRequired) throws Exception {				
			  CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 1);
				List<WebElement> Feature;
				Feature = CommonMethod.findElements("V2projectRatingFeatureName");
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				for (WebElement ele : Feature) {
					CommonMethod.WaitUntilClickble(ele, 120);
					CommonMethod.JavascriptClickElement(ele);
					CommonMethod.scrolldowntoElement("FeatureNextStep");
					rc.RemoveSpaceAndValidate("HsrDocumentIdText", "Document ID:"); 
					rc.RemoveSpaceAndValidate("HsrDocumentIdCopyBtn", "Copy");					
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrGetDocumentId", 0);
					String getDocumentId = CommonMethod.getattributeValueByTextContent("HsrGetDocumentId");
					getDocumentId= getDocumentId.replaceAll("\\s+", " ").trim();
					String[] splitFromColon = getDocumentId.split(":");
					String getFromColon = splitFromColon[1];	
					String getDocumentID = getFromColon.substring(1, 7);
					data.setCellData(SheetName, "ScorecardDocumentId", 3, getDocumentID);
					System.out.println("SplitId: "+getDocumentID);					
					CommonMethod.RobustclickElementVisible("HsrDocumentIdCopyBtn", "HsrDocumentIdCopiedToaster");
					if(CommonMethod.isElementsExist("getToasterMessage", 20)) {
					rc.RemoveSpaceAndValidate("getToasterMessage", "Document ID copied!");
				    }
					CommonMethod.JavascriptClickElement(ele);
					break;
		 }
     }
		  
			public void FetchDocumentIdFromNonEnhancedProjectAndAddInEnhancedProject(String SheetName, int rowNum, String ProjectTypeVerification, String ProjectType,
					String Commodity, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
					Boolean NoteRequired, Boolean IntentCheckboxRequired, String featureName) throws Exception {				
				testlog.info("Given User is on Scorecard page");
				List<WebElement> Feature;
				Feature = CommonMethod.findElements("HSRScorecardFeature");
				boolean flag = false;
				testlog.info("Fetching total no. of credits on page");
				for (WebElement ele : Feature) {
					String Creditname = ele.getText();
					Creditname = Creditname.replaceAll("\\.", "");
					if (Creditname.equalsIgnoreCase(featureName)) {
						flag = true;
						CommonMethod.scrolldowntoElement("ApplicableVersion");
						CommonMethod.JavascriptClickElement(ele); 
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRVerficationTab", 0);
						CommonMethod.RobustclickElementVisible("WPRVerficationTab", "PortfolioHsrOptnUploadButtonOptionOne");
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTable", 0);
						CommonMethod.scrolldowntoElement("Uploadbutton");
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioHsrOptnUploadButtonOptionOne", 0);
						CommonMethod.JavascriptClickElement("PortfolioHsrOptnUploadButtonOptionOne");
						CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("ScorecardFeatureLoader");
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadAddfeature", 0);
						CommonMethod.scrolldowntoElement("PortfolioScorecardUploadOptionName");
						FetchAndValidateSuperAdminDocumentIDFunctionality(SheetName, rowNum);
				        CommonMethod.JavascriptClickElement(ele);
				        break;
					}
				}
			CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match"); 
			}
			
			public void FetchAndValidateSuperAdminDocumentIDFunctionality(String SheetName, int rowNum) throws IOException, InterruptedException {				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrDocumentIdTextBox", 0);
		        String getDocumentID = data.getCellData(SheetName, "ScorecardDocumentId", rowNum);
		        System.out.println("My DocumentID: "+getDocumentID);			
		        CommonMethod.clearAndSendKey("HsrDocumentIdTextBox", getDocumentID);
		        rc.ScorecardUploadSaveButton();			        
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocFirstChildCbx", 0);
				CommonMethod.ClickCheckbox("WPRAssignLocFirstChildCbx");
				rc.ScorecardConfirmLocUploadSaveButton();				        
		        CommonMethod.scrolldowntoElement("Uploadbutton");		        
		        CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrValidateFileName", 0);
		        String actualFileName = CommonMethod.getattributeValueByTextContent("HsrValidateFileName");
		        actualFileName= actualFileName.replaceAll("\\s+", " ").trim();
		        System.out.println(actualFileName);
		        CommonMethod.negativesoftassertFieldValid(actualFileName, "sample", "Actual sample file name does not matched ");				        
		        CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrValidateUserName", 0);
		        String actualUserName = CommonMethod.getattributeValueByTextContent("HsrValidateUserName");
		        actualUserName= actualUserName.replaceAll("\\s+", " ").trim();
		        System.out.println(actualUserName);
		        CommonMethod.negativesoftassertFieldValid(actualUserName, "Test User 1", "Actual UserName does not matched ");		        
		        CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrValidateDocDate", 0);
		        String actualDate = CommonMethod.getattributeValueByTextContent("HsrValidateDocDate");
		        actualDate= actualDate.replaceAll("\\s+", " ").trim();
		        System.out.println(actualDate);
		        CommonMethod.negativesoftassertFieldValid(actualDate, CommonMethod.ValidateDate(), "Actual Date does not matched ");	
			}
			
			public void ForAlternativeDocFetchDocumentIdFromNonEnhancedProjectAndAddInEnhancedProject(String SheetName, int rowNum, String ProjectTypeVerification, String ProjectType,
					String Commodity, String FileName, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
					Boolean NoteRequired, Boolean IntentCheckboxRequired, String featureName) throws Exception {
				
				testlog.info("Given User is on Scorecard page");
				List<WebElement> Feature;
				Feature = CommonMethod.findElements("HSRScorecardFeature");
				boolean flag = false;
				testlog.info("Fetching total no. of credits on page");
				for (WebElement ele : Feature) {
					String Creditname = ele.getText();
					Creditname = Creditname.replaceAll("\\.", "");
					if (Creditname.equalsIgnoreCase(featureName)) {
						flag = true;
						CommonMethod.scrolldowntoElement("ApplicableVersion");
						CommonMethod.JavascriptClickElement(ele);
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRVerficationTab", 0);
						CommonMethod.click("WPRVerficationTab");	
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
				CommonMethod.JavascriptClickElement("PortfolioScoreCardAddOptionbutton");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadTaskEditAlternative", 0);
				CommonMethod.RobustclickElementVisible("PortfolioUploadTaskEditAlternative", "ScorecardAlternativeAddBtn");				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAlternativeAddBtn", 0);
				CommonMethod.click("ScorecardAlternativeAddBtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationCloseicon", 0);
				CommonMethod.Robustclick("PortfolioScoreCardVerificationCloseicon");
				AssignLocations();
				CommonMethod.scrollDown();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.JavascriptClickElement("Uploadbutton");
				generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired,
						VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndExitbtn", 0);
				CommonMethod.Robustclick("SaveAndExitbtn");  
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);			
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.JavascriptClickElement("Uploadbutton");
				FetchAndValidateSuperAdminDocumentIDFunctionality(SheetName, rowNum);
				CommonMethod.JavascriptClickElement(ele);
					}
				}
				CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
			}
			
			public void SearchPerformanceByIDAndCompleteTheForm(String SheetName, int rowNum) throws IOException, InterruptedException {
				testlog.info("Given User is on Dashboard page");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
				CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLHealthSafetyNavBar");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WELLHealthSafetyNavBar", 0);
				CommonMethod.RobustclickElementVisible("WELLHealthSafetyNavBar", "HsrIdSearch");
				testlog.info("When User clicks on WELL Healthsafety from top menu under Projects");
				CommonMethod.verifyDropdownValues("HSRTypeDropdown", "HsrType");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrIdSearch", 0);
				CommonMethod.RobustclickElementVisible("HsrIdSearch", "HsrIdSearch");
				testlog.info("HealthSafety ID:" + data.getCellData(SheetName, "ProjectID", rowNum));
				CommonMethod.sendKeys("HsrIdSearch", data.getCellData(SheetName, "ProjectID", rowNum));
				testlog.info("And User enter data to HsrId");
				CommonMethod.RobustclickElementVisible("HsrapplySearch", "SearchResultIDVerify");
				testlog.info("And User clicks on Apply button");
				int ProjectCount = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
				CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount), "1", "HealthSafety Search failed");
				CommonMethod.sendKeys("HsrNameList", data.getCellData(SheetName, "projectName", rowNum));
				testlog.info("And User enter data to HsrName");
				CommonMethod.RobustclickElementVisible("HsrapplySearch", "SearchResultIDVerify");
				int ProjectCount1 = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
				CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectCount1), "1", "HealthSafety Search failed");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingIdClick", 0);
				CommonMethod.RobustclickElementVisible("RatingIdClick", "Hsrlocationsize");				
				String Area = CommonMethod.randomNumberBetweenRanges(100, 50000);
				CommonMethod.clearAndSendKey("Hsrlocationsize", Area);
				data.setCellData(SheetName, "Area", rowNum, CommonMethod.getattributeValue("Hsrlocationsize"));
				testlog.info("Hsrlocations: " + data.getCellData(SheetName, "Area", rowNum));
				testlog.info("And User enter the Location Size");
				CommonMethod.WaitUntilVisibility("HsrLocContinuebutton", 120);
				CommonMethod.RobustclickElementVisible("HsrLocContinuebutton", "HsrDiscountBackButton");
				testlog.info("And User clicks on continue button");
				if (CommonMethod.isElementsExist("HsrWPRYesMyOrganizationCbx", 10)) {
					CommonMethod.RobustclickElementVisible("HsrProgramFeeContinuebtn", "HsrWPRYesMyOrganizationCbx");
					CommonMethod.negativesoftassertPageSource(
							"Yes, my organization meets the criteria of the listed discount category.* is required.",
							"My Organization CheckBox Error Name");
					CommonMethod.WaitUntilClickble("HsrWPRYesMyOrganizationCbx", 60);
					CommonMethod.ClickCheckbox("HsrWPRYesMyOrganizationCbx");
					testlog.info("And User checks the MyOrganization checkbox");
				}
				CommonMethod.RobustclickElementVisible("HsrProgramFeeContinuebtn", "HsrProgramFeePublicrbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrProgramFeePublicrbtn", 0);
				CommonMethod.scrolldowntoElement("HsrProgramFeePublicrbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrProgramFeePublicrbtn", 0);
				CommonMethod.VerifyRadioOrCheckboxSelcted("HsrProgramFeePublicrbtn","PublicYes");
				CommonMethod.ClickCheckbox("HsrProgramFeePublicrbtn");
				testlog.info("And User check on Program Fee Private checkboxes");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrAcknowledecbx", 0);
				CommonMethod.ClickCheckbox("HsrAcknowledecbx");
				testlog.info("And User check on Acknowlede checkboxes");
				CommonMethod.RobustclickElementVisible("HsrReviewbtn", "BillingLanding");
				testlog.info("And User clicks on Review button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
				testlog.info("Then User will be redirected to Billing invoice page");
				testlog.pass("**Verifies the Registration successful**");
			}
}