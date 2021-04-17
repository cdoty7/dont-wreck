package learn.mastery.ui;

import learn.mastery.model.Guest;
import learn.mastery.model.Host;
import learn.mastery.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class View {

    Scanner console = new Scanner(System.in);

    public int mainMenu(){
        displayHeader("Main Menu");
        displayMessage("0. Exit");
        displayMessage("1. View Reservations for Host");
        displayMessage("2. Make Reservation");
        displayMessage("3. Edit Reservation");
        displayMessage("4. Cancel Reservation");
        displayMessage("Select [0-4]: ");
        int input = Integer.parseInt(console.next());
        return input;
    }

    public void displayReservations(List<Reservation> reservations) {
        Host host = new Host();
        Guest guest = new Guest();
        System.out.printf("%s: %s, %s",
                host.getLastName(),
                host.getCity(),
                host.getState());
        for (Reservation reservation : reservations) {
            System.out.printf("ID: %s, %s - %s, Guest: %s, %s, Email: %s",
                    reservation.getReservationId(),
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    guest.getLastName(),
                    guest.getFirstName(),
                    guest.getGuestEmail());
        }
    }

    public String promptHostEmail() {
    displayMessage("Host Email: ");
    String hostEmail = console.next();
    return hostEmail;
    }

    public String promptGuestEmail(){
        displayMessage("Guest Email: ");
        String guestEmail = console.next();
        return guestEmail;
    }

    public void displayHeader(String message){
        System.out.println();
        System.out.println(message);
        System.out.println("-".repeat(message.length()));
    }

    public void displayMessage(String message){
        System.out.println(message);
    }
}
