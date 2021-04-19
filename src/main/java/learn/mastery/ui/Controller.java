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
        String hostLastName = host.getLastName();
        String hostCity = host.getCity();
        String hostState = host.getState();

        List<Reservation> reservations = reservationService.viewReservationsByHost(hostId);

        Guest guest = new Guest();
        for (Reservation reservation : reservations) {
            String guestId = reservation.getGuestId();
            guest = guestService.findById(guestId);
        }

        view.displayReservations(reservations, hostLastName, hostCity, hostState, guest);

        Boolean isRunning = true;
    }

    public void addReservation() throws DataAccessException {
        view.displayHeader("Add Reservation");
        Host host = getHost();
        UUID hostId = host.getHostId();


        getReservationsForGuest(hostId);
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
        getReservationsForGuest(hostId);

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
        Host host = getHost();
        UUID hostId = host.getHostId();
        getReservationsForGuest(hostId);

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

    private void getReservationsForGuest(UUID hostId) throws DataAccessException {
        Guest guest = getGuest();
        String guestId = guest.getGuestId();

        List<Reservation> reservations = reservationService.viewReservationsByHost(hostId);
        reservations.stream()
                .filter(reservation -> reservation.getGuestId().equals(guestId))
                .collect(Collectors.toList());
    }
}
