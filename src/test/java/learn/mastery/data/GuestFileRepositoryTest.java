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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestFileRepositoryTest {

    static final String SEED_FILE_PATH = "./data/guests-seed.csv";
    static final String TEST_FILE_PATH = "./data/guests-test.csv";

    GuestFileRepository repository = new GuestFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Guest> all = repository.findAll();
        assertEquals(1000, all.size());
    }

    @Test
    void findByEmailShouldFindGuest() throws DataAccessException {
        Guest guest = repository.findByEmail("slomas0@mediafire.com");
        List<Guest> guests = new ArrayList<>();
        guests.add(guest);
        assertEquals(1, guests.size());
    }
}