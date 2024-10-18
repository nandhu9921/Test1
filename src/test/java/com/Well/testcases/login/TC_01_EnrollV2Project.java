package com.Well.testcases.login;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ClientApiException;

import com.Well.Engine.BaseClass;

public class TC_01_EnrollV2Project extends BaseClass {
	
	@Test
	@Parameters({ "SheetName","rowNum","Country"})
	public void TC_01_Enroll(String SheetName,int rowNum,String Country) 
	{
		System.out.println("In Enroll class");
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"V2 project Enrollment" );
		try {
		Task.Enroll(SheetName, rowNum, Country);
	} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
