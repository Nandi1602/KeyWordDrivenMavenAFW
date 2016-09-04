package delta.main;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class KeyWord {
	
	public static void executeKeyWord(WebDriver driver, String action, String input1, String input2, ExtentTest testReport){
		
		if(action.equalsIgnoreCase("enter")){
			//Call getLocator static method of Locator class to get the locator value
			//System.out.println(Locator.getLocaor(input1));
			testReport.log(LogStatus.INFO, "Entering the text in the input field: " + Locator.getLocaor(input1));
			driver.findElement(Locator.getLocaor(input1)).sendKeys(input2);
			
			
		}
		else if(action.equalsIgnoreCase("click")){
			//driver.findElement(By.xpath(input1)).click();
			//Thread.sleep(3000);
			//System.out.println(Locator.getLocaor(input1));
			boolean flag=driver.findElement(Locator.getLocaor(input1)).isEnabled();
			System.out.println(flag);
			testReport.log(LogStatus.INFO, "Clicking on the Element: " + Locator.getLocaor(input1));
			driver.findElement(Locator.getLocaor(input1)).click();
			
		}
		else{
			System.out.println("Invalid action: " + action);
		}
	}

}