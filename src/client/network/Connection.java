package client.network;

import client.data.Data;
import client.manager.IOManager;

import java.io.IOException;
import java.net.Socket;

public class Connection {
    public static void connect() throws IOException {
        Data.socket = new Socket(Data.serverIp, Data.port);
        Data.connected = true;
        IOManager.getInstance().setOs(Data.socket.getOutputStream());
        IOManager.getInstance().setIs(Data.socket.getInputStream());
        IOManager.getInstance().getPs().println("NICK:" + Data.nickname);
    }

    public static void disconnect() throws IOException {
        SendMessage.disconnect();
    }
}
