package client.network;

import client.data.Data;
import client.manager.IOManager;

public class SendMessage {
    public static void changeNickName(String nickname) {
        IOManager.getInstance().getPs().println("NICK:" + nickname);
    }

    public static void disconnect() {
        IOManager.getInstance().getPs().println("DSCN:");
    }

    public static void createMatch() {
        IOManager.getInstance().getPs().println("CRNM:");
    }

    public static void refreshMatchList() {
        IOManager.getInstance().getPs().println("RFML:");
    }

    public static void joinMatch(String matchId) {
        IOManager.getInstance().getPs().println("JOIN:" + matchId);
    }

    public static void backToMatchList() {
        IOManager.getInstance().getPs().println("BACK:" + Data.matchId);
    }

    public static void ready() {
        IOManager.getInstance().getPs().println("REDY:" + Data.matchId);
    }

    public static void unready() {
        IOManager.getInstance().getPs().println("UNRD:" + Data.matchId);
    }

    public static void playChess(int chessX, int chessY) {
        String temp = "PLAY:" +
                String.valueOf(Data.matchId) +
                "-" +
                chessX +
                "-" +
                chessY;
        IOManager.getInstance().getPs().println(temp);
    }

    public static void surrender() {
        IOManager.getInstance().getPs().println("SRND:" + Data.matchId);
    }

    public static void cheki() {
        IOManager.getInstance().getPs().println("CHKI:" + Data.matchId);
    }

    public static void allowCheki() {
        IOManager.getInstance().getPs().println("ALCK:" + Data.matchId);
    }

    public static void refuseCkeki() {
        IOManager.getInstance().getPs().println("RFCK:" + Data.matchId);
    }
}
