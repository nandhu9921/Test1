package com.Well.testcases.V2Project;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class V1_TC_44_V1ProjectRecertificationTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_43_EquityAchievementTest.V1_TC_43_00_V1ProjectEquityAchievement" })
	@Parameters({"SheetName","rowNum", "Country"})
	public void V1_TC_44_00_V1ProjectRecertification(String SheetName, int rowNum, String Country) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate V1 Project Recertification Functionality");
		try {
			login.AdminLogin();
			v2project.AdminSearchById(SheetName, rowNum);
			v2project.validateV2PilotNonCoreProjectAdminCertification(SheetName, rowNum);
			
		} 
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_44_00_V1ProjectRecertification.V1_TC_44_00_V1ProjectRecertification" })
	@Parameters({"SheetName","rowNum", "Country"})
	public void V1_TC_44_01_V1ProjectRecertificationRatingAccessHsr(String SheetName, int rowNum, String Country) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate V1 Project Recertification Functionality");
		try {
			v2project.AdminSearchById(SheetName, rowNum);
			v2project.accessRecertification("HealthSafetyTab","V2projectRatingFeatureName");
		} 
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_44_00_V1ProjectRecertification.V1_TC_44_01_V1ProjectRecertificationRatingAccessHsr" })
	@Parameters({"SheetName","rowNum", "Country"})
	public void V1_TC_44_01_V1ProjectRecertificationRatingAccessWpr(String SheetName, int rowNum, String Country) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate V1 Project Recertification Functionality");
		try {
			v2project.AdminSearchById(SheetName, rowNum);
			v2project.accessRecertification("PerformanceTab","V2projectRatingFeatureName");
			
		} 
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
