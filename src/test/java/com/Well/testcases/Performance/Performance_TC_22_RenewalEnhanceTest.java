package com.Well.testcases.Performance;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class Performance_TC_22_RenewalEnhanceTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_16_MarkAcheivedToReviewDocument" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_22_00_RenewalEnhanceChangeDateAsAdmin(String SheetName,int rowNum) throws IOException {
		
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Renewal Enhance project functionality");
		try {
			login.AdminLogin();
			performance.AdminWprSearch(SheetName, rowNum);
			performance.editAdminAwardDate("EditAchievedStatus","WprAchievementAdminTab");
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_00_RenewalEnhanceChangeDateAsAdmin" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity"})
	public void Performance_TC_22_01_ValidateScorecardLockScreenBeforeRenewal(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {
		
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Scorecard Lock Screen functionality Before Renewal");
		try {			
			rc.clickScorecard();
			performance.ValidateScorecardLockScreenBeforeRenewal("Meet Thresholds for Particulate Matter",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false,
					false, false, false);
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_01_ValidateScorecardLockScreenBeforeRenewal" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity"})
	public void Performance_TC_22_02_ValidateDocumentsLibraryLockScreenBeforeRenewal(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {
		
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Documents Library Lock Screen functionality Before Renewal");
		try {	
			rc.clickDocument();
			performance.ValidateDocumentsLibraryLockScreenBeforeRenewal();
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
//	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_02_ValidateDocumentsLibraryLockScreenBeforeRenewal" })
//	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity"})
//	public void Performance_TC_22_03_ValidateDashboardRenewalGuidanceComingSoon(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {
//		
//		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//		StartTest(TestCaseName,"Validate Documents Library Lock Screen functionality Before Renewal");
//		try {			
//			rc.clickDashboard();
//			performance.ValidateDashboardRenewalTextAndButtons("RenewalDashboardText", "Get Ready to renew your rating",
//			"RenewalDashboardSecondText", "Click the button below to confirm WELL features are still in place or upload updated documents.",
//			"ValidateDashboardRenewalButton", " Renew Now");
//	
//		} catch (Throwable t) {
//			System.out.println(t.getLocalizedMessage());
//			Error e1 = new Error(t.getMessage());
//			e1.setStackTrace(t.getStackTrace());
//			throw e1;
//		}
//	}
//	
//	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_03_ValidateDashboardRenewalGuidanceComingSoon" })
//	@Parameters({ "SheetName","rowNum","Renewal"})
//	public void Performance_TC_22_04_NavigateRenewalAsYes(String SheetName,int rowNum, String Renewal) throws IOException {
//
//		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//		StartTest(TestCaseName,"Navigate Renewal Enhance project functionality");
//		try {
//			rc.SignOut();
//			login.Login();
//			performance.SearchPerformanceByID(SheetName, rowNum);
//			rc.navigateRenewal();
//	
//		} catch (Throwable t) {
//			System.out.println(t.getLocalizedMessage());
//			Error e1 = new Error(t.getMessage());
//			e1.setStackTrace(t.getStackTrace());
//			throw e1;
//		}
//	}
//	
//	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_04_NavigateRenewalAsYes" })
//	@Parameters({ "SheetName","rowNum","Renewal"})
//	public void Performance_TC_22_05_ArchieveToLocations(String SheetName,int rowNum, String Renewal) throws IOException {
//
//		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//		StartTest(TestCaseName,"Archieve and Restore Locations functionality");
//		try {			
//			rc.ArchieveToLocations();
//				
//		} catch (Throwable t) {
//			System.out.println(t.getLocalizedMessage());
//			Error e1 = new Error(t.getMessage());
//			e1.setStackTrace(t.getStackTrace());
//			throw e1;
//		}
//	}
//	
//	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_05_ArchieveToLocations" })
//	@Parameters({ "SheetName","rowNum","Renewal"})
//	public void Performance_TC_22_06_ValidateDashboardConfirmLocations(String SheetName,int rowNum, String Renewal) throws IOException {
//
//		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//		StartTest(TestCaseName,"Validate Dashboard Confirm Locations Text and Button");
//		try {						
//			rc.clickDashboard();
//			performance.ValidateDashboardRenewalTextAndButtons("RenewalDashboardText", "Confirm locations to renew",
//			"RenewalDashboardSecondText", "Click the button below to confirm the location(s) you wish to renew the WELL Rating for.",
//			"ValidateDashboardRenewalButton", " Confirm Locations");
//	
//		} catch (Throwable t) {
//			System.out.println(t.getLocalizedMessage());
//			Error e1 = new Error(t.getMessage());
//			e1.setStackTrace(t.getStackTrace());
//			throw e1;
//		}
//	}
//	
//	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_06_ValidateDashboardConfirmLocations" })
//	@Parameters({ "SheetName","rowNum","ProjectType","Commodity","Renewal"})
//	public void Performance_TC_22_07_ArchieveAddedAndImportedLocations(String SheetName,int rowNum, String ProjectType, String Commodity, String Renewal) throws IOException {
//
//		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//		StartTest(TestCaseName,"Add/Import location and Validate locations functionality");
//		try {				
//				rc.locationNavigate();
//				CommonMethod.refreshBrowser();
//				rc.addLocation(SheetName, rowNum, ProjectType);
//				performance.RenewalTeamValid();
//				generic.importLocationGeneric(Commodity, ImportfilePortfolioLocationRenewal);
//				rc.ValidateAddedLocations();
//				
//		} catch (Throwable t) {
//			System.out.println(t.getLocalizedMessage());
//			Error e1 = new Error(t.getMessage());
//			e1.setStackTrace(t.getStackTrace());
//			throw e1;
//		}
//	}
//	
//	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_07_ArchieveAddedAndImportedLocations" })
//	@Parameters({ "SheetName","rowNum","ProjectType","Renewal"})
//	public void Performance_TC_22_08_ConfirmLocations(String SheetName,int rowNum, String ProjectType, String Renewal) throws IOException {
//
//		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//		StartTest(TestCaseName,"Validate Renewal Confirm Locations functionality");
//		try {			
//				rc.ValidateConfirmLocations(SheetName,rowNum);
//				rc.ValidateRenewalPayment();
//				
//		} catch (Throwable t) {
//			System.out.println(t.getLocalizedMessage());
//			Error e1 = new Error(t.getMessage());
//			e1.setStackTrace(t.getStackTrace());
//			throw e1;
//		}
//	}
//	
//	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_08_ConfirmLocations" })
//	@Parameters({ "SheetName","rowNum"})
//	public void Performance_TC_22_09_RenewalReviewValidateBeforePayment(String SheetName,int rowNum) throws IOException {
//
//		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//		StartTest(TestCaseName,"Validate Renewal Before Payment functionality");
//		try {			
//			rc.ValidateRenewalMessageInReviewBeforePayment();
//			
//		} catch (Throwable t) {
//			System.out.println(t.getLocalizedMessage());
//			Error e1 = new Error(t.getMessage());
//			e1.setStackTrace(t.getStackTrace());
//			throw e1;
//		}
//	}
//	
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_09_RenewalReviewValidateBeforePayment" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_10_RenewalBilling(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Complete the Payment functionality");
//			try {
//				v2project.ClickBilling();
//				rc.Billing(SheetName, rowNum);
//				
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_10_RenewalBilling" })
//		@Parameters({ "SheetName","rowNum","Renewal"})
//		public void Performance_TC_22_11_RenewalReviewValidateAfterpayment(String SheetName,int rowNum, String Renewal) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Validate After payment functionality");
//			try {	
//				rc.ValidateRenewalMessageInReviewAfterPayment();
//		
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_11_RenewalReviewValidateAfterpayment" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_12_ValidateDashboardAfterRenewal(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Validate Dashboard After Renewal");
//			try {				
//				rc.clickDashboard();
//				performance.ValidateDashboardRenewalTextAndButtons("RenewalDashboardText", "Complete Renewal Scorecard",
//				"RenewalDashboardSecondText", "Click the button below to access your renewal scorecard to confirm or upload documents.",
//				"ValidateDashboardRenewalButton", " Access your Renewal Scorecard");
//				performance.ValidateDashboardHeaderTextAndCards();
//		
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_12_ValidateDashboardAfterRenewal" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_13_ValidateDocumentsLibraryAfterRenewal(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Validate Documents Library After Renewal");
//			try {
//				rc.clickDocument();
//				rc.RemoveSpaceAndValidate("RenewalDocumentsLibAttentionNeededBanner", "Attention needed");			
//				performance.ValidateDocumentsLibraryLockScreenAfterRenewal();
//		
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_13_ValidateDocumentsLibraryAfterRenewal" })
//		@Parameters({ "SheetName","rowNum","ProjectType","Commodity"})
//		public void Performance_TC_22_14_ValidateScorecardAfterRenewal(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Validate Scorecard After Renewal");
//			try {
//				rc.clickScorecard();
//				rc.RemoveSpaceAndValidate("RenewalDocumentsLibAttentionNeededBanner", "Attention needed");
//				performance.ValidateScorecardAfterRenewal("Meet Thresholds for Particulate Matter",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false,
//						false, false, false);
//		
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_14_ValidateScorecardAfterRenewal" })
//		@Parameters({ "SheetName","rowNum","ProjectType","Commodity"})
//		public void Performance_TC_22_15_ValidateLocationsTabAfterRenewal(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Validate Locations Tab After Renewal");
//			try {
//				rc.locationNavigate();
//				performance.ValidateLocationsTabAfterRenewal();
//		
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_15_ValidateLocationsTabAfterRenewal" })
//		@Parameters({ "SheetName","rowNum","ProjectType","Commodity"})
//		public void Performance_TC_22_16_ValidateExpiringSoonStatusInScorecardAndDocumentLib(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Validate Expiring Soon Status In Scorecard And DocumentLib");
//			try {
//			    rc.clickScorecard();
//				performance.ValidateExpiringSoonStatusInScorecardAndDocumentLib("Meet Thresholds for Particulate Matter",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false,
//						false, false, false, "Expiring Soon");
//				performance.ValidateReviewTabWarningMsgAndClickNoInScorecard("WPRReviewExpiringSoonWarningMsg", "Please ensure no ‘Expiring Soon’ or ‘Expired’ documents are in the account. Renew, replace, or archive those documents before resubmitting the review", "No");
//		
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_16_ValidateExpiringSoonStatusInScorecardAndDocumentLib" })
//		@Parameters({ "SheetName","rowNum","ProjectType","Commodity"})
//		public void Performance_TC_22_17_MarkWPRAsAchievedAndValidatePostAchievementDashboard(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Validate Expiring Soon Status In Scorecard And DocumentLib");
//			try {
//				login.AdminLogin();
//				performance.AdminWprSearch(SheetName, rowNum);
//				performance.MarkWPRAsAchievedAndValidatePostAchievementDashboard();
//		
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_17_MarkWPRAsAchievedAndValidatePostAchievementDashboard" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_18_DisableExpiringRenewButtonOngoingInDocumentLibrary(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//			StartTest(TestCaseName,"Verifies the Expiring Ongoing Disabled New button and Toaster message functionality");
//			try {
//				rc.SignOut();
//				login.Login();
//				performance.SearchPerformanceByID(SheetName,rowNum);
//				rc.clickDocument();
//				rc.NavigateToDocumentTab();
//				performance.VerificationFilter("ExpiringOngoingFilterOption");
//			    performance.ExpiringOngoingDisabledToaster();
//				performance.ExpiringOngoingDisabledToasterClearFilter();
//		
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_18_DisableExpiringRenewButtonOngoingInDocumentLibrary" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_19_DisableExpiringRenewButtonPerformanceTestInDocumentLibrary(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Verifies the Expiring PerformanceTest Disabled New button and Toaster message functionality");
//			try {
//				rc.NavigateToDocumentTab();
//				performance.VerificationFilter("ExpiringPerformanceTestFilterOption");
//				performance.ExpiringPerformanceTestDisabledToaster();
//				performance.ExpiringOngoingDisabledToasterClearFilter();
//				
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}	
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_19_DisableExpiringRenewButtonPerformanceTestInDocumentLibrary" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_20_ValidRenewExpireInDocumentLibrary(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//			StartTest(TestCaseName,"Validate the Renew page functionality");
//			try {
//				performance.ValidRenewExpireInDocumentLibrary();
//
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_20_ValidRenewExpireInDocumentLibrary" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_21_AttentionBannerInDocumentLibrary(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//			StartTest(TestCaseName,"AttentionBanner In DocumentLibrary functionality");
//			try {
//				performance.ValidateExpireRemoveMessage();
//				
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_21_AttentionBannerInDocumentLibrary" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_22_ValidRenewExpireReplaceLinkInScorecard(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//			StartTest(TestCaseName,"Submit Renewal Preliminary Health-Safety Review functionality");
//			try {
//		    	rc.clickScorecard();
//				performance.ValidRenewExpireInScorecard("Manage Relative Humidity");
//				
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_22_ValidRenewExpireReplaceLinkInScorecard" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_23_AttentionBannerInScorecard(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//			StartTest(TestCaseName,"AttentionBanner In DocumentLibrary functionality");
//			try {
//				performance.ValidateExpireRemoveMessage();
//				
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}  
//		}
//	
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_23_AttentionBannerInScorecard" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_24_DisableExpiringRenewButtonPerformanceTestInScorecard(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Disable Expiring RenewButton PerformanceTest functionality");
//			try {
//				performance.ValidRenewExpireInScorecard("Lighting for Day-Active People","Performance");
//				
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_24_DisableExpiringRenewButtonPerformanceTestInScorecard" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_25_DisableExpiringRenewButtonOngoingInScorecard(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//			StartTest(TestCaseName,"Disable Expiring RenewButton Ongoing functionality");
//			try {
//				performance.ValidRenewExpireInScorecard("Measure Air Parameters","Ongoing");
//				
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//
//		@SuppressWarnings("static-access")
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_25_DisableExpiringRenewButtonOngoingInScorecard" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_26_RemoveRenewInScorecard(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//			StartTest(TestCaseName,"Remove Renew warning In Scorecard functionality");
//			try {
//				performance.ScorecardfillHSRWPRPurseNo(1, 38, 38, "CommonScorecardPurseNo");
//		        commonAPI.storeRatingScorecardIdAPI(SheetName,rowNum,"wpr_scorecard_id");
//	         	commonAPI.fillScorecardAPI(SheetName,rowNum, "ScorecardId");
//				
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//		
//		
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_26_RemoveRenewInScorecard" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_27_RenewalSubmitReview(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Submit Renewal Preliminary Health-Safety Review functionality");
//			try {
//				hsr.SubmitHsrReview(SheetName, rowNum, "Renewal Preliminary Performance Rating Review");	
//				
//			} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
//	
//		@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_22_RenewalEnhanceTest.Performance_TC_22_27_RenewalSubmitReview" })
//		@Parameters({ "SheetName","rowNum"})
//		public void Performance_TC_22_28_RenewalCompleteReview(String SheetName,int rowNum) throws IOException {
//
//			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//			StartTest(TestCaseName,"Complete the Renewal Preliminary Health-Safety Review functionality");
//			try {
//				login.AdminLogin();
//			    performance.CompleteWPRReview(SheetName, rowNum, "Renewal Preliminary Performance Rating Review");	
//				
//				} catch (Throwable t) {
//				System.out.println(t.getLocalizedMessage());
//				Error e1 = new Error(t.getMessage());
//				e1.setStackTrace(t.getStackTrace());
//				throw e1;
//			}
//		}
}