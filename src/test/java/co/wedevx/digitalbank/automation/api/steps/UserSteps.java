package co.wedevx.digitalbank.automation.api.steps;

import co.wedevx.digitalbank.automation.api.models.UserAPIModel;
import co.wedevx.digitalbank.automation.api.models.UserDomainForDataTable;
import co.wedevx.digitalbank.automation.api.utils.RestHttpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

import java.util.List;

import static co.wedevx.digitalbank.automation.api.utils.ObjectMapperUtils.objectMapper;

public class UserSteps {
    public static int testUserId = 0;

    public static int id = 0;

    @Given("the following user is in the db")
    public void the_following_user_is_in_the_db(List<UserDomainForDataTable> userDomainForDataTables) throws JsonProcessingException {
//        RestAssured.baseURI = "http://aigulsul6411.mydevx.com/bank/api/v1";
//
//        Response authRequestResponse = given()
//                .queryParam("username", "admin@demo.io")
//                .queryParam("password", "Demo123!") //this method takes key and value pair
//                .when()
//                .post("/auth"); //all CRUD methods after when return a response object
//
//        JsonPath authResponseJsonPath = authRequestResponse.jsonPath();
//        String authToken = authResponseJsonPath.getString("authToken");

        //convert POJO of users into a single user json string
//        ObjectMapper mapper = new ObjectMapper();
//        String createUserBodyPayload = mapper.writeValueAsString(users.get(0));
//        System.out.println(createUserBodyPayload);
//
        //to send a create user request
//        Response createUserRequestResponse = given()
//                .header("Authorization", "Bearer " + authToken)
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .body(createUserBodyPayload)
//                .when()
//                .post("user");

       // createUserRequestResponse.prettyPrint();

        String createUserBodyPayload = objectMapper.writeValueAsString(userDomainForDataTables.get(0));

        Response response = RestHttpRequest.requestSpecification
                .body(createUserBodyPayload)
                .when()
                .post("user");

        response.prettyPrint();
        //here we are deserializing the JSON formatted response into java POJO class object
        UserAPIModel testUser = objectMapper.readValue(response.asString(), UserAPIModel.class);
        testUserId = testUser.getId(); //and getting specific info using the pojo getters
        System.out.println(testUser.getUsername());
    }
}
