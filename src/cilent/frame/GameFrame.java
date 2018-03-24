package cilent.frame;

import javax.swing.*;

public class GameFrame extends JFrame{
    private static GameFrame instance = null;
    private GameFrame(){

    }

    public static GameFrame getInstance() {
        if(instance == null){
            instance = new GameFrame();
        }
        return instance;
    }

    public void launchFrame(){
        this.setVisible(true);
    }
    public void hideFrame(){
        this.setVisible(false);
    }
}
