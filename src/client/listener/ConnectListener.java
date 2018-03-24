package client.listener;

import client.data.Data;
import client.frame.ClientFrame;
import client.network.CilentTread;
import client.network.Connection;
import client.network.SendMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnectListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        Data.serverIp = ClientFrame.getInstance().getTextField_serverIp().getText();
        Data.nickname = ClientFrame.getInstance().getTextField_nickname().getText();
        if (Data.nickname.contains("-") || Data.nickname.contains("&") || Data.nickname.contains(":") || Data.nickname.equals("")) {
            JOptionPane.showMessageDialog(null, "Nickname cannot be Empty or contain '-','&' or ':'.");
        } else {
            try {
                Connection.connect();
                ClientFrame clientFrame = ClientFrame.getInstance();
                clientFrame.getTextField_serverIp().setEnabled(false);
                clientFrame.getButton_connect().setEnabled(false);
                clientFrame.getButton_disconnect().setEnabled(true);
                clientFrame.getButton_refresh().setEnabled(true);
                clientFrame.getButton_createMatch().setEnabled(true);
                clientFrame.getButton_join().setEnabled(true);
                SendMessage.changeNickName(Data.nickname);
                CilentTread cilentThread = new CilentTread();
                cilentThread.start();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Server Not Found！");
                e1.printStackTrace();
            }
        }
    }
}
