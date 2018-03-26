package client.network;

import client.data.Data;
import client.frame.ChessBoardCanvas;
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
            GameFrame.getInstance().getLabel_matchId().setText("Match ID:"+param);
            //JOptionPane.showMessageDialog(null,"DATA.MATCHID:"+Data.matchId);
        }
        if (order.equals("MCFL:")) {
            JOptionPane.showMessageDialog(null, "Match is already has two player!");
        }
        if (order.equals("JNMC:")) {
            String[] ss = param.split("-");
            Data.matchId = ss[0];
            GameFrame.getInstance().getLabel_matchId().setText("Match ID:"+ss[0]);
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
        }
        if(order.equals("NWCH:")){
            String[] ss = param.split(":");
            Data.opponentNickname = ss[0];
            Data.opponentId = ss[1];
            GameFrame.getInstance().getLabel_opponent().setText(Data.opponentNickname+"("+Data.opponentId+")");
        }
        if(order.equals("CHOT:")){
            Data.opponentNickname = "";
            Data.opponentId = "";
            GameFrame.getInstance().getLabel_opponent().setText("Waiting for join...");
            GameFrame.getInstance().getLabel_switch().setText("");
        }
        if(order.equals("OPRD:")){
            GameFrame.getInstance().getLabel_oppoReady().setText("Ready");
        }
        if(order.equals("GSTR:")){
            GameFrame gameFrame = GameFrame.getInstance();
            gameFrame.getLabel_oppoReady().setText("");
            gameFrame.getLabel_Ready().setText("");
            Data.ready = false;
            Data.started = true;
            if(param.contains(String.valueOf(Data.myId))){
                gameFrame.getLabel_switch().setText("→");
                Data.myTurn = true;
                Data.myChess = Data.BLACK;
                Data.oppoChess = Data.WHITE;
            }
            else {
                gameFrame.getLabel_switch().setText("←");
                Data.myTurn = false;
                Data.myChess = Data.WHITE;
                Data.oppoChess = Data.BLACK;
            }
            gameFrame.getButton_back().setEnabled(false);
            gameFrame.getButton_ready().setText("Ready");
            gameFrame.getButton_ready().setEnabled(false);
            gameFrame.getButton_cheki().setEnabled(true);
            gameFrame.getButton_surrender().setEnabled(true);
        }
        if(order.equals("OPUR:")){
            GameFrame.getInstance().getLabel_oppoReady().setText("");
        }
        if(order.equals("OPPL:")){
            String[] ss = param.split("-");
            int chessX = Integer.parseInt(ss[0]);
            int chessY = Integer.parseInt(ss[1]);
            Data.last = 15 * chessY + chessX;
            Data.chessBoard[chessX][ chessY] = Data.oppoChess;
            ChessBoardCanvas mapCanvas = GameFrame.getInstance().getChessBoardCanvas();
            mapCanvas.paintMapImage();
            mapCanvas.repaint();
            Data.myTurn = true;
        }
        if(order.equals("OPSR:")){
            JOptionPane.showMessageDialog(null,"You win!");
            Data.started = false;
            Data.chessBoard = new int [15][15];
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
}
