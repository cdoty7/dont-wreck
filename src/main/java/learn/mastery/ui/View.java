package learn.mastery.ui;

import learn.mastery.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

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

    public void displayReservations(List<Reservation> reservations, String hostLastName, String hostCity, String hostState) {
        Reservation reservation = new Reservation();
        System.out.printf("%s: %s, %s%n",
                hostLastName,
                hostCity,
                hostState);

        if(reservations == null || reservations.isEmpty()){
            System.out.println("No reservations found.");
            return;
        }
        for (Reservation r : reservations) {
            System.out.printf("ID: %s, %s - %s, Guest: %s, %s, Email: %s, Total: %s%n",
                    reservation.getReservationId(),
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getGuest().getLastName(),
                    reservation.getGuest().getFirstName(),
                    reservation.getGuest().getGuestEmail(),
                    reservation.getTotal());
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

    public LocalDate promptStartDate(){
        displayMessage("Reservation Start Date: ");
        LocalDate startDate= LocalDate.parse(console.next());
        return startDate;
    }

    public LocalDate promptEndDate(){
        displayMessage("Reservation End Date: ");
        LocalDate endDate= LocalDate.parse(console.next());
        return endDate;
    }

    public int promptReservationId(){
        displayMessage("Reservation ID: ");
        int reservationId = Integer.parseInt(console.next());
        return reservationId;
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
