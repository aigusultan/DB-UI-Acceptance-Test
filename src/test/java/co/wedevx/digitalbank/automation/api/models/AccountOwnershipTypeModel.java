package co.wedevx.digitalbank.automation.api.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountOwnershipTypeModel {
    private int id;
    private String code;
    private String name;

        //    "ownershipType": {
        //        "id": 17,
        //        "code": "IND",
        //        "name": "Individual"

    public static AccountOwnershipTypeModel createDefaultSCKAccountOwnershipTypeModel() {
        AccountOwnershipTypeModel accountOwnershipTypeModel = new AccountOwnershipTypeModel();

        accountOwnershipTypeModel.setId(17);
        accountOwnershipTypeModel.setCode("IND");
        accountOwnershipTypeModel.setName("Individual");

        return accountOwnershipTypeModel;
    }
}
