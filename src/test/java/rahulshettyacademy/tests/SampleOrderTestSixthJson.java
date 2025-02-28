package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;

public class SampleOrderTestSixthJson extends BaseTest{
	
	WebDriver driver;
	String stremail; //= "ranjanamitr@gmail.com";
	String strpwd; //= "Test1234!";
	String strProduct; //= "qwerty";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrderTest(HashMap<String, String> inputHashMap) throws InterruptedException
	{
		System.out.println("submitOrderTest   ");
		stremail = inputHashMap.get("email");
		strpwd = inputHashMap.get("password");
		strProduct = inputHashMap.get("product");
		
		System.out.println("stremail   "+stremail);
		System.out.println("strpwd   "+strpwd);
		System.out.println("strProduct   "+strProduct);

	
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
		
		//Need to check whether the order is displayed in the order page which is accessible from the header tab ->Orders link
		@Test(dependsOnMethods= {"submitOrderTest"})
		public void OrderHistoryTest()
		{

			//"ZARA COAT 3";
			ProductCatalogue productCatalogue = landingPage.loginApplication(stremail, strpwd);
			OrderPage ordersPage = productCatalogue.goToOrdersPage();
			Assert.assertTrue(ordersPage.VerifyOrderDisplay(strProduct));
		}
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
			/*HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("email", "ranjanamitr@gmail.com");
			map1.put("password", "Test1234!");
			map1.put("product", "qwerty");
			
			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put("email", "ranjanamitra2016@gmail.com");
			map2.put("password", "Test12345!");
			map2.put("product", "Banarsi Saree");
						return new Object[][] {{map1},{map2}};*/

			
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");

			return new Object[][] {{data.get(0)},{data.get(1)}, {data.get(2)}};
		}

}
