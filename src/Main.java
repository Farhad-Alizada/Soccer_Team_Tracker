import java.io.File;

/*
CPSC 233 Project Demo 1 & 2
Ronakh Shariff, Hassan Alvi, Farhad Alizada
March 21, 2024
*/

public class Main {
    /**
     * The main method, which serves as the entry point for this program.
     * @param args The command-line arguments. Provide at most one argument specifying the filename to load from..
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        if (args.length > 1) {
            System.err.println("Expected at most one command line argument for the filename to load from.");
            System.exit(1);
        }
        if (args.length == 1) {
            String filename = args[0];
            File file = new File(filename);
            if (!file.exists() || !file.canRead()) {
                System.err.printf("Cannot load from the file %s%n", filename);
                System.exit(1);
            } else {
                // Load the data
                menu.getTeam().Load(file);
            }
        }
        // Display the menu after potentially loading data
        menu.displayMenu();
    }
}

