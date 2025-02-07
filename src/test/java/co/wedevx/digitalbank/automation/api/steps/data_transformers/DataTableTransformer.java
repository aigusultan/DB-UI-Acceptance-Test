package co.wedevx.digitalbank.automation.api.steps.data_transformers;

import co.wedevx.digitalbank.automation.api.models.AccountModel;
import co.wedevx.digitalbank.automation.api.models.UserDomainForDataTable;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTableTransformer {

    @DataTableType
    public UserDomainForDataTable userEntry(Map<String, String> entry) {
        String title = entry.get("title");
        String firstName = entry.get("firstName");
        String lastName = entry.get("lastName");
        String gender = entry.get("gender");
        String dob = entry.get("dob");
        String ssn = entry.get("ssn");
        String email = entry.get("emailAddress");
        String password = entry.get("password");
        String address = entry.get("address");
        String locality = entry.get("locality");
        String region = entry.get("region");
        String postalCode = entry.get("postalCode");
        String country = entry.get("country");
        String homePhone = entry.get("homePhone");
        String mobilePhone = entry.get("mobilePhone");
        String workPhone = entry.get("workPhone");

        return new UserDomainForDataTable(title, firstName, lastName, gender, dob, ssn, email, password, address, locality,
                region, postalCode, country, homePhone, mobilePhone, workPhone);
    }

    @DataTableType
    public AccountModel accountEntry(Map<String, String> entry) {
        String accountName = entry.get("accountName");
        String accountTypeCode = entry.get("accountTypeCode");
        double openingDeposit = Double.parseDouble(entry.get("openingDeposit"));
        String ownerTypeCode = entry.get("ownerTypeCode");
        String accountStandingName = entry.get("accountStandingName");

        return new AccountModel(accountName, accountTypeCode, openingDeposit, ownerTypeCode, accountStandingName);
    }
}
