package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver webDriver;

    @BeforeClass
    public void BaseMethod() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./config.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        String browser = properties.getProperty("browser");

        if (browser.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "./Drivers/chromedriver.exe");
            webDriver = new ChromeDriver();
            webDriver.navigate().to("https://weathershopper.pythonanywhere.com/");
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        else if (browser.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "./Drivers/geckodriver.exe");
            webDriver = new FirefoxDriver();
            webDriver.navigate().to("https://weathershopper.pythonanywhere.com/");
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }
    }

    @AfterClass
    public void TakeOutputSS() throws IOException {
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(webDriver);

        ImageIO.write(screenshot.getImage(), "jpg", new File(".\\screenshot\\FinalOutputResult.jpg"));
    }
}
