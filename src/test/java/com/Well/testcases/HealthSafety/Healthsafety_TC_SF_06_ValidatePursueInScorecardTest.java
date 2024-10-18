package com.Well.testcases.HealthSafety;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;


public class Healthsafety_TC_SF_06_ValidatePursueInScorecardTest extends BaseClass {

	@Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_05_SearchV2ProjectByRegisteredStatusTest.Healthsafety_TC_SF_05_SearchV2ProjectByRegisteredStatus"})
	@Parameters({ "Commodity" })
	public void Healthsafety_TC_SF_06_00_Location(String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Importing Locations to HSR Project");

		try {
			generic.importLocationGeneric(Commodity, RatingLocationImportfile);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	    @Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_00_Location"})
	    @Parameters({ "SheetName", "rowNum"})
	    public void Healthsafety_TC_SF_06_01_purseYesValidFromScorecard(String SheetName, int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Validate purse Yes functionality");
		try {
			rc.clickScorecard();
			hsr.purseYesValidFromScorecard();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
		@Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_01_purseYesValidFromScorecard"})
        public void Healthsafety_TC_SF_06_02_purseNoValidFromScorecard() throws IOException {
	    TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	    StartTest(TestCaseName, "Validate purse No functionality");
	    try {
		hsr.purseNoValidFromScorecard();
	    } catch (Throwable t) {
		System.out.println(t.getLocalizedMessage());
		Error e1 = new Error(t.getMessage());
		e1.setStackTrace(t.getStackTrace());
		throw e1;
	    }
	}	

		@Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_02_purseNoValidFromScorecard"})
        public void Healthsafety_TC_SF_06_03_purseMaybeValidFromScorecard() throws IOException {
	    TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	    StartTest(TestCaseName, "Validate purse Maybe functionality");
	    try {
		hsr.purseMaybeValidFromScorecard();
	    } catch (Throwable t) {
		System.out.println(t.getLocalizedMessage());
		Error e1 = new Error(t.getMessage());
		e1.setStackTrace(t.getStackTrace());
		throw e1;
    	}
     }

        @Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_03_purseMaybeValidFromScorecard"})
        @Parameters({ "SheetName", "rowNum","ProjectType","Commodity" })
        public void Healthsafety_TC_SF_06_04_purseYesToNoValidFromScorecard(String SheetName, int rowNum,String ProjectType, String Commodity) throws IOException {
	    TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	    StartTest(TestCaseName, "Validate purse Yes to No functionality");
	    try {	    	
		hsr.purseYesToNoValidFromScorecard();
		
	    } catch (Throwable t) {
		System.out.println(t.getLocalizedMessage());
		Error e1 = new Error(t.getMessage());
		e1.setStackTrace(t.getStackTrace());
		throw e1;
	    }
    }

        @Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_04_purseYesToNoValidFromScorecard"})
        @Parameters({ "SheetName", "rowNum" })
        public void Healthsafety_TC_SF_06_05_purseMaybeToNoValidFromScorecard(String SheetName, int rowNum) throws IOException {
	    TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	    StartTest(TestCaseName, "Validate purse Maybe to No functionality");
	    try {
		hsr.purseMaybeToNoValidFromScorecard();
	    } catch (Throwable t) {
		System.out.println(t.getLocalizedMessage());
		Error e1 = new Error(t.getMessage());
		e1.setStackTrace(t.getStackTrace());
		throw e1;
	    }
    }
        
        @Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_05_purseMaybeToNoValidFromScorecard"})
        @Parameters({ "SheetName", "rowNum" })
        public void Healthsafety_TC_SF_06_06_purseYesToMaybeValidFromScorecard(String SheetName, int rowNum) throws IOException {
	    TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	    StartTest(TestCaseName, "Validate purse Maybe to No functionality");
	    try { 
		hsr.purseYesToMaybeValidFromScorecard();
		
	    } catch (Throwable t) {
		System.out.println(t.getLocalizedMessage());
		Error e1 = new Error(t.getMessage());
		e1.setStackTrace(t.getStackTrace());
		throw e1;
	    }
    }
        
    	@Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_06_purseYesToMaybeValidFromScorecard" })
    	@Parameters({ "SheetName", "rowNum","Commodity" })
    	public void Healthsafety_TC_SF_06_07_ValidateScorecardUploadButtonCountAfterClosingTheAssignLocationModal(String SheetName, int rowNum, String Commodity) throws IOException {

    		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		StartTest(TestCaseName, "HSR Validate Scorecard Save And Exit Flow");
    		try {   			
    			hsr.ScorecardSaveAndExitFlow("Develop Emergency Preparedness Plan", SheetName, rowNum, Commodity);
    			
    		} catch (Throwable t) {
    			System.out.println(t.getLocalizedMessage());
    			Error e1 = new Error(t.getMessage());
    			e1.setStackTrace(t.getStackTrace());
    			throw e1;
    		}
    	}
    	
    	@Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_07_ValidateScorecardUploadButtonCountAfterClosingTheAssignLocationModal" })
    	@Parameters({ "SheetName", "rowNum","Commodity" })
    	public void Healthsafety_TC_SF_06_08_ValidateScorecardConfirmationModalFromPurseYesToNoAfterAddingOptions(String SheetName, int rowNum, String Commodity) throws IOException {

    		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		StartTest(TestCaseName, "HSR Validate Scorecard Confirmation Modal from Purse Yes to No After Adding Options");
    		try {
    			hsr.ValidateScorecardConfirmationModalFromPurseYesToNoAfterAddingOptions("Develop Emergency Preparedness Plan", SheetName, rowNum, Commodity);
    			
    		} catch (Throwable t) {
    			System.out.println(t.getLocalizedMessage());
    			Error e1 = new Error(t.getMessage());
    			e1.setStackTrace(t.getStackTrace());
    			throw e1;
    		}
    	}
    	
    	@Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_08_ValidateScorecardConfirmationModalFromPurseYesToNoAfterAddingOptions" })
    	@Parameters({ "SheetName", "rowNum","ProjectType","Commodity" })
    	public void Healthsafety_TC_SF_06_09_ValidateTaskCompletionOnAddingOptionFromDocEdit(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

    		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		StartTest(TestCaseName, "Validate Task Completion On Adding Option From Doc Edit");
    		try {  
    			hsr.ValidateTaskCompletionOnAddingOptionFromDocEdit(SheetName,rowNum,"WELL Health-Safety Rating",ProjectType, Commodity,FeaturefileUpload ,false,false,false,false,"Monitor Air and Water Quality");
    			
    		} catch (Throwable t) {
    			System.out.println(t.getLocalizedMessage());
    			Error e1 = new Error(t.getMessage());
    			e1.setStackTrace(t.getStackTrace());
    			throw e1;
    		}
    	}  	
    	
		@Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_09_ValidateTaskCompletionOnAddingOptionFromDocEdit" })
    	@Parameters({"SheetName","rowNum","DataValidate","Country","ProjectType","Upload","API","Commodity","ImportLocation","ProjectName"})
    	public void Healthsafety_TC_SF_06_10_FetchDocumentIdFromNonEnhancedProjectAndAddInEnhancedProject(String SheetName,int rowNum, String DataValidate, String Country, String ProjectType,String Upload,String API,String Commodity, String ImportLocation, String ProjectName) throws IOException {

    		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		StartTest(TestCaseName, "Fetch DocumentId From NonEnhanced Project And Add In EnhancedProject");
    		try {
    			login.AdminLogin();
    			hsr.AdminHsrSearch(SheetName, rowNum);
    			rc.clickScorecard();
    			hsr.FetchDocumentIdFromNonEnhancedProjectAndAddInEnhancedProject(SheetName,rowNum,"WELL Health-Safety Rating",ProjectType, Commodity, false, false, false, false,
    					"Monitor Air and Water Quality");
    			
    		} catch (Throwable t) {
    			System.out.println(t.getLocalizedMessage());
    			Error e1 = new Error(t.getMessage());
    			e1.setStackTrace(t.getStackTrace());
    			throw e1;
    		}
    	}
    	
    	@Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_10_FetchDocumentIdFromNonEnhancedProjectAndAddInEnhancedProject" })
    	@Parameters({ "SheetName", "rowNum","ProjectType","Commodity" })
    	public void Healthsafety_TC_SF_06_11_ForAlternativeDocFetchDocumentIdFromNonEnhancedProjectAndAddInEnhancedProject(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

    		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		StartTest(TestCaseName, "For Alternative Doc Fetch DocumentId From NonEnhanced Project And Add In EnhancedProject");
    		try {    			
    			hsr.ForAlternativeDocFetchDocumentIdFromNonEnhancedProjectAndAddInEnhancedProject(SheetName,rowNum,"WELL Health-Safety Rating",ProjectType, Commodity,AlternativeFileUpload ,false,false,false,false,"Manage Mold and Moisture");
    			
    		} catch (Throwable t) {
    			System.out.println(t.getLocalizedMessage());
    			Error e1 = new Error(t.getMessage());
    			e1.setStackTrace(t.getStackTrace());
    			throw e1;
    		}
    	}
    	
    	@Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_11_ForAlternativeDocFetchDocumentIdFromNonEnhancedProjectAndAddInEnhancedProject" })
    	@Parameters({"Commodity" })
    	public void Healthsafety_TC_SF_06_12_NotApplicable(String Commodity) throws IOException {

    		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		StartTest(TestCaseName, "For Alternative Doc Fetch DocumentId From NonEnhanced Project And Add In EnhancedProject");
    		try { 
    			rc.NAValidateInScorecard("Promote a Smoke-Free Environment","RatingFeatureName",Commodity,"SH5.2");
    		
    		} catch (Throwable t) {
    			System.out.println(t.getLocalizedMessage());
    			Error e1 = new Error(t.getMessage());
    			e1.setStackTrace(t.getStackTrace());
    			throw e1;
    		}
    	}
    	
    	@Test(dependsOnMethods = {"com.Well.testcases.HealthSafety.Healthsafety_TC_SF_06_ValidatePursueInScorecard.Healthsafety_TC_SF_06_12_NotApplicable" })
    	@Parameters({"Commodity" })
    	public void Healthsafety_TC_SF_06_13_WarningMessageInReview(String Commodity) throws IOException {

    		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		StartTest(TestCaseName, "For Alternative Doc Fetch DocumentId From NonEnhanced Project And Add In EnhancedProject");
    		try { 
    			rc.ScorecardAssignLoc("Plan for Healthy Re-Entry",Commodity);
    			rc.NavReview();
    			rc.WarningMessageInReview("The minimum point requirement is not met, please ensure");
    			rc.WarningMessageInReview("Please ensure all features are marked as either a YES");
    			rc.WarningMessageInReview("Please ensure you have fulfilled all the pending tasks");
    			rc.WarningMessageInReview("Please ensure you have all parts, the option, and the location");
    			rc.WarningMessageInReview("assigned to SH5 and fulfill the tasks accordingly.");
    		} catch (Throwable t) {
    			System.out.println(t.getLocalizedMessage());
    			Error e1 = new Error(t.getMessage());
    			e1.setStackTrace(t.getStackTrace());
    			throw e1;
    		}
    	}
}
