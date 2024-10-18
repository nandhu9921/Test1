package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;

public class V1_TC_39_EquityV1ProjectTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_38_DocumentV1ProjectTest.V1_TC_38_03_V1ProjectDeleteDocumentsTableList" })
	@Parameters({ "SheetName","rowNum" })
	public void V1_TC_39_00_EquityV1Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V1Project optn equity Functionality");
		try {
			v2project.WERV2Project(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_39_EquityV1ProjectTest.V1_TC_39_00_EquityV1Project" })
	@Parameters({ "SheetName","rowNum","ProjectType" })
	public void V1_TC_39_01_EquityCompleteScoreCardV1Project(String SheetName,int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V1Project optn equity pursing scorecard Functionality");
		try {
			v2project.ScorecardfillWER(32, 33, 71, 39);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_39_EquityV1ProjectTest.V1_TC_39_01_EquityCompleteScoreCardV1Project" })
	@Parameters({ "SheetName","rowNum","ProjectType" })
	public void V1_TC_39_02_EquityScoreCardUploadDocumentV1Project(String SheetName,int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V1Project optn equity scorecard document upload Functionality");
		try {
			CommonMethod.refreshBrowser();
			rc.ScorecardLoading();
			v2project.uploadEquityDocV2Project(SheetName);	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_39_EquityV1ProjectTest.V1_TC_39_01_EquityCompleteScoreCardV1Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V1_TC_39_03_ValidateEquityRatingInDocumentsTabV1Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Equity Rating in Documents Tab V1 Project");
		try {
			rc.clickDocument();
			v2project.ValidateEquityRatingInDocumentsTabV1Project(SheetName, rowNum);	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
