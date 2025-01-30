package API;

import API.domains.User2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ex24_14_1 {
    public static void main(String[] args) throws JsonProcessingException {
        FakerClass generator = new FakerClass();

        Map<String, String> userData = generator.generateRandomUser();

        User2 user = new User2();
        user.setName(userData.get("name"));
        user.setEmail(userData.get("email"));
        user.setGender(userData.get("gender"));
        user.setStatus(userData.get("status"));

        ObjectMapper mapper = new ObjectMapper();
        String bodyPayload = mapper.writeValueAsString(user); //no need to pretty print cuz computers don't care

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        String token = "f181f7510937089666195624585f26288cbe2d92aed002411fdeede6cc292248";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .accept(ContentType.JSON)//message format of the response body
                .contentType(ContentType.JSON)
                .body(bodyPayload)
                .when()
                .post("/users");

        System.out.println(response.statusCode());
        System.out.println(bodyPayload);

        Response response2 = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users");

        response2.prettyPrint();

    }
}


