package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class V1_TC_36_AgreementV1ProjectTest extends BaseClass {
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_32_RegisterV1ProjectTest.V1_TC_32_00_RegisterV1Project" })
	@Parameters({ "SheetName","rowNum","environment" })
	public void V1_TC_36_00_CompleteV1ProjectAgreementByAPI(String SheetName,int rowNum, String environment) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete V1Project Agreement By API");
		try {
			community.CompleteCommunityAgreementByAPI(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
