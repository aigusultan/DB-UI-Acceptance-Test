package co.wedevx.digitalbank.automation.api.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileAPIModel {
    private int id;
    private String firstName;
    private String lastName;
    private String title;
    private char gender;
    private String ssn;
    private String dob;
    private String dom;
    private String emailAddress;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String address;
    private String locality;
    private String region;
    private String postalCode;
    private String country;
    private String password;

}
