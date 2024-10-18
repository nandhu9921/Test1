package com.Well.testcases.Membership;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class Membership_TC_07_TeamTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_06_EducationTest.Membership_TC_06_Education" })
	@Parameters({ "SheetName","rowNum","MembershipName"  })
	public void Membership_TC_07_Team(String SheetName,int rowNum, String MembershipName) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Added and Deleted Team Member");
		try {
			Thread.sleep(70000);
			if (MembershipName.equalsIgnoreCase("WELL")) {
			membership.AddTeamMember(SheetName, rowNum,"MembershipTeamRoleOption");
			}
			else {
			membership.AddTeamMember(SheetName, rowNum,"MembershipTeamRolePointOfContactOption");
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
