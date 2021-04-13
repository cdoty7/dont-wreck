package learn.mastery.ui;

import learn.mastery.domain.HostService;
import learn.mastery.domain.ReservationService;
import org.springframework.stereotype.Component;

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
        System.out.println("running");
    }

    public void runMenu(){

    }

    public void viewReservationsByHost(){

    }

    public void addReservation(){

    }

    public void editReservation(){

    }

    public void cancelReservation(){

    }

    public void getHost(){

    }

    public void getGuest(){

    }
}
