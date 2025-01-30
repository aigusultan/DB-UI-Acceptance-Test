package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ex24_4_1 {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://restcountries.com/v3.1";

        Response response = given()
                .pathParams("CURRENCY_CODE", "aed")
                .queryParam("fields", "languages,capital,currencies")
                .when()
                .get("/currency/{CURRENCY_CODE}");

                response.prettyPrint();
                System.out.println("Status code " + response.getStatusCode());
    }
}
