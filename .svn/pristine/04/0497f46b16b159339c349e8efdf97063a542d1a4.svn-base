package delta.main;

import org.openqa.selenium.By;

public class Locator {
	/*This method is used to extract the locator type and name and reurn the locator*/
	public static By getLocaor(String input1)
	{
		By b=null;
		String[] str = input1.split("_");
		String locator=str[0];
		String value=str[1];
		if(locator.equalsIgnoreCase("ID")){
			b=By.id(value);
		}
		else if(locator.equalsIgnoreCase("NAME")){
			b=By.name(value);
		}
		else if(locator.equalsIgnoreCase("XPATH")){
			b=By.xpath(value);
		}
		else if(locator.equalsIgnoreCase("LINKTEXT")){
			b=By.linkText(value);
		}
		else if(locator.equalsIgnoreCase("PARTIALLINKTEXT")){
			b=By.partialLinkText(value);
		}
		else if(locator.equalsIgnoreCase("CLASSNAME")){
			b=By.className(value);
		}
		else if(locator.equalsIgnoreCase("CSSSELECTOR")){
			b=By.cssSelector(value);
		}
		else if(locator.equalsIgnoreCase("TAGNAME")){
			b=By.tagName(value);
		}
		return b;
	}

}