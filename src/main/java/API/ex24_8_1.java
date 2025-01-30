package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ex24_8_1 {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(4, 71, 75, 122, 128, 134, 157);

        int[] resultArray = findMinMax(numbers);

        System.out.println("Smallest Value " + resultArray[0]);
        System.out.println("Largest Value " + resultArray[1]);

        RestAssured.baseURI = "http://aigulsul6411.mydevx.com/bank/api/v1";

        Response authRequestResponse = given()
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!") //this method takes key and value pair
                .when()
                .post("/auth"); //all CRUD methods after when return a response object

        JsonPath authResponseJsonPath = authRequestResponse.jsonPath();

        String authToken = authResponseJsonPath.getString("authToken");

        Response allAccountsLargestIdResponse = given()
                .header("Authorization", "Bearer " + authToken)
                .pathParams("id", resultArray[1])
                .get("/user/{id}/account"); //this will return all accounts that this user has

        System.out.println("Largest ID accounts:");
        allAccountsLargestIdResponse.prettyPrint();

        JsonPath largestResponseJsonPath = allAccountsLargestIdResponse.jsonPath();

        String largestAccountTypeCode = largestResponseJsonPath.getString("[0].accountType.code");
        String largestAccountOwnership = largestResponseJsonPath.getString("[0].ownershipType.code");
        String largestAccountStandingCode = largestResponseJsonPath.getString("[0].accountStanding.code");

        System.out.println("accountType.code: " + largestAccountTypeCode);
        System.out.println("ownershipType.code: " + largestAccountOwnership);
        System.out.println("accountStanding.code: " + largestAccountStandingCode);

        Response allAccountsSmallestIdResponse = given()
                .header("Authorization", "Bearer " + authToken)
                .pathParams("id", resultArray[0]) //
                .get("/user/{id}/account"); //this will return all accounts that this user has

        System.out.println("Smallest ID accounts:");
        allAccountsSmallestIdResponse.prettyPrint();

        JsonPath smallestResponseJsonPath = allAccountsSmallestIdResponse.jsonPath();

        String smallestAccountTypeCode = smallestResponseJsonPath.getString("accountType.code");
        String smallestAccountOwnership = smallestResponseJsonPath.getString("ownershipType.code");
        String smallestAccountStandingCode = smallestResponseJsonPath.getString("accountStanding.code");

        System.out.println("accountType.code: " + smallestAccountTypeCode);
        System.out.println("ownershipType.code: " + smallestAccountOwnership);
        System.out.println("accountStanding.code: " + smallestAccountStandingCode);

    }

    public static int[] findMinMax(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }

        int max = Integer.MIN_VALUE; //-2,147,483,648
        int min = Integer.MAX_VALUE; //Integer.MAX_VALUE represents the largest possible value for an int in Java (2,147,483,647)

        for (int number : numbers) {
            max = Math.max(max, number);
            min = Math.min(min, number);
        }

        return new int[]{min, max};

    }
}
