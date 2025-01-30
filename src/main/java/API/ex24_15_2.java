package API;

import API.domains.AuthToken;
import API.domains.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ex24_15_2 {
    public static void main(String[] args) throws JsonProcessingException {
        RestAssured.baseURI = "http://aigulsul6411.mydevx.com/bank/api/v1";
        Response authRequestResponse = given()
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!") //this method takes key and value pair
                .when()
                .post("/auth");

        ObjectMapper mapper = new ObjectMapper();
        AuthToken authToken = mapper.readValue(authRequestResponse.asString(), AuthToken.class);

        Transaction transaction = new Transaction();
        transaction.setDescription("Test transaction for Candy");
        transaction.setAmount(25000.00);
        transaction.setTransactionTypeCode("DPT");

        String jsonRequestPayload = mapper.writeValueAsString(transaction);
        System.out.println(jsonRequestPayload);

        Response response = given()
                .header("Authorization", "Bearer " + authToken.getAuthToken())
                .pathParams("id", 173)
                .accept(ContentType.JSON)//message format of the response body
                .contentType(ContentType.JSON)
                .body(jsonRequestPayload)
                .when()
                .post("/account/{id}/transaction");

        response.prettyPrint();
        System.out.println(response.getStatusCode());

        JsonPath jsonPathResponse = response.jsonPath();
        int transactionId = jsonPathResponse.get("id");
        System.out.println("Transaction ID is " + transactionId);



    }
}
