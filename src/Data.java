import java.util.*;
import java.io.File;
import java.util.Comparator;
import Objects.StatType;
import Objects.Players;
import Util.FileLoader;
import Util.FileSaver;
import static Comparators.Comparators.*;

public class Data {
    private final Scanner scanner = new Scanner(System.in);
    private Map<String, Players> playersMap = new HashMap<>();
    /**
     * Checks if a player already exists in the playersMap.
     * @param playerName the name of the player to check
     * @return true if the player exists and false if the player does not exist
     */
    public boolean playerAlreadyExists(String playerName) {
        return playersMap.containsKey(playerName);
    }
    public static final String PLAYER_FORMAT = "%-20s %-10s";
    public static final String SHOOTING_FORMAT = "%-20s %-10s %-18s %-10s %-20s %-20s";
    public static final String EFFICIENCY_FORMAT = "%-20s %-10s %-10s %-10s";
    public static final String SCORER_HEADER = String.format(PLAYER_FORMAT, "NAME", "GOALS");
    public static final String ASSISTER_HEADER = String.format(PLAYER_FORMAT, "NAME", "ASSISTS");
    public static final String SHOOTING_HEADER = String.format(SHOOTING_FORMAT, "NAME", "SHOTS", "SHOTS ON TARGET", "GOALS", "SHOT ACCURACY", "SHOT CONVERSION");
    public static final String EFFICIENTSCORERS_HEADER = String.format(EFFICIENCY_FORMAT, "NAME", "GOALS", "GAMES", "RATIO");
    public static final String EFFICIENTASSISTER_HEADER = String.format(EFFICIENCY_FORMAT, "NAME", "ASSISTS", "GAMES", "RATIO");

    /**
     * Adds a new player to the playersMap if the player does not already exist and the name is valid.
     * @param playerName the name of the player to add
     */
    public void addPlayer(String playerName) {
        // Checks for invalid player names
        if (playerName.matches("[^a-zA-Z]+")) {
            System.out.println("Name cannot be numerical. Player not added.");
            return;
        }
        // Checks for existing player
        if (playersMap.containsKey(playerName)) {
            System.out.println("Player '" + playerName + "' already exists in the team.");
            return;
        }
        // Adds new player to the map
        playersMap.put(playerName, new Players(playerName));
        System.out.println("Player '" + playerName + "' added to the team.");
    }

    /** **
     * Retrieves a player object by name.
     * @param playerName the name of the player to retrieve
     * @return the Players object associated with the given name
     */
    public Players getPlayer(String playerName) {
        return playersMap.get(playerName);
    }


    /**
     * Updates a specific statistic for a given player.
     * @param playerName the name of the player whose statistic is to be updated
     * @param statType   the type of statistic to update
     * @param value      the value to add to the statistic
     */
    public void updateSpecificStat(String playerName, StatType statType, int value) {
        // Check if player exists
        Players player = playersMap.get(playerName);
        if (player == null) {
            System.out.println("Player not found.");
            return;
        }
        // Updates the specified statistic
        switch (statType) {
            case GAMES_PLAYED:
                player.updateGamesPlayed(value);
                break;
            case GOALS:
                player.updateGoals(value);
                break;
            case ASSISTS:
                player.updateAssists(value);
                break;
            case SHOTS:
                player.updateShots(value);
                break;
            case SHOTS_ON_TARGET:
                player.updateShotsOnTarget(value);
                break;
            case YELLOW_CARDS:
                player.updateYellowCards(value);
                break;
            case RED_CARDS:
                player.updateRedCards(value);
                break;
            default:
                System.out.println("Invalid stat type.");
                break;
        }
    }

    /**
     * Displays a list of all players who have scored goals, formatted according to PLAYER_FORMAT.
     */
    public void displayAllPlayersScored() {
        boolean hasScorers = false;
        System.out.println(SCORER_HEADER); // Display the header for scorers
        for (Players player : playersMap.values()) {
            if (player.getStats().getGoals() > 0) {
                System.out.printf(PLAYER_FORMAT + "%n", player.getName(), player.getStats().getGoals()); // Format output
                hasScorers = true;
            }
        }
        if (!hasScorers) {
            System.out.println("No players have scored."); // Display message if no players have scored
        }
    }

    /**
     * Displays a list of all players who have provided assists, formatted according to PLAYER_FORMAT.
     */
    public void displayAllPlayersAssist() {
        boolean hasAssisters = false;
        System.out.println(ASSISTER_HEADER); // Use ASSISTER_HEADER for formatting
        for (Players player : playersMap.values()) {
            if (player.getStats().getAssists() > 0) {
                System.out.printf(PLAYER_FORMAT + "%n", player.getName(), player.getStats().getAssists()); // Format output
                hasAssisters = true;
            }
        }
        if (!hasAssisters) {
            System.out.println("No players have assisted."); // Display message if no players have assisted
        }
    }

    /**
     * Displays shooting statistics for all players, including total shots and shots on target.
     */
    public void displayShootingStatistics() {
        System.out.println("Shooting statistics:");
        for (Players player : playersMap.values()) {
            System.out.println(player.getName() + " - Shots: " + player.getStats().getShots() + ", Shots on Target: " + player.getStats().getShotsOnTarget());
        }
    }

    /**
     * Identifies and displays the most accurate shooter based on shooting accuracy.
     */
    public void displayMostAccurateShooter() {
        System.out.println(SHOOTING_HEADER);
        Players mostAccurateShooter = null;
        double maxShootingAccuracy = -1;
        // Iterate through players to find the most accurate shooter
        for (Players player : playersMap.values()) {
            if (player.getShootingAccuracy() > maxShootingAccuracy) {
                maxShootingAccuracy = player.getShootingAccuracy();
                mostAccurateShooter = player;
            }
        }
        if (mostAccurateShooter != null) {
            // Display the most accurate shooter's statistics
            System.out.printf(SHOOTING_FORMAT + "%n", mostAccurateShooter.getName(), mostAccurateShooter.getStats().getShots(), mostAccurateShooter.getStats().getShotsOnTarget(), mostAccurateShooter.getStats().getGoals(), String.format("%.2f%%", mostAccurateShooter.getShootingAccuracy()), String.format("%.2f%%", mostAccurateShooter.getGoalConversion()));
            System.out.println(mostAccurateShooter.getName() + " is the most accurate shooter.");
        }
    }

    /**
     * Displays the most efficient scorer.
     * The method iterates through all players to find the one with the highest scoring efficiency.
     * It then displays the name of the player along with their goal statistics and scoring efficiency.
     * If no player has played any games, it does not display any scorer.
     */
    public void displayMostEfficientScorer() {
        System.out.println(EFFICIENTSCORERS_HEADER);
        Players mostEfficientScorer = null;
        double maxScoringEfficiency = -1;
        for (Players player : playersMap.values()) {
            if (player.getGoalEfficiency() > maxScoringEfficiency) {
                maxScoringEfficiency = player.getGoalEfficiency();
                mostEfficientScorer = player;
            }
        }
        if (mostEfficientScorer != null) {
            System.out.printf(EFFICIENCY_FORMAT + "%n", mostEfficientScorer.getName(), mostEfficientScorer.getStats().getGoals(), mostEfficientScorer.getStats().getGamesPlayed(), String.format("%.2f", mostEfficientScorer.getGoalEfficiency()));
            System.out.println(mostEfficientScorer.getName() + " is the most efficient scorer.");
        }
    }

    /**
     * Identifies and displays the player with the highest assist efficiency.
     * The efficiency is calculated based on the number of assists per game.
     * It displays the player's name, total assists, total games played, and the assist efficiency ratio.
     */
    public void displayMostEfficientAssister() {
        System.out.println(EFFICIENTASSISTER_HEADER);
        Players mostEfficientAssister = null;
        double maxAssistEfficiency = -1;
        // Iterate through all players to find the one with the highest assist efficiency
        for (Players player : playersMap.values()) {
            if (player.getAssistEfficiency() > maxAssistEfficiency) {
                maxAssistEfficiency = player.getAssistEfficiency();
                mostEfficientAssister = player;
            }
        }
        // If an efficient assister is found, display their details formatted as specified by EFFICIENCY_FORMAT
        if (mostEfficientAssister != null) {
            System.out.printf(EFFICIENCY_FORMAT + "%n", mostEfficientAssister.getName(), mostEfficientAssister.getStats().getAssists(), mostEfficientAssister.getStats().getGamesPlayed(), String.format("%.2f", mostEfficientAssister.getAssistEfficiency()));
            System.out.println(mostEfficientAssister.getName() + " is the most efficient assister.");
        }
    }

    /**
     * Identifies and displays the most efficient shooter based on goal conversion rate.
     * Goal conversion rate is calculated as the ratio of goals to shots on target.
     * The method displays the player's name, total shots, shots on target, total goals, shooting accuracy, and goal conversion rate.
     */
    public void displayMostEfficientShooter() {
        System.out.println(SHOOTING_HEADER); // Display the header for shooting statistics
        Players mostEfficientShooter = null;
        double maxGoalConversion = -1;
        // Iterate through all players to find the one with the highest goal conversion rate
        // for (Players player : playersMap.values()) {
        for (Players player : playersMap.values()) {
            if (player.getGoalConversion() > maxGoalConversion) {
                maxGoalConversion = player.getGoalConversion();
                mostEfficientShooter = player;
            }
        }
        // If an efficient shooter is found, display their details formatted as specified by SHOOTING_FORMAT
        if (mostEfficientShooter != null) {
            System.out.printf(SHOOTING_FORMAT + "%n", mostEfficientShooter.getName(), mostEfficientShooter.getStats().getShots(), mostEfficientShooter.getStats().getShotsOnTarget(), mostEfficientShooter.getStats().getGoals(), String.format("%.2f%%", mostEfficientShooter.getShootingAccuracy()), String.format("%.2f%%", mostEfficientShooter.getGoalConversion()));
            System.out.println(mostEfficientShooter.getName() + " is the most efficient shooter.");
        }
    }

    /**
     * Loads player data from a specified file. The user is prompted to enter the filename,
     * and the method checks if the file exists and is readable before attempting to load data.
     */
    public void Load() {
        System.out.println("Enter a filename: ");
        String filename = scanner.nextLine().trim();
        File file = new File(filename);
        if (!file.exists() || !file.canRead()) {
            System.out.println("File does not exist or cannot be read.");
            return;
        } else {
            System.out.println("File loaded successfully.");
        }
        FileLoader.load(file, playersMap);
    }

    /**
     * Loads data from a specified file into the players map.
     * @param file The file from which to load the data.
     */
    public void Load(File file) {
        loadFile(file);
    }

    /**
     * Handles the actual loading of data from a File object into the players map.
     * If the file does not exist or cannot be read, an error message is printed.
     * @param file The file from which to load the data.
     */
    private void loadFile(File file) {
        if (!file.exists() || !file.canRead()) {
            System.out.println("File does not exist or cannot be read.");
            return;
        } else {
            System.out.println("File loaded successfully.");
        }
        FileLoader.load(file, playersMap);
    }

    /**
     * Saves the current player data to a specified file. The user is prompted to enter the filename.
     * If the file exists, the method checks if it is writable before attempting to save.
     */
    public void Save() {
        System.out.println("Enter a filename: ");
        String filename = scanner.nextLine().trim();
        File file = new File(filename);
        if (file.exists() && !file.canWrite()) {
            System.out.println("File exists and cannot be written to.");
            return;
        }
        if (FileSaver.save(file, playersMap)) {
            System.out.println("Data saved successfully.");
        } else {
            System.out.println("Failed to save data.");
        }
    }

    /**
     * Displays the top performers in various categories using Comparators. The method evaluates players
     * based on shooting accuracy, scoring efficiency, assist efficiency, and goal conversion rate to find
     * the top performer in each category.
     */
    public void displayTopPerformersUsingComparators() {
        if (playersMap.isEmpty()) {
            System.out.println("No players data available.");
            return;
        }
        // Initialize variables to track top performers
        Players mostAccurateShooter = null;
        Players mostEfficientScorer = null;
        Players mostEfficientAssister = null;
        Players mostEfficientShooter = null; // Assuming this is based on goal conversion
        try {
            for (Players player : playersMap.values()) {
                if (player == null) continue; // Skip if the player is null
                if (mostAccurateShooter == null || BY_SHOOTING_ACCURACY.compare(player, mostAccurateShooter) > 0) {
                    mostAccurateShooter = player;
                }
                if (mostEfficientScorer == null || BY_SCORING_EFFICIENCY.compare(player, mostEfficientScorer) > 0) {
                    mostEfficientScorer = player;
                }
                if (mostEfficientAssister == null || BY_ASSIST_EFFICIENCY.compare(player, mostEfficientAssister) > 0) {
                    mostEfficientAssister = player;
                }
                if (mostEfficientShooter == null || BY_GOAL_CONVERSION.compare(player, mostEfficientShooter) > 0) {
                    mostEfficientShooter = player;
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while processing player data: " + e.getMessage());
            return; // Skip null players
        }
        // Compare and find top performers in each category
        if (mostAccurateShooter != null) {
            System.out.println("Most Accurate Shooter: " + mostAccurateShooter.getName() + " - Accuracy: " + String.format("%.2f%%", mostAccurateShooter.getShootingAccuracy()));
        }
        if (mostEfficientScorer != null) {
            System.out.println("Most Efficient Scorer: " + mostEfficientScorer.getName() + " - Efficiency: " + String.format("%.2f goals per game", mostEfficientScorer.getGoalEfficiency()));
        }
        if (mostEfficientAssister != null) {
            System.out.println("Most Efficient Assister: " + mostEfficientAssister.getName() + " - Efficiency: " + String.format("%.2f assists per game", mostEfficientAssister.getAssistEfficiency()));
        }
        if (mostEfficientShooter != null) {
            System.out.println("Most Efficient Shooter (Goal Conversion): " + mostEfficientShooter.getName() + " - Goal Conversion: " + String.format("%.2f%%", mostEfficientShooter.getGoalConversion()));
        }
        // Handle cases where no top performers were found
        if (mostAccurateShooter == null && mostEfficientScorer == null && mostEfficientAssister == null && mostEfficientShooter == null) {
            System.out.println("Unable to determine top performers. Please check player data.");
        }
    }

    /**
     * Displays the worst performers in various categories using Comparators. The method evaluates players
     * based on shooting accuracy, scoring efficiency, assist efficiency, and goal conversion rate to find
     * the least efficient performer in each category. This helps in identifying areas where players might
     * need improvement.
     */
    public void displayWorstPerformersUsingComparators() {

        if (playersMap.isEmpty()) {
            System.out.println("No players data available.");
            return;
        }
        // Initialize variables to track the worst performers
        Players leastAccurateShooter = null;
        Players leastEfficientScorer = null;
        Players leastEfficientAssister = null;
        Players leastEfficientShooter = null; // Assuming this is based on goal conversion
        // Compare and find the least efficient performers in each category
        try {
            for (Players player : playersMap.values()) {
                if (player == null) continue; // Skip if the player is null
                if (leastAccurateShooter == null || BY_SHOOTING_ACCURACY.compare(player, leastAccurateShooter) < 0) {
                    leastAccurateShooter = player;
                }
                if (leastEfficientScorer == null || BY_SCORING_EFFICIENCY.compare(player, leastEfficientScorer) < 0) {
                    leastEfficientScorer = player;
                }
                if (leastEfficientAssister == null || BY_ASSIST_EFFICIENCY.compare(player, leastEfficientAssister) < 0) {
                    leastEfficientAssister = player;
                }
                if (leastEfficientShooter == null || BY_GOAL_CONVERSION.compare(player, leastEfficientShooter) < 0) {
                    leastEfficientShooter = player;
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while processing player data: " + e.getMessage());
            return;
        }
        if (leastAccurateShooter != null) {
            System.out.println("Least Accurate Shooter: " + leastAccurateShooter.getName() + " - Accuracy: " + String.format("%.2f%%", leastAccurateShooter.getShootingAccuracy()));
        } else {
            System.out.println("Unable to determine least accurate shooter.");
        }
        if (leastEfficientScorer != null) {
            System.out.println("Least Efficient Scorer: " + leastEfficientScorer.getName() + " - Efficiency: " + String.format("%.2f goals per game", leastEfficientScorer.getGoalEfficiency()));
        } else {
            System.out.println("Unable to determine least efficient scorer.");
        }
        if (leastEfficientAssister != null) {
            System.out.println("Least Efficient Assister: " + leastEfficientAssister.getName() + " - Efficiency: " + String.format("%.2f assists per game", leastEfficientAssister.getAssistEfficiency()));
        } else {
            System.out.println("Unable to determine least efficient assister.");
        }
        if (leastEfficientShooter != null) {
            System.out.println("Least Efficient Shooter (Goal Conversion): " + leastEfficientShooter.getName() + " - Goal Conversion: " + String.format("%.2f%%", leastEfficientShooter.getGoalConversion()));
        } else {
            System.out.println("Unable to determine least efficient shooter.");
        }
    }

    /**
     * Displays the players with the most yellow and red cards. The method searches through all players
     * to find the ones with the highest number of yellow and red cards respectively. It then displays the
     * player's name along with the count of yellow or red cards they have received. If no player has
     * any yellow or red cards, it displays a message indicating no cards have been recorded.
     */
    public void displayPlayersWithMostCards() {
        System.out.println("Card Statistics:");
        // Check players with zero yellow cards
        Optional<Players> playerWithMostYellowCards = playersMap.values().stream()
                .filter(player -> player.getStats().getYellowCards() > 0)
                .max(Comparator.comparingInt(player -> player.getStats().getYellowCards()));
        // Check for players with zero red cards
        Optional<Players> playerWithMostRedCards = playersMap.values().stream()
                .filter(player -> player.getStats().getRedCards() > 0)
                .max(Comparator.comparingInt(player -> player.getStats().getRedCards()));
        // Display the player with the most yellow cards or display that there are no yellow card
        playerWithMostYellowCards.ifPresentOrElse(
                player -> System.out.println("Player with Most Yellow Cards: " + player.getName() + " - Yellow Cards: " + player.getStats().getYellowCards()),
                () -> System.out.println("No yellow cards recorded.")
        );
        // Display the player with the most red cards or display that there are no red card
        playerWithMostRedCards.ifPresentOrElse(
                player -> System.out.println("Player with Most Red Cards: " + player.getName() + " - Red Cards: " + player.getStats().getRedCards()),
                () -> System.out.println("No red cards recorded.")
        );
    }
}
