package learn.mastery.data;

import learn.mastery.model.Guest;
import learn.mastery.model.Host;
import learn.mastery.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFileRepositoryTest {

    static final String SEED_FILE_PATH = "./data/seed-2e72f86c-b8fe-4265-b4f1-304dea8762db.csv";
    static final String TEST_FILE_PATH = "./data/reservation_test_data/2e72f86c-b8fe-4265-b4f1-304dea8762db.csv";
    static final String TEST_DIR_PATH = "./data/reservation_test_data";


    final UUID hostId = UUID.fromString("2e72f86c-b8fe-4265-b4f1-304dea8762db");
    ReservationFileRepository repository = new ReservationFileRepository(TEST_DIR_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void viewReservationsByHostShouldFindReservations() throws DataAccessException {

        List<Reservation> reservations = repository.viewReservationsByHost(hostId);
        assertEquals(12, reservations.size());
    }

    @Test
    void addReservationShouldAdd() throws DataAccessException {
        Reservation reservation = new Reservation();
        Guest guest = new Guest();
        Host host = new Host();
        host.setHostId(hostId);
        reservation.setHost(host);
        List<Reservation> all = repository.viewReservationsByHost(hostId);
        reservation.setReservationId(13);
        reservation.setStartDate(LocalDate.of(2021, 5, 6));
        reservation.setEndDate(LocalDate.of(2021, 5, 7));
        guest.setGuestId("400");
        reservation.setGuest(guest);
        reservation.setTotal(new BigDecimal("500"));

        all.add(reservation);
        reservation = repository.addReservation(reservation);


        assertEquals(13, all.size());
    }

    @Test
    void editReservationShouldUpdate() throws DataAccessException {
        Reservation reservation = new Reservation();
        Host host = new Host();
        host.setHostId(hostId);
        reservation.setHost(host);
        reservation.setReservationId(1);
        reservation.setStartDate(LocalDate.of(2021, 5, 6));
        reservation.setEndDate(LocalDate.of(2021, 5, 7));

        Guest guest = new Guest();
        guest.setGuestId("400");
        reservation.setGuest(guest);
        reservation.setTotal(new BigDecimal("500"));

        boolean actual = repository.editReservation(reservation);
        assertTrue(actual);
    }

    @Test
    void cancelReservationShouldDelete() throws DataAccessException {
        Reservation reservation = new Reservation();
        Host host = new Host();
        host.setHostId(hostId);
        reservation.setHost(host);
        reservation.getHost().setHostId(hostId);
        reservation.setReservationId(12);
        boolean actual = repository.cancelReservation(reservation);

        assertTrue(actual);
    }
}