package API;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class ex24_3_1 {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        String BEARER_TOKEN = "f181f7510937089666195624585f26288cbe2d92aed002411fdeede6cc292248";

        given()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParams("user_id", 7655349)
                .when()
                .get("users/{user_id}/posts").prettyPrint();
    }
}
