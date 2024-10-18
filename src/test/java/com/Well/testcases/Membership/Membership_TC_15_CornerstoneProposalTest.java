package com.Well.testcases.Membership;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Membership_TC_15_CornerstoneProposalTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_08_ProfileTest.Membership_TC_08_Profile" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_00_SubmitProposal(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
			membership.MPProposalTabNavigate();
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_15_CornerstoneProposalTest.Membership_TC_15_00_SubmitProposal" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_01_FillFormProposal(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
			membership.fillProposalpage(SheetName, rowNum);
			membership.csthanksProposalPage();
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_15_CornerstoneProposalTest.Membership_TC_15_01_FillFormProposal" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_02_StoreProposalId(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
			membership.workersWithWellProposalValidate(SheetName, rowNum,"PENDING");
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_15_CornerstoneProposalTest.Membership_TC_15_02_StoreProposalId" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_03_ValidProposalStatus(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
			membership.validateProposalStatus(SheetName, rowNum,"Pending");
			membership.ProposalEditButton();
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_15_CornerstoneProposalTest.Membership_TC_15_03_ValidProposalStatus" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_04_AdminProposalNavigate(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
			login.AdminLogin();
			membership.AdminProposalNavigate(SheetName, rowNum,"PENDING");
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_15_CornerstoneProposalTest.Membership_TC_15_04_AdminProposalNavigate" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_05_AdminValidateProposalStatus(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
			membership.validateProposalStatus(SheetName, rowNum,"Pending");
			}	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_15_CornerstoneProposalTest.Membership_TC_15_05_AdminValidateProposalStatus" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_06_ProposalAddReviewResult(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
			membership.AdminAddReviewResult(SheetName, rowNum);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_15_CornerstoneProposalTest.Membership_TC_15_06_ProposalAddReviewResult" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_07_ValidateAdminProposalReviewStatus(String SheetName,int rowNum) {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
			membership.AdminProposalNavigate(SheetName, rowNum,"REVIEWED");
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_15_CornerstoneProposalTest.Membership_TC_15_07_ValidateAdminProposalReviewStatus" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_08_AuthValidateProposalStatus(String SheetName,int rowNum) {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
			rc.SignOut();
			login.Login();
			membership.navigateAuthsubmitProposal();
			membership.workersWithWellProposalValidate(SheetName, rowNum,"REVIEWED");
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_15_CornerstoneProposalTest.Membership_TC_15_08_AuthValidateProposalStatus" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_09_AuthValidateProposalStatus(String SheetName,int rowNum) {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
			membership.validateProposalReviewResult(SheetName, rowNum);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_15_CornerstoneProposalTest.Membership_TC_15_09_AuthValidateProposalStatus" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_10_SecondCornerstoneProposal(String SheetName,int rowNum) {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
				membership.proposalFlow(SheetName, rowNum);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_15_CornerstoneProposalTest.Membership_TC_15_10_SecondCornerstoneProposal" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_15_11_ThirdCornerstoneProposalWithBilling(String SheetName,int rowNum) {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			if (TestNGTestName.contains("CS")) {
			membership.navigateAuthsubmitProposal();
			membership.MPProposalTabNavigate();
			membership.fillProposalpage(SheetName, rowNum);
			rc.Billing(SheetName, rowNum);
			membership.csthanksProposalPage();
			membership.workersWithWellProposalValidate(SheetName, rowNum,"PENDING");
			login.AdminLogin();
			membership.AdminProposalNavigate(SheetName, rowNum,"PENDING");
			membership.validateProposalStatus(SheetName, rowNum,"Pending");
			membership.AdminAddReviewResult(SheetName, rowNum);
			rc.SignOut();
			login.Login();
			membership.navigateAuthsubmitProposal();
			membership.workersWithWellProposalValidate(SheetName, rowNum,"REVIEWED");
			membership.validateProposalReviewResult(SheetName, rowNum);
			membership.navigateAuthsubmitProposal();
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
