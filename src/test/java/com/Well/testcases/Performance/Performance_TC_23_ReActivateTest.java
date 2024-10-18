package com.Well.testcases.Performance;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;


public class Performance_TC_23_ReActivateTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_16_MarkAcheivedToReviewDocument" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_23_00_RenewalEnhanceChangeDateAsAdmin(String SheetName,int rowNum) throws IOException {
		
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Renewal Enhance project functionality");
		try {
			login.AdminLogin();
			performance.AdminWprSearch(SheetName, rowNum);
			performance.editAdminAwardDate("EditAchievedStatus","WprAchievementAdminTab");
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_23_ReActivateTest.Performance_TC_23_00_RenewalEnhanceChangeDateAsAdmin" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_23_01_PerformanceValidateDashboardBeforeReActivate(String SheetName,int rowNum) throws IOException {
		
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Performance Validate Dashboard Before ReActivate");
		try {
			performance.PerformanceValidateDashboardBeforeReActivate();
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_23_ReActivateTest.Performance_TC_23_01_PerformanceValidateDashboardBeforeReActivate" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_23_02_PerformanceMarkExpiredAndValidateDashboardBeforeReActivate(String SheetName,int rowNum) throws IOException {
		
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Performance Validate Dashboard Before ReActivate");
		try {
			performance.editAdminAwardDate("EditAchievedStatus","WprAchievementAdminTab");
			performance.PerformanceValidateReActivateDashboard();
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_23_ReActivateTest.Performance_TC_23_02_PerformanceMarkExpiredAndValidateDashboardBeforeReActivate" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_23_03_PerformanceMarkExpiredAndValidateDashboardBeforeReActivate(String SheetName,int rowNum) throws IOException {
		
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Performance Mark Expired and Validate Dashboard before ReActivate");
		try {
			rc.SignOut();
			login.Login();
			performance.SearchPerformanceFilterStatus(SheetName, rowNum);
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_23_ReActivateTest.Performance_TC_23_03_PerformanceMarkExpiredAndValidateDashboardBeforeReActivate" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_23_04_PerformanceNavigateReActivateAsNo(String SheetName,int rowNum) throws IOException {
		
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Performance Navigate ReActivate as No");
		try {
			performance.ClickReactivateButton();
			rc.noRenewal(SheetName,rowNum,"4875");
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_23_ReActivateTest.Performance_TC_23_04_PerformanceNavigateReActivateAsNo" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_23_05_PerformanceValidateConfirmYourRenewalFeesPage(String SheetName,int rowNum) throws IOException {
		
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Performance Navigate ReActivate as No");
		try {			
			performance.PerformanceValidateConfirmYourRenewalFeesPage();
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}  
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_23_ReActivateTest.Performance_TC_23_05_PerformanceValidateConfirmYourRenewalFeesPage" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_23_06_ValidateDashboardAfterRenewal(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Dashboard After Renewal");
		try {			
			rc.clickDashboard();
			performance.ValidateDashboardRenewalTextAndButtons("RenewalDashboardText", "Complete Renewal Scorecard",
			"RenewalDashboardSecondText", "Click the button below to access your renewal scorecard to confirm or upload documents.",
			"ValidateDashboardRenewalButton", " Access your Renewal Scorecard");
			performance.ValidateDashboardHeaderTextAndCards();
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_23_ReActivateTest.Performance_TC_23_06_ValidateDashboardAfterRenewal" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_23_07_PerformanceValidateReactivationBilling(String SheetName,int rowNum) throws IOException {
		
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Performance Validate Reactivation Billing");
		try {
			login.AdminLogin();
			performance.AdminWprSearch(SheetName, rowNum);
			performance.PerformanceValidateReactivationBilling();
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}