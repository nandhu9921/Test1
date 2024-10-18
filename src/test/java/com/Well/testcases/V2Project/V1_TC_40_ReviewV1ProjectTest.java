package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V1_TC_40_ReviewV1ProjectTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_39_EquityV1ProjectTest.V1_TC_39_03_ValidateEquityRatingInDocumentsTabV1Project" })
	@Parameters({ "SheetName","rowNum"})
	public void V1_TC_40_00_V1ProjectSubmitPreliminaryDocumentationReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V1Project Submit Preliminary Documentation Review Functionality");
		try {
		v2project.SubmitReviewV2Project(SheetName, rowNum,"Preliminary Documentation Review","ReviewViewButton");
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}		
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_40_ReviewV1ProjectTest.V1_TC_40_00_V1ProjectSubmitPreliminaryDocumentationReview" })
	@Parameters({ "SheetName","rowNum"})
	public void V1_TC_40_01_V1ProjectAdminCompletePreliminaryDocumentationReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V1Project Complete Preliminary Documentation Review Functionality");
		try {	
		login.AdminLogin();	
		v2project.AdminCompleteReviewV2Project(SheetName, rowNum, "Preliminary Documentation Review","ReviewViewButton");
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}		
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_40_ReviewV1ProjectTest.V1_TC_40_01_V1ProjectAdminCompletePreliminaryDocumentationReview" })
	@Parameters({ "SheetName","rowNum"})
	public void V1_TC_40_02_V1ProjectSubmitFinalDocumentationReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V1Project Submit Final Documentation Review Functionality");
		try {
		rc.SignOut();
		login.Login();
		v2project.SearchV2ProjectById(SheetName, rowNum);	
		v2project.SubmitReviewV2Project(SheetName, rowNum,"Final Documentation Review","ReviewViewButton");
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}		
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_40_ReviewV1ProjectTest.V1_TC_40_02_V1ProjectSubmitFinalDocumentationReview" })
	@Parameters({ "SheetName","rowNum"})
	public void V1_TC_40_03_V1ProjectAdminCompleteFinalDocumentationReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V1Project Complete Final Documentation Review Functionality");
		try {	
		login.AdminLogin();	
		v2project.AdminCompleteReviewV2Project(SheetName, rowNum, "Final Documentation Review","V2ProjectHSRReviewViewbtn");
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}		
	}
}
