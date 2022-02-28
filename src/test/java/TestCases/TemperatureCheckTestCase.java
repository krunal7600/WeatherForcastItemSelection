package TestCases;

import Utilities.BaseClass;
import WebAppFunctions.WebAppFunctions;
import org.testng.annotations.Test;

public class TemperatureCheckTestCase extends BaseClass {

    @Test
    public void temperatureCheckTestCase() throws InterruptedException {
        WebAppFunctions webAppFunctions = new WebAppFunctions(webDriver);
        webAppFunctions.getTemperature();
    }
}
