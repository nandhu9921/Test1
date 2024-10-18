package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V1_TC_38_DocumentV1ProjectTest extends BaseClass  {
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_37_SearchV2ProjectByIdTest.V2_TC_37_00_SearchV2ProjectById" })
	@Parameters({ "SheetName","rowNum" })
	public void V1_TC_38_00_UploadGeneralDocumentFromDocuments(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload General Document in Documents Tab");
		try {
			commonAPI.DatePickerAPI(SheetName, rowNum);
			v2project.V1ProjectUploadLegalAndGeneralDocumentFromDocumentLibrary(SheetName, rowNum, "General", 
			  "General", GeneralfileUpload, "General");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_38_DocumentV1ProjectTest.V1_TC_38_00_UploadGeneralDocumentFromDocuments" })
	@Parameters({ "SheetName","rowNum" })
	public void V1_TC_38_01_UploadFeatureDocumentInDocV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Feature Document in DocumentLibrary");
		try {
			v2project.V1ProjectUploadLegalAndGeneralDocumentFromDocumentLibrary(SheetName, rowNum, "Smoking ban", 
					  "Innovation Proposal", GeneralfileUpload, "Smoking ban");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_38_DocumentV1ProjectTest.V1_TC_38_01_UploadFeatureDocumentInDocV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V1_TC_38_02_ValidateDocumentsText(String SheetName,int rowNum) throws IOException {

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
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_38_DocumentV1ProjectTest.V1_TC_38_02_ValidateDocumentsText" })
	@Parameters({ "SheetName","rowNum" })
	public void V1_TC_38_03_V1ProjectDeleteDocumentsTableList(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Delete Documents in Documents Tab");
		try {
			v2project.V1ProjectDeleteDocumentsTableList();
			v2project.V1ProjectDeleteDocumentsTableList();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
