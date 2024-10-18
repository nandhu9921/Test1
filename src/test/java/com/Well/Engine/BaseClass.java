package com.Well.Engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import com.Well.ReusableMethods.ReusableMethodAssignment;
import com.Well.ReusableMethods.ReusableMethodCommon;
import com.Well.ReusableMethods.ReusableMethodCommonAPI;
import com.Well.ReusableMethods.ReusableMethodEquity;
import com.Well.ReusableMethods.ReusableMethodPerformance;
import com.Well.ReusableMethods.ReusableMethodsCommunity;
import com.Well.ReusableMethods.ReusableMethodsCustomPortfolio;
import com.Well.ReusableMethods.ReusableMethodsCustomPortfolioUpload;
import com.Well.ReusableMethods.ReusableMethodsExam;
import com.Well.ReusableMethods.ReusableMethodsFaculty;
import com.Well.ReusableMethods.ReusableMethodsGeneric;
import com.Well.ReusableMethods.ReusableMethodsHealthSafety;
import com.Well.ReusableMethods.ReusableMethodsLogin;
import com.Well.ReusableMethods.ReusableMethodsMembership;
import com.Well.ReusableMethods.ReusableMethodsPortfolio;
import com.Well.ReusableMethods.ReusableMethodsV2Project;
import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class BaseClass {

	protected DevTools devTools;
	public static String BrowserName;
	public static String Environment;
	public static String securtiyTest;
	public static String Projecttype;
	public static String VideoRecording;
	public static WebDriver driver;
	public static XlsReader data;
	public static XlsReader pl;
	public static XlsReader portfolioLocationDownload;
	public static int timeout = 60;
	public static int Scorecardtimeout = 1200;
	public static boolean recordingStarted = false;
	public static ExtentTest testlog;
	public static ExtentReports extent;
	public static String TestCaseName;
	public static String ModuleName;
	public static String TestNGTestName;
	public static String SecurityAssesment;
	public static String QaUrl; 
	public static String TestUrl;
	public static String DevUrl;
	public static String objectvalue;
	public static String previousPageSource = "";
	private ClientApi api;
	private ApiResponse response;
	public static Response res;
	public static StopWatch pageLoad;
	static final String ZAP_PROXY_ADDRESS = "localhost";
	static final int ZAP_PROXY_PORT = 8091;
	public static SoftAssert softAssert = new SoftAssert();
	public static SoftAssert negativesoftAssert = new SoftAssert();
	public static SoftAssert negativeFieldSoftAssert = new SoftAssert();
	public static String SamplePdffile = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "Resume.pdf";
	public static String SamplePdffile1 = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "SampleResume.pdf";
	public static String SampleJpgfile = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "Favicon.jpg";
	public static String SampleJpgfile1 = System.getProperty("user.dir") + File.separator + "src" + File.separator
					+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "Sample_JPG_500kB.jpg";
	public static String AlternativeFileUpload = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "AlternativeFile.xlsx";
	public static String AuditfileUpload = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "AuditFile.xlsx";
	public static String PortfolioLocationImportfile = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "portfoliolocations.xlsm";
	public static String ImportfileHundredPlusLocations = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "ImportHundredPlusLocation.xlsm";
	public static String ImportfilePortfolioLocation = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "Import_portfoliolocations.xlsm";	
	public static String DuplicatePortfolioLocation = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "Duplicate_portfoliolocations.xlsm";	
	public static String ImportfilePortfolioLocationArea = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "Import_portfoliolocations_Area.xlsm";
	public static String ImportfilePortfolioLocationRenewal = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "portfoliolocations_Renewal.xlsm";
	public static String PortfolioLocationUploadImportfile = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "PortfolioLocationsUpload.xlsm";
	public static String PortfolioLocationUploadV2Importfile = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "PortfolioLocationsUploadV2.xlsm";
	public static String PortfolioLocationUploadV2PilotImportfile = System.getProperty("user.dir") + File.separator + "src"
					+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
					+ "PortfolioLocationsUploadV2Pilot.xlsm";
	public static String FeaturefileUpload = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "FeatureFile.xlsx";
	public static String GeneralfileUpload = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "GeneralFile.xlsx";
	public static String LegalfileUpload = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "LegalFile.xlsx";
	public static String OngoingfileUpload = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "OngoingFile.xlsx";
	public static String LOAfileUpload = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "LOAFile.xlsx";
	public static String ProductSkuImportTestDataUpload = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "ProductSkuImportTestData.xlsx";
	public static String ProductLineReview = System.getProperty("user.dir") + File.separator + "Downloads"
			+ File.separator + "ProductReview.xlsx";
	public static Faker USfaker = new Faker(new Locale("en-US"));
	public static String downloadPath = System.getProperty("user.dir") + File.separator + "Downloads" + File.separator;
	public static String TestRecordingdownloadPath = System.getProperty("user.dir") + File.separator + "test-recordings" + File.separator;
	public static String ScreenshotsPath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator;
	public static String BillingReceiptPdffile = System.getProperty("user.dir") + File.separator + "Downloads"
			+ File.separator;
	public static String SpecialCharFeaturefileName = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "FeatureFile@!!##%&%Update.xlsx";
	public static String RatingLocationImportfile = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "Ratinglocations.xlsm";
	public static String ImportReviewUpload = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "Files" + File.separator
			+ "ImportReviewUpload.xlsx";
	public static String OS = System.getProperty("os.name");
	public static ReusableMethodsLogin login = new ReusableMethodsLogin();
	public static ReusableMethodsPortfolio portfolio = new ReusableMethodsPortfolio();
	public static ReusableMethodsCustomPortfolio pf = new ReusableMethodsCustomPortfolio();
	public static ReusableMethodsCustomPortfolioUpload pfu = new ReusableMethodsCustomPortfolioUpload();
	public static ReusableMethodsV2Project v2project = new ReusableMethodsV2Project();
	public static ReusableMethodsHealthSafety hsr = new ReusableMethodsHealthSafety();
	public static ReusableMethodPerformance performance = new ReusableMethodPerformance();
	public static ReusableMethodsFaculty faculty = new ReusableMethodsFaculty();
	public static ReusableMethodsExam exam = new ReusableMethodsExam();
	public static ReusableMethodsMembership membership = new ReusableMethodsMembership();
	public static ReusableMethodEquity equity = new ReusableMethodEquity();
	public static ReusableMethodsCommunity community = new ReusableMethodsCommunity();
	public static ReusableMethodCommon rc = new ReusableMethodCommon();
	public static ReusableMethodCommonAPI commonAPI = new ReusableMethodCommonAPI();
	public static ReusableMethodsGeneric generic = new ReusableMethodsGeneric();
	public static Map<String,Object> pathprms = new HashMap<String,Object>();
	public String envVar;
	public static ReusableMethodAssignment Task = new ReusableMethodAssignment();
	
	static Format formatter = new SimpleDateFormat("YYYY-MM-dd");
	static Date date = new Date();
	public static String fetchedID;
	public static String ProgramID;
	public static String signupID;
	public static String screenshotfile = System.getProperty("user.dir") + "/Screenshots/";
	public static String Reportfile = System.getProperty("user.dir") + "/Report/WELL-AutomationReport" + "_"
			+ formatter.format(date) + ".html";
	public static WebDriverWait wait;
	static WebElement element;
	public static List<String> uploadedFilesList = new ArrayList<String>();
	public static ArrayList<String> narrativevalues = new ArrayList<String>();
	public static ArrayList<String> numericvalues = new ArrayList<String>();
	public static ArrayList<String> textvalues = new ArrayList<String>();
	public static ArrayList<String> dropdownvalues = new ArrayList<String>();
	public static ArrayList<String> datevalues = new ArrayList<String>();

	
	//Application Global Values
	public static int V2PreconditionFeature = 46;
	public static int V2OptimizationFeature = 173;
	public static String PortfolioDocumentVerificationFilter = "21";
	public static String PortfolioDocumentPartTypeFilter = "2";
	public static String PortfolioDocumentConceptFilter = "11";
	
	@BeforeSuite
	@Parameters({ "browserName", "environment", "SecurtiyTest", "moduleName", "videoRecording" })
	public void setup(String browserName, String environment, @Optional("false") String SecurtiyTest, String moduleName, @Optional("false") String videoRecording)
			throws InterruptedException, IOException, ClientApiException {

		data = new XlsReader(System.getProperty("user.dir") + "/TestData.xlsx");
		Properties prop = new Properties();
		if ((System.getenv("browserName") != null && !System.getenv("browserName").isEmpty())
				&& System.getenv("environment") != null && !System.getenv("environment").isEmpty()) {
			browserName = System.getenv("browserName");
			environment = System.getenv("environment");
			Environment = environment;
			ModuleName = moduleName;
			BrowserName = browserName;
			securtiyTest = SecurtiyTest;
			VideoRecording = videoRecording;
			System.out.println(browserName);
			System.out.println(environment);
			System.out.println(moduleName);
		}
		else {
			BrowserName = browserName;
			securtiyTest = SecurtiyTest;
			ModuleName = moduleName;
			Environment = environment;
			VideoRecording = videoRecording;
		}
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("chrome") && SecurtiyTest.equalsIgnoreCase("true")) {
			SecurityAssesment = "true";
			String ProxyServerURL = ZAP_PROXY_ADDRESS + ":" + ZAP_PROXY_PORT;
			Proxy proxy = new Proxy();
			proxy.setAutodetect(false);
			proxy.setHttpProxy(ProxyServerURL);
			proxy.setSslProxy(ProxyServerURL);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--proxy-bypass-list=<-loopback>");
			options.setAcceptInsecureCerts(true);
			options.setProxy(proxy);
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", downloadPath);
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("safebrowsing.enabled", "false");
			// options.addArguments("--incognito");
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");
			options.setExperimentalOption("useAutomationExtension", false);
			// options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
			options.addArguments("--window-size=1920,1280");
			options.addArguments("--window-position=0,0");
			options.addArguments("--disable-web-security");
			options.addArguments("--remote-allow-origins=*");
//			options.setHeadless(false);
			driver = new ChromeDriver(options);
	        JSWaiter.setDriver(driver);
			api = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT);
		}
		else if (browserName.equalsIgnoreCase("chrome") && SecurtiyTest.equalsIgnoreCase("false")) {
			SecurityAssesment = "false";
			ChromeOptions options = new ChromeOptions();
			if (OS.contains("Window")) {
				FileUtils.cleanDirectory(new File(downloadPath));
				FileUtils.cleanDirectory(new File(TestRecordingdownloadPath));
				FileUtils.cleanDirectory(new File(ScreenshotsPath));
			WebDriverManager.chromedriver().browserVersion("128").setup();
			} else {
			WebDriverManager.chromedriver().clearDriverCache().driverVersion("104.0.5112.29").setup();
			}
			Map<String, Object> prefs = new HashMap<String, Object>();
			//System.setProperty("webdriver.chrome.verboseLogging", "true");
			prefs.put("download.default_directory", downloadPath);
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("safebrowsing.enabled", "false");
			// options.addArguments("--incognito");
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-application-cache");
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");
			options.setExperimentalOption("useAutomationExtension", false);
			// options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
			options.addArguments("--window-size=1920,1280");
			options.addArguments("--window-position=0,0");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-web-security");
			options.addArguments("disable-save-password-bubble");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		//	options.setHeadless(false);
			driver = new ChromeDriver(options);
			//driver.manage().deleteAllCookies();
			//JSWaiter.setDriver(driver);
		}
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(120));
		prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Environment.properties");
		prop.load(file); 
		QaUrl = prop.getProperty("ENV_QAS");
		String stgurl = prop.getProperty("ENV_STAGING");
		TestUrl = prop.getProperty("ENV_TEST");
		DevUrl = prop.getProperty("ENV_DEV");
		if (Environment.equalsIgnoreCase("QAS")) {
			driver.get(QaUrl);
			envVar = "https://qas-v2-api.wellcertified.com/api/";
			
		} else if (Environment.equalsIgnoreCase("STG")) {
			driver.get(stgurl);
			envVar = "https://stg-v2-api.wellcertified.com/api/";
		}
		else if (Environment.equalsIgnoreCase("DEV")) {
			driver.get(DevUrl);
			envVar = "https://dev-v2-api.wellcertified.com/api/";
		}
		
		else if (Environment.equalsIgnoreCase("TEST")) {
			do {
				driver.get(TestUrl);
				envVar = "https://test-v2-api.wellcertified.com/api/";
				// CommonMethod.waitForPageLoaded(120);
			} while (driver.getPageSource().contains("Project Error"));
		}
		RestAssured.baseURI = envVar;
		

//	        DevTools devTools = ((HasDevTools) driver).getDevTools();
//	        devTools.createSession();
	}

	@BeforeTest(alwaysRun = true)
	@Parameters({"ProjectType"})
	public void getTestNGTestName(final ITestContext testContext,@Optional("") String ProjectType) {
		TestNGTestName = testContext.getName();
		Projecttype = ProjectType;	
	}

	@BeforeMethod(alwaysRun = true)
	public static ExtentReports ExtentReportConfig() throws IOException {
		softAssert = new SoftAssert();
		pathprms.clear();
		if (extent == null) {
			final File CONF = new File(System.getProperty("user.dir") + "/src/main/resources/Extentconfig.json");
			extent = new ExtentReports();
			extent.setSystemInfo("Host Name", "Jenkins");
			extent.setSystemInfo("Environment", Environment);
			extent.setSystemInfo("User Name", "Abhishek Gupta");
			ExtentSparkReporter spark = new ExtentSparkReporter(
					System.getProperty("user.dir") + "/Report/WELL_AutomationReport" + /* TestNGTestName + */ ".html");
			extent.attachReporter(spark);
			spark.loadJSONConfig(CONF);
		}
		return extent;
	}
	
	public void StartTest(String TestcaseName, String Description) {
		
		testlog = extent.createTest(TestcaseName, "This Test is intented to verify -" + Description);
		System.out.println("Starting Test : " + TestNGTestName);
		System.out.println("Starting Test : " + TestCaseName);
		if(VideoRecording.equalsIgnoreCase("true")) {
	    ScreenRecorderUtil.startRecord(TestcaseName+"_" +Projecttype);
		}
	}

	public void logTestFailure() throws IOException, NumberFormatException, InterruptedException {

		testlog.log(Status.FAIL, MarkupHelper.createLabel(TestCaseName + " - Test Case Failed", ExtentColor.RED));
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
				.takeScreenshot(driver);
        File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
		int num = Integer.parseInt(CommonMethod.randomNumber(9999));
		ImageIO.write(screenshot.getImage(), "png", new File(filetest + File.separator + "Screenshots" + File.separator
				+ "_" + num + ".png"));
	}

	public static void captureScreenshot() throws IOException, InterruptedException {

		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage img = ImageIO.read(screen);
		File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
		ImageIO.write(img, "png", new File(
				filetest + File.separator + "Screenshots" + File.separator + CommonMethod.randomNumber() + ".png"));
	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws Exception {
	if(recordingStarted) {
	    ScreenRecorderUtil.stopRecord();
		}
		if (api != null && SecurityAssesment.equalsIgnoreCase("true")) {
			String Title = "WELL Security Report";
			String Template = "traditional-html";
			String Description = "Security Tests Intented to uncover vulnerabilities";
			String ReportFileName = "SecurityReport.html";
			String TargetFolder = System.getProperty("user.dir") + "/Report";
			response = api.reports.generate(Title, Template, null, Description, null, null, null, null, null,
					ReportFileName, null, TargetFolder, null);
			System.out.println("ZAP report generated at this location : " + response.toString());
		}
		
		if (result.getStatus() == ITestResult.FAILURE) {
			testlog.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			testlog.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			BufferedImage image = Shutterbug.shootPage(driver, Capture.FULL).getImage();
			File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
			ImageIO.write(image, "png", new File(filetest + File.separator + "Screenshots"
					+ File.separator /* +SuiteName+"_"+TestNGTestName +"-"+ */ + TestCaseName + ".png"));
		} else if (result.getStatus() == ITestResult.SKIP) {
			testlog.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			if(VideoRecording.equalsIgnoreCase("true")) {
			File dir = new File(TestRecordingdownloadPath);
			File[] dir_contents = dir.listFiles();
		for (int i = 0; i < dir_contents.length; i++) {
				if (dir_contents[i].getName().contains(TestCaseName+"_" +Projecttype)) {
				String path = TestRecordingdownloadPath+dir_contents[i].getName();
				File myObj = new File(path); 
			    if (myObj.delete()) { 
			      System.out.println("Deleted the file: " + myObj.getName());
			    } else {
			      System.out.println("Failed to delete the file.");
			    } 
			}
		}
		}
		}
	}

	@AfterClass(alwaysRun = true)
	public void flushReport() {
		extent.flush();
		System.out.println("EndClass");
	}

	@AfterTest(alwaysRun = true)
	public void quit() throws InterruptedException, IOException, ClientApiException {
		
		System.out.println("EndTest");
		try {
			FileUtils.cleanDirectory(new File(downloadPath));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		negativesoftAssert = new SoftAssert();
		negativeFieldSoftAssert = new SoftAssert();
	}

	@AfterSuite(alwaysRun = true)
	public void end() {
		if(recordingStarted) {
			ScreenRecorderUtil.stopRecord();
		}
		System.out.println("EndSuite");
		driver.quit();
	}
}