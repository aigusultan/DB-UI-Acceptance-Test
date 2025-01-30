package API.domains;

public class Account2 {

    private String accountName;
    private String accountTypeCode;
    private double openingDeposit;
    private String ownerTypeCode;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public double getOpeningDeposit() {
        return openingDeposit;
    }

    public void setOpeningDeposit(double openingDeposit) {
        this.openingDeposit = openingDeposit;
    }

    public String getOwnerTypeCode() {
        return ownerTypeCode;
    }

    public void setOwnerTypeCode(String ownerTypeCode) {
        this.ownerTypeCode = ownerTypeCode;
    }
}
