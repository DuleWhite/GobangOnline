package server.manager;

import server.entity.Player;
import server.frame.ServerFrame;

import java.util.HashMap;

public class PlayerManager {
    private static PlayerManager instance = null;
    private HashMap<Integer, Player> players = null;

    private PlayerManager() {

    }

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public HashMap<Integer, Player> getPlayers() {
        if (players == null) {
            players = new HashMap<>();
        }
        return players;
    }

    public void addPlayer(int playerId, Player player) {
        getPlayers().put(playerId, player);
        ServerFrame.getInstance().getModel_players().addElement(playerId);
        ServerFrame.getInstance().getList_players().repaint();
    }

    public void removePlayer(int playerId) {
        getPlayers().remove(playerId);
        ServerFrame.getInstance().getModel_players().removeElement(playerId);
        ServerFrame.getInstance().getList_players().repaint();
    }
}
