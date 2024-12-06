package UI;

import ObjectTools.Collision;
import ObjectTools.ObjectList;
import ObjectTools.Objecto2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Disegno extends JPanel {
    public static ArrayList<float[]> pointDebuggers = new ArrayList<>();




    public void paint(Graphics g){
        

        Toolkit.getDefaultToolkit().sync(); // altrimenti lagga in linux
        super.paint(g);
        for (Objecto2 o: ObjectList.objects) {
            g.setColor(o.color);
            switch (o.type){
                case Objecto2.LINE:

                    g.drawLine(Math.round(o.position[0]),Math.round(o.position[1]),Math.round(o.size[0]),Math.round(o.size[1]));
                    break;
                case Objecto2.SQUARE:
                    g.fillRect(Math.round(o.position[0]),Math.round(o.position[1]),Math.round(o.size[0]),Math.round(o.size[1]));

                    break;
                case Objecto2.OVAL:
                    g.fillOval(Math.round(o.position[0]),Math.round(o.position[1]),Math.round(o.size[0]),Math.round(o.size[1]));

                    break;
                case Objecto2.IMAGE:
                    g.drawImage(o.image,Math.round(o.position[0]),Math.round(o.position[1]),this);
                    break;
            }
        }
        g.setColor(Color.RED);
        for(float[] point : pointDebuggers){
            g.drawRect(Math.round(point[0]),Math.round(point[1]),2,2);
        }

        //g.fillRect(0,300,1080,900);
        //g.setColor(new Color(0x2780CB));
        //g.fillOval(200,600,500,200);
        //Image i = Toolkit.getDefaultToolkit().getImage("src/papera1.png");
        //i = i.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        //g.drawImage(i,position[0],position[1],this);
        //Clock.drawFrame(); //pk altrimenti sto metodo e asincrono e crea errori nell esecuzione
    }

    public void update(){
        for (Objecto2 o:ObjectList.objects ) {
            if(o.position[1]>1000){
                o.position = new float[]{500, 0};
                o.momentum[0]= -500;
                o.momentum[1]= 10;
            }
            o.update();
        }

        /*
        risolvo le collisioni solo dopo aver controllato tutti gli oggetti altrimenti
        solo uno dei due oggetti che collidono vede la collisione
         */
        for(Collision c:ObjectList.collisions.values()){


            c.resolve();
        }
        ObjectList.collisions.clear();
    }

}
