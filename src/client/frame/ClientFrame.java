package client.frame;

import client.data.Data;
import client.listener.*;
import client.network.Connection;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFrame extends JFrame {
    private static ClientFrame instance = null;
    private JTextField textField_serverIp;
    private JTextField textField_nickname;
    private JList list_matches = null;
    private DefaultListModel model = null;
    private JButton button_connect;
    private JButton button_disconnect;
    private JLabel label_myId2;
    private JButton button_refresh;
    private JButton button_createMatch;
    private JButton button_join;

    private ClientFrame() {
        super("Gobang Online");
        JLabel label_serverIp = new JLabel("Server IP : ");
        JLabel label_nickName = new JLabel("Nickname : ");
        textField_serverIp = new JTextField(10);
        textField_nickname = new JTextField(10);
        button_connect = new JButton("Connect");
        button_connect.addActionListener(new ConnectListener());
        button_disconnect = new JButton("Disconnect");
        button_disconnect.setEnabled(false);
        button_disconnect.addActionListener(new DisconnectListener());
        JButton button_changeNickname = new JButton("Change");
        button_changeNickname.addActionListener(new ChangeNicknameListener());
        button_refresh = new JButton("Refresh");
        button_refresh.setEnabled(false);
        button_refresh.addActionListener(new RefreshListener());
        JLabel label_myId = new JLabel("My ID: ");
        label_myId2 = new JLabel("");
        JScrollPane scrollPane_matches = new JScrollPane();
        scrollPane_matches.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_matches.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_matches.setViewportView(this.getList_matches());
        this.add(scrollPane_matches, BorderLayout.CENTER);
        button_createMatch = new JButton("Create new match");
        button_createMatch.setEnabled(false);
        button_createMatch.addActionListener(new CreateNewMatchListener());
        button_join = new JButton("Join");
        button_join.setEnabled(false);
        button_join.addActionListener(new JoinListener());

        JPanel top = new JPanel(new GridLayout(3, 4, 0, 0));
        top.add(label_serverIp);
        top.add(textField_serverIp);
        top.add(button_connect);
        top.add(button_disconnect);
        top.add(label_nickName);
        top.add(textField_nickname);
        top.add(button_changeNickname);
        top.add(button_refresh);
        top.add(label_myId);
        top.add(label_myId2);
        JPanel mid = new JPanel(new BorderLayout());
        mid.setBorder(new TitledBorder(new EtchedBorder(), "Match List", TitledBorder.CENTER, TitledBorder.TOP));
        mid.add(scrollPane_matches);
        JPanel foot = new JPanel(new GridLayout(1, 2, 0, 20));
        foot.add(button_createMatch);
        foot.add(button_join);
        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(mid, BorderLayout.CENTER);
        this.add(foot, BorderLayout.SOUTH);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (Data.connected) {
                    Connection.disconnect();
                }
                System.exit(0);
            }
        });
        this.setResizable(false);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width-500) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height-400) / 2;
        this.setLocation(w,h);
    }

    public static ClientFrame getInstance() {
        if (instance == null) {
            instance = new ClientFrame();
        }
        return instance;
    }

    public JButton getButton_createMatch() {
        return button_createMatch;
    }

    public JButton getButton_join() {
        return button_join;
    }

    public JList getList_matches() {
        if (list_matches == null) {
            list_matches = new JList(getModel());
            list_matches.setFixedCellWidth(200);
            list_matches.setVisibleRowCount(15);
        }
        return list_matches;
    }

    public DefaultListModel getModel() {
        if (model == null) {
            model = new DefaultListModel();
        }
        return model;
    }

    public JTextField getTextField_serverIp() {
        return textField_serverIp;
    }

    public JTextField getTextField_nickname() {
        return textField_nickname;
    }

    public JButton getButton_connect() {
        return button_connect;
    }

    public JButton getButton_disconnect() {
        return button_disconnect;
    }

    public JButton getButton_refresh() {
        return button_refresh;
    }

    public JLabel getLabel_myId2() {
        return label_myId2;
    }

    public void lunchFrame() {
        this.setVisible(true);
    }

    public void hideFrame() {
        this.setVisible(false);
    }

}
