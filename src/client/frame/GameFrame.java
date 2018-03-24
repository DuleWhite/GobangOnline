package client.frame;

import client.data.Data;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static GameFrame instance = null;
    private JLabel label_opponent;
    private JLabel label_switch;
    private JButton button_cheki;
    private JButton button_surrender;
    private JButton button_ready;
    private JButton button_back;

    private GameFrame() {
        label_opponent = new JLabel("waiting for join...",JLabel.CENTER);
        label_switch = new JLabel("→",JLabel.CENTER);
        JLabel label_me = new JLabel(Data.nickname + "(" + Data.myId + ")",JLabel.CENTER);
        ChessBoardCanvas chessBoardCanvas = new ChessBoardCanvas();
        button_back = new JButton("Back");
        button_cheki = new JButton("Cheki");
        button_surrender = new JButton("Surrender");
        button_ready = new JButton("Ready");

        JPanel top = new JPanel(new GridLayout(1,3,0,0));
        top.add(label_opponent);
        top.add(label_switch);
        top.add(label_me);
        JPanel foot = new JPanel(new GridLayout(1, 4, 0, 0));
        foot.add(button_back);
        foot.add(button_cheki);
        foot.add(button_surrender);
        foot.add(button_ready);
        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(chessBoardCanvas);
        this.add(foot, BorderLayout.SOUTH);
        this.pack();
    }

    public static GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame();
        }
        return instance;
    }

    public JLabel getLabel_opponent() {
        return label_opponent;
    }

    public JLabel getLabel_switch() {
        return label_switch;
    }

    public JButton getButton_cheki() {
        return button_cheki;
    }

    public JButton getButton_surrender() {
        return button_surrender;
    }

    public JButton getButton_ready() {
        return button_ready;
    }

    public JButton getButton_back() {
        return button_back;
    }

    public void launchFrame() {
        this.setVisible(true);
    }

    public void hideFrame() {
        this.setVisible(false);
    }
}