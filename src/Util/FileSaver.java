package Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import Objects.Players;

/**
 * Utility class for saving player data [name & stats] and statistics to a file.
 */
public class FileSaver {
    /**
     * Saves the player data [name & stats] and statistics to a file.
     * @param file The file to save the data [name & stats] to.
     * @param playersMap The map containing player data [name & stats].
     * @return True if the save operation was successful, false otherwise.
     */
    public static boolean save(File file, Map<String, Players> playersMap) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            //writes the header line of the file
            fileWriter.write("Player Name, Games Played, Goals,Assists, Shots,Shots on Target, Yellow Cards, Red Cards\n");
            // Loop through each player in the playersMap
            for (Players player : playersMap.values()) {
                fileWriter.write(String.format("%s,%d,%d,%d,%d,%d,%d,%d%n",
                        player.getName(), // Player Name
                        player.getStats().getGamesPlayed(), // Games Played
                        player.getStats().getGoals(), // Goals
                        player.getStats().getAssists(), // Assists
                        player.getStats().getShots(), // Shots
                        player.getStats().getShotsOnTarget(), // Shots on Target
                        player.getStats().getYellowCards(), // Yellow Cards
                        player.getStats().getRedCards())); // Red Cards
            }
            //flush and closes the FileWriter
            fileWriter.flush();
            return true; //if save was successful, returns true
        } catch (IOException ioe) {
            return false; //returns false if there was an error during file writing
        }
    }
}

