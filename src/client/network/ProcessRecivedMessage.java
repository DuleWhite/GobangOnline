package client.network;

import client.data.Data;
import client.frame.CilentFrame;

public class ProcessRecivedMessage {
    public static void process(String info) {
        if (info.substring(0, 5).equals("YRID:")) {
            Data.myId = Integer.parseInt(info.substring(5));
            CilentFrame.getInstance().getLabel_myId2().setText(String.valueOf(Data.myId));
            //CilentFrame.getInstance().repaint();
        }
        if (info.substring(0, 5).equals("UPML:")) {
            CilentFrame.getInstance().getModel().clear();
            String[] ss = info.substring(5).split("&");
            for (String s : ss) {
                CilentFrame.getInstance().getModel().addElement(s);
            }
            CilentFrame.getInstance().getList_matches().repaint();
        }
    }
}
