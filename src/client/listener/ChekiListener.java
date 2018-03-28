package client.listener;

import client.frame.GameFrame;
import client.network.SendMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChekiListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        SendMessage.cheki();
        GameFrame.getInstance().getButton_cheki().setEnabled(false);
    }
}
