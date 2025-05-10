package gamePrefabs;

import ObjectTools.*;

import game.GameManager;

public class GhostBall extends Ball{
    public GhostBall(float posX, float posY) {
        super(posX, posY);
        solid = false;
        texture = new StillTexture("src/Images/ballMan_white.png",BALL_SIZE,BALL_SIZE);
    }

    @Override
    public void onCollisionEnter(Objecto2 o) {
        super.onCollisionEnter(o);
        if(Line2.class.isAssignableFrom(o.getClass())){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
            destroy(1000);
            GameManager.endTurn(combo);
        }

    }

    @Override
    public Objecto2 getCopy() {
        Objecto2 copy = new GhostBall(position[0],position[1]);
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
