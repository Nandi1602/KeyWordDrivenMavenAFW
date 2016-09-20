package delta.main;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import generics.Utility;

public class KeyWordLibraries {
	
	public static void enter(WebDriver driver, String action, String input1, String input2, ExtentTest testReport){
		//Call getLocator static method of Locator class to get the locator value
		//System.out.println(Locator.getLocaor(input1));
		testReport.log(LogStatus.INFO, "Entering the text in the input field: " + Locator.getLocaor(input1));
		driver.findElement(Locator.getLocaor(input1)).sendKeys(input2);
	}
	
	public static void click(WebDriver driver, String action, String input1, String input2, ExtentTest testReport){
		//driver.findElement(By.xpath(input1)).click();
		//Thread.sleep(3000);
		//System.out.println(Locator.getLocaor(input1));
		boolean flag=driver.findElement(Locator.getLocaor(input1)).isEnabled();
		System.out.println(flag);
		testReport.log(LogStatus.INFO, "Clicking on the Element: " + Locator.getLocaor(input1));
		driver.findElement(Locator.getLocaor(input1)).click();
	}
	
	public static void wait(WebDriver driver, String action, String input1, String input2, ExtentTest testReport){
		try{
			Thread.sleep(Long.parseLong(input1));
			System.out.println("Waiting for " + input1 + "seconds");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void verifyElementPresent(WebDriver driver, String action, String input1, String input2, ExtentTest testReport){
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
		List<WebElement> elements = driver.findElements(Locator.getLocaor(input1));
		if(elements.size()> 0 && elements.get(0).isDisplayed() )
		{
			testReport.log(LogStatus.PASS,"Element is Present and Displayed");
		}
		else
		{
			testReport.log(LogStatus.FAIL,"Element is Not Present or Displayed");
		}
	}
	
	public static void verifyImage(WebDriver driver,String action,String input1,String input2,ExtentTest testReport)
	{
		String actualImageComplete=Utility.getPageScreenShot(driver, AutomationConstants.ActualImageFolder);
		String expectedImage=AutomationConstants.ExpectedImageFolder+"/"+input2;
		
		int x=driver.findElement(Locator.getLocaor(input1)).getLocation().getX();
		int y=driver.findElement(Locator.getLocaor(input1)).getLocation().getY();
		int width=driver.findElement(Locator.getLocaor(input1)).getSize().getWidth();
		int height=driver.findElement(Locator.getLocaor(input1)).getSize().getHeight();
		String actualImage=Utility.cropImage(actualImageComplete,x,y,width, height, AutomationConstants.ActualImageFolder);
		
		if(Utility.compareImage(actualImage,expectedImage))
		{
			String p = testReport.addScreenCapture("."+actualImage);
			testReport.log(LogStatus.PASS,"Image Comparission is sucessful, Page screen shot is:"+p);
		}
		else
		{
			String e=testReport.addScreenCapture("."+expectedImage);
			testReport.log(LogStatus.FAIL,"Expected image is:"+e);
			String a=testReport.addScreenCapture("."+actualImage);
			testReport.log(LogStatus.FAIL,"Actual image is:"+a);
		}
	}
	
	public static void verifyDisappearingText(WebDriver driver, String action, String input1, String input2, ExtentTest testReport){
		//String actualText = driver.findElement(Locator.getLocaor(input1)).getAttribute("innerHTML");
		String actualText = driver.findElement(Locator.getLocaor(input1)).getText();
		String expectedText = input2;
		System.out.println("*****" + actualText + "********");
		testReport.log(LogStatus.INFO, "actual text: " + actualText);
		testReport.log(LogStatus.INFO, "expected text: " + expectedText);
		if(actualText.contains(input2)){
			testReport.log(LogStatus.INFO, input2 + " message is displayed" + " in the page");
		}
		else{
			testReport.log(LogStatus.INFO, input2 + " message is not displayed" + " in the page");
		}
	}
	
	public static void verifyText(WebDriver driver, String action, String input1, String input2, ExtentTest testReport){
		//String actualText = driver.findElement(Locator.getLocaor(input1)).getAttribute("value");
		String actualText = driver.findElement(Locator.getLocaor(input1)).getText();
		String expectedText = input2;
		Assert.assertEquals(actualText, expectedText);
		
	}
	
	public static void selectFileToUpload(WebDriver driver, String action, String input1, String input2, ExtentTest testReport){
		String absoluteFilePath=new File(input2).getAbsolutePath();
		System.out.println("Abolute Path of the Image is "+ absoluteFilePath);
		driver.findElement(Locator.getLocaor(input1)).sendKeys(absoluteFilePath);
		testReport.log(LogStatus.INFO, "Profile Picture is uploaded");
		
	}
	
	public static void getText(WebDriver driver, String action, String input1, String input2, ExtentTest testReport){
		//String actualText = driver.findElement(Locator.getLocaor(input1)).getAttribute("value");
		String actualText = driver.findElement(Locator.getLocaor(input1)).getText();
		//String expectedText = input2;
		testReport.log(LogStatus.INFO, "Emplyoee ID: " + actualText);
		
	}
	
	public static void verifyTextContains(WebDriver driver,String action,String input1,String input2,ExtentTest testReport)
	{
		String text_getText=driver.findElement(Locator.getLocaor(input1)).getText();
		String text_innerHTML=driver.findElement(Locator.getLocaor(input1)).getAttribute("innerHTML");
		String text_textContent=driver.findElement(Locator.getLocaor(input1)).getAttribute("textContent");
		testReport.log(LogStatus.INFO, "Expected text is:"+input2);
		testReport.log(LogStatus.INFO, "getText:"+text_getText);
		testReport.log(LogStatus.INFO, "innerHTML:"+text_innerHTML);
		testReport.log(LogStatus.INFO, "textContent:"+text_textContent);
		if(text_getText.contains(input2))
		{	
			testReport.log(LogStatus.PASS,"Verified using getText; Actual text contains Expected text");
		}
		else if(text_innerHTML.contains(input2))
		{	
			testReport.log(LogStatus.PASS,"Verified using innerHTML; Actual text contains Expected text");
		}
		else if(text_textContent.contains(input2))
		{	
			testReport.log(LogStatus.PASS,"Verified using textContent; Actual text contains Expected text");
		}
		else
		{
			testReport.log(LogStatus.FAIL,"Actual text DO NOT contains Expected text");
		}
	}
	
	public static void verifyElementColor(WebDriver driver,String action,String input1,String input2,ExtentTest testReport)
	{
		
		String strRGB=driver.findElement(Locator.getLocaor(input1)).getCssValue("color");
		testReport.log(LogStatus.INFO,"RGB is:"+strRGB);
		String hex=Utility.convertRGBtoHex(strRGB);
		
		String msg1="<span style='color:"+input2+";'>Expected color</span>";
		testReport.log(LogStatus.INFO,"HTML",msg1);
		
		String msg2="<span style='color:"+hex+";'>Actual color</span>";
		testReport.log(LogStatus.INFO,"HTML",msg2);
		
		
		if(hex.equals(input2))
		{
			testReport.log(LogStatus.PASS,"Element color is matching");
		}
		else
		{
			testReport.log(LogStatus.FAIL,"Element color is not matching");
		}
	}
	
	
}
