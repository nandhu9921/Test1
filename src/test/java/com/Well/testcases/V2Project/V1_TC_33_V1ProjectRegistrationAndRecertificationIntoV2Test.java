package com.Well.testcases.V2Project;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class V1_TC_33_V1ProjectRegistrationAndRecertificationIntoV2Test extends BaseClass { 

	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.login.TC_01_LoginTest.TC_01_Login" })
	@Parameters({ "SheetName","rowNum", "Type", "Country" ,"Upload", "API","Commodity","ProjectType","ProjectStandard","ProjectName"})
	public void V1_TC_33_00_RegisterV1ProjectOffice(String SheetName, int rowNum, String Type, String Country, String Upload, String API,
			String Commodity, String ProjectType, String ProjectStandard, String ProjectName) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register WELL V2 Certification Project Functionality");
		try {
			Boolean ProType;
			if(Type.equalsIgnoreCase("WELLCore")) {
				ProType =true;
			}
			else {
				ProType =false;
			}
			commonAPI.StoreV1Project(SheetName, rowNum, Country, ProjectType, Upload, API, Commodity, ProjectStandard,ProjectName, ProType);
			commonAPI.BillingProject(SheetName, rowNum, Country, ProjectType, Commodity);	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_33_V1ProjectRegistrationAndRecertificationIntoV2Test.V1_TC_33_00_RegisterV1ProjectOffice" })
	@Parameters({"SheetName","rowNum", "Country", "ProjectType"})
	public void V1_TC_33_01_V1ProjectOfficeRecertificationIntoV2Project(String SheetName, int rowNum, String Country, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Recertification Functionality");
		try {
			login.AdminLogin();
			v2project.AdminSearchById(SheetName, rowNum);
			v2project.V1ProjectRecertificationIntoV2Project(SheetName, rowNum);
			v2project.ValidateV1ProjectRecertification(SheetName, rowNum, "V1ProjectValidateEditWorkingOnRecertification");
			v2project.ValidateUpdatedProjectArea();
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_33_V1ProjectRegistrationAndRecertificationIntoV2Test.V1_TC_33_01_V1ProjectOfficeRecertificationIntoV2Project" })
	@Parameters({ "SheetName","rowNum", "Type", "Country" ,"Upload", "API","Commodity","ProjectType","ProjectStandard","ProjectName"})
	public void V1_TC_33_02_RegisterV1ProjectRetail(String SheetName, int rowNum, String Type, String Country, String Upload, String API,
			String Commodity, String ProjectType, String ProjectStandard, String ProjectName) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register WELL V2 Certification Project Functionality");
		try {
			
			Boolean ProType;
			if(Type.equalsIgnoreCase("WELLCore")) {
				ProType =true;
			}
			else {
				ProType =false;
			}
			int rowNumber = 14;
			commonAPI.StoreV1Project(SheetName, rowNumber, Country, ProjectType, Upload, API, Commodity, "retail",ProjectName, ProType);
			commonAPI.BillingProject(SheetName, rowNumber, Country, ProjectType, Commodity);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_33_V1ProjectRegistrationAndRecertificationIntoV2Test.V1_TC_33_02_RegisterV1ProjectRetail" })
	@Parameters({"SheetName","rowNum", "Country", "ProjectType"})
	public void V1_TC_33_03_V1ProjectRetailRecertificationIntoV2Project(String SheetName, int rowNum, String Country, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Recertification Functionality");
		try {
			login.AdminLogin();
	        int rowNumber = 14;
			v2project.AdminSearchById(SheetName, rowNumber);
			v2project.V2PilotCoreProjectEditAndValidateRecertification(SheetName, rowNumber);
			v2project.ValidateV1ProjectRecertification(SheetName, rowNumber, "V1ProjectValidateEditWorkingOnRecertification");
			v2project.ValidateUpdatedProjectArea();
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_33_V1ProjectRegistrationAndRecertificationIntoV2Test.V1_TC_33_03_V1ProjectRetailRecertificationIntoV2Project" })
	@Parameters({ "SheetName","rowNum", "Type", "Country" ,"Upload", "API","Commodity","ProjectType","ProjectStandard","ProjectName"})
	public void V1_TC_33_04_RegisterV1ProjectRetailAndConvertIntoAllProject(String SheetName, int rowNum, String Type, String Country, String Upload, String API,
			String Commodity, String ProjectType, String ProjectStandard, String ProjectName) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register WELL V2 Certification Project Functionality");
		try {
			Boolean ProType;
			if(Type.equalsIgnoreCase("WELLCore")) {
				ProType =true;
			}
			else {
				ProType =false;
			}
			int rowNumber = 15;
			commonAPI.StoreV1Project(SheetName, rowNumber, Country, ProjectType, Upload, API, Commodity, "retail",ProjectName, ProType);
			commonAPI.BillingProject(SheetName, rowNumber, Country, ProjectType, Commodity);	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_33_V1ProjectRegistrationAndRecertificationIntoV2Test.V1_TC_33_04_RegisterV1ProjectRetailAndConvertIntoAllProject" })
	@Parameters({"SheetName","rowNum", "Country", "ProjectType"})
	public void V1_TC_33_05_V1AllProjectsInRecertificationIntoV2Project(String SheetName, int rowNum, String Country, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Recertification Functionality");
		try {
			login.AdminLogin();
			int rowNumber = 15;
			v2project.AdminSearchById(SheetName, rowNumber);
			v2project.editAndUpdateWellStandardAsAllProjectsIn();
			v2project.V1ProjectRecertificationIntoV2Project(SheetName, rowNumber);
			v2project.ValidateV1ProjectRecertification(SheetName, rowNumber, "V1ProjectValidateEditWorkingOnRecertification");
			v2project.ValidateUpdatedProjectArea();
		}
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
 }
