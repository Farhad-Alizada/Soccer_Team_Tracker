package Comparators;

import Objects.Players;
import java.util.Comparator;


public class Comparators {
        /**
         * Sorts players by shooting accuracy and then by name.
         */
        public static final Comparator<Players> BY_SHOOTING_ACCURACY = Comparator
                .comparingDouble(Players::getShootingAccuracy)
                .thenComparing(Players::getName);

        /**
         * sorts Players by goal conversion rate and then by name.
         */
        public static final Comparator<Players> BY_GOAL_CONVERSION = Comparator
                .comparingDouble(Players::getGoalConversion)
                .thenComparing(Players::getName);

        /**
         * sorts Players by scoring efficiency and then by name.
         */
        public static final Comparator<Players> BY_SCORING_EFFICIENCY = Comparator
                .comparingDouble(Players::getGoalEfficiency)
                .thenComparing(Players::getName);

        /**
         * sorts Players by assist efficiency and then by name.
         */
        public static final Comparator<Players> BY_ASSIST_EFFICIENCY = Comparator
                .comparingDouble(Players::getAssistEfficiency)
                .thenComparing(Players::getName);

        /**
         * sorts Players by the number of yellow cards received and then by name.
         */
        public static final Comparator<Players> BY_YELLOW_CARDS = Comparator
                .comparingInt(Players::getYellowCards)
                .thenComparing(Players::getName);

        /**
         * sorts Players by the number of red cards received and then by name.
         */
        public static final Comparator<Players> BY_RED_CARDS = Comparator
                .comparingInt(Players::getRedCards)
                .thenComparing(Players::getName);
}
