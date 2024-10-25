package utils;

import org.junit.jupiter.api.Test;
import org.worldcupscoreboard.Match;
import org.worldcupscoreboard.Team;
import org.worldcupscoreboard.utils.MatchUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchUtilTest {
    @Test
    public void  testListContainsMatch() {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match(new Team("Brazil"), new Team("Argentina")));
        matches.add(new Match(new Team("Germany"), new Team("France")));

        assertTrue(MatchUtil.listContains(matches, new Match(new Team("Brazil"), new Team("Argentina"))));
        assertFalse(MatchUtil.listContains(matches, new Match(new Team("Brazil"), new Team("France"))));
    }

    @Test
    public void  testListNotContainsMatch() {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match(new Team("Brazil"), new Team("Argentina")));
        matches.add(new Match(new Team("Germany"), new Team("France")));

        assertTrue(MatchUtil.listNotContains(matches, new Match(new Team("Brazil"), new Team("France"))));
        assertFalse(MatchUtil.listNotContains(matches, new Match(new Team("Brazil"), new Team("Argentina"))));
    }
}
