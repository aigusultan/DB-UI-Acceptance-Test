package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ExternalAccountPage extends BaseMenuPage {
    public ExternalAccountPage(WebDriver driver) {
        super(driver);
    }

    private String dropdownValidationText;

    @FindBy(id = "bankId")
    private WebElement bankIdDropDown;

    @FindBy(id = "username")
    private WebElement usernameTxtBox;

    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(xpath = "(//button[@type='submit'])[3]")
    private WebElement submitButton;

    @FindBy(xpath = "//span[text()='Error']/following-sibling::span")
    protected WebElement fullRedErrorMessage;

//    public void linkExternalAccount(String bankId, String userName, String password) {
//        new Select(bankIdDropDown).selectByVisibleText(bankId);
//        usernameTxtBox.sendKeys(userName);
//        passwordTxtBox.sendKeys(password);
//        submitButton.click();
//    }

    public void linkExternalAccount(){
        externalAccountsMenu.click();
        linkExternalAccount.click();
        dropdownValidationText = bankIdDropDown.getAttribute("validationMessage");
    }

    public void getFullRedErrorMessage() {
        System.out.println(fullRedErrorMessage.getText());
    }


//    public String getRequiredFieldErrorMessageExternalAccount(String fieldName) {
//        switch (fieldName.toLowerCase()) {
//            case "title":
//                return bankIdDropDown.getAttribute("validationMessage");
//            case "username":
//                return usernameTxtBox.getAttribute("validationMessage");
//            case "password":
//                return passwordTxtBox.getAttribute("validationMessage");
//            default:
//                return "Unknown field: " + fieldName;
//        }
//
//    }
}
