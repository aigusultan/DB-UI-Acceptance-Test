package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ex24_4_2 {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://restcountries.com/v3.1";

        Response response = given()
                .queryParam("fields", "languages,capital,currencies")
                .queryParam("status", "false")
                .when()
                .get("/independent");

        response.prettyPrint();
        System.out.println("Status code is: " + response.getStatusCode());
    }
}
