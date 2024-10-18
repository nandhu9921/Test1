package com.Well.testcases.login;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class AssignmentTask02 extends BaseClass {

	
	@SuppressWarnings("static-access")
	@Test
	@Parameters({ "SheetName","rowNum","ProjectName" })
	public void TC_01_LoginTask(String SheetName,int rowNum,String ProjectName)  {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"WELL Login Functionality" );
		try {
			Task.Logintask(SheetName, rowNum, ProjectName);
	} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
