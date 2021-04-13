package learn.mastery.ui;

import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class View {

    Scanner console = new Scanner(System.in);

    public int mainMenu(){
        return 0;
    }

    public void displayReservations() {

    }

    public String promptHostEmail() {
        return null;
    }

    public String promptGuestEmail(){
        return null;
    }

    public void displayHeader(){

    }

    public void displayMessage(){

    }
}
