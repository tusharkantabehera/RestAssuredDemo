package net.assuresign.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Base {
	
	public static Properties prop;
	public static Logger log;
	
	public Base()
	{
		log=Logger.getLogger("Assuresign");
		FileInputStream fis;
		
		
		try 
		{
			fis = new FileInputStream(".\\src\\main\\java\\net\\assuresign\\configs\\QA.properties");
			prop=new Properties();
			prop.load(fis);
			log.info("Properties file loaded");
			
//			String browserName=prop.getProperty("browser");
			
		} 
		catch (FileNotFoundException e) 
		{
			log.error("Properties file not found");
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
