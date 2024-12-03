package gamePrefabs;

import ObjectTools.Circle2;
import ObjectTools.Objecto2;

import java.awt.*;

public class Ball extends Circle2 {
    public static int BALL_SIZE = 20;
    public Ball(float posX, float posY) {
        super(posX, posY, BALL_SIZE);
        static_ = false;
        bounce = 0.8f;
        gravity = 5.81f;
        drag = 0f;

    }

    public Ball(float posX, float posY, Color color) {
        super(posX, posY, BALL_SIZE, color);
        static_ = false;
        bounce = 0.8f;
        gravity = 5.81f;
        drag = 0f;
    }

    @Override
    public void onCollisionEnter(Objecto2 o) {
        if(o.getClass() == Peg.class){
            o.destroy();
        }

    }
}
