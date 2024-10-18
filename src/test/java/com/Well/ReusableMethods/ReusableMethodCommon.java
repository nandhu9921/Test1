package com.Well.ReusableMethods;

import org.openqa.selenium.JavascriptExecutor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v127.network.Network;
import org.openqa.selenium.devtools.v127.network.model.RequestId;
import org.zaproxy.clientapi.core.ClientApiException;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;
import com.Well.Engine.XlsReader;
import net.sf.json.JSONObject;


public class ReusableMethodCommon extends BaseClass {

	String PortfolioAndRatingLocAccDocumentTable = "PortfolioAndRatingLocAccDocumentTable";
	String PortfolioAndRatingLocAccDocumentTableTr = "PortfolioAndRatingLocAccDocumentTableTr";
	String PortfolioAndRatingLocAccDocumentTableDeleteIcon = "PortfolioAndRatingLocAccDocumentTableDeleteIcon";
	
	
	public void GetToken()
	{
		DevTools devTools = ((HasDevTools) driver).getDevTools();
		devTools.createSession();
		devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
		devTools.addListener(Network.responseReceived(), response -> {
			org.openqa.selenium.devtools.v127.network.model.Response r=response.getResponse();
			String requestUrl=r.getUrl();
			String testurl="";
			 if (requestUrl.equals(testurl+"authenticate")) {
		           RequestId requestId = response.getRequestId();
		            Network.GetResponseBodyResponse responseBody = devTools.send(Network.getResponseBody(requestId));
		            if (responseBody != null && !responseBody.getBody().isEmpty()) {
		                String body = responseBody.getBody();
		                JSONObject jsonResponse = JSONObject.fromObject(body);
		                String bearerToken = jsonResponse.getString("token");
		                bearerToken = "Bearer " + bearerToken;
		                System.out.println("Bearer Token : "+ bearerToken);
		            }
		        }
		});
		
	}

public void SignOut() throws InterruptedException, IOException, ClientApiException {
		
	    CommonMethod.switchToParentTab();
	 // Clear Cookies
        driver.manage().deleteAllCookies();

        // Clear Local Storage
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");

        // Clear Session Storage
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
	    Thread.sleep(3000);
	    String testurl;
	    switch (Environment.toUpperCase()) {
	        case "TEST":
	            testurl = TestUrl + "logout";
	            break;
	        case "QAS":
	            testurl = QaUrl + "logout";
	            break;
	        case "STG":
	            testurl = "https://stg-nuxt.wellcertified.com/logout";
	            break;
	        case "DEV":
	            testurl = DevUrl + "logout";
	            break;
	        default:
	            throw new IllegalArgumentException("Unknown Environment: " + Environment);
	    }
	    long startTime = System.currentTimeMillis();
	    while (System.currentTimeMillis() - startTime <= 120000) {
	        try {
	            driver.get(testurl);
	            driver.navigate().refresh();
	            if (CommonMethod.isElementsExist("Username", 30)) {
	                break;
	            }
	            } catch (Exception ignored) {
	        	System.out.println("LOG OUT DIDN't WORK");
	        }
	        Thread.sleep(3000);
	    }
	    long startTime1 = System.currentTimeMillis();
	    while (driver.getPageSource().contains("Project Error")) {
	    	  if ((System.currentTimeMillis() - startTime1) < 60000) {
	                break;
	            }
	        driver.get(testurl);
	        Thread.sleep(2000);
	    }
	   CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
	}


	
//	public void RunTimeGetToken(int rowNum) throws InterruptedException, IOException, ClientApiException {
//	DevTools devTools = ((HasDevTools) driver).getDevTools();
//    devTools.createSession();
//    Thread.sleep(2000);
//    devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//    devTools.addListener(Network.responseReceived(), response -> {
//        org.openqa.selenium.devtools.v85.network.model.Response res = response.getResponse();
//        String requestUrl = res.getUrl();
//        String testurl;
//	    switch (Environment.toUpperCase()) {
//	        case "TEST":
//	            testurl = "https://test-v2-api.wellcertified.com/api/";
//	            break;
//	        case "QAS":
//	            testurl = "https://qas-v2-api.wellcertified.com/api/";
//	            break;
//	             case "DEV":
//	            testurl = "https://dev-v2-api.wellcertified.com/api/";
//	            break;
//	        default:
//	            throw new IllegalArgumentException("Unknown Environment: " + Environment);
//	    }
//	   
//        if (requestUrl.equals(testurl+"authenticate")) {
//           org.openqa.selenium.devtools.v85.network.model.RequestId requestId = response.getRequestId();
//            Network.GetResponseBodyResponse responseBody = devTools.send(Network.getResponseBody(requestId));
//            if (responseBody != null && !responseBody.getBody().isEmpty()) {
//                String body = responseBody.getBody();
//                JSONObject jsonResponse = JSONObject.fromObject(body);
//                String bearerToken = jsonResponse.getString("token");
//                bearerToken = "Bearer " + bearerToken;
//                System.out.println("bearerToken: "+bearerToken);
//                data.setCellData("Login", "Header", rowNum, bearerToken);
//            }
//        }
//    });
	
	
//	public void APISignOut(int rowNum) throws InterruptedException, IOException, ClientApiException {
//		String header = data.getCellData("Login", "Header", rowNum);
//		Response res = given().log().all().accept("application/json").contentType("application/json")
//				.header("Authorization", header).pathParams(pathprms).when().post("logout");
//		int StatusCode = res.getStatusCode();
//		Assert.assertEquals(StatusCode, 200, "Verifying status code");
//		CommonMethod.refreshBrowser();
//	    String testurl;
//	    switch (Environment.toUpperCase()) {
//	        case "TEST":
//	            testurl = TestUrl + "logout";
//	            break;
//	        case "QAS":
//	            testurl = QaUrl + "logout";
//	            break;
//	        case "STG":
//	            testurl = "https://stg-nuxt.wellcertified.com/logout";
//	            break;
//	        case "DEV":
//	            testurl = DevUrl + "logout";
//	            break;
//	        default:
//	            throw new IllegalArgumentException("Unknown Environment: " + Environment);
//	    }
//	    long startTime = System.currentTimeMillis();
//		while (driver.getPageSource().contains("Project Error")) {
//			if ((System.currentTimeMillis() - startTime) > 60000) {
//				break;
//			}
//	        driver.get(testurl);
//	        Thread.sleep(2000);
//	    }
//	//	driver.manage().deleteAllCookies();
//		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
//	}
	
//	public void SignOut() throws InterruptedException, IOException, ClientApiException {
//		CommonMethod.switchToParentTab();
//		int rowNum = 0; 
//		if(ModuleName.contains("Faculty")){
//			rowNum =5;
//		}
//		if(ModuleName.contains("V2")){
//			rowNum =6;
//			}
//		if(ModuleName.contains("Common")){
//			rowNum =3;
//			rc.RunTimeGetToken(rowNum);
//	}
//		if(ModuleName.contains("Keystone")){
//			rowNum =7;
//			}
//		if(ModuleName.contains("Cornerstone")){
//			rowNum =8;
//			}
//		if(ModuleName.contains("WELLMembership")){
//			rowNum =9;
//			}
//		APISignOut(rowNum);
//		long startTime = System.currentTimeMillis();
//		if (!CommonMethod.isElementsExist("Username", 5)) {	
//	CommonMethod.refreshBrowser();
//	if (CommonMethod.isElementsExist("AccountCloseBackButtonCommon", 20)) {	
//	CommonMethod.Robustclick("AccountCloseBackButtonCommon");
//	}
//  	CommonMethod.scrollUp();
//  	while (CommonMethod.isElementsExist("MPUserMenu", 5)) {
//  		if ((System.currentTimeMillis() - startTime) > 120000) {
//			break;
//		}
//  	  if (!CommonMethod.isElementsExist("MPUserMenu", 5)) {
//          break;
//      }
//  		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPUserMenu", 0);
//  		CommonMethod.RobustclickElementVisible("MPUserMenu", "Logout");
//  		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Logout", 0);
//  		CommonMethod.RobustJavaClickElementVisible("Logout","LoginButton");	
//  	}
//	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
//}
//	}
	
//	public void FeedBackLinkRemove() throws IOException {
//		if (CommonMethod.isElementsExist("HaveFeedbackBtn", 5)) {
//		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HaveFeedbackBtn", 0);
//		CommonMethod.hideElement("HaveFeedbackBtn");
//		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HaveFeedback", 0);
//		CommonMethod.hideElement("HaveFeedback");
//		}
//}

	public void SelectCountryAndState(String Country, String SheetName, int rowNum)
			throws IOException, InterruptedException {
		CommonMethod.verifyDropdownValues("CountryDropdown", "Country");
		CommonMethod.selectdropdownValue("CountryDropdown", Country);
		data.setCellData(SheetName, "Country", rowNum, CommonMethod.getSelectedDropdownValue("CountryDropdown"));
		CommonMethod.selectdropdownrandom("ProjectlocationState");
		data.setCellData(SheetName, "State", rowNum, CommonMethod.getSelectedDropdownValue("ProjectlocationState"));
		testlog.info("Country: " + data.getCellData(SheetName, "Country", rowNum));
		testlog.info("State: " + data.getCellData(SheetName, "State", rowNum));
	}

	public void SelectOwnerOrg(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
		CommonMethod.RobustclickElementVisible("OwnerOrgClick", "OwnerOrg");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrg", 0);
		CommonMethod.sendKeys("OwnerOrg", "R");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SelectOwnerOrgDyn", 0);
		CommonMethod.SelectRandomfromList("SelectOwnerOrgDyn", 1, 5).click();
	}

	public void SelectEnterpriseProviders(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEnterpriseProviders", 0);
//		CommonMethod.verifyDropdownValues("V2ProjectEnterpriseProviders", "Consultant");
		CommonMethod.selectdropdownVisibletext("V2ProjectEnterpriseProviders",
				data.getCellData(SheetName, "EnterpriseProvidersName", rowNum));
		data.setCellData(SheetName, "EnterpriseProvidersName", rowNum,
				CommonMethod.getSelectedDropdownValue("V2ProjectEnterpriseProviders"));
		testlog.info("And User select EnterpriseProviders");
	}

	public void Billing(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.info("And User redirects to Invoice page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CardHolderName",0);
		CommonMethod.scrolldowntoElement("CardHolderName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CardHolderName", 0);
		CommonMethod.sendKeys("CardHolderName", USfaker.address().firstName());
		data.setCellData(SheetName, "CardHolderName", rowNum, CommonMethod.getattributeValue("CardHolderName"));
		Thread.sleep(2000);
		CommonMethod.switchToFrame("CardNumberIframe");
		CommonMethod.WaitUntilClickble("CardHolderNumber", 60);
		Thread.sleep(2000);
		CommonMethod.sendKeys("CardHolderNumber", data.getCellData(SheetName, "CardNumber", rowNum));
		CommonMethod.switchToParentFrame();
		Thread.sleep(2000);
		CommonMethod.switchToFrame("CardExpDateIframe");
		CommonMethod.WaitUntilClickble("CardHolderExpDate", 60).sendKeys("0");
		CommonMethod.sendKeys("CardHolderExpDate", "9");
		CommonMethod.sendKeys("CardHolderExpDate", "2");
		CommonMethod.sendKeys("CardHolderExpDate", "5");
		CommonMethod.switchToParentFrame();
		Thread.sleep(2000);
		CommonMethod.switchToFrame("CardCVVIframe");
		CommonMethod.WaitUntilClickble("CardHolderCVC", 60);
		CommonMethod.sendKeys("CardHolderCVC", data.getCellData(SheetName, "CardCVC", rowNum));
		CommonMethod.switchToParentFrame();
		Thread.sleep(2000);
		testlog.info("And User enters data to HolderName, CardNumber, ExpDate, CardCVC fields");
		testlog.info("firstName:" + USfaker.address().firstName());
		testlog.info("CardHolderExpDate:" + "0925");
		testlog.info("CardHolderNumber:" + "4111111111111111");
		testlog.info("CardHolderCVC:" + "999");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PayNowButton", 0);
		CommonMethod.click("PayNowButton");
		testlog.info("And User clicks on pay now button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("CardHolderName", 1);
		testlog.pass("**Verifies the Completed Card Payment Billing successfully**");
	}
	
	public static void beforeSignAgreementProject() throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WellV2Tab");
		 CommonMethod.assertisElementPresentFalse("WellV2Tab","WellV2Tab is visible");
	}

	public void UpdateBilling(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.info("And User redirects to Invoice page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateInvoiceButtonBillingUpdate", 0);
		CommonMethod.RobustclickElementVisible("UpdateInvoiceButtonBillingUpdate", "PaymentStatusBillingUpdate");
		testlog.info("Given User is on Update Invoice page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PaymentStatusBillingUpdate", 0);
		testlog.info("Then User verifies added data in Invoice page");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("UpdateInvoiceOwnerNameBilling"),
				data.getCellData(SheetName, "OwnerName", rowNum), "OwnerName Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("UpdateInvoiceEmailBilling"),
				"frontendauthenticated", "Email Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("UpdateInvoiceCountryBilling"),
				data.getCellData(SheetName, "OwnerCountry", rowNum), "OwnerCountry Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("StreetName"),
				data.getCellData(SheetName, "OwnerStreet", rowNum), "StreetName Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("CityName"),
				data.getCellData(SheetName, "OwnerCity", rowNum), "CityName Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("PostalCode"),
				data.getCellData(SheetName, "OwnerPostalCode", rowNum), "CityName Error Mismatch");
		CommonMethod.selectdropdownVisibletext("PaymentStatusBillingUpdate",
				data.getCellData(SheetName, "PaymentStatus", rowNum));
		testlog.info("And User select Payment Status");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PaymentTypeBillingUpdate", 0);
		CommonMethod.selectdropdownVisibletext("PaymentTypeBillingUpdate",
				data.getCellData(SheetName, "PaymentTypes", rowNum));
		testlog.info("And User select Payment Type");
		CommonMethod.sendKeys("PaymentReferenceBillingUpdate", "01");
		testlog.info("And User enter data to Payment Reference field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PaymentDateBillingUpdate", 0);
		CommonMethod.RobustclickElementVisible("PaymentDateBillingUpdate", "DatePickerOkButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "UpdateInvoiceButtonBillingUpdate");
		testlog.info("And User select Date");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateInvoiceButtonBillingUpdate", 0);
		CommonMethod.RobustclickElementVisible("UpdateInvoiceButtonBillingUpdate", "UpdateInvoiceBackButtonBilling");
		testlog.info("And User clicks on Update button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PaidStatus", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UpdateInvoiceBackButtonBilling", 0);
		CommonMethod.RobustclickElementVisible("UpdateInvoiceBackButtonBilling", "PaidStatus");
		testlog.info("And User verifies Paid Status in Receipt");
		testlog.pass("**Verifies the Updated Payment successful**");
	}

	public void CreateSubset(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given user is on the location page");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.RobustclickElementVisible("AddButton", "AddLocationButton");
		testlog.info("when User clicks on Add Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddLocationButton", 0);
		if (SheetName.equalsIgnoreCase("Wpr") || SheetName.equalsIgnoreCase("Hsr")
				|| SheetName.equalsIgnoreCase("Wer")) {
			CommonMethod.RobustclickElementVisible("CreateSubsetButton", "SubmitSubsetButton");
			testlog.info("And User clicks on Create subset Button");
		} else {
			CommonMethod.RobustclickElementVisible("PortfolioSubsetButton", "SubmitSubsetButton");
			testlog.info("And User clicks on Create subset Button");
		}
		CommonMethod.click("SubmitSubsetButton");
		CommonMethod.negativesoftassertPageSource("Title is required.", "Title Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Description is required.", "Description Error Mismatch");
		testlog.info("And User verify Ownershiptype filter");
		CommonMethod.RobustclickElementVisible("Ownershiptypefilter", "OwnershipCheckbox");
		testlog.info("And User clicks on Ownershiptype filter");
		CommonMethod.ClickCheckbox("OwnershipCheckbox");
		testlog.info("And User clicks on cics checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Verifycheckboxfilter", 0);
		testlog.info("Then User verify Ownershiptype filter");
		CommonMethod.ClickCheckbox("SelectAllprojectscheck");
		testlog.info("And User clicks on select all project checkbox");
		CommonMethod.VerifyRadioOrCheckboxSelcted("SelectAllprojectscheck");
		testlog.info("And User verify select all project checkbox is checked");
		CommonMethod.sendKeys("AddTitlefield", "Test subset");
		testlog.info("When User Enter the substet title");
		CommonMethod.sendKeys("AddDescriptionfield", "Testing subset creation");
		testlog.info("And User Enter the substet discription");
		CommonMethod.RobustclickElementVisible("SubmitSubsetButton", "Subsettab");
		testlog.info("And User clicks on the submit button");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationRefresh", 0);
		CommonMethod.RobustclickElementVisible("LocationRefresh", "PagnitionResultCount");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Subsettab", 0);
		CommonMethod.RobustclickElementVisible("Subsettab", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationRefresSubset", 0);
		CommonMethod.RobustclickElementVisible("LocationRefresSubset", "table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationSubsetValid", 0);
		List<String> tableheaders = CommonMethod.fetchTableHeaders("table");
		CommonMethod.negativesoftassertFieldValid(tableheaders.get(1), "TEST SUBSET",
				"Table Header for TEST SUBSET doesn't present");
		testlog.pass("user verifies the table header as subset name");
		testlog.info("And User verifies the subset successfully");
		testlog.pass("And User verifies the subset creation functionality successfully");

	}

	public void clickOnAlternatives(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AlternativesTab", 0);
		CommonMethod.RobustclickElementVisible("AlternativesTab", "V2ProjectEPSubmitButton");
		testlog.info("And User clicks on AlternativesTab");
	}

	public void alternativesAAP(String SheetName, int rowNum, String optModuleName)
			throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAapSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAapSubmitButton", "V2ProjectFeatureDropdown");
		testlog.info("And User clicks on AAPSubmit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitButton", 0);
		CommonMethod.RobustclickElementVisible("SubmitButton", "V2ProjectFeatureDropdown");
		if (SheetName.equalsIgnoreCase("Community")) {
		CommonMethod.verifyDropdownValues("V2ProjectFeatureDropdown", "CommunityAlternativeFeature");
		}
		CommonMethod.negativesoftassertPageSource("Feature* is required.", "Feature Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Reason for Alternative Means and Methods * is required.",
				"Reason for Alternative Error Mismatch");
		if (SheetName.equalsIgnoreCase("Portfolio")) {
			CommonMethod.negativesoftassertPageSource("Applicable part(s) * is required.",
					"Applicable part Error Mismatch");
		}
		CommonMethod.scrollUp();
		if (SheetName.equalsIgnoreCase("V2Project")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAlternativeVersionDropdown", 0);
			CommonMethod.selectdropdownValue("V2ProjectAlternativeVersionDropdown", optModuleName);
		}
		Thread.sleep(1000);
		CommonMethod.WaitUntilClickble("V2ProjectAlternativesReasonTextArea", 60)
				.sendKeys("Reason for Alternative Means and Methods");
		CommonMethod.negativesoftassertPageSource("Proposed Alternative Means of Compliance * is required.",
				"Proposed Alternative Means of Compliance Error Mismatch");
		CommonMethod.WaitUntilClickble("V2ProjectAlternativesProposedTextArea", 60)
				.sendKeys("Proposed Alternative Means of Compliance");
		CommonMethod.uploadMultipleFile("DocumentsUpload", SampleJpgfile, SampleJpgfile, "MultipeUploadDeleteicon", 2,
				"MultipeUploadEnableButtonDeleteLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectFeatureDropdown", 0);
		Thread.sleep(3000);
		CommonMethod.selectdropdownrandom("V2ProjectFeatureDropdown");
		if (CommonMethod.isElementsExist("V2ProjectApplicablePartCheckBox", 10)) {
			CommonMethod.ClickCheckbox("V2ProjectApplicablePartCheckBox");
			testlog.info("And User checks the ApplicablePart checkbox");
		}
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
		if (SheetName.equalsIgnoreCase("Community")) {

			CommonMethod.sendKeys("CommunityAlternativeAAPApplicableParts", "Applicable Part Text");
		}
		testlog.info("And User Upload Feature Document");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitButton", 0);
		CommonMethod.RobustclickElementVisible("SubmitButton", "V2projectAAPTypeStatus");
		testlog.info("And User clicks on Submit button");
		if (SheetName.equalsIgnoreCase("V2Project") || SheetName.equalsIgnoreCase("Community")) {
			CommonMethod.scrollUp();
			if (optModuleName.equalsIgnoreCase("v2-hsr")) {
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateHSR");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateHSR"),
						"WELL HEALTH-SAFETY", "WELL HEALTH-SAFETY doesn't match");
			}
			if (optModuleName.equalsIgnoreCase("wpr")) {
				String getId = CommonMethod.getattributeValueByTextContent("CommunityAlternativeID");
				String[] stringArray = getId.split("-");
				String getAlternativeID = stringArray[1].trim();
				data.setCellData(SheetName, "AlternativeAapID", rowNum, getAlternativeID);
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateWPR");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateWPR"),
						"WELL PERFORMANCE", "WELL HEALTH-SAFETY doesn't match");
			}
			if (optModuleName.equalsIgnoreCase("wer")) {
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateWER");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateWER"),
						"WELL EQUITY", "WELL HEALTH-SAFETY doesn't match");
			}
			if (optModuleName.equalsIgnoreCase("CommunityAAP")) {
				String getId = CommonMethod.getattributeValueByTextContent("CommunityAlternativeID");
				String[] stringArray = getId.split("-");
				String getCommunityAlternativeID = stringArray[1].trim();
				data.setCellData(SheetName, "CommunityAlternativeID", rowNum, getCommunityAlternativeID);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("CommunityAlternativeType"), "AAP",
						"Table FEATURE TYPE doesn't match");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("CommunityAlternativeStatus"), "PENDING",
						"Table FEATURE STATUS doesn't match");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("CommunityAlternativeOwner"), "UI",
						"Table FEATURE OWNER name doesn't match");
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateWER");
				CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectAdminAlternativeEditButton", "Edit button is visible for non-admin user");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("CommunityAlternativeInsideStatus"),
						"Pending decision", "STATUS Pending decision doesn't match");
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getText("V2ProjectAdminAlternativeMeansCompliance"),
						"Proposed Alternative Means of Compliance", "Means of Compliance Text doesn't match");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("CommunityAlternativeValidateWV1"),
						"WELL V1", "WELL V1 doesn't match");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommunityAlternativeValidateImage", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommunityAlternativeValidateImage", 0);
			}
			CommonMethod.RobustclickElementVisible("V2ProjectAlternativeBackButton", "V2ProjectAapSubmitButton");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectAAPTypeStatus", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2projectAAPTypeStatus"), "AAP",
					"AAP Alternative doesn't match");
		}
		testlog.info("And User verifies the added details");
		testlog.info("And User verifies the Status");
		testlog.pass("**Added alternative AAP documents successfully**");
	}

	public void alternativesEP(String SheetName, int rowNum, String optModuleName)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEPSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectEPSubmitButton", "V2ProjectFeatureDropdown");
		testlog.info("And User clicks on EPSubmit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitButton", 0);
		CommonMethod.RobustclickElementVisible("SubmitButton", "V2ProjectFeatureDropdown");
		if (SheetName.equalsIgnoreCase("Hsr")) {
		CommonMethod.verifyDropdownValues("V2ProjectFeatureDropdown", "HsrAlternativeFeature");
		}
		testlog.info("And User clicks on Submit button");		
		CommonMethod.negativesoftassertPageSource("Feature* is required.", "Feature Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Reason for Equivalency Request * is required.",
				"Reason for Equivalency Error Mismatch");
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
		CommonMethod.scrollUp();
		if (SheetName.equalsIgnoreCase("V2Project")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAlternativeVersionDropdown", 0);
			CommonMethod.selectdropdownValue("V2ProjectAlternativeVersionDropdown", optModuleName);
		}
		Thread.sleep(2000);
		CommonMethod.negativesoftassertPageSource(
				"Regions/Countries where Equivalency may be Applicable * is required.",
				"Regions/Countries Equivalency Error Mismatch");
		CommonMethod.WaitUntilClickble("V2ProjectEquivalencyReason", 60).sendKeys("Reason for Equivalency Request");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentsUpload", 0);
		CommonMethod.uploadMultipleFile("DocumentsUpload", SampleJpgfile, SampleJpgfile, "MultipeUploadDeleteicon", 2,
				"MultipeUploadEnableButtonDeleteLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectFeatureDropdown", 0);
		CommonMethod.selectdropdownrandom("V2ProjectFeatureDropdown");
		testlog.info("And User select the Feature");
		data.setCellData(SheetName, "FeatureName", rowNum,
				CommonMethod.getSelectedDropdownValue("V2ProjectFeatureDropdown"));
		testlog.info("FeatureName: " + data.getCellData(SheetName, "FeatureName", rowNum));
		if (CommonMethod.isElementsExist("V2ProjectApplicablePartCheckBox", 10)) {
			CommonMethod.ClickCheckbox("V2ProjectApplicablePartCheckBox");
			testlog.info("And User checks the ApplicablePart checkbox");
		}
		CommonMethod.WaitUntilClickble("V2ProjectEquivalencyCountriesInput", 60)
				.sendKeys("Regions/Countries where Equivalency may be Applicable");
		if (CommonMethod.isElementsExist("V2ProjectVerificationTextArea", 10)) {
			CommonMethod.negativesoftassertPageSource("Verification method within proposed equivalent * is required.",
					"Verification method within proposed equivalent Error Mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectVerificationTextArea", 0);
			CommonMethod.clearAndSendKey("V2ProjectVerificationTextArea",
					"Verification method within proposed equivalent");
			testlog.info("And User enter data to Verification field");
		}
		if (CommonMethod.isElementsExist("PortfolioVerificationTextArea", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioVerificationTextArea", 0);
			CommonMethod.clearAndSendKey("PortfolioVerificationTextArea",
					"Verification method within proposed equivalent");
			testlog.info("And User enter data to Verification field");
		}
		if (CommonMethod.isElementsExist("V2ProjectSimilaritiesTextArea", 10)) {
			CommonMethod.negativesoftassertPageSource("Similarities to WELL feature requirement * is required.",
					"Similarities to WELL feature requirement Error Mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSimilaritiesTextArea", 0);
			CommonMethod.clearAndSendKey("V2ProjectSimilaritiesTextArea", "Similarities to WELL feature requirement");
			testlog.info("And User enter data to Similarities field");
		}
		if (CommonMethod.isElementsExist("PortfolioSimilaritiesTextArea", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSimilaritiesTextArea", 0);
			CommonMethod.clearAndSendKey("PortfolioSimilaritiesTextArea", "Similarities to WELL feature requirement");
			testlog.info("And User enter data to Similarities field");
		}
		if (CommonMethod.isElementsExist("V2ProjectDifferencesTextArea", 10)) {
			CommonMethod.negativesoftassertPageSource("Differences from WELL feature requirement * is required.",
					"Differences from WELL feature Error Mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDifferencesTextArea", 0);
			CommonMethod.clearAndSendKey("V2ProjectDifferencesTextArea", "Differences from WELL feature requirement");
			testlog.info("And User enter data to Differences field");
		}
		if (CommonMethod.isElementsExist("PortfolioDifferencesTextArea", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDifferencesTextArea", 0);
			CommonMethod.clearAndSendKey("PortfolioDifferencesTextArea", "Differences from WELL feature requirement");
			testlog.info("And User enter data to Differences field");
		}
		CommonMethod.negativesoftassertPageSource("Proposed Alternative Means of Compliance * is required.",
				"Proposed Alternative Means of Compliance Error Mismatch");
		CommonMethod.WaitUntilClickble("V2ProjectAlternativesProposedTextArea", 60)
				.sendKeys("Proposed Alternative Means of Compliance");
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
		testlog.info("And User upload feature document");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitButton", 0);
		CommonMethod.RobustclickElementVisible("SubmitButton", "V2projectEPTypeStatus");
		testlog.info("And User clicks on Submit button");
		CommonMethod.scrollUp();
		if (SheetName.equalsIgnoreCase("V2Project")) {
			if (optModuleName.equalsIgnoreCase("v2-hsr")) {
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateHSR");
				System.out.println("HSR Value:" + CommonMethod.getText("V2ProjectAlternativeValidateHSR"));
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateHSR"),
						"WELL HEALTH-SAFETY", "WELL HEALTH-SAFETY doesn't match");
			}
			if (optModuleName.equalsIgnoreCase("wpr")) {
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateWPR");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateWPR"),
						"WELL PERFORMANCE", "WELL PERFORMANCE doesn't match");
			}
			if (optModuleName.equalsIgnoreCase("wer")) {
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateWER");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateWER"),
						"WELL EQUITY", "WELL EQUITY doesn't match");
			}
			CommonMethod.RobustclickElementVisible("V2ProjectAlternativeBackButton", "V2ProjectEPSubmitButton");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectEPTypeStatus", 0);
			CommonMethod.negativesoftassertFieldValid("V2projectEPTypeStatus", "EP", "EP Alternative doesn't match");
		}
		if (SheetName.equalsIgnoreCase("V2Pilot_Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAlternative", 0);
			List<String> val = CommonMethod.fetchTableData("V2ProjectAlternative");
			CommonMethod.negativesoftassertFieldValid(val.get(6), "EP", "EP Alternative doesn't match");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectEPTypeStatus", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2projectEPTypeStatus"), "EP",
					"EP Alternative doesn't match");
		}
		testlog.info("Then User verifies the added details");
		testlog.info("And User verifies the Status");
		testlog.pass("**Added alternative EP documents successfully**");
	}

	public void team(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddMemberbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAddMemberbtn", "V2ProjectEmailAddress");
		testlog.info("And User clicks on Add member button");
		CommonMethod.RobustclickElementVisible("V2ProjectInvitebtn", "V2ProjectEmailAddress");
		CommonMethod.negativesoftassertPageSource("Email Address is required.", "Email Address Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Permissions Level is required.", "Permissions Level Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Role is required.", "Project Role Error Mismatch");
		testlog.info(
				"And User clicks on Invite button without entering the mandatory fields and verifies error meassage");
		String TeamEmail = data.getCellData(SheetName, "TeamMemberEmail", rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEmailAddress", 0);
		CommonMethod.sendKeys("V2ProjectEmailAddress", TeamEmail);
		testlog.info("And User enter email id field");
		testlog.info("Team Email ID: " + TeamEmail);
		CommonMethod.verifyDropdownValues("V2ProjectRole", "ProjectRole");
		CommonMethod.selectdropdownVisibletext("V2ProjectRole", "Acoustician");
		testlog.info("And User select the Role");
		CommonMethod.ClickCheckbox("V2ProjectMembercbx");
		testlog.info("And User checks the Member checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectInvitebtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectInvitebtn", "TeamMemberDeleteButton");
		testlog.info("And User clicks on Invite button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("TeamMemberDeleteButton", 1);
		CommonMethod.assertisElementPresentTrue("TeamMemberDeleteButton", "Delete icon is not visible");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("TeamMemberEditButton", 1);
		CommonMethod.assertisElementPresentTrue("TeamMemberEditButton", "Edit icon is not visible");
		testlog.info("And User verifies Edit button icon");
		testlog.info("And User verifies Delete button icon");
		testlog.info("Then User will be redirected to Team list page");
		testlog.pass("**Created Team member successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("AddedTeamMemberValidation", 1);
		String AddedTeamMemberEmail = CommonMethod.getattributeValueByTextContent("AddedTeamMemberEmail");
		CommonMethod.negativesoftassertFieldValid(AddedTeamMemberEmail, TeamEmail,
				"Added TeamMember Email doesn't match");
		testlog.info("And User verifies Team member email");
		String AddedTeamMemberRole = CommonMethod.getattributeValueByTextContent("AddedTeamMemberRole");
		CommonMethod.negativesoftassertFieldValid(AddedTeamMemberRole, "Acoustician",
				"Added TeamMember Role doesn't match");
		testlog.info("And User verifies Team member role");
		testlog.pass("**Validated Team member successfully**");
	}

	public void validateAddedTeam() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRProjectAdministrator", 0);
		String WPRProjectAdministrator = CommonMethod.getattributeValueByTextContent("WPRProjectAdministrator").trim();
		CommonMethod.negativesoftassertFieldValid(WPRProjectAdministrator, "Project Administrator",
				"Project Administrator is not visible");
		if (TestNGTestName.contains("Community")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRProjectAdministratorRole", 0);
			String WPRProjectAdministratorRole = CommonMethod
					.getattributeValueByTextContent("WPRProjectAdministratorRole").trim();
			CommonMethod.negativesoftassertFieldValid(WPRProjectAdministratorRole, "Administrator",
					"Administrator role is not matching");
		}
		String CommunityTeamMember = CommonMethod.getattributeValueByTextContent("CommunityTeamMember").trim();
		CommonMethod.negativesoftassertFieldValid(CommunityTeamMember, "Team Member", "Team Member is not visible");
		String CommunityProjectRole = CommonMethod.getattributeValueByTextContent("CommunityProjectRole").trim();
		CommonMethod.negativesoftassertFieldValid("Acoustician", CommunityProjectRole, "Project role is not matching");

	}

	public void deleteAddedTeamMember(String SheetName, int rowNum) throws IOException, InterruptedException {
		Thread.sleep(2000);
		CommonMethod.refreshBrowser();
		testlog.info("Given User is on Team model page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TeamMemberDeleteButton", 0);
		CommonMethod.Robustclick("TeamMemberDeleteButton");
		testlog.info("When User clicks on Delete Icon");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("TeamMemberDeleteButton");
		CommonMethod.negativeAssertElementNotPresentFalse("TeamMemberDeleteButton", "Delete icon is visible");
		testlog.info("Then User will be redirected to Team list page");
		testlog.pass("**Deleted Team member successfully**");
	}

	public void changeProjectAdministrator(String SheetName, int rowNum) throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTeamChangeAdministrator", 0);
		CommonMethod.RobustclickElementVisible("WPRTeamChangeAdministrator", "WPRTeamEmailAddress");
		String TeamEmail = data.getCellData(SheetName, "TeamMemberEmail", rowNum);
		CommonMethod.sendKeys("WPRTeamEmailAddress", TeamEmail);
		CommonMethod.selectdropdownVisibletext("WPRTeamAdminRole", "Architect");
		CommonMethod.click("UpdateButton");
	}

	public void validateProjectAdministrator() throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("WPRTeamChangeAdministrator");
		CommonMethod.assertisElementPresentFalse("WPRTeamChangeAdministrator",
				"Change Administrator Element is Present");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTeamMember", 0);
		String WPRTeamMember = CommonMethod.getattributeValueByTextContent("WPRTeamMember").trim();
		CommonMethod.negativesoftassertFieldValid(WPRTeamMember, "Team Manager", "Changed Manager is not visible");
		if (TestNGTestName.contains("Community")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTeamMemberProjectRole", 0);
			String WPRTeamMemberProjectRole = CommonMethod.getattributeValueByTextContent("WPRTeamMemberProjectRole")
					.trim();
			CommonMethod.negativesoftassertFieldValid(WPRTeamMemberProjectRole, "Acoustician",
					"Team Manager role is not matching");
		}
		String WPRProjectAdmin = CommonMethod.getattributeValueByTextContent("WPRProjectAdmin").trim();
		CommonMethod.negativesoftassertFieldValid(WPRProjectAdmin, "Administrator",
				"Changed Administrator is not visible");
		String WPRProjectAdminRole = CommonMethod.getattributeValueByTextContent("WPRProjectAdminRole").trim();
		CommonMethod.negativesoftassertFieldValid("Architect", WPRProjectAdminRole,
				"Project Administrator role is not matching");
		testlog.pass("And User verifies change administrator");
	}
	
	public void validDisabledProjectArea() throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectProjectNameInput");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectProjectNameInput1",1);
		CommonMethod.negativeAssertElementPresentTrue("V2ProjectProjectNameInput1","ProjectName is not present");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectGrossAreaInput1",1);
		CommonMethod.negativeAssertElementPresentTrue("V2ProjectGrossAreaInput1","Gross Area is not present");
	}

	public void editAndValidateOrganizationInformation(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectProjectNameInput");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectAdminFieldsButton");
		 CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectAdminFieldsButton","V2ProjectAdminFieldsButton is visible");
		
		if (CommonMethod.isElementsExist("ProjectInformationButton", 10)) {
			CommonMethod.RobustclickElementVisible("ProjectInformationButton", "V2ProjectProjectScope");
			testlog.info("And User clicks on Project Information Button");
			CommonMethod.clearAndSendKey("V2ProjectProjectNameInput",
					data.getCellData(SheetName, "ProjectName", rowNum));
			testlog.info("And User enters ProjectName");
		}
		if (CommonMethod.isElementsExist("ProjectInformationButton", 10)) {
			CommonMethod.RobustclickElementVisible("ProjectInformationButton", "V2ProjectProjectScope");
			testlog.info("And User clicks on Organization Information Button");
			CommonMethod.clearAndSendKey("V2ProjectProjectNameInput", data.getCellData(SheetName, "HsrName", rowNum));
			testlog.info("And User enters ProjectName");
		}
		testlog.info(
				"And User clicks on Save button without entering the mandatory fields and verifies error meassage");
		CommonMethod.sendKeys("V2ProjectProjectScope", data.getCellData(SheetName, "ProjectScope", rowNum));
		testlog.info("And User enter to ProjectScope field");
		CommonMethod.sendKeys("V2ProjectProjectGoals", data.getCellData(SheetName, "ProjectGoals", rowNum));
		testlog.info("And User enter to ProjectGoals field");
		CommonMethod.RobustclickElementVisible("V2ProjectSaveChangesButton", "WellV2DashboardTab");
		testlog.info("And User clicks on Save button");
		testlog.info("**Project Information data updated successfully**");
		/*
		 * Validate updated project information fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectProjectNameInput");
		testlog.info("And User clicks on EditTab");
		if (CommonMethod.isElementsExist("ProjectInformationButton", 10)) {
			CommonMethod.RobustclickElementVisible("ProjectInformationButton", "V2ProjectProjectScope");
			testlog.info("And User clicks on ProjectInformation Button");
		} else if (CommonMethod.isElementsExist("ProjectInformationButton", 10)) {
			CommonMethod.RobustclickElementVisible("ProjectInformationButton", "V2ProjectProjectScope");
			testlog.info("And User clicks on OwnerInformation Button");
		}
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectProjectScope"),
				data.getCellData(SheetName, "ProjectScope", rowNum), "Project scope data doesn't match");
		testlog.info("**Project scope data updated successfully**");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("V2ProjectProjectGoals"),
				data.getCellData(SheetName, "ProjectGoals", rowNum), "Project goals data doesn't match");
		CommonMethod.verifyDropdownValues("PortfolioEditOrganizationIndustry", "OrganizationIndustry");
		CommonMethod.verifyDropdownValues("UpdateInvoiceCountryBilling", "Country");			
//		CommonMethod.verifyDropdownValues("V2ProjectEnterpriseProviders", "Consultant");
		if (SheetName.equalsIgnoreCase("Hsr")) {			
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProjectScope", 0);
			CommonMethod.scrolldowntoElement("V2ProjectProjectScope");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrProgramFeePublicrbtn", 0);
			CommonMethod.VerifyRadioOrCheckboxSelcted("HsrProgramFeePublicrbtn");
			testlog.info("Then User verifies the project is Private");
		}
		String actualConsultant = CommonMethod.getSelectedDropdownValue("V2ProjectEnterpriseProviders");
		ArrayList<String> expectedConsultant = new ArrayList<String>();
		String getExcelConsultant = data.getCellData(SheetName, "EnterpriseProvidersName", rowNum);
		expectedConsultant.add(getExcelConsultant);
		expectedConsultant.add("En3 Sustainability Solutions");
		CommonMethod.negativesoftassertFieldValid(actualConsultant, expectedConsultant, "EnterpriseProvidersName Mismatch");
		Thread.sleep(3000);
		testlog.info("And User verifies the added details");
		testlog.pass("**Project goals data updated successfully**");
	}

	public void editAndValidateAdmin(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
		CommonMethod.Robustclick("V2ProjectAdminFieldsButton", "V2ProjectProjectNameInput");
		testlog.info("And User clicks on AdminFields Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CoachingContactsDropDown", 0);
		CommonMethod.selectdropdownVisibletext("CoachingContactsDropDown", "UI (welluiautomationtesting@gmail.com)");
		testlog.info("And User select CoachingContacts");
		data.setCellData(SheetName, "CoachingContacts", rowNum,
				CommonMethod.getSelectedDropdownValue("CoachingContactsDropDown"));
		testlog.info("Coaching Contacts: " + data.getCellData(SheetName, "CoachingContacts", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RelationshipManagerDropDown", 0);
		CommonMethod.selectdropdownVisibletext("RelationshipManagerDropDown", "UI (welluiautomationtesting@gmail.com)");
		testlog.info("And User select RelationshipManager");
		data.setCellData(SheetName, "RelationshipManager", rowNum,
				CommonMethod.getSelectedDropdownValue("RelationshipManagerDropDown"));
		testlog.info("Relationship Manager: " + data.getCellData(SheetName, "RelationshipManager", rowNum));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellReviewerDropDown", 0);
		CommonMethod.selectdropdownVisibletext("WellReviewerDropDown", "UI (welluiautomationtesting@gmail.com)");
		testlog.info("And User select Reviewer");
		data.setCellData(SheetName, "WellReviewer", rowNum,
				CommonMethod.getSelectedDropdownValue("WellReviewerDropDown"));
		testlog.info("Well Reviewer: " + data.getCellData(SheetName, "WellReviewer", rowNum));
		CommonMethod.uploadMultipleFile("DocumentsUpload", SampleJpgfile, SampleJpgfile, "MultipeUploadDeleteicon", 2,
				"MultipeUploadEnableButtonDeleteLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSaveChangesButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectSaveChangesButton", "WellV2DashboardTab");
		testlog.info("And User clicks on Save button");
		testlog.pass("**Admin data updated successfully**");
		/*
		 * Validate updated admin fields
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		testlog.info("And User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
		CommonMethod.Robustclick("V2ProjectAdminFieldsButton", "V2ProjectProjectNameInput");
		testlog.info("And User clicks on AdminFields Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CoachingContactsDropDown", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("CoachingContactsDropDown"),
				data.getCellData(SheetName, "CoachingContacts", rowNum), "Coaching contacts value doesn't match");
		testlog.pass("**Coaching contacts updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RelationshipManagerDropDown", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("RelationshipManagerDropDown"),
				data.getCellData(SheetName, "RelationshipManager", rowNum), "Relationship Manager value doesn't match");
		testlog.pass("**Relationship Manager value updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellReviewerDropDown", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("WellReviewerDropDown"),
				data.getCellData(SheetName, "WellReviewer", rowNum), "Well Reviewer value doesn't match");
		testlog.info("Then User verifies the added details");
		testlog.pass("**Well Reviewer value updated successfully**");
	}
	
	public void editAndValidateAdminAchievement(String SheetName, int rowNum, String WprAchievementAdminTabXpath) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		testlog.info("When User clicks on EditTab");
		rc.navigateAchievementAdminTab();
		if (SheetName.equalsIgnoreCase("Wer") || SheetName.equalsIgnoreCase("Wpr")
				|| SheetName.equalsIgnoreCase("Hsr")) {
			CommonMethod.ClickCheckbox("EditAchievedStatus");
			testlog.info("And User checks the AchievedStatus checkbox");
			CommonMethod.RobustclickElementVisible("DatePickerButton", "DatePickerOkButton");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
			CommonMethod.Robustclick("DatePickerOkButton");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DatePickerOkButton", 1);
			testlog.info("And User select Date");
		}
		rc.saveAchievementAdminTab(WprAchievementAdminTabXpath);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		testlog.info("When User clicks on EditTab");
		rc.navigateAchievementAdminTab();
		CommonMethod.VerifyRadioOrCheckboxSelcted("EditAchievedStatus");
		rc.saveAchievementAdminTab(WprAchievementAdminTabXpath);
	}
	
	public void ValidateCloseProject(String SheetName, int rowNum) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "V2ProjectAdminFieldsButton");
		testlog.info("When User clicks on EditTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAdminFieldsButton", "AccountCloseReasonSelectCommon");
		CommonMethod.verifyDropdownValues("AccountCloseReasonSelectCommon", "ProjectCloseReason");
		if(SheetName.equalsIgnoreCase("Wer") || (SheetName.equalsIgnoreCase("Wpr") || (SheetName.equalsIgnoreCase("Hsr")))) { 
		CommonMethod.verifyDropdownValues("WerCoachingSupport", "WellCoachingSupport");
		}
		if(SheetName.equalsIgnoreCase("V2Project")) {
		CommonMethod.verifyDropdownValues("CoachingSupport", "WellCoachingSupport");
		CommonMethod.verifyDropdownValues("EnthusiasmLevel", "WellEnthusiasmLevel");
		}
		ValidateCloseAccount();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveChangesButton", 0);
		CommonMethod.Robustclick("SaveChangesButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("SaveChangesButton", 1);
		ValidateSuccessmessage();
		testlog.info("And User clicks on Save button");
		testlog.pass("**Admin data updated successfully**");
		/*
		 * Validate updated Pop message
		 */
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccountCloseValidMessageCommon", 0);
		CommonMethod.WaitUntilTextToBePresentInLocator("AccountCloseValidMessageCommon",
				"This enrollment has been archived.", 300);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("AccountCloseValidMessageCommon"),
				"This enrollment has been archived", "Close Account Popup message doesn't match");
		CommonMethod.RobustclickElementVisible("AccountCloseBackButtonCommon","AccountCloseBackButtonValidCommon");
		CommonMethod.RobustclickElementVisible("AccountCloseBackButtonCommon","AccountCloseBackButtonValidCommon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		testlog.info("Then User verifies the added details");
		testlog.pass("**Well Reviewer value updated successfully**");
	}
	
	public void ValidateCloseProjectByAuthenticatedUser(String SheetName, int rowNum) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccountCloseValidMessageCommon", 0);
		CommonMethod.WaitUntilTextToBePresentInLocator("AccountCloseValidMessageCommon",
				"This enrollment has been archived.", 300);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("AccountCloseValidMessageCommon"),
				"This enrollment has been archived", "Close Account Popup message doesn't match");
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 10)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		String validEnrollXath ="";
		if(SheetName.equalsIgnoreCase("Wer")) {
			validEnrollXath ="WPRWERId";
		}
		else {
			validEnrollXath ="V2ProjectId";
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccountCloseBackButtonCommon", 0);
		CommonMethod.Robustclick("AccountCloseBackButtonCommon");
		Thread.sleep(5000);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException(validEnrollXath,1);
		 CommonMethod.assertisElementPresentTrue(validEnrollXath,"Enrollment list is not visible");
	}

	public void promotionCardValidation() throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PromotionTab", 0);
		CommonMethod.RobustclickElementVisible("PromotionTab", "PromotionCardContainer");
		testlog.info("When User clicks on PromotionTab");
		rc.ValidCardCount("PromotionCardContainer");
		testlog.info("Then User verifies Promotion card count");
		testlog.pass("**Verify card count successfully**");
	}

	public void profile(String SheetName, int rowNum) throws Exception {
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
		CommonMethod.uploadFile("FileUpload1", SampleJpgfile, "UploadFileVerifyScorecard");
		testlog.info("And User upload document for ProfileLogo and verify upload icon");
		CommonMethod.uploadMultipleFile("DocumentsUpload", SampleJpgfile, SampleJpgfile, "MultipeUploadDeleteicon", 3,
				"MultipeUploadEnableButtonDeleteLink");
		testlog.info("And User upload document for ProfileImage and verify upload icon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPrimaryProfileImageDeleteVerify", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProfileImageDeleteVerify", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectSave", "V2ProjectProfileUpdatedToastMessage");
		testlog.info("And User clicks on Save button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProfileUpdatedToastMessage", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectProfileUpdatedToastMessage"),
				"Profile updated successfully.", "Verified profile updated toast message");
		testlog.info("Then User verifies Profile updated successfully toast message");
		testlog.pass("**General Information data updated successfully**");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileTab", 0);
		if (SheetName.equalsIgnoreCase("Wpr")) {
			CommonMethod.RobustclickElementVisible("ProfileTab", "WellPerformanceProfileButton");
			testlog.info("And User clicks on ProfileTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellPerformanceProfileButton", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectGeneralInformation", "V2ProjectProjectBio");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectProjectBio"),
					"Project bio testing", "WPR About Your Org Value Error Mismatch");
			testlog.info("And User verifies the added details");
			CommonMethod.RobustclickElementVisible("WellPerformanceProfileButton", "V2ProjectYourObjective");
			testlog.info("And User clicks on WellPerformanceProfile Button");
		}
		if (SheetName.equalsIgnoreCase("Hsr")) {
			CommonMethod.RobustclickElementVisible("ProfileTab", "WellHealthSafty");
			testlog.info("And User clicks on ProfileTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellHealthSafty", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectGeneralInformation", "V2ProjectProjectBio");
			testlog.info("And User clicks on GeneralInformation Button");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectProjectBio"),
					"Project bio testing", "HSR About Your Org Value Error Mismatch");
			testlog.info("And User verifies the added details");
			CommonMethod.RobustclickElementVisible("WellHealthSafty", "V2ProjectYourObjective");
			testlog.info("And User clicks on WER Profile WellHealthSafty Story Button");
		}
		if (SheetName.equalsIgnoreCase("Wer")) {
			CommonMethod.RobustclickElementVisible("ProfileTab", "WERProfileWellEquityStoryButton");
			testlog.info("And User clicks on ProfileTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERProfileWellEquityStoryButton", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectGeneralInformation", "V2ProjectProjectBio");
			testlog.info("And User clicks on GeneralInformation Button");
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectProjectBio"),
					"Project bio testing", "WER About Your Org Value Error Mismatch");
			testlog.info("And User verifies the added details");
			CommonMethod.RobustclickElementVisible("WERProfileWellEquityStoryButton", "V2ProjectYourObjective");
			testlog.info("And User clicks on WER Profile WellEquity Story Button");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourObjective", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourObjective", "Your objective testing");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourOrganization", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourOrganization", "Your organization testing");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourWellGoals", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourWellGoals", "Your well goals testing");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourWellProject", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourWellProject", "Your well project testing");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourWellFeatures", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourWellFeatures", "Your well features testing");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectYourWellCertification", 0);
		CommonMethod.clearAndSendKey("V2ProjectYourWellCertification", "Your well certification testing");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPostCertificationMetric", 0);
		CommonMethod.clearAndSendKey("V2ProjectPostCertificationMetric", "Post certification metric testing");
		CommonMethod.RobustclickElementVisible("V2ProjectSave", "V2ProjectProfileUpdatedToastMessage");
		testlog.info("And User clicks on Save Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProfileUpdatedToastMessage", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectProfileUpdatedToastMessage"),
				"Profile updated successfully.", "Verified profile updated toast message");
		if (SheetName.equalsIgnoreCase("Wpr")) {
			CommonMethod.RobustclickElementVisible("ProfileTab", "WellPerformanceProfileButton");
			testlog.info("And User clicks on ProfileTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellPerformanceProfileButton", 0);
			CommonMethod.RobustclickElementVisible("WellPerformanceProfileButton", "V2ProjectYourObjective");
			testlog.info("And User clicks on WellPerformanceProfile Button");
		}
		if (SheetName.equalsIgnoreCase("Hsr")) {
			CommonMethod.RobustclickElementVisible("ProfileTab", "WellHealthSafty");
			testlog.info("And User clicks on ProfileTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellHealthSafty", 0);
			CommonMethod.RobustclickElementVisible("WellHealthSafty", "V2ProjectYourObjective");
			testlog.info("And User clicks on WellHealthSaftyStory Button");
		}
		if (SheetName.equalsIgnoreCase("Wer")) {
			CommonMethod.RobustclickElementVisible("ProfileTab", "WERProfileWellEquityStoryButton");
			testlog.info("And User clicks on ProfileTab");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERProfileWellEquityStoryButton", 0);
			CommonMethod.RobustclickElementVisible("WERProfileWellEquityStoryButton", "V2ProjectYourObjective");
			testlog.info("And User clicks on WellEquityStory Button");
		}
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectYourObjective"),
				"Your objective testing", "Your objective testing Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectYourOrganization"),
				"Your organization testing", "Your organization testing Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectYourWellGoals"),
				"Your well goals testing", "Your well goals testing Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectYourWellProject"),
				"Your well project testing", "Your well project testing Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectYourWellFeatures"),
				"Your well features testing", "Your well features testing Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectYourWellCertification"),
				"Your well certification testing", "Your well certification testing Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectPostCertificationMetric"),
				"Post certification metric testing", "Post certification metric testing Error Mismatch");
		testlog.info("And User verifies the added details");
		testlog.info("Then User verifies Profile updated successfully toast message");
		testlog.pass("**Certification story data updated successfully**");
	}

	public void addLocation(String SheetName, int rowNum, String ProjectType) throws Exception {
		testlog.info("Given User is on Dashboard page");
		String LocationName = null;
		if (SheetName.equalsIgnoreCase("Wpr") || SheetName.equalsIgnoreCase("Hsr")
				|| SheetName.equalsIgnoreCase("Portfolio") || ProjectType.equalsIgnoreCase("V2Pilot")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
			CommonMethod.RobustclickElementVisible("LocationsTab", "AddButton");
			testlog.info("When User clicks on LocationTab");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.RobustclickElementVisible("AddButton", "AddLocationButton");
		testlog.info("And User clicks on Add Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddLocationButton", 0);
		if (SheetName.equalsIgnoreCase("Wpr") || SheetName.equalsIgnoreCase("Hsr")
				|| SheetName.equalsIgnoreCase("Wer")) {
			CommonMethod.RobustclickElementVisible("AddLocationButton", "LocationName");
			testlog.info("And User clicks on Add Location Button");
			CommonMethod.RobustclickElementVisible("SubmitButton", "LocationName");			
			CommonMethod.verifyDropdownValues("LocationAreaUnit", "AreaType");
			CommonMethod.verifyDropdownValues("LocationSpaceType", "SpaceType");
			CommonMethod.verifyDropdownValues("LocationCountryName", "Country");			
			CommonMethod.negativesoftassertPageSource("Location Name* is required.", "Location Name Error Mismatch");
			CommonMethod.negativesoftassertPageSource("Location Area* is required.", "Location Area Error Mismatch");
			if (!(TestNGTestName.contains("WPR-Renewal") || TestNGTestName.contains("NonEnhanced"))){
				CommonMethod.negativesoftassertPageSource("Space Type* is required.", "Space Type Error Mismatch");
			}
			CommonMethod.negativesoftassertPageSource("Ownership Type* is required.", "Ownership Type Error Mismatch");
			testlog.info(
					"And User clicks on continue button without entering the mandatory fields and verifies error meassage");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationName", 0);
			LocationName = "Automation" + SheetName + "Location" + CommonMethod.randomNumber(8000000);
			data.setCellData(SheetName, "LocationName", rowNum, LocationName);
			CommonMethod.sendKeys("LocationName", LocationName);
			testlog.info("And User enter data to LocationName field");
			CommonMethod.sendKeys("LocationArea", data.getCellData(SheetName, "Area", rowNum));
			testlog.info("And User enter data to Area field");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationSpaceType", 0);
			CommonMethod.selectdropdownrandom("LocationSpaceType");
			testlog.info("And User select the SpaceType");
			data.setCellData(SheetName, "SpaceTypes", rowNum,
					CommonMethod.getSelectedDropdownValue("LocationSpaceType"));
			testlog.info("Space type: " + data.getCellData(SheetName, "SpaceTypes", rowNum));
			CommonMethod.selectdropdownVisibletext("LocationOwnershipType", "For sale/lease");
			testlog.info("And User select the OwnershipType");
			data.setCellData(SheetName, "OwnerType", rowNum,
					CommonMethod.getSelectedDropdownValue("LocationOwnershipType"));
			testlog.info("Owner type: " + data.getCellData(SheetName, "OwnerType", rowNum));
		} else if (SheetName.equalsIgnoreCase("Portfolio") || ProjectType.equalsIgnoreCase("V2Pilot")) {
			CommonMethod.RobustclickElementVisible("AddLocationButton", "PortfolioLocationProjectName");
			testlog.info("And User clicks on Add Location Button");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitButton", 0);
			CommonMethod.RobustclickElementVisible("SubmitButton", "PortfolioLocationProjectName");
			testlog.info("And User clicks on Submit Button");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationProjectName", 0);
			CommonMethod.negativesoftassertPageSource("Project Name* is required.", "Project Name Error Mismatch");
			CommonMethod.negativesoftassertPageSource("Project Version is required.", "Project Version Error Mismatch");
			CommonMethod.negativesoftassertPageSource("Project Area* is required.", "Project Area Error Mismatch");
			CommonMethod.negativesoftassertPageSource("Space Type* is required.", "Space Type Error Mismatch");
			CommonMethod.negativesoftassertPageSource("Ownership Type* is required.", "Ownership Type Error Mismatch");
			testlog.info(
					"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
			LocationName = "Automation portfolio Location" + CommonMethod.randomNumber(8000000);
			data.setCellData(SheetName, "LocationName", rowNum, LocationName);
			CommonMethod.sendKeys("PortfolioLocationProjectName", data.getCellData(SheetName, "LocationName", rowNum));
			testlog.info("And User enter data to ProjectName field");
			CommonMethod.selectdropdownrandom("PortfolioLocationProjectVersion");
			testlog.info("And User select the ProjectVersion");
			data.setCellData(SheetName, "ProjectVersion", rowNum,
					CommonMethod.getSelectedDropdownValue("PortfolioLocationProjectVersion"));
			testlog.info("Project Version: " + data.getCellData(SheetName, "ProjectVersion", rowNum));
			CommonMethod.sendKeys("PortfolioLocationProjectArea", data.getCellData(SheetName, "AreaSQFT", rowNum));
			testlog.info("And User enter data to Area field");
			CommonMethod.selectdropdownValue("PortfolioLocationSpaceType", "2");
			testlog.info("And User select the SpaceType");
			data.setCellData(SheetName, "SpaceTypes", rowNum,
					CommonMethod.getSelectedDropdownValue("PortfolioLocationSpaceType"));
			testlog.info("Space type: " + data.getCellData(SheetName, "SpaceTypes", rowNum));
			CommonMethod.selectdropdownValue("PortfolioLocationOwnerType", "new-existing");
			testlog.info("And User select the OwnerType");
			data.setCellData(SheetName, "OwnerType", rowNum,
					CommonMethod.getSelectedDropdownValue("PortfolioLocationOwnerType"));
			testlog.info("Owner type: " + data.getCellData(SheetName, "OwnerType", rowNum));
		}
		CommonMethod.negativesoftassertPageSource("Country is required.", "Country Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Street is required.", "Street Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("City is required.", "City Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Postal Code is required.", "Postal Code Name Error Mismatch");
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
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
		testlog.info("And User enter data to Street, City, PostalCode field");
		CommonMethod.RobustclickElementVisible("SubmitButton", "PagnitionResultCount");
		testlog.info("And User clicks on Submit button");
		CommonMethod.refreshBrowser();
		/*
		 * Validate location added successfully
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.scrolldowntoElement("AddButton");
		if (SheetName.equalsIgnoreCase("Wer") || SheetName.equalsIgnoreCase("Portfolio")
				|| SheetName.equalsIgnoreCase("V2Pilot_Portfolio")) {
			CommonMethod.WaitUntilPresence("LocationListTableLoading", 60);
			CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
			CommonMethod.WaitUntilPresence("LocationListTable", 180);
			List<String> val = CommonMethod.fetchTableData("LocationListTable");
			testlog.info("Fetching Data from Upload Table");
			if (SheetName.equalsIgnoreCase("Wer") || SheetName.equalsIgnoreCase("Wpr")
					|| SheetName.equalsIgnoreCase("Hsr")) {
				CommonMethod.negativesoftassertFieldValid(val.get(2), data.getCellData(SheetName, "City", rowNum),
						"City name doesn't match");
			} else {
				String[] LocationNameValid = val.get(0).split(LocationName);
				data.setCellData(SheetName, "LocationProjectID", rowNum, LocationNameValid[1].trim());
				CommonMethod.negativesoftassertFieldValid(val.get(0),
						data.getCellData(SheetName, "LocationName", rowNum), "Location name doesn't match");
			}
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationRefresh", 0);
		testlog.info("And User verifies the added details in Location list Table");
		testlog.pass("**Added single location successfully**");
	}

	public void ValidAddLocation(String SheetName, int rowNum, String LocationCount) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "Subsettab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Subsettab", 0);
		CommonMethod.scrolldowntoElement("Subsettab");
		testlog.info("When User clicks on LocationTab");
		if (SheetName.equalsIgnoreCase("Hsr") || SheetName.equalsIgnoreCase("Wer")
				|| SheetName.equalsIgnoreCase("Wpr")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationRefresh", 0);
			CommonMethod.RobustclickElementVisible("LocationRefresh", "PagnitionResultCount");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsPageTotalLoaded", 0);
		CommonMethod.GenericWaitUntil("LocationsPageTotalLoaded", LocationCount);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PagnitionResultCount", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("PagnitionResultCount"), LocationCount,
				"Result location count doesn't match");
	}

	public void ClickBilling() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BiilingTab", 0);
		CommonMethod.RobustclickElementVisible("BiilingTab", "DownloadReceipt");
		testlog.info("When User clicks on BiilingTab");
		Thread.sleep(3000);
		testlog.pass("**Nagavited to Billing successfully**");
	}

	public void DownloadBillingReceiptAndValidate(String SheetName, int rowNum, String Country)
			throws IOException, InterruptedException {
		CommonMethod.ClearDownloadFile();
		testlog.info("Given User on Receipt Page");
		String Amount = null;
		if (TestCaseName.equalsIgnoreCase("Healthsafety_TC_20_04_RenewalBilling")) {
			Amount = "4,875";
		} else {
			Amount = data.getCellData(SheetName, "EnrollFee", rowNum);
		}
		testlog.info("Amount: " + Amount);
		String Address = null;
		Thread.sleep(3000);
		if (Country.equalsIgnoreCase("US")) {
			Address = "New York, NY 10014";
			testlog.info("Address: " + Address);
		} else {
			Address = "IWBI China(HK) Limited";
			testlog.info("Address: " + Address);
		}
		String[] ProjDetails = { Address, Amount };
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DownloadReceipt", 0);
		if (TestCaseName.equalsIgnoreCase("Healthsafety_TC_20_04_RenewalBilling")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DownloadRenewalReceipt", 0);
			CommonMethod.JavascriptClickElement("DownloadRenewalReceipt");
		} else {
			CommonMethod.click("DownloadReceipt");
		}
		testlog.info("And User clicks on DownloadReceipt button");
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
				String ReceiptContent = CommonMethod.extractPDFContent(file.toString());
				for (String s : ProjDetails) {
					CommonMethod.negativesoftassertFieldValid(ReceiptContent, s,
							"Downloaded Billing Receipt Data mismatch");
					testlog.info("Then User verifies Address from Downloaded Receipt");
				}
				break;
			}
		}
		testlog.info("And User verifies Email fro Downloaded Receipt");
		testlog.info("And User verifies Amount fro Downloaded Receipt");
		testlog.pass("**Verifies Download Billing Receipt And Validate successfully**");
	}

	public void teamMemberLogin(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
		CommonMethod.scrolldowntoElement("Username");
		CommonMethod.clearAndSendKey("Username", data.getCellData(SheetName, "TeamMemberEmail", rowNum));
		testlog.info("Sending Username " + data.getCellData(SheetName, "TeamMemberEmail", rowNum));
		CommonMethod.clearAndSendKey("Password",data.getCellData("Login", "Password", 3));
		testlog.info("Sending Password " + data.getCellData("Login", "Password", 3));
		Thread.sleep(1000);
		CommonMethod.scrolldowntoElement("LoginButton");
		CommonMethod.RobustclickElementVisible("LoginButton", "SuccessfulLogin");
		testlog.info("Clicking on Submit Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SuccessfulLogin", 0);
		testlog.pass("Verfies Login Successful");
	}

	public void clickOnTeamTab() throws IOException, InterruptedException {
		testlog.info("Given User is on Dashboard");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TeamTab", 0);
		CommonMethod.RobustclickElementVisible("TeamTab", "V2ProjectAddMemberbtn");
		testlog.info("When User clicks on TeamTab");
	}

	public void errorMessageNegativeAssert() throws IOException, InterruptedException {
		negativesoftAssert.assertAll();
		testlog.pass("**Verifies Error Message for Madatory field**");
	}

	public void FieldNegativeAssert() throws IOException, InterruptedException {
		negativeFieldSoftAssert.assertAll();
		testlog.pass("**Verifies Error Message for Madatory field**");
	}

	public void searchFilterDocument(String documentName, String fileCount) throws IOException, InterruptedException {
		CommonMethod.RobustclickElementVisible("WPRDocumentFilterOption", "WPRDocumentSearchBox");
		CommonMethod.clearAndSendKey("WPRDocumentSearchBox", documentName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocumentSpinner", 0);
		CommonMethod.WaitUntilInVisibility("WPRDocumentSpinner", 60);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
		List<String> val = CommonMethod.fetchTableData(PortfolioAndRatingLocAccDocumentTable);
		CommonMethod.negativesoftassertFieldValid(val.get(0), "FeatureFile",
				"searchFilter Document table data mismatch");
		testlog.pass("**Verifies search filter successfully **");
	}

	public void VerifyScorecardPurseStatusCount(String FeatureNameXpath, String SheetName, int rowNum, String expectedPartCount)
			throws IOException, InterruptedException {
		/*
		 * Total FeaturePartCount
		 */
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(FeatureNameXpath, 0);
		int ScorecardPart = CommonMethod.ElementSize(FeatureNameXpath);
		String FeatureScorecardPartCount = Integer.toString(ScorecardPart);
		testlog.info("FeatureScorecardPartCount: " + FeatureScorecardPartCount);
		data.setCellData(SheetName, "ScorecardPartCount", rowNum, FeatureScorecardPartCount);
		rc.validateFeatureCount(ScorecardPart,Integer.parseInt(data.getCellData(SheetName, "ScorecardPartCount", rowNum)),"ScorecardPartCount");
		testlog.info("Then User verifies the Scorecard Feature Count");
	}

	public void VerifyScorecardPurseStatusCount(String SheetName, String expectedPartCount, String expectedYesCount,
			String expectedNoCount, int rowNum) throws IOException, InterruptedException {
		/*
		 * Total FeaturePartCount
		 */
		testlog.info("Given User is on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
		int ScorecardPart = CommonMethod.ElementSize("RatingFeatureName");
		String FeatureScorecardPartCount = Integer.toString(ScorecardPart);
		testlog.info("FeatureScorecardPartCount: " + FeatureScorecardPartCount);
		data.setCellData(SheetName, "ScorecardPartCount", rowNum, FeatureScorecardPartCount);
		CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "ScorecardPartCount", rowNum),
				expectedPartCount, "ScorecardPartCount doesn't match");
		/*
		 * YesPurseCount
		 */
		int YesFeature = CommonMethod.ElementSize("CommonSelectedPurseYes");
		String YesFeatureCount = Integer.toString(YesFeature);
		testlog.info("YesFeatureCount: " + YesFeatureCount);
		data.setCellData(SheetName, "YesPurseCount", rowNum, YesFeatureCount);
		CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "YesPurseCount", rowNum),
				expectedYesCount, "YesPurseCount doesn't match");
		/*
		 * NoPurseCount
		 */
		int NoFeature = CommonMethod.ElementSize("CommonSelectedPurseNo");
		String NoFeatureCount = Integer.toString(NoFeature);
		testlog.info("NoFeatureCount: " + NoFeatureCount);
		data.setCellData(SheetName, "NoPurseCount", rowNum, NoFeatureCount);
		CommonMethod.negativesoftassertFieldValid(data.getCellData(SheetName, "NoPurseCount", rowNum), expectedNoCount,
				"NoPurseCount doesn't match");
		testlog.info("Then User verifies the Scorecard Feature Count V2Project");
		testlog.info("And User verifies the " + expectedPartCount + " Part Count Scorecard V2Project");
		testlog.info("And User verifies the " + expectedYesCount + " Purse Yes Scorecard V2Project");
		testlog.info("And User verifies the " + expectedNoCount + " Purse No Scorecard V2Project");
		testlog.pass("**Verifies the Purse Scorecard Part Count V2Project successfully**");
	}

	public void paperIconWithoutRefresh(String PaperIconFeatureXpath) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException(PaperIconFeatureXpath, 1);
		int PaperIcon = CommonMethod.ElementSize(PaperIconFeatureXpath);
		String PaperIconActualCount = Integer.toString(PaperIcon);
		testlog.info("PaperIconCount: " + PaperIconActualCount);
		CommonMethod.negativesoftassertFieldValid(PaperIconActualCount, "1", "PaperIconCount doesn't match");
		testlog.info("And User verifies the Scorecard Upload Paper icon");
	}

	public void VerifyPaperIconCount(String SheetName, int rowNum, String expectedPaperIconCount)
			throws IOException, InterruptedException {
		/*
		 * PaperIconCount
		 */
		CommonMethod.refreshBrowser();
		if (CommonMethod.isElementsExist("V2ProjectHsrContinuebtn", 30)) {
			CommonMethod.Robustclick("V2ProjectHsrContinuebtn");
		}
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ValidPaperIcon",
				Integer.parseInt(expectedPaperIconCount));
		int PaperIcon = CommonMethod.ElementSize("ValidPaperIcon");
		String PaperIconActualCount = Integer.toString(PaperIcon);
		testlog.info("PaperIconCount: " + PaperIconActualCount);
		CommonMethod.negativesoftassertFieldValid(PaperIconActualCount, expectedPaperIconCount,
				"PaperIconCount doesn't match");
		testlog.pass("**Verifies the PaperIcon Count successfully**");
	}

	public void VerifyWeightIconCount(String SheetName, int rowNum, String expectedPaperIconCount)
			throws IOException, InterruptedException {
		/*
		 * WeightIconCount
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WeightPointCommon",
				Integer.parseInt(expectedPaperIconCount));
		int WeightIconCount = CommonMethod.ElementSize("WeightPointCommon");
		String WeightIconActualCount = Integer.toString(WeightIconCount);
		testlog.info("WeightPointCount: " + WeightIconActualCount);
		CommonMethod.negativesoftassertFieldValid(WeightIconActualCount, expectedPaperIconCount,
				"WeightPointCount doesn't match");
		testlog.pass("**Verifies the WeightPoint Count successfully**");
	}

	public void ValidCardCount(String locator) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(locator, 0);
		int countCard = CommonMethod.ElementSize(locator);
		boolean flag = true;
		if (countCard < 1) {
			flag = false;
		}
		String ValidCardCount = Boolean.toString(flag);
		CommonMethod.negativesoftassertFieldValid(ValidCardCount, "true", "Card count empty doesn't match");
	}

	public void ClickOnAlterativeRows(String locator, String moduleName) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(locator, 0);
		CommonMethod.scrolldowntoElement("V2ProjectEPSubmitButton");
		CommonMethod.RobustclickElementVisible(locator, "V2ProjectAlternativePageLoad");
		CommonMethod.WaitUntilInVisibility("V2ProjectAlternativePageLoad", 120);

	}

	public void supportCardValidation() throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SupportTab", 0);
		CommonMethod.RobustclickElementVisible("SupportTab", "PromotionCardContainer");
		testlog.info("When User clicks on SupportTab");
		rc.ValidCardCount("PromotionCardContainer");
		testlog.info("Then User verifies Support card count");
		testlog.pass("**Verify card count successfully**");
	}

	public void validImportHundredPlusLocations(String SheetName, int rowNum) throws IOException, InterruptedException {

		/*
		 * Validate location added successfully
		 */
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
		CommonMethod.GenericWaitUntil("LocationsPageTotalLoaded", "170");
		testlog.info("And User verifies Location list Table and location count");
		testlog.pass("**Imported 100+ Locations successfully**");
	}

	public void ScorecardLoading() throws IOException, InterruptedException {
		CommonMethod.WaitUntilVisibility("table", Scorecardtimeout);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 1);
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 20)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
	}

	public void ScorecardVersionValid(String ProjectType) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 1);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("ScorecardProjectTypeVersion"), ProjectType,
				"Project Type Doesn't match");
		testlog.info("And User verifies Project Type");
	}

	public void teamLogin(String SheetName, int rowNum, String Username) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
		CommonMethod.scrolldowntoElement("Username");
		CommonMethod.clearAndSendKey("Username", data.getCellData(SheetName, Username, rowNum));
		testlog.info("Sending Username " + data.getCellData(SheetName, Username, rowNum));
		CommonMethod.clearAndSendKey("Password",data.getCellData("Login", "Password", 3));
		testlog.info("Sending Password " + data.getCellData("Login", "Password", 3));
		Thread.sleep(1000);
		CommonMethod.scrolldowntoElement("LoginButton");
		CommonMethod.RobustclickElementVisible("LoginButton", "SuccessfulLogin");
		testlog.info("Clicking on Submit Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SuccessfulLogin", 0);
		testlog.pass("Verfies Login Successful");
	}

	public void SelectingReviewDate() throws IOException, InterruptedException {
		CommonMethod.WaitUntilClickble("DatePickerButton", 60);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("DatePickerButton", "DatePickerOkButton");
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "ReviewPaymentstatusRadio");
		testlog.info("And User select Date");
	}

	public void ValidateDateInList() throws IOException, InterruptedException {
		String DateValid = CommonMethod.getText("ListValidateDate").replaceAll("Expires on", "");
		String[] stringArray = DateValid.split("- ");
		String getDate = stringArray[0].trim();
		System.out.println("DateValid: " + getDate);
		testlog.info("getDate: " + getDate);
		CommonMethod.negativesoftassertFieldValid(getDate, CommonMethod.ValidateDateYear(), "Search Date valid failed");
	}

	public void ValidateCloseAccount() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccountCloseReasonSelectCommon", 0);
		CommonMethod.selectdropdownrandom("AccountCloseReasonSelectCommon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccountCloseReasonDateCommon", 0);
		CommonMethod.RobustclickElementVisible("AccountCloseReasonDateCommon", "DatePickerOkButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.Robustclick("DatePickerOkButton");
		testlog.info("And User select reason");
		testlog.info("And User select Date");
	}

	public void ValidateSuccessmessage() throws IOException, InterruptedException {
		if (CommonMethod.isElementsExist("UpdateToasterMessageCommon", 30)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("UpdateToasterMessageCommon", 60);
			testlog.info("And User verifies success message");
		}
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
			CommonMethod.RobustclickElementVisible("V2ProjectAddMemberbtn", "V2ProjectEmailAddress");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEmailAddress", 0);
			CommonMethod.sendKeys("V2ProjectEmailAddress", EmailDetails[i]);
			testlog.info("And User enter email id field");
			testlog.info("Team Email ID: " + EmailDetails[i]);
			CommonMethod.selectdropdownVisibletext("V2ProjectRole", "Acoustician");
			testlog.info("And User select the Role");
			CommonMethod.ClickCheckbox(PermissionsLevel[i]);
			testlog.info("And User checks the Member checkbox");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectInvitebtn", 0);
			CommonMethod.RobustclickElementVisible("V2ProjectInvitebtn", "V2ProjectDeleteIcon");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDeleteIcon", 0);
			testlog.info("And User clicks on Invite button");
			testlog.info("Then User will be redirected to Team list page");
			testlog.pass("**Created Team member successfully**");
		}
	}

	public void ValidFeatureDuplication() throws IOException, InterruptedException {
		testlog.info("Given User is on Scorecard page");
		boolean flag = false;
		ArrayList<String> featureList = new ArrayList<String>();
		List<WebElement> Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			featureList.add(Creditname);
		}
		// Remove empty values from the list
		for (int i = 0; i < featureList.size(); i++) {
			if (featureList.get(i).isEmpty()) {
				featureList.remove(i);
				i--;
			}
		}
		Set<String> res = findDuplicates(featureList);
		String resStr = "";
		if (res.size() > 0) {
			Iterator<String> itr = res.iterator();
			while (itr.hasNext()) {
				resStr += itr.next() + " , ";
			}
			flag = true;
			System.out.println("Duplicate feature found: " + resStr);
			testlog.info("Duplicate feature found: " + resStr);
		}
		String featureDuplicationExists = Boolean.toString(flag);
		CommonMethod.negativesoftassertFieldValid(featureDuplicationExists, "false",
				"Duplicate feature found: " + resStr);
		testlog.pass("User verifies feature Duplication");
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

	public void PurseStatus() throws IOException, InterruptedException {
		// yes
		v2project.RefreshScorecard();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseYes", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseYes");
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 30)) {
			CommonMethod.WaitUntilVisibility("PortfolioScorecardPursueToast", 120);
			CommonMethod.WaitUntilInVisibility("PortfolioScorecardPursueToast", 60);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseYes1", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseYes1");
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 30)) {
			CommonMethod.WaitUntilVisibility("PortfolioScorecardPursueToast", 120);
			CommonMethod.WaitUntilInVisibility("PortfolioScorecardPursueToast", 60);
		}
		// MayBe
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseMayBe", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseMayBe");
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 30)) {
			CommonMethod.WaitUntilVisibility("PortfolioScorecardPursueToast", 120);
			CommonMethod.WaitUntilInVisibility("PortfolioScorecardPursueToast", 60);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseMayBe1", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseMayBe1");
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 20)) {
			CommonMethod.WaitUntilVisibility("PortfolioScorecardPursueToast", 120);
			CommonMethod.WaitUntilInVisibility("PortfolioScorecardPursueToast", 60);
		}
		// No
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseNo", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseNo");
		if (CommonMethod.isElementsExist("DatePickerConfirmButton", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerConfirmButton", 0);
			CommonMethod.RobustclickElementVisible("DatePickerConfirmButton", "PortfolioScorecardPursueToast");
		}
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 20)) {
			CommonMethod.WaitUntilVisibility("PortfolioScorecardPursueToast", 120);
			CommonMethod.WaitUntilInVisibility("PortfolioScorecardPursueToast", 60);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortFolioScoreCardPurseNo1", 0);
		CommonMethod.JavascriptClickElement("PortFolioScoreCardPurseNo1");
		if (CommonMethod.isElementsExist("DatePickerConfirmButton", 15)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerConfirmButton", 0);
			CommonMethod.RobustclickElementVisible("DatePickerConfirmButton", "PortfolioScorecardPursueToast");
		}
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 20)) {
			CommonMethod.WaitUntilVisibility("PortfolioScorecardPursueToast", 120);
			CommonMethod.WaitUntilInVisibility("PortfolioScorecardPursueToast", 60);
		}

	}

	public void ScorecardPurseSearchFilter(String FeatureName, String labelName) throws IOException, InterruptedException {
		testlog.info("And User clicks on ScorecardTab");
		CommonMethod.refreshBrowser();
		rc.ScorecardLoading();
		CommonMethod.WaitUntilVisibility("ApplicableVersion", Scorecardtimeout);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
		List<WebElement> Feature;
		boolean flag = false;
		Feature = CommonMethod.findElements("RatingFeatureName");
		for (WebElement f : Feature) {
			if (f.getText().equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.WaitUntilClickble(f, 120);
				CommonMethod.JavascriptClickElement(f);
				CommonMethod.JavascriptClickElement("PortfolioScorecardFeatureVerificationTab");
				testlog.info("And User clicks on Verification tab");
				if (!CommonMethod.isElementsExist("ScorecardTablePendingStatus", 20)) {
				portfolio.PursingSearch(labelName);
				}
				CommonMethod.JavascriptClickElement(f);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void editAdminAwardDate(String AchievedStatus,String saveAchievementXpath) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		testlog.info("When User clicks on EditTab");
		navigateAchievementAdminTab();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditAchievedStatus", 0);
		CommonMethod.ClickCheckbox("EditAchievedStatus");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AwardedDate", 0);
		CommonMethod.RobustclickElementVisible("AwardedDate", "DatePickerOkButton");
		testlog.info("And User select Date");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectnextYearbtn", 0);
		CommonMethod.Robustclick("V2ProjectnextYearbtn", "V2ProjectDatePopupWeekday");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRSelectDate", 0);
		Thread.sleep(1000);
		CommonMethod.RobustclickElementVisible("WPRSelectDate", "DatePickerOkButton");
		Thread.sleep(1000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.Robustclick("DatePickerOkButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DatePickerOkButton", 1);
		saveAchievementAdminTab(saveAchievementXpath);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("HsrSealValidate", 1);
		CommonMethod.negativeAssertElementPresentTrue("HsrSealValidate", "Hsr Seal Image is not visble");
	}

	public void navigateRenewal() throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2RenewalButton", 0);
		CommonMethod.RobustclickElementVisible("V2RenewalButton", "Acknowledge");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Acknowledge", 0);
		CommonMethod.ClickCheckbox("Acknowledge");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalWprTermAndConditionSaveBtn", 0);
		CommonMethod.RobustclickElementVisible("RenewalWprTermAndConditionSaveBtn", "Acknowledge");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DeleteYesButton", 0);
		CommonMethod.RobustclickElementVisible("DeleteYesButton", "RenewalPopMsgValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalPopMsgValid", 0);
		if (TestNGTestName.contains("NonEnhanced")) {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("RenewalPopMsgValid").replaceAll("\\s+", " "),
					"Please review the instructions in the locations tab", "Renewal PopUp Msg doesn't match");
		} else {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("RenewalPopMsgValid").replaceAll("\\s+", " "),
					"Please confirm that the locations list", "Renewal PopUp Msg doesn't match");
		}
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AddButton", 0);
	}

	public void ArchieveToLocations() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationConfirmButton", 0);
		CommonMethod.assertisElementPresentTrue("RenewalLocationConfirmButton",
				"Renewal Location Confirm Button not present");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RenewalPopMsg", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationContentValid", 0);
		String ActualText = CommonMethod.getattributeValueByTextContent("RenewalLocationContentValid");
		String[] stringArray = ActualText.split("to:");
		CommonMethod.negativesoftassertFieldValid(stringArray[0].trim(), "Please follow the instructions below",
				"Renewal location Msg doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSubsetButton", 0);
		CommonMethod.scrolldowntoElement("PortfolioSubsetButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationListArchiveButton", 4);
		String locationCount;
		if (TestNGTestName.contains("NonEnhanced")) {
			locationCount = "6";
			CommonMethod.negativesoftassertFieldValid(
					String.valueOf(CommonMethod.ElementSize("RenewalLocationListArchiveButton")), locationCount,
					"Archive button count mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationListArchiveButton1", 0);
			CommonMethod.Robustclick("RenewalLocationListArchiveButton1");
		} else {
			locationCount = "5";
			CommonMethod.negativesoftassertFieldValid(
					String.valueOf(CommonMethod.ElementSize("RenewalLocationListArchiveButton")), locationCount,
					"Archive button count mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationListArchiveButton1", 0);
			CommonMethod.Robustclick("RenewalLocationListArchiveButton1");
		}
		if (CommonMethod.isElementsExist("RenewalLocationListArchiveToasterMsg", 7)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationListArchiveToasterMsg", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("RenewalLocationListArchiveToasterMsg", 1);
		}
		if (TestNGTestName.contains("NonEnhanced")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationListArchiveButton2", 0);
			CommonMethod.Robustclick("RenewalLocationListArchiveButton2");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationListArchiveButton2", 0);
			CommonMethod.Robustclick("RenewalLocationListArchiveButton2");
		}
		if (CommonMethod.isElementsExist("RenewalLocationListArchiveToasterMsg", 7)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationListArchiveToasterMsg", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("RenewalLocationListArchiveToasterMsg", 1);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		int var = CommonMethod.ElementSize("RenewalLocationListRestoreButtonValid");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(var), "2", "Restore button count mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationListRestoreButton", 0);
		CommonMethod.Robustclick("RenewalLocationListRestoreButton");
		if (CommonMethod.isElementsExist("RenewalLocationListArchiveToasterMsg", 7)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationListArchiveToasterMsg", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("RenewalLocationListArchiveToasterMsg", 1);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		int var1 = CommonMethod.ElementSize("RenewalLocationListRestoreButtonValid");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(var1), "1", "Restore button count mismatch");
		CommonMethod.refreshBrowser();
	}

	public void ValidateAddedLocations() throws Exception {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationListTableLoading", 0);
		CommonMethod.RobustclickElementVisible("LocationListTableLoading", "LocationListTable");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		// Disable Add loc
		if (TestCaseName
				.equalsIgnoreCase("ArchieveAddedAndImportedLocations")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalAddedLocationArchiveButtonDisable", 0);
			CommonMethod.Robustclick("RenewalAddedLocationArchiveButtonDisable");
		}
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("RenewalAddedLocationArchiveButtonDisable");
		CommonMethod.negativeAssertElementNotPresentFalse("RenewalAddedLocationArchiveButtonDisable",
				"Added Location Archive button is visible");
		// Disable Bulk Loc
		if (TestCaseName
				.equalsIgnoreCase("Healthsafety_TC_20_05_ValidateAddedLocationsArchieveAddedAndImportedLocations")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalImportLocationArchiveButtonDisable1", 0);
			CommonMethod.Robustclick("RenewalImportLocationArchiveButtonDisable1");
		}
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("RenewalImportLocationArchiveButtonDisable1");
		CommonMethod.negativeAssertElementPresentTrue("RenewalImportLocationArchiveButtonDisable1",
				"Import Archive button is visible");
	}

	public void ValidateConfirmLocations(String SheetName, int rowNum) throws Exception {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalLocationConfirmButton", 0);
		CommonMethod.RobustclickElementVisible("RenewalLocationConfirmButton", "Confirmbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalConfirmButtonPopupText", 0);
		String actualRenewalConfirmButtonPopupText=CommonMethod.getattributeValueByTextContent("RenewalConfirmButtonPopupText");
		actualRenewalConfirmButtonPopupText= actualRenewalConfirmButtonPopupText.replaceAll("\\s+", " ").trim();
		System.out.println(actualRenewalConfirmButtonPopupText);
		CommonMethod.negativesoftassertFieldValidEquals(actualRenewalConfirmButtonPopupText, "Please confirm that the locations list is final to proceed with renewal next steps and invoice generation.", "Confirm Locations button's popup text does not matched ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Confirmbtn", 0);
		CommonMethod.RobustclickElementVisible("Confirmbtn", "RenewalConfirmFinalLocMessage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalConfirmFinalLocMessage", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RenewalConfirmFinalLocMessage"),
		"Thank you for confirming", "Renewal Confirm Final Message doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalNextFinalLocButton", 0);
		CommonMethod.RobustclickElementVisible("RenewalNextFinalLocButton", "RenewalPaymentLanding");
		}

	public void ValidateRenewalPayment() throws Exception {
		if (TestCaseName.equalsIgnoreCase("Performance_TC_22_08_ConfirmLocations")) {
			CommonMethod.navigateBack();
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.RobustclickElementVisible("WellV2DashboardTab", "WellV2DashboardTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("RenewalConfirmLocationInDashboard", 1);
		CommonMethod.assertisElementPresentFalse("RenewalConfirmLocationInDashboard", "Confirm button is visible");
	}

	public void ValidateRenewalMessageInReviewBeforePayment() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "RenewalPaymentErrorMessageInReview");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalPaymentErrorMessageInReview", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RenewalPaymentErrorMessageInReview"),
				"Renewal fee is required before submitting for review",
				"Renewal fee is required message doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalPaymentNavigationFromReview", 0);
		CommonMethod.RobustclickElementVisible("RenewalPaymentNavigationFromReview", "V2ProjectPreBillingPayNowButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectPreBillingPayNowButton", 0);
	}

	public void ValidateRenewalMessageInReviewAfterPayment() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
		CommonMethod.RobustclickElementVisible("ReviewTab", "RenewalPaymentErrorMessageInReview");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("RenewalPaymentErrorMessageInReview", 1);
		CommonMethod.assertisElementPresentFalse("RenewalPaymentErrorMessageInReview",
				"Renewal fee is required message is visible");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrSubmitReview", 0);
		CommonMethod.RobustclickElementVisible("HsrSubmitReview", "HsrSelectedProjectPhaseReview");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrSelectedProjectPhaseReview", 0);
		if (TestCaseName.equalsIgnoreCase("Healthsafety_TC_20_05_RenewalReviewValidateAfterpayment")) {
			CommonMethod.DropdownOptionVisiblity("HsrSelectedProjectPhaseReview",
					"Renewal Preliminary Health-Safety Review", true);
			CommonMethod.DropdownOptionVisiblity("HsrSelectedProjectPhaseReview", "Renewal Final Health-Safety Review",
					true);
			CommonMethod.DropdownOptionVisiblity("HsrSelectedProjectPhaseReview", "Renewal Curative Action Plan Review",
					true);
		} else {
			CommonMethod.DropdownOptionVisiblity("HsrSelectedProjectPhaseReview",
					"Renewal Preliminary Performance Rating Review", true);
			CommonMethod.DropdownOptionVisiblity("HsrSelectedProjectPhaseReview",
					"Renewal Final Performance Rating Review", true);
			CommonMethod.DropdownOptionVisiblity("HsrSelectedProjectPhaseReview", "Renewal Curative Action Review",
					true);
		}
		CommonMethod.refreshBrowser();
	}

	public void noRenewal(String SheetName, int rowNum, String PricingAmount) throws Exception {
		testlog.info("Given User is on Dashboard page");
		if (TestCaseName.equalsIgnoreCase("Performance_TC_22_01_NavigateRenewalAsYes")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2RenewalButton", 0);
			CommonMethod.RobustclickElementVisible("V2RenewalButton", "Acknowledge");
		} 
		if (TestCaseName.equalsIgnoreCase("Performance_TC_22_04_NavigateRenewalAsYes")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalButton", 0);
			CommonMethod.RobustclickElementVisible("RenewalButton", "Acknowledge");
		}
		if (TestCaseName.equalsIgnoreCase("Healthsafety_TC_20_01_NavigateRenewalAsNo")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReActivateButton", 0);
			CommonMethod.RobustclickElementVisible("ReActivateButton", "Acknowledge");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Acknowledge", 0);
		CommonMethod.ClickCheckbox("Acknowledge");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalWprTermAndConditionSaveBtn", 0);
		CommonMethod.RobustclickElementVisible("RenewalWprTermAndConditionSaveBtn",
				"RenewalMultipleLocationRenewalPopupValid");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("RenewalMultipleLocationRenewalPopupValid", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DeleteNoButton", 0);
		Thread.sleep(3000);
		CommonMethod.RobustclickElementVisible("DeleteNoButton", "RenewalConfirmFinalLocMessage");
		Thread.sleep(3000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalConfirmFinalLocMessage", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RenewalConfirmFinalLocMessage"),
				"Thank you for confirming", "Renewal Confirm Final Message doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalNextFinalLocButton", 0);
		CommonMethod.RobustclickElementVisible("RenewalNextFinalLocButton", "RenewalPaymentLanding");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalPaymentLanding", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("RenewalPaymentLanding"),
				"Confirm your renewal fees", "Renewal Confirm Final Message doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RenewalPaymentPricingValid", 0);
		String PricingValid = CommonMethod.getattributeValueByTextContent("RenewalPaymentPricingValid").replaceAll("\\W", "");
		data.setCellData(SheetName, "RenewalPricing", rowNum, PricingValid);
		CommonMethod.negativesoftassertFieldValid(PricingValid, PricingAmount, "Renewal Payment Pricing doesn't match");
		CommonMethod.navigateBack();
	}

	public void uploadDocumentToastMessage() throws Exception {
		if (CommonMethod.isElementsExist("DocumentAddedToasterMessage", 5)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DocumentAddedToasterMessage", 1);
		}
	}
	
	public void PurseStatusToasterMessage() throws IOException, InterruptedException {
		if (CommonMethod.isElementsExist("PortfolioScorecardPursueToast", 30)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScorecardPursueToast", 1);
		}
		}
	
	public void OptionAddedToasterMessage() throws IOException, InterruptedException {
			if (CommonMethod.isElementsExist("OptionAdded", 20)) {
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("OptionAdded", 1);
			}
			}

	public void DownloadLocationFile(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocListTr", 0);
		FileUtils.cleanDirectory(new File(downloadPath));
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationDownloadButton", 0);
		CommonMethod.click("LocationDownloadButton");
		testlog.info("And User clicks on Download Review button");
		Thread.sleep(5000);
		boolean fileExistsReturnValue = CommonMethod.isFileDownloaded();
		testlog.info("Then User verifies Downloaded file");
		String fileExists = Boolean.toString(fileExistsReturnValue);
		CommonMethod.negativesoftassertFieldValid(fileExists, "true", "Download Location file doesn't Exist");
		CommonMethod.FileRename(downloadPath, "/Downloads/LocationOccupancyFile.xlsx");
		Thread.sleep(2000);
		portfolioLocationDownload = new XlsReader(
				System.getProperty("user.dir") + "/Downloads/LocationOccupancyFile.xlsx");
		// verify Download file Occupancy column with location table
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioLocListTr", 5);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("EstForImportLocation"),
				portfolioLocationDownload.getCellData("Import", "Size (sq ft)*",
						portfolioLocationDownload.findRow("Import", "WELL at scale Test location 01"), 2),
				"Download file Occupancy column with WELL at scale Test location 1 mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("EstForImportLocation2"),
				portfolioLocationDownload.getCellData("Import", "Size (sq ft)*",
						portfolioLocationDownload.findRow("Import", "WELL at scale Test location 02"), 2),
				"Download file Occupancy column with WELL at scale Test location 2 mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("EstForImportLocation3"),
				portfolioLocationDownload.getCellData("Import", "Size (sq ft)*",
						portfolioLocationDownload.findRow("Import", "WELL at scale Test location 03"), 2),
				"Download file Occupancy column with WELL at scale Test location 3 mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("EstForImportLocation4"),
				portfolioLocationDownload.getCellData("Import", "Size (sq ft)*",
						portfolioLocationDownload.findRow("Import", "WELL at scale Test location 04"), 2),
				"Download file Occupancy column with WELL at scale Test location 4 mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("EstForImportLocation5"),
				portfolioLocationDownload.getCellData("Import", "Size (sq ft)*",
						portfolioLocationDownload.findRow("Import", "WELL at scale Test location 05"), 2),
				"Download file Occupancy column with WELL at scale Test location 5 mismatch");
	}

	public void inviteTeam() throws Exception {
		testlog.info("Given User is on Team page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddMemberbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAddMemberbtn", "PortfolioEmailAddress");
		testlog.info("And User clicks on Add member button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioEmailAddress", 0);
		CommonMethod.sendKeys("PortfolioEmailAddress", "gokulautomationv2@gmail.com");
		testlog.info("And User enter email id field");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioRole", 0);
		if (TestNGTestName.contains("CoreProject") || TestNGTestName.contains("WPR")
				|| TestNGTestName.contains("Community")) {
			CommonMethod.selectdropdownVisibletext("V2ProjectRole", "Acoustician");
		} else {
			CommonMethod.selectdropdownVisibletext("PortfolioRole", "Acoustician");
		}
		testlog.info("And User select the Role");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectMembercbx", 0);
		CommonMethod.ClickCheckbox("V2ProjectMembercbx");
		testlog.info("And User checks the Member checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectInvitebtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectInvitebtn", "TeamInviteButtonCommon");
		testlog.info("And User clicks on Invite button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TeamInviteButtonCommon", 0);
		CommonMethod.JavascriptClickElement("TeamInviteButtonCommon");
		testlog.info("And User clicks on Invite button in table list");
		Thread.sleep(2000);
		if (TestCaseName.equalsIgnoreCase("Community_TC_07_01_InviteTeam")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("TeamInviteButtonCommunityToastermessage", 1);
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("TeamInviteButtonCommunityToastermessage"),
					"email invite!", "Email Invite Toaster message doesn't match");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("TeamInviteButtonCommonToastermessage", 1);
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("TeamInviteButtonCommonToastermessage"),
					"email invite!", "Email Invite Toaster message doesn't match");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("TeamInviteButtonCommonToastermessage", 1);
		testlog.info("Then User verifies sent invite toaster message");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TeamInviteDeleteButtonCommon", 0);
		CommonMethod.JavascriptClickElement("TeamInviteDeleteButtonCommon");
		testlog.info("And User clicks on Delete button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("TeamInviteDeleteButtonCommonToastermessage", 1);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("TeamInviteDeleteButtonCommonToastermessage"),
				"Invite deleted!", "Delete Invite Toaster message doesn't match");
		testlog.info("And User verifies delete invite toaster message");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("TeamInviteButtonCommon", 1);
		testlog.info("Then User will be redirected to Team list page");
		testlog.pass("**Verifies Invite sent and Delete Invite successfully**");
	}

	public void projectAdminTeam() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("TeamProjectAdministratorEditButton", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("TeamProjectAdministratorDeleteButton", 1);
		CommonMethod.assertisElementPresentFalse("TeamProjectAdministratorEditButton", "EditButton is visible");
		CommonMethod.assertisElementPresentFalse("TeamProjectAdministratorDeleteButton", "DeleteButton is visible");
	}

	public void clickScorecard() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTab", 0);
		CommonMethod.JavascriptClickElement("ScorecardTab");
		rc.ScorecardLoading();
		testlog.info("And User clicks on ScorecardTab");
	}
	
	public void clickDashboard() throws IOException, InterruptedException {
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 20)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2DashboardTab", 0);
		CommonMethod.JavascriptClickElement("WellV2DashboardTab");
		testlog.info("And User clicks on DashboardTab");
	}

	public void clickDocument() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentLibraryTab", 0);
		CommonMethod.JavascriptClickElement("DocumentLibraryTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
		testlog.info("When User clicks on DocumentTab");
		testlog.pass("**Navigate Document successfully**");
	}

	public void clickWellV2Tab() throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WellV2Tab", 0);
		CommonMethod.JavascriptClickElement("WellV2Tab");
	}

	public void clickDocumentsLibraryAllTabInTasks() throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioTaskListAllTab", 0);
		CommonMethod.JavascriptClickElement("PortfolioTaskListAllTab");
		testlog.info("When User clicks on All In Tasks Tab");
		testlog.pass("**Navigate All successfully**");
	}
	
  public void RemoveSpaceAndValidate(String ObjectLocator, String expectedText) throws IOException, InterruptedException {
	CommonMethod.WaitUntilNumberOfElementTextToBePresentWithException(ObjectLocator, expectedText, 300);
	String actualText=CommonMethod.getattributeValueByTextContent(ObjectLocator);
	actualText= actualText.replaceAll("\\s+", " ").trim();
	System.out.println(actualText);
	CommonMethod.negativesoftassertFieldValid(actualText.toLowerCase(), expectedText.toLowerCase(), "Expected text does not matched ");
  }
	
	public void scorecardPagination(String FeatureName, String SheetName, int rowNum, String Commodity, String FileName,
			Boolean PartNameRequired, Boolean VerificationMethodValidationRequired, Boolean NoteRequired,
			Boolean IntentCheckboxRequired) throws Exception {
		rc.ScorecardLoading();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		List<WebElement> Feature;
		if (Commodity.contains("Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardFeature", 0);
			Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
			Feature = CommonMethod.findElements("RatingFeatureName");
		}
		testlog.info("Fetching total no. of credits on page");
		testlog.info("TaskName : " + FeatureName);
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						"PortfolioScoreCardAddOptionbutton");
				testlog.info("And User clicks on VerificationTab");
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
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScoreCardVerificationCloseicon", 1);
				testlog.info("And User clicks on Closeicon");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Assignbutton", 0);
				Thread.sleep(1000);
				CommonMethod.JavascriptClickElement("Assignbutton");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
				CommonMethod.Robustclick("WPRAssignLocCbx", "WPRAssignDisabledbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignSavebtn", 0);
				CommonMethod.Robustclick("WPRAssignSavebtn", "PortfolioScoreCardVerificationAssignLocCancelbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AssignLocCloseIcon", 0);
				CommonMethod.Robustclick("AssignLocCloseIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
				testlog.info("And User clicks on Assign Button");
				CommonMethod.scrollDown();
				for (int i = 0; i <= 6; i++) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn",
							0);
					CommonMethod.JavascriptClickElement("PortfolioScoreCardVerificationUploadbtn");
					generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired,
							VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
					CommonMethod.Robustclick("WPRAssignLocCbx", "PortfolioScorecardValidDisable");
					ScorecardConfirmLocUploadSaveButton();
					rc.uploadDocumentToastMessage();
				}
				testlog.info("And User verifies Document Uploaded successfully toast message");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadbtn", 0);
				CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadbtn");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException(PortfolioAndRatingLocAccDocumentTableTr,
						5);
				int var = CommonMethod.ElementSize("PortfolioAndRatingLocAccDocumentTableTr");
				CommonMethod.negativesoftassertFieldValid(String.valueOf(var), "5",
						"Pagnition Scorecard table row Count mismatch");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRDocumentPaginationsecond", 0);
				CommonMethod.RobustclickElementVisible("WPRDocumentPaginationsecond", "WPRPaginationNumberText");
				testlog.info("And User clicks on pagination number");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRPaginationNumberText", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException(PortfolioAndRatingLocAccDocumentTableTr,
						2);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("WPRPaginationNumberText"), "6",
						"Document Library Pagition count Doesn't match");
				testlog.info("Then User verifies the pagition Count");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
	}

	public void ScorecardUploadSaveButton() throws IOException, InterruptedException {
		if (TestCaseName.contains("ValidateDocumentsLibAssignLocationCountPostUnderConstruction")
		   || (TestCaseName.contains("ValidateDocumentsLibraryAssignLocationCountPostUnderConstructionToComplete"))) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndContinuebtn", 0);
			CommonMethod.click("SaveAndContinuebtn");
		} else {
		CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationAddNote");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndContinuebtn", 0);
		if (TestCaseName.equalsIgnoreCase(
				"Portfolio_CTC_02_12_ValidateScorecardUploadButtonCountAfterClosingTheAssignLocationModal")
				|| TestCaseName.equalsIgnoreCase(
						"Healthsafety_TC_SF_06_06_ValidateScorecardUploadButtonCountAfterClosingTheAssignLocationModal")) {

			CommonMethod.click("SaveAndContinuebtn");
			testlog.info("And User clicks on save button");

		} else {
			CommonMethod.Robustclick("SaveAndContinuebtn", "PortfolioScoreCardVerificationAddNote");
			testlog.info("And User clicks on save button");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
	}

	public void ScorecardUploadUpdateSaveButton() throws IOException, InterruptedException {
		CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationAddNote");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndContinueUpdatebtn", 0);
		CommonMethod.Robustclick("SaveAndContinueUpdatebtn", "PortfolioScoreCardVerificationAddNote");
		testlog.info("And User clicks on save button");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("PortfolioScoreCardVerificationAddNote", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
	}

	public void ScorecardConfirmLocUploadSaveButton() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndExitbtn", 0);
		CommonMethod.Robustclick("SaveAndExitbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
	}

	public void SaveAndExitbtnUploadPage() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndExitbtnUploadPage", 0);
		CommonMethod.Robustclick("SaveAndExitbtnUploadPage");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
	}
	
	public void SaveAndExitbtnUploadInFirstPage() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndExitbtnUploadPage", 0);
		CommonMethod.Robustclick("SaveAndExitbtnUploadPage");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
	}

	public void ScorecardConfirmLocBulkUploadSaveButton() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocationLoading", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocFirstChildCbx", 0);
		if (!CommonMethod.isSelected("WPRAssignLocFirstChildCbx")) {
			CommonMethod.ClickCheckbox("WPRAssignLocFirstChildCbx");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveAndExitbtn", 0);
		CommonMethod.Robustclick("SaveAndExitbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("AssignLocCloseIcon", 1);
	}

	public void ScorecardNavigation() throws IOException, InterruptedException {
		testlog.info("Given User is on dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTab", 0);
		CommonMethod.RobustclickElementVisible("ScorecardTab", "ApplicableVersion");
		testlog.info("And User clicks on ScorecardTab");
		rc.ScorecardLoading();
	}

	public void validateReviewInProgressDocUpload(String FeatureName,String Commodity) throws Exception {
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
		if (Commodity.contains("Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardFeature", 0);
			Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RatingFeatureName", 0);
			Feature = CommonMethod.findElements("RatingFeatureName");
		}
		if (TestCaseName.contains("PortfolioOptnRating")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectWPRPFeature", 0);
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
				CommonMethod.RobustclickElementVisible("WPRVerficationTab", "WPRAddOption");
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioVerifyScorecardUploadButtonDisable",1);
				CommonMethod.negativeAssertElementPresentTrue("PortfolioVerifyScorecardUploadButtonDisable","UploadButtonDisable is not present");
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
	
	public void CommonSingleUploadScorecardDocument(String FeatureName, String SheetName, int rowNum, String Commodity,
			String FileName, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
			Boolean NoteRequired, Boolean IntentCheckboxRequired) throws Exception {
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
		if (Commodity.contains("Portfolio")) {
			Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		} else {
			Feature = CommonMethod.findElements("RatingFeatureName");
		}
		if (TestCaseName.contains("PortfolioOptnRating")) {
			Feature = CommonMethod.findElements("RatingFeatureName");
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
				CommonMethod.RobustclickElementVisible("WPRVerficationTab", "WPRAddOption");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOption", 0);
				CommonMethod.JavascriptClickElement("WPRAddOption");
				if (FileName.contains("Audit") || FileName.contains("Feature")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionbtn", 0);
					CommonMethod.RobustclickElementVisible("WPRAddOptionbtn", "ScorecardAddedOption");
					testlog.info("And User clicks on save button");
				}
				if (FileName.contains("Alternative")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecrdAlterntives", 0);
					CommonMethod.RobustclickElementVisible("ScorecrdAlterntives", "ScorecardAlternativeAddBtn");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAlternativeAddBtn", 0);
					CommonMethod.Robustclick("ScorecardAlternativeAddBtn");
					CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("ScorecardAlternativeAddBtn", 1);
				}
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAddedOption", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionCloseIcon", 0);
				CommonMethod.Robustclick("WPRAddOptionCloseIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRAddOptionCloseIcon", 1);
				generic.assignLocationGeneric(Commodity, true, false, false, false, false);
				/** Upload Document for Tasks */
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.scrollDown();
				if (TestCaseName.equalsIgnoreCase("Portfolio_CTC_03_09_DataReportUploadButton")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UploadButtonDataReport", 0);
					CommonMethod.JavascriptClickElement("UploadButtonDataReport");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadFeatureName", 0);
					CommonMethod.sendKeys("PortfolioScoreCardVerificationAddNote", "NoteComment");
				}
				if (TestCaseName.equalsIgnoreCase("Portfolio_CTC_03_08_MaintenanceReportUploadButton")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UploadButtonMaintenanceReport", 0);
					CommonMethod.JavascriptClickElement("UploadButtonMaintenanceReport");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadFeatureName", 0);
					CommonMethod.sendKeys("PortfolioScoreCardVerificationAddNote", "NoteComment");
				}
				if (TestCaseName.equalsIgnoreCase("Equity_TC_09_02_AuditUploadDocumentScorecard")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("UploadButtonAudit", 0);
					CommonMethod.JavascriptClickElement("UploadButtonAudit");
				} else {
					CommonMethod.JavascriptClickElement("Uploadbutton");
				}
				if (!FileName.contains("Alternative")) {
				CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ValidateExpandedAddFeaturePart",1);
				CommonMethod.negativeAssertElementPresentTrue("ValidateExpandedAddFeaturePart", "Add Feature Part(s) is not Expanded ");
				System.out.println("Validated Expanded Add Feature Part");
				}
				generic.uploadModalCompleteGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired,
						VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.scrolldowntoElement("Uploadbutton");
				pathprms.put("Status", "Ready For Review");
				generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, false, false, false,
						false, true, true, false);
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
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
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void CommonSingleUploadScorecardDocumentInOptn(String FeatureName, String SheetName, int rowNum,
			String Commodity, String FileName, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
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
					CommonMethod.Robustclick("WPRAddOptionCloseIcon");
					CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRAddOptionCloseIcon", 1);
					generic.assignLocationGeneric(Commodity, true, false, false, false, false);
				}
				if (FileName.contains("Audit")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioHsrOptnUploadButtonAudit", 0);
					CommonMethod.JavascriptClickElement("PortfolioHsrOptnUploadButtonAudit");
				} else {
					CommonMethod.JavascriptClickElement("Uploadbutton");
				}
				generic.uploadModalCompleteGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired,
						VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
				CommonMethod.scrolldowntoElement("Uploadbutton");
				pathprms.put("Status", "Ready For Review");
				generic.validateUploadedDocumentTableGeneric(SheetName, rowNum, Commodity, false, false, false, false,
						false, true, true, false);
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
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
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void CommonSingleUploadUpdateScorecardDocument(String FeatureName, String PartName, String SheetName,
			int rowNum, String Commodity, String FileName, Boolean PartNameRequired,
			Boolean VerificationMethodValidationRequired, Boolean NoteRequired, Boolean IntentCheckboxRequired)
			throws Exception {
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
		if (Commodity.contains("Portfolio")) {
			Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		} else {
			Feature = CommonMethod.findElements("RatingFeatureName");
		}
		boolean flag = false;
		testlog.info("Fetching total no. of credits on page");
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(FeatureName)) {
				flag = true;
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						PortfolioAndRatingLocAccDocumentTable);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
				CommonMethod.scrolldowntoElement("Uploadbutton");
				rc.documentTableEditButton();
				testlog.info("And User clicks on edit doc icon");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WERScorecardRemovePart", 0);
				if (!FileName.contains("Audit")) {
					testlog.info("And User clicks on Add feature");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadAddfeature", 0);
					CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature","PortfolioScoreCardVerificationSelectFeature");
					CommonMethod.scrolldowntoElement("PortfolioScoreCardVerificationUploadAddfeature");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
							"PortfolioScoreCardVerificationSelectFeature", 0);
					CommonMethod.selectdropdownVisibletext("PortfolioScoreCardVerificationSelectFeature", PartName);
					if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectSpaceType", 5)) {
						CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectSpaceType");
					}
					if (CommonMethod.isElementsExist("PortfolioScoreCardVerificationSelectOption", 5)) {
						CommonMethod.selectdropdownrandom("PortfolioScoreCardVerificationSelectOption");
					}
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddPart", 0);
					CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationAddPart",
							"PortfolioScoreCardVerificationUploadAddfeature");
					CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
							"PortfolioScoreCardVerificationAddNote");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RemoveOption", 0);
					if (!TestNGTestName.contains("WPR-Renewal")) {
					CommonMethod.Robustclick("RemoveOption");
					testlog.info("And User clicks on Remove part");
					CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("RemoveOption", 1);
					}
					CommonMethod.RobustclickElementVisible("PortfolioScoreCardVerificationUploadAddfeature",
							"PortfolioScoreCardVerificationSelectFeature");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardUploadRemovelink", 0);
					testlog.info("And User verifies Removelink should be visible");
				}
				rc.ScorecardUploadUpdateSaveButton();
				if (TestNGTestName.contains("WPR-Renewal")) {
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAssignLocCbx", 0);
					CommonMethod.ClickCheckbox("WPRAssignLocCbx");
				}
				rc.ScorecardConfirmLocUploadSaveButton();
				testlog.info("And User clicks on update button");
				testlog.info("Then User verifies toaster message for add part");
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}

	public void editAdminAchieveDate(String saveAchievementAdminTabXpath) throws Exception {
		testlog.info("Given User is on Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
		CommonMethod.RobustclickElementVisible("EditTab", "AchievementAdminTab");
		testlog.info("When User clicks on EditTab");
		rc.navigateAchievementAdminTab();
		testlog.info("And User clicks on AdminFields Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditAchievedStatus", 0);
		CommonMethod.ClickCheckbox("EditAchievedStatus");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AwardedDate", 0);
		CommonMethod.RobustclickElementVisible("AwardedDate", "DatePickerOkButton");
		Thread.sleep(1000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.Robustclick("DatePickerOkButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DatePickerOkButton", 1);
		rc.saveAchievementAdminTab(saveAchievementAdminTabXpath);
	}
	
	public void editAdminSelctAchieveAndDate(String selectStatus, String Status, String saveAchievementAdminTabXpath, String EditAchieveDate) throws Exception {
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
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.Robustclick("DatePickerOkButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DatePickerOkButton", 1);
		rc.saveAchievementAdminTab(saveAchievementAdminTabXpath);
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 30)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
	}

	public void documentTableEditButton() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAndRatingDeleteEditMenu", "PortfolioAndRatingLocAccDocumentTableEditIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableEditIcon", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAndRatingLocAccDocumentTableEditIcon",
				"PortfolioScoreVerifyUploadVerificationMethod");
		testlog.info("When User clicks on Edit button");
	}
	
	public void documentTableSelectEditButton(String Locator) throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(Locator, 0);
		CommonMethod.RobustclickElementVisible(Locator, "ScorecardTableEditIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardTableEditIcon", 0);
		CommonMethod.click("ScorecardTableEditIcon");
		testlog.info("When User clicks on Edit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreVerifyUploadVerificationMethod", 0);
	}
	

	public void documentTableDeleteButton() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);
		CommonMethod.click("PortfolioAndRatingDeleteEditMenu");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTableDeleteIcon, 0);
		CommonMethod.RobustclickElementVisible(PortfolioAndRatingLocAccDocumentTableDeleteIcon, "DeleteButton");
		testlog.info("When User clicks on Delete button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DeleteButton", 0);
		CommonMethod.Robustclick("DeleteButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("DeleteButton", 1);
		testlog.info("And User clicks on Delete Icon");
		testlog.info("And User clicks on Yes Delete button");
		if (CommonMethod.isElementsExist("DocumentAddedToasterMessage", 12)) {
			CommonMethod.WaitUntilInVisibility("DocumentAddedToasterMessage", 30);
			testlog.info("And User verifies Delete Toast Message Sucessfully");
		}
		testlog.info("And User verifies Delete Toast Message Sucessfully");
		if (!(TestCaseName.equalsIgnoreCase("Portfolio_CTC_02_07_DeleteInDocumentLibrary")
				|| TestCaseName.equalsIgnoreCase("Portfolio_TC_22_03_ValidateRestrictAccessAdminInDocumentLibrary")
				|| TestCaseName.equalsIgnoreCase("Portfolio_TC_22_05_ValidateRestrictAccessTeamManagerInDocument")
				|| TestCaseName.equalsIgnoreCase("Portfolio_CTC_07_08_DeleteDocumentUploadInDocumentLibrary")
				|| TestCaseName.equalsIgnoreCase("Equity_TC_21B_04_ValidateTeamManagerAInDocument")
				|| TestCaseName.equalsIgnoreCase("Equity_TC_21B_05_ValidateAdminInDocument")
				|| TestCaseName.equalsIgnoreCase("Portfolio_TC_22_03_ValidateRestrictAccessAdminInDocumentLibrary"))) {
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException(PortfolioAndRatingLocAccDocumentTableTr);
			CommonMethod.assertisElementPresentFalse("PortfolioAndRatingLocAccDocumentTableTr",
					"Delete Document table list visible");
		}
		testlog.info("And User verifies Document table list count");
	}

	public void VaidDeleteButton() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAndRatingDeleteEditMenu",
				PortfolioAndRatingLocAccDocumentTableDeleteIcon);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTableDeleteIcon, 0);
		CommonMethod.assertisElementPresentTrue(PortfolioAndRatingLocAccDocumentTableDeleteIcon,
				"Delete icon element not present");
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("PortfolioAndRatingLocAccDocumentLegalTableReplaceIcon");
		 CommonMethod.assertisElementPresentFalse("PortfolioAndRatingLocAccDocumentLegalTableReplaceIcon","Replace icon is visible");
		testlog.info("And User verifies Delete Icon in Document table");
		testlog.info("And User verifies Replace Icon shouldn't present in Document table");
	}

	public void deleteButtonTooltipMessage() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);
		CommonMethod.click("PortfolioAndRatingDeleteEditMenu");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTableDeleteIcon, 0);
		CommonMethod.RobustclickElementVisible(PortfolioAndRatingLocAccDocumentTableDeleteIcon,"PortfolioDocToolTipDelete");
		testlog.info("When User clicks on Delete button");
	}

	public void editButtonTooltipMessage() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);
		CommonMethod.click("PortfolioAndRatingDeleteEditMenu");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableEditIcon", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAndRatingLocAccDocumentTableEditIcon",
				"PortfolioDocToolTipDelete");
		testlog.info("When User clicks on Delete button");
	}
	
	public void documentTableReplaceButton() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingDeleteEditMenu", 0);
		CommonMethod.click("PortfolioAndRatingDeleteEditMenu");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableReplaceIcon", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAndRatingLocAccDocumentTableReplaceIcon", "PortfolioScoreVerifyUploadVerificationMethod");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreVerifyUploadVerificationMethod", 0);
		testlog.info("And User clicks on Replace button");
	}

	public void locationNavigate() throws Exception {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LocationsTab", 0);
		CommonMethod.RobustclickElementVisible("LocationsTab", "PortfolioLocationLanding");
		testlog.info("When User clicks on LocationsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioLocationLanding", 0);
	}

	public void alternativesAAPNew(String SheetName, int rowNum, String optModuleName, String featureName)
			throws IOException, InterruptedException {
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAapSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAapSubmitButton", "V2ProjectFeatureDropdown");
		testlog.info("And User clicks on AAPSubmit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitButton", 0);
		CommonMethod.RobustclickElementVisible("SubmitButton", "V2ProjectFeatureDropdown");
		CommonMethod.negativesoftassertPageSource("Feature* is required.", "Feature Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Reason for Alternative Means and Methods * is required.",
				"Reason for Alternative Error Mismatch");
		if (SheetName.equalsIgnoreCase("Portfolio")) {
			CommonMethod.negativesoftassertPageSource("Applicable part(s) * is required.",
					"Applicable part Error Mismatch");
		}
		CommonMethod.scrollUp();
		if (SheetName.equalsIgnoreCase("V2Project")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAlternativeVersionDropdown", 0);
			CommonMethod.selectdropdownValue("V2ProjectAlternativeVersionDropdown", optModuleName);
		}
		Thread.sleep(1000);
		CommonMethod.WaitUntilClickble("V2ProjectAlternativesReasonTextArea", 60)
				.sendKeys("Reason for Alternative Means and Methods");
		CommonMethod.negativesoftassertPageSource("Proposed Alternative Means of Compliance * is required.",
				"Proposed Alternative Means of Compliance Error Mismatch");
		CommonMethod.WaitUntilClickble("V2ProjectAlternativesProposedTextArea", 60)
				.sendKeys("Proposed Alternative Means of Compliance");
		CommonMethod.uploadMultipleFile("DocumentsUpload", SampleJpgfile, SampleJpgfile, "MultipeUploadDeleteicon", 2,
				"MultipeUploadEnableButtonDeleteLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectFeatureDropdown", 0);
		Thread.sleep(3000);
		CommonMethod.selectdropdownVisibletext("V2ProjectFeatureDropdown", featureName);
		if (CommonMethod.isElementsExist("V2ProjectApplicablePartCheckBox", 10)) {
			CommonMethod.ClickCheckbox("V2ProjectApplicablePartCheckBox");
			testlog.info("And User checks the ApplicablePart checkbox");
		}
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
		if (SheetName.equalsIgnoreCase("Community")) {

			CommonMethod.sendKeys("CommunityAlternativeAAPApplicableParts", "Applicable Part Text");
		}
		testlog.info("And User Upload Feature Document");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitButton", 0);
		CommonMethod.RobustclickElementVisible("SubmitButton", "V2projectAAPTypeStatus");
		testlog.info("And User clicks on Submit button");
		if (SheetName.equalsIgnoreCase("V2Project") || SheetName.equalsIgnoreCase("Community")) {
			CommonMethod.scrollUp();
			if (optModuleName.equalsIgnoreCase("v2-hsr")) {
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateHSR");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateHSR"),
						"WELL HEALTH-SAFETY", "WELL HEALTH-SAFETY doesn't match");
			}
			if (optModuleName.equalsIgnoreCase("wpr")) {
				String getId = CommonMethod.getattributeValueByTextContent("CommunityAlternativeID");
				String[] stringArray = getId.split("-");
				String getAlternativeID = stringArray[1].trim();
				data.setCellData(SheetName, "AlternativeAapID", rowNum, getAlternativeID);
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateWPR");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateWPR"),
						"WELL PERFORMANCE", "WELL HEALTH-SAFETY doesn't match");
			}
			if (optModuleName.equalsIgnoreCase("wer")) {
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateWER");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateWER"),
						"WELL EQUITY", "WELL HEALTH-SAFETY doesn't match");
			}
			if (optModuleName.equalsIgnoreCase("CommunityAAP")) {
				String getId = CommonMethod.getattributeValueByTextContent("CommunityAlternativeID");
				String[] stringArray = getId.split("-");
				String getCommunityAlternativeID = stringArray[1].trim();
				data.setCellData(SheetName, "CommunityAlternativeID", rowNum, getCommunityAlternativeID);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("CommunityAlternativeType"), "AAP",
						"Table FEATURE TYPE doesn't match");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("CommunityAlternativeStatus"), "PENDING",
						"Table FEATURE STATUS doesn't match");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("CommunityAlternativeOwner"), "UI",
						"Table FEATURE OWNER name doesn't match");
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateWER");
				CommonMethod.negativeAssertElementNotPresentFalse("V2ProjectAdminAlternativeEditButton", "Edit button is visible for non-admin user");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("CommunityAlternativeInsideStatus"),
						"Pending decision", "STATUS Pending decision doesn't match");
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getText("V2ProjectAdminAlternativeMeansCompliance"),
						"Proposed Alternative Means of Compliance", "Means of Compliance Text doesn't match");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("CommunityAlternativeValidateWV1"),
						"WELL V1", "WELL V1 doesn't match");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommunityAlternativeValidateImage", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommunityAlternativeValidateImage", 0);
			}
			CommonMethod.RobustclickElementVisible("V2ProjectAlternativeBackButton", "V2ProjectAapSubmitButton");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectAAPTypeStatus", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2projectAAPTypeStatus"), "AAP",
					"AAP Alternative doesn't match");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("StoreHsrAAPAlternativeId", 0);
		String StoreHsrAAPAlternativeId = CommonMethod.getattributeValueByTextContent("StoreHsrAAPAlternativeId");
		String[] AAPAlternativeId = StoreHsrAAPAlternativeId.split("-");
		String AAPId = AAPAlternativeId[1];
		data.setCellData(SheetName, "AlternativeHsrAAPID", rowNum, AAPId);
		testlog.info("AlternativeAAPID: " + AAPId);
		System.out.println("AlternativeAAPID: " + AAPId);
		testlog.info("And User verifies the added details");
		testlog.info("And User verifies the Status");
		testlog.pass("**Added alternative AAP documents successfully**");
	}

	public void alternativesEPNew(String SheetName, int rowNum, String ProjectType, String optModuleName, String featureName)
			throws IOException, InterruptedException {		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEPSubmitButton", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectEPSubmitButton", "V2ProjectFeatureDropdown");
		if (SheetName.equalsIgnoreCase("V2Project") && (!ProjectType.contains("pilot"))) {
			CommonMethod.verifyDropdownValues("V2ProjectFeatureDropdown", "V2ProjectAlternativeFeature");
		}
		testlog.info("And User clicks on EPSubmit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitButton", 0);
		CommonMethod.RobustclickElementVisible("SubmitButton", "V2ProjectFeatureDropdown");		
		testlog.info("And User clicks on Submit button");
		CommonMethod.negativesoftassertPageSource("Feature* is required.", "Feature Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Reason for Equivalency Request * is required.",
				"Reason for Equivalency Error Mismatch");
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
		CommonMethod.scrollUp();
		if (SheetName.equalsIgnoreCase("V2Project")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAlternativeVersionDropdown", 0);
			CommonMethod.selectdropdownValue("V2ProjectAlternativeVersionDropdown", optModuleName);
		}
		Thread.sleep(2000);
		CommonMethod.negativesoftassertPageSource(
				"Regions/Countries where Equivalency may be Applicable * is required.",
				"Regions/Countries Equivalency Error Mismatch");
		CommonMethod.WaitUntilClickble("V2ProjectEquivalencyReason", 60).sendKeys("Reason for Equivalency Request");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentsUpload", 0);
		CommonMethod.uploadMultipleFile("DocumentsUpload", SampleJpgfile, SampleJpgfile, "MultipeUploadDeleteicon", 2,
				"MultipeUploadEnableButtonDeleteLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectFeatureDropdown", 0);
		CommonMethod.selectdropdownVisibletext("V2ProjectFeatureDropdown", featureName);
		testlog.info("And User select the Feature");
		data.setCellData(SheetName, "FeatureName", rowNum,
				CommonMethod.getSelectedDropdownValue("V2ProjectFeatureDropdown"));
		testlog.info("FeatureName: " + data.getCellData(SheetName, "FeatureName", rowNum));
		if (CommonMethod.isElementsExist("V2ProjectApplicablePartCheckBox", 10)) {
			CommonMethod.ClickCheckbox("V2ProjectApplicablePartCheckBox");
			testlog.info("And User checks the ApplicablePart checkbox");
		}
		CommonMethod.WaitUntilClickble("V2ProjectEquivalencyCountriesInput", 60)
				.sendKeys("Regions/Countries where Equivalency may be Applicable");
		if (CommonMethod.isElementsExist("V2ProjectVerificationTextArea", 10)) {
			CommonMethod.negativesoftassertPageSource("Verification method within proposed equivalent * is required.",
					"Verification method within proposed equivalent Error Mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectVerificationTextArea", 0);
			CommonMethod.clearAndSendKey("V2ProjectVerificationTextArea",
					"Verification method within proposed equivalent");
			testlog.info("And User enter data to Verification field");
		}
		if (CommonMethod.isElementsExist("PortfolioVerificationTextArea", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioVerificationTextArea", 0);
			CommonMethod.clearAndSendKey("PortfolioVerificationTextArea",
					"Verification method within proposed equivalent");
			testlog.info("And User enter data to Verification field");
		}
		if (CommonMethod.isElementsExist("V2ProjectSimilaritiesTextArea", 10)) {
			CommonMethod.negativesoftassertPageSource("Similarities to WELL feature requirement * is required.",
					"Similarities to WELL feature requirement Error Mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectSimilaritiesTextArea", 0);
			CommonMethod.clearAndSendKey("V2ProjectSimilaritiesTextArea", "Similarities to WELL feature requirement");
			testlog.info("And User enter data to Similarities field");
		}
		if (CommonMethod.isElementsExist("PortfolioSimilaritiesTextArea", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioSimilaritiesTextArea", 0);
			CommonMethod.clearAndSendKey("PortfolioSimilaritiesTextArea", "Similarities to WELL feature requirement");
			testlog.info("And User enter data to Similarities field");
		}
		if (CommonMethod.isElementsExist("V2ProjectDifferencesTextArea", 10)) {
			CommonMethod.negativesoftassertPageSource("Differences from WELL feature requirement * is required.",
					"Differences from WELL feature Error Mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDifferencesTextArea", 0);
			CommonMethod.clearAndSendKey("V2ProjectDifferencesTextArea", "Differences from WELL feature requirement");
			testlog.info("And User enter data to Differences field");
		}
		if (CommonMethod.isElementsExist("PortfolioDifferencesTextArea", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDifferencesTextArea", 0);
			CommonMethod.clearAndSendKey("PortfolioDifferencesTextArea", "Differences from WELL feature requirement");
			testlog.info("And User enter data to Differences field");
		}
		CommonMethod.negativesoftassertPageSource("Proposed Alternative Means of Compliance * is required.",
				"Proposed Alternative Means of Compliance Error Mismatch");
		CommonMethod.WaitUntilClickble("V2ProjectAlternativesProposedTextArea", 60)
				.sendKeys("Proposed Alternative Means of Compliance");
		testlog.info(
				"And User clicks on Submit button without entering the mandatory fields and verifies error meassage");
		testlog.info("And User upload feature document");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubmitButton", 0);
		CommonMethod.RobustclickElementVisible("SubmitButton", "V2projectEPTypeStatus");
		testlog.info("And User clicks on Submit button");
		CommonMethod.scrollUp();
		if (SheetName.equalsIgnoreCase("V2Project")) {
			if (optModuleName.equalsIgnoreCase("v2-hsr")) {
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateHSR");
				System.out.println("HSR Value:" + CommonMethod.getText("V2ProjectAlternativeValidateHSR"));
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateHSR"),
						"WELL HEALTH-SAFETY", "WELL HEALTH-SAFETY doesn't match");
			}
			if (optModuleName.equalsIgnoreCase("wpr")) {
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateWPR");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateWPR"),
						"WELL PERFORMANCE", "WELL PERFORMANCE doesn't match");
			}
			if (optModuleName.equalsIgnoreCase("wer")) {
				ClickOnAlterativeRows("V2ProjectAlternativeRow", "V2ProjectAlternativeValidateWER");
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2ProjectAlternativeValidateWER"),
						"WELL EQUITY", "WELL EQUITY doesn't match");
			}
			CommonMethod.RobustclickElementVisible("V2ProjectAlternativeBackButton", "V2ProjectEPSubmitButton");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectEPTypeStatus", 0);
			CommonMethod.negativesoftassertFieldValid("V2projectEPTypeStatus", "EP", "EP Alternative doesn't match");
		}
		if (SheetName.equalsIgnoreCase("V2Pilot_Portfolio")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAlternative", 0);
			List<String> val = CommonMethod.fetchTableData("V2ProjectAlternative");
			CommonMethod.negativesoftassertFieldValid(val.get(6), "EP", "EP Alternative doesn't match");
		} else {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2projectEPTypeStatus", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("V2projectEPTypeStatus"), "EP",
					"EP Alternative doesn't match");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("StoreHsrEPAlternativeId", 0);
		String StoreHsrEPAlternativeId = CommonMethod.getattributeValueByTextContent("StoreHsrEPAlternativeId");
		String[] AAPAlternativeId = StoreHsrEPAlternativeId.split("-");
		String EPId = AAPAlternativeId[1];
		data.setCellData(SheetName, "AlternativeHsrEPID", rowNum, EPId);
		testlog.info("StoreHsrEPAlternativeId: " + EPId);
		System.out.println("StoreHsrEPAlternativeId: " + EPId);
		testlog.info("Then User verifies the added details");
		testlog.info("And User verifies the Status");
		testlog.pass("**Added alternative EP documents successfully**");
	}

	public void AlternativeChangeStatusAndValidate(String SheetName, int rowNum, String Module)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "V2ProjectAdminAlternativeNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminAlternativeNavBar", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAdminAlternativeNavBar",
				"V2ProjectAdminAlternativeStrategyTextBox");
		if (Module.equalsIgnoreCase("EP")) {
			String getCommunityAlternativeEPID = data.getCellData(SheetName, "AlternativeHsrEPID", rowNum);
			CommonMethod.sendKeys("V2ProjectAdminAlternativeStrategyTextBox", getCommunityAlternativeEPID);
		} else {
			String getCommunityAlternativeID = data.getCellData(SheetName, "AlternativeHsrAAPID", rowNum);
			CommonMethod.sendKeys("V2ProjectAdminAlternativeStrategyTextBox", getCommunityAlternativeID);
		}
		CommonMethod.click("ApplyButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommunityAlternativeIDLink", 0);
		CommonMethod.RobustclickElementVisible("CommunityAlternativeIDLink", "V2ProjectAdminAlternativeEditButton");
		community.validateCommunityAlternativeStatus(SheetName, rowNum, "Not Approved", "WELL HEALTH-SAFETY");
		community.validateCommunityAlternativeStatus(SheetName, rowNum, "Approved", "WELL HEALTH-SAFETY");
		community.validateCommunityAlternativeStatus(SheetName, rowNum, "Additional Information Requested",
				"WELL HEALTH-SAFETY");
	}

	public void confirmPursing() throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Confirmbtn", 0);
		CommonMethod.Robustclick("Confirmbtn");
		testlog.info("And User click on Cancel button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardPursueToast", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("PortfolioScorecardPursueToast"), "Pursue status changed!",
				"Purse Status message Doesn't match");
		testlog.info("And User verifies the Pursue status message");
	}

	public void validateCOA() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ReceiptCOA", 1);
		CommonMethod.assertisElementPresentTrue("ReceiptCOA", "COA button is not visible");
		CommonMethod.JavascriptClickElement("ReceiptCOA");
		Thread.sleep(2000);
		CommonMethod.switchToChildTab();
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getCurrentUrl(), "Confirmation-of-Agreement.pdf",
				"Confirmation-of-Agreement doesn't match");
		CommonMethod.switchToParentTab();
	}

	public void validateCOAWithPercert() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ReceiptCOAWithPre", 1);
		CommonMethod.assertisElementPresentTrue("ReceiptCOAWithPre", "COA with Percertificate button is not visible");
		CommonMethod.JavascriptClickElement("ReceiptCOA");
		Thread.sleep(2000);
		CommonMethod.switchToChildTab();
		Thread.sleep(2000);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getCurrentUrl(), "Confirmation-of-Agreement.pdf",
				"Confirmation-of-Agreement doesn't match");
		CommonMethod.switchToParentTab();
	}
	
	public void ValidateSpaceTypeDropdown(String SpaceTypeObjectLocator, String expectedSpaceTypeCount) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(SpaceTypeObjectLocator, 0);
		int SpaceTypeCount = CommonMethod.ElementSize(SpaceTypeObjectLocator);
		String actualSpaceTypeCount=Integer.toString(SpaceTypeCount);
		CommonMethod.negativesoftassertFieldValidEquals(actualSpaceTypeCount, expectedSpaceTypeCount, "SpaceTypeCount does not matched ");
	}
	
public void beforeBilling(String SheetName, int rowNum) throws IOException, InterruptedException {
	testlog.info("Given User is on Dashboard page");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BiilingTab", 0);
	CommonMethod.RobustclickElementVisible("BiilingTab", "V2ProjectPreBillingPayNowButton");
	testlog.info("When User clicks on BiilingTab");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingStatus", 0);
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("BillingStatus").replaceAll("\\s+", " ").trim(), "UNPAID",
			"Billing Status doesn't match");
	testlog.info("And User verifies the Reg Status");
	String RegFee;
	if(SheetName.equalsIgnoreCase("Portfolio")) {
	RegFee ="PortfolioBillingRegFee";
	}
	else {
		RegFee ="BillingRegFee";
	}
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(RegFee, 0);
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent(RegFee).replaceAll("\\s+", " ").trim(), data.getCellData(SheetName, "BillingRegFee", rowNum),
			"Billing RegFee doesn't match");	
	testlog.info("And User verifies the RegStatus status");
	CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectPreBillingPayNowButton", 1);
	CommonMethod.assertisElementPresentTrue("V2ProjectPreBillingPayNowButton", "PayNowButton is not visible");
	CommonMethod.RobustclickElementVisible("V2ProjectPreBillingPayNowButton", "BillingLanding");
	testlog.info("And User clicks on PayNow Button");
	Thread.sleep(3000);
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingInvoiceRegFee", 0);
	CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("BillingInvoiceRegFee").replaceAll("\\s+", " ").trim(), data.getCellData(SheetName, "BillingRegFee", rowNum),
			"Billing RegFee doesn't match");
	testlog.info("And User verifies the RegFee");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CardCheckbox", 0);
	CommonMethod.VerifyRadioOrCheckboxSelcted("CardCheckbox");
	testlog.pass("**Nagavited to Billing and verify Billing page successfully**");
	}
	
	public void afterBilling(String SheetName, int rowNum) throws IOException, InterruptedException {
		rc.RemoveSpaceAndValidate("BillingStatus", "paid");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("BillingStatus").replaceAll("\\s+", " "), "paid",
				"Billing Status doesn't match");	
		String RegFee;
		if(SheetName.equalsIgnoreCase("Portfolio")) {
		RegFee ="PortfolioBillingRegFee";
		}
		else {
			RegFee ="BillingRegFee";
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(RegFee, 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent(RegFee).replaceAll("\\s+", " ").trim(), data.getCellData(SheetName, "BillingRegFee", rowNum),
				"Billing RegFee doesn't match");	
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("V2ProjectPreBillingPayNowButton");
		CommonMethod.assertisElementPresentFalse("V2ProjectPreBillingPayNowButton", "PayNowButton is visible");
		
	}
	
	public void navigateAchievementAdminTab() throws IOException, InterruptedException {
		testlog.info("Given User is on AchievementAdminTab page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AchievementAdminTab", 0);
		CommonMethod.RobustclickElementVisible("AchievementAdminTab", "HSRScorecardAddPartButton");
}
	public void saveAchievementAdminTab(String statusXpath) throws IOException, InterruptedException {
		testlog.info("And User is on AchievementAdminTab page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(statusXpath, 0);
		CommonMethod.JavascriptClickElement(statusXpath);
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan(statusXpath, 1);
		if (CommonMethod.isElementsExist("ScorecardBannerClose", 10)) {
			CommonMethod.Robustclick("ScorecardBannerClose");
		}
	}
	
	   public void validateEditDisabledOptionAndTooltipMessage(String EditMenuIconXpath, String ToolTipMessage) throws IOException, InterruptedException {
		   CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
	 		testlog.info("And User verifies Document Upload Table");
		   CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(EditMenuIconXpath, 0);
	 		CommonMethod.click(EditMenuIconXpath);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableEditIcon", 0);
			CommonMethod.RobustclickElementVisible("PortfolioAndRatingLocAccDocumentTableEditIcon", "DisabledEditBtn");
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("DisabledEditBtn",1);
			CommonMethod.negativeAssertElementPresentTrue("DisabledEditBtn","EditBtn disabled is not visible");
			 CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioDocToolTipEdit",1);
			 CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioDocToolTipEdit").replaceAll("\\s+", " "), ToolTipMessage, "Replacebtn disabled TooltipMessage doesn't match");
			 testlog.pass("**Verifies the Document Disabled Tooltip message successful**");
		}
	
	public void validateReplaceDisabledOptionAndTooltipMessage(String EditMenuIconXpath, String ToolTipMessage) throws IOException, InterruptedException {
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
	 		testlog.info("And User verifies Document Upload Table");
     	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableReplaceIcon", 0);
		CommonMethod.RobustclickElementVisible("PortfolioAndRatingLocAccDocumentTableReplaceIcon", "Replacebtndisabled");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("Replacebtndisabled",1);
		CommonMethod.negativeAssertElementPresentTrue("Replacebtndisabled","EditBtn disabled is not visible");
		 CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ReplacebtndisabledTooltipMessage",1);
		 CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ReplacebtndisabledTooltipMessage").replaceAll("\\s+", " "), ToolTipMessage, "Editbtn disabled TooltipMessage doesn't match");
		 testlog.pass("**Verifies the Document Disabled Tooltip message successful**");
	}

     public void validateArchiveDisabledOptionAndTooltipMessage(String EditMenuIconXpath, String ToolTipMessage) throws IOException, InterruptedException {
    	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
 		testlog.info("And User verifies Document Upload Table");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardArchiveMenuBtn", 0);
 		CommonMethod.RobustclickElementVisible("PortfolioScorecardArchiveMenuBtn", "Archivebtndisabled");
 		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("Archivebtndisabled",1);
		CommonMethod.negativeAssertElementPresentTrue("Archivebtndisabled","Archivebtn disabled is not visible");
 		 CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioDocToolTipDelete",1);
 		 CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioDocToolTipDelete").replaceAll("\\s+", " "), ToolTipMessage, "Archievebtn disabled TooltipMessage doesn't match");
 		 testlog.pass("**Verifies the Document Disabled Tooltip message successful**");
}
     
     public void validateDeleteDisabledOptionAndTooltipMessage(String EditMenuIconXpath, String ToolTipMessage) throws IOException, InterruptedException {
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableDeleteIcon", 0);
 		CommonMethod.RobustclickElementVisible("PortfolioAndRatingLocAccDocumentTableDeleteIcon", "Deletebtndisabled");
 		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("Deletebtndisabled",1);
		CommonMethod.negativeAssertElementPresentTrue("Deletebtndisabled","Archivebtn disabled is not visible");
 		 CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioDocToolTipDelete",1);
 		 CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioDocToolTipDelete").replaceAll("\\s+", " "), ToolTipMessage, "Archievebtn disabled TooltipMessage doesn't match");
 		 testlog.pass("**Verifies the Document Disabled Tooltip message successful**");
}
	
     public void NavigateToDocumentTab() throws IOException, InterruptedException {
    		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocumentListLink", 0);
    		testlog.info("Then User will be redirected to DocumentList page");
    		CommonMethod.RobustclickElementVisible("PortfolioDocumentListLink", PortfolioAndRatingLocAccDocumentTable);
    		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PortfolioAndRatingLocAccDocumentTable, 0);
    	//	CommonMethod.scrolldowntoElement(PortfolioAndRatingLocAccDocumentTable);
    		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
    		}

     public void ValidateProjectNameAllCases() throws IOException, InterruptedException {
  		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBar", 0);
  		CommonMethod.RobustclickElementVisible("ProjectNavBar", "WELLCertificationNavBar");
  		CommonMethod.RobustclickElementVisible("WELLCertificationNavBar", "V2Projectstartav2projectbtn");
  		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectstartav2projectbtn", 0);
  		CommonMethod.Robustclick("V2Projectstartav2projectbtn");
  		CommonMethod.Robustclick("V2ProjectstartProjectcontinuebtn");
  		rc.ValidateProjectNameWithAcceptedChar(" Aa1_-@|.");
  		rc.ValidateProjectName("beoinrocgodwcczpwvheaqlxmoksajzqesmzzihxwbydcporiirbgkbuikrawwkdxxlwisgdlkzdxjgdfrplrmrgzaxzbfseqlqeioeawiqsbyeipejyaapjmoszsitypsldmqqdsptjaozcflxtqwmvmpoubkkebqglxnlxelnjtboyqtozcxappghnahczhmeswjgfpsrhgnnnsxlxjddivslaehirhilhyfeswnbchsmijfvyeylsvvwkety", 
  				"Size of Project nickname should be less than 250 characters.");   	 
     }
     
     public void ValidateProjectName(String enterText, String expectedErrorMessage) throws IOException, InterruptedException {    	 
    	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectprojectnickname", 0);
    	 CommonMethod.clearAndSendKey("V2Projectprojectnickname", enterText); 
    	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectnicknameContinuebtn", 0);
    	 CommonMethod.JavascriptClickElement("V2ProjectnicknameContinuebtn");
    	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MandatoryFieldErrorMessage", 0);
    	 String actualErrorMessage = CommonMethod.getattributeValueByTextContent("MandatoryFieldErrorMessage");
    	 CommonMethod.negativesoftassertFieldValid(actualErrorMessage, expectedErrorMessage, "Either error message does not matched or not present ");
    	 CommonMethod.clear("V2Projectprojectnickname"); 
     }
     
     public void ValidateProjectNameWithAcceptedChar(String enterText) throws IOException, InterruptedException {
    	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2Projectprojectnickname", 0);
    	 CommonMethod.clearAndSendKey("V2Projectprojectnickname", enterText); 
    	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectnicknameContinuebtn", 0);
    	 CommonMethod.JavascriptClickElement("V2ProjectnicknameContinuebtn");  	 
    	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectnicknameNextPage", 0);
    	 String actualNicknameNextPage=CommonMethod.getattributeValueByTextContent("V2ProjectnicknameNextPage");
    	 actualNicknameNextPage= actualNicknameNextPage.replaceAll("\\s+", " ").trim();
    	 System.out.println(actualNicknameNextPage);
    	 CommonMethod.negativesoftassertFieldValid(actualNicknameNextPage, "Where is your project located?", "Next page is not showing or showing wrong ");
    	 CommonMethod.scrolldowntoElement("StreetName");
    	 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectnicknameNextPageBackBtn", 0);
    	 CommonMethod.RobustclickElementVisible("V2ProjectnicknameNextPageBackBtn", "V2Projectprojectnickname"); 
    	 CommonMethod.clear("V2Projectprojectnickname"); 
     }

     public void validateFeatureCount(int actualCount, int expectedCount, String Message) throws IOException, InterruptedException {
    	 boolean flag = false;
    	 if (Math.abs(expectedCount - actualCount) <= 5) {
    		 flag = true;
    		 System.out.println("ActualCount: " + actualCount + ", ExpectedCount: " + expectedCount);
    		}
    	 else {
    		 flag = false;
    	 }
    	 CommonMethod.negativesoftassertFieldValidEquals("true",Boolean.toString(flag), Message+" ActualCount: "+actualCount +" , ExpectedCount: "+ expectedCount);
     } 
     
     public void MarkCertifiedByAdminUser() throws IOException, InterruptedException {		
    		testlog.info("Given User is on Dashboard page");
    		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EditTab", 0);
    		CommonMethod.click("EditTab");
    		testlog.info("When User clicks on EditTab");
    		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAdminFieldsButton", 0);
    		rc.navigateAchievementAdminTab();
    		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditHsrAchieved", 0);
    	    CommonMethod.ClickCheckbox("V2ProjectEditHsrAchieved");
         	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectEditHsrDateUpsell", 0);
        	CommonMethod.RobustclickElementVisible("V2ProjectEditHsrDateUpsell", "DatePickerOkButton");
        	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
    		CommonMethod.Robustclick("DatePickerOkButton");	
    	    rc.saveAchievementAdminTab("V2ProjectEditHsrSavebtn");
}
     public void documentTableReplaceButton(String replaceXpath) throws Exception {
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(replaceXpath, 0);
 		CommonMethod.click(replaceXpath);
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableReplaceIcon", 0);
 		CommonMethod.RobustclickElementVisible("TableReplaceIcon", "PortfolioScoreVerifyUploadVerificationMethod");
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreVerifyUploadVerificationMethod", 0);
 		testlog.info("And User clicks on Replace button");
 	}
     
 	public void purseNoValidFromScorecard(String SelectPursueNoLocator, String SelectedPursueNoLocator) throws IOException, InterruptedException {
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(SelectPursueNoLocator, 0);
		CommonMethod.JavascriptClickElement(SelectPursueNoLocator);
		testlog.info("And User click on Purse No");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRCloseIcon", 1);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("getToasterMessage"), "Pursue status changed!",
				"Purse Status message Doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException(SelectedPursueNoLocator, 1);
		CommonMethod.negativeAssertElementPresentTrue(SelectedPursueNoLocator, "PurseNo is not selected");
		testlog.info("And User verifies the Selected Purse No");
		testlog.info("Then User verifies the Pursue status message");
		testlog.pass("**Verifies the Pursue No status successful**");

	}
 	
 	public void purseMaybeValidFromScorecard(String SelectPursueNoLocator, String SelectedPursueNoLocator) throws IOException, InterruptedException {
		testlog.info("Given User on Scorecard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(SelectPursueNoLocator, 0);
		CommonMethod.Robustclick(SelectPursueNoLocator);
		testlog.info("And User click on Purse Maybe");
		testlog.info("And User verifies the Selected Purse Maybe");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("WPRCloseIcon", 1);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("getToasterMessage"), "Pursue status changed!",
				"Purse Status message Doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException(SelectedPursueNoLocator, 1);
		CommonMethod.negativeAssertElementPresentTrue(SelectedPursueNoLocator, "PurseMaybe is not selected");
		testlog.info("Then User verifies the Pursue status message");
		testlog.pass("**Verifies the Pursue Maybe status successful**");

	}
 	
 	public void BillingThroughCouponCode(String SheetName, int rowNum) throws IOException, InterruptedException {
 		String getCouponCodeFromExcel = null;
 		if(Environment.equalsIgnoreCase("Test")) {
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DiscountCodeTextbox", 0);
 		getCouponCodeFromExcel = data.getCellData("Wpr", "CouponCodeTest", 2);
 		}
 		if(Environment.equalsIgnoreCase("Qas")) {
 	 	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DiscountCodeTextbox", 0);
 	 	getCouponCodeFromExcel = data.getCellData("Wpr", "CouponCodeQas", 2);	
 		}
 		CommonMethod.clearAndSendKey("DiscountCodeTextbox", getCouponCodeFromExcel);
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Applybutton", 0);
 		CommonMethod.Robustclick("Applybutton");
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateDiscountCode", 0);
 		String actualCouponCode = CommonMethod.getattributeValueByTextContent("ValidateDiscountCode");
 		actualCouponCode= actualCouponCode.replaceAll("\\s+", " ").trim();
 		CommonMethod.negativesoftassertFieldValidEquals(actualCouponCode, getCouponCodeFromExcel, "Coupon code does not matched ");
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidatePaymentAmount", 0);
 		String actualPaymentAmount = CommonMethod.getattributeValueByTextContent("ValidatePaymentAmount");
 		actualPaymentAmount= actualPaymentAmount.replaceAll("\\s+", " ").trim();
 		CommonMethod.negativesoftassertFieldValidEquals(actualPaymentAmount, "0", "Payment Amount does not matched ");
 	}
 	
	public void ValidateChangeProjectAdministratorForInvalidEmail(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRTeamChangeAdministrator", 0);
		CommonMethod.RobustclickElementVisible("WPRTeamChangeAdministrator", "WPRTeamEmailAddress");
		CommonMethod.sendKeys("WPRTeamEmailAddress", "akshay@gmail.com");
		CommonMethod.selectdropdownVisibletext("WPRTeamAdminRole", "Architect");
		CommonMethod.click("UpdateButton");
		rc.RemoveSpaceAndValidate("getToasterMessage", "The email address provided by you is not associated with a WELL Online user account");
		CommonMethod.refreshBrowser();
	}
 	public void AgreementMainlandChinaProject(String SheetName, int rowNum) throws IOException, InterruptedException {
 		testlog.info("Given User is on Dashboard page");
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BiilingTab", 0);
 		CommonMethod.RobustclickElementVisible("BiilingTab", "ProjectAgreementButton");
 		testlog.info("When User clicks on BiilingTab");		
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectAgreementButton", 0);
 		CommonMethod.RobustclickElementVisible("ProjectAgreementButton", "PortfolioUploadLocationButton");
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadLocationButton", 0);
 		CommonMethod.uploadFile("PortfolioUploadLocationButton", SamplePdffile);
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadDocbtn", 0);
 		CommonMethod.Robustclick("PortfolioScoreCardVerificationUploadDocbtn");
 		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("ValidateSignedAgreementDisabledButton",1);
 		CommonMethod.assertisElementPresentTrue("ValidateSignedAgreementDisabledButton", "Agreement Signed button is not showing ");
 	}
 	
 	public void ResetBillingMainlandChinaProject(String SheetName, int rowNum, String Amount) throws IOException, InterruptedException {
 		if(TestCaseName.equalsIgnoreCase("Performance_TC_11B_03_BillingInBillingTabMainlandChinaProjectPerformance")) {
 	 		testlog.info("Given User is on Dashboard page");
 	 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BiilingTab", 0);
 	 		CommonMethod.RobustclickElementVisible("BiilingTab", "ProjectReceiptButton");
 	 		testlog.info("When User clicks on BiilingTab");		
 		}
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectReceiptButton", 0);
 		CommonMethod.RobustclickElementVisible("ProjectReceiptButton", "BillingReceiptTypeDropdown");		
 		UploadReceiptBillingMainlandChinaProject(SheetName, rowNum, Amount);		
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ResetButton", 0);
 		CommonMethod.RobustclickElementVisible("ResetButton", "RequiredFieldError");
 		rc.RemoveSpaceAndValidate("RequiredFieldError", "Amount is required");
 	}
 	
 	public void BillingMainlandChinaProject(String SheetName, int rowNum, String Amount) throws IOException, InterruptedException {		
 		UploadReceiptBillingMainlandChinaProject(SheetName, rowNum, Amount);		
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationUploadDocbtn", 0);
 		CommonMethod.Robustclick("PortfolioScoreCardVerificationUploadDocbtn");
 	}
 	
 	public void UploadReceiptBillingMainlandChinaProject(String SheetName, int rowNum, String Amount) throws IOException, InterruptedException {
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingReceiptTypeDropdown", 0);
 		CommonMethod.selectdropdownVisibletext("BillingReceiptTypeDropdown", "Registration Receipt");
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingReceiptAmount", 0);
 		CommonMethod.clearAndSendKey("BillingReceiptAmount", Amount);
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerButton", 0); 		
 		CommonMethod.RobustclickElementVisible("DatePickerButton", "DatePickerOkButton");
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.RobustclickElementVisible("DatePickerOkButton", "PortfolioUploadLocationButton");
		testlog.info("And User select Date"); 		
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioUploadLocationButton", 0);
 		CommonMethod.uploadFile("PortfolioUploadLocationButton", SamplePdffile);
 	}
 	
 	public void ValidateMainlandChinaProjectReceipt(String SheetName, int rowNum, String PaymentStatus,
 	String FileNameAndReciptType, String ValidateAmount) throws IOException, InterruptedException {
 		rc.RemoveSpaceAndValidate("BillingReceiptPaymentStatus", PaymentStatus);
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateFileNameAndReciptType", 0);
 		rc.RemoveSpaceAndValidate("ValidateFileNameAndReciptType", FileNameAndReciptType);
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidatePaymentDate", 0);
 		String getPaymentDate=CommonMethod.getattributeValueByTextContent("ValidatePaymentDate");
 		System.out.println(getPaymentDate);
 		String[] splitPaymentDate = getPaymentDate.split(":");
 		String actualPaymentDate = splitPaymentDate[1];
 		actualPaymentDate= actualPaymentDate.replaceAll("\\s+", " ").trim();
 		System.out.println(actualPaymentDate);		
 		CommonMethod.negativesoftassertFieldValid(actualPaymentDate, CommonMethod.ValidateMonDayYearDate(), "Payment date does not matched ");
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateRmbAmount", 0);
 		rc.RemoveSpaceAndValidate("ValidateRmbAmount", ValidateAmount);
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DownloadReceiptBtn", 0);
 		CommonMethod.click("DownloadReceiptBtn");
 		CommonMethod.switchToChildTab();
 		String getCurrentURL = CommonMethod.getCurrentUrl();
 		CommonMethod.negativesoftassertFieldValid(getCurrentURL, "Resume", "Current URL does not matched ");
 		CommonMethod.switchToParentTab();
 	}
 	
 	public void EditAndUpdateBillingMainlandChinaProject(String SheetName, int rowNum, String Amount) throws IOException, InterruptedException {		
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingEditReceiptButton", 0);
 		CommonMethod.RobustclickElementVisible("BillingEditReceiptButton", "BillingReceiptTypeDropdown");
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingReceiptAmount", 0);
 		CommonMethod.clearAndSendKey("BillingReceiptAmount", Amount);		
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingUpdateBtn", 0);
 		CommonMethod.Robustclick("BillingUpdateBtn");
 		rc.RemoveSpaceAndValidate("ValidateRmbAmount", "3,500");
 	}
 	
 	public void MultiplePartValidate() throws IOException, InterruptedException {
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MultiplePart", 0);
 		String Message ="All parts must be met to achieve this feature";
 		CommonMethod.WaitUntilTextToBePresentInLocator("MultiplePart",Message, 180);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("MultiplePart"), Message,
				"MultiplePart message Doesn't match");
 	
 	}
 	
 	public void MultiplePartValidateInScorecard(String featureName, String featureXpath) throws IOException, InterruptedException {
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(featureXpath, 0);
		List<WebElement> Feature = CommonMethod.findElements(featureXpath);
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
				MultiplePartValidate();
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						"PortfolioScoreCardAddOptionbutton");
				MultiplePartValidate();
				testlog.info("And User clicks on VerificationTab");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddOptionbutton",
						"PortfolioScoreCardAddButton");
				MultiplePartValidate();
				testlog.info("And User clicks on AddOption button");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + featureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
 	
 	public void CommonUploadScorecardDocument(String FeatureName, String SheetName, int rowNum, String Commodity,
			String FileName, Boolean PartNameRequired, Boolean VerificationMethodValidationRequired,
			Boolean NoteRequired, Boolean IntentCheckboxRequired) throws Exception {
		testlog.info("Given User is on Scorecard page");
		List<WebElement> Feature;
		if (Commodity.contains("Portfolio")) {
			Feature = CommonMethod.findElements("PortfolioScoreCardFeature");
		} else {
			Feature = CommonMethod.findElements("RatingFeatureName");
		}
		if (TestCaseName.contains("PortfolioOptnRating")) {
			Feature = CommonMethod.findElements("RatingFeatureName");
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
				CommonMethod.RobustclickElementVisible("WPRVerficationTab", "WPRAddOption");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOption", 0);
				CommonMethod.JavascriptClickElement("WPRAddOption");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAllAddOptionbtn", 0);
					CommonMethod.Robustclick("WPRAllAddOptionbtn");
					testlog.info("And User clicks on save button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardAddedOption", 0);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRAddOptionCloseIcon", 0);
				CommonMethod.Robustclick("WPRAddOptionCloseIcon");
				CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("WPRAddOptionCloseIcon", 1);
				pathprms.put("StartAssignLoc", "1");
				pathprms.put("EndAssignLoc", "4");
				generic.assignLocationGeneric(Commodity, false, false, false, true, false);
				/** Upload Document for Tasks */
				List<WebElement> UploadButton;
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WprUploadAllTaskDocument", 0);
				UploadButton = CommonMethod.findElements("WprUploadAllTaskDocument");
				for (WebElement uploadButton : UploadButton) {
					CommonMethod.JavascriptClickElement(uploadButton);
					generic.uploadModalFirstScreenGeneric(SheetName, rowNum, Commodity, FileName, PartNameRequired, VerificationMethodValidationRequired, NoteRequired, IntentCheckboxRequired);
			     	rc.ScorecardConfirmLocBulkUploadSaveButton();
			    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTable", 0);
					testlog.info("And User will be redirected to Document Upload Table page");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
				}
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
 	
 	public void NAValidateInScorecard(String featureName, String featureXpath, String Commodity, String PartId) throws Exception {
 		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TableTrSize", 20);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(featureXpath, 0);
		List<WebElement> Feature = CommonMethod.findElements(featureXpath);
		testlog.info("Fetching total no. of credits on page");
		boolean flag = false;
		for (WebElement ele : Feature) {
			String Creditname = ele.getText();
			Creditname = Creditname.replaceAll("\\.", "");
			if (Creditname.equalsIgnoreCase(featureName)) {
				flag = true;
				CommonMethod.scrolldowntoElement("ApplicableVersion");
				CommonMethod.JavascriptClickElement(ele);
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecardFeatureVerificationTab", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecardFeatureVerificationTab",
						"PortfolioScoreCardAddOptionbutton");
				testlog.info("And User clicks on VerificationTab");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardAddOptionbutton", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScoreCardAddOptionbutton",
						"PortfolioScorecarNAOption");
				testlog.info("And User clicks on AddOption button");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecarNAOption", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecarNAOption", "PortfolioScorecarNAAddButton");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScorecarNAAddButton", 0);
				CommonMethod.RobustclickElementVisible("PortfolioScorecarNAAddButton",
						"PortfolioScorecarNAValidSelectedAddButtonToasterMessage");
				CommonMethod.WaitUntilNumberOfElementTextToBePresentWithException("SelectThisOptionLabel","Select this option if these requirements do not apply to one", 180);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("SelectThisOptionLabel"),
						"Select this option if these requirements do not apply to one", "SelectThisOption Label doesn't match");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationCloseicon", 0);
				CommonMethod.Robustclick("PortfolioScoreCardVerificationCloseicon");
				testlog.info("And User clicks on Closeicon");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAssignbtn", 0);
				CommonMethod.WaitUntilNumberOfElementTextToBePresentWithException("PortfolioNAOption",PartId, 180);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioNAOption"),
						PartId, "Option part id doesn't match");
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioNATask", 0);
				CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("PortfolioNATask"),
						PartId, "Task part id doesn't match");
				generic.assignLocationGeneric(Commodity, false, false, true, false, false);
					/** Upload Document for Tasks */
				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton",0);
					CommonMethod.scrollDown();
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton",0);
					CommonMethod.JavascriptClickElement("Uploadbutton");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(
							"PortfolioScoreVerifyUploadVerificationMethod", 0);
					testlog.info("And User clicks on Upload Button");
					CommonMethod.negativeAssertElementPresentTrue("PortfolioScorecarNAPdfDoc",
							"Not_Applicable document link doesn't Present");
					testlog.info("And User verifies Not Applicable document link");
					CommonMethod.negativeAssertElementNotPresentFalse("PortfolioScoreCardVerificationUpload",
							"File Attach element shouldn't Present");
					testlog.info("And User verifies Upload Feature link");
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioScoreCardVerificationAddNote", 0);
					CommonMethod.sendKeys("PortfolioScoreCardVerificationAddNote", "Test Note");
					rc.ScorecardUploadSaveButton();
					rc.ScorecardConfirmLocUploadSaveButton();
					testlog.info("And User clicks on Upload button");
					rc.uploadDocumentToastMessage();
					CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTableTr", 0);
				CommonMethod.JavascriptClickElement(ele);
				break;
			}
		}
		CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + featureName + " doesn't match");
		testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
	}
 	
 	
 	public void NavReview() throws Exception {
 	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ReviewTab", 0);
	CommonMethod.RobustclickElementVisible("ReviewTab", "HsrSubmitReview");
	testlog.info("When User clicks on Review button");
	CommonMethod.WaitUntilPresence("HsrSubmitReview", 180);
	CommonMethod.RobustclickElementVisible("HsrSubmitReview", "ValidReviewErrorMessage1");
	testlog.info("And User clicks on HsrSubmit DocReview button");
 	}
 	
 	public void WarningMessageInReview(String WarningMessage) throws Exception {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidReviewErrorMessagev2", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("ValidReviewErrorMessagev2"),
				WarningMessage, "Review Mark Error message doesn't match");
}
 	public void ScorecardAssignLoc(String FeatureName, String Commodity) throws Exception {
	testlog.info("Given User is on Scorecard page");
	List<WebElement> Feature;
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
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
			CommonMethod.scrolldowntoElement("ApplicableVersion");
			CommonMethod.JavascriptClickElement(ele);
			break;
		}
	}
	CommonMethod.assertTruebooleanCondition(flag, "Feature Name: " + FeatureName + " doesn't match");
	testlog.pass("**Verifies Scorecard Feature Uploaded Document successful**");
}
 	
 	public void clickDocumentInRobustClick() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DocumentLibraryTab", 0);
		CommonMethod.RobustclickElementVisible("DocumentLibraryTab","Uploadbutton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Uploadbutton", 0);
		testlog.info("When User clicks on DocumentTab");
		testlog.pass("**Navigate Document successfully**");
	}
 	
 	public void betaFeature() throws IOException, InterruptedException {
 		String BetaProposal = "Test BetaProposal";
 		String BetaProposalOption = "Agree";
 		if (CommonMethod.isElementsExist("ScorecardBetaProposalOption", 10)) {
 			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardBetaProposalFewSentencesText", 0);
 			CommonMethod.sendKeys("ScorecardBetaProposalFewSentencesText", BetaProposal);
 			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardBetaProposalImplementingText", 0);
 			CommonMethod.sendKeys("ScorecardBetaProposalImplementingText", BetaProposal);
 			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardBetaProposalImproveText", 0);
 			CommonMethod.sendKeys("ScorecardBetaProposalImproveText", BetaProposal);
 			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardBetaProposalOverallText", 0);
 			CommonMethod.sendKeys("ScorecardBetaProposalOverallText", BetaProposal);
 			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardBetaProposalAdditionalText", 0);
 			CommonMethod.sendKeys("ScorecardBetaProposalAdditionalText", BetaProposal);
 			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardBetaProposalExpectationsText", 0);
 			CommonMethod.selectdropdownVisibletext("ScorecardBetaProposalExpectationsText", BetaProposalOption);
 			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardBetaProposalComponentsText", 0);
 			CommonMethod.selectdropdownVisibletext("ScorecardBetaProposalComponentsText", BetaProposalOption);
 			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardBetaProposalImplementText", 0);
 			CommonMethod.selectdropdownVisibletext("ScorecardBetaProposalImplementText", BetaProposalOption);
 			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardBetaProposalDocumentText", 0);
 			CommonMethod.selectdropdownVisibletext("ScorecardBetaProposalDocumentText", BetaProposalOption);
 			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardBetaProposalRecommendText", 0);
 			CommonMethod.selectdropdownVisibletext("ScorecardBetaProposalRecommendText", BetaProposalOption);
 			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ScorecardBetaPermanentImproveText", 0);
 			CommonMethod.selectdropdownVisibletext("ScorecardBetaPermanentImproveText", BetaProposalOption);
 		}
 	}
}

	