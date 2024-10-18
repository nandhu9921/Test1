package com.Well.testcases.Community;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class Community_TC_06_AgreementTest extends BaseClass {

	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.Community.Community_TC_05_EditTest.Community_TC_05_04_ValidateAuthUserRegisteredFilter" })
	@Parameters({ "SheetName","rowNum","environment" })
	public void Community_TC_06_00_CompleteCommunityAgreementByAPI(String SheetName,int rowNum, String environment) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete Community Agreement By API");
		try {

			commonAPI.getCommunityByProjectNumAPI(SheetName, rowNum);
			community.CompleteCommunityAgreementByAPI(SheetName, rowNum);
			CommonMethod.refreshBrowser();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
