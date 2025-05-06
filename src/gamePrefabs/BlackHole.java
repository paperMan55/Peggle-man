package gamePrefabs;

import ObjectTools.AnimatedTexture;
import ObjectTools.Circle2;
import ObjectTools.ObjectList;
import ObjectTools.Objecto2;
import game.Clock;

import java.awt.*;
import java.util.ArrayList;

public class BlackHole extends Circle2 {
    private ArrayList<Ball> balls = new ArrayList<>();
    public float force = 100;
    public BlackHole(float posX, float posY, float diameter) {
        super(posX, posY, diameter);
        color = Color.blue;
        solid = false;
        texture = new AnimatedTexture("src/Images/Blackhole",new Point((int)diameter,(int)diameter),12);
    }
    public BlackHole(float posX, float posY, float diameter, float force) {
        super(posX, posY, diameter);
        color = Color.blue;
        solid = false;
        this.force = force;
        texture = new AnimatedTexture("src/Images/Blackhole",new Point((int)diameter,(int)diameter),12);
    }

    @Override
    public void onCollisionEnter(Objecto2 o) {
        try {
            Ball a = (Ball)o;
            balls.add(a);
        }catch (Exception _){}

    }

    @Override
    public void onCollisionExit(Objecto2 o) {
        try {
            Ball a = (Ball)o;
            balls.remove(a);
        }catch (Exception _){}
    }

    @Override
    public void onUpdate() {
        for (Ball b : balls){
            double angle = Math.atan2(getCenter()[1]-b.getCenter()[1],getCenter()[0]-b.getCenter()[0]);
            double d = Math.sqrt( Math.pow(getCenter()[1]-b.getCenter()[1],2) + Math.pow(getCenter()[0]-b.getCenter()[0],2));
            d = (size[0]/2 +b.size[0]) - d;
            double[] f = new double[]{Math.cos(angle)*d*force*Clock.deltaTime,Math.sin(angle)*d*force*Clock.deltaTime};

            b.addForce(f);
        }


    }
}
