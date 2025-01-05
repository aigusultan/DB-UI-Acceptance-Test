package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;

    public Driver() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {

            String browser = ConfigReader.getPropertiesValue("digitalbank.browser");
            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "headless":
                    ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                    ChromeOptions options = new ChromeOptions();

                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("disable-extensions");
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.addArguments("--proxy-server='direct://");
                    options.addArguments("--proxy-bypass-list=*");
                    options.addArguments("--start-maximized");
                    options.addArguments("--headless");

                    driver = new ChromeDriver(options);
                    break;

                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    private static void saveScreenshot(byte[] screenshot) {
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String timeStamp = dateFormat.format(new Date());

        String Screenshot_Directory = "src/test/resources/screenshots";
        String fileName = Screenshot_Directory + File.separator + "screenshot_" + timeStamp + ".png";
        Path filePath = Paths.get(fileName);

        try {
            Files.write(filePath, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            //taking a screenshot
            final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            //adding the screenshot to the report
            scenario.attach(screenshot, "image/png", "screenshot");

            saveScreenshot(screenshot);
        }

    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
