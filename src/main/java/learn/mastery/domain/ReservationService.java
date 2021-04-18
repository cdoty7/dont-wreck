package learn.mastery.domain;

import learn.mastery.data.DataAccessException;
import learn.mastery.data.HostRepository;
import learn.mastery.data.ReservationRepository;
import learn.mastery.model.Host;
import learn.mastery.model.Reservation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;

    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
    }


    public List<Reservation> viewReservationsByHost(UUID hostId) throws DataAccessException {
        return reservationRepository.viewReservationsByHost(hostId);
    }

    public Result addReservation(Reservation reservation, UUID hostId) throws DataAccessException {
        Result result = validate(reservation);

        if(result.isSuccess()) {
            calculateTotal(reservation, hostId);
            reservation = reservationRepository.addReservation(reservation);
            result.setReservation(reservation);
        }

        return result;
    }

    public Result editReservation(Reservation reservation) throws DataAccessException {
        Result result = validate(reservation);

        if (result.isSuccess()){
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
        if(reservation.getHostId() == null) {
            result.addErrorMessage("Host email is required.");
        }
        //No guest email entered
        if(reservation.getGuest().getGuestId() == null || reservation.getGuest().getGuestId().isEmpty()) {
            result.addErrorMessage("Guest email is required.");
        }
        //Host has no reservations
        if(reservationRepository.viewReservationsByHost(reservation.getHostId()).size() == 0){
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
        if(reservation.getEndDate().isBefore(reservation.getStartDate())) {
            result.addErrorMessage("Start date must be before end date.");
        }
        //Start of reservation overlaps with existing reservation

        return result;
    }

    public BigDecimal calculateTotal(Reservation reservation, UUID hostId) throws DataAccessException {
        Host host = hostRepository.findById(hostId);
        BigDecimal standardRate = new BigDecimal(String.valueOf(host.getStandardRate()));
        BigDecimal weekendRate = new BigDecimal(String.valueOf(host.getWeekendRate()));
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
