package com.Well.testcases.HealthSafety;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Healthsafety_TC_SF_02_EnrollTest extends BaseClass {
	@Test(dependsOnMethods = { "com.Well.testcases.HealthSafety.Healthsafety_TC_SF_01_SearchReadyToEnrollStatusTest.Healthsafety_TC_SF_01_SearchReadyToEnrollStatus" })
	@Parameters({ "SheetName","rowNum"})
	public void Healthsafety_TC_SF_02_Enroll(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Enroll WELL Healthsafety Project");
		try {
		hsr.EnrollHealthSafetySF(SheetName, rowNum);
		hsr.AgreementHealthSafetySF(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}