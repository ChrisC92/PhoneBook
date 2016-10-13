import PhoneSearch.UI.UserInterface;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        
        new UserInterface(reader).start();
    }
}
