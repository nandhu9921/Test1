package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class Portfolio_USTC_08_UpsellV1ProjectOptnRatingTest extends BaseClass {
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_USTC_05_UpsellV2ProjectNotRegisteredAndRegisteredInProgressToPortfolioAccountTest.Portfolio_USTC_05_06_UpsellRegistrationInProgressValidateDashboardInLocationAccount"  })
	@Parameters({ "Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName","Type" })
	public void Portfolio_USTC_08_00_RegisterV1Project(String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName, String Type) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Register WELL V1 Project Functionality");

		try {
			Boolean ProType;
			if(Type.equalsIgnoreCase("WELLCore")) {
				ProType =true;
			}
			else {
				ProType =false;
			}
			String SheetName = "V2Project";
			int rowNum =21;
			rc.SignOut();
			String Username = data.getCellData("Login", "UserName", 6);
			String Password = data.getCellData("Login", "Password", 6);
			login.commonLogin(Username,Password,6);
			commonAPI.StoreAnyProject(SheetName, rowNum, Country, "v1", Upload, API, "SingleAsset",ImportLocation, "AutomationV1ProjectOptnHsrUpsell", ProType);
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
	public void Portfolio_USTC_08_01_HsrV1ProjectOpt() throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Hsr Separate Review Cycle V2ProjectOpt Scorecard");
		try {
			String SheetName = "V2Project";
			int rowNum =21;
			v2project.V2ProjectOpt(SheetName, rowNum, "HealthSafetyTab");
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
	public void Portfolio_USTC_08_02_RegisterPortfolioAccount(String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");

		try {
			String SheetName = "Portfolio";
			int rowNum =15;
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
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_06_UpsellV2ProjectOptnRatingTest.Portfolio_USTC_06_04_AdminMarkProjectHsrV2ProjectAsAchieved"  })
	@Parameters({"Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName" })
	public void Portfolio_USTC_08_03_V1ProjectUpsellInPortfolioAccount(String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"V1Project Upsell In Portfolio Account Functionality");

		try {
			String SheetName = "Portfolio";
			int rowNum =15;
			login.AdminLogin();
			portfolio.AdminSearch(SheetName, rowNum);
			rowNum =20;
			portfolio.AddingExternalProjectInPortfolioLocation(SheetName, rowNum);
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}