package com.Well.testcases.Portfolio;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class Portfolio_USTC_05_UpsellV2ProjectNotRegisteredAndRegisteredInProgressToPortfolioAccountTest extends BaseClass {

	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_04_UpsellV2PilotProjectToPortfolioAccountTest.Portfolio_USTC_04_06_HsrAssignedTask"  })
	@Parameters({ "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName","Type" })
	public void Portfolio_USTC_05_00_NotRegisteredV2Project(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName, String Type) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register NotRegistered Project Functionality");
		try {
			Boolean ProType;
			if(Type.equalsIgnoreCase("WELLCore")) {
				ProType =true;
			}
			else {
				ProType =false;
			}
			SheetName = "V2Project";
			rowNum =12;
			commonAPI.StoreAnyProject(SheetName, rowNum, Country, ProjectType, Upload, API, "SingleAsset",ImportLocation, "AutomationV2UpsellNotRegisteredV2project", ProType);
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_05_UpsellV2ProjectNotRegisteredAndRegisteredInProgressToPortfolioAccountTest.Portfolio_USTC_05_00_NotRegisteredV2Project"  })
	@Parameters({ "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName","Type" })
	public void Portfolio_USTC_05_01_RegistrationInProgressV2Project(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName, String Type) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Register RegistrationInProgress V2Project Functionality");

		try {
			Boolean ProType;
			if(Type.equalsIgnoreCase("WELLCore")) {
				ProType =true;
			}
			else {
				ProType =false;
			}
			SheetName = "V2Project";
			rowNum =13;
	     	String Username = data.getCellData("Login", "UserName", 6);
			String Password = data.getCellData("Login", "Password", 6);
			commonAPI.StoreAnyProject(SheetName, rowNum, Country, ProjectType, Upload, API, "SingleAsset",ImportLocation, "AutomationV2UpsellRegistrationInProgressV2project", ProType);
			commonAPI.SignAgreementProject(SheetName, rowNum,  "SingleAsset");
			commonAPI.RegisterProject(SheetName, rowNum, Country, ProjectType, "SingleAsset");
			rc.SignOut();
			login.commonLogin(Username,Password,6);
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
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_05_UpsellV2ProjectNotRegisteredAndRegisteredInProgressToPortfolioAccountTest.Portfolio_USTC_05_01_RegistrationInProgressV2Project"  })
	@Parameters({ "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName","Type" })
	public void Portfolio_USTC_05_02_RegisterPortfolioAccount(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName, String Type) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");

		try {
			rowNum =12;
			commonAPI.StoreAnyProject(SheetName, rowNum, Country, ProjectType, Upload, API, "Portfolio",ImportLocation, ProjectName, false);
			commonAPI.SignAgreementProject(SheetName, rowNum, Commodity);
			commonAPI.RegisterProject(SheetName, rowNum, Country, ProjectType, "Portfolio");
			commonAPI.BillingProject(SheetName, rowNum, Country, ProjectType, "Portfolio");
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_05_UpsellV2ProjectNotRegisteredAndRegisteredInProgressToPortfolioAccountTest.Portfolio_USTC_05_02_RegisterPortfolioAccount"  })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_USTC_05_03_UpsellNotRegisteredByAddingExternalProject(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");

		try {
			int rowNum1 =12;
			login.AdminLogin();
			portfolio.AdminSearch(SheetName, rowNum1);
			SheetName = "V2Project";
			portfolio.AddingExternalProjectInPortfolioLocation(SheetName, rowNum1);
			portfolio.ValidateAddedInLocationList(SheetName, rowNum1);
			
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_05_UpsellV2ProjectNotRegisteredAndRegisteredInProgressToPortfolioAccountTest.Portfolio_USTC_05_03_UpsellNotRegisteredByAddingExternalProject"  })
	@Parameters({ "SheetName","rowNum","Country","ProjectType" })
	public void Portfolio_USTC_05_04_UpsellNotRegisteredDashboardInLocationAccount(String SheetName, int rowNum, String Country, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");

		try {
			int rowNum1 =12;
			commonAPI.UpdatePartOwnerInformationById("V2Project", rowNum1);
			commonAPI.updatePartProjectAddressById("V2Project", rowNum1);
			portfolio.navigateToUpsellLocationId(SheetName, rowNum1,"PortfolioLocationValidUpsellIdNA");
		    portfolio.ValidateBillingAfterUpsell(SheetName,rowNum1,Country);
			portfolio.validateDashboardInLocationAccount();
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_05_UpsellV2ProjectNotRegisteredAndRegisteredInProgressToPortfolioAccountTest.Portfolio_USTC_05_04_UpsellNotRegisteredDashboardInLocationAccount"  })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_USTC_05_05_UpsellRegistrationInProgressByAddingExternalProject(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");

		try {
			rowNum =12;
			login.AdminLogin();
			portfolio.AdminSearch(SheetName, rowNum);
			SheetName = "V2Project";
			int rowNum1 =13;
			portfolio.AddingExternalProjectInPortfolioLocation(SheetName, rowNum1);
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {  "com.Well.testcases.Portfolio.Portfolio_USTC_05_UpsellV2ProjectNotRegisteredAndRegisteredInProgressToPortfolioAccountTest.Portfolio_USTC_05_05_UpsellRegistrationInProgressByAddingExternalProject"  })
	@Parameters({ "SheetName","rowNum","Country" })
	public void Portfolio_USTC_05_06_UpsellRegistrationInProgressValidateDashboardInLocationAccount(String SheetName, int rowNum, String Country) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Register WELL At Scale Project Functionality");

		try {
			rowNum =12;
			commonAPI.UpdatePartOwnerInformationById("V2Project", rowNum);
			commonAPI.updatePartProjectAddressById("V2Project", rowNum);
			portfolio.navigateToUpsellLocationId(SheetName, rowNum,"PortfolioLocationValidUpsellIdRIP");
			portfolio.ValidateBillingAfterUpsell(SheetName,rowNum,Country);
			portfolio.validateDashboardInLocationAccount();
			
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}