package server.network;

import server.entity.Match;
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
            Send(player, "OFLN:");
        }
    }

    public static void matchFull(Player player) {
        Send(player, "MCFL:");
    }

    public static void joinMatch(Player player, Match match) {
        Send(player, "JNMC:" + match.toString2());
    }

    public static void newChallenger(Player player1, Player player2) {
        Send(player1, "NWCH:" + player2.getNickname() + ":" + player2.getPlayerId());
    }

    public static void yourMatchId(Player player, String matchId) {
        Send(player, "MCID:" + matchId);
    }

    public static void ChallengerOut(Player player) {
        Send(player, "CHOT:");
    }

    public static void oppoReady(Player player) {
        Send(player, "OPRD:");
    }

    public static void gameStart(Match match, Player player) {
        Send(match.getPlayer(), "GSTR:" + player.getPlayerId());
        Send(match.getPlayer2(), "GSTR:" + player.getPlayerId());
    }

    public static void oppoUnready(Player player) {
        Send(player, "OPUR:");
    }

    public static void oppoPlay(Player player, String chessX, String chessY) {
        Send(player, "OPPL:" + chessX + "-" + chessY);
    }

    public static void oppoSurrender(Player player) {
        Send(player, "OPSR:");
    }

    public static void oppoRequestCheki(Player player) {
        Send(player, "OPCK:");
    }

    public static void chekiComfirm(Player player) {
        Send(player, "CKCO:");
    }

    public static void chekiMessage(Player player, String del, String last) {
        Send(player, "CKMS:" + del + "-" + last);
    }

    public static void chekiRefused(Player player) {
        Send(player, "CKRF:");
    }

    public static void youWin(Player player) {
        Send(player, "UWIN:");
    }

    public static void youLose(Player player) {
        Send(player, "ULOS:");
    }
}
