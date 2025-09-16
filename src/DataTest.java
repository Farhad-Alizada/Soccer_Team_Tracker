import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Objects.Stats;
import Objects.Players;
import Objects.StatType;


class DataTest {

    private Data data;

    @BeforeEach
    void setUp() {
        data = new Data();
        // Add players here if needed for setup
    }

    @Test
    void whenAddValidPlayer_thenPlayerIsAdded() {
        data.addPlayer("Modric");
        assertTrue(data.playerAlreadyExists("Modric"));
    }


    @Test
    void TestValidPLayerAdded() {
        data.addPlayer("Khabib");
        assertFalse(data.playerAlreadyExists("Tom Cruise"));
    }

    @Test
    void whenAddNumericPlayer_thenPlayerIsNotAdded() {
        data.addPlayer("12345");
        assertFalse(data.playerAlreadyExists("12345"));
    }
    @Test
    void playerExistsAfterBeingAdded() {
        data.addPlayer("Mudryk");
        assertTrue(data.playerAlreadyExists("Mudryk"));
    }

    @Test
    void playerDoesNotExistIfNotAdded() {
        assertFalse(data.playerAlreadyExists("Bounou"));
    }
    @Test
    void testNumberDoesntExist() {
        assertFalse(data.playerAlreadyExists("john123"));
    }

    @Test
    void addPlayerWithValidName() {
        data.addPlayer("Bale");
        assertTrue(data.playerAlreadyExists("Bale"));
    }

    @Test
    void doNotAddPlayerWithNumericName() {
        data.addPlayer("123");
        assertFalse(data.playerAlreadyExists("123"));
    }

    @Test
    void doNotAddDuplicatePlayer() {
        String playerName = "Suarez";

        // Add the player for the first time.
        data.addPlayer(playerName);

        // Retrieve the player and their initial stats.
        Players originalPlayer = data.getPlayer(playerName);
        int originalGoals = originalPlayer.getStats().getGoals();

        // Attempt to add the same player again.
        data.addPlayer(playerName);

        // Retrieve the player and their stats after the second add attempt.
        Players playerAfterDuplicateAdd = data.getPlayer(playerName);
        int goalsAfterDuplicateAdd = playerAfterDuplicateAdd.getStats().getGoals();

        // Assert that the player's stats (in this case, goals) remain unchanged,
        // indicating that the player was not duplicated.
        assertEquals(originalGoals, goalsAfterDuplicateAdd, "Player stats should remain unchanged after duplicate add attempt");
    }


    @Test
    void doNotAddDuplicatePlayer2() {
        String playerName = "Freddy";
        data.addPlayer(playerName);
        Players originalPlayer = data.getPlayer(playerName);
        int originalGoals = originalPlayer.getStats().getGoals();

        data.addPlayer(playerName);
        Players playerAfterDuplicateAdd = data.getPlayer(playerName);
        int goalsAfterDuplicateAdd = playerAfterDuplicateAdd.getStats().getGoals();
        assertEquals(originalGoals, goalsAfterDuplicateAdd, "Player stats should remain unchanged after duplicate add attempt");
    }


    @Test
    void returnPlayerWhenExists() {
        data.addPlayer("Ronaldinho");
        assertNotNull(data.getPlayer("Ronaldinho"));
    }

    @Test
    void returnNullWhenPlayerDoesNotExist() {
        assertNull(data.getPlayer("Messi"));
    }

    @Test
    void updatePlayerStatsAndRetrieve() {
        data.addPlayer("Ronaldo");
        data.updateSpecificStat("Ronaldo", StatType.GOALS, 3);
        Players player = data.getPlayer("Ronaldo");
        assertEquals(3, player.getStats().getGoals());
    }

    @Test
    void updateGoalsForExistingPlayer() {
        data.addPlayer("Benzema");
        data.updateSpecificStat("Benzema", StatType.GOALS, 1);
        assertEquals(1, data.getPlayer("Benzema").getStats().getGoals());
    }

    @Test
    void updateGamesPlayedForExistingPlayer() {
        data.addPlayer("Bellingham");
        data.updateSpecificStat("Bellingham", StatType.GAMES_PLAYED, 10);
        assertEquals(10, data.getPlayer("Bellingham").getStats().getGamesPlayed());
    }
    @Test
    void notUpdateGamesPLayed() {
        data.updateSpecificStat("lopez", StatType.GAMES_PLAYED, 5);
        assertNull(data.getPlayer("lopez"));
    }
    @Test
    void notUpdateStatsForNonExistentPlayer() {
        data.updateSpecificStat("Neymar", StatType.ASSISTS, 5);
        assertNull(data.getPlayer("Neymar"));
    }

    @Test
    void updateAssistsForExistingPlayer() {
        data.addPlayer("Hazard");
        data.updateSpecificStat("Hazard", StatType.ASSISTS, 2);
        assertEquals(2, data.getPlayer("Hazard").getStats().getAssists());
    }

    @Test
    void updateShotsForExistingPlayer() {
        data.addPlayer("Vinicius");
        data.updateSpecificStat("Vinicius", StatType.SHOTS, 5);
        assertEquals(5, data.getPlayer("Vinicius").getStats().getShots());
    }

    //yellow card test
    @Test
    void updateYellowCardsForExistingPlayer() {
        data.addPlayer("Ramos");
        data.updateSpecificStat("Ramos", StatType.YELLOW_CARDS, 1);
        assertEquals(1, data.getPlayer("Ramos").getStats().getYellowCards());
    }
    @Test
    void YellowCardTestExistingPlayer() {
        String Player = "Cole";
        int redCard = 4;
        data.addPlayer(Player);
        data.updateSpecificStat(Player, StatType.RED_CARDS, redCard);
        assertEquals(redCard, data.getPlayer(Player).getStats().getRedCards());
    }




    //RedCard Test
    @Test
    void updateRedCardsForExistingPlayer() {
        data.addPlayer("Pepe");
        data.updateSpecificStat("Pepe", StatType.RED_CARDS, 1);
        assertEquals(1, data.getPlayer("Pepe").getStats().getRedCards());
    }
    @Test
    void RedCardTestExistingPlayer() {
        String Player = "Gullet";
        int redCard = 1;
        data.addPlayer(Player);
        data.updateSpecificStat(Player, StatType.RED_CARDS, redCard);
        assertEquals(redCard, data.getPlayer(Player).getStats().getRedCards());
    }

}
