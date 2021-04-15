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
    Host host;

    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
    }

    public List<Reservation> viewReservationsByHost(UUID hostId) throws DataAccessException {
        return reservationRepository.viewReservationsByHost(hostId);
    }

    public Result addReservation(Reservation reservation) {
        return null;
    }

    public Result editReservation(Reservation reservation) {
        return null;
    }

    public boolean delete(Reservation reservation) {
        return false;
    }

    private Result validate(Reservation reservation) throws DataAccessException {
        Result result = new Result();
        //No host email entered
        if(reservation.getHostId() == null) {
            result.addErrorMessage("Host email is required.");
        }
        //No guest email entered
        if(reservation.getGuestId() == null || reservation.getGuestId().isEmpty()) {
            result.addErrorMessage("Guest email is required.");
        }
        //Host has no reservations
        if(reservationRepository.viewReservationsByHost(host.getHostId()).size() == 0){
            result.addErrorMessage("Host has no reservations.");
        }
        //Start date entered is before end date
        if(reservation.getEndDate().isBefore(reservation.getEndDate())) {
            result.addErrorMessage("Start date must be before end date.");
        }
        //Start of reservation overlaps with existing reservation

        return result;
    }

    private BigDecimal calculateTotal(Reservation reservation) {
        BigDecimal standardRate = new BigDecimal(String.valueOf(host.getStandardRate()));
        BigDecimal weekendRate = new BigDecimal(String.valueOf(host.getWeekendRate()));
        LocalDate start = reservation.getStartDate();
        LocalDate end = reservation.getEndDate();
        BigDecimal totalWeekday = null;
        BigDecimal totalWeekend = null;

        for (; start.compareTo(end) < 0; start = start.plusDays(1)) {
            if (start.getDayOfWeek() != DayOfWeek.FRIDAY && start.getDayOfWeek() != DayOfWeek.SATURDAY) {
                totalWeekday = totalWeekday.add(host.getStandardRate());
            }
            if (start.getDayOfWeek() == DayOfWeek.FRIDAY || start.getDayOfWeek() == DayOfWeek.SATURDAY) {
                totalWeekend = totalWeekend.add(host.getWeekendRate());
            }
        }
        return totalWeekday.add(totalWeekend);
    }
}
