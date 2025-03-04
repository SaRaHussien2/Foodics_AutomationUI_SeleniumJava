package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import utils.Logger;

public class videoGamePage {
	private WebDriver driver;
	private WebDriverWait wait;

	// Locators
	private By freeShippingFilterCheckbox = By.xpath("//i[contains(@class, 'a-icon-checkbox')]");
	private By newConditionFilter = By.xpath("//span[contains(text(), 'New')]");
	private By sortMenuDropdown = By.id("a-autoid-0");
	private By priceSortingOption = By.xpath("//a[text()='Price: High to Low']");
	private By productsList = By.xpath("//div[@data-component-type='s-search-result']");
	private By productPrice = By.xpath(".//span[@class='a-price-whole']");
	private By addToCartButton = By.xpath(".//button[@name='submit.addToCart' and contains(text(), 'Add to cart')]");
	private By nextPageButton = By.linkText("Next");

	public videoGamePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	private WebElement waitForElementToBeClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Apply filter and condition
	public void applyFiltersAndSortByPrice() {
		try {
			waitForElementToBeClickable(freeShippingFilterCheckbox).click();
			Logger.info("Applied 'Free Shipping' filter.");

			waitForElementToBeClickable(newConditionFilter).click();
			Logger.info("Applied 'New' condition filter.");

			waitForElementToBeClickable(sortMenuDropdown).click();
			Logger.info("Opened sort menu.");

			waitForElementToBeClickable(priceSortingOption).click();
			Logger.info("Sorted by 'Price: High to Low'.");
		} catch (Exception e) {
			Logger.error("Error while applying filters and sorting: ", e);
		}
	}

	public int addProductsBelowPrice() throws IOException, InterruptedException {
		int addedProductsCount = 0;
		boolean productsAdded = false;

		do {

			List<WebElement> productContainers = new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productsList));

			if (productContainers.isEmpty()) {
				throw new AssertionError("No products found on the page");
			}

			System.out.println("Total products found: " + productContainers.size());

			for (WebElement product : productContainers) {
				try {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
							product);

					WebElement priceElement = product.findElement(productPrice);
					String priceText = priceElement.getText().replaceAll("[^0-9]", "");

					if (priceElement == null || priceElement.getText().isEmpty()) {
						System.out.println("Skipping product: Price not found.");
						continue;
					}

					if (priceText.isEmpty()) {
						System.out.println("Skipping product: Invalid price format.");
						continue;
					}

					double price = Double.parseDouble(priceText);
					System.out.println("Product Price: " + price);

					if (price < 15000) {
						WebElement addToCartBtn = new WebDriverWait(driver, Duration.ofSeconds(5))
								.until(ExpectedConditions.elementToBeClickable(product.findElement(addToCartButton)));

						if (addToCartBtn == null) {
							System.out.println("Skipping product: Add to cart button not found.");
							continue;
						}

						// Scroll and click the "Add to Cart" button
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", addToCartBtn);
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);

						System.out.println("Added product with price: " + price);
						addedProductsCount++;
						productsAdded = true;

						// Wait for the cart to update
						Thread.sleep(2000);
					}
				} catch (NoSuchElementException | TimeoutException | InterruptedException e) {
					System.out.println("Skipping product: Missing price or add-to-cart button.");
				} catch (StaleElementReferenceException e) {
					System.out.println("Retrying: Element went stale, trying again...");
				}
			}

			System.out.println("Total products added to cart: " + addedProductsCount);

			// Stop if products were added on the current page
			if (productsAdded) {
				break;
			}

			// Navigate to the next page only if no products were added on the current page
			if (isNextPageAvailable()) {
				goToNextPage();
				Thread.sleep(2500);
			} else {
				break; // Stop if there are no more pages
			}
		} while (true);

		return addedProductsCount;
	}

	public boolean isNextPageAvailable() {
		try {
			WebElement nextpage = driver.findElement(nextPageButton);
			return nextpage.isDisplayed() && nextpage.isEnabled();
		} catch (NoSuchElementException e) {
			System.out.println("No next page available.");
			return false;
		}
	}

	public void goToNextPage() {
		try {
			WebElement nextpage = driver.findElement(nextPageButton);
			((JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", nextpage);
			nextpage.click();
		} catch (NoSuchElementException e) {
			System.out.println("Failed to navigate to next page.");
		}
	}

}