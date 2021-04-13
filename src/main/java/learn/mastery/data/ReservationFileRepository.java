package learn.mastery.data;

import learn.mastery.model.Reservation;

import java.util.List;
import java.util.UUID;

public class ReservationFileRepository implements ReservationRepository{

    private final String header = "id,start_date,end_date,guest_id,total";
    private final String directory;

    public ReservationFileRepository(String directory) {
        this.directory = directory;
    }

    @Override
    public List<Reservation> viewReservationsByHost(String hostEmail) {
        return null;
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return null;
    }

    @Override
    public boolean editReservation(Reservation reservation) {
        return false;
    }

    @Override
    public boolean cancelReservation(Reservation reservation) {
        return false;
    }

    private String getFilePath(UUID hostID) {
        return null;
    }

    private String serialize(Reservation reservation){
        return null;
    }

    private void writeAll(){

    }
}

