package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;
import com.Well.ReusableMethods.ReusableMethodCommonAPI;

public class V2_TC_11B_WEROptSeperateReviewcycleTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11A_HealthSafetyReviewV2ProjectTest.V2_TC_11A_04_HealthSafetyCompleteReviewV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_00_EquitySeparateReviewCycleV2ProjectOpt(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Separate Review Cycle V2Project Opt Functionality");
		try {
			
			rc.SignOut();
			login.Login();
			v2project.SearchV2ProjectById(SheetName, rowNum);
			v2project.V2ProjectOpt(SheetName, rowNum, "EquityTab");
			v2project.SeparateReviewCycleV2ProjectOpt(SheetName, rowNum);	
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_00_EquitySeparateReviewCycleV2ProjectOpt" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_01_EquityFillScorecardByAPIV2Project(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Fill Scorecard by API");
		try {
			ReusableMethodCommonAPI.getWerScorecardIdByProjectNumAPI(SheetName,rowNum);
			commonAPI.fillScorecardAPIWithPoints(SheetName,rowNum, "ScorecardWerOptn","21");
			v2project.uploadDocumentInV2ProjectOptn("Provide Personal Cooling Options","V2ProjectScorecardVerificationMethodLOA",LOAfileUpload);
			v2project.uploadDocumentInV2ProjectOptn("Conduct Baseline Surveys","V2ProjectScorecardVerificationMethodOngoing",OngoingfileUpload);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_01_EquityFillScorecardByAPIV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_02_EquitySubmitReviewV2ProjectSeperateReview(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Equity Submit Review V2Project");
		try {
			v2project.werSubmitReviewV2Project(SheetName, rowNum);	
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_02_EquitySubmitReviewV2ProjectSeperateReview" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_03_EquityCompleteReviewV2Project(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete Review by Admin");
		try {
			login.AdminLogin();	
		    v2project.werCompleteReviewV2Project(SheetName, rowNum);
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_03_EquityCompleteReviewV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_04_EquityAchievingReviewDocument(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Equity Achieving Review Document V2Project");
		try {
			commonAPI.getProjectIdByProjectNumAPI(SheetName, rowNum);
			String ID = data.getCellData(SheetName, "ID", rowNum);
			pfu.ValidatingAcheivedV2ProjectReviewDocument(SheetName, rowNum, "wer",ID);
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_04_EquityAchievingReviewDocument" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_05_ValidateDocsStatusInEquityRatingTabOfDocumentsTab(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Docs Status In Equity Rating Tab Of DocumentsTab");
		try {
			rc.SignOut();
			login.Login();
			v2project.SearchV2ProjectById(SheetName, rowNum);
			rc.clickDocument();
	        v2project.ClickEquityRatingTabInDocumentsTab();
			v2project.ValidateDocsStatusInEquityRatingTabOfDocumentsTab("WERTableDocumentsListStatus","Reviewed");
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_05_ValidateDocsStatusInEquityRatingTabOfDocumentsTab" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_06_EquityChangeDateAsAdminAndValidateDashboard(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Equity Change Date As Admin And Validate Dashboard");
		try {
			login.AdminLogin();
			v2project.AdminSearchById(SheetName, rowNum);
			performance.editAdminAwardDate("WerAchievedStatus","WerAchievementAdminTab");
			performance.ValidateDashboard();
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_06_EquityChangeDateAsAdminAndValidateDashboard" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_07_EquityValidateDocumentsTabBeforeRenewal(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Equity Validate DocumentsTab Before Renewal");
		try {
			rc.SignOut();
			login.Login();
			v2project.SearchV2ProjectById(SheetName, rowNum);
			rc.clickDocument();
			v2project.ClickEquityRatingTabInDocumentsTab();
			v2project.EquityValidateDocumentsTabBeforeRenewal();
			v2project.ValidateDocsStatusInEquityRatingTabOfDocumentsTab("WERTableDocumentsListStatus","Expiring Soon");
			v2project.EquityValidateDocumentsListDataBeforeRenewal();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_07_EquityValidateDocumentsTabBeforeRenewal" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_08_ValidateEquityTabBeforeRenewal(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Equity Validate DocumentsTab Before Renewal");
		try {
			v2project.ValidateEquityTabBeforeRenewal();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_08_ValidateEquityTabBeforeRenewal" })
	@Parameters({ "SheetName","rowNum","ProjectType","Commodity" })
	public void V2_TC_11B_09_ValidateEquityScorecardTabBeforeRenewal(String SheetName,int rowNum,String ProjectType,String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Equity Scorecard Tab Before Renewal");
		try {
			v2project.ValidateEquityScorecardTabBeforeRenewal();			
			v2project.ValidateScorecardLockScreenBeforeRenewal("Create DEI Assessment and Action Plan",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false,
					false, false, false);	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_09_ValidateEquityScorecardTabBeforeRenewal" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_10_Renewal(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Renewal");
		try {
			rc.clickDashboard();
			v2project.Renewal();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_10_Renewal" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_11_RenewalBilling(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete the payment for Renewal");
		try {
			v2project.ClickBilling();
			rc.Billing(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_11_RenewalBilling" })
	@Parameters({ "SheetName","rowNum","ProjectType","Commodity" })
	public void V2_TC_11B_12_ValidatePostRenewalWellEquityTab(String SheetName,int rowNum,String ProjectType,String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete the payment for Renewal");
		try {
			v2project.ClickWellEquityTabAndAccessScorecard();
			v2project.EquityValidateScorecardLockScreenPostRenewal("Create DEI Assessment and Action Plan",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false,
					false, false, false);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_12_ValidatePostRenewalWellEquityTab" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_13_ValidateEquityRatingListInDocumentsTabPostRenewal(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete the payment for Renewal");
		try {
			rc.clickDocument();
			v2project.ClickEquityRatingTabInDocumentsTab();
			v2project.ValidateEquityRatingListInDocumentsTabPostRenewal();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_13_ValidateEquityRatingListInDocumentsTabPostRenewal" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_14_DisableExpiringRenewButtonOngoingInDocumentLibrary(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete the payment for Renewal");
		try {
			v2project.V2ProjectDecumentSearchBox("OngoingFile");
			  performance.ExpiringOngoingDisabledToaster();
			   CommonMethod.refreshBrowser();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_14_DisableExpiringRenewButtonOngoingInDocumentLibrary" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_15_ValidRenewExpireLOAInDocumentLibrary(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete the payment for Renewal");
		try {
			v2project.V2ProjectDecumentSearchBox("LOAFile");
			v2project.ValidRenewExpireInDocumentLibrary();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_15_ValidRenewExpireLOAInDocumentLibrary" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_16_WerAttentionBannerInDocumentLibrary(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete the payment for Renewal");
		try {
			v2project.ExpiringAttentionLink();	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_16_WerAttentionBannerInDocumentLibrary" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_17_DisableExpiringRenewButtonOngoingInScorecard(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Disable Expiring RenewButton Ongoing functionality");
		try {
			v2project.ClickWellEquityTabAndAccessScorecard();
			v2project.validateRenewInScorecard("Conduct Baseline Surveys","Ongoing");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_17_DisableExpiringRenewButtonOngoingInScorecard" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_18_ValidRenewExpireLOAInScorecard(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Renew Document to Resubmission for Renewal");
		try {
			v2project.validateRenewInScorecard("Prioritize Audio Devices and Policies","LOA");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_18_ValidRenewExpireLOAInScorecard" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_19_AttentionBannerInScorecardV2(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Attention Banner In Scorecard for Renewal");
		try {
			v2project.ExpiringAttentionLink();	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_19_AttentionBannerInScorecardV2" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_20_RemoveRenewbtnByArchieve(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Remove Renew In Scorecard for Renewal");
		try {
			rc.clickDocument();
			v2project.ClickEquityRatingTabInDocumentsTab();
			v2project.removeRenewbtnByArchieve();
      	v2project.ClickWellEquityTabAndAccessScorecard();
			commonAPI.fillScorecardAPIWithPoints(SheetName,rowNum, "ScorecardWerOptn","21");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_20_RemoveRenewbtnByArchieve" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_21_SubmitReview(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"SubmitReview for Renewal");
		try {
			v2project.SubmitReviewV2Project(SheetName, rowNum,"Renewal Preliminary Equity Rating Review","V2ProjectRenewalWerReviewviewButton");	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11B_WEROptSeperateReviewcycleTest.V2_TC_11B_21_SubmitReview" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11B_22_CompleteReview(String SheetName,int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete the Review for Renewal");
		try {
			login.AdminLogin();
			v2project.AdminCompleteReviewV2Project(SheetName, rowNum, "Renewal Preliminary Equity Rating Review","V2ProjectRenewalWerReviewviewButton");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
}
