package co.wedevx.digitalbank.automation.api.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountStandingModel {
    private int id;
    private String code;
    private String name;

    //    },
    //    "accountStanding": {
    //        "id": 19,
    //        "code": "A1",
    //        "name": "Open"
    //    }

    public static AccountStandingModel createDefaultSCKAccountStandingModel() {
        AccountStandingModel accountStandingModel = new AccountStandingModel();

        accountStandingModel.setId(19);
        accountStandingModel.setCode("A1");
        accountStandingModel.setName("Open");

        return accountStandingModel;
    }
}
