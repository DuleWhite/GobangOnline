package client.listener;

import client.data.Data;
import client.frame.GameFrame;
import client.network.SendMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadyListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!Data.ready){
            Data.ready = true;
            GameFrame gameFrame = GameFrame.getInstance();
            gameFrame.getButton_ready().setText("Unready");
            gameFrame.getLabel_Ready().setText("Ready");
            SendMessage.ready();
        }
        else{
            Data.ready=false;
            GameFrame gameFrame = GameFrame.getInstance();
            gameFrame.getButton_ready().setText("Ready");
            gameFrame.getLabel_Ready().setText("");
            SendMessage.unready();
        }
    }
}
