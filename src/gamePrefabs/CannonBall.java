package gamePrefabs;

import ObjectTools.Circle2;
import ObjectTools.Objecto2;
import ObjectTools.SphereCast;
import game.GameManager;

import java.awt.*;
import java.util.ArrayList;

public class CannonBall extends Circle2 {
    public static int BALL_SIZE = 20;
    private int combo = 1;
    public CannonBall(float posX, float posY, Color color) {
        super(posX, posY, BALL_SIZE, color);
        static_ = false;
        bounce = 0.5f;
        gravity = 5f;
        drag = 0f;
    }

    @Override
    public void onCollisionEnter(Objecto2 o) {

        if(o.getClass() == Peg.class){
            ArrayList<Objecto2> objs = new SphereCast(position,65).overlap();
            for (Objecto2 ob:objs){
                if(ob.getClass() == Peg.class){
                    GameManager.addPoints(((Peg) ob).value * combo);
                    combo++;
                    ob.destroy();
                }
            }
            GameManager.endTurn(combo);
            destroy();

        }

        if(o.getClass() == BottomTrigger.class){
            GameManager.endTurn(combo);
            destroy(1000);
        }

    }

    @Override
    public Objecto2 getCopy() {
        Objecto2 copy = new CannonBall(position[0],position[1],color);
        copy.type = this.type;
        copy.static_ = this.static_;
        copy.debug = this.debug;
        copy.position[0] = this.position[0];
        copy.position[1] = this.position[1];
        copy.momentum[0] = this.momentum[0];
        copy.momentum[1] = this.momentum[1];
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
