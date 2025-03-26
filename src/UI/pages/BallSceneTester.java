package UI.pages;

import ObjectTools.Line2;
import ObjectTools.ObjectList;
import ObjectTools.Objecto2;
import UI.Disegno;
import UI.PanelBottom;
import UI.PanelOnTheLeft;
import UI.PanelOnTheRight;
import game.Clock;
import gamePrefabs.Aimer;
import gamePrefabs.Ball;
import gamePrefabs.BallTester;
import gamePrefabs.BottomTrigger;
import map_creator.MapManager;

import javax.swing.*;
import java.awt.*;

import static UI.UIManager.*;
import static UI.UIManager.GAME_WIDTH;

public class BallSceneTester extends JPanel {
    public static PanelOnTheLeft panelontheleft;
    public static PanelOnTheRight panelOnTheRight;
    public static PanelBottom panelbottom;
    public static Disegno panelOnTheCenter;

    public BallSceneTester(){

        this.setBounds(0,0, WINDOWS_WIDTH,WINDOWS_HEIGHT);
        this.setLayout(null);
        panelontheleft= new PanelOnTheLeft(0,0,(WINDOWS_WIDTH-GAME_WIDTH)/2,WINDOWS_HEIGHT);
        panelOnTheRight= new PanelOnTheRight((WINDOWS_WIDTH-GAME_WIDTH)/2+GAME_WIDTH, 0, (WINDOWS_WIDTH-GAME_WIDTH)/2, WINDOWS_HEIGHT);
        panelOnTheCenter = new Disegno();
        panelOnTheCenter.setBounds((WINDOWS_WIDTH-GAME_WIDTH)/2, 0,GAME_WIDTH,GAME_HEIGHT);
        //panelOnTheCenter= new PanelOnTheCenter((WINDOWS_WIDTH-GAME_WIDTH)/2, 0,GAME_WIDTH,GAME_HEIGHT);


        //dichiarazione del pannello in basso
        panelbottom= new PanelBottom(panelontheleft.getWidth(), panelOnTheCenter.getHeight(), WINDOWS_WIDTH-panelOnTheRight.getWidth()-panelOnTheRight.getWidth(), WINDOWS_HEIGHT-panelOnTheCenter.getHeight());

        this.add(panelontheleft);
        this.add(panelOnTheCenter);

        this.add(panelOnTheRight);
        this.add(panelbottom);
        creazioneCampoDiGioco();

        Clock clock = new Clock(Game.panelOnTheCenter);
        clock.start();
    }

    public void creazioneCampoDiGioco(){

        ObjectList.addObject(new BallTester());
    }
}
