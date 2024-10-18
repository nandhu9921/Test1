package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V1_TC_32_RegisterV1ProjectTest extends BaseClass {

	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.login.TC_01_LoginTest.TC_01_Login" })
	@Parameters({ "SheetName","rowNum", "Type", "Country" ,"Upload", "API","Commodity","ProjectType","ProjectStandard","ProjectName"})
	public void V1_TC_32_00_RegisterV1Project(String SheetName, int rowNum, String Type, String Country, String Upload, String API,
			String Commodity, String ProjectType, String ProjectStandard, String ProjectName) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register WELL V1 Project Functionality");
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
}
