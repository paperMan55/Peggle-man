package game;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.plaf.PanelUI;

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



        //conterrà la mappa del gioco e il gioco in  sè
        panelCenterGame.setBackground(Color.CYAN);
        panelCenterGame.setBounds(m.getWINDOWS_width()/2-385, 0, 750, 725);
        m.addingComponent(panelCenterGame);


        //A destra ci saranno: moltiplicatore, 
        panelOnTheRight.setBackground(Color.ORANGE);
        panelOnTheRight.setBounds(m.getWINDOWS_width()-235,0,222,m.getWINDOWS_HEIGHT());
        m.addingComponent(panelOnTheRight);



        panelBottom.setBackground(Color.lightGray);
        panelBottom.setBounds(panelOnTheLeft.getWidth(), panelCenterGame.getHeight(),m.getWINDOWS_width()-panelOnTheLeft.getWidth()-panelOnTheRight.getWidth(),m.getWINDOWS_HEIGHT()-panelCenterGame.getHeight());
        m.addingComponent(panelBottom);
    }
}
