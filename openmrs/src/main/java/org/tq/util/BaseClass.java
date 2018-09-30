package org.tq.util;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class BaseClass {
	

	protected WebDriver driver;
	@Parameters({"url","browserType"})
	@BeforeTest
	public void launchApplication(String url,String browserType)
	{
		switch(browserType) 
		{	
			case "FF":
				System.out.println("in FF");
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			case "IE":
				System.out.println("in IE");
				System.setProperty("webdriver.ie.driver","IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				//zoom set to 100%
				//Enabled protected mode for all the zones..
				break;
			case "CH":
				System.out.println("in CH");
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
				break;
			default:
				break;
		}
		driver.get(url);
	}
	protected static String[][] readXLSFile(String fileName,String sheetName) throws BiffException, IOException
	{
		File f = new File(fileName);
		Workbook wb = Workbook.getWorkbook(f);
 		Sheet sh = wb.getSheet(sheetName);
		int rows = sh.getRows();
		int cols = sh.getColumns();
		
		String arr [] [] = new String[rows][cols];
		for(int i =0 ; i < rows ;i++)
		{
			
			for(int j =0 ; j<cols;j++)
			{
				Cell cell = sh.getCell(j,i);
				arr[i][j]= cell.getContents();
				System.out.println("Values stored in Array "+ arr[i][j]);
			}
			
		}
		return arr;
	}
	public static void screenshotEx(WebDriver driver,String destLoc) throws IOException
	{
		TakesScreenshot tsh = (TakesScreenshot) driver;
	 	File srcFile = tsh.getScreenshotAs(OutputType.FILE);
	 	System.out.println("Location ::" + srcFile.getAbsolutePath());
	 	//FileUtils is having methods to copy teh file from one location to antoher location
	 	//commons-io
	 	FileHandler.copy(srcFile,new File(destLoc));
	 	
	 	
	}
	
	
	
	
	
	
	
	
	

}
