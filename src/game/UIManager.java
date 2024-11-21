package game;

import javax.swing.*;
import java.awt.*;

public class UIManager{
    private JFrame finestraGioco;
    private final int WINDOWS_HEIGHT=900;
    private final int WINDOWS_width=1200;

    private JPanel panel;
    public UIManager(String nameofFrame){
        settingFrame(nameofFrame);
    }
    public void settingFrame(String nameofFrame){
        finestraGioco= new JFrame(nameofFrame);
        finestraGioco.setSize(WINDOWS_width, WINDOWS_HEIGHT);
        finestraGioco.setVisible(true);
        finestraGioco.setLayout(null);
    }
    public void addingComponent(JComponent componenToAdd){
        this.finestraGioco.add(componenToAdd);
        
    }
    public int getWINDOWS_HEIGHT() {
        return WINDOWS_HEIGHT;
    }
    public int getWINDOWS_width() {
        return WINDOWS_width;
    }
}