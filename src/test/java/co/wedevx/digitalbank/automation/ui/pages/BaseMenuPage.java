package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseMenuPage extends BasePage{

    public BaseMenuPage (WebDriver driver) {
        super(driver);

    }

    @FindBy(id = "checking-menu")
    protected WebElement checkingMenu;

    @FindBy(id = "new-checking-menu-item")
    protected WebElement newCheckingMenuItem;

    @FindBy (id = "view-checking-menu-item")
    protected WebElement viewCheckingButton;

    @FindBy (id = "home-menu-item")
    protected WebElement homeButton;

    @FindBy (id = "savings-menu")
    protected WebElement savingsMenu;

    @FindBy (id = "external-accounts-menu")
    protected WebElement externalAccountsMenu;

    @FindBy (id = "link-external-menu-item")
    protected WebElement linkExternalAccount;

    @FindBy (id = "view-external-menu-item")
    protected WebElement viewExternalAccount;


    public void goToHomePage() {
        homeButton.click();
    }

    public void goToExternalAccountMenu() {
        externalAccountsMenu.click();
    }

    public void goToLinkExternalAccount() {
        linkExternalAccount.click();
    }
}
