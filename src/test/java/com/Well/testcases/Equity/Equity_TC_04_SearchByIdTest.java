package com.Well.testcases.Equity;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Equity_TC_04_SearchByIdTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_03_SubscribeAndStoreIdTest.Equity_TC_03_01_StoreId" })
	@Parameters({ "SheetName","rowNum", "API" })
	public void Equity_TC_04_SearchById(String SheetName,int rowNum, String API) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
        StartTest(TestCaseName,"Equity Enrollment Search list Functionality");

		try {
			if (API.equalsIgnoreCase("false")) {
				equity.SearchEquityByID(SheetName,rowNum);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
