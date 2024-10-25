package org.worldcupscoreboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Objects;

public class Match {
    private static final Logger logger = LoggerFactory.getLogger(Match.class);

    private final Team homeTeam;
    private final Team awayTeam;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Match(Team homeTeam, Team awayTeam) {
        validateTeams(homeTeam, awayTeam);

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void startMatch() {
        if (startTime != null) {
            throw new IllegalStateException("Match already started");
        }
        this.startTime = LocalDateTime.now();
    }

    public void endMatch() {
        if (endTime != null) {
            throw new IllegalStateException("Match already ended");
        }
        this.endTime = LocalDateTime.now();
    }

    public void goalScored(TeamSide teamSide) {
        if (!isGameRunning()) {
            throw new IllegalStateException("Cannot add goal since match not running");
        }

        if (teamSide == TeamSide.HOME) {
            homeTeam.addScore(1);
        } else {
            awayTeam.addScore(1);
        }
    }

    public int totalScore() {
        return homeTeam.getScore() + awayTeam.getScore();
    }

    public boolean isGameRunning() {
        return startTime != null && endTime == null;
    }

    public TeamSide teamSide(String teamName) {
        if (teamName.equalsIgnoreCase(homeTeam.getName())) {
            return TeamSide.HOME;
        } else if (teamName.equalsIgnoreCase(awayTeam.getName())) {
            return TeamSide.AWAY;
        } else {
            throw new IllegalArgumentException("Team not found in the match");
        }
    }

    public boolean isSameMatch(Match match) {
        return homeTeam.getName().equalsIgnoreCase(match.getHomeTeam().getName())
                && awayTeam.getName().equalsIgnoreCase(match.getAwayTeam().getName());
    }

    private static void validateTeams(Team homeTeam, Team awayTeam) {
        if (homeTeam == null) {
            throw new IllegalArgumentException("homeTeam must not be null");
        }
        if (awayTeam == null) {
            throw new IllegalArgumentException("awayTeam must not be null");
        }
        if (homeTeam.getName().equalsIgnoreCase(awayTeam.getName())) {
            throw new IllegalArgumentException("Teams must not be the same");
        }
    }

}
