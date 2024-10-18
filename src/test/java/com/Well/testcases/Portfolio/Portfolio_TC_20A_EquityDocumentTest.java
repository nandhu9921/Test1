package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_20A_EquityDocumentTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20_EquityPortfolioTest.Portfolio_TC_20_01_WERScoreCardUploadDocumentPortfolio" })
	@Parameters({ "SheetName", "rowNum","Commodity" })
	public void Portfolio_TC_20A_00_LegalUploadDocumentInDocument(String SheetName, int rowNum, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Type Legal Upload Document");

		try {
			portfolio.clickWERDocument();
			portfolio.ValidatingLegalUploadDocument(SheetName, rowNum, Commodity);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20A_EquityDocumentTest.Portfolio_TC_20A_00_LegalUploadDocumentInDocument" })
	@Parameters({"SheetName", "rowNum", "Commodity","ProjectType" })
	public void Portfolio_TC_20A_01_AuditUploadDocumentInDocument(String SheetName, int rowNum, String Commodity, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Verifies Audit Upload Document in Document Library");

		try {
			portfolio.ValidatingAuditUploadDocument(SheetName, rowNum, Commodity, ProjectType,"ED9.1","On-site Photographs");

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20A_EquityDocumentTest.Portfolio_TC_20A_01_AuditUploadDocumentInDocument" })
	@Parameters({"SheetName", "rowNum", "Commodity","ProjectType" })
	public void Portfolio_TC_20A_02_FeatureUploadDocumentInDocument(String SheetName, int rowNum, String Commodity, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Verifies Feature Upload Document in Document Library");

		try {
		portfolio.ValidatingFeatureUploadDocument(SheetName, rowNum, Commodity, ProjectType,"ED5.5");

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
