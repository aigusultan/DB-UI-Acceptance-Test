package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ex24_6_1 {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://aigulsul6411.mydevx.com/bank/api/v1";

        String responsePayload = given()
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!") //this method takes key and value pair
                .when()
                .post("/auth").asString();

        //System.out.println(responsePayload);

        String authToken = responsePayload.substring(responsePayload.indexOf("eyJhbGciOiJIUzI1NiJ9"),
                responsePayload.lastIndexOf("\""));

        given()
                .header("Authorization", "Bearer " + authToken)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("/users")
                .prettyPrint();
    }
}
