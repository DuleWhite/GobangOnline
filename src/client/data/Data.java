package client.data;

import java.net.Socket;

public class Data {
    public static final int port = 7470;
    //InGame
    public static final int BLACK = 1;
    public static final int WHITE = -1;
    //Connection
    public static String serverIp = "";
    public static Socket socket = null;
    public static String nickname = "";
    public static int myId = 0;
    public static boolean connected = false;
    //InRoom
    public static String matchId = "";
    public static boolean ready = false;
    public static String opponentId = "";
    public static String opponentNickname = "";
    public static boolean started = false;
    public static boolean myTurn = false;
    public static int myChess = 0;
    public static int oppoChess = 0;
    public static int last = -1;
    public static int[][] chessBoard = new int[15][15];

    public static void resetRoomStatus() {
        ready = false;
        started = false;
        myTurn = false;
        myChess = 0;
        oppoChess = 0;
        last = -1;
        chessBoard = new int[15][15];
    }
}
