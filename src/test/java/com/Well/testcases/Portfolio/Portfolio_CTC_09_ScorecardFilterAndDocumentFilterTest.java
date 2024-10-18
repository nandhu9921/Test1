package com.Well.testcases.Portfolio;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class Portfolio_CTC_09_ScorecardFilterAndDocumentFilterTest  extends BaseClass {
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_11_ReplaceDocumentUploadTest.Portfolio_CTC_11_08_DocumentLibraryUploadReplaceOneLocation" })
	@Parameters({  "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation"  })
	public void Portfolio_CTC_09_00_UploadScorecard(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");
		try {
			commonAPI.StoreAnyProject(SheetName, rowNum, Country, ProjectType, Upload, API, Commodity, ImportLocation, "AutomationCustomProductScorecardPortfolioFilters", false);		  
			portfolio.SearchPortfolioById(SheetName,rowNum);
			commonAPI.SignAgreementProject(SheetName, rowNum, Commodity);
			commonAPI.RegisterProject(SheetName, rowNum, Country, ProjectType, "Portfolio");
			commonAPI.BillingProject(SheetName, rowNum, Country, ProjectType, "Portfolio");
			CommonMethod.refreshBrowser();			
			generic.importLocationGeneric(Commodity, PortfolioLocationUploadImportfile);
			portfolio.PortfolioBuildScorecard();
			commonAPI.storeRatingScorecardIdAPI(SheetName,rowNum,"scorecard_id");
			commonAPI.fillScorecardAPI(SheetName,rowNum, "ScorecardId");
			CommonMethod.refreshBrowser();
			rc.ScorecardLoading();
			pfu.purseYesToNoValidFromScorecard();
			rc.CommonSingleUploadScorecardDocument("Meet Thresholds for Particulate Matter", SheetName, rowNum, Commodity,AlternativeFileUpload ,false,false,false,false);
			
		} 
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_09_ScorecardFilterAndDocumentFilterTest.Portfolio_CTC_09_00_UploadScorecard" })
	@Parameters({  "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation"  })
	public void Portfolio_CTC_09_01_ValidateScoreOptionFilter(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Scorecard Option Filter Portfolio");
		try {
			portfolio.scorecardOptionFilterPortfolio(Commodity);
		} 
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_09_ScorecardFilterTest.Portfolio_CTC_09_01_ValidateScoreOptionFilter" })
	@Parameters({  "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation"  })
	public void Portfolio_CTC_09_02_DocumentOptionFilter(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");
		try {
			pfu.documentOptionFilter();
			
		} 
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}