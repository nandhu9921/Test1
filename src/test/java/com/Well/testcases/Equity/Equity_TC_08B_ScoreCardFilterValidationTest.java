package com.Well.testcases.Equity;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Equity_TC_08B_ScoreCardFilterValidationTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_21B_ValidateTeamPTAccessTest.Equity_TC_21B_06_ValidateAdminInScorecard" })
	@Parameters({ "SheetName","rowNum","Commodity" })
	public void Equity_TC_08B_ScoreCardFilterValidation(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verifies Scorecard Filter Options Functionality");
		try {
			rc.SignOut();
			login.Login();
			equity.SearchEquityByID(SheetName,rowNum);
			equity.WERPurseScorecard(SheetName,rowNum);
			hsr.verifyScoreCardFilterRating(Commodity,"V2ProjectScorecardResponseFilter","V2ProjectScorecardYesFilter", "HsrScorecardPurseStatusYesValid","2","true");
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	
}