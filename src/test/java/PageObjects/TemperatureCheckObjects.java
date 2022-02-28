package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TemperatureCheckObjects {

    WebDriver webDriver;

    public TemperatureCheckObjects(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(xpath = "//div[@class='row justify-content-center headline']/span")
    public WebElement GetTemperature;

    @FindBy(xpath = "//div[@class='text-center col-4']/a/button")
    public WebElement BuyMoisturized;

    @FindBy(xpath = "//div[@class='text-center col-4 offset-4']/a/button")
    public WebElement BuySunscreens;

    @FindBy(xpath = "//div[@class='container']//div[@class='row justify-content-center top-space-50']/div/p[1]")
    public List<WebElement> GetSuncreensName;

    @FindBy(xpath = "//p[contains(text(),'30')]/following-sibling::p")
    public List<WebElement> SPF30SunscreensPrice;

    @FindBy(xpath = "//p[contains(text(),'50')]/following-sibling::p")
    public List<WebElement> SPF50SunscreensPrice;

    @FindBy(xpath = "//p[@class='font-weight-bold top-space-10']")
    public List<WebElement> GetMoisturizedName;

    @FindBy(xpath = "//p[contains(text(),'Almond')]/following-sibling::p")
    public List<WebElement> GetAlmondPrice;

    @FindBy(xpath = "//p[contains(text(),'Aloe')]/following-sibling::p")
    public List<WebElement> GetAloePrice;

    @FindBy(xpath = "//button[@class='thin-text nav-link']")
    public WebElement ViewCartDetails;

    @FindBy(xpath = "//table[@class='table table-striped']/tbody/tr[1]/td[1]")
    public WebElement CartProductGetName1;

    @FindBy(xpath = "//table[@class='table table-striped']/tbody/tr[2]/td[1]")
    public WebElement CartProductGetName2;

    @FindBy(xpath = "//p[@id='total']")
    public WebElement CartTotalAmount;

    @FindBy(xpath = "//button[@class='stripe-button-el']")
    public WebElement PayWithCard;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement EmailForPyayment;

    @FindBy(xpath = "//input[@id='card_number']")
    public WebElement CardNumber;

    @FindBy(xpath = "//input[@id='cc-exp']")
    public WebElement CardExpiryDate;

    @FindBy(xpath = "//input[@id='cc-csc']")
    public WebElement CardCVCNumber;

    @FindBy(xpath = "//input[@id='billing-zip']")
    public WebElement ZipCode;

    @FindBy(xpath = "//button[@id='submitButton']")
    public WebElement PayButton;

    @FindBy(xpath = "//div[@class='row justify-content-center']/h2")
    public WebElement PaymentSuccess;

    @FindBy(xpath = "//div[@class='row justify-content-center']/h2")
    public WebElement getProductTitle;

    public void add(String addMin)
    {
        webDriver.findElement(By.xpath("//p[contains(text(),'"+addMin+"')]/following-sibling::button")).click();
    }

    public String getNameMatch(String addMin)
    {
        String getName = webDriver.findElement(By.xpath("//p[contains(text(),'"+addMin+"')]/preceding-sibling::p")).getText();
        return getName;
    }

}
