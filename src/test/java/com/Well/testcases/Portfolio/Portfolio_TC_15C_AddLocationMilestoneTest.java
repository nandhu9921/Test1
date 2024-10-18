package com.Well.testcases.Portfolio;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;


public class Portfolio_TC_15C_AddLocationMilestoneTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20C_ReplaceDocumentUploadOptnTest.PortfolioOptnRating_TC_20C_07_PostUploadDocumentLibraryReplace" })
	@Parameters({ "SheetName", "rowNum"})
	public void Portfolio_TC_15C_00_FutureReviewCycle_Milestone_Validation(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verify Future review cycle Milestone tab Successfully");
		try {
			
			portfolio.NavigateLocation();
			portfolio.VerifyMilestonetab(SheetName, rowNum,"TargetAchievementEditIconRow5","WELL at scale Test location 05");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_00_FutureReviewCycle_Milestone_Validation" })
	@Parameters({ "SheetName", "rowNum"})
	public void Portfolio_TC_15C_01_ReviewCycle_Milestone_Validation(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verify review cycle and Milestone tab Successfully");
		try {
			portfolio.VerifyMilestonetab(SheetName, rowNum,"TargetAchievementEditIconRow4","WELL at scale Test location 04");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
		
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_01_ReviewCycle_Milestone_Validation" })
	@Parameters({ "SheetName", "rowNum"})
	public void Portfolio_TC_15C_02_ReviewCycle_MilestoneFilter_Validation() throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verify Filter review cycle Milestone tab Successfully");
		try {
			portfolio.VerifyMilestoneLocationFilters("This review cycle", "1");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
}
	
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_02_ReviewCycle_MilestoneFilter_Validation" })
		@Parameters({ "SheetName", "rowNum"})
		public void Portfolio_TC_15C_03_TargetAllReviewCycle_MilestoneFilter_Validation() throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Filter Future review cycle Milestone tab Successfully");
			try {
				CommonMethod.refreshBrowser();
				portfolio.VerifyMilestoneLocationFilters("All targets","6");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
	}
	
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_03_TargetAllReviewCycle_MilestoneFilter_Validation" })
		@Parameters({ "SheetName", "rowNum"})
		public void Portfolio_TC_15C_04_TargetAllReviewCycle_Milestone_SummaryValidation(String SheetName, int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Summary Future review cycle Milestone tab Successfully");
			try {
				portfolio.VerifyMilestoneReviewCycle("All targets");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_04_TargetAllReviewCycle_Milestone_SummaryValidation" })
		@Parameters({ "SheetName", "rowNum"})
		public void Portfolio_TC_15C_05_ReviewCycle_Milestone_SummaryValidation(String SheetName, int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Summary Review cycle Milestone tab Successfully");
			try {
				portfolio.VerifyMilestoneReviewCycle("This review cycle");
				} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_05_ReviewCycle_Milestone_SummaryValidation" })
		@Parameters({ "SheetName", "rowNum"})
		public void Portfolio_TC_15C_08_ValidateMilestonelocationSearchFilter(String SheetName, int rowNum) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Search filter Milestone location File Successfully");
			try {	
			portfolio.ValidateMilestoneSearchLocation(SheetName, rowNum);
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_08_ValidateMilestonelocationSearchFilter" })
		@Parameters({ "SheetName", "rowNum"})
		public void Portfolio_TC_15C_09_UnderConstructionAsYes(String SheetName, int rowNum) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify checks Under Construction As Yes Successfully");
			try {
				login.AdminLogin();
				portfolio.AdminSearch(SheetName, rowNum);
				portfolio.UnderConstructionAsYes();
			
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_09_UnderConstructionAsYes" })
		public void Portfolio_TC_15C_10_ValidateUnderConstructionInLocationTable() throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"verifies TickMark In UnderContruction column Successfully");
			try {
				portfolio.validUnderConstructionInLocationTable();
			
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_10_ValidateUnderConstructionInLocationTable" })
		@Parameters({ "SheetName", "rowNum"})
		public void Portfolio_TC_15C_11_OverviewTabValidUnderConstructionfilterInLocationTable(String SheetName, int rowNum) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"verifies UnderContruction filter In OverviewTab Location Table Successfully");
			try {
				portfolio.overviewTab();
				portfolio.validUnderConstructionfilterInLocationTable("2");
			
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_11_OverviewTabValidUnderConstructionfilterInLocationTable" })
		public void Portfolio_TC_15C_12_MilestonetabTargetValidUnderConstructionfilterInLocationTable() throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"verifies UnderContruction filter In MilestonetabTarget Location Table Successfully");
			try {
         		portfolio.targetAndMilestoneTab();
				portfolio.validUnderConstructionfilterInLocationTable("2");
			
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_12_MilestonetabTargetValidUnderConstructionfilterInLocationTable" })
		public void Portfolio_TC_15C_13_ValidateUnderContructionInOverviewLocation() throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Disabled checkboxes for Target ReviewCycle Successfully");
			try {
				CommonMethod.refreshBrowser();
				portfolio.overviewtabTargetReviewCycle("Location Three");
				portfolio.UnderConstructionDisableTargetReviewCycle();
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
          }
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_13_ValidateUnderContructionInOverviewLocation" })
		@Parameters({ "SheetName", "rowNum"})
		public void Portfolio_TC_15C_14_ValidateEnableUnderConstructionTargetReviewCycle(String SheetName, int rowNum) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Disabled checkboxes for Target ReviewCycle Successfully");
			try {
				CommonMethod.refreshBrowser();
				portfolio.overviewtabTargetReviewCycle("Location One");
				portfolio.ValidateEnableUnderConstructionTargetReviewCycle("Locatione One");
				portfolio.overviewtabTargetReviewCycle("Location One");
				portfolio.UnderConstructionDisableTargetReviewCycle();
				portfolio.overviewtabTargetReviewCycle("Location Two");
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
          }
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_14_ValidateEnableUnderConstructionTargetReviewCycle" })
		@Parameters({ "SheetName", "rowNum"})
		public void Portfolio_TC_15C_15_ValidateUnderContructionInTargetMilestoneLocation(String SheetName, int rowNum) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Disabled checkboxes for Target ReviewCycle Successfully");
			try {
		
				CommonMethod.refreshBrowser();
				portfolio.MilestonetabTargetReviewCycle("Location One");
				CommonMethod.refreshBrowser();
				portfolio.overviewTab();
				portfolio.locationToTarget("TargetAchievementEditIconRow3");
				CommonMethod.refreshBrowser();
				portfolio.overviewTab();
				portfolio.locationToTarget("TargetAchievementEditIconRow2");
				CommonMethod.refreshBrowser();
				portfolio.MilestonetabTargetReviewCycle("Location Three");
				portfolio.UnderConstructionDisableTargetReviewCycle();
				portfolio.MilestonetabTargetReviewCycle("Location Two");
				portfolio.ValidateEnableUnderConstructionTargetReviewCycle("Location Two");
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_15_ValidateUnderContructionInTargetMilestoneLocation" })
		public void Portfolio_TC_15C_16_TargetAsGoldFilterInTargetMilestoneLocation() throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Target As Gold Filter Successfully");
			try {
				portfolio.TargetAsGoldFilter("4");
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_16_TargetAsGoldFilterInTargetMilestoneLocation" })
		public void Portfolio_TC_15C_17_OwnershipTypeFilterInTargetMilestoneLocation() throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify OwnershipType Filter In TargetMilestoneLocation Successfully");
			try {
				portfolio.targetAndMilestoneTab();
				portfolio.OwnershipType();
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_17_OwnershipTypeFilterInTargetMilestoneLocation" })
		public void Portfolio_TC_15C_18_TargetOccupancySizeFilterInTargetMilestoneLocation() throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Disabled checkboxes for Target ReviewCycle Successfully");
			try {
				portfolio.targetAndMilestoneTab();
				portfolio.OccupancyFilter("2");
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		

		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_18_TargetOccupancySizeFilterInTargetMilestoneLocation" })
		@Parameters({ "Commodity","SheetName","rowNum" })
		public void Portfolio_TC_15C_19_AdminReviewimportLogs(String Commodity, String SheetName, int rowNum) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Disabled checkboxes for Target ReviewCycle Successfully");
			try {
				performance.clickOnReviewTab();
				portfolio.clickOnReviewViewBtn();
				portfolio.AdminImportResult();				
				portfolio.ValidateRefreshButton();				
				portfolio.AdminReviewimportLogs(Commodity);
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_19_AdminReviewimportLogs" })
		@Parameters({ "Commodity" })
		public void Portfolio_TC_15C_20_AdminReviewimportHistory(String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Disabled checkboxes for Target ReviewCycle Successfully");
			try {
				portfolio.AdminReviewimportHistory(Commodity);
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_20_AdminReviewimportHistory" })
		@Parameters({ "Commodity","SheetName","rowNum" })
		public void Portfolio_TC_15C_21_AdminImportLocationPulishReview(String Commodity, String SheetName, int rowNum) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Admin Import Location Pulish Review Successfully");
			try {
				portfolio.clickOnReviewViewBtn();
				portfolio.AdminImportLocationPulishReview(Commodity);
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_21_AdminImportLocationPulishReview" })
		@Parameters({ "SheetName","rowNum" })
		public void Portfolio_TC_15C_22_ConstructionFilterInScorecard(String SheetName, int rowNum) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify Disabled checkboxes for Target ReviewCycle Successfully");
			try {
				
				rc.clickScorecard();
				pfu.ConstructionFilterInScorecard("Measure Air Parameters");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_22_ConstructionFilterInScorecard" })
		 @Parameters({ "SheetName", "rowNum","ProjectType","Commodity" })
		public void Portfolio_TC_15C_23_ValidateUnderContructionRemoveLocInScorecardAssignAndConfirmLocation(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate UnderContruction Remove Location In Scorecard Assign And Confirm Location Successfully");
			try {
				
				portfolio.validateUnderContructionRemoveLocInScorecardAssignAndConfirmLoc("Ensure Adequate Ventilation");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_23_ValidateUnderContructionRemoveLocInScorecardAssignAndConfirmLocation" })
	@Parameters({"SheetName", "rowNum", "Commodity","ProjectType" })
	public void Portfolio_TC_15C_24_ValidateDocumentsLibAssignLocationCountPostUnderConstruction(String SheetName, int rowNum, String Commodity, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Documents Lib Assign Location Count Post Under Construction");

		try {	
		portfolio.clickDocument(); 
		portfolio.ValidateDocumentsLibTasksAssignLocationCountPostUnderConstruction(SheetName, rowNum, Commodity, ProjectType,"2");
		portfolio.ValidateDocumentsLibAssignLocationCountPostUnderConstruction(SheetName, rowNum, Commodity, ProjectType, "A02.1", "2");
		rc.NavigateToDocumentTab();
		portfolio.ValidateDocumentsLibDocumentListAssignLocationCountPostUnderConstruction("2");
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
		}
	
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_24_ValidateDocumentsLibAssignLocationCountPostUnderConstruction" })
		 @Parameters({ "SheetName", "rowNum","ProjectType","Commodity" })
		public void Portfolio_TC_15C_25_ValidateUnderContructionRemoveLocInRatingScorecardAssignAndConfirmLocation(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate UnderContruction Remove Location In Scorecard Optn Rating Assign And Confirm Location Successfully");
			try {
				portfolio.HSROptnProfolioAccount();
				portfolio.validateUnderContructionRemoveLocInScorecardAssignAndConfirmLoc("Provide Sick Leave");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_25_ValidateUnderContructionRemoveLocInRatingScorecardAssignAndConfirmLocation" })
		 @Parameters({ "SheetName", "rowNum","ProjectType","Commodity" })
		public void Portfolio_TC_15C_26_ValidateSubsetFilterUnderContructionRemoveLocInScorecardAssignAndConfirmLocation(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate UnderContruction Remove Location In Scorecard Optn Rating Assign And Confirm Location Successfully");
			try {
				portfolio.SubsetUnderConstruction();
				rc.clickScorecard();
				portfolio.validateUnderContructionRemoveLocInScorecardAssignAndConfirmLoc("Meet Chemical Thresholds");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_26_ValidateSubsetFilterUnderContructionRemoveLocInScorecardAssignAndConfirmLocation" })
		 @Parameters({ "SheetName", "rowNum","ProjectType","Commodity" })
		public void Portfolio_TC_15C_27_ValidateSubsetFilterUnderContructionRemoveLocInRatingScorecardAssignAndConfirmLocation(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate UnderContruction Remove Location In Scorecard Optn Rating Assign And Confirm Location Successfully");
			try {
				portfolio.HSROptnProfolioAccount();
				portfolio.validateUnderContructionRemoveLocInScorecardAssignAndConfirmLoc("Provide Health Benefits");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
}
		}
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_27_ValidateSubsetFilterUnderContructionRemoveLocInRatingScorecardAssignAndConfirmLocation" })
	    @Parameters({ "SheetName", "rowNum","ProjectType","Commodity" })
		public void Portfolio_TC_15C_28_ValidateScorecardAssignLocationCountPostUnderConstruction(String SheetName, int rowNum,String ProjectType, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName, "Validate Scorecard Assign Location Count Post Under Construction");
			try {	
				rc.clickScorecard();
				portfolio.ValidateScorecardAssignLocationCountPostUnderConstruction("Meet Thresholds for Organic Gases", SheetName, rowNum, Commodity, false,false,false,false, "2");
				
		     } catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_28_ValidateScorecardAssignLocationCountPostUnderConstruction" })
		@Parameters({ "SheetName", "rowNum"})
		public void Portfolio_TC_15C_29_UnderConstructionAsNo(String SheetName, int rowNum) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verify checks Under Construction As No Successfully");
			try {
				login.AdminLogin();
				portfolio.AdminSearch(SheetName, rowNum);
				portfolio.UnderConstructionAsNo();
			
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
            }
		}
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_29_UnderConstructionAsNo" })
	    @Parameters({ "SheetName", "rowNum","ProjectType","Commodity" })
		public void Portfolio_TC_15C_30_ValidateScorecardAssignLocationCountPostUnderConstructionToComplete(String SheetName, int rowNum,String ProjectType, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName, "Validate Scorecard Assign Location Count Post Under Construction to Completed Location");
			try {
				rc.SignOut();
				login.Login();
				portfolio.SearchPortfolioById(SheetName, rowNum);
				rc.clickScorecard();
				portfolio.ValidateScorecardAssignLocationCountPostUnderConstruction("Meet Thresholds for Organic Gases", SheetName, rowNum, Commodity, false,false,false,false,
					"3");
				
		     } catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_30_ValidateScorecardAssignLocationCountPostUnderConstructionToComplete" })
		@Parameters({"SheetName", "rowNum", "Commodity","ProjectType" })
		public void Portfolio_TC_15C_31_ValidateDocumentsLibraryAssignLocationCountPostUnderConstructionToComplete(String SheetName, int rowNum, String Commodity, String ProjectType) throws IOException {
	
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	
			StartTest(TestCaseName,"Validate Documents Lib Assign Location Count Post Under Construction");
	
			try {	
			portfolio.clickDocument(); 
			portfolio.ValidateDocumentsLibAssignLocationCountPostUnderConstruction(SheetName, rowNum, Commodity, ProjectType,"A02.1", "3");
			
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
			}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_31_ValidateDocumentsLibraryAssignLocationCountPostUnderConstructionToComplete" })
		@Parameters({ "SheetName","rowNum", "Upload", "ProjectType","API","Commodity","ImportLocation" })
		public void Portfolio_TC_15C_32_ImportLocationAndValidateDuplicateLocationName(String SheetName,int rowNum, String Upload, String ProjectType, String API, String Commodity, String ImportLocation) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Importing Locations to WELL At Scale Project");

			try {
				rc.locationNavigate();
				portfolio.ImportLocationAndValidateDuplicateLocationName(Commodity, DuplicatePortfolioLocation);

			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_15C_AddLocationMilestoneTest.Portfolio_TC_15C_32_ImportLocationAndValidateDuplicateLocationName" })
		@Parameters({"SheetName", "rowNum", "Commodity","ProjectType" })
		public void Portfolio_TC_15C_33_ClickOnLocationNameLinkAndValidateDuplicateLocationName(String SheetName, int rowNum, String Commodity, String ProjectType) throws IOException {
	
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();	
			StartTest(TestCaseName,"Click On Location Name Link And Validate Duplicate Location Name");	
			try {
			login.AdminLogin();
			portfolio.AdminSearch(SheetName, rowNum);
			portfolio.ClickOnLocationNameLinkAndValidateDuplicateLocationName(SheetName, rowNum, "WELL at scale Test location 01");
			
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
			}
}