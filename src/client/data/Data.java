package client.data;

import java.net.Socket;

public class Data {
    public static final int port = 7470;
    public static final int BLACK = 1;
    public static final int WHITE = -1;
    public static int last = -1;
    public static String nickname = "";
    public static int myId = 0;
    public static String opponentId = "";
    public static String opponentNickname = "";
    public static String matchId = "";
    public static boolean ready = false;
    public static boolean started = false;
    public static boolean myTurn = false;
    public static int myChess = 0;
    public static int oppoChess = 0;
    public static String serverIp = "";
    public static Socket socket = null;
    public static boolean connected = false;
    public static int[][] chessBoard = new int[15][15];
}
