package learn.mastery.data;

import java.io.FileNotFoundException;

public class DataAccessException extends Exception {

    public DataAccessException(String message){
        super(message);
    }

    public DataAccessException(String message, Throwable cause){
        super(message, cause);
    }

    public DataAccessException(Exception ex) {
    }
}
