package server.network;

import server.entity.Match;
import server.entity.Player;
import server.manager.MatchManager;
import server.manager.PlayerManager;

public class ProcessRecivedMessage {
    public static void process(Player player, String info) {
        if (info.substring(0, 5).equals("NICK:")) {
            player.setNickname(info.substring(5));
        }
        if (info.substring(0, 5).equals("DSCN:")) {
            player.connected = false;
            PlayerManager.getInstance().removePlayer(player.getPlayerId());
        }
        if (info.substring(0, 5).equals("CRNM:")) {
            Match match = new Match();
            match.addPlayer(player);
            MatchManager.getInstance().addMatch(match);
            updateCilentMatchList();
            player.setStatus(Player.ROOM_UNREADY);
        }
    }

    private static void updateCilentMatchList() {
        for (Player player : PlayerManager.getInstance().getPlayers().values()) {
            SendMessage.UpdateMatchList(player.getPlayerId());
        }
    }
}
