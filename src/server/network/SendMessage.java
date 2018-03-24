package server.network;

import server.manager.MatchManager;
import server.manager.PlayerManager;

import java.io.IOException;
import java.io.PrintStream;

public class SendMessage {
    public static void yourId(int playerId) {
        try {
            PrintStream ps = new PrintStream(PlayerManager.getInstance().getPlayers().get(playerId).getSocket().getOutputStream());
            ps.println("YRID:" + playerId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateMatchList(int playerId) {
        try {
            PrintStream ps = new PrintStream(PlayerManager.getInstance().getPlayers().get(playerId).getSocket().getOutputStream());
            ps.println("UPML:" + MatchManager.getInstance().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
