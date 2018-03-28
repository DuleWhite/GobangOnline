package client.frame;

import client.data.Data;
import client.listener.BackListener;
import client.listener.ChekiListener;
import client.listener.ReadyListener;
import client.listener.SurrenderListener;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static GameFrame instance = null;
    private JLabel label_opponent;
    private JLabel label_switch;
    private JLabel label_oppoReady;
    private JLabel label_Ready;
    private JLabel label_matchId;
    private ChessBoardCanvas chessBoardCanvas;
    private JButton button_cheki;
    private JButton button_surrender;
    private JButton button_ready;
    private JButton button_back;

    private GameFrame() {
        super("Gobang Online");
        label_opponent = new JLabel("Waiting for join...", JLabel.RIGHT);
        label_switch = new JLabel("", JLabel.CENTER);
        JLabel label_me = new JLabel(Data.nickname + "(" + Data.myId + ")", JLabel.LEFT);
        label_oppoReady = new JLabel("", JLabel.RIGHT);
        label_matchId = new JLabel("Match ID:", JLabel.CENTER);
        label_Ready = new JLabel("", JLabel.LEFT);
        chessBoardCanvas = new ChessBoardCanvas();
        button_back = new JButton("Back");
        button_back.addActionListener(new BackListener());
        button_cheki = new JButton("Cheki");
        button_cheki.setEnabled(false);
        button_cheki.addActionListener(new ChekiListener());
        button_surrender = new JButton("Surrender");
        button_surrender.setEnabled(false);
        button_surrender.addActionListener(new SurrenderListener());
        button_ready = new JButton("Ready");
        button_ready.addActionListener(new ReadyListener());

        JPanel top = new JPanel(new GridLayout(2, 3, 0, 0));
        top.add(label_opponent);
        top.add(label_switch);
        top.add(label_me);
        top.add(label_oppoReady);
        top.add(label_matchId);
        top.add(label_Ready);
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
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        int w = ClientFrame.getInstance().getLocation().x-50;
        int h = ClientFrame.getInstance().getLocation().y-100;
        this.setLocation(w,h);
    }

    public static GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame();
        }
        return instance;
    }

    public JLabel getLabel_matchId() {
        return label_matchId;
    }

    public ChessBoardCanvas getChessBoardCanvas() {
        return chessBoardCanvas;
    }

    public JLabel getLabel_oppoReady() {
        return label_oppoReady;
    }

    public JLabel getLabel_Ready() {
        return label_Ready;
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
        label_Ready.setText("");
        chessBoardCanvas.repaint();
        button_cheki.setEnabled(false);
        button_surrender.setEnabled(false);
        button_ready.setText("Ready");
        int w = ClientFrame.getInstance().getLocation().x-50;
        int h = ClientFrame.getInstance().getLocation().y-100;
        this.setLocation(w,h);
        this.setVisible(true);
    }

    public void hideFrame() {
        this.setVisible(false);
    }
}
