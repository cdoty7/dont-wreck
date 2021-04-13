package learn.mastery.model;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;

public class Reservation {
    private int reservationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int guestId;
    private BigDecimal total;

    public Reservation(int reservationId, LocalDate startDate, LocalDate endDate, int guestId, BigDecimal total) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestId = guestId;
        this.total = total;
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

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal setTotal(UUID hostId, String lastName, String email, String city, String state, BigDecimal standardRate, BigDecimal weekendRate) {
        Host host = new Host(hostId, lastName, email, city, state, standardRate, weekendRate);
        LocalDate start = getStartDate();
        LocalDate end = getEndDate();
        BigDecimal totalWeekday = null;
        BigDecimal totalWeekend = null;

        for (; start.compareTo(end) < 0; start = start.plusDays(1)) {
            if (start.getDayOfWeek() != DayOfWeek.FRIDAY && start.getDayOfWeek() != DayOfWeek.SATURDAY) {
                totalWeekday = standardRate.add(standardRate);
            }
            if (start.getDayOfWeek() == DayOfWeek.FRIDAY || start.getDayOfWeek() == DayOfWeek.SATURDAY) {
                totalWeekend = weekendRate.add(weekendRate);
            }
        }

        return totalWeekday.add(totalWeekend);
    }
}
