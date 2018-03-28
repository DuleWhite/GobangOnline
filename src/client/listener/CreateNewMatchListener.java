package client.listener;

import client.data.Data;
import client.frame.ClientFrame;
import client.frame.GameFrame;
import client.network.SendMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewMatchListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        Data.resetRoomStatus();
        Data.opponentNickname = "";
        ClientFrame.getInstance().hideFrame();
        GameFrame.getInstance().launchFrame();
        SendMessage.createMatch();
    }
}
