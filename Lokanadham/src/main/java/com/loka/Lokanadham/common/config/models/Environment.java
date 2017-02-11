package com.loka.Lokanadham.common.config.models;

public enum Environment 
{

	QA("qa"),PROD("prod"),STAGE("prod");
	
	
	private String name;

	private Environment (String name)
	{
		this.name = name;
	}
	
	public String getname()
	{
		return name;
	}
	
	
}
