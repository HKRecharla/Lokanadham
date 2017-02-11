package com.loka.Lokanadham.common.config;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.sun.jna.platform.win32.GL;


public class ContextManger
{

	public static TestContext globalcontext = new TestContext();
	public static ThreadLocal<TestContext> threadlocal = new ThreadLocal<TestContext>();
	private static Map<XmlTest, TestContext> testlevel = new HashMap<XmlTest, TestContext>();
	
	
	public static void initglobal(ITestContext context)
	{
	globalcontext.buildtestcontext(context);
	}
	
	public static void initThreadlocal(ITestContext context)
	{
		if (threadlocal.get() == null) 
		{
		threadlocal.set(new TestContext());	
		threadlocal.get().buildtestcontext(context);
		}
	}
	
	public static void intitTestLevel(ITestContext context)
	{
		testlevel.put(context.getCurrentXmlTest(), new TestContext());
		testlevel.get(context.getCurrentXmlTest()).buildtestcontext(context);
	}
	
	public static TestContext getglobalcontext()
	{
		return globalcontext;
	}
	
	public static TestContext getThreadLevelContext() {
		return threadlocal.get();
	}

	public static TestContext getTestLevelContext() {
		return testlevel.get(((ITestContext) getThreadLevelContext()
				.getParameter(TestContext.TESTNG_CONTEXT))
				.getCurrentXmlTest());
	}
	
	public static TestContext getTestLevelContext(ITestContext context) {
		return testlevel.get(context.getCurrentXmlTest());
	}
	
	
}
