package client.listener;

import client.data.Data;
import client.frame.ClientFrame;
import client.network.SendMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeNicknameListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        String newNickname = ClientFrame.getInstance().getTextField_nickname().getText();
        if (!Data.nickname.equals(newNickname)) {
            if (!(newNickname.contains("-") || newNickname.contains("&") || newNickname.contains(":") || newNickname.equals(""))) {
                Data.nickname = newNickname;
                if (Data.connected) {
                    SendMessage.changeNickName(Data.nickname);
                }
                JOptionPane.showMessageDialog(ClientFrame.getInstance(), "Change Nickname success!");
            } else
                JOptionPane.showMessageDialog(ClientFrame.getInstance(), "Nickname cannot be Empty or contain '-','&' or ':'.");
        }
    }
}
