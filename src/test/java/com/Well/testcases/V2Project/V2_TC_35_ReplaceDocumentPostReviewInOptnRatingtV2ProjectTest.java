package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V2_TC_35_ReplaceDocumentPostReviewInOptnRatingtV2ProjectTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_13A_EquityReviewV2ProjectTest.V2_TC_13A_13_AdminReviewimportHistory" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void V2_TC_35_ReplaceDocumentPostReviewInOptnRatingtV2Project(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			String Id = commonAPI.storeprojectId(SheetName, rowNum);
			pfu.ValidatingAcheivedV2ProjectReviewDocument(SheetName, rowNum, "v2",Id);
			pfu.ValidatingAcheivedV2ProjectReviewDocument(SheetName, rowNum, "wer",Id);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
