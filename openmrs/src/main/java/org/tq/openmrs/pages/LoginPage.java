package org.tq.openmrs.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tq.util.BaseClass;

public class LoginPage {


	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public void login(String username,String password) throws IOException
	{
		//login
				BaseClass.screenshotEx(driver,"LoginPage.jpg");
				driver.findElement(By.id("username")).sendKeys(username);
				driver.findElement(By.id("password")).sendKeys(password);
				driver.findElement(By.id("Inpatient Ward")).click();
				driver.findElement(By.id("loginButton")).click();
				
	}
}
