import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;


public class Main {
    public static ArrayList<Objecto> objs;

    public static void main(String[] args) {


        objs = new ArrayList<>();
        //classe che contiene posizione e grandezza dell oggetto
        Objecto o = new Objecto(2,3,50,50, Objecto.SQUARE, new Color(255, 0, 0));
        objs.add(o);
        objs.add(new Objecto(0,0,300,300, Objecto.SQUARE,new Color(67, 107, 55)));
        objs.add(new Objecto(0,400,300,20, Objecto.SQUARE,new Color(67, 107, 55)));
        objs.add(new Objecto(0,0,20,400, Objecto.SQUARE,new Color(67, 107, 55)));
        objs.add(new Objecto(300,400,20,200, Objecto.SQUARE,new Color(67, 107, 55)));
        objs.add(new Objecto(300,600,200,20, Objecto.SQUARE,new Color(67, 107, 55)));
        objs.add(new Objecto(500,600,20,80, Objecto.SQUARE,new Color(67, 107, 55)));

        o.setDrag(0.015f);
        o.gravity = 0.1f;
        o.bounce = 0.5f;
        o.debug = true;
        JFrame f = new JFrame("antonio");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height
        f.setSize(900,900);

        Disegno p = new Disegno(objs);

        p.setLayout(null);



        JButton b = new JButton("aaa");
        b.setSize(100,20);
        b.setLocation(80,500);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                o.position[0]=0;o.position[1]=0;
            }
        });

        p.add(b);
        b.setVisible(true);
        b.setFocusable(false);

        f.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                switch (c){
                    case 'w':
                        o.addForce(0,-10);
                        break;
                    case 'a':
                        o.addForce(-10,0);
                        break;
                    case 's':
                        o.addForce(0,10);
                        break;
                    case 'd':
                        o.addForce(10,0);
                        break;
                    default:
                        System.out.println("boh");
                }
                //System.out.println("X-> "+o.position[0]+"    Y-> " + o.position[1]);
            }
        });


        f.add(p);
        f.setVisible(true);

        //permette l'aggiornamento del Panel in automatico
        Clock clock = new Clock(p);

        clock.start();
    }
    public void update(){
        for (Objecto o:objs ) {

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
    public ArrayList<Objecto> getObjs(){
        return objs;
    }

}