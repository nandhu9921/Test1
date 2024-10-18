package com.Well.testcases.Equity;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Equity_TC_05_DownloadBillingAndValidateTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_18_DashboardTest.Equity_TC_18_Dashboard" })
	@Parameters({ "SheetName","rowNum" ,"Country","API"})
	public void Equity_TC_05_DownloadBillingAndValidate(String SheetName,int rowNum,String Country, String API) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
        StartTest(TestCaseName,"Validate billing receipt");
		try {
			if (API.equalsIgnoreCase("false")) {
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
