package com.Well.testcases.HealthSafety;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Healthsafety_TC_15_AddLocationTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.HealthSafety.Healthsafety_TC_14_ProfileTest.Healthsafety_TC_14_Profile" })
	@Parameters({ "SheetName","rowNum" ,"ProjectType"})
	public void Healthsafety_TC_15_AddLocation(String SheetName,int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Adding new location");
		try {
			if (!TestNGTestName.contains("NonEnhanced")) {
			rc.addLocation(SheetName, rowNum, ProjectType);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
