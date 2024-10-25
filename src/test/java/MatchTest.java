import org.junit.jupiter.api.Test;
import org.worldcupscoreboard.Match;
import org.worldcupscoreboard.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MatchTest {
    @Test
    public void testMatch() {
        Team homeTeam = new Team("Brazil");
        Team awayTeam = new Team("Germany");
        Match match = new Match(homeTeam, awayTeam);

        assertEquals(homeTeam, match.getHomeTeam());
        assertEquals(awayTeam, match.getAwayTeam());

        assertNull(match.getStartTime());
        assertNull(match.getEndTime());

        match.startMatch();
        assertNotNull(match.getStartTime());
        assertNull(match.getEndTime());

        match.endMatch();
        assertNotNull(match.getStartTime());
        assertNotNull(match.getEndTime());
    }

    @Test
    public void testMatchNullTeam() {
        try {
            new Match(null, new Team("Germany"));
        } catch (IllegalArgumentException e) {
            assertEquals("homeTeam must not be null", e.getMessage());
        }

        try {
            new Match(new Team("Brazil"), null);
        } catch (IllegalArgumentException e) {
            assertEquals("awayTeam must not be null", e.getMessage());
        }
    }

    @Test
    public void testMatchSameTeam() {
        Team team = new Team("Brazil");
        try {
            new Match(team, team);
        } catch (IllegalArgumentException e) {
            assertEquals("Teams must not be the same", e.getMessage());
        }
    }

    @Test
    public void testMatchStartTwice() {
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        match.startMatch();
        try {
            match.startMatch();
        } catch (IllegalStateException e) {
            assertEquals("Match already started", e.getMessage());
        }
    }

    @Test
    public void testMatchEndTwice() {
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        match.startMatch();
        match.endMatch();
        try {
            match.endMatch();
        } catch (IllegalStateException e) {
            assertEquals("Match already ended", e.getMessage());
        }
    }
}
