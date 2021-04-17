package learn.mastery.model;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;

public class Reservation {
    private UUID hostId;
    private Host host;
    private int reservationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Guest guest;
    private String guestId;
    private BigDecimal total;

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

//        Host host = new Host();
//        LocalDate start = getStartDate();
//        LocalDate end = getEndDate();
//        BigDecimal totalWeekday = null;
//        BigDecimal totalWeekend = null;
//
//        for (; start.compareTo(end) < 0; start = start.plusDays(1)) {
//            if (start.getDayOfWeek() != DayOfWeek.FRIDAY && start.getDayOfWeek() != DayOfWeek.SATURDAY) {
//                totalWeekday = totalWeekday.add(host.getStandardRate());
//            }
//            if (start.getDayOfWeek() == DayOfWeek.FRIDAY || start.getDayOfWeek() == DayOfWeek.SATURDAY) {
//                totalWeekend = totalWeekend.add(host.getWeekendRate());
//            }
//        }
//
//        return totalWeekday.add(totalWeekend);
//    }
    }

    public UUID getHostId() {
        return hostId;
    }

    public void setHostId(UUID hostId) {
        this.hostId = hostId;
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

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }
}
