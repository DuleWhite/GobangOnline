package client.network;

import client.data.Data;
import client.frame.ClientFrame;
import client.frame.GameFrame;

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
            //JOptionPane.showMessageDialog(null,"CLIENT:RECIVED:UPML");
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
        if(order.equals("MCID:")){
            //JOptionPane.showMessageDialog(null,"CLIENT:RECIVED:MCID"+param);
            Data.matchId = param;
            //JOptionPane.showMessageDialog(null,"DATA.MATCHID:"+Data.matchId);
        }
        if (order.equals("MCFL:")) {
            JOptionPane.showMessageDialog(null, "Match is already has two player!");
        }
        if (order.equals("JNMC:")) {
            String[] ss = param.split("-");
            Data.matchId = ss[0];
            if(ss[1].contains(String.valueOf(Data.myId))){
                Data.opponentNickname = ss[2].split(":")[0];
                Data.opponentId = ss[2].split(":")[1];
            }
            else{
                Data.opponentNickname = ss[1].split(":")[0];
                Data.opponentId = ss[1].split(":")[1];
            }
            ClientFrame.getInstance().hideFrame();
            GameFrame.getInstance().launchFrame();
            GameFrame.getInstance().getLabel_opponent().setText(Data.opponentNickname+"("+Data.opponentId+")");
            GameFrame.getInstance().getLabel_switch().setText("←");
        }
        if(order.equals("NWCH:")){
            String[] ss = param.split(":");
            Data.opponentNickname = ss[0];
            Data.opponentId = ss[1];
            GameFrame.getInstance().getLabel_opponent().setText(Data.opponentNickname+"("+Data.opponentId+")");
            GameFrame.getInstance().getLabel_switch().setText("→");
        }
        if(order.equals("CHOT:")){
            Data.opponentNickname = "";
            Data.opponentId = "";
            GameFrame.getInstance().getLabel_opponent().setText("Waiting for join...");
            GameFrame.getInstance().getLabel_switch().setText("");
        }
    }
}
