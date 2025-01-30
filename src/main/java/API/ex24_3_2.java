package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ex24_3_2 {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://restcountries.com/v3.1";

        Response response = given()
                .pathParams("country_name", "slovakia")
                .when()
                .get("name/{country_name}");

        response.prettyPrint();

        System.out.println("Status code is for country " + response.getStatusCode());

        Response response2 = given()
                .pathParams("currency_code", "usd")
                .when()
                .get("/currency/{currency_code}");

        response2.prettyPrint();
        System.out.println("Status code is for currency " + response.getStatusCode());

    }
}
