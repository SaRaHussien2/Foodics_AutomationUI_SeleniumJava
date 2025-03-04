package abstractcomponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Logger;

public class AbstractComponent {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected static final String BASE_URL = "https://www.amazon.eg/";

	public WebDriver setUp() {
		try {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(BASE_URL);
		} catch (Exception e) {
			Logger.error("Error during setup: ", e);
		}
		return driver;
	}

	public void tearDown() {
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			Logger.error("Error during teardown: ", e);
		}
	}
}
