package gamePrefabs;

import ObjectTools.Circle2;
import ObjectTools.Objecto2;
import ObjectTools.StillTexture;
import game.Cache;
import game.GameManager;

import java.awt.*;

public class Ball extends Circle2 {
    public static int BALL_SIZE = 20;
    public int combo = 1;
    public Ball(float posX, float posY) {
        super(posX, posY, BALL_SIZE);
        static_ = false;
        bounce = 0.5f;
        gravity = 5f;
        drag = 0f;
        texture = new StillTexture("src/Images/ballMan.png",BALL_SIZE,BALL_SIZE);
    }

    @Override
    public void onCollisionEnter(Objecto2 o) {


        if(Peg.class.isAssignableFrom(o.getClass())){
            GameManager.addPoints(((Peg) o).value*combo);
            combo ++;
            o.destroy();
        }else if(BottomTrigger.class.isAssignableFrom(o.getClass())){
            GameManager.endTurn(combo);
            destroy(1000);
        }else if (Collectable.class.isAssignableFrom(o.getClass())) {
            GameManager.addBallsToCurrent(((Collectable) o).collectable,1);
            System.out.println("collezionato");
            o.destroy();
        }

    }

    @Override
    public Objecto2 getCopy() {
        Objecto2 copy = new Ball(position[0],position[1]);
        copy.type = this.type;
        copy.static_ = this.static_;
        copy.debug = this.debug;
        copy.position[0] = this.position[0];
        copy.position[1] = this.position[1];
        copy.momentum[0] = this.momentum[0];
        copy.momentum[1] = this.momentum[1];
        copy.size = this.size;
        copy.texture = this.texture;
        copy.drag = this.drag;
        copy.gravity = this.gravity;
        copy.bounce = this.bounce;
        copy.color = this.color;
        copy.solid = this.solid;
        return copy;
    }
}
