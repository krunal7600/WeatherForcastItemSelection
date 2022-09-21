package WebAppFunctions;

import PageObjects.TemperatureCheckObjects;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class WebAppFunctions {

    WebDriver driver;

    public WebAppFunctions(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

        String minValue = null;

        ArrayList<String> price = null , price1 = null;
        String SPF50MinPrice = null;
        String SPF30MinPrice = null;
        String AlmondMinPrice = null;
        String AloeMinPrice = null;

        String getMinProductName1 = null;
        String getMinProductName2 = null;
        String getMinProductName12 = null;
        String getMinProductName22 = null;

    public void getTemperature()
    {
        TemperatureCheckObjects temperatureCheckObjects = new TemperatureCheckObjects(driver);
        String getTemp = temperatureCheckObjects.GetTemperature.getText().replaceAll("[^0-9]","");

        int tempValue = Integer.parseInt(getTemp);
        if (tempValue <= 19)
        {
            temperatureCheckObjects.BuyMoisturized.click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        else if (tempValue >= 34)
        {
            temperatureCheckObjects.BuySunscreens.click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
      }

      public void getMinimumValueOfProducts()
      {
          TemperatureCheckObjects temperatureCheckObjects = new TemperatureCheckObjects(driver);
          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

          if (temperatureCheckObjects.getProductTitle.getText().equals("Moisturizers"))
          {
              for (WebElement col1 : temperatureCheckObjects.GetMoisturizedName)
              {
                  String getAlmond = col1.getText();
                  String getAloe = col1.getText();

                  if (getAlmond.toLowerCase().contains("almond")) {
                      AlmondMinPrice = minValue(temperatureCheckObjects.GetAlmondPrice);
                  }
                  if (getAloe.toLowerCase().contains("aloe")) {
                      AloeMinPrice = minValue(temperatureCheckObjects.GetAloePrice);
                  }
              }
              System.out.println("MINIMUM VALUE OF ALMOND::" + AlmondMinPrice);
              System.out.println("MINIMUM VALUE OF ALOE::" + AloeMinPrice);

              temperatureCheckObjects.add(AlmondMinPrice);
              temperatureCheckObjects.add(AloeMinPrice);

              getMinProductName1 = temperatureCheckObjects.getNameMatch(AlmondMinPrice);
              getMinProductName2 = temperatureCheckObjects.getNameMatch(AloeMinPrice);
          }
          else if (temperatureCheckObjects.getProductTitle.getText().equals("Sunscreens"))
          {
                  for (WebElement col : temperatureCheckObjects.GetSuncreensName)
                  {
                      String getSPF30 = col.getText();
                      String getSPF50 = col.getText();

                      if (getSPF30.toUpperCase().contains("SPF-30")) {
                          SPF30MinPrice = minValue(temperatureCheckObjects.SPF30SunscreensPrice);
                      }
                      if (getSPF50.contains("SPF-50")) {
                          SPF50MinPrice = minValue(temperatureCheckObjects.SPF50SunscreensPrice);
                      }
                  }
                  System.out.println("MINIMUM VALUE OF SPF-30::" + SPF30MinPrice);
                  System.out.println("MINIMUM VALUE OF SPF-50::" + SPF50MinPrice);

                  temperatureCheckObjects.add(SPF30MinPrice);
                  temperatureCheckObjects.add(SPF50MinPrice);

                  getMinProductName12 = temperatureCheckObjects.getNameMatch(SPF30MinPrice);
                  getMinProductName22 = temperatureCheckObjects.getNameMatch(SPF50MinPrice);
              }
          }


    public String minValue(@NotNull List<WebElement> pricelist)
    {
        price = new ArrayList<>();
        for (WebElement coll : pricelist)
        {
            price.add(coll.getText().replaceAll("[^0-9]",""));
            minValue = Collections.min(price);
        }
        return minValue;
    }

    public int SumProductPrice(String minPrice1 , String minPrice2)
    {
        int total1 = Integer.parseInt(minPrice1) + Integer.parseInt(minPrice2);
        System.out.println("Minimum Value Total Amount::" + total1);
        return total1;
    }

    public void MatchingStrings()
    {
        TemperatureCheckObjects temperatureCheckObjects = new TemperatureCheckObjects(driver);

        temperatureCheckObjects.ViewCartDetails.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        if(temperatureCheckObjects.CartProductGetName1.getText().toUpperCase().contains("ALMOND"))
        {
            String getCartProductTotalAmount1 = temperatureCheckObjects.CartTotalAmount.getText().replaceAll("[^0-9]", "");
            int getCartProductTotalAmountInt = Integer.parseInt(getCartProductTotalAmount1);

            int total = SumProductPrice(AlmondMinPrice, AloeMinPrice);

            Assert.assertEquals(getMinProductName1,temperatureCheckObjects.CartProductGetName1.getText());
            Assert.assertEquals(getMinProductName2,temperatureCheckObjects.CartProductGetName2.getText());
            Assert.assertEquals(getCartProductTotalAmountInt,total);
        }
        else if(temperatureCheckObjects.CartProductGetName1.getText().toUpperCase().contains("SPF-30"))
        {
            String getCartProductTotalAmount = temperatureCheckObjects.CartTotalAmount.getText().replaceAll("[^0-9]", "");
            int getCartProductTotalAmountInt1 = Integer.parseInt(getCartProductTotalAmount);

            int total1 = SumProductPrice(SPF30MinPrice, SPF50MinPrice);

            Assert.assertEquals(getMinProductName12,temperatureCheckObjects.CartProductGetName1.getText());
            Assert.assertEquals(getMinProductName22,temperatureCheckObjects.CartProductGetName2.getText());
            Assert.assertEquals(getCartProductTotalAmountInt1,total1);
        }
    }

    public void PaymentDetails()
    {
        TemperatureCheckObjects temperatureCheckObjects = new TemperatureCheckObjects(driver);

        temperatureCheckObjects.PayWithCard.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.switchTo().frame("stripe_checkout_app");
        System.out.println("Frame ACTIVE:::");
        temperatureCheckObjects.EmailForPyayment.sendKeys("ParekhKunal@agileinfoways.com");
        temperatureCheckObjects.CardNumber.sendKeys("4242");
        temperatureCheckObjects.CardNumber.sendKeys("4242");
        temperatureCheckObjects.CardNumber.sendKeys("4242");
        temperatureCheckObjects.CardNumber.sendKeys("4242");
        temperatureCheckObjects.CardExpiryDate.sendKeys("07");
        temperatureCheckObjects.CardExpiryDate.sendKeys("26");
        temperatureCheckObjects.CardCVCNumber.sendKeys("676");
        temperatureCheckObjects.ZipCode.sendKeys("382210");
        temperatureCheckObjects.PayButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void PaymentSuccess() throws InterruptedException
    {
        TemperatureCheckObjects temperatureCheckObjects = new TemperatureCheckObjects(driver);
        Thread.sleep(5000);
        System.out.println("Success::::::::" + temperatureCheckObjects.PaymentSuccess.getText());
        Assert.assertTrue(temperatureCheckObjects.PaymentSuccess.getText().contains("PAYMENT"));
    }
}
