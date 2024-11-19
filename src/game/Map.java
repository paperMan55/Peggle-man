package game;

import ObjectTools.ObjectList;
import map_creator.Peg;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Map {//sta classe non serve
    final static int window_width=900;
    final static int window_height=900;

    static JFrame thisframe;
    static Disegno thisdisegno;
    static String imagename= "prova2.png";
    static BufferedImage backgroundImage;
    public static ArrayList<Peg> listapeg;
    ArrayList <Peg> listaPegs;
    public Map(ArrayList<Peg> arraylitPegs){
        this.listaPegs=arraylitPegs;
        createWindow();
        creationDefinitiveMap();

    }
    public void creationDefinitiveMap(){
        for(Peg peg: listaPegs){
            ObjectList.objects.add(peg);
        }

        //ObjectList.objects.add();
    }
    public void createWindow(){
        thisframe= new JFrame("Peggle_manDef");
        thisframe.setSize(window_width, window_height);
        thisdisegno = new Disegno();
        thisframe.add(thisdisegno);

        thisframe.setVisible(true);
        thisdisegno.repaint();
    }

}
