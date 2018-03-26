package client.network;

import client.data.Data;
import client.manager.IOManager;

import javax.swing.*;

public class SendMessage {
    public static void changeNickName(String nickname) {
        IOManager.getInstance().getPs().println("NICK:" + nickname);
    }

    public static void disconnect() {
        IOManager.getInstance().getPs().println("DSCN:");
        //JOptionPane.showMessageDialog(null,"CLIENT:SENT:DSCN");
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

    public static void backToMatchList(){
        IOManager.getInstance().getPs().println("BACK:" + Data.matchId);
        //JOptionPane.showMessageDialog(null,"CLIENT:SENT:BACK"+Data.matchId);
    }
}
