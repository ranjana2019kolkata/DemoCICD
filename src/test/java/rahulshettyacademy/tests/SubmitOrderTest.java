package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	String productName;

	//@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrderold(HashMap<String,String> input) throws IOException, InterruptedException
	{
		System.out.println("first ");
		
		String email = input.get("email");
		String pwd = input.get("password");
		System.out.println("email  "+email);
		System.out.println("pwd  "+pwd);


		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		
		System.out.println("after adding product  "+input.get("product"));
		
		CartPage cartPage = productCatalogue.goToCartPage();
		
		System.out.println("on cart page  ");


		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		
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
	
	}
	
	@Test
	public void submitOrder(String stremail, String strpwd, String strProduct) throws IOException, InterruptedException
	{
		System.out.println("first ");
		stremail = "ranjanamitra2016@gmail.com";
		strpwd = "Test12345!";
		strProduct = "IPHONE 13 PRO";
		
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
	
	}
	
	
	
	
	//@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		System.out.println("before method  ");

		//"ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("ranjanamitra2016@gmail.com", "Test12345!");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
	}
	
	@DataProvider
	public HashMap<String, String> getLoginData() throws IOException
	{
		
		//List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		//return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
		HashMap<String, String> logindataone = new HashMap<String, String>();
		logindataone.put("email" ,"ranjanamitra2016@gmail.com");
		logindataone.put("password" ,"Test12345!");
		logindataone.put("product" ,"IPHONE 13 PRO");
		
		return logindataone;

	}

	

}
