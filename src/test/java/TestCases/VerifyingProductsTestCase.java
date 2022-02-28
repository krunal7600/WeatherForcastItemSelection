package TestCases;

import Utilities.BaseClass;
import WebAppFunctions.WebAppFunctions;
import org.testng.annotations.Test;

public class VerifyingProductsTestCase extends BaseClass {

    @Test
    public void verifyingProductsTestCase() throws InterruptedException {
        WebAppFunctions webAppFunctions = new WebAppFunctions(webDriver);
        webAppFunctions.getTemperature();
        webAppFunctions.getMinimumValueOfProducts();
        webAppFunctions.MatchingStrings();
    }
}
