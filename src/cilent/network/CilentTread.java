package cilent.network;

import cilent.data.Data;
import cilent.manager.IOManager;

import java.io.BufferedReader;
import java.io.IOException;

public class CilentTread extends Thread {
    @Override
    public void run() {
        BufferedReader br = IOManager.getInstance().getBr();
        String info = null;
        while (Data.connected) {
            try {
                info = br.readLine();
                if (info != null)
                    System.out.println("flag:::" + info);
                ProcessRecivedMessage.process(info);
            } catch (IOException e) {
                Data.connected = false;
                e.printStackTrace();
            }
        }
    }
}
