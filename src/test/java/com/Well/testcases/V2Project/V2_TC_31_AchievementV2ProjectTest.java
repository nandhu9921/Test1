package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V2_TC_31_AchievementV2ProjectTest extends BaseClass {

	@Test(dependsOnMethods = {"com.Well.testcases.V2Project.V2_TC_07A_ScorecardTargetPointSummaryV2Project.V2_TC_07A_01_DeleteScorecardDocumentUpload" })
	@Parameters({ "SheetName", "rowNum" })
	public void V2_TC_31_AchievementV2Project(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Achievement Card Validation");
		try {
			login.AdminLogin();
			v2project.AdminSearchById(SheetName, rowNum);
			portfolio.editAdminAsCertification();
			rc.SignOut();
			login.Login();
			v2project.SearchV2ProjectById(SheetName, rowNum);	
			portfolio.ValidateAcheivementTabAndCard();
			hsr.validateDownloadedAchievementDocument("LocationsAchievmentDownloadCertificate", "NA");
			v2project.VerifyPdfDownload(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
