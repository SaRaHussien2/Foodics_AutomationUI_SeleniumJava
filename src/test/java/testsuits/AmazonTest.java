
package testsuits;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Logger;

public class AmazonTest extends BaseTest {

	@Test
	public void Amazon_TC1() {
		try {
			homePage.clickSignIn();
			loginPage.login("01202960227", "123456");
			homePage.navigateToVideoGames();
			videoGamePage.applyFiltersAndSortByPrice();

			// Fetch the total of added products that are < 15000
			int totalAddedProducts = videoGamePage.addProductsBelowPrice();

			cartPage.goToCartPage();

			int totalCartItems = cartPage.fetchCartItems(); // Fetch the total count of added products in cart
			Assert.assertEquals(totalCartItems, totalAddedProducts,
					"The count of cart items does not match the added products counts");

			String subTotal = cartPage.getSubtotal(); // Fetch the subtotal in cart
			cartPage.proceedtobuy(); // Click on Proceed to buy
			checkoutPage.addNewAddress();

			String subTotalCheckout = checkoutPage.getSubtotalCheckoutPage();

			// ✅ Normalize subtotal values before comparison
			String formattedCartSubtotal = normalizePrice(subTotal);
			String formattedCheckoutSubtotal = normalizePrice(subTotalCheckout);

			Assert.assertEquals(formattedCartSubtotal, formattedCheckoutSubtotal,
					"The subTotal in the Cart page match the subTotal in the Checkout page");

		} catch (Exception e) {
			Logger.error("Error during test execution: ", e);
		}
	}

	/**
	 * Helper method to normalize price by removing spaces, currency symbols, and
	 * formatting.
	 */
	private String normalizePrice(String price) {
		return price.replaceAll("[^0-9.]", "").trim(); // Removes non-numeric characters except decimal
	}

}
