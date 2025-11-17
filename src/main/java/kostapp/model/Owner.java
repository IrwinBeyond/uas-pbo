package kostapp.model;

public class Owner extends Person {
    private String ownerId;
    private String bankAccountNumber;

    public Owner(String name, String email, String phone, String ownerId, String bankAccountNumber) {
        super(name, email, phone);
        this.ownerId = ownerId;
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId='" + ownerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                '}';
    }
}
