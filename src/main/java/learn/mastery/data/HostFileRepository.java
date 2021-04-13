package learn.mastery.data;

import learn.mastery.model.Host;

import java.util.List;

public class HostFileRepository implements HostRepository{

    private final String filePath;

    public HostFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Host> findAll() {
        return null;
    }
}
