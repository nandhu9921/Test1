package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class Portfolio_CTC_07_DocumentUploadForAddedLocationTest extends BaseClass {
	 
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_06_ScorecardUploadForAddedLocationTest.Portfolio_CTC_06_12_ValidatePortfolioSharedDocInLocationAccScorecard" })
	 @Parameters({ "SheetName","rowNum", "Commodity" })
	 public void Portfolio_CTC_07_00_UploadLegalDocumentFromDocumentLibrary(String SheetName,int rowNum, String Commodity) throws IOException {
	 TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	 StartTest(TestCaseName,"Upload Legal Document in DocumentLibrary");
	 try {
	 pfu.UploadLegalFromDocumentLibrary(SheetName, rowNum,"Upload Legal",LegalfileUpload, Commodity); 
	 
	} catch (Throwable t) {
	 System.out.println(t.getLocalizedMessage());
	 Error e1 = new Error(t.getMessage());
	 e1.setStackTrace(t.getStackTrace());
	 throw e1;
	 }
	 }	 

	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_00_UploadLegalDocumentFromDocumentLibrary" })
	 @Parameters({ "SheetName","rowNum","ProjectType","Commodity" })
	 public void Portfolio_CTC_07_01_UploadFeatureDocumentFromDocumentLibrary(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {
	 TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	 StartTest(TestCaseName,"Upload Feature Document in DocumentLibrary");
	 try { 
	 pfu.UploadFeatureDocV2ProjectInsideDocument(SheetName, rowNum,"Feature",FeaturefileUpload,ProjectType, Commodity); 
	
	 } catch (Throwable t) {
	 System.out.println(t.getLocalizedMessage());
	 Error e1 = new Error(t.getMessage());
	 e1.setStackTrace(t.getStackTrace());
	 throw e1;
	 }
	 }

	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_01_UploadFeatureDocumentFromDocumentLibrary" })
	 @Parameters({ "SheetName","rowNum","Commodity" })
	 public void Portfolio_CTC_07_02_UploadAuditDocumentFromDocumentLibrary(String SheetName,int rowNum, String Commodity) throws IOException {
	 TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	 StartTest(TestCaseName,"Upload Audit Document in DocumentLibrary");
	 try {
		 pfu.UploadAuditDocV2ProjectInsideDocument(SheetName, rowNum,"Audit",AuditfileUpload, Commodity); 
	} catch (Throwable t) {
	 System.out.println(t.getLocalizedMessage());
	 Error e1 = new Error(t.getMessage());
	 e1.setStackTrace(t.getStackTrace());
	 throw e1;
	 }
	 }
	
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_02_UploadAuditDocumentFromDocumentLibrary" })
		 @Parameters({ "SheetName","rowNum" })
		 public void Portfolio_CTC_07_03_GeneralSearchFilterDocumentFromDocumentLibrary(String SheetName,int rowNum) throws IOException {
		 TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		 StartTest(TestCaseName,"Verify General SearchFilter Document in DocumentLibrary");
		 try {
			 pfu.GeneralSearchFilterDocumentFromDocumentLibrary(SheetName,rowNum);
		} catch (Throwable t) {
		 System.out.println(t.getLocalizedMessage());
		 Error e1 = new Error(t.getMessage());
		 e1.setStackTrace(t.getStackTrace());
		 throw e1;
		 }
	 }
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_03_GeneralSearchFilterDocumentFromDocumentLibrary" })
		 @Parameters({ "SheetName","rowNum" })
		 public void Portfolio_CTC_07_04_ScorecardSearchFilterDocumentFromDocumentLibrary(String SheetName,int rowNum) throws IOException {
		 TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		 StartTest(TestCaseName,"Verify Scorecard SearchFilter in DocumentLibrary");
		 try {
			 pfu.ScorecardSearchFilterDocumentFromDocumentLibrary(SheetName,rowNum);
		} catch (Throwable t) {
		 System.out.println(t.getLocalizedMessage());
		 Error e1 = new Error(t.getMessage());
		 e1.setStackTrace(t.getStackTrace());
		 throw e1;
		 }
	 }
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_04_ScorecardSearchFilterDocumentFromDocumentLibrary" })
		 @Parameters({ "SheetName","rowNum" })
		 public void Portfolio_CTC_07_05_PortfolioSharedDocInLocAccDocumentLibrary(String SheetName,int rowNum) throws IOException {
		 TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		 StartTest(TestCaseName,"Upload Audit Document in DocumentLibrary");
		 try {
			 pfu.SharedDocSearchFilterDocumentFromDocumentLibrary(SheetName,rowNum);
			String editTooltip = "This document cannot be edited as this is part of portfolio upload.";
			String ReplaceTooltip =  "This document cannot be replaced as this is part of portfolio upload.";
			String ArchiveTooltip = "This document cannot be deleted as this is part of portfolio upload.";
			 pfu.SharedDocDocument("PortfolioLocationDocumentSharedDoc",editTooltip,ReplaceTooltip,ArchiveTooltip);
		} catch (Throwable t) {
		 System.out.println(t.getLocalizedMessage());
		 Error e1 = new Error(t.getMessage());
		 e1.setStackTrace(t.getStackTrace());
		 throw e1;
		 }
	 }
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_05_PortfolioSharedDocInLocAccDocumentLibrary"})
		@Parameters({ "SheetName", "rowNum" })
		public void Portfolio_CTC_07_06_EditDocumentUploadInDocumentLibrary(String SheetName, int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

			StartTest(TestCaseName, "Validation Location Count Functionality");

			try {
				pfu.editScorecardDocumentFromDocumentLibrary();
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_06_EditDocumentUploadInDocumentLibrary"})
		@Parameters({ "SheetName", "rowNum", "ProjectType" })
		public void Portfolio_CTC_07_07_ValidPagnitionInDocumentLibrary(String SheetName, int rowNum, String ProjectType) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

			StartTest(TestCaseName, "Validation Pagnition In Document Library Functionality");

			try {

				pfu.pagnitionScorecardDocumentFromDocumentLibrary();
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_07_ValidPagnitionInDocumentLibrary"})
		@Parameters({ "SheetName", "rowNum" })
		public void Portfolio_CTC_07_08_DeleteDocumentUploadInDocumentLibrary(String SheetName, int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

			StartTest(TestCaseName, "Delete Document Upload In Document Library Functionality");

			try {
				pfu.deleteScorecardDocumentFromDocumentLibrary();
			
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_08_DeleteDocumentUploadInDocumentLibrary" })
		 @Parameters({ "SheetName","rowNum" })
		 public void Portfolio_CTC_07_09_ValidateLocationUploadDocumentInPortfolioAcccount(String SheetName,int rowNum) throws IOException {
		 TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		 StartTest(TestCaseName,"Validate Document count in Portfolio Account DocumentLibrary");
		 try {
			 portfolio.SearchPortfolioById(SheetName,rowNum);
			 portfolio.clickDocument();
			 pfu.ValidateLocationUploadDocumentInPortfolioAcccount(SheetName,rowNum);
			 String editTooltip = "Documents uploaded in individual project scorecards cannot be edited here.";
				String ReplaceTooltip =  "Documents uploaded in individual project scorecards cannot be replaced here.";
				String ArchiveTooltip = "Documents uploaded in individual project scorecards cannot be deleted here.";
				 pfu.SharedDocDocument("PortfolioDocumentListLink",editTooltip,ReplaceTooltip,ArchiveTooltip);
                portfolio.PortfolioBuildScorecard();
                pfu.SharedDocInScorecard("Meet Thresholds for Organic Gases","EditMenuIconA01.2",editTooltip,ReplaceTooltip,ArchiveTooltip,"PortfolioScoreCardFeature");
		} catch (Throwable t) {
		 System.out.println(t.getLocalizedMessage());
		 Error e1 = new Error(t.getMessage());
		 e1.setStackTrace(t.getStackTrace());
		 throw e1;
		 }
	 }
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_09_ValidateLocationUploadDocumentInPortfolioAcccount"})
		@Parameters({ "SheetName", "rowNum" })
		public void Portfolio_CTC_07_10_ValidAddLocation(String SheetName, int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

			StartTest(TestCaseName, "Validation Location Count Functionality");

			try {
				rc.ValidAddLocation(SheetName, rowNum,"20");
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_10_ValidAddLocation"})
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_07_11_UploadScorecardDocumentForMultipleOptionsAndMultipleLocations(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Upload Scorecard Document for Multiple Options and Multiple Locations");

		try {
			rc.clickScorecard();
			pfu.UploadScorecardDocumentForMultipleOptionsAndMultipleLocations("Meet Thresholds for Organic Gases",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	} 
	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_11_UploadScorecardDocumentForMultipleOptionsAndMultipleLocations"})
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_07_12_UploadScorecardDocumentForOneLocationAndOneOption(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Upload Scorecard Document for one Location and one Option");

		try {
			pfu.UploadScorecardDocumentForOneLocationAndOneOption("Meet Enhanced Thresholds for Organic Gases",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}          
	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_12_UploadScorecardDocumentForOneLocationAndOneOption"})
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_07_13_UploadScorecardDocumentForOneOptionAndMultipleLocations(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Upload Scorecard Document for one Option and Multiple Locations");

		try {
			pfu.UploadScorecardDocumentForOneOptionAndMultipleLocations("Meet Enhanced Thresholds for Inorganic Gases",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage()); 
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_13_UploadScorecardDocumentForOneOptionAndMultipleLocations"})
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_07_14_UploadScorecardDocumentForMultipleOptionsAndOneLocation(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Upload Scorecard Document for multiple Options and one Location");

		try {
			if (!ProjectType.contains("pilot")) {
			pfu.UploadScorecardDocumentForMultipleOptionsAndOneLocation("Increase Outdoor Air Supply",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_14_UploadScorecardDocumentForMultipleOptionsAndOneLocation"})
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_07_15_UploadScorecardDocumentForAuditDocument(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Upload Scorecard Document for one Option and Multiple Locations");

		try {
			if (!ProjectType.contains("pilot")) {
//			pfu.RefreshThePage();
//			pfu.UploadScorecardDocumentForAuditDocument("Perform Envelope Commissioning",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false);
//			pfu.RefreshThePage();
//			pfu.UploadScorecardDocumentForAuditDocument("Improve Supply Air",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false);
//			pfu.RefreshThePage();
//			pfu.UploadScorecardDocumentForAuditDocument("Manage Pollution and Exhaust",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false);
//			pfu.RefreshThePage();
//			rc.CommonSingleUploadScorecardDocument("Improve Ventilation Effectiveness", SheetName, rowNum, Commodity,AlternativeFileUpload ,false,false,false,false);
//			pfu.RefreshThePage();
//			rc.CommonSingleUploadScorecardDocument("Promote Drinking Water Transparency", SheetName, rowNum, Commodity,FeaturefileUpload ,false,false,false,false);
//			rc.CommonSingleUploadScorecardDocument("Improve Ventilation Effectiveness", SheetName, rowNum, Commodity,AlternativeFileUpload ,false,false,false,false);
			
			}
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage()); 
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
		

	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_15_UploadScorecardDocumentForAuditDocument"})
	@Parameters({ "SheetName", "rowNum", "ProjectType" })
	public void Portfolio_CTC_07_16_ValidatingAcheivedReviewDocument(String SheetName, int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Mark Document as Achieved or Reviewed");

		try {	
			if (!ProjectType.contains("pilot")) {
	//	pfu.ValidatingAcheivedReviewDocument(SheetName,rowNum);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_16_ValidatingAcheivedReviewDocument"})
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_07_17_ValidateDocumentsLibUnassignLocationFunctionalityForReviewedAlternativeDocs(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
		try {
			if (!ProjectType.contains("pilot")) {
//			rc.clickDocument();
//			pfu.ValidateDocumentsLibUnassignLocationFunctionalityForReviewedDocs(SheetName, rowNum, ProjectType, Commodity, 
//					"A06.2", "Alternative Strategy", "Reviewed", "9 Assigned");
			}

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
    
    @Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_17_ValidateDocumentsLibUnassignLocationFunctionalityForReviewedAlternativeDocs"})
	@Parameters({ "SheetName", "rowNum","ProjectType" })
	public void Portfolio_CTC_07_18_AfterReviewHistory(String SheetName, int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Mark Document as Achieved or Reviewed");

		try {
			
			if (!ProjectType.contains("pilot")) {
//			rc.clickScorecard();
//			pfu.scorecardPostReviewHistoryView("Provide Active Workstations");
//			login.AdminLogin();
//			portfolio.AdminSearch(SheetName, rowNum);
//			portfolio.PortfolioBuildScorecard();
//			pfu.scorecardPostReviewHistoryAsAdmin("Provide Active Workstations");
//			rc.SignOut();
//			login.Login();
//			portfolio.SearchPortfolioById(SheetName, rowNum);
//			portfolio.PortfolioBuildScorecard();
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_18_AfterReviewHistory"})
		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
		public void Portfolio_CTC_07_19_ValidateScorecardUnassignLocationFromOptionWithReviewedDocs(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

			StartTest(TestCaseName, "Validate scorecard unassign Location from Option with Reviewed Docs");

			try {
				if (!ProjectType.contains("pilot")) {
			//	pfu.ValidateScorecardUnassignLocationFromOptionWithReviewedDocs("Meet Thresholds for Organic Gases",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false);
				}
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_19_ValidateScorecardUnassignLocationFromOptionWithReviewedDocs"})
		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
		public void Portfolio_CTC_07_20_ValidateScorecardArchiveDocumenForMultipleOptionsOneLocation(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

			StartTest(TestCaseName, "Validate scorecard unassign Location from Option with Reviewed Docs");

			try {
				if (!ProjectType.contains("pilot")) {
			//	pfu.ValidateArchiveDocumentsOnUnassigningAllLocation("Meet Enhanced Thresholds for Organic Gases",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false);
				}
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_20_ValidateScorecardArchiveDocumenForMultipleOptionsOneLocation"})
		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
		public void Portfolio_CTC_07_21_ValidateScorecardArchiveDocumenForOneLocationOneOption(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

			StartTest(TestCaseName, "Validate scorecard unassign Location from Option with Reviewed Docs");

			try {
				if (!ProjectType.contains("pilot")) {
//				pfu.RefreshThePage();
//				pfu.ValidateArchiveDocumentsOnUnassigningAllLocation("Promote Drinking Water Transparency",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false);

				}
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_21_ValidateScorecardArchiveDocumenForOneLocationOneOption"})
		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
		public void Portfolio_CTC_07_22_ValidateScorecardArchiveDocumenForOneOptionMultipleLocations(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			
			StartTest(TestCaseName, "Validate scorecard unassign Location from Option with Reviewed Docs");

			try {
				if (!ProjectType.contains("pilot")) {
//				pfu.RefreshThePage();
//				pfu.ValidateScorecardOneLocationUnassignLocationFunctionalityForReviewedDocs("Meet Enhanced Thresholds for Inorganic Gases", SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false, 
//						"9 Achieved", "9 Assigned", "ONE LOCATION");
				}
			} catch (Throwable t) {  
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_22_ValidateScorecardArchiveDocumenForOneOptionMultipleLocations"})
		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
		public void Portfolio_CTC_07_23_ValidateScorecardArchiveDocumenForMultipleOptionsAndMultipleLocations(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			
			StartTest(TestCaseName, "Validate scorecard unassign Location from Option with Reviewed Docs");

			try {
				if (!ProjectType.contains("pilot")) {
//				pfu.RefreshThePage();
//				pfu.ValidateScorecardArchiveDocumenForMultipleOptionsAndMultipleLocations("Meet Thresholds for Organic Gases",SheetName, rowNum, ProjectType, Commodity);
				}
			} catch (Throwable t) {  
				System.out.println(t.getLocalizedMessage());     
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
    	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_23_ValidateScorecardArchiveDocumenForMultipleOptionsAndMultipleLocations" })
    	@Parameters({ "SheetName", "rowNum","ProjectType","Commodity" })
    	public void Portfolio_CTC_07_24_ValidateScorecardYesToNoConfirmationModalAfterUploadingMultipleDocs(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

    		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		StartTest(TestCaseName, "HSR Validate Scorecard Confirmation Modal from Purse Yes to No After Adding Options");
    		try {
    			if (ProjectType.contains("pilot")) {
    			//	pfu.ValidateScorecardConfirmationModalAfterUploadingMultipleDocs("Monitor Fundamental Air Parameters", SheetName, rowNum, Commodity);
    	    		} 
  				else {
  				//	pfu.ValidateScorecardConfirmationModalAfterUploadingMultipleDocs("Measure Air Parameters", SheetName, rowNum, Commodity);	
  				}
    		} catch (Throwable t) {
    			System.out.println(t.getLocalizedMessage());
    			Error e1 = new Error(t.getMessage());
    			e1.setStackTrace(t.getStackTrace());
    			throw e1;
    		}
    	}
    	
    	
    	
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_24_ValidateScorecardYesToNoConfirmationModalAfterUploadingMultipleDocs" })
		@Parameters({ "SheetName","rowNum","ProjectType","Commodity" })
		public void Portfolio_CTC_07_25_ValidateArchiveDocumentFunctionalityInScorecardForFeatureDoc(String SheetName,int rowNum, String ProjectType,String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Feature Post Review Functionality");
			try {
				if (!ProjectType.contains("pilot")) {
		//		equity.ValidatePostReviewArchiveDocumentFunctionalityInScorecard(SheetName, rowNum, "Meet Thresholds for Particulate Matter", Commodity);
				}
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_25_ValidateArchiveDocumentFunctionalityInScorecardForFeatureDoc" })
    	@Parameters({ "SheetName", "rowNum","ProjectType","Commodity" })
    	public void Portfolio_CTC_07_26_ValidateScorecardYesToMaybe(String SheetName, int rowNum, String ProjectType, String Commodity) throws IOException {

    		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
    		StartTest(TestCaseName, "Validate Scorecard Yes To Maybe");
    		try {
    			if (!ProjectType.contains("pilot")) {
    	//	pfu.purseYesToMaybeValidFromScorecard();
    			}	
    		} catch (Throwable t) {
    			System.out.println(t.getLocalizedMessage());
    			Error e1 = new Error(t.getMessage());
    			e1.setStackTrace(t.getStackTrace());
    			throw e1;
    		}
    	}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_26_ValidateScorecardYesToMaybe" })
		@Parameters({ "SheetName","rowNum","ProjectType","Commodity" })
		public void Portfolio_CTC_07_27_ValidateArchiveDocumentFunctionalityInScorecardForAuditDoc(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Audit Post Review Functionality");
			try {
				if (!ProjectType.contains("pilot")) {
			//	equity.ValidatePostReviewArchiveDocumentFunctionalityInScorecard(SheetName, rowNum, "Prohibit Outdoor Smoking", Commodity);
				}
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_27_ValidateArchiveDocumentFunctionalityInScorecardForAuditDoc"})
		@Parameters({ "SheetName","rowNum","ProjectType","Commodity" })
		public void Portfolio_CTC_07_28_MoveOnArchiveAndValidateArchiveInDocumentListForFeatureDoc(String SheetName,int rowNum, String ProjectType,String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Feature Post Review Functionality");
			try {
				if (!ProjectType.contains("pilot")) {
//				rc.clickDocument();
//				equity.ValidatePostReviewArchiveDocumentFunctionalityInDocumentsLibrary(SheetName, rowNum, Commodity, "Feature");
				}
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_28_MoveOnArchiveAndValidateArchiveInDocumentListForFeatureDoc"})
		@Parameters({ "SheetName","rowNum","ProjectType","Commodity" })
		public void Portfolio_CTC_07_29_MoveOnArchiveAndValidateArchiveInDocumentListForAlternativeDoc(String SheetName,int rowNum, String ProjectType,String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
			try {
				if (!ProjectType.contains("pilot")) {
			//	equity.ValidatePostReviewArchiveDocumentFunctionalityInDocumentsLibrary(SheetName, rowNum, Commodity, "Alternative");
				}
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_29_MoveOnArchiveAndValidateArchiveInDocumentListForAlternativeDoc"})
		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
		public void Portfolio_CTC_07_30_ValidateScorecardUnassignLocationFunctionalityForReviewedAuditDocs(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
			try {
				if (!ProjectType.contains("pilot")) {
//				pfu.ValidateScorecardOneLocationUnassignLocationFunctionalityForReviewedDocs("Perform Envelope Commissioning", SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false, 
//						"2 Achieved", "2 Assigned", "ONE LOCATION");
//				pfu.RefreshThePage();
//				pfu.ValidateScorecardOneLocationUnassignLocationFunctionalityForReviewedDocs("Manage Pollution and Exhaust", SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false, 
//						"1 Achieved", "1 Assigned", "TWO LOCATION");
//				pfu.RefreshThePage();
//				pfu.ValidateArchiveDocumentsOnUnassigningAllLocation("Improve Supply Air",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false, false, false, false);
				}
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_30_ValidateScorecardUnassignLocationFunctionalityForReviewedAuditDocs"})
		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
		public void Portfolio_CTC_07_31_ValidateDocumentsLibUnassignLocationFunctionalityForReviewedFeatureDocs(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
			try {
				if (!ProjectType.contains("pilot")) {
//				rc.clickDocument();
//				pfu.ValidateDocumentsLibUnassignLocationFunctionalityForReviewedDocs(SheetName, rowNum, ProjectType, Commodity, 
//						"A05.3", "Performance Test OR Sensor Data", "Reviewed", "9 Assigned");
				}
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_31_ValidateDocumentsLibUnassignLocationFunctionalityForReviewedFeatureDocs"})
		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
		public void Portfolio_CTC_07_32_ValidateDocumentsLibRemovePartFunctionalityForReviewedFeatureDocs(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
			try {
				if (!ProjectType.contains("pilot")) {
					
			//	pfu.ValidateDocumentsLibRemovePartFunctionalityForReviewedDocs(SheetName, rowNum, ProjectType, Commodity, 
			//			"A05.3", "Performance Test OR Sensor Data", "Reviewed", "10 Pursuing");
				}
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
}