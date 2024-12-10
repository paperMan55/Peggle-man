package gamePrefabs;

import ObjectTools.Objecto2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Aimer extends Objecto2{
    public Objecto2 toShoot;

    public Aimer(float posX, float posY) {
        super(posX, posY, 0,0);
        type = Objecto2.SQUARE;
        static_ = true;
        solid = false;

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
    public void onUpdate()   { }
}
