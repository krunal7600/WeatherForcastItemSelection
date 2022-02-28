package TestCases;

import Utilities.BaseClass;
import WebAppFunctions.WebAppFunctions;
import org.testng.annotations.Test;

public class PaymentDetailsTestCase extends BaseClass {
    @Test
    public void paymentDetailsTestCase() throws InterruptedException {
        WebAppFunctions webAppFunctions = new WebAppFunctions(webDriver);
        webAppFunctions.getTemperature();
        webAppFunctions.getMinimumValueOfProducts();
        webAppFunctions.MatchingStrings();
        webAppFunctions.PaymentDetails();
    }
}
