package pages;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import utils.Logger;

public class CheckoutPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actions;

	private By addNewAddressButton = By.linkText("Add a new delivery address");

	private By fullName = By.id("address-ui-widgets-enterAddressFullName");
	private By phoneNum = By.id("address-ui-widgets-enterAddressPhoneNumber");
	private By streetNumber = By.id("address-ui-widgets-enterAddressLine1");
	private By buildingNumber = By.id("address-ui-widgets-enter-building-name-or-number");
	private By cityArea = By.id("address-ui-widgets-enterAddressCity");

	private By districtField = By
			.xpath("//input[@type=\"text\" and @id=\"address-ui-widgets-enterAddressDistrictOrCounty\"]");

	private By useThisAddress = By.id("checkout-primary-continue-button-id");

	private By paymentRadioBtn = By.className("pmts-instrument-selector");

	private By useThisPaymentMethod = By.id("checkout-primary-continue-button-id");
	
	private By subTotalCheckout = By.xpath("//div[@class='order-summary-line-definition']//span[@class='aok-nowrap a-nowrap']");
	 

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.actions = new Actions(driver);
	}

	private WebElement waitForElementToBeClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	private WebElement waitForElementVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void addNewAddress() throws IOException, InterruptedException {
		Faker faker = new Faker();

		String name = faker.name().fullName();
		String number = "1020304050";
		String street = faker.address().streetName();
		String building = String.valueOf(faker.number().numberBetween(1, 100));
		String city = "AL Haram";
		String district = "Tersa";

		waitForElementVisible(addNewAddressButton).click();
		Logger.info("newAddress ");

		waitForElementVisible(fullName).sendKeys(name);
		Logger.info("Added fullName");

		waitForElementVisible(phoneNum).sendKeys(number);
		Logger.info("Added phoneNum");

		waitForElementVisible(streetNumber).sendKeys(street);
		Logger.info("streetNumber Added");

		WebElement popup = driver.findElement(By.className("a-modal-scroller"));

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollBy({ top: arguments[0].scrollHeight, behavior: 'smooth', block: 'center'});",
				popup);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		waitForElementVisible(buildingNumber).sendKeys(building);
		Logger.info("buildingNumber Added");

		waitForElementVisible(cityArea).click();
		Logger.info("click on city field");

		WebElement dropdown = driver.findElement(By.id("address-ui-widgets-autocompleteResultsContainer"));

		dropdown.click();

		//Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).perform();

		Logger.info("dropdown on city field");

		waitForElementVisible(districtField).click();

		WebElement dropdown1 = driver.findElement(By.id("address-ui-widgets-autocompleteResultsContainer"));

		dropdown1.click();

		Actions actions1 = new Actions(driver);
		actions1.sendKeys(Keys.ARROW_DOWN).perform();

		Logger.info("district added ");

		WebElement radioButton = driver.findElement(By.id("address-ui-widgets-addr-details-res-radio-input"));

		if (!radioButton.isSelected()) {
			radioButton.click();
		}

		radioButton.sendKeys(Keys.ENTER);
		Logger.info("dropdown on city field");

		waitForElementVisible(useThisAddress).click();
		Logger.info("Address added successfully");

		System.out.println("Generated Address:");
		System.out.println("Name: " + name);
		System.out.println("Street: " + street);
		System.out.println("Building No: " + building);
		System.out.println("City: " + city);
		System.out.println("District: " + district);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		waitForElementToBeClickable(paymentRadioBtn).click();
		Logger.info("Payment method is selected ");

		WebElement useThisPayment = driver.findElement(useThisPaymentMethod);
		((JavascriptExecutor) driver)
				.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", useThisPayment);

		waitForElementVisible(useThisPaymentMethod).click();
		Logger.info("confirm use this payment method ");

	}



public String getSubtotalCheckoutPage() throws IOException {
	WebElement subTotalAmount = driver.findElement(subTotalCheckout);
	String SubTotalAmount = subTotalAmount.getText();

	return SubTotalAmount;

}

}