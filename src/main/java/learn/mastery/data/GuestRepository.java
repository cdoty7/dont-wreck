package learn.mastery.data;

import learn.mastery.model.Guest;

import java.util.List;

public interface GuestRepository {

    List<Guest> findAll() throws DataAccessException;

    Guest findByEmail(String guestEmail) throws DataAccessException;

    Guest findById(String guestId) throws DataAccessException;
}
