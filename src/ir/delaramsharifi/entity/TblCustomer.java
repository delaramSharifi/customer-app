package ir.delaramsharifi.entity;

public class TblCustomer {

    private Integer id;
    private String name;
    private String family;
    private String nationalCode;
    private String mobileNumber;
    private String password;

    public TblCustomer() {
    }

    public TblCustomer(Integer id, String name, String family, String nationalCode, String mobileNumber, String password) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.nationalCode = nationalCode;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer Information: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password;
    }
}
