package learn.mastery.ui;

import learn.mastery.data.DataAccessException;
import learn.mastery.domain.GuestService;
import learn.mastery.domain.HostService;
import learn.mastery.domain.ReservationService;
import learn.mastery.domain.Result;
import learn.mastery.model.Guest;
import learn.mastery.model.Host;
import learn.mastery.model.Reservation;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class Controller {
    private final ReservationService reservationService;
    private final HostService hostService;
    private final GuestService guestService;
    private final View view;

    Scanner console = new Scanner(System.in);

    public Controller(ReservationService reservationService, HostService hostService, GuestService guestService, View view) {
        this.reservationService = reservationService;
        this.hostService = hostService;
        this.guestService = guestService;
        this.view = view;
    }

    public void run(){
        boolean isRunning = true;
        view.displayHeader("Don't Wreck My House");
        while(isRunning) {
            try {
                int input = view.mainMenu();
                runMenu(input);
            } catch (DataAccessException ex) {
                view.displayMessage("Error: " + (List.of(ex.getMessage())));
            }
        }
        view.displayMessage("Goodbye");
    }

    public void runMenu(int input) throws DataAccessException{
        if (input > 0) {
            switch (input) {
                case 1:
                    viewReservationsByHost();
                    break;
                case 2:
                    addReservation();
                    break;
                case 3:
                    editReservation();
                    break;
                case 4:
                    cancelReservation();
                    break;
            }
        }
    }

    public void viewReservationsByHost() throws DataAccessException {
        view.displayHeader("View Reservations for Host");
        Host host = getHost();
        UUID hostId = host.getHostId();
        List<Reservation> reservations = reservationService.viewReservationsByHost(hostId);
        view.displayReservations(reservations);

        Boolean isRunning = true;
    }

    public void addReservation() throws DataAccessException {
        view.displayHeader("Add Reservation");
        Host host = getHost();
        UUID hostId = host.getHostId();

        Guest guest = getGuest();

        List<Reservation> reservations = reservationService.viewReservationsByHost(hostId);
        view.displayReservations(reservations); //need to only display reservation for specific guest
        String input = "";

        do {
            LocalDate startDate = view.promptStartDate();
            LocalDate endDate = view.promptStartDate();
            Reservation reservation = new Reservation();
            BigDecimal total = new BigDecimal(String.valueOf(reservationService.calculateTotal(reservation, hostId)));

            view.displayHeader("Summary");
            view.displayMessage("Start: " + startDate);
            view.displayMessage("End: " + endDate);
            view.displayMessage("Is this ok? [y/n]: ");
            input = console.next();
        }while(input.equalsIgnoreCase("n"));

        Reservation reservation = new Reservation();
        Result result = reservationService.editReservation(reservation);

        if (result.isSuccess()) {
            view.displayMessage("Reservation added.");
        }
    }

    public void editReservation() throws DataAccessException {
        view.displayHeader("Edit Reservation");
        Host host = getHost();
        UUID hostId = host.getHostId();

        Guest guest = getGuest();

        List<Reservation> reservations = reservationService.viewReservationsByHost(hostId);
        view.displayReservations(reservations); //need to only display reservation for specific guest

        int reservationId = view.promptReservationId();
        view.displayMessage("Editing reservation " + reservationId);
        String input = "";

        do {
            LocalDate startDate = view.promptStartDate();
            LocalDate endDate = view.promptStartDate();
            Reservation reservation = new Reservation();
            BigDecimal total = new BigDecimal(String.valueOf(reservationService.calculateTotal(reservation, hostId)));

            view.displayHeader("Summary");
            view.displayMessage("Start: " + startDate);
            view.displayMessage("End: " + endDate);
            view.displayMessage("Is this ok? [y/n]: ");
            input = console.next();
        }while(input.equalsIgnoreCase("n"));

        Reservation reservation = new Reservation();
        Result result = reservationService.editReservation(reservation);

        if (result.isSuccess()) {
            view.displayMessage("Reservation updated.");
        }

    }

    public void cancelReservation() throws DataAccessException {
        view.displayHeader("Delete Reservation");
        view.displayHeader("Edit Reservation");
        Host host = getHost();
        UUID hostId = host.getHostId();

        Guest guest = getGuest();

        List<Reservation> reservations = reservationService.viewReservationsByHost(hostId);
        view.displayReservations(reservations); //need to only display reservation for specific guest

        int reservationId = view.promptReservationId();
        Reservation reservation = new Reservation();
        boolean result = reservationService.cancelReservation(reservation);

        if (result) {
            view.displayMessage("Reservation cancelled.");
        }
    }

    private Host getHost() throws DataAccessException {
        String hostEmail = view.promptHostEmail();
        return hostService.findByEmail(hostEmail);
    }

    private Guest getGuest() throws DataAccessException {
        String guestEmail = view.promptGuestEmail();
        return guestService.findByEmail(guestEmail);
    }
}
