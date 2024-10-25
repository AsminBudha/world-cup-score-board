package org.worldcupscoreboard.utils;

import org.worldcupscoreboard.Match;

import java.util.List;
import java.util.function.Predicate;

public class MatchUtil {
    public static boolean listContains(List<Match> matches, Match match) {
        Predicate<Match> isSameTeam = existingMatch -> existingMatch.getHomeTeam().getName().equalsIgnoreCase(match.getHomeTeam().getName())
                && existingMatch.getAwayTeam().getName().equalsIgnoreCase(match.getAwayTeam().getName());

        return matches.stream().anyMatch(isSameTeam);
    }

    public static boolean listNotContains(List<Match> matches, Match match) {
        return !listContains(matches, match);
    }
}
