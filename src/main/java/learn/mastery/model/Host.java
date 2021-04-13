package learn.mastery.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Host {
    UUID hostId;
    private String lastName;
    private String email;
    private String city;
    private String state;
    BigDecimal standardRate;
    BigDecimal weekendRate;

    public Host(UUID hostId, String lastName, String email, String city, String state, BigDecimal standardRate, BigDecimal weekendRate) {
        this.hostId = hostId;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.state = state;
        this.standardRate = standardRate;
        this.weekendRate = weekendRate;
    }


    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
