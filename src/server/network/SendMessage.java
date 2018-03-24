package server.network;

import server.entity.Player;
import server.manager.MatchManager;
import server.manager.PlayerManager;

import java.io.IOException;
import java.io.PrintStream;

public class SendMessage {

    private static void Send(Player player, String info) {
        try {
            PrintStream ps = new PrintStream(player.getSocket().getOutputStream());
            ps.println(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void yourId(int playerId) {
        Send(PlayerManager.getInstance().getPlayers().get(playerId), "YRID:" + playerId);
    }

    public static void UpdateMatchList(Player player) {
        Send(player, "UPML:" + MatchManager.getInstance().toString());
    }

    public static void serverOffline() {
        for (Player player : PlayerManager.getInstance().getPlayers().values()) {
            Send(player, "OFLN:" + MatchManager.getInstance().toString());
        }
    }

    public static void matchFull(Player player) {
        Send(player, "MCFL:");
    }

    public static void joinMatch(Player player) {
        //TODO:
        Send(player, "JNMC:" + "Something");
    }

}
