package com.Well.testcases.Membership;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Membership_TC_14_NoMembershipProposalTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.login.TC_01_LoginTest.TC_01_Login" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_14_00_SubmitProposal(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			membership.submitProposal(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_14_NoMembershipProposalTest.Membership_TC_14_00_SubmitProposal" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_14_01_FillProposal(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			membership.fillProposalpage(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_14_NoMembershipProposalTest.Membership_TC_14_01_FillProposal" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_14_02_BillingProposal(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			membership.billingProposal(SheetName, rowNum);
			membership.thanksProposalPage();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_14_NoMembershipProposalTest.Membership_TC_14_02_BillingProposal" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_14_03_StoreProposalId(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			membership.validateTab(SheetName, rowNum);
			membership.workersWithWellProposalValidate(SheetName, rowNum,"PENDING");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_14_NoMembershipProposalTest.Membership_TC_14_03_StoreProposalId" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_14_04_ValidateProposalStatus(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			
			membership.validateProposalStatus(SheetName, rowNum,"Pending");
			membership.ProposalEditButton();
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_14_NoMembershipProposalTest.Membership_TC_14_04_ValidateProposalStatus" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_14_05_AdminProposalNavigate(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			login.AdminLogin();
			membership.AdminProposalNavigate(SheetName, rowNum,"PENDING");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_14_NoMembershipProposalTest.Membership_TC_14_05_AdminProposalNavigate" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_14_06_AdminValidateProposalStatus(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			membership.validateProposalStatus(SheetName, rowNum,"Pending");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_14_NoMembershipProposalTest.Membership_TC_14_06_AdminValidateProposalStatus" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_14_07_ProposalAddReviewResult(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			membership.AdminAddReviewResult(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_14_NoMembershipProposalTest.Membership_TC_14_07_ProposalAddReviewResult" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_14_08_ValidateAdminProposalReviewStatus(String SheetName,int rowNum) {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			membership.AdminProposalNavigate(SheetName, rowNum,"REVIEWED");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_14_NoMembershipProposalTest.Membership_TC_14_08_ValidateAdminProposalReviewStatus" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_14_09_AuthValidateProposalStatus(String SheetName,int rowNum) {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			rc.SignOut();
			login.Login();
			membership.navigateAuthsubmitProposal();
			membership.workersWithWellProposalValidate(SheetName, rowNum,"REVIEWED");

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_14_NoMembershipProposalTest.Membership_TC_14_09_AuthValidateProposalStatus" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_14_10_AuthValidateProposalStatus(String SheetName,int rowNum) {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			membership.validateProposalReviewResult(SheetName, rowNum);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
