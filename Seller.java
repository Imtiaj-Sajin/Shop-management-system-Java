import java.util.Date;

public class Seller extends Employee {
    private Date birthdate;
    private String address;
    private String phoneNumber;
    private String email;
    private int salesCount;

    public Seller(String name, int employeeId, double salary, Date birthdate, String address, String phoneNumber, String email, int salesCount) {
        super(name, employeeId, salary);
        this.birthdate = birthdate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salesCount = salesCount;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Sales Count: " + salesCount);
    }
}
