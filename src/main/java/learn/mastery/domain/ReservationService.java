package learn.mastery.domain;

import learn.mastery.data.DataAccessException;
import learn.mastery.data.GuestRepository;
import learn.mastery.data.HostRepository;
import learn.mastery.data.ReservationRepository;
import learn.mastery.model.Guest;
import learn.mastery.model.Host;
import learn.mastery.model.Reservation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;

    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository, GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
    }


    public List<Reservation> viewReservationsByHost(UUID hostId) throws DataAccessException {
        List <Reservation> reservations = reservationRepository.viewReservationsByHost(hostId);
        for (Reservation reservation : reservations) {
            String guestId = reservation.getGuest().getGuestId();
            reservation.setGuest(guestRepository.findById(guestId));
            reservation.setHost(hostRepository.findById(hostId));
        }

        return reservations;
    }

    public List<Reservation> viewReservationsForGuest(Host host, Guest guest) throws DataAccessException {
        List <Reservation> reservations = reservationRepository.viewReservationsByHost(host.getHostId());
        List<Reservation> reservationsForGuest = new ArrayList<>();
        for (Reservation reservation : reservations) {
            String guestId = reservation.getGuest().getGuestId();
            reservation.setGuest(guestRepository.findById(guestId));
            if (reservation.getGuest().getGuestId().equals(guest.getGuestId())) {
                reservationsForGuest.add(reservation);
            }
        }
        return reservationsForGuest;
    }

    public Result addReservation(Reservation reservation) throws DataAccessException {
        Result result = validate(reservation);

        if(result.isSuccess()) {
            reservation.setTotal(calculateTotal(reservation));
            reservation = reservationRepository.addReservation(reservation);
            result.setReservation(reservation);
        }

        return result;
    }

    public Result editReservation(Reservation reservation) throws DataAccessException {
        Result result = validate(reservation);

        if (result.isSuccess()){
            reservation.setTotal(calculateTotal(reservation));
            reservationRepository.editReservation(reservation);
            result.setReservation(reservation);

        }
        return result;
    }

    public boolean cancelReservation(Reservation reservation) throws DataAccessException {
        reservationRepository.cancelReservation(reservation);
        return true;
    }

    private Result validate(Reservation reservation) throws DataAccessException {
        Result result = new Result();
        //No host email entered
        if(reservation.getHost() == null || reservation.getHost().getHostId() == null) {
            result.addErrorMessage("Host is required.");
        }
        //No guest email entered
        if(reservation.getGuest() == null || reservation.getGuest().getGuestId() == null || reservation.getGuest().getGuestId().isEmpty()) {
            result.addErrorMessage("Guest is required.");
        }
        //Host has no reservations
        if(reservationRepository.viewReservationsByHost(reservation.getHost().getHostId()).size() == 0){
            result.addErrorMessage("Host has no reservations.");
        }
        //No start date entered
        if(reservation.getStartDate() == null) {
            result.addErrorMessage("Start date required.");
        }
        //No end date entered
        if(reservation.getEndDate() == null) {
            result.addErrorMessage("End date required.");
        }
        //Start date entered is before end date
        if((reservation.getStartDate() != null) && (reservation.getEndDate() != null) && (reservation.getEndDate().isBefore(reservation.getStartDate()))) {
            result.addErrorMessage("Start date must be before end date.");
        }
        //Start of reservation overlaps with existing reservation

        return result;
    }

    public BigDecimal calculateTotal(Reservation reservation) throws DataAccessException {
        BigDecimal standardRate = new BigDecimal(String.valueOf(reservation.getHost().getStandardRate()));
        BigDecimal weekendRate = new BigDecimal(String.valueOf(reservation.getHost().getWeekendRate()));
        LocalDate start = reservation.getStartDate();
        LocalDate end = reservation.getEndDate();
        BigDecimal totalWeekday = new BigDecimal("0");
        BigDecimal totalWeekend = new BigDecimal("0");

        for (; start.compareTo(end) < 0; start = start.plusDays(1)) {
            if (start.getDayOfWeek() != DayOfWeek.FRIDAY && start.getDayOfWeek() != DayOfWeek.SATURDAY) {
                totalWeekday = totalWeekday.add(standardRate);
            }
            if (start.getDayOfWeek() == DayOfWeek.FRIDAY || start.getDayOfWeek() == DayOfWeek.SATURDAY) {
                totalWeekend = totalWeekend.add(weekendRate);
            }
        }
        return totalWeekday.add(totalWeekend);
    }
}
