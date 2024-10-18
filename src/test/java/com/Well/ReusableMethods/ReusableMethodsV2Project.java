package com.Well.ReusableMethods;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.zaproxy.clientapi.core.ClientApiException;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;
import io.restassured.response.Response;

public class ReusableMethodsV2Project extends BaseClass {
	static JSONObject param = new JSONObject();
	static String HeaderSheetName = "Login";
	static String header;
	String PortfolioAndRatingLocAccDocumentTable ="PortfolioAndRatingLocAccDocumentTable";
	String PortfolioAndRatingLocAccDocumentTableTr ="PortfolioAndRatingLocAccDocumentTableTr";
	String ScorecardPortfolioAccountDeleteDisableIcon ="ScorecardPortfolioAccountDeleteDisableIcon";
	
	
	@SuppressWarnings("static-access")
	public void RegisterV2Project(String SheetName, int rowNum, String Type, String Country, String ProjectName)
			throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL certified account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLCertificationNavBar");
		CommonMethod.RobustclickElementVisible("WELLCertificationNavBar", "V2Projectstartav2projectbtn");
		testlog.info("When User clicks on WELL Certification from top menu under Projects");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectstartav2projectbtn", 0);
		CommonMethod.Robustclick("V2Projectstartav2projectbtn");
		testlog.info("When User clicks on Startv2project button");
		CommonMethod.Robustclick("V2ProjectstartProjectcontinuebtn");
		testlog.info("And User clicks on continue button");		
		CommonMethod.RobustclickElementVisible("V2ProjectnicknameContinuebtn", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Project nickname is required.", "Project Name Error Mismatch");
		testlog.info(
				"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		String AccountName = ProjectName + CommonMethod.randomNumber(8000000);
		testlog.info("V2 ProjectName is: " + AccountName);
		data.setCellData(SheetName, "ProjectName", rowNum, AccountName);
		CommonMethod.sendKeys("V2Projectprojectnickname", AccountName);
		testlog.info("And User enter data to project nickname");
		CommonMethod.RobustclickElementVisible("V2ProjectnicknameContinuebtn", "V2ProjectlocationContinuebtn");
		testlog.info("And User clicks on continue button");
		if (Type.equalsIgnoreCase("WELLCore")) {
			CommonMethod.RobustclickElementVisible("V2ProjectLocationBackbtn", "V2Projectprojectnickname");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2Projectprojectnickname"),
					AccountName, "ProjectName Error Mismatch");
			CommonMethod.RobustclickElementVisible("V2ProjectnicknameContinuebtn", "V2ProjectlocationContinuebtn");
			testlog.info("And User verifies the added details about you by clicking on back button");
		}
		CommonMethod.RobustclickElementVisible("V2ProjectlocationContinuebtn", "StreetName");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Country is required.", "Country Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Street is required.", "Street Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("City is required.", "City Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Postal Code is required.", "Postal Code Error Mismatch");
		testlog.info(
				"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		String ProjectAddress = USfaker.address().streetAddress();
		String ProjectCity = USfaker.address().cityName();
		String PostalCode = USfaker.address().zipCode();
		testlog.info("Street: " + ProjectAddress);
		testlog.info("City: " + ProjectCity);
		testlog.info("Postalcode: " + PostalCode);
		CommonMethod.sendKeys("StreetName", ProjectAddress);
		data.setCellData(SheetName, "Street", rowNum, CommonMethod.getattributeValue("StreetName"));
		CommonMethod.sendKeys("CityName", ProjectCity);
		data.setCellData(SheetName, "City", rowNum, CommonMethod.getattributeValue("CityName"));
		CommonMethod.sendKeys("PostalCode", PostalCode);
		data.setCellData(SheetName, "PostalCode", rowNum,
				CommonMethod.getattributeValue("PostalCode"));
		if(Country.equalsIgnoreCase("CN")) {
			CommonMethod.selectdropdownValue("CountryDropdown", Country);
			data.setCellData(SheetName, "Country", rowNum, CommonMethod.getSelectedDropdownValue("CountryDropdown"));
			CommonMethod.selectdropdownVisibletext("ProjectlocationState","Macao");
			data.setCellData(SheetName, "State", rowNum, CommonMethod.getSelectedDropdownValue("ProjectlocationState"));
			testlog.info("Country: " + data.getCellData(SheetName, "Country", rowNum));
			testlog.info("State: " + data.getCellData(SheetName, "State", rowNum));
		}
		if (!Country.equalsIgnoreCase("CN") && !Country.equalsIgnoreCase("MainlandChina")) {
			rc.SelectCountryAndState(Country, SheetName, rowNum);
		}
		if(Country.equalsIgnoreCase("MainlandChina")) {
			CommonMethod.selectdropdownValue("CountryDropdown", "CN");
			data.setCellData(SheetName, "Country", rowNum, CommonMethod.getSelectedDropdownValue("CountryDropdown"));
			CommonMethod.selectdropdownVisibletext("ProjectlocationState","Beijing");
			data.setCellData(SheetName, "State", rowNum, CommonMethod.getSelectedDropdownValue("ProjectlocationState"));
			testlog.info("Country: " + data.getCellData(SheetName, "Country", rowNum));
			testlog.info("State: " + data.getCellData(SheetName, "State", rowNum));
		}
		testlog.info(
				"And User enter data to First name, Last name, Country, State, Street address, City and Postal Code, Phone number fields");
		CommonMethod.RobustclickElementVisible("V2ProjectlocationContinuebtn", "V2ProjectareaContinuebtn");
		testlog.info("And User clicks on continue button");
		if (Type.equalsIgnoreCase("WELLCore")) {
			CommonMethod.RobustclickElementVisible("V2ProjectDetailsBackbtn", "StreetName");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("CountryDropdown"),
					data.getCellData(SheetName, "Country", rowNum), "Country Name Error Mismatch");			
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("ProjectlocationState"),
					data.getCellData(SheetName, "State", rowNum), "State Name Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("StreetName"),
					ProjectAddress, "Street Name Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("CityName"),
					ProjectCity, "City Name Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PostalCode"),
					PostalCode, "PostalCode Error Mismatch");
			testlog.info("And User verifies the added details about you by clicking on back button");
			CommonMethod.RobustclickElementVisible("V2ProjectlocationContinuebtn", "V2ProjectareaContinuebtn");
			testlog.info("And User clicks on continue button");
		}
		String Area = CommonMethod.randomNumberBetweenRanges(100, 85000);
		CommonMethod.clearAndSendKey("V2ProjectareaSize", Area);
		testlog.info("And User enter data to Area Size");
		data.setCellData(SheetName, "Area", rowNum, CommonMethod.getattributeValue("V2ProjectareaSize"));
		testlog.info("Area: " + data.getCellData(SheetName, "Area", rowNum));
		CommonMethod.RobustclickElementVisible("V2ProjectareaContinuebtn", "V2ProjectspacetypeContinuebtn");
		if (Type.equalsIgnoreCase("WELLCore")) {
			CommonMethod.RobustclickElementVisible("V2ProjectTypeBackbtn", "V2ProjectareaSize");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectareaSize"), Area,
					"Area Error Mismatch");
			CommonMethod.RobustclickElementVisible("V2ProjectareaContinuebtn", "V2ProjectspacetypeContinuebtn");
			testlog.info("And User clicks on continue button");
		}
		CommonMethod.RobustclickElementVisible("V2ProjectspacetypeContinuebtn", "V2ProjectspaceType");
		CommonMethod.negativesoftassertPageSource("You need at least 1 checkbox.", "CheckBox Error Mismatch");
		CommonMethod.ClickCheckbox("V2ProjectspaceType");
		CommonMethod.WaitUntilVisibility("V2ProjectspacetypeContinuebtn", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectspacetypeContinuebtn", "V2Projectwellcorecertification");
		if (Type.equalsIgnoreCase("WELLCore")) {
			CommonMethod.RobustclickElementVisible("V2ProjectSelectProjectBackbtn", "V2ProjectspacetypeContinuebtn");
			CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectspaceType");
			testlog.info("And User verifies the added details about you by clicking on back button");
			CommonMethod.RobustclickElementVisible("V2ProjectspacetypeContinuebtn", "V2ProjectSelectProjectBackbtn");
			testlog.info("And User clicks on continue button");
		}
		if (Type.equalsIgnoreCase("WELLCore")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectwellcorecertification", 0);
			CommonMethod.Robustclick("V2Projectwellcorecertification");
			if (CommonMethod.isElementsExist("HsrIframe", 5)) {
				CommonMethod.WaitUntilPresence("HsrIframe", 180);
				CommonMethod.switchToFrame("HsrIframe");
				CommonMethod.WaitUntilPresence("HsrCloseCard", 60);
				CommonMethod.Robustclick("HsrCloseCard");
				CommonMethod.switchToParentFrame();
			}
		}
		if (Type.equalsIgnoreCase("WELLCertification")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectwellCertification", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectwellCertification", "V2ProjectownershipOflocation");
			testlog.info("And User select on wellCertification project");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectownershipOflocation", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectownershipContinuebtn", "V2ProjectownershipOflocation");
			CommonMethod.negativesoftassertPageSource("Ownership type is required.", "CheckBox Error Mismatch");
			testlog.info(
					"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
			CommonMethod.ClickCheckbox("V2ProjectownershipOflocation");
			testlog.info("And User checks the ownership Of location checkbox");
			CommonMethod.RobustclickElementVisible("V2ProjectownershipContinuebtn", "DatePickerButton");
			testlog.info("And User clicks on continue button");
		}
		testlog.info("Then User will be redirected to Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EnrollTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("StoreId", 0);
		String getId = CommonMethod.getText("StoreId");
		String[] stringArray = getId.split(": ");
		String getProjectId = stringArray[1].trim();
		data.setCellData(SheetName, "ProjectID", rowNum, getProjectId);
		if (!ModuleName.contains("Common")) {
			if (!Country.contains("MainlandChina")) {
			commonAPI.getProjectIdByProjectNumAPI(SheetName, rowNum);
			}
		}
		if (Country.contains("MainlandChina")) {
			commonAPI.getMainlandChinaProjectIdByProjectNumAPI(SheetName, rowNum);	
		}				
		testlog.info("And User Storing the Registered id  in excel");
		testlog.pass("**Verifies the Registration successful**");
	}

	public void SearchV2ById(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLCertificationNavBar");
		CommonMethod.RobustclickElementVisible("WELLCertificationNavBar", "V2ProjectClearFilterList");
		testlog.info("When User clicks on WELL Certification from top menu under Projects");
		testlog.info("ProjectId:" + data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchFilterClear", 0);
		CommonMethod.Robustclick("SearchFilterClear", "TopMenuValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectId", 0);
		CommonMethod.sendKeys("V2ProjectId", data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter data to V2ProjectId field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectApplybtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectApplybtn", "EnrollSearchPagination");
		testlog.info("When User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int var = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var), "1", "V2 Search Table row count failed");
		testlog.info("Then User verifies Search filter V2Project Count");
	}

	public void ClickSearchV2ProjectById(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchResultIDVerify", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("SearchResultIDVerify"),
				data.getCellData(SheetName, "ProjectID", rowNum), "Project Id doesn't matches in search");
		testlog.info("And User verifies Search V2Project By Id");
		CommonMethod.RobustclickElementVisible("SearchResultIDVerify", "WellV2DashboardTab");
		testlog.info("When User clicks on V2ProjectId");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.info("Then User will be redirected to Dashboard page");
		
	}

	public void SearchV2ProjectById(String SheetName, int rowNum) throws IOException, InterruptedException {
		SearchV2ById(SheetName, rowNum);
		ClickSearchV2ProjectById(SheetName, rowNum);
		testlog.pass("**Verifies the Search V2Project ByID successfully**");
	}

	public void SearchV2ProjectFilterByStatus(String SheetName, int rowNum, String Status)
			throws IOException, InterruptedException {
		testlog.info("StatusList: " + Status);
		CommonMethod.verifyDropdownValues("V2ProjectIdStatusList", "V2ProjectStatus");
		CommonMethod.verifyDropdownValues("V2ProjectSubscribedToWELL", "V2ProjectSubscribedToWellAtScale");
		CommonMethod.selectdropdownVisibletext("V2ProjectIdStatusList", Status);
		CommonMethod.RobustclickElementVisible("V2ProjectApplybtn", "V2ProjectStatusResultList");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStatusResultList", 0);
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getattributeValueByTextContent("V2ProjectStatusResultList").toLowerCase().trim(), Status.toLowerCase(),
				"Authenticted User Status Search doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int var = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var), "1", "Authenticted User V2 Search failed");
		testlog.info("Then Authenticted User verifies Search filter V2Project " + Status.toLowerCase());
		testlog.pass("**Verifies the Search V2Project By StatusName successfully for Authenticted User**");
	}

	public void SearchV2ProjectFilters(String SheetName, int rowNum) throws IOException, InterruptedException {
		/*
		 * FilterByProjectName
		 */
		testlog.info("Given User is on V2ProjectId List page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectIdNameList", 0);
		testlog.info("ProjectName: " + data.getCellData(SheetName, "ProjectName", rowNum));
		CommonMethod.sendKeys("V2ProjectIdNameList", data.getCellData(SheetName, "ProjectName", rowNum));
		testlog.info("When User enter data to ProjectName");
		CommonMethod.RobustclickElementVisible("V2ProjectApplybtn", "V2ProjectProjectNameResultList");
		testlog.info("And User clicks on Apply Button");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectProjectNameResultList"),
				data.getCellData(SheetName, "ProjectName", rowNum), "V2 ProjectName Search failed");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int ProjectName = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(ProjectName), "1", "V2 Search failed");
		testlog.info("Then User verifies Search filter by ProjectName");
		/*
		 * FilterByCountryName
		 */
		testlog.info("Given User is on V2ProjectId List page");
		testlog.info("CountryList: " + data.getCellData(SheetName, "Country", rowNum));
		CommonMethod.WaitUntilVisibility("V2ProjectIdCountryList", 120);
		CommonMethod.verifyDropdownValues("V2ProjectIdCountryList", "Country");
		CommonMethod.selectdropdownVisibletext("V2ProjectIdCountryList",
				data.getCellData(SheetName, "Country", rowNum));
		testlog.info("When User select the CountryName");
		CommonMethod.RobustclickElementVisible("V2ProjectApplybtn", "V2ProjectCountryResultList");
		testlog.info("When User clicks on Apply Button");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectCountryResultList"),
				data.getCellData(SheetName, "Country", rowNum), "V2 CountryList Search failed");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int Country = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(Country), "1", "V2 Search failed");
		testlog.info("Then User verifies Search filter by CountryName");
		/*
		 * FilterByOrganizationName
		 */
		testlog.info("Given User is on V2ProjectId List page");
		testlog.info("OwnerOrganizationName: " + data.getCellData(SheetName, "OrgName", rowNum));
		CommonMethod.sendKeys("V2ProjectIdOwnerOrganizationList", data.getCellData(SheetName, "OrgName", rowNum));
		testlog.info("When User enter data to OrgName");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectOrgResultList"),
				data.getCellData(SheetName, "OrgName", rowNum), "V2 CountryList Search failed");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int OrgName = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(OrgName), "1", "V2 Search failed");
		testlog.pass("Then User verifies Search filter for V2Project");
	}

	public void EnrollV2ProjectById(String SheetName, int rowNum, String Type, String Country, boolean is_Leed)
			throws IOException, InterruptedException {		
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EnrollTab", 0);
		CommonMethod.RobustclickElementVisible("EnrollTab", "V2ProjectprojectOwnerContinuebtn");
		testlog.info("And User clicks on EnrollTab");
		CommonMethod.RobustclickElementVisible("V2ProjectprojectOwnerContinuebtn", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		testlog.info(
				"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		CommonMethod.negativesoftassertPageSource("Organization is required.", "Organization Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner name* is required.", "Owner name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner email* is required.", "Owner email Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Organization Industry is required.",
				"Organization Industry Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Consultant is required.", "Enterprise Providers Error Mismatch");
		CommonMethod.negativesoftassertPageSource(
				"Are you planning to undergo construction or renovation?* is required.", "construction Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Estimated date of document submission* is required",
				"Estimated Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Anticipated date of construction completion* is required.",
				"Anticipated date Error Mismatch");
		CommonMethod.refreshBrowser();
		rc.SelectOwnerOrg(SheetName, rowNum);
		data.setCellData(SheetName, "OrgName", rowNum, CommonMethod.getText("OrgName"));
		testlog.info("OrganizationName: " + data.getCellData(SheetName, "OrgName", rowNum));
		String Ownername = USfaker.address().firstName();
		String Email = USfaker.internet().emailAddress();
		String Phoneno = USfaker.number().digits(10);
		String PostalCode = USfaker.address().zipCode();
		testlog.info("Ownername: " + Ownername);
		CommonMethod.sendKeys("OwnerName", Ownername);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("OwnerName"));
		testlog.info("OwnerEmail: " + Email);
		CommonMethod.sendKeys("V2ProjectownerEmail", Email);
		data.setCellData(SheetName, "OwnerEmail", rowNum, CommonMethod.getattributeValue("V2ProjectownerEmail"));
		CommonMethod.sendKeys("V2Projectownerphone", Phoneno);
		data.setCellData(SheetName, "PhoneNum", rowNum, CommonMethod.getattributeValue("V2Projectownerphone"));	
		CommonMethod.verifyDropdownValues("OrgIndustry", "OrganizationIndustry");
		CommonMethod.selectdropdownrandom("OrgIndustry");
		testlog.info("And User select Organisation Sector");
		data.setCellData(SheetName, "OrgIndustry", rowNum, CommonMethod.getSelectedDropdownValue("OrgIndustry"));
		rc.SelectEnterpriseProviders(SheetName, rowNum);
		CommonMethod.sendKeys("V2Projectorganizationwebsite",
				"https://test-nuxt.wellcertified.com/projects/v2/2202266385/register");
		data.setCellData(SheetName, "Website", rowNum, CommonMethod.getattributeValue("V2Projectorganizationwebsite"));
		CommonMethod.sendKeys("V2ProjectorganizationOverview", Ownername);
		data.setCellData(SheetName, "Overview", rowNum,
				CommonMethod.getattributeValue("V2ProjectorganizationOverview"));
		testlog.info(
				"And User enter data to Organisation Name, Ownername, Country, State, Street address, City and Postal Code, Phone number fields");
		CommonMethod.ClickCheckbox("V2ProjectconstructionOrrenovation");
		testlog.info("And User checks constructionOrrenovation checkbox");
		if (is_Leed == true) {
			CommonMethod.ClickCheckbox("V2ProjectLeed");
			CommonMethod.WaitUntilPresence("V2ProjectLeedId", 30);
			CommonMethod.WaitUntilPresence("V2ProjectLeedAndWELLCheckbox", 30);
			CommonMethod.sendKeys("V2ProjectLeedId", "987654321");
			data.setCellData(SheetName, "LeedCertificationID", rowNum,
					CommonMethod.getattributeValueByTextContent("V2ProjectLeedId"));
			CommonMethod.sendKeys("V2ProjectLeedName", "TEST LEED Project");
			data.setCellData(SheetName, "LeedCertificationName", rowNum,
					CommonMethod.getattributeValueByTextContent("V2ProjectLeedName"));
		}
		CommonMethod.RobustclickElementVisible("ClickDate", "DateOkbtn");
		CommonMethod.RobustclickElementVisible("DateOkbtn", "DatePickerButton2");
		testlog.info("And User select Estimate Date");
		CommonMethod.RobustclickElementVisible("DatePickerButton2", "DateOkbtn");
		testlog.info("And User select Anticipate Date");
		CommonMethod.click("DateOkbtn");
		if (Type.equalsIgnoreCase("WELLCore")) {
			CommonMethod.verifyDropdownValues("V2ProjectSector", "SectorDiscounts");
			CommonMethod.selectdropdownValue("V2ProjectSector", "government/municipal-buildings");
			testlog.info("And User select Organisation Sector");
			data.setCellData(SheetName, "MarketSectorName", rowNum,
					CommonMethod.getSelectedDropdownValue("V2ProjectSector"));
			testlog.info("MarketSector: " + data.getCellData(SheetName, "MarketSectorName", rowNum));
		}
		CommonMethod.RobustclickElementVisible("V2ProjectprojectOwnerContinuebtn",
				"V2ProjectprojectaddressContinuebtn");
		if (Type.equalsIgnoreCase("WELLCore")) {
			CommonMethod.RobustclickElementVisible("V2ProjectEnrollBackbtn", "V2ProjectprojectOwnerContinuebtn");
			CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectconstructionOrrenovation");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("OrgName"),
					data.getCellData(SheetName, "OrgName", rowNum), "OrgName Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("OwnerName"),
					data.getCellData(SheetName, "OwnerName", rowNum), "OwnerName Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectownerEmail"), Email,
					"OwnerEmail Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2Projectownerphone"), Phoneno,
					"PhoneNum Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getSelectedDropdownValue("V2ProjectEnterpriseProviders"),
					data.getCellData(SheetName, "EnterpriseProvidersName", rowNum), "EnterpriseProvidersName Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("OrgIndustry"),
					data.getCellData(SheetName, "OrgIndustry", rowNum), "OrgIndustry Error Mismatch");
			testlog.info("And User verifies the added details about you by clicking on back button");
			CommonMethod.RobustclickElementVisible("V2ProjectprojectOwnerContinuebtn",
					"V2ProjectprojectaddressContinuebtn");
			testlog.info("And User clicks on continue button");
		}
		CommonMethod.RobustclickElementVisible("V2ProjectprojectaddressContinuebtn", "V2ProjectprojectaddressCountry");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Country is required.", "Country Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Street is required.", "Street Error Mismatch");
		CommonMethod.negativesoftassertPageSource("City is required.", "City Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Postal Code is required.", "CPostal Code Error Mismatch");
		String ProjectAddress1 = USfaker.address().streetAddress();
		String ProjectCity = USfaker.address().cityName();
		testlog.info(
				"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		testlog.info("Organization Address:" + ProjectAddress1);
		testlog.info("Organization City:" + ProjectCity);
		testlog.info("Organization Postalcode:" + PostalCode);
		CommonMethod.verifyDropdownValues("V2ProjectprojectaddressCountry", "Country");
		CommonMethod.verifyDropdownValues("V2ProjectowneraddressCountry", "Country");
		if(Country.equalsIgnoreCase("MainlandChina")) {
		CommonMethod.selectdropdownValue("V2ProjectowneraddressCountry", "CN");
		CommonMethod.WaitUntilVisibility("V2ProjectowneraddressState", 10);
		CommonMethod.selectdropdownValue("V2ProjectowneraddressState", "Beijing");	
		} else {
		CommonMethod.selectdropdownValue("V2ProjectowneraddressCountry", Country);
		CommonMethod.WaitUntilVisibility("V2ProjectowneraddressState", 10);
		CommonMethod.selectdropdownrandom("V2ProjectowneraddressState");
		}
		CommonMethod.sendKeys("V2ProjectowneraddressStreet", ProjectAddress1);
		CommonMethod.sendKeys("V2ProjectowneraddressCity", ProjectCity);
		CommonMethod.sendKeys("V2ProjectowneraddressPostalcode", PostalCode);
		data.setCellData(SheetName, "OwnerStreet", rowNum,
				CommonMethod.getattributeValue("V2ProjectowneraddressStreet"));
		data.setCellData(SheetName, "OwnerCity", rowNum, CommonMethod.getattributeValue("V2ProjectowneraddressCity"));
		data.setCellData(SheetName, "OwnerPostalCode", rowNum,
				CommonMethod.getattributeValue("V2ProjectowneraddressPostalcode"));
		testlog.info(
				"And User enter data to Country, OwnerState, OwnerStreet address, OwnerCity and OwnerPostal Code, Phone number fields");
		CommonMethod.ClickCheckbox("V2Projectisthisapublicproject");
		testlog.info("And User checks the public project checkbox");
		CommonMethod.RobustclickElementVisible("V2ProjectprojectaddressContinuebtn", "WellV2DashboardTab");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "DatePickerButton");
		
		testlog.info("And User clicks on DashboardTab");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
        	CommonMethod.Robustclick("ScorecardBannerClose");
        }
		CommonMethod.WaitUntilVisibility("DatePickerButton", 300);
		CommonMethod.RobustclickElementVisible("DatePickerButton", "V2ProjectDatePopupWeekday");
		CommonMethod.WaitUntilVisibility("V2ProjectnextYearbtn", 120);
		CommonMethod.Robustclick("V2ProjectnextYearbtn", "V2ProjectDatePopupWeekday");
		CommonMethod.WaitUntilVisibility("DatePickerNextYear", 120);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("DatePickerNextYear", "DatePickerOkButton");
		Thread.sleep(1000);
		CommonMethod.WaitUntilPresence("DatePickerOkButton", 120);
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "V2ProjectDatePopupWeekday");
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "DatePickerUpdateButton");
		CommonMethod.WaitUntilPresence("DatePickerUpdateButton", 60);
		CommonMethod.RobustclickElementVisible("DatePickerUpdateButton", "DatePickerContinueJourneyButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectcontinuethejourneybtn", 0);
		CommonMethod.JavascriptClickElement("V2Projectcontinuethejourneybtn");
		CommonMethod.refreshBrowser();
		CommonMethod.refreshBrowser();
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 20)) {
        	CommonMethod.Robustclick("ScorecardBannerClose");
        }
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartBuilding", 0);
		testlog.info("And User select Estimate Date");
		testlog.pass("**Verifies the Enrolling successfully**");
		
	}

	public void ClickBilling() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BiilingTab", 0);
		CommonMethod.RobustclickElementVisible("BiilingTab", "V2ProjectPreBillingPayNowButton");
		testlog.info("When User clicks on BiilingTab");
		CommonMethod.RobustclickElementVisible("V2ProjectPreBillingPayNowButton", "BillingLanding");
		testlog.info("And User clicks on PayNow Button");
		Thread.sleep(3000);
		testlog.pass("**Nagavited to Billing successfully**");
	}

	public void DownloadBillingReceiptAndValidate(String SheetName, int rowNum, String Country)
			throws IOException, InterruptedException {
		testlog.info("Given User on Receipt Page");
		Thread.sleep(3000);
		// String Amount = data.getCellData(SheetName, "EnrollFee", rowNum);
		String Email = "frontendauthenticated";
		String Address = null;
		if (Country.equalsIgnoreCase("US")) {
			Address = "New York, NY 10014";
		} else {
			Address = "IWBI China(HK) Limited";
		}
		String[] ProjDetails = { Email, Address };
		CommonMethod.WaitUntilVisibility("DownloadReceipt", 120);
		CommonMethod.click("DownloadReceipt");
		testlog.info("When User clicks on DownloadReceipt button");
		Thread.sleep(4000);
		boolean fileExistsReturnValue = CommonMethod.isFileDownloaded();
		testlog.info("Then User verifies Downloaded file");
		String fileExists = Boolean.toString(fileExistsReturnValue);
		CommonMethod.negativesoftassertFieldValid(fileExists, "true", "Downloaded Billing Receipt file doesn't Exist");
		if (CommonMethod.isFileDownloaded()) {
			File path = new File(downloadPath);
			File[] files = path.listFiles();
			for (File file : files) {
				String ReceiptContent = CommonMethod.extractPDFContent(file.toString());
				for (String s : ProjDetails) {
					CommonMethod.negativesoftassertFieldValid(ReceiptContent, s,
							"Billing Receipt Project " + s + "details doesn't match");
				}
				break;
			}
			testlog.info("And User verifies Address");
			testlog.info("And User verifies Email");
		}
		testlog.pass("**Verifies Download Billing Receipt And Validate successfully**");
	}

	public void AgreementV2ProjectById(String SheetName, int rowNum) throws IOException, InterruptedException {
		// testlog.info("Given User is on Dashboard page");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		
	}

	public void CalculatePricingV2Project(String SheetName, int rowNum, String Type)
			throws IOException, InterruptedException {
		long Enroll;
		long Programfee;
		Double Area = Double.valueOf(data.getCellData(SheetName, "Area", rowNum));
		if (Type.equalsIgnoreCase("WellCore")) {
			Enroll = Math.round(3000);
			data.setCellData(SheetName, "EnrollFee", rowNum, String.valueOf(Enroll));
			Double InterimProgramfee = Area * 0.09;
			if (InterimProgramfee <= 8000) {
				Programfee = (8000 - Math.round(8000 * .25));
			} else if (InterimProgramfee >= 98000) {
				Programfee = (98000 - Math.round(98000 * .25));
			} else {
				Programfee = Math.round(InterimProgramfee - (InterimProgramfee * .25));
			}
			data.setCellData(SheetName, "ProgramFee", rowNum, String.valueOf(Programfee));
			long TotalFee = Programfee + Enroll;
			data.setCellData(SheetName, "TotalFee", rowNum, String.valueOf(TotalFee));
		}

		if (Type.equalsIgnoreCase("WELLCertification")) {
			rowNum = rowNum - 1;
			data.setCellData(SheetName, "NoDiscEnrollFee", rowNum, "3000");
			Double InterimProgramfee1 = Area * 0.16;
			if (InterimProgramfee1 <= 8000) {
				Programfee = 8000;
			} else if (InterimProgramfee1 >= 98000) {
				Programfee = 98000;
			} else {
				Programfee = Math.round(InterimProgramfee1);
			}
			data.setCellData(SheetName, "NoDiscProgramFee", rowNum, String.valueOf(Programfee));
			long NoDiscTotalFee = Programfee + 3000;
			data.setCellData(SheetName, "NoDiscTotalFee", rowNum, String.valueOf(NoDiscTotalFee));
		}
	}

	public void PricingSectorDiscountValidationV2Project(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PricingTab", 0);
		CommonMethod.RobustclickElementVisible("PricingTab", "V2ProjectPricingLanding");
		testlog.info("When User clicks on PricingTab");
		CommonMethod.WaitUntilPresence("V2ProjectPricingLanding", 300);
		testlog.info("Then User will be redirected to Pricing page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPricingEnrollFee", 0);
		String PricingEnrollFee = CommonMethod.getText("V2ProjectPricingEnrollFee").replaceAll("USD", "")
				.replaceAll("\\W", "");
		testlog.info("PricingEnrollFee: " + PricingEnrollFee);
		CommonMethod.negativesoftassertFieldValidEquals(PricingEnrollFee,
				data.getCellData(SheetName, "EnrollFee", rowNum), "PricingEnrollFee data doesn't match");
		String DiscountName = CommonMethod.getText("V2ProjectPricingSectorDiscountName").split(":")[1].toString()
				.trim();
		testlog.info("Sector DiscountName:" + DiscountName);
		DiscountName = DiscountName.replace("(-25%)", "").trim();
		CommonMethod.negativesoftassertFieldValidEquals(DiscountName,
				data.getCellData(SheetName, "MarketSectorName", rowNum),
				"Pricing Sector DiscountName data doesn't match");
		String ProgramFee = CommonMethod.getText("V2ProjectPricingProgramFee").replaceAll("USD", "").replaceAll("\\W",
				"");
		testlog.info("ProgramFee: " + ProgramFee);
		CommonMethod.negativesoftassertFieldValidEquals(ProgramFee, data.getCellData(SheetName, "ProgramFee", rowNum),
				"Pricing ProgramFee data doesn't match");
		String TotalFee = CommonMethod.getText("V2ProjectPricingTotalWellFees").replaceAll("USD", "").replaceAll("\\W",
				"");
		testlog.info("TotalFee: " + TotalFee);
		CommonMethod.negativesoftassertFieldValidEquals(TotalFee, data.getCellData(SheetName, "TotalFee", rowNum),
				"Pricing Total WellFees data doesn't match");
		testlog.info("And User verifies the EnrollFee");
		testlog.info("And User verifies the ProgramFee");
		testlog.info("And User verifies the TotalFee");
		testlog.info("And User verifies the Sector DiscountName");
		testlog.info("And User verifies the Sector Discount Percentage");
		testlog.pass("**Verifies Pricing Sector Discount successfully**");
	}

	public void PricingNoDiscountValidationV2Project(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PricingTab", 0);
		CommonMethod.RobustclickElementVisible("PricingTab", "V2ProjectPricingLanding");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPricingLanding", 0);
		rowNum = rowNum - 1;
		String PricingEnrollFee = CommonMethod.getText("V2ProjectPricingEnrollFee").replaceAll("USD", "")
				.replaceAll("\\W", "");
		testlog.info("PricingEnrollFee: " + PricingEnrollFee);
		CommonMethod.negativesoftassertFieldValidEquals(PricingEnrollFee,
				data.getCellData(SheetName, "NoDiscEnrollFee", rowNum), "PricingEnrollFee data doesn't match");
		String ProgramFee = CommonMethod.getText("V2ProjectPricingProgramFee").replaceAll("USD", "").replaceAll("\\W",
				"");
		testlog.info("ProgramFee: " + ProgramFee);
		CommonMethod.negativesoftassertFieldValidEquals(ProgramFee,
				data.getCellData(SheetName, "NoDiscProgramFee", rowNum), "Pricing ProgramFee data doesn't match");
		String TotalFee = CommonMethod.getText("V2ProjectPricingNoTotalWellFees").replaceAll("USD", "")
				.replaceAll("\\W", "");
		testlog.info("TotalFee: " + TotalFee);
		CommonMethod.negativesoftassertFieldValidEquals(TotalFee, data.getCellData(SheetName, "NoDiscTotalFee", rowNum),
				"Pricing Total WellFees data doesn't match");
		testlog.pass("**Verifies Pricing No Discount successfully**");
	}

	public void VerifyReviewErrorMessageByMinScorecardPurseYes(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 20)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		
		CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "V2ProjectStartBuilding");
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartBuilding", 0);
		CommonMethod.Robustclick("V2ProjectStartBuilding");
		testlog.info("When User clicks on StartBuilding");
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 10);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseYes", 0);
		CommonMethod.Robustclick("PortFolioScoreCardPurseYes");
		testlog.info("When User clicks on YesPurse button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseYes1", 0);
		CommonMethod.Robustclick("PortFolioScoreCardPurseYes1");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("When User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "ValidReviewErrorMessagev2");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ValidReviewErrorMessagev2"),
				"Oops! It looks like your scorecard is incomplete. Please ensure all features are marked as either a YES or NO and then resubmit.",
				"Review Mark Error message doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidReviewErrorMessage2", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ValidReviewErrorMessage2"),
				"Oops! It looks like your scorecard is incomplete.", "Review Mark Error message doesn't match");
		testlog.info("And User verifies Review ErrorMessage By Minimum selecting ScorecardPurseYes");
		testlog.pass("And User verifies Review ErrorMessage By Minimum selecting ScorecardPurseYes");
	}

	public void VerifyReviewErrorMessageBySelectingMoreThan110ScorecardPurseYes()
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("When User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "ValidReviewErrorMessage110Points");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidReviewErrorMessage110Points", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ValidReviewErrorMessage110Points"),
				"You are targeting more than the maximum 110 points allowed",
				"110 points Review Error message doesn't match");
		testlog.info("And User verifies Review ErrorMessage By selecting more than 110 points ScorecardPurseYes");
		testlog.pass("And User verifies Review ErrorMessage By Minimum selecting ScorecardPurseYes");
	}

	public void Verify12PointErrorMessageInScorecardConcept()
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAirconcept", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAirconcept", "ApplicableVersion");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidReviewErrorMessage110Points", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ValidReviewErrorMessage110Points"),
				"12 points allowed for this concept.",
				"12 points Review Error message doesn't match");
		testlog.info("And User verifies Concept warning ErrorMessage By selecting more than 12 points ScorecardPurseYes");
	}
	
	public void ScorecardV2ProjectById() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "V2ProjectStartBuilding");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartBuilding", 0);
	}

	public void BuildScorecardV2ProjectById(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartBuilding", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectStartBuilding", "ScorecardLoading");
		testlog.info("When User clicks on StartBuilding");
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 10);
		testlog.info("Then User will be redirected to Scorecard page");
		testlog.pass("**Verfies Scorecard Page successfully**");
	}

	public void VerifyDocumentPlusIconNotVisible(String SheetName, int rowNum, String FeatureName) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "V2ProjectStartBuilding");
		BuildScorecardV2ProjectById(SheetName, rowNum);
		List<WebElement> Feature;
		boolean flag = false;
		Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				System.out.println("FeatureName: " + FeatureName);
				CommonMethod.WaitUntilClickble(ele, 120);
				CommonMethod.JavascriptClickElement(ele);
				flag = true;
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectscorecardDocbtn");
				CommonMethod.assertisElementPresentFalse("V2ProjectscorecardDocbtn",
						"Document plus icon is visible");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.Robustclick("WellV2DashboardTab", "V2ProjectScorecardFeature");
	}
	
	public void RefreshScorecard() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();

	}

	public void Scorecardfill(int YesEnd, int NoStart, int NoEnd, int DifferencePlusOne)
			throws IOException, InterruptedException {
		List<WebElement> YesButton;
		List<WebElement> NoButton;
		Boolean flag = false;
		YesButton = CommonMethod.findElements("CommonScorecardPurseYes");
		for (int i = 1; i <= YesEnd; i++) {
			long startTime = System.currentTimeMillis();
			Thread.sleep(2000);
			int RemainingYes = YesButton.size();
			System.out.println(RemainingYes);
			do {
				if (!((System.currentTimeMillis() - startTime) < 60000)) {
					flag = true;
					System.out.println("Build Stuck");
					break;
				}
				CommonMethod.WaitUntilClickble("CommonScorecardPurseYes", 60);
				CommonMethod.JavascriptClickElement("CommonScorecardPurseYes");
				testlog.info("And User select PurseYes Status");
				Thread.sleep(2000);
				YesButton = CommonMethod.findElements("CommonScorecardPurseYes");
			} while ((YesButton.size() == RemainingYes));
			RemainingYes--;
			if (flag) {
				System.out.println("Build Exit");
				break;
			}
		}

		NoButton = CommonMethod.findElements("CommonScorecardPurseNo");
		int j = DifferencePlusOne;
		for (int i = NoStart; i <= NoEnd; i++) {
			long startTime = System.currentTimeMillis();
			Thread.sleep(2000);
			int RemainingNo = NoButton.size();
			do {
				if (!((System.currentTimeMillis() - startTime) < 60000)) {
					flag = true;
					System.out.println("Build Stuck");
					break;
				}
				CommonMethod.WaitUntilClickble(NoButton.get(RemainingNo - j), 60);
				CommonMethod.JavascriptClickElement(NoButton.get(RemainingNo - j));
				testlog.info("And User select PurseNo Status");
				Thread.sleep(2000);
				NoButton = CommonMethod.findElements("CommonScorecardPurseNo");
			} while ((NoButton.size() == RemainingNo));
			RemainingNo--;
			j--;
			if (flag) {
				System.out.println("Build Exit");
				break;
			}
		}
		RefreshScorecard();
	}

	public void ScorecardfillHSRWPR(int YesEnd, int NoStart, int NoEnd, int DifferencePlusOne)
			throws IOException, InterruptedException {

		List<WebElement> YesButton;
		List<WebElement> NoButton;
		Boolean flag = false;
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
				CommonMethod.WaitUntilClickble(NoButton.get(RemainingNo - j), 60);
				CommonMethod.JavascriptClickElement(NoButton.get(RemainingNo - j));
				testlog.info("And User select No Purse Status");
				Thread.sleep(1000);
				CommonMethod.WaitUntilNumberOfElementToBePresent("CommonScorecardPurseNo", RemainingNo - 1);
				NoButton = CommonMethod.findElements("CommonScorecardPurseNo");
			} while ((NoButton.size() == RemainingNo));
			RemainingNo--;
			j--;
			if (flag) {
				System.out.println("Build Exit");
				break;
			}
		}
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
				CommonMethod.WaitUntilClickble("CommonScorecardPurseYes", 60);
				CommonMethod.JavascriptClickElement("CommonScorecardPurseYes");
				testlog.info("And User select Yes Purse Status");
				Thread.sleep(1000);
				CommonMethod.WaitUntilNumberOfElementToBePresent("CommonScorecardPurseYes", RemainingYes - 1);
				YesButton = CommonMethod.findElements("CommonScorecardPurseYes");
			} while ((YesButton.size() == RemainingYes));
			RemainingYes--;
			if (flag) {
				System.out.println("Build Exit");
				break;
			}
		}
	}

	public void CompleteScorecardHsrById(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilVisibility("WellV2ScorecardTab", Scorecardtimeout);
		CommonMethod.RobustclickElementVisible("WellV2ScorecardTab", "V2ProjectHsrScorecard");
		Scorecardfill(15, 16, 27, 8);
		testlog.pass("**Verifies the 15 Purse Yes Scorecard HealthSafety ByID successfully**");
	}

	public String GetConceptPoints() throws IOException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardConceptpoint", 0);
		String[] cpoints = CommonMethod.getText("V2ProjectScorecardConceptpoint").trim().split(" ");
		return cpoints[0];
	}

	public void CompleteScorecardV2ProjectById(String SheetName, int rowNum, String ProjectType)
			throws IOException, InterruptedException {
		String conceptpoint;
		CommonMethod.RobustclickElementVisible("CommonScorecardPurseMaybe", "ApplicableVersion");
		Thread.sleep(2000);
		CommonMethod.RobustclickElementVisible("CommonScorecardPurseMaybe", "ApplicableVersion");
		testlog.info("Given User is on Scorecard page");
		/** Air concept */
		CommonMethod.WaitUntilClickble("V2ProjectAirconcept", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectAirconcept", "ApplicableVersion");
		testlog.info("And User clicks on Air concept tab");
		if (ProjectType.contains("pilot")) {
			Scorecardfill(22, 23, 29, 7);
		} else {
			Scorecardfill(16, 17, 25, 9);
		}
		CommonMethod.RobustclickElementVisible("V2ProjectAirconcept", "ApplicableVersion");
		conceptpoint = GetConceptPoints();
		data.setCellData(SheetName, "AirConceptPoints", rowNum, conceptpoint);
		System.out.println(data.getCellData(SheetName, "AirConceptPoints", rowNum));
		testlog.pass("**Completed Scorecard Air concept Purse Status V2Project successfully**");
		/** Water concept */
		testlog.info("And User clicks on Water concept tab");
		CommonMethod.WaitUntilClickble("V2ProjectWaterconcept", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectWaterconcept", "ApplicableVersion");
		if (ProjectType.contains("pilot")) {
			Scorecardfill(19, 20, 21, 2);
		} else {
			Scorecardfill(13, 14, 17, 4);
		}
		CommonMethod.RobustclickElementVisible("V2ProjectWaterconcept", "ApplicableVersion");
		conceptpoint = GetConceptPoints();
		data.setCellData(SheetName, "WaterConceptPoints", rowNum, conceptpoint);
		System.out.println(data.getCellData(SheetName, "WaterConceptPoints", rowNum));
		testlog.pass("**Completed Scorecard Water concept Purse Status V2Project successfully**");
		/** Nourisement concept */
		testlog.info("And User clicks on Nourisement concept tab");
		CommonMethod.WaitUntilClickble("V2ProjectNourisementconcept", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectNourisementconcept", "ApplicableVersion");
		testlog.info("And User clicks on Nourisement concept tab");
		if (ProjectType.contains("pilot")) {
			Scorecardfill(16, 17, 23, 7);
		} else {
			Scorecardfill(14, 15, 15, 1);
		}
		CommonMethod.RobustclickElementVisible("V2ProjectNourisementconcept", "ApplicableVersion");
		conceptpoint = GetConceptPoints();
		data.setCellData(SheetName, "NourishmentConceptPoints", rowNum, conceptpoint);
		System.out.println(data.getCellData(SheetName, "NourishmentConceptPoints", rowNum));
		testlog.pass("**Completed Scorecard Nourisement concept Purse Status V2Project successfully**");
		/** Light concept */
		CommonMethod.WaitUntilClickble("V2ProjectLightconcept", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectLightconcept", "ApplicableVersion");
		testlog.info("And User clicks on Light concept tab");
		if (ProjectType.contains("pilot")) {
			Scorecardfill(10, 11, 14, 4);
		} else {
			Scorecardfill(6, 7, 12, 6);
		}

		CommonMethod.RobustclickElementVisible("V2ProjectLightconcept", "ApplicableVersion");
		conceptpoint = GetConceptPoints();
		data.setCellData(SheetName, "LightConceptPoints", rowNum, conceptpoint);
		System.out.println(data.getCellData(SheetName, "LightConceptPoints", rowNum));
		testlog.pass("**Completed Scorecard Light concept Purse Status V2Project successfully**");
		/* * Movement concept */
		CommonMethod.WaitUntilClickble("V2Projectmovementconcept", 60);
		CommonMethod.RobustclickElementVisible("V2Projectmovementconcept", "ApplicableVersion");
		testlog.info("And User clicks on Movement concept tab");
		if (ProjectType.contains("pilot")) {
			Scorecardfill(15, 16, 29, 14);
		} else {
			Scorecardfill(11, 12, 22, 11);
		}
		CommonMethod.RobustclickElementVisible("V2Projectmovementconcept", "ApplicableVersion");
		conceptpoint = GetConceptPoints();
		data.setCellData(SheetName, "MovementConceptPoints", rowNum, conceptpoint);
		System.out.println(data.getCellData(SheetName, "MovementConceptPoints", rowNum));
		testlog.pass("**Completed Scorecard Movement concept Purse Status V2Project successfully**");
		/** Thermal concept */
		CommonMethod.WaitUntilClickble("V2ProjectThermalconcept", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectThermalconcept", "ApplicableVersion");
		testlog.info("And User clicks on Thermal concept tab");
		if (ProjectType.contains("pilot")) {
			Scorecardfill(10, 11, 16, 6);
		} else {
			Scorecardfill(10, 11, 15, 5);
		}
		CommonMethod.RobustclickElementVisible("V2ProjectThermalconcept", "ApplicableVersion");
		conceptpoint = GetConceptPoints();
		data.setCellData(SheetName, "ThermalConceptPoints", rowNum, conceptpoint);
		System.out.println(data.getCellData(SheetName, "ThermalConceptPoints", rowNum));
		testlog.pass("**Completed Scorecard Thermal concept Purse Status V2Project successfully**");
		/** Sound concept */
		CommonMethod.WaitUntilClickble("V2ProjectSoundconcept", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectSoundconcept", "ApplicableVersion");
		testlog.info("And User clicks on Sound concept tab");
		if (ProjectType.contains("pilot")) {
			Scorecardfill(10, 11, 15, 5);
		} else {
			Scorecardfill(9, 10, 14, 5);
		}
		CommonMethod.RobustclickElementVisible("V2ProjectSoundconcept", "ApplicableVersion");
		conceptpoint = GetConceptPoints();
		data.setCellData(SheetName, "SoundConceptPoints", rowNum, conceptpoint);
		System.out.println(data.getCellData(SheetName, "SoundConceptPoints", rowNum));
		testlog.pass("**Completed Scorecard Sound concept Purse Status V2Project successfully**");
		/** Material concept */
		CommonMethod.WaitUntilClickble("V2ProjectMaterialconcept", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectMaterialconcept", "ApplicableVersion");
		testlog.info("And User clicks on Material concept tab");
		if (ProjectType.contains("pilot")) {
			Scorecardfill(16, 17, 26, 10);
		} else {
			Scorecardfill(17, 18, 24, 7);
		}
		CommonMethod.RobustclickElementVisible("V2ProjectMaterialconcept", "ApplicableVersion");
		conceptpoint = GetConceptPoints();
		data.setCellData(SheetName, "MaterialsConceptPoints", rowNum, conceptpoint);
		System.out.println(data.getCellData(SheetName, "MaterialConceptPoints", rowNum));
		testlog.pass("**Completed Scorecard Material concept Purse Status V2Project successfully**");
		/** Mind concept */
		CommonMethod.WaitUntilClickble("V2ProjectMindconcept", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectMindconcept", "ApplicableVersion");
		testlog.info("And User clicks on Mind concept tab");
		if (ProjectType.contains("pilot")) {
			Scorecardfill(13, 14, 26, 13);
		} else {
			Scorecardfill(8, 9, 19, 11);
		}

		CommonMethod.RobustclickElementVisible("V2ProjectMindconcept", "ApplicableVersion");
		conceptpoint = GetConceptPoints();
		data.setCellData(SheetName, "MindConceptPoints", rowNum, conceptpoint);
		System.out.println(data.getCellData(SheetName, "MindConceptPoints", rowNum));
		testlog.pass("**Completed Scorecard Mind concept Purse Status V2Project successfully**");
		/** Community concept */
		CommonMethod.WaitUntilClickble("V2ProjectCommunityconcept", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectCommunityconcept", "ApplicableVersion");
		testlog.info("And User clicks on Community concept tab");
		if (ProjectType.contains("pilot")) {
			Scorecardfill(16, 17, 48, 32);
		} else {
			Scorecardfill(19, 20, 40, 21);
		}
		CommonMethod.RobustclickElementVisible("V2ProjectCommunityconcept", "ApplicableVersion");
		conceptpoint = GetConceptPoints();
		data.setCellData(SheetName, "CommunityConceptPoints", rowNum, conceptpoint);
		System.out.println(data.getCellData(SheetName, "CommunityConceptPoints", rowNum));
		testlog.pass("**Completed Scorecard Community concept Purse Status V2Project successfully**");
		/*
		 * Innovation concept
		 */
		CommonMethod.WaitUntilClickble("V2ProjectInnovationconcept", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectInnovationconcept", "ApplicableVersion");
		testlog.info("And User clicks on Innovation concept tab");
		if (ProjectType.contains("pilot")) {
			Scorecardfill(1, 2, 9, 8);
		} else {
			Scorecardfill(7, 8, 18, 11);
		}
		CommonMethod.RobustclickElementVisible("V2ProjectInnovationconcept", "ApplicableVersion");
		CommonMethod.WaitUntilVisibility("V2ProjectScorecardInnovationConceptpoint", 60);
		String[] points = CommonMethod.getText("V2ProjectScorecardInnovationConceptpoint").trim().split(" ");
		data.setCellData(SheetName, "InnovationConceptPoints", rowNum, points[0]);
		System.out.println(data.getCellData(SheetName, "Innovation", rowNum));
		RefreshScorecard();
		testlog.pass("**Completed Scorecard Innovation concept Purse Status V2Project successfully**");
		RefreshScorecard();
	}

	public void VerifyScorecardSummaryPoints(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardTargetpoint",
				"V2ProjectScorecardVerifySummarySlider");
		testlog.info("User clicks on target points button");
		List<WebElement> concepts = CommonMethod.findElementsCustom("V2ProjectScorecardSummaryConcept");
		Iterator<WebElement> it = concepts.iterator();
		while (it.hasNext()) {
			String conceptstext = it.next().getText();
			if (conceptstext.contains("AIR")) {
				System.out.println("Inside Air " + data.getCellData(SheetName, "AirConceptPoints", rowNum));
				CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "AirConceptPoints", rowNum),"13", "Air Concept Points doesn't match");
				testlog.info("Verifies Air concept points with summary points successfully");

			} else if (conceptstext.contains("WATER")) {
				System.out.println("Inside water: " + data.getCellData(SheetName, "WaterConceptPoints", rowNum));
				CommonMethod.negativesoftassertFieldValid(
						data.getCellData(SheetName, "WaterConceptPoints", rowNum),"13",
						"Water Concept Points doesn't match");
				testlog.info("Verifies Water concept points with summary points successfully");

			} else if (conceptstext.contains("NOURISHMENT")) {
				System.out.println(
						"Inside Nourishment: " + data.getCellData(SheetName, "NourishmentConceptPoints", rowNum));
				CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "NourishmentConceptPoints", rowNum),"10",
						"Nourishment Concept Points doesn't match");
				testlog.info("Verifies Nourishment concept points with summary points successfully");
			} else if (conceptstext.contains("LIGHT")) {
				System.out.println("Inside Light " + data.getCellData(SheetName, "LightConceptPoints", rowNum));
				CommonMethod.negativesoftassertFieldValid(
						data.getCellData(SheetName, "LightConceptPoints", rowNum),"11",
						"Light Concept Points doesn't match");
				testlog.info("Verifies Light concept points with summary points successfully");
			} else if (conceptstext.contains("MOVEMENT")) {
				System.out.println("inside Movement " + data.getCellData(SheetName, "MovementConceptPoints", rowNum));
				CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "MovementConceptPoints", rowNum),"11",
						"Movement Concept Points doesn't match");
				testlog.info("Verifies Movement concept points with summary points successfully");
			} else if (conceptstext.contains("THERMAL COMFORT")) {
				System.out.println("inside Thermal " + data.getCellData(SheetName, "ThermalConceptPoints", rowNum));
				CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "ThermalConceptPoints", rowNum),"12",
						"Thermal Concept Points doesn't match");
				testlog.info("Verifies Thermal concept points with summary points successfully");
			} else if (conceptstext.contains("SOUND")) {
				System.out.println("Inside sound " + data.getCellData(SheetName, "SoundConceptPoints", rowNum));
				CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "SoundConceptPoints", rowNum),"10.5",
						"sound Concept Points doesn't match");
				testlog.info("Verifies Sound concept points with summary points successfully");
			} else if (conceptstext.contains("MATERIALS")) {
				System.out.println("Inside Materials " + data.getCellData(SheetName, "MaterialsConceptPoints", rowNum));
				CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "MaterialsConceptPoints", rowNum),"12",
						"Materials Concept Points doesn't match");
				testlog.info("Verifies Materials concept points with summary points successfully");
			} else if (conceptstext.contains("MIND")) {
				System.out.println("Inside Mind " + data.getCellData(SheetName, "MindConceptPoints", rowNum));
				CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "MindConceptPoints", rowNum),"2.5", "Mind Concept Points doesn't match");
				testlog.info("Verifies Mind concept points with summary points successfully");
			} else if (conceptstext.contains("COMMUNITY")) {
				System.out.println("Inside Community " + data.getCellData(SheetName, "CommunityConceptPoints", rowNum));
				CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "CommunityConceptPoints", rowNum),"7.5",
						"Community Concept Points doesn't match");
				testlog.info("Verifies Community concept points with summary points successfully");
			} else if (conceptstext.contains("INNOVATION")) {
				System.out
						.println("Inside Innovation " + data.getCellData(SheetName, "InnovationConceptPoints", rowNum));
				CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "InnovationConceptPoints", rowNum),"7",
						"Innovation Concept Points doesn't match");
				testlog.info("Verifies Innovation concept points with summary points successfully");
			} else {
				System.out.println("Never go inside the summary!!!!");
			}

		}
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardSummaryClosePanel", "V2ProjectScorecardTargetpoint");
		String actualTargetedPointsScore = CommonMethod.getattributeValueByTextContent("TargetedPoints").trim().replaceAll("\\s+", " ");
		CommonMethod.negativesoftassertFieldValid(actualTargetedPointsScore, "110 Points", "Targeted Points Score data does not matched");
		testlog.info("Summary target point: "+actualTargetedPointsScore);
		testlog.info("Verifies summary target 110 points successfully");
	}

	public void ScorecardPurseStatusCount(String SheetName, int rowNum, String Type)
			throws IOException, InterruptedException {
		/*
		 * Total FeaturePartCount
		 */
		RefreshScorecard();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 10);
		testlog.info("Given User is on Scorecard page");
		int ScorecardPart = CommonMethod.ElementSize("V2ProjectScorecardPartCount");
		String FeatureScorecardPartCount = Integer.toString(ScorecardPart);
		testlog.info("FeatureScorecardPartCount: " + FeatureScorecardPartCount);
		data.setCellData(SheetName, "ScorecardPartCount", rowNum, FeatureScorecardPartCount);
		CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "ScorecardPartCount", rowNum),"221",
				"V2Project ScorecardPartCount doesn't match");
		/*
		 * YesPurseCount
		 */
		int YesFeature = CommonMethod.ElementSize("CommonSelectedPurseYes");
		String YesFeatureCount = Integer.toString(YesFeature);
		testlog.info("YesFeatureCount: " + YesFeatureCount);
		data.setCellData(SheetName, "YesPurseCount", rowNum, YesFeatureCount);
		CommonMethod.negativesoftassertFieldValid( data.getCellData(SheetName, "YesPurseCount", rowNum),"130",
				"YesPurseCount doesn't match");
		/*
		 * NoPurseCount
		 */
		int NoFeature = CommonMethod.ElementSize("CommonSelectedPurseNo");
		String NoFeatureCount = Integer.toString(NoFeature);
		testlog.info("NoFeatureCount: " + NoFeatureCount);
		data.setCellData(SheetName, "NoPurseCount", rowNum, NoFeatureCount);
		CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "NoPurseCount", rowNum),"91",
				"NoPurseCount doesn't match");
		testlog.info("Then User verifies the " + YesFeatureCount + "Purse Yes Scorecard V2Project successfully");
		testlog.info("And User verifies the " + NoFeatureCount + " Purse No Scorecard V2Project successfully");
		testlog.info("And User verifies the " + FeatureScorecardPartCount
				+ " Purse Scorecard Part Count V2Project successfully");
		testlog.pass("***Validate the Scorecard Part Count V2Project successfully****");

	}

	public void VerifyCorePointOnlyTargetingFeaturePoint(String FeatureName, String SelectedPurseYes,
			String EnabledWeightCirclePoint, String EnabledPlusIcon, String EnabledPlusOneCircle)
			throws IOException, InterruptedException {
		boolean flag = false;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		List<WebElement> Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardFeatureSelectCorePoint", 0);
				CommonMethod.assertisElementPresentTrue("V2ProjectScorecardFeatureSelectCorePoint",
						"Scorecard Feature CorePoint doesn't present");
				CommonMethod.negativesoftassertFieldValid(
						String.valueOf(CommonMethod.getdropdownSize("V2ProjectScorecardFeatureSelectCorePoint")), "3",
						"Feature CorePoint dropdown doesn't Match");
				testlog.info("And User verifies DocumentType size successfully**");
				// Only targeting the feature point
				if (FeatureName.equalsIgnoreCase("Provide Visual Acuity")) {
					CommonMethod.selectdropdownVisibletext("V2ProjectScorecardFeatureSelectCorePoint",
							"Only targeting the Precondition");
				} else {
					CommonMethod.selectdropdownVisibletext("V2ProjectScorecardFeatureSelectCorePoint",
							"Only targeting the feature point");
				}
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(SelectedPurseYes, 0);
				CommonMethod.assertisElementPresentTrue(SelectedPurseYes,
						"Scorecard Feature enabled weight point doesn't present");
				testlog.info("And User verifies Scorecard Feature enabled weight point successfully**");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(EnabledWeightCirclePoint, 0);
				CommonMethod.assertisElementPresentTrue(EnabledWeightCirclePoint,
						"Scorecard Feature enabled weight point doesn't present");
				testlog.info("And User verifies Scorecard Feature enabled weight point successfully**");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
	}

	public void VerifyCorePointOnlyTargetingAdditionalFeaturePoint(String FeatureName, String SelectedPurseYes,
			String EnabledWeightCirclePoint, String EnabledPlusIcon, String EnabledPlusOneCircle)
			throws IOException, InterruptedException {
		boolean flag = false;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		List<WebElement> Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardFeatureSelectCorePoint", 0);
				if (FeatureName.equalsIgnoreCase("Provide Visual Acuity")) {
					CommonMethod.selectdropdownVisibletext("V2ProjectScorecardFeatureSelectCorePoint",
							"Targeting additional WELL Core point along with the Precondition");
				} else {
					CommonMethod.selectdropdownVisibletext("V2ProjectScorecardFeatureSelectCorePoint",
							"Targeting additional WELL Core point along with the feature point");
				}
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(EnabledPlusIcon, 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(SelectedPurseYes, 0);
				CommonMethod.assertisElementPresentTrue(SelectedPurseYes,
						"Scorecard Feature enabled Purse Yes doesn't present");
				testlog.info("And User verifies Scorecard Feature enabled Purse Yes successfully**");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(EnabledWeightCirclePoint, 0);
				if (FeatureName.equalsIgnoreCase("Provide Visual Acuity")) {
					CommonMethod.negativesoftassertFieldValid(CommonMethod.getText(EnabledWeightCirclePoint), "P",
							"TargetingAdditional Weight Circle Point doesn't present");
				} else {
					CommonMethod.negativesoftassertFieldValid(CommonMethod.getText(EnabledWeightCirclePoint), "1",
							"TargetingAdditional Weight Circle Point doesn't present");
				}
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText(EnabledPlusOneCircle), "1",
						"TargetingAdditional Plus One Circle doesn't present");

				testlog.info("And User verifies Scorecard Feature Plus one weight point successfully**");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectFeatureEnabledPlusTargetPoint", 0);
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getText("V2ProjectFeatureEnabledPlusTargetPoint"), "+ 1 CORE POINTS",
						"Scorecard Feature Target point doesn't present");
				testlog.info("And User verifies Target Core point label successfully**");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
	}

	public void VerifyCorePointAdditionalWELLCorePoint(String FeatureName, String SelectedPurseYes,
			String EnabledWeightCirclePoint, String EnabledPlusIcon, String EnabledPlusOneCircle)
			throws IOException, InterruptedException {
		boolean flag = false;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		List<WebElement> Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardFeatureSelectCorePoint", 0);
				CommonMethod.selectdropdownVisibletext("V2ProjectScorecardFeatureSelectCorePoint",
						"Only targeting the additional WELL Core point");
				Thread.sleep(2000);
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(EnabledPlusIcon, 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(SelectedPurseYes, 0);
				CommonMethod.assertisElementPresentTrue(SelectedPurseYes,
						"Scorecard Feature enabled Purse Yes doesn't present");
				testlog.info("And User verifies Scorecard Feature enabled Purse Yes successfully**");
				CommonMethod.assertisNotElementPresent(EnabledPlusOneCircle,
						"Scorecard Feature Disable weight point present");
				testlog.info("And User verifies Scorecard Feature Disable weight point successfully**");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(EnabledPlusIcon, 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText(EnabledPlusOneCircle), "1",
						"Additional WELL Core Added core point doesn't present");
				testlog.info("And User verifies Scorecard Feature Plus one weight point successfully**");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectFeatureEnabledPlusTargetPoint", 0);
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getText("V2ProjectFeatureEnabledPlusTargetPoint"), "+ 1 CORE POINTS",
						"Additional WELL Core Scorecard Feature Target point doesn't present");
				testlog.info("And User verifies Target Core point label successfully**");
				if (FeatureName.equalsIgnoreCase("Provide Visual Acuity")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectA01PreWeightPointTooltipValid",
							0);
					CommonMethod.moveToElement("V2ProjectL02.1WeightPtCircleP");
					CommonMethod.negativesoftassertFieldValid(
							CommonMethod.getText("V2ProjectA01PreWeightPointTooltipValid"), "1",
							"Additional WELL Core Scorecard Feature Target point tooltip doesn't present");
				}
				testlog.info("And User verifies Target Core point label successfully**");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
	}

	public void resetCorePoint(String ResetCorePoint, String SelectedPurseYes, String WeightPoint, String PurseYes)
			throws IOException, InterruptedException {
		if (ResetCorePoint.equalsIgnoreCase("V2ProjectSupportResetPurseMayBe")) {
			CommonMethod.hideElement("NavBarHide");
			CommonMethod.scrolldowntoElement(ResetCorePoint);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(ResetCorePoint, 0);
		CommonMethod.Robustclick(ResetCorePoint, SelectedPurseYes);
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException(WeightPoint);
		CommonMethod.assertisNotElementPresent(WeightPoint, "Scorecard Feature Disable weight point present");
		testlog.info("And User verifies Scorecard Feature Disable weight point successfully**");
		CommonMethod.assertisNotElementPresent("V2ProjectFeatureEnabledPlusTargetPoint", "Target is present");
		testlog.info("And User verifies Target point label not present successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PurseYes, 0);
		CommonMethod.JavascriptClickElement(PurseYes);
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
	}
	
	public void VerifyCorePointInScorecard(String FeatureName, String SelectedPurseYes,
			String EnabledWeightCirclePoint)
			throws IOException, InterruptedException {
				testlog.info("And User verifies Scorecard Feature enabled weight point successfully**");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(EnabledWeightCirclePoint, 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText(EnabledWeightCirclePoint), "0.5",
						"Additional Plus One Circle Point doesn't present");
				CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectL07.1FeatureEnabledPlusIcon", "EnabledWeightCirclePoint is visible");
				testlog.info("And User verifies Scorecard Feature enabled weight point successfully**");
	}
	
	public void CommonUploadScorecardDocument(String FeatureName,String SheetName,int rowNum,String Commodity,String FileName,Boolean PartNameRequired, Boolean VerificationMethodValidationRequired, Boolean NoteRequired,  Boolean IntentCheckboxRequired)
			throws Exception {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 10);
		List<WebElement> Feature;
		boolean flag = false;
		Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				System.out.println("FeatureName: " + FeatureName);
				CommonMethod.WaitUntilClickble(ele, 120);
				CommonMethod.JavascriptClickElement(ele);
				flag = true;
				testlog.info("When User clicks on Feature");
				if (TestCaseName.equalsIgnoreCase("V2_TC_08A_04_UploadFeatureDocumentInScorecardV2Project")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardCommercialKitchenSpaceTypeValid", 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("V2ProjectScorecardCommercialKitchenSpaceTypeValid"), "For Commercial Kitchen Spaces & Industrial",
						"V2ProjectScorecard CommercialKitchen SpaceType Valid doesn't match");
				}
				generic.uploadModalCompleteGeneric(SheetName, rowNum, Commodity, FileName,PartNameRequired, VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
				testlog.info("Then User verifies upload scorecard document");
				int PaperIcon = 0;
				if (FeatureName.equalsIgnoreCase("Support Mindful Eating")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardN08.1Papericon",
							1);
					PaperIcon = CommonMethod.ElementSize("V2ProjectScorecardN08.1Papericon");
					CommonMethod.negativesoftassertFieldValid(Integer.toString(PaperIcon), "1",
							"PaperIconCount doesn't match");
				} 
				if (FeatureName.equalsIgnoreCase("Prohibit Outdoor Smoking")) { 
					CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardpilotPapericon",
							1);
					PaperIcon = CommonMethod.ElementSize("V2ProjectScorecardpilotPapericon");
					CommonMethod.negativesoftassertFieldValid(Integer.toString(PaperIcon), "1",
							"PaperIconCount doesn't match");
				}
				testlog.info("And User verifies papericon for upload scorecard");
				if(TestCaseName.contains("UploadFeatureDocV2ProjectInsideScorecard")) {
				v2project.ValidateIntentCheckboxInUploadedDocs(SheetName, rowNum);
				}
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectscorecardVerifyUploadDoc", 1);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}


	public void UploadLegalAndGeneralDocumentFromDocumentLibrary(String SheetName, int rowNum, String DocumentType,
			String FileName) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2ProjectDocumentTab", 0);
		CommonMethod.RobustclickElementVisible("WellV2ProjectDocumentTab", "V2ProjectDocUploadbtn");
		testlog.info("When User clicks on DocumentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocUploadbtn", "V2ProjectPortfolioDocType");
		testlog.info("And User clicks on Upload button");
		testlog.info("Document Type : General");
		if(TestNGTestName.contains("Main") && (TestCaseName.equalsIgnoreCase("V2_TC_08_00_UploadGeneralDocumentFromDocumentLibrary"))) {
		CommonMethod.verifyDropdownValues("V2ProjectPortfolioDocType", "V2ProjectDocumentType");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPortfolioDocType", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectPortfolioDocType", DocumentType);
		testlog.info("And User select Document Type");
		CommonMethod.uploadFile("V2Projectscorecarddocupload", FileName, "UploadFileVerifyScorecard");
		testlog.info("And User upload Document");
		if(TestCaseName.contains("UploadGeneralDocumentFromDocumentLibrary")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioScorecardVerifyTaskUploadIntentStage", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioScorecardVerifyTaskUploadIntentStage","Intent checkbox is not present");
		CommonMethod.ClickCheckbox("PortfolioScorecardVerifyTaskUploadIntentStage");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocSubmit", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocSubmit", "V2ProjectScorecardWaitUntilDocUploaded");
		testlog.info("And User clicks on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilInVisibility("V2ProjectScorecardWaitUntilDocUploaded", 120);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		if(TestCaseName.contains("UploadGeneralDocumentFromDocumentLibrary")) {			
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardList", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardList", "V2ProjectValidateIntentCheckbox");
		v2project.ValidateIntentCheckboxInUploadedDocs(SheetName, rowNum);
		}
		testlog.info("Then User will be redirected to Document list");
		testlog.info("And User verifies uploaded document");
		testlog.pass("**Upload Document successfully**");
	}

	public void UploadOngoingDocumentInDocV2Project(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		testlog.info("Given User is on Document page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocUploadbtn", "V2ProjectPortfolioDocType");
		testlog.info("When User click on Upload button");
		testlog.info("Document Type: Ongoing data reports");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPortfolioDocType", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectPortfolioDocType", "Ongoing data reports");
		Thread.sleep(2000);
		CommonMethod.RobustclickElementVisible("V2ProjectResetbtn", "V2ProjectPortfolioDocType");
		Thread.sleep(1000);
		CommonMethod.selectdropdownVisibletext("V2ProjectPortfolioDocType", "Legal");
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("V2ProjectResetbtn", "V2ProjectPortfolioDocType");
		Thread.sleep(2000);
		System.out.println(CommonMethod.getText("V2ProjectPortfolioDocType"));
		CommonMethod.selectdropdownVisibletext("V2ProjectPortfolioDocType", "Ongoing data reports");
		testlog.info("And User select Document Type");
		CommonMethod.WaitUntilPresence("OwnerOrgClick", 10);
		CommonMethod.RobustclickElementVisible("OwnerOrgClick", "OwnerOrg");
		CommonMethod.sendKeys("OwnerOrg", "R");
		CommonMethod.WaitUntilClickble("SelectOwnerOrgDyn", 10);
		CommonMethod.SelectRandomfromList("SelectOwnerOrgDyn", 1, 5).click();
		CommonMethod.selectdropdownVisibletext("V2ProjectDocPeriod", "Year 1 reporting");
		testlog.info("And User select Document Period");
		CommonMethod.uploadFile("V2Projectscorecarddocupload", OngoingfileUpload, "UploadFileVerifyScorecard");
		testlog.info("And User upload Document");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocSubmit", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocSubmit", "V2ProjectDocUploadbtn");
		testlog.info("And User clicks on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocumentTabScorecardList", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocumentTabScorecardList", "V2ProjectValidOngoingDocLibrary");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectValidOngoingDocLibrary",1);
		CommonMethod.negativeAssertElementPresentTrue("V2ProjectValidOngoingDocLibrary","ValidOngoingDocLibrary is not present");
		testlog.info("Then User will be redirected to Document list");
		testlog.info("And User verifies uploaded document");
		testlog.pass("**Upload Document successfully**");
	}

	public void UploadFeatureDocumentInDocV2Project(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Document page");
		Thread.sleep(2000);
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocUploadbtn", "V2ProjectPortfolioDocType");
		testlog.info("When User click on Upload button");
		Thread.sleep(2000);
		testlog.info("Document Type: " + "Feature");
		CommonMethod.selectdropdownVisibletext("V2ProjectPortfolioDocType", "Feature");
		testlog.info("And User select Document type");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
		CommonMethod.RobustclickElementVisible("OwnerOrgClick", "V2ProjectDocLOA");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocLOA", 0);
		CommonMethod.JavascriptClickElement("V2ProjectDocLOA");
		CommonMethod.uploadFile("V2Projectscorecarddocupload", FeaturefileUpload, "UploadFileVerifyScorecard");
		testlog.info("And User upload Document");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectDisabledUploadBtn", 1);
		CommonMethod.assertisElementPresentTrue("V2ProjectDisabledUploadBtn", "Upload button is enabled ");
		Thread.sleep(3000);
		CommonMethod.RobustclickElementVisible("V2ProjectPart", "V2ProjectSelectPartDoc");
		Thread.sleep(3000);
		CommonMethod.Robustclick("V2ProjectSelectPartDoc");
		Thread.sleep(1000);
		testlog.info("And User select the Part");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UploadFileVerifyScorecard", 0);
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectDisabledUploadBtn");
		CommonMethod.assertisElementPresentFalse("V2ProjectDisabledUploadBtn", "Upload button is enabled");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocSubmit", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocSubmit", "V2ProjectDocUploadbtn");
		testlog.info("And User click on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocumentTabScorecardList", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocumentTabScorecardList", "V2ProjectValidFeatureDocLibrary");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectValidFeatureDocLibrary",1);
		CommonMethod.negativeAssertElementPresentTrue("V2ProjectValidFeatureDocLibrary","ValidFeatureDocLibrary is not present");
		testlog.info("Then User will be redirected to Document list");
		testlog.info("And User verifies uploaded document");
		testlog.pass("**Upload Document successfully**");
	}
	
	public void CorePointDocumentUploadInDocumentLibraryV2Project(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Document page");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2ProjectDocumentTab", 0);
		CommonMethod.RobustclickElementVisible("WellV2ProjectDocumentTab", "V2ProjectDocUploadbtn");
		testlog.info("When User clicks on DocumentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocUploadbtn", "V2ProjectPortfolioDocType");
		testlog.info("When User click on Upload button");
		Thread.sleep(2000);
		testlog.info("Document Type: " + "Feature");
		CommonMethod.selectdropdownVisibletext("V2ProjectPortfolioDocType", "Feature");
		testlog.info("And User select Document type");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
		CommonMethod.RobustclickElementVisible("OwnerOrgClick", "V2ProjectDocProfessional");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocProfessional", 0);
		CommonMethod.JavascriptClickElement("V2ProjectDocProfessional");
		CommonMethod.uploadFile("V2Projectscorecarddocupload", FeaturefileUpload, "UploadFileVerifyScorecard");
		testlog.info("And User upload Document");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectDisabledUploadBtn", 1);
		CommonMethod.assertisElementPresentTrue("V2ProjectDisabledUploadBtn", "Upload button is enabled ");
		Thread.sleep(3000);
		CommonMethod.RobustclickElementVisible("V2ProjectPart", "V2ProjectSelectPartDocL07");
		Thread.sleep(3000);
		CommonMethod.Robustclick("V2ProjectSelectPartDocL07");
		Thread.sleep(1000);
		testlog.info("And User select the Part");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UploadFileVerifyScorecard", 0);
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectDisabledUploadBtn");
		CommonMethod.assertisElementPresentFalse("V2ProjectDisabledUploadBtn", "Upload button is enabled");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocSubmit", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocSubmit", "V2ProjectDocUploadbtn");
		testlog.info("And User click on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocumentTabScorecardList", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocumentTabScorecardList", "V2ProjectDocProfessionalValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectDocProfessionalValid",1);
		CommonMethod.negativeAssertElementPresentTrue("V2ProjectDocProfessionalValid","ValidFeatureDocLibrary is not present");
		testlog.info("Then User will be redirected to Document list");
		testlog.info("And User verifies uploaded document");
		testlog.pass("**Upload Document successfully**");
	}

	public void UploadDocumentCountInDocV2Project(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Document page");
		CommonMethod.scrolldowntoElement("V2ProjectDocUploadbtn");
		CommonMethod.RobustclickElementVisible("V2ProjectGeneralDoc", "V2ProjectDocumentValid");
		testlog.info("And User click on General Document list");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioV2ProjectDocumentTableTr", 2);
		int V2ProjectDocCount = CommonMethod.ElementSize("PortfolioV2ProjectDocumentTableTr");
		String V2ProjectDocuCounts = Integer.toString(V2ProjectDocCount);
		CommonMethod.negativesoftassertFieldValidEquals(V2ProjectDocuCounts, "2",
				"General tab Document Count doesn't match");
		testlog.info("Then User will be redirected to General Document list");
		testlog.info("And User verifies Document Count");
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardDoc", "V2ProjectDocumentValid");
		testlog.info("And User click on Doc Scorecard list");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioV2ProjectDocumentTableTr", 4);
		int V2ProjectScoreDocCount = CommonMethod.ElementSize("PortfolioV2ProjectDocumentTableTr");
		String V2ProjectDocCounts = Integer.toString(V2ProjectScoreDocCount);
		CommonMethod.negativesoftassertFieldValidEquals(V2ProjectDocCounts, "4",
				"Scorecard tab Document Count doesn't match");
		testlog.info("Then User will be redirected to Document list");
		testlog.info("And User verifies uploaded document");
		testlog.pass("**Verifies Uploaded Document successfully**");
	}

	public void SubmitReviewV2Project(String SheetName, int rowNum, String ReviewPhaseName, String ReviewViewButton) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("When User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "V2ProjectcommentReview");
		if(TestCaseName.equalsIgnoreCase("V2_TC_09_00_SubmitReviewV2Project")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSubmitPhaseReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectSubmitPhaseReview", "V2ProjectcommentReview");
		CommonMethod.negativesoftassertPageSource("Doc-submit-phase is required.", "Phase Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Please provide your comments below to notify the IWBI team is required.","Comment Name Error Mismatch");
		testlog.info("And User clicks on submit button without entering the mandatory fields and verifies error meassage");
		}
		data.setCellData(SheetName, "ReviewPhase", rowNum, ReviewPhaseName);
		CommonMethod.WaitUntilClickble("V2ProjectcommentReview", 60).sendKeys(ReviewPhaseName);
		testlog.info("And User enter on Comment field");
		if(TestCaseName.equalsIgnoreCase("V2_TC_09_00_SubmitReviewV2Project")) {
		ValidateReviewPhases();
		}
		if (!TestCaseName.contains("V1ProjectSubmitFinalDocumentationReview")) {
		CommonMethod.verifyDropdownValues("V2ProjectSelectPhase", "ReviewPhase");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSelectPhase", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectSelectPhase", ReviewPhaseName);
		testlog.info("And User select the Phase");
		if(TestCaseName.equalsIgnoreCase("V2_TC_09_00_SubmitReviewV2Project")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidReviewPhasesCheckbox", 0);
		CommonMethod.ClickCheckbox("ValidReviewPhasesCheckbox");
		}
		if(TestCaseName.equalsIgnoreCase("V1_TC_40_00_V1ProjectSubmitPreliminaryDocumentationReview")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubstantialCompletionReviewPhasesCheckbox", 0);
		CommonMethod.ClickCheckbox("SubstantialCompletionReviewPhasesCheckbox");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EquityReviewPhasesCheckbox", 0);
		CommonMethod.ClickCheckbox("EquityReviewPhasesCheckbox");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSubmitPhaseReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectSubmitPhaseReview", "ReviewViewButton");
		testlog.info("And User clicks on submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
		testlog.info("Then User will be redirected to Review List page");	
		if(TestCaseName.equalsIgnoreCase("V2_TC_09_00_SubmitReviewV2Project")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectValidEPLable", 0);
		String actualWELLEpLabel=CommonMethod.getattributeValueByTextContent("V2ProjectValidEPLable");
		actualWELLEpLabel= actualWELLEpLabel.replaceAll("\\s+", " ").trim();
		CommonMethod.negativesoftassertFieldValid(actualWELLEpLabel, "WELL EP", "Date added column name does not matched");
		}
	}

	public void AdminCompleteReviewV2Project(String SheetName, int rowNum, String ReviewPhaseName, String ViewBtn)
			throws IOException, InterruptedException {
		/** Admin Review */
		if(!TestCaseName.contains("Portfolio_USTC_02_05_AdminCompleteReviewV2Project")) {
			AdminSearchById(SheetName, rowNum);	
		}
		AdminCompleteReview(SheetName, rowNum, ReviewPhaseName, ViewBtn);
	}
	
		public void AdminCompleteReview(String SheetName, int rowNum, String ReviewPhaseName, String ViewBtn)
				throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", ViewBtn);
		testlog.info("And User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(ViewBtn, 0);
		CommonMethod.RobustclickElementVisible(ViewBtn, "ReviewAdminActionsButton");
		testlog.info("And User clicks on view button");
		ReviewAdminActionsButton();
		CommonMethod.RobustclickElementVisible("ReviewReturnButton", "CommentNarrative");
		testlog.info("And User clicks on Return button");
		CommonMethod.WaitUntilClickble("CommentNarrative", 60)
				.sendKeys(ReviewPhaseName);
		testlog.info("And User enter on CommentNarrative field");
		CommonMethod.sendKeys("CommentNarrative", ReviewPhaseName);
		testlog.info("And User enter on General SCommentNarrative field");
		CommonMethod.uploadMultipleFile("FileUpload1", SampleJpgfile, SampleJpgfile, "MultipeUploadDeleteicon", 2,
				"MultipeUploadEnableButtonDeleteLink");
		testlog.info("And User Upload Logo image to V2ProjectLogo");
		CommonMethod.WaitUntilClickble("DatePickerButton", 60);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("DatePickerButton", "DatePickerOkButton");
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "ReviewPaymentstatusRadio");
		testlog.info("And User select Date"); 
		if(TestCaseName.equalsIgnoreCase("V1_TC_40_01_V1ProjectAdminCompletePreliminaryDocumentationReview")) {
		CommonMethod.scrolldowntoElement("WerText");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWerCommentNarrative", 0);
	    CommonMethod.sendKeys("V2ProjectWerCommentNarrative", ReviewPhaseName);
		CommonMethod.WaitUntilClickble("DatePickerButton2", 60);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("DatePickerButton2", "DatePickerOkButton");
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "ReviewPaymentstatusRadio");
		testlog.info("And User select Date"); 
		}
		CommonMethod.scrollDown();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewPaymentstatusRadio", 0);
		CommonMethod.ClickCheckbox("ReviewPaymentstatusRadio");
		testlog.info("And User checks the Paymentstatus checkbox");
		if (CommonMethod.isElementsExist("EditAchievedStatus", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditAchievedStatus", 0);
			CommonMethod.ClickCheckbox("EditAchievedStatus");
			CommonMethod.VerifyRadioOrCheckboxSelcted("EditAchievedStatus");
			CommonMethod.WaitUntilClickble("AwardedDate", 60).click();
			CommonMethod.WaitUntilClickble("DatePickerOkButton", 60).click();
			CommonMethod.scrollDown();
			Thread.sleep(1000);
			CommonMethod.ClickCheckbox("ReviewPaymentstatusRadio");
			testlog.info("And User checks the Payment status checkbox");
			}
		CommonMethod.Robustclick("ReviewReturnSubmit");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ReviewReturnSubmit", 1);
		testlog.info("And User clicks on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectReviewedStatus", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("V2ProjectReviewedStatus"), "Reviewed", "Verified Review status");
		testlog.info("And User verifies the Reviewed Status");
		testlog.pass("**Reviewed "+ReviewPhaseName+ " successfully**");
	}

		public void validateSubmitReviewV2Project() throws IOException, InterruptedException {
			testlog.info("Given User is on Dashboard page");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
			CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
			testlog.info("When User clicks on ReviewTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectsubmitReview");
			 CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectsubmitReview","V2ProjectsubmitReview is visible");
		}

	public void HealthSafetyV2ProjectRegisterOptn(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
		CommonMethod.RobustclickElementVisible("HealthSafetyTab", "V2ProjectHsrContinuebtn");
		testlog.info("When User clicks on HealthSafetyTab");
		if (CommonMethod.isElementsExist("V2ProjectHsrContinuebtn", 30)) {
			CommonMethod.WaitUntilVisibility("V2ProjectHsrContinuebtn", 60);
			CommonMethod.Robustclick("V2ProjectHsrContinuebtn");
			testlog.info("And User clicks on Continue button");
			if (SheetName.equalsIgnoreCase("V2Project")) {
			CommonMethod.verifyDropdownValues("V2ProjectOptConfirmNoEnrollSelect", "ReviewCycle");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Terms", 0);
			CommonMethod.RobustclickElementVisible("Terms", "V2ProjectHsrTermsbtn");
			testlog.info("And User checks the terms and conditions checkbox");
			CommonMethod.RobustclickElementVisible("V2ProjectHsrTermsbtn", "Terms");
			testlog.info("And User checks the terms and conditions checkbox");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Terms", 0);
			CommonMethod.RobustclickElementVisible("Terms", "V2ProjectHsrProceedbtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrProceedbtn", 0);
			CommonMethod.Robustclick("V2ProjectHsrProceedbtn");
			testlog.info("And User checks the Proceed checkbox");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("GoToAccount", 0);
			CommonMethod.JavascriptClickElement("GoToAccount");
			testlog.info("And User clicks on Account button");
			rc.ScorecardLoading();
		}
	}

	public void HsrV2ProjectRegisterOptn() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
		CommonMethod.RobustclickElementVisible("HealthSafetyTab", "V2projectRatingFeatureName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
		testlog.info("When User clicks on HealthSafetyTab");
		if (CommonMethod.isElementsExist("V2ProjectHsrContinuebtn", 30)) {
			CommonMethod.Robustclick("V2ProjectHsrContinuebtn");
			testlog.info("And User clicks on Continue button");
		}
		rc.ScorecardLoading();
	}
	
	public void HealthSafetyV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		HealthSafetyV2ProjectRegisterOptn(SheetName, rowNum);
		testlog.info("Then User will be redirected to Scorecard page");
		ScorecardfillHSRWPR(15, 1, 31, 31);
		testlog.info("And User verifies the 15 Purse Yes Scorecard");
		testlog.info("And User verifies the 12 Purse No Scorecard");
		testlog.pass("**Verifies the 15 Purse Yes Scorecard HealthSafety ByID successfully**");
	}

	public void uploadDocumentInFeature(String SheetName, int LastFeatureNumber) throws IOException, InterruptedException {
		if (SheetName.equalsIgnoreCase("V2Project") && TestCaseName.equalsIgnoreCase("V2_TC_10_01_UploadHealthSafetyScorecardDocV2Project")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrScorecardVerificationFilter", 0);
		CommonMethod.RobustclickElementVisible("HsrScorecardVerificationFilter", "HsrScorecardFilterSelectbyVerification");
		CommonMethod.verifyDropdownValues("HsrScorecardFilterSelectbyVerification", "FilterByVerification");
		CommonMethod.JavascriptClickElement("HsrScorecardVerificationFilter");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
		}
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
		Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		Feature = Feature.subList(0, LastFeatureNumber);
		for (WebElement f : Feature) {
			CommonMethod.WaitUntilClickble(f, 120);
			CommonMethod.JavascriptClickElement(f);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
			CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPDocIcon", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectWPRPDocIcon", "V2ProjectDocUpload");
			CommonMethod.uploadFile("V2ProjectDocUpload", FeaturefileUpload, "PortfolioScorecardUploadRemoveLink");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadRemoveLink", 0);
			if (!CommonMethod.isElementsExist("V2ProjectScorecardSelectedDocTypeValid", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect1", 0);
				CommonMethod.click("Multiselect1");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectOwnerOrgDyn", 0);
				List<WebElement> ele = CommonMethod.findElements("SelectOwnerOrgDyn");
				ele.get(0).click();
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature", 0);
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
			if (CommonMethod.isElementsExist("V2ProjectScorecardVerificationMethodSelectAddPart", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"V2ProjectScorecardVerificationMethodSelectAddPart", 0);
				CommonMethod.Robustclick("V2ProjectScorecardVerificationMethodSelectAddPart",
						"PortfolioScoreCardVerificationSelectFeature");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EnabledUploadbtn", 0);
			CommonMethod.Robustclick("EnabledUploadbtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("EnabledUploadbtn", 1);
			if (CommonMethod.isElementsExist("DocumentAddedToasterMessage", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DocumentAddedToasterMessage", 1);
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTableTr, 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRScorecardLanding", 0);
			CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
			CommonMethod.JavascriptClickElement(f);
		}
		System.out.println("LastFeatureNumber: " + LastFeatureNumber);
	}

	public void uploadHsrDocV2Project(String SheetName) throws IOException, InterruptedException {
		uploadDocumentInFeature(SheetName, 15);
		testlog.pass("**Upload 15 Scorecard Documents successfully**");
	}
	
public void underReviewScorecardResponseValid() throws IOException, InterruptedException {
		
		if (TestCaseName.equalsIgnoreCase("V2_TC_09_00_SubmitReviewV2Project")) {
       rc.clickScorecard();
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardSelectPurseNoParticulate", 0);
		CommonMethod.RobustclickElementVisible("ScorecardSelectPurseNoParticulate", "V2ProjectScorecardHsrResponseYes");
		}
		else {
			if (TestCaseName.equalsIgnoreCase("Healthsafety_TC_09_00_PreliminarySubmitReview")) {
				rc.clickScorecard();
			}
			else {

				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
				CommonMethod.RobustclickElementVisible("HealthSafetyTab", "V2ProjectScorecardHsrResponseYes"); 
			}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardHsrResponseYes", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardHsrResponseYes", "V2ProjectScorecardHsrUnderReviewPopUp");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardHsrUnderReviewPopUp", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardHsrUnderReviewPopUpMessage", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("V2ProjectScorecardHsrUnderReviewPopUpMessage"), "No changes can be made when the project is under review.", "Under Review Message doesn't match");
		if (TestCaseName.equalsIgnoreCase("V2_TC_09_00_SubmitReviewV2Project")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ScorecardSelectPurseNoParticulate",1);
			 CommonMethod.assertisElementPresentTrue("ScorecardSelectPurseNoParticulate",
							"Purse Yes changed the selection");
		}
		else {
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardHsrResponseYes",1);
	        CommonMethod.assertisElementPresentTrue("V2ProjectScorecardHsrResponseYes","Purse Yes changed the selection");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
			List<WebElement> Feature = CommonMethod.findElements("V2projectRatingFeatureName");
			Feature.get(0).click();
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectWPRPDocIcon");
			CommonMethod.assertisElementPresentFalse("V2ProjectWPRPDocIcon","Upload button is visible");
		}
		testlog.info("User cannot change the Certification scorecard response when the Certfication review InProgress");
	}


	public void hsrSubmitReviewV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("When User clicks on Review tab");
		CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "V2ProjectcommentReview");
		testlog.info("And User clicks on Submit DocReview button");
		CommonMethod.WaitUntilClickble("V2ProjectcommentReview", 60).sendKeys("Final Documentation Review");
		testlog.info("And User enter data to commentReview");
		CommonMethod.selectdropdownVisibletext("V2ProjectSelectPhase", "Final Documentation Review");
		testlog.info("And User select the phase");
		CommonMethod.ClickCheckbox("V2ProjectsubmittingHsrcbx");
		testlog.info("And User checks the submit hsr checkbox");
		CommonMethod.RobustclickElementVisible("V2ProjectSubmitPhaseReview", "V2ProjectHSRReviewViewbtn");
		testlog.info("And User clicks on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHSRReviewViewbtn", 0);
		testlog.info("Then User will be redirected to Review list page");
		testlog.pass("**Reviewed Final Precertification Review successfully**");

	}

	public void hsrCompleteReviewV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		/*
		 * Admin Review
		 */
		AdminSearchById(SheetName, rowNum);
		if(SheetName.equalsIgnoreCase("V2Project") && (TestCaseName.equalsIgnoreCase("V2_TC_11_01_HealthSafetyCompleteReviewV2Project"))) {	
			HsrV2ProjectRegisterOptn();
			AddGeneralObservation(SheetName, rowNum);
		}
		hsrCompleteReview(SheetName, rowNum);
	}
	
	public void hsrCompleteReview(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectHSRReviewViewbtn");
		testlog.info("And User clicks on ReviewTab");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHSRReviewViewbtn",0);
		CommonMethod.RobustclickElementVisible("V2ProjectHSRReviewViewbtn", "ReviewAdminActionsButton");
		testlog.info("And User clicks on view button");
		Thread.sleep(2000);
		if(SheetName.equalsIgnoreCase("V2Project") && (TestCaseName.equalsIgnoreCase("V2_TC_11_01_HealthSafetyCompleteReviewV2Project"))) {	
			ValidateGeneralObservationInEditReview(SheetName, rowNum);
		}
		ReviewAdminActionsButton();
		CommonMethod.RobustclickElementVisible("ReviewReturnButton", "ReturnComment");
		testlog.info("And User clicks on Return button");
		CommonMethod.WaitUntilClickble("CommentNarrative", 60)
		.sendKeys("Review Comment");
        testlog.info("And User enter on CommentNarrative field");
        CommonMethod.WaitUntilClickble("ReturnReviewDate", 60).click();
        CommonMethod.WaitUntilClickble("DatePickerOkButton", 60).click();
    	CommonMethod.uploadMultipleFile("FileUpload1", SampleJpgfile, SampleJpgfile,
				"MultipeUploadDeleteicon", 2, "MultipeUploadEnableButtonDeleteLink");
		CommonMethod.WaitUntilClickble("ReturnComment", 60).sendKeys("Rating Review Comment");
		  CommonMethod.WaitUntilClickble("ReturnReviewDate2", 60).click();
	        CommonMethod.WaitUntilClickble("DatePickerOkButton", 60).click();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditAchievedStatus", 0);
		CommonMethod.ClickCheckbox("EditAchievedStatus");
		CommonMethod.VerifyRadioOrCheckboxSelcted("EditAchievedStatus");
		 CommonMethod.WaitUntilClickble("AwardedDate", 60).click();
		 CommonMethod.WaitUntilClickble("DatePickerOkButton", 60).click();
		 CommonMethod.WaitUntilClickble("V2ProjectHsrReviewComment", 60).sendKeys("Hsr Rating Review Comment");
		   testlog.info("And User enter on CommentNarrative field");
	        CommonMethod.WaitUntilClickble("V2ProjectHsrReviewDate", 60).click();
	        CommonMethod.WaitUntilClickble("DatePickerOkButton", 60).click();
		testlog.info("And User select Date");
		CommonMethod.scrollDown();
		Thread.sleep(1000);
		if(SheetName.equalsIgnoreCase("V2Project") && (TestCaseName.equalsIgnoreCase("V2_TC_11_01_HealthSafetyCompleteReviewV2Project"))) {	
			ValidateGeneralObservationInReturnReview(SheetName, rowNum);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewPaymentstatusRadio", 0);
		CommonMethod.ClickCheckbox("ReviewPaymentstatusRadio");
		CommonMethod.VerifyRadioOrCheckboxSelcted("ReviewPaymentstatusRadio");
		testlog.info("And User checks the Paymentstatus checkbox");
		CommonMethod.RobustclickElementVisible("ReviewReturnSubmit", "ReviewedStatus");
		testlog.info("And User clicks on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewedStatus", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ReviewedStatus"), "Reviewed", "Verified Review status");
		testlog.info("And User verifies the Reviewed Status");
		testlog.pass("**Completed Final Precertification Review successfully**");
	}

	public void performanceOptnV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PerformanceTab", 0);
		CommonMethod.RobustclickElementVisible("PerformanceTab", "V2ProjectWPRContinuebtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRContinuebtn", 0);
		CommonMethod.Robustclick("V2ProjectWPRContinuebtn");		
		if (SheetName.equalsIgnoreCase("V2Project")) {
		CommonMethod.verifyDropdownValues("V2ProjectOptConfirmNoEnrollSelect", "ReviewCycle");
		}		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Terms", 0);
		CommonMethod.ClickCheckbox("Terms");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRTermsbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectWPRTermsbtn", "Terms");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Terms", 0);
		CommonMethod.ClickCheckbox("Terms");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRProceedbtn", 0);
		CommonMethod.Robustclick("V2ProjectWPRProceedbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("GoToAccount", 0);
		CommonMethod.JavascriptClickElement("GoToAccount");
		rc.ScorecardLoading();
	}

	public void performanceV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		performanceOptnV2Project(SheetName, rowNum);
		ScorecardfillHSRWPR(20, 1, 40, 40);
		testlog.pass("**Verifies the 21 Purse Yes Scorecard Performance successfully**");
	}

	public void uploadPerformanceDocV2Project(String SheetName) throws IOException, InterruptedException {
		uploadDocumentInFeature(SheetName, 21);
		testlog.pass("**Upload 21 Scorecard Documents successfully**");
	}

	public void wprSubmitReviewV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.Robustclick("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("When User clicks on Review tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "V2ProjectcommentReview");
		testlog.info("And User clicks on Submit DocReview button");
		CommonMethod.WaitUntilClickble("V2ProjectcommentReview", 60).sendKeys("Final Performance Review");
		testlog.info("And User enter data to commentReview field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectSelectPhase", "Final Performance Review");
		testlog.info("And User checks the submit hsr checkbox");		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectsubmittingHsrcbx");
		CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectsubmittingHsrcbx", "HSR checkbox is present ");		
		testlog.info("And User checks the submit wpr checkbox");
		CommonMethod.ClickCheckbox("V2ProjectsubmittingWprcbx");
		CommonMethod.RobustclickElementVisible("V2ProjectSubmitPhaseReview", "V2ProjectWPRReviewViewbtn");
		testlog.info("And User Verifies HSR checkbox should not present");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRReviewViewbtn", 0);
		testlog.info("Then User will be redirected to Review page");
		testlog.pass("**Reviewed Final Precertification Review successfully**");
	}

	public void wprCompleteReviewV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		/** Admin Review */
		AdminSearchById(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectWPRReviewViewbtn");
		testlog.info("And User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRReviewViewbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectWPRReviewViewbtn", "ReviewAdminActionsButton");
		testlog.info("And User clicks on view button");
		ReviewAdminActionsButton();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewReturnButton", 0);
		CommonMethod.RobustclickElementVisible("ReviewReturnButton", "ReturnComment");
		testlog.info("And User clicks on Return button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilClickble("ReturnComment", 60).sendKeys("Final Review");
		testlog.info("And User enter on Comment field");
		CommonMethod.WaitUntilClickble("CommentNarrative", 60).sendKeys("Final Precertification Review");
		testlog.info("And User enter on CommentNarrative field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerButton", 0);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("DatePickerButton", "DatePickerOkButton");
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "ReviewPaymentstatusRadio");
		testlog.info("And User select Date");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWerCommentNarrative", 0);
	    CommonMethod.sendKeys("V2ProjectWerCommentNarrative", "Final Precertification Review");
		CommonMethod.WaitUntilClickble("DatePickerButton2", 60);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("DatePickerButton2", "DatePickerOkButton");
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "ReviewPaymentstatusRadio");
		testlog.info("And User select Date"); 		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditAchievedStatus", 0);
		CommonMethod.ClickCheckbox("EditAchievedStatus");
		CommonMethod.VerifyRadioOrCheckboxSelcted("EditAchievedStatus");
		CommonMethod.WaitUntilClickble("AwardedDate", 60).click();
	    CommonMethod.WaitUntilClickble("DatePickerOkButton", 60).click();
		CommonMethod.scrollDown();
		Thread.sleep(1000);
		CommonMethod.ClickCheckbox("ReviewPaymentstatusRadio");
		testlog.info("And User checks the Payment status checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewReturnSubmit", 0);
		CommonMethod.RobustclickElementVisible("ReviewReturnSubmit", "ReviewedStatus");
		testlog.info("And User clicks on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewedStatus", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ReviewedStatus"), "REVIEWED", "Verified Review status");
		testlog.info("And User verifies the Reviewed Status");
		testlog.pass("**Completed Final Precertification Review successfully**");
	}

	public void supportV2Project(String SheetName, int rowNum, String ProjectType)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		if (CommonMethod.isElementsExist("SupportLoading", 10)) {
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
		CommonMethod.WaitUntilClickble("V2ProjectSubjectInputField", 60).sendKeys("Testing");
		testlog.info("And User enter data Subject field");
		data.setCellData(SheetName, "Subject", rowNum, CommonMethod.getattributeValue("V2ProjectSubjectInputField"));
		testlog.info("FeatureName" + data.getCellData(SheetName, "Subject", rowNum));
		CommonMethod.WaitUntilClickble("V2ProjectDescriptionTextArea", 60).sendKeys("Testing");
		testlog.info("And User enter data Description field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectIssueSubTypeDropdown", 0);
		if (!ProjectType.contains("pilot")) {
			CommonMethod.selectdropdownValue("V2ProjectIssueSubTypeDropdown", "A01 Air Quality");
		} else {
			CommonMethod.selectdropdownValue("V2ProjectIssueSubTypeDropdown", "A01 Fundamental Air Quality");
		}
		testlog.info("And User select IssueSubType");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
		CommonMethod.RobustclickElementVisible("OwnerOrgClick", "OwnerOrg");
		CommonMethod.sendKeys("OwnerOrg", "R");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectOwnerOrgDyn", 0);
		CommonMethod.SelectRandomfromList("SelectOwnerOrgDyn", 1, 3).click();
		testlog.info("And User select IssueSubType");

		CommonMethod.uploadMultipleFile("DocumentsUpload", SampleJpgfile, SampleJpgfile, "MultipeUploadDeleteicon", 2,
				"MultipeUploadEnableButtonDeleteLink");
		testlog.info("And User upload feature document");
		CommonMethod.WaitUntilVisibility("V2ProjectUploadFeatureVerify", 120);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitButton", 0);
		CommonMethod.RobustclickElementVisible("SubmitButton", "V2ProjectBackToTicket");
		testlog.info("And User clicks on Submit button");
		CommonMethod.RobustclickElementVisible("V2ProjectBackToTicket", "V2ProjectTicketListStatus");
		testlog.info("And User clicks on BackToTicket");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectTicketListStatus", 0);
		String TicketStatus = CommonMethod.getText("V2ProjectTicketListStatus");
		testlog.info("TicketStatus: " + TicketStatus);
		CommonMethod.negativesoftassertFieldValid(TicketStatus, "NEW","TicketStatus doesn't match");
		testlog.info("Then User verifies New status");
		testlog.pass("**Raised support ticket successfully**");
	}

	public void editAndValidateProjectInformationV2Project(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "ProjectInformationButton");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectInformationButton", 0);
		CommonMethod.RobustclickElementVisible("ProjectInformationButton", "V2ProjectProjectScope");
		testlog.info("And User clicks on Project Information Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectEditProjectNameDisabled",1);
		CommonMethod.assertisElementPresentTrue("V2ProjectEditProjectNameDisabled", "ProjectName is not disabled");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectEditProjectAreaDisabled",1);
		CommonMethod.assertisElementPresentTrue("V2ProjectEditProjectAreaDisabled", "ProjectArea is not disabled");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdateProjectsName"),
				data.getCellData(SheetName, "ProjectName", rowNum), "Update ProjectName Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdateProjectsarea"),
				data.getCellData(SheetName, "Area", rowNum), "Update Area Error Mismatch");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectUpdateCs");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectUpdateSpacetypes");
		testlog.info("And User verifies the Project added details");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectScope", 0);
		CommonMethod.sendKeys("V2ProjectProjectScope", data.getCellData(SheetName, "ProjectScope", rowNum));
		testlog.info("And User enter data to Scope");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectGoals", 0);
		CommonMethod.sendKeys("V2ProjectProjectGoals", data.getCellData(SheetName, "ProjectGoals", rowNum));
		testlog.info("And User enter data to Goal");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectSaveChangesButton", "FeatureNextStep");
		testlog.info("And User clicks on Save button");
		testlog.info("Then User will be redirected to Dashboard page");
		testlog.info("**Project Information data updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartBuilding", 0);
		/** Validate updated project information fields */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "ProjectInformationButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectInformationButton", 0);
		CommonMethod.RobustclickElementVisible("ProjectInformationButton", "V2ProjectProjectScope");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectProjectScope"),
				data.getCellData(SheetName, "ProjectScope", rowNum), "Project scope data doesn't match");
		testlog.info("**Project scope data updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectGoals", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectProjectGoals"),
				data.getCellData(SheetName, "ProjectGoals", rowNum), "Project goals data doesn't match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("V2ProjectEnterpriseProviders"),
				data.getCellData(SheetName, "EnterpriseProvidersName", rowNum), "EnterpriseProvidersName Mismatch");
		testlog.info("And User verifies the updated details");
		testlog.pass("**Project goals data updated successfully**");
	}

	public void editAndValidateOwnerInformationV2Project(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "OwnerInformationButton");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerInformationButton", 0);
		CommonMethod.RobustclickElementVisible("OwnerInformationButton", "V2ProjectOrganizationIndustry");
		testlog.info("And User clicks on Owner Information Button");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("OwnerOrgClick"),
				data.getCellData(SheetName, "OrgName", rowNum), "Updated OrgName Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesOwnername"),
				data.getCellData(SheetName, "OwnerName", rowNum), "Updated OwnerName Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesOwneremail"),
				data.getCellData(SheetName, "OwnerEmail", rowNum), "Updated OwnerEmail Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesOwnerphone"),
				data.getCellData(SheetName, "PhoneNum", rowNum), "Updated PhoneNum Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValue("V2ProjectUpdatesOrganizationIndustry"),
				data.getCellData(SheetName, "OrgIndustry", rowNum), "Updated OrgIndustry Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesOrganizationwebsite"),
				data.getCellData(SheetName, "Website", rowNum), "Updated Website Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValue("V2ProjectUpdatesOrganizationOverview"),
				data.getCellData(SheetName, "Overview", rowNum), "Updated Overview Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getSelectedDropdownValue("V2ProjectUpdatesCountryRegion"),
				data.getCellData(SheetName, "Country", rowNum), "Updated Country Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesStreetaddress"),
				data.getCellData(SheetName, "OwnerStreet", rowNum), "Updated OwnerStreet Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesLocationCity"),
				data.getCellData(SheetName, "OwnerCity", rowNum), "Updated OwnerCity Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesPostalCode"),
				data.getCellData(SheetName, "OwnerPostalCode", rowNum), "Updated OwnerPostalCode Error Mismatch");
		testlog.info("And User verifies the added Owner details");
		CommonMethod.verifyDropdownValues("V2ProjectOrganizationIndustry", "OrganizationIndustry");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOrganizationIndustry", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectOrganizationIndustry",
				data.getCellData(SheetName, "OrgIndustry", rowNum));
		testlog.info("And User select OrgIndustry");
		CommonMethod.verifyDropdownValues("V2ProjectCountry", "Country");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectCountry", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectCountry", data.getCellData(SheetName, "OwnerCountry", rowNum));
		testlog.info("And User select Country");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectState", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectState", data.getCellData(SheetName, "OwnerState", rowNum));
		testlog.info("And User select State");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectSaveChangesButton", "FeatureNextStep");
		testlog.info("And User clicks on Save button");
		testlog.info("Then User will be redirected to Dashboard page");
		testlog.pass("**Owner Information data updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartBuilding", 0);
		/*
		 * Validate updated owner information fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "OwnerInformationButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerInformationButton", 0);
		CommonMethod.RobustclickElementVisible("OwnerInformationButton", "V2ProjectOrganizationIndustry");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectCountry", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("V2ProjectCountry"),
				data.getCellData(SheetName, "Country", rowNum), "Country name doesn't match");
		testlog.info("**Country name updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectState", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectState"),
				data.getCellData(SheetName, "OwnerState", rowNum), "State name doesn't match");
		testlog.info("And User verifies the updated details");
		testlog.pass("**Verifies Information data updated successfully**");
	}

	public void editAndValidateAddressV2Project(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAddressButton");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddressButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAddressButton", "V2ProjectBillingName");
		testlog.info("And User clicks on Address button");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getSelectedDropdownValue("V2ProjectUpdatesPACountryRegion"),
				data.getCellData(SheetName, "Country", rowNum), "Address Updated Country Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getSelectedDropdownValue("V2ProjectUpdatesPAStateProvince"),
				data.getCellData(SheetName, "State", rowNum), "Address Updated State Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesPAStreet"),
				data.getCellData(SheetName, "Street", rowNum), "Address Updated Street Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesPACity"),
				data.getCellData(SheetName, "City", rowNum), "Address Updated City Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesPAPostalCode"),
				data.getCellData(SheetName, "PostalCode", rowNum), "Address Updated PostalCode Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesBABillingName"),
				data.getCellData(SheetName, "OwnerName", rowNum), "Address Updated OwnerName Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValue("V2ProjectUpdatesBABillingOrganization"),
				data.getCellData(SheetName, "OrgName", rowNum), "Updated OrgName Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getSelectedDropdownValue("V2ProjectUpdatesBACountryRegion"),
				data.getCellData(SheetName, "Country", rowNum), "Address Updated Country Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesBAStreetaddress"),
				data.getCellData(SheetName, "OwnerStreet", rowNum), "Address Updated Street Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesBACity"),
				data.getCellData(SheetName, "OwnerCity", rowNum), "Address OwnerCity Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdatesBAPostalCode"),
				data.getCellData(SheetName, "OwnerPostalCode", rowNum), "Address OwnerPostalCode Error Mismatch");
		testlog.info("And User verifies the added Owner details");
		CommonMethod.WaitUntilVisibility("V2ProjectBillingName", 60);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectBillingName"),
				data.getCellData(SheetName, "OwnerName", rowNum), "Billing name doesn't match");
		testlog.info("**Billing name updated successfully**");
		CommonMethod.WaitUntilVisibility("V2ProjectBillingOrganization", 60);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectBillingOrganization"),
				data.getCellData(SheetName, "OrgName", rowNum), "Billing organization name doesn't match");
		testlog.info("**Billing organization name updated successfully**");
		CommonMethod.WaitUntilVisibility("V2ProjectBillingCountry", 60);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("V2ProjectBillingCountry"),
				data.getCellData(SheetName, "Country", rowNum), "Billing country name doesn't match");
		testlog.info("**Country name updated successfully**");
		CommonMethod.WaitUntilVisibility("V2ProjectBillingStreetAddress", 60);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectBillingStreetAddress"),
				data.getCellData(SheetName, "OwnerStreet", rowNum), "Billing street name doesn't match");
		testlog.info("**Street address name updated successfully**");
		CommonMethod.WaitUntilVisibility("V2ProjectBillingCity", 60);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectBillingCity"),
				data.getCellData(SheetName, "OwnerCity", rowNum), "Billing city name doesn't match");
		testlog.info("**City name updated successfully**");
		String PostalCode = USfaker.address().zipCode();
		CommonMethod.clearAndSendKey("V2ProjectBillingPostalCode", PostalCode);
		CommonMethod.WaitUntilVisibility("V2ProjectSaveChangesButton", 60);
		CommonMethod.RobustclickElementVisible("V2ProjectSaveChangesButton", "FeatureNextStep");
		testlog.info("And User clicks on Save button");
		testlog.pass("**Address data updated successfully**");
		CommonMethod.WaitUntilVisibility("V2ProjectStartBuilding", 60);
		/*
		 * Validate updated address fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAddressButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddressButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAddressButton", "V2ProjectBillingName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectBillingPostalCode", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectBillingPostalCode"),
				PostalCode, "Billing postal code doesn't match");
		testlog.info("And User verifies the updated Address details");
		testlog.pass("**Verifies Address data updated successfully**");
	}

	public void editAndValidateAdminV2Project(String SheetName, int rowNum) throws Exception {
		AdminSearchById(SheetName, rowNum);
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		testlog.info("When User clicks on EditTab");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectUpdatesAdminStatus");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectUpdatesAdminTestProject");
		testlog.info("And User verifies the added Admin details");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAdminFieldsButton", "V2projectAdminBillingStatus");
		testlog.info("And User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectAdminBillingStatus", 0);
		CommonMethod.sendKeys("V2projectAdminBillingStatus", data.getCellData(SheetName, "BillingStatus", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectAdminBillingChallengeNote", 0);
		CommonMethod.sendKeys("V2projectAdminBillingChallengeNote",
				data.getCellData(SheetName, "ChallengeNote", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectAdminBillingCommunicationNote", 0);
		CommonMethod.sendKeys("V2projectAdminBillingCommunicationNote",
				data.getCellData(SheetName, "CommunicationNote", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectAdminBillingNote", 0);
		CommonMethod.sendKeys("V2projectAdminBillingNote", data.getCellData(SheetName, "BillingNote", rowNum));
		testlog.info("And User enter data to Note");
		CommonMethod.uploadMultipleFile("DocumentsUpload", SampleJpgfile, SampleJpgfile, "MultipeUploadDeleteicon", 2,
				"MultipeUploadEnableButtonDeleteLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectSaveChangesButton", "FeatureNextStep");
		testlog.info("And User clicks on Save button");
		testlog.info("Then User will be redirected to Dashboard page");
		testlog.pass("**Admin data updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartBuilding", 0);
		/*
		 * Validate updated admin fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAdminFieldsButton", "V2projectAdminBillingStatus");
		CommonMethod.scrolldowntoElement("V2projectAdminBillingStatus");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectAdminBillingStatus", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2projectAdminBillingStatus"),
				data.getCellData(SheetName, "BillingStatus", rowNum), "Billing status value doesn't match");
		testlog.info("**Billing status updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectAdminBillingChallengeNote", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2projectAdminBillingChallengeNote"),
				data.getCellData(SheetName, "ChallengeNote", rowNum), "Challenge note value doesn't match");
		testlog.info("**Challenge note updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectAdminBillingCommunicationNote", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValue("V2projectAdminBillingCommunicationNote"),
				data.getCellData(SheetName, "CommunicationNote", rowNum), "Communication note value doesn't match");
		testlog.info("**Communication updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectAdminBillingNote", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2projectAdminBillingNote"),
				data.getCellData(SheetName, "BillingNote", rowNum), "Billing note value doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
		CommonMethod.Robustclick("V2ProjectSaveChangesButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectSaveChangesButton", 1);
		testlog.info("And User verifies the updated Admin details");
		testlog.info("**Billing note updated successfully**");
		testlog.pass("**Verifies Admin data updated successfully**");
	}
	
	public void editValidateAchievementAdminV2Project() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditHsrAchieved", 0);
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectEditHsrAchieved");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectEditWprAchieved");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectEditWerAchieved");
	}
	
	public void editAndValidateAchievementAdminV2Project(String SheetName, int rowNum) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectValidatePrecertificationDisableDate", 1);
		CommonMethod.negativeAssertElementPresentTrue("V2ProjectValidatePrecertificationDisableDate", "Precertification Date is enable ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectValidateCertificationDisableDate", 1);
		CommonMethod.negativeAssertElementPresentTrue("V2ProjectValidateCertificationDisableDate", "Certification Date is enable ");
//		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectValidateWERDisableDate", 1);
//		CommonMethod.negativeAssertElementPresentTrue("V2ProjectValidateWERDisableDate", "WER Date is enable ");
		testlog.info("And User verifies the updated Admin details");
	}
	
	public void overviewV2Project(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OverviewTab", 0);
		CommonMethod.RobustclickElementVisible("OverviewTab", "V2ProjectProjectID");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectID", 0);
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectProjectID"),
				data.getCellData(SheetName, "ProjectID", rowNum), "Project id value doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectName", 0);
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectProjectName"),
				data.getCellData(SheetName, "ProjectName", rowNum), "Project name doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectArea", 0);
		String getArea = CommonMethod.getText("V2ProjectProjectArea").replace("sq ft", "").replace(",", "").trim();
		CommonMethod.negativesoftassertFieldValidEquals(getArea, data.getCellData(SheetName, "Area", rowNum),
				"Project area value doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectStreet", 0);
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectProjectStreet"),
				data.getCellData(SheetName, "Street", rowNum), "Street name doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectCity", 0);
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectProjectCity"),
				data.getCellData(SheetName, "City", rowNum), "City name doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectStateOverview", 0);
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectProjectStateOverview"),
				data.getCellData(SheetName, "State", rowNum), "State name doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectCountry", 0);
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectProjectCountry"),
				data.getCellData(SheetName, "Country", rowNum), "Country name doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOwnerIndustry", 0);
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectOwnerIndustry"),
				data.getCellData(SheetName, "OrgIndustry", rowNum), "Owner industry name doesn't match");
		testlog.info("Then User verfies Project id");
		testlog.info("And User verfies Project Name");
		testlog.info("And User verfies Area");
		testlog.info("And User verfies Country");
		testlog.info("And User verfies State");
		testlog.info("And User verfies Street name");
		testlog.info("And User verfies City");
		testlog.info("And User verfies Product area");
		testlog.info("And User verfies ProjectName");
		testlog.info("And User verfies City name");
		testlog.info("And User verfies Country name");
		testlog.info("And User verfies Owner industry name");
		testlog.pass("**verifies overview fields successfully**");
	}

	public void profileV2Project(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileTab", 0);
		CommonMethod.RobustclickElementVisible("ProfileTab", "V2ProjectGeneralInformation");
		testlog.info("When User clicks on ProfileTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectGeneralInformation", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectGeneralInformation", "V2ProjectProjectBio");
		testlog.info("And User clicks on GeneralInformation");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectBio", 0);
		CommonMethod.clearAndSendKey("V2ProjectProjectBio", "Project bio testing");
		testlog.info("And User enter data to ProjectBio field");
		CommonMethod.uploadFile("FileUpload1", SampleJpgfile);
		testlog.info("And User upload document for ProfileLogo and verify upload icon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectFeviconLogo", 0);
		CommonMethod.uploadFile("FileUpload1", SampleJpgfile, "UploadFileVerifyScorecard");
		testlog.info("And User upload document for ProfileImage and verify upload icon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPrimaryProfileImageDeleteVerify", 0);
		CommonMethod.uploadMultipleFile("V2ProjectProfileImage", SampleJpgfile, SampleJpgfile,
				"MultipeUploadDeleteicon", 3, "MultipeUploadEnableButtonDeleteLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectFeviconLogo", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPrimaryProfileImageDeleteVerify", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProfileImageDeleteVerify", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectSave", "V2ProjectGeneralInformation");
		testlog.info("And User clicks on Save button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProfileUpdatedToastMessage", 0);
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectProfileUpdatedToastMessage"),
				"Updated Profile!", "Verified profile updated toast message");
		testlog.info("Then User verifies Profile updated successfully toast message");
		testlog.pass("**General Information data updated successfully**");
		CommonMethod.WaitUntilInVisibility("V2ProjectProfileUpdatedToastMessage", 60);
		/** V2ProjectCertificationStory */
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectCertificationStory", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectCertificationStory", "V2ProjectYourObjective");
		testlog.info("And User clicks on CertificationStory");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourObjective", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourObjective", "Your objective testing");
		testlog.info("And User enter data to Objective field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourOrganization", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourOrganization", "Your organization testing");
		testlog.info("And User enter data to YourOrganization field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourWellGoals", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourWellGoals", "Your well goals testing");
		testlog.info("And User enter data to Goals field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourWellProject", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourWellProject", "Your well project testing");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourWellFeatures", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourWellFeatures", "Your well features testing");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourWellCertification", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourWellCertification", "Your well certification testing");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPostCertificationMetric", 0);
		CommonMethod.clearAndSendKey("V2ProjectPostCertificationMetric", "Post certification metric testing");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourCompanyCulture", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourCompanyCulture", "Your company culture testing");
		testlog.info("And User enter data to Narrative textboxes field");
		CommonMethod.RobustclickElementVisible("V2ProjectSave", "V2ProjectProfileUpdatedToastMessage");
		testlog.info("And User clicks on Save button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProfileUpdatedToastMessage", 0);
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectProfileUpdatedToastMessage"),
				"Updated Profile!", "Verified profile updated toast message");
		testlog.info("Then User verifies Profile updated successfully toast message");
		testlog.info("And User verifies the added details");
		testlog.pass("**Certification story data updated successfully**");
	}

	public void searchFilterScoreCardV2Project() throws IOException, InterruptedException {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardSearchBox", 0);
		CommonMethod.sendKeys("V2ProjectScoreCardSearchBox", "Meet Thresholds for Particulate Matter");
		testlog.info("When User enter data to SearchBox field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("table", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectScorecardPartCount"),
				"Meet Thresholds for Particulate Matter", "searchFilter by name doesn't match");
		CommonMethod.negativesoftassertFieldValid(
				Integer.toString(CommonMethod.ElementSize("V2ProjectScorecardPartCount")), "1",
				"searchFilter by count doesn't match");
		testlog.info("Then User will be redirected to Scorecard page");
		testlog.info("And User verifies Feature Part Count");
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardRefreshButton", "table");
		testlog.info("And User click Refresh Button");
		testlog.pass("**Verifies Scorecard Search filter successfully**");
	}

	public void scorecardOptionFilterV2Project(String SheetName, int rowNum, String Type, String Commodity)
			throws Exception {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		if (Type.equalsIgnoreCase("WELLCore")) {
			CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "V2ProjectScorecardApplybutton");
			testlog.info("And User clicks on Filter button");
			generic.filterGeneric(Commodity,"V2ProjectScorecardResponseFilter","V2ProjectScorecardYesFilter", "V2ProjectScorecardPartCount","128","true");
			generic.filterGeneric(Commodity,"V2ProjectScorecardVerificationFilter","V2ProjectScorecardOnsitePhotographsFilter", "V2ProjectScorecardPartCount","34","true");
			generic.filterGeneric(Commodity,"V2ProjectScorecardPartTypeFilter","V2ProjectScorecardPreconditionsFilter", "V2ProjectScorecardPartCount","46","true");
			generic.filterGeneric(Commodity,"V2ProjectScorecardSpaceTypeFilter","V2ProjectScorecardCommercialKitchenSpacesFilter", "V2ProjectScorecardPartCount","215","true");
			generic.filterGeneric(Commodity,"V2ProjectScorecardRatingsFilter","V2ProjectScorecardWELLHealthSafetyFilter", "V2ProjectScorecardPartCount","26","true");
			generic.filterGeneric(Commodity,"V2ProjectScorecardStrategyTypeFilter","V2ProjectScorecardPerformanceFilter", "V2ProjectScorecardPartCount","18","true");
			generic.filterGeneric(Commodity,"V2ProjectScorecardCrosswalkFilter","V2ProjectScorecardLEEDFilter", "V2ProjectScorecardPartCount","21","true");
			generic.filterGeneric(Commodity,"V2ProjectScorecardResponsiblePartyFilter","V2ProjectScorecardFacilityManagerFilter", "V2ProjectScorecardPartCount","28","true");
			testlog.pass("**All Scorecard filter options successfully**");
		}
	}

	public void SearchFilterDocument(String documentName, String filterOption, String fileCount)
			throws IOException, InterruptedException {
		if (filterOption.equalsIgnoreCase("Scorecard")) {
			CommonMethod.click("V2ProjectScorecardDoc");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDecumentSearchBox", 0);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDecumentSearchBox", 0);
		CommonMethod.clearAndSendKey("V2ProjectDecumentSearchBox", documentName);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PagnitionResultCount",  Integer.parseInt(fileCount));
		int V2ProjectScoreDocCount = CommonMethod.ElementSize("PagnitionResultCount");
		String V2ProjectDocCounts = Integer.toString(V2ProjectScoreDocCount);
		CommonMethod.negativesoftassertFieldValid(V2ProjectDocCounts, fileCount, "Feature Search Filter doesn't match");
		testlog.pass("**Verifies search filter successfully **");
	}

	public void DashboardV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEstimatePriceButton", 0);
		CommonMethod.WaitUntilPresence("V2ProjectReviewAgreeButton", 30);
		CommonMethod.WaitUntilPresence("V2ProjectSignNow", 30);
		CommonMethod.WaitUntilPresence("V2ProjectStartBuilding", 30);
		CommonMethod.WaitUntilPresence("V2ProjectDashboardEnrollButton", 30);
		CommonMethod.WaitUntilPresence("V2ProjectDashboardLearnMoreButton", 30);
		CommonMethod.WaitUntilPresence("V2ProjectDashboardLearnMoreButton1", 30);
		CommonMethod.WaitUntilPresence("EnrollTab", 30);
		CommonMethod.WaitUntilPresence("BiilingTab", 30);
		CommonMethod.WaitUntilPresence("TeamTab", 30);
		CommonMethod.WaitUntilPresence("OverviewTab", 30);
		CommonMethod.WaitUntilPresence("PricingTab", 30);
		CommonMethod.WaitUntilPresence("EditTab", 30);
		testlog.pass("**Verifies Dashboard fields and SideBar Navigation tab successfully **");
	}
	
	public void ValidateAlternativesTabDisabledButtons(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AlternativesTab", 0);
		CommonMethod.RobustclickElementVisible("AlternativesTab","V2ProjectSubmitAAPDisabledBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSubmitAAPDisabledBtn", 0);	
	    CommonMethod.assertisElementPresentTrue("V2ProjectSubmitAAPDisabledBtn", "SubmitAAP button is enabled");
	    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSubmitEPDisabledBtn", 0);	
	    CommonMethod.assertisElementPresentTrue("V2ProjectSubmitEPDisabledBtn", "SubmitEP button is enabled");    
	    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);	
	} 
	
	public void ValidateAdminOverviewTabEmptyVersionDropdown(String SheetName, int rowNum) throws IOException, InterruptedException {	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Overviewtab", 0);
	    CommonMethod.RobustclickElementVisible("Overviewtab","V2ProjectSelectVersionDisabledDropdown");
	    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSelectVersionDisabledDropdown", 0);	
	 	CommonMethod.assertisElementPresentTrue("V2ProjectSelectVersionDisabledDropdown", "SelectVersion dropdown is enabled");
	} 
	
	public void AdminSearchById(String SheetName, int rowNum) throws IOException, InterruptedException {		
		/** Admin Review */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLCertificationNavBar");
		CommonMethod.RobustclickElementVisible("AdminWELLCertificationNavBar", "AdminV2ProjectId");
		testlog.info("And User clicks on Admin WELL Certification from top menu under Projects");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectVersion", 0);
		CommonMethod.verifyDropdownValues("V2ProjectProjectVersion", "V2ProjectAdminProjectVersion");
		CommonMethod.verifyDropdownValues("UpdateInvoiceCountryBilling", "Country");
		CommonMethod.verifyDropdownValues("V2ProjectProjectPhase", "V2ProjectPhase");
		CommonMethod.verifyDropdownValues("V2ProjectRegistrationStatusDropdown", "V2ProjectStatus");
		CommonMethod.verifyDropdownValues("V2ProjectAgreementStatus", "AgreementStatus");
		CommonMethod.verifyDropdownValues("V2ProjectAdminSpaceType", "SpaceType");
		CommonMethod.WaitUntilClickble("AdminV2ProjectId", 60)
				.sendKeys(data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter on ProjectID in  AdminV2ProjectId field");
		CommonMethod.RobustclickElementVisible("AdminV2ProjectApplybtn", "SearchResultIDVerify");
		testlog.info("And User clicks on Apply button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchResultIDVerify", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("SearchResultIDVerify"),
				data.getCellData(SheetName, "ProjectID", rowNum), "ProjectID doesn't matches in search");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchResultIDVerify", 0);
		CommonMethod.RobustclickElementVisible("SearchResultIDVerify", "WellV2DashboardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		
	}
	
	public void ValidateFilterStatusByAdmin(String SheetName, int rowNum, String RegStatusFilter) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminWELLCertificationNavBar");
		CommonMethod.RobustclickElementVisible("AdminWELLCertificationNavBar", "AdminV2ProjectId");
		testlog.info("And User clicks on Admin WELL Certification from top menu under Projects");
		CommonMethod.WaitUntilClickble("AdminV2ProjectId", 60)
				.sendKeys(data.getCellData(SheetName, "ProjectID", rowNum));
		testlog.info("And User enter on ProjectID in  AdminV2ProjectId field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRegistrationStatusDropdown", 0);
		CommonMethod.verifyDropdownValues("V2ProjectRegistrationStatusDropdown", "V2ProjectStatus");
		CommonMethod.verifyDropdownValues("V2ProjectSubscribedToWELL", "V2ProjectSubscribedToWellAtScale");
		CommonMethod.selectdropdownVisibletext("V2ProjectRegistrationStatusDropdown", RegStatusFilter);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardApplybutton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardApplybutton", "V2ProjectValidateRegistrationStatus");		
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectValidateRegistrationStatus", 0);
		List<WebElement> regStatus = CommonMethod.findElements("V2ProjectValidateRegistrationStatus");
		for(int i=0; i<regStatus.size(); i++) {				
			String actualRegStatus = regStatus.get(i).getText();
	        CommonMethod.negativesoftassertFieldValid(actualRegStatus.toLowerCase(), RegStatusFilter.toLowerCase(), RegStatusFilter.toLowerCase() +": Admin User Status does not matched ");
	        testlog.info("Then Admin User Verifies "+RegStatusFilter.toLowerCase()+": Admin User Subscription Status");
		}
		if(TestCaseName.equalsIgnoreCase("V2_TC_08C_02_AgreementAndBillingMainlandChinaV2Project")) {
			rc.RemoveSpaceAndValidate("V2ProjectValidateAgreementStatus", "Signed");
		}		
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("SearchResultIDVerify", 1);
		int var = CommonMethod.ElementSize("SearchResultIDVerify");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var), "1", "Admin User V2 Search failed");
	}

	public void werSubmitReviewV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("When User clicks on Review tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "V2ProjectcommentReview");
		testlog.info("And User clicks on Submit DocReview button");
		if(TestCaseName.contains("EquitySubmitReviewV2ProjectSeperateReview")) {
		CommonMethod.WaitUntilClickble("V2ProjectcommentReview", 60).sendKeys("Preliminary Equity Rating Review");
		testlog.info("And User enter data to commentReview");
		CommonMethod.selectdropdownVisibletext("V2ProjectSelectPhase", "Preliminary Equity Rating Review");
		testlog.info("And User select the phase");
		} else {
		CommonMethod.WaitUntilClickble("V2ProjectcommentReview", 60).sendKeys("Preliminary Documentation Review");
		testlog.info("And User enter data to commentReview");
		CommonMethod.selectdropdownVisibletext("V2ProjectSelectPhase", "Preliminary Documentation Review");
		testlog.info("And User select the phase");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmittingV2cbx", 0);
		CommonMethod.ClickCheckbox("V2ProjectsubmittingV2cbx");
		if (!TestCaseName.contains("LocationAccountSubmit")) {
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectsubmittingHsrcbx");
		CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectsubmittingHsrcbx", "HSR checkbox is present ");	
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectsubmittingWprcbx");
		CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectsubmittingWprcbx", "WPR checkbox is present ");	
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectsubmittingWercbx", 1);
		CommonMethod.ClickCheckbox("V2ProjectsubmittingWercbx");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectsubmittingWercbx");
		testlog.info("And User checks the submit wer checkbox");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSubmitPhaseReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectSubmitPhaseReview", "V2ProjectWERReviewViewbtnTwo");
		testlog.info("And User clicks on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWERReviewViewbtnTwo", 0);
		testlog.info("Then User will be redirected to Review list page");
		testlog.pass("**Reviewed Final Precertification Review successfully**");
	}

	public void werCompleteReviewV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		/** Admin Review */
		AdminSearchById(SheetName, rowNum);
		werCompleteReview(SheetName, rowNum);
	}
	
		public void werCompleteReview(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.JavascriptClickElement("ReviewTab");
		testlog.info("And User clicks on ReviewTab");
		if(TestCaseName.contains("EquityCompleteReviewV2Project")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWERReviewViewbtnTwo", 0);
		CommonMethod.Robustclick("V2ProjectWERReviewViewbtnTwo");	
		} else {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWERReviewViewbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectWERReviewViewbtn", "ReviewAdminActionsButton");
		}
		testlog.info("And User clicks on view button");
		ReviewAdminActionsButton();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewReturnButton", 0);
		CommonMethod.RobustclickElementVisible("ReviewReturnButton", "ReturnComment");
		testlog.info("And User clicks on Return button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilClickble("ReturnComment", 60).sendKeys("Preliminary Equity Rating Review");
		testlog.info("And User enter on Comment field");
		CommonMethod.WaitUntilClickble("CommentNarrative", 60).sendKeys("Preliminary Equity Rating Review");
		testlog.info("And User enter on CommentNarrative field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerButton", 0);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("DatePickerButton", "DatePickerOkButton");
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "ReviewPaymentstatusRadio");
		testlog.info("And User select Date");		
		CommonMethod.scrolldowntoElement("WerText");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWerCommentNarrative", 0);
	    CommonMethod.sendKeys("V2ProjectWerCommentNarrative", "Final Precertification Review");
		CommonMethod.WaitUntilClickble("DatePickerButton2", 60);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("DatePickerButton2", "DatePickerOkButton");
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "ReviewPaymentstatusRadio");
		testlog.info("And User select Date");		
		if (CommonMethod.isElementsExist("EditAchievedStatus", 10)) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditAchievedStatus", 0);
		CommonMethod.ClickCheckbox("EditAchievedStatus");
		CommonMethod.VerifyRadioOrCheckboxSelcted("EditAchievedStatus");
		CommonMethod.WaitUntilClickble("AwardedDate", 60).click();
		CommonMethod.WaitUntilClickble("DatePickerOkButton", 60).click();
		CommonMethod.scrollDown();
		Thread.sleep(1000);
		CommonMethod.ClickCheckbox("ReviewPaymentstatusRadio");
		testlog.info("And User checks the Payment status checkbox");
		}
		if (CommonMethod.isElementsExist("ReviewPaymentstatusRadio", 10)) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewPaymentstatusRadio", 0);
		CommonMethod.ClickCheckbox("ReviewPaymentstatusRadio");
		testlog.info("And User checks the Payment status checkbox");
	    }
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewReturnSubmit", 0);
		CommonMethod.Robustclick("ReviewReturnSubmit");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ReviewReturnSubmit", 1);
		testlog.info("And User clicks on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewedStatus", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ReviewedStatus"), "REVIEWED", "Verified Review status");
		testlog.info("And User verifies the Reviewed Status");
		testlog.pass("**Completed Final Precertification Review successfully**");
	}

		
	public void WERV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EquityTab", 0);
		CommonMethod.click("EquityTab");
		if (CommonMethod.isElementsExist("V2ProjectWPRContinuebtn", 20)) {
			CommonMethod.WaitUntilVisibility("V2ProjectWPRContinuebtn", 60);
			CommonMethod.RobustclickElementVisible("V2ProjectWPRContinuebtn", "Terms");
			CommonMethod.WaitUntilVisibility("Terms", 60);
			CommonMethod.ClickCheckbox("Terms");
			CommonMethod.RobustclickElementVisible("V2ProjectWERTermsbtn", "V2ProjectWERProceedbtn");
			CommonMethod.WaitUntilPresence("V2ProjectWERProceedbtn", 120);
			CommonMethod.ClickCheckbox("Terms");
			CommonMethod.WaitUntilVisibility("V2ProjectWERProceedbtn", 60);
			CommonMethod.Robustclick("V2ProjectWERProceedbtn");
			CommonMethod.WaitUntilInVisibility("ScorecardPageLoading", Scorecardtimeout);
			CommonMethod.WaitUntilVisibility("ApplicableVersion", Scorecardtimeout);
		}
		testlog.pass("**Verifies the 21 Purse Yes Scorecard Performance successfully**");
	}

	public void validCommentsV2Project(String SheetName, int rowNum, String FeatureName)
			throws IOException, InterruptedException {
		// Add comment
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 0);
		Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardCommentbtn", 0);
				CommonMethod.assertisElementPresentTrue("V2ProjectScorecardCommentbtn", "Comment plus icon is visible");
				CommonMethod.RobustclickElementVisible("V2ProjectScorecardCommentbtn",
						"V2ProjectScorecardCommnettextarea");
				testlog.info("Add comment modal open successfully!!");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectscorecardAddCommentsubmit", 0);
				CommonMethod.JavascriptClickElement("V2ProjectscorecardAddCommentsubmit");
				CommonMethod.negativesoftassertPageSource("Textarea is required.", "Text area error message");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardCommnettextarea", 0);
				CommonMethod.sendKeys("V2ProjectScorecardCommnettextarea",
						data.getCellData(SheetName, "Comment", rowNum));
				testlog.info(data.getCellData(SheetName, "Comment", rowNum) + "fetched comment");
				CommonMethod.click("V2ProjectscorecardAddCommentsubmit");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardVerifyAddComment", 0);
				testlog.pass("**Verifies the Add Comment in Scorecard Feature successfully**");
				// Edit comment
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardEditComment", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectScorecardEditComment",
						"V2ProjectScorecardCommentUpdatebtn");
				testlog.info("Edit comment modal open successfully!!");
				CommonMethod.clearAndSendKey("V2ProjectScorecardCommnettextarea",
						data.getCellData(SheetName, "UpdateComment", rowNum));
				CommonMethod.click("V2ProjectScorecardCommentUpdatebtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardVerifyUpdateComment",1);
				String actualcomment = CommonMethod.findElement("V2ProjectScorecardVerifyUpdateComment").getText();
				String expectedcomment = data.getCellData(SheetName, "UpdateComment", rowNum);
				CommonMethod.negativesoftassertFieldValid(actualcomment, expectedcomment, "Updated Comment mismatch");
				testlog.info("Comment Updated successfully!!");
				// delete comment
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecarddeleteComment", 0);
				CommonMethod.Robustclick("V2ProjectScorecarddeleteComment");
				testlog.info("User clicks on delete icon**");
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectScorecardVerifyAddComment");
				CommonMethod.assertisElementPresentFalse("V2ProjectScorecardVerifyAddComment",
						"Comment deleted icon is visible", 60);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardCommentbtn", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectScorecardCommentbtn",
						"V2ProjectScorecardCommnettextarea");
				testlog.info("Add comment modal open successfully!!");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardCommnettextarea", 0);
				CommonMethod.sendKeys("V2ProjectScorecardCommnettextarea",
						data.getCellData(SheetName, "Comment", rowNum));
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPrivateCheckbox", 0);
				if (TestCaseName.contains("V2_TC_08A_04_UploadFeatureDocumentInScorecardV2Project")) {
				CommonMethod.ClickCheckbox("V2ProjectPrivateCheckbox");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectscorecardAddCommentsubmit", 0);
				CommonMethod.click("V2ProjectscorecardAddCommentsubmit");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardVerifyAddComment", 0);
				testlog.pass("**Verifies the Add Comment in Scorecard Feature successfully**");
				testlog.pass("**Verifies the Edit Comment in Scorecard Feature successfully**");
				testlog.pass("**Verifies the Deleted Comment in Scorecard Feature successfully**");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardVerifyCommentPaperIcon", 2);
		CommonMethod.negativesoftassertFieldValid(
				String.valueOf(CommonMethod.ElementSize("V2ProjectScorecardVerifyCommentPaperIcon")), "2",
				"Comment Papericon not visible");
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		RefreshScorecard();
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
	
	public void validPrivateCommentsV2ProjectAsTeam(String FeatureName)
			throws IOException, InterruptedException {
		// Add comment
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 0);
		Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectScorecarddeleteComment");
				CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectScorecarddeleteComment","Private Comment delete icon is present");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
	

	public void featureAdminScorecardReview(String Type, String SheetName, int rowNum, String FeatureName,
			String SelectResponseReview, String SelectcommentReview) throws IOException, InterruptedException {
		boolean flag = false;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 0);
		List<WebElement> Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardAdminReviewButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardAdminReviewButton",
				"V2ProjectScorecardSelectResponseReview");
		testlog.info("And User clicks on Admin Review Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardSelectResponseReview", 0);
		CommonMethod.selectdropdownValue("V2ProjectScorecardSelectResponseReview", SelectResponseReview);
		testlog.info("And User select Response Review");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardSelectcommentReview", 0);
		CommonMethod.selectdropdownValue("V2ProjectScorecardSelectcommentReview", SelectcommentReview);
		if (Type.equalsIgnoreCase("WELLCore") && SelectResponseReview.equalsIgnoreCase("Pending")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdditionalCorePoint", 0);
		CommonMethod.ClickCheckbox("V2ProjectAdditionalCorePoint");
		CommonMethod.scrolldowntoElement("V2ProjectScorecardAdminReviewButton");
		testlog.info("And User select Comment Review");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardSubmitReview", 0);
		CommonMethod.JavascriptClickElement("V2ProjectScorecardSubmitReview");
		testlog.info("And User clicks on Submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardEditReview", 0);
		testlog.info("Then User will be redirected to Edit Review page");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectScorecardValidReviewPhase"),"Preliminary Precertification Review", "ReviewPhase Error Mismatch");
		testlog.info("And User verifies ReviewPhase");
		testlog.info("And User verifies ReviewCommnet");
		testlog.pass("**Created the Scorecard Review successfully**");
		}
		if (SelectResponseReview.equalsIgnoreCase("Pending")) {
			/* Edit Review* */
			CommonMethod.scrolldowntoElement("V2ProjectScorecardAdminReviewButton");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardEditReview", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectScorecardEditReview",
					"V2ProjectScorecardUpdateButtonReview");
			testlog.info("And User verifies Edit icon");
			CommonMethod.selectdropdownValue("V2ProjectScorecardSelectResponseReview", "Achieved");
			testlog.info("And User select Response Review");
			CommonMethod.selectdropdownValue("V2ProjectScorecardSelectcommentReview", SelectcommentReview);
			testlog.info("And User select Comment Review");
			CommonMethod.clearAndSendKey("V2ProjectScorecardUpdateCommentReview", "Achieved Comment");
			testlog.info("And User enter data CommentReview field");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardUpdateButtonReview", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectScorecardUpdateButtonReview",
					"V2ProjectScorecardAdminReviewButton");
			testlog.info("And User clicks on Update button");
			testlog.pass("**Updated the Scorecard Review successfully**");
			/* Delete Review* */
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardInstallDeleteReview", 0);
			testlog.info("And User verifies Delete icon");
			CommonMethod.RobustclickElementVisible("V2ProjectScorecardInstallDeleteReview",
					"V2ProjectScorecardAdminReviewButton");
			testlog.info("And User clicks on Delete icon");
			testlog.pass("**Deleted the Scorecard Review successfully**");
			CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
			CommonMethod.JavascriptClickElement(ele);
			break;
		}
	}

}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void ScorecardfillWER(int YesEnd, int NoStart, int NoEnd, int DifferencePlusOne)
			throws IOException, InterruptedException {
		List<WebElement> YesButton;
		List<WebElement> NoButton;
		Boolean flag = false;
		rc.ScorecardLoading();
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
				CommonMethod.WaitUntilClickble("CommonScorecardPurseYes", 60);
				CommonMethod.JavascriptClickElement("CommonScorecardPurseYes");
				if (CommonMethod.isElementsExist("V2ProjectWEROptScorecardFeatureClosebtn", 5)) {
					if (CommonMethod.isElementsExist("V2ProjectScorecardWERToasterMessage", 10)) {
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardWERToasterMessage",
								0);
						CommonMethod.WaitUntilInVisibility("V2ProjectScorecardWERToasterMessage", 180);
					}
					CommonMethod.WaitUntilPresence("V2ProjectWEROptScorecardFeatureRadiobtn", 60);
					CommonMethod.ClickCheckbox("V2ProjectWEROptScorecardFeatureRadiobtn");
					CommonMethod.JavascriptClickElement("V2ProjectWEROptScorecardFeatureClosebtn");
				}
				testlog.info("And User select Yes Purse Status");
				Thread.sleep(1000);
				YesButton = CommonMethod.findElements("CommonScorecardPurseYes");
			} while ((YesButton.size() == RemainingYes));
			RemainingYes--;
			if (flag) {
				System.out.println("Build Exit");
				break;
			}
		}

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
				testlog.info("And User select No Purse Status");
				Thread.sleep(1000);
				NoButton = CommonMethod.findElements("CommonScorecardPurseNo");
			} while ((NoButton.size() == RemainingNo));
			RemainingNo--;
			j--;
			if (flag) {
				System.out.println("Build Exit");
				break;
			}
		}
	}

	public void validateAdminAlternativesAAP(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeMeansAndMethod", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAdminAlternativeMeansAndMethod"),
				data.getCellData(SheetName, "ReasonAlternativeMeansAndMethods", rowNum),
				"In Admin Reason Alternative Means And Methods doesn't match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAdminAlternativeMeansCompliance"),
				data.getCellData(SheetName, "ProposedAlternativeMeansCompliance", rowNum),
				"In Admin Project Name doesn't match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAdminAlternativeStatus"), "Pending",
				"In Admin Status doesn't match");
		validateAlternativeStatus(SheetName, rowNum, "Approved");
		validateAlternativeStatus(SheetName, rowNum, "Approved with Altered Language");
		validateAlternativeStatus(SheetName, rowNum, "Not Approved");
		validateAlternativeStatus(SheetName, rowNum, "Additional Information Requested");
		validateAlternativeStatus(SheetName, rowNum, "Not Applicable");
		testlog.pass("**Validate alternative status changed succesfully**");
	}

	public void validateAlternativeStatus(String SheetName, int rowNum, String status)
			throws IOException, InterruptedException {
		CommonMethod.hideElement("NavBarHide");
		CommonMethod.scrolldowntoElement("V2ProjectAdminAlternativeEditButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeEditButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAdminAlternativeEditButton",
				"V2ProjectAdminAlternativeSubmit");
		if (status.equalsIgnoreCase("Approved")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeApproved", 0);
			CommonMethod.ClickCheckbox("V2ProjectAdminAlternativeApproved");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidEditAlternative", 0);
			CommonMethod.scrolldowntoElement("ValidEditAlternative");
			CommonMethod.Robustclick("V2ProjectAdminAlternativeSubmit", "ValidEditAlternative");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ValidEditAlternative", 1);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeStatus", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAdminAlternativeStatus"), status,
					"In Admin " + status + " Status doesn't match");
		}
		if (status.equalsIgnoreCase("Approved with Altered Language")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeAltered", 0);
			CommonMethod.ClickCheckbox("V2ProjectAdminAlternativeAltered");
			
		}
		if (status.equalsIgnoreCase("Not Approved")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeNotApproved", 0);
			CommonMethod.ClickCheckbox("V2ProjectAdminAlternativeNotApproved");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidEditAlternative", 0);
			CommonMethod.scrolldowntoElement("ValidEditAlternative");
			CommonMethod.Robustclick("V2ProjectAdminAlternativeSubmit", "ValidEditAlternative");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ValidEditAlternative", 1);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeStatus", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAdminAlternativeStatus"), status,
					"In Admin " + status + " Status doesn't match");	
		}
		if (status.equalsIgnoreCase("Additional Information Requested")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeAdditionalInfo", 0);
			CommonMethod.ClickCheckbox("V2ProjectAdminAlternativeAdditionalInfo");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeMidReview", 0);
			CommonMethod.sendKeys("V2ProjectAdminAlternativeMidReview", "Test");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidEditAlternative", 0);
			CommonMethod.scrolldowntoElement("ValidEditAlternative");
			CommonMethod.Robustclick("V2ProjectAdminAlternativeSubmit", "ValidEditAlternative");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ValidEditAlternative", 1);
			CommonMethod.WaitUntilVisibility("V2ProjectAdminAlternativeStatus", 60);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAdminAlternativeStatus"), status,
					"In Admin " + status + " Status doesn't match");
		}
		if (status.equalsIgnoreCase("Not Applicable")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeNotApplicable", 0);
			CommonMethod.ClickCheckbox("V2ProjectAdminAlternativeNotApplicable");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidEditAlternative", 0);
			CommonMethod.scrolldowntoElement("ValidEditAlternative");
			CommonMethod.Robustclick("V2ProjectAdminAlternativeSubmit", "ValidEditAlternative");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ValidEditAlternative", 1);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeStatus", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAdminAlternativeStatus"), status,
					"In Admin " + status + " Status doesn't match");
		}
	}

	public void uploadEquityDocV2Project(String SheetName) throws IOException, InterruptedException {
		uploadDocumentInFeature(SheetName, 32);
		testlog.pass("**Upload 32 Scorecard Documents successfully**");
	}

	public void ScorecardCertificationValidationV2Project(String SheetName, int rowNum, String Type)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "V2ProjectStartBuilding");
		BuildScorecardV2ProjectById(SheetName, rowNum);
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "V2ProjectScorecardApplybutton");
		testlog.info("And User clicks on Filter button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartTypeFilter", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardPartTypeFilter",
				"V2ProjectScorecardPreconditionsFilter");
		testlog.info("And User select Part type Filter option");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPreconditionsFilter", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardPreconditionsFilter",
				"V2ProjectScorecardApplybutton");
		testlog.info("And User check on Preconditions Filter checkbox");
		CommonMethod.WaitUntilPresence("V2ProjectScorecardApplybutton", 120);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardApplybutton", "ScorecardFeatureLoading");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardPartCount",
				V2PreconditionFeature);
		List<WebElement> Precondtions = CommonMethod.findElements("V2ProjectScorecardPartCount");
		int Precondtionsize = Precondtions.size();
		rc.validateFeatureCount(V2PreconditionFeature,Precondtionsize,"preconditions as yes");
		CommonMethod.WaitUntilClickble("V2ProjectScorecardFilterClosePanel", 60);
		CommonMethod.click("V2ProjectScorecardFilterClosePanel");
		/*
		 * Selecting all the preconditions as yes
		 */
		ScorecardfillPreconditions();
		testlog.info("And User selected all the preconditons as yes!!");
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "V2ProjectScorecardApplybutton");
		testlog.info("And User clicks on Filter button");
		CommonMethod.WaitUntilPresence("V2ProjectScorecardPartTypeFilter", 120);
		CommonMethod.WaitUntilPresence("V2ProjectScorecardPreconditionsFilter", 120);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardPreconditionsFilter",
				"V2ProjectScorecardApplybutton");
		testlog.info("And User uncheck Preconditions Filter checkbox");
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardOptimizationFilter", "V2ProjectScorecardApplybutton");
		testlog.info("And User check optimization Filter checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardApplybutton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardApplybutton", "ScorecardFeatureLoading");
		testlog.info("And User clicks on Apply button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardPartCount",
				V2OptimizationFeature);
		List<WebElement> features = CommonMethod.findElements("V2ProjectScorecardPartCount");
		int featuresize = features.size();
		rc.validateFeatureCount(V2OptimizationFeature,featuresize,"V2OptimizationFeature");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardFilterClosePanel", 0);
		CommonMethod.click("V2ProjectScorecardFilterClosePanel");
		testlog.info("And User clicks on Close panel button");
		Thread.sleep(2000);
		/*
		 * Selecting Optimiztion points
		 */
		testlog.info("Then User clicks on ScoreCardPageLand");
		ScorecardfillPreAndOptimizationPoints(30);
		testlog.info("And User selects optimization points");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetedPoints", 0);	
		String actualTargetedPointsScore = CommonMethod.getattributeValueByTextContent("TargetedPoints").trim();
		System.out.println(actualTargetedPointsScore);
		actualTargetedPointsScore = actualTargetedPointsScore.replaceAll("\\s+", " ").trim();
		CommonMethod.negativesoftassertFieldValid(actualTargetedPointsScore, "47 Points", "Targeted Points Score data does not matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardTargetpoint", 0);
		CommonMethod.JavascriptClickElement("V2ProjectScorecardTargetpoint");
		testlog.info("And User Clicks on target point slider");
		if (Type.equalsIgnoreCase("WELLCore")) {
			CommonMethod.WaitUntilPresence("V2ProjectScorecardSilverLevel", 60);
			String actualstring1 = CommonMethod.getText("V2ProjectScorecardVerifyActiveMilestone");
			CommonMethod.negativesoftassertFieldValidEquals(actualstring1, "SILVER", "Certifiction status doesn't match!!");
			testlog.info("And verifies certification status" + actualstring1);
		} else {
			CommonMethod.WaitUntilPresence("V2ProjectScorecardBronzeLevel", 60);
			String actualstring = CommonMethod.getText("V2ProjectScorecardVerifyActiveMilestone");
			CommonMethod.negativesoftassertFieldValidEquals(actualstring, "BRONZE", "Certifiction status doesn't match!!");
			testlog.info("And verifies certification status" + actualstring);
		}
		if (Type.equalsIgnoreCase("WELLCore")) {
			CommonMethod.click("V2ProjectScorecardSummaryClosePanel");
			CommonMethod.RobustclickElementVisible("V2ProjectLightconcept", "ApplicableVersion");
			testlog.info("And User clicks on Light concept tab");
			CommonMethod.ScrollUpToElement("V2ProjectScorecardTargetpoint");
			CommonMethod.RobustclickElementVisible("V2ProjectScorecardTargetpoint", "ApplicableVersion");
			ScorecardfillPreAndOptimizationPoints(5);
			testlog.info("And User selects optimization point from Light concept");
			CommonMethod.WaitUntilClickble("V2ProjectScorecardTargetpoint", 120);
			CommonMethod.JavascriptClickElement("V2ProjectScorecardTargetpoint");
			CommonMethod.WaitUntilPresence("V2ProjectScorecardGoldLevel", 60);
			String actualstring2 = CommonMethod.getText("V2ProjectScorecardVerifyActiveMilestone");
			CommonMethod.negativesoftassertFieldValidEquals(actualstring2, "GOLD", "Certifiction status doesn't match!!");
			testlog.info("And verifies certification status" + actualstring2);
		}
		testlog.pass("**User verified different certification status successfully**");

	}

	public void ScorecardfillPreAndOptimizationPoints(int YesEnd) throws IOException, InterruptedException {
		List<WebElement> YesButton;
		YesButton = CommonMethod.findElements("CommonScorecardPurseYes");
		for (int i = 1; i <= YesEnd; i++) {
			int RemainingYes = YesButton.size();
			System.out.println(RemainingYes);
			do {
				CommonMethod.WaitUntilClickble("CommonScorecardPurseYes", 60);
				CommonMethod.JavascriptClickElement("CommonScorecardPurseYes");
				testlog.info("And User select PurseYes Status");
				Thread.sleep(2000);
				YesButton = CommonMethod.findElements("CommonScorecardPurseYes");
				System.out.println(YesButton.size());
			} while ((YesButton.size() == RemainingYes));
			RemainingYes--;
		}

	}

	public void ScorecardfillPreconditions() throws IOException, InterruptedException {
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresent("CommonScorecardPurseYes", V2PreconditionFeature);
		List<WebElement> YesButton;
		YesButton = CommonMethod.findElements("CommonScorecardPurseYes");
		System.out.println(YesButton.size());
		for (int i = 1; i <= YesButton.size(); i++) {
			do {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseYes", 0);
				CommonMethod.JavascriptClickElement("CommonScorecardPurseYes");
				testlog.info("And User select PurseYes Status");
				Thread.sleep(2000);
				YesButton = CommonMethod.findElements("CommonScorecardPurseYes");
				System.out.println(YesButton.size());
			} while (CommonMethod.isElementsExist("CommonScorecardPurseYes", 10));
		}
	}

	public void EnrolledDashboardV2Project(String SheetName, int rowNum, boolean is_Leed)
			throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.JavascriptClickElement("WellV2DashboardTab");
		CommonMethod.WaitUntilPresence("V2ProjectStartBuilding", 30);
		CommonMethod.WaitUntilPresence("ReviewTab", 30);
		CommonMethod.WaitUntilPresence("HealthSafetyTab", 30);
		CommonMethod.WaitUntilPresence("PerformanceTab", 30);
		CommonMethod.WaitUntilPresence("EquityTab", 30);
		CommonMethod.WaitUntilPresence("WellV2ProjectDocumentTab", 30);
		CommonMethod.WaitUntilPresence("TeamTab", 30);
		CommonMethod.WaitUntilPresence("ProfileTab", 30);
		CommonMethod.WaitUntilPresence("PromotionTab", 30);
		CommonMethod.WaitUntilPresence("AlternativesTab", 30);
		CommonMethod.WaitUntilPresence("OverviewTab", 30);
		CommonMethod.WaitUntilPresence("PricingTab", 30);
		CommonMethod.WaitUntilPresence("BiilingTab", 30);
		CommonMethod.WaitUntilPresence("V2ProjectDashboardLearnMoreButton1", 30);
		if (is_Leed == true) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectLeedDashboardCard", 0);
			CommonMethod.WaitUntilClickble("V2ProjectLeedOptinButton", 60);
			CommonMethod.JavascriptClickElement("V2ProjectLeedOptinButton");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("V2ProjectLeedpopupId"),
					data.getCellData(SheetName, "LeedCertificationID", rowNum), "Leed Certification ID Error Mismatch");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("V2ProjectLeedpopupname"),
					data.getCellData(SheetName, "LeedCertificationName", rowNum),
					"Leed Certification name Error Mismatch");
			CommonMethod.WaitUntilPresence("V2ProjectLeedPlusWELLPopupchk", 30);
			CommonMethod.RobustclickElementVisible("V2ProjectLeedPlusWELLPopupchk", "V2ProjectLeedLearnMore");
			CommonMethod.RobustclickElementVisible("V2ProjectLeedSubmitBtn", "V2ProjectLeedToasterMessage");
			CommonMethod.WaitUntilPresence("V2ProjectLeedOptedButton", 30);
		}
		testlog.pass("**Verifies Dashboard fields and opted for Leed successfully **");
	}

	public void ValidateLeedProjectInformationV2Project(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilVisibility("EditTab", 180);
		CommonMethod.RobustclickElementVisible("EditTab", "ProjectInformationButton");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilVisibility("ProjectInformationButton", 60);
		CommonMethod.RobustclickElementVisible("ProjectInformationButton", "V2ProjectProjectScope");
		testlog.info("And User clicks on Project Information Button");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdateProjectsName"),
				data.getCellData(SheetName, "ProjectName", rowNum), "Update ProjectName Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectUpdateProjectsarea"),
				data.getCellData(SheetName, "Area", rowNum), "Update Area Error Mismatch");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectUpdateCics");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectUpdateSpacetypes");		
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectLeed");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectLeedId"),
				data.getCellData(SheetName, "LeedCertificationID", rowNum), "Leed Certification ID Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectLeedName"),
				data.getCellData(SheetName, "LeedCertificationName", rowNum), "Leed Certification name Error Mismatch");
		CommonMethod.WaitUntilPresence("V2ProjectLeedAndWELLCheckbox", 30);
		testlog.info("And User verifies the LEED Project added details");
		testlog.info("And User verifies the Project added details");
	}

	public void SubmitLeedReviewV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("When User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSubmitPhaseReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectSubmitPhaseReview", "V2ProjectcommentReview");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Doc-submit-phase is required.", "Phase Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource(
				"Please provide your comments below to notify the IWBI team is required.",
				"Comment Name Error Mismatch");
		testlog.info(
				"And User clicks on submit button without entering the mandatory fields and verifies error meassage");
		String reviewPhase = "Preliminary Documentation Review";
		data.setCellData(SheetName, "ReviewPhase", rowNum, reviewPhase);
		CommonMethod.WaitUntilClickble("V2ProjectcommentReview", 60).sendKeys(reviewPhase);
		testlog.info("And User enter on Comment field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSelectPhase", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectSelectPhase", "Preliminary Documentation Review");
		testlog.info("And User select the Phase");
		CommonMethod.ClickCheckbox("DocReviewCheckbox");
		testlog.info("And User select document checkbox");
		CommonMethod.ClickCheckbox("LeedReviewCheckbox");
		testlog.info("And User select LEED + WELL Crosswalk checkbox");
		CommonMethod.RobustclickElementVisible("V2ProjectSubmitPhaseReview", "LeedTag");
		testlog.info("And User clicks on submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("LeedTag", 1);
		CommonMethod.assertisElementPresentTrue("LeedTag","LeedTag button is not visible");
		testlog.info("Then User will be redirected to Review List page");
		testlog.pass("And User verified submit review with LEED successfully!!");
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	public void SignAgreementV2project(String SheetName, int rowNum) throws IOException, InterruptedException {
		String projectId = data.getCellData(SheetName, "ProjectID", rowNum);
		String[] Id = projectId.split("22024");
		System.out.println("Id: " + Id[1]);
		String header = portfolio.PostRequestAuthenticate("UserName", 6);
		testlog.info("Given User set POST service api endpoint");
		testlog.info("When User set Request header and Payload");
		testlog.info("And User Send a POST HTTP request");
		testlog.info("Header Token: " + header);
		@SuppressWarnings("rawtypes")
		HashMap pathprms = new HashMap();
		pathprms.put("project_id", Id[1]);
		Response res = given().log().all().accept("application/json").contentType("application/json")
				.header("Authorization", header).pathParams(pathprms).when().post("project/{project_id}/aggrementSign");
		int StatusCode = res.getStatusCode();
		Assert.assertEquals(StatusCode, 200, "Verifying status code");
		testlog.info("Then User verifies response body");
		testlog.info("And User verifies Status Code");
		testlog.info("And User Complete Sign project agreement");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.pass("**Verifies project agreement sign successfully**");
		
	}

	public void ValidateDocumentInDocumentLibrary(String SheetName, int rowNum, String Access)
			throws IOException, InterruptedException {
		/*
		 * For General Document
		 */
		if (Access.equalsIgnoreCase("true")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectGeneralDoc", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectGeneralDoc", "V2ProjectGeneralDocDeleteBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectGeneralDocDeleteBtn", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectGeneralDocDeleteBtn", "V2ProjectDeleteRestrictionPopu");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDeleteRestrictionPopu", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectDeleteRestrictionPopu"),
					"You do not have permission to delete this document.", "Delete Restriction Error Mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PopupCancel", 0);
			CommonMethod.RobustclickElementVisible("PopupCancel", "V2ProjectGeneralDocDeleteBtn");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectGeneralDoc", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectGeneralDoc", "V2ProjectGeneralDocDeleteBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectGeneralDocDeleteBtn", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectGeneralDocDeleteBtn", "V2ProjectPopUpDeleteBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPopUpDeleteBtn", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectPopUpDeleteBtn", "TableTrSize");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 0);
		}
	}

	public void purseYesValidFromScorecard() throws IOException, InterruptedException {
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardPurseYesSelectParticulate", 0);
		CommonMethod.Robustclick("ScorecardPurseYesSelectParticulate");
		testlog.info("And User click on Purse Yes");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ScorecardPurseYesSelectedParticulate", 1);
		CommonMethod.assertisElementPresentTrue("ScorecardPurseYesSelectedParticulate", "PurseYes is not selected");
		testlog.info("And User verifies the Selected Purse Yes");
		testlog.info("Then User verifies the Pursue status message");
		testlog.pass("**Verifies the Pursue Yes status successful**");
	}
	
	public void purseNoValidFromScorecard() throws IOException, InterruptedException {
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardSelectPurseNoInorganic", 0);
		CommonMethod.Robustclick("ScorecardSelectPurseNoInorganic");
		testlog.info("And User click on Purse No");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardSelectedPurseNo", 1);
		CommonMethod.assertisElementPresentTrue("V2ProjectScorecardSelectedPurseNo", "PurseNo is not selected");
		testlog.info("And User verifies the Selected Purse No");
		testlog.pass("**Verifies the Pursue No status successful**");

	}

	public void purseMaybeValidFromScorecard() throws IOException, InterruptedException {
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardSelectPurseMayBe", 0);
		CommonMethod.Robustclick("V2ProjectScorecardSelectPurseMayBe");
		testlog.info("And User click on Purse Maybe");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardSelectedPurseMayBe", 1);
		CommonMethod.assertisElementPresentTrue("V2ProjectScorecardSelectedPurseMayBe", "PurseMaybe is not selected");
		testlog.info("And User verifies the Selected Purse Maybe");
		testlog.info("Then User verifies the Pursue status message");
		testlog.pass("**Verifies the Pursue Maybe status successful**");

	}

	public void purseYesToNoValidFromScorecard() throws IOException, InterruptedException {
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardSelectPurseNoParticulate", 0);
		CommonMethod.Robustclick("ScorecardSelectPurseNoParticulate");
		testlog.info("And User click on Purse No");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("ScorecardSelectPurseNoParticulate");
		CommonMethod.assertisElementPresentFalse("ScorecardSelectPurseNoParticulate", "PurseYes is selected");
		testlog.info("And User verifies the Unseleceted Pursue Yes");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioScorecardPurseYesToNoSelected", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioScorecardPurseYesToNoSelected", "PurseNo is not selected");
		testlog.info("And User verifies the seleceted Pursue No");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardPurseYesSelectParticulate", 0);
		CommonMethod.Robustclick("ScorecardPurseYesSelectParticulate");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("ScorecardPurseYesSelectParticulate");
		testlog.pass("**Verifies the Converting Pursue Yes to No status successful**");
	}

	public void purseMaybeToNoValidFromScorecard() throws IOException, InterruptedException {
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardSelectPurseMayToNo", 0);
		CommonMethod.Robustclick("V2ProjectScorecardSelectPurseMayToNo");
		testlog.info("And User click on Purse No");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardSelectedPurseMayToNo", 1);
		CommonMethod.assertisElementPresentTrue("V2ProjectScorecardSelectedPurseMayToNo", "PurseYes is not selected");
		testlog.info("Then User verifies the Pursue yes button");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectScorecardSelectPurseMayToNo");
		CommonMethod.assertisElementPresentFalse("V2ProjectScorecardSelectPurseMayToNo", "Maybe is selected");
		testlog.info("Then User verifies the Unseleceted Pursue Maybe");
		testlog.pass("**Verifies the Converting Pursue Maybe to No status successful**");
	}
	
	public void ValidateDocumentInScorecardDocument(String SheetName, int rowNum, String Access)
			throws IOException, InterruptedException {
		/*
		 * For Scorecard Document
		 */
		if (Access.equalsIgnoreCase("true")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardDoc", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectScorecardDoc", "V2ProjectScorecardDocDeleteBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardDocDeleteBtn", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectScorecardDocDeleteBtn", "V2ProjectDeleteRestrictionPopu");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDeleteRestrictionPopu", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectDeleteRestrictionPopu"),
					"You do not have permission to delete this document.", "Delete Restriction Error Mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PopupCancel", 0);
			CommonMethod.RobustclickElementVisible("PopupCancel", "V2ProjectScorecardDocDeleteBtn");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardDoc", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectScorecardDoc", "V2ProjectScorecardDocDeleteBtn");
			if (SheetName.equalsIgnoreCase("V2Project")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentScorecardDeletePTIcon", 0);
				CommonMethod.RobustclickElementVisible("PortfolioDocumentScorecardDeletePTIcon",
						"V2ProjectPopUpDeleteBtn");
			} else {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardDocDeleteBtn", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectScorecardDocDeleteBtn", "V2ProjectPopUpDeleteBtn");
			}

			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPopUpDeleteBtn", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectPopUpDeleteBtn", "TableTrSize");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 0);
		}
	}

	public void ValidateDeleteDocumentForProjectAdminInScorecard(String SheetName, int rowNum, String Commodity)
			throws Exception {
		CommonMethod.WaitUntilPresence("WellV2Tab", 120);
		CommonMethod.RobustclickElementVisible("WellV2Tab", "ScorecardTab");
		testlog.info("When User clicks on WellV2Tab");
		CommonMethod.Robustclick("ScorecardTab");
		CommonMethod.WaitUntilVisibility("PortFolioScoreCardPageLand", Scorecardtimeout);
		CommonUploadScorecardDocument("Meet Thresholds for Particulate Matter", SheetName, rowNum, Commodity,FeaturefileUpload ,false,false,false,false);
		CommonMethod.WaitUntilPresence("V2ProjectScorecardDocDeleteIcon", 120);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardDocDeleteIcon", "V2ProjectScorecardDocDeleteYes");
		CommonMethod.WaitUntilPresence("V2ProjectScorecardDocDeleteYes", 120);
		CommonMethod.WaitUntilPresence("V2ProjectScorecardDocDeleteNo", 120);
	}

	public void GotoDocumentLibraryPage() throws IOException, InterruptedException {
		CommonMethod.WaitUntilPresence("WellV2ProjectDocumentTab", 60);
		CommonMethod.RobustclickElementVisible("WellV2ProjectDocumentTab", "V2ProjectDocUploadbtn");
		testlog.info("When User clicks on DocumentTab");
		CommonMethod.WaitUntilVisibility("V2ProjectDocUploadbtn", 60);
	}

	public void ValidateUploadDocV2ProjectInsideScorecard(String SheetName, int rowNum, String FeatureName,
			String VerficationName) throws IOException, InterruptedException {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		List<WebElement> Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
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
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardDocDeleteIcon", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectScorecardDocDeleteIcon",
						"V2ProjectDeleteRestrictionPopu");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDeleteRestrictionPopu", 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectDeleteRestrictionPopu"),
						"You do not have permission to delete this document.", "Delete Restriction Error Mismatch");
				CommonMethod.RobustclickElementVisible("V2ProjectRestrictPopupCrossIcon",
						"V2ProjectScorecardDocDeleteIcon");
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.refreshBrowser();
				CommonMethod.WaitUntilPresence("ApplicableVersion", Scorecardtimeout);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
	}

	public void ClickDashboardV2Project() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.JavascriptClickElement("WellV2DashboardTab");
		
	}

	public void UploadFeatureDocumentInDocumentV2Project(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Document page");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocUploadbtn", "V2ProjectPortfolioDocType");
		testlog.info("When User click on Upload button");
		Thread.sleep(2000);
		testlog.info("Document Type: " + "Feature");
		CommonMethod.selectdropdownVisibletext("V2ProjectPortfolioDocType", "Feature");
		testlog.info("And User select Document type");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectVerificationmethodDoc", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectVerificationmethodDoc", "V2ProjectVerificationmethodDocOs");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectVerificationmethodDocOs", 0);
		CommonMethod.click("V2ProjectVerificationmethodDocOs");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPart", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectPart", "PortfolioScorecardAssignfilterSpaceTypeSelectHighlighter");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPartDoc", 0);
		CommonMethod.click("V2ProjectPartDoc");
		Thread.sleep(1000);
		testlog.info("And User select the Part");
		CommonMethod.uploadFile("V2Projectscorecarddocupload", FeaturefileUpload, "UploadFileVerifyScorecard");
		testlog.info("And User upload Document");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UploadFileVerifyScorecard", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocSubmit", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectDocSubmit", "V2ProjectScorecardWaitUntilDocUploaded");
		testlog.info("And User click on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilInVisibility("V2ProjectScorecardWaitUntilDocUploaded", 120);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		testlog.info("Then User will be redirected to Document list");
		testlog.info("And User verifies uploaded document");
		testlog.pass("**Upload Document successfully**");
	}

	public void VerifyWeightIconCount(String SheetName, int rowNum, String expectedPaperIconCount)
			throws IOException, InterruptedException {
		/*
		 * WeightIconCount
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWeightPoint", 0);
		int WeightIconCount = CommonMethod.ElementSize("V2ProjectWeightPoint");
		String WeightIconActualCount = Integer.toString(WeightIconCount);
		testlog.info("WeightPointCount: " + WeightIconActualCount);
		CommonMethod.negativesoftassertFieldValid(WeightIconActualCount, expectedPaperIconCount,
				"WeightPointCount doesn't match");
		testlog.pass("**Verifies the WeightPoint Count successfully**");
	}

	public void NavigateScorecardFilter() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScoreCardFilterButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScoreCardFilterButton", "V2ProjectScorecardApplybutton");
	}

	public void DatePicker() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerButton", 0);
		CommonMethod.RobustclickElementVisible("DatePickerButton", "V2ProjectDatePopupWeekday");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectnextYearbtn", 0);
		CommonMethod.Robustclick("V2ProjectnextYearbtn", "V2ProjectDatePopupWeekday");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerNextYear", 0);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("DatePickerNextYear", "DatePickerOkButton");
		Thread.sleep(1000);
		CommonMethod.WaitUntilPresence("DatePickerOkButton", 120);
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "V2ProjectDatePopupWeekday");
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "DatePickerUpdateButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerUpdateButton", 0);
		CommonMethod.RobustclickElementVisible("DatePickerUpdateButton", "DatePickerContinueJourneyButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerContinueJourneyButton", 0);
		CommonMethod.JavascriptClickElement("DatePickerContinueJourneyButton");
		CommonMethod.refreshBrowser();
		CommonMethod.refreshBrowser();
		testlog.info("And User select the Date");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartBuilding", 0);
	}

	public void V2ProjectOpt(String SheetName, int rowNum, String TabNameLocator) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(TabNameLocator, 0);
		CommonMethod.JavascriptClickElement(TabNameLocator);
		CommonMethod.RobustclickElementVisible(TabNameLocator, "V2ProjectHsrContinuebtn");
		if (CommonMethod.isElementsExist("V2ProjectHsrContinuebtn", 10)) {
		testlog.info("When User clicks on Opt Tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrContinuebtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectHsrContinuebtn","Terms");
		testlog.info("And User clicks on Continue button");
		}
	}
	
	public void SeparateReviewCycleV2ProjectOpt(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptConfirmNoEnrollSelect", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectOptConfirmNoEnrollSelect",
				"No, we will initiate a separate review cycle.");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptConfirmNoEnrollSelect", 0);
		CommonMethod.ClickCheckbox("V2ProjectOptNoEnrollCheckbox");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectOptNoEnrollFeevalid"), "$3,000",
				"Complete PricingEnrollFee Optn data doesn't match");
		testlog.info("And User checks the terms and conditions checkbox");
		CommonMethod.RobustclickElementVisible("V2ProjectHsrTermsbtn", "Terms");
		testlog.info("And User checks the terms and conditions checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Terms", 0);
		CommonMethod.ClickCheckbox("Terms");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrProceedbtn", 0);
		CommonMethod.Robustclick("V2ProjectHsrProceedbtn");
		testlog.info("And User checks the Proceed checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptEnrollFeePagevalid", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptEdit", 0);
		CommonMethod.JavascriptClickElement("V2ProjectOptEdit");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CityName", 0);
		String City = USfaker.address().cityName();
		CommonMethod.clearAndSendKey("CityName", City);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptUpdateBtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectOptUpdateBtn", "CityName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptValid", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectOptValid"), City,
				"City Optn data doesn't match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectOptTotalEnrollFeePagevalid"), "$3,000",
				"Complete PricingEnrollFee Optn data doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptPayNowButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectOptPayNowButton", "BillingLanding");
		rc.Billing(SheetName, rowNum);
		if(TestCaseName.contains("EquitySeparateReviewCycleV2ProjectOpt")) {
		rc.ScorecardLoading();	
		} else {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("GoToAccount", 0);
		CommonMethod.JavascriptClickElement("GoToAccount");
		rc.ScorecardLoading();
		}
		testlog.info("And User clicks on Account button");
		testlog.info("Then User will be redirected to Scorecard page");
		testlog.pass("**Verifies the 15 Purse Yes Scorecard HealthSafety ByID successfully**");
	}

	public void hsrOptSubmitReviewV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("When User clicks on Review tab");
		CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "V2ProjectcommentReview");
		testlog.info("And User clicks on Submit DocReview button");
		CommonMethod.WaitUntilClickble("V2ProjectcommentReview", 60).sendKeys("Preliminary Health-Safety Review");
		testlog.info("And User enter data to commentReview");
		CommonMethod.selectdropdownVisibletext("V2ProjectSelectPhase", "Preliminary Health-Safety Review");
		testlog.info("And User select the phase");
		CommonMethod.RobustclickElementVisible("V2ProjectSubmitPhaseReview", "ReviewViewButton");
		testlog.info("And User clicks on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
		testlog.info("Then User will be redirected to Review list page");
		testlog.pass("**Reviewed Final Precertification Review successfully**");

	}

	public void hsrOptCompleteReviewV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		/*
		 * Admin Review
		 */
		AdminSearchById(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "ReviewViewButton");
		testlog.info("And User clicks on ReviewTab");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
		CommonMethod.RobustclickElementVisible("ReviewViewButton", "ReviewAdminActionsButton");
		testlog.info("And User clicks on view button");
		ReviewAdminActionsButton();
		Thread.sleep(2000);
		CommonMethod.RobustclickElementVisible("ReviewReturnButton", "ReturnComment");
		testlog.info("And User clicks on Return button");
		CommonMethod.WaitUntilClickble("ReturnComment", 60).sendKeys("Final Review");
		testlog.info("And User enter on CommentNarrative field");
		CommonMethod.WaitUntilClickble("CommentNarrative", 60)
				.sendKeys("Preliminary Health-Safety Review");
		CommonMethod.WaitUntilClickble("DatePickerButton", 60).click();
		CommonMethod.WaitUntilClickble("DatePickerOkButton", 60).click();
		testlog.info("And User select Date");
		CommonMethod.scrollDown();
		Thread.sleep(1000);
		CommonMethod.ClickCheckbox("ReviewPaymentstatusRadio");
		testlog.info("And User checks the Paymentstatus checkbox");
		CommonMethod.RobustclickElementVisible("ReviewReturnSubmit", "ReviewedStatus");
		testlog.info("And User clicks on Submit button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewedStatus", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ReviewedStatus"), "REVIEWED",
				"Verified Review status");
		testlog.info("And User verifies the Reviewed Status");
		testlog.pass("**Completed Final Precertification Review successfully**");
	}

	public void ScorecardfillPreconditionsUnselected() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrScorecardResponseFilter", 0);
		CommonMethod.RobustclickElementVisible("HsrScorecardResponseFilter", "V2PilotYesCheckboxFilter");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotYesCheckboxFilter", 0);
		CommonMethod.ClickCheckbox("V2PilotYesCheckboxFilter");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("CommonScorecardPurseMaybe1", 59);
		int YesButton;
		do {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommonScorecardPurseMaybe1", 0);
			CommonMethod.JavascriptClickElement("CommonScorecardPurseMaybe1");
			testlog.info("And User select PurseYes Status");
			Thread.sleep(2000);
			YesButton = CommonMethod.ElementSize("CommonSelectedPurseYes");
			System.out.println("YesButton: " + YesButton);
		} while (!(YesButton < 1));
		RefreshScorecard();
	}

	public void DeleteDocV2ProjectInsideScorecard() throws IOException, InterruptedException {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectUploadDocDeleteIcon", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectUploadDocDeleteIcon", "DeleteButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CancelButton", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DeleteButton", 0);
		CommonMethod.Robustclick("DeleteButton");
		if (CommonMethod.isElementsExist("V2ProjectScorecardUploadDeleteToastermessage", 5)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectScorecardUploadDeleteToastermessage", 1);
		}
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectUploadDocDeleteIcon");
		CommonMethod.assertisElementPresentFalse("V2ProjectUploadDocDeleteIcon", "Deleted Upload Document is visible");
	}

	public void DeleteUploadDocumentInScorecardFeature(String FeatureName) throws IOException, InterruptedException {
		List<WebElement> Feature;
		v2project.RefreshScorecard();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 0);
		Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				DeleteDocV2ProjectInsideScorecard();
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void specialCharUploadFeatureDocV2ProjectInsideScorecard(String SheetName, int rowNum, String FeatureName)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 10);
		List<WebElement> Feature;
		boolean flag = false;
		Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				System.out.println("FeatureName: " + FeatureName);
				CommonMethod.WaitUntilClickble(ele, 120);
				CommonMethod.JavascriptClickElement(ele);
				flag = true;
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectscorecardDocbtn", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectscorecardDocbtn",
						"V2Projectscorecardverificationdropdown");
				testlog.info("And User clicks on Document Plus icon");
				testlog.info("TaskName : Performance Test OR Sensor Data");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectscorecardverificationdropdown", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectscorecarddocupload", 0);
				CommonMethod.uploadFile("V2Projectscorecarddocupload", SpecialCharFeaturefileName,
						"UploadFileVerifyScorecard");
				testlog.info("And User upload scorecard Document");
				CommonMethod.selectdropdownrandom("V2Projectscorecardverificationdropdown");
				testlog.info("And User select the Verification");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectscorecarddocuploadsubmit", 0);
				CommonMethod.RobustclickElementVisible("V2Projectscorecarddocuploadsubmit",
						"V2ProjectscorecardVerifyUploadDoc");
				testlog.info("And User clicks on Submit button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectscorecardVerifyUploadDoc", 0);
				testlog.info("Then User verifies upload scorecard document");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectscorecardVerifyUploadDoc", 0);
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getattributeValueByTextContent("V2ProjectScorecardUploadSpecialCharFileName").trim(),
						"FeatureFile_Update", "Special character FileName doesn't match");
			}
			CommonMethod.JavascriptClickElement(ele);
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectscorecardVerifyUploadDoc", 1);
			break;
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void ValidatingPaginitionUploadDocument() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2ProjectDocumentTab", 0);
		CommonMethod.RobustclickElementVisible("WellV2ProjectDocumentTab", "V2ProjectDocUploadbtn");
		testlog.info("When User clicks on DocumentTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUploadbtn", 0);
		testlog.info("Given User is on Document page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRTabDocumentList", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectWPRTabDocumentList", "V2ProjectDocumentValid");
		CommonMethod.scrolldowntoElement("V2ProjectWPRTabDocumentList");
		testlog.info("And User click on General Document list");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("TableTrSize", 10);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocumentPaginationsecond", 0);
		CommonMethod.RobustclickElementVisible("WPRDocumentPaginationsecond", "WPRPaginationNumberText");
		testlog.info("And User clicks on pagination number");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRPaginationNumberText", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("TableTrSize", 10);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRPaginationNumberText"), "11",
					"Document Library Pagition count Doesn't match");
		testlog.info("Then User verifies the pagition Count");
		CommonMethod.refreshBrowser();
	}

	public void HealthSafetyV2ProjectOptn() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
		CommonMethod.RobustclickElementVisible("HealthSafetyTab", "V2ProjectHsrContinuebtn");
		testlog.info("When User clicks on HealthSafetyTab");
		if (CommonMethod.isElementsExist("V2ProjectHsrContinuebtn", 30)) {
		CommonMethod.WaitUntilVisibility("V2ProjectHsrContinuebtn", 60);
		CommonMethod.Robustclick("V2ProjectHsrContinuebtn");
		testlog.info("And User clicks on Continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Terms", 0);
		CommonMethod.ClickCheckbox("Terms");
		testlog.info("And User checks the terms and conditions checkbox");
		CommonMethod.RobustclickElementVisible("V2ProjectHsrTermsbtn", "V2ProjectHsrProceedbtn");
		testlog.info("And User checks the terms and conditions checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Terms", 0);
		CommonMethod.ClickCheckbox("Terms");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrProceedbtn", 0);
		CommonMethod.Robustclick("V2ProjectHsrProceedbtn");
		testlog.info("And User checks the Proceed checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrAccountbtn", 0);
		CommonMethod.Robustclick("V2ProjectHsrAccountbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectHsrAccountbtn", 1);
		testlog.info("And User clicks on Account button");
		}
		rc.ScorecardLoading();
		testlog.info("Then User will be redirected to Scorecard page");
		}

	public void HealthSafetyV2ProjectAfterOptn() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
		CommonMethod.RobustclickElementVisible("HealthSafetyTab", "V2ProjectHsrContinuebtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrContinuebtn", 0);
		CommonMethod.Robustclick("V2ProjectHsrContinuebtn");
		rc.ScorecardLoading();
		testlog.info("Then User will be redirected to Scorecard page");
	}

	public void uploadDocumentInOptnFeature(String featureName, String SheetName, int rowNum, String Commodity, String FileName, String PartId) throws Exception {
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("NavBarHide", 0);
		CommonMethod.hideElement("NavBarHide");
		CommonMethod.hideElement("NavBarDownHide");
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
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPDocIcon", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectWPRPDocIcon", "V2ProjectDocUpload");
				CommonMethod.uploadFile("V2ProjectDocUpload", FileName, "UploadFileVerifyScorecard");
				if (!CommonMethod.isElementsExist("V2ProjectScorecardSelectedDocTypeValid", 5)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect1", 0);
					CommonMethod.click("Multiselect1");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectOwnerOrgDyn", 0);
					List<WebElement> ele = CommonMethod.findElements("SelectOwnerOrgDyn");
					ele.get(0).click();
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature", 0);
				CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature", PartId);
				testlog.info("And User select Partname");
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
							"PortfolioScoreCardVerificationSelectSpaceType", 0);
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
					testlog.info("And User Select SpaceType");
				}
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
							"PortfolioScoreCardVerificationSelectOption", 0);
					CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
					testlog.info("And User Select Option");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPartbtn", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPartbtn",
						"UploadFileVerifyScorecard");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UploadFileVerifyScorecard", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddNote", 0);
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationAddNote");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EnabledUploadbtn", 0);
				CommonMethod.Robustclick("EnabledUploadbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("EnabledUploadbtn", 1);
				rc.uploadDocumentToastMessage();
				testlog.info("And User will be redirected to Document Upload Table page");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage", 0);
				CommonMethod.JavascriptClickElement(f);
				break;
			}
		}
			CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
			testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void ValiateScorecardUploadDocInHSROptnLocationAccount(String featureName, String SheetName, int rowNum, String Commodity)
			throws Exception {
		
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
		List<WebElement> Feature = CommonMethod.findElements("V2projectRatingFeatureName");
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
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FeatureNextStep", 0);
				CommonMethod.scrolldowntoElement("FeatureNextStep");
				generic.validateUploadedDocumentTableOptnGeneric(SheetName, rowNum, Commodity,false, false, false, false, false, true);
				CommonMethod.JavascriptClickElement(f);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void UploadAuditDocLocationAccOptnInsideScorecard(String FeatureName, String ProjectType) throws Exception {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
		List<WebElement> Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		testlog.info("Fetching total no. of credits on page");
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
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPDocIcon", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectWPRPDocIcon", "V2ProjectDocUpload");
				testlog.info("And User clicks on Document Plus icon");
				testlog.info("TaskName : Technical Document (Audited)");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUpload", 0);
				CommonMethod.uploadFile("V2ProjectDocUpload", AuditfileUpload, "UploadFileVerifyScorecard");
				testlog.info("And User upload scorecard Document");
				if (ProjectType.contains("pilot")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
					CommonMethod.RobustclickElementVisible("OwnerOrgClick",
							"PortfolioDocumentAuditVerficationMethod");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
							"PortfolioDocumentAuditVerficationMethod", 0);
					CommonMethod.click("PortfolioDocumentAuditVerficationMethod");
				} else {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
					CommonMethod.RobustclickElementVisible("OwnerOrgClick",
							"PortfolioDocumentAuditVerficationMethod");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
							"PortfolioDocumentAuditVerficationMethod", 0);
					CommonMethod.click("PortfolioDocumentAuditVerficationMethod");
				}
				testlog.info("And User select the Verification");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature",
						0);
				CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature", "SC5");
				testlog.info("And User select SC5 Partname");
				CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
				testlog.info("And User Select SpaceType");
				CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
				testlog.info("And User Select Option");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPartbtn", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPartbtn",
						"PortfolioLocationScorecardUploadbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadRemovelink", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddNote", 0);
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationAddNote");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EnabledUploadbtn", 0);
				CommonMethod.Robustclick("EnabledUploadbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("EnabledUploadbtn", 1);
				rc.uploadDocumentToastMessage();
				testlog.info("And User clicks on Submit button");
				testlog.info("Then User verifies upload scorecard document");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage");
				rc.documentTableDeleteButton();
				CommonMethod.JavascriptClickElement(ele);
				testlog.pass("**Verifies the Upload Document in Scorecard Feature successfully**");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
	}

	public void UploadAlternativeDocLocationAccOptnInsideScorecard(String FeatureName) throws Exception {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
		List<WebElement> Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		testlog.info("Fetching total no. of credits on page");
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
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPDocIcon", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectWPRPDocIcon", "V2ProjectDocUpload");
				testlog.info("And User clicks on Document Plus icon");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocUpload", 0);
				CommonMethod.uploadFile("V2ProjectDocUpload", AlternativeFileUpload, "UploadFileVerifyScorecard");
				testlog.info("And User upload scorecard Document");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
				CommonMethod.RobustclickElementVisible("OwnerOrgClick",
						"PortfolioScorecardAlternativeVerficationMethod");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScorecardAlternativeVerficationMethod", 0);
				CommonMethod.click("PortfolioScorecardAlternativeVerficationMethod");
				testlog.info("And User select the Verification");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature",
						0);
				testlog.info("Then User verifies option to add multiple feature parts");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationUploadAddfeature", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationSelectFeature");
				CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature", "SE1");
				testlog.info("And User select SE1 Partname");
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
					CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectOption",
							"Alternative Adherence Path (AAP)");
				}
				testlog.info("And User select Alternative Adherence Path (AAP) Options");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPartbtn", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPartbtn",
						"PortfolioLocationScorecardUploadbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadRemovelink", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationUploadAddfeature", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationSelectFeature");
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadAddfeature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature",
						0);
				testlog.info("Then User verifies option to add multiple feature parts");
				CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature", "SA1");
				testlog.info("And User select SE1 Partname");
				if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
					CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectOption",
							"Alternative Adherence Path (AAP)");
				}
				testlog.info("And User select Alternative Adherence Path (AAP) Options");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPartbtn", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPartbtn",
						"PortfolioLocationScorecardUploadbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"PortfolioScoreCardVerificationUploadAddfeature", 0);
				CommonMethod.Robustclick("PortfolioScoreCardVerificationUploadAddfeature",
						"PortfolioScoreCardVerificationSelectFeature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddNote", 0);
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationAddNote");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRUploadbtn1", 0);
				CommonMethod.Robustclick("V2ProjectWPRUploadbtn1");
				rc.uploadDocumentToastMessage();
				testlog.info("And User clicks on Submit button");
				testlog.info("Then User verifies upload scorecard document");
				CommonMethod.JavascriptClickElement(ele);
				testlog.pass("**Verifies the Upload Document in Scorecard Feature successfully**");
				break;
			}

		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
	}

	public void DeleteUploadDocumentOptnInScorecardFeature(String FeatureName)
			throws Exception {
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
		Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage");
					rc.documentTableDeleteButton();
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void validDeletedUploadDocumentOptnInScorecardFeature(String FeatureName, String SheetName, int rowNum, String Commodity)
			throws Exception {
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
		Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException(PortfolioAndRatingLocAccDocumentTableTr);                                                                                                                                                                                                                                                                                                                                                                                                                      
				CommonMethod.assertisElementPresentFalse("PortfolioAndRatingLocAccDocumentTableTr", "Delete Document table list visible");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void refreshScorecardOptn() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
	}
	
	public void ValidateDashboardAndNavigateToScorecard(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 60)) {
        	CommonMethod.Robustclick("ScorecardBannerClose");
        }
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2RenewalButton", 0);
		CommonMethod.RobustclickElementVisible("V2RenewalButton", "V2ProjectHsrOptnAcknowlede");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnAcknowlede", 0);
		CommonMethod.ClickCheckbox("V2ProjectHsrOptnAcknowlede");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrProceedbtn", 0);
		CommonMethod.Robustclick("V2ProjectHsrProceedbtn","V2ProjectHsrOptnAcknowlede");
		testlog.info("And User checks the Proceed checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptConfirmNoEnrollSelect", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectOptConfirmNoEnrollSelect",
				"No, we will initiate a separate review cycle.");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptConfirmNoEnrollSelect", 0);
		CommonMethod.ClickCheckbox("V2ProjectOptNoEnrollCheckbox");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectOptNoEnrollFeevalid"), "$3,000",
				"Complete PricingEnrollFee Optn data doesn't match");
		testlog.info("And User checks the terms and conditions checkbox");
		CommonMethod.RobustclickElementVisible("V2ProjectHsrTermsbtn", "Terms");
		testlog.info("And User checks the terms and conditions checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Terms", 0);
		CommonMethod.ClickCheckbox("Terms");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrProceedbtn", 0);
		CommonMethod.Robustclick("V2ProjectHsrProceedbtn");
		testlog.info("And User checks the Proceed checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptRenewalPayNowButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectOptRenewalPayNowButton", "BillingLanding");
		rc.Billing(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "HealthSafetyTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
		CommonMethod.RobustclickElementVisible("HealthSafetyTab", "V2ProjectHsrOptnRenewalScorecardButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalScorecardButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectHsrOptnRenewalScorecardButton", "V2ProjectHsrOptnRenewalScorecardPopUpValidMsg");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalScorecardPopUpValidMsg", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("V2ProjectHsrOptnRenewalScorecardPopUpValidMsg"), "documents you've submitted for your previous WELL Health-Safety", "Renewal Scorecard PopUp Valid Msg data doesn't match");
		Thread.sleep(3000);
		
	}
	
	public void OptnHsrValidateScorecardFeature(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Scorecard page");
//		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalScorecardPopUpClose", 0);
//		CommonMethod.click("V2ProjectHsrOptnRenewalScorecardPopUpClose");
		testlog.info("Then User will be redirected to Scorecard page");
		RefreshScorecard();
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalScorecardSelectRenewal", 0);
		CommonMethod.negativesoftassertFieldValid(String.valueOf(CommonMethod.getdropdownSize("V2ProjectHsrOptnRenewalScorecardSelectRenewalValid")), "3",
				"Renewal Scorecard dropdown list doesn't match");
		CommonMethod.selectdropdownVisiblePartialtext("V2ProjectHsrOptnRenewalScorecardSelectRenewal", "Renewal Scorecard");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(CommonMethod.getText("V2ProjectHsrOptnRenewalScorecardSelectRenewalValid")), "Renewal Scorecard",
				"Renewal Scorecard dropdown list doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalScorecardRenewalWarningIcon", 0);
		if (TestCaseName.equalsIgnoreCase("Healthsafety_TC_20_06_RenewalScorecardUpload")) {
			CommonMethod.negativesoftassertFieldValid(String.valueOf(CommonMethod.ElementSize("V2ProjectHsrOptnRenewalScorecardRenewalWarningIcon")), "16",
					"Renewal Scorecard warning Paper icon count doesn't match");
		}
		else {
		CommonMethod.negativesoftassertFieldValid(String.valueOf(CommonMethod.ElementSize("V2ProjectHsrOptnRenewalScorecardRenewalWarningIcon")), "16",
				"Renewal Scorecard warning Paper icon count doesn't match");
		}
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
}
	
	public void OptnHsrMarkAsYes(String SheetName, int rowNum, String FeatureName) throws Exception {
	
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
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);
				CommonMethod.RobustclickElementVisible("PortfolioAndRatingDeleteEditMenu", "PortfolioAndRatingLocAccDocumentTableDeleteIcon");
				//CommonMethod.WaitUntilNumberOfElementToBePresentWithException(ScorecardPortfolioAccountDeleteDisableIcon, 1);
				//CommonMethod.assertisElementPresentTrue(ScorecardPortfolioAccountDeleteDisableIcon, "Renewal DeleteDisableIcon element doesn't present");
				CommonMethod.Robustclick("V2ProjectHsrOptnRenewalYesTick");
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectHsrOptnRenewalYesTick");
				CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalYesTick", "YesTick icon element present");
				CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalNoTick", "NoTick icon element present");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAccLocScoreCardSelectUploadButton", 1);
				CommonMethod.assertisElementPresentTrue("PortfolioAccLocScoreCardSelectUploadButton", "Upload Document plus icon doesn't present");
				ValidateEnabledButtonInDocListOnMarkingAsYes();
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectHsrOptnRenewalScorecardDocumentSC1WarningIcon");
				CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalScorecardDocumentSC1WarningIcon", "Renewal Warning Paper icon element is present");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
}
	public void ValidateEnabledButtonInDocListOnMarkingAsYes() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioArchivedDocActionMenu");
		CommonMethod.assertisElementPresentFalse("PortfolioArchivedDocActionMenu", "Document is disabled or Showing in Archive List ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAndRatingLocAccDocumentTableEditIcon", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioAndRatingLocAccDocumentTableEditIcon", "Edit button is not present ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAndRatingLocAccDocumentTableReplaceIcon", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioAndRatingLocAccDocumentTableReplaceIcon", "Replace button is not present ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAndRatingLocAccDocumentTableDeleteIcon", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioAndRatingLocAccDocumentTableDeleteIcon", "Delete button is not present ");
	}
	
	public void ValidateDisabledButtonInDocListOnMarkingAsNo() throws IOException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioArchivedDocActionMenu", 1);
		CommonMethod.assertisElementPresentTrue("PortfolioArchivedDocActionMenu", "Document is not disabled ");
	}
	
	public void OptnHsrMarkAsNo(String SheetName, int rowNum, String FeatureName) throws IOException, InterruptedException {
		
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
				ValidateDisabledButtonInDocListOnMarkingAsNo();
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectHsrOptnRenewalYesTick");
				CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalYesTick", "YesTick icon element present");
				CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalNoTick", "NoTick icon element present");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAccLocScoreCardSelectUploadButton", 1);
				CommonMethod.assertisElementPresentTrue("PortfolioAccLocScoreCardSelectUploadButton", "Upload Document plus icon doesn't present");
			    CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectHsrOptnRenewalScorecardDocumentSC2WarningIcon", 1);
				CommonMethod.negativeAssertElementPresentTrue("V2ProjectHsrOptnRenewalScorecardDocumentSC2WarningIcon", "Renewal Warning Paper icon element present");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
}
	
public void OptnHsrMarkAsNoAndValidUploadDocument(String FeatureName) throws Exception {
	CommonMethod.refreshBrowser();
	rc.ScorecardLoading();
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 10);
		List<WebElement> Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			System.out.println("Creditname: " + Creditname);
			System.out.println("FeatureName: " + FeatureName);
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1"); 
				if (TestCaseName.equalsIgnoreCase("Healthsafety_TC_20_09_HsrMarkAsNoAndValidUploadDocument")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrRenewalScorecardDocumentPlusIcon", 0);
					CommonMethod.RobustclickElementVisible("HsrRenewalScorecardDocumentPlusIcon",
							"V2Projectscorecardverificationdropdown");
					}
				else {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccLocScoreCardSelectUploadButton", 0);
				CommonMethod.RobustclickElementVisible("PortfolioAccLocScoreCardSelectUploadButton",
						"V2Projectscorecardverificationdropdown");
				}
				testlog.info("And User clicks on Document Plus icon");
			CommonMethod.uploadFile("V2ProjectDocUpload", FeaturefileUpload, "UploadFileVerifyScorecard");
			CommonMethod.WaitUntilPresence("PortfolioScorecardUploadRemovelink", 60);
			if (!CommonMethod.isElementsExist("V2ProjectScorecardSelectedDocTypeValid", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect1",0);
				CommonMethod.click("Multiselect1");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectOwnerOrgDyn", 0);
				List<WebElement> ele1 = CommonMethod.findElements("SelectOwnerOrgDyn");
				ele1.get(0).click();
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature", 0);
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
			if (CommonMethod.isElementsExist("V2ProjectScorecardVerificationMethodSelectAddPart", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"V2ProjectScorecardVerificationMethodSelectAddPart", 0);
				CommonMethod.Robustclick("V2ProjectScorecardVerificationMethodSelectAddPart",
						"PortfolioScoreCardVerificationSelectFeature");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EnabledUploadbtn", 0);
			CommonMethod.Robustclick("EnabledUploadbtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("EnabledUploadbtn", 1);
			if (CommonMethod.isElementsExist("DocumentAddedToasterMessage", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DocumentAddedToasterMessage", 1);
			}
			rc.VaidDeleteButton();
			CommonMethod.WaitUntilPresence("V2ProjectWPRScorecardLanding", 60);
			CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioAccLocScoreCardSelectUploadButton",1);
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectHsrOptnRenewalScorecardDocumentSC2WarningIcon");
				CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalScorecardDocumentSC2WarningIcon", "Renewal Warning Paper icon element present");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
} 
	
public void OptnHsrOngoingDataReport(String SheetName, int rowNum, String FeatureName, String Warning) throws Exception {
		
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
				if (Warning.equalsIgnoreCase("true")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1"); 
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAccLocScoreCardSelectUploadButton",1);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalScorecardOngoingReport", 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectHsrOptnRenewalScorecardOngoingReport"), "Updated ongoing data report is required for renewal",
						"Renewal Scorecard Current document doesn't match");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);
				CommonMethod.RobustclickElementVisible("PortfolioAndRatingDeleteEditMenu", "PortfolioAndRatingLocAccDocumentTableDeleteIcon");
				}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAccLocScoreCardSelectUploadButton", 0);
			CommonMethod.RobustclickElementVisible("PortfolioAccLocScoreCardSelectUploadButton", "V2ProjectDocUpload");
			CommonMethod.uploadFile("V2ProjectDocUpload", FeaturefileUpload, "UploadFileVerifyScorecard");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadRemovelink", 0);
			if (!CommonMethod.isElementsExist("V2ProjectScorecardSelectedDocTypeValid", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect1",0);
				CommonMethod.click("Multiselect1");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectOwnerOrgDyn", 0);
				List<WebElement> ele1 = CommonMethod.findElements("SelectOwnerOrgDyn");
				ele1.get(0).click();
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature", 0);
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
			if (CommonMethod.isElementsExist("V2ProjectScorecardVerificationMethodSelectAddPart", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
						"V2ProjectScorecardVerificationMethodSelectAddPart", 0);
				CommonMethod.Robustclick("V2ProjectScorecardVerificationMethodSelectAddPart",
						"PortfolioScoreCardVerificationSelectFeature");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRUploadbtn1", 0);
			CommonMethod.Robustclick("V2ProjectWPRUploadbtn1");
			if (CommonMethod.isElementsExist("DocumentAddedToasterMessage", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DocumentAddedToasterMessage", 1);
			}
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectHsrOptnRenewalScorecardOngoingReport");
			CommonMethod.assertisElementPresentFalse("V2ProjectHsrOptnRenewalScorecardOngoingReport", "OngoingReport msg element present");
			rc.VaidDeleteButton();
			CommonMethod.WaitUntilPresence("V2ProjectWPRScorecardLanding", 60);
			CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioAccLocScoreCardSelectUploadButton",1);
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("HsrRenewalScorecardDocumentSA4WarningIcon");
				CommonMethod.assertisElementPresentFalse("HsrRenewalScorecardDocumentSA4WarningIcon", "Renewal Warning Paper icon element present");
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
} 

    public void OptnHsrValidateRenewalReviewOptions() throws IOException, InterruptedException {
    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
	CommonMethod.JavascriptClickElement("ReviewTab");
	testlog.info("When User clicks on ReviewTab");
	if (TestCaseName.equalsIgnoreCase("Healthsafety_TC_20_11_OptnHsrValidateRenewalReviewOptions")) {
        CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrSubmitReview", 0);
		CommonMethod.RobustclickElementVisible("HsrSubmitReview", "ReviewModelSubmitButton");
	}
	else {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
	CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "V2ProjectSelectPhase");
	}
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidReviewErrorMessagev2", 0);
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ValidReviewErrorMessagev2"),
			"Oops! It looks like your scorecard is incomplete.", "Review Mark Error message doesn't match");
	testlog.info("And User verifies Review ErrorMessage By Minimum selecting ScorecardPurseYes");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSelectPhase", 0);
	CommonMethod.DropdownOptionVisiblity("V2ProjectSelectPhase",
			"Renewal Preliminary Health-Safety Review", true);
	CommonMethod.DropdownOptionVisiblity("V2ProjectSelectPhase", "Renewal Final Health-Safety Review",
			true);
	CommonMethod.DropdownOptionVisiblity("V2ProjectSelectPhase", "Renewal Curative Action Plan Review",
			true);
	CommonMethod.refreshBrowser();
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
	if (TestCaseName.equalsIgnoreCase("Healthsafety_TC_20_11_OptnHsrValidateRenewalReviewOptions")) {
		hsr.NavigateScorecard();
	}
	else {
	CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "HealthSafetyTab");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
	CommonMethod.RobustclickElementVisible("HealthSafetyTab", "V2ProjectHsrOptnRenewalScorecardButton");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalScorecardButton", 0);
	CommonMethod.RobustclickElementVisible("V2ProjectHsrOptnRenewalScorecardButton", "V2ProjectHsrOptnRenewalScorecardSelectRenewal");
	rc.ScorecardLoading();
	}
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnRenewalScorecardSelectRenewal", 0);
}
    
public void OptnHsrRenewalRemoveWarningIconInScorecard(int LastFeatureNumber) throws IOException, InterruptedException {
	
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 10);
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
		Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		Feature = Feature.subList(0, LastFeatureNumber);
		for (WebElement f : Feature) {
			CommonMethod.scrolldowntoElement(f);
			CommonMethod.JavascriptClickElement(f);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
			CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1"); 
			if (CommonMethod.isElementsExist("V2ProjectHsrOptnRenewalYesTick", 5)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectHsrOptnRenewalYesTick", 1);
				CommonMethod.Robustclick("V2ProjectHsrOptnRenewalYesTick");
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectHsrOptnRenewalYesTick");
			}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRScorecardLanding", 0);
			CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
			CommonMethod.JavascriptClickElement(f);
		}
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
		
public void OptnHsrValidateRenewalReview() throws IOException, InterruptedException {
    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
	CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
	testlog.info("When User clicks on ReviewTab");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
	CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "V2ProjectSelectPhase");
	CommonMethod.WaitUntilClickble("V2ProjectcommentReview", 60).sendKeys("Renewal Preliminary Health-Safety Review");
	testlog.info("And User enter on Comment field");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSelectPhase", 0);
	CommonMethod.selectdropdownVisibletext("V2ProjectSelectPhase", "Renewal Preliminary Health-Safety Review");
	testlog.info("And User select the Phase");
	CommonMethod.RobustclickElementVisible("V2ProjectSubmitPhaseReview", "ReviewViewButton");
	testlog.info("And User clicks on submit button");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewViewButton", 0);
	testlog.info("Then User will be redirected to Review List page");

}

public void DocumentCountFromDocumentLibrary() throws IOException, InterruptedException {
	testlog.info("Given User is on Dashboard page");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2ProjectDocumentTab", 0);
	CommonMethod.RobustclickElementVisible("WellV2ProjectDocumentTab", "V2ProjectDocUploadbtn");
	testlog.info("When User clicks on DocumentTab");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnhsrDocumentLibrary", 0);
	CommonMethod.RobustclickElementVisible("V2ProjectHsrOptnhsrDocumentLibrary", "V2ProjectDocUploadbtn");
	testlog.info("Given User is on Document page");
	CommonMethod.scrolldowntoElement("V2ProjectHsrOptnhsrDocumentLibrary");
	testlog.info("And User click on General Document list");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("TableTrSize", 10);
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PaginitionDocumentCount", 0);
//	CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PaginitionDocumentCount"), "15",
//			"Hsr Uploaded Document Count doesn't match");
	
}

public void ValidationSealAndDate()throws IOException, InterruptedException {
	testlog.info("Given User is on Dashboard page");
CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "V2ProjectDashboardHsrSeal");
CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDashboardHsrSeal", 0);
CommonMethod.assertisElementPresentTrue("V2ProjectDashboardHsrSeal", "Seal Image is not visble");
testlog.info("Then User verifies seal from and to date");
CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDashboardHsrSealDate", 0);
String SealDate =CommonMethod.getText("V2ProjectDashboardHsrSealDate");
String[] Date = SealDate.split("to");
String fromDate = Date[0].split("from")[1].trim();
String toDate =Date[1].trim();
CommonMethod.negativesoftassertFieldValid(fromDate,CommonMethod.ValidateDate(),"Seal Current Year Date Mismatch");
CommonMethod.negativesoftassertFieldValid(toDate,CommonMethod.ValidateDateYear(),"Seal Next Year Date Mismatch");
testlog.pass("**Verifies the Seal Date successful**");
}

public void validateScorecardAsTeamMember() throws IOException, InterruptedException {

	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
	CommonMethod.RobustclickElementVisible("HealthSafetyTab","table");
	CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectTeamMemberObtinWarningText");
	CommonMethod.assertisElementPresentFalse("V2ProjectTeamMemberObtinWarningText","Warning message for Project Administrator element present");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("table", 1);
	CommonMethod.assertisElementPresentTrue("table","Team member Access Scorecard is element not present");
}

public void verifyScorecardAsMember(String SheetName, int rowNum) throws IOException, InterruptedException {

	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HealthSafetyTab", 0);
	CommonMethod.RobustclickElementVisible("HealthSafetyTab","V2ProjectTeamMemberRatingsContent");
	System.out.println("This is Health Safety Ratings Content"+CommonMethod.getattributeValueByTextContent("V2ProjectTeamMemberRatingsContent"));
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectTeamMemberRatingsContent", 1);
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("V2ProjectTeamMemberRatingsContent"), "Only Project Administrators are allowed to", "Team member warning message does not visible");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PerformanceTab", 0);
	CommonMethod.RobustclickElementVisible("PerformanceTab","V2ProjectTeamMemberRatingsContent");
	System.out.println("This is Performance Ratings Content"+CommonMethod.getattributeValueByTextContent("V2ProjectTeamMemberRatingsContent"));
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectTeamMemberRatingsContent", 1);
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("V2ProjectTeamMemberRatingsContent"), "Only Project Administrators are allowed to", "Team member warning message does not visible");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EquityTab", 0);
	CommonMethod.RobustclickElementVisible("EquityTab","V2ProjectTeamMemberRatingsContent");
	System.out.println("This is Equity Ratings Content"+CommonMethod.getattributeValueByTextContent("V2ProjectTeamMemberRatingsContent"));
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectTeamMemberRatingsContent", 1);
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("V2ProjectTeamMemberRatingsContent"), "Only Project Administrators are allowed to", "Team member warning message does not visible");
	
}

    public void editAndValidateAdminCertification(String SheetName, int rowNum, String RecertificationStatus) throws IOException, InterruptedException, ClientApiException {
    	
    	testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
	    CommonMethod.click("EditTab");
		testlog.info("When User clicks on EditTab");
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PrecertificationAchievementAdminTab", 0);
		CommonMethod.scrolldowntoElement("PrecertificationAchievementAdminTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditCertificationStatus", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectEditCertificationStatus", "Bronze");
		if (CommonMethod.isElementsExist("AssignFilterClear", 10)) {
			CommonMethod.click("AssignFilterClear");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditDateCertified", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectEditDateCertified", "DatePickerOkButton");
		CommonMethod.scrolldowntoElement("V2ProjectEditDateCertified");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickYearIcon", 0);
		CommonMethod.click("V2ProjectClickYearIcon");
		if(RecertificationStatus.equalsIgnoreCase("Expired Recertification")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMarkDateCertifiedFourYearBack", 0);
		CommonMethod.click("V2ProjectMarkDateCertifiedFourYearBack");
        }
		if(RecertificationStatus.equalsIgnoreCase("Recertification")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMarkDateCertifiedTwoYearBack", 0);
		CommonMethod.click("V2ProjectMarkDateCertifiedTwoYearBack");	
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.click("DatePickerOkButton");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.click("DatePickerOkButton");
    	rc.saveAchievementAdminTab("V2AchievementAdminTab");
		if(RecertificationStatus.equalsIgnoreCase("Expired Recertification")) {
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
				CommonMethod.Robustclick("ScorecardBannerClose");
		}
		if(Environment.equalsIgnoreCase("Test")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectValidateExpireRecertificationText", 1);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("V2ProjectValidateExpireRecertificationText").trim(), data.getCellData(SheetName, "ExpireRecertificationText", rowNum), "Expired Recertification Text does not matched");
		}
		}
		if(RecertificationStatus.equalsIgnoreCase("Recertification")) {
		rc.SignOut();
		login.Login();
		v2project.SearchV2ProjectById(SheetName, rowNum);
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		if (TestCaseName.contains("V2Project_TC_28_01_Recertification")) {
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectStartRecertificationBtn");
			 CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectStartRecertificationBtn","StartRecertificationBtn is visible");
			login.AdminLogin();
			v2project.AdminSearchById(SheetName, rowNum);
		}
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectValidateExpireRecertificationText", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartRecertificationBtn", 0);
		CommonMethod.Robustclick("V2ProjectStartRecertificationBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYesBtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectYesBtn","V2ProjectValidateAttentionAlertText");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectValidateAttentionAlertText", 0);
		String actualAttentionAlertText = CommonMethod.getattributeValueByTextContent("V2ProjectValidateAttentionAlertText").trim();
		CommonMethod.negativesoftassertFieldValid(actualAttentionAlertText, data.getCellData(SheetName, "AttentionAlertText", rowNum), "Attention Alert Text does not matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectNoBtn", 0);
		CommonMethod.click("V2ProjectNoBtn");
		if(TestCaseName.equalsIgnoreCase("V2_TC_23C_01_V2PilotNonCoreProjectRecertification")) {
			CommonMethod.scrolldowntoElement("V2ProjectNoBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotContinueBtn", 0);
			CommonMethod.Robustclick("V2PilotContinueBtn");
			CommonMethod.scrolldowntoElement("V2ProjectNoBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotRecertificationContinueBtn", 0);
			CommonMethod.Robustclick("V2PilotRecertificationContinueBtn");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationSubmitBtn", 0);
		CommonMethod.click("V2ProjectRecertificationSubmitBtn");
		if(TestCaseName.equalsIgnoreCase("V2_TC_23C_01_V2PilotNonCoreProjectRecertification")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPopUpCancelBtn", 0);
			CommonMethod.Robustclick("V2ProjectPopUpCancelBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationSubmitBtn", 0);
			CommonMethod.click("V2ProjectRecertificationSubmitBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotRecertificationStaySameVersionBtn", 0);
			CommonMethod.Robustclick("V2PilotRecertificationStaySameVersionBtn");
		}
		validateAdminCertificationDashboard();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScrollElement", 0);	
		CommonMethod.scrolldowntoElement("V2ProjectScrollElement");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickHereLink", 0);	
		CommonMethod.Robustclick("V2ProjectClickHereLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationBanner", 0);
		CommonMethod.click("V2ProjectRecertificationBanner");
		validateAdminCertificationDashboard();
	    }
    }
    
    public void validateV2PilotNonCoreProjectAdminCertification(String SheetName, int rowNum) throws Exception {
    	
    	testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
	    CommonMethod.click("EditTab");
		testlog.info("When User clicks on EditTab");
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditCertificationStatus", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectEditCertificationStatus", "Bronze");
		if (CommonMethod.isElementsExist("AssignFilterClear", 10)) {
			CommonMethod.click("AssignFilterClear");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditDateCertified", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectEditDateCertified", "DatePickerOkButton");
		CommonMethod.scrolldowntoElement("V2ProjectEditDateCertified");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickYearIcon", 0);
		CommonMethod.click("V2ProjectClickYearIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMarkDateCertifiedTwoYearBack", 0);
		CommonMethod.click("V2ProjectMarkDateCertifiedTwoYearBack");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.click("DatePickerOkButton");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.click("DatePickerOkButton");
		rc.saveAchievementAdminTab("V2AchievementAdminTab");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
        
		if(TestCaseName.contains("V1ProjectRecertification")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartRecertificationBtn", 0);
		CommonMethod.click("V2ProjectStartRecertificationBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYesBtn", 0);
		CommonMethod.click("V2ProjectYesBtn");
		} else {
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectValidateExpireRecertificationText", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartRecertificationBtn", 0);
		CommonMethod.click("V2ProjectStartRecertificationBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYesBtn", 0);
		CommonMethod.click("V2ProjectYesBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectValidateAttentionAlertText", 0);
		String actualAttentionAlertText = CommonMethod.getattributeValueByTextContent("V2ProjectValidateAttentionAlertText").trim();
		CommonMethod.negativesoftassertFieldValid(actualAttentionAlertText, data.getCellData(SheetName, "AttentionAlertText", rowNum), "Attention Alert Text does not matched");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectNoBtn", 0);
		CommonMethod.click("V2ProjectNoBtn");
		CommonMethod.scrolldowntoElement("V2ProjectNoBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotContinueBtn", 0);
		CommonMethod.Robustclick("V2PilotContinueBtn");
		CommonMethod.scrolldowntoElement("V2ProjectNoBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectInitialCertificationContinuebtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectInitialCertificationContinuebtn");
		if(TestCaseName.contains("V1ProjectRecertification")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectUpgradeToWELLV2Continuebtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectUpgradeToWELLV2Continuebtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotRecertificationStaySameVersionBtn", 0);
		CommonMethod.Robustclick("V2PilotRecertificationStaySameVersionBtn");
		ValidateV1ProjectRecertification(SheetName, rowNum, "V1ProjectValidateEditWellStandardRecertification");
		} else {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationSubmitBtn", 0);
		CommonMethod.click("V2ProjectRecertificationSubmitBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPopUpCancelBtn", 0);
		CommonMethod.Robustclick("V2ProjectPopUpCancelBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationSubmitBtn", 0);
		CommonMethod.click("V2ProjectRecertificationSubmitBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotRecertificationStaySameVersionBtn", 0);
		CommonMethod.Robustclick("V2PilotRecertificationStaySameVersionBtn");
		validateAdminCertificationDashboard();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScrollElement", 0);	
		CommonMethod.scrolldowntoElement("V2ProjectScrollElement");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickHereLink", 0);	
		CommonMethod.Robustclick("V2ProjectClickHereLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationBanner", 0);
		CommonMethod.click("V2ProjectRecertificationBanner");
		validateAdminCertificationDashboard();
		}
    }
    
    public void validateAdminCertificationDashboard() throws IOException {  	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationReviewScorecardBtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationSubmitForReviewBtn", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectValidateSeal", 1);
    }
    
	public void AdminReviewUpload(String DocumentType) throws IOException, InterruptedException {
		testlog.info("Given User on Admin Review");
    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewUploadButton", 0);
	CommonMethod.RobustclickElementVisible("PortfolioAdminReviewUploadButton",
			"PortfolioAdminReviewSelectDocumentType");
	testlog.info("When User clicks on Upload button");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewSelectDocumentType", 0);
	CommonMethod.negativesoftassertFieldValid(
			String.valueOf(CommonMethod.getdropdownSize("PortfolioAdminReviewSelectDocumentType")), "8",
			"DocumentType Dropdown in Review Upload doesn't Match");
	testlog.info("And User verifies DocumentType size successfully**");
    CommonMethod.selectdropdownVisibletext("PortfolioAdminReviewSelectDocumentType", DocumentType);
	CommonMethod.uploadFile("DocumentsUpload", ImportReviewUpload, "UploadFileVerifyScorecard");
	testlog.info("And User Upload Feature Document");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewModelUploadButton", 0);
	CommonMethod.RobustclickElementVisible("PortfolioAdminReviewModelUploadButton",
			"PortfolioAdminReviewValidDocumentType");
	}
	
	public void validAdminReviewUpload() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioImportDocLogWer", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioImportDocLogWer"), "WER",
				"DocumentType Wer doesn't Match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioImportDocLogWpr"), "WPR",
				"DocumentType Wpr doesn't Match");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PortfolioImportLogDocHsr"), "HSR",
				"DocumentType Hsr doesn't Match");
		}
	
	public void AdminImportDocumentV2Review(String Commodity, String ReviewType, String CheckboxXpath) throws IOException, InterruptedException {
		testlog.info("Given User on AdminReview Import page");
		ReviewAdminActionsButton();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAdminReviewImportButton",
				"PortfolioAdminReviewImportSelectFile");
		testlog.info("When User clicks on Import Button");
		CommonMethod.selectdropdownVisibletextwithoutdelay("PortfolioAdminReviewImportSelectReviewType", ReviewType);
		testlog.info("And user select on ReviewType");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSelectFile", 0);
			CommonMethod.selectdropdownVisibletextwithoutdelay("PortfolioAdminReviewImportSelectFile",
					"ImportReviewUpload");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(CheckboxXpath,
				0);
		CommonMethod.ClickCheckbox(CheckboxXpath);
		testlog.info("And user checks on Import Document Rulings");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportSubmitButton", 0);
		CommonMethod.Robustclick("PortfolioAdminReviewImportSubmitButton", CheckboxXpath);
		testlog.info("And user clicks on the submit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan(CheckboxXpath, 1);
	}
	
	public void AdminReviewImportButton() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewAdminActionsButton", 0);
		CommonMethod.RobustclickElementVisible("ReviewAdminActionsButton", "ReviewReturnButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewImportButton", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAdminReviewImportButton",
				"PortfolioAdminReviewValidImportLogFilename");
	}
	
	public void AdminReviewimportLogs(String Commodity) throws IOException, InterruptedException {
		
		// Valid Import Review
		testlog.info("And user again clicks on import review result");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAdminReviewValidImportLogFilename", 0);
		List<String> expImportName = new ArrayList<>();
		expImportName.add("Imported Scores");
		expImportName.add("Imported Achievements");
		expImportName.add("Imported Document Rulings");
		List<WebElement> ImportName;
		ImportName = CommonMethod.findElements("PortfolioAdminReviewValidImportLogType");
		for (WebElement s : ImportName) {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText(s).replaceAll(",", ""), expImportName,
					"Option label in Document table data mismatch");
			testlog.info("And User verifies Added DocumentType in Log successfully**");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioImportLogcompleteLabel",3);
		int completeLabel = CommonMethod.ElementSize("PortfolioImportLogcompleteLabel");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(completeLabel), "3",
				"Import Log Complete label count mismatch");
		int fileName = CommonMethod.ElementSize("PortfolioImportLogFileName");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(fileName), "3", "Import Log FileName count mismatch");
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
	}

	public void AdminReviewimportHistory(String Commodity) throws IOException, InterruptedException {
		// Valid Import Review history
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioBackToAllReview", 0);
		CommonMethod.RobustclickElementVisible("PortfolioBackToAllReview", "PortfolioReviewImportHistory");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioReviewImportHistory", 0);
		CommonMethod.RobustclickElementVisible("PortfolioReviewImportHistory",
				"PortfolioAdminReviewValidImportLogFilename");
		AdminReviewimportLogs(Commodity);
		testlog.info("And user verifies imported review data on the history popup");
		testlog.pass("And User Imported review results successfully**");
	}

	public void DeleteAdminReviewUpload(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User on AdminReview Import page");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAdminReviewDeleteIconLog", 3);
		int deleteIcon = CommonMethod.ElementSize("PortfolioAdminReviewDeleteIconLog");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(deleteIcon), "3",
				"AdminReview Delete Icon Log doesn't present");
		testlog.info("Then User verifies Upload Review delete icon count");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioImportLogUploadDeleteIcon", 0);
		CommonMethod.RobustclickElementVisible("PortfolioImportLogUploadDeleteIcon",
				"PortfolioImportLogUploadDeleteYesButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioImportLogUploadDeleteYesButton", 0);
		CommonMethod.Robustclick("PortfolioImportLogUploadDeleteYesButton", "PortfolioImportLogUploadDeleteIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAdminReviewDeleteIconLog", 2);
		int afterDeleteIcon = CommonMethod.ElementSize("PortfolioAdminReviewDeleteIconLog");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(afterDeleteIcon), "2",
				"AdminReview Delete Icon Log doesn't present");
		testlog.info("Then User verifies Upload Review delete icon count");
	}
	
	public void validTeamMemberAccessForSubmitReview() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("And User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "V2ProjectcommentReview");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidReviewErrorMessage110Points", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ValidReviewErrorMessage110Points"), "You don't have sufficient privileges to submit documentation.", "Verified not sufficient privileges to submit documentation Message");	
	}
	
	public void ValidateTeamMemberEditProject() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("EditTab",1);
		CommonMethod.assertisElementPresentTrue("EditTab","EditTab is not visible");
}
	
	public void ValidateTeamMemberNotEditProject() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("EditTab");
		CommonMethod.assertisElementPresentFalse("EditTab","EditTab is visible");
}
	
	public void ValidateTeamManagerNotAdminEditProject() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectProjectScope");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectAdminFieldsButton");
		CommonMethod.assertisElementPresentFalse("V2ProjectAdminFieldsButton","EditTab is visible");
	}
	
	public void ValidateTeamMemberNotEditUser() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectAddMemberbtn");
		CommonMethod.assertisElementPresentFalse("V2ProjectAddMemberbtn",
				"AddMemberbtn is visible");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("TeamMemberDeleteButton");
		CommonMethod.assertisElementPresentFalse("TeamMemberDeleteButton",
				"Delete icon is visible");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("TeamMemberEditButton");
		CommonMethod.assertisElementPresentFalse("TeamMemberEditButton",
				"Edit icon is visible");
		testlog.info("And User verifies Edit button icon");
		testlog.info("And User verifies Delete button icon");
}
	
	public void ValidateTeamManagerNotEditUser() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectAddMemberbtn",1);
		CommonMethod.assertisElementPresentTrue("V2ProjectAddMemberbtn",
				"AddMemberbtn is visible");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("TeamMemberDeleteButton");
		CommonMethod.assertisElementPresentFalse("TeamMemberDeleteButton",
				"Delete icon is visible");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("TeamMemberEditButton");
		CommonMethod.assertisElementPresentFalse("TeamMemberEditButton",
				"Edit icon is visible");
		testlog.info("And User verifies Edit button icon");
		testlog.info("And User verifies Delete button icon");
}
	
	public void CoreProjectEditAndValidateAdminCertification(String SheetName, int rowNum, String Country) throws IOException, InterruptedException {
		
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
	    CommonMethod.click("EditTab");
		testlog.info("When User clicks on EditTab");
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditCertificationStatus", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectEditCertificationStatus", "Bronze");
		if (CommonMethod.isElementsExist("AssignFilterClear", 10)) {
			CommonMethod.click("AssignFilterClear");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditDateCertified", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectEditDateCertified", "DatePickerOkButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickYearIcon", 0);
		CommonMethod.click("V2ProjectClickYearIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMarkDateCertifiedTwoYearBack", 0);
		CommonMethod.click("V2ProjectMarkDateCertifiedTwoYearBack");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.click("DatePickerOkButton");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.click("DatePickerOkButton");
	    rc.saveAchievementAdminTab("V2AchievementAdminTab");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectValidateExpireRecertificationText", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartRecertificationBtn", 0);
		CommonMethod.click("V2ProjectStartRecertificationBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYesBtn", 0);
		CommonMethod.click("V2ProjectYesBtn");
//		rc.SelectOwnerOrg(SheetName, rowNum);
//		data.setCellData(SheetName, "OrgName", rowNum, CommonMethod.getText("OrgName"));
//		testlog.info("OrganizationName: " + data.getCellData(SheetName, "OrgName", rowNum));
//		String Ownername = USfaker.address().firstName();
//		String Email = USfaker.internet().emailAddress();
//		String Phoneno = USfaker.number().digits(10);
//		testlog.info("Ownername: " + Ownername);
//		CommonMethod.sendKeys("OwnerName", Ownername);
//		testlog.info("Ownername: " + Ownername);
//		CommonMethod.sendKeys("OwnerName", Ownername);
//		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("OwnerName"));
//		testlog.info("OwnerEmail: " + Email);
//		CommonMethod.sendKeys("V2ProjectownerEmail", Email);
//		data.setCellData(SheetName, "OwnerEmail", rowNum, CommonMethod.getattributeValue("V2ProjectownerEmail"));
//		CommonMethod.sendKeys("V2Projectownerphone", Phoneno);
//		data.setCellData(SheetName, "PhoneNum", rowNum, CommonMethod.getattributeValue("V2Projectownerphone"));
//		CommonMethod.selectdropdownrandom("OrgIndustry");
//		testlog.info("And User select Organisation Sector");
//		data.setCellData(SheetName, "OrgIndustry", rowNum, CommonMethod.getSelectedDropdownValue("OrgIndustry"));
//		CommonMethod.sendKeys("V2Projectorganizationwebsite",
//				"https://test-nuxt.wellcertified.com/projects/v2/2202266385/register");
//		data.setCellData(SheetName, "Website", rowNum, CommonMethod.getattributeValue("V2Projectorganizationwebsite"));
//		CommonMethod.sendKeys("V2ProjectorganizationOverview", Ownername);
//		data.setCellData(SheetName, "Overview", rowNum,
//				CommonMethod.getattributeValue("V2ProjectorganizationOverview"));
//		rc.SelectCountryAndState(Country, SheetName, rowNum);
//		String ProjectAddress = USfaker.address().streetAddress();
//		String ProjectCity = USfaker.address().cityName();
//		String PostalCode = USfaker.address().zipCode();
//		testlog.info("Street: " + ProjectAddress);
//		testlog.info("City: " + ProjectCity);
//		testlog.info("Postalcode: " + PostalCode);
//		CommonMethod.sendKeys("StreetName", ProjectAddress);
//		data.setCellData(SheetName, "Street", rowNum, CommonMethod.getattributeValue("StreetName"));
//		CommonMethod.sendKeys("CityName", ProjectCity);
//		data.setCellData(SheetName, "City", rowNum, CommonMethod.getattributeValue("CityName"));
//		CommonMethod.sendKeys("PostalCode", PostalCode);
//		data.setCellData(SheetName, "PostalCode", rowNum,
//				CommonMethod.getattributeValue("PostalCode"));	
//		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplyButton", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitCoreRecertButton", 0);
		CommonMethod.JavascriptClickElement("SubmitCoreRecertButton");
		validateAdminCertificationDashboard();
		CommonMethod.scrolldowntoElement("V2ProjectScrollElement");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickHereLink", 0);	
		CommonMethod.Robustclick("V2ProjectClickHereLink");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationBanner", 0);
		CommonMethod.Robustclick("V2ProjectRecertificationBanner");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		validateAdminCertificationDashboard();
	}
	
	public void UpdateSpaceType() throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
	CommonMethod.RobustclickElementVisible("EditTab", "ProjectInformationButton");
	testlog.info("When User clicks on EditTab");
    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardUpdateCommercialKitchenSpaceType", 0);
	CommonMethod.ClickCheckbox("V2ProjectScorecardUpdateCommercialKitchenSpaceType");
	testlog.info("And User Checks CommercialKitchen SpaceType");
    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
	CommonMethod.Robustclick("V2ProjectSaveChangesButton");
	CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectSaveChangesButton", 1);
	testlog.info("And User clicks on Save button");
}
	
	public void clickMayBeAndValidWarningMessageInReview() throws IOException, InterruptedException {
		rc.clickScorecard();
		purseMaybeValidFromScorecard();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("When User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "ValidReviewErrorMessagev2");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ValidReviewErrorMessagev2"),
				"Oops! It looks like your scorecard is incomplete. Please ensure all features are marked as either a YES or NO and then resubmit.",
				"Review Mark Error message doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidReviewErrorMessage2", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ValidReviewErrorMessage2"),
				"Oops! It looks like your scorecard is incomplete.", "Review Mark Error message doesn't match");
		rc.clickScorecard();
		purseMaybeToNoValidFromScorecard();
	}
	
	public void V2PilotCoreProjectEditAndValidateRecertification(String SheetName, int rowNum) throws IOException, InterruptedException {
		
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
	    CommonMethod.click("EditTab");
		testlog.info("When User clicks on EditTab");
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditCertificationStatus", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectEditCertificationStatus", "Bronze");
		if (CommonMethod.isElementsExist("AssignFilterClear", 10)) {
			CommonMethod.click("AssignFilterClear");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditDateCertified", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectEditDateCertified", "DatePickerOkButton");
		CommonMethod.scrolldowntoElement("V2ProjectEditDateCertified");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickYearIcon", 0);
		CommonMethod.click("V2ProjectClickYearIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMarkDateCertifiedTwoYearBack", 0);
		CommonMethod.click("V2ProjectMarkDateCertifiedTwoYearBack");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.click("DatePickerOkButton");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.click("DatePickerOkButton");
		 rc.saveAchievementAdminTab("V2AchievementAdminTab");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 20)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		if (TestCaseName.contains("V1ProjectRetailRecertificationIntoV2Project")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartRecertificationBtn", 0);
		CommonMethod.click("V2ProjectStartRecertificationBtn");	
		} else {
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectValidateExpireRecertificationText", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartRecertificationBtn", 0);
		CommonMethod.click("V2ProjectStartRecertificationBtn");	
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectNoBtn", 0);
		CommonMethod.click("V2ProjectNoBtn");
		CommonMethod.scrolldowntoElement("V2ProjectNoBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotContinueBtn", 0);
		CommonMethod.RobustclickElementVisible("V2PilotContinueBtn", "V2PilotYesBtn");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotYesBtn", 0);
		CommonMethod.click("V2PilotYesBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotUpdatedProjectArea", 0);
		CommonMethod.click("V2PilotUpdatedProjectArea");
		CommonMethod.clearAndSendKey("V2PilotUpdatedProjectArea","5555.00"); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommentBox", 0);
		CommonMethod.clearAndSendKey("CommentBox","Describe the change to your project area in a few sentences.");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotChangeAreaContinueBtn", 0);
		CommonMethod.click("V2PilotChangeAreaContinueBtn");    
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotUpgradeToV2Btn", 0);
		CommonMethod.click("V2PilotUpgradeToV2Btn");
		CommonMethod.scrolldowntoElement("V2PilotUpgradeToV2Btn");
		if (TestCaseName.equalsIgnoreCase("V1_TC_33_03_V1ProjectRetailRecertificationIntoV2Project")) 
		{
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRContinuebtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectWPRContinuebtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotUpgradeToWellV2Btn", 0);
		CommonMethod.RobustclickElementVisible("V2PilotUpgradeToWellV2Btn", "V2ProjectRecertificationSpaceTypeDisabledRetail");				
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectRecertificationSpaceTypeDisabledRetail", 1);
		CommonMethod.assertisElementPresentTrue("V2ProjectRecertificationSpaceTypeDisabledRetail", "Recertification SpaceType: Retail is enabled ");		
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectRecertificationSpaceTypeGrayColorRetail", 1);
		CommonMethod.assertisElementPresentTrue("V2ProjectRecertificationSpaceTypeGrayColorRetail", "Recertification SpaceType: Retail is not gray color ");	
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectRecertificationSpaceTypeGrayColorRetail"); 		
		CommonMethod.scrolldowntoElement("V2ProjectRecertificationSpaceTypeGrocery");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSpaceTypeContinuebtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectSpaceTypeContinuebtn");	
		CommonMethod.ScrollUpToElement("V2ProjectwellCertificationText");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectwellCertificationForRecertification", 0);
		CommonMethod.JavascriptClickElement("V2ProjectwellCertificationForRecertification");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSelectOwnershipTypeRadioBtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectSelectOwnershipTypeRadioBtn");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectSelectOwnershipTypeRadioBtn");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertOwnershipContinuebtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectRecertOwnershipContinuebtn");
		} else {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationSubmitBtn", 0);
		CommonMethod.click("V2ProjectRecertificationSubmitBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPopUpCancelBtn", 0);
		CommonMethod.Robustclick("V2ProjectPopUpCancelBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationSubmitBtn", 0);
		CommonMethod.click("V2ProjectRecertificationSubmitBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotUpgradeToWellV2Btn", 0);
		CommonMethod.Robustclick("V2PilotUpgradeToWellV2Btn");		
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScrollElement", 0);	
		CommonMethod.scrolldowntoElement("V2ProjectScrollElement");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickHereLink", 0);	
		CommonMethod.Robustclick("V2ProjectClickHereLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationBanner", 0);
		CommonMethod.Robustclick("V2ProjectRecertificationBanner");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		validateAdminCertificationDashboard();
	}
	
	
	public void ValidateParentScorecardDataInChildScorecard(String SheetName, int rowNum, String featureName) throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TargetedPoints", 0);	
		String actualTargetedPointsScore = CommonMethod.getattributeValueByTextContent("TargetedPoints").trim();
		System.out.println(actualTargetedPointsScore);
		actualTargetedPointsScore = actualTargetedPointsScore.replaceAll("\\s+", " ").trim();
		CommonMethod.negativesoftassertFieldValid(actualTargetedPointsScore, "88 Points", "Targeted Points Score data does not matched");
		
		List<WebElement> Feature;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 0);
		Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		boolean flag=false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag=true;
				CommonMethod.JavascriptClickElement(ele);
	        CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FeatureNextStep", 0);
	        CommonMethod.scrolldowntoElement("FeatureNextStep");
	        Thread.sleep(3000);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotComment", 0);	
			String actualCommentedText = CommonMethod.getText("V2PilotComment").trim();
			System.out.println(actualCommentedText);
			String expectedCommentedText = data.getCellData(SheetName, "Comment", rowNum);
			CommonMethod.negativesoftassertFieldValid(actualCommentedText, expectedCommentedText, "Comment data does not matched");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardverifyVerficationMethod", 0);	
			String actualFeatureFileText = CommonMethod.getattributeValueByTextContent("PortfolioScorecardverifyVerficationMethod").trim();
			CommonMethod.negativesoftassertFieldValid(actualFeatureFileText, "Performance Test", "Performance Test text data does not matched");
			CommonMethod.JavascriptClickElement(ele);
			break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
	}
	
	public void ValidateUpdatedProjectArea() throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.JavascriptClickElement("EditTab");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotUpdatedProjectAreaValidate", 0);
		String actualUpdatedProjectAreaNumber = CommonMethod.getattributeValue("V2PilotUpdatedProjectAreaValidate");
		CommonMethod.negativesoftassertFieldValidEquals(actualUpdatedProjectAreaNumber, "5555.00", "Updated Project Area Number data does not matched");
	}   
	
	public void ValidateScorecardDataInScorecardListOfDocumentsTab() throws IOException, InterruptedException {
	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotScorecardBtnInDocumentsTab", 0);
		CommonMethod.RobustclickElementVisible("V2PilotScorecardBtnInDocumentsTab", "LocationTableTrSize");
		CommonMethod.scrolldowntoElement("V2PilotScorecardBtnInDocumentsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("TableTrSize", 10);
		CommonMethod.negativesoftassertFieldValid(String.valueOf(CommonMethod.ElementSize("TableTrSize")), "10",
				"Hsr Uploaded Document Count doesn't match"); 
	}
	
	public void StoreIdInRecertification(String SheetName, int rowNum) throws IOException, InterruptedException {
		
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("StoreId", 0);
	String getId = CommonMethod.getText("StoreId");
	String[] stringArray = getId.split(": ");
	String getProjectId = stringArray[1].trim();
	data.setCellData(SheetName, "ProjectID", rowNum, getProjectId);
	}
	
	public void CoreProjectMarkPreCertified(String SheetName, int rowNum) throws IOException, InterruptedException {
		
	testlog.info("Given User is on Dashboard page");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
	CommonMethod.click("EditTab");
	testlog.info("When User clicks on EditTab");
	rc.navigateAchievementAdminTab();
	testlog.info("And User clicks on AdminFields Button");
	CommonMethod.verifyDropdownValues("V2ProjectEditPreCertificationStatus", "ProjectPrecertificationStatus");
	CommonMethod.verifyDropdownValues("V2ProjectEditCertificationStatus", "ProjectCertificationStatus");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditPreCertificationStatus", 0);
	CommonMethod.selectdropdownVisibletext("V2ProjectEditPreCertificationStatus", "Achieved");
	String DatePreCertified;
	if(SheetName.equalsIgnoreCase("V2Project")) {
		DatePreCertified = "V2ProjectEditDatePreCertified";
	}
	else {
		DatePreCertified = "CommunityEditDatePreCertified";
	}
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(DatePreCertified, 0);
	CommonMethod.RobustclickElementVisible(DatePreCertified, "DatePickerOkButton");
	if(SheetName.equalsIgnoreCase("Community")) {	
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
	CommonMethod.Robustclick("DatePickerOkButton");		
	} else {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickYearIcon", 0);
	CommonMethod.click("V2ProjectClickYearIcon");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMarkDateCertifiedTwoYearBack", 0);
	CommonMethod.click("V2ProjectMarkDateCertifiedTwoYearBack");	
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
	CommonMethod.click("DatePickerOkButton");	
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
	CommonMethod.Robustclick("DatePickerOkButton");
	}
	rc.saveAchievementAdminTab("PrecertificationAchievementAdminTab");
	}
	
	public void SearchAndClickV2ProjectByIdForRecertificationTest(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchResultIDVerify", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("SearchResultIDVerify"),
				data.getCellData(SheetName, "ProjectID", rowNum), "Project Id doesn't matches in search");
		testlog.info("And User verifies Search V2Project By Id");
		CommonMethod.Robustclick("SearchResultIDVerify");
	}
	
	public void ReviewAdminActionsButton() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewAdminActionsButton", 0);
		CommonMethod.JavascriptClickElement("ReviewAdminActionsButton");
		}
	
	public void ValidateTargetedAndAwardedPoints(String HalfTargetedPointsObjectLocator, String FullTargetedPointsObjectLocator,
	String HalfAwardedPointsObjectLocator, String FullAwardedPointsObjectLocator) throws IOException, InterruptedException {
		
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException(HalfTargetedPointsObjectLocator, 1);
	CommonMethod.assertisElementPresentTrue(HalfTargetedPointsObjectLocator, "0.5 Points Targeted not present ");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException(FullTargetedPointsObjectLocator, 1);
	CommonMethod.assertisElementPresentTrue(FullTargetedPointsObjectLocator, "1 Points Targeted not present ");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException(HalfAwardedPointsObjectLocator, 1);
	CommonMethod.assertisElementPresentTrue(HalfAwardedPointsObjectLocator, "0.5 Points Awarded not present ");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException(FullAwardedPointsObjectLocator, 1);
	CommonMethod.assertisElementPresentTrue(FullAwardedPointsObjectLocator, "1 Points Awarded not present ");
	}
	
	public void featureAdminScorecardReviewAndSelectAdminReview( String SheetName, int rowNum, String FeatureName,
			String SelectResponseReview) throws IOException, InterruptedException {
		boolean flag = false;
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
	List<WebElement> Feature ;
		if (TestCaseName.contains("RatingScorecard")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
			Feature = CommonMethod.findElements("V2projectRatingFeatureName");
		}
		else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 0);
			Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		}
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				testlog.info("When User clicks on Feature");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardAdminReviewButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardAdminReviewButton",
				"V2ProjectScorecardSelectResponseReview");
		testlog.info("And User clicks on Admin Review Button");
		CommonMethod.scrolldowntoElement("V2ProjectScorecardAdminReviewButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardSelectResponseReview", 0);
		CommonMethod.selectdropdownValue("V2ProjectScorecardSelectResponseReview", SelectResponseReview);
		testlog.info("And User select Response Review");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSelectResponse", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectSelectResponse", "Achieved");	
		if (CommonMethod.isElementsExist("V2ProjectAdditionalCorePoint", 10)) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdditionalCorePoint", 0);
		CommonMethod.ClickCheckbox("V2ProjectAdditionalCorePoint");
		}
		CommonMethod.JavascriptClickElement(ele);
		break;
		}
	}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		v2project.RefreshScorecard();
}
	
	public void VerifyPdfDownload(String SheetName, int rowNum) throws IOException, InterruptedException {
		
		String ProjectName = data.getCellData(SheetName, "ProjectName", rowNum).toUpperCase();
		String CountryName = data.getCellData(SheetName, "Country", rowNum).toUpperCase();
		String StateName = data.getCellData(SheetName, "State", rowNum).toUpperCase();
		String[] ProjDetails = { ProjectName, CountryName, StateName};
		testlog.info("And User clicks on Project as Certification button");
		boolean fileExistsReturnValue = CommonMethod.isFileDownloaded();
		testlog.info("Then User verifies Downloaded file");
		String fileExists = Boolean.toString(fileExistsReturnValue);
		System.out.println("fileExists:" + fileExists);
		CommonMethod.negativesoftassertFieldValid(fileExists, "true", "Downloaded Billing Receipt file doesn't Exist");
		Thread.sleep(3000);
		if (CommonMethod.isFileDownloaded()) {
			File path = new File(downloadPath);
			File[] files = path.listFiles();
			for (File file : files) {
				String pdfContent = CommonMethod.extractPDFContent(file.toString()).replaceAll("\\s+", " ").trim();
				for (String s : ProjDetails) {
					CommonMethod.negativesoftassertFieldValid(pdfContent, s,"Project as Certification Data mismatch");
					testlog.info("Then User verifies Address from Downloaded Receipt");
				}
				String[] splits = pdfContent.split("STATES");
				String pdf = splits[1];
				CommonMethod.negativesoftassertFieldValid(pdf.trim(), CommonMethod.ValidateDateYearAndDate(),"Achievement Certification Pdf Date Data mismatch");
				break;
			}
		}
		testlog.info("And User verifies ProjectName Project as Certification");
		testlog.info("And User verifies CountryName Project as Certification");
		testlog.info("And User verifies StateName Project as Certification");
		testlog.info("And User verifies Date as Certification");
}
	public void ValidateIntentCheckboxInUploadedDocs(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectValidateIntentCheckbox", 0);
		String actualIntentTag = CommonMethod.getattributeValueByTextContent("V2ProjectValidateIntentCheckbox");
		CommonMethod.negativesoftassertFieldValid(actualIntentTag, "Intent", "Intent tag does not matched ");
	}
	
	public void ValidateV1ProjectRecertification(String SheetName, int rowNum, String WellStandardLocator) throws Exception {
		testlog.info("User is on DashboardTab");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 10)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V1ProjectValidateDashboardRecertification", 1);
		CommonMethod.assertisElementPresentTrue("V1ProjectValidateDashboardRecertification", "Ongoing reporting and recertification timeline is not showing ");
		CommonMethod.refreshBrowser();		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.JavascriptClickElement("EditTab");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectInformationButton", 0);
		CommonMethod.RobustclickElementVisible("ProjectInformationButton", "V2ProjectProjectScope");
		testlog.info("And User clicks on Project Information Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(WellStandardLocator, 0);
		CommonMethod.VerifyRadioOrCheckboxSelcted(WellStandardLocator);	
		rc.ClickBilling();
		testlog.info("When User clicks on BillingTab");
	    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V1ProjectValidateBillingRecertification", 0);
	    String actualBillingStatus=CommonMethod.getattributeValueByTextContent("V1ProjectValidateBillingRecertification");
	    actualBillingStatus= actualBillingStatus.replaceAll("\\s+", " ").trim();
	    System.out.println(actualBillingStatus);
	    CommonMethod.negativesoftassertFieldValid(actualBillingStatus.toLowerCase(), "waived", "WAIVED status does not matched ");
	}
	
public void V2ProjectMarkDateCertifiedTwoYearBack() throws IOException, InterruptedException {
		
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
	    CommonMethod.click("EditTab");
		testlog.info("When User clicks on EditTab");
		rc.navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditCertificationStatus", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectEditCertificationStatus", "Bronze");
		if (CommonMethod.isElementsExist("AssignFilterClear", 10)) {
			CommonMethod.click("AssignFilterClear");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditDateCertified", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectEditDateCertified", "DatePickerOkButton");
		CommonMethod.scrolldowntoElement("V2ProjectEditDateCertified");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickYearIcon", 0);
		CommonMethod.click("V2ProjectClickYearIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMarkDateCertifiedTwoYearBack", 0);
		CommonMethod.click("V2ProjectMarkDateCertifiedTwoYearBack");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.click("DatePickerOkButton");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.click("DatePickerOkButton");
		 rc.saveAchievementAdminTab("V2AchievementAdminTab");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 20)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
}
	
	public void V1ProjectRecertificationIntoV2Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		V2ProjectMarkDateCertifiedTwoYearBack();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectStartRecertificationBtn", 0);
		CommonMethod.click("V2ProjectStartRecertificationBtn");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectNoBtn", 0);
		CommonMethod.click("V2ProjectNoBtn");
		CommonMethod.scrolldowntoElement("V2ProjectNoBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotContinueBtn", 0);
		CommonMethod.RobustclickElementVisible("V2PilotContinueBtn", "V2PilotYesBtn");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotYesBtn", 0);
		CommonMethod.click("V2PilotYesBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotUpdatedProjectArea", 0);
		CommonMethod.click("V2PilotUpdatedProjectArea");
		CommonMethod.clearAndSendKey("V2PilotUpdatedProjectArea","5555.00"); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommentBox", 0);
		CommonMethod.clearAndSendKey("CommentBox","Describe the change to your project area in a few sentences.");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotChangeAreaContinueBtn", 0);
		CommonMethod.click("V2PilotChangeAreaContinueBtn");    
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotUpgradeToV2Btn", 0);
		CommonMethod.click("V2PilotUpgradeToV2Btn");
		CommonMethod.scrolldowntoElement("V2PilotUpgradeToV2Btn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotUpgradeContinueToV2Btn", 0);
		CommonMethod.JavascriptClickElement("V2PilotUpgradeContinueToV2Btn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2PilotUpgradeToWellV2Btn", 0);
		CommonMethod.JavascriptClickElement("V2PilotUpgradeToWellV2Btn");
	    if(TestCaseName.contains("V1ProjectOfficeRecertificationIntoV2Project")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectRecertificationSpaceTypeDisabledOffice", 1);
		CommonMethod.assertisElementPresentTrue("V2ProjectRecertificationSpaceTypeDisabledOffice", "Recertification SpaceType: Office is enabled ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectRecertificationSpaceTypeGrayColorOffice", 1);
		CommonMethod.assertisElementPresentTrue("V2ProjectRecertificationSpaceTypeGrayColorOffice", "Recertification SpaceType: Office is not gray color ");
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectRecertificationSpaceTypeGrayColorOffice");
		CommonMethod.scrolldowntoElement("V2ProjectRecertificationSpaceTypeGrocery");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSpaceTypeContinuebtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectSpaceTypeContinuebtn");
		} 
		if(TestCaseName.contains("V1AllProjectsInRecertificationIntoV2Project")) {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationSelectSpaceTypeOffice", 0);	
		CommonMethod.ClickCheckbox("V2ProjectRecertificationSelectSpaceTypeOffice");
		CommonMethod.scrolldowntoElement("V2ProjectRecertificationSpaceTypeGrocery");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSpaceTypeContinuebtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectSpaceTypeContinuebtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationSelectPrimarySpaceTypeOffice", 0);	
		CommonMethod.ClickCheckbox("V2ProjectRecertificationSelectPrimarySpaceTypeOffice");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationPrimarySpaceTypeContinueButton", 0);	
		CommonMethod.JavascriptClickElement("V2ProjectRecertificationPrimarySpaceTypeContinueButton");	
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectwellCertificationForRecertification", 0);
		CommonMethod.JavascriptClickElement("V2ProjectwellCertificationForRecertification");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSelectOwnershipTypeRadioBtn", 0);	
		CommonMethod.ClickCheckbox("V2ProjectSelectOwnershipTypeRadioBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertOwnershipContinuebtn", 0);
		CommonMethod.JavascriptClickElement("V2ProjectRecertOwnershipContinuebtn");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScrollElement", 0);	
		CommonMethod.scrolldowntoElement("V2ProjectScrollElement");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickHereLink", 0);	
		CommonMethod.Robustclick("V2ProjectClickHereLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationBanner", 0);
		CommonMethod.Robustclick("V2ProjectRecertificationBanner");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		validateAdminCertificationDashboard();
	}
	
	public void editAndUpdateWellStandardAsAllProjectsIn() throws IOException, InterruptedException {		
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
	    CommonMethod.JavascriptClickElement("EditTab");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V1ProjectUpdateAsAllProjectsIn", 0);
	    CommonMethod.JavascriptClickElement("V1ProjectUpdateAsAllProjectsIn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
	    CommonMethod.Robustclick("V2ProjectSaveChangesButton");
	}
	
	public void ValidateLEEDCheckboxForAllReviewPhases() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("When User clicks on ReviewTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectsubmitReview", 0);
		CommonMethod.JavascriptClickElement("V2ProjectsubmitReview"); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectReviewPhases", 0);
		List<WebElement> getAllReviewPhases = CommonMethod.findElements("V2ProjectReviewPhases");
		for(int i=0; i<getAllReviewPhases.size(); i++) {
			String getReviewPhase = getAllReviewPhases.get(i).getText();
			CommonMethod.selectdropdownVisibletext("V2ProjectReviewPhasesDropdown", getReviewPhase);
			int getLeedRadioButtonSize = CommonMethod.ElementSize("V2ProjectValidateLeedRadioButton");
			CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(getLeedRadioButtonSize), "3", "Leed Radio Button count does not matched ");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectReviewModalCloseIcon", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectReviewModalCloseIcon", "V2ProjectsubmitReview");
	}
	
	public void V2ProjectValidateLEEDCheckboxForAllReviewPhases() throws IOException, InterruptedException {
		ValidateLEEDCheckboxForAllReviewPhases();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.JavascriptClickElement("EditTab");
		testlog.info("When User clicks on EditTab"); 
		CommonMethod.scrolldowntoElement("V2ProjectEditTabLEED"); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditTabIntendLEEDCheckbox", 0);
		CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectEditTabIntendLEEDCheckbox");
	}
	
	public void V2PilotValidateLEEDCheckboxForAllReviewPhases() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.JavascriptClickElement("EditTab");
		testlog.info("When User clicks on EditTab"); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditTabLEED", 0);
		CommonMethod.ClickCheckbox("V2ProjectEditTabLEED"); 		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectEditTabIntendLEEDCheckbox");
		CommonMethod.assertisElementPresentFalse("V2ProjectEditTabIntendLEEDCheckbox", "Intend to use LEED...:Checkbox is present ");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditTabLEEDCertification", 0);
		CommonMethod.clearAndSendKey("V2ProjectEditTabLEEDCertification", "Leed Certification Id");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditTabLEEDCertificationName", 0);
		CommonMethod.clearAndSendKey("V2ProjectEditTabLEEDCertificationName", "Leed Certification Name");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
		CommonMethod.Robustclick("V2ProjectSaveChangesButton");
		ValidateLEEDCheckboxForAllReviewPhases();
	}
	

	public void ValidateDashboardTimelineModule() throws IOException, InterruptedException {
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 15)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDashboardHsrSealDate", 0);
		String getSealDate=CommonMethod.getattributeValueByTextContent("V2ProjectDashboardHsrSealDate");		
        String[] getDate=getSealDate.split("to");
        String[] splitFromDate=getDate[0].split("from");
        String actualFromDate=splitFromDate[1];
        actualFromDate=actualFromDate.replaceAll("\\s+", " ").trim();
        System.out.println("Actual From Date: " +actualFromDate);
        String actualToDate=getDate[1];
        actualToDate=actualToDate.replaceAll("\\s+", " ").trim();
        System.out.println("Actual To Date: " + actualToDate);
        int currentYear = LocalDate.now().getYear();
        String expectedCurrentYear=String.valueOf(currentYear);
        System.out.println("Expected From Date: " + expectedCurrentYear);
		CommonMethod.negativesoftassertFieldValid(actualFromDate, expectedCurrentYear, "Seal's from date does not mathed ");
		int futureYear = currentYear + 5;
	    String expectedToDate = String.valueOf(futureYear);
	    System.out.println("Expected To Date: " + expectedToDate);
	    CommonMethod.negativesoftassertFieldValid(actualToDate, expectedToDate, "Seal's to date does not mathed ");	    
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommunityDashboardTimelineDate", 0);
		List<WebElement> TimelineDates = CommonMethod.findElements("CommunityDashboardTimelineDate");
		for(int i=0; i<TimelineDates.size(); i++) {
			String getTimelineDates = TimelineDates.get(i).getText();
			System.out.println("Index: " + getTimelineDates);	
			CommonMethod.negativesoftassertFieldValid(getTimelineDates, String.valueOf(currentYear + i), "Timeline year does not mathed ");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommunityDashboardTimelineText", 0);
		List<WebElement> TimelineText = CommonMethod.findElements("CommunityDashboardTimelineText");
		for(int i=0; i<TimelineText.size(); i++) {
			String actualTimelineText = TimelineText.get(i).getText();
			System.out.println("Index: " + actualTimelineText);	
			ArrayList<String> expectedTimelineText=new ArrayList<String>();
			expectedTimelineText.add("WELL CERTIFIED");
			expectedTimelineText.add("Please submit ongoing reporting data for Features PWT, WFS, and POC.");
			expectedTimelineText.add("Please submit ongoing reporting data for Features PWT, WFS, and POC.");
			expectedTimelineText.add("Please submit ongoing reporting data for Features PWT, WFS, and POC.");
			expectedTimelineText.add("Please ensure you have the proper documentation to upload for recertification. Visit the Knowledge Base for guidance.");
			expectedTimelineText.add("Reporting & Enroll for Recertification");
			expectedTimelineText.add("Recertification Process");
			CommonMethod.negativesoftassertFieldValid(actualTimelineText, expectedTimelineText, "Timeline year wise text does not mathed ");
		}
	}
	
	public void ValidateReviewPhases() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectReviewPhases", 0);
		List<WebElement> getAllReviewPhases = CommonMethod.findElements("V2ProjectReviewPhases");
		for(int i=0; i<getAllReviewPhases.size(); i++) {
			String getReviewPhase=getAllReviewPhases.get(i).getText();
			CommonMethod.selectdropdownVisibletext("V2ProjectReviewPhasesDropdown", getReviewPhase);
			if(getReviewPhase.equalsIgnoreCase("Documentation Appeal Review") || getReviewPhase.equalsIgnoreCase("Curative Action Plan Review")
				|| getReviewPhase.equalsIgnoreCase("Performance Verification Appeal Review") || getReviewPhase.equalsIgnoreCase("Curative Action Review")) {
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("ValidReviewPhasesCheckbox");
				CommonMethod.assertisElementPresentFalse("ValidReviewPhasesCheckbox", "Checkbox is showing ");	
			} else {
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ValidReviewPhasesCheckbox", 1);
				CommonMethod.assertisElementPresentTrue("ValidReviewPhasesCheckbox", "Checkbox is not showing ");	
			}
		}
	}
	
	public void ValidatePostBilling() throws IOException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ScorecardTab", 1);
		CommonMethod.assertisElementPresentTrue("ScorecardTab", "Scorecard tab is not showing ");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectContinueCustomizing", 1);
		CommonMethod.assertisElementPresentTrue("V2ProjectContinueCustomizing", "Continue Customizing button is not showing ");
	}
	
	public void underConstructionProjectInformation() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectInformationButton", 0);
		CommonMethod.RobustclickElementVisible("ProjectInformationButton", "V2ProjectUpdateProjectsName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectInformationButton", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConstructionYes", 0);
		CommonMethod.ClickCheckbox("PortfolioLocationConstructionYes");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioLocationUnderConstructionProceed");
		CommonMethod.negativeAssertElementNotPresentFalse("PortfolioLocationUnderConstructionProceed","UnderConstruction Proceed button is present");
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstructionYes");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
		CommonMethod.Robustclick("V2ProjectSaveChangesButton", "PortfolioLocationConstructionYes");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectSaveChangesButton", 1);
		testlog.info("And User clicks on Save button");
		testlog.info("Then User will be redirected to Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "ProjectInformationButton");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectInformationButton", 0);
		CommonMethod.RobustclickElementVisible("ProjectInformationButton", "V2ProjectUpdateProjectsName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationConstructionYes", 0);
		CommonMethod.VerifyRadioOrCheckboxSelcted("PortfolioLocationConstructionYes");
	}
	
	public void getProjectIdAndSetInIdColumnInExcel(String SheetName, int rowNum) {

		String getProjectID = data.getCellData(SheetName, "ProjectID", rowNum);
		String splitId = getProjectID.substring(4, 9);
		data.setCellData(SheetName, "ID", 2, splitId);
		System.out.println(splitId);
	}
	
	public void ValidateDocsStatusInEquityRatingTabOfDocumentsTab(String DocumentStatusLocator, String expectedDocsStatus) throws IOException, InterruptedException {		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(DocumentStatusLocator, 0);
		String actualDocsStatus=CommonMethod.getattributeValueByTextContent(DocumentStatusLocator);
		actualDocsStatus=actualDocsStatus.replaceAll("\\s+", " ").trim();
		System.out.println(actualDocsStatus);
		CommonMethod.negativesoftassertFieldValid(actualDocsStatus, expectedDocsStatus, "Docs Status does not matched ");
	}
	
	public void ClickEquityRatingTabInDocumentsTab() throws IOException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioOptScorecardWer", 0);
		CommonMethod.JavascriptClickElement("PortfolioOptScorecardWer");
		CommonMethod.scrolldowntoElement("PortfolioOptScorecardWer");	
	}
	
	public void EquityValidateDocumentsTabBeforeRenewal() throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERValidateAttentionNeededBannerLink", 0);
	CommonMethod.JavascriptClickElement("WERValidateAttentionNeededBannerLink");
	CommonMethod.switchToChildTab();
	String actualURL = CommonMethod.getCurrentUrl();
	CommonMethod.negativesoftassertFieldValid(actualURL, "renew-or-replace", "URL does not matched ");
	CommonMethod.switchToParentTab();
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERValidateAttentionNeededInDocumentsTab", 0);
	String actualAttentionNeededText=CommonMethod.getattributeValueByTextContent("WERValidateAttentionNeededInDocumentsTab");
	actualAttentionNeededText=actualAttentionNeededText.replaceAll("\\s+", " ").trim();
	CommonMethod.negativesoftassertFieldValid(actualAttentionNeededText, "Attention needed", "WELL Equity Rating :  Attention needed text does not matched ");
	}	
	
	public void EquityValidateDocumentsListDataBeforeRenewal() throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WERValidateDocsValidity", 1);
	CommonMethod.assertisElementPresentTrue("WERValidateDocsValidity", "Documents Validity is not showing ");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2RenewalButton", 1);
	CommonMethod.assertisElementPresentTrue("V2RenewalButton", "Renew now button is not showing ");
	CommonMethod.moveToElement("V2RenewalButton");	
	rc.RemoveSpaceAndValidate("WERValidateTooltipBeforeRenewal", "Unlock this action by enrolling into renewal");	
	performance.ValidateLockScreenBeforeRenewal();
	}
	
	public void ValidateEquityTabBeforeRenewal() throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EquityTab", 0);
	CommonMethod.RobustclickElementVisible("EquityTab", "V2RenewalButton");		
	rc.RemoveSpaceAndValidate("WERValidateAttentionNeededInDocumentsTab", "Attention needed");		
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WERValidateDocsValidity", 1);
	CommonMethod.assertisElementPresentTrue("WERValidateDocsValidity", "Documents Validity is not showing ");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2RenewalButton", 1);
	CommonMethod.assertisElementPresentTrue("V2RenewalButton", "Renew now button is not showing ");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WERSealImgValidate",1);
	CommonMethod.negativeAssertElementPresentTrue("WERSealImgValidate","Seal is not present "); 
	rc.RemoveSpaceAndValidate("RenewalWprValidateHeaderOnDashboard", "Congratulations on earning the WELL Equity seal");
	rc.RemoveSpaceAndValidate("WERValidateAccessYourScorecardBtn", "Access your Scorecard");	
	rc.RemoveSpaceAndValidate("WERValidateAccessYourWellEquityScorecardBtn", "Access your Scorecard");
	}
	
 public void ValidateEquityScorecardTabBeforeRenewal() throws IOException, InterruptedException {
	if (CommonMethod.isElementsExist("ScorecardBannerClose", 20)) {
	    CommonMethod.Robustclick("ScorecardBannerClose");
	}
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERValidateAccessYourScorecardBtn", 0);
	CommonMethod.Robustclick("WERValidateAccessYourScorecardBtn");
	if (CommonMethod.isElementsExist("ScorecardBannerClose", 20)) {
	    CommonMethod.Robustclick("ScorecardBannerClose");
	}
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERValidateWaningIndicator", 0);
	int actualWarningIndicatorCount = CommonMethod.ElementSize("WERValidateWaningIndicator");
	CommonMethod.negativesoftassertFieldValid(String.valueOf(actualWarningIndicatorCount), "32", "Warning indicator in equity scorecard tab is showing wrong ");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERDisabledYesBtnValidate", 0);
	int actualDisabledYesBtnCount = CommonMethod.ElementSize("WERDisabledYesBtnValidate");
	CommonMethod.negativesoftassertFieldValid(String.valueOf(actualDisabledYesBtnCount), "71", "In equity scorecard tab disabled Yes Button Count is showing wrong ");
	CommonMethod.moveToElement("WERScorecardEE1feature");
	rc.RemoveSpaceAndValidate("WERScorecardEE1YesBtnTooltip", "Unlock this action by enrolling into renewal");	
 }
 
	public void ValidateScorecardLockScreenBeforeRenewal(String featureName, String SheetName, int rowNum, String ProjectType, String Commodity, String FileName, boolean PartNameRequired,
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
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRDisabledUploadButton", 1);
			CommonMethod.negativeAssertElementPresentTrue("WPRDisabledUploadButton", "Upload Button is enabled ");			
			performance.ValidateLockScreenBeforeRenewal();
			v2project.ValidateDocsStatusInEquityRatingTabOfDocumentsTab("WERTableDocumentsListStatus","Expiring Soon");
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WERValidateDocsValidity", 1);
			CommonMethod.assertisElementPresentTrue("WERValidateDocsValidity", "Documents Validity is not showing ");
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2RenewalButton", 1);
			CommonMethod.assertisElementPresentTrue("V2RenewalButton", "Renew now button is not showing ");
			CommonMethod.JavascriptClickElement(ele);
		break;
		}
	}
	CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
		
	}
	
	 public void Renewal() throws IOException, InterruptedException {
	  testlog.info("Given User is on Dashboard page");
	  CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2RenewalButton", 0);
	  CommonMethod.RobustclickElementVisible("V2RenewalButton", "Acknowledge");
	  CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Acknowledge", 0);
	  CommonMethod.ClickCheckbox("Acknowledge");
	  CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrProceedbtn", 0);
	  CommonMethod.RobustclickElementVisible("V2ProjectHsrProceedbtn", "V2ProjectOptConfirmNoEnrollSelect");
	  CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptConfirmNoEnrollSelect", 0);
	  CommonMethod.selectdropdownVisibletext("V2ProjectOptConfirmNoEnrollSelect",
					"No, we will initiate a separate review cycle.");
	  CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrProceedbtn", 0);
	  CommonMethod.JavascriptClickElement("V2ProjectHsrProceedbtn");
	  CommonMethod.refreshBrowser();
	 }
	 
	 public void ClickWellEquityTabAndAccessScorecard() throws IOException, InterruptedException {		  
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EquityTab", 0);
		 CommonMethod.RobustclickElementVisible("EquityTab", "RenewalAccessScorecardButton");
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalAccessScorecardButton", 0);
		 CommonMethod.Robustclick("RenewalAccessScorecardButton");
		 rc.ScorecardLoading();
	 }	
	 
	 public void EquityValidateScorecardLockScreenPostRenewal(String featureName, String SheetName, int rowNum, String ProjectType, String Commodity, String FileName, boolean PartNameRequired,
				boolean VerificationMethodValidationRequired, boolean NoteRequired, boolean IntentCheckboxRequired) throws Exception {
		rc.RemoveSpaceAndValidate("WERValidateAttentionNeededInDocumentsTab", "Attention needed");	
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
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledUploadButton");
				CommonMethod.assertisElementPresentFalse("WPRDisabledUploadButton", "Upload Button is disabled ");
				v2project.ValidateDocsStatusInEquityRatingTabOfDocumentsTab("WERTableDocumentsListStatus","Expiring Soon");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WERValidateDocsValidity", 1);
				CommonMethod.assertisElementPresentTrue("WERValidateDocsValidity", "Documents Validity is not showing ");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2RenewalButton", 1);
				CommonMethod.assertisElementPresentTrue("V2RenewalButton", "Renew now button is not showing ");				
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledDocumentListEditButton");
				CommonMethod.assertisElementPresentFalse("WPRDisabledDocumentListEditButton", "Document List Edit Button is disabled ");						
				CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledDocumentListReplaceButton");
				CommonMethod.assertisElementPresentFalse("WPRDisabledDocumentListReplaceButton", "Document List Replace Button is disabled ");			
				CommonMethod.JavascriptClickElement(ele);
			break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
			
		}
	 
		public void ValidateEquityRatingListInDocumentsTabPostRenewal() throws IOException, InterruptedException {
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WERValidateDocsValidity", 1);
			CommonMethod.assertisElementPresentTrue("WERValidateDocsValidity", "Documents Validity is not showing ");
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2RenewalButton", 1);
			CommonMethod.assertisElementPresentTrue("V2RenewalButton", "Renew now button is not showing ");	
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledDocumentListEditButton");
			CommonMethod.assertisElementPresentFalse("WPRDisabledDocumentListEditButton", "Document List Edit Button is disabled ");						
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRDisabledDocumentListReplaceButton");
			CommonMethod.assertisElementPresentFalse("WPRDisabledDocumentListReplaceButton", "Document List Replace Button is disabled ");	
		}
		
		public void uploadDocumentInV2ProjectOptn(String FeatureName,String VerificationMethod, String FileName) throws IOException, InterruptedException {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectRatingFeatureName", 0);
			List<WebElement> Feature = CommonMethod.findElements("V2projectRatingFeatureName");
			testlog.info("Fetching total no. of credits on page");
			boolean flag = false;
			for (WebElement ele : Feature) {
				String Creditname = ele.getText();
				Creditname = Creditname.replaceAll("\\.", "");
				if (Creditname.equalsIgnoreCase(FeatureName)) {
					flag = true;
					CommonMethod.scrolldowntoElement("ApplicableVersion");
					CommonMethod.JavascriptClickElement(ele);
					testlog.info("When User clicks on Feature");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
				CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPDocIcon", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectWPRPDocIcon", "V2ProjectDocUpload");
				CommonMethod.uploadFile("V2ProjectDocUpload", FileName, "UploadFileVerifyScorecard");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadRemovelink", 0);
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect1", 0);
					CommonMethod.click("Multiselect1");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(VerificationMethod, 0);
					CommonMethod.click(VerificationMethod);
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
				if (CommonMethod.isElementsExist("V2ProjectScorecardVerificationMethodSelectAddPart", 5)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
							"V2ProjectScorecardVerificationMethodSelectAddPart", 0);
					CommonMethod.Robustclick("V2ProjectScorecardVerificationMethodSelectAddPart",
							"PortfolioScoreCardVerificationSelectFeature");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRUploadbtn1", 0);
				CommonMethod.Robustclick("V2ProjectWPRUploadbtn1");
				if (CommonMethod.isElementsExist("DocumentAddedToasterMessage", 5)) {
					CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DocumentAddedToasterMessage", 1);
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTableTr, 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRScorecardLanding", 0);
				CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
				CommonMethod.JavascriptClickElement(ele);
				}
		}
	CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		}	
	
		public void V2ProjectDecumentSearchBox(String documentName) throws IOException, InterruptedException {
			
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioOptScorecardWer", 0);
			CommonMethod.RobustclickElementVisible("PortfolioOptScorecardWer", "ExpiringAttentionValid");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringAttentionValid", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDecumentSearchBox", 0);
			CommonMethod.clearAndSendKey("V2ProjectDecumentSearchBox", documentName);
			testlog.info("And User enter data to SearchBox field");
			CommonMethod.JavascriptClickElement("PortfolioCreateAccountSubmit");
			testlog.info("And User clicks on Search Button");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTableTr, 0);
		}
		
		 public void ValidRenewExpireInDocumentLibrary() throws IOException, InterruptedException {
		    	
		    	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ExpiringLOAEnabled",1);
		    	CommonMethod.negativeAssertElementPresentTrue("ExpiringLOAEnabled","Enabled Renew button is not present");
		    	CommonMethod.RobustclickElementVisible("ExpiringLOAEnabled", "ExpiringLOAStatus");
		    	performance.ValidRenewExpire();
		    	testlog.pass("**Verifies the Validate the Renew page from Document list successful**");
		    }
		 
		 public void ExpiringAttentionLink() throws IOException, InterruptedException {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringAttentionLink", 0);
		   	CommonMethod.JavascriptClickElement("ExpiringAttentionValid");
		   	if (TestCaseName.contains("AttentionBannerInScorecardV2")) {
		   		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ExpiringLabelInScorecard",1);
				CommonMethod.negativeAssertElementPresentTrue("ExpiringLabelInScorecard","Expire label is not present");
		   	}
		   	else {
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ExpiringV2RenewReplace",1);
			CommonMethod.negativeAssertElementPresentTrue("ExpiringV2RenewReplace","Expire label is not present");
		   	}
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringAttentionLink", 0);
			CommonMethod.JavascriptClickElement("ExpiringAttentionLink");
}
		 
			public void validateRenewInScorecard(String FeatureName,String VerificationMethod) throws IOException, InterruptedException {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardFeature", 0);
				List<WebElement> Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
				testlog.info("Fetching total no. of credits on page");
				boolean flag = false;
				for (WebElement ele : Feature) {
					String Creditname = ele.getText();
					Creditname = Creditname.replaceAll("\\.", "");
					if (Creditname.equalsIgnoreCase(FeatureName)) {
						flag = true;
						CommonMethod.scrolldowntoElement("ApplicableVersion");
						CommonMethod.JavascriptClickElement(ele);
						testlog.info("When User clicks on Feature");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
					CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTableTr, 0);
					if (VerificationMethod.contains("Ongoing")) { 
					performance.ExpiringOngoingDisabledToaster();
					}
					if (VerificationMethod.contains("LOA")) { 
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ExpiringV2RenewButtonScorecard", 0);
						CommonMethod.RobustclickElementVisible("ExpiringV2RenewButtonScorecard", "ExpiringLOAStatus");
				    	performance.ValidRenewExpire();
				    	testlog.pass("**Verifies the Validate the Renew page from Document list successful**");
				 
						}
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRScorecardLanding", 0);
					CommonMethod.scrolldowntoElement("V2ProjectWPRScorecardLanding");
					CommonMethod.JavascriptClickElement(ele);
					}
			}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
			}	
		
			public void removeRenewbtnByArchieve() throws IOException, InterruptedException {
			int renewBtn;
			Boolean flag = true;
			do {
				long startTime = System.currentTimeMillis();
				if (!((System.currentTimeMillis() - startTime) < 60000)) {
					flag = false;
					System.out.println("Build Stuck");
					break;
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
				testlog.info("And User will be redirected to Document Upload Table page");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);	
				CommonMethod.moveToElement("PortfolioAndRatingDeleteEditMenu");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardArchiveMenuBtn", 0);
				CommonMethod.JavascriptClickElement("PortfolioScorecardArchiveMenuBtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardArchiveBtn", 0);
				CommonMethod.JavascriptClickElement("PortfolioScorecardArchiveBtn");
				CommonMethod.refreshBrowser();
			   v2project.ClickEquityRatingTabInDocumentsTab();
			   renewBtn = CommonMethod.ElementSize("ExpiringV2RenewButtonScorecard");	
			} while (!(renewBtn < 1));
			CommonMethod.assertTruebooleanCondition(flag, "Renew button is visible");
			}	
		
			public void clickScorecardTabInDocumentLibrary() throws IOException, InterruptedException {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardList", 0);
				CommonMethod.JavascriptClickElement("V2ProjectScorecardList");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTable", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
				CommonMethod.scrolldowntoElement("PortfolioAndRatingLocAccDocumentTable");
			}
			
			public void V1ProjectUploadLegalAndGeneralDocumentFromDocumentLibrary(String SheetName, int rowNum, String Feature, 
			String DocumentType, String FileName, String ValidateFeature) throws IOException, InterruptedException {
				testlog.info("Given User is on Dashboard page");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2ProjectDocumentTab", 0);
				CommonMethod.RobustclickElementVisible("WellV2ProjectDocumentTab", "Uploadbutton");
				testlog.info("When User clicks on DocumentTab");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.RobustclickElementVisible("Uploadbutton", "V2ProjectPortfolioDocType");
				testlog.info("And User clicks on Upload button");				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
				CommonMethod.RobustclickElementVisible("OwnerOrgClick", "OwnerOrg");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrg", 0);
				CommonMethod.sendKeys("OwnerOrg", Feature);
				Thread.sleep(3000);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectOwnerOrgDyn", 0);
				CommonMethod.JavascriptClickElement("V2ProjectClickGeneralFeature");				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPortfolioDocType", 0);
				CommonMethod.selectdropdownVisibletext("V2ProjectPortfolioDocType", DocumentType);
				testlog.info("And User select Document Type");
				CommonMethod.uploadFile("V2Projectscorecarddocupload", FileName, "UploadFileVerifyScorecard");
				testlog.info("And User upload Document");				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDocSubmit", 0);
				CommonMethod.RobustclickElementVisible("V2ProjectDocSubmit", "table");
				testlog.info("And User clicks on Submit button");
				Thread.sleep(2000);				
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("table", 0);
                List<String> val = CommonMethod.fetchTableData("table");
                testlog.info("Fetching Data from Upload Table");
                CommonMethod.negativesoftassertFieldValid(val.get(1), ValidateFeature, "Table data doesn't match");
			}
			
			public void V1ProjectDeleteDocumentsTableList() throws IOException, InterruptedException {
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ResetButton", 0);
				CommonMethod.scrolldowntoElement("ResetButton");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DeleteDocumentsTableList", 0);
				CommonMethod.RobustclickElementVisible("DeleteDocumentsTableList", "DeleteThisDocumentBtn");
                CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DeleteThisDocumentBtn", 0);
                CommonMethod.JavascriptClickElement("DeleteThisDocumentBtn");
                CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioV2ProjectDocumentScorecardDeleteToasterMessage", 1);
                System.out.println("********Document deleted successfully*********");
			}
			

			@SuppressWarnings({ "unchecked", "static-access" })
			public void SignAgreementMainlandChinaV2project(String SheetName, int rowNum) throws IOException, InterruptedException {
				String projectId = data.getCellData(SheetName, "ProjectID", rowNum);
				String[] Id = projectId.split("22024");
				System.out.println("Id: " + Id[1]);
				String header = portfolio.PostRequestAuthenticate("UserName", 10);
				testlog.info("Given User set POST service api endpoint");
				testlog.info("When User set Request header and Payload");
				testlog.info("And User Send a POST HTTP request");
				testlog.info("Header Token: " + header);
				@SuppressWarnings("rawtypes")
				HashMap pathprms = new HashMap();
				pathprms.put("project_id", Id[1]);
				Response res = given().log().all().accept("application/json").contentType("application/json")
						.header("Authorization", header).pathParams(pathprms).when().post("project/{project_id}/aggrementSign");
				int StatusCode = res.getStatusCode();
				Assert.assertEquals(StatusCode, 200, "Verifying status code");
				testlog.info("Then User verifies response body");
				testlog.info("And User verifies Status Code");
				testlog.info("And User Complete Sign project agreement");
				CommonMethod.refreshBrowser();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
				testlog.pass("**Verifies project agreement sign successfully**");
			}
			
	public void ValidateMainlandChinaBannerText() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MainlandChinaBannerText", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("MainlandChinaBannerText"),
				"You now have two portals for your WELL journey", "MainlandChinaBannerText doesn't Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("MainlandChinaBannerText"),
				"Certification process for Mainland China projects", "MainlandChinaBannerText process doesn't Mismatch");		
	}
	
	public void ValidateMainlandChinaAfterCertBannerText() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MainlandChinaBannerText", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("MainlandChinaBannerText"),
				"all Mainland China projects are required", "MainlandChinaBannerText doesn't Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("MainlandChinaBannerText"),
				"enroll through the qualified third-party WELL Verifier in China", "MainlandChinaBannerText process doesn't Mismatch");		
	}
			
	public void ValidateMainlandChinaProjectWithTeamMemberRole(String SheetName,int rowNum) throws IOException, InterruptedException {
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 10)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectContinueCustomizing");
		CommonMethod.assertisElementPresentFalse("V2ProjectContinueCustomizing", "Continue Customizing button is present");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectRecertificationSubmitForReviewBtn");
		CommonMethod.assertisElementPresentFalse("V2ProjectRecertificationSubmitForReviewBtn", "Submit for Review button is present");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectDashboardLearnMoreButton1");
		CommonMethod.assertisElementPresentFalse("V2ProjectDashboardLearnMoreButton1", "Learn More button is present");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectLeedOptinButton");
		CommonMethod.assertisElementPresentFalse("V2ProjectLeedOptinButton", "Opt-in button is present");		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("ScorecardTab");
		CommonMethod.assertisElementPresentFalse("ScorecardTab", "Scorecard Tab is present");		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("DocumentLibraryTab");
		CommonMethod.assertisElementPresentFalse("DocumentLibraryTab", "Documents Tab is present");		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("ReviewTab");
		CommonMethod.assertisElementPresentFalse("ReviewTab", "Reviews Tab is present");		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("HealthSafetyTab");
		CommonMethod.assertisElementPresentFalse("HealthSafetyTab", "WELL Health-Safety Tab is present");		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PerformanceTab");
		CommonMethod.assertisElementPresentFalse("PerformanceTab", "WELL Performance Tab is present");		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("EquityTab");
		CommonMethod.assertisElementPresentFalse("EquityTab", "WELL Equity Tab is present");		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("EnrollTab");
		CommonMethod.assertisElementPresentFalse("EnrollTab", "Enroll Tab is present");
		if(SheetName.equalsIgnoreCase("V2Project")) {
		String getCurrentURL = CommonMethod.getCurrentUrl();
		String modifiedURL = getCurrentURL.replace("dashboard", "scorecard");
		System.out.println(modifiedURL);
		driver.get(modifiedURL);
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 40)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		rc.RemoveSpaceAndValidate("V2ProjectDashboardNextStep", "Next Steps");
		String actualCurrentURL = CommonMethod.getCurrentUrl();
		System.out.println(actualCurrentURL);
		CommonMethod.negativesoftassertFieldValid(actualCurrentURL, "dashboard", "It is not landed on Dashboard ");
		}
	}

			public void EditUnderConstructionStatusAsYes(String SheetName, int rowNum) throws IOException, InterruptedException {
			  CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);	
			  CommonMethod.RobustclickElementVisible("EditTab", "ProjectInformationButton");
			  CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectUpdatesLocationcurrently");
			  CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectOptnYesUnderConstruction", 0);	
			  CommonMethod.ClickCheckbox("V2ProjectOptnYesUnderConstruction");
			  CommonMethod.VerifyRadioOrCheckboxSelcted("V2ProjectOptnYesUnderConstruction");
			  CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
			  CommonMethod.Robustclick("V2ProjectSaveChangesButton");
			  CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("V2ProjectSaveChangesButton", 1);
			}	
			
			public void ValidateUpdateForProjectsWithLocationsInChinaModalDuringRegistration(String SheetName, int rowNum, String Type, String Country, String ProjectName)
					throws IOException, InterruptedException {
				testlog.info("Given User logged in to the WELL certified account");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
				CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLCertificationNavBar");
				CommonMethod.RobustclickElementVisible("WELLCertificationNavBar", "V2Projectstartav2projectbtn");
				testlog.info("When User clicks on WELL Certification from top menu under Projects");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectstartav2projectbtn", 0);
				CommonMethod.Robustclick("V2Projectstartav2projectbtn");
				testlog.info("When User clicks on Startv2project button");
				CommonMethod.Robustclick("V2ProjectstartProjectcontinuebtn");
				testlog.info("And User clicks on continue button");		
				CommonMethod.RobustclickElementVisible("V2ProjectnicknameContinuebtn", "MandatoryFieldErrorMessage");
				String AccountName = ProjectName + CommonMethod.randomNumber(8000000);
				testlog.info("V2 ProjectName is: " + AccountName);
				data.setCellData(SheetName, "ProjectName", rowNum, AccountName);
				CommonMethod.sendKeys("V2Projectprojectnickname", AccountName);
				testlog.info("And User enter data to project nickname");
				CommonMethod.RobustclickElementVisible("V2ProjectnicknameContinuebtn", "V2ProjectlocationContinuebtn");
				testlog.info("And User clicks on continue button");
				CommonMethod.RobustclickElementVisible("V2ProjectlocationContinuebtn", "StreetName");
				CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
				String ProjectAddress = USfaker.address().streetAddress();
				String ProjectCity = USfaker.address().cityName();
				String PostalCode = USfaker.address().zipCode();
				testlog.info("Street: " + ProjectAddress);
				testlog.info("City: " + ProjectCity);
				testlog.info("Postalcode: " + PostalCode);
				CommonMethod.sendKeys("StreetName", ProjectAddress);
				data.setCellData(SheetName, "Street", rowNum, CommonMethod.getattributeValue("StreetName"));
				CommonMethod.sendKeys("CityName", ProjectCity);
				data.setCellData(SheetName, "City", rowNum, CommonMethod.getattributeValue("CityName"));
				CommonMethod.sendKeys("PostalCode", PostalCode);
				data.setCellData(SheetName, "PostalCode", rowNum,
						CommonMethod.getattributeValue("PostalCode"));
				if(Country.equalsIgnoreCase("MainlandChina")) {
					CommonMethod.selectdropdownValue("CountryDropdown", "CN");
					data.setCellData(SheetName, "Country", rowNum, CommonMethod.getSelectedDropdownValue("CountryDropdown"));
					CommonMethod.selectdropdownVisibletext("ProjectlocationState","Beijing");
					data.setCellData(SheetName, "State", rowNum, CommonMethod.getSelectedDropdownValue("ProjectlocationState"));
					testlog.info("Country: " + data.getCellData(SheetName, "Country", rowNum));
					testlog.info("State: " + data.getCellData(SheetName, "State", rowNum));
				}
				testlog.info(
						"And User enter data to First name, Last name, Country, State, Street address, City and Postal Code, Phone number fields");
				CommonMethod.RobustclickElementVisible("V2ProjectlocationContinuebtn", "V2ProjectGetUpdatePojectText");
				testlog.info("And User clicks on continue button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectGetUpdatePojectText", 0);
				rc.RemoveSpaceAndValidate("V2ProjectGetUpdatePojectText", "Update for projects with locations in China");
			}
			
			public void V1ProjectAdminUserMarkCertification(String SheetName,int rowNum, String CertificationStatus, 
					String CertificationDate, String CertificationName, String SaveBtnLocator) throws IOException, InterruptedException {
		    	testlog.info("Given User is on Dashboard page");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
			    CommonMethod.click("EditTab");
				testlog.info("When User clicks on EditTab");
				rc.navigateAchievementAdminTab();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PrecertificationAchievementAdminTab", 0);
				CommonMethod.scrolldowntoElement("PrecertificationAchievementAdminTab");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(CertificationStatus, 0);
				CommonMethod.selectdropdownVisibletext(CertificationStatus, CertificationName);
				if (CommonMethod.isElementsExist("AssignFilterClear", 10)) {
					CommonMethod.click("AssignFilterClear");
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(CertificationDate, 0);
				CommonMethod.RobustclickElementVisible(CertificationDate, "DatePickerOkButton");
				CommonMethod.scrolldowntoElement(CertificationDate);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickYearIcon", 0);
				CommonMethod.click("V2ProjectClickYearIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMarkDateCertifiedFourYearBack", 0);
				CommonMethod.click("V2ProjectMarkDateCertifiedFourYearBack");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
				CommonMethod.click("DatePickerOkButton");	
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
				CommonMethod.click("DatePickerOkButton");
		    	rc.saveAchievementAdminTab(SaveBtnLocator);
				if (CommonMethod.isElementsExist("ScorecardBannerClose", 10)) {
					CommonMethod.Robustclick("ScorecardBannerClose");
			    }
			}
			
			public void V1ProjectValidateSealAndDate()throws IOException, InterruptedException {
				testlog.info("Given User is on Dashboard page");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
			CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "AchievementEquitySeal");	
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AchievementEquitySeal", 0);
			CommonMethod.assertisElementPresentTrue("AchievementEquitySeal", "Equity Seal Image is not visble");			
			}
			
			public void uploadDocumentInOptnFeature(String featureName, String SheetName, int rowNum, String Commodity, String FileName) throws Exception {
				rc.ScorecardLoading();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("NavBarHide", 0);
				CommonMethod.hideElement("NavBarHide");
				CommonMethod.hideElement("NavBarDownHide");
				boolean flag = false;
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPFeature", 0);
				List<WebElement> Feature = CommonMethod.findElements("V2ProjectWPRPFeature");
				for (WebElement f : Feature) {
					String Creditname = f.getText();
					Creditname = Creditname.replaceAll("\\.", "");
					if (Creditname.equalsIgnoreCase(featureName)) {
						flag = true;
						CommonMethod.WaitUntilClickble(f, 120);
						CommonMethod.JavascriptClickElement(f);
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage1", 0);
						CommonMethod.scrolldowntoElement("PortfolioScorecardlocAccountValidMessage1");
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPDocIcon", 0);
						CommonMethod.RobustclickElementVisible("V2ProjectWPRPDocIcon", "V2ProjectDocUpload");
						CommonMethod.uploadFile("V2ProjectDocUpload", FileName, "UploadFileVerifyScorecard");
						if (!CommonMethod.isElementsExist("V2ProjectScorecardSelectedDocTypeValid", 5)) {
							CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Multiselect1", 0);
							CommonMethod.click("Multiselect1");
							CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectOwnerOrgDyn", 0);
							List<WebElement> ele = CommonMethod.findElements("SelectOwnerOrgDyn");
							ele.get(0).click();
						}
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationSelectFeature", 0);
						CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature", "SC2");
						testlog.info("And User select SC2 Partname");
						if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
							CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
									"PortfolioScoreCardVerificationSelectSpaceType", 0);
							CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
							testlog.info("And User Select SpaceType");
						}
						if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
							CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
									"PortfolioScoreCardVerificationSelectOption", 0);
							CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
							testlog.info("And User Select Option");
						}
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPartbtn", 0);
						CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPartbtn",
								"UploadFileVerifyScorecard");
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UploadFileVerifyScorecard", 0);
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddNote", 0);
						CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationAddNote");
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EnabledUploadbtn", 0);
						CommonMethod.Robustclick("EnabledUploadbtn");
						CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("EnabledUploadbtn", 1);
						rc.uploadDocumentToastMessage();
						testlog.info("And User will be redirected to Document Upload Table page");
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
						CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardlocAccountValidMessage", 0);
						CommonMethod.JavascriptClickElement(f);
						break;
					}
				}
					CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +featureName +" doesn't match");
					testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
			}
			
	public void ValidateDisabledSpaceTypeInEditTab(String SheetName, int rowNum) throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
	CommonMethod.RobustclickElementVisible("EditTab", "ProjectInformationButton");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectRecertificationSpaceTypeDisabledOffice",1);
	CommonMethod.assertisElementPresentTrue("V2ProjectRecertificationSpaceTypeDisabledOffice", "Space Type is enabled");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectEditTabDisabledTooltip",1);
	CommonMethod.assertisElementPresentTrue("V2ProjectEditTabDisabledTooltip", "Space Type is enabled");
	}	
	
	public void ValidateEnabledSpaceTypeInEditTab(String SheetName, int rowNum) throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
	CommonMethod.RobustclickElementVisible("EditTab", "ProjectInformationButton");	
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectEnabledSpaceTypeOffice",1);
	CommonMethod.assertisElementPresentTrue("V2ProjectEnabledSpaceTypeOffice", "Space Type: Office is disabled");
	}
	
	public void ValidateUnderConstructionAsYesReviewWarning(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "V2ProjectsubmitReview");
		testlog.info("When User clicks on Review tab");
		CommonMethod.RobustclickElementVisible("V2ProjectsubmitReview", "V2ProjectcommentReview");
		testlog.info("And User clicks on Submit DocReview button");
		CommonMethod.WaitUntilClickble("V2ProjectcommentReview", 60).sendKeys("Final Documentation Review");
		testlog.info("And User enter data to commentReview");
		CommonMethod.selectdropdownVisibletext("V2ProjectSelectPhase", "Final Documentation Review");
		testlog.info("And User select the phase");
		CommonMethod.ClickCheckbox("V2ProjectsubmittingHsrcbx");
		testlog.info("And User checks the submit hsr checkbox");		
		rc.RemoveSpaceAndValidate("V2ProjectReviewWarning", "Project needs to be construction completed in order to pursue rating achievements");
		CommonMethod.refreshBrowser();
	}
	
	public void ValidateDocumentsText(String SheetName, int rowNum) throws IOException, InterruptedException {
		rc.RemoveSpaceAndValidate("V2ProjectDocumentsTextOne", "A completed copy of the");
		rc.RemoveSpaceAndValidate("V2ProjectDocumentsTextTwo", "Representative floor plans for the entire building");
		rc.RemoveSpaceAndValidate("V2ProjectDocumentsTextThree", "Photos, renderings, or drawings");
		rc.RemoveSpaceAndValidate("V2ProjectDocumentsTextFour", "Sections/elevations");
		rc.RemoveSpaceAndValidate("V2ProjectDocumentsTextFive", "Mechanical schedules, plans and/or drawings");
		if(TestCaseName.equalsIgnoreCase("V1_TC_38_02_ValidateDocumentsText")) {
			rc.RemoveSpaceAndValidate("V2ProjectDocumentsTextSix", "A completed project Scorecard or other document confirming which WELL Features are being attempted");	
		}
	}
	
	public void AddGeneralObservation(String SheetName, int rowNum) throws IOException, InterruptedException {
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 20)) {
		CommonMethod.Robustclick("ScorecardBannerClose");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrScorecardGeneralObservationBtn", 0);
		CommonMethod.RobustclickElementVisible("HsrScorecardGeneralObservationBtn", "HsrScorecardGeneralObservationTextBox");
		CommonMethod.sendKeys("HsrScorecardGeneralObservationTextBox", "HSR General Observation Text");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectscorecarddocuploadsubmit", 0);
		CommonMethod.JavascriptClickElement("V2Projectscorecarddocuploadsubmit");
		}
	
	public void ValidateGeneralObservationInReturnReview(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("HsrReviewGeneralObservationField",1);
		CommonMethod.assertisElementPresentTrue("HsrReviewGeneralObservationField", "General Observation is not showing ");
		rc.RemoveSpaceAndValidate("ValidateHsrReviewGeneralObservation", "HSR General Observation Text");
	}
	
	public void ValidateGeneralObservationInEditReview(String SheetName, int rowNum) throws IOException, InterruptedException {
		ReviewAdminActionsButton();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRReviewEditButton",0);
		CommonMethod.RobustclickElementVisible("WPRReviewEditButton", "HsrReviewGeneralObservationField");	
		ValidateGeneralObservationInReturnReview(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrReviewCloseModal", 0);
		CommonMethod.Robustclick("HsrReviewCloseModal");
	}
	
	public void ValidateOptimizationDisabledTargetPointDuringUnderReview(String FeatureName,String SheetName,int rowNum,String Commodity,Boolean PartNameRequired, Boolean VerificationMethodValidationRequired, Boolean NoteRequired,  Boolean IntentCheckboxRequired) throws Exception {
		testlog.info("Given User is on Scorecard page");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 10);
		List<WebElement> Feature;
		boolean flag = false;
		Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				System.out.println("FeatureName: " + FeatureName);
				CommonMethod.WaitUntilClickble(ele, 120);
				CommonMethod.JavascriptClickElement(ele);
				flag = true;
				CommonMethod.scrolldowntoElement("V2ProjectScorecardAndBtnScroll");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardDisabledTargetDropdown",1);
				CommonMethod.assertisElementPresentTrue("V2ProjectScorecardDisabledTargetDropdown", "TargetDropdown is not disabled");				
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");	
	}
	
	public void ValidatePreconditionDisabledTargetPointDuringUnderReview(String FeatureName,String SheetName,int rowNum,String Commodity,Boolean PartNameRequired, Boolean VerificationMethodValidationRequired, Boolean NoteRequired,  Boolean IntentCheckboxRequired) throws Exception {
		testlog.info("Given User is on Scorecard page");
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardPartCount", 10);
		List<WebElement> Feature;
		boolean flag = false;
		Feature = CommonMethod.findElements("V2ProjectScorecardPartCount");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				System.out.println("FeatureName: " + FeatureName);
				CommonMethod.WaitUntilClickble(ele, 120);
				CommonMethod.JavascriptClickElement(ele);
				flag = true;
				CommonMethod.scrolldowntoElement("V2ProjectScorecardAndBtnScroll");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectScorecardDisabledTargetDropdown",1);
				CommonMethod.assertisElementPresentTrue("V2ProjectScorecardDisabledTargetDropdown", "TargetDropdown is not disabled");				
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " +FeatureName +" doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");	
	}
	
	public void ValidateEquityRatingInDocumentsTabV1Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioOptScorecardWer", 0);
		CommonMethod.JavascriptClickElement("PortfolioOptScorecardWer");		
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioV2ProjectDocumentScorecardTable",1);
		CommonMethod.assertisElementPresentTrue("PortfolioV2ProjectDocumentScorecardTable", "Equity Rating table is not showing ");
	}
	
	public void ValidateHealthSafetyInDocumentsTabV1Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectHsrOptnhsrDocumentLibrary", 0);
		CommonMethod.JavascriptClickElement("V2ProjectHsrOptnhsrDocumentLibrary");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioV2ProjectDocumentScorecardTable",1);
		CommonMethod.assertisElementPresentTrue("PortfolioV2ProjectDocumentScorecardTable", "Health Safety table is not showing ");
	}
	
	public void ValidatePerformanceRatingInDocumentsTabV1Project(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRTabDocumentList", 0);
		CommonMethod.JavascriptClickElement("V2ProjectWPRTabDocumentList");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioV2ProjectDocumentScorecardTable",1);
		CommonMethod.assertisElementPresentTrue("PortfolioV2ProjectDocumentScorecardTable", "Health Safety table is not showing ");
	}
	
	public void accessRecertification(String ratingTab, String validateChildOptn) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(ratingTab, 0);
		CommonMethod.RobustclickElementVisible(ratingTab, "AccessRecertification");
		testlog.info("When User clicks on ratingTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccessRecertification", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("AccessRecertification").trim(),
				"This project has been enrolled for recertification.", "AccessRecertification doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectClickHereLink", 0);
		if (TestCaseName.contains("V1_TC_44")) {
		CommonMethod.RobustclickElementVisible("V2ProjectClickHereLink","V2ProjectScorecardCompleteRegValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectScorecardCompleteRegValid", 0);
		}
		else {
			CommonMethod.RobustclickElementVisible("V2ProjectClickHereLink","V2ProjectRecertificationReviewScorecardBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectRecertificationReviewScorecardBtn", 0);	
		}
		CommonMethod.RobustclickElementVisible(ratingTab, validateChildOptn);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException(validateChildOptn, 1);
		CommonMethod.negativeAssertElementPresentTrue(validateChildOptn, "Optn Continue button not visible");
	}
}