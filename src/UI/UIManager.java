package UI;

import javax.swing.*;

import ObjectTools.Line2;
import ObjectTools.ObjectList;
import ObjectTools.Objecto2;
import gamePrefabs.Ball;
import map_creator.MapManager;

import java.awt.*;

public class UIManager{
    private JFrame finestraGioco;
    public static final int WINDOWS_HEIGHT=900;
    public static final int WINDOWS_WIDTH=1200;
    public static final int GAME_HEIGHT=750;
    public static final int GAME_WIDTH=750;
    public PanelOnTheLeft panelontheleft;
    public PanelOnTheRight panelOnTheRight;
    public PanelBottom panelbottom;
    public PanelOnTheCenter panelOnTheCenter;

    
    public UIManager(String nameofFrame){
        settingFrame(nameofFrame);
        setLayoutUp();
        //creazioneCampoDiGioco();
    }
    public void settingFrame(String nameofFrame){
        finestraGioco= new JFrame(nameofFrame);
        finestraGioco.setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        finestraGioco.setVisible(true);
        finestraGioco.setLayout(null);
    }
    public void addComponent(JComponent componenToAdd){
        this.finestraGioco.add(componenToAdd);
        
    }
    public int getWINDOWS_HEIGHT() {
        return WINDOWS_HEIGHT;
    }
    public int getWINDOWS_width() {
        return WINDOWS_WIDTH;
    }

    public void setLayoutUp(){

        //dichiarazione del pannello nella parte a sinistra dello schermo
        panelontheleft= new PanelOnTheLeft(0,0,215,300);


        //dischiarazione del pannello a destra
        //panelOnTheRight= new PanelOnTheRight(UIManager.WINDOWS_WIDTH-235, 0, 222, this.WINDOWS_HEIGHT);


        //panelOnTheCenter= new PanelOnTheCenter((UIManager.WINDOWS_WIDTH)/2-385, 0,750,725);


        //dichiarazione del pannello in basso
        //panelbottom= new PanelBottom(panelontheleft.getWidth(), panelOnTheCenter.getHeight(), UIManager.WINDOWS_WIDTH-panelOnTheRight.getWidth()-panelOnTheRight.getWidth(), UIManager.WINDOWS_HEIGHT-panelOnTheCenter.getHeight());
        
        this.addComponent(panelontheleft);
        //this.addComponent(panelOnTheCenter);

        //this.addComponent(panelOnTheRight);
        //this.addComponent(panelbottom);
    }

    public void creazioneCampoDiGioco(){
        MapManager.setMapFromPNG(MapManager.imagename);
        Objecto2 pallina= new Ball(300, 300, Color.CYAN);
        pallina.momentum[0]=1;
        
        ObjectList.objects.add(pallina);
        
        Objecto2 lineasinistra= new Line2(-1, 750, -1, 0);
        Objecto2 lineadestra= new Line2(750, 0, 750, 750);
        Objecto2 lineasu= new Line2(0, -1, 750, -1);
        ObjectList.objects.add(lineadestra);
        ObjectList.objects.add(lineasinistra);
        ObjectList.objects.add(lineasu);
        this.panelOnTheCenter.disegno.startClock();
    }
}