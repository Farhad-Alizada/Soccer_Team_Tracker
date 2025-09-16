package Util;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import Objects.Players;

/**
 * Utility class for loading player data [name & stats] from a file into a player map.
 */
public class FileLoader {
    /**
     * Loads player data from a file into the given map.
     * @param file The file containing player data [name & stats]
     * @param playersMap The map to store player data [name & stats] in.
     */
    public static void load(File file, Map<String, Players> playersMap) {
        try (Scanner scanner = new Scanner(file)) {
            //skips the first line because it is a header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            //loops through each line in the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //splits the line into parts
                String[] parts = line.split(",");
                //turns each part into a variable to be used later
                String playerName = parts[0];
                int gamesPlayed = Integer.parseInt(parts[1]);
                int goals = Integer.parseInt(parts[2]);
                int assists = Integer.parseInt(parts[3]);
                int shots = Integer.parseInt(parts[4]);
                int shotsOnTarget = Integer.parseInt(parts[5]);
                int yellowCards = Integer.parseInt(parts[6]);
                int redCards = Integer.parseInt(parts[7]);
                //checks if player is in map

                if (!playersMap.containsKey(playerName)) {
                    playersMap.put(playerName, new Players(playerName)); //creates new entry if not in map
                }
                //sets the players stats
                Players player = playersMap.get(playerName);
                player.getStats().setGamesPlayed(gamesPlayed);
                player.getStats().setGoals(goals);
                player.getStats().setAssists(assists);
                player.getStats().setShots(shots);
                player.getStats().setShotsOnTarget(shotsOnTarget);
                player.getStats().setYellowCards(yellowCards);
                player.getStats().setRedCards(redCards);
            }
        }
        //catches IOexception if any error happens with file reader
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}