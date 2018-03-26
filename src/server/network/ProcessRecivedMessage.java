package server.network;

import server.entity.Match;
import server.entity.Player;
import server.manager.MatchManager;
import server.manager.PlayerManager;

import javax.swing.*;

public class ProcessRecivedMessage {
    public static void process(Player player, String info) {
        //JOptionPane.showMessageDialog(null,"INFO:"+info);
        String order = info.substring(0, 5);
        String param = info.substring(5);
        if (order.equals("NICK:")) {
            player.setNickname(param);
        }
        if (order.equals("DSCN:")) {
            //JOptionPane.showMessageDialog(null,"SERVER:RECIVED:DSCN");
            player.connected = false;
            PlayerManager.getInstance().removePlayer(player.getPlayerId());
        }
        if (order.equals("CRNM:")) {
            Match match = new Match(player);
            MatchManager.getInstance().addMatch(match);
            updateClientMatchList();
            player.setStatus(Player.ROOM_UNREADY);
            SendMessage.yourMatchId(player,String.valueOf(match.getMatchId()));
        }
        if (order.equals("RFML:")) {
            SendMessage.UpdateMatchList(player);
        }
        if (order.equals("JOIN:")) {
            int matchId = Integer.parseInt(param);
            Match match = MatchManager.getInstance().getMatches().get(matchId);
            Player oppo = match.getPlayer();
            if (match.addPlayer(player)) {
                SendMessage.joinMatch(player,match);
                player.setStatus(Player.ROOM_UNREADY);
                SendMessage.newChallenger(oppo,player);
                updateClientMatchList();
            } else {
                SendMessage.matchFull(player);
            }
        }
        if(order.equals("BACK:")){
            //JOptionPane.showMessageDialog(null,"SERVER:RECIVE:BACK");
            int matchId = Integer.parseInt(param);
            Match match = MatchManager.getInstance().getMatches().get(matchId);
            match.removePlayer(player.getPlayerId());
            player.setStatus(Player.OUT_OF_ROOM);
            if(match.getPlayer()==null) {
                //JOptionPane.showMessageDialog(null, "SERVER:DETECT:MATCH EMPTY");
                MatchManager.getInstance().removeMatch(matchId);
            }
            else {
                SendMessage.ChallengerOut(match.getPlayer());
                match.getPlayer().setStatus(Player.ROOM_UNREADY);
            }
            updateClientMatchList();
        }
    }

    private static void updateClientMatchList() {
        for (Player player : PlayerManager.getInstance().getPlayers().values()) {
            SendMessage.UpdateMatchList(player);
        }
        //JOptionPane.showMessageDialog(null,"SERVER:SENT:UPML");
    }
}
