package learn.mastery.data;

import learn.mastery.model.Guest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestFileRepository implements GuestRepository{

    private final String filePath;
    private final String delimiter = ",";

    public GuestFileRepository(@Value("${guestFilePath}") String filePath) {
        this.filePath = filePath;
    }

    public List<Guest> findAll() throws DataAccessException{
        List<Guest> guests = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            for (String line =reader.readLine(); line != null; line = reader.readLine()){
                String[] fields = line.split(delimiter);

                if (fields.length == 6){
                    guests.add(deserialize(fields));
                }
            }
        } catch (IOException ex){
            throw new DataAccessException("Could not open file at: " + filePath, ex);
        }
        return guests;
    }

    private Guest deserialize(String[] fields) {
        Guest guest = new Guest();
        guest.setGuestId(Integer.parseInt(fields[0]));
        guest.setFirstName(fields[1]);
        guest.setLastName(fields[2]);
        guest.setGuestEmail(fields[3]);
        return guest;
    }
}