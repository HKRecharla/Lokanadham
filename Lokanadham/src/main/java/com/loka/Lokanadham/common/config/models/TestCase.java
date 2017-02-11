package com.loka.Lokanadham.common.config.models;

public class TestCase 
{

	private String name;
	private Test_priority Priority;
	private Test_Type Type;
	
	public static  enum Test_priority
	{
		P0,P1,P2;
	}
	
	public static enum Test_Type
	{
		FUNCTIONAL,SMOKE,REGRESSION,ACCEPTANCE;
	}
	
	public String getname()
	{
		return name;
		
	}
	
	public void setname()
	{
		this.name = name;
	}
	
	
	public Test_priority getprioty()
	{
		return Priority;
	}
	
	public void setPriority(Test_priority priority)
	{
		this.Priority=Priority;
	}
	
	public Test_Type gettype()
	{
		return Type;
	}
	
	public void setType(Test_Type type)
	{
		this.Type=Type;
	}
	
}
