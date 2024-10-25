import org.junit.jupiter.api.Test;
import org.worldcupscoreboard.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamTest {
    @Test
    public void testTeam() {
        Team team = new Team("Brazil");
        assertEquals("Brazil", team.getName());
        assertEquals(0, team.getScore());
        team.addScore(3);
        assertEquals(3, team.getScore());
        team.addScore(2);
        assertEquals(5, team.getScore());
        team.resetScore();
        assertEquals(0, team.getScore());
    }
}
