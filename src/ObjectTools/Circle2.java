package ObjectTools;

import java.awt.*;


public class Circle2 extends Objecto2{
    public Circle2(float posX, float posY, float diameter) {
        super(posX, posY, diameter, diameter);
        type = Objecto2.OVAL;
    }

    public Circle2(float posX, float posY, float diameter, Color color) {
        super(posX, posY, diameter, diameter, color);
        type = Objecto2.OVAL;
    }

    public Circle2(float posX, float posY, float diameter, Color color, boolean solid) {
        super(posX, posY, diameter, diameter, color, solid);
        type = Objecto2.OVAL;
    }

    public Circle2(float posX, float posY, float diameter, Color color, boolean solid, boolean static_) {
        super(posX, posY, diameter, diameter, color, solid, static_);
        type = Objecto2.OVAL;
    }

    @Override
    public boolean collideWithCircle(Objecto2 o) {
        if(o.size[0] == o.size[1]){
            double distance = Math.sqrt(Math.pow(getCenter()[0]-o.getCenter()[0],2)+Math.pow(getCenter()[1]-o.getCenter()[1],2));
            if(distance<=size[0]/2+o.size[0]/2){
                double ang = Math.atan2(o.getCenter()[1]-getCenter()[1],o.getCenter()[0]-getCenter()[0]);
                double[] pos1 = new double[]{Math.cos(ang)*size[0]/2 + getCenter()[0],Math.sin(ang)*size[0]/2 + getCenter()[1]};
                double[] pos2 = new double[]{Math.cos(ang+Math.PI)*o.size[0]/2 + o.getCenter()[0],Math.sin(ang+Math.PI)*o.size[0]/2 + o.getCenter()[1]};

                float[] toadjust= new float[]{(float)(pos2[0]-pos1[0]),(float)(pos2[1]-pos1[1])};

                new Collision(this,o,toadjust,momentum[1]/momentum[0],new float[]{getCenter()[0]-o.getCenter()[0],getCenter()[1]-o.getCenter()[1] });
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean collideWithSquare(Objecto2 o) {
        
        Rectangle this_ = new Rectangle(Math.round(position[0]),Math.round(position[1]),(int)size[0],(int)size[1]);
        Rectangle other = new Rectangle(Math.round(o.position[0]),Math.round(o.position[1]),(int)o.size[0],(int)o.size[1]);
        //b  = se due oggetti si toccano
        if(!o.solid || !this_.intersects(other)){
            return false;
        }
        boolean res = false;
        res = collideWithLine(new Line2(o.position[0]+o.size[0],o.position[1],o.position[0],o.position[1]));
        res = res || collideWithLine(new Line2(o.position[0],o.position[1],o.position[0],o.position[1]+o.size[1]));
        res = res || collideWithLine(new Line2(o.position[0]+o.size[0],o.position[1]+o.size[1],o.position[0]+o.size[0],o.position[1]));
        res = res || collideWithLine(new Line2(o.position[0],o.position[1]+o.size[1],o.position[0]+o.size[0],o.position[1]+o.size[1]));
        return res;
    }

    @Override
    public boolean collideWithLine(Objecto2 o) { //codice preso in "prestito", ancora da testare
        /*float ax =o.position[0] - getCenter()[0];
        float ay =o.position[1] - getCenter()[1];
        float bx =o.size[0] - getCenter()[0];
        float by =o.size[1] - getCenter()[1];
        double a = Math.pow(bx - ax,2) + Math.pow(by - ay,2);
        double b = 2*(ax*(bx - ax) + ay*(by - ay));
        double c = Math.pow(ax,2) + Math.pow(ay,2) - Math.pow(size[1],2);
        double disc = Math.pow(b,2) - 4*a*c;
        if(disc <= 0) return false; // doesn't intersect
        double sqrtdisc = Math.sqrt(disc);
        double t1 = (-b + sqrtdisc)/(2*a);
        double t2 = (-b - sqrtdisc)/(2*a);
        if((0 < t1 && t1 < 1) || (0 < t2 && t2 < 1)) {
            //intersect
            System.out.println("intersect");
            return true;
        }
        return false;
        */

        if(!new Rectangle((int)position[0],(int)position[1],(int)size[0],(int)size[1]).intersectsLine(o.position[0],o.position[1],o.size[0],o.size[1])){
            return false;
        }

        float momentumM = momentum[1]/momentum[0];
        float[] toAdjust = adjustPosition(o);
        if(toAdjust == null){
            return false;
        }
        new Collision(this,o,toAdjust, momentumM,new float[]{(o.position[1]-o.size[1]), -(o.position[0]-o.size[0])});
        return true;
    }

    @Override
    public float[] adjustPosition(Objecto2 o) {
        float[] center = getCenter();

        float lineM = (o.position[1]-o.size[1])/(o.position[0]-o.size[0]);//coefficiente angolare retta
        float lineQ = o.position[1]-lineM*o.position[0];//termine noto retta
        //System.out.println(getVelocity() + " [ENTER] --> "+getMomentum()+"; "+lineM+"x+"+lineQ);
        float distance;
        float[] angPos;
        if(Double.isInfinite(lineM)){
            distance = center[0] - o.position[0];
        }else if(lineM != 0){
            float pos = lineM*center[0]+lineQ;
            distance = (float) ((center[1]-pos)*Math.cos(Math.atan(Math.abs(lineM))));
            if(lineM>0){
                distance = -distance;
            }
        }else{ //vuol dire che linea orizzontale
            distance = center[1] - o.position[1];
        }
        if(Math.abs(distance)>size[0]/2){
            return null; // vuol dire che il quadrato interseca ma non il cerchio
        }

        if(distance>0){
            distance = size[0]/2-distance;
        }else {
            distance =-size[0]/2-distance;
        }
        double perp = Math.atan(-1/lineM);

        float moveX = (float) (Math.cos(perp)*distance);
        float moveY = (float) (Math.sin(perp)*distance);
        if(lineM==0 ){
            return new float[]{0,distance};
        }else if(Double.isInfinite(lineM)){
            return new float[]{distance,0};
        }
        return new float[]{moveX,moveY};
    }

	@Override
	public void onCollisionEnter(Objecto2 o) {}

    @Override
    public void onCollisionExit(Objecto2 o) {

    }

    @Override
    public void onCollisionStay(Objecto2 o) {

    }

    @Override
    public void onUpdate() {}
    @Override
    public Objecto2 getCopy() {
        Objecto2 copy = new Circle2(position[0],position[1],size[0]);
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
