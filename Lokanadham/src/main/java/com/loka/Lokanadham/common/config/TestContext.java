package com.loka.Lokanadham.common.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.apache.lucene.analysis.util.CharArrayMap.EntrySet;
import org.testng.ITestContext;

import com.loka.Lokanadham.common.config.models.Browser;
import com.loka.Lokanadham.common.config.models.Environment;
import com.loka.Lokanadham.common.utils.Utiltiy;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.val;

public class TestContext 
{

	public static final String ENVIRONMENT = "environment";
	public static final String RETRY_COUNT = "retry_count";
	public static final String BROWSER = "browser";
	public static final String TESTNG_CONTEXT = "testng_context";
	public static final String DOWNLOAD_DIR = "download_dir";
	public static final String FIREFOX_PROFILE = "ff_profile";
	public static final String WEBDRIVER_TIMEOUT = "webdriver_timeout";
	public static final String TESTNG_REPORT_DIR = "testng_report_dir";
	public static final String HTML_FILE_DIR = "html_file_dir";
	public static final String SCREENSHOTS_DIR = "screenshots_dir";
	public static final String RETRY_ENABLED = "retry_enabled";
	public static final String ENABLE_LOGS = "enable_logs";
	public static final String ENABLE_REPORTING = "enable_reporting";
	public static final String API_VERSION = "api_version";
	public static final String PLATFORM = "platform";
	public static final String WEBDRIVER = "webdriver";
	public static final String CHROME_DRIVER_SERVICE = "chrome_driver_service";
	public static final String CHROME_DRIVER_PATH = "chrome_driver_path";

	// Android configurations
	public static final String APP_LOCATION = "path_to_app";
	public static final String APK_NAME = "apk_name";
	public static final String ANDROID_VERSION = "android_version";
	public static final String APPIUM_SERVER = "appium_server";
	public static final String DEVICE_NAME = "deiveName";
	public static final String ANDROID_DEVICE_ID = "deviceId";
	public static final String APP_PACKAGE = "app_package";
	public static final String APP_LAUNCH_ACTIVITY = "app_launch_activity";
	public static final String APP_WAIT_ACTIVITY = "app_wait_activity";
	
	//IOS Configurations
	
	public static final String IOS_DEVICE_ID = "deviceId";
	
	
	private Map<String , Object> context = new HashMap<String,Object>();
	
	public Boolean isempty()
	{
		return (context.size() > 0 ? false : true) ;
	}
	
	protected void LoadProperties(Properties properties)
	{
		for (Entry<Object, Object> e : properties.entrySet()) 
		{
			context.put(e.getKey().toString(), e.getValue());
		}
	}
	
	public Object getParameter(String key)
	{
		return context.get(key);
	}
	
	
	public Environment getEnvironemt()
	{
		return Environment.valueOf((String)getParameter(ENVIRONMENT));
	}
	
	public void setvalue(String key,Object value)
	{
		
		context.put(key, value);
	}
	
	public String getvalueFromContext(ITestContext context, String key)
	{
		try 
		{
			return context.getSuite().getXmlSuite().getParameter(key);
		} catch (Exception e) {
			return null;
		}	
	}
	
	public void setAttribute(String key,Object value)
	{
		context.put(key, value);
	}
	
	
	public void setAttribute(ITestContext context,String key,Object value)
	{
		setAttribute(key, getvalueFromContext(context, key) != null ? getvalueFromContext(context, key) : value );
		
	}
	public String getValueasString(String key)
	{
		Object val  = getParameter(key);
		return val != null ? val.toString() : null;
	}
	
	
	public void buildtestcontext(ITestContext context)
	{
		setvalue(TESTNG_CONTEXT, context);
		setAttribute(context, ENVIRONMENT,"QA");
		
		try 
		{
		switch (Environment.valueOf(getValueasString(ENVIRONMENT))) 
		{
		case QA:
		{
			LoadProperties(Utiltiy.readproperties("qa.properites"));
			break;
		}
		
		case PROD:
		{
			LoadProperties(Utiltiy.readproperties("prod.properties"));
			break;
		}
		case STAGE:
		{
			LoadProperties(Utiltiy.readproperties("stage.properties")); 
			break;
		}

		default:
			break;
		}
			

		} catch (Exception e) {}
		
		
		setAttribute(context, RETRY_COUNT, "2");
		setAttribute(context, BROWSER, Browser.FIREFOX);
		setAttribute(context, PLATFORM, "DESKTOP");

		// setting web timeout in ms
		setAttribute(context, WEBDRIVER_TIMEOUT, "60000");

		setAttribute(context, ENABLE_LOGS, true);
		setAttribute(context, ENABLE_REPORTING, true);

		// setting default report directory
		setAttribute(context, TESTNG_REPORT_DIR, System.getProperty("user.dir")
				+ "/test-output/");

		setAttribute(context, RETRY_ENABLED, true);
		setAttribute(context, DEVICE_NAME, "Android Emulator");
		
		Map<String, String > params = context.getSuite().getXmlSuite().getAllParameters();
		
		if (params != null) 
		{
			for (Entry<String, String> e : params.entrySet()) 
			{
			setvalue(e.getKey(), e.getValue());
				
			}
		}
		
		
		
		
		
	}
	
	
	
	public Browser getBrowser()
	{
		Browser browser = null;
		
		try {
			
		} catch (Exception e) {
			Browser.valueOf(this.getValueasString(TestContext.BROWSER));
		}
		
		return null;
		
	}

	public AppiumDriverLocalService getAppiumDriver()
	{
		return (AppiumDriverLocalService)getParameter(TestContext.APPIUM_SERVER);
	}
	
}
