package com.Well.testcases.Performance;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Performance_TC_03_SubscribeAndStoreIdTest extends BaseClass {

	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_02_RegisterPerformanceTest.Performance_TC_02_RegisterPerformance" })
	@Parameters({"SheetName","rowNum","Country","ProjectType","API","Commodity","Upload" })
	public void Performance_TC_03_00_SubscribeAndStoreId(String SheetName,int rowNum,String Country, String ProjectType,String API,String Commodity, String Upload) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Subscribe the Performance Functionality");

		try {
			if (TestNGTestName.equalsIgnoreCase("WELL-WPR-Renewal-MulipleYesLocation")) {
					commonAPI.BillingProject(SheetName, rowNum, Country, ProjectType, Commodity);	
		    }
			else if (API.equalsIgnoreCase("true") && (TestNGTestName.contains("MulipleNoLocation"))) {
				performance.SearchPerformanceByIDAndCompleteTheForm(SheetName,rowNum);
				rc.BillingThroughCouponCode(SheetName, rowNum);	
				
			}
			else {
				
				rc.Billing(SheetName, rowNum);
			}
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_03_SubscribeAndStoreIdTest.Performance_TC_03_00_SubscribeAndStoreId" })
	@Parameters({ "SheetName","rowNum","API" })
	public void Performance_TC_03_01_StoreId(String SheetName,int rowNum, String API) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Store Performance Id");

		try {
			if (API.equalsIgnoreCase("false")) {
			performance.StoreIdPerformance(SheetName, rowNum);	
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_03_SubscribeAndStoreIdTest.Performance_TC_03_01_StoreId" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_03_02_ValidateRegistered(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate filter Registered");
		try {
			if (TestNGTestName.contains("Main")) {	
			performance.SearchPerformanceProjectId(SheetName, rowNum);	
			performance.SearchPerformanceFilterByStatus(SheetName, rowNum, "Registered");
			performance.ClickPerformanceProjectId(SheetName, rowNum);	
			login.AdminLogin();
			performance.AdminSearchPerformanceFilterByStatus(SheetName, rowNum, "Registered");
			rc.SignOut();
			login.Login();
			}
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
