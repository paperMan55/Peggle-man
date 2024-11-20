package game;

import ObjectTools.Circle2;
import ObjectTools.ObjectList;
import ObjectTools.Objecto2;
import map_creator.Peg;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Map {//sta classe non serve
    public final static int window_width=900;
    public final static int window_height=600;

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
        Objecto2 o = new Circle2(500,0,20, Color.GREEN,true, false);
        o.bounce = 1f;
        o.gravity = 9.81f;
        o.drag = 0f;
        o.momentum[0]= 1;
        o.momentum[1]= 0;

        ObjectList.objects.add(o);

        Clock clock = new Clock(thisdisegno,this);
        clock.start();
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

    public void update(){
        for (Objecto2 o:ObjectList.objects ) {
            if(o.position[1]>1000){
                o.position = new float[]{500, 0};
                o.momentum[0]= 1;
                o.momentum[1]= 0;
            }
            o.update();
        }

    }

}
