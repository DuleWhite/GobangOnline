package client.listener;

import client.data.Data;
import client.frame.ChessBoardCanvas;
import client.frame.GameFrame;
import client.network.SendMessage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ChessBoardListener extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
        ChessBoardCanvas canvas = (ChessBoardCanvas) e.getSource();
        if (Data.started) {
            if (Data.myTurn) {
                if (e.getX() < canvas.getMapWidth() - 6 && e.getY() < canvas.getMapHeight() - 7) {
                    int chessX;
                    int chessY;
                    chessX = e.getX() / 35;
                    chessY = e.getY() / 35;
                    Data.last = 15 * chessY + chessX;
                    if (Data.chessBoard[chessX][chessY] == 0) {
                        Data.chessBoard[chessX][chessY] = Data.myChess;
                        ChessBoardCanvas mapCanvas = GameFrame.getInstance().getChessBoardCanvas();
                        mapCanvas.paintMapImage();
                        mapCanvas.repaint();
                        Data.myTurn = false;
                        GameFrame.getInstance().getButton_cheki().setEnabled(true);
                        GameFrame.getInstance().getLabel_switch().setText("←");
                        SendMessage.playChess(chessX, chessY);
                    }
                }
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }

    public void mouseEntered(MouseEvent e) {
        ChessBoardCanvas canvas = (ChessBoardCanvas) e.getSource();

        if (!Data.myTurn) {

            canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            canvas.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        }
    }


    public void mouseExited(MouseEvent e) {
        ChessBoardCanvas canvas = (ChessBoardCanvas) e.getSource();
        canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
