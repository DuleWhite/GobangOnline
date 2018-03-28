package server.network;

import server.entity.ChessPosation;
import server.entity.Match;
import server.entity.Player;
import server.manager.MatchManager;
import server.manager.PlayerManager;

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
            SendMessage.yourMatchId(player, String.valueOf(match.getMatchId()));
            System.out.println("Server Sent Match ID to "+player.getPlayerId() + ":" + match.getMatchId());
        }
        if (order.equals("RFML:")) {
            SendMessage.UpdateMatchList(player);
        }
        if (order.equals("JOIN:")) {
            int matchId = Integer.parseInt(param);
            Match match = MatchManager.getInstance().getMatches().get(matchId);
            Player oppo = match.getPlayer();
            if (match.addPlayer(player)) {
                SendMessage.joinMatch(player, match);
                player.setStatus(Player.ROOM_UNREADY);
                SendMessage.newChallenger(oppo, player);
                updateClientMatchList();
            } else {
                SendMessage.matchFull(player);
            }
        }
        if (order.equals("BACK:")) {
            System.out.println("Server Recived BACK:" + param);
            //JOptionPane.showMessageDialog(null,"SERVER:RECIVE:BACK");
            int matchId = Integer.parseInt(param);
            Match match = MatchManager.getInstance().getMatches().get(matchId);
            match.removePlayer(player.getPlayerId());
            player.setStatus(Player.OUT_OF_ROOM);
            player.setChessType(0);
            if (match.getPlayer() == null) {
                //JOptionPane.showMessageDialog(null, "SERVER:DETECT:MATCH EMPTY");
                MatchManager.getInstance().removeMatch(matchId);
            } else {
                SendMessage.ChallengerOut(match.getPlayer());
                match.getPlayer().setStatus(Player.ROOM_UNREADY);
            }
            updateClientMatchList();
        }
        if (order.equals("REDY:")) {
            player.setStatus(Player.ROOM_READY);
            Match match = MatchManager.getInstance().getMatches().get(Integer.parseInt(param));
            Player oppo = match.getOppo(String.valueOf(player.getPlayerId()));
            SendMessage.oppoReady(oppo);
            if (oppo.getStatus() == Player.ROOM_READY) {
                SendMessage.gameStart(match, match.getPlayer());
                match.started = true;
                match.setTurn(match.getPlayer().getChessType());
                match.getPlayer().setStatus(Player.ROOM_INGAME);
                match.getPlayer2().setStatus(Player.ROOM_INGAME);
            }
        }
        if (order.equals("UNRD:")) {
            player.setStatus(Player.ROOM_UNREADY);
            Match match = MatchManager.getInstance().getMatches().get(Integer.parseInt(param));
            Player oppo = match.getOppo(String.valueOf(player.getPlayerId()));
            SendMessage.oppoUnready(oppo);
        }
        if (order.equals("PLAY:")) {
            String[] ss = param.split("-");
            Match match = MatchManager.getInstance().getMatches().get(Integer.parseInt(ss[0]));
            Player oppo = match.getOppo(String.valueOf(player.getPlayerId()));
            SendMessage.oppoPlay(oppo, ss[1], ss[2]);
            match.getHistory().push(new ChessPosation(player.getChessType(), Integer.parseInt(ss[1]), Integer.parseInt(ss[2])));
            match.setTurn(oppo.getChessType());
        }
        if (order.equals("SRND:")) {
            Match match = MatchManager.getInstance().getMatches().get(Integer.parseInt(param));
            Player oppo = match.getOppo(String.valueOf(player.getPlayerId()));
            SendMessage.oppoSurrender(oppo);
            match.swapPlayer();
        }
        if(order.equals("CHKI:")){
            System.out.println("Server Recived Cheki");
            Match match = MatchManager.getInstance().getMatches().get(Integer.parseInt(param));
            Player oppo = match.getOppo(String.valueOf(player.getPlayerId()));
            SendMessage.oppoRequestCheki(oppo);
        }
        if(order.equals("ALCK:")){
            System.out.println("Server Recived allow Cheki");
            Match match = MatchManager.getInstance().getMatches().get(Integer.parseInt(param));
            Player oppo = match.getOppo(String.valueOf(player.getPlayerId()));
            ChessPosation del = match.getHistory().pop();
            ChessPosation last = match.getHistory().peek();
            match.setTurn(oppo.getChessType());
            SendMessage.chekiComfirm(oppo);
            SendMessage.chekiMessage(oppo,del.toString(),last.toString());
            SendMessage.chekiMessage(player,del.toString(),last.toString());
        }
        if(order.equals("RFCK:")){
            System.out.println("Server Recived refuse Cheki");
            Match match = MatchManager.getInstance().getMatches().get(Integer.parseInt(param));
            Player oppo = match.getOppo(String.valueOf(player.getPlayerId()));
            SendMessage.chekiRefused(oppo);
        }
    }

    private static void updateClientMatchList() {
        for (Player player : PlayerManager.getInstance().getPlayers().values()) {
            SendMessage.UpdateMatchList(player);
        }
        //JOptionPane.showMessageDialog(null,"SERVER:SENT:UPML");
    }
}
