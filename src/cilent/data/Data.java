package cilent.data;

import java.net.Socket;

public class Data {
    public static final int port = 7470;
    public static String nickname = "";
    public static int myId = 0;
    public static String opponentId = "";
    public static String matchId = "";
    public static boolean ready = false;
    public static String serverIp = "";
    public static Socket socket = null;
    public static boolean connected = false;
}
