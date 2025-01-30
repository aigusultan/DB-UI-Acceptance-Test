package API;

import API.domains.Account;
import API.domains.AuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ex24_12_1 {
    public static void main(String[] args) throws JsonProcessingException {
        RestAssured.baseURI = "http://aigulsul6411.mydevx.com/bank/api/v1";

        Response authRequestResponse = given()
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!") //this method takes key and value pair
                .when()
                .post("/auth");

        ObjectMapper mapper = new ObjectMapper();
        AuthToken authToken = mapper.readValue(authRequestResponse.asString(), AuthToken.class);

        Response getUserResponse = given()
                .header("Authorization", "Bearer " + authToken.getAuthToken())
                .pathParams("ACCOUNT_ID", 160)
                .get("account/{ACCOUNT_ID}");

        getUserResponse.prettyPrint();

        Account accountResponse = mapper.readValue(getUserResponse.asString(), Account.class);
        System.out.println(accountResponse.getId());
        System.out.println(accountResponse.getName());
        System.out.println(accountResponse.getAccountNumber());
        System.out.println(accountResponse.getCurrentBalance());
        System.out.println(accountResponse.getOpeningBalance());
        System.out.println(accountResponse.getInterestRate());
        System.out.println(accountResponse.getInterestRate());
        System.out.println(accountResponse.getPaymentAmount());
        System.out.println(accountResponse.getPaymentTerm());
        System.out.println(accountResponse.getAccountType().toString());
        System.out.println(accountResponse.getOwnershipType().toString());
        System.out.println(accountResponse.getAccountStanding().toString());
        System.out.println(accountResponse.getDateOpened());
        System.out.println(accountResponse.getDateClosed());
        System.out.println(accountResponse.getPaymentDue());

    }
}
