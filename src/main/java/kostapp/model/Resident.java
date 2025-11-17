package kostapp.model;

public class Resident extends Person {
    private String residentId;
    private String idCardNumber;

    public Resident(String name, String email, String phone, String residentId, String idCardNumber) {
        super(name, email, phone);
        this.residentId = residentId;
        this.idCardNumber = idCardNumber;
    }

    public String getResidentId() {
        return residentId;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "residentId='" + residentId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                '}';
    }
}
