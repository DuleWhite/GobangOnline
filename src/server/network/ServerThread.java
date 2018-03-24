package server.network;

import server.entity.Player;
import server.manager.PlayerManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class ServerThread extends Thread {

    private Player player;
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
        player = new Player(this.hashCode(), socket);
        PlayerManager.getInstance().addPlayer(this.hashCode(), player);
        SendMessage.yourId(this.hashCode());
        SendMessage.UpdateMatchList(player);
    }


    public void run() {
        String info;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        while (player != null && player.connected) {
            try {
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                info = br.readLine();
                ProcessRecivedMessage.process(player, info);
            } catch (IOException e) {
                player.connected = false;
                e.printStackTrace();
            }
        }
        try {
            if (is != null) {
                is.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (br != null) {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
