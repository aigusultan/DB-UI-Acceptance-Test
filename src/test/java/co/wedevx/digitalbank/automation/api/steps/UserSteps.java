package co.wedevx.digitalbank.automation.api.steps;

import co.wedevx.digitalbank.automation.api.models.User;
import io.cucumber.java.en.Given;

import java.util.List;

public class UserSteps {
    @Given("the following user is in the db")
    public void the_following_user_is_in_the_db(List<User> users) {
        System.out.println(users);

    }
}
