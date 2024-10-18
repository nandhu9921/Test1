package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V2_TC_08C_ValidateMainlandChinaProjectWithWellVerifierRoleV2ProjectTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08B_ValidateTeamAccessScorecardOrDocumentV2ProjectTest.V2_TC_08B_14_ValidateTeamMemberNotEditUser" })
	@Parameters({ "SheetName","rowNum", "Type", "Country" ,"Upload", "API","Commodity","ProjectType","ImportLocation","ProjectName" })
	public void V2_TC_08C_00_RegisterMainlandChinaProjectWithWellVerifierRole(String SheetName, int rowNum, String Type, String Country, String Upload, String API,
			String Commodity, String ProjectType, String ImportLocation, String ProjectName) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register WELL V2 Mainland China Project With Well Verifier Role");
		try {
			login.AdminLogin();
			int rowNumber = 19;
			v2project.RegisterV2Project(SheetName, rowNumber, Type, "MainlandChina", "AutomationV2ProjectMainlandChina");
		
		    } catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08C_ValidateMainlandChinaProjectWithWellVerifierRoleV2ProjectTest.V2_TC_08C_00_RegisterMainlandChinaProjectWithWellVerifierRole" })
	@Parameters({ "SheetName","rowNum","ProjectType","Country","is_Leed","API", "Commodity","Type" })
	public void V2_TC_08C_01_EnrollV2Project(String SheetName,int rowNum, String ProjectType, String Country,boolean is_Leed, String API, String Commodity, String Type) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Enroll V2Project Project");
		try {
			int rowNumber = 19;
			v2project.EnrollV2ProjectById(SheetName, rowNumber, Type, "MainlandChina", is_Leed);
		
		    } catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08C_ValidateMainlandChinaProjectWithWellVerifierRoleV2ProjectTest.V2_TC_08C_01_EnrollV2Project" })
	@Parameters({ "SheetName","rowNum","Country","ProjectType","Type","API","Commodity" })
	public void V2_TC_08C_02_AgreementAndBillingMainlandChinaV2Project(String SheetName,int rowNum, String Country, String ProjectType, String Type, String API, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"V2Project Card Payment Functionality");
		try {
			 int rowNumber = 19;
			 rc.AgreementMainlandChinaProject(SheetName, rowNumber);
			 rc.ResetBillingMainlandChinaProject(SheetName, rowNumber, "2500");
			 rc.BillingMainlandChinaProject(SheetName, rowNumber, "2500");
			 rc.ValidateMainlandChinaProjectReceipt(SheetName, rowNumber, "PAID", "Resume - Registration Receipt", "2,500");
			 rc.EditAndUpdateBillingMainlandChinaProject(SheetName, rowNumber, "3500");			 
			 v2project.ValidateFilterStatusByAdmin(SheetName, rowNumber, "Registered");
			 v2project.ClickSearchV2ProjectById(SheetName, rowNumber);
			 v2project.ValidateMainlandChinaBannerText(); 
		    } catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08C_ValidateMainlandChinaProjectWithWellVerifierRoleV2ProjectTest.V2_TC_08C_02_AgreementAndBillingMainlandChinaV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_08C_03_AddTeamMemberV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"V2Project Add Team Member");
		try {
			int rowNumber = 19;
			rc.TeamPermissionLevel(SheetName, rowNumber);
			rc.SignOut();
			rc.teamLogin(SheetName, rowNumber,"TeamMemberEmail");
			v2project.SearchV2ProjectById(SheetName, rowNumber);

			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08C_ValidateMainlandChinaProjectWithWellVerifierRoleV2ProjectTest.V2_TC_08C_03_AddTeamMemberV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_08C_04_ValidateMainlandChinaProjectWithTeamMemberRole(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Mainland China Project With Team Member Role");
		try {
			int rowNumber = 19;
			v2project.ValidateMainlandChinaProjectWithTeamMemberRole(SheetName, rowNumber);

			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08C_ValidateMainlandChinaProjectWithWellVerifierRoleV2ProjectTest.V2_TC_08C_04_ValidateMainlandChinaProjectWithTeamMemberRole" })
	@Parameters({ "SheetName","rowNum", "Type", "Country" ,"Upload", "API","Commodity","ProjectType","ImportLocation","ProjectName" })
	public void V2_TC_08C_05_ValidateUpdateForProjectsWithLocationsInChinaModalDuringRegistration(String SheetName, int rowNum, String Type, String Country, String Upload, String API,
			String Commodity, String ProjectType, String ImportLocation, String ProjectName) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Mainland China Project With Team Member Role");
		try {
			int rowNumber = 19;
			rc.SignOut();
			login.Login();
			v2project.ValidateUpdateForProjectsWithLocationsInChinaModalDuringRegistration(SheetName, rowNumber, Type, "MainlandChina", ProjectName);
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08C_ValidateMainlandChinaProjectWithWellVerifierRoleV2ProjectTest.V2_TC_08C_05_ValidateUpdateForProjectsWithLocationsInChinaModalDuringRegistration" })
	@Parameters({ "SheetName","rowNum", "Type", "Country" ,"Upload", "API","Commodity","ProjectType","ImportLocation","ProjectName" })
	public void V2_TC_08C_06_ValidateMainlandChinaAfterCertBannerText(String SheetName, int rowNum, String Type, String Country, String Upload, String API,
			String Commodity, String ProjectType, String ImportLocation, String ProjectName) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Mainland China Project With Team Member Role");
		try {
			int rowNumber = 19;
			login.AdminLogin();
			v2project.AdminSearchById(SheetName, rowNumber);	
			portfolio.editAdminAsCertification();
			v2project.ValidateMainlandChinaAfterCertBannerText();
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
