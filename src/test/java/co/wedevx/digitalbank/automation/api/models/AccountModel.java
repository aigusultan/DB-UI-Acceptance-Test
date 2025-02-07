package co.wedevx.digitalbank.automation.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {
    private String accountName;
    private String accountTypeCode;
    private double openingDeposit;
    private String ownerTypeCode;
    private String accountStandingName;

    @Override
    public String toString() {
        return "AccountModel{" +
                "accountName='" + accountName + '\'' +
                ", accountTypeCode='" + accountTypeCode + '\'' +
                ", openingDeposit=" + openingDeposit +
                ", ownerTypeCode='" + ownerTypeCode + '\'' +
                ", accountStandingName='" + accountStandingName + '\'' +
                '}';
    }
}
