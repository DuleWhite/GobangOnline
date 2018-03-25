package client.listener;

import client.network.SendMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        SendMessage.refreshMatchList();
    }
}
