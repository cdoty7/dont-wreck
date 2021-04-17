package learn.mastery.data;

import learn.mastery.model.Guest;
import learn.mastery.model.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostFileRepositoryTest {

    static final String SEED_FILE_PATH = "src/data/hosts-seed.csv";
    static final String TEST_FILE_PATH = "src/data/hosts-test.csv";

    HostFileRepository repository = new HostFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Host> all = repository.findAll();
        assertEquals(1000, all.size());
    }
}