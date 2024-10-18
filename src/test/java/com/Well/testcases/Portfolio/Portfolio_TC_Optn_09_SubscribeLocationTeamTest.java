package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_Optn_09_SubscribeLocationTeamTest extends BaseClass {
	
	@Test//(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_08_SubscribeLocationPostReviewTest.PortfolioSubscribeLocation_TC_08_00_AchieveReview" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Portfolio_TC_Optn_09_00_SubscribeLocationTeam(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			portfolio.SearchPortfolioById(SheetName,rowNum);
			portfolio.clickOnTeamPortfolio(SheetName, rowNum);
			rc.inviteTeam();
			portfolio.teamPortfolio(SheetName, rowNum);
		    portfolio.deleteAddedTeamMemberPortfolio(SheetName, rowNum);
		    portfolio.TeamPermissionLevel(SheetName, rowNum);	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_09_SubscribeLocationTeamTest.Portfolio_TC_Optn_09_00_SubscribeLocationTeam" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Portfolio_TC_Optn_09_01_SubscribeLocationValidateRestrictAccessTeamMember(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			rc.SignOut();
			rc.teamLogin(SheetName, rowNum,"TeamMemberEmail");
			pfu.NavigateAddedLocation(SheetName, rowNum);
			v2project.validateSubmitReviewV2Project();
			v2project.BuildScorecardV2ProjectById(SheetName, rowNum);
			v2project.ValidateUploadDocV2ProjectInsideScorecard(SheetName, rowNum,"Prohibit Indoor Smoking","Letter of Assurance – Owner");
			v2project.validPrivateCommentsV2ProjectAsTeam("Meet Thresholds for Particulate Matter");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_09_SubscribeLocationTeamTest.Portfolio_TC_Optn_09_01_SubscribeLocationValidateRestrictAccessTeamMember" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Portfolio_TC_Optn_09_02_SubscribeLocationValidateRestrictAccessTeamManager(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			rc.SignOut();
			rc.teamLogin(SheetName, rowNum,"TeamManagerEmail");
			pfu.NavigateAddedLocation(SheetName, rowNum);
			rc.inviteTeam();
			portfolio.teamPortfolio(SheetName, rowNum);
		    portfolio.deleteAddedTeamMemberPortfolio(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_09_SubscribeLocationTeamTest.Portfolio_TC_Optn_09_02_SubscribeLocationValidateRestrictAccessTeamManager" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Portfolio_TC_Optn_09_03_SubscribeLocationValidateRestrictAccessAdmin(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			rc.SignOut();
			rc.teamLogin(SheetName, rowNum,"Email");
			pfu.NavigateAddedLocation(SheetName, rowNum);
			rc.inviteTeam();
			portfolio.teamPortfolio(SheetName, rowNum);
		    portfolio.deleteAddedTeamMemberPortfolio(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	}

