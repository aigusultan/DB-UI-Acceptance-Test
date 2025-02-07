package co.wedevx.digitalbank.automation.api.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountTypeModel {
    private int id;
    private String code;
    private String category;
    private String name;
    private double interestRate;
    private double minDeposit;
    private double overdraftLimit;
    private double overdraftFee;

        //        "id": 8,
        //        "code": "SCK",
        //        "category": "CHK",
        //        "name": "Standard Checking",
        //        "interestRate": 0.0,
        //        "minDeposit": 25.00,
        //        "overdraftLimit": 25.00,
        //        "overdraftFee": 10.00

    //the default SCK account variables are always the same
    //this method will give us the expected result for our test cases
    public static AccountTypeModel createDefaultSCKAccountTypeModel() {
        AccountTypeModel accountTypeModel = new AccountTypeModel();

        accountTypeModel.setId(8);
        accountTypeModel.setCode("SCK");
        accountTypeModel.setCategory("CHK");
        accountTypeModel.setName("Standard Checking");
        accountTypeModel.setInterestRate(0.0);
        accountTypeModel.setMinDeposit(25);
        accountTypeModel.setOverdraftLimit(25.00);
        accountTypeModel.setOverdraftFee(10.00);

        return accountTypeModel;
    }
}
