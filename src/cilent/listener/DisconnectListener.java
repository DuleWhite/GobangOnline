package cilent.listener;

import cilent.data.Data;
import cilent.frame.CilentFrame;
import cilent.network.Connection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DisconnectListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Connection.disconnect();
            Data.connected = false;
            CilentFrame.getInstance().getTextField_serverIp().setEnabled(true);
            CilentFrame.getInstance().getButton_connect().setEnabled(true);
            CilentFrame.getInstance().getButton_disconnect().setEnabled(false);
            CilentFrame.getInstance().getButton_refresh().setEnabled(false);
            CilentFrame.getInstance().getLabel_myId2().setText("");
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Disconnect Faild!");
            e1.printStackTrace();
        }
    }
}
