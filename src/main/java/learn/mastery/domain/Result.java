package learn.mastery.domain;

import learn.mastery.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<String> messages = new ArrayList<>();

    public boolean isSuccess(){
        return messages.size() == 0;
    }

    public void addErrorMessage(String message){
        messages.add(message);
    }

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }
}
