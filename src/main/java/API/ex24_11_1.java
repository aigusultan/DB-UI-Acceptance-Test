package API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ex24_11_1 {
    public static void main(String[] args) throws JsonProcessingException {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        String token = "f181f7510937089666195624585f26288cbe2d92aed002411fdeede6cc292248";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParams("USER_ID", 7664297)
                .when()
                .get("/users/{USER_ID}");

        // Get the response body as a JSON string
        String jsonResponse = response.getBody().asString();

        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialize the JSON response into the User object
        User user = objectMapper.readValue(jsonResponse, User.class);

        //User user = response.as(User.class);
        System.out.println(user);

        Response response2 = given()
                .header("Authorization", "Bearer " + token)
                .pathParams("POST_ID", 188848)
                .when()
                .get("/posts/{POST_ID}");

        // Get the response body as a JSON string
        String jsonResponse2 = response2.getBody().asString();

        // Deserialize the JSON response into the Post object
        Post post = objectMapper.readValue(jsonResponse2, Post.class);

        // Print the deserialized post object
        System.out.println(post);

    }
}

class User {
    private String id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

class Post {
    private String id;
    private String user_id;
    private String title;
    private String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", userId='" + user_id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
