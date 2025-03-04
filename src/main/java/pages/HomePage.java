package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import utils.Logger;

public class HomePage {

	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actions;

	private By signInButton = By.xpath("//a[@id='nav-link-accountList']/span");
	private By allMenuButton = By.id("nav-hamburger-menu");
	private By seeAllCategoriesLink = By.xpath("//a[@aria-label='See All Categories']");
	private By videoGamesLink = By.xpath("//a[@class='hmenu-item' and @data-menu-id='16']");
	private By allVideoGamesLink = By.xpath("//a[contains(text(),'All Video Games')]");

	public HomePage(WebDriver driver) {
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

	public void clickSignIn() {
		try {
			waitForElementToBeClickable(signInButton).click();
		} catch (Exception e) {
			Logger.error("Error clicking sign in button: ", e);
		}
	}

	public void navigateToVideoGames() {
		try {
			waitForElementToBeClickable(allMenuButton).click();
			waitForElementVisible(seeAllCategoriesLink).click();
			Logger.info("Click on 'See All' ");

			WebElement videoGamesElement = waitForElementVisible(videoGamesLink);
			actions.moveToElement(videoGamesElement).perform();
			videoGamesElement.click();
			Logger.info("Video Games menu item is clicked");

			WebElement allVideoGamesElement = waitForElementToBeClickable(allVideoGamesLink);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", allVideoGamesElement);
			Logger.info("All Video Games link is clicked");
		} catch (Exception e) {
			Logger.error("Error when navigating to All Video Games: ", e);
		}
	}
}
