package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_25_AdminAnalyzeDashboardTest extends BaseClass {

	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_24_LocationAccountPromotionAsCertificationTest.Portfolio_TC_24_05_ValidateAchievementTabInPortfolioAccount" })
	@Parameters({ "SheetName", "rowNum" })
	public void Portfolio_TC_25_00_AdminAnalyzeDashboard(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Portfolio Scorecard custom scenarios");
		try {
			login.AdminLogin();
			portfolio.AdminSearch(SheetName, rowNum);
			portfolio.AdminAnalyzeDashboard(SheetName, rowNum);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
