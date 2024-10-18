package com.Well.testcases.Performance;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Performance_TC_16_DownloadBillingAndValidateTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_05_WprProjectFieldValidationTest.Performance_TC_05_WprProjectFieldValidation" })
	@Parameters({ "SheetName","rowNum" ,"Country"})
	public void Performance_TC_16_DownloadBillingAndValidate(String SheetName,int rowNum,String Country) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate billing receipt");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			rc.ClickBilling();
			rc.DownloadBillingReceiptAndValidate(SheetName, rowNum, Country);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
