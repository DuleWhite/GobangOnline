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
        if (order.equals("MCID:")) {
            //JOptionPane.showMessageDialog(null,"CLIENT:RECIVED:MCID"+param);
            System.out.println("Client (" + Data.myId + ") Recived Match ID : " + param);
            Data.matchId = param;
            System.out.println("Data.matchId set : " + Data.matchId );
            String temp = "Match ID:" + param;
            System.out.println("temp:"+temp);
            GameFrame.getInstance().getLabel_matchId().setText(temp);
            GameFrame.getInstance().repaint();
            System.out.println("GameFrame MatchID set : " + GameFrame.getInstance().getLabel_matchId().getText());
            //JOptionPane.showMessageDialog(null,"DATA.MATCHID:"+Data.matchId);
        }
        if (order.equals("MCFL:")) {
            JOptionPane.showMessageDialog(null, "Match is already has two player!");
        }
        if (order.equals("JNMC:")) {
            Data.resetRoomStatus();
            String[] ss = param.split("-");
            Data.matchId = ss[0];
            GameFrame.getInstance().getLabel_matchId().setText("Match ID:" + ss[0]);
            if (ss[1].contains(String.valueOf(Data.myId))) {
                Data.opponentNickname = ss[2].split(":")[0];
                Data.opponentId = ss[2].split(":")[1];
            } else {
                Data.opponentNickname = ss[1].split(":")[0];
                Data.opponentId = ss[1].split(":")[1];
            }
            ClientFrame.getInstance().hideFrame();
            GameFrame.getInstance().launchFrame();
            GameFrame.getInstance().getLabel_opponent().setText(Data.opponentNickname + "(" + Data.opponentId + ")");
        }
        if (order.equals("NWCH:")) {
            System.out.println("Client Recived NWCH:"+param);
            String[] ss = param.split(":");
            Data.opponentNickname = ss[0];
            Data.opponentId = ss[1];
            GameFrame.getInstance().getLabel_opponent().setText(Data.opponentNickname + "(" + Data.opponentId + ")");
        }
        if (order.equals("CHOT:")) {
            Data.opponentNickname = "";
            Data.opponentId = "";
            GameFrame.getInstance().getLabel_opponent().setText("Waiting for join...");
            GameFrame.getInstance().getLabel_oppoReady().setText("");
            GameFrame.getInstance().getLabel_switch().setText("");
        }
        if (order.equals("OPRD:")) {
            GameFrame.getInstance().getLabel_oppoReady().setText("Ready");
        }
        if (order.equals("GSTR:")) {
            GameFrame gameFrame = GameFrame.getInstance();
            gameFrame.getLabel_oppoReady().setText("");
            gameFrame.getLabel_Ready().setText("");
            Data.ready = false;
            Data.started = true;
            if (param.contains(String.valueOf(Data.myId))) {
                gameFrame.getLabel_switch().setText("→");
                Data.myTurn = true;
                Data.myChess = Data.BLACK;
                Data.oppoChess = Data.WHITE;
            } else {
                gameFrame.getLabel_switch().setText("←");
                Data.myTurn = false;
                Data.myChess = Data.WHITE;
                Data.oppoChess = Data.BLACK;
            }
            gameFrame.getButton_back().setEnabled(false);
            gameFrame.getButton_ready().setText("Ready");
            gameFrame.getButton_ready().setEnabled(false);
            gameFrame.getButton_surrender().setEnabled(true);
        }
        if (order.equals("OPUR:")) {
            GameFrame.getInstance().getLabel_oppoReady().setText("");
        }
        if (order.equals("OPPL:")) {
            String[] ss = param.split("-");
            int chessX = Integer.parseInt(ss[0]);
            int chessY = Integer.parseInt(ss[1]);
            Data.last = 15 * chessY + chessX;
            Data.chessBoard[chessX][chessY] = Data.oppoChess;
            ChessBoardCanvas mapCanvas = GameFrame.getInstance().getChessBoardCanvas();
            mapCanvas.paintMapImage();
            mapCanvas.repaint();
            Data.myTurn = true;
            GameFrame.getInstance().getLabel_switch().setText("→");
            GameFrame.getInstance().getButton_cheki().setEnabled(false);
        }
        if (order.equals("OPSR:")) {
            JOptionPane.showMessageDialog(null, "You win!");
            Data.resetRoomStatus();
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
        if (order.equals("OPCK:")) {
            int value = JOptionPane.showConfirmDialog(null, "Your opponent request cheki, Allow ?", "Cheki Request", JOptionPane.YES_NO_OPTION);
            if (value == JOptionPane.YES_OPTION) {
                SendMessage.allowCheki();
                Data.myTurn = false;
            } else {
                SendMessage.refuseCkeki();
            }
        }
        if(order.equals("CKCO:")){
            System.out.println("Client Recived CKCO:");
            Data.myTurn = true;
        }
        if (order.equals("CKMS:")) {
            System.out.println("Client Recived CKMS:");
            String[] ss = param.split("-");
            int delx = Integer.parseInt(ss[0].split(",")[0]);
            int dely = Integer.parseInt(ss[0].split(",")[1]);
            int lastx = Integer.parseInt(ss[1].split(",")[0]);
            int lasty = Integer.parseInt(ss[1].split(",")[1]);
            Data.chessBoard[delx][dely] = 0;
            Data.last = 15 * lasty + lastx;
            ChessBoardCanvas mapCanvas = GameFrame.getInstance().getChessBoardCanvas();
            mapCanvas.paintMapImage();
            mapCanvas.repaint();
        }
        if(order.equals("CKRF:")){
            System.out.println("Client Recived CKRF:");
            JOptionPane.showMessageDialog(null,"Opponent Do not allow your Cheki!");
        }
    }
}
