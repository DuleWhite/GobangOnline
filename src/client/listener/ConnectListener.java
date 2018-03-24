package client.listener;

import client.data.Data;
import client.frame.CilentFrame;
import client.network.CilentTread;
import client.network.Connection;
import client.network.SendMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnectListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Data.serverIp = CilentFrame.getInstance().getTextField_serverIp().getText();
        Data.nickname = CilentFrame.getInstance().getTextField_nickname().getText();
        if (Data.nickname.equals("")) {
            JOptionPane.showMessageDialog(null, "Nickname Cannot be empty!");
        } else {
            try {
                Connection.connect();
                CilentFrame.getInstance().getTextField_serverIp().setEnabled(false);
                CilentFrame.getInstance().getButton_connect().setEnabled(false);
                CilentFrame.getInstance().getButton_disconnect().setEnabled(true);
                CilentFrame.getInstance().getButton_refresh().setEnabled(true);
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
