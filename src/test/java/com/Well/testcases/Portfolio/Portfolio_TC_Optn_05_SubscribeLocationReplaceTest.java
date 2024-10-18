package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_Optn_05_SubscribeLocationReplaceTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_04_SubscribeLocationToPortfolioWerOptnTest.Portfolio_TC_Optn_04_04_OptnWprValidateUploadDocumentLibraryInPortfolioAccount" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void PortfolioSubscribeLocationReplace_TC_05_00_FeatureScorecardUpload(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Feature Document Functionality");
		try {
		pfu.NavigateAddedLocation(SheetName, rowNum);
		v2project.BuildScorecardV2ProjectById(SheetName, rowNum);
		portfolio.uploadDocumentInFeatureForSubscribeLocRating("Prohibit Indoor Smoking",FeaturefileUpload,"A02.1","V2ProjectScorecardPartCount");
		pfu.ScorecardUploadSubscribeLocReplace(SheetName, rowNum, Commodity, "Prohibit Indoor Smoking", "Policy and/or Operations Schedule", "A02.1","All Spaces", "Feature", "TestComment", FeaturefileUpload, "A09.1","V2ProjectScorecardPartCount");
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_05_SubscribeLocationReplaceTest.PortfolioSubscribeLocationReplace_TC_05_00_FeatureScorecardUpload" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void PortfolioSubscribeLocationReplace_TC_05_01_AuditScorecardUpload(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Audit Document Functionality");
		try {
			portfolio.uploadDocumentInFeatureForSubscribeLocRating("Increase Outdoor Air Supply",AuditfileUpload,"A06.1","V2ProjectScorecardPartCount");
			pfu.ScorecardUploadSubscribeLocReplace(SheetName, rowNum, Commodity, "Increase Outdoor Air Supply", "Technical Document (Audited)", "A06.1","All Spaces", "Audit", "TestComment", AuditfileUpload,"A06.1","V2ProjectScorecardPartCount");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_05_SubscribeLocationReplaceTest.PortfolioSubscribeLocationReplace_TC_05_01_AuditScorecardUpload" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void PortfolioSubscribeLocationReplace_TC_05_02_AlternativeScorecardUpload(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			portfolio.uploadDocumentInFeatureForSubscribeLocRating("Measure Air Parameters",AlternativeFileUpload,"A01.5","V2ProjectScorecardPartCount");
			pfu.ScorecardUploadSubscribeLocReplace(SheetName, rowNum, Commodity, "Measure Air Parameters", "Alternative Strategy", "A01.5","All Spaces", "Feature", "TestComment", AlternativeFileUpload,"A02.2","V2ProjectScorecardPartCount");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_05_SubscribeLocationReplaceTest.PortfolioSubscribeLocationReplace_TC_05_02_AlternativeScorecardUpload" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void PortfolioSubscribeLocationReplace_TC_05_03_FeatureUploadReplaceInDocumentLibrary(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Feature Document Library Functionality");
		try {
			rc.clickDocument();
			v2project.clickScorecardTabInDocumentLibrary();
			rc.documentTableReplaceButton("FeatureMenuIcon");
		    pfu.SubscribeLocReplace(SheetName, rowNum, Commodity, "Prohibit Indoor Smoking", "Policy and/or Operations Schedule", "A02.1","All Spaces", "Feature", "TestComment", FeaturefileUpload,"W05.2");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_05_SubscribeLocationReplaceTest.PortfolioSubscribeLocationReplace_TC_05_03_FeatureUploadReplaceInDocumentLibrary" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void PortfolioSubscribeLocationReplace_TC_05_04_UploadAuditDocumentLibraryReplace(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Audit Document Functionality");
		try {
			
			rc.documentTableReplaceButton("PortfolioDocumentsAuditMenuIcon");
			pfu.SubscribeLocReplace(SheetName, rowNum, Commodity, "Increase Outdoor Air Supply", "Technical Document (Audited)", "A06.1","All Spaces", "Audit", "TestComment", AuditfileUpload, "A06.1");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_05_SubscribeLocationReplaceTest.PortfolioSubscribeLocationReplace_TC_05_04_UploadAuditDocumentLibraryReplace" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void PortfolioSubscribeLocationReplace_TC_05_05_UploadAlternativeDocumentLibraryReplace(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
		
			rc.documentTableReplaceButton("PortfolioDocumentsAlternativeMenuIcon");
			pfu.SubscribeLocReplace(SheetName, rowNum, Commodity, "Measure Air Parameters", "Alternative Strategy", "A01.5","All Spaces", "Feature", "TestComment", AlternativeFileUpload,"A03.1");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	}

