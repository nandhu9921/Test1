package com.Well.ReusableMethods;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.zaproxy.clientapi.core.ClientApiException;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;
import com.Well.Engine.XlsReader;
import io.restassured.response.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReusableMethodsMembership extends BaseClass {

	public void RegisterMembership(String SheetName, int rowNum, String MembershipName)
			throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL certified account for Membership");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPUserMenu", 0);
		CommonMethod.RobustclickElementVisible("MPUserMenu", "MPMyMembership");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPMyMembership", 0);
		CommonMethod.RobustclickElementVisible("MPMyMembership", "MPGetStartedWithWELL");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPGetStartedWithWELL", 0);
		CommonMethod.RobustclickElementVisible("MPGetStartedWithWELL", "MPPLCommunications");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLCommunications", 0);
		testlog.info("MembershipName:" + MembershipName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLCommunications", 0);
		CommonMethod.RobustclickElementVisible("MPPLCommunications", "MPPLCommunicationsContinueBtn");
		testlog.info("And User is select on Product Licensing Communications group");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLCommunicationsContinueBtn", 0);
		CommonMethod.Robustclick("MPPLCommunicationsContinueBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLGroupNo", 0);
		CommonMethod.sendKeys("MPPLGroupNo", "3");
		testlog.info("And User enters Group numer");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLGroupNoContinueBtn", 0);
		CommonMethod.Robustclick("MPPLGroupNoContinueBtn");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLPricingContinueBtn", 0);
		CommonMethod.Robustclick("MPPLPricingContinueBtn");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPmembershipOwnerContinuebtn", 0);
		CommonMethod.RobustclickElementVisible("MPmembershipOwnerContinuebtn", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Owner name is required.", "Owner Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner email is required.", "Owner Email Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner job title is required.", "Owner job title Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner phone is required.", "Owner phone Error Mismatch");
		testlog.info(
				"When User clicks on continue button without entering the mandatory fields and verifies error meassage");
		String firstName = USfaker.address().firstName();
		String PhoneNumber = USfaker.number().digits(10);
		CommonMethod.sendKeys("Name", firstName);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("Name"));
		CommonMethod.sendKeys("MPOwnerEmail", data.getCellData("Login", "UserName", 9));
		data.setCellData(SheetName, "OwnerEmail", rowNum, CommonMethod.getattributeValue("MPOwnerEmail"));
		CommonMethod.sendKeys("MPOwnerJob", "Testing");
		data.setCellData(SheetName, "OwnerJobTitle", rowNum, CommonMethod.getattributeValue("MPOwnerJob"));
		CommonMethod.sendKeys("MPOwnerPhone", PhoneNumber);
		data.setCellData(SheetName, "OwnerPhoneNumber", rowNum, CommonMethod.getattributeValue("MPOwnerPhone"));
		testlog.info("And User enters data to Owner Name, Email, JobTitle, Phone number fields");
		testlog.info("website:" + "https://test-nuxt.wellcertified.com/");
		testlog.info("firstName:" + firstName);
		testlog.info("phonenumber: " + PhoneNumber);
		testlog.info("Name: " + data.getCellData(SheetName, "Name", rowNum));
		testlog.info("Email: " + data.getCellData(SheetName, "Email", rowNum));
		testlog.info("JobTitle: " + data.getCellData(SheetName, "JobTitle", rowNum));
		testlog.info("And User check Agree to share point of contact");
		CommonMethod.sendKeys("MPmembershipadminName", firstName);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("Name"));
		CommonMethod.sendKeys("MPmembershipadminEmail", data.getCellData(SheetName, "Email", rowNum));
		data.setCellData(SheetName, "OwnerEmail", rowNum, CommonMethod.getattributeValue("MPOwnerEmail"));
		CommonMethod.sendKeys("MPmembershipadminJob", "Testing");
		data.setCellData(SheetName, "OwnerJobTitle", rowNum, CommonMethod.getattributeValue("MPOwnerJob"));
		CommonMethod.sendKeys("MPmembershipadminPhone", PhoneNumber);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPmembershipLicName", 0);
		CommonMethod.sendKeys("MPmembershipLicName", firstName);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("Name"));
		CommonMethod.sendKeys("MPmembershipLicEmail", data.getCellData(SheetName, "Email", rowNum));
		data.setCellData(SheetName, "OwnerEmail", rowNum, CommonMethod.getattributeValue("MPOwnerEmail"));
		CommonMethod.sendKeys("MPmembershipLicJob", "Testing");
		data.setCellData(SheetName, "OwnerJobTitle", rowNum, CommonMethod.getattributeValue("MPOwnerJob"));
		CommonMethod.sendKeys("MPmembershipLicPhone", PhoneNumber);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPmembershipOwnerContinuebtn", 0);
		CommonMethod.RobustclickElementVisible("MPmembershipOwnerContinuebtn", "MPOrganizationContinuebtn");
		testlog.info("And User clicks on continue button");
		testlog.pass("**Verifies the Registration successful**");
	}

	public void BillingMembership(String SheetName, int rowNum, String MembershipName)
			throws IOException, InterruptedException {
		rc.Billing(SheetName, rowNum);
		testlog.info("MembershipName:" + MembershipName);
		if (MembershipName.equalsIgnoreCase("Cornerstone")) {
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("MPValidMembershipeType"), "Cornerstone Membership",
					"Mismatch Cornerstone Membership Name");
			testlog.info("And User verifies Cornerstone Membership Name");
		} else if (MembershipName.equalsIgnoreCase("Keystone")) {
			CommonMethod.negativesoftassertFieldValid(
					CommonMethod.getattributeValueByTextContent("MPValidMembershipeType"), "Keystone Membership",
					"Mismatch KEYSTONE Membership Name");
			testlog.info("And User verifies Keystone Membership Name");
		}
		testlog.pass("**Verified Membership Name successfully**");
	}

	public void NavigateInPL(String SheetName, int rowNum, String MembershipName)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Membership Dashboard Page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLEnroll", 0);
		CommonMethod.JavascriptClickElement("MPPLEnroll");
		testlog.info("When User is clicks on Product Licensing Enroll tab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLGetstartedBtn", 0);
		CommonMethod.JavascriptClickElement("MPPLGetstartedBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLEnrollReg", 0);
		CommonMethod.JavascriptClickElement("MPPLEnrollReg");
		testlog.info("And User is clicks on Product Licensing Start button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLCommunications", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLCommunicationsDisableContinueBtn", 0);
		//CommonMethod.RobustclickElementVisible("MPPLCommunications", "MPPLCommunicationsContinueBtn");
		CommonMethod.click("MPPLCommunications");
		testlog.info("And User is select on Product Licensing Communications group");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLCommunicationsContinueBtn", 0);
		CommonMethod.Robustclick("MPPLCommunicationsContinueBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLGroupNo", 0);
		CommonMethod.sendKeys("MPPLGroupNo", "3");
		testlog.info("And User enters Group number");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLGroupNoContinueBtn", 0);
		CommonMethod.Robustclick("MPPLGroupNoContinueBtn");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLPricingContinueBtn", 0);
		CommonMethod.Robustclick("MPPLPricingContinueBtn");
		testlog.info("And User clicks on continue button");
		String firstName = USfaker.address().firstName();
		String PhoneNumber = USfaker.number().digits(10);
		CommonMethod.sendKeys("MPmembershipadminName", firstName);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("Name"));
		CommonMethod.clearAndSendKey("MPmembershipadminEmail", data.getCellData(SheetName, "Email", rowNum));
		data.setCellData(SheetName, "OwnerEmail", rowNum, CommonMethod.getattributeValue("MPOwnerEmail"));
		CommonMethod.sendKeys("MPmembershipadminJob", "Testing");
		data.setCellData(SheetName, "OwnerJobTitle", rowNum, CommonMethod.getattributeValue("MPOwnerJob"));
		CommonMethod.sendKeys("MPmembershipadminPhone", PhoneNumber);
		CommonMethod.ClickCheckbox("MPmarketingadminCheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPmembershipOwnerContinuebtn", 0);
		CommonMethod.Robustclick("MPmembershipOwnerContinuebtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPOrgDomain", 0);
		CommonMethod.sendKeys("MPOrgDomain", "@promantus");
		CommonMethod.sendKeys("MPOrgwebsite", "https://test-nuxt.wellcertified.com/");
		testlog.info("And User enters data to Street, City, Postalcode, Domain and  website fields");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPOrganizationContinuebtn", 0);
		CommonMethod.Robustclick("MPOrganizationContinuebtn");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Acknowledge", 0);
		CommonMethod.ClickCheckbox("Acknowledge");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPReviewContinueButton", 0);
		CommonMethod.JavascriptClickElement("MPReviewContinueButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		rc.Billing(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("MPPLEnroll", 1);
		testlog.pass("**Navigate to Product Licensing Page successfully**");
	}

	public void AdminMembershipNavigation(String SheetName, int rowNum, String MembershipName)
			throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL certified Admin account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminMembershipNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminMembershipNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminMembershipNavBar", "MPViewButton");
		testlog.info("When User clicks on WELL Membership from top menu under Admin");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplyButton", 0);
		CommonMethod.click("ApplyButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminMembershipIdTextBox", 0);
		String getMembershipId = data.getCellData(SheetName, "MembershipId", rowNum);
		CommonMethod.sendKeys("AdminMembershipIdTextBox", getMembershipId);
		CommonMethod.RobustclickElementVisible("V2ProjectScorecardApplybutton", "SearchFilterClear");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchFilterClear", 0);
		testlog.info("And User clicks on Apply Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectProjectNameResultList", 1);
		int var = CommonMethod.ElementSize("V2ProjectProjectNameResultList");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var), "1", "MembershipId row count failed");
		testlog.info("Then User verifies Search filter V2Project Count");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("V2ProjectProjectNameResultList"),
				data.getCellData(SheetName, "MembershipName", rowNum), "MembershipName Search failed");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("AdminMembershipId"), getMembershipId,
				"MembershipId Search failed");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPViewButton", 0);
		CommonMethod.RobustclickElementVisible("MPViewButton", "MPLicensingTab");
		testlog.info("And User clicks on View Button");
		CommonMethod.RobustclickElementVisible("MPLicensingTab", "MPDownloadApplicationform");
		testlog.info("And User clicks on LicensingTab");
	}

	public void CreatePLTeam(String SheetName, int rowNum, String MembershipName)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicensingTab", 0);
		CommonMethod.RobustclickElementVisible("MPLicensingTab", "MPDownloadApplicationform");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPDownloadApplicationform", 0);
		CommonMethod.RobustclickElementVisible("MPDownloadApplicationform", "MPLicensingContinue");
		testlog.info("And User clicks on Download Applicationform");
		Thread.sleep(2000);
		CommonMethod.isFileDownloaded();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicensingContinue", 0);
		CommonMethod.RobustclickElementVisible("MPLicensingContinue", "Name");
		testlog.info("And User clicks on continue button");
		testlog.info("Then User will be redirected to Product Licensing page");
		testlog.pass("**Verifies Navigate to Product Licensing Page successfully**");
	}

	public void CreateLicensing(String SheetName, int rowNum, String MembershipName)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Product Licensing Page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSaveProductButton", 0);
		CommonMethod.RobustclickElementVisible("MPSaveProductButton", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Categories is required.", "Categories Error Mismatch");
		CommonMethod.negativesoftassertPageSource("ProductType is required.", "ProductType Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Parts is required.", "Feature Parts Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Group name is required.", "Group Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("At least one sku document is required",
				"Upload Document Error Mismatch");
		CommonMethod.negativesoftassertPageSource("At least one document is required",
				"Application Form Error Mismatch");
		CommonMethod.negativesoftassertPageSource("At least one supporting document is required.",
				"Supporting document Error Mismatch");
		testlog.info(
				"When User clicks on continue button without entering the mandatory fields and verifies error meassage");
		for (int i = 1; i <= 3; i++) {
			CommonMethod.selectdropdownrandom("MPSelectProductCategories");
			testlog.info("And User select Product Categories");
			String ProductCategories = CommonMethod.getSelectedDropdownValue("MPSelectProductCategories");
			testlog.info("ProductCategories: " + ProductCategories);
			data.setCellData(SheetName, "ProductCategories", rowNum, ProductCategories);
			CommonMethod.selectdropdownrandom("MPSelectproductType");
			testlog.info("And User select Product Type");
			String getproductType = CommonMethod.getSelectedDropdownValue("MPSelectproductType");
			testlog.info("ProductType: " + getproductType);
			data.setCellData(SheetName, "ProductType", rowNum, getproductType);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSelectPartname", 0);
			CommonMethod.RobustclickElementVisible("MPSelectPartname", "MPSelectPartnameSelect");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSelectPartnameSelect", 0);
			CommonMethod.Robustclick("MPSelectPartnameSelect");
			Thread.sleep(1000);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseValidFeaturePart", 0);
			data.setCellData(SheetName, "FeaturePart", rowNum, CommonMethod.getText("MPLicenseValidFeaturePart"));
			testlog.info("And User select Feature Part");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSelectNovelFeaturePart", 0);
			CommonMethod.RobustclickElementVisible("MPSelectNovelFeaturePart", "MPSelectPartnameSelect");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSelectPartnameSelect", 0);
			CommonMethod.Robustclick("MPSelectPartnameSelect");
			Thread.sleep(1000);
			CommonMethod.sendKeys("MPGroupName", "Test" + i);
			data.setCellData(SheetName, "GroupName", rowNum, "Test" + i);
			testlog.info("And User enter GroupName field");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProductUploadForm", 0);
			CommonMethod.uploadFile("MPProductUploadForm", ProductSkuImportTestDataUpload, "MPValidUploadFile1");
			testlog.info("And User upload ProductForm Document");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPValidUploadFile1", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPApplicationform", 0);
			CommonMethod.uploadFile("MPApplicationform", ProductSkuImportTestDataUpload, "MPValidUploadFile2");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPValidUploadFile2", 0);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSupportingdocuments", 0);
			CommonMethod.uploadFile("MPSupportingdocuments", ProductSkuImportTestDataUpload, "MPValidUploadFile3");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPValidUploadFile3", 0);
			testlog.info("And User upload Applicationform Document");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseComment", 0);
			CommonMethod.sendKeys("MPLicenseComment", "QA Team");
			String getComment = CommonMethod.getattributeValue("MPLicenseComment");
			data.setCellData(SheetName, "Comment", rowNum, getComment);
			testlog.info("And User enter Comment field");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSaveProductButton", 0);
			CommonMethod.Robustclick("MPSaveProductButton", "MPValidUploadFile3");
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("MPValidUploadFile3", 1);
			testlog.info("And User clicks on Save button");
			CommonMethod.scrollUp();
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseValidGroup", 0);
			testlog.info("Then User will be redirected to Product list page");
			if (i == 1) {
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getattributeValueByTextContent("MPLicenseValidGroupName"),
						data.getCellData(SheetName, "GroupName", rowNum), "GroupName data mismatch");
				CommonMethod.negativesoftassertFieldValid(
						CommonMethod.getattributeValueByTextContent("MPLicenseValidFeaturePartName"),
						data.getCellData(SheetName, "FeaturePart", rowNum), "FeaturePart data mismatch");
			}
		}
		testlog.info("And User verifies the added details");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MPLicenseProductCard", 3);
		int var = CommonMethod.ElementSize("MPLicenseProductCard");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var), "3", "MembershipId row count failed");
		testlog.info("Then User verifies Search filter V2Project Count");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MPLicenseEdit", 3);
		int var1 = CommonMethod.ElementSize("MPLicenseEdit");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var1), "3", "MembershipId row count failed");
		testlog.info("Then User verifies Search filter V2Project Count");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MPDeleteIcon", 3);
		int var2 = CommonMethod.ElementSize("MPDeleteIcon");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(var2), "3", "MembershipId row count failed");
		testlog.info("Then User verifies Search filter V2Project Count");
		testlog.info("And User verifies the ProductCard Count");
		testlog.info("**Verifies added 3 Product Licensing Group successfully**");
		testlog.pass("**Verifies Created Product Licensing successfully**");
	}

	public void UpdateLicensing(String SheetName, int rowNum, String MembershipName)
			throws IOException, InterruptedException {
		CommonMethod.refreshBrowser(); 
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicensingTab", 0);
		CommonMethod.RobustclickElementVisible("MPLicensingTab","MPLicenseValidGroup");
//		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicensingContinue", 0);
//		CommonMethod.JavascriptClickElement("MPLicensingContinue");		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseValidGroup", 0);
		testlog.info("Given User is on Product Licensing list");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseEditOne", 0);
		CommonMethod.RobustclickElementVisible("MPLicenseEditOne", "MPSelectUpdateProductCategories");		
		testlog.info("When User clicks on Edit button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSelectUpdateProductCategories", 0);
		CommonMethod.selectdropdownVisibletext("MPSelectUpdateProductCategories", "Communications");
		testlog.info("And User select on Product Categories");
		CommonMethod.selectdropdownVisibletext("MPSelectproductType", "Audio-video systems");
		testlog.info("And User select on Product Type");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSelectPartname", 0);
		CommonMethod.RobustclickElementVisible("MPSelectPartname", "MPSelectS08.1");
		CommonMethod.JavascriptClickElement("MPSelectS08.1");
		testlog.info("And User select on Partname");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseComment", 0);
		Thread.sleep(2000);
		CommonMethod.scrolldowntoElement("MPLicenseComment");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseUpdateSaveButton", 0);
		CommonMethod.Robustclick("MPLicenseUpdateSaveButton", "MPLicenseUpdaateClosePopup");
		testlog.info("And User clicks on Save button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseValidGroup", 0);
		testlog.info("Then User will be redirected to Product list page");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("MPValidType"), "Audio-video systems",
				"GroupName doesn't match");
		CommonMethod.negativesoftassertFieldValidEquals(CommonMethod.getText("MPValidCategory"), "Communications",
				"Category doesn't match");
		testlog.info("And User verifies the added details");
		testlog.pass("**Verifies Updated Product Licensing successfully**");
	}

	public void DeleteLicensing(String SheetName, int rowNum, String MembershipName)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Product Licensing list");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSelectProductCategories", 0);
		CommonMethod.scrolldowntoElement("MPSelectProductCategories");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPDeleteIconGroup", 0);
		CommonMethod.RobustclickElementVisible("MPDeleteIconGroup", "DeleteButton");
		testlog.info("When User clicks on DeleteIcon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DeleteButton", 0);
		CommonMethod.RobustclickElementVisible("DeleteButton", "MPSelectProductCategories");
		testlog.info("And User clicks on Confirm Delete");
		testlog.info("Then User will be redirected to Product list page");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MPDeleteIcon", 2);
		int MPDeleteIcon = CommonMethod.ElementSize("MPDeleteIcon");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(MPDeleteIcon), "2",
				"TableTrSize in Document list doesn't match");
		testlog.info("And User verifies the  Delete Product Licensing");
		testlog.pass("**Verifies Delete Product Licensing Group successfully**");
	}

	public void SubmitProductLicensingReview(String SheetName, int rowNum, String MembershipName)
			throws IOException, InterruptedException {
		testlog.info("Given User is on Submit Product Licensing Review page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseSubmitForReview", 0);
		CommonMethod.RobustclickElementVisible("MPLicenseSubmitForReview", "MPLicenseReviewStatus");
		testlog.info("When User clicks on SubmitReview button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MPLicenseProductCard", 2);
		int MPDeleteIcon = CommonMethod.ElementSize("MPLicenseProductCard");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(MPDeleteIcon), "2",
				"TableTrSize in Document list doesn't match");
		testlog.info("Then User will be redirected to Product list page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseReviewStatus", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MPLicenseReviewStatus"), "UNDER REVIEW",
				"Review Status doesn't match");
		testlog.info("And User verifies Review Status");
		testlog.pass("**Verifies Submitted Product Licensing Review successfully**");

	}

	public void PromotionCardValidation(String SheetName, int rowNum, String cardValue) throws Exception {
		int countCard = CommonMethod.ElementSize("PromotionCardContainer");
		String cardCount = Integer.toString(countCard);
		CommonMethod.negativesoftassertFieldValid(cardCount, cardValue, "Promotion Card doesn't match");
		testlog.info("Card count: " + cardCount);

	}

	public void ValidatePromtionCard(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Membership Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PromotionTab", 0);
		CommonMethod.RobustclickElementVisible("PromotionTab", "PromotionCardContainer");
		testlog.info("When User clicks on PromotionTab");
		testlog.info("Then User will be redirected to Promotion page");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MembershipPromotionMembershipType"),
				"CORNERSTONE MEMBERSHIP", "Membership Type doesn't match");
		rc.ValidCardCount("PromotionCardContainer");
		testlog.info("And User verifies cards on the Promotion page");
		testlog.pass("**Verify card count successfully**");
	}

	public void ValidateEducationCard(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Membership Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EducationTab", 0);
		CommonMethod.RobustclickElementVisible("EducationTab", "PromotionCardContainer");
		testlog.info("When User clicks on EducationTab");
		testlog.info("Then User will be redirected to Education page");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MembershipPromotionMembershipType"),
				"CORNERSTONE MEMBERSHIP", "Membership Type doesn't match");
		rc.ValidCardCount("PromotionCardContainer");
		testlog.info("And User verifies cards on the Education page");
		testlog.pass("**Verify card count successfully**");
	}

	public void AddTeamMember(String SheetName, int rowNum, String PermissionLevelXpath) throws Exception {
		testlog.info("Given User is on Membership Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TeamTab", 0);
		CommonMethod.RobustclickElementVisible("TeamTab", "V2ProjectAddMemberbtn");
		testlog.info("When User clicks on TeamTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddMemberbtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectAddMemberbtn", "MembershipTeamEmailAddress");
		testlog.info("And User clicks on AddMember button");
		String TeamEmail = data.getCellData(SheetName, "TeamEmailID", rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MembershipTeamEmailAddress", 0);
		CommonMethod.sendKeys("MembershipTeamEmailAddress", TeamEmail);
		testlog.info("And User enter on EmailAddress");
		testlog.info("Team Email ID: " + TeamEmail);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(PermissionLevelXpath, 0);
		CommonMethod.ClickCheckbox(PermissionLevelXpath);
		testlog.info("And User checks the Role checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectInvitebtn", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectInvitebtn", "TeamTableTr");
		testlog.info("And User clicks on Invite button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectAddMemberbtn", 0);
		CommonMethod.scrolldowntoElement("V2ProjectAddMemberbtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TeamTableTr", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectDeleteIcon", 2);
		CommonMethod.RobustclickElementVisible("V2ProjectDeleteIcon", "V2ProjectAddMemberbtn");
		testlog.info("And User clicks on Delete Icon");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectDeleteIcon", 0);
		testlog.info("Then User will be redirected to Team list");
		testlog.pass("**Created Team member successfully**");
	}

	public void ProfileUpdated(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Membership Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileTab", 0);
		CommonMethod.RobustclickElementVisible("ProfileTab", "MPmembershipadminName");
		testlog.info("And User clicks on ProfileTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPmembershipadminName", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MembershipProfileOrgnameTooltip", 0);
		CommonMethod.RobustclickElementVisible("MembershipProfileOrgnameTooltip", "MPProfileOrgName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProfileOrgName", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("MPProfileOrgName"),
				"Used for organization search", "MPProfileOrgName message doesn't match");
		CommonMethod.sendKeys("Name", "Automation");
		String TeamEmail = data.getCellData(SheetName, "TeamEmailID", rowNum);
		CommonMethod.sendKeys("MPmembershiProfileEmail", TeamEmail);
		CommonMethod.sendKeys("MPmembershipadminName", "Automation");
		testlog.info("And User enter data to AboutYourOrg narrative");
		CommonMethod.sendKeys("MPmembershiPLProfileEmail", TeamEmail);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MembershipProfileUpdateButton", 0);
		Thread.sleep(50000);
		CommonMethod.JavascriptClickElement("MembershipProfileUpdateButton");

		testlog.info("Then User verifies the added details");
		testlog.pass("**Profile updated successfully**");
	}

	public void adminProfileUpdated() throws Exception {
		testlog.info("Given User is on Membership Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileTab", 0);
		CommonMethod.RobustclickElementVisible("ProfileTab", "MPmembershipadminName");
		testlog.info("And User clicks on ProfileTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MembershipProfileEnterpriseCheckbox", 0);
		CommonMethod.negativeAssertElementPresentTrue("MembershipProfileEnterpriseCheckbox",
				"MembershipProfileEnterpriseCheckbox is not present");
		CommonMethod.negativeAssertElementPresentTrue("MembershipProfileProductCheckbox",
				"MembershipProfileProductCheckbox is not present");
		CommonMethod.negativeAssertElementPresentTrue("MembershipProfileSurveyCheckbox",
				"MembershipProfileSurveyCheckbox is not present");
		CommonMethod.negativeAssertElementPresentTrue("MembershipProfileEducationCheckbox",
				"MembershipProfileEducationCheckbox is not present");
		CommonMethod.negativeAssertElementPresentTrue("MembershipProfileStrategicCheckbox",
				"MembershipProfileStrategicCheckbox is not present");
		CommonMethod.negativeAssertElementPresentTrue("MembershipProfileIndustryCheckbox",
				"MembershipProfileIndustryCheckbox is not present");
		CommonMethod.negativeAssertElementPresentTrue("MembershipProfileLabCheckbox",
				"MembershipProfileLabCheckbox is not present");
		CommonMethod.negativeAssertElementPresentTrue("MembershipProfilePerformanceCheckbox",
				"MembershipProfilePerformanceCheckbox is not present");
		testlog.pass("**Validated Adminfields**");
	}

	public void ValidateProductGroupSuccessMessage(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Product Group page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicensingContinue", 0);
		CommonMethod.RobustclickElementVisible("MPLicensingContinue", "FacultyPLProductGroupButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLProductGroupButton", 0);
		CommonMethod.RobustclickElementVisible("FacultyPLProductGroupButton",
				"FacultyPLProductGroupSucessToastMessage");
		testlog.info("When User clicks on ProductGroup button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLProductGroupSucessToastMessage", 0);
		testlog.info("Then User will be redirected to ProductGroup page");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("FacultyPLProductGroupSucessToastMessage"),
				"Success! We will send you an email shortly with the link to download the zip files.",
				"Product Group Success message doesn't match");
		testlog.info("And User verifies Success Toast message");
		testlog.pass("**Validated Product Group successfully**");
	}

	public void DownloadReviewFile(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Download Review page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLProductInfoCheckBox", 0);
		CommonMethod.ClickCheckbox("FacultyPLProductInfoCheckBox");
		testlog.info("When User checks the Product Info checkbox");
		CommonMethod.RobustclickElementVisible("FacultyPLImportButton", "FacultyPLDownloadReviewButton");
		testlog.info("And User clicks on Import button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLDownloadReviewButton", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLImportReviewButton", 0);
		FileUtils.cleanDirectory(new File(downloadPath));
		CommonMethod.click("FacultyPLDownloadReviewButton");
		testlog.info("And User clicks on Download Review button");
		Thread.sleep(5000);
		CommonMethod.assertTruebooleanCondition(CommonMethod.isFileDownloaded(), "File not downloaded");
		CommonMethod.FileRename(downloadPath, "/Downloads/ProductReview.xlsx");
		pl = new XlsReader(System.getProperty("user.dir") + "/Downloads/ProductReview.xlsx");
		softAssert.assertAll();
		testlog.info("Then User verifies downloaded file");
		testlog.pass("**Download Product Review successfully**");
	}

	public void UpdateReviewFile(String SheetName, int rowNum) throws Exception {
		Thread.sleep(2000);
		testlog.info("Given User is on Download Review Page");
		pl.setCellData("Worksheet", "Status", rowNum, "NotAligned");
		testlog.info("When User updates the status to excel");
		testlog.pass("**Updated status in Review file successfully**");
	}

	public void ImportReviewFile(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Import Review Page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLImportReviewButton", 0);
		CommonMethod.RobustclickElementVisible("FacultyPLImportReviewButton", "FacultyPLSubmitResponseButton");
		testlog.info("When User clicks on Import Review button");
		CommonMethod.uploadFile("MPOrgLogoUpload", ProductLineReview, "FacultyPLDeleteICon");
		testlog.info("And User upload Product Review Document");
		Thread.sleep(2000);
		CommonMethod.RobustclickElementVisible("FacultyPLSubmitResponseButton", "FacultyPLImportReviewButton");
		testlog.info("And User clicks on Submit Response button");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("FacultyPLSubmitResponseButton", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLSuccessToastMessage", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("FacultyPLSuccessToastMessage"), "Success!!",
				"Import Review Success message doesn't match");
		testlog.info("Then User verifies Import Review Success message");
		testlog.pass("**Imported Review File successfully**");
	}

	public void MarkAsAchieved(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Profile Page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileTab", 0);
		CommonMethod.RobustclickElementVisible("ProfileTab", "FacultyPLAchievedRadioButton");
		testlog.info("When User clicks on ProfileTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLAchievedRadioButton", 0);
		CommonMethod.ClickCheckbox("FacultyPLAchievedRadioButton");
		testlog.info("And User checks the Achieved checkbox");
		CommonMethod.RobustclickElementVisible("MembershipProfileUpdateButton", "FacultyPLProfileUpdateSuccessMessage");
		testlog.info("And User clicks on Update button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLProfileUpdateSuccessMessage", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("FacultyPLProfileUpdateSuccessMessage"),
				"Profile changes saved!", "Profile Update Success message doesn't match");
		testlog.info("Then User verifies Profile Update Success message");
	}

	public void ValidateUpdatedImportData(String SheetName, int rowNum) throws Exception {
		testlog.info("Given User is on Licensing Page");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LicensingTab", 0);
		CommonMethod.RobustclickElementVisible("LicensingTab", "FacultyPLContinueButton");
		testlog.info("When User clicks on LicensingTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicensingContinue", 0);
		CommonMethod.RobustclickElementVisible("MPLicensingContinue", "FacultyPLContinueButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLContinueButton", 0);
		CommonMethod.RobustclickElementVisible("FacultyPLContinueButton", "FacultyPLCatalogPageLink");
		testlog.info("And User clicks on Continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLCatalogPageLink", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseReviewPassDate", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPLicenseReviewPassDate").replaceAll("\\s+", " ").trim(),
				CommonMethod.ValidateDate(), "MP License Review Pass Date Table Error Mismatch");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseDuration", 0);
//		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("MPLicenseDuration").replaceAll("\\s+", " ").trim(), CommonMethod.ValidateDateYear(),
//				"MP License Duration Table Error Mismatch");
		// DownloadSubmittedDocuments
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PLDownloadSubmittedDocuments", 0);
		CommonMethod.JavascriptClickElement("PLDownloadSubmittedDocuments");
		testlog.info("Then User Downloaded The Documents and Validated the Text");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPLicenseSuccessToasterMessage", 0);
		String HsrValidateDownloadedDocs = CommonMethod.getattributeValueByTextContent("MPLicenseSuccessToasterMessage")
				.trim();
		CommonMethod.negativesoftassertFieldValid(HsrValidateDownloadedDocs,
				"Success! We will send you an email shortly", "Downloaded Text Message Does not Matched");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("MPLicenseSuccessToasterMessage", 1);
		// DownloadReviewResults
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PLDownloadReviewResults", 0);
		CommonMethod.JavascriptClickElement("PLDownloadReviewResults");
		boolean fileExistsReturnValue = CommonMethod.isFileDownloaded();
		testlog.info("Then User verifies Downloaded file");
		String fileExists = Boolean.toString(fileExistsReturnValue);
		CommonMethod.negativesoftassertFieldValid(fileExists, "true", "Downloaded Card file doesn't Exist");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("FacultyPLCatalogPageLink", 0);
		CommonMethod.click("FacultyPLCatalogPageLink");
		testlog.info("And User clicks on Catalog Page Link");
		Thread.sleep(2000);
		CommonMethod.switchToChildTab();
		Thread.sleep(2000);
		if (TestNGTestName.contains("CS")) {
			Thread.sleep(3000);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getCurrentUrl(), "product-group",
					"Catalog Url doesn't match");
		}
		CommonMethod.switchToParentTab();
	}

	public void RegisterNewMembership(String SheetName, int rowNum, String MembershipName)
			throws IOException, InterruptedException {

		testlog.info("Given User logged in to the WELL certified account for Membership");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPUserMenu", 0);
		CommonMethod.RobustclickElementVisible("MPUserMenu", "MPMyMembership");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPMyMembership", 0);
		CommonMethod.RobustclickElementVisible("MPMyMembership", "MPGetStartedWithMembership");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPGetStartedWithMembership", 0);
		CommonMethod.RobustclickElementVisible("MPGetStartedWithMembership", "MPSubscribeCornerstoneMembershipValid");
		testlog.info("MembershipName:" + MembershipName);
//			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPIWBIMembership", 0);
//			CommonMethod.ClickCheckbox("MPIWBIMembership");
//			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSelectProgramContinuebtn", 0);
//			CommonMethod.Robustclick("MPSelectProgramContinuebtn");
		if (MembershipName.equalsIgnoreCase("Cornerstone")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSubscribeCornerstoneMembershipValid", 0);
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MPSubscribeCornerstoneMembershipValid"),
					"$5,000 USD", "CornerstoneMembership Error Mismatch");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSubscribeCornerstoneMembership", 0);
			CommonMethod.ClickCheckbox("MPSubscribeCornerstoneMembership");
		} else if (MembershipName.equalsIgnoreCase("Keystone")) {
			CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("MPSubscribeKeystoneMembershipValid"),
					"$15,000 USD", "KeystoneMembership Error Mismatch");
			CommonMethod.ClickCheckbox("MPSubscribeKeystoneMembership");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSubscribeContinuebtn", 0);
		CommonMethod.Robustclick("MPSubscribeContinuebtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPmembershipOwnerContinuebtn", 0);
		CommonMethod.RobustclickElementVisible("MPmembershipOwnerContinuebtn", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Owner name is required.", "Owner Name Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner email is required.", "Owner Email Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner job title is required.", "Owner job title Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Owner phone is required.", "Owner phone Error Mismatch");
		testlog.info(
				"When User clicks on continue button without entering the mandatory fields and verifies error meassage");
		String firstName = USfaker.address().firstName();
		String PhoneNumber = USfaker.number().digits(10);
		String email = USfaker.internet().emailAddress();
		CommonMethod.sendKeys("Name", firstName);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("Name"));
		CommonMethod.sendKeys("MPOwnerEmail", email);
		data.setCellData(SheetName, "OwnerEmail", rowNum, CommonMethod.getattributeValue("MPOwnerEmail"));
		CommonMethod.sendKeys("MPOwnerJob", "Testing");
		data.setCellData(SheetName, "OwnerJobTitle", rowNum, CommonMethod.getattributeValue("MPOwnerJob"));
		CommonMethod.sendKeys("MPOwnerPhone", PhoneNumber);
		data.setCellData(SheetName, "OwnerPhoneNumber", rowNum, CommonMethod.getattributeValue("MPOwnerPhone"));
		testlog.info("And User enters data to Owner Name, Email, JobTitle, Phone number fields");
		testlog.info("website:" + "https://test-nuxt.wellcertified.com/");
		testlog.info("firstName:" + firstName);
		testlog.info("phonenumber: " + PhoneNumber);
		testlog.info("Name: " + data.getCellData(SheetName, "Name", rowNum));
		testlog.info("Email: " + data.getCellData(SheetName, "Email", rowNum));
		testlog.info("JobTitle: " + data.getCellData(SheetName, "JobTitle", rowNum));
		CommonMethod.ClickCheckbox("MPOwnerAgreeCheckbox");
		testlog.info("And User check Agree to share point of contact");
		CommonMethod.sendKeys("MPmembershipadminName", firstName);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("Name"));
		CommonMethod.sendKeys("MPmembershipadminEmail", email);
		data.setCellData(SheetName, "OwnerEmail", rowNum, CommonMethod.getattributeValue("MPOwnerEmail"));
		CommonMethod.sendKeys("MPmembershipadminJob", "Testing");
		data.setCellData(SheetName, "OwnerJobTitle", rowNum, CommonMethod.getattributeValue("MPOwnerJob"));
		CommonMethod.sendKeys("MPmembershipadminPhone", PhoneNumber);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPmembershipOwnerContinuebtn", 0);
		CommonMethod.Robustclick("MPmembershipOwnerContinuebtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPOrganizationContinuebtn", 0);
		testlog.info("And User clicks on continue button");
		testlog.pass("**Verifies the Registration successful**");
	}

	public void ErollNewMembership(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Organization page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPOrganizationContinuebtn", 0);
		CommonMethod.RobustclickElementVisible("MPOrganizationContinuebtn", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Company is required.", "OrgName Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Country is required.", "Country Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Street is required.", "Street Error Mismatch");
		CommonMethod.negativesoftassertPageSource("City is required.", "City Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Postal Code is required.", "Postal Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Domain is required.", "Domain Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Website is required.", "Website Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Logo is required.", "Logo Error Mismatch");
		testlog.info(
				"When User clicks on continue button without entering the mandatory fields and verifies error meassage");
		rc.SelectOwnerOrg(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPOrgCountry", 0);
		CommonMethod.selectdropdownVisibletext("MPOrgCountry", "United States");
		data.setCellData(SheetName, "Country", rowNum, CommonMethod.getSelectedDropdownValue("MPOrgCountry"));
		CommonMethod.selectdropdownrandom("StateName");
		data.setCellData(SheetName, "State", rowNum, CommonMethod.getSelectedDropdownValue("StateName"));
		String ProjectAddress = USfaker.address().streetAddress();
		String ProjectCity = USfaker.address().cityName();
		String PostalCode = USfaker.address().zipCode();
		CommonMethod.sendKeys("StreetName", ProjectAddress);
		data.setCellData(SheetName, "Street", rowNum, CommonMethod.getattributeValue("StreetName"));
		CommonMethod.sendKeys("CityName", ProjectCity);
		data.setCellData(SheetName, "City", rowNum, CommonMethod.getattributeValue("CityName"));
		CommonMethod.sendKeys("MPOrgPostal", PostalCode);
		data.setCellData(SheetName, "PostalCode", rowNum, CommonMethod.getattributeValue("MPOrgPostal"));
		CommonMethod.sendKeys("MPOrgDomain", "@promantus");
		CommonMethod.sendKeys("MPOrgwebsite", "https://test-nuxt.wellcertified.com/");
		testlog.info("And User enters data to Street, City, Postalcode, Domain and  website fields");
		CommonMethod.uploadFile("MPOrgLogoUpload", SampleJpgfile1, "UploadFileVerifyScorecard");
		testlog.info("And User Upload logo document");
		testlog.info("Country: " + data.getCellData(SheetName, "Country", rowNum));
		testlog.info("And User enters data to Country, State, Street address, City and Postal Code fields");
		testlog.info("State: " + data.getCellData(SheetName, "State", rowNum));
		testlog.info("Street: " + data.getCellData(SheetName, "Street", rowNum));
		testlog.info("PostalCode: " + data.getCellData(SheetName, "PostalCode", rowNum));
		CommonMethod.Robustclick("MPOrganizationContinuebtn");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Acknowledge", 0);
		CommonMethod.RobustclickElementVisible("MPReviewContinueButton", "MandatoryFieldErrorMessage");
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("Terms is required.", "Terms Condition Error Mismatch");
		testlog.info(
				"When User clicks on continue button without entering the mandatory fields and verifies error meassage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Acknowledge", 0);
		CommonMethod.ClickCheckbox("Acknowledge");
		testlog.info("And User checks on Review Checkbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPReviewContinueButton", 0);
		CommonMethod.RobustclickElementVisible("MPReviewContinueButton", "BillingLanding");
		testlog.info("And User clicks on continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.pass("**Verifies Enrollment successfully**");
	}

	public void validateAdminMembershipStatus(String SheetName, int rowNum, String Status)
			throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SubscriptionStatusDropdown", 0);
		testlog.info("When User is on Subscription Status Dropdown");
		if (Status.equalsIgnoreCase("IN PROGRESS")) {
			CommonMethod.selectdropdownVisibletext("SubscriptionStatusDropdown", "Subscription in progress");
			testlog.info("And User is on Status:  IN PROGRESS");
		} else {
			CommonMethod.selectdropdownVisibletext("SubscriptionStatusDropdown", "Subscribed");
			testlog.info("And User is on Status: SUBSCRIBED");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplyButton", 0);
		CommonMethod.click("ApplyButton");
		testlog.info("And User clicks on Apply Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminMembershipSubscriptionStatus", 0);
		String actualStatus = CommonMethod.getattributeValueByTextContent("AdminMembershipSubscriptionStatus");
		CommonMethod.negativesoftassertFieldValid(actualStatus.toLowerCase(), "subscribed",
				"Status: subscribed does not matched ");
	}

	public void navigateAdminMembership() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		testlog.info("Given User is on Admin Nav Bar");
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminMembershipNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminMembershipNavBar", 0);
		testlog.info("And User is on Admin Membership Nav Bar");
		CommonMethod.RobustclickElementVisible("AdminMembershipNavBar", "SubscriptionStatusDropdown");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplyButton", 0);
		CommonMethod.click("ApplyButton");
	}

	public void clearFilter() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchFilterClear", 0);
		CommonMethod.JavascriptClickElement("SearchFilterClear");
	}

	public void validateAdminMembershipEngagementLevel(String SheetName, int rowNum, String Option, String Status)
			throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EngagementLevelDropdown", 0);
		testlog.info("When User is on Subscription Status Dropdown");
		CommonMethod.selectdropdownVisibletext("EngagementLevelDropdown", Option);
		testlog.info("And User is on Status: WORKS WITH WELL");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplyButton", 0);
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchFilterClear");
		testlog.info("And User clicks on Apply Button");
		Thread.sleep(2000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EngagementLevelStatusMEMBERSHIP", 0);
		List<WebElement> label;
		if (Status.equalsIgnoreCase("MEMBERSHIP")) {
			label = CommonMethod.findElements("EngagementLevelStatusMEMBERSHIP");
		} else {
			label = CommonMethod.findElements("EngagementLevelStatusWorkWithWELL");
		}
		for (WebElement ele : label) {
			String subscribeName = ele.getText();
			CommonMethod.negativesoftassertFieldValid(subscribeName, Status,
					"Engagement Level" + Status + "does not matched");
			testlog.info("Engagement Level Label is MEMBERSHIP");
		}
	}

	public void validateAdminMembershipFilterID(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminMembershipIdTextBox", 0);
		CommonMethod.sendKeys("AdminMembershipIdTextBox", data.getCellData(SheetName, "MembershipId", rowNum).trim());
		testlog.info("And User added the text in text box");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplyButton", 0);
		CommonMethod.RobustclickElementVisible("ApplyButton", "SearchFilterClear");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SearchFilterClear", 0);
		testlog.info("And User clicks on Apply Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("TableTrSize", 1);
		int ProjectNameCount = CommonMethod.ElementSize("TableTrSize");
		CommonMethod.negativesoftassertFieldValid(String.valueOf(ProjectNameCount), "1", "Portfolio Search failed");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminMembershipId", 0);
		String getExpectedData = CommonMethod.getattributeValueByTextContent("AdminMembershipId").trim();
		CommonMethod.negativesoftassertFieldValid(getExpectedData,
				data.getCellData(SheetName, "MembershipId", rowNum).trim(), "MembershipId Data does not matched");
	}

	public void validateAdminMembershipFilterEmail(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPOwnerEmail", 0);
		CommonMethod.sendKeys("MPOwnerEmail", data.getCellData(SheetName, "MembershipOwnerEmail", rowNum));
		validateAdminMembershipFilterID(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminMembershipUserEmail", 0);
		String getExpectedData = CommonMethod.getattributeValueByTextContent("AdminMembershipUserEmail").trim();
		CommonMethod.negativesoftassertFieldValid(getExpectedData,
				data.getCellData(SheetName, "MembershipOwnerEmail", rowNum).trim(),
				"MembershipOwnerEmail Data does not matched");
	}

	public void validateAdminMembershipFilterName(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Name", 0);
		CommonMethod.sendKeys("Name",
				data.getCellData(SheetName, "MembershipName", rowNum).trim());
		validateAdminMembershipFilterID(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminMembershipName", 0);
		String getExpectedData = CommonMethod.getattributeValueByTextContent("AdminMembershipName").trim();
		CommonMethod.negativesoftassertFieldValid(getExpectedData,
				data.getCellData(SheetName, "MembershipName", rowNum).trim(), "MembershipName Data does not matched");
	}

	public void getAndSetAdminMembershipFilterData(String SheetName, int rowNum)
			throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminMembershipIdTextBox", 0);
		String getMembershipId = data.getCellData(SheetName, "MembershipId", rowNum);
		CommonMethod.sendKeys("AdminMembershipIdTextBox", getMembershipId);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplyButton", 0);
		CommonMethod.click("ApplyButton");
		testlog.info("And User clicks on Apply Button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminMembershipName", 0);
		String getExpectedMembershipName = CommonMethod.getattributeValueByTextContent("AdminMembershipName");
		data.setCellData(SheetName, "MembershipName", 2, getExpectedMembershipName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminMembershipUserEmail", 0);
		String getExpectedOwnerEmail = CommonMethod.getattributeValueByTextContent("AdminMembershipUserEmail");
		data.setCellData(SheetName, "MembershipOwnerEmail", 2, getExpectedOwnerEmail);
		testlog.info("Given User fetch the data");
		testlog.info("When User added the data in excel");
	}

	public void ClickOnProfile() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ClickProfileImage", 0);
		CommonMethod.RobustclickElementVisible("ClickProfileImage", "V2ProjectProfileUpdatedToastMessage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("V2ProjectProfileUpdatedToastMessage", 0);
		CommonMethod.RobustclickElementVisible("V2ProjectProfileUpdatedToastMessage", "ProfileContactTab");
		CommonMethod.refreshBrowser();
	}

	public void ValidateUserProfilePersonalInformationFunctionality(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileEditButton", 0);
		CommonMethod.Robustclick("ProfileEditButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfilePersonalInformationTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfilePersonalInfoJobTitle", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrgClick", 0);
		CommonMethod.RobustclickElementVisible("OwnerOrgClick", "OwnerOrg");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("OwnerOrg", 0);
		CommonMethod.sendKeys("OwnerOrg", "+VG Architects");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileOrg", 0);
		CommonMethod.click("ProfileOrg");
		CommonMethod.clearAndSendKey("ProfilePersonalInfoJobTitle", "Software Engineer");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfilePersonalInfoIndustryDropdown", 0);
		CommonMethod.selectdropdownVisibletext("ProfilePersonalInfoIndustryDropdown", "Engineering");
		if (CommonMethod.isElementsExist("CloseSelectedIcon", 10)) {
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CloseSelectedIcon", 0);
			CommonMethod.Robustclick("CloseSelectedIcon");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioPrimarlyLocated", 0);
		CommonMethod.RobustclickElementVisible("PortfolioPrimarlyLocated", "PortfolioDocFilterCountry");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioDocFilterCountry", 0);
		CommonMethod.sendKeys("PortfolioDocFilterCountry", "English");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSelectPartnameSelect", 0);
		CommonMethod.JavascriptClickElement("MPSelectPartnameSelect");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileMaleRadioBtn", 0);
		CommonMethod.RobustclickElementVisible("ProfileMaleRadioBtn", "SaveChangesButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveChangesButton", 0);
		CommonMethod.Robustclick("SaveChangesButton");
	}
	
	public void ValidateUserProfilePersonalInformationFunctionality1(String SheetName, int rowNum)
			throws IOException, InterruptedException {
	CommonMethod.refreshBrowser();
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileUserName", 0);
	String actualUserName = CommonMethod.getattributeValueByTextContent("ProfileUserName");
	CommonMethod.negativesoftassertFieldValid(actualUserName, "UI Automation", "User Name does not matched");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateUserDesignation", 0);
	String actualDesignation = CommonMethod.getattributeValueByTextContent("ValidateUserDesignation");
	CommonMethod.negativesoftassertFieldValid(actualDesignation, "Software Engineer",
			"Designation does not matched");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateIndustryName", 0);
	String actualIndustryName = CommonMethod.getattributeValueByTextContent("ValidateIndustryName");
	CommonMethod.negativesoftassertFieldValid(actualIndustryName, "Engineering",
			"Industry Name: Engineering does not matched ");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateLanguage", 0);
	String actualLanguage = CommonMethod.getattributeValueByTextContent("ValidateLanguage");
	CommonMethod.negativesoftassertFieldValid(actualLanguage, "English", "Language: English does not matched ");
	}

	public void ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality(String SheetName, int rowNum,
			String GreenbuildAwardRadio, String GreenbuildAwardLogo) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileEditButton", 0);
		CommonMethod.Robustclick("ProfileEditButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfilePersonalInformationTab", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfilePersonalInfoJobTitle", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(GreenbuildAwardRadio, 0);
		CommonMethod.click(GreenbuildAwardRadio);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveChangesButton", 0);
		CommonMethod.Robustclick("SaveChangesButton");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException(GreenbuildAwardLogo, 1);
		CommonMethod.assertisElementPresentTrue(GreenbuildAwardLogo, "Logo is not showing or Wrong Logo is showing ");
	}

	public void ValidateUserProfileContactInformation(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileEditButton", 0);
		CommonMethod.Robustclick("ProfileEditButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileContactInformationTab", 0);
		CommonMethod.RobustclickElementVisible("ProfileContactInformationTab", "HsrWprEditCountry");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprEditCountry", 0);
		CommonMethod.selectdropdownVisibletext("HsrWprEditCountry", "India");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprEditState", 0);
		CommonMethod.selectdropdownVisibletext("HsrWprEditState", "Delhi");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprEditStreet", 0);
		CommonMethod.clearAndSendKey("HsrWprEditStreet", "Mohan Garden");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprEditCity", 0);
		CommonMethod.clearAndSendKey("HsrWprEditCity", "Delhi");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HsrWprPostalCode", 0);
		CommonMethod.clearAndSendKey("HsrWprPostalCode", "110059");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileSameMailingAddressCheckbox", 0);
		CommonMethod.ClickCheckbox("ProfileSameMailingAddressCheckbox");
		String PhoneNumber = USfaker.number().digits(10);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileEnterPhoneNumber", 0);
		CommonMethod.clearAndSendKey("ProfileEnterPhoneNumber", PhoneNumber);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileEnterWebsiteLink", 0);
		CommonMethod.clearAndSendKey("ProfileEnterWebsiteLink", "https://www.google.com/");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileEnterPhoneNumber", 0);
		CommonMethod.clearAndSendKey("ProfileEnterPhoneNumber", "1234567899");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileEnterLinkedInURL", 0);
		CommonMethod.clearAndSendKey("ProfileEnterLinkedInURL", "https://in.linkedin.com/");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileEnterTwitterURL", 0);
		CommonMethod.clearAndSendKey("ProfileEnterTwitterURL", "https://twitter.com/");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileEnterFacebookURL", 0);
		CommonMethod.clearAndSendKey("ProfileEnterFacebookURL", "https://www.facebook.com/");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveChangesButton", 0);
		CommonMethod.Robustclick("SaveChangesButton");
	}
	
	public void ValidateUserProfileContactInformationFunctionality(String SheetName, int rowNum)
			throws IOException, InterruptedException {
		ValidateUserProfileContactInformation(SheetName,rowNum);
		if (!TestCaseName.contains("ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality")) {
			CommonMethod.refreshBrowser();
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateLinkedInURL", 0);
			CommonMethod.click("ValidateLinkedInURL");
			Thread.sleep(3000);
			CommonMethod.switchToChildTab();
			Thread.sleep(3000);
			String childLinkedinUrl = CommonMethod.getCurrentUrl();
			CommonMethod.negativesoftassertFieldValid(childLinkedinUrl, "linkedin", "linkedin URL does not matched");
			CommonMethod.switchToParentTab();
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateTwitterURL", 0);
			CommonMethod.click("ValidateTwitterURL");
			Thread.sleep(3000);
			CommonMethod.switchToChildTab();
			Thread.sleep(3000);
			String childTwitterUrl = CommonMethod.getCurrentUrl();
			CommonMethod.negativesoftassertFieldValid(childTwitterUrl, "x.com", "twitter URL does not matched");
			CommonMethod.switchToParentTab();
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateFacebookURL", 0);
			CommonMethod.click("ValidateFacebookURL");
			Thread.sleep(3000);
			CommonMethod.switchToChildTab();
			Thread.sleep(3000);
			String childFacebookUrl = CommonMethod.getCurrentUrl();
			CommonMethod.negativesoftassertFieldValid(childFacebookUrl, "facebook", "facebook URL does not matched");
			CommonMethod.switchToParentTab();
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileContactTab", 0);
			CommonMethod.RobustclickElementVisible("ProfileContactTab", "ValidateBillingAddress");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateBillingAddress", 0);
			String actualBillingAddress = CommonMethod.getattributeValueByTextContent("ValidateBillingAddress");
			actualBillingAddress = actualBillingAddress.replaceAll("\\s+", " ").trim();
			CommonMethod.negativesoftassertFieldValid(actualBillingAddress, "Mohan Garden, Delhi, IN",
					"Billing Address: Mohan Garden, Delhi, IN does not matched");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidatePhoneNumber", 0);
			String actualPhoneNumber = CommonMethod.getattributeValueByTextContent("ValidatePhoneNumber");
			actualPhoneNumber = actualPhoneNumber.replaceAll("\\s+", " ").trim();
			CommonMethod.negativesoftassertFieldValidEquals(actualPhoneNumber, "1234567899",
					"Phone No: 1234567899 does not matched");
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateWebsiteURL", 0);
			CommonMethod.click("ValidateWebsiteURL");
			Thread.sleep(3000);
			CommonMethod.switchToChildTab();
			Thread.sleep(3000);
			String childGoogleUrl = CommonMethod.getCurrentUrl();
			CommonMethod.negativesoftassertFieldValid(childGoogleUrl, "google",
					"WebsiteURL: google.com URL does not matched ");
			CommonMethod.switchToParentTab();
		}
	}

	public void ValidateUserProfileAboutFunctionality(String SheetName, int rowNum)
			throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileEditButton", 0);
		CommonMethod.Robustclick("ProfileEditButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileAboutTab", 0);
		CommonMethod.RobustclickElementVisible("ProfileAboutTab", "ProfileShareBio");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileShareBio", 0);
		CommonMethod.clearAndSendKey("ProfileShareBio", "Share your bio");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileWhyIJoinedWellCommunity", 0);
		CommonMethod.clearAndSendKey("ProfileWhyIJoinedWellCommunity", "Why I joined the WELL community");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileWellMeanTextBox", 0);
		CommonMethod.clearAndSendKey("ProfileWellMeanTextBox", "What does wellness mean to you");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveChangesButton", 0);
		CommonMethod.Robustclick("SaveChangesButton");
		CommonMethod.refreshBrowser();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateAboutMe", 0);
		String actualAboutMeText = CommonMethod.getattributeValueByTextContent("ValidateAboutMe");
		actualAboutMeText = actualAboutMeText.replaceAll("\\s+", " ").trim();
		CommonMethod.negativesoftassertFieldValidEquals(actualAboutMeText, "Share your bio",
				"About me: Share your bio text does not matched ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateWELLCommunity", 0);
		String actualWellCommunityText = CommonMethod.getattributeValueByTextContent("ValidateWELLCommunity");
		actualWellCommunityText = actualWellCommunityText.replaceAll("\\s+", " ").trim();
		CommonMethod.negativesoftassertFieldValidEquals(actualWellCommunityText, "Why I joined the WELL community",
				"Why I joined the WELL community text does not matched ");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ValidateWellness", 0);
		String actualWellnessText = CommonMethod.getattributeValueByTextContent("ValidateWellness");
		actualWellnessText = actualWellnessText.replaceAll("\\s+", " ").trim();
		CommonMethod.negativesoftassertFieldValidEquals(actualWellnessText, "What does wellness mean to you",
				"What wellness means to me Text does not matched ");
	}

	public void ValidateUserProfileUpdateLogInFunctionality(String SheetName, int rowNum)
			throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileEditButton", 0);
		CommonMethod.Robustclick("ProfileEditButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProfileUpdateLogInInfoTab", 0);
		CommonMethod.click("ProfileUpdateLogInInfoTab");
		Thread.sleep(3000);
		CommonMethod.switchToChildTab();
		Thread.sleep(3000);
		String childSupportUrl = CommonMethod.getCurrentUrl();
		CommonMethod.negativesoftassertFieldValid(childSupportUrl, "how-to-update-you-well-online-account",
				"Support URL does not matched ");
		CommonMethod.switchToParentTab();
	}

	public void startNavigatesubmitProposal() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPUserMenu", 0);
		CommonMethod.RobustclickElementVisible("MPUserMenu", "MPMyMembership");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPMyMembership", 0);
		CommonMethod.RobustclickElementVisible("MPMyMembership", "MPGetStartedWithWELL");
		CommonMethod.refreshBrowser();
	}

//	public void navigatesubmitProposal() throws IOException, InterruptedException {
//		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPUserMenu", 0);
//		CommonMethod.RobustclickElementVisible("MPUserMenu", "MPMyMembership");
//		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPMyMembership", 0);
//		CommonMethod.RobustclickElementVisible("MPMyMembership", "MPGetStartedWithWELL");
//	}

	public void submitProposal(String SheetName, int rowNum) throws IOException, InterruptedException {
		startNavigatesubmitProposal();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPGetStartedWithWELL", 0);
		CommonMethod.RobustclickElementVisible("MPGetStartedWithWELL", "MPPLCommunications");
		CommonMethod.scrollDown();
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("MPPLCommunicationsContinueBtn");
		CommonMethod.negativeAssertElementNotPresentFalse("MPPLCommunicationsContinueBtn",
				"Continue button is visible");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalLink", 0);
		CommonMethod.RobustclickElementVisible("MPProposalLink", "MPProposalSubmitToasterMsg");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalSubmitToasterMsg", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalSubmitToasterMsg"),
				"User must first click on the table before they can click",
				"MPProposal Link disable Button ToasterMsg");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalContinueBtn", 0);
		CommonMethod.RobustclickElementVisible("MPProposalContinueBtn",
				"MPProposalCommunicationsContinueBtnToasterMsg");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalCommunicationsContinueBtnToasterMsg", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalCommunicationsContinueBtnToasterMsg"),
				"Please view the list of eligible categories",
				"MPProposal CommunicationsContinue disable Button ToasterMsg");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLCommunications", 0);
		CommonMethod.RobustclickElementVisible("MPPLCommunications", "MPPLCommunicationsContinueBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalLink", 0);
		CommonMethod.RobustclickElementVisible("MPProposalLink", "StreetWithId");
		testlog.info("And User is select on Product Licensing Communications group");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AccountCloseBackButtonCommon", 0);
		CommonMethod.RobustclickElementVisible("AccountCloseBackButtonCommon", "MPPLCommunications");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPPLCommunications", 0);
		CommonMethod.RobustclickElementVisible("MPPLCommunications", "MPPLCommunicationsContinueBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposal", 0);
		CommonMethod.Robustclick("MPProposal");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("MPProposal", 1);
		CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("MPProposalSubmitContinueBtn");
		CommonMethod.negativeAssertElementNotPresentFalse("MPProposalSubmitContinueBtn",
				"MPProposalSubmitContinueBtn is visible");
		testlog.pass("**Verifies the Completed Card Payment Billing successfully**");
	}

	public void fillProposalpage(String SheetName, int rowNum) throws IOException, InterruptedException {

		if (TestCaseName.contains("FillProposal")) {
			rc.SelectCountryAndState("US", SheetName, rowNum);
			rc.SelectOwnerOrg(SheetName, rowNum);
			String ProjectAddress = USfaker.address().streetAddress();
			String ProjectCity = USfaker.address().cityName();
			String PostalCode = USfaker.address().zipCode();
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("StreetWithId", 0);
			CommonMethod.sendKeys("StreetWithId", ProjectAddress);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CityWithId", 0);
			CommonMethod.sendKeys("CityWithId", ProjectCity);
			CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PostalWithId", 0);
			CommonMethod.sendKeys("PostalWithId", PostalCode);
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalName", 0);
		CommonMethod.sendKeys("MPProposalName", "Test Proposal");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalCategoryRadioBtn", 0);
		CommonMethod.ClickCheckbox("MPProposalCategoryRadioBtn");
		CommonMethod.VerifyRadioOrCheckboxSelcted("MPProposalCategoryRadioBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalTypeRadioBtn", 0);
		CommonMethod.ClickCheckbox("MPProposalTypeRadioBtn");
		CommonMethod.VerifyRadioOrCheckboxSelcted("MPProposalTypeRadioBtn");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MPProposalProductCatergory", 1);
		CommonMethod.negativeAssertElementPresentTrue("MPProposalProductCatergory", "Category dropdown is disabled");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MPProposalProductType", 1);
		CommonMethod.negativeAssertElementPresentTrue("MPProposalProductType", "Type dropdown is disabled");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalWellConcept", 0);
		CommonMethod.selectdropdownVisibletext("MPProposalWellConcept", "Air");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalFeaturePart", 0);
		CommonMethod.RobustclickElementVisible("MPProposalFeaturePart", "MPProposalFeaturePartName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalFeaturePartName", 0);
		CommonMethod.JavascriptClickElement("MPProposalFeaturePartName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalFeatureProductNameMeet", 0);
		String FeatureProductNameMeet = "Feature part comment Test Comment";
		data.setCellData(SheetName, "FeatureProductNameMeet", rowNum, FeatureProductNameMeet);
		CommonMethod.sendKeys("MPProposalFeatureProductNameMeet", FeatureProductNameMeet);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveChangesButton", 0);
		CommonMethod.RobustclickElementVisible("SaveChangesButton", "MPProposalAddedFeatureProductNameMeet");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalAddedFeatureProductNameMeet", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioScorecardUploadRemovelink", 1);
		CommonMethod.negativeAssertElementPresentTrue("PortfolioScorecardUploadRemovelink", "Removelink not present");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalAddedFeatureProductNameMeet"),
				"Meet Thresholds for Particulate Matter", "MPProposal Added FeatureProductName not match");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalAddedFeatureCommentNameMeet"), "Test Comment",
				"MPProposal Added Comment Name not match");
		CommonMethod.selectdropdownVisibletext("MPProposalWellConcept", "Air");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalFeaturePart", 0);
		CommonMethod.RobustclickElementVisible("MPProposalFeaturePart", "MPProposalFeaturePartName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalFeaturePartName", 0);
		CommonMethod.JavascriptClickElement("MPProposalFeaturePartName");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalFeatureProductNameMeet", 0);
		CommonMethod.sendKeys("MPProposalFeatureProductNameMeet", "Test Comment");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SaveChangesButton", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioScorecardUploadRemovelink", 1);
		CommonMethod.negativeAssertElementPresentTrue("PortfolioScorecardUploadRemovelink", "Removelink not present");
		CommonMethod.uploadFile("DocumentsUpload", FeaturefileUpload, "UploadFileVerifyScorecard");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalAddedFeatureOtherCommentTypeName", 0);
		String OtherCommentTypeName = "Product type Test Comment";
		data.setCellData(SheetName, "OtherCommentTypeName", rowNum, OtherCommentTypeName);
		CommonMethod.sendKeys("MPProposalAddedFeatureOtherCommentTypeName", OtherCommentTypeName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalAddedFeatureOtherCommentCatergoryName", 0);
		String OtherCommentcategoryName = "Product category Test Comment";
		data.setCellData(SheetName, "OtherCommentcategoryName", rowNum, OtherCommentcategoryName);
		CommonMethod.sendKeys("MPProposalAddedFeatureOtherCommentCatergoryName", OtherCommentcategoryName);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalSubmitContinueBtn", 0);
		CommonMethod.Robustclick("MPProposalSubmitContinueBtn");
		testlog.pass("**Fill the proposal fields successfully**");
	}

	public void billingProposal(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("InvoicePaymentAmount", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("InvoicePaymentAmount"),
				"220", "InvoicePaymentAmount doesn't match");
		rc.Billing(SheetName, rowNum);
	}

	public void thanksProposalPage() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalThankLink", 0);
		CommonMethod.RobustclickElementVisible("MPProposalThankLink", "MPProposalTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalTab", 0);
		testlog.pass("**Verifies the Completed Card Payment Billing for Proposal successfully**");

	}

	public void csthanksProposalPage() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalThankLink", 0);
		CommonMethod.JavascriptClickElement("MPProposalThankLink");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalclickPorposalId", 0);
		testlog.pass("**Verifies the Completed Card Payment Billing for Proposal successfully**");

	}

	public void validateTab(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("BiilingTab", 1);
		CommonMethod.negativeAssertElementPresentTrue("BiilingTab", "BiilingTab is not present");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("LicensingTab", 1);
		CommonMethod.negativeAssertElementPresentTrue("LicensingTab", "LicensingTab is not present");
	}

	public void workersWithWellProposalValidate(String SheetName, int rowNum, String Status)
			throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalTab", 0);
		CommonMethod.RobustclickElementVisible("MPProposalTab", "PortfolioAndRatingLocAccDocumentTable");
		if (Status.equalsIgnoreCase("PENDING")) {
			CommonMethod.WaitUntilNumberOfElementToBeNotPresentWithException("MPProposal");
			CommonMethod.negativeAssertElementNotPresentFalse("MPProposal", "MPProposal is enabled");
		}
		if (Status.equalsIgnoreCase("REVIEWED")) {
			CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MPProposal", 1);
			CommonMethod.negativeAssertElementPresentTrue("MPProposal", "MPProposal is disabled");
		}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalTable", 0);
		List<String> val = CommonMethod.fetchTableData("MPProposalTable");
		testlog.info("Fetching Data from Upload Table");
		CommonMethod.negativesoftassertFieldValid(val.get(1), Status, "Proposal Status doesn't match");
		CommonMethod.negativesoftassertFieldValid(val.get(2), CommonMethod.ValidateDateYearAndDateWithHypen(),
				"Proposal Date doesn't match");
		CommonMethod.negativesoftassertFieldValid(val.get(3), "UI", "Proposal User doesn't match");
		data.setCellData(SheetName, "ProposalId", rowNum, val.get(0));
		testlog.info("Store Proposal id: " + val.get(0));
		testlog.pass("**Store Proposal id and validate table successfully**");
	}

	public void validateProposalStatus(String SheetName, int rowNum, String Status)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalclickPorposalId", 0);
		CommonMethod.RobustclickElementVisible("MPProposalclickPorposalId", "MPProposalWorksWellStatus");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalWorksWellStatus", 0);
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MPProposalBackToProposal", 1);
		CommonMethod.negativeAssertElementPresentTrue("MPProposalBackToProposal",
				"MPProposalBackToProposal is not present");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalWorksWellStatus"), Status,
				"Well Proposal Status doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalWorksWellId", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValueByTextContent("MPProposalWorksWellId"),
				data.getCellData(SheetName, "ProposalId", rowNum), "WellProposalId doesn't match");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalWellProductCategory"),
				data.getCellData(SheetName, "OtherCommentcategoryName", rowNum), "WellProposalId doesn't match");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalWellProductType"),
				data.getCellData(SheetName, "OtherCommentTypeName", rowNum), "WellProposalId doesn't match");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalWellFeaturePartName"),
				"Meet Thresholds for Particulate Matter", "WellProposalId doesn't match");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalWellFeaturePartComment"),
				data.getCellData(SheetName, "FeatureProductNameMeet", rowNum), "WellProposalId doesn't match");

	}

	public void ProposalEditButton() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("MPProposalWellEditButton", 1);
		CommonMethod.negativeAssertElementPresentTrue("MPProposalWellEditButton", "MPProposal EditButton is enabled");
	}

	public void AdminProposalDocument() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalAdminValidAddedDocument", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalAdminValidAddedDocument"), "FeatureFile",
				"Added Document doesn't match");
	}

	public void AdminProposalNavigate(String SheetName, int rowNum, String Status)
			throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar", "AdminProposalsTab");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminProposalsTab", 0);
		CommonMethod.RobustclickElementVisible("AdminProposalsTab", "MPProposalAdminTextbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalAdminTextbox", 0);
		String ProposalId = data.getCellData(SheetName, "ProposalId", rowNum);
		CommonMethod.sendKeys("MPProposalAdminTextbox", ProposalId);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ApplyButton", 0);
		CommonMethod.click("ApplyButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("PortfolioAndRatingLocAccDocumentTableTr", 1);
		int MPProposalclickPorposalId = CommonMethod.ElementSize("PortfolioAndRatingLocAccDocumentTableTr");
		CommonMethod.negativesoftassertFieldValidEquals(String.valueOf(MPProposalclickPorposalId), "1",
				"TableTrSize in Proposal list doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("PortfolioAndRatingLocAccDocumentTable", 0);
		List<String> val = CommonMethod.fetchTableData("PortfolioAndRatingLocAccDocumentTable");
		testlog.info("Fetching Data from Upload Table");
		CommonMethod.negativesoftassertFieldValid(val.get(0), ProposalId, "Proposal id doesn't match");
		CommonMethod.negativesoftassertFieldValid(val.get(2), "A01.1", "Proposal Feature name doesn't match");
		CommonMethod.negativesoftassertFieldValid(val.get(3), "UNITED STATES", "Proposal Country doesn't match");
		CommonMethod.negativesoftassertFieldValid(val.get(4), CommonMethod.ValidateDateYearAndDateWithHypen(),
				"Proposal Date doesn't match");
		CommonMethod.negativesoftassertFieldValid(val.get(5), "UI", "Proposal Username doesn't match");
		CommonMethod.negativesoftassertFieldValid(val.get(6), Status, "Proposal Status doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException("V2ProjectDocumentValid", 1);
		CommonMethod.negativeAssertElementPresentTrue("V2ProjectDocumentValid",
				"MPProposal delete icon is not visible");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalclickPorposalId", 0);
	}

	public void AdminAddReviewResult(String SheetName, int rowNum) throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalAdminAddReviewResult", 0);
		CommonMethod.RobustclickElementVisible("MPProposalAdminAddReviewResult", "MPProposalAdminCompatibleCheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalAdminCompatibleCheckbox", 0);
		CommonMethod.ClickCheckbox("MPProposalAdminCompatibleCheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalAdminSubmitReviewResult", 0);
		CommonMethod.Robustclick("MPProposalAdminSubmitReviewResult", "MPProposalAdminCompatibleCheckbox");
		CommonMethod.WaitUntilNumberOfElementToBePresentLessThan("MPProposalAdminSubmitReviewResult", 1);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalWorksWellStatus", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalWorksWellStatus"), "Reviewed",
				"Well Proposal Reviewed Status doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalBackToProposal", 0);
		CommonMethod.RobustclickElementVisible("MPProposalBackToProposal", "MPProposalAdminTextbox");
	}

	public void validateProposalReviewResult(String SheetName, int rowNum) throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalclickPorposalId", 0);
		CommonMethod.RobustclickElementVisible("MPProposalclickPorposalId", "MPProposalReviewResultProductCategory");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalReviewResultProductCategory", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalReviewResultProductCategory"),
				data.getCellData(SheetName, "OtherCommentcategoryName", rowNum),
				"Well OtherCommentcategoryName doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalReviewResultProductType", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalReviewResultProductType"),
				data.getCellData(SheetName, "OtherCommentTypeName", rowNum), "Well OtherCommentTypeName doesn't match");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalReviewResultReviewStatus"), "Compatible",
				"Well ReviewResult ReviewStatus doesn't match");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalReviewResultOption", 0);
		CommonMethod.RobustclickElementVisible("MPProposalReviewResultOption",
				"MPProposalReviewResultFeaturePartComment");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalReviewResultFeaturePartComment", 0);
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalReviewResultFeaturePartComment"),
				data.getCellData(SheetName, "FeatureProductNameMeet", rowNum), "Well FeatureProductName doesn't match");
		CommonMethod.negativesoftassertFieldValid(
				CommonMethod.getattributeValueByTextContent("MPProposalReviewResultFeaturePartIdComment"), "A01.1",
				"Well FeatureProductId doesn't match");
		CommonMethod.negativeAssertElementPresentTrue("MPProposalReviewResultContinueErollbtn",
				"MPProposal ReviewResult ContinueErollbtnnot visible");
	}

	public void MPProposalTabNavigate() throws IOException, InterruptedException {

		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposalTab", 0);
		CommonMethod.RobustclickElementVisible("MPProposalTab", "MPProposal");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPProposal", 0);
		CommonMethod.RobustclickElementVisible("MPProposal", "PostalWithId");

	}

	public void navigateAuthsubmitProposal() throws IOException, InterruptedException {
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPUserMenu", 0);
		CommonMethod.RobustclickElementVisible("MPUserMenu", "MPMyMembership");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPMyMembership", 0);
		CommonMethod.RobustclickElementVisible("MPMyMembership", "MPProposalTab");
	}

	public void proposalFlow(String SheetName, int rowNum)
			throws IOException, InterruptedException, ClientApiException {

		membership.navigateAuthsubmitProposal();
		membership.MPProposalTabNavigate();
		membership.fillProposalpage(SheetName, rowNum);
		membership.csthanksProposalPage();
		membership.workersWithWellProposalValidate(SheetName, rowNum, "PENDING");
		login.AdminLogin();
		membership.AdminProposalNavigate(SheetName, rowNum, "PENDING");
		membership.validateProposalStatus(SheetName, rowNum, "Pending");
		membership.AdminAddReviewResult(SheetName, rowNum);
		rc.SignOut();
		login.Login();
	}

	public static JSONObject submitProductLineProposalPayload(String SheetName, int rowNum) throws IOException {
		JSONObject projObj1 = new JSONObject();
		JSONArray projArr = new JSONArray();
		JSONArray projArr1 = new JSONArray();
		JSONObject paramData = new JSONObject();
		paramData.put("first_name", "UI");
		paramData.put("last_name", "Automation");
		paramData.put("email", data.getCellData(SheetName, "Email", rowNum));
		paramData.put("country_code", "US");
		paramData.put("city", USfaker.address().cityName());
		paramData.put("street", USfaker.address().streetAddress());
		paramData.put("state", "Alaska");
		paramData.put("postal_code", USfaker.address().zipCode());
		paramData.put("product_category_id", 2);
		paramData.put("product_type_id", 7);
		paramData.put("name", "Test Proposal for A01.1");
		paramData.put("company_id", data.getCellData(SheetName, "CompanyId", rowNum));
		projArr.add("https://well-v2-dev.s3.amazonaws.com/membership/FeatureFile-_~_-61301a.xlsx");
		projObj1.put("part", "A01.1");
		projObj1.put("part_comment", "Test requirement");
		projArr1.add(projObj1);
		paramData.put("parts", projArr1);
		paramData.put("documents", projArr);
		return paramData;

	}

	public void getCompanyId(String SheetName, int rowNum, int userRow) throws IOException {

		@SuppressWarnings("static-access")
		String header = commonAPI.PostRequestAuthenticate("UserName", userRow);
		Response res = given().log().all().accept("application/json").contentType("application/json")
				.header("Authorization", header).when().get("companies");
		System.out.println(res.asString());
		int StatusCode = res.getStatusCode();
		Assert.assertEquals(StatusCode, 200, "Verifying status code");
		String companyId = (res.path("id[0]")).toString();
		System.out.println(companyId);
		data.setCellData(SheetName, "CompanyId", rowNum, companyId);
	}

	public void SubmitProductLineProposal(String SheetName, int rowNum, int userRow) throws IOException {

		String createProposal = submitProductLineProposalPayload(SheetName, rowNum).toString();
		@SuppressWarnings("static-access")
		String header = commonAPI.PostRequestAuthenticate("UserName", userRow);
		Response res = given().log().all().accept("application/json").contentType("application/json")
				.header("Authorization", header).when().body(createProposal).post("product-line-proposal");
		System.out.println(res.asString());
		int StatusCode = res.getStatusCode();
		Assert.assertEquals(StatusCode, 201, "Verifying status code");
		String id = (res.path("id")).toString();
		String proposal_number = (res.path("proposal_number")).toString();
		String invoice = (res.path("invoice.id")).toString();
		data.setCellData(SheetName, "ProposalId", rowNum, id);
		data.setCellData(SheetName, "ProposalName", rowNum, proposal_number);
		data.setCellData(SheetName, "ProposalInvoice", rowNum, invoice);
	}

	public void GetProductLicenseCategories(String SheetName, int rowNum, int userRow) throws IOException {
		String CategoryID = null;
		String CategoryName = null;
		String SubCategoryName = null;
		@SuppressWarnings("static-access")
		String header = commonAPI.PostRequestAuthenticate("UserName", userRow);
		Response res = given().log().all().accept("application/json").contentType("application/json")
				.header("Authorization", header).when().post("product-license/categories");
		System.out.println(res.asString());
		int StatusCode = res.getStatusCode();
		Assert.assertEquals(StatusCode, 201, "Verifying status code");
	    CategoryID =(res.path("id[0]")).toString();
		data.setCellData(SheetName,"ProductCategoryID",rowNum, CategoryID);
		CategoryName =(res.path("name[0]")).toString();
		data.setCellData(SheetName,"ProductCategoryName",rowNum, CategoryName);
		SubCategoryName =(res.path("product_category_types[0].name[0]")).toString();
		data.setCellData(SheetName,"ProductSubCategoryName",rowNum, SubCategoryName);
		}
	
	public static JSONObject updateProductLinePayload(String SheetName, int rowNum, int userRow) throws IOException {
		JSONObject projObj = new JSONObject();
		JSONObject projObj1 = new JSONObject();
		JSONArray projArr = new JSONArray();
		JSONArray projArr1 = new JSONArray();
		JSONObject paramData = new JSONObject();
		paramData.put("name", "Updated Group1");
		paramData.put("product_category_type_id", data.getCellData(SheetName, "ProductCategoryID", 2));
		paramData.put("comment", "Updated Test Comment");
		paramData.put("questionnaire_url",
				"https://well-v2-dev.s3.amazonaws.com/product-licensing/2/TestData_7-_~_-c745a1.xlsx");
		projObj.put("id", data.getCellData(SheetName, "ProductLicenseID", 2));
		projObj1.put("link_s3", "https://well-v2-dev.s3.amazonaws.com/product-licensing/2/TestData_7-_~_-c745a1.xlsx");
		projArr.add(projObj1);
		projArr1.add(data.getCellData(SheetName, "ProductCategoryPartPrefix", 2));
		paramData.put("parts", projArr1);
		paramData.put("support_document_urls", projArr);
		paramData.put("sku_url",
				"https://well-v2-dev.s3.amazonaws.com/product-licensing/364/ProductSku_Import_Test_Data-_~_-1574a8.xlsx");
		return paramData;
	}

	public void UpdateProductLineProposal(String SheetName, int rowNum, int userRow) throws IOException {
		String updateProposal = updateProductLinePayload(SheetName, rowNum, userRow).toString();
		String proposalId = data.getCellData(SheetName, "ProposalId", rowNum);
		pathprms.put("proposal", proposalId);
		@java.lang.SuppressWarnings("static-access")
		String amdinHeader = commonAPI.PostRequestAuthenticate("AdminUserName", userRow);
		Response res = given().log().all().accept("application/json").contentType("application/json")
				.header("Authorization", amdinHeader).when().body(updateProposal)
				.put("product-line-proposal/{proposal}");
		System.out.println(res.asString());
		int StatusCode = res.getStatusCode();
		Assert.assertEquals(StatusCode, 200, "Verifying status code");
	}

public void ValidateMidReviewClarification() throws Exception {
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPMidReviewClarificationBtn", 0);
	CommonMethod.RobustclickElementVisible("MPMidReviewClarificationBtn", "CommentBox");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("CommentBox", 0);
	CommonMethod.clearAndSendKey("CommentBox", "Mid Review Clarification Comment");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ClickDate", 0);
	CommonMethod.RobustclickElementVisible("ClickDate", "CommunityAlternativeDateOkBtn");
	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPSubmitResponseBtn", 0);
	CommonMethod.RobustclickElementVisible("MPSubmitResponseBtn", "MPValidateUnderReview");
	rc.RemoveSpaceAndValidate("MPValidateUnderReview", "UNDER REVIEW");
}

    public void ClickForgotYourPasswordLink() throws IOException, InterruptedException {
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPForgotYourPassword", 0);
    	CommonMethod.RobustclickElementVisible("MPForgotYourPassword", "MPRequestPasswordBtn");   	
    }
    
    public void ValidateForgotPasswordFunctionality(String Email, String ValidMsg) throws IOException, InterruptedException {
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPForgotYourPasswordTextbox", 0);
    	CommonMethod.clearAndSendKey("MPForgotYourPasswordTextbox", Email);
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("MPRequestPasswordBtn", 0);
    	CommonMethod.RobustclickElementVisible("MPRequestPasswordBtn", "MPValidateResetPasswordMsg");
    	rc.RemoveSpaceAndValidate("MPValidateResetPasswordMsg", ValidMsg);
    	CommonMethod.refreshBrowser();
    }
}