package Objects;

public class Stats {
    private int gamesPlayed;
    private int goals;
    private int assists;
    private int shots;
    private int shotsOnTarget;
    private int yellowCards;
    private int redCards;

    /**
     * Initializes all statistics to zero.
     */
    public Stats() {
        this.gamesPlayed = 0;
        this.goals = 0;
        this.assists = 0;
        this.shots = 0;
        this.shotsOnTarget = 0;
    }

    // Getters and setters for each statistic

    /**
     * Retrieves the number of games played.
     * @return The number of games played.
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Sets the number of games played.
     * @param gamesPlayed The number of games played.
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = Math.max(gamesPlayed, 0); // Ensure non-negative
    }

    /**
     * Retrieves the number of goals scored.
     * @return The number of goals scored.
     */
    public int getGoals() {
        return goals;
    }

    /**
     * Sets the number of goals scored.
     * @param goals The number of goals scored.
     */
    public void setGoals(int goals) {
        this.goals = Math.max(goals, 0); // Ensure non-negative
    }

    /**
     * Retrieves the number of assists.
     * @return The number of assists.
     */
    public int getAssists() {
        return assists;
    }

    /**
     * Sets the number of assists.
     * @param assists The number of assists.
     */
    public void setAssists(int assists) {
        this.assists = Math.max(assists, 0); // Ensure non-negative
    }

    /**
     * Retrieves the total number of shots taken.
     * @return The total number of shots taken.
     */
    public int getShots() {
        return shots;
    }

    /**
     * Sets the total number of shots taken.
     * @param shots The total number of shots taken.
     */
    public void setShots(int shots) {
        this.shots = Math.max(shots, 0); // Ensure non-negative
    }

    /**
     * Retrieves the number of shots on target.
     * @return The number of shots on target.
     */
    public int getShotsOnTarget() {
        return shotsOnTarget;
    }

    /**
     * Sets the number of shots on target.
     * @param shotsOnTarget The number of shots on target.
     */
    public void setShotsOnTarget(int shotsOnTarget) {
        this.shotsOnTarget = Math.max(shotsOnTarget, 0); // Ensure non-negative and not greater than total shots
        this.shotsOnTarget = Math.min(this.shotsOnTarget, this.shots); // Adjust if shots on target exceed total shots
    }

    /**
     * Retrieves the number of yellow cards received.
     * @return The number of yellow cards received.
     */
    public int getYellowCards() {
        return yellowCards;
    }

    /**
     * Sets the number of yellow cards received.
     * @param yellowCards The number of yellow cards received.
     */
    public void setYellowCards(int yellowCards) {
        this.yellowCards = Math.max(yellowCards, 0); // Ensure non-negative
    }

    /**
     * Retrieves the number of red cards received.
     * @return The number of red cards received.
     */
    public int getRedCards() {
        return redCards;
    }

    /**
     * Sets the number of red cards received.
     * @param redCards The number of red cards received.
     */
    public void setRedCards(int redCards) {
        this.redCards = Math.max(redCards, 0); // Ensure non-negative
    }

    /**
     * Calculates the shooting accuracy as a percentage.
     * @return The shooting accuracy as a percentage.
     */
    public double calculateShootingAccuracy() {
        if (shots > 0) {
            return (double) shotsOnTarget / shots * 100; // Multiply by 100 for percentage
        }
        return 0;
    }

    /**
     * Calculates the goal conversion ratio as a percentage.
     * @return The goal conversion ratio as a percentage.
     */
    public double calculateGoalConversion() {
        if (shots > 0) {
            return (double) goals / shots * 100; // Multiply by 100 for percentage
        }
        return 0;
    }

    /**
     * Calculates the efficiency (goals per game).
     * @return The efficiency (goals per game).
     */
    public double calculateEfficiency() {
        if (gamesPlayed > 0) {
            return (double) goals / gamesPlayed;
        }
        return 0;
    }

    /**
     * Calculates the assist efficiency (assists per game).
     * @return The assist efficiency (assists per game).
     */
    public double calculateAssistEfficiency() {
        if (gamesPlayed > 0) {
            return (double) assists / gamesPlayed;
        }
        return 0;
    }
}

