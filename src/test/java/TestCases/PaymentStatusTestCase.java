package TestCases;

import Utilities.BaseClass;
import WebAppFunctions.WebAppFunctions;
import org.testng.annotations.Test;

public class PaymentStatusTestCase extends BaseClass {
    @Test
    public void paymentStatusTestCase() throws InterruptedException {
        WebAppFunctions webAppFunctions = new WebAppFunctions(webDriver);
        webAppFunctions.getTemperature();
        webAppFunctions.getMinimumValueOfProducts();
        webAppFunctions.MatchingStrings();
        webAppFunctions.PaymentDetails();
        webAppFunctions.PaymentSuccess();
    }
}
