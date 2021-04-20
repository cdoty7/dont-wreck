package learn.mastery.domain;

import learn.mastery.data.DataAccessException;
import learn.mastery.data.GuestRepositoryDouble;
import learn.mastery.data.HostRepositoryDouble;
import learn.mastery.data.ReservationRepositoryDouble;
import learn.mastery.model.Guest;
import learn.mastery.model.Host;
import learn.mastery.model.Reservation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    ReservationService service = new ReservationService(new ReservationRepositoryDouble(), new HostRepositoryDouble(), new GuestRepositoryDouble());
    final UUID hostId = UUID.fromString("2e72f86c-b8fe-4265-b4f1-304dea8762db");

    @Test
    void addReservationShouldAdd() throws DataAccessException {
        Reservation reservation = new Reservation();
        Host host = new Host();
        Guest guest = new Guest();
        host.setStandardRate(new BigDecimal("150"));
        host.setWeekendRate(new BigDecimal("150"));
        reservation.getHost().setHostId(hostId);
        reservation.setReservationId(13);
        reservation.setStartDate(LocalDate.of(2021, 5, 6));
        reservation.setEndDate(LocalDate.of(2021, 5, 7));
        reservation.getGuest().setGuestId("400");

        Result result = service.addReservation(reservation, hostId);
        assertTrue(result.isSuccess());
    }

    @Test
    void addReservationShouldNotAddIfStartDateMissing() throws DataAccessException {
        Reservation reservation = new Reservation();
        Host host = new Host();
        Guest guest = new Guest();
        host.setStandardRate(new BigDecimal("150"));
        host.setWeekendRate(new BigDecimal("150"));
        reservation.getHost().setHostId(hostId);
        reservation.setReservationId(13);
        reservation.setStartDate(null);
        reservation.setEndDate(LocalDate.of(2021, 5, 7));
        reservation.getGuest().setGuestId("400");

        Result result = service.addReservation(reservation, hostId);
        assertFalse(result.isSuccess());
    }

    @Test
    void addReservationShouldNotAddIfEndDateMissing() throws DataAccessException {
        Reservation reservation = new Reservation();
        Host host = new Host();
        host.setStandardRate(new BigDecimal("150"));
        host.setWeekendRate(new BigDecimal("150"));
        reservation.getHost().setHostId(hostId);
        reservation.setReservationId(13);
        reservation.setStartDate(LocalDate.of(2021, 5, 6));
        reservation.getGuest().setGuestId("400");

        Result result = service.addReservation(reservation, hostId);
        assertFalse(result.isSuccess());
    }

    @Test
    void addReservationShouldNotAddIfGuestMissing() throws DataAccessException {
        Reservation reservation = new Reservation();
        Host host = new Host();
        host.setStandardRate(new BigDecimal("150"));
        host.setWeekendRate(new BigDecimal("150"));
        reservation.getHost().setHostId(hostId);
        reservation.setReservationId(13);
        reservation.setStartDate(LocalDate.of(2021, 5, 6));
        reservation.setEndDate(LocalDate.of(2021, 5, 7));
        reservation.getGuest().setGuestId("");

        Result result = service.addReservation(reservation, hostId);
        assertFalse(result.isSuccess());
    }

    @Test
    void editReservationShouldUpdate() throws DataAccessException {
        Reservation reservation = new Reservation();
        Host host = new Host();
        reservation.getHost().setHostId(hostId);
        reservation.setReservationId(13);
        reservation.setStartDate(LocalDate.of(2021, 5, 6));
        reservation.setEndDate(LocalDate.of(2021, 5, 9));
        reservation.getGuest().setGuestId("400");
        Result result = service.editReservation(reservation);
        assertTrue(result.isSuccess());
    }

    @Test
    void editReservationShouldNotUpdateIfEndDateIsBeforeStartDate() throws DataAccessException {
        Reservation reservation = new Reservation();
        Host host = new Host();
        reservation.getHost().setHostId(hostId);
        reservation.setReservationId(13);
        reservation.setStartDate(LocalDate.of(2021, 5, 6));
        reservation.setEndDate(LocalDate.of(2021, 5, 4));
        reservation.getGuest().setGuestId("400");
        Result result = service.editReservation(reservation);
        assertFalse(result.isSuccess());
    }
}