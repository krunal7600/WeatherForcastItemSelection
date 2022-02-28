package TestCases;

import Utilities.BaseClass;
import WebAppFunctions.WebAppFunctions;
import org.testng.annotations.Test;

public class FindingMinimumValueOfProductsTestCase extends BaseClass {

    @Test
    public void findingMinimumValueOfProductsTestCase() {
        WebAppFunctions webAppFunctions = new WebAppFunctions(webDriver);
        webAppFunctions.getTemperature();
        webAppFunctions.getMinimumValueOfProducts();
    }
}
