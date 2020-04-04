package ro.siit;

public class Contact {
    private int contactID; //va fi folosit pt apelare si cautare rapida
    private String phoneNumber;
    private String firstName;
    private String lastName;


    public Contact(int contactID, String phoneNumber, String firstName, String lastName) {
        this.contactID = contactID;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "contactID: " +  contactID + ", firstName: " + firstName +  ", lastName: " + lastName +
        ", phoneNumber: " + phoneNumber;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getContactID() {
        return contactID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
