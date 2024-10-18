package com.Well.testcases.Portfolio;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class Portfolio_USTC_03_UpsellV2projectToLocationAccountTest extends BaseClass {	
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_USTC_02_RegisterTest.Portfolio_USTC_02_09_RegisterPortfolioAccount" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_USTC_03_00_AddingExternalProjectInPortfolioLocation(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Adding ExternalProject In Portfolio Location Functionality");

		try {			
			login.AdminLogin();
			portfolio.AdminSearch(SheetName, rowNum);
			portfolio.AddingExternalProjectInPortfolioLocation(SheetName, rowNum);
			
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}	

	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_USTC_03_UpsellV2projectToLocationAccountTest.Portfolio_USTC_03_00_AddingExternalProjectInPortfolioLocation" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_USTC_03_01_ValidateAddedProjectInPortfolioLocationList(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Added In LocationList Functionality");

		try {
			portfolio.ValidateAddedInLocationList(SheetName, rowNum);
			
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_USTC_03_UpsellV2projectToLocationAccountTest.Portfolio_USTC_03_01_ValidateAddedProjectInPortfolioLocationList" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_USTC_03_02_V2OptnHsrRatingMigratedDocument(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Added In LocationList Functionality");

		try {
			rc.SignOut();
			login.Login();
			portfolio.SearchPortfolioById(SheetName,rowNum);
			portfolio.ValidatePortfolioAccountBannerInDashboard(SheetName, rowNum, "141");
			portfolio.ValidateLocationAccountBannerInDashboard(SheetName, rowNum,"Performance Test OR Sensor Data","142");
			portfolio.MigratedDocumentsTab();
			portfolio.ratingMigratedDocumentsCount("141");
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_USTC_03_UpsellV2projectToLocationAccountTest.Portfolio_USTC_03_02_V2OptnHsrRatingMigratedDocument" })
	@Parameters({ "SheetName","rowNum"})
	public void Portfolio_USTC_03_03_ValidateTargetedAndAwardedPoints(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Navigate Location Account And Validate Dashboard Banner Functionality");

		try {
		
			rc.clickScorecard();
			v2project.ValidateTargetedAndAwardedPoints("V2ProjectA08.1TargetedPoints0.5", "V2ProjectA08.1TargetedPoints1",
			"V2ProjectA08.1AwardedPoints0.5", "V2ProjectA08.1AwardedPoints1");
			v2project.ValidateTargetedAndAwardedPoints("V2ProjectOneN08.1TargetedPoints1", "V2ProjectTwoN08.1TargetedPoints1",
			"V2ProjectOneN08.1AwardedPoints1", "V2ProjectTwoN08.1AwardedPoints1");
			
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}	
	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_USTC_03_UpsellV2projectToLocationAccountTest.Portfolio_USTC_03_03_ValidateTargetedAndAwardedPoints" })
	@Parameters({ "SheetName","rowNum"})
	public void Portfolio_USTC_03_04_ValidateNewTagAbsenceInDocumentsTab(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate new tag absence in Documents Tab");

		try {
			portfolio.SearchPortfolioById(SheetName,rowNum);
			portfolio.clickDocument();
			portfolio.ValidateNewTagAbsenceInDocumentsTab();
			
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
