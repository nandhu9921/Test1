package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class Portfolio_USTC_06_UpsellV2ProjectOptnRatingTest extends BaseClass {
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_USTC_05_UpsellV2ProjectNotRegisteredAndRegisteredInProgressToPortfolioAccountTest.Portfolio_USTC_05_06_UpsellRegistrationInProgressValidateDashboardInLocationAccount"  })
	@Parameters({ "Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName","Type" })
	public void Portfolio_USTC_06_00_RegisterV2Project(String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName, String Type) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");

		try {
			Boolean ProType;
			if(Type.equalsIgnoreCase("WELLCore")) {
				ProType =true;
			}
			else {
				ProType =false;
			}
			String SheetName = "V2Project";
			int rowNum =18;
			rc.SignOut();
			String Username = data.getCellData("Login", "UserName", 6);
			String Password = data.getCellData("Login", "Password", 6);
			login.commonLogin(Username,Password,6);
			commonAPI.StoreAnyProject(SheetName, rowNum, Country, ProjectType, Upload, API, "SingleAsset",ImportLocation, "AutomationV2ProjectOptnHsrUpsell", ProType);
			commonAPI.SignAgreementProject(SheetName, rowNum,  "SingleAsset");
			commonAPI.RegisterProject(SheetName, rowNum, Country, ProjectType, "SingleAsset");
			commonAPI.BillingProject(SheetName, rowNum, Country, ProjectType, "SingleAsset");
			CommonMethod.refreshBrowser();
			v2project.SearchV2ProjectById(SheetName, rowNum);	
			commonAPI.DatePickerAPI(SheetName, rowNum);
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}	
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_06_UpsellV2ProjectOptnRatingTest.Portfolio_USTC_06_00_RegisterV2Project"  })
	public void Portfolio_USTC_06_01_HsrSeparateReviewCycleV2ProjectOpt() throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Hsr Separate Review Cycle V2ProjectOpt Scorecard");
		try {
			String SheetName = "V2Project";
			int rowNum =18;
			v2project.V2ProjectOpt(SheetName, rowNum, "HealthSafetyTab");
			v2project.SeparateReviewCycleV2ProjectOpt(SheetName, rowNum);	
			commonAPI.storeScorecardIdAPI(SheetName,rowNum, "ScorecardHsrOptnId","hsr_scorecard_id");
			commonAPI.fillScorecardAPI(SheetName,rowNum, "ScorecardHsrOptnId");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_06_UpsellV2ProjectOptnRatingTest.Portfolio_USTC_06_01_HsrSeparateReviewCycleV2ProjectOpt"  })
	public void Portfolio_USTC_06_02_HsrSubmitReviewV2ProjectSeperateReview() throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Submit V2Project Review by Admin User");
		try {
			String SheetName = "V2Project";
			int rowNum =18;
			v2project.SubmitReviewV2Project(SheetName, rowNum,"Preliminary Health-Safety Review","V2ProjectHsrSeperateReviewViewbtn");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_06_UpsellV2ProjectOptnRatingTest.Portfolio_USTC_06_02_HsrSubmitReviewV2ProjectSeperateReview"  })
    public void Portfolio_USTC_06_03_HsrRatingScorecardCompleteReviewV2Project() throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete V2Project Review by Admin User");
		try {
			String SheetName = "V2Project";
			int rowNum =18;
			login.AdminLogin();
			v2project.AdminSearchById(SheetName, rowNum);
			v2project.V2ProjectOpt(SheetName, rowNum, "HealthSafetyTab");
			rc.ScorecardLoading();
			v2project.featureAdminScorecardReviewAndSelectAdminReview(SheetName, rowNum, "Support Handwashing" ,"Achieved");
			v2project.AdminCompleteReviewV2Project(SheetName, rowNum, "Preliminary Health-Safety Review","V2ProjectHsrSeperateReviewViewbtn");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_06_UpsellV2ProjectOptnRatingTest.Portfolio_USTC_06_03_HsrRatingScorecardCompleteReviewV2Project"  })
    public void Portfolio_USTC_06_04_AdminMarkProjectHsrV2ProjectAsAchieved() throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete V2Project Review by Admin User");
		try {
			rc.MarkCertifiedByAdminUser();
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_06_UpsellV2ProjectOptnRatingTest.Portfolio_USTC_06_04_AdminMarkProjectHsrV2ProjectAsAchieved"  })
	@Parameters({"Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName" })
	public void Portfolio_USTC_06_05_RegisterPortfolioAccount(String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");

		try {
			String SheetName = "Portfolio";
			int rowNum =13;
			rc.SignOut();
			login.Login();
			commonAPI.StoreAnyProject(SheetName, rowNum, Country, ProjectType, Upload, API, "Portfolio",ImportLocation, "AutomationV2PortfolioUpsellV2projectOptnHsrToLocationAccount", false);
			CommonMethod.refreshBrowser();
			portfolio.SearchPortfolioById(SheetName,rowNum);
			commonAPI.SignAgreementProject(SheetName, rowNum, Commodity);
			commonAPI.RegisterProject(SheetName, rowNum, Country, ProjectType, "Portfolio");
			commonAPI.BillingProject(SheetName, rowNum, Country, ProjectType, "Portfolio");
			rc.SignOut();
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_USTC_06_UpsellV2ProjectOptnRatingTest.Portfolio_USTC_06_05_RegisterPortfolioAccount" })
	public void Portfolio_USTC_06_06_V2OptnHsrRatingAddingExternalProjectInPortfolioLocation() throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Adding ExternalProject In Portfolio Location Functionality");

		try {
			String SheetName = "Portfolio";
			int rowNum =13;
			login.AdminLogin();
			portfolio.AdminSearch(SheetName, rowNum);
			rowNum =18;
			portfolio.AddingExternalProjectInPortfolioLocation(SheetName, rowNum);
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_USTC_06_UpsellV2ProjectOptnRatingTest.Portfolio_USTC_06_06_V2OptnHsrRatingAddingExternalProjectInPortfolioLocation" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_USTC_06_07_V2OptnHsrRatingValidateAddedLocation(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Added In LocationList In Portfolio Location Functionality");

		try {	
			rowNum =18;
			portfolio.ValidateAddedInLocationList(SheetName, rowNum);
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_USTC_06_UpsellV2ProjectOptnRatingTest.Portfolio_USTC_06_07_V2OptnHsrRatingValidateAddedLocation" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_USTC_06_08_V2OptnHsrRatingMigratedDocument(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Hsr Rating MigratedDocument Functionality");

		try {
			rowNum =13;
			rc.SignOut();
			login.Login();
			portfolio.SearchPortfolioById(SheetName,rowNum);
			rowNum =18;
			portfolio.ValidatePortfolioAccountBannerInDashboard(SheetName, rowNum, "30");
			portfolio.ValidateLocationAccountBannerInDashboard(SheetName, rowNum,"Policy and/or Operations Schedule","29");	
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}