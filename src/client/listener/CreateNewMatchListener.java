package client.listener;

import client.frame.CilentFrame;
import client.frame.GameFrame;
import client.network.SendMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewMatchListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        SendMessage.createMatch();
        CilentFrame.getInstance().hideFrame();
        GameFrame.getInstance().launchFrame();
    }
}
