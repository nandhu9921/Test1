package com.Well.testcases.Community;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Community_TC_07_ReviewTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Community.Community_TC_06_AgreementTest.Community_TC_06_00_CompleteCommunityAgreementByAPI" })
	@Parameters({ "SheetName","rowNum" })
	public void Community_TC_07_00_SubmitReviewAndValidateUnderReviewFilter(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Submit Review and Validate Under Review Filter");
		try {
			v2project.SubmitReviewV2Project(SheetName, rowNum,"Preliminary Precertification Review","ReviewViewButton");	
			community.SearchV2ProjectFilterByStatus(SheetName, rowNum, "Under Review");
			login.AdminLogin();
			v2project.ValidateFilterStatusByAdmin(SheetName, rowNum, "Under Review");
	        v2project.ClickSearchV2ProjectById(SheetName, rowNum);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Community.Community_TC_07_ReviewTest.Community_TC_07_00_SubmitReviewAndValidateUnderReviewFilter" })
	@Parameters({ "SheetName","rowNum"})
	public void Community_TC_07_01_AdminCompleteReviewCummunity(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Admin Complete Review Cummunity");
		try {
			community.AdminCompleteReviewCummunity(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}	
	
	@Test(dependsOnMethods = { "com.Well.testcases.Community.Community_TC_07_ReviewTest.Community_TC_07_01_AdminCompleteReviewCummunity" })
	@Parameters({ "SheetName","rowNum" })
	public void Community_TC_07_02_MarkAndValidatePrecertifiedFilter(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Mark and Validate Precertified Filter");
		try {
			v2project.CoreProjectMarkPreCertified(SheetName, rowNum);
	        v2project.ValidateFilterStatusByAdmin(SheetName, rowNum, "Precertified");
			rc.SignOut();
			login.Login();
			community.SearchV2ProjectFilterByStatus(SheetName, rowNum, "Precertified");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Community.Community_TC_07_ReviewTest.Community_TC_07_02_MarkAndValidatePrecertifiedFilter" })
	@Parameters({ "SheetName","rowNum" })
	public void Community_TC_07_03_ValidateCertifiedFilter(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Certified Filter");
		try {
			login.AdminLogin();
			community.AdminSearchById(SheetName, rowNum);                 
			community.MarkCertifiedByAdminUser(SheetName, rowNum);
			v2project.ValidateFilterStatusByAdmin(SheetName, rowNum, "Certified");			
			rc.SignOut();
			login.Login();
			community.SearchV2ProjectFilterByStatus(SheetName, rowNum, "Certified");
			v2project.ClickSearchV2ProjectById(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Community.Community_TC_07_ReviewTest.Community_TC_07_03_ValidateCertifiedFilter" })
	@Parameters({ "SheetName","rowNum" })
	public void Community_TC_07_04_ValidateDashboardTimelineModule(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Dashboard Timeline Module");
		try {
			v2project.ValidateDashboardTimelineModule();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
