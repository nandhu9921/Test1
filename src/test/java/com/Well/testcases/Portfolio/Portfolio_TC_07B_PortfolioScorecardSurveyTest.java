package com.Well.testcases.Portfolio;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class Portfolio_TC_07B_PortfolioScorecardSurveyTest extends BaseClass {

	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_06A_PortfolioLocationImportAreaTest.Portfolio_TC_06A_02_UpdateAndvalidOccupancyValidation" })
	@Parameters({ "SheetName", "rowNum"})
	public void Portfolio_TC_07B_00_PortfolioScorecardSurvey(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Complete WELL At Scale Scorecard Survey Functionality");
		try {
	
			portfolio.ScorecardSurvey();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_TC_07B_PortfolioScorecardSurveyTest.Portfolio_TC_07B_00_PortfolioScorecardSurvey" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_TC_07B_01_PortfolioScorecardFeatureUpload(String SheetName, int rowNum, String ProjectType,
			String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "WELL At Scale Scorecard Upload Feature Document Functionality");
		try {
			rc.CommonSingleUploadScorecardDocument("Mitigate Construction Pollution", SheetName, rowNum, Commodity,
					FeaturefileUpload, false, false, false, false);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_TC_07B_PortfolioScorecardSurveyTest.Portfolio_TC_07B_01_PortfolioScorecardFeatureUpload" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_TC_07B_02_PortfolioScorecardAuditUpload(String SheetName, int rowNum, String ProjectType,
			String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "WELL At Scale Scorecard Upload Feature Document Functionality");
		try {
			portfolio.AuditScorecardUpload("Prohibit Outdoor Smoking", SheetName, rowNum, ProjectType, Commodity,
					AuditfileUpload, false, false, false, false);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_07B_PortfolioScorecardSurveyTest.Portfolio_TC_07B_02_PortfolioScorecardAuditUpload" })
    @Parameters({ "SheetName", "rowNum" })
    public void Portfolio_TC_07B_03_ValidateAuditDocsInScorecard(String SheetName, int rowNum) throws IOException {

      TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
      StartTest(TestCaseName, "WELL At Scale Scorecard Upload Feature Document Functionality");
      try { 
	  portfolio.ValidateAuditDocsInScorecard("Prohibit Outdoor Smoking", SheetName, rowNum);

      } catch (Throwable t) {
	  System.out.println(t.getLocalizedMessage());
	  Error e1 = new Error(t.getMessage());
	  e1.setStackTrace(t.getStackTrace());
	  throw e1;
      }
   }
	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_07B_PortfolioScorecardSurveyTest.Portfolio_TC_07B_03_ValidateAuditDocsInScorecard" })
    @Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
    public void Portfolio_TC_07B_04_ValidateAuditDocsInDocumentsLibrary(String SheetName, int rowNum, String ProjectType,
	String Commodity) throws IOException {

      TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
      StartTest(TestCaseName, "WELL At Scale Scorecard Upload Feature Document Functionality");
      try {
    	  portfolio.clickDocument();
    	  portfolio.ValidateAuditDocsInDocumentsLibrary(SheetName, rowNum);

      } catch (Throwable t) {
	  System.out.println(t.getLocalizedMessage());
	  Error e1 = new Error(t.getMessage());
	  e1.setStackTrace(t.getStackTrace());
	  throw e1;
      }
   }

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_TC_07B_PortfolioScorecardSurveyTest.Portfolio_TC_07B_04_ValidateAuditDocsInDocumentsLibrary" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_TC_07B_05_PortfolioScorecardAlternativeUpload(String SheetName, int rowNum,
			String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "WELL At Scale Scorecard Upload Audit Document Functionality");
		try {
			rc.clickScorecard();
			rc.CommonSingleUploadScorecardDocument("Ensure Adequate Ventilation", SheetName, rowNum, Commodity,
					AlternativeFileUpload, false, false, false, false);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_TC_07B_PortfolioScorecardSurveyTest.Portfolio_TC_07B_05_PortfolioScorecardAlternativeUpload" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_TC_07B_06_PortfolioScorecardPagination(String SheetName, int rowNum, String ProjectType,
			String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "WELL At Scale Scorecard Pagination Functionality");
		try {

			rc.scorecardPagination("Measure Air Parameters", SheetName, rowNum, Commodity, AlternativeFileUpload, false,
					false, false, false);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_TC_07B_PortfolioScorecardSurveyTest.Portfolio_TC_07B_06_PortfolioScorecardPagination" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_TC_07B_07_UploadAndValidateFulfilledDocs(String SheetName, int rowNum, String ProjectType,
			String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "WELL At Scale Scorecard Upload Feature Document Functionality");
		try {
			portfolio.uploadAndValidateFulfilledDocs("Meet Thresholds for Particulate Matter", SheetName, rowNum,
					ProjectType, Commodity, FeaturefileUpload, false, false, false, false);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_TC_07B_PortfolioScorecardSurveyTest.Portfolio_TC_07B_07_UploadAndValidateFulfilledDocs" })
	@Parameters({ "SheetName", "rowNum", "Commodity" })
	public void Portfolio_TC_07B_08_PortfolioDocumentPagination(String SheetName, int rowNum, String Commodity)
			throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "WELL At Scale Document library Pagination Functionality");
		try {
			
			 CommonMethod.refreshBrowser();
			 portfolio.ValidatingLegalUploadDocument(SheetName, rowNum, Commodity);
			 CommonMethod.refreshBrowser();
			 portfolio.ValidatingLegalUploadDocument(SheetName, rowNum, Commodity);
			 CommonMethod.refreshBrowser();
			 portfolio.ValidatingLegalUploadDocument(SheetName, rowNum, Commodity);
			 CommonMethod.refreshBrowser();
			 portfolio.ValidatingLegalUploadDocument(SheetName, rowNum, Commodity);
			 CommonMethod.refreshBrowser(); 
			 performance.DocumentPagination();
			 
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_TC_07B_PortfolioScorecardSurveyTest.Portfolio_TC_07B_08_PortfolioDocumentPagination" })
	@Parameters({ "SheetName", "rowNum" })
	public void Portfolio_TC_07B_09_ValidAddLocation(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Validation Location Count Functionality");

		try {
			rc.ValidAddLocation(SheetName, rowNum, "5");

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
