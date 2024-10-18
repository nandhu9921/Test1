package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class Portfolio_TC_20C_ReplaceDocumentUploadOptnTest extends BaseClass {
	
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20B_ReviewEquityPortfolioTest.Portfolio_TC_20B_10_ValidateWERSectionInAdminFields" })
		@Parameters({ "SheetName","rowNum", "Commodity" })
		public void PortfolioOptnRating_TC_20C_00_FeatureScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Upload Alternative Document Functionality");
			try {
				rc.SignOut();
				login.Login();
				portfolio.SearchPortfolioById(SheetName,rowNum);
			    portfolio.werOptnPortfolioAccount1();
				rc.CommonSingleUploadScorecardDocument("Support Equitable Working Hours", SheetName, rowNum, Commodity, FeaturefileUpload, false, false, true, false);
				pfu.ScorecardUploadReplace(SheetName, rowNum, "Ratings", "Support Equitable Working Hours", "Policy and/or Operations Schedule", "EB5",	"All Spaces", "Feature verification", "TestComment", FeaturefileUpload, "4");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20C_ReplaceDocumentUploadOptnTest.PortfolioOptnRating_TC_20C_00_FeatureScorecardUploadReplace" })
		@Parameters({ "SheetName","rowNum", "Commodity" })
		public void PortfolioOptnRating_TC_20C_01_AuditScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Upload Alternative Document Functionality");
			try {
				rc.CommonSingleUploadScorecardDocument("Provide Community Space", SheetName, rowNum, Commodity, AuditfileUpload, false, false, true, false);
				pfu.ScorecardUploadReplace(SheetName, rowNum, "Ratings", "Provide Community Space",
						"Technical Document (Audited)", "EC2", "All Spaces", "Audit verification", "TestComment", AuditfileUpload,
						"2");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20C_ReplaceDocumentUploadOptnTest.PortfolioOptnRating_TC_20C_01_AuditScorecardUploadReplace" })
		@Parameters({ "SheetName","rowNum", "Commodity" })
		public void PortfolioOptnRating_TC_20C_02_AlternativeScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Upload Alternative Document Functionality");
			try {
				rc.CommonSingleUploadScorecardDocument("Provide Restorative Space", SheetName, rowNum, Commodity, AlternativeFileUpload, false, false, true, false);
				pfu.ScorecardUploadReplace(SheetName, rowNum, "Ratings", "Provide Restorative Space",
						"Alternative Strategy", "ES2", "Alternative Adherence Path (AAP)", "Feature verification",
						"TestComment", AlternativeFileUpload, "4");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20C_ReplaceDocumentUploadOptnTest.PortfolioOptnRating_TC_20C_02_AlternativeScorecardUploadReplace" })
		@Parameters({ "SheetName","rowNum", "Commodity" })
		public void PortfolioOptnRating_TC_20C_03_UploadDocumentLibraryReplace(String SheetName,int rowNum, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Upload Alternative Document Functionality");
			try {
				portfolio.clickWERDocument();
				pfu.DocumentUploadReplace(SheetName, rowNum, "Ratings", "Provide Restorative Space",
						"Alternative Strategy", "ES2", "Alternative Adherence Path (AAP)", "Feature verification",
						"TestComment", AlternativeFileUpload, "4");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20C_ReplaceDocumentUploadOptnTest.PortfolioOptnRating_TC_20C_03_UploadDocumentLibraryReplace" })
		@Parameters({ "SheetName","rowNum", "Commodity" })
		public void PortfolioOptnRating_TC_20C_04_PostFeatureScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Upload Alternative Document Functionality");
			try {
				 portfolio.werOptnPortfolioAccount1();
				 String Id = commonAPI.storePortfolioId(SheetName, rowNum);
				 commonAPI.ValidatingAcheivedReviewDocument(SheetName,rowNum,"wer",Id);
				 pfu.postScorecardUploadReplace(SheetName, rowNum, "Ratings", "Support Equitable Working Hours", "Policy and/or Operations Schedule", "EB5",
						"All Spaces", "Feature verification", "TestComment", FeaturefileUpload, "6");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20C_ReplaceDocumentUploadOptnTest.PortfolioOptnRating_TC_20C_04_PostFeatureScorecardUploadReplace" })
		@Parameters({ "SheetName","rowNum", "Commodity" })
		public void PortfolioOptnRating_TC_20C_05_PostAuditScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Upload Alternative Document Functionality");
			try {
				pfu.postScorecardUploadReplace(SheetName, rowNum, "Ratings", "Provide Community Space",
				"Technical Document (Audited)", "EC2", "All Spaces", "Audit verification", "TestComment", AuditfileUpload,"3");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20C_ReplaceDocumentUploadOptnTest.PortfolioOptnRating_TC_20C_05_PostAuditScorecardUploadReplace" })
		@Parameters({ "SheetName","rowNum", "Commodity" })
		public void PortfolioOptnRating_TC_20C_06_PostAlternativeScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Upload Alternative Document Functionality");
			try {
				pfu.postScorecardUploadReplace(SheetName, rowNum, "Ratings", "Provide Restorative Space",
						"Alternative Strategy", "ES2", "Alternative Adherence Path (AAP)", "Feature verification",
						"TestComment", AlternativeFileUpload, "6");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20C_ReplaceDocumentUploadOptnTest.PortfolioOptnRating_TC_20C_06_PostAlternativeScorecardUploadReplace" })
		@Parameters({ "SheetName","rowNum", "Commodity" })
		public void PortfolioOptnRating_TC_20C_07_PostUploadDocumentLibraryReplace(String SheetName,int rowNum, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Upload Alternative Document Functionality");
			try {
				portfolio.clickWERDocument();
				pfu.DocumentUploadReplace(SheetName, rowNum, "Ratings", "Provide Restorative Space",
						"Alternative Strategy", "ES2", "Alternative Adherence Path (AAP)", "Feature verification",
						"TestComment", AlternativeFileUpload, "4");
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	}
