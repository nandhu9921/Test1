package com.Well.ReusableMethods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v127.network.Network;
import org.openqa.selenium.devtools.v127.network.model.RequestId;
import org.openqa.selenium.devtools.v128.network.model.Response;
import org.zaproxy.clientapi.core.ClientApiException;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;
import com.Well.Engine.XlsReader;

import net.sf.json.JSONObject;

@SuppressWarnings("unused")
public class ReusableMethodAssignment extends BaseClass {
	
	public static void Logintask(String SheetName,int rowNum,String ProjectName) throws IOException, InterruptedException {
		CommonMethod.RobustclickElementVisible("ProjectNavBar", "NavtoWellCertifi");
		CommonMethod.RobustclickElementVisible("NavtoWellCertifi", "StartV2Project");
		CommonMethod.Robustclick("StartV2Project");
		CommonMethod.scrollDown();
		CommonMethod.RobustclickElementVisible("ContinueBTNProject","ProjectName");
		String AccountName = ProjectName + CommonMethod.randomNumber(8000000);
		data.setCellData(SheetName, "ProjectName", rowNum, AccountName);
		CommonMethod.sendKeys("ProjectName",AccountName);
		CommonMethod.click("ProjectContinue");
		CommonMethod.RobustclickElementVisible("SelectCountrybutton","SelectStatebtn");
		CommonMethod.click("SelectStatebtn");
		String ProjectAddress = USfaker.address().streetAddress();
		String ProjectCity = USfaker.address().cityName();
		String PostalCode = USfaker.address().zipCode();
		CommonMethod.sendKeys("StreetInputBox",ProjectAddress);
		CommonMethod.scrollDown();
		CommonMethod.sendKeys("CityInputBox",ProjectCity);
		CommonMethod.sendKeys("PostalCodeInputBox",PostalCode);
		CommonMethod.RobustclickElementVisible("ContinueBtninLocation","AreaInputBtn");
		String AreaOfProject = CommonMethod.randomNumberBetweenRanges(100,20000); 
		CommonMethod.clearAndSendKey("AreaInputBtn",AreaOfProject );
		data.setCellData(SheetName, "Area", rowNum, CommonMethod.getattributeValue("AreaInputBtn"));
		CommonMethod.scrolldowntoElement("AreaContinueBtnfinal");
		CommonMethod.RobustclickElementVisible("AreaContinueBtnfinal","Checkbox");
		CommonMethod.RobustclickElementVisible("Checkbox","DiscountBtn");
		CommonMethod.click("DiscountBtn");
		CommonMethod.click("Checkboxfinal");
		CommonMethod.scrolldowntoElement("ContinueBtnFinal");
		CommonMethod.Robustclick("ContinueBtnFinal");
		CommonMethod.scrolldowntoElement("WellcertificationBTN");
		CommonMethod.click("WellcertificationBTN");
		CommonMethod.click("OwnershipType");
		CommonMethod.click("OnwershiptypeCOntinueBtn");
		CommonMethod.WaitUntilClickble("IDProject", 10);
		String IDNumberis = CommonMethod.getattributeValueByTextContent("IDProject");
	    String[] IDNumber2 = IDNumberis.split(":");
	    System.out.print(IDNumber2[1].trim());
	    IDNumberis = IDNumber2[1].toString();
		data.setCellData(SheetName, "ProjectID", rowNum, IDNumberis);
}
	
	
	
	
	public void Enroll(String SheetName, int rowNum, String Country) throws IOException, InterruptedException
	{
		Thread.sleep(5000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectNavBarAssignment", 0);
		CommonMethod.RobustclickElementVisible("ProjectNavBarAssignment", "WELLCertificationNavBarAssignment");
		CommonMethod.RobustclickElementVisible("WELLCertificationNavBarAssignment", "V2ProjectClearFilterListAssignment");
		CommonMethod.click("V2ProjectIdAssignment");
		CommonMethod.sendKeys("V2ProjectIdAssignment", data.getCellData(SheetName, "ProjectID", rowNum));
		CommonMethod.RobustclickElementVisible("V2ProjectApplybtnAssignment", "EnrollSearchPaginationAssignment");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("ProjectIdclick", 0);
		CommonMethod.RobustclickElementVisible("ProjectIdclick", "EnrollTabAssignment");
//		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("EnrollTabAssignment", 0);
		CommonMethod.RobustclickElementVisible("EnrollTabAssignment", "V2ProjectprojectOwnerContinuebtnAssignment");
		rc.SelectOwnerOrg(SheetName, rowNum);
		data.setCellData(SheetName, "OrgName", rowNum, CommonMethod.getText("OwnerOrgClick"));
		String Ownername=USfaker.address().firstName();
		CommonMethod.sendKeys("OwnerNameAssignment", Ownername);
		data.setCellData(SheetName, "OwnerName", rowNum, CommonMethod.getattributeValue("OwnerNameAssignment"));
		String Email=USfaker.internet().emailAddress();
		CommonMethod.sendKeys("V2ProjectownerEmailAssignment", Email);
		data.setCellData(SheetName, "OwnerEmail", rowNum, CommonMethod.getattributeValue("V2ProjectownerEmailAssignment"));	
		String Phoneno=USfaker.number().digits(10);
		CommonMethod.sendKeys("V2ProjectownerphoneAssignment", Phoneno);
		data.setCellData(SheetName, "PhoneNum", rowNum, CommonMethod.getattributeValue("V2ProjectownerphoneAssignment"));	
		CommonMethod.verifyDropdownValues("OrgIndustryAssignment", "OrganizationIndustry");
		CommonMethod.selectdropdownrandom("OrgIndustryAssignment");
		data.setCellData(SheetName, "OrgIndustry", rowNum, CommonMethod.getattributeValue("OrgIndustryAssignment"));	
		rc.SelectEnterpriseProviders(SheetName, rowNum);
		String Website="https://test-nuxt.wellcertified.com/";
		CommonMethod.sendKeys("OrgWebsite", Website);
		data.setCellData(SheetName, "Website", rowNum, CommonMethod.getattributeValue("OrgWebsite"));	
		CommonMethod.sendKeys("V2ProjectorganizationOverviewAssignment", Ownername);
		data.setCellData(SheetName, "Overview", rowNum, CommonMethod.getattributeValue("V2ProjectorganizationOverviewAssignment"));	
		CommonMethod.ClickCheckbox("V2ProjectconstructionOrrenovationAssignment");
		CommonMethod.ClickCheckbox("OtherCertifications");
		CommonMethod.RobustclickElementVisible("EstimatedDate", "DateOkBtn");
		CommonMethod.RobustclickElementVisible("DateOkBtn", "AnticipatedDate");
		CommonMethod.RobustclickElementVisible("AnticipatedDate", "DateOkBtn");
		CommonMethod.RobustclickElementVisible("DateOkBtn", "ContinueBtn");
		CommonMethod.RobustclickElementVisible("ContinueBtn", "V2ProjectowneraddressCountryAssignment");
		CommonMethod.selectdropdownValue("V2ProjectowneraddressCountryAssignment", Country);
		CommonMethod.WaitUntilVisibility("V2ProjectowneraddressStateAssignment", 10);
		CommonMethod.selectdropdownrandom("V2ProjectowneraddressStateAssignment");
		data.setCellData(SheetName, "OwnerCountry", rowNum, CommonMethod.getattributeValue("V2ProjectowneraddressCountryAssignment"));	
		data.setCellData(SheetName, "OwnerState", rowNum, CommonMethod.getattributeValue("V2ProjectowneraddressStateAssignment"));	
		String ProjectAddress=USfaker.address().streetAddress();
		String ProjectCity=USfaker.address().cityName();
		String PostalCode = USfaker.address().zipCode();
		CommonMethod.sendKeys("V2ProjectowneraddressStreetAssignment", ProjectAddress);
		CommonMethod.sendKeys("V2ProjectowneraddressCityAssignment", ProjectCity);
		CommonMethod.sendKeys("V2ProjectowneraddressPostalcodeAssignment", PostalCode);
		data.setCellData(SheetName, "OwnerStreet", rowNum, CommonMethod.getattributeValue("V2ProjectowneraddressStreetAssignment"));	
		data.setCellData(SheetName, "OwnerCity", rowNum, CommonMethod.getattributeValue("V2ProjectowneraddressCityAssignment"));	
		data.setCellData(SheetName, "OwnerPostalCode", rowNum, CommonMethod.getattributeValue("V2ProjectowneraddressPostalcodeAssignment"));
		CommonMethod.click("ContinueBtnEnrollment");
		Thread.sleep(5000);
//		String id="22024100107";
	}
	
}