package learn.mastery.data;

import learn.mastery.model.Guest;
import learn.mastery.model.Host;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class HostFileRepository implements HostRepository{

    private final String filePath;
    private final String delimiter = ",";

    public HostFileRepository(@Value("${hostFilePath}")String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Host> findAll() throws DataAccessException {
        List<Host> hosts = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            for (String line =reader.readLine(); line != null; line = reader.readLine()){
                String[] fields = line.split(delimiter);

                if (fields.length == 10){
                    hosts.add(deserialize(fields));
                }
            }
        } catch (IOException ex){
            throw new DataAccessException("Could not open file at: " + filePath, ex);
        }
        return hosts;
    }

    public Host findById(UUID hostId) throws DataAccessException {
        return findAll().stream()
                .filter(host -> host.getHostId().equals(hostId))
                .findFirst()
                .orElse(null);
    }

    public Host findByEmail(String hostEmail) throws DataAccessException {
        return findAll().stream()
                .filter(host ->host.getHostEmail().equals(hostEmail))
                .findFirst()
                .orElse(null);
    }

    private Host deserialize(String[] fields) {
        Host host = new Host();
        host.setHostId(UUID.fromString(fields[0]));
        host.setLastName(fields[1]);
        host.setHostEmail(fields[2]);
        host.setCity(fields[5]);
        host.setState(fields[6]);
        host.setStandardRate(new BigDecimal(fields[8]));
        host.setWeekendRate(new BigDecimal(fields[9]));
        return host;
    }
}
