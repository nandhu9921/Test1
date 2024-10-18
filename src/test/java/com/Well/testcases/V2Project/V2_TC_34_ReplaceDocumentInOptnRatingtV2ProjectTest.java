package com.Well.testcases.V2Project;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class V2_TC_34_ReplaceDocumentInOptnRatingtV2ProjectTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_06_AgreementV2ProjectTest.V2_TC_06_00_AgreementV2Project" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void V2_TC_34_00_ReplaceFeatureScorecardUploadInOptnRatingV2Project(String SheetName,int rowNum, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Alternative Document Functionality");
		try {
		v2project.WERV2Project(SheetName, rowNum);
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
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_34_ReplaceDocumentInOptnRatingtV2ProjectTest.V2_TC_34_00_ReplaceFeatureScorecardUploadInOptnRatingV2Project" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void V2_TC_34_01_ReplaceAuditScorecardUploadInOptnRatingtV2Project(String SheetName,int rowNum, String Commodity) throws IOException {
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
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_34_ReplaceDocumentInOptnRatingtV2ProjectTest.V2_TC_34_01_ReplaceAuditScorecardUploadInOptnRatingtV2Project" })
	@Parameters({ "SheetName","rowNum", "Commodity" })
	public void V2_TC_34_02_ReplaceAlternativeScorecardUploadInOptnRatingtV2Project(String SheetName,int rowNum, String Commodity) throws IOException {
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
	
//	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_34_ReplaceDocumentInOptnRatingtV2ProjectTest.V2_TC_34_02_ReplaceAlternativeScorecardUploadInOptnRatingtV2Project" })
//	@Parameters({ "SheetName","rowNum", "Commodity" })
//	public void V2_TC_34_03_ReplaceFeatureDocumentLibraryUploadInOptnRatingtV2Project(String SheetName,int rowNum, String Commodity) throws IOException {
//		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//		StartTest(TestCaseName,"Upload Alternative Document Functionality");
//		try {
//			rc.clickDocument();
//			v2project.clickScorecardTabInDocumentLibrary();
//			rc.documentTableReplaceButton("FeatureMenuIcon");
//	//	    pfu.SubscribeLocReplace(SheetName, rowNum, Commodity, "Prohibit Indoor Smoking", "Policy and/or Operations Schedule", "A02.1","All Spaces", "Feature", "TestComment", FeaturefileUpload);
//		
//		} catch (Throwable t) {
//			System.out.println(t.getLocalizedMessage());
//			Error e1 = new Error(t.getMessage());
//			e1.setStackTrace(t.getStackTrace());
//			throw e1;
//		}
//	}
//	
//	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_34_ReplaceDocumentInOptnRatingtV2ProjectTest.V2_TC_34_03_ReplaceFeatureDocumentLibraryUploadInOptnRatingtV2Project" })
//	@Parameters({ "SheetName","rowNum", "Commodity" })
//	public void V2_TC_34_04_ReplaceAuditDocumentLibraryUploadInOptnRatingtV2Project(String SheetName,int rowNum, String Commodity) throws IOException {
//		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//		StartTest(TestCaseName,"Upload Alternative Document Functionality");
//		try {
//			rc.documentTableReplaceButton("PortfolioDocumentsAuditMenuIcon");
//		//	pfu.SubscribeLocReplace(SheetName, rowNum, Commodity, "Increase Outdoor Air Supply", "Technical Document (Audited)", "A06.1","All Spaces", "Audit", "TestComment", AuditfileUpload);
//		
//		} catch (Throwable t) {
//			System.out.println(t.getLocalizedMessage());
//			Error e1 = new Error(t.getMessage());
//			e1.setStackTrace(t.getStackTrace());
//			throw e1;
//		}
//	}
//	
//	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_34_ReplaceDocumentInOptnRatingtV2ProjectTest.V2_TC_34_04_ReplaceAuditDocumentLibraryUploadInOptnRatingtV2Project" })
//	@Parameters({ "SheetName","rowNum", "Commodity" })
//	public void V2_TC_34_05_ReplaceAlternativeDocumentLibraryUploadInOptnRatingtV2Project(String SheetName,int rowNum, String Commodity) throws IOException {
//		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
//		StartTest(TestCaseName,"Upload Alternative Document Functionality");
//		try {
//			rc.documentTableReplaceButton("PortfolioDocumentsAlternativeMenuIcon");
//		//	pfu.SubscribeLocReplace(SheetName, rowNum, Commodity, "Measure Air Parameters", "Policy and/or Operations Schedule", "A01.5","All Spaces", "Feature", "TestComment", AlternativeFileUpload);
//		
//		} catch (Throwable t) {
//			System.out.println(t.getLocalizedMessage());
//			Error e1 = new Error(t.getMessage());
//			e1.setStackTrace(t.getStackTrace());
//			throw e1;
//		}
//	}
}
