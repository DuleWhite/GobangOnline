package client.listener;

import client.data.Data;
import client.frame.ClientFrame;
import client.frame.GameFrame;
import client.network.SendMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        SendMessage.backToMatchList();
        Data.opponentNickname = "";
        Data.opponentId = "";
        Data.matchId = "";
        GameFrame.getInstance().hideFrame();
        ClientFrame.getInstance().lunchFrame();
    }
}
