package learn.mastery.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Host {
    private UUID hostId;
    private String lastName;
    private String hostEmail;
    private String city;
    private String state;
    private BigDecimal standardRate;
    private BigDecimal weekendRate;

    public Host(UUID hostId, String lastName, String hostEmail, String city, String state, BigDecimal standardRate, BigDecimal weekendRate) {
        this.hostId = hostId;
        this.lastName = lastName;
        this.hostEmail = hostEmail;
        this.city = city;
        this.state = state;
        this.standardRate = standardRate;
        this.weekendRate = weekendRate;
    }

    public Host() {
    }

    public String getLastName() {
        return lastName;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public UUID getHostId() {
        return hostId;
    }

    public void setHostId(UUID hostId) {
        this.hostId = hostId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(BigDecimal standardRate) {
        this.standardRate = standardRate;
    }

    public BigDecimal getWeekendRate() {
        return weekendRate;
    }

    public void setWeekendRate(BigDecimal weekendRate) {
        this.weekendRate = weekendRate;
    }
}
