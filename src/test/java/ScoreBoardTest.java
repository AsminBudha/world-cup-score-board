import org.junit.jupiter.api.Test;
import org.worldcupscoreboard.Match;
import org.worldcupscoreboard.ScoreBoard;
import org.worldcupscoreboard.Team;
import org.worldcupscoreboard.TeamSide;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ScoreBoardTest {
    @Test
    public void testScoreBoard() {
        var subject = new ScoreBoard();
        assertNotNull(subject.getMatches());

        // Add a match
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        subject.addMatch(match);
        assertEquals(1, subject.getMatches().size());

        // Start a match
        subject.startMatch(match);
        assertNotNull(match.getStartTime());
        assertNull(match.getEndTime());

        // End a match
        subject.endMatch(match);
        assertNotNull(match.getStartTime());
        assertNotNull(match.getEndTime());
        assertEquals(0, subject.getMatches().size());
    }

    @Test
    public void testScoreBoardAddMatchTwice() {
        var subject = new ScoreBoard();
        assertNotNull(subject.getMatches());

        // Add a match
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        subject.addMatch(match);
        assertEquals(1, subject.getMatches().size());

        // Add the same match again
        subject.addMatch(match);
        assertEquals(1, subject.getMatches().size());
    }

    @Test
    public void testScoreBoardAddMatchTwice_differentInstance() {
        var subject = new ScoreBoard();
        assertNotNull(subject.getMatches());

        // Add a match
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        subject.addMatch(match);
        assertEquals(1, subject.getMatches().size());

        // Add the same match again
        Match match2 = new Match(new Team("Brazil"), new Team("Germany"));
        subject.addMatch(match2);
        assertEquals(1, subject.getMatches().size());
    }

    @Test
    public void testScoreBoardStartMatchNotInScoreBoard() {
        var subject = new ScoreBoard();
        assertNotNull(subject.getMatches());

        // Add a match
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        subject.addMatch(match);
        assertEquals(1, subject.getMatches().size());

        // Start a match not in the scoreboard
        try {
            subject.startMatch(new Match(new Team("Brazil"), new Team("Germany")));
        } catch (IllegalArgumentException e) {
            assertEquals("Match not found in the scoreboard", e.getMessage());
        }
    }

    @Test
    public void testScoreBoardEndMatchNotInScoreBoard() {
        var subject = new ScoreBoard();
        assertNotNull(subject.getMatches());

        // Add a match
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        subject.addMatch(match);
        assertEquals(1, subject.getMatches().size());

        // End a match not in the scoreboard
        try {
            subject.endMatch(new Match(new Team("Brazil"), new Team("Argentina")));
        } catch (IllegalArgumentException e) {
            assertEquals("Match not found in the scoreboard", e.getMessage());
        }
    }

    @Test
    public void testScoreBoardStartMatchTwice() {
        var subject = new ScoreBoard();
        assertNotNull(subject.getMatches());

        // Add a match
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        subject.addMatch(match);
        assertEquals(1, subject.getMatches().size());

        // Start a match
        subject.startMatch(match);
        var initialStartTime = match.getStartTime();
        assertNotNull(match.getStartTime());
        assertNull(match.getEndTime());

        // Start the same match again
        subject.startMatch(match);
        assertEquals(initialStartTime, match.getStartTime());
        assertNull(match.getEndTime());
    }

    @Test
    public void testScoreBoardEndMatchTwice() {
        var subject = new ScoreBoard();
        assertNotNull(subject.getMatches());

        // Add a match
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        subject.addMatch(match);
        assertEquals(1, subject.getMatches().size());

        // Start a match
        subject.startMatch(match);
        assertNotNull(match.getStartTime());
        assertNull(match.getEndTime());

        // End a match
        subject.endMatch(match);
        assertNotNull(match.getStartTime());
        assertNotNull(match.getEndTime());
        assertEquals(0, subject.getMatches().size());

        // End the same match again
        try {
            subject.endMatch(match);
        } catch (IllegalArgumentException e) {
            assertEquals("Match not found in the scoreboard", e.getMessage());
        }
    }

    @Test
    public void testScoreBoardGoalScored() {
        var subject = new ScoreBoard();
        assertNotNull(subject.getMatches());

        // Add a match
        Match match = new Match(new Team("Brazil"), new Team("Germany"));
        subject.addMatch(match);

        subject.startMatch(match);

        // Goal scored
        subject.goalScored(match, TeamSide.HOME);
        assertEquals(1, subject.getMatches().get(0).totalScore());

        subject.goalScored(match, TeamSide.AWAY);
        assertEquals(2, subject.getMatches().get(0).totalScore());

        subject.goalScored(match, new Team("Brazil"));
        assertEquals(3, subject.getMatches().get(0).totalScore());

        subject.goalScored(match, new Team("Germany"));
        assertEquals(4, subject.getMatches().get(0).totalScore());

        subject.goalScored(match, "Brazil");
        assertEquals(5, subject.getMatches().get(0).totalScore());

    }
}
