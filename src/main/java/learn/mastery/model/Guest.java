package learn.mastery.model;

public class Guest {
    int guestId;
    private String firstName;
    private String lastName;
    private String guestEmail;

    public Guest(int guestId, String firstName, String lastName, String guestEmail) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.guestEmail = guestEmail;
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

    public String getGuestEmail() {
        return guestEmail;
    }
}
