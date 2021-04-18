package learn.mastery.data;

import learn.mastery.model.Guest;
import learn.mastery.model.Host;

import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryDouble implements GuestRepository {
    private final List<Guest> guests = new ArrayList<>();
    final static Guest guest = new Guest();


    public GuestRepositoryDouble() {
        guests.add(guest);
    }

    public List<Guest> findAll() throws DataAccessException {
        return guests;
    }

    @Override
    public Guest findByEmail(String guestEmail) throws DataAccessException {
        return guest;
    }
}
