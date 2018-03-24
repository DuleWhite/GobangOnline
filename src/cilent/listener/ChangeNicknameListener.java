package cilent.listener;

import cilent.data.Data;
import cilent.frame.CilentFrame;
import cilent.network.SendMessage;

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
