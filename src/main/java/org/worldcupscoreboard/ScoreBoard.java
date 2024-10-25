package org.worldcupscoreboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.worldcupscoreboard.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class ScoreBoard {
    private static final Logger logger = LoggerFactory.getLogger(ScoreBoard.class);

    private List<Match> matches;

    public ScoreBoard() {
        this.matches = new ArrayList<>();
    }

    public ScoreBoard(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }

    /**
     * Add a match to the scoreboard.
     * If the match is already added, it will log a message and return.
     * @param match the match to add
     */
    public void addMatch(Match match) {
        ObjectUtil.notNull(match, "match must not be null");

        if (this.contains(match)) {
            logger.info("Match between {} and {} already added to the scoreboard", match.getHomeTeam().getName(), match.getAwayTeam().getName());
            return;
        }

        validateIfTeamExistsInAnotherMatch(matches, match);
        matches.add(match);
    }

    /**
     * Start a match.
     * If the match is already started, it will log a message and return.
     * @param match the match to start
     */
    public void startMatch(Match match) {
        ObjectUtil.notNull(match, "match must not be null");

        if (!this.contains(match)) {
            throw new IllegalArgumentException("Match not found in the scoreboard");
        }

        if (match.getStartTime() != null) {
            logger.info("Match already started");
            return;
        }
        match.startMatch();
    }

    /**
     * End a match. And, removes it from scoreboard.
     * @param match the match to end
     */
    public void endMatch(Match match) {
        ObjectUtil.notNull(match, "match must not be null");

        if (!this.contains(match)) {
            throw new IllegalArgumentException("Match not found in the scoreboard");
        }
        if (!match.isGameRunning()) {
            throw new IllegalStateException("Match has not started yet");
        }

        match.endMatch();
        matches.remove(match);
    }

    private boolean contains(Match match) {
        Predicate<Match> isSameTeam = existingMatch -> existingMatch.getHomeTeam().getName().equalsIgnoreCase(match.getHomeTeam().getName())
                || existingMatch.getAwayTeam().getName().equals(match.getAwayTeam().getName());

        return matches.stream().anyMatch(isSameTeam);
    }


    private static void validateIfTeamExistsInAnotherMatch(List<Match> matches, Match match) {
        Predicate<Match> isSameTeam = existingMatch -> existingMatch.getHomeTeam().getName().equalsIgnoreCase(match.getHomeTeam().getName())
                || existingMatch.getAwayTeam().getName().equals(match.getHomeTeam().getName())
                || existingMatch.getHomeTeam().getName().equals(match.getAwayTeam().getName())
                || existingMatch.getAwayTeam().getName().equals(match.getAwayTeam().getName());

        if (matches.stream().anyMatch(isSameTeam)) {
            throw new IllegalArgumentException("Teams are already playing in another match");
        }
    }
}
