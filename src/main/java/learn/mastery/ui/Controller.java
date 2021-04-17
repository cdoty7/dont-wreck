package learn.mastery.ui;

import learn.mastery.data.DataAccessException;
import learn.mastery.domain.HostService;
import learn.mastery.domain.ReservationService;
import learn.mastery.model.Host;
import learn.mastery.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class Controller {
    private final ReservationService reservationService;
    private final HostService hostService;
    private final View view;

    public Controller(ReservationService reservationService, HostService hostService, View view) {
        this.reservationService = reservationService;
        this.hostService = hostService;
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
        Host host = new Host();
        hostService.findByEmail(view.promptHostEmail());
        UUID hostId = host.getHostId();
        List<Reservation> reservations = reservationService.viewReservationsByHost(hostId);
        view.displayReservations(reservations);
        Boolean isRunning = true;
    }

    public void addReservation(){

    }

    public void editReservation(){

    }

    public void cancelReservation(){

    }
}
