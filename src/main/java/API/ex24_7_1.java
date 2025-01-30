package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ex24_7_1 {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://aigulsul6411.mydevx.com/bank/api/v1";

        Response authRequestResponse = given()
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!") //this method takes key and value pair
                .when()
                .post("/auth"); //all CRUD methods after when return a response object

        JsonPath authResponseJsonPath = authRequestResponse.jsonPath();

        String authToken = authResponseJsonPath.getString("authToken");

        Response getUserResponse = given()
                .header("Authorization", "Bearer " + authToken)
                .get("/users");

        JsonPath jsonResponse = getUserResponse.jsonPath();
        List<Integer> allIDValues = jsonResponse.getList("id");
        //The getList() method is part of RestAssured's JsonPath class, which allows you to extract a
        // list of values from a JSON response using a specified JSONPath expression.
        //The getList() method is used to extract all the values of a particular key or field in a
        // JSON structure and return them as a Java List.

        int count = allIDValues.size();

        System.out.println("Number of 'id' keys: " + count);
        System.out.println("List of IDs: " + allIDValues);

    }
}
