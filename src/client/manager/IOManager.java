package client.manager;

import java.io.*;

public class IOManager {
    private static IOManager instance = null;
    private OutputStream os = null;
    private PrintStream ps = null;
    private InputStream is = null;
    private InputStreamReader isr = null;
    private BufferedReader br = null;

    private IOManager() {

    }

    public static IOManager getInstance() {
        if (instance == null) {
            instance = new IOManager();
        }
        return instance;
    }

    public void setOs(OutputStream os) {
        this.os = os;
        this.ps = new PrintStream(os);
    }

    public void setIs(InputStream is) {
        this.is = is;
        this.isr = new InputStreamReader(is);
        this.br = new BufferedReader(isr);
    }

    public BufferedReader getBr() {
        return br;
    }

    public PrintStream getPs() {
        return ps;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (br != null)
            br.close();
        if (isr != null)
            isr.close();
        if (ps != null)
            ps.close();
        if (os != null)
            os.close();
        if (is != null)
            is.close();
    }
}
