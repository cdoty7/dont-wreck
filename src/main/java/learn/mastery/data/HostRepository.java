package learn.mastery.data;

import learn.mastery.model.Host;

import java.util.List;

public interface HostRepository {

    List<Host> findAll();
}
