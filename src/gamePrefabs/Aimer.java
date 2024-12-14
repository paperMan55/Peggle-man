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
    private static float force= 1000;

    public Aimer(float posX, float posY, Objecto2 object) {
        super(posX, posY, 10,10);
        this.toShoot = object;
        color = Color.GREEN;
        type = Objecto2.SQUARE;
        static_ = true;
        solid = false;
        UIManager.finestraGioco.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point pos = UIManager.panelOnTheCenter.getMousePosition();
                angle = (pos.y - position[1])/(pos.x - position[0]);
                double forceY = Math.sqrt(force*force/(1/(angle*angle) + 1)) * (pos.y >= position[1]?1:-1);
                double forceX = Math.sqrt(force*force - forceY*forceY) * (pos.x >= position[0]?1:-1);
                System.out.println("forces: ["+forceX+";"+forceY+"]");
                Objecto2 o = new Ball(position[0],position[1],Color.RED);

                ObjectList.addObject(o);
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


    }
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
