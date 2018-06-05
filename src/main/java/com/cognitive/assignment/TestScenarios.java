package com.cognitive.assignment;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class TestScenarios {

	public static WebDriver driver;
	
	@BeforeClass
	public static void initDriver() throws Exception
	{
		if(System.getProperty("browser") != null)
		{
		
			if(System.getProperty("browser").equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\resources\\geckodriver.exe");
				driver=new FirefoxDriver();
			}
			else if(System.getProperty("browser").equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\resources\\chromedriver.exe");
				driver=new ChromeDriver();
			}
			else if(System.getProperty("browser").equals("ie"))
			{
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\resources\\IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}
			else
			{
				throw new Exception("Browser not sent is not available");
			}
		}
		else
		{
			throw new Exception("Browser value is not sent in args");
		}
		
		driver.manage().window().maximize();
		
	}
	
	//Test case for login module in case study
	//@Test
	public void validateLogin()
	{	
		driver.get("https://www.gmail.com");
		driver.findElement(By.id("Email")).sendKeys("prashantcognitive@gmail.com");
		driver.findElement(By.id("next")).submit();
		
		//applied explicit wait till password text is rendered
		WebDriverWait wait = new WebDriverWait(driver, 20) ;
		WebElement passwordField;
		passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/input[@id='Passwd' and @placeholder='Password']")));						
		passwordField.sendKeys("Pras@1234");
		driver.findElement(By.xpath("//*[@id='signIn']")).submit();
		
				
		//wait untill compose button is rendered after login
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='button' and text()='COMPOSE']")));	
		
		if(driver.findElement(By.xpath("//a[@title='Gmail']")).isDisplayed() && driver.findElement(By.xpath("//*[text()='Primary' and @id=':2i']")).isDisplayed())
		{
			System.out.println("login successful");
		}
		else
		{
			Assert.fail("login failed");
		}
		
	}
	
	//validate text hover of a menu item will change the css 
	//@Test
	public void validateMenu()
	{
		
		driver.get("https://www.cognitivescale.com/");
		
		WebElement mainmenuelem=driver.findElement(By.xpath("//header//a[@href='https://www.cognitivescale.com/technology/' and text()='Technology']"));
		WebElement subelment=driver.findElement(By.xpath("//header//a[text()='Commerce']"));
		if(mainmenuelem.isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(mainmenuelem).click().moveToElement(subelment).click();
		
		}
		else
		{
			Assert.fail("failed to get submenu from mainmenu");
		}
		
	}
	
	
	//validate text hover of a menu item will change the css 
		//@Test
		public void validateMouseover()
		{
			
			driver.get("https://www.cognitivescale.com/");
			
			WebElement seemorebtn=driver.findElement(By.xpath("//a[@href='/products/#engage']"));
			seemorebtn.click();
			
			String h2text=driver.findElement(By.xpath("//*[@id='engage']/div[1]/h2")).getText();
			if(!h2text.contains("LEARN, ENGAGE, BUILD"))
			{
				Assert.fail("failed to navigate to invalid moreover location");
			
			}
			
		}
	

				
				//validate color,aligment and  font of element
				//@Test
				public void readaligmentFontColorEle(){
					 
				 driver.get("https://unifiedportal.insideview.com/unifiedportal/");
				 
				 
				 WebDriverWait wait = new WebDriverWait(driver, 20) ;
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Sign In']")));
				 
				 WebElement signintext = driver.findElement(By.xpath("//label[text()='Sign In']"));
				 
				 //Read font-size property and print It In console.
				 String fontSize = signintext.getCssValue("font-size");
				 System.out.println("Font Size -> "+fontSize);
				 
				 //Read color property and print It In console.
				 String fontColor = signintext.getCssValue("color");
				 System.out.println("Font Color -> "+fontColor);
				 
				 //Read margin-top property and print It In console.
				 String margintop = signintext.getCssValue("margin-top");
				 System.out.println("margin-top in px -> "+margintop);
				 
				 Assert.assertEquals(fontSize, "30px");
				 Assert.assertEquals(fontColor, "rgba(51, 51, 51, 1)");
				 Assert.assertEquals(margintop, "22px");
				 
				 
				 //validate footer elements
				 
				 WebElement footersupportlink = driver.findElement(By.xpath("//*[@id='footLinks']/a[text()='Support']"));
				 
				 //Read font-size property and print It In console.
				 String footerelemetbackground = footersupportlink.getCssValue("background-color");
				 System.out.println("background-color -> "+footerelemetbackground);

				 Assert.assertEquals(footerelemetbackground, "transparent");

				}
				
					
	
	@AfterClass
	public static void closeDriver(){
		driver.quit();
	}
	
	
}

