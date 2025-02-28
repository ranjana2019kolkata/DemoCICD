package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;

public class SampleOrderTestSecond extends BaseTest{
	
	WebDriver driver;
	
	@Test
	public void submitOrderTest() throws InterruptedException
	{
		String stremail = "ranjanamitr@gmail.com";
		String strpwd = "Test1234!";
		String strProduct = "qwerty";
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(stremail, strpwd);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(strProduct);
		
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(strProduct);
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	/*public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.out.println("first ");
			
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		
		String stremail = "ranjanamitr@gmail.com";
		String strpwd = "Test1234!";
		String strProduct = "qwerty";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(stremail, strpwd);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(strProduct);
		
		System.out.println("after adding product  ");
		
		CartPage cartPage = productCatalogue.goToCartPage();
		
		System.out.println("on cart page  ");


		Boolean match = cartPage.VerifyProductDisplay(strProduct);
		
		System.out.println("before assert  ");

		Assert.assertTrue(match);
		System.out.println("after assert  ");

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		
		System.out.println("on checkoutPage  ");

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		System.out.println("on ConfirmationPage  ");

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}*/

}
