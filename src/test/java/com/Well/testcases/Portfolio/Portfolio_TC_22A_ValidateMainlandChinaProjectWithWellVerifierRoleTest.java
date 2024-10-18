package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_22A_ValidateMainlandChinaProjectWithWellVerifierRoleTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22_ValidateTeamPTAccessTest.Portfolio_TC_22_07_ProjectAdminUpdateAndValidateLocationAccount" })
	@Parameters({ "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName" })
	public void Portfolio_TC_22A_00_RegisterMainlandChinaProjectWithWellVerifierRole(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register Portfolio Mainland China Project");
		try {
			login.AdminLogin();
			int rowNumber = 14;
			portfolio.RegisterPortfolio(SheetName, rowNumber, Engagement_level, "MainlandChina", "AutomationV2PortfolioMainlandChina");
		
		    } catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22A_ValidateMainlandChinaProjectWithWellVerifierRoleTest.Portfolio_TC_22A_00_RegisterMainlandChinaProjectWithWellVerifierRole" })
	@Parameters({ "SheetName","rowNum","Country","ProjectType","API","Commodity"})
	public void Portfolio_TC_22A_01_SubscribePortfolio(String SheetName,int rowNum,String Country,String ProjectType, String API, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Portfolio Subscribe Functionality");
		try {
			int rowNumber = 14;
			portfolio.SubscribePortfolio(SheetName, rowNumber);
						
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}	
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22A_ValidateMainlandChinaProjectWithWellVerifierRoleTest.Portfolio_TC_22A_01_SubscribePortfolio" })
	@Parameters({ "SheetName","rowNum","Country","ProjectType","API","Commodity" })
	public void Portfolio_TC_22A_02_BillingPortfolio(String SheetName,int rowNum,String Country, String ProjectType, String API, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Portfolio Card Payment Functionality");
		try {
			 int rowNumber = 14;
			 rc.AgreementMainlandChinaProject(SheetName, rowNumber);
			 rc.ResetBillingMainlandChinaProject(SheetName, rowNumber, "2500");
			 rc.BillingMainlandChinaProject(SheetName, rowNumber, "2500");
			 rc.ValidateMainlandChinaProjectReceipt(SheetName, rowNumber, "PAID", "Resume - Registration Receipt", "2,500");
			 rc.EditAndUpdateBillingMainlandChinaProject(SheetName, rowNumber, "3500");			 
			 portfolio.ValidatePortfolioProjectStatus(SheetName, rowNumber);
			 v2project.ValidateMainlandChinaBannerText(); 
		    } catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22A_ValidateMainlandChinaProjectWithWellVerifierRoleTest.Portfolio_TC_22A_02_BillingPortfolio" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_22A_03_AddTeamMemberPortfolio(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Add Team Member Functionality");
		try {
			int rowNumber = 14;
			portfolio.TeamPermissionLevel(SheetName, rowNumber);
			rc.SignOut();
			rc.teamLogin(SheetName, rowNumber, "TeamMemberEmail");
			portfolio.SearchPortfolioById(SheetName, rowNumber);

			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22A_ValidateMainlandChinaProjectWithWellVerifierRoleTest.Portfolio_TC_22A_03_AddTeamMemberPortfolio" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_22A_04_ValidateMainlandChinaProjectWithTeamMemberRole(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Mainland China Project With Team Member Role");
		try {
			int rowNumber = 14;
			v2project.ValidateMainlandChinaProjectWithTeamMemberRole(SheetName, rowNumber);

			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	

	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22A_ValidateMainlandChinaProjectWithWellVerifierRoleTest.Portfolio_TC_22A_04_ValidateMainlandChinaProjectWithTeamMemberRole" })
	@Parameters({ "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName" })
	public void Portfolio_TC_22A_05_ValidateUpdateForProjectsWithLocationsInChinaModalDuringRegistration(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register Portfolio Mainland China Project");
		try {
			rc.SignOut();
			login.Login();
			portfolio.ValidateUpdateForProjectsWithLocationsInChinaModalDuringRegistration(SheetName, rowNum, Engagement_level, "MainlandChina", ProjectName);
		
		    } catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}