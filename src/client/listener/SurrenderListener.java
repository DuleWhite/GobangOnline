package client.listener;

import client.data.Data;
import client.frame.ChessBoardCanvas;
import client.frame.GameFrame;
import client.network.SendMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurrenderListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        SendMessage.surrender();
        JOptionPane.showMessageDialog(null, "You lose!");
        Data.last = -1;
        Data.started = false;
        Data.chessBoard = new int[15][15];
        ChessBoardCanvas mapCanvas = GameFrame.getInstance().getChessBoardCanvas();
        mapCanvas.paintMapImage();
        mapCanvas.repaint();
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.getButton_back().setEnabled(true);
        gameFrame.getButton_cheki().setEnabled(false);
        gameFrame.getButton_surrender().setEnabled(false);
        gameFrame.getButton_ready().setEnabled(true);
        gameFrame.getLabel_switch().setText("");
    }
}
