package client.listener;

import client.data.Data;
import client.frame.ClientFrame;
import client.network.Connection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DisconnectListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        try {
            Connection.disconnect();
            Data.connected = false;
            ClientFrame clientFrame = ClientFrame.getInstance();
            clientFrame.getTextField_serverIp().setEnabled(true);
            clientFrame.getButton_connect().setEnabled(true);
            clientFrame.getButton_disconnect().setEnabled(false);
            clientFrame.getButton_refresh().setEnabled(false);
            clientFrame.getButton_createMatch().setEnabled(false);
            clientFrame.getButton_join().setEnabled(false);
            ClientFrame.getInstance().getLabel_myId2().setText("");
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Disconnect Faild!");
            e1.printStackTrace();
        }
    }
}
