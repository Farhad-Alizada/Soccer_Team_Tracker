public class OldData {
    public static final String PLAYER_FORMAT = "%-20s %-10s";
    public static final String SHOOTING_FORMAT = "%-20s %-10s %-18s %-10s %-20s %-20s";
    public static final String EFFICIENCY_FORMAT = "%-20s %-10s %-10s %-10s";

    public static final String redCard_Format = "%-20s %-10s %-10s";;
    public static final String yellowCard_Format = "%-20s %-10s %-10s";;
    public static final String yellowCard_Header = String.format(PLAYER_FORMAT, "NAME", "GAMES", "YELLOW CARDS");
    public static final String redCard_Header = String.format(PLAYER_FORMAT, "NAME", "GAMES", "RED CARDS");
    public static final String SCORER_HEADER = String.format(PLAYER_FORMAT, "NAME", "GOALS");
    public static final String ASSISTER_HEADER = String.format(PLAYER_FORMAT, "NAME", "ASSISTS");
    public static final String SHOOTING_HEADER = String.format(SHOOTING_FORMAT, "NAME", "SHOTS", "SHOTS ON TARGET", "GOALS", "SHOT ACCURACY", "SHOT CONVERSION");
    public static final String EFFICIENTSCORERS_HEADER = String.format(EFFICIENCY_FORMAT, "NAME", "GOALS", "GAMES", "RATIO");
    public static final String EFFICIENTASSISTER_HEADER = String.format(EFFICIENCY_FORMAT, "NAME", "ASSISTS", "GAMES", "RATIO");
}