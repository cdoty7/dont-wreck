package learn.mastery.domain;

import learn.mastery.data.HostRepository;
import learn.mastery.data.ReservationRepository;
import learn.mastery.model.Reservation;

import java.util.List;

public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;

    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
    }

    public List<Reservation> viewReservationsByHost(String hostEmail) {
        return null;
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
}
