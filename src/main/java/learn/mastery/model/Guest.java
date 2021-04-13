package learn.mastery.model;

public class Guest {
    int guestId;
    private String firstName;
    private String lastName;
    private String email;

    public Guest(int guestId, String firstName, String lastName, String email) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getGuestId() {
        return guestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
