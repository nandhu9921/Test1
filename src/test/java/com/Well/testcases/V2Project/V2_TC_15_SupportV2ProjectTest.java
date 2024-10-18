package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V2_TC_15_SupportV2ProjectTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_13A_EquityReviewV2ProjectTest.V2_TC_13A_01_EquityCompleteReviewV2Project" })
	@Parameters({ "SheetName","rowNum","ProjectType" })
	public void V2_TC_15_SupportV2Project(String SheetName,int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Rasing the Ticket in Support Functionality");
		try {
		v2project.supportV2Project(SheetName,rowNum, ProjectType);	
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
