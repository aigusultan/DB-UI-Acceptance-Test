package API;

import API.domains.Account2;
import API.domains.AuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ex24_15_1 {
    public static void main(String[] args) throws JsonProcessingException {
        RestAssured.baseURI = "http://aigulsul6411.mydevx.com/bank/api/v1";

        Response authRequestResponse = given()
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!") //this method takes key and value pair
                .when()
                .post("/auth");

        ObjectMapper mapper = new ObjectMapper();
        AuthToken authToken = mapper.readValue(authRequestResponse.asString(), AuthToken.class);

        Account2 account = new Account2();
        account.setAccountName("Testing automated creation for Candy");
        account.setAccountTypeCode("SAV");
        account.setOpeningDeposit(45000.00);
        account.setOwnerTypeCode("IND");

        String jsonRequestPayload = mapper.writeValueAsString(account);
        System.out.println(jsonRequestPayload);

        Response response = given()
                .header("Authorization", "Bearer " + authToken.getAuthToken())
                .pathParams("USER_ID", 166)
                .accept(ContentType.JSON)//message format of the response body
                .contentType(ContentType.JSON)
                .body(jsonRequestPayload)
                .when()
                .post("/user/{USER_ID}/account");

        response.prettyPrint();
        System.out.println(response.getStatusCode());

        JsonPath jsonPathResponse = response.jsonPath();
        int accountId = jsonPathResponse.get("id");
        System.out.println("Account ID is " + accountId);

        Response response2 = given()
                .header("Authorization", "Bearer " + authToken.getAuthToken())
                .pathParams("id", accountId)
                .when()
                .get("/account/{id}");

        response2.prettyPrint();
    }
}
