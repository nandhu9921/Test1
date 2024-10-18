package com.Well.testcases.Equity;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class Equity_TC_13_PromotionTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_12_EditTest.Equity_TC_12_editAndValidateAdmin" })
	@Parameters({ "SheetName","rowNum" })
	public void Equity_TC_13_Promotion(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Verifying Card link details");
		try {
			
			rc.promotionCardValidation();
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
