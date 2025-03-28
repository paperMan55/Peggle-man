package UI.pages;

import ObjectTools.Circle2;
import ObjectTools.Line2;
import ObjectTools.ObjectList;
import ObjectTools.Objecto2;
import UI.Disegno;
import UI.PanelBottom;
import UI.PanelOnTheLeft;
import UI.PanelOnTheRight;
import UI.UIManager;
import game.Clock;
import game.GameManager;
import gamePrefabs.Aimer;
import gamePrefabs.Ball;
import gamePrefabs.BallTester;
import gamePrefabs.BottomTrigger;
import map_creator.MapManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static UI.UIManager.*;

public class Game extends JPanel {
    public static PanelOnTheLeft panelontheleft;
    public static PanelOnTheRight panelOnTheRight;
    public static PanelBottom panelbottom;
    public static Disegno panelOnTheCenter;

    public Game(){

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
        /*
        MapManager.setMapFromPNG(MapManager.imagename);
        ObjectList.objects.add(new Aimer(GAME_WIDTH/2f,0, new Ball(0,0,Color.RED)));
        Objecto2 lineasinistra= new Line2(-1, 750, -1, 0);
        Objecto2 lineadestra= new Line2(750, 0, 750, 750);
        Objecto2 lineasu= new Line2(0, -1, 750, -1);
        ObjectList.objects.add(lineadestra);
        ObjectList.objects.add(lineasinistra);
        ObjectList.objects.add(lineasu);
        ObjectList.objects.add(new BottomTrigger());
        */
        BallTester b = new BallTester();
        ObjectList.addObject(b);
        ObjectList.addObject(new Circle2(300,300,80));
        System.out.println("zio billy");


    }
}
