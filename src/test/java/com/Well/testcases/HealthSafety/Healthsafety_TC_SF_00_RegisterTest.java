package com.Well.testcases.HealthSafety;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Healthsafety_TC_SF_00_RegisterTest extends BaseClass {

	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.login.TC_01_LoginTest.TC_01_Login" })
	@Parameters({"SheetName","rowNum","DataValidate","Country","ProjectType","Upload","API","Commodity","ImportLocation","ProjectName"})
	public void Healthsafety_TC_SF_00_Register(String SheetName,int rowNum, String DataValidate, String Country, String ProjectType,String Upload,String API,String Commodity, String ImportLocation, String ProjectName) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register WELL Healthsafety Project Functionality");
		try {
			commonAPI.StoreAnyProject(SheetName, rowNum, "US", ProjectType, Upload, API, Commodity, ImportLocation,"AutomationHsrNonEnhanced", false);		
			commonAPI.SignAgreement(SheetName, rowNum, ProjectType, Commodity);
            commonAPI.BillingProject(SheetName, rowNum, "US", ProjectType, Commodity);	
			commonAPI.UpdateNonEnhanced(SheetName, rowNum, "US", ProjectType, Upload, API, Commodity);
		    hsr.SearchHealthSafetyByID(SheetName,rowNum);
		    hsr.StoreIdHealthSafety(SheetName, rowNum);	
		    generic.importLocationGeneric(Commodity, RatingLocationImportfile);
			commonAPI.storeRatingScorecardIdAPI(SheetName,rowNum,"hsr_scorecard_id");
			rc.clickScorecard();
			commonAPI.fillScorecardAPI(SheetName,rowNum, "ScorecardId");
		    login.AdminLogin();
		    hsr.AdminHsrSearch(SheetName, rowNum);
		    rc.clickScorecard();
			hsr.LoginWithSuperAdminAndSaveDocIdInExcel("Support Handwashing",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false,
					false, false, false);
			v2project.refreshScorecardOptn();
            rc.SignOut();
			login.Login();
		rc.ValidateProjectNameAllCases();
		hsr.RegisterHealthSafetySF(SheetName, rowNum, Country, ProjectName);	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
