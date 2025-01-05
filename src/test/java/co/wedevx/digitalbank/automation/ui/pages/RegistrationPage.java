package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.utils.MockData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    MockData mockData = new MockData();

    @FindBy(id = "title")
    private WebElement titleDropDown;

    @FindBy(id = "firstName")
    private WebElement firstNameTxtBox;

    @FindBy(id = "lastName")
    private WebElement lastNameTxtBox;

    @FindBy(xpath = "//input[@value='M']")
    private WebElement genderMaleRadio;

    @FindBy(xpath = "//input[@value='F']")
    private WebElement genderFemaleRadio;

    @FindBy(id = "dob")
    private WebElement dobTxtBox;

    @FindBy(id = "ssn")
    private WebElement ssnTxtBox;

    @FindBy(id = "emailAddress")
    private WebElement emailAddressTxtBox;

    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordTxtBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement nextButton;

    @FindBy(id = "address")
    private WebElement addressTxtBox;

    @FindBy(id = "locality")
    private WebElement localityTxtBox;

    @FindBy(id = "region")
    private WebElement regionTxtBox;

    @FindBy(id = "postalCode")
    private WebElement postalCodeTxtBox;

    @FindBy(id = "country")
    private WebElement countryTxtBox;

    @FindBy(id = "homePhone")
    private WebElement homePhoneTxtBox;

    @FindBy(id = "mobilePhone")
    private WebElement mobilePhoneTxtBox;

    @FindBy(id = "workPhone")
    private WebElement workPhoneTxtBox;

    @FindBy(id = "agree-terms")
    private WebElement agreeTermsCheckBox;

    @FindBy(xpath = "//button")
    private WebElement registerButton;

    @FindBy(xpath = "//button[@data-dismiss='alert']/../span[2]")
    private WebElement successMessageTxt;


    public void fillOutRegistrationForm(List<Map<String, String>> registrationPageTestDataListOfMap) {
        Map<String, String> row = registrationPageTestDataListOfMap.get(0);

        Select titleSelect = new Select(titleDropDown);

        if (row.get("title") != null) {
            titleSelect.selectByVisibleText(row.get("title"));
        }

        if (row.get("firstName") != null) {
            firstNameTxtBox.sendKeys(row.get("firstName"));
        }

        if (row.get("lastName") != null) {
            lastNameTxtBox.sendKeys(row.get("lastName"));
        }

        if (row.get("gender") != null) {
            if (row.get("gender").equalsIgnoreCase("M")) {
                genderMaleRadio.click();
            } else if (row.get("gender").equalsIgnoreCase("F")) {
                genderFemaleRadio.click();
            } else {
                System.out.println("Wrong Gender");
            }
        }

        if (row.get("dob") != null) {
            dobTxtBox.sendKeys(row.get("dob"));
        }

        if (row.get("ssn") != null) {
//            if (row.get("ssn").equalsIgnoreCase("random")) {
//                ssnTxtBox.sendKeys(mockData.generateRandomSsn()); we are not mocking it anymore
//            }
            ssnTxtBox.sendKeys(row.get("ssn"));
        }

        if (row.get("email") != null) {
//            if (row.get("email").equalsIgnoreCase("random")) {
//                Map<String, String> mockNameAndEmail = mockData.generateRandomNameAndEmail();
//                emailAddressTxtBox.sendKeys(mockNameAndEmail.get("email"));
//            }
            emailAddressTxtBox.sendKeys(row.get("email"));
        }

        if (row.get("password") != null) {
            passwordTxtBox.sendKeys(row.get("password"));
        }

        if (row.get("confirmPassword") != null) {
            confirmPasswordTxtBox.sendKeys(row.get("confirmPassword"));
        }

        nextButton.click();

        if (addressTxtBox.isDisplayed()) {

            if (row.get("address") != null) {
                addressTxtBox.sendKeys(row.get("address"));
            }

            if (row.get("locality") != null) {
                localityTxtBox.sendKeys(row.get("locality"));
            }

            if (row.get("region") != null) {
                regionTxtBox.sendKeys(row.get("region"));
            }

            if (row.get("postalCode") != null) {
                postalCodeTxtBox.sendKeys(row.get("postalCode"));
            }

            if (row.get("country") != null) {
                countryTxtBox.sendKeys(row.get("country"));
            }

            if (row.get("homePhone") != null) {
                homePhoneTxtBox.sendKeys(row.get("homePhone"));
            }

            if (row.get("mobilePhone") != null) {
                mobilePhoneTxtBox.sendKeys(row.get("mobilePhone"));
            }

            if (row.get("workPhone") != null) {
                workPhoneTxtBox.sendKeys(row.get("workPhone"));
            }

            if (row.get("termsCheckMark") != null) {
                if (row.get("termsCheckMark").equalsIgnoreCase("true")) {
                    agreeTermsCheckBox.click();
                }
            }

            registerButton.click();
        }
    }

    public String retrieveSuccessMessage() {
        return successMessageTxt.getText();

    }


    public String getRequiredFieldErrorMessage(String fieldName) {
        switch (fieldName.toLowerCase()) {
            case "title":
                return titleDropDown.getAttribute("validationMessage");
            case "firstname":
                return firstNameTxtBox.getAttribute("validationMessage");
            case "lastname":
                return lastNameTxtBox.getAttribute("validationMessage");
            case "gender":
                return genderMaleRadio.getAttribute("validationMessage");
            case "dob":
                return dobTxtBox.getAttribute("validationMessage");
            case "ssn":
                return ssnTxtBox.getAttribute("validationMessage");
            case "email":
                return emailAddressTxtBox.getAttribute("validationMessage");
            case "password":
                return passwordTxtBox.getAttribute("validationMessage");
            case "confirmpassword":
                return confirmPasswordTxtBox.getAttribute("validationMessage");
            case "address":
                return addressTxtBox.getAttribute("validationMessage");
            case "locality":
                return localityTxtBox.getAttribute("validationMessage");
            case "region":
                return regionTxtBox.getAttribute("validationMessage");
            case "postalcode":
                return postalCodeTxtBox.getAttribute("validationMessage");
            case "country":
                return countryTxtBox.getAttribute("validationMessage");
            case "homephone":
                return homePhoneTxtBox.getAttribute("validationMessage");
            case "mobilephone":
                return mobilePhoneTxtBox.getAttribute("validationMessage");
            case "workphone":
                return workPhoneTxtBox.getAttribute("validationMessage");
            case "termscheckmark":
                return agreeTermsCheckBox.getAttribute("validationMessage");
            default:
                return "Unknown field: " + fieldName;
        }
    }
}

