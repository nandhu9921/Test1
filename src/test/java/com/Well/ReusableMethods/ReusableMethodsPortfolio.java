package com.Well.ReusableMethods;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.zaproxy.clientapi.core.ClientApiException;

import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;
import com.Well.Engine.XlsReader;
import io.restassured.response.Response;

public class ReusableMethodsPortfolio extends BaseClass {
	String header;
	long EstArea;
	String PortfolioAndRatingLocAccDocumentTable = "PortfolioAndRatingLocAccDocumentTable";
	String PortfolioAndRatingLocAccDocumentTableTr = "PortfolioAndRatingLocAccDocumentTableTr";
	String PortfolioScoreCardVerificationAssignLocSavebtn = "PortfolioScoreCardVerificationAssignLocSavebtn";
	String toolTipMessage = "You do not have permission to delete this document";

	public void RegisterPortfolio(String SheetName, int rowNum, String Engagement_level, String Country,
			String ProjectName) throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL certified account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WellAtScaleNavBar");
		CommonMethod.RobustclickElementVisible("WellAtScaleNavBar", "PortfolioCreateAccountButton");
		testlog.info("When User clicks on WELL At Scale from top menu under Projects");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCreateAccountButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioCreateAccountButton", "PortfolioAccountName");
		testlog.info("And User clicks on Create Account Button");
		String AccountName = ProjectName + CommonMethod.randomNumber(8000000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccountName", 0);
		CommonMethod.RobustclickElementVisible("PortfolioCreateAccountSubmit", "PortfolioAccountName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccountName", 0);
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Acount Name is required.", "Acount Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Organization is required.", "Organization Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Organization Industry is required.",
				"Organization Industry Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Locations Questionaire is required.",
				"Locations Questionaire Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Total number of assets is required.",
				"Total number of assets Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Number of locations is required.",
				"Number of locations Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Area is required.", "Area Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Area in Meter is required.", "Area in Meter Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Primary Locations is required.", "Primary Locations Error Mismatch");
		CommonMethod.negativesoftassertPageSource("SpaceTypes is required.", "SpaceTypes Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Country is required.", "Country Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Street is required.", "Street Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("City is required.", "City Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Postal Code is required.", "Postal Code Error Mismatch");
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccountName", 0);
		CommonMethod.sendKeys("PortfolioAccountName", AccountName);
		data.setCellData(SheetName, "AccountName", rowNum, AccountName);
		CommonMethod.scrolldowntoElement("PortfolioAccountName");
		rc.SelectOwnerOrg(SheetName, rowNum);
		CommonMethod.verifyDropdownValues("HsrWprEditOrgIndustry", "OrganizationIndustry");
		data.setCellData(SheetName, "OrgName", rowNum, CommonMethod.getText("OrgName"));
		testlog.info("OrganizationName: " + data.getCellData(SheetName, "OrgName", rowNum));
		CommonMethod.verifyDropdownValues("V2ProjectUpdatesCountryRegion", "Country");
		CommonMethod.selectdropdownVisibletext("OrgIndustry", "Hospitals");
		testlog.info("And User select OrgIndustry");
		data.setCellData(SheetName, "OrgIndustry", rowNum, CommonMethod.getSelectedDropdownValue("OrgIndustry"));
		testlog.info("OrgIndustry: " + data.getCellData(SheetName, "OrgIndustry", rowNum));
		if (Engagement_level.equalsIgnoreCase("Portfolio")) {
			CommonMethod.ClickCheckbox("PortfolioSubsetRadio");
			testlog.info("And User checks the Subscription checkbox");
			CommonMethod.sendKeys("PortfolioLocationDescription", "Test Portfolio");
		} 
		else if (Engagement_level.equalsIgnoreCase("EnterpriseProject")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEnterpriseLegalEntityRadio", 0);
			CommonMethod.ClickCheckbox("PortfolioEnterpriseLegalEntityRadio");
			testlog.info("And User checks the Enterprise Legal Entity Radio");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEnterpriseDisabledCheckbox", 0);
			CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioEnterpriseDisabledCheckbox");
			AddLegalEntities("PortfolioEnterpriseAddMoreBtn", "PortfolioEnterpriseLegalEntityTextBox", "Delete This Legal Entity");
			DeleteLegalEntities("PortfolioEnterpriseDeleteButton");
			AddLegalEntities("PortfolioEnterpriseAddMoreBtn", "PortfolioEnterpriseLegalEntityTextBox", "Test Legal Entity");
		}
		else {
			CommonMethod.ClickCheckbox("PortfolioNotSureRadio");
			testlog.info("And User checks the NotSure checkbox");
		}
		CommonMethod.sendKeys("PortfolioNumberOfLocation", "15");
		data.setCellData(SheetName, "Location", rowNum, CommonMethod.getattributeValue("PortfolioNumberOfLocation"));
		testlog.info("PortfolioNumberOfLocation: " + data.getCellData(SheetName, "Location", rowNum));
		CommonMethod.sendKeys("PortfolioEstimatedNumberOfLocation", "10");
		testlog.info("And User enter data to Estimated Number Of Location");
		data.setCellData(SheetName, "EstimatedNumberOfLocation", rowNum,
				CommonMethod.getattributeValue("PortfolioEstimatedNumberOfLocation"));
		testlog.info("EstimatedNumberOfLocation: " + data.getCellData(SheetName, "EstimatedNumberOfLocation", rowNum));
		String Area = CommonMethod.randomNumberBetweenRanges(100, 50000);
		CommonMethod.sendKeys("PortfolioGrossAreaSQFT", Area);
		testlog.info("And User enter data to AreaSQFT");
		data.setCellData(SheetName, "AreaSQFT", rowNum, CommonMethod.getattributeValue("PortfolioGrossAreaSQFT"));
		testlog.info("PortfolioGrossAreaSQFT: " + data.getCellData(SheetName, "AreaSQFT", rowNum));
		CommonMethod.scrolldowntoElement("PortfolioPrimarlyLocated");
		CommonMethod.RobustclickElementVisible("PortfolioPrimarlyLocated", "SelectOwnerOrg");
		CommonMethod.WaitUntilClickble("SelectOwnerOrg", 10);
		CommonMethod.RobustclickElementVisible("SelectOwnerOrg", "PortfolioSpaceType");
		testlog.info("And User select PrimarlyLocated");
		rc.ValidateSpaceTypeDropdown("PortfolioSpaceTypeCount", "28");
		CommonMethod.RobustclickElementVisible("PortfolioSpaceType", "PortfolioSelectSpaceType");
		CommonMethod.RobustclickElementVisible("PortfolioSelectSpaceType", "PortfolioOwnerCountry");
		testlog.info("And User select SpaceType");
		if(Country.equalsIgnoreCase("MainlandChina")) {
			CommonMethod.selectdropdownValue("PortfolioOwnerCountry", "CN");
			testlog.info("And User select OwnerCountry");	
		} else {
			CommonMethod.selectdropdownValue("PortfolioOwnerCountry", Country);
			testlog.info("And User select OwnerCountry");	
		}
		data.setCellData(SheetName, "Country", rowNum, CommonMethod.getSelectedDropdownValue("PortfolioOwnerCountry"));
		testlog.info("PortfolioOwnerCountry: " + data.getCellData(SheetName, "Country", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioOwnerState", 0);
		if(Country.equalsIgnoreCase("CN")) {
		CommonMethod.selectdropdownVisibletext("PortfolioOwnerState","Macao");
		}
		else if(Country.equalsIgnoreCase("MainlandChina")) {
		CommonMethod.selectdropdownVisibletext("PortfolioOwnerState","Beijing");
		}
		else {
			CommonMethod.selectdropdownrandom("PortfolioOwnerState");
		}
		testlog.info("And User select OwnerState");
		data.setCellData(SheetName, "State", rowNum, CommonMethod.getSelectedDropdownValue("PortfolioOwnerState"));
		testlog.info("PortfolioOwnerState: " + data.getCellData(SheetName, "State", rowNum));
		String ProjectAddress1 = USfaker.address().streetAddress();
		String ProjectCity = USfaker.address().cityName();
		String PostalCode = USfaker.address().zipCode();
		CommonMethod.sendKeys("PortfolioOwnerStreetAddress", ProjectAddress1);
		testlog.info("And User enter data to OwnerStreet Address");
		data.setCellData(SheetName, "Street", rowNum, CommonMethod.getattributeValue("PortfolioOwnerStreetAddress"));
		testlog.info("PortfolioOwnerStreetAddress: " + data.getCellData(SheetName, "Street", rowNum));
		CommonMethod.sendKeys("PortfolioOwnerCity", ProjectCity);
		testlog.info("And User enter data to City");
		data.setCellData(SheetName, "City", rowNum, CommonMethod.getattributeValue("PortfolioOwnerCity"));
		testlog.info("PortfolioOwnerCity: " + data.getCellData(SheetName, "City", rowNum));
		CommonMethod.sendKeys("PortfolioOwnerPostalCode", PostalCode);
		testlog.info("And User enter data to PostalCode");
		data.setCellData(SheetName, "PostalCode", rowNum, CommonMethod.getattributeValue("PortfolioOwnerPostalCode"));
		testlog.info("PortfolioOwnerPostalCode: " + data.getCellData(SheetName, "PostalCode", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCreateAccountSubmit", 0);
		CommonMethod.RobustclickElementVisible("PortfolioCreateAccountSubmit", "WellV2DashboardTab");
		testlog.info("And User clicks on Submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		
		testlog.info("Then User will be redirected to Dashboard page");
		String getId = CommonMethod.getText("StoreId");
		String[] stringArray = getId.split(": ");
		String getProjectId = stringArray[1].trim();
		data.setCellData(SheetName, "ProjectID", rowNum, getProjectId);
		testlog.info("And User store Registered id  in excel");
		testlog.pass("**Verifies the Registration successful**");

	}

	public void SearchPortfolioById(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WellAtScaleNavBar");
		CommonMethod.RobustclickElementVisible("WellAtScaleNavBar", "SearchByID");
		testlog.info("When User clicks on WELL AtScale from top menu under Projects");
		testlog.info("Portfolio Name:" + data.getCellData(SheetName, "AccountName", rowNum));
		testlog.info("Portfolio ID:" + data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.verifyDropdownValues("PortfolioAccountStatusList", "PortfolioAccountStatus");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchByID", 0);
		CommonMethod.RobustclickElementVisible("SearchByID", "PortfolioCreateAccountSubmit");
		CommonMethod.WaitUntilClickble("SearchByID", 60)
				.sendKeys(data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.sendKeyEnter("SearchByID");
		testlog.info("And User enter data to PortfolioID");
		CommonMethod.RobustclickElementVisible("PortfolioCreateAccountSubmit", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		int var = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var), "1", "Portfolio Search failed");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("SearchResultIDVerify"),
				data.getCellData(SheetName, "ProjectID", rowNum),
				"Portfolio ID doesn't matched with exceles in search");
		testlog.info("Then User verifies PortfolioID in search filter");
		CommonMethod.RobustclickElementVisible("SearchResultIDVerify", "WellV2DashboardTab");
		testlog.info("And User clicks on PortfolioID");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.info("And User will be redirected to Dashboard page");
		testlog.pass("**Verifies the Search Portfolio Name successfully**");
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	public void SignAgreementPortfolio(String SheetName, int rowNum) throws IOException, InterruptedException {
		String projectId = data.getCellData(SheetName, "ProjectID", rowNum);
		String[] Id = projectId.split("WELLP");
		System.out.println("Id: " + Id[1]);
		String header = portfolio.PostRequestAuthenticate("UserName", 3);
		testlog.info("Given User set POST service api endpoint");
		testlog.info("When User set Request header and Payload");
		testlog.info("And User Send a POST HTTP request");
		testlog.info("Header Token: " + header);
		@SuppressWarnings("rawtypes")
		HashMap pathprms = new HashMap();
		pathprms.put("portfolio_id", Id[1]);
		Response res = given().log().all().accept("application/json").contentType("application/json")
				.header("Authorization", header).pathParams(pathprms).when()
				.post("portfolio/{portfolio_id}/aggrementSign");
		int StatusCode = res.getStatusCode();
		Assert.assertEquals(StatusCode, 200, "Verifying status code");
		testlog.info("Then User verifies response body");
		testlog.info("And User verifies Status Code");
		testlog.info("And User Complete Sign project agreement");
		testlog.pass("**Verifies project agreement sign successfully**");
		CommonMethod.refreshBrowser();
	}

	public void SubscribePortfolio(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubscribeTab", 0);
		CommonMethod.RobustclickElementVisible("SubscribeTab", "PortfolioAccountValidName");
		testlog.info("When User clicks on SubscribeTab");
		if (rowNum == 2) {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioAccountNameValid"),
					data.getCellData(SheetName, "AccountName", rowNum), "Register AccountName Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("OrgName"),
					data.getCellData(SheetName, "OrgName", rowNum), "OrgName Error Mismatch");
			Thread.sleep(3000);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("StreetWithId"),
					data.getCellData(SheetName, "Street", rowNum), "Register Street Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("CityWithId"),
					data.getCellData(SheetName, "City", rowNum), "Register City Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PostalWithId"),
					data.getCellData(SheetName, "PostalCode", rowNum), "Register PostalCode Error Mismatch");
			CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioBillingCheckboxValid");
			testlog.info("And User verifies the added details");
		}
		String OwnerName = USfaker.address().firstName();
		String OwnerEmail = USfaker.internet().emailAddress();
		String OwnerPhone = USfaker.number().digits(10);
		CommonMethod.RobustclickElementVisible("PortfolioSubcribeContinueButton", "PortfolioOwnerName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioOwnerName", 0);
		CommonMethod.scrolldowntoElement("PortfolioOwnerName");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Owner Name is required.", "Owner Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner Email is required.", "Owner Email Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner Phone is required.", "Owner Phone Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Consultant is required.", "Enterprise Error Mismatch");
		testlog.info(
				"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		CommonMethod.sendKeys("PortfolioOwnerName", OwnerName);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("PortfolioOwnerName"));
		testlog.info("OwnerName: " + data.getCellData(SheetName, "OwnerName", rowNum));
		CommonMethod.sendKeys("PortfolioOwnerEmail", OwnerEmail);
		data.setCellData(SheetName, "OwnerEmail", rowNum, CommonMethod.getattributeValue("PortfolioOwnerEmail"));
		testlog.info("OwnerEmail: " + data.getCellData(SheetName, "OwnerEmail", rowNum));
		CommonMethod.sendKeys("PortfolioOwnerPhone", OwnerPhone);
		data.setCellData(SheetName, "OwnerPhone", rowNum, CommonMethod.getattributeValue("PortfolioOwnerPhone"));
		testlog.info("And User enter data to Name, Email and Phone number fields");
		testlog.info("OwnerPhone: " + data.getCellData(SheetName, "OwnerPhone", rowNum));
		rc.SelectEnterpriseProviders(SheetName, rowNum);
		testlog.info("And User select the enterprise checkbox");
		testlog.info("And User checks the consultants checkbox");
		CommonMethod.scrolldowntoElement("PortfolioSubcribeContinueButton");
		CommonMethod.JavascriptClickElement("PortfolioSubcribeContinueButton");
		testlog.info("And User clicks on continue button");
		if (rowNum == 2) {
			CommonMethod.RobustclickElementVisible("PortfolioSubscribeBackButton", "PortfolioOwnerName");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioAccountNameValid"),
					data.getCellData(SheetName, "AccountName", rowNum), "AccountName Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("OrgName"),
					data.getCellData(SheetName, "OrgName", rowNum), "OrgName Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioOwnerName"),
					data.getCellData(SheetName, "OwnerName", rowNum), "OwnerName Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioOwnerEmail"),
					data.getCellData(SheetName, "OwnerEmail", rowNum), "OwnerEmail Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioOwnerPhone"),
					data.getCellData(SheetName, "OwnerPhone", rowNum), "OwnerPhone Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("StreetWithId"),
					data.getCellData(SheetName, "Street", rowNum), "Street Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("CityWithId"),
					data.getCellData(SheetName, "City", rowNum), "City Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PostalWithId"),
					data.getCellData(SheetName, "PostalCode", rowNum), "PostalCode Error Mismatch");
			CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioBillingCheckboxValid", "PortfolioBillingCheckboxValid");
			testlog.info("And User verifies the added details about you by clicking on back button");
			CommonMethod.verifyDropdownValues("OwnerOrgIndustry", "OrganizationIndustry");
			CommonMethod.verifyDropdownValues("WEROwnerCountry", "Country");
			CommonMethod.JavascriptClickElement("PortfolioSubcribeContinueButton");
			testlog.info("And User clicks on continue button");
			
		}
		if (rowNum != 6 && rowNum != 2) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEntityOtherRadio", 0);
		CommonMethod.ClickCheckbox("PortfolioEntityOtherRadio");
			testlog.info("And User checks the Question checkbox");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationDescriptionTextbox", 0);
			CommonMethod.sendKeys("PortfolioLocationDescriptionTextbox", "Test Description");
			testlog.info("And User enter data to Description field");
		}
		if (rowNum == 2) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEnterpriseLegalEntityRadio", 0);
			CommonMethod.ClickCheckbox("PortfolioEnterpriseLegalEntityRadio");
			testlog.info("And User checks the Enterprise Legal Entity Radio");
			AddLegalEntities("PortfolioEnterpriseAddMoreBtn", "PortfolioEnterpriseLegalEntityTextBoxThree", "Test Legal Entity Two");
		}
		data.setCellData(SheetName, "PostalCode", rowNum, CommonMethod.getattributeValue("PortfolioOwnerPostalCode"));
		testlog.info("PortfolioOwnerPostalCode: " + data.getCellData(SheetName, "PostalCode", rowNum));
		CommonMethod.scrolldowntoElement("PortfolioSubcribeContinueButton2");
		CommonMethod.JavascriptClickElement("PortfolioSubcribeContinueButton2");
		testlog.info("And User clicks on continue button");
		if (rowNum == 2) {
			CommonMethod.RobustclickElementVisible("PortfolioOrganizationalConfidentialityBackButton",
					"PortfolioEntityOtherRadio");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioGrossAreaSQFT"),
					data.getCellData(SheetName, "AreaSQFT", rowNum), "AreaSQFT Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioValidNumberOfLocation"),
					data.getCellData(SheetName, "EstimatedNumberOfLocation", rowNum), "Location Error Mismatch");
			testlog.info("And User verifies the added details about you by clicking on back button");
			CommonMethod.JavascriptClickElement("PortfolioSubcribeContinueButton2");
			testlog.info("And User clicks on continue button");
		}
		if(TestCaseName.equalsIgnoreCase("Portfolio_TC_22A_01_SubscribePortfolio")) {
		CommonMethod.RobustclickElementVisible("PortfolioSubscribeDone", "PortfolioGoToBilling");
		testlog.info("And User clicks on SubscribeDone button");
		CommonMethod.WaitUntilVisibility("PortfolioGoToBilling", 60);
		CommonMethod.Robustclick("PortfolioGoToBilling");	
		} else {
		CommonMethod.RobustclickElementVisible("PortfolioSubscribeDone", "PortfolioGoToBilling");
		testlog.info("And User clicks on SubscribeDone button");
		CommonMethod.WaitUntilVisibility("PortfolioGoToBilling", 60);
		CommonMethod.Robustclick("PortfolioGoToBilling");
		testlog.info("And User clicks on GoToBilling button");
		CommonMethod.WaitUntilVisibility("V2ProjectPreBillingPayNowButton", 20);
		CommonMethod.navigateBack();
		testlog.pass("**Verifies Subscribe Portfolio successfully**");
		}
	}

	public void PortfolioClickOnBilling() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilVisibility("BiilingTab", 60);
		CommonMethod.RobustclickElementVisible("BiilingTab", "V2ProjectPreBillingPayNowButton");
		testlog.info("When User clicks on BilingTab");
		CommonMethod.WaitUntilVisibility("V2ProjectPreBillingPayNowButton", 20);
		CommonMethod.RobustclickElementVisible("V2ProjectPreBillingPayNowButton", "BillingLanding");
		testlog.info("And User clicks on PayNow Button");
		CommonMethod.WaitUntilVisibility("BillingLanding", 120);
		testlog.info("Then User will be redirected to Billing Invoice page");
		testlog.pass("**Nagavited to Billing successfully**");
	}

	public void PortfolioBuildScorecard() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2Tab", 0);
		CommonMethod.RobustclickElementVisible("WellV2Tab", "ScorecardTab");
		testlog.info("When User clicks on WellV2Tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTab", 0);
		CommonMethod.JavascriptClickElement("ScorecardTab");
		testlog.info("And User clicks on ScorecardTab");
		if (CommonMethod.isElementsExist("PortfolioScorecardZeroPointButton", 60)) {
			Thread.sleep(2000);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardZeroPointButton", 0);
			CommonMethod.RobustclickElementVisible("PortfolioScorecardZeroPointButton",
					"PortfolioScorecardPopupButton");
			testlog.info("And User clicks on Finished Button");
			Thread.sleep(2000);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPopupButton", 0);
			CommonMethod.Robustclick("PortfolioScorecardPopupButton");
			testlog.info("And User clicks on Popup Button");
		}
		rc.ScorecardLoading();
		testlog.info("Then User will be redirected to ScoreCardPage page");
		testlog.pass("**Verfies Scorecard Page successfully**");
	}

	public void PursingSearch(String labelName) throws IOException, InterruptedException {
		testlog.info("And User is on Purse Search filter");
		if (labelName.equalsIgnoreCase("Pursuing")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSearchPurseLink",
					0);
			CommonMethod.Robustclick("PortfolioScoreCardVerificationSearchPurseLink");
		}
		if (labelName.equalsIgnoreCase("Achieved")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAchievedLink", 0);
			CommonMethod.Robustclick("PortfolioScoreCardAchievedLink");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardValidatePurseLabelInSearch", 0);
		List<WebElement> getLabels = CommonMethod.findElements("PortfolioScoreCardValidatePurseLabelInSearch");
		for (int i = 0; i < getLabels.size(); i++) {
			String actualLabel = getLabels.get(i).getText();
			actualLabel = actualLabel.replaceAll("\\s+", " ").trim();
			System.out.println(actualLabel);
			CommonMethod.negativesoftassertFieldValid(actualLabel.toLowerCase(), labelName.toLowerCase(),
					"Pursuing Label does not matched");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSearchTextbox", 0);
		CommonMethod.sendKeys("PortfolioScoreCardVerificationSearchTextbox", "Test location 01");
		Thread.sleep(3000);
		CommonMethod.sendKeyEnter("PortfolioScoreCardVerificationSearchTextbox");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("ScorecardFeatureLoader");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException(
				"PortfolioScoreCardValidatePurseLabelInSearch", 1);
		int var = CommonMethod.ElementSize("PortfolioScoreCardValidatePurseLabelInSearch");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var), "1",
				"Scorecard Assign location Pursuing Search filter Count Mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignPurseSearchFilterCloseIcon", 0);
		CommonMethod.Robustclick("AssignPurseSearchFilterCloseIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignPurseSearchFilterCloseIcon", 1);
		testlog.info("Then User verifies Purse Search filter");
		testlog.pass("**Verifies Purse Search filter successfully**");
	}

	public void clickDocument() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2Tab", 0);
		CommonMethod.RobustclickElementVisible("WellV2Tab", "DocumentLibraryTab");
		testlog.info("When User clicks on WellV2Tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentLibraryTab", 0);
		CommonMethod.RobustclickElementVisible("DocumentLibraryTab", "Uploadbutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
		testlog.info("When User clicks on DocumentTab");
		testlog.pass("**Navigate Document successfully**");
	}

	public void clickHSRDocument() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioHSRTab", 0);
		CommonMethod.RobustclickElementVisible("PortfolioHSRTab", "PortfolioHSROptInDocumentLibraryTab");
		testlog.info("When User clicks on PortfolioHSRTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioHSROptInDocumentLibraryTab", 0);
		CommonMethod.RobustclickElementVisible("PortfolioHSROptInDocumentLibraryTab", "Uploadbutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
		testlog.info("When User clicks on DocumentTab");
		testlog.pass("**Navigate Document successfully**");
	}

	public void clickWPRDocument() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PerformanceTab", 0);
		CommonMethod.RobustclickElementVisible("PerformanceTab", "PortfolioWPROptInDocumentLibraryTab");
		testlog.info("When User clicks on Portfolio PerformanceTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioWPROptInDocumentLibraryTab", 0);
		CommonMethod.RobustclickElementVisible("PortfolioWPROptInDocumentLibraryTab", "Uploadbutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
		testlog.info("When User clicks on DocumentTab");
		testlog.pass("**Navigate Document successfully**");
	}

	public void clickWERDocument() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EquityTab", 0);
		CommonMethod.RobustclickElementVisible("EquityTab", "PortfolioWEROptInDocumentLibraryTab");
		testlog.info("When User clicks on Portfolio EquityTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioWEROptInDocumentLibraryTab", 0);
		CommonMethod.RobustclickElementVisible("PortfolioWEROptInDocumentLibraryTab", "Uploadbutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
		testlog.info("When User clicks on DocumentTab");
		testlog.pass("**Navigate Document successfully**");
	}

	public void ValidatingLegalUploadDocument(String SheetName, int rowNum, String Commodity) throws Exception {
		testlog.info("Given User is on Document page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
		CommonMethod.JavascriptClickElement("Uploadbutton");
		testlog.info("When User click on Upload button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPortfolioDocTypeLegal", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectPortfolioDocTypeLegal", "PortfolioSelectdocumenttype");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSelectdocumenttype", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioSelectdocumenttype", "Signed certification agreement");
		testlog.info("And User select Document Type");
		CommonMethod.uploadFile("DocumentsUpload", LegalfileUpload, "UploadFileVerifyScorecard");
		testlog.info("And User Upload Document");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentTaskListUploadButton", 0);
		CommonMethod.Robustclick("PortfolioDocumentTaskListUploadButton");
		testlog.info("When User click on Submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
		testlog.info("Then User will be redirected to DocumentList page");
		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
		CommonMethod.scrolldowntoElement(PortfolioAndRatingLocAccDocumentTable);
		pathprms.put("Stage", "Legal");
		generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, false, false, false, true,
				false, false, false);
		testlog.info("And User verifies Document Upload Table");
		rc.VaidDeleteButton();
		testlog.pass("**Upload Legal Document successfully**");
	}

	public void ValidatingAuditUploadDocument(String SheetName, int rowNum, String Commodity, String ProjectType,
			String PartId, String VerificationMethod) throws Exception {
		CommonMethod.refreshBrowser();
		testlog.info("Given User is on Document page");
		CommonMethod.WaitUntilNumberOfElementToBePresent("Uploadbutton", 1);
		CommonMethod.RobustclickElementVisible("Uploadbutton",
				"V2ProjectPortfolioDocTypeFeatureAudit");
		testlog.info("When User click on Upload button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPortfolioDocTypeFeatureAudit", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectPortfolioDocTypeFeatureAudit", "V2ProjectPortfolioDocType");
		CommonMethod.selectdropdownValue("V2ProjectPortfolioDocType", "audit");
		testlog.info("And User select Document Type");
		if (ProjectType.equalsIgnoreCase("v2") || Commodity.equalsIgnoreCase("Ratings")) {
			CommonMethod.WaitUntilPresence("PortfolioSelectverificationMethod", 300);
			CommonMethod.selectdropdownVisibletext("PortfolioSelectverificationMethod", VerificationMethod);
		} else {
			CommonMethod.WaitUntilVisibility("OwnerOrgClick", 60);
			CommonMethod.RobustclickElementVisible("OwnerOrgClick", "PortfolioDocumentAuditVerficationMethod");
			CommonMethod.WaitUntilPresence("PortfolioDocumentAuditVerficationMethod", 30);
			CommonMethod.click("PortfolioDocumentAuditVerficationMethod");
		}
		CommonMethod.WaitUntilPresence("WPRSelectFeaturePart", 60);
		CommonMethod.scrolldowntoElement("V2ProjectPortfolioDocType");
		CommonMethod.selectdropdownVisibletext("WPRSelectFeaturePart", PartId);
		testlog.info("And User select FeaturePart");
		CommonMethod.scrolldowntoElement("WPRSelectFeaturePart");
		CommonMethod.WaitUntilPresence("PortfolioScoreCardVerificationAddPart", 60);
		CommonMethod.Robustclick("PortfolioScoreCardVerificationAddPart", "WPRSelectFeaturePart");
		generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, AuditfileUpload, false, false, false,
				false);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignLocCbx", 0);
		CommonMethod.Robustclick("PortfolioScoreCardVerificationAssignLocCbx", "PortfolioScorecardValidDisable");
		rc.ScorecardConfirmLocUploadSaveButton();
		testlog.info("When User click on Submit button");
		CommonMethod.WaitUntilPresence("PortfolioDocumentListLink", 120);
		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
		CommonMethod.WaitUntilPresence(PortfolioAndRatingLocAccDocumentTable, 120);
		testlog.info("And User verifies Document Upload Table");
		CommonMethod.scrolldowntoElement(PortfolioAndRatingLocAccDocumentTable);
		pathprms.put("Stage", "Audit");
		generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, false, false, false, true,
				false, false, false);
		testlog.pass("**Upload Audit Document successfully**");
	}

	public void ValidatingFeatureUploadDocument(String SheetName, int rowNum, String Commodity, String ProjectType,
			String PartId) throws Exception {
		CommonMethod.refreshBrowser();
		testlog.info("Given User is on Document page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
		CommonMethod.RobustclickElementVisible("Uploadbutton",
				"V2ProjectPortfolioDocTypeFeatureAudit");
		testlog.info("When User click on Upload button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPortfolioDocTypeFeatureAudit", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectPortfolioDocTypeFeatureAudit", "V2ProjectPortfolioDocType");
		testlog.info("When User click on Upload button");
		CommonMethod.selectdropdownValue("V2ProjectPortfolioDocType", "feature");
		testlog.info("And User select Document Type");
		if (ProjectType.equalsIgnoreCase("v2") || Commodity.equalsIgnoreCase("Ratings")) {
			CommonMethod.selectdropdownVisibletext("PortfolioSelectverificationMethod",
					"Policy and/or Operations Schedule");
			testlog.info("And User select VerificationMethod");
		}
		if (ProjectType.contains("pilot")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
			CommonMethod.RobustclickElementVisible("OwnerOrgClick", "PortfolioDocumentFatureV2PilotVerficationMethod");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentFatureV2PilotVerficationMethod",
					0);
			CommonMethod.click("PortfolioDocumentFatureV2PilotVerficationMethod");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRSelectFeaturePart", 0);
		CommonMethod.scrolldowntoElement("V2ProjectPortfolioDocType");
		CommonMethod.selectdropdownVisibletext("WPRSelectFeaturePart", PartId);
		testlog.info("And User select FeaturePart");
		if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
			CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
		}
		if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
			CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
		}
		CommonMethod.scrolldowntoElement("WPRSelectFeaturePart");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPart", 0);
		CommonMethod.Robustclick("PortfolioScoreCardVerificationAddPart", "WPRSelectFeaturePart");
		generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FeaturefileUpload, false, false, false,
				true);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignLocCbx", 0);
		if (TestCaseName.equalsIgnoreCase("Portfolio_TC_08_02_FeatureUploadDocumentInDocument")) {
			CommonMethod.sendKeys("PortfolioEditSearchLocation", "WELL at scale Test location 01");
			Thread.sleep(3000);
			CommonMethod.sendKeyEnter("PortfolioEditSearchLocation");
			Thread.sleep(2000);
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("AssignLocationTableTr", 1);
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
			int AssignLocTableTrSize = CommonMethod.ElementSize("AssignLocationTableTr");
			String TableTrSize = Integer.toString(AssignLocTableTrSize);
			CommonMethod.negativesoftassertFieldValidEquals(TableTrSize, "1",
					"TableTrSize in Document list doesn't match");
			testlog.info("And User verifies Search Location Name in Edit Location model");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
					"PortfolioScorecardAssignLocationSearchFilterCloseIcon", 0);
			CommonMethod.Robustclick("PortfolioScorecardAssignLocationSearchFilterCloseIcon");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignChildLocCbx", 0);
		CommonMethod.Robustclick("PortfolioScoreCardVerificationAssignChildLocCbx", "PortfolioScorecardValidDisable");
		rc.ScorecardConfirmLocUploadSaveButton();
		testlog.info("And User clicks on Submit button");
		CommonMethod.WaitUntilPresence("PortfolioDocumentListLink", 120);
		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
		CommonMethod.WaitUntilPresence(PortfolioAndRatingLocAccDocumentTable, 120);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
		CommonMethod.scrolldowntoElement(PortfolioAndRatingLocAccDocumentTable);
		pathprms.put("Stage", "Feature");
		generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, false, false, false, true,
				false, false, false);
		testlog.pass("**Upload Feature Document successfully**");

	}

	public void ValidatingAlternativeUploadDocument(String SheetName, int rowNum, String Commodity, String ProjectType,
			String PartId) throws Exception {
		CommonMethod.refreshBrowser();
		testlog.info("Given User is on Document page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
		CommonMethod.RobustclickElementVisible("Uploadbutton",
				"V2ProjectPortfolioDocTypeFeatureAudit");
		testlog.info("When User click on Upload button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPortfolioDocTypeFeatureAudit", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectPortfolioDocTypeFeatureAudit", "V2ProjectPortfolioDocType");
		testlog.info("When User click on Upload button");
		CommonMethod.selectdropdownValue("V2ProjectPortfolioDocType", "feature");
		testlog.info("And User select Document Type");
		if (ProjectType.equalsIgnoreCase("v2") || Commodity.equalsIgnoreCase("Ratings")) {
			CommonMethod.selectdropdownVisibletext("PortfolioSelectverificationMethod", "Alternative Strategy");
			testlog.info("And User select VerificationMethod");
		}
		if (ProjectType.contains("pilot")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
			CommonMethod.RobustclickElementVisible("OwnerOrgClick", "PortfolioDocumentFatureV2PilotVerficationMethod");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentFatureV2PilotVerficationMethod",
					0);
			CommonMethod.click("PortfolioDocumentFatureV2PilotVerficationMethod");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRSelectFeaturePart", 0);
		CommonMethod.scrolldowntoElement("V2ProjectPortfolioDocType");
		CommonMethod.selectdropdownVisibletext("WPRSelectFeaturePart", PartId);
		testlog.info("And User select FeaturePart");
		if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
			CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
		}
		if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
			CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
		}
		CommonMethod.scrolldowntoElement("WPRSelectFeaturePart");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPart", 0);
		CommonMethod.Robustclick("PortfolioScoreCardVerificationAddPart", "WPRSelectFeaturePart");
		generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FeaturefileUpload, false, false, false,
				true);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignLocCbx", 0);
		if (TestCaseName.equalsIgnoreCase("Portfolio_TC_08_02_FeatureUploadDocumentInDocument")) {
			CommonMethod.sendKeys("PortfolioEditSearchLocation", "WELL at scale Test location 01");
			Thread.sleep(3000);
			CommonMethod.sendKeyEnter("PortfolioEditSearchLocation");
			Thread.sleep(2000);
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("AssignLocationTableTr", 1);
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
			int AssignLocTableTrSize = CommonMethod.ElementSize("AssignLocationTableTr");
			String TableTrSize = Integer.toString(AssignLocTableTrSize);
			CommonMethod.negativesoftassertFieldValidEquals(TableTrSize, "1",
					"TableTrSize in Document list doesn't match");
			testlog.info("And User verifies Search Location Name in Edit Location model");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
					"PortfolioScorecardAssignLocationSearchFilterCloseIcon", 0);
			CommonMethod.Robustclick("PortfolioScorecardAssignLocationSearchFilterCloseIcon");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignChildLocCbx", 0);
		CommonMethod.Robustclick("PortfolioScoreCardVerificationAssignChildLocCbx", "PortfolioScorecardValidDisable");
		rc.ScorecardConfirmLocUploadSaveButton();
		testlog.info("And User clicks on Submit button");
		CommonMethod.WaitUntilPresence("PortfolioDocumentListLink", 120);
		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
		CommonMethod.WaitUntilPresence(PortfolioAndRatingLocAccDocumentTable, 120);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
		CommonMethod.scrolldowntoElement(PortfolioAndRatingLocAccDocumentTable);
		pathprms.put("Stage", "Feature");
		generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, false, false, false, true,
				false, false, false);
		testlog.pass("**Upload Feature Document successfully**");

	}

	public void SubmitReviewDocument(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "PortfolioReviewSubmitButton");
		testlog.info("When User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewSubmitButton", "PortfolioReviewTextbox");
		testlog.info("And User clicks on Submit Button");
		CommonMethod.RobustclickElementVisible("PortfolioReviewSubmitDocButton", "OwnerOrgClick");
		CommonMethod.negativesoftassertPageSource("Portfolio-review is required.", "Select the Review Error Mismatch");
		CommonMethod.negativesoftassertPageSource(
				"Please provide your comments below to notify the IWBI team is required.",
				"Comment Name Error Mismatch");
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
		CommonMethod.RobustclickElementVisible("OwnerOrgClick", "PortfolioSelectV2Review");
		CommonMethod.click("PortfolioSelectV2Review");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewTextbox", 0);
		CommonMethod.sendKeys("PortfolioReviewTextbox", "Submit Documentation for Year 1, Review Cycle #1");
		testlog.info("And User enter data to Review field");
		CommonMethod.RobustclickElementVisible("PortfolioReviewSubmitDocButton", "PortfolioReviewListStatus");
		testlog.info("And User clicks on Submit Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewListStatus", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioReviewListStatus"),
				"ROUND 1 REVIEW IN PROGRESS", "Review status In Review Table doesn't match");
		List<String> tabledata = CommonMethod.fetchTableData("PortfolioReviewTable");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(3), CommonMethod.ValidateDate(),
				"Submitted Date In Review Tabl Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(4), "-",
				"Estimate date In Review Table doesn't Mismatch");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(5), "",
				"Returned Date In Review Table doesn't Mismatch");
		testlog.info("Then User will be redirected to Review list page");
		testlog.pass("**Submitted Documentation for Year 1, Review Cycle #1 successfully**");
	}
	
	public void deleteVaidateInAuthUser(String Reviewbtn) throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
	CommonMethod.RobustclickElementVisible("ReviewTab", Reviewbtn);
	testlog.info("Given User clicks on Review tab");		
	CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioReviewSubmitButton");
	CommonMethod.negativeAssertElementNotPresentFalse("PortfolioReviewSubmitButton","PortfolioReviewSubmitButton is present");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(Reviewbtn, 0);
	CommonMethod.RobustclickElementVisible(Reviewbtn, "PortfolioReturnReview");
	testlog.info("then User clicks on view button");
	CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectPopUpDeleteBtn");
	CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectPopUpDeleteBtn","V2ProjectPopUpDeleteBtn is present");
	}
	
	public void deleteVaidateInTeamUser(String SheetName, int rowNum) throws IOException, InterruptedException {
	portfolio.clickOnTeamPortfolio(SheetName, rowNum);
	CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRTeamEditButton");
	 CommonMethod.negativeAssertElementNotPresentFalse("WPRTeamEditButton","Edit icon is visible");
	CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectDeleteIcon");
	 CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectDeleteIcon","Delete icon is visible");
}

	public void AdminCompleteReview(String SheetName, int rowNum) throws IOException, InterruptedException {
		/*
		 * Admin Review
		 */
		AdminSearch(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "PortfolioReviewListViewButton");
		testlog.info("And User clicks on Review tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewListViewButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewListViewButton", "PortfolioReturnReview");
		testlog.info("And User clicks on view button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReturnReview", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReturnReview", "DocumentsUpload");
		CommonMethod.uploadFile("DocumentsUpload", ImportReviewUpload,
				"UploadFileVerifyScorecard");
		testlog.info("And User clicks on Return button");
		testlog.info("And User Upload Review Document");
		CommonMethod.WaitUntilClickble("ReturnComment", 60).sendKeys("Preliminary Precertification Review");
		Thread.sleep(1000);
		CommonMethod.scrollDown();
		Thread.sleep(1000);
		testlog.info("And User select Review Date");
		CommonMethod.RobustclickElementVisible("ReviewReturnSubmit", "PortfolioReviewStatus");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewStatus", 0);
		testlog.info("Then User verifies Reviewed Status");
		testlog.pass("**Completed Reviewed Preliminary Precertification Review successfully**");
	}
	
	public void teamReviewUserAccess() throws IOException, InterruptedException {
		
	testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "PortfolioReviewSubmitButton");
		testlog.info("When User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewSubmitButton", "ValidReviewErrorMessage110Points");
		CommonMethod.WaitUntilNumberOfElementTextToBePresentWithException("ValidReviewErrorMessage110Points","Only portfolio administrators and team managers are allowed", 500);
		testlog.info("And User clicks on Submit Button");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ValidReviewErrorMessage110Points"),
				"Only portfolio administrators and team managers are allowed",
				"You don't have sufficient privileges to submit documentation Error message doesn't match");
		testlog.info("And User verifies Review You don't have sufficient privileges to submit documentation");
		CommonMethod.refreshBrowser();
}

	public void AdminCompleteReviewDocument(String SheetName, int rowNum) throws IOException, InterruptedException {
		/*
		 * Admin Review
		 */
		if (TestCaseName.equalsIgnoreCase("Portfolio_TC_09_09_CompleteRound1Review")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
			CommonMethod.RobustclickElementVisible("ReviewTab", "PortfolioReviewDownloadButton");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioBackToAllReview", 0);
			CommonMethod.RobustclickElementVisible("PortfolioBackToAllReview", "PortfolioReviewDownloadButton");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTableTrValid", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ReviewTableEstDateInVisible", 1);
		}
		List<String> tabledata = CommonMethod.fetchTableData("PortfolioReviewTable");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(3), CommonMethod.ValidateDate(),
				"Review Return Submit Date Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(4), CommonMethod.ValidateDate(),
				"Review Return Estimated Date Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(5), "", "Review Return Error Mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewListViewButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewListViewButton", "PortfolioReturnReview");
		testlog.info("Then User clicks on view button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReturnReview", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReturnReview", "DocumentsUpload");
		CommonMethod.uploadMultipleFile("DocumentsUpload", SampleJpgfile, SampleJpgfile,
				"MultipeUploadDeleteicon", 2, "MultipeUploadEnableButtonDeleteLink");
		testlog.info("And User clicks on Return button");
		testlog.info("And User Upload Review Document");
		CommonMethod.WaitUntilClickble("ReturnComment", 60).sendKeys("Preliminary Precertification Review");
		Thread.sleep(1000);
		CommonMethod.scrollDown();
		Thread.sleep(1000);
		testlog.info("And User select Review Date");
		CommonMethod.RobustclickElementVisible("ReviewReturnSubmit", "PortfolioReviewStatus");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewStatus", 0);
		testlog.info("Then User verifies Reviewed Status");
		testlog.pass("**Completed Reviewed Preliminary Precertification Review successfully**");
	}

	public void registerFieldValidationInEditTab(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AccountInformationTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccountInformationTab", 0);
		CommonMethod.RobustclickElementVisible("AccountInformationTab", "PortfolioEditAccountName");
		CommonMethod.WaitUntilVisibility("PortfolioEditAccountName", 30);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioEditAccountName"),
				data.getCellData(SheetName, "AccountName", rowNum), "Portfolio Project Name doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditApproximatelyLoc", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioEditApproximatelyLoc"),
				data.getCellData(SheetName, "Location", rowNum), "Portfolio Approximately Location doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditLocationSubscribing", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioEditLocationSubscribing"),
				data.getCellData(SheetName, "EstimatedNumberOfLocation", rowNum),
				"Portfolio Location Subscribing doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditAreaSqft", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValue("PortfolioEditAreaSqft").replace("sq ft", "").replace(",", "").trim(),
				data.getCellData(SheetName, "AreaSQFT", rowNum), "Portfolio AreaSqft doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OrgName", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("OrgName"),
				data.getCellData(SheetName, "OrgName", rowNum), "Portfolio Organization Name doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditOrganizationIndustry", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getSelectedDropdownValue("PortfolioEditOrganizationIndustry"),
				data.getCellData(SheetName, "OrgIndustry", rowNum), "Portfolio OrganizationIndustry doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CountryWithId", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("CountryWithId"),
				data.getCellData(SheetName, "Country", rowNum), "Portfolio Country doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("StateWithId", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("StateWithId"),
				data.getCellData(SheetName, "State", rowNum), "Portfolio State doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("StreetWithId", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("StreetWithId"),
				data.getCellData(SheetName, "Street", rowNum), "Portfolio StreetAddress doesn't match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("CityWithId"),
				data.getCellData(SheetName, "City", rowNum), "Portfolio City doesn't match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PostalWithId"),
				data.getCellData(SheetName, "PostalCode", rowNum), "Portfolio postal code matched with excel");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditOrganizationOverview", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioEditOrganizationOverview"),
				data.getCellData(SheetName, "Overview", rowNum), "Portfolio OrganizationOverview doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditOwnerName", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioEditOwnerName"),
				data.getCellData(SheetName, "OwnerName", rowNum), "Portfolio OwnerName doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditOwnerEmail", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioEditOwnerEmail"),
				data.getCellData(SheetName, "OwnerEmail", rowNum), "Portfolio OwnerEmail doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditOwnerPhone", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioEditOwnerPhone"),
				data.getCellData(SheetName, "OwnerPhone", rowNum), "Portfolio OwnerPhone doesn't match");
		testlog.info("Then User verifies the added Project and Owner details");
		testlog.pass("**Register Field Validation successfully**");
	}

	public void editAndValidateAccountInformationPortfolio(String SheetName, int rowNum, String ProjectType)
			throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AccountInformationTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccountInformationTab", 0);
		CommonMethod.RobustclickElementVisible("AccountInformationTab", "PortfolioGoal");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccountNameEdit", 0);
		CommonMethod.clearAndSendKey("PortfolioAccountNameEdit", data.getCellData(SheetName, "AccountName", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioGoal", 0);
		CommonMethod.sendKeys("PortfolioGoal", data.getCellData(SheetName, "Goals", rowNum));		
		CommonMethod.verifyDropdownValues("PortfolioEditOrganizationIndustry", "OrganizationIndustry");
		CommonMethod.verifyDropdownValues("UpdateInvoiceCountryBilling", "Country");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScope", 0);
		CommonMethod.sendKeys("PortfolioScope", data.getCellData(SheetName, "Scope", rowNum));
		CommonMethod.scrolldowntoElement("PortfolioGoal");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioGRESBRadio",1);
		CommonMethod.assertisElementPresentTrue("PortfolioGRESBRadio", "GRESB Radio is not visible ");
		CommonMethod.ClickCheckbox("PortfolioGRESBRadio");
		CommonMethod.scrolldowntoElement("PortfolioGRESBRadio");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioClientNameTextBox",1);
		CommonMethod.assertisElementPresentTrue("PortfolioClientNameTextBox", "Client Name text box is not visible ");
		CommonMethod.moveToElement("PortfolioClientNameTextBox");
		rc.RemoveSpaceAndValidate("PortfolioClientNameTooltip", "Enter the preferred short-form client name to be used in reports");		
		CommonMethod.clearAndSendKey("PortfolioClientNameTextBox", "Updated Client Name");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSaveButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioSaveButton", "WellV2DashboardTab");
		testlog.info("**Project Information data updated successfully**");
		/*
		 * Validate updated account information fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AccountInformationTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccountInformationTab", 0);
		CommonMethod.RobustclickElementVisible("AccountInformationTab", "PortfolioGoal");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioGoal", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioGoal"),
				data.getCellData(SheetName, "Goals", rowNum), "Goals data doesn't match");
		testlog.info("**Goals data updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScope", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioScope"),
				data.getCellData(SheetName, "Scope", rowNum), "Scope data doesn't match");
		testlog.pass("**Verifies Project Information data updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEnterpriseProviders", 0);
		if (ProjectType.contains("pilot")) {
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getSelectedDropdownValue("V2ProjectEnterpriseProviders"),
					"En3 Sustainability Solutions", "EnterpriseProvidersName Mismatch");
		} else {
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getSelectedDropdownValue("V2ProjectEnterpriseProviders"),
					data.getCellData(SheetName, "EnterpriseProvidersName", rowNum), "EnterpriseProvidersName Mismatch");
		}
		CommonMethod.scrolldowntoElement("PortfolioGoal");
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioGRESBRadio");
	}

	public void editAndValidateAdmin(String SheetName, int rowNum) throws Exception {
		AdminSearch(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAdminFieldsButton", "PortfolioCoachingContacts");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCoachingContacts", 0);
		testlog.info("Coaching Contacts: " + data.getCellData(SheetName, "CoachingContacts", rowNum));
		CommonMethod.selectdropdownVisibletext("PortfolioCoachingContacts",
				data.getCellData(SheetName, "CoachingContacts", rowNum));
		CommonMethod.verifyDropdownValues("PortfolioMySubscriptiontoWELL", "MySubscriptionWellIncludes");
		CommonMethod.verifyDropdownValues("PortfolioSubscriptionTermYears", "SubscriptionTermYears");
		CommonMethod.verifyDropdownValues("PortfolioLevelOfTechnicalSupport", "WellCoachingSupport");
		CommonMethod.verifyDropdownValues("PortfolioAccountCloseReason", "ProjectCloseReason");
		CommonMethod.clearAndSendKey("PortfolioChallengesNotes", data.getCellData(SheetName, "ChallengeNote", rowNum));
		CommonMethod.clearAndSendKey("PortfolioCommunicationNotes",
				data.getCellData(SheetName, "CommunicationNotes", rowNum));
		CommonMethod.uploadMultipleFile("DocumentsUpload", FeaturefileUpload, FeaturefileUpload,
				"MultipeUploadDeleteicon", 2, "MultipeUploadEnableButtonDeleteLink");
		CommonMethod.clearAndSendKey("PortfolioAccountNotes", data.getCellData(SheetName, "AccountNotes", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSaveButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioSaveButton", "WellV2DashboardTab");
		testlog.pass("**Admin data updated successfully**");
		/*
		 * Validate updated admin fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAdminFieldsButton", "PortfolioCoachingContacts");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCoachingContacts", 0);
		CommonMethod.negativesoftassertFieldValidEquals(
				CommonMethod.getSelectedDropdownValue("PortfolioCoachingContacts"),
				data.getCellData(SheetName, "CoachingContacts", rowNum), "Coaching contacts value doesn't match");
		testlog.info("**Coaching contacts updated successfully**");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getattributeValue("PortfolioChallengesNotes"),
				data.getCellData(SheetName, "ChallengeNote", rowNum), "Challenges notes value doesn't match");
		testlog.info("**Challenges notes value updated successfully**");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getattributeValue("PortfolioCommunicationNotes"),
				data.getCellData(SheetName, "CommunicationNotes", rowNum), "Communication notes value doesn't match");
		testlog.info("**Communication notes value updated successfully**");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getattributeValue("PortfolioAccountNotes"),
				data.getCellData(SheetName, "AccountNotes", rowNum), "Account notes value doesn't match");
		testlog.pass("**Verifies Admin fields updated successfully**");
	}

	public void teamPortfolio(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddMemberbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAddMemberbtn", "PortfolioEmailAddress");
		testlog.info("And User clicks on Add member button");
		String TeamEmail = data.getCellData(SheetName, "TeamMemberEmail", rowNum);
		System.out.println("TeamEmail: " + TeamEmail);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEmailAddress", 0);
		CommonMethod.sendKeys("PortfolioEmailAddress", TeamEmail);
		testlog.info("And User enter email id field");
		CommonMethod.verifyDropdownValues("PortfolioRole", "ProjectRole");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioRole", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioRole", "Acoustician");
		testlog.info("And User select the Role");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMembercbx", 0);
		CommonMethod.ClickCheckbox("V2ProjectMembercbx");
		testlog.info("And User checks the Member checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectInvitebtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectInvitebtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("TeamMemberDeleteButton", 1);
		CommonMethod.negativeAssertElementPresentTrue("TeamMemberDeleteButton", "Delete icon is not visible");
		testlog.info("And User clicks on Invite button");
		testlog.info("Then User will be redirected to Team list page");
		testlog.pass("**Created Team member successfully**");
	}

	public void deleteAddedTeamMemberPortfolio(String SheetName, int rowNum) throws IOException, InterruptedException {
		Thread.sleep(2000);
		CommonMethod.refreshBrowser();
		testlog.info("Given User is on Team model page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDeleteIcon", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDeleteIcon", "V2ProjectAddMemberbtn");
		testlog.info("When User clicks on Delete Icon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddMemberbtn", 0);
		testlog.info("Then User will be redirected to Team list page");
	}

	public void validateTeamsPortfolio(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WellAtScaleNavBar");
		CommonMethod.RobustclickElementVisible("WellAtScaleNavBar", "SearchByID");
		testlog.info("Portfolio Name:" + data.getCellData(SheetName, "AccountName", rowNum));
		testlog.info("Portfolio ID:" + data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.WaitUntilClickble("SearchByID", 60)
				.sendKeys(data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.RobustclickElementVisible("PortfolioCreateAccountSubmit", "SearchResultIDVerify");
		int var = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(var), "1", "Portfolio Search failed");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("SearchResultIDVerify"),
				data.getCellData(SheetName, "ProjectID", rowNum),
				"Portfolio ID doesn't matched with exceles in search");
		testlog.info("And User verifies user able to access the invited project");
		testlog.pass("**Verifies user able to access the invited project**");
	}

	public void clickOnTeamPortfolio(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TeamTab", 0);
		CommonMethod.RobustclickElementVisible("TeamTab", "V2ProjectAddMemberbtn");
	    testlog.info("When User clicks on TeamTab");
	}

	public void searchFilterScoreCard() throws IOException, InterruptedException {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardSearchBox", 0);
		CommonMethod.sendKeys("V2ProjectScoreCardSearchBox", "Meet Thresholds for Particulate Matter");
		testlog.info("When User enter data to SearchBox field");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScoreCardOptionValue"),
				"Meet Thresholds for Particulate Matter", "Search Feature name doesn't match");
		CommonMethod.negativesoftassertFieldValidEquals(
				Integer.toString(CommonMethod.ElementSize("PortfolioScoreCardOptionValue")), "1",
				"Search Feature Count doesn't match");
		testlog.info("Then User will be redirected to Scorecard page");
		testlog.info("And User verifies Feature Part Count");
		testlog.pass("**Verifies Scorecard Search filter successfully**");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
	}

	public void scorecardOptionFilterPortfolio(String Commodity) throws Exception {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardRefreshButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardRefreshButton", "V2ProjectScoreCardFilterButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "V2ProjectScorecardApplybutton");
		testlog.info("And User clicks on Filter button");
		generic.filterGeneric(Commodity,"PortfolioScorecardGresbFilter","PortfolioManagementFilter", "PortfolioScoreCardFeature","38","true");
		generic.filterGeneric(Commodity,"PortfolioScorecardSustainableFilter","PortfolioPovertyCheckboxFilter", "PortfolioScoreCardFeature","8","true");
		generic.filterGeneric(Commodity,"V2ProjectScorecardResponsiblePartyFilter","V2ProjectScorecardFacilityManagerFilter", "PortfolioScoreCardFeature","30","true");
		if (TestCaseName.equalsIgnoreCase("Portfolio_CTC_09_01_ValidateScoreOptionFilter")) {
			generic.filterGeneric(Commodity,"V2ProjectScorecardResponseFilter","V2ProjectScorecardYesFilter", "PortfolioScoreCardFeature","225","true");
			
		} else {
			generic.filterGeneric(Commodity,"V2ProjectScorecardResponseFilter","V2ProjectScorecardYesFilter", "PortfolioScoreCardFeature","5","true");
			
		}
		generic.filterGeneric(Commodity,"V2ProjectScorecardVerificationFilter","V2ProjectScorecardOnsitePhotographsFilter", "PortfolioScoreCardFeature","39","true");
		generic.filterGeneric(Commodity,"PortfolioScorecardDocumentScaleFilter","PortfolioIndividualFilter", "PortfolioScoreCardFeature","36","true");
		generic.filterGeneric(Commodity,"V2ProjectScorecardPartTypeFilter","V2ProjectScorecardPreconditionsFilter", "PortfolioScoreCardFeature","48","true");
		generic.filterGeneric(Commodity,"V2ProjectScorecardPrioritiesFilter","V2ProjectScorecardBuildingPerformanceFilter", "PortfolioScoreCardFeature","25","true");
		generic.filterGeneric(Commodity,"V2ProjectScorecardSpaceTypeFilter","V2ProjectScorecardCommercialKitchenSpacesFilter", "PortfolioScoreCardFeature","215","true");
		generic.filterGeneric(Commodity,"V2ProjectScorecardRatingsFilter","V2ProjectScorecardWELLHealthSafetyFilter", "PortfolioScoreCardFeature","27","true");
		generic.filterGeneric(Commodity,"V2ProjectScorecardStrategyTypeFilter","V2ProjectScorecardPerformanceFilter", "PortfolioScoreCardFeature","18","true");
		generic.filterGeneric(Commodity,"V2ProjectScorecardCrosswalkFilter","V2ProjectScorecardLEEDFilter", "PortfolioScoreCardFeature","21","true");
		testlog.pass("**All Scorecard filter options successfully**");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
	}

	public void SearchPortfolioBySubcribedStatus(String SheetName, int rowNum, String Status)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WellAtScaleNavBar");
		CommonMethod.RobustclickElementVisible("WellAtScaleNavBar", "SearchByID");
		testlog.info("When User clicks on WellAtScaleNavBar from top menu under Projects");
		testlog.info("Portfolio Name:" + data.getCellData(SheetName, "AccountName", rowNum));
		testlog.info("Portfolio ID:" + data.getCellData(SheetName, "ProjectID", rowNum));
		/*
		 * ProjectID
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchByID", 0);
		CommonMethod.clearAndSendKey("SearchByID", data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter data to PortfolioId");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCreateAccountSubmit", 0);
		CommonMethod.RobustclickElementVisible("PortfolioCreateAccountSubmit", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		CommonMethod.negativesoftassertFieldValidEquals(
				String.valueOf(CommonMethod.ElementSize("SearchResultIDVerify")), "1",
				"Portfolio Search failed");

		/*
		 * AccountName
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSearchByName", 0);
		CommonMethod.clearAndSendKey("PortfolioSearchByName", data.getCellData(SheetName, "AccountName", rowNum));
		testlog.info("And User enter data to PortfolioName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCreateAccountSubmit", 0);
		CommonMethod.RobustclickElementVisible("PortfolioCreateAccountSubmit", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		CommonMethod.negativesoftassertFieldValidEquals(
				String.valueOf(CommonMethod.ElementSize("SearchResultIDVerify")), "1",
				"Portfolio Search failed");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("PortfolioNameVerify"),
				data.getCellData(SheetName, "AccountName", rowNum),
				"Portfolio ID doesn't matched with exceles in search");
		/*
		 * OrgName
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioOrganizationList", 0);
		CommonMethod.clearAndSendKey("PortfolioOrganizationList", data.getCellData(SheetName, "OrgName", rowNum));
		testlog.info("And User enter data to OrgName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCreateAccountSubmit", 0);
		CommonMethod.RobustclickElementVisible("PortfolioCreateAccountSubmit", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("PortfolioOrgNameResultList"),
				data.getCellData(SheetName, "OrgName", rowNum), "Portfolio Search failed");
		/*
		 * Status
		 */
		if (Status.equalsIgnoreCase("SUBSCRIBED") || Status.equalsIgnoreCase("NOT SUBSCRIBED")) {
			CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("PortfolioStatusResultList"), Status,
					"Portfolio Search failed");
		}
		if (Status.equalsIgnoreCase("IN PROGRESS")) {
			CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("PortfolioSubInProgressResultList"),
					Status, "Portfolio Search failed");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		CommonMethod.negativesoftassertFieldValidEquals(
				String.valueOf(CommonMethod.ElementSize("SearchResultIDVerify")), "1",
				"Portfolio Search failed");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("SearchResultIDVerify"),
				data.getCellData(SheetName, "ProjectID", rowNum),
				"Portfolio ID doesn't matched with exceles in search");
		testlog.info("And User verifies the Portfolio ID in Search Filter");
		testlog.info("And User verifies the Portfolio Name in Search Filter");
		testlog.info("And User verifies the OrgName in Search Filter");
		testlog.info("And User verifies the Status in Search Filter");
		CommonMethod.RobustclickElementVisible("SearchResultIDVerify", "WellV2DashboardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.pass("**Verifies the Search Portfolio Name successfully**");
		
	}

	public void ValidDashboardPortfolioField(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		testlog.info("Then User will be redirected to Dashboard page");
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.WaitUntilPresence("PortfolioClickSignNow", 30);
		CommonMethod.WaitUntilPresence("SubscribeTab", 30);
		CommonMethod.WaitUntilPresence("ResourcesTab", 30);
		CommonMethod.WaitUntilPresence("BiilingTab", 30);
		CommonMethod.WaitUntilPresence("TeamTab", 30);
		CommonMethod.WaitUntilPresence("EditTab", 30);
		CommonMethod.assertisNotElementPresent("LocationsTab", "LocationsTab element is Present");
		CommonMethod.assertisNotElementPresent("WellV2Tab", "WellV2Tab element is Present");
		CommonMethod.assertisNotElementPresent("ReviewTab", "ReviewTab element is Present");
		CommonMethod.assertisNotElementPresent("HealthSafetyTab", "HealthSafetyTab element is Present");
		CommonMethod.assertisNotElementPresent("PerformanceTab", "PerformanceTab element is Present");
		CommonMethod.assertisNotElementPresent("EquityTab", "EquityTab element is Present");
		CommonMethod.assertisNotElementPresent("ProfileTab", "ProfileTab element is Present");
		CommonMethod.assertisNotElementPresent("AlternativesTab", "AlternativesTab element is Present");
		softAssert.assertAll();
		testlog.info("And User verifies Dashboard fields and SideBar Navigation tab");
		testlog.pass("**Verifies Dashboard fields and SideBar Navigation tab successfully **");
	}

	public void ValidDashboardEnrollPortfolioField(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		testlog.info("Then User will be redirected to Dashboard page");
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.WaitUntilPresence("ResourcesTab", 30);
		CommonMethod.WaitUntilPresence("BiilingTab", 30);
		CommonMethod.WaitUntilPresence("TeamTab", 30);
		CommonMethod.WaitUntilPresence("EditTab", 30);
		CommonMethod.assertisNotElementPresent("PortfolioClickSignNow", "LocationsTab element is Present");
		CommonMethod.assertisNotElementPresent("SubscribeTab", "LocationsTab element is Present");
		CommonMethod.assertisNotElementPresent("LocationsTab", "LocationsTab element is Present");
		CommonMethod.assertisNotElementPresent("WellV2Tab", "WellV2Tab element is Present");
		CommonMethod.assertisNotElementPresent("ReviewTab", "ReviewTab element is Present");
		CommonMethod.assertisNotElementPresent("HealthSafetyTab", "HealthSafetyTab element is Present");
		CommonMethod.assertisNotElementPresent("PerformanceTab", "PerformanceTab element is Present");
		CommonMethod.assertisNotElementPresent("EquityTab", "EquityTab element is Present");
		CommonMethod.assertisNotElementPresent("ProfileTab", "ProfileTab element is Present");
		CommonMethod.assertisNotElementPresent("AlternativesTab", "AlternativesTab element is Present");
		softAssert.assertAll();
		testlog.info("And User verifies Dashboard fields and SideBar Navigation tab");
		testlog.pass("**Verifies Dashboard fields and SideBar Navigation tab successfully **");
	}

	public void ValidDashboardAfterBillingPortfolioField(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		testlog.info("Then User will be redirected to Dashboard page");
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.WaitUntilPresence("ResourcesTab", 30);
		CommonMethod.WaitUntilPresence("BiilingTab", 30);
		CommonMethod.WaitUntilPresence("TeamTab", 30);
		CommonMethod.WaitUntilPresence("LocationsTab", 30);
		CommonMethod.WaitUntilPresence("EditTab", 30);
		CommonMethod.WaitUntilPresence("WellV2Tab", 60);
		CommonMethod.WaitUntilPresence("ReviewTab", 30);
		CommonMethod.WaitUntilPresence("HealthSafetyTab", 30);
		CommonMethod.WaitUntilPresence("PerformanceTab", 30);
		CommonMethod.WaitUntilPresence("EquityTab", 30);
		CommonMethod.WaitUntilPresence("ProfileTab", 30);
		CommonMethod.WaitUntilPresence("AlternativesTab", 30);
		CommonMethod.assertisNotElementPresent("PortfolioClickSignNow", "PortfolioClickSignNow element is Present");
		CommonMethod.assertisNotElementPresent("SubscribeTab", "SubscribeTab element is Present");
		softAssert.assertAll();
		testlog.info("And User verifies Dashboard fields and SideBar Navigation tab");
		testlog.pass("**Verifies Dashboard fields and SideBar Navigation tab successfully **");
	}

	public void ResorceCardValidation(String SheetName, int rowNum, String cardValue) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ResourcesTab", 0);
		CommonMethod.RobustclickElementVisible("ResourcesTab", "PromotionCardContainer");
		testlog.info("When User clicks on ResourcesTab");
		rc.ValidCardCount("PromotionCardContainer");
		testlog.info("Then User will be redirected to Resources page");
		testlog.info("And User verifies Card Count in Resources page");
		testlog.pass("**Verify card count successfully**");

	}

	public void uploadDocumentInFeature(int LastFeatureNumber, String Commodity) throws Exception {
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardFeatureName", 0);
		Feature = CommonMethod.findElements("PortfolioScoreCardFeatureName");
		Feature = Feature.subList(0, LastFeatureNumber);
		CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
		for (WebElement f : Feature) {
			String Creditname = f.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			CommonMethod.WaitUntilClickble(f, 180);
			CommonMethod.JavascriptClickElement(f);
			testlog.info("When Click on " + Creditname + " feature");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
			CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
					"PortfolioScoreCardAddOptionbutton");
			testlog.info("And User clicks on VerificationTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
			CommonMethod.JavascriptClickElement("PortfolioScoreCardAddOptionbutton");
			testlog.info("And User clicks on AddOption button");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddButton", 0);
			CommonMethod.Robustclick("PortfolioScoreCardAddButton");
			testlog.info("And User clicks on Add Button");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationCloseicon", 0);
			CommonMethod.Robustclick("PortfolioScoreCardVerificationCloseicon");
			testlog.info("And User clicks on Closeicon");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocbtn", 0);
			Thread.sleep(1000);
			generic.assignLocationGeneric(Commodity, false, false, true, false, false);
			testlog.info("And User clicks on Assign button");
			testlog.info("And User checks on Assign location");
			testlog.info("And User clicks on Save button");
			CommonMethod.scrolldowntoLast();
			Thread.sleep(2000);
			List<WebElement> UploadButton;
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
			UploadButton = CommonMethod.findElements("PortfolioScoreCardVerificationUploadbtn");
			for (WebElement uploadButton : UploadButton) {
				CommonMethod.JavascriptClickElement(uploadButton);
				CommonMethod.scrolldowntoLast();
				CommonMethod.uploadFile("DocumentsUpload", AuditfileUpload,
						"UploadFileVerifyScorecard");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadDocbtn",
						0);
				CommonMethod.Robustclick("PortfolioScoreCardVerificationUploadDocbtn",
						"PortfolioScoreCardVerificationAddNote");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentAddedToasterMessage", 0);
				String actualDocumentAddedToast = CommonMethod
						.getattributeValueByTextContent("DocumentAddedToasterMessage");
				CommonMethod.negativesoftassertFieldValid(actualDocumentAddedToast, "added!",
						"Toast Message: Document added! Doesn't matched ");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
					"PortfolioScorecardDocumentCompleteAllGreenCircleValid", 0);
			int GreenCircleValid = CommonMethod.ElementSize("PortfolioScorecardDocumentCompleteAllGreenCircleValid");
			testlog.info("And User clicks on Upload button");
			testlog.info("And User upload Scorecard Document");
			testlog.info("And User clicks on Save button");
			testlog.info("Then User verifies Document Upload toast message");
			testlog.info("And User verifies Upload Document Table");
			testlog.info("And User verifies Task Completed " + GreenCircleValid + " GreenCircleValid");
			CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
			CommonMethod.JavascriptClickElement(f);
		}
	}

	@SuppressWarnings("unchecked")
	public static String PostRequestAuthenticate(String colName, int rowNum) {
		/*
		 * Get Token by authentication
		 */
		String username = data.getCellData("Login", colName, rowNum);
		JSONObject param = new JSONObject();
		param.put("email", username);
		param.put("password", data.getCellData("Login", "Password", rowNum));
		System.out.println(param.toString());
		Response res = given().log().all().accept("application/json").contentType("application/json").body(param).when()
				.post("authenticate");
		System.out.println(res.asString());
		String admin_Header = (res.path("token")).toString();
		admin_Header = "Bearer " + admin_Header;
		return admin_Header;
	}

	public void ValidateAddedLocationFields(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLCertificationNavBar");
		CommonMethod.RobustclickElementVisible("WELLCertificationNavBar", "V2ProjectId");
		CommonMethod.WaitUntilClickble("V2ProjectId", 60);
		System.out.println("LocationProjectID:" + data.getCellData(SheetName, "LocationProjectID", rowNum));
		testlog.info("LocationProjectID:" + data.getCellData(SheetName, "LocationProjectID", rowNum));
		CommonMethod.sendKeys("V2ProjectId", data.getCellData(SheetName, "LocationProjectID", rowNum));
		CommonMethod.RobustclickElementVisible("V2ProjectApplybtn", "SearchResultIDVerify");
		int var = CommonMethod.WaitUntilNumberOfElementToBePresent("SearchResultIDVerify", 1).size();
		CommonMethod.assertExpectedContainsActual(String.valueOf(var), "1", "V2 Search failed");
		CommonMethod.assertcontainsmessage("SearchResultIDVerify",
				data.getCellData(SheetName, "LocationProjectID", rowNum), "Project name doesn't matches in search");
		CommonMethod.RobustclickElementVisible("SearchResultIDVerify", "V2ProjectStartBuilding");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartBuilding", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.Robustclick("EditTab", "OwnerInformationButton");

	}

	public void ValidateAdminAddedLocationFields(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLCertificationNavBar");
		CommonMethod.RobustclickElementVisible("AdminWELLCertificationNavBar", "AdminV2ProjectId");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardApplybutton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardApplybutton", "SearchResultIDVerify");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminV2ProjectId", 0);
		CommonMethod.sendKeys("AdminV2ProjectId", data.getCellData(SheetName, "LocationProjectID", rowNum));
		testlog.info("And User enter data to V2ProjectId field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardApplybutton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardApplybutton", "SearchResultIDVerify");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchResultIDVerify", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("SearchResultIDVerify"),
				data.getCellData(SheetName, "LocationProjectID", rowNum), "Project Id doesn't matches in search");
		testlog.info("And User verifies Search V2Project By Id");
		CommonMethod.RobustclickElementVisible("SearchResultIDVerify", "WellV2DashboardTab");
		testlog.info("When User clicks on V2ProjectId");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.info("Then User will be redirected to Dashboard page");
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.Robustclick("EditTab", "OwnerInformationButton");

	}

	public void ValidateEditProjectInformation(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdateProjectsName"),
				data.getCellData(SheetName, "LocationName", rowNum), "Update ProjectName Error Mismatch");
		if (TestNGTestName.contains("Main") || TestNGTestName.contains("WELL-At-Scale-V2-Pilot")) {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdateProjectsarea"),
					"30000", "Update Area Error Mismatch");
		} else {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdateProjectsarea"),
					data.getCellData(SheetName, "AreaSQFT", rowNum), "Update Area Error Mismatch");
		}
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstruction");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectEditpublicprojectyes");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccProjectNameDisable", 0);
		CommonMethod.assertisElementPresentTrue("PortfolioAccProjectNameDisable", "ProjectName is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioAccProjectAreaDisable", "Project Area is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioAccexistingDisableCheckbox",
				"existingDisableCheckbox is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioAccNonCsDisableCheckbox", "NonCsDisableCheckbox is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioAccCicsDisableCheckbox", "CicsDisableCheckbox is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioAccCsDisableCheckbox", "CsDisableCheckbox is enabled");
		testlog.pass("**Project goals data updated successfully**");
	}
	
	public void ValidateDisableProjectInformation() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectEditProjectNameDisabled",1);
		CommonMethod.negativeAssertElementPresentTrue("V2ProjectEditProjectNameDisabled","V2ProjectEditProjectNameDisabled is not present");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectEditProjectAreaDisabled",1);
		CommonMethod.negativeAssertElementPresentTrue("V2ProjectEditProjectAreaDisabled","V2ProjectEditProjectAreaDisabled is not present");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectAdminFieldsButton");
		 CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectAdminFieldsButton","V2ProjectAdminFieldsButton is visible");
	}

	public void ValidateEditOwnerInformation(String SheetName, int rowNum) throws IOException, InterruptedException {

		/*
		 * Validate updated owner information fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerInformationButton", 0);
		CommonMethod.RobustclickElementVisible("OwnerInformationButton", "V2ProjectOrganizationIndustry");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("OwnerOrgClick"),
				data.getCellData(SheetName, "OrgName", rowNum), "Updated OrgName Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesOwnername"),
				data.getCellData(SheetName, "OwnerName", rowNum), "Updated OwnerName Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesOwneremail"),
				data.getCellData(SheetName, "OwnerEmail", rowNum), "Updated OwnerEmail Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValue("V2ProjectUpdatesOrganizationIndustry"),
				data.getCellData(SheetName, "OrgIndustry", rowNum), "Updated OrgIndustry Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getSelectedDropdownValue("V2ProjectUpdatesCountryRegion"),
				data.getCellData(SheetName, "Country", rowNum), "Updated Country Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesStreetaddress"),
				data.getCellData(SheetName, "Street", rowNum), "Updated OwnerStreet Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesLocationCity"),
				data.getCellData(SheetName, "City", rowNum), "Updated OwnerCity Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesPostalCode"),
				data.getCellData(SheetName, "PostalCode", rowNum), "Updated OwnerPostalCode Error Mismatch");
		testlog.pass("**Verifies Information data updated successfully**");
	}

	public void ValidateEditAddress(String SheetName, int rowNum) throws IOException, InterruptedException {

		/*
		 * Validate updated address fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddressButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAddressButton", "V2ProjectUpdatesPACountryRegion");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getSelectedDropdownValue("V2ProjectEditProjectAddCountry"),
				data.getCellData(SheetName, "Country", rowNum), "Address Updated Country Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("HsrWprEditStreet"),
				data.getCellData(SheetName, "Street", rowNum), "Address Updated Street Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectEditProjectAddCity"),
				data.getCellData(SheetName, "City", rowNum), "Address Updated City Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectEditProjectAddPostalCode"),
				data.getCellData(SheetName, "PostalCode", rowNum), "Address Updated PostalCode Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValue("V2ProjectUpdatesBABillingOrganization"),
				data.getCellData(SheetName, "OrgName", rowNum), "Updated OrgName Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getSelectedDropdownValue("V2ProjectEditBillingAddCountry"),
				data.getCellData(SheetName, "Country", rowNum), "Billing Address Updated Country Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectEditBillingAddStreet"),
				data.getCellData(SheetName, "Street", rowNum), "Billing Address Updated Street Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectEditBillingAddCity"),
				data.getCellData(SheetName, "City", rowNum), "Billing Address OwnerCity Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectEditBillingAddPostalCode"),
				data.getCellData(SheetName, "PostalCode", rowNum), "Billing Address OwnerPostalCode Error Mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccEditProjectAddCountry", 0);
		CommonMethod.assertisElementPresentTrue("PortfolioAccEditProjectAddCountry", "Country is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioAccEditProjectAddState", "State is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioAccEditProjectAddStreet", "Street is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioAccEditProjectAddCity", "City is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioAccEditProjectAddPostalCode", "PostalCode is enabled");
		testlog.pass("**Verifies Address data updated successfully**");
	}

	public void ValidateEditAdmin(String SheetName, int rowNum)
			throws IOException, InterruptedException, ClientApiException {

		/*
		 * Validate updated admin fields
		 */
		login.AdminLogin();
		ValidateAdminAddedLocationFields(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAdminFieldsButton", "V2projectAdminBillingStatus");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectEditOpenRadioButton");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectEditTestProjectYesRadioButton");
		testlog.pass("**Verifies Admin data updated successfully**");
	}

	public void ValidateAddedLocationAdditionalFields(String SheetName, int rowNum)
			throws IOException, InterruptedException {

		/*
		 * Validate location Owner fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationOwnerButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationOwnerButton", "PortfolioLocationOwnerName");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioLocationOwnerName"),
				data.getCellData(SheetName, "OwnerName", rowNum), "Owner Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioLocationOwnerEmail"),
				data.getCellData(SheetName, "OwnerEmail", rowNum), "Owner Email Id Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioLocationOwnerOrg"),
				data.getCellData(SheetName, "OrgName", rowNum), "Owner Org Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getSelectedDropdownValue("PortfolioLocationOwnerCountry"),
				data.getCellData(SheetName, "Country", rowNum), "Owner Country Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioLocationOwnerState"),
				data.getCellData(SheetName, "State", rowNum), "Owner State Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioLocationOwnerStreet"),
				data.getCellData(SheetName, "Street", rowNum), "Owner Street Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioLocationOwnerCity"),
				data.getCellData(SheetName, "City", rowNum), "Owner City Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioLocationOwnerPostalCode"),
				data.getCellData(SheetName, "PostalCode", rowNum), "Owner Postal Code Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("OwnerOrgIndustry"),
				data.getCellData(SheetName, "OrgIndustry", rowNum), "Owner Org Industry Error Mismatch");
		testlog.pass("**Validate location Owner fields successfully**");
	}

	public void ValidateAddedLocationOwnerFields(String SheetName, int rowNum)
			throws IOException, InterruptedException {

		/*
		 * Validate location Owner fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationOwnerButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationOwnerButton", "OwnerOrgIndustry");
		CommonMethod.verifyDropdownValues("OwnerOrgIndustry", "OrganizationIndustry");
		CommonMethod.verifyDropdownValues("V2ProjectUpdatesBACountryRegion", "Country");
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstruction");
		testlog.pass("**Validate location Owner fields successfully**");
	}

	public void ValidateAddedLocationAdditionalField(String SheetName, int rowNum)
			throws IOException, InterruptedException {

		/*
		 * Validate location Additional fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAdditionalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAdditionalButton",
				"PortfolioLocationRadiobuttonContainOutdoorSpaces");
		CommonMethod.verifyDropdownValues("PortfolioLocCertificationLevelDropdown", "CertificationLevel");
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstruction");
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationCommercialDining");
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationRadiobuttonContainOutdoor");
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationRetailSpaces");
		testlog.pass("**Validate location Additinal fields successfully**");
	}

	public void AdminSearch(String SheetName, int rowNum) throws IOException, InterruptedException {
		/*
		 * Admin Review
		 */
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLAtScaleNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminWELLAtScaleNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminWELLAtScaleNavBar", "SearchByID");
		testlog.info("When User clicks on WELL AtScaleNavBar from top menu under Projects");
		CommonMethod.WaitUntilClickble("SearchByID", 60)
				.sendKeys(data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter data to PortfolioId");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("SearchResultIDVerify"),
				data.getCellData(SheetName, "ProjectID", rowNum), "Portfolio ID doesn't matched with excel in search");
		Thread.sleep(2000);
		CommonMethod.RobustclickElementVisible("SearchResultIDVerify", "ReviewTab");
		testlog.info("And User clicks on PortfolioAdminId");
		
	}
	
	public void EditlocationAndValidateDuplicateLocationNameErrorMessage(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on location tab");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
		CommonMethod.RobustclickElementVisible("Overviewtab", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresent("LocationListTable", 1);
		testlog.info("And User clicks on overview tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresent("LocationListTable", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Editlocationbutton", 0);
		CommonMethod.RobustclickElementVisible("Editlocationbutton", "VerifyEditlocationslider");
		Thread.sleep(3000);
		testlog.info("And User clicks on edit location button");
		EnterDuplicateLocationAndValidateErrorMsg(SheetName, rowNum, "WELL at scale Test location 01");
	}

	public void SingleEditlocation(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on location tab");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
		CommonMethod.RobustclickElementVisible("Overviewtab", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresent("LocationListTable", 1);
		testlog.info("And User clicks on overview tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresent("LocationListTable", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Editlocationbutton", 0);
		CommonMethod.RobustclickElementVisible("Editlocationbutton", "VerifyEditlocationslider");
		Thread.sleep(3000);
		testlog.info("And User clicks on edit location button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSpaceType", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioLocationSpaceType", "Hospitality");
		testlog.info("when User update space type as Hospitality spaces");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationOwnershipDisable", 0);
		CommonMethod.assertisElementPresentTrue("PortfolioLocationOwnershipDisable", "Ownership is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioLocationCountryDisable", "Country is enabled");
		CommonMethod.assertisElementPresentTrue("PortfolioLocationStateDisable", "State is enabled");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSpaceType", 0);
		CommonMethod.verifyDropdownValues("PortfolioLocationSpaceType", "SpaceType");
		CommonMethod.selectdropdownVisibletext("PortfolioLocationSpaceType", "Hospitality");
		testlog.info("when User update space type as Hospitality spaces");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAdditionalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAdditionalButton",
				"PortfolioLocCertificationLevelDropdown");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocCertificationLevelDropdown", 0);
		CommonMethod.selectdropdownIndex("PortfolioLocCertificationLevelDropdown", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateButton", 0);
		CommonMethod.Robustclick("UpdateButton", "PortfolioLocationSpaceType");
		testlog.info("then User clicks on update button");
		if (CommonMethod.isElementsExist("Toastermessage", 15)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("Toastermessage", 1);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("Closepanelicon", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTable", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Editlocationbutton", 0);
		CommonMethod.RobustclickElementVisible("Editlocationbutton", "VerifyEditlocationslider");
		Thread.sleep(3000);
		testlog.info("Then User again clicks on edit location button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSpaceType", 0);
		CommonMethod.WaitUntilNumberOfElementTextToBePresentWithException("PortfolioLocationSpaceType", "Hospitality",
				500);
		CommonMethod.verifySelectedDropdownText("PortfolioLocationSpaceType", "Hospitality",
				"Update Spacetype doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAdditionalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAdditionalButton",
				"PortfolioLocationRadiobuttonContainOutdoorSpaces");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioLocationConstructionYes");
		CommonMethod.negativeAssertElementNotPresentFalse("PortfolioLocationConstructionYes",
				"ConstructionYes radiobutton is not disabled");
		testlog.info("And User verify updated Space type");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Closepanelicon", 0);
		CommonMethod.Robustclick("Closepanelicon");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("Closepanelicon", 1);
		testlog.info("And User click on close panel");

	}

	public void EditMultipleLocation(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on location tab");
		/*
		 * Edit multiple location
		 */
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTable", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MultieditButton", 0);
		CommonMethod.RobustclickElementVisible("MultieditButton", "locationCheckbox");
		testlog.info("then User clicks Multiselect location button");
		List<WebElement> checkbox = CommonMethod.findElements("locationCheckbox");
		checkbox.get(0).click();
		Thread.sleep(2000);
		checkbox.get(1).click();
		testlog.info("And User select location 1 and location 2 checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Editselectedbutton", 0);
		CommonMethod.RobustclickElementVisible("Editselectedbutton", "Selectfielddropdown");
		testlog.info("And User clicks on Multiple edit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Selectfielddropdown", 0);
		CommonMethod.selectdropdownVisibletext("Selectfielddropdown", "Space Types");
		testlog.info("And User select field as Space type to update");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSpaceType", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioLocationSpaceType", "Hospitality");
		testlog.info("And User update Space type as For All Spaces");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateButton", 0);
		CommonMethod.JavascriptClickElement("UpdateButton");
		testlog.info("And User clicks on update button");
		if (CommonMethod.isElementsExist("Toastermessage", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("Toastermessage", 1);
		}
		testlog.info("And User verify toaster message");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTable", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Editlocationbutton", 0);
		CommonMethod.RobustclickElementVisible("Editlocationbutton", "VerifyEditlocationslider");
		Thread.sleep(3000);
		testlog.info("Then User again clicks on edit location button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSpaceType", 0);
		String actual_value = CommonMethod.getSelectedDropdownValue("PortfolioLocationSpaceType");
		CommonMethod.negativesoftassertFieldValid(actual_value, "Hospitality",
				"Multiple Updated for 1st table row mismatch");
		testlog.info("And User verify updated Space type");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Closepanelicon", 0);
		CommonMethod.Robustclick("Closepanelicon");
		testlog.info("And User click on close panel");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Editlocation2button", 0);
		CommonMethod.RobustclickElementVisible("Editlocation2button", "VerifyEditlocationslider");
		Thread.sleep(3000);
		testlog.info("then User again clicks on edit location button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSpaceType", 0);
		String actual_value1 = CommonMethod.getSelectedDropdownValue("PortfolioLocationSpaceType");
		CommonMethod.negativesoftassertFieldValid(actual_value1, "Hospitality",
				"Multiple Updated for 2nd table row mismatch");
		testlog.info("And User verify updated Space type");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Closepanelicon", 0);
		CommonMethod.Robustclick("Closepanelicon");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("Closepanelicon", 1);
		testlog.info("And User click on close panel");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationLanding", 0);
		CommonMethod.refreshBrowser();
		testlog.pass("And User verify multiple edit location successfully**");
	}

	public void locationFilters(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresent("LocationListTable", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FilterButton", 0);
		testlog.info("Given User is on LocationsTab");
		/*
		 * space type filter
		 */
		CommonMethod.JavascriptClickElement("FilterButton");
		testlog.info("when User click on Filter button");
		CommonMethod.RobustclickElementVisible("Warehousecheckbox", "Applybutton");
		testlog.info("And User select Warehouse checkbox ");
		CommonMethod.RobustclickElementVisible("Applybutton", "table");
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 2);
		WebElement table = CommonMethod.findElement("tablebody");
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 4);
		CommonMethod.negativesoftassertFieldValid(String.valueOf(rows), "4",
				"Space type Location filter doesn't match");
		testlog.info("And User verify space type filter ");
		CommonMethod.WaitUntilPresence("LocationListTableLoading", 60);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		/*
		 * Ownership type filter
		 */
		CommonMethod.RobustclickElementVisible("FilterButton", "OwnershipWellCore");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnershipWellCore", 0);
		testlog.info("then User again click on  Filter Button ");
		CommonMethod.WaitUntilPresence("OwnershipWellCore", 60);
		CommonMethod.RobustclickElementVisible("OwnershipWellCore", "Applybutton");
		testlog.info("And User select OwnershipWellCore checkbox");
		CommonMethod.WaitUntilPresence("Applybutton", 30);
		CommonMethod.RobustclickElementVisible("Applybutton", "LocationListTable");
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilPresence("AddButton", 200);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 2);
		WebElement tableBod = CommonMethod.findElement("tablebody");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 3);
		List<WebElement> alRows = tableBod.findElements(By.tagName("tr"));
		int trows = alRows.size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(trows), "3",
				"Ownership Location filter doesn't match");
		testlog.info("And User verify Ownership type filter ");
		CommonMethod.WaitUntilPresence("LocationListTableLoading", 60);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilPresence("LocationListTableLoading", 60);
		/*
		 * Country filter
		 */
		CommonMethod.JavascriptClickElement("FilterButton");
		testlog.info("Then User again click on  Filter Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationIndia", 0);
		CommonMethod.ClickCheckbox("LocationIndia");
		testlog.info("And User select Location India checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Applybutton", 0);
		CommonMethod.RobustclickElementVisible("Applybutton", "table");
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresent("LocationListTable", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 2);
		CommonMethod.WaitUntilVisibility("tablebody", 60);
		WebElement tableBody = CommonMethod.findElement("tablebody");
		List<WebElement> totalRows = tableBody.findElements(By.tagName("tr"));
		int totrows = totalRows.size();
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 4);
		CommonMethod.negativesoftassertFieldValid(String.valueOf(totrows), "4",
				"Table Rows for Location filter doesn't match");
		testlog.info("And User verify Location filter");
		CommonMethod.WaitUntilPresence("LocationListTableLoading", 60);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		/*
		 * Owner Industry filter
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FilterButton", 0);
		CommonMethod.JavascriptClickElement("FilterButton");
		testlog.info("then User again click on  Filter Button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("IndustryRetirementcheckbox", 0);
		CommonMethod.RobustclickElementVisible("IndustryRetirementcheckbox", "Applybutton");
		testlog.info("And User select Industry Retirement checkbox ");
		CommonMethod.RobustclickElementVisible("Applybutton", "table");
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresent("LocationListTable", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("tablebody", 0);
		WebElement tablebody = CommonMethod.findElement("tablebody");
		List<WebElement> totalrows = tablebody.findElements(By.tagName("tr"));
		int Crows = totalrows.size();
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 5);
		CommonMethod.negativesoftassertFieldValid(String.valueOf(Crows), "5",
				"Table rows for Owner Industry filter doesn't match");
		testlog.info("And User verify Location filter ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		/*
		 * Multiple filters apply
		 * 
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FilterButton", 0);
		CommonMethod.JavascriptClickElement("FilterButton");
		testlog.info("then User again click on  Filter Button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Warehousecheckbox", 0);
		CommonMethod.ClickCheckbox("Warehousecheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnershipWellCore", 0);
		CommonMethod.ClickCheckbox("OwnershipWellCore");
		testlog.info("And User select Warehouse and OwnershipWellCore checkbox ");
		CommonMethod.RobustclickElementVisible("Applybutton", "table");
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresent("LocationListTable", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("tablebody", 0);
		WebElement tableB = CommonMethod.findElement("tablebody");
		List<WebElement> totalRs = tableB.findElements(By.tagName("tr"));
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 2);
		int CRows = totalRs.size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(CRows), "2",
				"Table rows Multiple filters apply doesn't match");
		testlog.info("And User verify Location filter ");
		testlog.info("And User verify Reset filter");
		testlog.info("And User verify single filter successfully**");
		testlog.info("And User verify multiple filter successfully**");
		testlog.pass("And User verify locations filter successfully**");
	}

	public void VerifySingleUpdateOccupancy(String SheetName, int rowNum) throws IOException, InterruptedException {

		testlog.info("Given User is on LocationsTab");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTable", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresent("OccupancyIcon", 5);
		CommonMethod.negativesoftassertFieldValidEquals(Integer.toString(CommonMethod.ElementSize("OccupancyIcon")),
				"5", "Occupancy Icon Count In Location table doesn't match");
		testlog.info("Then User verify pending occupnacy count"
				+ Integer.toString(CommonMethod.ElementSize("OccupancyIcon")));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OccupancyIcon1", 0);
		CommonMethod.RobustclickElementVisible("OccupancyIcon1", "Occupancytextfield");
		testlog.info("When User clicks on first occupancy icon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Occupancytextfield", 0);
		CommonMethod.IsElementPresentTrue("locationCheckbox");
		CommonMethod.clearAndSendKey("Occupancytextfield", "25");
		testlog.info("when User enter occupancy");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ConfirmButton", 0);
		CommonMethod.JavascriptClickElement("ConfirmButton");
		testlog.info("And User clicks on confirm button");
		if (CommonMethod.isElementsExist("UpdateOccupancyToasterMessageCommon", 30)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("UpdateOccupancyToasterMessageCommon", 1);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresent("OccupancyIcon", 4);
		List<WebElement> occuIconsafterupdate = CommonMethod.findElements("OccupancyIcon");
		int sizeafterupdate = occuIconsafterupdate.size();
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(sizeafterupdate), "4",
				"After updating Occupancy Icon Count In Location table doesn't match");
		testlog.info("Then User verify the remining occupancy count");
	}

	public void VerifyMultipleUpdateOccupancy(String SheetName, int rowNum) throws IOException, InterruptedException {

		testlog.info("Given User is on LocationsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTable", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OccupancyIcon1", 0);
		CommonMethod.RobustclickElementVisible("OccupancyIcon1", "Occupancytextfield");
		testlog.info("And User again clicks on first occupancy icon");
		CommonMethod.WaitUntilVisibility("Occupancytextfield", 60);
		CommonMethod.IsElementPresentTrue("locationCheckbox");
		CommonMethod.clearAndSendKey("Occupancytextfield", "15");
		testlog.info("when User enter occupancy for all");
		CommonMethod.ClickCheckbox("locationCheckbox");
		testlog.info("And User clicks on checkbox to update all");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ConfirmButton", 0);
		CommonMethod.JavascriptClickElement("ConfirmButton");
		testlog.info("And User clicks on confirm button");
		if (CommonMethod.isElementsExist("UpdateOccupancyToasterMessageCommon", 30)) {
			CommonMethod.WaitUntilInVisibility("UpdateOccupancyToasterMessageCommon", 60);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTable", 0);
		if (CommonMethod.isElementsExist("OccupancyIcon", 30)) {
			CommonMethod.WaitUntilInVisibility("OccupancyIcon", 60);
		}
		CommonMethod.assertisElementPresentFalse("OccupancyIcon", "Occupancy icon still present!!!");
		testlog.info("And User verify occupancy update for all the location");
		testlog.pass("And User verify update occupancy successfully**");
	}

	public void VerifyLocationUpdatesRestricts(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on LocationsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTable", 0);
		// ImportIcon
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationsImportButton", 0);
		CommonMethod.moveToElement("PortfolioLocationsImportButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioImportRestrictUpdateToastMsg", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioImportRestrictUpdateToastMsg"),
				"Locations cannot be updated while a review cycle is in progress",
				"Import Locations cannot be updated toaster message Error Mismatch");
		// single Loc
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.RobustclickElementVisible("AddButton", "AddLocationButton");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddLocationButton", 0);
		CommonMethod.moveToElement("AddLocationButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAddNewRestrictUpdateToastMsg", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioAddNewRestrictUpdateToastMsg"),
				"Locations cannot be updated while a review cycle is in progress",
				"single Locations cannot be updated toaster message Error Mismatch");

		// EditLocationIcon
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditLocationIconRestrict", 0);
		CommonMethod.moveToElement("PortfolioEditLocationIconRestrict");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditLocationIconRestrictToastMsg", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEditLocationIconRestrictToastMsg"),
				"Locations cannot be updated while a review cycle is in progress",
				"Edit Locations cannot be updated toaster message Error Mismatch");
	}

	public void HSRPortfolio(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
		CommonMethod.RobustclickElementVisible("HealthSafetyTab", "ConfirmTheEnrollment");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ConfirmTheEnrollment", 0);
		CommonMethod.RobustclickElementVisible("ConfirmTheEnrollment",
				"PortfolioHSRScorecardTabcustomizescorecardheading");
		testlog.pass("**Verifies the Purse Yes/No Scorecard Performance successfully**");
		ScorecardfillHSRWPRPortfolio(5, 6, 8, 3);
	}

	public void ScorecardfillHSRWPRPortfolio(int YesEnd, int NoStart, int NoEnd, int DifferencePlusOne)
			throws IOException, InterruptedException {
		List<WebElement> YesButton;
		List<WebElement> NoButton;
		Boolean flag = false;
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseYes", 0);
		YesButton = CommonMethod.findElements("CommonScorecardPurseYes");
		for (int i = 1; i <= YesEnd; i++) {
			long startTime = System.currentTimeMillis();
			int RemainingYes = YesButton.size();
			do {
				if (!((System.currentTimeMillis() - startTime) < 60000)) {
					flag = true;
					System.out.println("Build Stuck");
					break;
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseYes", 0);
				CommonMethod.JavascriptClickElement("CommonScorecardPurseYes");
				if (CommonMethod.isElementsExist("WPRCloseIcon", 5)) {
					CommonMethod.WaitUntilVisibility("WPRCloseIcon", 120);
					CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRCloseIcon", 1);
				}
				YesButton = CommonMethod.findElements("CommonScorecardPurseYes");
				Thread.sleep(2000);
			} while ((YesButton.size() == RemainingYes));
			RemainingYes--;
			if (flag) {
				System.out.println("Build Exit");
				break;
			}
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseNo", 0);
		NoButton = CommonMethod.findElements("CommonScorecardPurseNo");
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
				CommonMethod.JavascriptClickElement(NoButton.get(RemainingNo - j));
				if (CommonMethod.isElementsExist("WPRCloseIcon", 5)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRCloseIcon", 1);
				}
				NoButton = CommonMethod.findElements("CommonScorecardPurseNo");
				Thread.sleep(2000);
			} while ((NoButton.size() == RemainingNo));
			RemainingNo--;
			j--;
			if (flag) {
				System.out.println("Build Exit");
				break;
			}
		}
		CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
	}
	
	public void ValidateDuplicateLocationErrorMsg(String SheetName, int rowNum) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");
		testlog.info("When User clicks on LocationTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationPageValid", 0);
		CommonMethod.scrolldowntoElement("LocationPageValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.RobustclickElementVisible("AddButton", "AddLocationButton");
		testlog.info("And User clicks on Add Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddLocationButton", 0);
		CommonMethod.RobustclickElementVisible("AddLocationButton", "PortfolioLocationProjectName");
		testlog.info("And User clicks on Add Location Button");		
		EnterDuplicateLocationAndValidateErrorMsg(SheetName, rowNum, "WELL at scale Test location 05");
		CommonMethod.refreshBrowser();
	}
	
	public void EnterDuplicateLocationAndValidateErrorMsg(String SheetName, int rowNum, String EnterLocationName) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationProjectName", 0);
		CommonMethod.clearAndSendKey("PortfolioLocationProjectName", EnterLocationName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDuplicateLocationErrorMsg", 0);
		rc.RemoveSpaceAndValidate("PortfolioDuplicateLocationErrorMsg", "Duplicate Project Name");
		CommonMethod.clear("PortfolioLocationProjectName");
	}

	public void addLocation(String SheetName, int rowNum, String ProjectType, String Upload, String API)
			throws Exception {
		testlog.info("Given User is on Dashboard page");
		String LocationName = null;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");
		testlog.info("When User clicks on LocationTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationPageValid", 0);
		CommonMethod.scrolldowntoElement("LocationPageValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.RobustclickElementVisible("AddButton", "AddLocationButton");
		testlog.info("And User clicks on Add Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddLocationButton", 0);
		CommonMethod.RobustclickElementVisible("AddLocationButton", "PortfolioLocationProjectName");
		testlog.info("And User clicks on Add Location Button");		
		CommonMethod.RobustclickElementVisible("SubmitButton", "PortfolioLocationProjectName");
		testlog.info("And User clicks on Submit Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationProjectName", 0);
		CommonMethod.negativesoftassertPageSource("Project Name* is required.", "Project Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Project Area* is required.", "Project Area Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Space Type* is required.", "Space Type Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Ownership Type* is required.", "Ownership Type Error Mismatch");
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
		if (Upload.equalsIgnoreCase("true") && API.equalsIgnoreCase("true")) {
			if (ProjectType.contains("pilot")) {
				LocationName = "TEST W@S Location 19";
			}
		} else {
			LocationName = "Automation portfolio Location" + CommonMethod.randomNumber(8000000);
		}
		data.setCellData(SheetName, "LocationName", rowNum, LocationName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationProjectName", 0);
		CommonMethod.clearAndSendKey("PortfolioLocationProjectName", data.getCellData(SheetName, "LocationName", rowNum));
		testlog.info("And User enter data to ProjectName field");
		if (ProjectType.contains("pilot")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationProjectVersion", 0);
			int version = CommonMethod.getdropdownSize("PortfolioLocationProjectVersion");
			CommonMethod.negativesoftassertFieldValid(Integer.toString(version), "2",
					"ProjectVersion count doesn't match");
			if (TestCaseName.equalsIgnoreCase(
					"Portfolio_TC_02_01_AddingSingleLocBySelectingV2VersionAndValidateScorecardInAddedLocation")) {
				CommonMethod.selectdropdownVisibletext("PortfolioLocationProjectVersion", "WELL v2");
			} else {
				CommonMethod.selectdropdownVisibletext("PortfolioLocationProjectVersion", "WELL v2-Pilot");
			}
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationProjectVersion", 0);
			int version = CommonMethod.getdropdownSize("PortfolioLocationProjectVersion");
			CommonMethod.negativesoftassertFieldValid(Integer.toString(version), "1",
					"ProjectVersion count doesn't match");
			CommonMethod.selectdropdownVisibletextwithoutdelay("PortfolioLocationProjectVersion", "WELL v2");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationProjectArea", 0);
		testlog.info("And User select the ProjectVersion");
		if (TestNGTestName.contains("Main") || TestNGTestName.contains("WELL-At-Scale-V2-Pilot")) {
			CommonMethod.sendKeys("PortfolioLocationProjectArea", "30000");
		} else {
			CommonMethod.sendKeys("PortfolioLocationProjectArea", data.getCellData(SheetName, "AreaSQFT", rowNum));
		}
		testlog.info("And User enter data to Area field");
		CommonMethod.selectdropdownVisibletext("PortfolioLocationSpaceType", "Commercial Kitchen");
		testlog.info("And User select the SpaceType");
		data.setCellData(SheetName, "SpaceTypes", rowNum,
				CommonMethod.getSelectedDropdownValue("PortfolioLocationSpaceType"));
		testlog.info("Space type: " + data.getCellData(SheetName, "SpaceTypes", rowNum));
		CommonMethod.selectdropdownValue("PortfolioLocationOwnerType", "new-existing");
		testlog.info("And User select the OwnerType");
		data.setCellData(SheetName, "OwnerType", rowNum,
				CommonMethod.getSelectedDropdownValue("PortfolioLocationOwnerType"));
		testlog.info("Owner type: " + data.getCellData(SheetName, "OwnerType", rowNum));
		CommonMethod.negativesoftassertPageSource("Country is required.", "Country Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Street is required.", "Street Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("City is required.", "City Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Postal Code is required.", "Postal Code Name Error Mismatch");
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
		CommonMethod.verifyDropdownValues("LocationCountryName", "Country");
		CommonMethod.selectdropdownValue("LocationCountryName", "US");
		testlog.info("And User select the Country");
		CommonMethod.selectdropdownrandom("LocationStateName");
		testlog.info("And User select the State");
		CommonMethod.sendKeys("LocationStreetName", data.getCellData(SheetName, "Street", rowNum));
		testlog.info("And User enter data to Area field");
		CommonMethod.sendKeys("LocationCityName", data.getCellData(SheetName, "City", rowNum));
		CommonMethod.sendKeys("LocationPostalCode", data.getCellData(SheetName, "PostalCode", rowNum));
		if (SheetName.equalsIgnoreCase("Portfolio")) {
			portfolio.ValidateAddedLocationOwnerFields(SheetName, rowNum);
			portfolio.ValidateAddedLocationAdditionalField(SheetName, rowNum);
		}
		if (TestNGTestName.contains("WELL-At-Scale-Main")) {
		AddAndUpdateLegalEntitiesInLocationsTab();
		}
		testlog.info("And User enter data to Street, City, PostalCode field");
		CommonMethod.Robustclick("SubmitButton", "PortfolioLocationSpaceType");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioLocationSpaceType", 1);
		testlog.info("And User clicks on Submit button");
		CommonMethod.refreshBrowser();
		/*
		 * Validate location added successfully
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.JavascriptClickElement("LocationListTableLoading");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		List<String> val = CommonMethod.fetchTableData("LocationListTable");
		testlog.info("Fetching Data from Upload Table");
		String[] LocationNameValid = val.get(0).split(LocationName);
		data.setCellData(SheetName, "LocationProjectID", rowNum, LocationNameValid[1].trim());
		CommonMethod.negativesoftassertFieldValid(val.get(0), data.getCellData(SheetName, "LocationName", rowNum),
				"Location name doesn't match");
		System.out.println("LocationNameValid[1].trim(): " + LocationNameValid[1].trim());
		testlog.info("And User verifies the added details in Location list Table");
		testlog.pass("**Added single location successfully**");
	}

	public void getLocationVersionId() throws Exception {
		if (TestCaseName.equalsIgnoreCase("Portfolio_USTC_04_03_ValidateAddedLocationInLocationList")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("getLocationVersionId", 0);
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("getLocationVersionId").trim(), "WELL v2 pilot",
					"Location version doesn't match");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("getLocationVersionId", 0);
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("getLocationVersionId").trim(), "WELL v2",
					"Location version doesn't match");
		}
	}

	public void ValidateSearchLocation(String SheetName, int rowNum) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSearchLocation", 0);
		CommonMethod.sendKeys("PortfolioSearchLocation", data.getCellData(SheetName, "LocationProjectID", rowNum));
		CommonMethod.sendKeyEnter("PortfolioSearchLocation");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresent("PortfolioLocListTr", 1);
		int PortfolioLocListTr = CommonMethod.ElementSize("PortfolioLocListTr");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(PortfolioLocListTr), "1",
				"Location list count doesn't Mismatch");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
	}

	public void ValidateMilestoneSearchLocation(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on location tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Milestonetab", 0);
		CommonMethod.RobustclickElementVisible("Milestonetab", "table");
		testlog.info("Given User clicks on Milestone tab");
		ValidateSearchLocation(SheetName, rowNum);
	}

	public void ScorecardSurvey() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2Tab", 0);
		CommonMethod.RobustclickElementVisible("WellV2Tab", "ScorecardTab");
		testlog.info("When User clicks on WellV2Tab");
		CommonMethod.Robustclick("ScorecardTab");
		if (CommonMethod.isElementsExist("PortfolioSurveyBuildButton", 30)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSurveyBuildButton", 0);
			CommonMethod.RobustclickElementVisible("PortfolioSurveyBuildButton", "PortfolioSurveyPage");
			testlog.info("And User clicks on PortfolioSurveyBuildButton");
		}
		/*
		 * Category Design
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCategoryDesign", 0);
		CommonMethod.RobustclickElementVisible("PortfolioCategoryDesign", "PortfolioSurveyBackToAllCategoryBtn");
		testlog.info("And User clicks on Design Category");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSurveyQuestions", 0);
		List<WebElement> questions = CommonMethod.findElements("PortfolioSurveyQuestions");
		int quesNum = questions.size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(quesNum), "12",
				"Questions count for Design doesn't match!!");
		testlog.info("And User verifies Number of questions");
		CommonMethod.click(questions.get(1));
		testlog.info("And User clicks on Site selection question");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSurveySiteSelectionDesign", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSurveyDesignVerifyInitialScore", 0);
		testlog.info("And User clicks on Site selection question");
		List<WebElement> surveyques = CommonMethod.findElements("PortfolioSurveyVerifyQuestions");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(surveyques.size()), "9",
				"Questions count for Design doesn't match!!");
		testlog.info("And User verifies Number of question for Site selection");
		List<WebElement> YesnoOps = CommonMethod.findElements("PortfolioSurveyQuestionYesAndNo");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSurveyQuestionYesAndNo", 0);
		CommonMethod.click(YesnoOps.get(0));
		testlog.info("And User clicks Yes for first question");
		Thread.sleep(3000);
		CommonMethod.click(YesnoOps.get(4));
		Thread.sleep(3000);
		testlog.info("And User clicks No for second question");
		CommonMethod.click(YesnoOps.get(6));
		Thread.sleep(3000);
		testlog.info("And User clicks Yes for third question");
		CommonMethod.click(YesnoOps.get(8));
		testlog.info("And User clicks No for fourth question");
		Thread.sleep(3000);
		CommonMethod.click(YesnoOps.get(9));
		testlog.info("And User clicks Yes for fifth question");
		Thread.sleep(30000);
		CommonMethod.click(YesnoOps.get(12));
		testlog.info("And User clicks No for six question");
		CommonMethod.click(YesnoOps.get(15));
		testlog.info("And User clicks Yes for seven question");
		CommonMethod.click(YesnoOps.get(18));
		testlog.info("And User clicks No for eight question");
		CommonMethod.click(YesnoOps.get(21));
		testlog.info("And User clicks Yes for first question");
		CommonMethod.click(YesnoOps.get(24));
		CommonMethod.ScrollUpToElement("PortfolioSurveySiteSelectionDesign");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSurveyDesignVerifyfinalScore", 0);
		testlog.info("And User verified question score for site selection");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSurveySaveAndExitBtn", 0);
		CommonMethod.JavascriptClickElement("PortfolioSurveySaveAndExitBtn");
		testlog.info("And User clicks on SaveAndExit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCategoryDesign", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSurveyDesignPropotion", 0);
		testlog.info("And User verified design propotions successfully!!");

		/*
		 * Category Policy
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCategoryPolicy", 0);
		CommonMethod.RobustclickElementVisible("PortfolioCategoryPolicy", "PortfolioSurveyBackToAllCategoryBtn");
		testlog.info("And User clicks on Policy Category");
		List<WebElement> questions1 = CommonMethod.findElements("PortfolioSurveyQuestions");
		int quesNum1 = questions1.size();
		CommonMethod.negativesoftassertFieldValid(String.valueOf(quesNum1), "5",
				"Questions count for Policy doesn't match!!");
		testlog.info("And User verifies Number of questions");
		CommonMethod.click(questions1.get(4));
		testlog.info("And User clicks on Educational Resources question");
		CommonMethod.WaitUntilPresence("PortfolioSurveyEducationalResources", 60);
		CommonMethod.WaitUntilPresence("PortfolioSurveyPolicyVerifyInitialScore", 60);
		testlog.info("And User verified Initial Score for Educational Resources question");
		List<WebElement> surveyques1 = CommonMethod.findElements("PortfolioSurveyVerifyQuestions");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(surveyques1.size()), "8",
				"Questions count for Policy doesn't match!!");
		testlog.info("And User verifies Number of question for Educational Resource");
		List<WebElement> YesnoOps1 = CommonMethod.findElements("PortfolioSurveyQuestionYesAndNo");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSurveyQuestionYesAndNo", 0);
		CommonMethod.click(YesnoOps1.get(0));
		testlog.info("And User clicks Yes for first question");
		CommonMethod.click(YesnoOps1.get(3));
		testlog.info("And User clicks No for second question");
		CommonMethod.click(YesnoOps1.get(4));
		testlog.info("And User clicks Yes for third question");
		CommonMethod.click(YesnoOps1.get(7));
		testlog.info("And User clicks No for fourth question");
		CommonMethod.click(YesnoOps1.get(8));
		testlog.info("And User clicks Yes for fifth question");
		CommonMethod.click(YesnoOps1.get(11));
		testlog.info("And User clicks No for six question");
		CommonMethod.click(YesnoOps1.get(12));
		testlog.info("And User clicks Yes for seven question");
		CommonMethod.click(YesnoOps1.get(15));
		testlog.info("And User clicks No for first question");
		CommonMethod.click(YesnoOps1.get(18));
		CommonMethod.click(YesnoOps1.get(21));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSurveyPolicyVerifyfinalScore", 0);
		testlog.info("And User verified final score for Educational Resource");
		CommonMethod.ScrollUpToElement("PortfolioSurveySaveAndExitBtn");
		CommonMethod.WaitUntilClickble("PortfolioSurveySaveAndExitBtn", 60);
		CommonMethod.JavascriptClickElement("PortfolioSurveySaveAndExitBtn");
		testlog.info("And User clicks on SaveAndExit button");
		CommonMethod.WaitUntilPresence("PortfolioCategoryPolicy", 60);
		CommonMethod.WaitUntilPresence("PortfolioSurveyPolicyPropotion", 60);
		testlog.pass("And User verified Policy propotions successfully!!");
		CommonMethod.scrolldowntoElement("PortfolioSurveyFinishButton");
		CommonMethod.JavascriptClickElement("PortfolioSurveyFinishButton");
		testlog.info("And User clicks on finish button");
		CommonMethod.RobustclickElementVisible("PortfolioSurveyFinishPopup", "PortfolioSurveyDownloadButton");
		testlog.info("And User clicks on finish button on the popup");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSurveyManageScorecardBtn", 0);
		CommonMethod.Robustclick("PortfolioSurveyManageScorecardBtn");
		testlog.info("And User clicks on Manage scorecard button");
		rc.ScorecardLoading();
		testlog.info("Then User will be redirected to ScoreCardPage page");
		testlog.pass("**Scorecard survey completed**");

	}

	public void uploadDocumentInFeatureOpt(int LastFeatureNumber) throws IOException, InterruptedException {
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioHSRScorecardPartFeature", 0);
		Feature = CommonMethod.findElements("PortfolioHSRScorecardPartFeature");
		Feature = Feature.subList(0, LastFeatureNumber);
		for (WebElement f : Feature) {
			CommonMethod.WaitUntilClickble(f, 120);
			CommonMethod.JavascriptClickElement(f);
			CommonMethod.WaitUntilPresence("V2ProjectWPRPDocIcon", 120);
			CommonMethod.RobustclickElementVisible("V2ProjectWPRPDocIcon", "V2ProjectWPRVerificationMethod");
			CommonMethod.WaitUntilVisibility("PortfolioHSRScorecardDocumentType", 60);
			CommonMethod.selectdropdownValue("PortfolioHSRScorecardDocumentType", "feature");
			CommonMethod.ClickCheckbox("PortfolioHSRScorecardSingleLocation");
			CommonMethod.WaitUntilVisibility("V2ProjectWPRVerificationMethod", 60);
			CommonMethod.selectdropdownIndex("V2ProjectWPRVerificationMethod", 1);
			CommonMethod.uploadFile("V2ProjectDocUpload", FeaturefileUpload, "UploadFileVerifyScorecard");
			CommonMethod.WaitUntilVisibility("PortfolioScorecardUploadRemovelink", 60);
			CommonMethod.Robustclick("PortfolioHSRScorecardUploadButton");
			CommonMethod.WaitUntilPresence("V2ProjectWPRScorecardLanding", 60);
			CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
			CommonMethod.JavascriptClickElement(f);
		}
	}

	public void WPRPortfolioOpt(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PerformanceTab", 0);
		CommonMethod.RobustclickElementVisible("PerformanceTab", "ConfirmTheEnrollment");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ConfirmTheEnrollment", 0);
		CommonMethod.RobustclickElementVisible("ConfirmTheEnrollment",
				"PortfolioHSRScorecardTabcustomizescorecardheading");
		testlog.pass("**Verifies the 27 Purse Yes Scorecard Performance successfully**");
		ScorecardfillHSRWPRPortfolio(5, 6, 8, 3);
	}

	public void navigateWERPortfolioOpt() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EquityTab", 0);
		CommonMethod.RobustclickElementVisible("EquityTab", "ConfirmTheEnrollment");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ConfirmTheEnrollment", 0);
		CommonMethod.RobustclickElementVisible("ConfirmTheEnrollment",
				"PortfolioHSRScorecardTabcustomizescorecardheading");
	}

	public void WERPortfolioOpt(String SheetName, int rowNum) throws IOException, InterruptedException {
		navigateWERPortfolioOpt();
		testlog.pass("**Verifies the 27 Purse Yes Scorecard Performance successfully**");
		ScorecardfillHSRWPRPortfolio(5, 6, 8, 3);
	}

	public void clickOnReviewViewBtn() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "PortfolioReviewListViewButton");
		testlog.info("Given User clicks on Review tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewListViewButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewListViewButton", "PortfolioAdminReviewImportButton");
		testlog.info("then User clicks on view button");
	}

	public void AdminEditReviewMrc(String SheetName, int rowNum) throws IOException, InterruptedException {
		AdminSearch(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "PortfolioReviewListViewButton");
		testlog.info("Given User clicks on Review tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewListViewButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewListViewButton", "PortfolioReturnReview");
		testlog.info("then User clicks on view button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReturnReview", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEditReviewButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioEditReviewButton", "PortfolioUpdateButton");
		testlog.info("And User clicks on Edit Review button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioMidReviewRadio", 0);
		CommonMethod.ClickCheckbox("PortfolioMidReviewRadio");
		testlog.info("And User clicks on Need clarification radio button");
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioMidReviewRadio");
		testlog.info("And User verifies radio button is selected");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioMidReviewComment", 0);
		CommonMethod.sendKeys("PortfolioMidReviewComment", "MRC Test note for round 1 review");
		testlog.info("And User enters mrc comment");
		CommonMethod.RobustclickElementVisible("PortfolioAddEstDate", "V2ProjectDatePopupWeekday");
		CommonMethod.WaitUntilClickble("DatePickerOkButton", 30);
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "PortfolioUpdateButton");
		CommonMethod.WaitUntilClickble("PortfolioUpdateButton", 30);
		CommonMethod.RobustclickElementVisible("PortfolioUpdateButton", "PortfolioStatusRequireClr");
		testlog.info("And User clicks on update button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioStatusRequireClr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortoflioResubmitReviewBtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioMrcComment", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioMrcDocupload", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAddCommentBtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCommentDeleteicon", 0);
	}

	public void ReSubmitReview() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "PortfolioReviewListViewButton");
		testlog.info("Given User clicks on Review tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewListViewButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewListViewButton", "PortfolioReturnReview");
		testlog.info("then User clicks on view button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortoflioResubmitReviewBtn", 0);
		CommonMethod.RobustclickElementVisible("PortoflioResubmitReviewBtn", "PortfolioResubmitPopuptext");
		testlog.info("And User clicks on resubmit review button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioResubmitTextarea", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioResubmitSubmitbtn", 0);
		CommonMethod.sendKeys("PortfolioResubmitTextarea", "Test Resubmitting round 1 review");
		testlog.info("And User enters text in text area");
		CommonMethod.RobustclickElementVisible("PortfolioResubmitSubmitbtn", "PortfolioStatusInprogress");
		testlog.info("And User clicks on submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioStatusInprogress", 0);
		testlog.info("And User verifies status as Inprogress after resubmission");
		testlog.pass("And User verifies MRC functionality successfully***");
	}

	public void InitiateRound2Review(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "PortfolioReviewListViewButton");
		testlog.info("When User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTableTrValid", 0);
		List<String> tabledata = CommonMethod.fetchTableData("PortfolioReviewTable");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(3), CommonMethod.ValidateDate(),
				"Review Submitted Date Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(5), CommonMethod.ValidateDate(),
				"Review Returned Date Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(4), "-", "Review Date Error Mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioStatusRound1", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewListViewButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewListViewButton", "PortfolioInitiateRound2Button");
		testlog.info("And User clicks on view button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewStatus", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioInitiateRound2Button", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewAccetpFinalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioInitiateRound2Button", "PortfolioInitiateRound2Popup");
		testlog.info("then User clicks on initiate round 2 button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioInitiateRound2Popup", 0);
		CommonMethod.click("PortfolioSubmitRound2");
		testlog.info("And User clicks on submit button");
		CommonMethod.negativesoftassertPageSource(
				"Please provide your comments below to notify the IWBI team is required.",
				"Review comments Error Mismatch!!");
		testlog.info("And User verifies validation message for comment field");
		CommonMethod.sendKeys("PortfolioRound2Textarea", "Test Initiating round 2");
		testlog.info("then User enters text in the textfield");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubmitRound2", 0);
		CommonMethod.RobustclickElementVisible("PortfolioSubmitRound2", "PortfolioReview2InProgress");
		testlog.info("And User clicks on submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReview2InProgress", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioRound2SubDate", 0);
		testlog.info("And User verifies review status");
		testlog.pass("And User successfully initiated round 2 review");
	}

	public void AdminCompleteRound2Review(String SheetName, int rowNum) throws IOException, InterruptedException {
		AdminSearch(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "PortfolioReviewListViewButton");
		testlog.info("And User clicks on Review tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTableTrValid", 0);
		List<String> tabledata = CommonMethod.fetchTableData("PortfolioReviewTable");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(3), CommonMethod.ValidateDate(),
				"Round2 Review Submitted Date Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(4), "-", "Est Review Date Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(tabledata.get(5), "", "Return Review Date Error Mismatch");
		CommonMethod.WaitUntilVisibility("PortfolioReviewListViewButton", 60);
		CommonMethod.RobustclickElementVisible("PortfolioReviewListViewButton", "PortfolioReturnRound2Review");
		testlog.info("And User clicks on view button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReturnRound2Review", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReturnRound2Review", "DocumentsUpload");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentsUpload", 0);
		CommonMethod.uploadFile("DocumentsUpload", ImportReviewUpload, "UploadFileVerifyScorecard");
		testlog.info("And User clicks on Return button");
		testlog.info("And User Upload Review Document");
		CommonMethod.WaitUntilClickble("ReturnComment", 60).sendKeys("Returned round 2 review");
		Thread.sleep(1000);
		CommonMethod.scrollDown();
		Thread.sleep(1000);
		testlog.info("And User select Review Date");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewReturnSubmit", 0);
		CommonMethod.RobustclickElementVisible("ReviewReturnSubmit", "PortfolioRound2ReviewStatus");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioRound2ReviewStatus", 0);
		testlog.info("Then User verifies Reviewed Status");
		testlog.pass("**Completed Reviewed Round 2 Review successfully**");
	}

	public void PortfolioOptSubmitReviewDocument(String SheetName, int rowNum, String locator)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "PortfolioReviewSubmitButton");
		testlog.info("When User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewSubmitButton", "PortfolioReviewTextbox");
		testlog.info("And User clicks on Submit Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
		CommonMethod.RobustclickElementVisible("OwnerOrgClick", locator);
		CommonMethod.click(locator);
		CommonMethod.sendKeys("PortfolioReviewTextbox", "Submit Documentation for Year 1, Review Cycle #1");
		testlog.info("And User enter data to Review field");
		CommonMethod.RobustclickElementVisible("PortfolioReviewSubmitDocButton", "PortfolioReviewListStatus");
		testlog.info("And User clicks on Submit Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewListStatus", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("PortfolioReviewListStatus"), "Round 1 Review In Progress",
				"Verified Submitted Review list status");
		testlog.info("Then User will be redirected to Review list page");
		testlog.pass("**Submitted Documentation for Year 1, Review Cycle #1 successfully**");
	}

	public void AuthCompleteReviewDocument(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "PortfolioReviewListViewButton");
		testlog.info("When User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewListViewButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewListViewButton", "PortfolioInitiateReview");
		testlog.info("When User clicks on ReviewListView");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioInitiateReview", 0);
		CommonMethod.RobustclickElementVisible("PortfolioInitiateReview", "PortfolioOptscorecardIWBINote");
		testlog.info("When User clicks on Initiate Round 2 Review Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioOptscorecardIWBINote", 0);
		CommonMethod.sendKeys("PortfolioOptscorecardIWBINote", "Test");
		testlog.info("When User enter text in IWBI note input field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioOptscorecardSubmitButton", 0);
		CommonMethod.Robustclick("PortfolioOptscorecardSubmitButton", "PortfolioOptscorecardIWBINote");
		testlog.info("When User clicks on submit Button");
	}

	public void EditHsrDocForFeature(String SheetName, int rowNum, String Commodity, String FeatureName)
			throws Exception {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPFeature", 0);
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
				CommonMethod.RobustclickElementVisible("WPRVerficationTab", "PortfolioAndRatingLocAccDocumentTable");
				testlog.info("And User clicks on Verification tab");
				/*
				 * Edit Upload Document
				 */
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.scrolldowntoElement("Uploadbutton");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
				rc.documentTableEditButton();
				testlog.info("When User clicks on Edit button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreVerifyUploadVerificationMethod",
						0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardVerificationMethodSelectAddPart", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectScorecardVerificationMethodSelectAddPart","HSRScorecardSelectFeaturePart");
				testlog.info("And User clicks on Add feature part dropdown");
				CommonMethod.scrolldowntoElement("V2ProjectScorecardVerificationMethodSelectAddPart");
				testlog.info("And User scroll to the Add part button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecardSelectFeaturePart", 0);
				int dropdownsize = CommonMethod.getdropdownSize("HSRScorecardSelectFeaturePart");
				CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(dropdownsize), "19",
						"HSR Opt Scorecard Select FeaturePart Count doesn't match");
				testlog.info("Then User verifies the avaialble option to add");
				CommonMethod.selectdropdownVisibletext("HSRScorecardSelectFeaturePart", "SE3");
				testlog.info("And User select feature SE3");
				String dropdowntext = CommonMethod.getSelectedDropdownValue("HSRScorecardSelectFeaturePart");
				CommonMethod.negativesoftassertFieldValidEquals(dropdowntext, "SE3",
						"HSR Opt Scorecard Select FeaturePart doesn't match");
				testlog.info("then User verifies selected feature SE3");
				CommonMethod.WaitUntilVisibility("HSRScorecardSelectOption", 30);
				CommonMethod.selectdropdownIndex("HSRScorecardSelectOption", 1);
				testlog.info("And User select option");
				CommonMethod.RobustclickElementVisible("HSRScorecardAddPartButton", "HSRScorecardVerifyFeatureId");
				testlog.info("And User clicks on Add part button");
				Thread.sleep(2000);
				/** Valid FeatureName */
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecardVerifyFeatureName", 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("HSRScorecardVerifyFeatureName"),
						"Plan for Healthy Re-Entry", "Feature Name doesn't match");
				testlog.info("FeatureName: " + CommonMethod.getText("HSRScorecardVerifyFeatureName"));
				testlog.info("And User verifies FeatureName");

				/** Valid PartName */
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HSRScorecardVerifyFeatureId", 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("HSRScorecardVerifyFeatureId"), "SE3",
						"PartName doesn't match");
				testlog.info("PartName: " + CommonMethod.getText("PortfolioScorecardUploadPartName"));
				testlog.info("And User verifies PartName");
				rc.ScorecardUploadUpdateSaveButton();
				rc.ScorecardConfirmLocUploadSaveButton();
				testlog.info("And User clicks on update button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.scrolldowntoElement("Uploadbutton");
				pathprms.put("PartId", "SE3");
				generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, true, false, false,
						false, false, false, false);
				testlog.info("And User verifies updated feature in document table");
				testlog.pass("And User Validated the edit document functionality Successfully**");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void ValidateDateAddedColumnAndCurrentDate(String SheetName, int rowNum) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "PortfolioSubsetButton");
		CommonMethod.scrolldowntoElement("PortfolioSubsetButton");
		JavascriptExecutor scrollRight = (JavascriptExecutor) driver;
		scrollRight.executeScript("window.scrollBy(500,0)");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDateAddedColumnName", 0);
		String dateAddedColumnName = CommonMethod.getattributeValueByTextContent("PortfolioDateAddedColumnName");
		dateAddedColumnName = dateAddedColumnName.replaceAll("\\s+", " ").trim();
		CommonMethod.negativesoftassertFieldValid(dateAddedColumnName, "Date added",
				"Date added column name does not matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDateAddedColumnDate", 0);
		String actualCurrentDate = CommonMethod.getattributeValueByTextContent("PortfolioDateAddedColumnDate");
		actualCurrentDate = actualCurrentDate.replaceAll("\\s+", " ").trim();
		CommonMethod.negativesoftassertFieldValid(actualCurrentDate, CommonMethod.ValidateDate(),
				"Date added current date does not matched");

	}

	public void supportV2Project(String SheetName, int rowNum, String ProjectType)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		if (CommonMethod.isElementsExist("SupportLoading", 30)) {
			CommonMethod.WaitUntilInVisibility("SupportLoading", Scorecardtimeout);
		}
		CommonMethod.WaitUntilVisibility("V2ProjectSupportButton", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectSupportButton", "V2ProjectGetHelpButton");
		testlog.info("When User clicks on Support tab");
		CommonMethod.WaitUntilVisibility("V2ProjectGetHelpButton", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectGetHelpButton", "V2ProjectQuestionAboutDropdown");
		testlog.info("And User clicks on GetHelp Button");
		CommonMethod.WaitUntilPresence("SubmitButton", 120);
		CommonMethod.RobustclickElementVisible("SubmitButton", "V2ProjectQuestionAboutDropdown");
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
		CommonMethod.negativesoftassertPageSource("Question About is required.", "Question About Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Subject is required.", "Subject Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Description is required.", "Description Error Mismatch");
		CommonMethod.WaitUntilVisibility("V2ProjectQuestionAboutDropdown", 120);
		CommonMethod.selectdropdownValue("V2ProjectQuestionAboutDropdown", "well-v2-feature");
		testlog.info("And User select QuestionAbout");
		CommonMethod.WaitUntilVisibility("V2ProjectIssueSubTypeDropdown", 60);
		if (ProjectType.contains("pilot")) {
			CommonMethod.selectdropdownValue("V2ProjectIssueSubTypeDropdown", "A01 Fundamental Air Quality");
		} else {
			CommonMethod.selectdropdownValue("V2ProjectIssueSubTypeDropdown", "A01 Air Quality");
		}
		testlog.info("And User select IssueSubType");
		CommonMethod.WaitUntilClickble("OwnerOrgClick", 10);
		CommonMethod.RobustclickElementVisible("OwnerOrgClick", "OwnerOrg");
		CommonMethod.sendKeys("OwnerOrg", "R");
		CommonMethod.WaitUntilClickble("SelectOwnerOrgDyn", 10);
		CommonMethod.SelectRandomfromList("SelectOwnerOrgDyn", 1, 5).click();
		testlog.info("And User select IssueSubType");
		CommonMethod.WaitUntilClickble("V2ProjectSubjectInputField", 60).sendKeys("Testing");
		testlog.info("And User enter data Subject field");
		data.setCellData(SheetName, "Subject", rowNum, CommonMethod.getattributeValue("V2ProjectSubjectInputField"));
		testlog.info("FeatureName" + data.getCellData(SheetName, "Subject", rowNum));
		CommonMethod.WaitUntilClickble("V2ProjectDescriptionTextArea", 60).sendKeys("Testing");
		testlog.info("And User enter data Description field");
		CommonMethod.uploadMultipleFile("DocumentsUpload", FeaturefileUpload, FeaturefileUpload,
				"MultipeUploadDeleteicon", 2, "MultipeUploadEnableButtonDeleteLink");
		testlog.info("And User upload feature document");
		CommonMethod.WaitUntilVisibility("V2ProjectUploadFeatureVerify", 120);
		CommonMethod.WaitUntilVisibility("SubmitButton", 60);
		CommonMethod.RobustclickElementVisible("SubmitButton", "V2ProjectBackToTicket");
		testlog.info("And User clicks on Submit button");
		CommonMethod.RobustclickElementVisible("V2ProjectBackToTicket", "V2ProjectTicketListStatus");
		testlog.info("And User clicks on BackToTicket");
		CommonMethod.WaitUntilPresence("V2ProjectTicketListStatus", 180);
		String TicketStatus = CommonMethod.getText("V2ProjectTicketListStatus");
		testlog.info("TicketStatus: " + TicketStatus);
		CommonMethod.assertActualContainsExpected(TicketStatus, "NEW");
		testlog.info("Then User verifies New status");
		testlog.pass("**Raised support ticket successfully**");
	}

	public void PortfolioCreateSubset() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.JavascriptClickElement("LocationsTab");
		testlog.info("Given user is on the location page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationTableTr");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Subsettab", 0);
		CommonMethod.JavascriptClickElement("Subsettab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetButton", 0);
		CommonMethod.JavascriptClickElement("PortfolioSubsetButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetNameTextBox", 0);
		CommonMethod.sendKeys("PortfolioSubsetNameTextBox", "I am Subset Name Text Box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDescriptionTextBox", 0);
		CommonMethod.sendKeys("PortfolioDescriptionTextBox", "I am Optional Description Text Box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetSaveAndContinueBtnFirstPage", 0);
		CommonMethod.Robustclick("PortfolioSubsetSaveAndContinueBtnFirstPage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignLocCbx", 0);

	}

	public void PortfolioCreateSubsetFilter(String SheetName, int rowNum, String Commodity) throws Exception {
		// Validate WELL V2 Filter
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFiltersPilot", 0);
		CommonMethod.JavascriptClickElement("PortfolioScorecardFiltersPilot");
		CommonMethod.verifyDropdownValues("PortfolioSubsetWellVersionDropdown", "SubsetProjectVersion");
		CommonMethod.verifyDropdownValues("PortfolioScorecardAssignfilterOwnershipType", "OwnershipType");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetWellVersionDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioSubsetWellVersionDropdown", "WELL V2");
		SubsetApplyFilter();
		SubsetCloseModal();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetValidateAppliedFilter", 0);
		String actualAppliedFilterText = CommonMethod
				.getattributeValueByTextContent("PortfolioSubsetValidateAppliedFilter").trim();
		CommonMethod.negativesoftassertFieldValid(actualAppliedFilterText, "1 filters active",
				"1 filters active text does not matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetValidateWellV2FilterCount", 0);
		int WellV2Count = CommonMethod.findElements("PortfolioSubsetValidateWellV2FilterCount").size();
		String actualWellV2Count = Integer.toString(WellV2Count);
		CommonMethod.negativesoftassertFieldValidEquals(actualWellV2Count, "6", "6 location count does not matched");

		// Validate Countries Filter
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFiltersPilot", 0);
		CommonMethod.JavascriptClickElement("PortfolioScorecardFiltersPilot");
		SubsetClearFilter();
		pfu.validAssignLocationCountryFilter("Afghanistan");
		pfu.validAssignLocationFilter("1", "Country");

		// Validate SpaceType Filter
		pfu.validAssignLocationSpaceTypeFilter("Commercial Kitchen");
		pfu.validAssignLocationFilter("1", "SpaceType");

		// Validate OwnershipType Filter
		pfu.validAssignLocationOwnershipTypeFilter("Owner-occupied");
		pfu.validAssignLocationFilter("3", "OwnershipType");

		// Validate OccupancySizeRange Filter
		pfu.validSubsetOccupancySizeRangeFilter("10,000 - 49,999 ft²");
		pfu.validAssignLocationFilter("6", "OccupancySizeRange");

		// Validate ConstructionStatus Filter
		pfu.validAssignLocationConstructionStatusFilter("Not under construction/renovation");
		pfu.validAssignLocationFilter("6", "ConstructionStatus");

		// Validate LocationStatus Filter
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetLocationStatusDropdown", 0);
		CommonMethod.verifyDropdownValues("PortfolioSubsetLocationStatusDropdown", "FilterStatus");
		CommonMethod.selectdropdownVisibletext("PortfolioSubsetLocationStatusDropdown", "Not assigned");
		SubsetApplyFilter();
		pfu.validAssignLocationFilter("6", "LocationStatus");
		SubsetCloseModal();

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

		// Validate Subset Table Name
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableName", 0);
		String actualSubsetTableName = CommonMethod.getattributeValueByTextContent("PortfolioSubsetTableName").trim();
		CommonMethod.negativesoftassertFieldValid(actualSubsetTableName, "I am Subset Name Text Box",
				"I am Subset Name Text Box text does not matched");

		// Validate Subset Table Locations Count
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableLocationsCount", 0);
		String actualSubsetTableLocationsCount = CommonMethod
				.getattributeValueByTextContent("PortfolioSubsetTableLocationsCount").trim();
		CommonMethod.negativesoftassertFieldValid(actualSubsetTableLocationsCount, "1",
				"Table Locations Count does not matched");

		// Edit and Update Subset Table
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableEditIcon", 0);
		CommonMethod.Robustclick("PortfolioAndRatingLocAccDocumentTableEditIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetNameTextBox", 0);
		CommonMethod.clearAndSendKey("PortfolioSubsetNameTextBox", "I am Subset Name Text Box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDescriptionTextBox", 0);
		CommonMethod.clearAndSendKey("PortfolioDescriptionTextBox", "I am Updated Optional Description Text Box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetSaveAndContinueBtnFirstPage", 0);
		CommonMethod.Robustclick("PortfolioSubsetSaveAndContinueBtnFirstPage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectIndex2CheckBox", 0);
		CommonMethod.ClickCheckbox("SelectIndex2CheckBox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSaveAndExitBtn", 0);
		CommonMethod.Robustclick("PortfolioSaveAndExitBtn");

		// Validate Updated Subset Table Name
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableName", 0);
		String actualUpdatedSubsetTableName = CommonMethod.getattributeValueByTextContent("PortfolioSubsetTableName")
				.trim();
		CommonMethod.negativesoftassertFieldValid(actualUpdatedSubsetTableName, "I am Subset Name Text Box",
				"I am Subset Name Text Box text does not matched");

		// Validate Updated Subset Table Locations Count
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableLocationsCount", 0);
		Thread.sleep(2000);
		String actualUpdatedSubsetTableLocationsCount = CommonMethod
				.getattributeValueByTextContent("PortfolioSubsetTableLocationsCount").trim();
		CommonMethod.negativesoftassertFieldValid(actualUpdatedSubsetTableLocationsCount, "1",
				"Updated Subset Table Count does not matched ");
	}

	public void PortfolioDeleteSubset() throws Exception {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReplaceCrossIcon", 0);
		CommonMethod.Robustclick("ReplaceCrossIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ReplaceCrossIcon", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableDeleteIcon", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAndRatingLocAccDocumentTableDeleteIcon", "DeletePopUp");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DeletePopUp", 0);
		CommonMethod.Robustclick("DeletePopUp");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DeletePopUp", 1);
		CommonMethod
				.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioAndRatingLocAccDocumentTableDeleteIcon");
		CommonMethod.negativeAssertElementNotPresentFalse("PortfolioAndRatingLocAccDocumentTableDeleteIcon",
				"Subset delete icon is present");
		CommonMethod.refreshBrowser();
	}

	public void PortfolioCreateSubsetAchievementFilter() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFiltersPilot", 0);
		CommonMethod.JavascriptClickElement("PortfolioScorecardFiltersPilot");
		CommonMethod.scrolldowntoElement("PortfolioScorecardAssignfilterConstructionOption");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardAssignfilterAchievementsSelect", 0);
		CommonMethod.RobustclickElementVisible("PortfolioScorecardAssignfilterAchievementsSelect",
				"PortfolioScorecardAssignfilterSpaceTypeSelectHighlighter");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardAssignfilterAchievementsInput", 0);
		Thread.sleep(2000);
		CommonMethod.JavascriptClickElement("PortfolioScorecardAssignfilterAchievementsInput");
		Thread.sleep(2000);
		CommonMethod.sendKeysWithoutWait("PortfolioScorecardAssignfilterAchievementsInput", "WELL Health-Safety");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
				"PortfolioScorecardAssignfilterSpaceTypeSelectHighlighter", 0);
		CommonMethod.JavascriptClickElement("PortfolioScorecardAssignfilterSpaceTypeSelectHighlighter");
		SubsetApplyFilter();
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
		CommonMethod.ClickCheckbox("WPRAssignLocCbx");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSaveAndExitBtn", 0);
		CommonMethod.Robustclick("PortfolioSaveAndExitBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioSaveAndExitBtn", 1);
		// Validate Subset Table Name
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableName", 0);
		String actualSubsetTableName = CommonMethod.getattributeValueByTextContent("PortfolioSubsetTableName").trim();
		CommonMethod.negativesoftassertFieldValid(actualSubsetTableName, "I am Subset Name Text Box",
				"I am Subset Name Text Box text does not matched");
		// Validate Subset Table Locations Count
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableLocationsCount", 0);
		String actualSubsetTableLocationsCount = CommonMethod
				.getattributeValueByTextContent("PortfolioSubsetTableLocationsCount").trim();
		CommonMethod.negativesoftassertFieldValid(actualSubsetTableLocationsCount, "1",
				"Table Locations Count does not matched");
	}

	public void V2PilotCreateSubset(String SheetName, int rowNum, String Commodity) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.JavascriptClickElement("LocationsTab");
		testlog.info("Given user is on the location page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationTableTr");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Subsettab", 0);
		CommonMethod.JavascriptClickElement("Subsettab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetButton", 0);
		CommonMethod.JavascriptClickElement("PortfolioSubsetButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetAddNewButton", 0);
		CommonMethod.JavascriptClickElement("PortfolioSubsetAddNewButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetBtnAfterClickAddNewBtn", 0);
		CommonMethod.JavascriptClickElement("PortfolioSubsetBtnAfterClickAddNewBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetAddNewButton", 0);
		CommonMethod.JavascriptClickElement("PortfolioSubsetAddNewButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetNameTextBox", 0);
		CommonMethod.sendKeys("PortfolioSubsetNameTextBox", "I am Subset Name Text Box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDescriptionTextBox", 0);
		CommonMethod.sendKeys("PortfolioDescriptionTextBox", "I am Optional Description Text Box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetSaveAndContinueBtnFirstPage", 0);
		CommonMethod.Robustclick("PortfolioSubsetSaveAndContinueBtnFirstPage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFiltersPilot", 0);
		CommonMethod.JavascriptClickElement("PortfolioScorecardFiltersPilot");

		// Validate WELL V2-pilot Filter
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetWellVersionDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioSubsetWellVersionDropdown", "WELL V2-pilot");
		SubsetApplyFilter();
		SubsetCloseModal();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetValidateAppliedFilter", 0);
		String actualAppliedFilterText = CommonMethod
				.getattributeValueByTextContent("PortfolioSubsetValidateAppliedFilter").trim();
		CommonMethod.negativesoftassertFieldValid(actualAppliedFilterText, "1 filters active",
				"1 filters active text does not matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetValidateWellV2FilterCount", 0);
		int WellV2PilotCount = CommonMethod.findElements("PortfolioSubsetValidateWellV2FilterCount").size();
		String actualWellV2PilotCount = Integer.toString(WellV2PilotCount);
		CommonMethod.negativesoftassertFieldValidEquals(actualWellV2PilotCount, "6",
				"6 location count does not matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFiltersPilot", 0);
		CommonMethod.JavascriptClickElement("PortfolioScorecardFiltersPilot");
		SubsetClearFilter();

		// Validate Countries Filter
		pfu.validAssignLocationCountryFilter("Afghanistan");
		pfu.validAssignLocationFilter("1", "Country");

		// Validate SpaceType Filter
		pfu.validAssignLocationSpaceTypeFilter("Commercial Kitchen");
		pfu.validAssignLocationFilter("1", "SpaceType");

		// Validate OwnershipType Filter
		pfu.validAssignLocationOwnershipTypeFilter("Owner-occupied");
		pfu.validAssignLocationFilter("3", "OwnershipType");

		// Validate OccupancySizeRange Filter
		pfu.validSubsetOccupancySizeRangeFilter("10,000 - 49,999 ft²");
		pfu.validAssignLocationFilter("6", "OccupancySizeRange");

		// Validate ConstructionStatus Filter
		pfu.validAssignLocationConstructionStatusFilter("Not under construction/renovation");
		pfu.validAssignLocationFilter("6", "ConstructionStatus");

		// Validate LocationStatus Filter
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetLocationStatusDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioSubsetLocationStatusDropdown", "Not assigned");
		SubsetApplyFilter();
		pfu.validAssignLocationFilter("6", "Status");
		SubsetCloseModal();

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

		// Validate Subset Table Name
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableName", 0);
		String actualSubsetTableName = CommonMethod.getattributeValueByTextContent("PortfolioSubsetTableName").trim();
		CommonMethod.negativesoftassertFieldValid(actualSubsetTableName, "I am Subset Name Text Box",
				"I am Subset Name Text Box text does not matched");

		// Validate Subset Table Locations Count
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableLocationsCount", 0);
		String actualSubsetTableLocationsCount = CommonMethod
				.getattributeValueByTextContent("PortfolioSubsetTableLocationsCount").trim();
		CommonMethod.negativesoftassertFieldValid(actualSubsetTableLocationsCount, "1",
				"Table Locations Count does not matched");

		// Edit and Update Subset Table
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableEditIcon", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAndRatingLocAccDocumentTableEditIcon",
				"PortfolioSubsetNameTextBox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetNameTextBox", 0);
		CommonMethod.clearAndSendKey("PortfolioSubsetNameTextBox", "I am Subset Name Text Box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDescriptionTextBox", 0);
		CommonMethod.clearAndSendKey("PortfolioDescriptionTextBox", "I am Updated Optional Description Text Box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetSaveAndContinueBtnFirstPage", 0);
		CommonMethod.Robustclick("PortfolioSubsetSaveAndContinueBtnFirstPage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectIndex2CheckBox", 0);
		CommonMethod.ClickCheckbox("SelectIndex2CheckBox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSaveAndExitBtn", 0);
		CommonMethod.Robustclick("PortfolioSaveAndExitBtn");

		// Validate Updated Subset Table Name
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableName", 0);
		String actualUpdatedSubsetTableName = CommonMethod.getattributeValueByTextContent("PortfolioSubsetTableName")
				.trim();
		CommonMethod.negativesoftassertFieldValid(actualUpdatedSubsetTableName, "I am Subset Name Text Box",
				"I am Subset Name Text Box text does not matched");

		// Validate Updated Subset Table Locations Count
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetTableLocationsCount", 0);
		Thread.sleep(2000);
		String actualUpdatedSubsetTableLocationsCount = CommonMethod
				.getattributeValueByTextContent("PortfolioSubsetTableLocationsCount").trim();
		CommonMethod.negativesoftassertFieldValid(actualUpdatedSubsetTableLocationsCount, "1",
				"Table Locations Count does not matched ");
	}

	public void SubsetApplyFilter() throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Applybutton", 0);
		CommonMethod.JavascriptClickElement("Applybutton");
	}

	public void SubsetCloseModal() throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetCloseFilterModal", 0);
		CommonMethod.click("PortfolioSubsetCloseFilterModal");
	}

	public void SubsetClearFilter() throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetClearFilter", 0);
		CommonMethod.JavascriptClickElement("PortfolioSubsetClearFilter");
	}

	public void TeamPermissionLevel(String SheetName, int rowNum) throws IOException, InterruptedException {
		rc.clickOnTeamTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddMemberbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAddMemberbtn", "V2ProjectEmailAddress");
		testlog.info("And User clicks on Add member button");
		String TeamManagerEmail = data.getCellData(SheetName, "TeamManagerEmail", rowNum);
		String TeamMemberEmail = data.getCellData(SheetName, "TeamMemberEmail", rowNum);
		String PTFTEmail = data.getCellData(SheetName, "PTFTEmail", rowNum);
		String PTLTDEmail = data.getCellData(SheetName, "PTLTDEmail", rowNum);
		String[] EmailDetails = { TeamManagerEmail, TeamMemberEmail, PTFTEmail, PTLTDEmail };
		String[] PermissionsLevel = { "TeamManagerCommon", "TeamMemberCommon", "TeamFullAccessCommon",
				"TeamLimitedCommon" };
		for (int i = 0; i < PermissionsLevel.length; i++) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddMemberbtn", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectAddMemberbtn", "PortfolioEmailAddress");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEmailAddress", 0);
			CommonMethod.sendKeys("PortfolioEmailAddress", EmailDetails[i]);
			testlog.info("And User enter email id field");
			testlog.info("And User enter email id field");
			testlog.info("Team Email ID: " + EmailDetails[i]);
			CommonMethod.selectdropdownVisibletext("PortfolioRole", "Acoustician");
			testlog.info("And User select the Role");
			CommonMethod.ClickCheckbox(PermissionsLevel[i]);
			testlog.info("And User checks the Member checkbox");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectInvitebtn", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectInvitebtn", "V2ProjectAddMemberbtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddMemberbtn", 0);
			testlog.info("And User clicks on Invite button");
			testlog.info("Then User will be redirected to Team list page");
			testlog.pass("**Created Team member successfully**");
		}
	}

	public void PurseStatus() throws IOException, InterruptedException {
		// yes
		v2project.RefreshScorecard();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseYes", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseYes");
		rc.PurseStatusToasterMessage();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseYes1", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseYes1");
		rc.PurseStatusToasterMessage();
		// MayBe
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseMayBe", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseMayBe");
		rc.PurseStatusToasterMessage();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseMayBe1", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseMayBe1");
		rc.PurseStatusToasterMessage();
		// No
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseNo", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseNo");
		if (CommonMethod.isElementsExist("DatePickerConfirmButton", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerConfirmButton", 0);
			CommonMethod.RobustclickElementVisible("DatePickerConfirmButton", "PortfolioScorecardPursueToast");
		}
		rc.PurseStatusToasterMessage();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseNo1", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseNo1");
		if (CommonMethod.isElementsExist("DatePickerConfirmButton", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerConfirmButton", 0);
			CommonMethod.RobustclickElementVisible("DatePickerConfirmButton", "PortfolioScorecardPursueToast");
		}
		rc.PurseStatusToasterMessage();
	}

	public Set<String> findDuplicates(List<String> listContainingDuplicates) {
		final Set<String> setToReturn = new HashSet<>();
		final Set<String> set1 = new HashSet<>();
		for (String feature : listContainingDuplicates) {
			if (!feature.equalsIgnoreCase("Propose Innovation")) {
				if (!set1.add(feature)) {
					setToReturn.add(feature);
				}
			}
		}
		return setToReturn;
	}

	public void ValidateSearchLocationInDocumentLibrary(String SheetName, int rowNum) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
		testlog.info("Given User will be redirected to DocumentList page");
		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", "Multiselect1");
		CommonMethod.scrolldowntoElement("PortfolioDocumentListLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardEditLocRowCount", 0);
		CommonMethod.RobustclickElementVisible("Multiselect1", "OwnerOrg");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrg", 0);
		CommonMethod.sendKeys("OwnerOrg", "WELL at scale Test location 05");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocSearchNameEnter", 0);
		CommonMethod.RobustclickElementVisible("PortfolioDocSearchNameEnter", "PortfolioDocumentListLink");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardEditLocRowCount", 0);
		int PortfolioLocListTr = CommonMethod.ElementSize("PortfolioScorecardEditLocRowCount");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(PortfolioLocListTr), "2",
				"Location Table row count doesn't Mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardVerifyFilterReset", 0);
		CommonMethod.Robustclick("PortfolioScorecardVerifyFilterReset");
	}

	public void LocationImportAreaValid(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Location table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OccupancyIcon", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		int totalsize = CommonMethod.ElementSize("OccupancyIcon");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(totalsize), "4",
				"Location table Occupancy rows Count mismatch");
		testlog.info("Then User verifies location table data");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("table", 0);
		testlog.info("Fetching Data from Upload Table");
		int ImportSizeArea = Math.round(10000 / 250);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForImportLoc5"),
				String.valueOf(ImportSizeArea),
				"EstArea Calculation and Occupancy column with WELL at scale Test location 5 mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForImportLoc4"),
				String.valueOf(ImportSizeArea),
				"EstArea Calculation and Occupancy column with WELL at scale Test location 4 mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForImportLoc3"),
				String.valueOf(ImportSizeArea),
				"EstArea Calculation and Occupancy column with WELL at scale Test location 3 mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForImportLoc2"),
				String.valueOf(ImportSizeArea),
				"EstArea Calculation and Occupancy column with WELL at scale Test location 2 mismatch");
		// Validate EstArea Calculation and location table column occupancy in WELL at
		// scale Test location 1
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForImportLoc"), "50",
				"EstArea Calculation and Occupancy column with WELL at scale Test location 1 mismatch");
		testlog.info("And User verifies Location list Table and location count");
		testlog.pass("**Imported Locations successfully**");
	}

	public void EditOccupancylocation(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OccupancyIcon", 2);
		CommonMethod.RobustclickElementVisible("PortfolioEditButtonInLocTable", "PortfolioLocationAdditionalButton");
		Thread.sleep(3000);
		testlog.info("And User clicks on edit location button");
		/*
		 * Validate location Additional fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAdditionalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAdditionalButton",
				"PortfolioLocationRadiobuttonContainOutdoorSpaces");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardEstArea", 0);
		int ImportSizeArea = Math.round(10000 / 250);
		Thread.sleep(3000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioScorecardEstArea"),
				String.valueOf(ImportSizeArea),
				"EstArea Calculation and Est Occupancy field in Additional tab data mismatch");
		CommonMethod.clearAndSendKey("PortfolioScorecardEstArea", "30");
		testlog.info("Then User verifies Estimate Area data");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateButton", 0);
		CommonMethod.RobustclickElementVisible("UpdateButton", "Toastermessage");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("UpdateButton", 1);
		testlog.info("And User clicks on update button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OccupancyIcon", 2);
		int totalsize = CommonMethod.ElementSize("OccupancyIcon");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(totalsize), "3",
				"Location table Occupancy icon Count mismatch");
		testlog.info("Then User verifies location table data");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForImportLoc5"), "30",
				"Edited Est Occupancy field and Occupancy column with WELL at scale Test location 5 mismatch");
	}

	public void VerifySingleLocationTable(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User clicks on LocationsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OccupancyIcon", 2);
		CommonMethod.fetchTableData("table");
		List<String> val = CommonMethod.fetchTableData("table");
		CommonMethod.negativesoftassertFieldValid(val.get(0), data.getCellData(SheetName, "LocationName", rowNum),
				"LocationName in Location table data mismatch");
		CommonMethod.negativesoftassertFieldValid(val.get(1), data.getCellData(SheetName, "City", rowNum),
				"City Location table data mismatch");
		CommonMethod.negativesoftassertFieldValid(val.get(2), "US", "Country Location table data mismatch");
		Double Area = Double.valueOf(data.getCellData(SheetName, "AreaSQFT", rowNum));
		EstArea = Math.round(Area / 250);
		System.out.println("EstArea: " + String.valueOf(EstArea));
		// Validate EstArea Calculation and location table column occupancy in
		// Automation portfolio Location
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForSingleLoc"),
				String.valueOf(EstArea),
				"EstArea Calculation and Occupancy location table for single location data mismatch");
		testlog.info("Then User verifies Added single location with location table");

	}

	public void VerifySingleLocationOccupancyInAdditionalTab(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.RobustclickElementVisible("Editlocationbutton", "VerifyEditlocationslider");
		Thread.sleep(3000);
		testlog.info("And User clicks on edit location button");
		/*
		 * Validate location Additional fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAdditionalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAdditionalButton",
				"PortfolioLocationRadiobuttonContainOutdoorSpaces");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardEstArea", 0);
		Thread.sleep(3000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PortfolioScorecardEstArea"),
				String.valueOf(EstArea), "EstArea Calculation and Est Occupancy field in Additional tab data mismatch");
		testlog.info("Then User verifies Estimate Area data");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateButton", 0);
		CommonMethod.Robustclick("UpdateButton");
		testlog.info("And User clicks on update button");
	}

	public void DownloadOccupancylocationFile(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OccupancyIcon", 2);
		FileUtils.cleanDirectory(new File(downloadPath));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDownloadLocation", 0);
		CommonMethod.JavascriptClickElement("PortfolioDownloadLocation");
		testlog.info("And User clicks on Download Review button");
		Thread.sleep(5000);
		boolean fileExistsReturnValue = CommonMethod.isFileDownloaded();
		testlog.info("Then User verifies Downloaded file");
		String fileExists = Boolean.toString(fileExistsReturnValue);
		CommonMethod.negativesoftassertFieldValid(fileExists, "true", "Overview Download Location file doesn't Exist");
		CommonMethod.FileRename(downloadPath, "/Downloads/LocationOccupancyFile.xlsx");
		Thread.sleep(2000);
		portfolioLocationDownload = new XlsReader(
				System.getProperty("user.dir") + "/Downloads/LocationOccupancyFile.xlsx");
		// verify Download file Occupancy column with location table
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 6);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForImportLoc"),
				portfolioLocationDownload.getCellData("Import", "Estimated occupancy",
						portfolioLocationDownload.findRow("Import", "WELL at scale Test location 01"), 2),
				"Download file Occupancy column with WELL at scale Test location 1 mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForImportLoc2"),
				portfolioLocationDownload.getCellData("Import", "Estimated occupancy",
						portfolioLocationDownload.findRow("Import", "WELL at scale Test location 02"), 2),
				"Download file Occupancy column with WELL at scale Test location 2 mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForImportLoc3"),
				portfolioLocationDownload.getCellData("Import", "Estimated occupancy",
						portfolioLocationDownload.findRow("Import", "WELL at scale Test location 03"), 2),
				"Download file Occupancy column with WELL at scale Test location 3 mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForImportLoc4"),
				portfolioLocationDownload.getCellData("Import", "Estimated occupancy",
						portfolioLocationDownload.findRow("Import", "WELL at scale Test location 04"), 2),
				"Download file Occupancy column with WELL at scale Test location 4 mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioEstOccupancyForImportLoc5"),
				portfolioLocationDownload.getCellData("Import", "Estimated occupancy",
						portfolioLocationDownload.findRow("Import", "WELL at scale Test location 05"), 2),
				"Download file Occupancy column with WELL at scale Test location 5 mismatch");
	}

	public void VerifyMilestonetab(String SheetName, int rowNum, String editXpath, String LocationName)
			throws Exception {
		testlog.info("Given User is on location tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationPageValid", 0);
		CommonMethod.scrolldowntoElement("LocationPageValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Milestonetab", 0);
		CommonMethod.JavascriptClickElement("Milestonetab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MilestoneReviewCycle", 0);
		CommonMethod.verifyDropdownValues("MilestoneReviewCycle", "PortfolioTargetsCycle");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("MilestoneReviewCycle"), "All targets",
				"Pre-selected Target Milestone dropdown doesn't Match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.scrolldowntoElement("table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationTableTr", 0);
		testlog.info("Given User clicks on Milestone tab");
		List<String> tableHeaders = CommonMethod.fetchTableHeaders("table");
		int headersize = tableHeaders.size();
		testlog.info("And User gets Milestone header name");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(editXpath, 0);
		CommonMethod.RobustclickElementVisible(editXpath, "EditTargets");
		testlog.info("And User clicks on EditIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTargets", 0);
		testlog.info("And User verifies Milestone EditTargets");
		// Hsr
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditHsrCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditHsrCheckbox");
		testlog.info("And User checks on Hsr TargetAchievement");
		// Wpr
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditWprCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditWprCheckbox");
		testlog.info("And User checks on Wpr TargetAchievement");
		// Wer
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditWerCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditWerCheckbox");
		testlog.info("And User checks on Wer TargetAchievement");
		// PreCertification
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditPreCertficationCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditPreCertficationCheckbox");
		testlog.info("And User checks on PreCertification TargetAchievement");
		// Certification
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditCertficationCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditCertficationCheckbox");
		testlog.info("And User checks on Certification TargetAchievement");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocCertificationLevelDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioLocCertificationLevelDropdown", "Gold");
		testlog.info("And User selects Certfication Level");
		if (LocationName.equalsIgnoreCase("WELL at scale Test location 04")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditTargetCheckbox", 0);
			List<WebElement> ReviewCycle;
			ReviewCycle = CommonMethod.findElements("TargetAchievementEditTargetCheckbox");
			for (WebElement f : ReviewCycle) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditTargetCheckbox", 0);
				CommonMethod.JavascriptClickElement(f);
				testlog.info("And User checks on TargetAchievement");
			}
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocCertificationLevelDropdown", 0);
		CommonMethod.scrolldowntoElement("PortfolioLocCertificationLevelDropdown");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUpdateButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioUpdateButton", "table");
		if (CommonMethod.isElementsExist("VerifyUpdateToaster", 30)) {
			CommonMethod.WaitUntilPresence("VerifyUpdateToaster", 120);
			CommonMethod.WaitUntilInVisibility("VerifyUpdateToaster", 180);
			testlog.info("And User verifies Milestone Update Toaster");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Milestonetab", 0);
		CommonMethod.RobustclickElementVisible("Milestonetab", "MilestoneReviewCycle");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "table");
		Thread.sleep(3000);
		CommonMethod.scrolldowntoElement("table");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(headersize), "7", "Header count did not Match");
		testlog.info("And User verifies Milestone header count");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 6);
		Thread.sleep(3000);
		testlog.info("And User fetched Milestone table data");
		if (LocationName.equalsIgnoreCase("WELL at scale Test location 05")) {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioFutureReviewCertification"),
					"Targeting Gold", "Location Certification column with WELL at scale Test location 5 mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioFutureReviewPreCertification"),
					"Targeting", "Location Certification column with WELL at scale Test location 5 mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioFutureReviewHsr"), "Targeting",
					"Location Hsr column with WELL at scale Test location 5 mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioFutureReviewWpr"), "Targeting",
					"Location Wpr column with WELL at scale Test location 5 mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioFutureReviewWer"), "Targeting",
					"Location Wer column with WELL at scale Test location 5 mismatch");
		} else {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioReviewCycleCertification"),
					"Targeting Gold", "Location Certification column with WELL at scale Test location 4 mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioReviewCyclePreCertification"),
					"Targeting", "Location PreCertification column with WELL at scale Test location 4 mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioReviewCycleHsr"), "Targeting",
					"Location Hsr column with WELL at scale Test location 4 mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioReviewCycleWpr"), "Targeting",
					"Location Wpr column with WELL at scale Test location 4 mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioReviewCycleWer"), "Targeting",
					"Location Wer column with WELL at scale Test location 4 mismatch");
		}
		testlog.info("And User verifies location Certification status");
		testlog.info("And User verifies location PreCertification status");
		testlog.info("And User verifies location Hsr status");
		testlog.info("And User verifies location Wpr status");
		testlog.info("And User verifies location Wer status");
		testlog.pass("And User verifies Milestone tab successfully**");
	}

	public void VerifyMilestoneReviewCycle(String ReviewCycleName) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Milestonetab", 0);
		CommonMethod.RobustclickElementVisible("Milestonetab", "MilestoneReviewCycle");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MilestoneReviewCycle", 0);
		System.out.println("All targets: " + ReviewCycleName);
		CommonMethod.selectdropdownVisibletext("MilestoneReviewCycle", ReviewCycleName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		if (ReviewCycleName.equalsIgnoreCase("This review cycle")) {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MilestonePrecertifiedSealTarget"), "1",
					"Precertified Future ReviewCycle Milestone Summary count doesn't Match");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MilestoneHsrSealTarget"), "1",
					"Hsr Future ReviewCycle Milestone Summary count doesn't Match");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MilestoneWprSealTarget"), "1",
					"Wpr Future ReviewCycle Milestone Summary count doesn't Match");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MilestoneWerSealTarget"), "1",
					"Wer Future ReviewCycle Milestone Summary count doesn't Match");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MilestonecertifiedSealTarget"), "1",
					"certified ReviewCycle Milestone Summary count doesn't Match");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MilestonePrecertifiedSealTargetValid", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MilestonePrecertifiedSealTarget"), "2",
					"Precertified Future ReviewCycle Milestone Summary count doesn't Match");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MilestoneHsrSealTarget"), "2",
					"Hsr Future ReviewCycle Milestone Summary count doesn't Match");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MilestoneWprSealTarget"), "2",
					"Wpr Future ReviewCycle Milestone Summary count doesn't Match");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MilestoneWerSealTarget"), "2",
					"Wer Future ReviewCycle Milestone Summary count doesn't Match");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MilestonecertifiedSealTarget"), "2",
					"Certified Future ReviewCycle Milestone Summary count doesn't Match");
		}
		testlog.info("And User verifies location Seal Certification status");
		testlog.info("And User verifies location Seal PreCertification status");
		testlog.info("And User verifies location Seal Hsr status");
		testlog.info("And User verifies location Seal Wpr status");
		testlog.info("And User verifies location Seal Wer status");
		testlog.pass("And User verifies Milestone tab successfully**");
		testlog.pass("And User verifies Milestone tab successfully**");
	}

	public void VerifyMilestoneLocationFilters(String ReviewName, String Count) throws IOException, InterruptedException {
		testlog.info("Given User is on LocationsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Milestonetab", 0);
		CommonMethod.RobustclickElementVisible("Milestonetab", "MilestoneReviewCycle");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationPageValid", 0);
		CommonMethod.scrolldowntoElement("LocationPageValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FilterButton", 0);
		CommonMethod.JavascriptClickElement("FilterButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MilestoneFilterTarget", 0);
		CommonMethod.ClickCheckbox("MilestoneFilterTarget");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MilestoneReviewCycle", 0);
		CommonMethod.selectdropdownVisibletext("MilestoneReviewCycle", ReviewName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Applybutton", 0);
		CommonMethod.Robustclick("Applybutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", Integer.parseInt(Count));
		int rowCount = CommonMethod.ElementSize("PortfolioLocListTr");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(rowCount), Count,
				"Location Table Row count in Milestone Filter doesn't Match");
		testlog.info("And User verifies location Seal Certification status");
		testlog.info("And User verifies location Seal PreCertification status");
		testlog.info("And User verifies location Seal Hsr status");
		testlog.info("And User verifies location Seal Wpr status");
		testlog.info("And User verifies location Seal Wer status");
		testlog.pass("And User verifies Milestone tab successfully**");
	}

	@SuppressWarnings("static-access")
	public void ValidateMilestonelocationFile(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Milestonetab", 0);
		CommonMethod.RobustclickElementVisible("Milestonetab", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		List<String> tableData = CommonMethod.fetchTableData("table");
		testlog.info("And User fetched Milestone table data");
		pfu.ValidTableDataUpdateLocation(tableData, "WELL at scale Test location 03", 21, "Targeting");
		pfu.ValidTableDataUpdateLocation(tableData, "WELL at scale Test location 03", 22, "Targeting");
		pfu.ValidTableDataUpdateLocation(tableData, "WELL at scale Test location 03", 23, "Targeting");
		pfu.ValidTableDataUpdateLocation(tableData, "WELL at scale Test location 03", 24, "Targeting");
		pfu.ValidTableDataUpdateLocation(tableData, "WELL at scale Test location 03", 25, "Targeting");
	}

	public void AdminReviewUpload(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User on Admin Review");
		String[] DocType = { "WER", "WPR", "HSR", "v2p cert" };
		for (String s : DocType) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewUploadButton", 0);
			CommonMethod.RobustclickElementVisible("PortfolioAdminReviewUploadButton",
					"PortfolioAdminReviewSelectDocumentType");
			testlog.info("When User clicks on Upload button");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewSelectDocumentType", 0);
			CommonMethod.negativesoftassertFieldValid(
					String.valueOf(CommonMethod.getdropdownSize("PortfolioAdminReviewSelectDocumentType")), "8",
					"DocumentType Dropdown in Review Upload doesn't Match");
			testlog.info("And User verifies DocumentType size successfully**");
			CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewSelectDocumentType", s);
			CommonMethod.uploadFile("DocumentsUpload", FeaturefileUpload, "UploadFileVerifyScorecard");
			testlog.info("And User Upload Feature Document");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewModelUploadButton", 0);
			CommonMethod.RobustclickElementVisible("PortfolioAdminReviewModelUploadButton",
					"PortfolioAdminReviewValidDocumentType");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioImportDocLogWer", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioImportDocLogWer"), "WER",
				"DocumentType Wer doesn't Match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioImportDocLogWpr"), "WPR",
				"DocumentType Wpr doesn't Match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioImportLogDocHsr"), "HSR",
				"DocumentType Hsr doesn't Match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioImportLogDocV2"), "v2p cert",
				"DocumentType V2 doesn't Match");
		testlog.info("And User verifies Added DocumentType in Log successfully**");
		testlog.pass("And User verifies Admin Review Upload successfully**");
	}

	public void AdminReviewImportUploadedFile(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User on AdminReview Import page");
		AdminImportResult();
		testlog.info("When User clicks on Import Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewRound", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewImportSelectReviewRound", "Round 2");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewType", 0);
		String[] DocType = { "WELL V2", "WELL Health-Safety Rating", "WELL Performance Rating", "WELL Equity Rating" };
		for (String s : DocType) {
			CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewImportSelectReviewType", s);
			testlog.info("And user select on ReviewType");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectFile", 0);
			int importFile = CommonMethod.getdropdownSize("PortfolioAdminReviewImportSelectFile");
			CommonMethod.negativesoftassertFieldValid(String.valueOf(importFile), "5",
					"ImportFile dropdown option count Mismatch");
			testlog.info("Then user verifies importFile dropdown option count");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioImportCloseModal", 0);
		CommonMethod.Robustclick("PortfolioImportCloseModal");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioImportCloseModal", 1);
	}

	public void DeleteAdminReviewUpload(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User on AdminReview Import page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewDeleteIconLog", 0);
		int deleteIcon = CommonMethod.ElementSize("PortfolioAdminReviewDeleteIconLog");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(deleteIcon), "4",
				"AdminReview Delete Icon Log doesn't present");
		testlog.info("Then User verifies Upload Review delete icon count");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioImportLogUploadDeleteIcon", 0);
		CommonMethod.RobustclickElementVisible("PortfolioImportLogUploadDeleteIcon",
				"PortfolioImportLogUploadDeleteYesButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioImportLogUploadDeleteYesButton", 0);
		CommonMethod.Robustclick("PortfolioImportLogUploadDeleteYesButton", "PortfolioImportLogUploadDeleteIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAdminReviewDeleteIconLog", 3);
		int afterDeleteIcon = CommonMethod.ElementSize("PortfolioAdminReviewDeleteIconLog");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(afterDeleteIcon), "3",
				"AdminReview Delete Icon Log doesn't present");
		testlog.info("Then User verifies Upload Review delete icon count");
	}

	public void AdminImportResult() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportButton", 0);
		CommonMethod.JavascriptClickElement("PortfolioAdminReviewImportButton");
		testlog.info("When User clicks on Import Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewResultOption", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAdminReviewResultOption",
				"PortfolioAdminReviewImportSelectFile");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectFile", 0);
	}

	public void AdminImportResultQC() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAdminReviewImportButton", "PortfolioReviewQCResultsbtn");
		testlog.info("When User clicks on Import Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewQCResultsbtn", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewQCResultsbtn",
				"PortfolioAdminReviewImportSelectReviewType");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewType", 0);
	}

	public void AdminImportDocumentRulingsV2Review(String Commodity) throws IOException, InterruptedException {
		testlog.info("Given User on AdminReview Import page");
		AdminImportResult();
		if (Commodity.equalsIgnoreCase("Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewRound", 0);
			CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewImportSelectReviewRound", "Round 2");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewType", 0);
			int ReviewType = CommonMethod.getdropdownSize("PortfolioAdminReviewImportSelectReviewType");
			testlog.info("Then user verifies ReviewType dropdown option count");
			CommonMethod.negativesoftassertFieldValid(String.valueOf(ReviewType), "4",
					"ReviewType dropdown option count Mismatch");
			CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewImportSelectReviewType", "WELL V2");
			testlog.info("And user select on ReviewType");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectFile", 0);
		CommonMethod.selectdropdownVisibletextwithoutdelay("PortfolioAdminReviewImportSelectFile",
				"ImportReviewUpload");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportDocumentRulingsCheckbox",
				0);
		CommonMethod.ClickCheckbox("PortfolioAdminReviewImportDocumentRulingsCheckbox");
		testlog.info("And user checks on Import Document Rulings");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSubmitButton", 0);
		CommonMethod.Robustclick("PortfolioAdminReviewImportSubmitButton",
				"PortfolioAdminReviewImportSelectReviewRound");
		testlog.info("And user clicks on the submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioAdminReviewImportSelectReviewRound", 1);
	}

	public void AdminImportAchievementsHsrReview(String Commodity) throws IOException, InterruptedException {
		testlog.info("Given User on AdminReview Import page");
		AdminImportResult();
		if (Commodity.equalsIgnoreCase("Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewRound", 0);
			CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewImportSelectReviewRound", "Round 2");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewType", 0);
			CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewImportSelectReviewType",
					"WELL Health-Safety Rating");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectFile", 0);
		CommonMethod.selectdropdownVisibletextwithoutdelay("PortfolioAdminReviewImportSelectFile",
				"ImportReviewUpload");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportAchievementsCheckbox", 0);
		CommonMethod.ClickCheckbox("PortfolioAdminReviewImportAchievementsCheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSubmitButton", 0);
		CommonMethod.Robustclick("PortfolioAdminReviewImportSubmitButton",
				"PortfolioAdminReviewImportSelectReviewRound");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioAdminReviewImportSelectReviewRound", 1);
		testlog.info("And user clicks on the submit button");
	}

	public void AdminImportLocationScoresWprReview(String Commodity) throws IOException, InterruptedException {
		testlog.info("Given User on AdminReview Import page");
		AdminImportResult();
		if (Commodity.equalsIgnoreCase("Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewRound", 0);
			CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewImportSelectReviewRound", "Round 2");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewType", 0);
			CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewImportSelectReviewType",
					"WELL Performance Rating");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectFile", 0);
		CommonMethod.selectdropdownVisibletextwithoutdelay("PortfolioAdminReviewImportSelectFile",
				"ImportReviewUpload");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportLocationCheckbox", 0);
		CommonMethod.ClickCheckbox("PortfolioAdminReviewImportLocationCheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSubmitButton", 0);
		CommonMethod.Robustclick("PortfolioAdminReviewImportSubmitButton",
				"PortfolioAdminReviewImportSelectReviewRound");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioAdminReviewImportSelectReviewRound", 1);
		testlog.info("And user clicks on the submit button");
	}

	public void AdminImportLocationPulishReview(String Commodity) throws IOException, InterruptedException {
		testlog.info("Given User on AdminReview Import page");
		AdminImportResult();
		if (Commodity.equalsIgnoreCase("Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewRound", 0);
			CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewImportSelectReviewRound", "Round 2");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewType", 0);
			CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewImportSelectReviewType",
					"WELL Performance Rating");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectFile", 0);
		CommonMethod.selectdropdownVisibletextwithoutdelay("PortfolioAdminReviewImportSelectFile",
				"ImportReviewUpload");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportPublishCheckbox", 0);
		CommonMethod.ClickCheckbox("PortfolioAdminReviewImportPublishCheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSubmitButton", 0);
		CommonMethod.Robustclick("PortfolioAdminReviewImportSubmitButton",
				"PortfolioAdminReviewImportSelectReviewRound");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioAdminReviewImportSelectReviewRound", 1);
		testlog.info("And user clicks on the submit button");
	}

	public void AdminImportResultQCWerReviewWerReview(String Commodity, String ReviewType, String ReviewRound)
			throws IOException, InterruptedException {
		testlog.info("Given User on AdminReview Import page");

		AdminImportResultQC();
		testlog.info("When User clicks on Upload button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewType", 0);
		CommonMethod.selectdropdownVisibletextwithoutdelay("PortfolioAdminReviewImportSelectReviewType", ReviewType);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectReviewRound", 0);
		CommonMethod.selectdropdownVisibletextwithoutdelay("PortfolioAdminReviewImportSelectReviewRound", ReviewRound);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportCompareSelectFile", 0);
		CommonMethod.selectdropdownVisibletextwithoutdelay("PortfolioAdminReviewImportCompareSelectFile",
				"ImportReviewUpload");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAdminReviewImportSubmitButton",
				"PortfolioReviewResultToasterMessage");
		testlog.info("And user clicks on the submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioReviewResultToasterMessage", 1);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("getToasterMessage"),
				"results", "Review Result Toaster Message mismatch");
		if (CommonMethod.isElementsExist("PortfolioReviewQCResultsCrossIcon", 20)) {
			CommonMethod.Robustclick("PortfolioReviewQCResultsCrossIcon");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioReviewQCResultsCrossIcon", 1);
		}
	}

	public void AdminReviewImportButton() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAdminReviewImportButton",
				"PortfolioAdminReviewImportSelectFile");
	}

	public void AdminReviewimportLogs(String Commodity) throws IOException, InterruptedException {

		// Valid Import Review
		testlog.info("And user again clicks on import review result");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewValidImportLogFilename", 0);
		List<String> expImportName = new ArrayList<>();
		expImportName.add("Location Scores Imported");
		expImportName.add("Generated Scores for Locations & Portfolio");
		expImportName.add("Achievements Imported & Tasks Updated");
		expImportName.add("Document Rulings Imported");
		List<WebElement> ImportName;
		ImportName = CommonMethod.findElements("PortfolioAdminReviewValidImportLogType");
		for (WebElement s : ImportName) {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText(s), expImportName,
					"Import log Data mismatch: " + expImportName);
			testlog.info("And User verifies Added DocumentType in Log successfully**");

		}
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioImportLogcompleteLabel", 4);
		int completeLabel = CommonMethod.ElementSize("PortfolioImportLogcompleteLabel");
//		CommonMethod.negativesoftassertFieldValid(String.valueOf(completeLabel), "4",
//				"Import Log Complete label count mismatch");
		if (Commodity.equalsIgnoreCase("Portfolio")) {
			int roundLabel = CommonMethod.ElementSize("PortfolioImportLogRoundLabel");
//			CommonMethod.negativesoftassertFieldValid(String.valueOf(roundLabel), "4",
//					"Import Log Round label count mismatch");
		}
		int fileName = CommonMethod.ElementSize("PortfolioImportLogFileName");
	//	CommonMethod.negativesoftassertFieldValid(String.valueOf(fileName), "4", "Import Log FileName count mismatch");
		List<WebElement> dateLabel;
		dateLabel = CommonMethod.findElements("PortfolioImportLogDocDate");
		for (WebElement date : dateLabel) {
			String getDate = CommonMethod.getText(date);
			String[] stringArray = getDate.split("Imported");
			String[] dateValid = stringArray[1].split(" ");
			String actualDate = dateValid[1] + " " + dateValid[2] + " " + dateValid[3];
			CommonMethod.negativesoftassertFieldValid(actualDate, CommonMethod.ValidateDate(),
					"Review Import log Date Error Mismatch");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioImportCloseModal", 0);
		CommonMethod.Robustclick("PortfolioImportCloseModal");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioImportCloseModal", 1);
	}


	public void AdminReviewimportHistory(String Commodity) throws IOException, InterruptedException {
		// Valid Import Review history
		if (Commodity.equalsIgnoreCase("Portfolio")) {
			CommonMethod.refreshBrowser();
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioBackToAllReview", 0);
		CommonMethod.RobustclickElementVisible("PortfolioBackToAllReview", "PortfolioReviewImportHistory");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewImportHistory", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewImportHistory",
				"PortfolioAdminReviewValidImportLogFilename");
		CommonMethod.negativeAssertElementPresentTrue("PortfolioImportHistoryRegeneratebtn",
				"Import History Regenerate button is visible");
		AdminReviewimportLogs(Commodity);
		testlog.info("And user verifies imported review data on the history popup");
		testlog.pass("And User Imported review results successfully**");
	}

	public void NavigateLocation() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "PortfolioLocationLanding");
		testlog.info("When User clicks on LocationsTab");
	}

	public void ValidAddLocation(String SheetName, int rowNum, String LocationCount) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "Subsettab");
		testlog.info("When User clicks on LocationTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.scrolldowntoElement("LocationListTableLoading");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr",
				Integer.parseInt(LocationCount));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PagnitionResultCount", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PagnitionResultCount"), LocationCount,
				"Result location count doesn't match");
	}

	public void ValidateDocumentInDocumentLibrary() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
		CommonMethod.scrolldowntoElement(PortfolioAndRatingLocAccDocumentTable);
		rc.deleteButtonTooltipMessage();
	}

	public void ValidateDocumentInDocumentLibrary(String SheetName, int rowNum, String Access) throws Exception {
		/*
		 * For Document tab
		 */

		if (Access.equalsIgnoreCase("true")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
			CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
			CommonMethod.scrolldowntoElement(PortfolioAndRatingLocAccDocumentTable);
			rc.deleteButtonTooltipMessage();
			Thread.sleep(2000);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingDocRestrictDeleteAccesspopup", 0);
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("RatingDocRestrictDeleteAccesspopup"), toolTipMessage,
					"Delete Restriction Error Mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CancelTooltipPopUpInDocument", 0);
			CommonMethod.RobustclickElementVisible("CancelTooltipPopUpInDocument",
					PortfolioAndRatingLocAccDocumentTable);
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
			CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
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
		List<WebElement> Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
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
					CommonMethod.negativesoftassertFieldValid(
							CommonMethod.getattributeValueByTextContent("RatingDocRestrictDeleteAccesspopup"),
							toolTipMessage, "Delete Restriction Error Mismatch");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CancelTooltipPopUpInScorecard", 0);
					CommonMethod.RobustclickElementVisible("CancelTooltipPopUpInScorecard", "PtDeleteIcon");
				}
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilPresence("ApplicableVersion", Scorecardtimeout);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
	}

	public void AddingExternalProjectInPortfolioLocation(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "LocationPageValid");
		testlog.info("When User clicks on LocationTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationPageValid", 0);
		CommonMethod.scrolldowntoElement("LocationPageValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.RobustclickElementVisible("AddButton", "PortfolioLocationExternalProjectOption");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationExternalProjectOption", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationExternalProjectOption",
				"PortfolioLocationSelectProjectName");
		testlog.info("And User clicks on Add Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSelectProjectName", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationSelectProjectName",
				"PortfolioLocationEnterProjectName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationEnterProjectName", 0);
		CommonMethod.JavascriptClickElement("PortfolioLocationEnterProjectName");
		CommonMethod.sendKeys("PortfolioLocationEnterProjectName", data.getCellData("V2Project", "ProjectID", rowNum));
		CommonMethod.WaitUntilTextToBePresentInLocator("PortfolioLocationSelectOptionProjectName",
				data.getCellData("V2Project", "ProjectName", rowNum), 200);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSelectOptionProjectName", 0);
		CommonMethod.Robustclick("PortfolioLocationSelectOptionProjectName");
		if (TestCaseName.equalsIgnoreCase("Portfolio_USTC_03_00_AddingExternalProjectInPortfolioLocation")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPreliminaryPrecertificationReviewRadio",
					0);
			CommonMethod.ClickCheckbox("V2ProjectPreliminaryPrecertificationReviewRadio");
			CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectPreliminaryPrecertificationReviewRadio");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocumentHsrOptnReviewRadio", 0);
			CommonMethod.ClickCheckbox("V2ProjectDocumentHsrOptnReviewRadio");
			CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectDocumentHsrOptnReviewRadio");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnReviewRadio", 0);
			CommonMethod.ClickCheckbox("V2ProjectHsrOptnReviewRadio");
			CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectHsrOptnReviewRadio");
		}
		if (TestCaseName.contains("V2OptnHsrRatingAddingExternal")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSeperatePreliminaryReview", 0);
			CommonMethod.ClickCheckbox("V2ProjectSeperatePreliminaryReview");
			CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectSeperatePreliminaryReview");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConfirmButton", 0);
		Thread.sleep(3000);
		CommonMethod.Robustclick("PortfolioLocationConfirmButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioLocationConfirmButton", 1);
	}

	public void ValidateAddedInLocationList(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationPageValid", 0);
		CommonMethod.scrolldowntoElement("LocationPageValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationValidUpsellId", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("PortfolioLocationValidUpsellId").trim(),
				data.getCellData("V2Project", "ProjectID", rowNum), "Upsell location id Mismatch");
	}

	public void ValidatePortfolioAccountBannerInDashboard(String SheetName, int rowNum, String docCount)
			throws Exception {
		rc.locationNavigate();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "PortfolioAccBannerCongratsText");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccBannerCongratsText", 0);
		CommonMethod.negativesoftassertFieldValid(
		CommonMethod.getattributeValueByTextContent("PortfolioAccBannerCongratsText"),
				"Congratulations on adding new location", "Upsell Portfolio Account Banner Congrats Text Mismatch");
		CommonMethod.negativesoftassertFieldValid(
	    CommonMethod.getattributeValueByTextContent("PortfolioAccBannerLocationDocumentText"),
				"The location(s) below have documents that need your input",
				"Upsell Portfolio Account Banner Attention Text Mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccBannerLink", 0);
		CommonMethod.assertisElementPresentTrue("PortfolioAccBannerLink",
				"Portfolio Account Banner Link element not present");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.JavascriptClickElement("WellV2DashboardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccBannerCongratsText", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioAccBannerCongratsText"),
				"Congratulations on adding new location", "Upsell Portfolio Account Banner Congrats Text Mismatch");
		CommonMethod.negativesoftassertFieldValid(
		CommonMethod.getattributeValueByTextContent("PortfolioAccBannerLocationDocumentText"),"The location(s) below have documents that need your input",
				"Upsell Portfolio Account Banner Attention Text Mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccBannerLink", 0);
		CommonMethod.assertisElementPresentTrue("PortfolioAccBannerLink",
				"Portfolio Account Banner Link element not present");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccBannerLink", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAccBannerLink", "PortfolioLocationValidUpsellIdInDasboard");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationValidUpsellGoToLocation", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationValidUpsellIdInDasboard", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioLocationValidUpsellIdInDasboard"),
				data.getCellData("V2Project", "ProjectID", rowNum), "Upsell location id Mismatch");
		CommonMethod.JavascriptClickElement("PortfolioLocationValidUpsellGoToLocation");
		CommonMethod.switchToChildTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		CommonMethod.scrolldowntoElement("V2ProjectDocUploadbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationValidUpsellMigratedDocuments", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationValidUpsellMigratedDocuments",
				"PortfolioLocationValidUpsellMigratedDocumentsAttention");
		CommonMethod.scrolldowntoElement("PortfolioLocationValidUpsellMigratedDocuments");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
				"PortfolioLocationValidUpsellMigratedDocumentsAttention", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioLocationValidUpsellMigratedDocumentsAttention"),
				"There are documents that need your attention", "Upsell Attention Message Mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioV2ProjectSearchDocument", 0);
		CommonMethod.sendKeys("PortfolioV2ProjectSearchDocument", "sample");
		testlog.info("And User enter SearchDocument field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationDocumentSearchbtn", 0);
		CommonMethod.JavascriptClickElement("PortfolioLocationDocumentSearchbtn");
		testlog.info("And User clicks on Search button");
		CommonMethod.WaitUntilNumberOfElementTextToBePresentWithException("PortfolioLocationValidUpsellMigratedDocumentsFileName","sample" , 180);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioLocationValidUpsellMigratedDocumentsFileName"),
				"sample", "Filename Mismatch");
		CommonMethod.assertisElementPresentTrue("PortfolioLocationValidUpsellMigratedDocumentsverificationMethod",
				"Portfolio Account Banner Link verificationMethod element not present");
		CommonMethod.assertisElementPresentTrue("PortfolioLocationValidUpsellMigratedDocumentsspaceType",
				"Portfolio Account Banner Link spaceType element not present");
		CommonMethod.assertisElementPresentTrue("PortfolioLocationValidUpsellMigratedDocumentspartOption",
				"Portfolio Account Banner Link partOption element not present");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
				"PortfolioLocationValidUpsellMigratedDocumentsConfirmbtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MigrateDocTableTr", 10);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PaginitionDocumentCount", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PaginitionDocumentCount"), docCount,
				"Total Uploaded Document Count doesn't match");
		testlog.pass("**Validate PortfolioAccount Banner In Dashboard successful**");
	}

	public void ValidateLocationAccountBannerInDashboard(String SheetName, int rowNum, String verificationMethod,
			String docCount) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "PortfolioAccBannerCongratsText");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccBannerCongratsText", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("PortfolioAccBannerCongratsText"),
				"Congratulations on adding", "Upsell location Account Banner Congrats Text Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("PortfolioAccBannerLocationDocumentText"),
				"Please confirm or update", "Upsell location Account Banner Attention Text Mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccBannerLink", 0);
		CommonMethod.assertisElementPresentTrue("PortfolioAccBannerLink",
				"Portfolio Account Banner Link element not present");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccBannerLink", 0);
		CommonMethod.JavascriptClickElement("PortfolioAccBannerLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationValidUpsellMigratedDocuments", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationValidUpsellMigratedDocuments",
				"PortfolioLocationValidUpsellMigratedDocumentsAttention");
		CommonMethod.scrolldowntoElement("PortfolioLocationValidUpsellMigratedDocuments");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
				"PortfolioLocationValidUpsellMigratedDocumentsAttention", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("PortfolioLocationValidUpsellMigratedDocumentsAttention"),
				"There are documents that need your attention", "Upsell Attention Message Mismatch");
		testlog.info("And User click on General Document list");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioV2ProjectSearchDocument", 0);
		CommonMethod.sendKeys("PortfolioV2ProjectSearchDocument", "sample");
		testlog.info("And User enter SearchDocument field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationDocumentSearchbtn", 0);
		CommonMethod.JavascriptClickElement("PortfolioLocationDocumentSearchbtn");
		testlog.info("And User clicks on Search button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
				"PortfolioLocationValidUpsellMigratedDocumentsFileName", 0);
		List<String> Options = new ArrayList<>();
		Options.add(verificationMethod);
		Options.add("Professional Narrative");
		Options.add("Technical Document (Individual)");
		Options.add("Technical Document (Shareable)");
		Options.add("Performance Test");
		CommonMethod.WaitUntilNumberOfElementTextToBePresentWithException("PortfolioLocationValidUpsellMigratedDocumentsFileName","sample" , 180);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("PortfolioLocationValidUpsellMigratedDocumentsverificationMethod").trim(),
				Options, "Upsell verificationMethod Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioLocationValidUpsellMigratedDocumentsFileName"),
				"sample", "Upsell Filename Mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
				"PortfolioLocationValidUpsellMigratedDocumentsConfirmbtn", 0);
		if(TestCaseName.equalsIgnoreCase("Portfolio_USTC_03_02_V2OptnHsrRatingMigratedDocument")) {
			ValidateMigratedDocumentTags("Certification");
		}
		if(TestCaseName.equalsIgnoreCase("Portfolio_USTC_06_08_V2OptnHsrRatingMigratedDocument")) {
			ValidateMigratedDocumentTags("WELL Health-Safety");	
		}
		CommonMethod.JavascriptClickElement("PortfolioLocationValidUpsellMigratedDocumentsConfirmbtn");
		MigratedDocumentsCount(docCount);
	}
	
	public void MigratedDocumentsTab() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationValidUpsellMigratedDocuments", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationValidUpsellMigratedDocuments",
				"PortfolioMigrationConfirmbtn");
		}
	
	public void MigratedDocumentsCount(String docCount) throws IOException, InterruptedException {
	CommonMethod.refreshBrowser();
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationValidUpsellMigratedDocuments", 0);
	CommonMethod.RobustclickElementVisible("PortfolioLocationValidUpsellMigratedDocuments",
			"PortfolioLocationValidUpsellMigratedDocumentsAttention");
	CommonMethod.scrolldowntoElement("PortfolioLocationValidUpsellMigratedDocuments");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
			"PortfolioLocationValidUpsellMigratedDocumentsAttention", 0);
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MigrateDocTableTr", 10);
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PaginitionDocumentCount", 0);
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PaginitionDocumentCount"), docCount,
			"Total Uploaded Document Count doesn't match");
}
	
	public void ratingMigratedDocumentsCount(String docCount) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioV2ProjectSearchDocument", 0);
		CommonMethod.sendKeys("PortfolioV2ProjectSearchDocument", "Favicon");
		testlog.info("And User enter SearchDocument field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationDocumentSearchbtn", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationDocumentSearchbtn",
				"PortfolioMigrationConfirmbtn");
		testlog.info("And User clicks on Search button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioMigrationConfirmbtn1", 0);
		CommonMethod.Robustclick("PortfolioMigrationConfirmbtn1");
		MigratedDocumentsCount(docCount);
	}
	
	public void storeLocationId(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.scrolldowntoElement("table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("getLocationId", 0);
		String locationId = CommonMethod.getattributeValueByTextContent("getLocationId");
		System.out.println("locationId: " + locationId);
		data.setCellData(SheetName, "LocationProjectID", rowNum, locationId);
	}

	public void HSROptnLocationAccount() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
		CommonMethod.RobustclickElementVisible("HealthSafetyTab", "ConfirmTheEnrollment");
		testlog.info("When User clicks on HealthSafetyTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ConfirmTheEnrollment", 0);
		CommonMethod.Robustclick("ConfirmTheEnrollment");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ConfirmTheEnrollment", 1);
	}

	public void HSROptnProfolioAccount() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
		CommonMethod.RobustclickElementVisible("HealthSafetyTab", "HsrScorecardOptnTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrScorecardOptnTab", 0);
		CommonMethod.RobustclickElementVisible("HsrScorecardOptnTab", "PortfolioHSRScorecardTabcustomizescorecardheading");
		rc.ScorecardLoading();
	}

	public void HSROptnDocumentProfolioAccount(String featureName, String SheetName, int rowNum, String Commodity)
			throws Exception {

		if (featureName.equalsIgnoreCase("Support Handwashing")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
			CommonMethod.RobustclickElementVisible("HealthSafetyTab", "OptnDocumentTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OptnDocumentTab", 0);
			CommonMethod.RobustclickElementVisible("OptnDocumentTab", "PortfolioDocumentListLink");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
			CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
			CommonMethod.scrolldowntoElement("PortfolioDocumentListLink");
			pathprms.put("PartId", "SC1");
			pathprms.put("VerificationMethod", "Policy and/or Operations Schedule");
			pathprms.put("Stage", "Feature");
			pathprms.put("Status", "Ready For Review");
			generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, true, false, true, true,
					true, true, false);

		}
		if (featureName.equalsIgnoreCase("Meet Thresholds for Particulate Matter")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PerformanceTab", 0);
			CommonMethod.RobustclickElementVisible("PerformanceTab", "OptnDocumentTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OptnDocumentTab", 0);
			CommonMethod.RobustclickElementVisible("OptnDocumentTab", "PortfolioDocumentListLink");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
			CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
			CommonMethod.scrolldowntoElement("PortfolioDocumentListLink");
			pathprms.put("PartId", "PA1");
			pathprms.put("VerificationMethod", "Performance Test");
			pathprms.put("Stage", "Feature");
			pathprms.put("Status", "Ready For Review");
			generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, true, false, true, true,
					true, true, false);
		}
		if (featureName.equalsIgnoreCase("Create DEI Assessment and Action Plan")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EquityTab", 0);
			CommonMethod.RobustclickElementVisible("EquityTab", "OptnDocumentTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OptnDocumentTab", 0);
			CommonMethod.RobustclickElementVisible("OptnDocumentTab", "PortfolioDocumentListLink");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
			CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
			CommonMethod.scrolldowntoElement("PortfolioDocumentListLink");
			pathprms.put("PartId", "EE1");
			pathprms.put("VerificationMethod", "Policy and/or Operations Schedule");
			pathprms.put("Stage", "Feature");
			pathprms.put("Status", "Ready For Review");
			generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, true, false, true, true,
					true, true, false);
		}
		CommonMethod.refreshBrowser();
		testlog.info("And User verifies Edit Icon in Document table");
		testlog.info("And User verifies Delete Icon in Document table");
		testlog.pass("**Verifies Document library Upload document successful**");

	}

	public void ValiateScorecardUploadDocInHSROptnPortfolioAccount(String featureName, String SheetName, int rowNum,
			String Commodity) throws Exception {
		testlog.info("Given User is on Scorecard page");
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPFeature", 0);
		List<WebElement> Feature = CommonMethod.findElements("V2ProjectWPRPFeature");
		boolean flag = false;
		CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
		for (WebElement f : Feature) {
			String Creditname = f.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag = true;
				CommonMethod.WaitUntilClickble(f, 180);
				CommonMethod.JavascriptClickElement(f);
				testlog.info("When Click on " + Creditname + " feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						"PortfolioScoreCardAddOptionbutton");
				testlog.info("And User clicks on VerificationTab");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
				CommonMethod.JavascriptClickElement("PortfolioScoreCardAddOptionbutton");
				testlog.info("And User clicks on AddOption button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddButton", 0);
				CommonMethod.Robustclick("PortfolioScoreCardAddButton");
				testlog.info("And User clicks on Add Button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationCloseicon", 0);
				CommonMethod.Robustclick("PortfolioScoreCardVerificationCloseicon");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScoreCardVerificationCloseicon", 1);
				testlog.info("And User clicks on Closeicon");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
				if (featureName.equalsIgnoreCase("Support Handwashing")) {
					pathprms.put("PartId", "SC1");
					pathprms.put("VerificationMethod", "Policy and/or Operations Schedule");
					pathprms.put("Stage", "Feature");
					pathprms.put("Status", "Ready For Review");
					generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, true, false, true,
							true, true, true, false);
				}
				if (featureName.equalsIgnoreCase("Meet Thresholds for Particulate Matter")) {
					pathprms.put("PartId", "PA1");
					pathprms.put("VerificationMethod", "Performance Test");
					pathprms.put("Stage", "Feature");
					pathprms.put("Status", "Ready For Review");
					generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, true, false, true,
							true, true, true, false);
				}
				if (featureName.equalsIgnoreCase("Create DEI Assessment and Action Plan")) {
					pathprms.put("PartId", "EE1");
					pathprms.put("VerificationMethod", "Policy and/or Operations Schedule");
					pathprms.put("Stage", "Feature");
					pathprms.put("Status", "Ready For Review");
					generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, true, false, true,
							true, true, true, false);
				}
				CommonMethod.JavascriptClickElement(f);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + featureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void wprOptnLocationAccount() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PerformanceTab", 0);
		CommonMethod.RobustclickElementVisible("PerformanceTab", "ConfirmTheEnrollment");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ConfirmTheEnrollment", 0);
		CommonMethod.Robustclick("ConfirmTheEnrollment");
		rc.ScorecardLoading();
	}

	public void wprOptnPortfolioAccount() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PerformanceTab", 0);
		CommonMethod.RobustclickElementVisible("PerformanceTab", "ConfirmTheEnrollment");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ConfirmTheEnrollment", 0);
		CommonMethod.Robustclick("ConfirmTheEnrollment");
		rc.ScorecardLoading();
	}

	public void wprOptnPortfolioAccount1() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PerformanceTab", 0);
		CommonMethod.RobustclickElementVisible("PerformanceTab", "WPRScorecardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRScorecardTab", 0);
		CommonMethod.RobustclickElementVisible("WPRScorecardTab", "PortfolioHSRScorecardTabcustomizescorecardheading");
		rc.ScorecardLoading();
	}

	public void werOptnPortfolioAccount1() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EquityTab", 0);
		CommonMethod.RobustclickElementVisible("EquityTab", "WPRScorecardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRScorecardTab", 0);
		CommonMethod.RobustclickElementVisible("WPRScorecardTab", "PortfolioHSRScorecardTabcustomizescorecardheading");
		rc.ScorecardLoading();
	}

	public void uploadDocumentInOptnFeature(String featureName) throws Exception {
		rc.ScorecardLoading();
		boolean flag = false;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
		List<WebElement> Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		for (WebElement f : Feature) {
			String Creditname = f.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag = true;
				CommonMethod.WaitUntilClickble(f, 120);
				CommonMethod.JavascriptClickElement(f);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPDocIcon", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectWPRPDocIcon", "V2ProjectDocUpload");
				CommonMethod.uploadFile("V2ProjectDocUpload", FeaturefileUpload, "UploadFileVerifyScorecard");
				if (!CommonMethod.isElementsExist("V2ProjectScorecardSelectedDocTypeValid", 5)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
							"Multiselect1", 0);
					CommonMethod.click("Multiselect1");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectOwnerOrgDyn", 0);
					List<WebElement> ele = CommonMethod.findElements("SelectOwnerOrgDyn");
					ele.get(0).click();
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature",
						0);
				testlog.info("And User select Partname");
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
					testlog.info("And User Select SpaceType");
				}
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
					testlog.info("And User Select Option");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPartbtn", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPartbtn",
						"WERScorecardRemovePart");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadRemovelink", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRUploadbtn1", 0);
				CommonMethod.Robustclick("V2ProjectWPRUploadbtn1");
				rc.uploadDocumentToastMessage();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTableTr, 0);
				break;
			}
			CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + featureName + " doesn't match");
			testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
		}
	}

	public void DeleteUploadDocumentInScorecardFeature(String FeatureName) throws IOException, InterruptedException {
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPFeature", 0);
		Feature = CommonMethod.findElements("V2ProjectWPRPFeature");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				v2project.DeleteDocV2ProjectInsideScorecard();
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void validDeletedUploadDocumentInScorecardFeature(String FeatureName)
			throws IOException, InterruptedException {
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPFeature", 0);
		Feature = CommonMethod.findElements("V2ProjectWPRPFeature");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectUploadDocDeleteIcon");
				CommonMethod.assertisElementPresentFalse("V2ProjectUploadDocDeleteIcon",
						"Deleted Upload Document is visible");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void NavigateAddedLocation(String SheetName, int rowNum)
			throws IOException, InterruptedException, ClientApiException {
		login.AdminLogin();
		testlog.info("Given User is on Dashboard page");
		String Id = data.getCellData(SheetName, "LocationProjectID", rowNum).trim();
		System.out.println("LocationProjectID:" + Id);
		testlog.info("LocationProjectID:" + Id);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLCertificationNavBar");
		CommonMethod.RobustclickElementVisible("AdminWELLCertificationNavBar", "AdminV2ProjectId");
		testlog.info("And User clicks on Admin WELL Certification from top menu under Projects");
		CommonMethod.WaitUntilClickble("AdminV2ProjectId", 60).sendKeys(Id);
		testlog.info("And User enter on ProjectID in  AdminV2ProjectId field");
		CommonMethod.RobustclickElementVisible("AdminV2ProjectApplybtn", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchResultIDVerify", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("SearchResultIDVerify"), Id,
				"ProjectID doesn't matches in search");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchResultIDVerify", 0);
		CommonMethod.RobustclickElementVisible("SearchResultIDVerify", "V2ProjectStartBuilding");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartBuilding", 0);
	}

	public void ValidateAcheivementTabAndNavigateToLocationAccount(String SheetName, int rowNum)
			throws IOException, InterruptedException, ClientApiException {

		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AchievementsTab", 1);
		CommonMethod.assertisElementPresentFalse("AchievementsTab", "AchievementsTab is present");
		pfu.StoreLocationId(SheetName, rowNum);
		portfolio.NavigateAddedLocation(SheetName, rowNum);
	}

	public void BeforeValidatingPromotionInLocationAccount() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PromotionTab", 0);
		CommonMethod.RobustclickElementVisible("PromotionTab", "PromotionCardContainer");
		testlog.info("When User clicks on PromotionTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PromotionCardContainer", 3);
		CommonMethod.negativesoftassertFieldValid(String.valueOf(CommonMethod.ElementSize("PromotionCardContainer")),
				"3", "Before Promotion Card count empty doesn't match");
	}

	public void AfterValidatingPromotion(String CardCount) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PromotionTab", 0);
		CommonMethod.RobustclickElementVisible("PromotionTab", "PromotionCardContainer");
		testlog.info("When User clicks on PromotionTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PromotionCardContainer", 0);
		CommonMethod.negativesoftassertFieldValid(String.valueOf(CommonMethod.ElementSize("PromotionCardContainer")),
				CardCount, "After Promotion Card count empty doesn't match");

	}

	public void downloadFileExist(String DownloadFile) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(DownloadFile, 0);
		List<WebElement> clickFile = CommonMethod.findElements(DownloadFile);
		for (WebElement ele : clickFile) {
			CommonMethod.click(ele);
			Thread.sleep(5000);
			if (TestCaseName.equalsIgnoreCase("Portfolio_TC_23_05_ValidateAchievementTabInPortfolioAccount")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AchievementToasterMessage", 0);
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getattributeValueByTextContent("AchievementToasterMessage"), "download link",
						"Achievement Toaster Message doesn't match");
				Thread.sleep(3000);
			} else {
				boolean fileExistsReturnValue = CommonMethod.isFileDownloaded();
				testlog.info("Then User verifies Downloaded file");
				String fileExists = Boolean.toString(fileExistsReturnValue);
				CommonMethod.negativesoftassertFieldValid(fileExists, "true", "Downloaded Card file doesn't Exist");
				CommonMethod.ClearDownloadFile();
			}
		}
	}

	public void ValidateAcheivementTabAndCard() throws Exception {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AchievementsTab", 0);
		CommonMethod.assertisElementPresentTrue("AchievementsTab", "AchievementsTab is not present");
		CommonMethod.RobustclickElementVisible("AchievementsTab", "PromotionCardContainer");
		rc.ValidCardCount("PromotionCardContainer");
	}

	public void editAdminAsCertification() throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		testlog.info("When User clicks on EditTab");
		rc.navigateAchievementAdminTab();
		testlog.info("And User clicks on AdminFields Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditCertificationStatus", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectEditCertificationStatus", "Bronze");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditDateCertified", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectEditDateCertified", "DatePickerOkButton");
		Thread.sleep(1000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.click("DatePickerOkButton");
		rc.saveAchievementAdminTab("V2AchievementAdminTab");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
	}

	public void AuditScorecardUpload(String featureName, String SheetName, int rowNum, String ProjectType,
			String Commodity, String FileName, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
			Boolean NoteRequired, Boolean IntentCheckboxRequired) throws Exception {
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		testlog.info("Fetching total no. of credits on page");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.click(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						"PortfolioScoreCardAddOptionbutton");
				testlog.info("And User clicks on VerificationTab");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddOptionbutton",
						"PortfolioScoreCardAddButton");
				testlog.info("And User clicks on AddOption button");
				/** ScoreCard Add option */
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddButton",
						"PortfolioScoreCardVerificationCloseicon");
				testlog.info("And User clicks on Add button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationCloseicon", 0);
				CommonMethod.Robustclick("PortfolioScoreCardVerificationCloseicon");
				testlog.info("And User clicks on Closeicon");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocbtn", 0);
				generic.assignLocationGeneric(Commodity, true, false, false, false, false);
				Thread.sleep(2000);
				/** Upload Document for Tasks */
				CommonMethod.scrollDown();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
				CommonMethod.clickOnListWebelementFromIndex("PortfolioScoreCardVerificationUploadbtn", 0);
				generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired,
						VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationAssignLocCbxGeneral", 0);
				CommonMethod.ClickUnCheckbox("WPRAssignLocFirstChildCbx");
				testlog.info("And User checks the AssignLocation checkbox");
				CommonMethod.scrolldowntoElement("PortfolioSaveAndExitBtn");
				rc.ScorecardConfirmLocUploadSaveButton();
				testlog.info("And User clicks on save button");
				rc.uploadDocumentToastMessage();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
				pathprms.put("LocationCount", "1 Assigned");
				pathprms.put("PartId", "A02.2");
				pathprms.put("VerificationMethod", "AUDIT");
				pathprms.put("Stage", "Audit");
				pathprms.put("Status", "Ready For Review");
				generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, false, true, true,
						true, true, true, false);
				testlog.info("And User verifies Upload Document Table Data");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardTaskPurseLocation"),
						"1/2 Locations", "Task Assign Location Count doesn't match");
				CommonMethod.JavascriptClickElement(ele);
				testlog.pass("**Verifies the Audit Scorecard Upload successful**");
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + featureName + " doesn't match");
	}

	public void uploadAndValidateFulfilledDocs(String featureName, String SheetName, int rowNum, String ProjectType,
			String Commodity, String FileName, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
			Boolean NoteRequired, Boolean IntentCheckboxRequired) throws Exception {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardFeature", 0);
		List<WebElement> Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		testlog.info("Fetching total no. of credits on page");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						"PortfolioScoreCardAddOptionbutton");
				testlog.info("And User clicks on VerificationTab");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddOptionbutton",
						"PortfolioScoreCardAddButton");
				testlog.info("And User clicks on AddOption button");
				/** ScoreCard Add option */
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddButton",
						"PortfolioScoreCardVerificationCloseicon");
				testlog.info("And User clicks on Add button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationCloseicon", 0);
				CommonMethod.Robustclick("PortfolioScoreCardVerificationCloseicon");
				testlog.info("And User clicks on Closeicon");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocbtn", 0);
				pathprms.put("StartAssignLoc", "1");
				pathprms.put("EndAssignLoc", "2");
				generic.assignLocationGeneric(Commodity, false, false, false, true, false);
				Thread.sleep(2000);
				/** Upload Document for Tasks */
				CommonMethod.scrollDown();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
				CommonMethod.clickOnListWebelementFromIndex("PortfolioScoreCardVerificationUploadbtn", 0);
				generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired,
						VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
				testlog.info("And User checks the AssignLocation checkbox");
				CommonMethod.scrolldowntoElement("PortfolioSaveAndExitBtn");
				rc.ScorecardConfirmLocUploadSaveButton();
				testlog.info("And User clicks on save button");
				rc.uploadDocumentToastMessage();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTableTr, 0);
				CommonMethod.JavascriptClickElement(ele);
				testlog.pass("**Verifies the Audit Scorecard Upload successful**");
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + featureName + " doesn't match");
		portfolio.clickDocument();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTaskTab", 0);
		CommonMethod.RobustclickElementVisible("WPRTaskTab", "TaskFullFilledTab");
		CommonMethod.scrolldowntoElement("WPRTaskTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TaskFullFilledTab", 0);
		CommonMethod.click("TaskFullFilledTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocListA1.1", 0);
		CommonMethod.click("PortfolioDocListA1.1");
		generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FeaturefileUpload, false, false, false,
				false);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUncheckLoc", 0);
		CommonMethod.ClickCheckbox("PortfolioScorecardUncheckLoc");
		testlog.info("And User checks the AssignLocation checkbox");
		CommonMethod.scrolldowntoElement("PortfolioSaveAndExitBtn");
		rc.ScorecardConfirmLocUploadSaveButton();
		testlog.info("And User clicks on save button");
		rc.uploadDocumentToastMessage();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TaskFullFilledTab", 0);
		CommonMethod.click("TaskFullFilledTab");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardTaskPurseLocation"),
				"2/2 Locations", "Task Assign Location Count doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioSaveAndExitBtn", 1);
	}

	public void validatePortfolioAchievementDocument(String documentDownloadBtnObjectLocator,
			String actualDownloadedFileMsg, String expectedMessage) throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(documentDownloadBtnObjectLocator, 0);
		CommonMethod.click(documentDownloadBtnObjectLocator);
		testlog.info("Then User Downloaded The Documents and Validated the Text");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(actualDownloadedFileMsg, 0);
		String actualDownloadFileTextObjectLocator = CommonMethod
				.getattributeValueByTextContent(actualDownloadedFileMsg).trim();
		CommonMethod.negativesoftassertFieldValid(actualDownloadFileTextObjectLocator, expectedMessage,
				"Downloaded Text Message Does not Matched");
	}

	public void SingleUploadScorecardDocumentInOptn(String FeatureName, String SheetName, int rowNum, String Commodity,
			String FileName, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
			Boolean NoteRequired, Boolean IntentCheckboxRequired) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPFeature", 0);
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
					if (TestCaseName.equalsIgnoreCase("Portfolio_TC_18_01_HSRScoreCardUploadDocumentPortfolio")) {
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AuditAddButton", 0);
						CommonMethod.RobustclickElementVisible("AuditAddButton", "ScorecardAddedOption");
					} else {
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionbtn", 0);
						CommonMethod.RobustclickElementVisible("WPRAddOptionbtn", "ScorecardAddedOption");
					}
					CommonMethod.Robustclick("WPRAddOptionCloseIcon");
					CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRAddOptionCloseIcon", 1);
					generic.assignLocationGeneric(Commodity, true, false, false, false, false);
				}
				if (FeatureName.contains("Promote a Smoke-Free Environment")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioHsrOptnUploadButtonAudit", 0);
					CommonMethod.JavascriptClickElement("PortfolioHsrOptnUploadButtonAudit");
				} else {
					CommonMethod.JavascriptClickElement("PortfolioHsrOptnUploadButtonFeature");
				}
				generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired,
						VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationAssignChildLocCbx", 0);
				CommonMethod.ClickUnCheckbox("PortfolioScoreCardVerificationAssignChildLocCbx");
				testlog.info("And User checks the AssignLocation checkbox");
				CommonMethod.scrolldowntoElement("PortfolioSaveAndExitBtn");
				rc.ScorecardConfirmLocUploadSaveButton();
				testlog.info("And User clicks on save button");
				rc.uploadDocumentToastMessage();
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void upsellHSROptnLocationAccount() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioGetUpsellLocationId", 0);
		CommonMethod.click("PortfolioGetUpsellLocationId");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
		CommonMethod.RobustclickElementVisible("HealthSafetyTab", "ConfirmTheEnrollment");
		testlog.info("When User clicks on HealthSafetyTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ConfirmTheEnrollment", 0);
		CommonMethod.Robustclick("ConfirmTheEnrollment");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ConfirmTheEnrollment", 1);
		CommonMethod.switchToParentTab();
	}

	public void assignedTaskNavigate(Boolean AssignedTask) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignedTasksTab", 0);
		CommonMethod.RobustclickElementVisible("AssignedTasksTab", "WELLV2Tab");
		if (AssignedTask) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WELLV2Tab", 0);
			CommonMethod.RobustclickElementVisible("WELLV2Tab", "PortfolioNoAssignmentText");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioNoAssignmentText", 0);
			String getExpectedPartId = CommonMethod.getattributeValueByTextContent("PortfolioNoAssignmentText");
			CommonMethod.negativesoftassertFieldValid(getExpectedPartId, "Version mismatch",
					"Version mismatch in Location Account not matched");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnhsrDocumentLibrary", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectHsrOptnhsrDocumentLibrary", "PortfolioNoAssignmentText");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioNoAssignmentText", 0);
			String getExpectedPartId = CommonMethod.getattributeValueByTextContent("PortfolioNoAssignmentText");
			CommonMethod.negativesoftassertFieldValid(getExpectedPartId, "No assignments in WELL at scale scorecard",
					"No assignments in Location Account not matched");
		}
	}

	public void ValidateWERSection() throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.click("LocationsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioClickOnLocation", 0);
		CommonMethod.click("PortfolioClickOnLocation");
		CommonMethod.switchToChildTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAccountAdminRatingLabel", 0);
		String[] expRatingName = { "WELL Precertification", "WELL Certification", "WELL Health Safety Rating",
				"WELL Performance Rating", "WELL Equity Rating" };
		List<WebElement> RatingName;
		RatingName = CommonMethod.findElements("PortfolioLocationAccountAdminRatingLabel");
		int i = 0;
		for (WebElement s : RatingName) {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText(s), expRatingName[i],
					"Import log Data mismatch: " + expRatingName[i]);
			testlog.info("And User verifies Added DocumentType in Log successfully**");
			i = i + 1;
		}
		CommonMethod.switchToParentTab();
	}

	public void validateRecertificationButtonInAdminField(String SheetName, int rowNum,
			String saveAchievementAdminTabXpath) throws Exception {
		pfu.StoreLocationId(SheetName, rowNum);
		portfolio.NavigateAddedLocation(SheetName, rowNum);
		navigateWERPortfolioOpt();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAccountAdminRatingLabel", 0);
		String[] expRatingName = { "WELL Precertification", "WELL Certification", "WELL Health Safety",
				"WELL Performance Rating", "WELL Equity Rating" };
		List<WebElement> RatingName;
		RatingName = CommonMethod.findElements("PortfolioLocationAccountAdminRatingLabel");
		int i = 0;
		for (WebElement s : RatingName) {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText(s), expRatingName[i],
					"Import log Data mismatch: " + expRatingName[i]);
			testlog.info("And User verifies Added DocumentType in Log successfully**");
			i = i + 1;
		}
		rc.editAdminSelctAchieveAndDate("V2ProjectEditHsrSelect", "Achieved", "HsrAchievementLocAdminTab",
				"V2ProjectEditHsrDate");
	}

	public void navigateToUpsellLocationId(String SheetName, int rowNum, String UpsellLocationId) throws Exception {
		rc.SignOut();
		login.Login();
		portfolio.SearchPortfolioById(SheetName, rowNum);
		rc.locationNavigate();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(UpsellLocationId, 0);
		CommonMethod.JavascriptClickElement(UpsellLocationId);
		CommonMethod.switchToChildTab();

	}

	public void validateDashboardInLocationAccount() throws Exception {
		testlog.info("Given User is on Dashboard page");
		testlog.info("Then User will be redirected to Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.negativeAssertElementPresentTrue("WellV2DashboardTab",
				"WellV2DashboardTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("HealthSafetyTab", "HealthSafetyTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("PerformanceTab", "PerformanceTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("EquityTab", "EquityTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("AssignedTasksTab", "AssignedTasksTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("DocumentLibraryTab",
				"DocumentLibraryTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("ReviewTab", "ReviewTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("ProfileTab", "ProfileTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("PromotionTab", "PromotionTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("TeamTab", "TeamTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("SupportTab", "SupportTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("AlternativesTab", "AlternativesTab element is not Present");
		CommonMethod.negativeAssertElementPresentTrue("OverviewTab", "OverviewTab element is not Present");
		if (TestCaseName.equalsIgnoreCase(
				"Portfolio_USTC_05_06_UpsellRegistrationInProgressValidateDashboardInLocationAccount")) {
			CommonMethod.negativeAssertElementPresentTrue("ScorecardTab", "ScorecardTab element is not Present");
			CommonMethod.negativeAssertElementPresentTrue("BiilingTab", "BiilingTab element is not Present");

		} else {
			CommonMethod.negativeAssertElementPresentTrue("V2ProjectStartBuilding",
					"V2ProjectStartBuilding element is not Present");

		}
		CommonMethod.switchToParentTab();
		testlog.info("And User verifies Dashboard fields and SideBar Navigation tab");
		testlog.pass("**Verifies Dashboard fields and SideBar Navigation tab successfully **");
	}

	public void ValidateTaskCompletionOnAddingOptionFromDocEdit(String SheetName, int rowNum,
			String ProjectTypeVerification, String ProjectType, String Commodity, String FileName,
			Boolean PartNameRequired, Boolean VerificationMethodValidationRequired, Boolean NoteRequired,
			Boolean IntentCheckboxRequired, String featureName) throws Exception {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardFeature", 0);
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
		Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
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
				CommonMethod.RobustclickElementVisible("WPRVerficationTab","PortfolioScoreCardAddOptionbutton");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddOptionbutton","PortfolioScoreCardAddButtonOne");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddButtonOne", 0);
				CommonMethod.JavascriptClickElement("PortfolioScoreCardAddButtonOne");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAddedOption", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddButtonOne", 0);
				CommonMethod.JavascriptClickElement("PortfolioScoreCardAddButtonOne");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationCloseicon", 0);
				CommonMethod.Robustclick("PortfolioScoreCardVerificationCloseicon");
				AssignLocations();
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
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioAndRatingLocAccDocumentTableEditIcon", 0);
				CommonMethod.JavascriptClickElement("PortfolioAndRatingLocAccDocumentTableEditIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationUploadAddfeature", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardUploadOptionName");
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationSelectFeature");
				testlog.info("And User clicks on Add feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardPartDropdown", 0);
				CommonMethod.selectdropdownVisibletext("PortfolioScoreCardPartDropdown", "A01.1");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardSelectSpaceTypeFromDropdown", 0);
				CommonMethod.selectdropdownVisibletext("PortfolioScoreCardSelectSpaceTypeFromDropdown", "All Spaces");

				if (ProjectType.contains("pilot")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
							"PortfolioScoreCardSelectOptionFromDropdown", 0);
					CommonMethod.selectdropdownVisibletext("PortfolioScoreCardSelectOptionFromDropdown", "Option 2");

				} else {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
							"PortfolioScoreCardSelectOptionFromDropdown", 0);
					CommonMethod.selectdropdownVisibletext("PortfolioScoreCardSelectOptionFromDropdown",
							"Option 2 Modified thresholds in polluted regions");

				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPart", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPart",
						"PortfolioScoreCardVerificationUploadAddfeature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndExitbtnUploadPage", 0);
				CommonMethod.Robustclick("SaveAndExitbtnUploadPage");
				CommonMethod.scrolldowntoElement("PortfolioHsrOptnUploadButtonOptionOne");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTablePartIdOne", 0);
				String actualTablePartIdOne = CommonMethod.getattributeValueByTextContent("PortfolioTablePartIdOne")
						.trim();
				CommonMethod.negativesoftassertFieldValid(actualTablePartIdOne, "A01.1", "A01.1 text does't matched");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTableOptionsOne", 0);
				String actualTableOptionsOne = CommonMethod.getattributeValueByTextContent("PortfolioTableOptionsOne")
						.trim();
				CommonMethod.negativesoftassertFieldValid(actualTableOptionsOne, "Option 1",
						"Option 1 text does't matched");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTablePartIdTwo", 0);
				String actualTablePartIdTwo = CommonMethod.getattributeValueByTextContent("PortfolioTablePartIdTwo")
						.trim();
				CommonMethod.negativesoftassertFieldValid(actualTablePartIdTwo, "A01.1", "A01.1 text does't matched");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTableOptionsTwo", 0);
				String actualTableOptionsTwo = CommonMethod.getattributeValueByTextContent("PortfolioTableOptionsTwo")
						.trim();
				CommonMethod.negativesoftassertFieldValid(actualTableOptionsTwo, "Option 2",
						"Option 2 text does't matched");

				if (ProjectType.contains("pilot")) {

					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationCountInOptions",
							0);
					String actualUploadedDocLocationCount = CommonMethod
							.getattributeValueByTextContent("LocationCountInOptions").trim();
					CommonMethod.negativesoftassertFieldValidEquals(actualUploadedDocLocationCount, "2/2 Locations",
							"2/2 Locations count does't matched");
				} else {

					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardUploadedDocLocationCountMTPR",
							0);
					String actualUploadedDocLocationCount = CommonMethod
							.getattributeValueByTextContent("ScorecardUploadedDocLocationCountMTPR").trim();
					CommonMethod.negativesoftassertFieldValidEquals(actualUploadedDocLocationCount, "2/2 Locations",
							"2/2 Locations count does't matched");
				}
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + featureName + " doesn't match");
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

	public void UnderConstructionAsYes() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
		CommonMethod.RobustclickElementVisible("Overviewtab", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationEditButtonUnderConstruction", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationEditButtonUnderConstruction",
				"PortfolioLocationAdditionalButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAdditionalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAdditionalButton",
				"PortfolioLocCertificationLevelDropdown");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocCertificationLevelDropdown", 0);
		CommonMethod.scrolldowntoElement("PortfolioLocCertificationLevelDropdown");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConstructionYes", 0);
		CommonMethod.ClickCheckbox("PortfolioLocationConstructionYes");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConstructionYesPopMsg", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("PortfolioLocationConstructionYesPopMsg").replaceAll("\\s+",
						" "),
				"This action cannot be undone. Are you sure you wish to proceed",
				"ConstructionYes PopMsg doesn't matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationUnderConstructionProceed", 0);
		CommonMethod.negativeAssertElementPresentTrue("PortfolioLocationUnderConstructionProceed",
				"UnderConstruction Proceed button is not present");
		CommonMethod.Robustclick("PortfolioLocationUnderConstructionProceed");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioLocationUnderConstructionProceed", 1);
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstructionYes");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateButton", 0);
		CommonMethod.Robustclick("UpdateButton");
		testlog.info("then User clicks on update button");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("UpdateButton", 1);
		testlog.pass("**User checks Under Construction As Yes successfully**");
	}

	public void validUnderConstructionInLocationTable() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
		CommonMethod.RobustclickElementVisible("Overviewtab", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocationTickMarkInUnderContruction", 1);
		CommonMethod.negativeAssertElementPresentTrue("PortfolioLocationTickMarkInUnderContruction",
				"TickMark In UnderContruction is not visible");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("PortfolioLocationListOccupancy"), "0",
				"LocationList Occupancy Under Construction doesn't match");
		testlog.pass("**User verifies TickMark In UnderContruction column successfully**");
	}

	public void overviewTab() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
		CommonMethod.RobustclickElementVisible("Overviewtab", "table");
	}

	public void targetAndMilestoneTab() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Milestonetab", 0);
		CommonMethod.RobustclickElementVisible("Milestonetab", "MilestoneReviewCycle");
	}

	public void validUnderConstructionfilterInLocationTable(String Count) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.JavascriptClickElement("LocationListTableLoading");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FilterButton", 0);
		CommonMethod.JavascriptClickElement("FilterButton");
		// Yes
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConstructionYes", 0);
		CommonMethod.ClickCheckbox("PortfolioLocationConstructionYes");
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstructionYes");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Applybutton", 0);
		CommonMethod.Robustclick("Applybutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioLocationConstructionYes", 1);
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr",  Integer.parseInt(Count));
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(CommonMethod.ElementSize("PortfolioLocListTr")),
				Count, "Yes UnderContruction Locations table count does't matched");
		if (TestCaseName.equalsIgnoreCase("Portfolio_TC_15C_11_OverviewTabValidUnderConstructionfilterInLocationTable")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException(
					"PortfolioLocationEditButtonUnderConstruction", 1);
			CommonMethod.assertisElementPresentTrue("PortfolioLocationEditButtonUnderConstruction",
					"UnderContruction location id is not visible");
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocationTickMarkInUnderContruction",
					1);
			CommonMethod.assertisElementPresentTrue("PortfolioLocationTickMarkInUnderContruction",
					"TickMark In UnderContruction is not visible");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		// No
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FilterButton", 0);
		CommonMethod.JavascriptClickElement("FilterButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConstructionYes", 0);
		CommonMethod.ClickUnCheckbox("PortfolioLocationConstructionYes");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConstruction", 0);
		CommonMethod.ClickCheckbox("PortfolioLocationConstruction");
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstruction");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Applybutton", 0);
		CommonMethod.Robustclick("Applybutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioLocationConstructionYes", 1);
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 4);
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(CommonMethod.ElementSize("PortfolioLocListTr")),
				"4", "No UnderContruction Locations table count does't matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		testlog.info("**User verifies TickMark In UnderContruction column successfully**");
		testlog.pass("**User verifies UnderContruction filter successfully**");
	}

	public void overviewtabTargetReviewCycle(String locationName) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
		CommonMethod.JavascriptClickElement("Overviewtab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		if (locationName.equalsIgnoreCase("Location One")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationOneEditButtonUnderConstruction",
					0);
			CommonMethod.RobustclickElementVisible("PortfolioLocationOneEditButtonUnderConstruction",
					"PortfolioLocationAdditionalButton");
		}
		if (locationName.equalsIgnoreCase("Location Two")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationTwoEditButtonUnderConstruction",
					0);
			CommonMethod.RobustclickElementVisible("PortfolioLocationTwoEditButtonUnderConstruction",
					"PortfolioLocationAdditionalButton");
		}
		if (locationName.equalsIgnoreCase("Location Three")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationEditButtonUnderConstruction", 0);
			CommonMethod.RobustclickElementVisible("PortfolioLocationEditButtonUnderConstruction",
					"PortfolioLocationAdditionalButton");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAdditionalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAdditionalButton",
				"PortfolioLocationRadiobuttonContainOutdoorSpaces");
		if (locationName.equalsIgnoreCase("Location Two")) {
			CommonMethod.scrolldowntoElement("TargetAchievementEditWerCheckbox");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationUnderConsRadio", 0);
			CommonMethod.click("PortfolioLocationUnderConsRadio");
			if (CommonMethod.isElementsExist("PortfolioLocationUnderConstructionProceed", 20)) {
				CommonMethod.Robustclick("PortfolioLocationUnderConstructionProceed");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioLocationUnderConstructionProceed",
						1);
			}
			CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstructionYes");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateButton", 0);
			CommonMethod.Robustclick("UpdateButton");

		}
	}

	public void MilestonetabTargetReviewCycle(String locationName) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");
		CommonMethod.ScrollUpToElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Milestonetab", 0);
		CommonMethod.RobustclickElementVisible("Milestonetab", "MilestoneReviewCycle");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		if (locationName.equalsIgnoreCase("Location One")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementPrecertificationStatusLoc1", 0);
			String actualPrecertificationStatusLoc1 = CommonMethod
					.getattributeValueByTextContent("TargetAchievementPrecertificationStatusLoc1");
			actualPrecertificationStatusLoc1 = actualPrecertificationStatusLoc1.replaceAll("\\s+", " ").trim();
			System.out.println(actualPrecertificationStatusLoc1);
			CommonMethod.negativesoftassertFieldValid(actualPrecertificationStatusLoc1, "Targeting",
					"Precertification Status: Targeting does not matched ");
		}
		if (locationName.equalsIgnoreCase("Location Two")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditIconRow2", 0);
			CommonMethod.RobustclickElementVisible("TargetAchievementEditIconRow2", "TargetAchievementEditHsrCheckbox");
		}
		if (locationName.equalsIgnoreCase("Location Three")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditIconRow3", 0);
			CommonMethod.RobustclickElementVisible("TargetAchievementEditIconRow3", "TargetAchievementEditHsrCheckbox");
		}
	}

	public void UnderConstructionDisableTargetReviewCycle() throws IOException, InterruptedException {
		// Hsr
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditHsrCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditHsrCheckbox");
		testlog.info("And User checks on Hsr TargetAchievement");
		// Wpr
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditWprCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditWprCheckbox");
		testlog.info("And User checks on Wpr TargetAchievement");
		// Wer
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditWerCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditWerCheckbox");
		testlog.info("And User checks on Wer TargetAchievement");
		// PreCertification
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditPreCertficationCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditPreCertficationCheckbox");
		testlog.info("And User checks on PreCertification TargetAchievement");
		// Certification
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditCertficationCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditCertficationCheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocationDisableTargetReviewCycle", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioLocationDisableTargetReviewCycle",
				"Disable Target ReviewCycle is not visible");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Closepanelicon", 0);
		CommonMethod.Robustclick("Closepanelicon");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("Closepanelicon", 1);
		testlog.pass("**Verifies Disabled checkboxes for Target ReviewCycle successfully**");
	}

	public void ValidateEnableUnderConstructionTargetReviewCycle(String ValidateLocation)
			throws IOException, InterruptedException {
		// Hsr
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditHsrCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditHsrCheckbox");
		testlog.info("And User checks on Hsr TargetAchievement");
		// Wpr
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditWprCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditWprCheckbox");
		testlog.info("And User checks on Wpr TargetAchievement");
		// Wer
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditWerCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditWerCheckbox");
		testlog.info("And User checks on Wer TargetAchievement");
		// PreCertification
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditPreCertficationCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditPreCertficationCheckbox");
		testlog.info("And User checks on PreCertification TargetAchievement");
		// Certification
		CommonMethod.scrolldowntoElement("TargetAchievementEditWerCheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditCertficationCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditCertficationCheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocationEnableTargetReviewCycle", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioLocationEnableTargetReviewCycle",
				"Enable Target ReviewCycle is not visible");
		CommonMethod.ClickCheckbox("PortfolioLocationEnableTargetReviewCycle");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocCertificationLevelDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioLocCertificationLevelDropdown", "Bronze");
		if (ValidateLocation.equalsIgnoreCase("Locatione One")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationUnderConsRadio", 0);
			CommonMethod.ClickCheckbox("PortfolioLocationUnderConsRadio");
			if (CommonMethod.isElementsExist("PortfolioLocationUnderConstructionProceed", 20)) {
				CommonMethod.Robustclick("PortfolioLocationUnderConstructionProceed");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioLocationUnderConstructionProceed",
						1);
			}
			CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstructionYes");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateButton", 0);
		CommonMethod.scrolldowntoElement("UpdateButton");
		CommonMethod.Robustclick("UpdateButton", "PortfolioLocationUnderConsRadio");
		if (ValidateLocation.equalsIgnoreCase("Locatione One")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException(
					"PortfolioLocationOneValidateUnderConstruction", 1);
			CommonMethod.assertisElementPresentTrue("PortfolioLocationOneValidateUnderConstruction",
					"Location One is not visible ");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationOneValidateOccupancy", 0);
			String actualOccupancy = CommonMethod.getText("PortfolioLocationOneValidateOccupancy");
			CommonMethod.negativesoftassertFieldValidEquals(actualOccupancy, "0", "Zero Occupancy does not matched ");
		}
		if (ValidateLocation.equalsIgnoreCase("Location Two")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementPrecertificationStatusLoc2", 0);
			String actualPrecertificationStatusLoc2 = CommonMethod
					.getattributeValueByTextContent("TargetAchievementPrecertificationStatusLoc2");
			actualPrecertificationStatusLoc2 = actualPrecertificationStatusLoc2.replaceAll("\\s+", " ").trim();
			System.out.println(actualPrecertificationStatusLoc2);
			CommonMethod.negativesoftassertFieldValid(actualPrecertificationStatusLoc2, "Targeting",
					"Precertification Status: Targeting does not matched ");
		}
	}

	public void SelectTargetFilter() throws IOException, InterruptedException {
		targetAndMilestoneTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FilterButton", 0);
		CommonMethod.JavascriptClickElement("FilterButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MilestoneReviewCycle", 0);
		CommonMethod.selectdropdownVisibletext("MilestoneReviewCycle", "This review cycle");
		CommonMethod.RobustclickElementVisible("Applybutton", "table");
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 1);
		CommonMethod.ElementSize("PortfolioLocListTr");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(CommonMethod.ElementSize("PortfolioLocListTr")),
				"6", "Milestone Review Cycle filter Locations table count does't matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.JavascriptClickElement("LocationListTableLoading");
	}

	public void OwnershipType() throws IOException, InterruptedException {
		/*
		 * Ownership type filter
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FilterButton", 0);
		CommonMethod.RobustclickElementVisible("FilterButton", "OwnershipWellCore");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnershipWellCore", 0);
		testlog.info("then User again click on  Filter Button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnershipWellCore", 0);
		CommonMethod.RobustclickElementVisible("OwnershipWellCore", "Applybutton");
		testlog.info("And User select OwnershipWellCore checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Applybutton", 0);
		CommonMethod.RobustclickElementVisible("Applybutton", "LocationListTable");
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 2);
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(CommonMethod.ElementSize("PortfolioLocListTr")),
				"2", "Milestone OwnershipType filter Locations table count does't matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.JavascriptClickElement("LocationListTableLoading");
	}

	public void TargetAsGoldFilter(String Count) throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		targetAndMilestoneTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FilterButton", 0);
		CommonMethod.JavascriptClickElement("FilterButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationTargetReviewCycle", 0);
		CommonMethod.ClickCheckbox("PortfolioLocationTargetReviewCycle");
		CommonMethod.RobustclickElementVisible("Applybutton", "table");
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", Integer.parseInt(Count));
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(CommonMethod.ElementSize("PortfolioLocListTr")),Count, "Target As Gold filter Locations table count does't matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.JavascriptClickElement("LocationListTableLoading");

	}

	public void OccupancyFilter(String count) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FilterButton", 0);
		CommonMethod.JavascriptClickElement("FilterButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioFilterOccupancySize", 0);
		CommonMethod.ClickCheckbox("PortfolioFilterOccupancySize");
		CommonMethod.RobustclickElementVisible("Applybutton", "table");
		testlog.info("And User clicks on  Apply button ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", Integer.parseInt(count));
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(CommonMethod.ElementSize("PortfolioLocListTr")),
				count, "Target As Gold filter Locations table count does't matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.JavascriptClickElement("LocationListTableLoading");
	}

	public void AdminAnalyzeDashboard(String SheetName, int rowNum) throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDashboardToggleON", 0);
		CommonMethod.RobustclickElementVisible("PortfolioDashboardToggleON", "PortfolioProjectCount");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioProjectCount", 0);
		String ProjectCount = CommonMethod.getattributeValueByTextContent("PortfolioProjectCount");
		CommonMethod.negativesoftassertFieldValidEquals(ProjectCount, "5",
				"Project Count Text does not matched");
		String CountryCount = CommonMethod.getattributeValueByTextContent("PortfolioCountryCount");
		CommonMethod.negativesoftassertFieldValidEquals(CountryCount, "2",
				"Country Count Text does not matched");
		String SqFtCount = CommonMethod.getattributeValueByTextContent("PortfolioSqFtCount").trim();
		CommonMethod.negativesoftassertFieldValid(SqFtCount, "50000",
				"SqFt Count Text does not matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectReviewEditButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectReviewEditButton", "PortfolioCertificationScoreInput");
		CommonMethod.clearAndSendKey("PortfolioCertificationScoreInput", "45");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioConceptScoresAir", 0);
		CommonMethod.clearAndSendKey("PortfolioConceptScoresAir", "9");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioConceptScoresWater", 0);
		CommonMethod.clearAndSendKey("PortfolioConceptScoresWater", "9");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioConceptScoresNourishment", 0);
		CommonMethod.clearAndSendKey("PortfolioConceptScoresNourishment", "9");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioConceptScoresLight", 0);
		CommonMethod.clearAndSendKey("PortfolioConceptScoresLight", "9");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioConceptScoresMovement", 0);
		CommonMethod.clearAndSendKey("PortfolioConceptScoresMovement", "9");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioConceptScoresThermalComfort", 0);
		CommonMethod.clearAndSendKey("PortfolioConceptScoresThermalComfort", "9");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioConceptScoresSound", 0);
		CommonMethod.clearAndSendKey("PortfolioConceptScoresSound", "9");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioConceptScoresMaterials", 0);
		CommonMethod.clearAndSendKey("PortfolioConceptScoresMaterials", "9");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioConceptScoresMind", 0);
		CommonMethod.clearAndSendKey("PortfolioConceptScoresMind", "9");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioConceptScoresCommunity", 0);
		CommonMethod.clearAndSendKey("PortfolioConceptScoresCommunity", "9");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioConceptScoresInnovation", 0);
		CommonMethod.clearAndSendKey("PortfolioConceptScoresInnovation", "9");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAddRegionBtn", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAddRegionBtn", "PortfolioAddRegionDeleteIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAddRegionDeleteIcon", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioAddRegionDeleteIcon",
				"Add Region Delete Icon is not available ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAddRegionDeleteIcon", 0);
		CommonMethod.Robustclick("PortfolioAddRegionDeleteIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSubmitButton", 0);
		CommonMethod.JavascriptClickElement("PortfolioAdminReviewImportSubmitButton");
		CommonMethod.WaitUntilNumberOfElementTextToBePresentWithException("PortfolioValidateWellScore","45", 300);
		String actualWellScore = CommonMethod.getattributeValueByTextContent("PortfolioValidateWellScore");
		CommonMethod.negativesoftassertFieldValidEquals(actualWellScore, "45", "WellScore: 45 Count does not matched ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioValidateWellScoreDate", 0);
		String actualCurrentDate = CommonMethod.getattributeValueByTextContent("PortfolioValidateWellScoreDate");
		actualCurrentDate = actualCurrentDate.replaceAll("\\s+", " ").trim();
		CommonMethod.negativesoftassertFieldValid(actualCurrentDate, CommonMethod.ValidateDateByFullMonthName(),
				"Date added current date does not matched ");

	}

	public void RefreshAndClickOnUpsellProject() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRefreshButton", 0);
		CommonMethod.click("V2ProjectRefreshButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickOnUpsellProject", 0);
		CommonMethod.click("V2ProjectClickOnUpsellProject");
		CommonMethod.switchToChildTab();
	}

	public void ValidateMRCFromUnderReviewToReadyForReviewInScorecard(String featureName, String SheetName, int rowNum,
			String Commodity) throws Exception {
		List<WebElement> Feature;
		if (Commodity.contains("Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardFeature", 0);
			Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
			Feature = CommonMethod.findElements("RatingFeatureName");
		}
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.Robustclick("PortfolioScorecardFeatureVerificationTab");
				testlog.info("And User clicks on VerificationTab");
				CommonMethod.scrolldowntoElement("Uploadbutton");
				ValidateCommonEnableButtonsInDocumentTable();
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + featureName + " doesn't match");
	}

	public void ValidateMRCFromUnderReviewToReadyForReviewInDocumentLib(String SheetName, int rowNum, String Commodity)
			throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentFilterOption", 0);
		CommonMethod.JavascriptClickElement("PortfolioDocumentFilterOption");
		pathprms.put("Status", "Ready For Review");
		generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, false, false, false, false,
				true, false, false);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocLibraryDeleteEditMenuSecondDoc", 0);
		CommonMethod.click("PortfolioDocLibraryDeleteEditMenuSecondDoc");
		ValidateCommonEnableButtonsInDocumentTable();
	}

	public void ValidateCommonEnableButtonsInDocumentTable() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("EnableEditBtn");
		CommonMethod.negativeAssertElementPresentTrue("EnableEditBtn", "Edit button is disabled ");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("DisabledReplaceBtn");
		CommonMethod.negativeAssertElementPresentTrue("EnableReplaceBtn", "Replace button is disabled ");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("DisabledDeleteBtn");
		CommonMethod.negativeAssertElementPresentTrue("EnableDeleteBtn", "Delete button is disabled ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioScorecardUploadbtn", 1);
		CommonMethod.negativeAssertElementPresentTrue("PortfolioScorecardUploadbtn", "Upload button is disabled ");
	}
	

	public void AlternativeAddOption(String SheetName, int rowNum, String Commodity, String FeatureName)
			throws Exception {
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWERPartFeature", 0);
		Feature = CommonMethod.findElements("V2ProjectWERPartFeature");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						"PortfolioScoreCardAddOptionbutton");
				testlog.info("And User clicks on VerificationTab");
				/** ScoreCard Add Feature option */
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddOptionbutton",
						"PortfolioScoreCardAddButton");
				testlog.info("And User clicks on AddOption button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecrdAlterntives", 0);
				CommonMethod.RobustclickElementVisible("ScorecrdAlterntives", "ScorecardAlternativeAddBtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAlternativeAddBtn", 0);
				CommonMethod.Robustclick("ScorecardAlternativeAddBtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ScorecardAlternativeAddBtn", 1);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAddedOption", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionCloseIcon", 0);
				CommonMethod.Robustclick("WPRAddOptionCloseIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRAddOptionCloseIcon", 1);
				generic.assignLocationGeneric(Commodity, true, false, false, false, false);
				/** Upload Document for Tasks */
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
	}

	public void AlternativeDocumentUploadInDocumentLibrary(String SheetName, int rowNum, String Commodity)
			throws Exception {
		testlog.info("Given User is on Document Library page");

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTaskListTab", 0);
		CommonMethod.RobustclickElementVisible("PortfolioTaskListTab", "PortfolioTaskListPendingTab");
		testlog.info("When User clicks on TaskList Tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTaskListPendingTab", 0);
		CommonMethod.RobustclickElementVisible("PortfolioTaskListPendingTab", "PortfolioDocListA1.5");
		testlog.info("And User clicks on PendingTab");
		CommonMethod.scrolldowntoElement("PortfolioTaskListPendingTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocListA1.5", 0);
		CommonMethod.RobustclickElementVisible("PortfolioDocListA1.5", "PortfolioScoreVerifyUploadVerificationMethod");
		testlog.info("And User clicks on Upload button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadAddfeature", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreVerifyUploadVerificationMethod", 0);
		String VerificationMethod = CommonMethod.getText("PortfolioScoreVerifyUploadVerificationMethod");
		CommonMethod.negativesoftassertFieldValid(VerificationMethod, "Alternative Strategy",
				"Verification Method doesn't match");
		testlog.info("VerificationMethod: " + VerificationMethod);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadFeatureName", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioScorecardUploadFeatureName"),
				"Measure Air Parameters", "Feature Name doesn't match");
		testlog.info("Then User verifies VerificationMethod");
		testlog.info("And User verifies FeatureName");
		testlog.info("And User verifies Assigned location count");
		CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
				"PortfolioScoreCardVerificationSelectFeature");
		CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadAddfeature");
		CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature", "A09.2");
		if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
			CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
		}
		if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
			CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPart", 0);
		CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPart",
				"PortfolioScoreCardVerificationUploadAddfeature");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadAddfeature", 0);
		CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
				"PortfolioScoreCardVerificationAddNote");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RemoveButtonNotDisabled", 1);
		int RemoveButtonNotDisabled = CommonMethod.ElementSize("RemoveButtonNotDisabled");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(RemoveButtonNotDisabled), "1",
				"RemoveButton NotDisabled Count doesn't match");
		generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FeaturefileUpload, false, false, false,
				false);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignLocCbx", 0);
		rc.ScorecardConfirmLocUploadSaveButton();
		testlog.info("And User clicks on Upload button");
		if (CommonMethod.isElementsExist("DocumentAddedToasterMessage", 3)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentAddedToasterMessage", 0);
		}
		testlog.info("Fetching Data from Upload Table");
		testlog.info("And User verifies Document Uploaded successfully toast message");
		testlog.info("And User verifies Document table data");
		CommonMethod.scrolldowntoElement("Uploadbutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
		rc.documentTableEditButton();
		testlog.info("And User clicks on Edit icon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreVerifyUploadVerificationMethod", 0);
		CommonMethod.sendKeys("PortfolioScoreCardVerificationAddNote", "document share with review team.");
		testlog.info("And User enter data to Note field");
		rc.ScorecardUploadUpdateSaveButton();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignLocCbx", 0);
		rc.ScorecardConfirmLocUploadSaveButton();
		testlog.info("And User clicks on Update Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
		CommonMethod.scrolldowntoElement("Uploadbutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTaskListTab", 0);
		CommonMethod.RobustclickElementVisible("PortfolioTaskListTab", "PortfolioTaskListPendingTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TaskFullFilledTab", 0);
		CommonMethod.RobustclickElementVisible("TaskFullFilledTab",
				"PortfolioDocumentVerifyCompleteTask");
		testlog.info("And User clicks on FullFilledTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentVerifyCompleteTask", 0);
		CommonMethod.negativesoftassertPageSource("A01.5", "A01.5 task doesn't match");
		testlog.info("And User verifies feature name in FullFilledTab list");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentVerifyCompleteTask", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTaskListTab", 0);
		CommonMethod.RobustclickElementVisible("PortfolioTaskListTab", "PortfolioTaskListPendingTab");
		CommonMethod.click("PortfolioTaskListPendingTab");
		testlog.pass("**Verifies the Attach and Completed Document for successful**");
	}

	public void ValidateBillingAfterUpsell(String SheetName, int rowNum, String Country)
			throws IOException, InterruptedException {
		rc.ClickBilling();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingStatus", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("BillingStatus").replaceAll("\\s+", " "), "waived",
				"Billing Status doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioBillingRegistrationFee", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod
				.getattributeValueByTextContent("PortfolioBillingRegistrationFee").replaceAll("\\s+", " ").trim(), "$0",
				"Billing RegFee doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioBillingRegDate", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("PortfolioBillingRegDate").replaceAll("\\s+", " ").trim(),
				CommonMethod.ValidateMonDayYearDate(), "Billing Payment date doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectPreBillingPayNowButton");
		CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectPreBillingPayNowButton", "PayNowButton is visible");
		rc.DownloadBillingReceiptAndValidate(SheetName, rowNum, Country);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingStatus", 0);
		CommonMethod.RobustclickElementVisible("BillingStatus", "PortfolioBillingRegDescription");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioBillingRegDescription", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioBillingRegDescription").replaceAll("\\s+", " ").trim(),
						"Waived as part of WELL at Scale program", "Billing Description doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateInvoiceBackButtonBilling", 0);
		CommonMethod.Robustclick("UpdateInvoiceBackButtonBilling");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "V2ProjectStartBuilding");

	}

	public void LocationAccountProjectInformation() throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectInformationButton", 0);
		CommonMethod.RobustclickElementVisible("ProjectInformationButton", "V2ProjectUpdateProjectsName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectInformationButton", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConstructionYes", 0);
		CommonMethod.ClickCheckbox("PortfolioLocationConstructionYes");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConstructionYesPopMsg", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("PortfolioLocationConstructionYesPopMsg").replaceAll("\\s+",
						" "),
				"This action cannot be undone. Are you sure you wish to proceed",
				"ConstructionYes PopMsg doesn't matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationUnderConstructionProceed", 0);
		CommonMethod.negativeAssertElementPresentTrue("PortfolioLocationUnderConstructionProceed",
				"UnderConstruction Proceed button is not present");
		CommonMethod.Robustclick("PortfolioLocationUnderConstructionProceed");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioLocationUnderConstructionProceed", 1);
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstructionYes");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEnterpriseProviders", 0);
		CommonMethod.selectdropdownrandom("V2ProjectEnterpriseProviders");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
		CommonMethod.Robustclick("V2ProjectSaveChangesButton", "PortfolioLocationConstructionYes");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectSaveChangesButton", 1);
		testlog.info("And User clicks on Save button");
		testlog.info("Then User will be redirected to Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "ProjectInformationButton");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConstructionYes", 0);
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstructionYes");
		testlog.info("**Project Information data updated successfully**");
	}

	public void validUpdateUnderConstructionLocationAccountInPortfolioAccount()
			throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
		CommonMethod.RobustclickElementVisible("Overviewtab", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocationTickMarkInUnderContructionLA",
				1);
		CommonMethod.negativeAssertElementPresentTrue("PortfolioLocationTickMarkInUnderContructionLA",
				"TickMark In UnderContruction is not visible");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("PortfolioLocationListOccupancyLA"), "0",
				"LocationList Occupancy Under Construction doesn't match");
		testlog.pass("**User verifies TickMark In UnderContruction column successfully**");
	}

	public void ProjectAdminUpdateLocationAccount(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
		CommonMethod.RobustclickElementVisible("Overviewtab", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationFiveEditButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationFiveEditButton", "PortfolioLocationAdditionalButton");
		CommonMethod.checkTextFieldIsNotEmpty("PortfolioLocationProjectName", "Project Name is empty ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationProjectName", 0);
		CommonMethod.clearAndSendKey("PortfolioLocationProjectName", "WELL at scale Test location 05 Updated new");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationStreetName", 0);
		CommonMethod.clearAndSendKey("LocationStreetName", "Mohan Garden");
		testlog.info("And User enter data to Area field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationCityName", 0);
		CommonMethod.clearAndSendKey("LocationCityName", "Delhi");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationPostalCode", 0);
		CommonMethod.clearAndSendKey("LocationPostalCode", "110059");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAdditionalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAdditionalButton", "PortfolioLocationOccupants");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationOccupants", 0);
		CommonMethod.clearAndSendKey("PortfolioLocationOccupants", "2600");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateButton", 0);
		CommonMethod.Robustclick("UpdateButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("VerifyUpdateToaster", 1);
	}

	public void ProjectAdminValidateLocationAccount() throws IOException, InterruptedException {
		rc.RemoveSpaceAndValidate("getLocationNameFive", "WELL at scale Test location 05 Updated new");
		rc.RemoveSpaceAndValidate("PortfolioEstOccupancyForImportLoc5", "2600");
	}

	public void ValidateLocationAccountForTeamMemberAndPerformanceTestingAgent()
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
		CommonMethod.RobustclickElementVisible("Overviewtab", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationFiveEditButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationFiveEditButton", "PortfolioLocationAdditionalButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocationDisabledProjectName", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioLocationDisabledProjectName", "ProjectName is enabled ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAdditionalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAdditionalButton", "PortfolioLocationOccupants");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocationDisabledOccupantsTextBox", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioLocationDisabledOccupantsTextBox",
				"Occupants textbox is enabled ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationFiveEditButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationFiveEditButton", "PortfolioLocationAdditionalButton");
		CommonMethod.checkTextFieldIsNotEmpty("PortfolioLocationProjectName", "Project Name is empty ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("DisabledCountryName", 1);
		CommonMethod.assertisElementPresentTrue("DisabledCountryName", "DisabledCountryName is enabled ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("DisabledStateName", 1);
		CommonMethod.assertisElementPresentTrue("DisabledStateName", "DisabledStateName is enabled ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("DisabledStreetName", 1);
		CommonMethod.assertisElementPresentTrue("DisabledStreetName", "DisabledStreetName is enabled ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("DisabledCityName", 1);
		CommonMethod.assertisElementPresentTrue("DisabledCityName", "DisabledCityName is enabled ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("DisabledPostalCode", 1);
		CommonMethod.assertisElementPresentTrue("DisabledPostalCode", "DisabledPostalCode is enabled ");
	}

	public void validateUnderContructionRemoveLocInScorecardAssignAndConfirmLoc(String featureName) throws Exception {
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		List<WebElement> Feature;
		if (TestCaseName.contains("RatingScorecard")) {
			Feature = CommonMethod.findElements("V2ProjectWPRPFeature");
		} else {
			Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		}
		testlog.info("Fetching total no. of credits on page");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						"PortfolioScoreCardAddOptionbutton");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddOptionbutton",
						"PortfolioScoreCardAddButton");
				testlog.info("And User clicks on AddOption button");
				/** ScoreCard Add option */
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddButton", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddButton",
						"PortfolioScoreCardVerificationCloseicon");
				testlog.info("And User clicks on Add button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationCloseicon", 0);
				CommonMethod.Robustclick("PortfolioScoreCardVerificationCloseicon");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Assignbutton", 0);
				CommonMethod.JavascriptClickElement("Assignbutton");
				if (TestCaseName.contains("SubsetFilterUnderContruction")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFilters", 0);
					CommonMethod.RobustclickElementVisible("PortfolioScorecardFilters", "OwnerOrgClick");
					testlog.info("And User clicks on Filters Button");
					pfu.validAssignLocationSubsetTypeFilter("I am Subset Name Text Box");
				}
				LocationCount("2");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignLocCbx",
						0);
				CommonMethod.ClickCheckbox("PortfolioScoreCardVerificationAssignLocCbx");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignLocCbx",
						0);
				CommonMethod.Robustclick("WPRAssignSavebtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
				CommonMethod.Robustclick("AssignLocCloseIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
				CommonMethod.scrollDown();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.JavascriptClickElement("Uploadbutton");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationUploadAddfeature", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardUploadOptionName");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentsUpload", 0);
				CommonMethod.uploadFile("DocumentsUpload", FeaturefileUpload,
						"UploadFileVerifyScorecard");
				testlog.info("And User Upload Feature Document");
				rc.ScorecardUploadSaveButton();
				if (TestCaseName.contains("SubsetFilterUnderContruction")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFilters", 0);
					CommonMethod.RobustclickElementVisible("PortfolioScorecardFilters", "OwnerOrgClick");
					testlog.info("And User clicks on Filters Button");
					pfu.validAssignLocationSubsetTypeFilter("I am Subset Name Text Box");
					LocationCount("2");
				} else {
					LocationCount("2");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
					CommonMethod.Robustclick("AssignLocCloseIcon");
					CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
				}
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + featureName + " doesn't match");
	}

	public void LocationCount(String locCount) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("AssignLocationTableTr",Integer.parseInt(locCount));
		int AssignLocTableTrSize = CommonMethod.ElementSize("AssignLocationTableTr");
		String TableTrSize = Integer.toString(AssignLocTableTrSize);
		CommonMethod.negativesoftassertFieldValidEquals(TableTrSize, locCount, "Location TableTrSize doesn't match");
	}

	public void SubsetUnderConstruction() throws Exception {
		portfolio.NavigateLocation();
		portfolio.overviewTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationTableTr");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Subsettab", 0);
		CommonMethod.JavascriptClickElement("Subsettab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableEditIcon", 0);
		CommonMethod.Robustclick("PortfolioAndRatingLocAccDocumentTableEditIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetSaveAndContinueBtnFirstPage", 0);
		CommonMethod.Robustclick("PortfolioSubsetSaveAndContinueBtnFirstPage");
		LocationCount("6");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignLocCbx", 0);
		CommonMethod.ClickCheckbox("PortfolioScoreCardVerificationAssignLocCbx");
		rc.ScorecardConfirmLocUploadSaveButton();
	}

	public void ValidateSubscriptionDetailsInDashboard() throws IOException, InterruptedException {
		rc.RemoveSpaceAndValidate("PortfolioWelcometoWELLText", "Welcome to WELL");
		rc.RemoveSpaceAndValidate("PortfolioYouAreSubscribedText", "You are subscribed");
		ValidateCommonSubscriptionDetails("PortfolioValidateCurrentSubscriptionYear",
				"PortfolioValidateNextRenewalDate");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioValidateIIconLink", 0);
		CommonMethod.click("PortfolioValidateIIconLink");
		CommonMethod.switchToChildTab();
		String actualURL = CommonMethod.getCurrentUrl();
		CommonMethod.negativesoftassertFieldValid(actualURL, "well-at-scale-subscription-renewal",
				"Expected URL does not matched ");
		CommonMethod.switchToParentTab();
	}

	public void ValidateCommonSubscriptionDetails(String LocatorSubscriptionYear, String LocatorRenewalDate)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(LocatorSubscriptionYear, 0);
		String actualCurrentSubscriptionYear = CommonMethod.getattributeValueByTextContent(LocatorSubscriptionYear);
		actualCurrentSubscriptionYear = actualCurrentSubscriptionYear.replaceAll("\\s+", " ").trim();
		System.out.println(actualCurrentSubscriptionYear);
		CommonMethod.negativesoftassertFieldValidEquals(actualCurrentSubscriptionYear, "Year 1",
				"Expected Current Subscription Year does not matched ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(LocatorRenewalDate, 0);
		String actualNextRenewalDate = CommonMethod.getattributeValueByTextContent(LocatorRenewalDate);
		actualNextRenewalDate = actualNextRenewalDate.replaceAll("\\s+", " ").trim();
		System.out.println(actualNextRenewalDate);
		CommonMethod.negativesoftassertFieldValid(actualNextRenewalDate, CommonMethod.ValidateDateYear(),
				"Expected Next Renewal Date does not matched ");
	}
	
	public void adminNavigateAddedLocation(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLCertificationNavBar");
		CommonMethod.RobustclickElementVisible("AdminWELLCertificationNavBar", "AdminV2ProjectId");
		testlog.info("And User clicks on Admin WELL Certification from top menu under Projects");			
		CommonMethod.WaitUntilClickble("AdminV2ProjectId", 60)
				.sendKeys(data.getCellData(SheetName, "LocationProjectID", rowNum).trim());
		testlog.info("And User enter on ProjectID in  AdminV2ProjectId field");
		CommonMethod.RobustclickElementVisible("AdminV2ProjectApplybtn", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchResultIDVerify", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("SearchResultIDVerify"),
				data.getCellData(SheetName, "LocationProjectID", rowNum).trim(), "ProjectID doesn't matches in search");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchResultIDVerify", 0);
		CommonMethod.RobustclickElementVisible("SearchResultIDVerify", "WellV2DashboardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
	}
	
	public void ValidateScorecardAssignLocationCountPostUnderConstruction(String FeatureName, String SheetName, int rowNum, String Commodity,
			Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
			Boolean NoteRequired, Boolean IntentCheckboxRequired, String LocationCount) throws Exception {
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
		if (Commodity.contains("Portfolio")) {
			Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		} else {
			Feature = CommonMethod.findElements("V2ProjectWPRPFeature");
		}
		if (TestCaseName.contains("PortfolioOptnRating")) {
			Feature = CommonMethod.findElements("V2ProjectWPRPFeature");
		}
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
				CommonMethod.RobustclickElementVisible("WPRVerficationTab", "PortfolioTaskListEditLocation");				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTaskListEditLocation", 0);
				CommonMethod.JavascriptClickElement("PortfolioTaskListEditLocation");				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocationTableTr", 0);
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("AssignLocationLoading");
				LocationCount(LocationCount);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
				CommonMethod.Robustclick("AssignLocCloseIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
	
	public void ValidateDocumentsLibAssignLocationCountPostUnderConstruction(String SheetName, int rowNum, String Commodity, String ProjectType,
			String PartId, String LocationCount) throws Exception {
		testlog.info("Given User is on Document page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
		CommonMethod.RobustclickElementVisible("Uploadbutton",
				"V2ProjectPortfolioDocTypeFeatureAudit");
		testlog.info("When User click on Upload button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPortfolioDocTypeFeatureAudit", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectPortfolioDocTypeFeatureAudit", "V2ProjectPortfolioDocType");
		testlog.info("When User click on Upload button");
		CommonMethod.selectdropdownValue("V2ProjectPortfolioDocType", "feature");
		testlog.info("And User select Document Type");
		CommonMethod.selectdropdownVisibletext("PortfolioSelectverificationMethod",
					"Policy and/or Operations Schedule");
		testlog.info("And User select VerificationMethod");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRSelectFeaturePart", 0);
		CommonMethod.scrolldowntoElement("V2ProjectPortfolioDocType");
		CommonMethod.selectdropdownVisibletext("WPRSelectFeaturePart", PartId);
		testlog.info("And User select FeaturePart");
		if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
			CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
		}
		if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
			CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
		}
		CommonMethod.scrolldowntoElement("WPRSelectFeaturePart");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPart", 0);
		CommonMethod.Robustclick("PortfolioScoreCardVerificationAddPart", "WPRSelectFeaturePart");
		generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FeaturefileUpload, false, false, false,
				false);
		LocationCount(LocationCount);
		CommonMethod.refreshBrowser();
	}
	
	public void ValidateDocumentsLibTasksAssignLocationCountPostUnderConstruction(String SheetName, int rowNum, String Commodity, String ProjectType, String Count) throws Exception {
		testlog.info("Given User is on Document page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrUploadButtonTwo", 0);
		CommonMethod.RobustclickElementVisible("HsrUploadButtonTwo",
				"V2ProjectPortfolioDocTypeFeatureAudit");
		testlog.info("When User click on Upload button");
		generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FeaturefileUpload, false, false, false,
				false);
		LocationCount(Count);
		CommonMethod.refreshBrowser();
	}
	
	public void ValidateDocumentsLibDocumentListAssignLocationCountPostUnderConstruction(String count) throws Exception {
		rc.documentTableEditButton();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetSaveAndContinueBtnSecondPage", 0);
		CommonMethod.RobustclickElementVisible("PortfolioSubsetSaveAndContinueBtnSecondPage", "WPRAssignLocCbx");
		LocationCount(count);
		CommonMethod.refreshBrowser();
		
	}
	
	public void UnderConstructionAsNo() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
		CommonMethod.RobustclickElementVisible("Overviewtab", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationEditButtonUnderConstruction", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAutomationLocationEditButtonUnderConstruction",
				"PortfolioLocationAdditionalButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAdditionalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAdditionalButton",
				"PortfolioLocCertificationLevelDropdown");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocCertificationLevelDropdown", 0);
		CommonMethod.scrolldowntoElement("PortfolioLocCertificationLevelDropdown");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConstructionNo", 0);
		CommonMethod.ClickCheckbox("PortfolioLocationConstructionNo");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateButton", 0);
		CommonMethod.Robustclick("UpdateButton");
		testlog.info("then User clicks on update button");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("UpdateButton", 1);
		testlog.pass("**User checks Under Construction As Yes successfully**");
	}
	
	public void uploadDocumentInFeatureForSubscribeLocRating(String FeatureName, String fileName, String partId, String FeatureXpath) throws IOException, InterruptedException {

		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(FeatureXpath, 0);
		Feature = CommonMethod.findElements(FeatureXpath);
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
			CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
			CommonMethod.RobustclickElementVisible("Uploadbutton", "V2ProjectDocUpload");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUpload1", 0);
			CommonMethod.uploadFile("V2ProjectDocUpload1", fileName, "UploadFileVerifyScorecard");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadRemovelink", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect1", 0);
			CommonMethod.click("Multiselect1");
			if (fileName.contains("Feature")) {
				if (!CommonMethod.isElementsExist("PortfolioValidVerificationMethod", 10)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect1", 0);
					CommonMethod.click("Multiselect1");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAccFeature", 0);
				CommonMethod.JavascriptClickElement("PortfolioLocationAccFeature");
				}
			}
			if (fileName.contains("Audit")) {
				if (!CommonMethod.isElementsExist("PortfolioValidVerificationMethodTech", 10)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect1", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAccAudit", 0);
				CommonMethod.click("PortfolioLocationAccAudit");
			}
			}
            if (fileName.contains("Alternative")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAccAlternative", 0);
				CommonMethod.click("PortfolioLocationAccAlternative");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature", 0);
			testlog.info("And User select Partname");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardPartDropdown", 0);
			CommonMethod.selectdropdownVisibletext("PortfolioScoreCardPartDropdown", partId);
			if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
				CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
				testlog.info("And User Select SpaceType");
			}
			if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
				CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
				testlog.info("And User Select Option");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPartbtn", 0);
			CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPartbtn",
					"WERScorecardRemovePart");
			if (CommonMethod.isElementsExist("V2ProjectScorecardVerificationMethodSelectAddPart", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"V2ProjectScorecardVerificationMethodSelectAddPart", 0);
				CommonMethod.Robustclick("V2ProjectScorecardVerificationMethodSelectAddPart",
						"PortfolioScoreCardVerificationSelectFeature");
			}

			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddNote", 0);
			CommonMethod.sendKeys("PortfolioScoreCardVerificationAddNote","TestComment");
			testlog.info("And User enter data to AddNote field");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EnabledUploadbtn", 0);
			CommonMethod.Robustclick("EnabledUploadbtn");
			if (CommonMethod.isElementsExist("DocumentAddedToasterMessage", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DocumentAddedToasterMessage", 1);
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException(PortfolioAndRatingLocAccDocumentTableTr, 1);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRScorecardLanding", 0);
			CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
			CommonMethod.JavascriptClickElement(ele);
			break;
		}
	}
	CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
}
	
	public void ScorecardResponseFilterV2Pilot() throws IOException, InterruptedException {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrScorecardResponseFilter", 0);
		CommonMethod.RobustclickElementVisible("HsrScorecardResponseFilter", "PortFolioResponseYesFilter");	
		//Yes Filter
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioResponseYesFilter", 0);
		CommonMethod.ClickCheckbox("PortFolioResponseYesFilter");
		Thread.sleep(3000); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioResponseYesFilterCount", 0);
		int actualYesFilterCount=CommonMethod.ElementSize("PortFolioResponseYesFilterCount");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(actualYesFilterCount), "2", "Yes Filter Count does not matched ");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
		//No Filter
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrScorecardResponseFilter", 0);
		CommonMethod.RobustclickElementVisible("HsrScorecardResponseFilter", "PortFolioResponseYesFilter");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioResponseNoFilter", 0);
		CommonMethod.ClickCheckbox("PortFolioResponseNoFilter");
		Thread.sleep(3000); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonSelectedPurseNo", 0);
		int actualNoFilterCount=CommonMethod.ElementSize("CommonSelectedPurseNo");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(actualNoFilterCount), "1", "No Filter Count does not matched ");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
		//Maybe Filter
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrScorecardResponseFilter", 0);
		CommonMethod.RobustclickElementVisible("HsrScorecardResponseFilter", "PortFolioResponseYesFilter");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioResponseMaybeFilter", 0);
		CommonMethod.ClickCheckbox("PortFolioResponseMaybeFilter");
		Thread.sleep(3000); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioResponseMaybeFilterCount", 0);
		int actualMaybeFilterCount=CommonMethod.ElementSize("PortFolioResponseMaybeFilterCount");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(actualMaybeFilterCount), "1", "May be Filter Count does not matched ");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
	}
	
	public void ScorecardVerificationFilterV2Pilot() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationFilterBtn", 0);
		CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationFilterBtn", "PortFolioAssuranceDropdown");
		//Architect Filter
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioAssuranceDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortFolioAssuranceDropdown", "Architect");
		Thread.sleep(3000); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardFilterCount", 0);
		int actualArchitectFilterCount=CommonMethod.ElementSize("PortFolioScorecardFilterCount");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(actualArchitectFilterCount), "25", "Architect Filter Count does not matched ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardClearAllBtn", 0);
		CommonMethod.JavascriptClickElement("PortFolioScorecardClearAllBtn");
		rc.ScorecardLoading();
		//Annotated Doc Filter
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardAnnotatedDocDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortFolioScorecardAnnotatedDocDropdown", "Operations Schedule");
		Thread.sleep(3000); 		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardFilterCount", 0);
		int actualAnnotatedDocFilterCount=CommonMethod.ElementSize("PortFolioScorecardFilterCount");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(actualAnnotatedDocFilterCount), "11", "Architect Filter Count does not matched ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardClearAllBtn", 0);
		CommonMethod.JavascriptClickElement("PortFolioScorecardClearAllBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardClearAllBtn", 0);
		CommonMethod.JavascriptClickElement("PortFolioScorecardClearAllBtn");
		rc.ScorecardLoading();
		//On-Site Filter Count	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardOnSiteDropdown", 0);
		CommonMethod.selectdropdownVisibletext("PortFolioScorecardOnSiteDropdown", "Performance Test");
		Thread.sleep(3000); 		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardFilterCount", 0);
		int actualOnSiteFilterCount=CommonMethod.ElementSize("PortFolioScorecardFilterCount");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(actualOnSiteFilterCount), "24", "On-Site Filter Count does not matched ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardClearAllBtn", 0);
		CommonMethod.JavascriptClickElement("PortFolioScorecardClearAllBtn");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
	}
	
	public void ScorecardMoreFiltersV2Pilot() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardMoreFiltersBtn", 0);
		CommonMethod.RobustclickElementVisible("PortFolioScorecardMoreFiltersBtn", "PortFolioScorecardOptimizationsOnToggle");
		/* Off the toggle for Optimizations */		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardOptimizationsOnToggle", 0);
		CommonMethod.RobustclickElementVisible("PortFolioScorecardOptimizationsOnToggle","PortFolioScorecardOptimizationsOffToggle");		
		Thread.sleep(2000);	
		/* Off the toggle for Spaces */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardOptimizationsOnToggle", 0);
		CommonMethod.RobustclickElementVisible("PortFolioScorecardOptimizationsOnToggle","PortFolioScorecardOptimizationsOffToggle");		
		Thread.sleep(3000); 		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScorecardPreconditions", 0);
		int actualPreconditionsCount=CommonMethod.ElementSize("PortFolioScorecardPreconditions");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(actualPreconditionsCount), "59", "Preconditions Filter Count does not matched ");
		CommonMethod.refreshBrowser();
	}
	
	public void locationToTarget(String editXpath) throws Exception {
		testlog.info("Given User is on location tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationPageValid", 0);
		CommonMethod.scrolldowntoElement("LocationPageValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.scrolldowntoElement("table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(editXpath, 0);
		CommonMethod.RobustclickElementVisible(editXpath, "PortfolioLocationAdditionalButton");
		testlog.info("And User clicks on EditIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAdditionalButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAdditionalButton",
				"PortfolioLocCertificationLevelDropdown");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocCertificationLevelDropdown", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetAchievementEditHsrCheckbox", 0);
		CommonMethod.ClickCheckbox("TargetAchievementEditHsrCheckbox");
		testlog.info("And User checks on Hsr TargetAchievement");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUpdateButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioUpdateButton", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("TargetAchievementEditHsrCheckbox",1);
		if (CommonMethod.isElementsExist("VerifyUpdateToaster", 20)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("VerifyUpdateToaster", 0);
			CommonMethod.WaitUntilInVisibility("VerifyUpdateToaster", 180);
			testlog.info("And User verifies Milestone Update Toaster");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "table");
		}
	


	@SuppressWarnings("unchecked")
	public void SignAgreementMainlandChinaPortfolio(String SheetName, int rowNum) throws IOException, InterruptedException {
		String projectId = data.getCellData(SheetName, "ProjectID", rowNum);
		String[] Id = projectId.split("WELLP");
		System.out.println("Id: " + Id[1]);
		@SuppressWarnings("static-access")
		String header = portfolio.PostRequestAuthenticate("UserName", 10);
		testlog.info("Given User set POST service api endpoint");
		testlog.info("When User set Request header and Payload");
		testlog.info("And User Send a POST HTTP request");
		testlog.info("Header Token: " + header);
		@SuppressWarnings("rawtypes")
		HashMap pathprms = new HashMap();
		pathprms.put("portfolio_id", Id[1]);
		Response res = given().log().all().accept("application/json").contentType("application/json")
				.header("Authorization", header).pathParams(pathprms).when()
				.post("portfolio/{portfolio_id}/aggrementSign");
		int StatusCode = res.getStatusCode();
		Assert.assertEquals(StatusCode, 200, "Verifying status code");
		testlog.info("Then User verifies response body");
		testlog.info("And User verifies Status Code");
		testlog.info("And User Complete Sign project agreement");
		testlog.pass("**Verifies project agreement sign successfully**");
		CommonMethod.refreshBrowser();
	}
	

	public void deleteReview(String SheetName, int rowNum, String ReviewViewButton, String Commodity) throws IOException, InterruptedException, ClientApiException {
		
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
	CommonMethod.RobustclickElementVisible("ReviewTab", ReviewViewButton);
	testlog.info("Given User clicks on Review tab");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(ReviewViewButton, 0);
	CommonMethod.RobustclickElementVisible(ReviewViewButton, "PortfolioReturnReview");
	testlog.info("then User clicks on view button");
	if(!Commodity.equalsIgnoreCase("Portfolio")) {
	v2project.ReviewAdminActionsButton();
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReturnReviewDeleteOption", 0);
	CommonMethod.RobustclickElementVisible("ReturnReviewDeleteOption", "V2ProjectPopUpDeleteBtn");
	}
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPopUpDeleteBtn", 0);
	CommonMethod.RobustclickElementVisible("V2ProjectPopUpDeleteBtn", "PortfolioDeleteReviewPopup");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDeleteReviewPopup", 0);
	CommonMethod.Robustclick("PortfolioDeleteReviewPopup");
	CommonMethod.WaitUntilNumberOfElementToBePresentLessThan(ReviewViewButton, 1);
	
}

	public void ClickOnLocationNameLinkAndValidateDuplicateLocationName(String SheetName, int rowNum,
			String EnterLocationName) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("getLocationNameFive", 0);
		CommonMethod.JavascriptClickElement("getLocationNameFive");	
		CommonMethod.switchToChildTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AccountInformationTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminLocationProjectName", 0);
		CommonMethod.clearAndSendKey("PortfolioAdminLocationProjectName", EnterLocationName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDuplicateLocationErrorMsg", 0);
		rc.RemoveSpaceAndValidate("PortfolioDuplicateLocationErrorMsg", "Duplicate Project Name");
		CommonMethod.switchToParentTab();
	}
	
	public void ImportLocationAndValidateDuplicateLocationName(String Commodity, String ImportFileName) throws Exception {
		testlog.info("Given User is on Dashboard page");
		System.out.println("ImportFileName: " + ImportFileName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "PortfolioLocationLanding");
		testlog.info("When User clicks on LocationsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationLanding", 0);
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationsImportButton", 0);
		Thread.sleep(3000);
		CommonMethod.refreshBrowser();
		CommonMethod.RobustclickElementVisible("PortfolioLocationsImportButton", "PortfolioUploadLocationButton");
		if (Commodity.equalsIgnoreCase("Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationSelectCard", 0);
			CommonMethod.RobustclickElementVisible("LocationSelectCard", "PortfolioUploadFileNextButton");
			testlog.info("And User clicks on Import Button");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadLocationButton", 0);
		CommonMethod.scrolldowntoElement("PortfolioUploadLocationButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadLocationButton", 0);
		CommonMethod.uploadFile("PortfolioUploadLocationButton", ImportFileName, "UploadFileVerifyScorecard");
		testlog.info("And User upload Excel Location List Document");
		if (Commodity.equalsIgnoreCase("Portfolio")) {
			if (CommonMethod.isElementsExist("PortfolioUploadFileNextButton", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadFileNextButton", 0);
				CommonMethod.click("PortfolioUploadFileNextButton");
			}
			if (CommonMethod.isElementsExist("PortfolioUnmatchFieldcbx", 10)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUnmatchFieldcbx", 0);
				CommonMethod.ClickCheckbox("PortfolioUnmatchFieldcbx");
				testlog.info("And User checks the Unmatch checkbox");
			}
		}
		if (CommonMethod.isElementsExist("PortfolioUploadFileNextButton", 5)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadFileNextButton", 0);
			CommonMethod.RobustclickElementVisible("PortfolioUploadFileNextButton", "PortfolioFinishImportButton");
		}		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("getLocationNameFive", 0);
		CommonMethod.moveToElement("getLocationNameFive");
		rc.RemoveSpaceAndValidate("PortfolioValidateImportDuplicateLocationErrorMsg", "Duplicate location name");
		CommonMethod.refreshBrowser();
	}

	public void validateNAAdminFieldInTeamAccess() throws Exception {

	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
	CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectProjectNameInput");
	testlog.info("When User clicks on EditTab");
	CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectAdminFieldsButton");
	 CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectAdminFieldsButton","V2ProjectAdminFieldsButton is visible");
}
	
	public void ValidateUpdateForProjectsWithLocationsInChinaModalDuringRegistration(String SheetName, int rowNum, String Engagement_level, String Country,
			String ProjectName) throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL certified account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WellAtScaleNavBar");
		CommonMethod.RobustclickElementVisible("WellAtScaleNavBar", "PortfolioCreateAccountButton");
		testlog.info("When User clicks on WELL At Scale from top menu under Projects");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCreateAccountButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioCreateAccountButton", "PortfolioAccountName");
		testlog.info("And User clicks on Create Account Button");
		String AccountName = ProjectName + CommonMethod.randomNumber(8000000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccountName", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccountName", 0);
		CommonMethod.sendKeys("PortfolioAccountName", AccountName);
		data.setCellData(SheetName, "AccountName", rowNum, AccountName);
		CommonMethod.scrolldowntoElement("PortfolioAccountName");
		rc.SelectOwnerOrg(SheetName, rowNum);
		CommonMethod.verifyDropdownValues("HsrWprEditOrgIndustry", "OrganizationIndustry");
		data.setCellData(SheetName, "OrgName", rowNum, CommonMethod.getText("OrgName"));
		testlog.info("OrganizationName: " + data.getCellData(SheetName, "OrgName", rowNum));
		CommonMethod.verifyDropdownValues("V2ProjectUpdatesCountryRegion", "Country");
		CommonMethod.selectdropdownVisibletext("OrgIndustry", "Hospitals");
		testlog.info("And User select OrgIndustry");
		data.setCellData(SheetName, "OrgIndustry", rowNum, CommonMethod.getSelectedDropdownValue("OrgIndustry"));
		testlog.info("OrgIndustry: " + data.getCellData(SheetName, "OrgIndustry", rowNum));
		CommonMethod.ClickCheckbox("PortfolioNotSureRadio");
		testlog.info("And User checks the NotSure checkbox");
		CommonMethod.sendKeys("PortfolioNumberOfLocation", "15");
		data.setCellData(SheetName, "Location", rowNum, CommonMethod.getattributeValue("PortfolioNumberOfLocation"));
		testlog.info("PortfolioNumberOfLocation: " + data.getCellData(SheetName, "Location", rowNum));
		CommonMethod.sendKeys("PortfolioEstimatedNumberOfLocation", "10");
		testlog.info("And User enter data to Estimated Number Of Location");
		data.setCellData(SheetName, "EstimatedNumberOfLocation", rowNum,
				CommonMethod.getattributeValue("PortfolioEstimatedNumberOfLocation"));
		testlog.info("EstimatedNumberOfLocation: " + data.getCellData(SheetName, "EstimatedNumberOfLocation", rowNum));
		String Area = CommonMethod.randomNumberBetweenRanges(100, 50000);
		CommonMethod.sendKeys("PortfolioGrossAreaSQFT", Area);
		testlog.info("And User enter data to AreaSQFT");
		data.setCellData(SheetName, "AreaSQFT", rowNum, CommonMethod.getattributeValue("PortfolioGrossAreaSQFT"));
		testlog.info("PortfolioGrossAreaSQFT: " + data.getCellData(SheetName, "AreaSQFT", rowNum));
		CommonMethod.scrolldowntoElement("PortfolioPrimarlyLocated");
		CommonMethod.RobustclickElementVisible("PortfolioPrimarlyLocated", "SelectOwnerOrg");
		CommonMethod.WaitUntilClickble("SelectOwnerOrg", 10);
		CommonMethod.RobustclickElementVisible("SelectOwnerOrg", "PortfolioSpaceType");
		testlog.info("And User select PrimarlyLocated");
		rc.ValidateSpaceTypeDropdown("PortfolioSpaceTypeCount", "28");
		CommonMethod.RobustclickElementVisible("PortfolioSpaceType", "PortfolioSelectSpaceType");
		CommonMethod.RobustclickElementVisible("PortfolioSelectSpaceType", "PortfolioOwnerCountry");
		testlog.info("And User select SpaceType");
		CommonMethod.selectdropdownValue("PortfolioOwnerCountry", "CN");
		testlog.info("And User select OwnerCountry");	
		data.setCellData(SheetName, "Country", rowNum, CommonMethod.getSelectedDropdownValue("PortfolioOwnerCountry"));
		testlog.info("PortfolioOwnerCountry: " + data.getCellData(SheetName, "Country", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioOwnerState", 0);
		CommonMethod.selectdropdownVisibletext("PortfolioOwnerState","Beijing");
		testlog.info("And User select OwnerState");
		data.setCellData(SheetName, "State", rowNum, CommonMethod.getSelectedDropdownValue("PortfolioOwnerState"));
		testlog.info("PortfolioOwnerState: " + data.getCellData(SheetName, "State", rowNum));
		String ProjectAddress1 = USfaker.address().streetAddress();
		String ProjectCity = USfaker.address().cityName();
		String PostalCode = USfaker.address().zipCode();
		CommonMethod.sendKeys("PortfolioOwnerStreetAddress", ProjectAddress1);
		testlog.info("And User enter data to OwnerStreet Address");
		data.setCellData(SheetName, "Street", rowNum, CommonMethod.getattributeValue("PortfolioOwnerStreetAddress"));
		testlog.info("PortfolioOwnerStreetAddress: " + data.getCellData(SheetName, "Street", rowNum));
		CommonMethod.sendKeys("PortfolioOwnerCity", ProjectCity);
		testlog.info("And User enter data to City");
		data.setCellData(SheetName, "City", rowNum, CommonMethod.getattributeValue("PortfolioOwnerCity"));
		testlog.info("PortfolioOwnerCity: " + data.getCellData(SheetName, "City", rowNum));
		CommonMethod.sendKeys("PortfolioOwnerPostalCode", PostalCode);
		testlog.info("And User enter data to PostalCode");
		data.setCellData(SheetName, "PostalCode", rowNum, CommonMethod.getattributeValue("PortfolioOwnerPostalCode"));
		testlog.info("PortfolioOwnerPostalCode: " + data.getCellData(SheetName, "PostalCode", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioCreateAccountSubmit", 0);
		CommonMethod.JavascriptClickElement("PortfolioCreateAccountSubmit");
		testlog.info("And User clicks on Submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioGetUpdatePojectText", 0);
        rc.RemoveSpaceAndValidate("PortfolioGetUpdatePojectText", "Update for projects with locations in China");
		}
	
	public void AddLegalEntities(String AddMoreBtnLocator, String LegalEntityTextBoxLocator, String TextboxText) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(AddMoreBtnLocator, 0);
		CommonMethod.JavascriptClickElement(AddMoreBtnLocator);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(LegalEntityTextBoxLocator, 0);
		CommonMethod.sendKeys(LegalEntityTextBoxLocator, TextboxText);
	}
	
	public void DeleteLegalEntities(String DeleteBtnLocator) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(DeleteBtnLocator, 0);
		CommonMethod.Robustclick(DeleteBtnLocator);
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan(DeleteBtnLocator, 1);
	}
	
	public void AddAndUpdateLegalEntitiesInLocationsTab() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationOwnerButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationOwnerButton", "PortfolioLocationLegalEntityOptions");
		CommonMethod.scrolldowntoElement("SubmitButton");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationLegalEntityOptions", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationLegalEntityOptions", "PortfolioLocationAddLegalEntityOptionsTwo");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAddLegalEntityOptionsTwo", 0);
		CommonMethod.JavascriptClickElement("PortfolioLocationAddLegalEntityOptionsTwo");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAddLegalEntityOptionsOne", 0);
		CommonMethod.JavascriptClickElement("PortfolioLocationAddLegalEntityOptionsOne");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAddNewLegalEntityBtn", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAddNewLegalEntityBtn","PortfolioEnterpriseLegalEntityTextBoxOne");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEnterpriseLegalEntityTextBoxOne", 0);
		CommonMethod.clearAndSendKey("PortfolioEnterpriseLegalEntityTextBoxOne", "Test Legal Entity Three");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConfirmButton", 0);
		CommonMethod.JavascriptClickElement("PortfolioLocationConfirmButton");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationLegalEntityOptions", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationLegalEntityOptions", "PortfolioLocationAddLegalEntityOptionsTwo");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAddLegalEntityOptionsThree", 0);
		CommonMethod.JavascriptClickElement("PortfolioLocationAddLegalEntityOptionsThree");		
	}
	
	public void ValidateLegalEntities() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationAutomationEditButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationAutomationEditButton", "PortfolioLocationProjectName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationOwnerButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationOwnerButton", "PortfolioLocationLegalEntityOptions");
		CommonMethod.scrolldowntoElement("V2ProjectReviewUpdate");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationValidateTestLegalEntity", 0);
		rc.RemoveSpaceAndValidate("PortfolioLocationValidateTestLegalEntity", "Test Legal Entity");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationValidateTestLegalEntityTwo", 0);
		rc.RemoveSpaceAndValidate("PortfolioLocationValidateTestLegalEntityTwo", "Test Legal Entity Two");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationValidateTestLegalEntityThree", 0);
		rc.RemoveSpaceAndValidate("PortfolioLocationValidateTestLegalEntityThree", "Test Legal Entity Three");
	}
	
	public void AccountInformationAddAndValidateLegalEntity() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AccountInformationTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccountInformationTab", 0);
		CommonMethod.RobustclickElementVisible("AccountInformationTab", "PortfolioGoal");
		int actualLegalEntityCount = CommonMethod.ElementSize("PortfolioLocationValidLegalEntityCount");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(actualLegalEntityCount), "4", "Legal Entity Count does not matched ");
		AddLegalEntities("PortfolioEnterpriseAddMoreBtn", "PortfolioEnterpriseLegalEntityTextBoxFive", "Test Legal Entity Five");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSaveButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioSaveButton", "WellV2DashboardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AccountInformationTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccountInformationTab", 0);
		CommonMethod.RobustclickElementVisible("AccountInformationTab", "PortfolioGoal");
		int actualLegalEntityCountValid = CommonMethod.ElementSize("PortfolioLocationValidLegalEntityCount");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(actualLegalEntityCountValid), "5", "Legal Entity Count does not matched ");
	}
	
	public void AdminFieldsAddAndValidateLegalEntity() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAdminFieldsButton", "PortfolioCoachingContacts");
		int actualLegalEntityCount = CommonMethod.ElementSize("PortfolioLocationValidLegalEntityCount");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(actualLegalEntityCount), "5", "Legal Entity Count does not matched ");
		AddLegalEntities("PortfolioEnterpriseAddMoreBtn", "PortfolioEnterpriseLegalEntityTextBoxSix", "Test Legal Entity Six");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSaveButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioSaveButton", "WellV2DashboardTab");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAdminFieldsButton", "PortfolioCoachingContacts");
		int actualLegalEntityCountValid = CommonMethod.ElementSize("PortfolioLocationValidLegalEntityCount");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(actualLegalEntityCountValid), "6", "Legal Entity Count does not matched ");
	}
	
	public void UploadDocumentsInLocationAccount(String SheetName, int rowNum, String Commodity, String ProjectType) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("getLocationNameFive", 0);
		CommonMethod.JavascriptClickElement("getLocationNameFive");	
		CommonMethod.switchToChildTab();
		rc.clickDocument();
		pfu.UploadFeatureDocV2ProjectInsideDocument(SheetName, rowNum,"Feature",FeaturefileUpload,ProjectType, Commodity); 
		CommonMethod.switchToParentTab();
	}
	
	public void ValidateDocumentsStatusAsUnderReview(String DocumentsStatus) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentFilterOption", 0);
		CommonMethod.RobustclickElementVisible("PortfolioDocumentFilterOption", "WERTableFullDocumentsListStatus");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERTableFullDocumentsListStatus", 0);
		List<WebElement> AllDocuments = CommonMethod.findElements("WERTableFullDocumentsListStatus");
		for(int i=0; i<AllDocuments.size(); i++) {
		String actualDocStatus = AllDocuments.get(i).getText();
		CommonMethod.negativesoftassertFieldValid(actualDocStatus, DocumentsStatus, "Under Review Documents Status does not matched in Documents Lib ");
		}
	}
	
	public void StoreApplicableVersionInExcel(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardProjectTypeVersion", 0);
		String getFullVersionName = CommonMethod.getattributeValueByTextContent("ScorecardProjectTypeVersion");
		getFullVersionName= getFullVersionName.replaceAll("\\s+", " ").trim();
		String[] getVersion = getFullVersionName.split(",");
		String SplitAndGetVersion = getVersion[1];	
		SplitAndGetVersion= SplitAndGetVersion.replaceAll("\\s+", " ").trim();
		System.out.println("SplitId: "+SplitAndGetVersion);
		data.setCellData(SheetName, "StoreVersion", rowNum, SplitAndGetVersion);
	}
	
	public void FetchAndValidateApplicableVersion(String SheetName, int rowNum,
			String VersionLocator) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(VersionLocator, 0);
		String getFullVersionName = CommonMethod.getattributeValueByTextContent(VersionLocator);
		getFullVersionName= getFullVersionName.replaceAll("\\s+", " ").trim();
		String[] getVersion = getFullVersionName.split(",");
		String actualVersion = getVersion[1];	
		actualVersion= actualVersion.replaceAll("\\s+", " ").trim();
		System.out.println("SplitId: "+actualVersion);
		String expectedVersion = data.getCellData(SheetName, "StoreVersion", rowNum);
		CommonMethod.negativesoftassertFieldValidEquals(actualVersion, expectedVersion, "Applicable Version does not matched ");
	}
	
	public void ValidateLocationsExternalProjectAsUnderReview(String SheetName, int rowNum, String ExpernalProjectStatus) throws IOException, InterruptedException {
		testlog.info("Given User is on LocationsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "LocationPageValid");
		testlog.info("When User clicks on LocationTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationPageValid", 0);
		CommonMethod.scrolldowntoElement("LocationPageValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.RobustclickElementVisible("AddButton", "PortfolioLocationExternalProjectOption");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationExternalProjectOption", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationExternalProjectOption", "PortfolioLocationSelectProjectName");
		testlog.info("And User clicks on Add Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationExternalProjectWarning", 0);
		rc.RemoveSpaceAndValidate("PortfolioLocationExternalProjectWarning", ExpernalProjectStatus);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSelectProjectName", 0);
		CommonMethod.RobustclickElementVisible("PortfolioLocationSelectProjectName", "PortfolioLocationEnterProjectName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationEnterProjectName", 0);
		CommonMethod.JavascriptClickElement("PortfolioLocationEnterProjectName");
		CommonMethod.sendKeys("PortfolioLocationEnterProjectName", data.getCellData("V2Project", "ProjectID", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationSelectOptionProjectName", 0);
		CommonMethod.Robustclick("PortfolioLocationSelectOptionProjectName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConfirmButton", 0);
		Thread.sleep(3000);
		CommonMethod.JavascriptClickElement("PortfolioLocationConfirmButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("getToasterMessage", 0);
	    rc.RemoveSpaceAndValidate("getToasterMessage", "This account is currently under review.");
	    CommonMethod.refreshBrowser();
	}
	
	public void ValidateMigratedDocumentTags(String expectedTagName) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateCertificationTags", 0);
		List<WebElement> CertificationTags = CommonMethod.findElements("ValidateCertificationTags");
		for(int i=0; i<CertificationTags.size(); i++) {
			String actualCertificationTag = CertificationTags.get(i).getText();
			CommonMethod.negativesoftassertFieldValid(actualCertificationTag, expectedTagName, "Tags does not matched ");
		}
	}
	
	
	public void ValidateNewTagAbsenceInDocumentsTab() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortFolioValidateNewTagAbsence");
		CommonMethod.assertisElementPresentFalse("PortFolioValidateNewTagAbsence", "New Tag in document table is not present ");
	}
	

	public void ValidateAuditDocsInScorecard(String featureName, String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		testlog.info("Fetching total no. of credits on page");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.click(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab", "EditLocations");
				testlog.info("And User clicks on VerificationTab");	
				CommonMethod.scrolldowntoElement("EditLocations");
				ValidateAuditDocs(SheetName, rowNum, "PortfolioScorecardAuditLabel", "PortfolioScorecardAuditIicon", "PortfolioScorecardAuditIiconText", "PortfolioScorecardAuditIiconLearnMoreURL");
				CommonMethod.JavascriptClickElement(ele);
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + featureName + " doesn't match");
	}
	
	public void ValidateAuditDocs(String SheetName, int rowNum, String AuditLabelLocator,
	  String AuditLabelIiconLocator, String AuditIiconTextLocator, String LearnMoreLocator) throws Exception {
		rc.RemoveSpaceAndValidate("PortfolioScorecardAuditLabel", "Audit");
		CommonMethod.moveToElement("PortfolioScorecardAuditIicon");
		rc.RemoveSpaceAndValidate("PortfolioScorecardAuditIiconText", "Audited documents allow you to provide documentation");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardAuditIiconLearnMoreURL", 0);
		CommonMethod.JavascriptClickElement("PortfolioScorecardAuditIiconLearnMoreURL");
		CommonMethod.switchToChildTab();
		String actualCurrentURL = CommonMethod.getCurrentUrl();
		CommonMethod.negativesoftassertFieldValid(actualCurrentURL, "audit-documentation", "Current URL does not matched ");
		CommonMethod.switchToParentTab();
	}
	
	public void ValidateAuditDocsInDocumentsLibrary(String SheetName, int rowNum) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTaskListAllTab", 0);
		CommonMethod.RobustclickElementVisible("PortfolioTaskListAllTab", "PortfolioScorecardAuditLabel");
		ValidateAuditDocs(SheetName, rowNum, "PortfolioScorecardAuditLabel", "PortfolioScorecardAuditIicon", "PortfolioScorecardAuditIiconText", "PortfolioScorecardAuditIiconLearnMoreURL");
	}
	
	public void ValidateAuditDocsInLocationsTab(String SheetName, int rowNum) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRLocationVerifyLocationResult", 0);
		CommonMethod.JavascriptClickElement("WPRLocationVerifyLocationResult");
		CommonMethod.switchToChildTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignedTasksTab", 0);
		CommonMethod.RobustclickElementVisible("AssignedTasksTab", "PortfolioTaskListAllTab");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTaskListAllTab", 0);
		CommonMethod.RobustclickElementVisible("PortfolioTaskListAllTab", "PortfolioScorecardAuditLabel");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioPartIdInAllTabOfDocumentsLibrary", 0);	
		rc.RemoveSpaceAndValidate("PortfolioScorecardAuditLabel", "Audit");
	    CommonMethod.moveToElement("PortfolioScorecardAuditIicon");
		rc.RemoveSpaceAndValidate("PortfolioScorecardAuditIiconText", "Audited documents allow you to provide documentation");
		CommonMethod.switchToParentTab();
	}
	
	public void EditAdminSelectAchieveAndMarkNineMonthBeforeDate(String selectStatus, String Status, String saveAchievementAdminTabXpath, String EditAchieveDate) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		testlog.info("When User clicks on EditTab");
		rc.navigateAchievementAdminTab();
		testlog.info("And User clicks on AdminFields Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(selectStatus, 0);
		CommonMethod.selectdropdownVisibletext(selectStatus, Status);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(EditAchieveDate, 0);
		CommonMethod.RobustclickElementVisible(EditAchieveDate, "DatePickerOkButton");
		Thread.sleep(1000);
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
		rc.saveAchievementAdminTab(saveAchievementAdminTabXpath);
	}
	
	public void ValidateAttentionBanner() throws Exception {
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 20)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioValidadeAttentionBanner");
		CommonMethod.assertisElementPresentFalse("PortfolioValidadeAttentionBanner", "Attention Banner is showing ");
	}
	public void ValidatePortfolioProjectStatus(String SheetName, int rowNum) throws IOException, InterruptedException {
		/*
		 * Admin Review
		 */
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLAtScaleNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminWELLAtScaleNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminWELLAtScaleNavBar", "SearchByID");
		testlog.info("When User clicks on WELL AtScaleNavBar from top menu under Projects");
		CommonMethod.WaitUntilClickble("SearchByID", 60)
				.sendKeys(data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter data to PortfolioId");
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		rc.RemoveSpaceAndValidate("PortfolioAdminRegistrationStatus", "subscribed");
		rc.RemoveSpaceAndValidate("PortfolioAdminAgreementStatus", "Signed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("SearchResultIDVerify"),
				data.getCellData(SheetName, "ProjectID", rowNum), "Portfolio ID doesn't matched with excel in search");
		Thread.sleep(2000);
		CommonMethod.RobustclickElementVisible("SearchResultIDVerify", "ReviewTab");
		testlog.info("And User clicks on PortfolioAdminId");
	}
	
	public void ValidateRefreshButton() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAdminReviewImportModalRefreshBtn", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioAdminReviewImportModalRefreshBtn", "Refresh Button is not visible ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportModalRefreshBtn", 0);
		CommonMethod.click("PortfolioAdminReviewImportModalRefreshBtn");		
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAdminReviewImportModalFormLoaded", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioAdminReviewImportModalFormLoaded", "Form is not visible ");
	}
	
	public void PortfolioEquityValidateOldDocumentId(String FeatureName, String SheetName, int rowNum, String Commodity,
			String FileName, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
			Boolean NoteRequired, Boolean IntentCheckboxRequired) throws Exception {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EquityTab", 0);
		CommonMethod.RobustclickElementVisible("EquityTab", "PortfolioEquityScorecard");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEquityScorecard", 0);
		CommonMethod.RobustclickElementVisible("PortfolioEquityScorecard", "ApplicableVersion");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 20)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		List<WebElement> Feature=CommonMethod.findElements("V2ProjectWPRPFeature");
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
			    String getDocumentID = data.getCellData("Wer", "WerOldDocumentId", 2);
			    System.out.println("My DocumentID: "+getDocumentID);
				CommonMethod.sendKeys("HsrDocumentIdTextBox", getDocumentID);	
		        rc.ScorecardUploadSaveButton();			        
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocFirstChildCbx", 0);
				CommonMethod.ClickCheckbox("WPRAssignLocFirstChildCbx");
				rc.ScorecardConfirmLocUploadSaveButton();				        				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.scrolldowntoElement("Uploadbutton");
				pathprms.put("Status", "Ready For Review");
				generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, false, false, false,
						false, true, true, false);				
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WERDocumentIdCopyBtn");
				CommonMethod.assertisElementPresentFalse("WERDocumentIdCopyBtn", "Document Copy button is present ");				
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
}




