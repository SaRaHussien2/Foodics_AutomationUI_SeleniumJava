package testsuits;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import abstractcomponents.AbstractComponent;
import pages.*;

public class BaseTest {
	protected WebDriver driver;
	protected AbstractComponent abstractComponent;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected videoGamePage videoGamePage;
	protected CartPage cartPage;
	protected videoGamePage videocartPage;
	protected CheckoutPage checkoutPage;

	@BeforeClass
	public void setUpTest() {
		abstractComponent = new AbstractComponent();
		driver = abstractComponent.setUp();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		videoGamePage = new videoGamePage(driver);
		cartPage = new CartPage(driver);
		videocartPage = new videoGamePage(driver);
		checkoutPage = new CheckoutPage(driver);

	}

	@AfterClass
	public void tearDownTest() {
		 abstractComponent.tearDown();
	}
}
