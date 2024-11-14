package ObjectTools;

import java.awt.*;

public class Circle2 extends Objecto2{
    public Circle2(float posX, float posY, float diameter) {
        super(posX, posY, diameter, diameter);
    }

    public Circle2(float posX, float posY, float diameter, Color color) {
        super(posX, posY, diameter, diameter, color);
    }

    public Circle2(float posX, float posY, float diameter, Color color, boolean solid) {
        super(posX, posY, diameter, diameter, color, solid);
    }

    public Circle2(float posX, float posY, float diameter, Color color, boolean solid, boolean static_) {
        super(posX, posY, diameter, diameter, color, solid, static_);
    }

    @Override
    public void collideWithCircle(Objecto2 o) {
        if(o.size[0] == o.size[1]){
            double distance = Math.sqrt(Math.pow(getCenter()[0]-o.getCenter()[0],2)+Math.pow(getCenter()[1]-o.getCenter()[1],2));
            if(distance<size[0]/2+o.size[0]/2){
                double toMove = (size[0]/2+o.size[0]/2) -distance;
                float lineM = (getCenter()[1]-o.getCenter()[1])/(getCenter()[0]-o.getCenter()[0]);
                double moveX = (toMove/Math.sqrt(Math.pow(lineM,2)+1));
                double moveY = Math.sqrt(Math.pow(toMove,2)-Math.pow(moveX,2));
                System.out.println("distance: "+toMove);
                System.out.println("["+moveX+";"+moveY+"]");
                if(getCenter()[0]>o.getCenter()[0]){ //non funzia ☺
                    position[0] += moveX;
                    position[1] += moveY;
                }else {
                    position[0] -= moveX;
                    position[1] -= moveY;
                }
                resolveBounce(momentum[1]/momentum[0],-1/lineM);
                System.out.println("  -> "+getMomentum());
            }
        }
    }

    @Override
    public void collideWithSquare(Objecto2 o) {

    }

    @Override
    public void collideWithLine(Objecto2 o) { //codice preso in "prestito", ancora da testare
        float ax =o.position[0] - getCenter()[0];
        float ay =o.position[1] - getCenter()[1];
        float bx =o.size[0] - getCenter()[0];
        float by =o.size[1] - getCenter()[1];
        double a = Math.pow(bx - ax,2) + Math.pow(by - ay,2);
        double b = 2*(ax*(bx - ax) + ay*(by - ay));
        double c = Math.pow(ax,2) + Math.pow(ay,2) - Math.pow(size[1],2);
        double disc = Math.pow(b,2) - 4*a*c;
        if(disc <= 0) return; // doesn't intersect
        double sqrtdisc = Math.sqrt(disc);
        double t1 = (-b + sqrtdisc)/(2*a);
        double t2 = (-b - sqrtdisc)/(2*a);
        if((0 < t1 && t1 < 1) || (0 < t2 && t2 < 1)) {
            //intersect
            System.out.println("intersect");

        }
    }

    @Override
    public void adjustPosition(Objecto2 o) {

    }
}
