import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {
    public static String name;
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        JOptionPane.showConfirmDialog(null,"Do You Accept The Responsibility Of Owning This Virtual Pet?");
        new GUI();
    }
}