package pages;

public class UserData {

    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String zip;
    public String country;

    // Optional: convenient constructor / toString
    @Override
    public String toString() {
        return username + "," + password + "," + firstName + "," + lastName + "," + email;
    }

}
