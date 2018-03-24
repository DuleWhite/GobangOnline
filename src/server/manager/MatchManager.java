package server.manager;

import server.entity.Match;
import server.frame.ServerFrame;

import java.util.HashMap;

public class MatchManager {
    private static MatchManager instance = null;
    private HashMap<Integer, Match> matches = null;

    public static MatchManager getInstance() {
        if (instance == null) {
            instance = new MatchManager();
        }
        return instance;
    }

    public HashMap<Integer, Match> getMatches() {
        if (matches == null) {
            matches = new HashMap<>();
        }
        return matches;
    }

    public void addMatch(Match match) {
        getMatches().put(match.getMatchId(), match);
        ServerFrame.getInstance().getModel_matches().addElement(match.getMatchId());
        ServerFrame.getInstance().getList_matches().repaint();
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (Match match : getMatches().values()) {
            temp.append(match.toString());
            temp.append("&");
        }
        return temp.toString();
    }
}
