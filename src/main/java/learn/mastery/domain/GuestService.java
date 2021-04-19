package learn.mastery.domain;

import learn.mastery.data.DataAccessException;
import learn.mastery.data.GuestRepository;
import learn.mastery.data.HostRepository;
import learn.mastery.model.Guest;
import learn.mastery.model.Host;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    private final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public List<Guest> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public Guest findByEmail(String guestEmail) throws DataAccessException {
        return repository.findByEmail(guestEmail);
    }

    public Guest findById(String guestId) throws DataAccessException {
        return repository.findById(guestId);
    }
}
