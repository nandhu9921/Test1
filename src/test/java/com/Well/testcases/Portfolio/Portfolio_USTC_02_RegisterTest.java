package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;



public class Portfolio_USTC_02_RegisterTest extends BaseClass {
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = {  "com.Well.testcases.login.TC_01_LoginTest.TC_01_Login"  })
	@Parameters({ "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName","Type" })
	public void Portfolio_USTC_02_00_RegisterV2Project(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName, String Type) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");

		try {
			Boolean ProType;
			if(Type.equalsIgnoreCase("WELLCore")) {
				ProType =true;
			}
			else {
				ProType =false;
			}
			SheetName = "V2Project";
			String Username = data.getCellData("Login", "UserName", 6);
			String Password = data.getCellData("Login", "Password", 6);
			commonAPI.StoreAnyProject(SheetName, rowNum, Country, ProjectType, Upload, API, "SingleAsset",ImportLocation, "AutomationV2UpsellV2project", ProType);
			commonAPI.SignAgreementProject(SheetName, rowNum,  "SingleAsset");
			commonAPI.RegisterProject(SheetName, rowNum, Country, ProjectType, "SingleAsset");
			commonAPI.BillingProject(SheetName, rowNum, Country, ProjectType, "SingleAsset");
			rc.SignOut();
			login.commonLogin(Username,Password,6);
			v2project.SearchV2ProjectById(SheetName, rowNum);	
			commonAPI.DatePickerAPI(SheetName, rowNum);
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}	

	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_02_RegisterTest.Portfolio_USTC_02_00_RegisterV2Project"  })
	@Parameters({ "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation" })
	public void Portfolio_USTC_02_01_UploadDocumentInV2ProjectScorecard(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"WELL V2Project Build and Upload Scorecard document Functionality");
		try {
			SheetName = "V2Project";
			v2project.CorePointDocumentUploadInDocumentLibraryV2Project(SheetName, rowNum);
			rc.ScorecardNavigation();
			v2project.VerifyCorePointInScorecard("Balance Visual Lighting","V2ProjectL07.1FeatureSelectedPurseYes","V2ProjectL07.1FeatureEnabledWeightCirclePoint");
			commonAPI.storeScorecardIdAPI(SheetName,rowNum, "ScorecardId","scorecard_id");
			commonAPI.fillScorecardAPIWithPoints(SheetName,rowNum, "ScorecardId","100");			
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_02_RegisterTest.Portfolio_USTC_02_01_UploadDocumentInV2ProjectScorecard"  })
	@Parameters({ "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation" })
	public void Portfolio_USTC_02_02_VerifyCorePointOnlyTargetingAdditionalFeaturePoint(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verify Core Point Only Targeting Additional Feature Point");
		try {
			v2project.VerifyCorePointOnlyTargetingAdditionalFeaturePoint("Support Mindful Eating","V2ProjectSupportFeatureSelectedPurseYes","V2ProjectSupportFeatureEnabledWeightCirclePoint","V2ProjectSupportFeatureEnabledPlusIcon","V2ProjectSupportFeatureEnabledPlusOneCircle");
			v2project.VerifyCorePointOnlyTargetingAdditionalFeaturePoint("Install Indoor Air Monitors","V2ProjectSupportFeatureSelectedPurseYes","V2ProjectSupportFeatureEnabledWeightCirclePoint","V2ProjectSupportFeatureEnabledPlusIcon","V2ProjectSupportFeatureEnabledPlusOneCircle");
				
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_02_RegisterTest.Portfolio_USTC_02_02_VerifyCorePointOnlyTargetingAdditionalFeaturePoint"  })
	@Parameters({ "Type","SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation" })
	public void Portfolio_USTC_02_03_SubmitReviewV2Project(String Type, String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Submit the V2Project Review");
		try {
			v2project.SubmitReviewV2Project(SheetName, rowNum,"Preliminary Precertification Review","ReviewViewButton");	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_02_RegisterTest.Portfolio_USTC_02_03_SubmitReviewV2Project"  })
	@Parameters({ "Type","SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation" })
	public void Portfolio_USTC_02_04_AdminReviewAsAchieved(String Type, String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Mark Admin Review as Achieved");
		try {
			SheetName = "V2Project";
			login.AdminLogin();
			v2project.AdminSearchById(SheetName, rowNum);
			rc.clickScorecard();
			v2project.featureAdminScorecardReviewAndSelectAdminReview(SheetName, rowNum, "Support Mindful Eating" ,"Achieved");
			v2project.featureAdminScorecardReviewAndSelectAdminReview(SheetName, rowNum, "Install Indoor Air Monitors" ,"Achieved");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_02_RegisterTest.Portfolio_USTC_02_04_AdminReviewAsAchieved"  })
	@Parameters({ "Type","SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation" })
	public void Portfolio_USTC_02_05_AdminCompleteReviewV2Project(String Type, String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete V2Project Review by Admin User");
		try {
			SheetName = "V2Project";
			v2project.AdminSearchById(SheetName, rowNum);
			v2project.AdminCompleteReviewV2Project(SheetName, rowNum, "Preliminary Precertification Review","ReviewViewButton");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_02_RegisterTest.Portfolio_USTC_02_05_AdminCompleteReviewV2Project"  })
	@Parameters({ "Type","SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation" })
	public void Portfolio_USTC_02_06_CompleteHsrRatingOptnInscorecard(String Type, String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete V2Project Review by Admin User");
		try {
			SheetName = "V2Project";
			v2project.HealthSafetyV2ProjectRegisterOptn(SheetName, rowNum);
			commonAPI.storeScorecardIdAPI(SheetName,rowNum, "ScorecardHsrOptnId","hsr_scorecard_id");
			commonAPI.fillScorecardAPI(SheetName,rowNum, "ScorecardHsrOptnId");
			v2project.uploadDocumentInOptnFeature("Reduce Surface Contact", SheetName, rowNum, Commodity, SampleJpgfile,"SC2");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_02_RegisterTest.Portfolio_USTC_02_06_CompleteHsrRatingOptnInscorecard"  })
	@Parameters({ "Type","SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation" })
	public void Portfolio_USTC_02_07_SubmitAndCompleteRatingScorecardOptn(String Type, String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete V2Project Review by Admin User");
		try {
			SheetName = "V2Project";
			v2project.hsrSubmitReviewV2Project(SheetName, rowNum);
			v2project.HealthSafetyV2ProjectRegisterOptn(SheetName, rowNum);
			rc.ScorecardLoading();
			v2project.featureAdminScorecardReviewAndSelectAdminReview(SheetName, rowNum, "Support Handwashing" ,"Achieved");
			v2project.hsrCompleteReviewV2Project(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_02_RegisterTest.Portfolio_USTC_02_07_SubmitAndCompleteRatingScorecardOptn"  })
	@Parameters({ "Type","SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation" })
	public void Portfolio_USTC_02_08_AdminMarkProjectStatusCertified(String Type, String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Mark Project Status as Certified by Admin User");
		try {
			community.MarkCertifiedByAdminUser(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_02_RegisterTest.Portfolio_USTC_02_08_AdminMarkProjectStatusCertified"  })
	@Parameters({ "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName" })
	public void Portfolio_USTC_02_09_RegisterPortfolioAccount(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");

		try {
			commonAPI.StoreAnyProject(SheetName, rowNum, Country, ProjectType, Upload, API, "Portfolio",ImportLocation, ProjectName, false);
			commonAPI.SignAgreementProject(SheetName, rowNum, Commodity);
			commonAPI.RegisterProject(SheetName, rowNum, Country, ProjectType, "Portfolio");
			commonAPI.BillingProject(SheetName, rowNum, Country, ProjectType, "Portfolio");
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}