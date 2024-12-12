package gamePrefabs;

import ObjectTools.ObjectList;
import ObjectTools.Objecto2;
import ObjectTools.Rectangle2;
import UI.UIManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Aimer extends Objecto2{
    public Objecto2 toShoot;
    private float angle;
    private static float force= 100;

    public Aimer(float posX, float posY, Objecto2 object) {
        super(posX, posY, 0,0);
        this.toShoot = object;
        type = Objecto2.SQUARE;
        static_ = true;
        solid = false;
        UIManager.finestraGioco.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point pos = MouseInfo.getPointerInfo().getLocation();

                System.out.println("cliccato---> angolo: "+angle+" position "+pos);
                double forceY = Math.sqrt(force*force/(1/(angle*angle) + 1));
                double forceX = Math.sqrt(force*force + forceY*forceY);
                Objecto2 o = toShoot.getCopy();
                ObjectList.addObject(o);
                o.momentum =  new float[]{0,0};
                o.position = Aimer.super.position;
                o.addForce(forceX,forceY);

            }
        });
    }


    @Override
    public boolean collideWithCircle(Objecto2 o) {
        return false;
    }

    @Override
    public boolean collideWithSquare(Objecto2 o) {
        return false;
    }

    @Override
    public boolean collideWithLine(Objecto2 o) {
        return false;
    }

    @Override
    public float[] adjustPosition(Objecto2 o) {
        return new float[0];
    }

    @Override
    public void onCollisionEnter(Objecto2 o) {

    }

    @Override
    public void onUpdate()   {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        angle = (mousePos.y - position[1])/mousePos.x - position[0];
    } // @TODO transform screen pos of pointer to local
    @Override
    public Objecto2 getCopy() {
        Objecto2 copy = new Aimer(position[0],position[1], this.toShoot);
        copy.type = this.type;
        copy.static_ = this.static_;
        copy.debug = this.debug;
        copy.position = this.position;
        copy.momentum = this.momentum;
        copy.size = this.size;
        copy.image = this.image;
        copy.drag = this.drag;
        copy.gravity = this.gravity;
        copy.bounce = this.bounce;
        copy.color = this.color;
        copy.solid = this.solid;
        return copy;
    }
}
