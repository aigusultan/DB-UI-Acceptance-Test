package co.wedevx.digitalbank.automation.api.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseModel {
    private int id;
    private String name;
    private long accountNumber;
    private double currentBalance;
    private double openingBalance;
    private double interestRate;
    private double paymentAmount;
    private int paymentTerm;
    private AccountTypeModel accountType;
    private AccountOwnershipTypeModel ownershipType;
    private AccountStandingModel accountStanding;
    private String dateOpened;
    private String dateClosed;
    private String paymentDue;

}
