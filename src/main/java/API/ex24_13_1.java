package API;

import API.domains.Account;
import API.domains.AuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ex24_13_1 {
    public static void main(String[] args) throws JsonProcessingException {
        RestAssured.baseURI = "http://aigulsul6411.mydevx.com/bank/api/v1";

        Response authRequestResponse = given()
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!") //this method takes key and value pair
                .when()
                .post("/auth");

        authRequestResponse.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        AuthToken authToken = mapper.readValue(authRequestResponse.asString(), AuthToken.class);

        Response getUserResponse = given()
                .header("Authorization", "Bearer " + authToken.getAuthToken())
                .get("account");

        Account[] accountArr = mapper.readValue(getUserResponse.asString(), Account[].class);
        List<Account> accountList = Arrays.asList(accountArr);

        for (Account account : accountList) {
            if (account.getAccountNumber() % 2 == 0) {
                System.out.println(account.getName());
                System.out.println("-----------------");
            }

            if (account.getOpeningBalance() < 50) {
                System.out.println(account.getCurrentBalance());
                System.out.println("--------------------");
            }

        }
    }
}
