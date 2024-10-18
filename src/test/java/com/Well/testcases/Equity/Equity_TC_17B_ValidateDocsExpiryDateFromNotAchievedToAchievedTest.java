package com.Well.testcases.Equity;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Equity_TC_17B_ValidateDocsExpiryDateFromNotAchievedToAchievedTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_17_SearchByAchievedStatusTest.Equity_TC_17_SearchByAchievedStatus" })
	@Parameters({ "SheetName","rowNum"})
	public void Equity_TC_17B_00_ValidateDocumentsLibraryDocsExpiryDateOnMarkingNotAchievedToAchieved(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Documents Library Docs Expiry Date on Marking NotAchieved to Achieved");
		try {
			login.AdminLogin();
			equity.AdminSearchWer(SheetName, rowNum);
			performance.ValidateDocumentsExpiryDateOnMarkingNotAchievedToAchieved();
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_17B_ValidateDocsExpiryDateFromNotAchievedToAchievedTest.Equity_TC_17B_00_ValidateDocumentsLibraryDocsExpiryDateOnMarkingNotAchievedToAchieved" })
	@Parameters({ "SheetName","rowNum","ProjectType","Commodity"})
	public void Equity_TC_17B_01_ValidateScorecardDocsExpiryDateOnMarkingNotAchievedToAchieved(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Scorecard Docs Expiry Date on Marking NotAchieved to Achieved");
		try {
		    rc.clickScorecard();
			performance.ValidateScorecardDocsExpiryDateOnMarkingNotAchievedToAchieved("Utilize Enhanced Survey",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false,
					false, false, false);
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}