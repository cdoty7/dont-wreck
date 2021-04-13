package learn.mastery.data;

import learn.mastery.model.Reservation;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> viewReservationsByHost(String hostEmail);

    Reservation addReservation(Reservation reservation);

    boolean editReservation(Reservation reservation);

    boolean cancelReservation(Reservation reservation);

}
