package learn.mastery.model;

public class Guest {
    int guestId;
    private String firstName;
    private String lastName;
    private String guestEmail;

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

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }
}
