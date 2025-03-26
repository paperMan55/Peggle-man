package gamePrefabs;

import ObjectTools.Circle2;
import ObjectTools.Objecto2;
import game.GameManager;

import java.awt.*;
import java.time.Duration;

public class Ball extends Circle2 {
    public static int BALL_SIZE = 20;
    private int combo = 1;
    public Ball(float posX, float posY) {
        super(posX, posY, BALL_SIZE);
        static_ = false;
        bounce = 0.7f;
        gravity = 5.81f;
        drag = 0f;

    }

    @Override
    public void resolveBounce(float angObj) {
        super.resolveBounce(angObj);
        System.out.println("AAAAAAAAAAAAAAAAAAAA");
    }

    public Ball(float posX, float posY, Color color) {
        super(posX, posY, BALL_SIZE, color);
        static_ = false;
        bounce = 0.7f;
        gravity = 5.81f;
        drag = 0f;
    }

    @Override
    public void onCollisionEnter(Objecto2 o) {
        if(o.getClass() == Peg.class){
            GameManager.addPoints(((Peg) o).value*combo);
            combo ++;
            o.destroy();
        }

        if(o.getClass() == BottomTrigger.class){
            GameManager.endTurn(combo);
            destroy(1000);
        }

    }
}
