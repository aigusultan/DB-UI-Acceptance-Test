package co.wedevx.digitalbank.automation.api.steps;

import co.wedevx.digitalbank.automation.api.models.*;
import co.wedevx.digitalbank.automation.api.utils.ObjectMapperUtils;
import co.wedevx.digitalbank.automation.api.utils.RestHttpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountSteps {

    private AccountResponseModel accountResponseModel;

    @When("the following account is created")
    public void the_following_account_is_created(List<AccountModel> accountModelList) throws JsonProcessingException {
        System.out.println(accountModelList);

        //serialize the POJO to send it to restAPI to create an account
        String accountRequestBodyJson = ObjectMapperUtils.objectMapper.writeValueAsString(accountModelList.get(0));

//        Response createAccountResponse = RestHttpRequest.requestSpecification
//                .body(accountRequestBodyJson)
//                .pathParams("id", UserSteps.testUserId)
//                .when()
//                .post("/user/{id}/account");

        Response createAccountResponse = RestHttpRequest.sendPostRequestWithPathParam(
                "/user/{id}/account", "id", UserSteps.testUserId,
                accountRequestBodyJson);

        createAccountResponse.prettyPrint();

        //now that we created an account we need to assert the details of it
        //we deserialize the account info received and perform assertions
        System.out.println(createAccountResponse.asString());
        accountResponseModel = ObjectMapperUtils.objectMapper.readValue(
                createAccountResponse.asString(), AccountResponseModel.class);
    }

    @Then("the following account details are returned in the response")
    public void theFollowingAccountDetailsAreReturnedInTheResponse(List<AccountModel> accountModelList) {
        //expected comes from the table - the info we used to create an account
        //actual comes from the deserialized info that we got when we requested the info of a just created account
        assertEquals(accountModelList.get(0).getAccountName(),
                accountResponseModel.getName(), "Account Name mismatch after creating an account");

        assertEquals(accountModelList.get(0).getAccountTypeCode(),
                accountResponseModel.getAccountType().getCode(), "Account Code mismatch after creating an account");

        assertEquals(accountModelList.get(0).getOpeningDeposit(),
                accountResponseModel.getOpeningBalance(), "Account Opening deposit mismatch after creating an account");

        assertEquals(accountModelList.get(0).getOwnerTypeCode(),
                accountResponseModel.getOwnershipType().getCode(), "Account ownership type code mismatch after creating an account");

        assertEquals(accountModelList.get(0).getAccountName(),
                accountResponseModel.getName(), "Account Name mismatch after creating an account");

        assertEquals(accountModelList.get(0).getAccountStandingName(),
                accountResponseModel.getAccountStanding().getName(), "Account Standing Name mismatch after creating an account");

        //things that do not change, you can validate them in steps without putting it in the feature file
        //but because we did not include the static details in the feature file we need to get the expected
        //values from a different source

        //validate AccountType
        //we already have AccountTypeModel class we use to deserialize files with
        //now we need an accountType object which has the static values populated
        //so we need to create an accountType object that will have all these values populated in it
        //where do we create that class? we will create it in the accountTypeModel class

        if (accountModelList.get(0).getAccountTypeCode().equalsIgnoreCase("SCK")) {
            AccountTypeModel expectedAccountTypeModel = AccountTypeModel.createDefaultSCKAccountTypeModel();

            assertEquals(expectedAccountTypeModel.getId(), accountResponseModel.getAccountType().getId(), "Default SCK account ID mismatch");
            assertEquals(expectedAccountTypeModel.getCode(), accountResponseModel.getAccountType().getCode(), "Default SCK account code mismatch");
            assertEquals(expectedAccountTypeModel.getCategory(), accountResponseModel.getAccountType().getCategory(), "Default SCK account category mismatch");
            assertEquals(expectedAccountTypeModel.getOverdraftLimit(), accountResponseModel.getAccountType().getOverdraftLimit(), "Default SCK account overdraft limit mismatch");
            assertEquals(expectedAccountTypeModel.getName(), accountResponseModel.getAccountType().getName(), "Default SCK account name mismatch");
            assertEquals(expectedAccountTypeModel.getMinDeposit(), accountResponseModel.getAccountType().getMinDeposit(), "Default SCK account type mismatch");
            assertEquals(expectedAccountTypeModel.getInterestRate(), accountResponseModel.getAccountType().getInterestRate(), "Default SCK account interest rate mismatch");
            assertEquals(expectedAccountTypeModel.getOverdraftFee(), accountResponseModel.getAccountType().getOverdraftFee(), "Default SCK account overdraft fee mismatch");

            //validate ownershipType
            AccountOwnershipTypeModel expectedAccountOwnershipTypeModel = AccountOwnershipTypeModel.createDefaultSCKAccountOwnershipTypeModel();
            assertEquals(expectedAccountOwnershipTypeModel.getId(), accountResponseModel.getOwnershipType().getId(), "Default SCK account ownership type ID mismatch");
            assertEquals(expectedAccountOwnershipTypeModel.getCode(), accountResponseModel.getOwnershipType().getCode(), "Default SCK account ownership type code mismatch");
            assertEquals(expectedAccountOwnershipTypeModel.getName(), accountResponseModel.getOwnershipType().getName(), "Default SCK account ownership type name mismatch");

            //validate AccountStanding
            AccountStandingModel expectedAccountStandingModel = AccountStandingModel.createDefaultSCKAccountStandingModel();
            assertEquals(expectedAccountStandingModel.getId(), accountResponseModel.getAccountStanding().getId(), "Default SCK account standing ID mismatch");
            assertEquals(expectedAccountStandingModel.getCode(), accountResponseModel.getAccountStanding().getCode(), "Default SCK account standing code mismatch");
            assertEquals(expectedAccountStandingModel.getName(), accountResponseModel.getAccountStanding().getName(), "Default SCK account standing name mismatch");
        }

        //{
        //    "id": 172(incremented variable),
        //    "name": "Elon Musk test Checking",
        //    "accountNumber": 486130065(incremented variable),
        //    "currentBalance": 155.00,
        //    "openingBalance": 155.00,
        //    "interestRate": 0.0,
        //    "paymentAmount": 0.0,
        //    "paymentTerm": 0,
        //    "accountType": {
        //        "id": 8,
        //        "code": "SCK",
        //        "category": "CHK",
        //        "name": "Standard Checking",
        //        "interestRate": 0.0,
        //        "minDeposit": 25.00,
        //        "overdraftLimit": 25.00,
        //        "overdraftFee": 10.00
        //    },
        //    "ownershipType": {
        //        "id": 17,
        //        "code": "IND",
        //        "name": "Individual"
        //    },
        //    "accountStanding": {
        //        "id": 19,
        //        "code": "A1",
        //        "name": "Open"
        //    },
        //    "dateOpened": "2023-10-26T05:41 (current time)",
        //    "dateClosed": null,
        //    "paymentDue": null
        //}

    }
}
