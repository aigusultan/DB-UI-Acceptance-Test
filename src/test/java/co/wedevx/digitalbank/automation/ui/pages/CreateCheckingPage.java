package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingPage extends BaseMenuPage {

    public CreateCheckingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "Standard Checking")
    private WebElement standardCheckingTypeRadioButton;

    @FindBy(id = "Interest Checking")
    private WebElement interestCheckingTypeRadioButton;

    @FindBy(id = "Individual")
    private WebElement individualTypeRadioButton;

    @FindBy(id = "Joint")
    private WebElement jointTypeRadioButton;

    @FindBy(id = "name")
    private WebElement accountNameTxtBox;

    @FindBy(id = "openingBalance")
    private WebElement openingBalanceTxtBox;

    @FindBy(id = "newCheckingSubmit")
    private WebElement newCheckingSubmitButton;

    public void createNewChecking(List<NewCheckingAccountInfo> checkingAccountInfoList) {
        NewCheckingAccountInfo testDataForOneCheckingAccount = checkingAccountInfoList.get(0);
        //user clicks on the checking button
        checkingMenu.click();

        //user clicks on the new checking button
        newCheckingMenuItem.click();

        assertEquals(ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"),
                getDriver().getCurrentUrl(), "New Checking Button did not redirect the user to "
                        + ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"));

        //user selects account type
        if (testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard Checking")) {
            standardCheckingTypeRadioButton.click();
        } else if (testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest Checking")) {
            interestCheckingTypeRadioButton.click();
        } else {
            throw new NoSuchElementException("Invalid checking account type");
        }

        //user select account ownership
        if (testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Individual")) {
            individualTypeRadioButton.click();
        } else if (testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Joint")) {
            jointTypeRadioButton.click();
        } else {
            throw new NoSuchElementException("Invalid account ownership type");
        }

        //user provides account name
        accountNameTxtBox.sendKeys(testDataForOneCheckingAccount.getAccountName());

        //user provides an opening balance
        openingBalanceTxtBox.sendKeys(String.valueOf(testDataForOneCheckingAccount.getInitialDepositAmount()));

        newCheckingSubmitButton.click();
    }
}
