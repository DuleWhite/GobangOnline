package client.listener;

import client.data.Data;
import client.frame.CilentFrame;
import client.network.SendMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeNicknameListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Data.nickname = CilentFrame.getInstance().getTextField_nickname().getText();
        if (Data.connected) {
            SendMessage.changeNickName(Data.nickname);
        }
        JOptionPane.showMessageDialog(null, "Change Nickname success!");
    }
}
