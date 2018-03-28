package client.listener;

import client.frame.ClientFrame;
import client.network.SendMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String matchId = ClientFrame.getInstance().getList_matches().getSelectedValue().toString();
            matchId = matchId.split(":")[0];
            SendMessage.joinMatch(matchId);
        } catch (NullPointerException er) {
            JOptionPane.showMessageDialog(null, "You have not selected any match to join.");
        }
    }
}
