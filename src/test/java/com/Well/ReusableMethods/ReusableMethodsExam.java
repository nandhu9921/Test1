package com.Well.ReusableMethods;

import java.io.IOException;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class ReusableMethodsExam extends BaseClass {

	public void RegisterExam(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL certified account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("TrainingNavBar", 0);
		CommonMethod.RobustclickElementVisible("TrainingNavBar","WELLExamRatingNavBar");
		testlog.info("When User clicks on WELL Exam from top menu under Training");
		CommonMethod.RobustclickElementVisible("WELLExamRatingNavBar","WFExamContinuebtn");
		CommonMethod.RobustclickElementVisible("WFExamContinuebtn","APRegContinue");
		testlog.info("Then User will be redirected to the Register now page successfully");
		Thread.sleep(5000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("WPRExamOwnerCountry", 0);
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("WPRExamOwnerCountry"),"India", "Country Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("WPRExamOwnerState"),"Delhi", "State Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("WPRExamOrgAddress"),"Mohan Garden", "Street Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("WPRExamOrgCity"),"Delhi", "City Name Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("WPRExamOrgPostalcode"),"110059", "Postal code Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("APPhoneNo"),"1234567899", "Phone Number Error Mismatch");
		testlog.info("And User verifies the added details about you by clicking on back button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APRegContinue", 0);
		CommonMethod.RobustclickElementVisible("APRegContinue","APJobtitle");
		testlog.info("And User clicks on the continue button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APJobtitle", 0);
		testlog.info("Then User will be redirected to Tell us more about you page");
		testlog.pass("**Verifies the Registration successful**");
	}
	
	public void EnrollExam(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User is on Tell us more about you page");
		CommonMethod.RobustclickElementVisible("APJobtitleContinuebtn","APJobtitle");
		CommonMethod.negativesoftassertPageSource("Industry/Sector is required.", "Industry/Sector Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Gender* is required.", "Gender Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Date of birth* is required.", "Date of birth Error Mismatch");
		testlog.info("And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APJobtitle", 0);
		data.setCellData(SheetName, "APJobtitle", rowNum, CommonMethod.getattributeValue("APJobtitle"));
		data.setCellData(SheetName, "OrgName", rowNum, CommonMethod.getText("OrgName"));
		testlog.info("And User enters data to Jobtitle, OrgName, Country, State fields");
		//testlog.info("firstName: " + firstName);
		testlog.info("OrganizationName: " + data.getCellData(SheetName, "OrgName", rowNum));
		CommonMethod.selectdropdownrandom("APIndustry");
		data.setCellData(SheetName, "APIndustry", rowNum, CommonMethod.getSelectedDropdownValue("APIndustry"));
		testlog.info("And User selects the Industry Sector");
		CommonMethod.ClickCheckbox("APDesignationscbx");
		testlog.info("And User checks the designations checkbox");
		CommonMethod.ClickCheckbox("APGender");
		testlog.info("And User selects the Gender");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerButton", 0);
		CommonMethod.RobustclickElementVisible("DatePickerButton","APYearDOB");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APYearDOB", 0);
		CommonMethod.RobustclickElementVisible("APYearDOB","APSelctyear");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APSelctyear", 0);
		CommonMethod.RobustclickElementVisible("APSelctyear","DatePickerOkButton");
		Thread.sleep(1000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("DatePickerOkButton", 0);
		CommonMethod.RobustclickElementVisible("DatePickerOkButton","DatePickerOkButton");
		CommonMethod.RobustclickElementVisible("DatePickerOkButton","APJobtitleContinuebtn");
		testlog.info("User enters the Date of birth");
		CommonMethod.RobustclickElementVisible("APJobtitleContinuebtn","APAlmostContinuebtn");
		testlog.info("And User clicks on continue button");
		CommonMethod.RobustclickElementVisible("WPRBackToHomepagebutton","APJobtitleContinuebtn");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getattributeValue("APJobtitle"),data.getCellData(SheetName, "APJobtitle", rowNum), "Job Title Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getText("OrgName"),data.getCellData(SheetName, "OrgName", rowNum), "OrgName Error Mismatch");
		CommonMethod.negativesoftassertFieldValid(CommonMethod.getSelectedDropdownValue("APIndustry"),data.getCellData(SheetName, "APIndustry", rowNum), "APIndustry Error Mismatch");
		testlog.info("And User clicks on Back button without entering the mandatory fields and verifies error meassage");
		CommonMethod.VerifyRadioOrCheckboxSelcted("APDesignationscbx");
		CommonMethod.VerifyRadioOrCheckboxSelcted("APGender");
		testlog.info("And User verifies Designations and Gender checkboxes");
		CommonMethod.RobustclickElementVisible("APJobtitleContinuebtn","APAlmostContinuebtn");
		testlog.info("Then User will be redirected to Almost finished page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APAlmostContinuebtn", 0);
		CommonMethod.RobustclickElementVisible("APAlmostContinuebtn","APSelectWELLAP");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APSelectWELLAP", 0);
		CommonMethod.WaitUntilNumberOfElementMoreThanToBePresentWithException("MandatoryFieldErrorMessage");
		CommonMethod.negativesoftassertPageSource("How did you hear about the WELL AP?* is required.", "How did you hear about the WELL AP Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Exam language is required.", "Exam language Error Mismatch");
		CommonMethod.negativesoftassertPageSource("Agreement is required.", "Agreement Error Mismatch");
		testlog.info("And User clicks on continue button without entering the mandatory fields and verifies error meassage");
		 CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APSelectWELLAP", 0);
		CommonMethod.selectdropdownIndex("APSelectWELLAP", 1);
		testlog.info("And User selects How did you hear about the WELL AP field");
		data.setCellData(SheetName, "APSelectWELLAP", rowNum, CommonMethod.getSelectedDropdownValue("APSelectWELLAP"));
		CommonMethod.ClickCheckbox("APPrimaryReason");
		testlog.info("And User checks the primary reasons checkbox");
		CommonMethod.selectdropdownIndex("APLanguage", 1);
		testlog.info("And User selects the Exam language");
		data.setCellData(SheetName, "APLanguage", rowNum, CommonMethod.getSelectedDropdownValue("APLanguage"));
		CommonMethod.ClickCheckbox("APAgree");
		testlog.info("And User checks the Exam Appeals Policy checkbox");
		CommonMethod.RobustclickElementVisible("APAlmostContinuebtn","APPay");
		testlog.info("And User clicks on submit button");
		if(TestCaseName.equalsIgnoreCase("Exam_TC_07_01_EnrollExamAndValidateBundleCount")) {
	    ValidateBundlesCount();
	    CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APExpectedSUSTAINABLEExamFee", 0);
		String expectedAPSadaExamFee = CommonMethod.getattributeValueByTextContent("APExpectedSUSTAINABLEExamFee");
		expectedAPSadaExamFee = expectedAPSadaExamFee.replaceAll("\\s+", " ").trim();
		data.setCellData("Exam", "APExamFee", 2, expectedAPSadaExamFee);
		CommonMethod.RobustclickElementVisible("APSadaExamPay","BillingLanding");
		testlog.info("And User clicks on Pay button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.info("Then User will be redirected to Billing Invoice page");
		testlog.pass("**Verifies the Enroll Exam successfully**");
		} else {	
		CommonMethod.RobustclickElementVisible("APPay","BillingLanding");
		testlog.info("And User clicks on Pay button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		testlog.info("Then User will be redirected to Billing Invoice page");
		testlog.pass("**Verifies the Enroll Exam successfully**");
		}
	}

	public void BillingExam(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User will be Billing Invoice page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("BillingLanding", 0);
		rc.Billing(SheetName, rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APStoreExamId", 0);
		testlog.info("Then User will be redirected to ExamId page");
		testlog.pass("**Completed Card Payment Billing successfully**");
	}

	public void StoreExam(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User will be ExamId page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APStoreExamId", 0);
		testlog.info("Then User will be redirected to ExamId page");
		String getId = CommonMethod.getText("APStoreExamId");
		data.setCellData(SheetName, "examId", rowNum, getId);
		testlog.info("And User will be store ExamId in excel");
		testlog.info("ExamId: "+getId);
		testlog.pass("**Stored the Registered id  in excel successfully**");
	}

	public void CancelExam(String SheetName, int rowNum) throws IOException, InterruptedException {
		testlog.info("Given User logged in to the WELL certified Admin account");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminNavBar","AdminWELLExamNavBar");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("AdminWELLExamNavBar", 0);
		CommonMethod.RobustclickElementVisible("AdminWELLExamNavBar","APId");
		testlog.info("When User clicks on WELL Exam from top menu under Training");
		CommonMethod.WaitUntilClickble("APId", 60).sendKeys(data.getCellData(SheetName, "examId", rowNum));
		testlog.info("And User enter data to APId field");
		CommonMethod.RobustclickElementVisible("APApplybtn","APClickId");
		testlog.info("And User clicks on Apply Button");
		CommonMethod.scrolldowntoLast();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APClickId", 0);
		CommonMethod.RobustclickElementVisible("APClickId","APCancelExam");
		testlog.info("And User clicks on APId");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APCancelExam", 0);
		testlog.info("Then User will be redirected to Exam Cancel page");
		CommonMethod.Robustclick("APCancelExam");
		testlog.info("And User clicks on Exam Cancel Button");
		testlog.pass("**Verifies Cancel the Exam successfully**");
	}
	
    public void ValidateBundlesCount() throws IOException, InterruptedException {
		
    	CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APAllBundles", 0);
    	int APBundlesCount = CommonMethod.ElementSize("APAllBundles");
    	String actualAPBundlesCount = Integer.toString(APBundlesCount);
    	CommonMethod.negativesoftassertFieldValidEquals(actualAPBundlesCount, "5", "Bundles Count does not matched ");
	}
	
	public void ValidateSadaLearnPracticeExamBundle(String SheetName, int rowNum) throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APActualSadaExamFee", 0);
		String actualAPSadaExamFee = CommonMethod.getattributeValueByTextContent("APActualSadaExamFee");
		actualAPSadaExamFee = actualAPSadaExamFee.replaceAll("\\s+", " ").trim();
		String expectedAPSadaExamFee = data.getCellData("Exam", "APExamFee", 2);
		CommonMethod.negativesoftassertFieldValidEquals("$"+actualAPSadaExamFee, expectedAPSadaExamFee, "AP Sada Exam Fee does not matched ");		
		String actualBillingPageDescription = CommonMethod.getattributeValueByTextContent("APBillingPageDescription");
		actualBillingPageDescription = actualBillingPageDescription.replaceAll("\\s+", " ").trim();
		CommonMethod.negativesoftassertFieldValid(actualBillingPageDescription, "bundle fee", "Billing Page Description Text does not matched ");	
	}
	
	
	public void ValidateWellAPExamRegisteredData(String SheetName, int rowNum) throws IOException, InterruptedException {
		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APValidateSadaCoupon", 0);
		String FullSadaCouponText = CommonMethod.getattributeValueByTextContent("APValidateSadaCoupon");
		String[] getSadaCouponText=FullSadaCouponText.split(":");
		String actualSadaCoupon = getSadaCouponText[1].replaceAll("\\s+", " ").trim();
		System.out.println(actualSadaCoupon);
        if (actualSadaCoupon == null || actualSadaCoupon.isEmpty()) {
        	throw new IllegalArgumentException("String cannot be NULL or EMPTY");
        }		
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("APSadaLearnPracticeExamLink", 0);
		CommonMethod.click("APSadaLearnPracticeExamLink");
		testlog.info("When User Clicks on Download Document Icon");
		Thread.sleep(3000);	
		CommonMethod.switchToChildTab();
		Thread.sleep(3000);
		String childUrl = CommonMethod.getCurrentUrl();
		CommonMethod.negativesoftassertFieldValid(childUrl, "coupon_code", "URL does not matched");	
		CommonMethod.switchToParentTab();
	}
}