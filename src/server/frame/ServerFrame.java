package server.frame;

import client.frame.ClientFrame;
import server.data.Data;
import server.network.SendMessage;
import server.network.ServerThread;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFrame extends JFrame {
    private static ServerFrame instance = null;

    private JList list_players;
    private DefaultListModel model_players;
    private JList list_matches;
    private DefaultListModel model_matches;

    private ServerFrame() {
        super("Gobang Online [ Server ]");
        JScrollPane scrollPane_players = new JScrollPane();
        scrollPane_players.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_players.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_players.setViewportView(this.getList_players());
        JScrollPane scrollPane_matches = new JScrollPane();
        scrollPane_matches.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_matches.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_matches.setViewportView(this.getList_matches());
        JPanel panel_players = new JPanel(new BorderLayout());
        panel_players.setBorder(new TitledBorder(new EtchedBorder(), "Players List", TitledBorder.CENTER, TitledBorder.TOP));
        panel_players.add(scrollPane_players);
        JPanel panel_matches = new JPanel(new BorderLayout());
        panel_matches.setBorder(new TitledBorder(new EtchedBorder(), "Match List", TitledBorder.CENTER, TitledBorder.TOP));
        panel_matches.add(scrollPane_matches);
        this.setLayout(new BorderLayout());
        this.add(panel_players, BorderLayout.WEST);
        this.add(panel_matches, BorderLayout.EAST);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                SendMessage.serverOffline();
                System.exit(0);
            }
        });


    }

    public static ServerFrame getInstance() {
        if (instance == null) {
            instance = new ServerFrame();
        }
        return instance;
    }

    public JList getList_players() {
        if (list_players == null) {
            list_players = new JList(getModel_players());
            list_players.setFixedCellWidth(200);
            list_players.setVisibleRowCount(15);
        }
        return list_players;
    }

    public JList getList_matches() {
        if (list_matches == null) {
            list_matches = new JList(getModel_matches());
            list_matches.setFixedCellWidth(200);
            list_matches.setVisibleRowCount(15);
        }
        return list_matches;
    }

    public DefaultListModel getModel_players() {
        if (model_players == null) {
            model_players = new DefaultListModel();
        }
        return model_players;
    }

    public DefaultListModel getModel_matches() {
        if (model_matches == null) {
            model_matches = new DefaultListModel();
        }
        return model_matches;
    }

    public void lunchFrame() {
        this.setVisible(true);
        //启用服务器端口
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(Data.port);
            System.out.println("Server Start！");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(ClientFrame.getInstance(), "Port " + Data.port + " used !");
            e.printStackTrace();
            System.exit(0);
        }
        while (true) {
            Socket s;
            try {
                s = ss.accept();
                ServerThread serverThread = new ServerThread(s);
                serverThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
