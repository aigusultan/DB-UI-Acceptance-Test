package co.wedevx.digitalbank.automation.api.steps.data_transformers;

import co.wedevx.digitalbank.automation.api.models.User;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTableTransformer {

    @DataTableType
    public User userEntry(Map<String, String> entry) {
        String title = entry.get("title");
        String firstName = entry.get("firstName");
        String lastName = entry.get("lastName");
        String gender = entry.get("gender");
        String dob = entry.get("dob");
        String ssn = entry.get("ssn");
        String email = entry.get("email");
        String password = entry.get("password");
        String address = entry.get("address");
        String locality = entry.get("locality");
        String region = entry.get("region");
        String postalCode = entry.get("postalCode");
        String country = entry.get("country");
        String homePhone = entry.get("homePhone");
        String mobilePhone = entry.get("mobilePhone");
        String workPhone = entry.get("workPhone");

        return new User(title, firstName, lastName, gender, dob, ssn, email, password, address, locality,
                region, postalCode, country, homePhone, mobilePhone, workPhone);
    }
}
