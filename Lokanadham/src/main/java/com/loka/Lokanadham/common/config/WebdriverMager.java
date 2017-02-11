package com.loka.Lokanadham.common.config;

import org.openqa.selenium.WebDriver;

public class WebdriverMager 
{

	public static void quitWebdriver()
	{
		
	}
	
	public static WebDriver getWebdriverFromContext()
	{
		return (WebDriver)ContextManger.getThreadLevelContext().getParameter(TestContext.WEBDRIVER);
	}
	
}
