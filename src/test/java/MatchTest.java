import org.junit.jupiter.api.Test;
import org.worldcupscoreboard.Match;
import org.worldcupscoreboard.Team;
import org.worldcupscoreboard.TeamSide;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testGoalScored() {
        Team homeTeam = new Team("Brazil");
        Team awayTeam = new Team("Germany");
        Match match = new Match(homeTeam, awayTeam);

        match.startMatch();
        match.goalScored(TeamSide.HOME);
        assertEquals(1, homeTeam.getScore());
        assertEquals(0, awayTeam.getScore());

        match.goalScored(TeamSide.AWAY);
        assertEquals(1, homeTeam.getScore());
        assertEquals(1, awayTeam.getScore());
    }

    @Test
    public void testGoalScoredMatchNotRunning() {
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        try {
            match.goalScored(TeamSide.HOME);
        } catch (IllegalStateException e) {
            assertEquals("Cannot add goal since match not running", e.getMessage());
        }
    }

    @Test
    public void testTeamSide() {
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        assertEquals(TeamSide.HOME, match.teamSide("Brazil"));
        assertEquals(TeamSide.AWAY, match.teamSide("Germany"));
        try {
            match.teamSide("Argentina");
        } catch (IllegalArgumentException e) {
            assertEquals("Team not found in the match", e.getMessage());
        }
    }

    @Test
    public void testIsSame() {
        Team homeTeam = new Team("Brazil");
        Team awayTeam = new Team("Germany");
        Match match = new Match(homeTeam, awayTeam);
        Match match2 = new Match(homeTeam, awayTeam);
        assertTrue(match.isSameMatch(match2));

        Match match3 = new Match(new Team("Brazil"), new Team("Germany"));
        assertTrue(match.isSameMatch(match3));

        Match match4 = new Match(new Team("Brazil"), new Team("Argentina"));
        assertFalse(match.isSameMatch(match4));
    }
}
