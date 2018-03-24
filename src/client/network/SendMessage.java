package client.network;

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
}
