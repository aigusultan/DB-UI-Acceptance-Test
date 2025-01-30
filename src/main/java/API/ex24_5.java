package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ex24_5 {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://api.restful-api.dev";

        String bodyPayload = "{\n" +
                "   \"name\": \"Apple MacBook Pro 16\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1849.99,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";


        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyPayload)
                .when()
                .post("/objects");
        response.prettyPrint();

        // Retrieve the value of the "name" key
        String nameValue = response.jsonPath().getString("name");
        String idValue = response.jsonPath().getString("id");

        // Print or use the retrieved value
        System.out.println("Name: " + nameValue);

        Response response1 = given()
                .when()
                .get("/objects/" + idValue);

        response1.prettyPrint();
        System.out.println("Status code of get method: " + response1.getStatusCode());

        String updatePayload = "{\n" +
                "   \"name\": \"Apple MacBook Pro 16\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 2049.99,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\",\n" +
                "      \"color\": \"silver\"\n" +
                "   }\n" +
                "}";

        Response response2 = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(updatePayload)
                .when()
                .pathParams("OBJECT_ID", idValue)
                .put("objects/{OBJECT_ID}");

        response2.prettyPrint();
        System.out.println("The status code for put is " + response2.statusCode());
    }
}
