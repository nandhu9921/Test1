package com.Well.testcases.Community;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Community_TC_08_ProfileTest extends BaseClass {
	
	@Test(dependsOnMethods = {"com.Well.testcases.Community.Community_TC_07_ReviewTest.Community_TC_07_04_ValidateDashboardTimelineModule"})
	@Parameters({ "SheetName","rowNum" })
	public void Community_TC_08_Profile(String SheetName, int rowNum) throws Exception {
		
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Profile Your Well Certification Story Functionality");
		
		try {	
			v2project.profileV2Project(SheetName, rowNum);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
