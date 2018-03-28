package server.network;

import server.entity.ChessPosation;
import server.entity.Match;
import server.entity.Player;
import server.manager.MatchManager;
import server.manager.PlayerManager;

import java.util.Arrays;

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
            Match match = new Match(player);
            MatchManager.getInstance().addMatch(match);
            updateClientMatchList();
            player.setStatus(Player.ROOM_UNREADY);
            SendMessage.yourMatchId(player, String.valueOf(match.getMatchId()));
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
            int matchId = Integer.parseInt(param);
            Match match = MatchManager.getInstance().getMatches().get(matchId);
            match.removePlayer(player.getPlayerId());
            player.setStatus(Player.OUT_OF_ROOM);
            player.setChessType(0);
            if (match.getPlayer() == null) {
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
            match.chessBoard[Integer.parseInt(ss[1])][Integer.parseInt(ss[2])] = player.getChessType();
            if (checkWin(Integer.parseInt(ss[1]), Integer.parseInt(ss[2]), player.getChessType(), match.chessBoard)) {
                SendMessage.youWin(player);
                SendMessage.youLose(oppo);
                match.swapPlayer();
                match.chessBoard = new int[15][15];
                match.started = false;
                match.getHistory().clear();
                match.setTurn(match.getPlayer().getChessType());
            }
            match.setTurn(oppo.getChessType());
        }
        if (order.equals("SRND:")) {
            Match match = MatchManager.getInstance().getMatches().get(Integer.parseInt(param));
            Player oppo = match.getOppo(String.valueOf(player.getPlayerId()));
            SendMessage.oppoSurrender(oppo);
            match.swapPlayer();
            match.chessBoard = new int[15][15];
            match.started = false;
            match.getHistory().clear();
            match.setTurn(match.getPlayer().getChessType());
        }
        if (order.equals("CHKI:")) {
            Match match = MatchManager.getInstance().getMatches().get(Integer.parseInt(param));
            Player oppo = match.getOppo(String.valueOf(player.getPlayerId()));
            SendMessage.oppoRequestCheki(oppo);
        }
        if (order.equals("ALCK:")) {
            Match match = MatchManager.getInstance().getMatches().get(Integer.parseInt(param));
            Player oppo = match.getOppo(String.valueOf(player.getPlayerId()));
            ChessPosation del = match.getHistory().pop();
            ChessPosation last = match.getHistory().peek();
            match.setTurn(oppo.getChessType());
            SendMessage.chekiComfirm(oppo);
            SendMessage.chekiMessage(oppo, del.toString(), last.toString());
            SendMessage.chekiMessage(player, del.toString(), last.toString());
        }
        if (order.equals("RFCK:")) {
            Match match = MatchManager.getInstance().getMatches().get(Integer.parseInt(param));
            Player oppo = match.getOppo(String.valueOf(player.getPlayerId()));
            SendMessage.chekiRefused(oppo);
        }
    }

    private static void updateClientMatchList() {
        for (Player player : PlayerManager.getInstance().getPlayers().values()) {
            SendMessage.UpdateMatchList(player);
        }
    }

    private static boolean checkWin(int x, int y, int chessType, int[][] chessBoard) {
        int winPoint[] = new int[4];

        winPoint[0] = checkX(x, y, chessType, chessBoard);
        winPoint[1] = checkY(x, y, chessType, chessBoard);
        winPoint[2] = checkM(x, y, chessType, chessBoard);
        winPoint[3] = checkN(x, y, chessType, chessBoard);

        Arrays.sort(winPoint);

        return winPoint[3] > 4;
    }

    private static int checkX(int line, int row, int chessType, int[][] chessBoard) {
        int check;
        int checkLeft = 0;
        int checkRight = 0;

        for (int i = 0; i < 5; i++) {

            if (line - i > -1) {

                if (chessBoard[line - i][row] == chessType) {

                    checkLeft++;
                } else {

                    break;
                }
            }

        }

        for (int i = 1; i < 5; i++) {

            if (line + i < 15) {

                if (chessBoard[line + i][row] == chessType) {

                    checkRight++;
                } else {
                    break;
                }
            }

        }

        check = checkLeft + checkRight;
        return (check);
    }

    private static int checkY(int line, int row, int chessType, int[][] chessBoard) {
        int check;
        int checkLeft = 0;
        int checkRight = 0;

        for (int i = 0; i < 5; i++) {

            if (row - i >= 0) {

                if (chessBoard[line][row - i] == chessType) {

                    checkLeft++;
                } else {

                    break;
                }
            }

        }

        for (int i = 1; i < 5; i++) {

            if (row + i < 15) {

                if (chessBoard[line][row + i] == chessType) {

                    checkRight++;
                } else {
                    break;
                }
            }

        }
        check = checkLeft + checkRight;
        return (check);
    }

    private static int checkN(int line, int row, int chessType, int[][] chessBoard) {
        int check;
        int checkLeft = 0;
        int checkRight = 0;

        for (int i = 0; i < 5; i++) {

            if ((line - i > -1) && (row - i > -1)) {

                if (chessBoard[line - i][row - i] == chessType) {

                    checkLeft++;
                } else {

                    break;
                }
            }

        }

        for (int i = 1; i < 5; i++) {

            if ((line + i < 15) && (row + i < 15)) {

                if (chessBoard[line + i][row + i] == chessType) {

                    checkRight++;
                } else {
                    break;
                }
            }

        }

        check = checkLeft + checkRight;
        return (check);
    }

    private static int checkM(int line, int row, int chessType, int[][] chessBoard) {
        int check;
        int checkLeft = 0;
        int checkRight = 0;

        for (int i = 0; i < 5; i++) {

            if ((line - i > -1) && (row + i < 15)) {

                if (chessBoard[line - i][row + i] == chessType) {

                    checkLeft++;
                } else {

                    break;
                }
            }

        }

        for (int i = 1; i < 5; i++) {

            if ((line + i < 15) && (row - i > -1)) {

                if (chessBoard[line + i][row - i] == chessType) {

                    checkRight++;
                } else {
                    break;
                }
            }

        }

        check = checkLeft + checkRight;
        return (check);
    }
}
