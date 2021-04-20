package learn.mastery.data;

import learn.mastery.model.Guest;
import learn.mastery.model.Host;
import learn.mastery.model.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.UUID;

@Repository
public class ReservationFileRepository implements ReservationRepository{

    private final String header = "id,start_date,end_date,guest_id,total";
    private final String directory;

    public ReservationFileRepository(@Value("${reservationFilePath}") String directory) {
        this.directory = directory;
    }

    @Override
    public List<Reservation> viewReservationsByHost(UUID hostId) throws DataAccessException {

        List<Reservation> reservations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(hostId)))) {
            reader.readLine();

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    reservations.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            throw new DataAccessException("Could not open file at: " + getFilePath(hostId), ex);
        }
        return reservations;
    }

    @Override
    public Reservation addReservation(Reservation reservation) throws DataAccessException {
        List<Reservation> all = viewReservationsByHost(reservation.getHostId());

        int nextId = all.stream()
                .mapToInt(Reservation::getReservationId)
                .max()
                .orElse(0) + 1;

        reservation.setReservationId(nextId);

        all.add(reservation);
        writeAll(all, reservation.getHostId());
        return reservation;
    }

    @Override
    public boolean editReservation(Reservation reservation) throws DataAccessException {
        List <Reservation> all = viewReservationsByHost(reservation.getHostId());

        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).getReservationId() == reservation.getReservationId()) {
                all.set(i, reservation);
                writeAll(all, reservation.getHostId());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cancelReservation(Reservation reservation) throws DataAccessException {
        List <Reservation> all = viewReservationsByHost(reservation.getHostId());

        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).getReservationId() == reservation.getReservationId()) {
                all.remove(i);
                writeAll(all, reservation.getHostId());
                return true;
            }
        }
        return false;
    }

    private String getFilePath(UUID hostId) {
        return Paths.get(directory, hostId + ".csv").toString();
    }

    private String serialize(Reservation reservation){
        return String.format("%s,%s,%s,%s,%s",
                reservation.getReservationId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getGuest().getGuestId(),
                reservation.getTotal());
    }

    private Reservation deserialize (String[] fields) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(Integer.parseInt(fields[0]));
        reservation.setStartDate(LocalDate.parse(fields[1]));
        reservation.setEndDate(LocalDate.parse(fields[2]));

        Guest guest = new Guest();
        guest.setGuestId(fields[3]);
        reservation.setGuest(guest);

        reservation.setTotal(new BigDecimal(fields[4]));

        return reservation;
    }

    private void writeAll(List<Reservation> reservations, UUID hostId) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(getFilePath(hostId))) {

            writer.println(header);

            if (reservations == null) {
                return;
            }

            for (Reservation reservation : reservations) {
                writer.println(serialize(reservation));
            }

        } catch (FileNotFoundException ex) {

        }
    }
    }


