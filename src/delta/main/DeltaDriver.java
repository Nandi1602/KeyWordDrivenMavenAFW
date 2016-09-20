package delta.main;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.LogStatus;

import generics.Excel;
import generics.Property;
import generics.Utility;

public class DeltaDriver extends BaseDriver {
	
	public String browser;
	public URL remoteAddress=null;
	
	@BeforeMethod
	public void launchApp(XmlTest x){
		String appURL=Property.getPropertyValue(configPptPath, "URL");
		String timeOut=Property.getPropertyValue(configPptPath, "TimeOut");
		browser=x.getParameter("browser");
		System.out.println(browser);

		//Running tests on remote machine - HUB configuration
		/*try{
			remoteAddress = new URL(x.getParameter("hubURL")); //get the HUB URL from testNG xml
			System.out.println("HUB URL: " + remoteAddress);
		}catch(Exception e){
			
		}
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(browser);
		dc.setPlatform(Platform.ANY);
		
		//dc.setVersion("46");
		//dc.setPlatform(Platform.LINUX);
		System.out.println(browser);
		
		driver = new RemoteWebDriver(remoteAddress,dc);*/
		
		
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			System.out.println("Opening Google Chrome Browser");
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("internetexplorer")){
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			System.out.println("Opening InternetExplorer Browser");
			driver=new InternetExplorerDriver();
		}
		else{
			System.out.println("Opening Firefox Browser");
			driver=new FirefoxDriver();					
		}
		
		driver.manage().timeouts().implicitlyWait(Long.parseLong(timeOut), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);
		
	}
	
	//@Test(dataProviderClass=BaseDriver.class, dataProvider="getScenarios") - Used when data provider method is static and declared in different class
	@Test(dataProvider="getScenarios")
	public void testScenarios(String scenarioSheet, String executionStatus){
		//String scenariosSheet="Scenario1";
		testReport=eReport.startTest(browser + "_" + scenarioSheet);
		
		if(executionStatus.equalsIgnoreCase("yes")){	
		int stepCount=Excel.getRowCount(scenariosPath, scenarioSheet);
		testReport.log(LogStatus.INFO, "Executing the Scenario " +scenarioSheet);
		for(int i=1;i<=stepCount;i++){
			String description=Excel.getCellValue(scenariosPath, scenarioSheet, i, 0);
			String action=Excel.getCellValue(scenariosPath, scenarioSheet, i, 1);
			String input1=Excel.getCellValue(scenariosPath, scenarioSheet, i, 2);
			String input2=Excel.getCellValue(scenariosPath, scenarioSheet, i, 3);
			String msg="description: "+description+" action: "+action+" input1: "+input1+" input2: "+input2;
			testReport.log(LogStatus.INFO, msg);
			KeyWord.executeKeyWord(driver, action, input1, input2,testReport);
			//Assert.fail();
			}
		}
		else{
			testReport.log(LogStatus.SKIP, "Execution Status is 'NO'");
			throw new SkipException("Skipping Scenario as execution status is 'NO'"); //to show the test status as skipped in testNG reports
		}
		if(testReport.getRunStatus()==LogStatus.FAIL)
		{
			System.out.println("test case failed");
			Assert.fail();
		}
}
	
	@AfterMethod
	public void quitApp(ITestResult test){
		//if(test.getStatus()==ITestResult.FAILURE)
		if(test.getStatus()==2){
			String pImage=Utility.getPageScreenShot(driver, imageFolderPath);
			System.out.println("Screenshot File Name: "+pImage);
			String p=testReport.addScreenCapture("."+pImage); //to add the HTML code in the report to add image
			testReport.log(LogStatus.FAIL, "Page Screen Shot: "+p); //Show the image in HTML report
		}

		eReport.endTest(testReport);
		driver.close();
		
		
	}
		

}
