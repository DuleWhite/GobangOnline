package cilent.listener;

import cilent.network.SendMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewMatchListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        SendMessage.createMatch();
        JOptionPane.showMessageDialog(null, "Create Success!");
    }
}
