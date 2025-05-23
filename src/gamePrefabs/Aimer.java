package gamePrefabs;

import ObjectTools.Collision;
import ObjectTools.ObjectList;
import ObjectTools.Objecto2;
import UI.pages.Game;
import UI.UIManager;
import game.GameManager;
import game.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Aimer extends Objecto2{

    private float angle;
    private static final float force= 600;
    private final MouseAdapter mouseAdapter;

    public Aimer(float posX, float posY) {
        super(posX, posY, 10,10);
        color = Color.GREEN;
        type = Objecto2.SQUARE;
        static_ = true;
        solid = false;
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point pos = Game.panelOnTheCenter.getMousePosition();
                if(pos == null){
                    return;
                }
                System.out.println("--------> "+GameManager.canShoot);
                Player p = GameManager.getCurrentPlayer();
                if(p.balls.size()<=0 || !GameManager.canShoot){
                    return;
                }
                GameManager.canShoot = false;

                angle = (pos.y - position[1])/(pos.x - position[0]);
                double forceY = Math.sqrt(force*force/(1/(angle*angle) + 1)) * (pos.y >= position[1]?1:-1);
                double forceX = Math.sqrt(force*force - forceY*forceY) * (pos.x >= position[0]?1:-1);


                Objecto2 o = p.balls.getFirst();
                o.position = new float[]{position[0]-o.size[0]/2,position[1]-o.size[1]/2};

                ObjectList.addObject(o);
                o.addForce(forceX,forceY);

                p.balls.removeFirst();
                Game.panelontheleft.setBalls_left(p.balls);
            }
        };
        UIManager.finestraGioco.addMouseListener(mouseAdapter);
        UIManager.finestraGioco.requestFocus();
    }


    @Override
    public Collision collideWithCircle(Objecto2 o) {
        return null;
    }

    @Override
    public Collision collideWithSquare(Objecto2 o) {
        return null;
    }

    @Override
    public Collision collideWithLine(Objecto2 o) {
        return null;
    }

    @Override
    public float[] adjustPosition(Objecto2 o) {
        return new float[0];
    }


    @Override
    public Objecto2 getCopy() {
        Objecto2 copy = new Aimer(position[0],position[1]);
        copy.type = this.type;
        copy.static_ = this.static_;
        copy.debug = this.debug;
        copy.position = this.position;
        copy.momentum = this.momentum;
        copy.size = this.size;
        copy.texture = this.texture;
        copy.drag = this.drag;
        copy.gravity = this.gravity;
        copy.bounce = this.bounce;
        copy.color = this.color;
        copy.solid = this.solid;
        return copy;
    }

    @Override
    public void onDestroy(boolean forced) {
        UIManager.finestraGioco.removeMouseListener(mouseAdapter);
    }
}
