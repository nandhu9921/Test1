package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V2_TC_08_DocumentV2ProjectTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_07_ScorecardV2ProjectTest.V2_TC_07_15_ScoreCardOptionFilterV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_08_00_UploadGeneralDocumentFromDocumentLibrary(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload General Document in DocumentLibrary");
		try {
			v2project.UploadLegalAndGeneralDocumentFromDocumentLibrary(SheetName, rowNum,"General",GeneralfileUpload);	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08_DocumentV2ProjectTest.V2_TC_08_00_UploadGeneralDocumentFromDocumentLibrary" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_08_01_UploadLegalDocumentFromDocumentLibrary(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Legal Document in DocumentLibrary");
		try {
			v2project.UploadLegalAndGeneralDocumentFromDocumentLibrary(SheetName, rowNum,"Legal",LegalfileUpload);	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08_DocumentV2ProjectTest.V2_TC_08_01_UploadLegalDocumentFromDocumentLibrary" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_08_02_UploadOngoingDocumentInDocV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Ongoing Document in DocumentLibrary");
		try {			
			v2project.UploadOngoingDocumentInDocV2Project(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08_DocumentV2ProjectTest.V2_TC_08_02_UploadOngoingDocumentInDocV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_08_03_UploadFeatureDocumentInDocV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Feature Document in DocumentLibrary");
		try {
			v2project.UploadFeatureDocumentInDocV2Project(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08_DocumentV2ProjectTest.V2_TC_08_03_UploadFeatureDocumentInDocV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_08_04_UploadDocumentCountInDocV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Upload Document Count");
		try {
			
			v2project.UploadDocumentCountInDocV2Project(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08_DocumentV2ProjectTest.V2_TC_08_04_UploadDocumentCountInDocV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_08_05_ValidateDocumentsText(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Documents Text");
		try {
			v2project.ValidateDocumentsText(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08_DocumentV2ProjectTest.V2_TC_08_05_ValidateDocumentsText" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_08_06_GeneralSearchFilterDocumentV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Upload Document Count");
		try {
			v2project.SearchFilterDocument("GENERALFILE", "General","1");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08_DocumentV2ProjectTest.V2_TC_08_06_GeneralSearchFilterDocumentV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_08_07_AuditSearchFilterDocumentV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		StartTest(TestCaseName,"Upload Document Count");
		try {
			v2project.SearchFilterDocument("AUDITFILE", "Scorecard","1");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_08_DocumentV2ProjectTest.V2_TC_08_07_AuditSearchFilterDocumentV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_08_08_CorePointDocumentUploadInDocumentLibraryV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		StartTest(TestCaseName,"Upload Document Count");
		try {
			if (!TestNGTestName.contains("Pilot")) {
			v2project.CorePointDocumentUploadInDocumentLibraryV2Project(SheetName, rowNum);
			rc.ScorecardNavigation();
			v2project.VerifyCorePointInScorecard("Balance Visual Lighting","V2ProjectL07.1FeatureSelectedPurseYes","V2ProjectL07.1FeatureEnabledWeightCirclePoint");
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
