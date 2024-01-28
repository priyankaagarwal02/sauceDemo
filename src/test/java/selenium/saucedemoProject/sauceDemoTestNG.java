package selenium.saucedemoProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class sauceDemoTestNG 
{
	public static WebDriver driver;

	// Parameterization for all elements
	public String userNameValue = "standard_user";
	public String passwordValue = "secret_sauce";
	public String expectedTitleValue = "Swag Labs";
	public String expectedHeaderValue = "Products";
	public String firstNameValue = "Priya";
	public String lastNameValue = "Sharma";
	public String zipCodeValue = "201305";

	@BeforeClass
	public void launchApplication() 
	{
		// Set the system property for the chrome driver
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		// Creating instance of Chrome driver
		driver = new ChromeDriver();
		// Maximize the application
		driver.manage().window().maximize();
		// Adding for execution purpose - pageLoadTimeout
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Launch the application : https://www.saucedemo.com/
		// Storing the Application URL in the String variable
		String url = "https://www.saucedemo.com/";
		// Launch the application
		driver.get(url);
		Reporter.log("Application sauceDemo is launched successfully");
		// Adding for execution purpose - pageLoadTimeout
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void closeApplication() 
	{
		// Close the application
		driver.close();
		Reporter.log("Application is closed");
	}

	@Test(priority = 1)
	public void loginApplicationSite() 
	{
		// Implicitly wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Verify login functionality works and log into the app
		WebElement userNameLocator = driver.findElement(By.id("user-name"));
		WebElement passwordLocator = driver.findElement(By.id("password"));
		if ((userNameLocator.isEnabled()) && (passwordLocator.isEnabled())) 
		{
			Reporter.log("Username and Password are enabled");
			// Enter User Name and Password
			userNameLocator.sendKeys(userNameValue);
			passwordLocator.sendKeys(passwordValue);
			Reporter.log("Username and password are entered successfully");
		} 
		else 
		{
			Reporter.log("Username and password are disabled");
		}
		// Adding for execution purpose - pageLoadTimeout
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Verify Login button is displayed or not
		WebElement loginLocator = driver.findElement(By.xpath("//input[@id='login-button']"));
		if (loginLocator.isDisplayed()) 
		{
			Reporter.log("Login button is displayed");
			// Click Login button
			loginLocator.click();
			Reporter.log("Login button is clicked successfully");
			
			// Adding for execution purpose - pageLoadTimeout
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

			// Confirm the user is taken to the products page
			String actualTitleValue = driver.getTitle();
			WebElement actualHeaderValue = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
			String message = actualHeaderValue.getText();
			if ((actualTitleValue.equals(expectedTitleValue)) && (message.equals(expectedHeaderValue)))
				Reporter.log("User is successfully landed to Products Page");
			else
				Reporter.log("Invalid input");	
		} 
		else 
		{
			Reporter.log("Username and password do not match any user in this service");
		}
	}

	@Test(priority = 2, dependsOnMethods = { "loginApplicationSite" })
	public void buyItems() 
	{
		// Adding for execution purpose - pageLoadTimeout
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		WebElement backpackProduct = driver.findElement(By.partialLinkText("Backpack"));
		backpackProduct.click();
		Reporter.log("Clicked Sauce Labs Backpack Product");
		WebElement addToCartBackpackLocator = driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
		addToCartBackpackLocator.click();
		Reporter.log("Added Sauce Labs Backpack Product to cart");
		WebElement backToProductLocator1 = driver.findElement(By.xpath("//*[@id=\"back-to-products\"]"));
		backToProductLocator1.click();
		
		WebElement jacketProduct = driver.findElement(By.partialLinkText("Jacket"));
		jacketProduct.click();
		Reporter.log("Clicked Sauce Labs Fleece Jacket Product");
		WebElement addToCartJacketLocator = driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]"));
		addToCartJacketLocator.click();
		Reporter.log("Added Sauce Labs Fleece Jacket Product to cart");
		WebElement backToProductLocator2 = driver.findElement(By.xpath("//*[@id=\"back-to-products\"]"));
		backToProductLocator2.click();
		
		WebElement onesieProduct = driver.findElement(By.partialLinkText("Onesie"));
		onesieProduct.click();
		Reporter.log("Clicked Sauce Labs Onesie Product");
		WebElement addToCartOnesieLocator = driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-onesie\"]"));
		addToCartOnesieLocator.click();
		Reporter.log("Added Sauce Labs Onesie Product to cart");
		WebElement backToProductLocator3= driver.findElement(By.xpath("//*[@id=\"back-to-products\"]"));
		backToProductLocator3.click();
		
		WebElement shoppingCartLocator = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
		shoppingCartLocator.click();
		Reporter.log("Clicked shooping cart");
		
		WebElement removeCartJacketLocator = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-fleece-jacket\"]"));
		removeCartJacketLocator.click();
		Reporter.log("Removed Sauce Labs Fleece Jacket Product from cart");
	}

	@Test(priority = 3, dependsOnMethods = { "loginApplicationSite" })
	public void placeOrder() 
	{
		WebElement checkoutLocator = driver.findElement(By.xpath("//*[@id=\"checkout\"]"));
		checkoutLocator.click();
		Reporter.log("User has clicked Checkout button");
		WebElement firstNameLocator = driver.findElement(By.id("first-name"));
		WebElement lastNameLocator = driver.findElement(By.id("last-name"));
		WebElement zipCodeLocator = driver.findElement(By.id("postal-code"));
		firstNameLocator.sendKeys(firstNameValue);
		lastNameLocator.sendKeys(lastNameValue);
		zipCodeLocator.sendKeys(zipCodeValue);
		WebElement continueLocator = driver.findElement(By.xpath("//*[@id=\"continue\"]"));
		continueLocator.click();
		WebElement finishLocator = driver.findElement(By.xpath("//*[@id=\"finish\"]"));
		finishLocator.click();
		Reporter.log("User has placed the order");
		WebElement confirmMessage = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2"));
		String message1 = confirmMessage.getText();
		Reporter.log("Confirmation Message : "+message1);
		WebElement dispatchMessage = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/div"));
		String message2 = dispatchMessage.getText();
		Reporter.log("Dispatch Message : "+message2);
	}
}
