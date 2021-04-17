package learn.mastery.data;

import learn.mastery.model.Host;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HostRepositoryDouble implements HostRepository{

    private final List<Host> hosts = new ArrayList<>();
    final static Host host = makeHost();

    public HostRepositoryDouble() {
        hosts.add(host);
    }

    public List<Host> findAll() throws DataAccessException {
        return hosts;
    }

    public Host findById(UUID hostId) throws DataAccessException {
        return hosts.stream()
                .filter(host -> host.getHostId().equals(hostId))
                .findFirst()
                .orElse(null);
    }

    private static Host makeHost() {
        Host host = new Host();
        host.setHostId(UUID.fromString("2e72f86c-b8fe-4265-b4f1-304dea8762db"));
        host.setStandardRate(new BigDecimal("200"));
        host.setWeekendRate(new BigDecimal("250"));
        return host;
    }
}
