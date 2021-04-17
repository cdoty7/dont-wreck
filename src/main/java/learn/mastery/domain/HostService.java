package learn.mastery.domain;

import learn.mastery.data.DataAccessException;
import learn.mastery.data.HostRepository;
import learn.mastery.model.Host;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HostService {
    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }

    public List<Host> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public Host findByEmail(String hostEmail) throws DataAccessException {
        return repository.findByEmail(hostEmail);
    }

}
