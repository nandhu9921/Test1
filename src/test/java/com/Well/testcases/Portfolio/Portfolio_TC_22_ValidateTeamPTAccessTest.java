package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class  Portfolio_TC_22_ValidateTeamPTAccessTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_13B_AddingTeamTest.Portfolio_TC_13B_AddingTeam" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_22_00_ValidateRestrictAccessPTFA(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Restrict Access PT FullAccess in DocumentLibrary/Scorecard");
		try {
			rc.SignOut();
			rc.teamLogin(SheetName, rowNum,"PTFTEmail");
			portfolio.SearchPortfolioById(SheetName,rowNum);
			portfolio.clickDocument();
			portfolio.ValidateDocumentInDocumentLibrary(SheetName, rowNum, "true");
			portfolio.ValidatePTAInsideScorecard(SheetName, rowNum,"Prohibit Indoor Smoking","false");
			portfolio.ValidateLocationAccountForTeamMemberAndPerformanceTestingAgent();
			
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22_ValidateTeamPTAccessTest.Portfolio_TC_22_00_ValidateRestrictAccessPTFA" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_22_01_ValidateRestrictAccessLtd(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Restrict Access PT LimitedAccess in DocumentLibrary/Scorecard");
		try {
			
			rc.SignOut();
			rc.teamLogin(SheetName, rowNum,"PTLTDEmail");
			portfolio.SearchPortfolioById(SheetName,rowNum);
			portfolio.clickDocument();
			portfolio.ValidateDocumentInDocumentLibrary(SheetName, rowNum, "true");
			portfolio.ValidatePTAInsideScorecard(SheetName, rowNum,"Prohibit Indoor Smoking","false");
			
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22_ValidateTeamPTAccessTest.Portfolio_TC_22_01_ValidateRestrictAccessLtd" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_22_02_ValidateRestrictAccessTeamMember(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Restrict Access PT TeamMember in DocumentLibrary/Scorecard");
		try {
			
			rc.SignOut();
			rc.teamLogin(SheetName, rowNum,"TeamMemberEmail");
			portfolio.SearchPortfolioById(SheetName,rowNum);
			portfolio.clickDocument();
			portfolio.ValidateDocumentInDocumentLibrary(SheetName, rowNum, "true");
			portfolio.ValidatePTAInsideScorecard(SheetName, rowNum,"Prohibit Indoor Smoking","false");
			portfolio.ValidateLocationAccountForTeamMemberAndPerformanceTestingAgent();
		//	portfolio.deleteVaidateInTeamUser(SheetName,rowNum);
		//	portfolio.teamReviewUserAccess();
		//	portfolio.validateNAAdminFieldInTeamAccess();
			
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22_ValidateTeamPTAccessTest.Portfolio_TC_22_02_ValidateRestrictAccessTeamMember" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_22_03_ValidateRestrictAccessAdminInDocumentLibrary(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Restrict Access PT Admin in DocumentLibrary/Scorecard");
		try {
			
			rc.SignOut();
			rc.teamLogin(SheetName, rowNum,"Email");
			portfolio.AdminSearch(SheetName,rowNum);
			portfolio.clickDocument();
			portfolio.ValidateDocumentInDocumentLibrary(SheetName, rowNum, "false");
		
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22_ValidateTeamPTAccessTest.Portfolio_TC_22_03_ValidateRestrictAccessAdminInDocumentLibrary" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_22_04_ValidateRestrictAccessAdminInScorecard(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Restrict Access PT Admin in DocumentLibrary/Scorecard");
		try {
			
			portfolio.ValidatePTAInsideScorecard(SheetName, rowNum,"Meet Thresholds for Inorganic Gases","true");
			
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22_ValidateTeamPTAccessTest.Portfolio_TC_22_04_ValidateRestrictAccessAdminInScorecard" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_22_05_ValidateRestrictAccessTeamManagerInDocument(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Restrict Access PT TeamManager in DocumentLibrary/Scorecard");
		try {
			
			rc.SignOut();
			rc.teamLogin(SheetName, rowNum,"TeamManagerEmail");
			portfolio.SearchPortfolioById(SheetName,rowNum);
			portfolio.clickDocument();
			portfolio.ValidateDocumentInDocumentLibrary();
//			portfolio.clickOnTeamPortfolio(SheetName, rowNum);
//			portfolio.teamPortfolio(SheetName, rowNum);
//			performance.EditTeamMemberRole(SheetName, rowNum);
//			portfolio.deleteAddedTeamMemberPortfolio(SheetName, rowNum);
//			portfolio.validateNAAdminFieldInTeamAccess();
//			rc.SignOut();
//			login.Login();
//			portfolio.SearchPortfolioById(SheetName,rowNum);
//			portfolio.SubmitReviewDocument(SheetName, rowNum);
//			rc.SignOut();
//			rc.teamLogin(SheetName, rowNum,"TeamManagerEmail");
//			portfolio.SearchPortfolioById(SheetName,rowNum);
//			equity.locationDisable();
//			login.AdminLogin();
//			portfolio.AdminCompleteReview(SheetName, rowNum);
//			rc.SignOut();
//			rc.teamLogin(SheetName, rowNum,"TeamManagerEmail");
//			portfolio.SearchPortfolioById(SheetName,rowNum);
			
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22_ValidateTeamPTAccessTest.Portfolio_TC_22_05_ValidateRestrictAccessTeamManagerInDocument" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_22_06_ValidateRestrictAccessTeamManagerV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Restrict Access PT TeamManager in DocumentLibrary/Scorecard");
		try {
			portfolio.ValidatePTAInsideScorecard(SheetName, rowNum,"Prohibit Indoor Smoking","true");
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_22_ValidateTeamPTAccessTest.Portfolio_TC_22_06_ValidateRestrictAccessTeamManagerV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_22_07_ProjectAdminUpdateAndValidateLocationAccount(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"ProjectAdmin Update and Validate LocationAccount");
		try {
			login.AdminLogin();
			portfolio.AdminSearch(SheetName, rowNum);
			portfolio.ProjectAdminUpdateLocationAccount(SheetName, rowNum);
			portfolio.ProjectAdminValidateLocationAccount();
			
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
