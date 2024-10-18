package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_Optn_08_SubscribeLocationPostReviewTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_07_SubscribeLocationReviewTest.Portfolio_TC_Optn_07_03_LocationAccountSubmitAndCompleteOptnRatingReview" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void PortfolioSubscribeLocation_TC_08_00_AchieveReview(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			String Id = commonAPI.storeprojectId(SheetName, rowNum);
			pfu.ValidatingAcheivedV2ProjectReviewDocument(SheetName, rowNum, "wer",Id);
		//	pfu.ValidatingAcheivedReviewDocument(SheetName,rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	}

