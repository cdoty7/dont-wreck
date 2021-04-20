package learn.mastery.model;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;

public class Reservation {
    private Host host;
    private int reservationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Guest guest;
    private BigDecimal total;

    public Reservation() {
    }

    public Reservation(Host host, LocalDate startDate, LocalDate endDate, Guest guest) {
        this.host = host;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guest = guest;
    }

    public Reservation(Host host, int reservationId, LocalDate startDate, LocalDate endDate, Guest guest) {
        this.host = host;
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guest = guest;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
