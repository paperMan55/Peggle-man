package game;

import javax.swing.*;
import java.awt.*;

public class UIManager{
    private JFrame finestraGioco;
    private final int WINDOWS_HEIGHT=1200;
    private final int WINDOWS_width=900;

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
    private void addiingComponent(JComponent componenToAdd){
        this.finestraGioco.add(componenToAdd);

    }
}