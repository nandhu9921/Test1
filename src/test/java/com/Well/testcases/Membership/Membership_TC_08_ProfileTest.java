package com.Well.testcases.Membership;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Membership_TC_08_ProfileTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_07_TeamTest.Membership_TC_07_Team" })
	@Parameters({ "SheetName","rowNum","MembershipName"  })
	public void Membership_TC_08_Profile(String SheetName,int rowNum, String MembershipName) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Updated Profile Data");
		try {
			
			membership.ProfileUpdated(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
