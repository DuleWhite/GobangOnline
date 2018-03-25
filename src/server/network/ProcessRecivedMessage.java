package server.network;

import server.entity.Match;
import server.entity.Player;
import server.manager.MatchManager;
import server.manager.PlayerManager;

public class ProcessRecivedMessage {
    public static void process(Player player, String info) {
        String order = info.substring(0, 5);
        String param = info.substring(5);
        if (order.equals("NICK:")) {
            player.setNickname(param);
        }
        if (order.equals("DSCN:")) {
            player.connected = false;
            PlayerManager.getInstance().removePlayer(player.getPlayerId());
        }
        if (order.equals("CRNM:")) {
            Match match = new Match();
            match.addPlayer(player);
            MatchManager.getInstance().addMatch(match);
            updateClientMatchList();
            player.setStatus(Player.ROOM_UNREADY);
        }
        if (order.equals("RFML:")) {
            SendMessage.UpdateMatchList(player);
        }
        if (order.equals("JION:")) {
            int matchId = Integer.parseInt(param);
            Match match = MatchManager.getInstance().getMatches().get(matchId);
            if (match.isFull()) {
                SendMessage.matchFull(player);
            } else {
                SendMessage.joinMatch(player);
            }
        }
    }

    private static void updateClientMatchList() {
        for (Player player : PlayerManager.getInstance().getPlayers().values()) {
            SendMessage.UpdateMatchList(player);
        }
    }
}
