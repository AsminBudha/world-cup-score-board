package org.worldcupscoreboard.utils;

import org.worldcupscoreboard.Match;

import java.util.List;
import java.util.function.Predicate;

public class MatchUtil {
    public static boolean listContains(List<Match> matches, Match match) {
        if (matches == null || match == null) {
            return false;
        }

        Predicate<Match> isSameTeam = existingMatch -> existingMatch.isSameMatch(match);

        return matches.stream().anyMatch(isSameTeam);
    }

    public static boolean listNotContains(List<Match> matches, Match match) {
        return !listContains(matches, match);
    }
}
