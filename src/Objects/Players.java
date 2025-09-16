package Objects;
//for player in soccer team
public class Players {
    private String name; //object that holds the name
    private Stats stats; //object that holds the players stats

    /**
     * Constructs a player with the given name and initializes their stats.
     * @param name The name of the player.
     */
    public Players(String name) {
        this.name = name;
        this.stats = new Stats(); // Initialize the stats object for this player
    }

    /**
     * Gets the name of the player.
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the statistics of the player.
     * @return The statistics of the player.
     */
    public Stats getStats() {
        return stats;
    }

    // following methods are used to update stats
    /**
     * Updates the number of goals scored by the player.
     * @param goals The number of goals to add to the player's record.
     */
    public void updateGoals(int goals) {
        stats.setGoals(stats.getGoals() + goals);
    }

    /**
     * Updates the number of assists made by the player.
     * @param assists The number of assists to add to the player's record.
     */
    public void updateAssists(int assists) {
        stats.setAssists(stats.getAssists() + assists);
    }

    /**
     * Updates the number of shots taken by the player.
     * @param shots The number of shots to add to the player's record.
     */
    public void updateShots(int shots) {
        stats.setShots(stats.getShots() + shots);
    }

    /**
     * Updates the number of shots on target by the player.
     * @param shotsOnTarget The number of shots on target to add to the player's record.
     */
    public void updateShotsOnTarget(int shotsOnTarget) {
        stats.setShotsOnTarget(stats.getShotsOnTarget() + shotsOnTarget);
    }

    /**
     * Gets the number of yellow cards received by the player.
     * @return The number of yellow cards received by the player.
     */
    public int getYellowCards() {
        return this.stats.getYellowCards();
    }

    /**
     * Gets the number of red cards received by the player.
     * @return The number of red cards received by the player.
     */
    public int getRedCards() {
        return this.stats.getRedCards();
    }

    /**
     * Updates the number of yellow cards received by the player.
     * @param yellowCards The number of yellow cards to add to the player's record.
     */
    public void updateYellowCards(int yellowCards) {
        stats.setYellowCards(stats.getYellowCards() + yellowCards);
    }

    /**
     * Updates the number of red cards received by the player.
     * @param redCards The number of red cards to add to the player's record.
     */
    public void updateRedCards(int redCards) {
        stats.setRedCards(stats.getRedCards() + redCards);
    }

    /**
     * Updates the number of games played by the player.
     * @param games The number of games to add to the player's record.
     */
    public void updateGamesPlayed(int games) {
        stats.setGamesPlayed(stats.getGamesPlayed() + games);
    }

    //the following methods calculate the special outputs
    /**
     * Calculates the shooting accuracy of the player.
     * @return The shooting accuracy of the player.
     */
    public double getShootingAccuracy() {
        return stats.calculateShootingAccuracy();
    }

    /**
     * Calculates the goal conversion rate of the player.
     * @return The goal conversion rate of the player.
     */
    public double getGoalConversion() {
        return stats.calculateGoalConversion();
    }

    /**
     * Calculates the goal efficiency of the player.
     * @return The goal efficiency of the player.
     */
    public double getGoalEfficiency() {
        return stats.calculateEfficiency();
    }

    /**
     * Calculates the assist efficiency of the player.
     * @return The assist efficiency of the player.
     */
    public double getAssistEfficiency() {
        return stats.calculateAssistEfficiency();
    }
}
