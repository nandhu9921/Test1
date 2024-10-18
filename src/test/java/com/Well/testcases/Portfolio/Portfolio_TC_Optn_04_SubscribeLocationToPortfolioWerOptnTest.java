package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_Optn_04_SubscribeLocationToPortfolioWerOptnTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_03_SubscribeLocationToPortfolioWprOptnTest.Portfolio_TC_Optn_03_04_OptnWprValidateUploadDocumentLibraryInPortfolioAccount" })
	@Parameters({ "SheetName", "rowNum" })
	public void Portfolio_TC_Optn_04_01_OptnWerLocationAccountUploadDoc(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register Portfolio Account, Location Account and Upload Scorecard document WELL At Scale Project Functionality");
		try {
			portfolio.navigateWERPortfolioOpt();
			pfu.NavigateAddedLocation(SheetName, rowNum);
			portfolio.navigateWERPortfolioOpt();
			rc.ScorecardLoading();
			v2project.uploadDocumentInFeature(SheetName, 1);
		} 
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_04_SubscribeLocationToPortfolioWerOptnTest.Portfolio_TC_Optn_04_01_OptnWerLocationAccountUploadDoc" })
		@Parameters({ "SheetName", "rowNum" })
		public void Portfolio_TC_Optn_04_02_OptnWerValidateUploadDocInPortfolioAccount(String SheetName, int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Navigate to Portfolio Account WELL At Scale Project Functionality");
			try {
				portfolio.SearchPortfolioById(SheetName,rowNum);
				portfolio.werOptnPortfolioAccount1();
					} 
			catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_04_SubscribeLocationToPortfolioWerOptnTest.Portfolio_TC_Optn_04_02_OptnWerValidateUploadDocInPortfolioAccount" })
		@Parameters({ "SheetName", "rowNum", "Commodity" })
		public void Portfolio_TC_Optn_04_03_OptnWprValidateUploadScorecardDocInPortfolioAccount(String SheetName, int rowNum, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate location account Scorecard Upload document in Portfolio Account WELL At Scale Project Functionality");
			try {
				portfolio.ValiateScorecardUploadDocInHSROptnPortfolioAccount("Create DEI Assessment and Action Plan", SheetName, rowNum, Commodity);
				
			} 
			catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_04_SubscribeLocationToPortfolioWerOptnTest.Portfolio_TC_Optn_04_03_OptnWprValidateUploadScorecardDocInPortfolioAccount" })
		@Parameters({ "SheetName", "rowNum", "Commodity" })
		public void Portfolio_TC_Optn_04_04_OptnWprValidateUploadDocumentLibraryInPortfolioAccount(String SheetName, int rowNum, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate location account Document library Upload document in Portfolio Account WELL At Scale Project Functionality");
			try {
				portfolio.HSROptnDocumentProfolioAccount("Create DEI Assessment and Action Plan", SheetName, rowNum, Commodity);
			} 
			catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	}

