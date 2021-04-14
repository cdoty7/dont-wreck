package learn.mastery.data;

import learn.mastery.model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository {

    List<Reservation> viewReservationsByHost(UUID hostId) throws DataAccessException;

    Reservation addReservation(Reservation reservation) throws DataAccessException;

    boolean editReservation(Reservation reservation) throws DataAccessException;

    boolean cancelReservation(Reservation reservation) throws DataAccessException;

}
