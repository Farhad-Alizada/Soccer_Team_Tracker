import java.util.Scanner;
import Objects.*;

public class Menu {
    private final Scanner scanner = new Scanner(System.in); // Scanner object to take user input
    private final Data team = new Data(); // Team object to manage player data
    /**
    initialize players by taking user input
     prompts the user to input the players on the team
     ensures the inputs are valid
     **/
    private void initializePlayers() {
        System.out.println("Enter player names: (Press Enter to finish)");
        while (true) {
            String playerName = scanner.nextLine().trim(); //reads the player names from the input
            if (playerName.isEmpty()) { //breaks the loop if the user presses enter
                break;
            } else if (!playerName.matches("^[a-zA-Z\\s]+")) { // Check if name contains only letters
                System.out.println("Name cannot be numerical or contain special characters. Try again.");
            } else if (team.playerAlreadyExists(playerName)) { // Check if player already exists
                System.out.println("Player '" + playerName + "' already exists. Try a different name.");
            } else {
                team.addPlayer(playerName); // Add player to the team
            }
        }
    }

    /**
     * updates the player stats
     * prompts the user to choose an option
     * checks if the input is valid
     **/
    private void updatePlayerStats() {
        System.out.println("Choose statistic to update:");
        System.out.println("1. Games Played\n2. Goals\n3. Assists\n4. Shots\n5. Shots on Target\n6. Yellow Cards\n7. Red Cards");
        System.out.print("Enter your choice (1-7): "); // Make sure this matches the number of options
        int choice = scanner.nextInt(); //reads the users choice
        scanner.nextLine(); // Consume newline
        if (choice < 1 || choice > 7) { // Check if choice is within valid range
            System.out.println("Invalid option. Please select a number between 1 and 7.");
            return;
        }
        System.out.print("Enter player's name: "); //prompts the user to enter the players name
        String playerName = scanner.nextLine().trim();
        System.out.print("Enter the value: "); // Prompt user to enter value for selected statistic
        int value = scanner.nextInt(); //reads the value from the input
        scanner.nextLine(); // Consume newline
        // Convert the integer choice to StatType
        StatType statType = convertIntToStatType(choice);
        if (statType == null) {
            System.out.println("Invalid statistic selected.");
            return;
        }
        team.updateSpecificStat(playerName, statType, value); //updates the players statistics
    }

    /**
     * Converts an integer representing a choice to the corresponding StatType enum value.
     * @param choice The integer representing the choice.
     * @return The StatType enum value corresponding to the choice. Returns null if the choice is invalid.
     */
    private StatType convertIntToStatType(int choice) {
        switch (choice) {
            case 1:
                return StatType.GAMES_PLAYED;
            case 2:
                return StatType.GOALS;
            case 3:
                return StatType.ASSISTS;
            case 4:
                return StatType.SHOTS;
            case 5:
                return StatType.SHOTS_ON_TARGET;
            case 6:
                return StatType.YELLOW_CARDS;
            case 7:
                return StatType.RED_CARDS;
            default:
                return null; // Invalid choice
        }
    }

    /**
     * displays the main menu
     * prompts the user to keep choosing an option until they press enter
     */
    // displays the main menu and handles the users choices
    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println(this); // Using toString method to display menu options
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); //reads the users choice
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> initializePlayers(); // Initialize Players
                case 2 -> updatePlayerStats(); // Update Player Stats
                case 3 -> team.displayAllPlayersScored(); // Display all Goalscorers
                case 4 -> team.displayAllPlayersAssist(); // Display all Assisters
                case 5 -> team.displayShootingStatistics(); // Display Shooting Statistics
                case 6 -> team.displayMostEfficientScorer(); // Display Most Efficient Scorer
                case 7 -> team.displayMostEfficientAssister(); // Display Most Efficient Assister
                case 8 -> team.displayMostAccurateShooter(); // Display Most Accurate Shooter
                case 9 -> team.displayMostEfficientShooter(); // Display Most Efficient Shooter
                case 10 -> team.displayTopPerformersUsingComparators(); // Display Most Efficient Players
                case 11 -> team.displayWorstPerformersUsingComparators(); // Display Least Efficient Players
                case 12 -> team.displayPlayersWithMostCards(); // Display Players with Most Cards
                case 13 -> team.Load(); // Load
                case 14 -> team.Save(); // Save
                case 15 -> {
                    System.out.println("Exiting program..."); //exits
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again."); //returns when there is an invalid choice
            }
        }
    }

    /**
     * Retrieves the Data object representing the team.
     *
     * @return The Data object representing the team.
     */
    public Data getTeam() {
        return team;
    }

    /**
     * Returns a string of the menu options.
     * @return A string containing the available menu options.
     */
    @Override
    public String toString() {
        return
                "Available Options: \n" +
                "1. Initialize Players\n" +
                "2. Update Player Stats\n" +
                "3. Display all Goalscorers\n" +
                "4. Display all Assisters\n" +
                "5. Display Shooting Statistics\n" +
                "6. Display Most Efficient Scorer\n" +
                "7. Display Most Efficient Assister\n" +
                "8. Display Most Accurate Shooter\n" +
                "9. Display Most Efficient Shooter\n" +
                "10. Display Most Efficient Players\n" +
                "11. Display Least Efficient Players\n" +
                "12. Display Players with Most Cards\n" +
                "13. Load\n" +
                "14. Save\n" +
                "15. Exit\n" ;
    }
}
