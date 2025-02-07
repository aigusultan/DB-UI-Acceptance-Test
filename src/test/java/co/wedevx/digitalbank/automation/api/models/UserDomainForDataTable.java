package co.wedevx.digitalbank.automation.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDomainForDataTable {
    private String title;
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;
    private String ssn;
    private String emailAddress;
    private String password;
    private String address;
    private String locality;
    private String region;
    private String postalCode;
    private String country;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;

    @Override
    public String toString() {
        return "User{" +
                "title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", ssn='" + ssn + '\'' +
                ", email='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", locality='" + locality + '\'' +
                ", region='" + region + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                '}';
    }
}
