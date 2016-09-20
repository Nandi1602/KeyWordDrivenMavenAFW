package delta.main;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class KeyWord {
	
	public static void executeKeyWord(WebDriver driver, String action, String input1, String input2, ExtentTest testReport){
		
		if(action.equalsIgnoreCase("enter")){
			//Call getLocator static method of Locator class to get the locator value
			//System.out.println(Locator.getLocaor(input1));
			
			/*testReport.log(LogStatus.INFO, "Entering the text in the input field: " + Locator.getLocaor(input1));
			driver.findElement(Locator.getLocaor(input1)).sendKeys(input2);*/
			KeyWordLibraries.enter(driver, action, input1, input2, testReport);
			
		}
		else if(action.equalsIgnoreCase("click")){
			//driver.findElement(By.xpath(input1)).click();
			//Thread.sleep(3000);
			//System.out.println(Locator.getLocaor(input1));
			
			/*boolean flag=driver.findElement(Locator.getLocaor(input1)).isEnabled();
			System.out.println(flag);
			testReport.log(LogStatus.INFO, "Clicking on the Element: " + Locator.getLocaor(input1));
			driver.findElement(Locator.getLocaor(input1)).click();*/
			KeyWordLibraries.click(driver, action, input1, input2, testReport);
		}
		else if(action.equalsIgnoreCase("wait")){
			/*try{
				Thread.sleep(Long.parseLong(input1));
				System.out.println("Waiting for " + input1 + "seconds");
			}catch (Exception e){
				e.printStackTrace();
			}*/
			KeyWordLibraries.wait(driver, action, input1, input2, testReport);
		}
		else if(action.equalsIgnoreCase("verifyElementPresent")){
			/*int size = driver.findElements(Locator.getLocaor(input1)).size();
			if(size>0){
				testReport.log(LogStatus.INFO, "No of Elements present in the page: " + size);
				if(driver.findElement(Locator.getLocaor(input1)).isDisplayed()){
					testReport.log(LogStatus.INFO, "Element " + Locator.getLocaor(input1) + " is displayed in the page");
				}
			}
			else{
				testReport.log(LogStatus.FAIL, "element is not present in the page: " + size);
				
			}*/
			KeyWordLibraries.verifyElementPresent(driver, action, input1, input2, testReport);
		}
		else if(action.equalsIgnoreCase("VerifyText")){
			//String actualText = driver.findElement(Locator.getLocaor(input1)).getAttribute("value");
			/*String actualText = driver.findElement(Locator.getLocaor(input1)).getText();
			String expectedText = input2;
			Assert.assertEquals(actualText, expectedText);*/
			KeyWordLibraries.verifyText(driver, action, input1, input2, testReport);
			
		}
		else if(action.equalsIgnoreCase("VerifyDisappearingText")){
			KeyWordLibraries.verifyDisappearingText(driver, action, input1, input2, testReport);
			
		}
		else if(action.equalsIgnoreCase("GetText")){
			/*//String actualText = driver.findElement(Locator.getLocaor(input1)).getAttribute("value");
			String actualText = driver.findElement(Locator.getLocaor(input1)).getText();
			//String expectedText = input2;
			testReport.log(LogStatus.INFO, "Emplyoee ID: " + actualText);*/
			KeyWordLibraries.getText(driver, action, input1, input2, testReport);
					
		}
		
		else if(action.equalsIgnoreCase("SelectFileToUpload")){
			/*String absoluteFilePath=new File(input2).getAbsolutePath();
			System.out.println("Abolute Path of the Image is "+ absoluteFilePath);
			driver.findElement(Locator.getLocaor(input1)).sendKeys(absoluteFilePath);
			testReport.log(LogStatus.INFO, "Profile Picture is uploaded");*/
			KeyWordLibraries.selectFileToUpload(driver, action, input1, input2, testReport);
			
		}
		else if(action.equalsIgnoreCase("verifyImage"))
		{
			KeyWordLibraries.verifyImage(driver, action, input1, input2, testReport);
		}
		else if(action.equalsIgnoreCase("verifyElementColor"))
		{
			KeyWordLibraries.verifyElementColor(driver, action, input1, input2, testReport);
		}
		else if(action.equalsIgnoreCase("VerifyTextContains"))
		{
			KeyWordLibraries.verifyTextContains(driver, action, input1, input2, testReport);
		}
		else{
			testReport.log(LogStatus.FAIL, "invalid action code" + action);
			}
		}
	}