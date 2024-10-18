package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class Portfolio_CTC_11_ReplaceDocumentUploadTest extends BaseClass {

	@Test(dependsOnMethods ={"com.Well.testcases.Portfolio.Portfolio_CTC_10_AssignedTaskInLocationAccountTest.Portfolio_CTC_10_08_ValidateHsrUploadedDocumentInPotfolio"	 })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_11_00_FeatureScorecardUploadReplace(String SheetName, int rowNum, String ProjectType,
			String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Portfolio Feature Scorecard Upload and Replace the Document");
		try {
			rc.clickScorecard();
			String featurename = "Meet Chemical Thresholds";
			rc.CommonSingleUploadScorecardDocument(featurename, SheetName, rowNum, Commodity, FeaturefileUpload, false,
					false, true, false);
			pfu.ScorecardUploadReplace(SheetName, rowNum, Commodity, featurename, "Performance Test", "W02.1",
					"All Spaces", "Feature verification", "TestComment", FeaturefileUpload, "9");

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_CTC_11_ReplaceDocumentUploadTest.Portfolio_CTC_11_00_FeatureScorecardUploadReplace" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_11_01_AuditScorecardUploadReplace(String SheetName, int rowNum, String ProjectType,
			String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Portfolio Audit Scorecard Upload and Replace the Document");
		try {
			rc.CommonSingleUploadScorecardDocument("Promote Fruit and Vegetable Visibility", SheetName, rowNum,
					Commodity, AuditfileUpload, false, false, true, false);
			pfu.ScorecardUploadReplace(SheetName, rowNum, Commodity, "Promote Fruit and Vegetable Visibility",
					"On-site Photographs", "N01.2", "All Spaces", "Audit verification", "TestComment", AuditfileUpload,
					"2");

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_CTC_11_ReplaceDocumentUploadTest.Portfolio_CTC_11_01_AuditScorecardUploadReplace" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_11_02_AlternativeScorecardUploadReplace(String SheetName, int rowNum, String ProjectType,
			String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Portfolio Alternative Scorecard Upload and Replace the Document");
		try {
			rc.CommonSingleUploadScorecardDocument("Provide Supplemental Lighting", SheetName, rowNum, Commodity,
					AlternativeFileUpload, false, false, true, false);
			pfu.ScorecardUploadReplace(SheetName, rowNum, Commodity, "Provide Supplemental Lighting",
					"Alternative Strategy", "L09.2", "Alternative Adherence Path (AAP)", "Feature verification",
					"TestComment", AlternativeFileUpload, "9");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_CTC_11_ReplaceDocumentUploadTest.Portfolio_CTC_11_02_AlternativeScorecardUploadReplace" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_11_03_FeatureUploadReplaceInDocumentLibrary(String SheetName, int rowNum,
			String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Portfolio Alternative Document Upload and Replace the Document");
		try {
			portfolio.clickDocument();
			pfu.DocumentUploadReplace(SheetName, rowNum, Commodity, "Provide Supplemental Lighting",
					"Alternative Strategy", "L09.2", "Alternative Adherence Path (AAP)", "Feature verification",
					"TestComment", AlternativeFileUpload, "9");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_CTC_11_ReplaceDocumentUploadTest.Portfolio_CTC_11_03_FeatureUploadReplaceInDocumentLibrary" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_11_04_AchieveReview(String SheetName, int rowNum, String ProjectType, String Commodity)
			throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Portfolio Alternative Scorecard Upload and Replace the Document");
		try {
			portfolio.SubmitReviewDocument(SheetName, rowNum);
			login.AdminLogin();
			portfolio.AdminCompleteReview(SheetName, rowNum);
			pfu.ValidatingAcheivedReviewDocument(SheetName, rowNum);
			rc.SignOut();
			login.Login();
			portfolio.SearchPortfolioById(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_CTC_11_ReplaceDocumentUploadTest.Portfolio_CTC_11_04_AchieveReview" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_11_05_PostFeatureScorecardUploadReplace(String SheetName, int rowNum, String ProjectType,
			String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Validate Alternative Post Review Functionality");
		try {
			rc.clickScorecard();
			String featurename = "Meet Chemical Thresholds";
				pfu.postScorecardUploadReplace(SheetName, rowNum, Commodity, featurename, "Performance Test", "W02.1",
						"All Spaces", "Feature verification", "TestComment", FeaturefileUpload, "10");

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_CTC_11_ReplaceDocumentUploadTest.Portfolio_CTC_11_05_PostFeatureScorecardUploadReplace" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_11_06_PostAuditScorecardUploadReplace(String SheetName, int rowNum, String ProjectType,
			String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Validate Alternative Post Review Functionality");
		try {
	  				pfu.postScorecardUploadReplace(SheetName, rowNum, Commodity, "Promote Fruit and Vegetable Visibility",
  						"On-site Photographs", "N01.2", "All Spaces", "Audit verification", "TestComment", AuditfileUpload,
  						"3");

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {
			"com.Well.testcases.Portfolio.Portfolio_CTC_11_ReplaceDocumentUploadTest.Portfolio_CTC_11_06_PostAuditScorecardUploadReplace" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_11_07_PostAlternativeScorecardUploadReplace(String SheetName, int rowNum,
			String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Validate Alternative Post Review Functionality");
		try {
  				pfu.postScorecardUploadReplace(SheetName, rowNum, Commodity, "Provide Supplemental Lighting",
  						"Alternative Strategy", "L09.2", "Alternative Adherence Path (AAP)", "Feature verification",
  						"TestComment", AlternativeFileUpload, "10");

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = {	"com.Well.testcases.Portfolio.Portfolio_CTC_11_ReplaceDocumentUploadTest.Portfolio_CTC_11_07_PostAlternativeScorecardUploadReplace" })
	@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	public void Portfolio_CTC_11_08_DocumentLibraryUploadReplaceOneLocation(String SheetName, int rowNum,
			String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Validate Post Review Remove Option and Add Location Functionality");
		try {
			pfu.ValidatingAcheivedReviewDocument(SheetName, rowNum);
			portfolio.clickDocument();
			pfu.ReplaceOneOptionDocumentUpload(SheetName, rowNum,Commodity, FeaturefileUpload, "2","ReplaceDocInDocLibrary");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}