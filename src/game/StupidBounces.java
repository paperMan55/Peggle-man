package game;

import ObjectTools.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;


public class StupidBounces {
    private static JLabel b;
    private static JSlider s;
    public static void main(String[] args) {

        //classe che contiene posizione e grandezza dell oggetto

        ObjectList.objects.add(new Circle2(300,200,100,Color.BLACK));


        Objecto2 o = new Circle2(150,200,50,Color.GREEN,true, false);
        o.bounce = 1f;
        o.gravity = 5f;
        o.drag = 0f;
        o.momentum[0]= 150;
        o.momentum[1]= 0;

/*
        Objecto u = new Objecto(150,200,50,50,Objecto.IMAGE,Color.BLACK,true, false);
        u.bounce = 1f;
        u.gravity = 5f;
        u.drag= 0f;
        u.debug=false;
        u.momentum[0]= -18;
        u.momentum[1]= 0;
*/

        try{
            o.image = ImageIO.read(new File("src/game/DVD_video_logo.png"));
            //u.image = ImageIO.read(new File("src/DVD_video_logo.png"));

        }catch (Exception a){
            System.out.println("boh");
        }

        ObjectList.objects.add(o);
        //objs.add(u);
        JFrame f = new JFrame("antonio");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height
        f.setSize(900,900);

        Disegno p = new Disegno();

        b = new JLabel("boh");
        b.setSize(300,20);
        b.setLocation(10,10);

        s = new JSlider();
        s.setValue(5);
        s.setMaximum(60);
        s.setMinimum(1);
        s.setSize(300,20);
        s.setLocation(400,10);

        p.add(s);
        p.add(b);
        p.setLayout(null);
        f.add(p);
        f.setVisible(true);

        //permette l'aggiornamento del Panel in automatico
        Clock clock = new Clock(p);
        clock.start();
    }
    private long exMillis = System.currentTimeMillis();
    public void update(){
        Clock.fpsLimit = s.getValue();
        if(System.currentTimeMillis()-exMillis>=1000){
            b.setText("fps: "+Math.round(1/Clock.deltaTime));
            exMillis = System.currentTimeMillis();
        }
        for (Objecto2 o:ObjectList.objects ) {
            o.update();
        }
        
    }
/*
    public void hahaha(){
        Gson g = new Gson();
        String s = g.toJson(objs.get(0));
        System.out.println(s);

        Type t = new TypeToken<ArrayList<Objecto>>(){}.getType();
        ArrayList<Objecto> readed = g.fromJson(s,t);
    }

*/

}