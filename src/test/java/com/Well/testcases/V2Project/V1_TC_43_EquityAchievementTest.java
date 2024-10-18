package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V1_TC_43_EquityAchievementTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_42_PerformanceV1ProjectTest.V1_TC_42_02_ValidatePerformanceRatingInDocumentsTabV1Project" })
	@Parameters({ "SheetName","rowNum"})
	public void V1_TC_43_00_V1ProjectEquityAchievement(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V1Project Validate Equity Achievement Functionality");
		try {
			login.AdminLogin();
			v2project.AdminSearchById(SheetName, rowNum);
			v2project.V1ProjectAdminUserMarkCertification(SheetName, rowNum, "V2ProjectEditEquityRatingStatus",
					"V2ProjectEditEquityDateAchieved", "Achieved", "V2AchievementAdminTabEquitySaveBtn");
			v2project.V1ProjectValidateSealAndDate();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}		
	}
}
