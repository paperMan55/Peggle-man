package gamePrefabs;

import ObjectTools.*;
import game.GameManager;

import java.awt.*;
import java.util.ArrayList;

public class CannonBall extends Ball {
    public  int range = 65;
    public CannonBall(float posX, float posY) {
        super(posX, posY);
        static_ = false;
        bounce = 0.5f;
        gravity = 5f;
        drag = 0f;
        texture = new StillTexture("src/Images/ballMan_red.png",BALL_SIZE,BALL_SIZE);
    }

    @Override
    public void onCollisionEnter(Objecto2 o) {

        if(Peg.class.isAssignableFrom(o.getClass())){
            ArrayList<Objecto2> objs = new SphereCast(position,range).overlap();
            for (Objecto2 ob:objs){
                if(ob.getClass() == Peg.class){
                    GameManager.addPoints(((Peg) ob).value * combo);
                    combo++;
                    ob.destroy();
                }
            }
            GameManager.endTurn(combo);
            destroy();
            ObjectList.addObject(new Explosion(getCenter(), range*2));

        }else if(BottomTrigger.class.isAssignableFrom(o.getClass())){
            GameManager.endTurn(combo);
            destroy(1000);
        } else if (Collectable.class.isAssignableFrom(o.getClass())) {
            GameManager.addBallsToCurrent(((Collectable) o).collectable,1);
        }

    }

    @Override
    public Objecto2 getCopy() {
        Objecto2 copy = new CannonBall(position[0],position[1]);
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
