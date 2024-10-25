package org.worldcupscoreboard;

import java.time.LocalDateTime;

public class Match {
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
