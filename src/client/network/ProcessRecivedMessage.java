package client.network;

import client.data.Data;
import client.frame.ClientFrame;

import javax.swing.*;

public class ProcessRecivedMessage {
    public static void process(String info) {
        String order = info.substring(0, 5);
        String param = info.substring(5);
        if (order.equals("YRID:")) {
            Data.myId = Integer.parseInt(param);
            ClientFrame.getInstance().getLabel_myId2().setText(String.valueOf(Data.myId));
        }
        if (order.equals("UPML:")) {
            ClientFrame.getInstance().getModel().clear();
            String[] ss = param.split("&");
            for (String s : ss) {
                ClientFrame.getInstance().getModel().addElement(s);
            }
            ClientFrame.getInstance().getList_matches().repaint();
        }
        if (order.equals("OFLN:")) {
            JOptionPane.showMessageDialog(null, "Server Offline!");
            System.exit(0);
        }
        if (order.equals("MCFL:")) {
            JOptionPane.showMessageDialog(null, "Match is already has two player!");
        }
        if (order.equals("JNMC:")) {
            //TODO:
        }
    }
}
