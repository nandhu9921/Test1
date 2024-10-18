package com.Well.testcases.Equity;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;


public class Equity_TC_08C_ReplaceUploadedDocumentTest extends BaseClass{
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_10_ReviewTest.Equity_TC_10_17_purseYesToNoValidFromScorecard" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Equity_TC_08C_00_FeatureScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			rc.clickScorecard();
			rc.CommonSingleUploadScorecardDocument("Support Equitable Working Hours", SheetName, rowNum, Commodity, FeaturefileUpload, false, false, true, false);
			pfu.ScorecardUploadReplace(SheetName, rowNum, Commodity, "Support Equitable Working Hours", "Policy and/or Operations Schedule", "EB5",	"All Spaces", "Feature verification", "TestComment", FeaturefileUpload, "5");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_08C_ReplaceUploadedDocumentTest.Equity_TC_08C_00_FeatureScorecardUploadReplace" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Equity_TC_08C_01_AuditScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			rc.CommonSingleUploadScorecardDocument("Provide Community Space", SheetName, rowNum, Commodity, AuditfileUpload, false, false, true, false);
			pfu.ScorecardUploadReplace(SheetName, rowNum, Commodity, "Provide Community Space",
					"Technical Document (Audited)", "EC2", "All Spaces", "Audit verification", "TestComment", AuditfileUpload,
					"2");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_08C_ReplaceUploadedDocumentTest.Equity_TC_08C_01_AuditScorecardUploadReplace" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Equity_TC_08C_02_AlternativeScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			rc.CommonSingleUploadScorecardDocument("Provide Restorative Space", SheetName, rowNum, Commodity, AlternativeFileUpload, false, false, true, false);
			pfu.ScorecardUploadReplace(SheetName, rowNum, Commodity, "Provide Restorative Space",
					"Alternative Strategy", "ES2", "Alternative Adherence Path (AAP)", "Feature verification",
					"TestComment", AlternativeFileUpload, "5");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_08C_ReplaceUploadedDocumentTest.Equity_TC_08C_01_AuditScorecardUploadReplace" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Equity_TC_08C_03_UploadDocumentLibraryReplace(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			pfu.DocumentTab();
			pfu.DocumentUploadReplace(SheetName, rowNum, Commodity, "Provide Restorative Space",
					"Alternative Strategy", "ES2", "Alternative Adherence Path (AAP)", "Feature verification",
					"TestComment", AlternativeFileUpload, "5");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_08_ScoreCardTest.Equity_TC_08C_03_UploadDocumentLibraryReplace" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Equity_TC_08C_04_PostFeatureScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			rc.clickScorecard();
			 String Id = commonAPI.storePortfolioId(SheetName, rowNum);
			 data.setCellData(SheetName, "ID", rowNum, Id);
			pfu.ValidatingAcheivedReviewDocument(SheetName,rowNum);
			pfu.postScorecardUploadReplace(SheetName, rowNum, Commodity, "Support Equitable Working Hours", "Policy and/or Operations Schedule", "EB5",
					"All Spaces", "Feature verification", "TestComment", FeaturefileUpload, "6");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_08C_ReplaceUploadedDocumentTest.Equity_TC_08C_04_PostFeatureScorecardUploadReplace" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Equity_TC_08C_05_PostAuditScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			pfu.postScorecardUploadReplace(SheetName, rowNum, Commodity, "Provide Community Space",
			"Technical Document (Audited)", "EC2", "All Spaces", "Audit verification", "TestComment", AuditfileUpload,"3");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_08C_ReplaceUploadedDocumentTest.Equity_TC_08C_05_PostAuditScorecardUploadReplace" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Equity_TC_08C_06_PostAlternativeScorecardUploadReplace(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			pfu.postScorecardUploadReplace(SheetName, rowNum, Commodity, "Provide Restorative Space",
					"Alternative Strategy", "ES2", "Alternative Adherence Path (AAP)", "Feature verification",
					"TestComment", AlternativeFileUpload, "6");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_08C_ReplaceUploadedDocumentTest.Equity_TC_08C_05_PostAuditScorecardUploadReplace" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void Equity_TC_08C_07_PostUploadDocumentLibraryReplace(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			pfu.DocumentTab();
			pfu.DocumentUploadReplace(SheetName, rowNum, Commodity, "Provide Restorative Space",
					"Alternative Strategy", "ES2", "Alternative Adherence Path (AAP)", "Feature verification",
					"TestComment", AlternativeFileUpload, "5");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}