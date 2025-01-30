package API.domains;

public class AccountType {
    private int id;
    private String code;
    private String category;
    private String name;
    private double interestRate;
    private double minDeposit;
    private double overdraftLimit;
    private double overdraftFee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMinDeposit() {
        return minDeposit;
    }

    public void setMinDeposit(double minDeposit) {
        this.minDeposit = minDeposit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftFee() {
        return overdraftFee;
    }

    public void setOverdraftFee(double overdraftFee) {
        this.overdraftFee = overdraftFee;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", interestRate=" + interestRate +
                ", minDeposit=" + minDeposit +
                ", overdraftLimit=" + overdraftLimit +
                ", overdraftFee=" + overdraftFee +
                '}';
    }
}
