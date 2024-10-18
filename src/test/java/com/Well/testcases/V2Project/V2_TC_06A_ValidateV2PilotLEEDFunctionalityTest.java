package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;


public class V2_TC_06A_ValidateV2PilotLEEDFunctionalityTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_02_HealthSafetyV2ProjectOptn_AddMultiplePartTest.V2Project_TC_Optn_02_09_OptnHsrAlternativeValidDeletedDoc" })
	@Parameters({ "SheetName","rowNum","ProjectType"})
	public void V2_TC_06A_01_V2PilotValidateLEEDCheckboxForAllReviewPhases(String SheetName,int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate LEED Checkbox for all Review Phases");
		try {
			if (ProjectType.contains("Pilot")) {
		v2project.V2PilotValidateLEEDCheckboxForAllReviewPhases();	
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
