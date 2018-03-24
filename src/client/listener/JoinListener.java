package client.listener;

import client.frame.ClientFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String matchId = ClientFrame.getInstance().getList_matches().getSelectedValue().toString();
        matchId = matchId.split(":")[0];
        //JOptionPane.showMessageDialog(null,matchId);

    }
}
