package delta.main;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import generics.Excel;

public class BaseDriver implements AutomationConstants{
	public WebDriver driver;
	//declare the extent report as static as it was creating multiple instances during report creation and it was not consolidated status. Always latest report was showing.
	public static ExtentReports eReport;
	public ExtentTest testReport;
	public String suiteSheet="Suite";
	
		
	@DataProvider
	public String[][] getScenarios(){
		//2 Dimensional Array
		//No of Rows represents how many times the test method has to run
		//No of Columns represents how many parameters it takes
		int scenarioCount=Excel.getRowCount(controllerPath, suiteSheet); //get the number of scenarios present in suite file
		System.out.println("Total Number of Scenarios to be executed from SUITE file: "+scenarioCount);
		String[][] data = new String[scenarioCount][2];
		
		/* Reading single data from excel
		String scenarioName=Excel.getCellValue(controllerPath, suiteSheet, 1, 0);
		String executionStatus=Excel.getCellValue(controllerPath, suiteSheet, 1, 1);
		data[0][0]=scenarioName;
		data[0][1]=executionStatus;
		*/
		
		//Reading data from controller dynamically
		for(int i=1;i<=scenarioCount;i++){
			String scenarioName=Excel.getCellValue(controllerPath, suiteSheet, i, 0);
			String executionStatus=Excel.getCellValue(controllerPath, suiteSheet, i, 1);
			data[i-1][0]=scenarioName;
			data[i-1][1]=executionStatus;
			System.out.println(data[i-1][0]);
			System.out.println(data[i-1][1]);
		}
		
		return data;
	}
	
	@BeforeTest
	public void initFrameWork(){
		eReport=new ExtentReports(reportFilePath);
				
	}
	
	@AfterTest
	public void endFrameWork(){
		eReport.flush();
	}
}
