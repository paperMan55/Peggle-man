package game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.plaf.PanelUI;

import ObjectTools.Image2;
import ObjectTools.Line2;
import ObjectTools.ObjectList;
import ObjectTools.Objecto2;
import gamePrefabs.Ball;
import map_creator.MapManager;

public class UITester {
    public static void main(String[] args) {
        UIManager m= new UIManager("Prova_Man");



        //DICHIARAZIONE OGGETTI SCENA
        JPanel panelOnTheLeft = new JPanel();
        JPanel panelCenterGame= new JPanel();
        JPanel panelOnTheRight= new JPanel();
        JPanel panelBottom= new JPanel();




        //MANIPOLAZIONE DEI PANEL

        //Conterrà le palline come un flipper e il menu di gioco
        panelOnTheLeft.setBackground(Color.MAGENTA);
        panelOnTheLeft.setBounds(0,0,215,m.getWINDOWS_HEIGHT());
        m.addingComponent(panelOnTheLeft);

        panelCenterGame.setBounds(m.getWINDOWS_width()/2-385, 0, 750, 725);
        m.addingComponent(panelCenterGame);
        panelCenterGame.setLayout(null);
        
        //Map_Tester pam_tester= new Map_Tester();
         
             /*
             immagine di sfondo
             new Image2(0, 0, 750, 750, ImageIO.read(new File("src/map_creator/Images/"+MapManager.imagename+"_image.jpg")))
             */ 
        
    
        System.out.println("weeeeeee");
        //A destra ci saranno: moltiplicatore, 
        panelOnTheRight.setBackground(Color.ORANGE);
        panelOnTheRight.setBounds(m.getWINDOWS_width()-235,0,222,m.getWINDOWS_HEIGHT());
        m.addingComponent(panelOnTheRight);



        panelBottom.setBackground(Color.lightGray);
        panelBottom.setBounds(panelOnTheLeft.getWidth(), panelCenterGame.getHeight(),m.getWINDOWS_width()-panelOnTheLeft.getWidth()-panelOnTheRight.getWidth(),m.getWINDOWS_HEIGHT()-panelCenterGame.getHeight());
        //panelBottom.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false));
        
        m.addingComponent(panelBottom);

        
        MapManager.setMapFromPNG(MapManager.imagename); 
        //conterrà la mappa del gioco e il gioco in  sè
        //panelCenterGame.setBackground();
        Disegno disegno=new Disegno();
        panelCenterGame.add(disegno);
        
        
        

        Objecto2 pallina= new Ball(300, 300, Color.CYAN);
        pallina.momentum[0]=1;
        
        ObjectList.objects.add(pallina);
        

        
        System.out.println(ObjectList.objects.size());
        

        disegno.setBounds(0, 0, 750, 750);
        

        Objecto2 lineasinistra= new Line2(-1, 750, -1, 0);
        Objecto2 lineadestra= new Line2(750, 0, 750, 750);
        Objecto2 lineasu= new Line2(0, -1, 750, -1);
        ObjectList.objects.add(lineadestra);
        ObjectList.objects.add(lineasinistra);
        ObjectList.objects.add(lineasu);
        disegno.startClock();
    }
}
