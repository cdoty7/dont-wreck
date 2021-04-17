package learn.mastery.data;

import learn.mastery.model.Host;

import java.util.List;
import java.util.UUID;

public interface HostRepository {

    List<Host> findAll() throws DataAccessException;

    Host findById(UUID hostId) throws DataAccessException;

    Host findByEmail(String hostEmail) throws DataAccessException;
}
