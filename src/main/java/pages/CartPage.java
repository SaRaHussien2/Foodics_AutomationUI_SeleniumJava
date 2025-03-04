package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Logger;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class CartPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actions;


	private By proceedToBuyBtn = By.xpath("//input[@name='proceedToRetailCheckout' and @value='Proceed to checkout']");

	private By shoppingCart = By.id("sc-active-items-header");

	private By cartItemsLocator = By.xpath("//div[@id='sc-active-cart']//div[contains(@class, 'item-content-inner')]");

	private By subTotal = By.id("sc-subtotal-amount-activecart");

	public CartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased timeout
		this.actions = new Actions(driver);
	}

	private WebElement waitForElementToBeClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void goToCartPage() {
		driver.get("https://www.amazon.eg/-/en/cart?ref_=ewc_gtc");
	}

	public int fetchCartItems() throws IOException {

		WebElement ShoppingCartPageTitle = driver.findElement(shoppingCart);
		String ShoppingCartTitle = ShoppingCartPageTitle.getText();
		Logger.info("Shopping Cart Title is: " + ShoppingCartTitle);

		List<WebElement> cartItems = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartItemsLocator));

		if (cartItems.isEmpty()) {
			throw new AssertionError("No products are added to cart");
		}
		Logger.info("Total added products to cart: " + cartItems.size());

		return cartItems.size();
	}

	public String getSubtotal() throws IOException {
		WebElement subTotalAmount = driver.findElement(subTotal);
		String SubTotalAmount = subTotalAmount.getText();

		return SubTotalAmount;

	}

	public void proceedtobuy() {
		waitForElementToBeClickable(proceedToBuyBtn).click();
	}

}