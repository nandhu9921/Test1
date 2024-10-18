package com.Well.testcases.Faculty;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Faculty_TC_14_FieldNegativeAssertTest extends BaseClass {

	@Test
	@Parameters({ "SheetName", "rowNum" })
	public void Faculty_TC_14_FieldNegativeAssert(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
        StartTest(TestCaseName, "Field Negative Assert Validation");
        try {
			rc.FieldNegativeAssert();
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
