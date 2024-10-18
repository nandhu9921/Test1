package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class Portfolio_CTC_12_PostReplaceDocumentUploadForAddedLocationTest extends BaseClass {
	 
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_32_ValidateDocumentsLibRemovePartFunctionalityForReviewedFeatureDocs"})
		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
		public void Portfolio_CTC_11_00_PostFeatureScorecardUploadReplace(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
			try {
//				pfu.ScorecardUploadReplace(SheetName, rowNum, Commodity, featurename, "Performance Test", "W02.1",
//						"All Spaces", "Feature verification", "TestComment", FeaturefileUpload, "9");

			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_33_FeatureScorecardUploadReplace"})
	  		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
	  		public void Portfolio_CTC_11_01_PostAuditScorecardUploadReplace(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

	  			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	  			StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
	  			try {
//	  				pfu.ScorecardUploadReplace(SheetName, rowNum, Commodity, "Promote Fruit and Vegetable Visibility",
//	  						"On-site Photographs", "N01.2", "All Spaces", "Audit verification", "TestComment", AuditfileUpload,
//	  						"2");

	  			} catch (Throwable t) {
	  				System.out.println(t.getLocalizedMessage());
	  				Error e1 = new Error(t.getMessage());
	  				e1.setStackTrace(t.getStackTrace());
	  				throw e1;
	  			}
	  		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_34_AuditScorecardUploadReplace"})
  		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
  		public void Portfolio_CTC_11_02_PostAlternativeScorecardUploadReplace(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

  			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
  			StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
  			try {
//  				pfu.ScorecardUploadReplace(SheetName, rowNum, Commodity, "Provide Supplemental Lighting",
//  						"Alternative Strategy", "L09.2", "Alternative Adherence Path (AAP)", "Feature verification",
//  						"TestComment", AlternativeFileUpload, "9");

  			} catch (Throwable t) {
  				System.out.println(t.getLocalizedMessage());
  				Error e1 = new Error(t.getMessage());
  				e1.setStackTrace(t.getStackTrace());
  				throw e1;
  			}
  		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_35_AlternativeScorecardUploadReplace"})
  		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
  		public void Portfolio_CTC_11_03_PostScorecardUploadReplaceOneOption(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

  			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
  			StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
  			try {
  			

  			} catch (Throwable t) {
  				System.out.println(t.getLocalizedMessage());
  				Error e1 = new Error(t.getMessage());
  				e1.setStackTrace(t.getStackTrace());
  				throw e1;
  			}
  		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_36_ScorecardUploadReplaceOneOption"})
  		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
  		public void Portfolio_CTC_11_04_ScorecardUploadReplaceOneLocation(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

  			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
  			StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
  			try {
  			

  			} catch (Throwable t) {
  				System.out.println(t.getLocalizedMessage());
  				Error e1 = new Error(t.getMessage());
  				e1.setStackTrace(t.getStackTrace());
  				throw e1;
  			}
  		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_07_DocumentUploadForAddedLocationTest.Portfolio_CTC_07_37_ScorecardUploadReplaceOneLocation"})
  		@Parameters({ "SheetName", "rowNum", "ProjectType", "Commodity" })
  		public void Portfolio_CTC_11_04_DocumentLibraryUploadReplaceOneLocation(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

  			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
  			StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
  			try {
  			

  			} catch (Throwable t) {
  				System.out.println(t.getLocalizedMessage());
  				Error e1 = new Error(t.getMessage());
  				e1.setStackTrace(t.getStackTrace());
  				throw e1;
  			}
  		}
}