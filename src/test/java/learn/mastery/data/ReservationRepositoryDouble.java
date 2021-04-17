package learn.mastery.data;

import learn.mastery.model.Host;
import learn.mastery.model.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReservationRepositoryDouble implements ReservationRepository {

    private List<Reservation> reservations = new ArrayList<>();
    final UUID hostId = UUID.fromString("2e72f86c-b8fe-4265-b4f1-304dea8762db");

    public ReservationRepositoryDouble() {
        Reservation reservation = new Reservation();
        Host host = new Host();
        host.setStandardRate(new BigDecimal("150"));
        host.setWeekendRate(new BigDecimal("150"));
        reservation.setHostId(hostId);
        reservation.setReservationId(13);
        reservation.setStartDate(LocalDate.of(2021, 5, 6));
        reservation.setEndDate(LocalDate.of(2021, 5, 7));
        reservation.setGuestId("400");


        reservations.add(reservation);
    }

    public List<Reservation> viewReservationsByHost(UUID hostId) throws DataAccessException {
        return reservations.stream()
                .filter(reservation -> reservation.getHostId().equals(hostId))
                .collect(Collectors.toList());
    }

    public Reservation addReservation(Reservation reservation) throws DataAccessException {
        reservations.add(reservation);
        return reservation;
    }

    public boolean editReservation(Reservation reservation) throws DataAccessException {
        return false;
    }

    public boolean cancelReservation(Reservation reservation) throws DataAccessException {
        reservations.remove(reservation);
        return false;
    }

}
