package learn.mastery.domain;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<String> messages = new ArrayList<>();

    public boolean isSuccess(){
        return false;
    }

    public void addErrorMessage(){

    }

    public List<String> getErrorMessages() {
        return null;
    }
}
