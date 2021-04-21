package learn.mastery.ui;

import learn.mastery.data.DataAccessException;
import learn.mastery.domain.*;
import learn.mastery.model.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    public void run() {
        boolean isRunning = true;
        view.displayHeader("Don't Wreck My House");
        while (isRunning) {
            try {
                int input = view.mainMenu();
                runMenu(input);
            } catch (DataAccessException ex) {
                view.displayMessage("Error: " + (List.of(ex.getMessage())));
            }
        }
        view.displayMessage("Goodbye");
    }

    public void runMenu(int input) throws DataAccessException {
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

        List<Reservation> reservations = reservationService.viewReservationsByHost(host.getHostId());
        Guest guest = new Guest();

        view.displayReservations(reservations, host);

        Boolean isRunning = true;
    }

    public void addReservation() throws DataAccessException {
        view.displayHeader("Add Reservation");
        Reservation reservation = new Reservation();
        Host host = getHost();
        Guest guest = getGuest();

        List<Reservation> reservations = reservationService.viewReservationsByHost(host.getHostId());
        view.displayReservations(reservations, host);

        populateReservation(host, guest);

        Result result = reservationService.addReservation(reservation);

        if (result.isSuccess()) {
            view.displayMessage("Reservation added.");
        }
    }

    public void editReservation() throws DataAccessException {
        view.displayHeader("Edit Reservation");
        Reservation reservation = new Reservation();
        Host host = getHost();
        Guest guest = getGuest();

        List<Reservation> reservations = reservationService.viewReservationsByHost(host.getHostId());
        view.displayReservations(reservations, host);

        int reservationId = view.promptReservationId();
        view.displayMessage("Editing reservation " + reservation.getReservationId());

        populateReservation(host, guest);

        Result result = reservationService.editReservation(reservation);

        if (result.isSuccess()) {
            view.displayMessage("Reservation updated.");
        }

    }

    public void cancelReservation() throws DataAccessException {
        view.displayHeader("Delete Reservation");
        Reservation reservation = new Reservation();
        Host host = getHost();

        reservationService.viewReservationsByHost(host.getHostId());
        List<Reservation> reservations = reservationService.viewReservationsByHost(host.getHostId());
        view.displayReservations(reservations, host);

        reservation.setReservationId(view.promptReservationId());
        reservation.setHost(host);
        boolean result = reservationService.cancelReservation(reservation);

        if (result) {
            view.displayMessage("Reservation cancelled.");
        }
    }

    private Host getHost() throws DataAccessException {
        String hostEmail = view.promptHostEmail();
        Host host = hostService.findByEmail(hostEmail);
        UUID hostId = host.getHostId();
        String hostLastName = host.getLastName();
        String hostCity = host.getCity();
        String hostState = host.getState();
        return host;
    }

    private Guest getGuest() throws DataAccessException {
        String guestEmail = view.promptGuestEmail();
        return guestService.findByEmail(guestEmail);
    }

    private void populateReservation(Host host, Guest guest) throws DataAccessException {
        String input = "";

        do {
            LocalDate startDate = view.promptStartDate();
            LocalDate endDate = view.promptEndDate();
            Reservation reservation = new Reservation(host, startDate, endDate, guest);
            BigDecimal total = new BigDecimal(String.valueOf(reservationService.calculateTotal(reservation)));

            view.displayHeader("Summary");
            view.displayMessage("Start: " + startDate);
            view.displayMessage("End: " + endDate);
            view.displayMessage("Total: $" + total);
            view.displayMessage("Is this ok? [y/n]: ");
            input = console.next();
        } while (input.equalsIgnoreCase("n"));
    }

}
