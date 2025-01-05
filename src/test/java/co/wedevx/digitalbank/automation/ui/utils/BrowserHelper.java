package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

//WebDriver helper extensions are designed to simplify Java based Selenium/WebDriver tests
//It's built on top of Selenium/WebDriver to make your tests more readable, reusable, maintainable
//by providing easy handling towards browser and advance identifiers.
public class BrowserHelper {

    //wait until the element is visible
    public static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element, int timeToWaitInSecs) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSecs));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //wait until the element is clickable and click on it
    public static WebElement waitUntilElementClickableAndClick(WebDriver driver, WebElement element,
                                                               int timeToWaitInSecs) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSecs));
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();

        return clickableElement;

    }

    public static WebElement fluentWaitForElementPresence(WebDriver driver, By locator,
                                                          int timeToWaitInSecs) {
        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeToWaitInSecs))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        WebElement visibleElement = null;
        try {
            fluentWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.out.println("Element wasn't found in given " + timeToWaitInSecs + " seconds");
        } catch (NoSuchElementException e) {
            System.out.println("No such element was found");
        }

        return visibleElement;
    }

    public static void scrollIntoView (WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);

    }

    public static WebElement clickElementWithText(WebDriver driver, String textOfTheElement) {

        try {
            WebElement elementToBeClicked  = driver.findElement(By.xpath("//*[text()='" + textOfTheElement + "']"));
            elementToBeClicked.click();
            return elementToBeClicked;
        } catch (NoSuchElementException e) {
            System.out.println("An element with such text was not found");
            e.printStackTrace();
            return null;
        }
    }

    public static void fillTextInput(WebDriver driver, WebElement textInputElement, String toBeFilledInInput) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", textInputElement);

        if (textInputElement.isEnabled() && textInputElement.isDisplayed()) {
            textInputElement.clear();
            textInputElement.sendKeys(toBeFilledInInput);
        } else {
            System.out.println("The he text input element is not visible or not enabled.");
        }
    }
}
