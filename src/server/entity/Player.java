package server.entity;

import java.net.Socket;

public class Player {
    public static final int OUT_OF_ROOM = 0xa01;
    public static final int ROOM_UNREADY = 0xb01;
    public static final int ROOM_READY = 0xc01;
    public static final int ROOM_INGAME = 0xd01;
    public boolean connected;
    private Socket socket;
    private int playerId;
    private String nickname = "";
    private int status;
    private int chessType = 0;

    public Player(int playerId, Socket socket) {
        this.playerId = playerId;
        this.socket = socket;
        this.connected = true;
        this.status = OUT_OF_ROOM;
    }

    public int getChessType() {
        return chessType;
    }

    public void setChessType(int chessType) {
        this.chessType = chessType;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if (status == OUT_OF_ROOM || status == ROOM_UNREADY || status == ROOM_READY || status == ROOM_INGAME)
            this.status = status;
    }
}
