package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V1_TC_37_SearchV2ProjectByIdTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_36_AgreementV1ProjectTest.V1_TC_36_00_CompleteV1ProjectAgreementByAPI" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_37_00_SearchV2ProjectById(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"WELL V2 Project Certification Enrollment list Functionality");
		try {
			v2project.SearchV2ProjectById(SheetName, rowNum);	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
