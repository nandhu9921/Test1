package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_Optn_06_SubscribeLocationReplaceOptnTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_05_SubscribeLocationReplaceTest.PortfolioSubscribeLocationReplace_TC_05_05_UploadAlternativeDocumentLibraryReplace" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void PortfolioSubscribeLocationOptnReplace_TC_06_00_FeatureScorecardUpload(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			pfu.NavigateAddedLocation(SheetName, rowNum);
			v2project.V2ProjectOpt(SheetName, rowNum, "EquityTab");
			rc.ScorecardLoading();
			portfolio.uploadDocumentInFeatureForSubscribeLocRating("Select Project Survey",FeaturefileUpload, "EE3.1","V2projectRatingFeatureName");
			pfu.ScorecardUploadSubscribeLocReplace(SheetName, rowNum, Commodity, "Select Project Survey", "Policy and/or Operations Schedule", "EE3.1",	"All Spaces", "Feature", "TestComment", FeaturefileUpload,"ED6.1","V2projectRatingFeatureName");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_06_SubscribeLocationReplaceOptnTest.PortfolioSubscribeLocationOptnReplace_TC_06_00_FeatureScorecardUpload" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void PortfolioSubscribeLocationOptnReplace_TC_06_01_AuditScorecardUpload(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			portfolio.uploadDocumentInFeatureForSubscribeLocRating("Select Sites with Access to Mass Transit",AuditfileUpload,"ED10.2","V2projectRatingFeatureName");
			pfu.ScorecardUploadSubscribeLocReplace(SheetName, rowNum, Commodity, "Select Sites with Access to Mass Transit",
					"Technical Document (Audited)", "ED10.2", "All Spaces", "Audit", "TestComment", AuditfileUpload,"ED10.2","V2projectRatingFeatureName");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_06_SubscribeLocationReplaceOptnTest.PortfolioSubscribeLocationOptnReplace_TC_06_01_AuditScorecardUpload" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void PortfolioSubscribeLocationOptnReplace_TC_06_02_AlternativeScorecardUpload(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
			portfolio.uploadDocumentInFeatureForSubscribeLocRating("Facilitate Stakeholder Charrette",AlternativeFileUpload,"EE2.1","V2projectRatingFeatureName");
			pfu.ScorecardUploadSubscribeLocReplace(SheetName, rowNum, Commodity, "Facilitate Stakeholder Charrette",
					"Alternative Strategy", "EE2.1", "Alternative Adherence Path (AAP)", "Feature",
					"TestComment", AlternativeFileUpload,"EE3.2","V2projectRatingFeatureName");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	}

