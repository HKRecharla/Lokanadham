package com.loka.Lokanadham.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utiltiy
{

	
	public static Properties readproperties(String filename) throws IOException
	{
		InputStream file = Utiltiy.class.getClassLoader().getResourceAsStream(filename);
		
		Properties prop = new Properties();
		prop.load(file);
		return prop;
		
	}
}
