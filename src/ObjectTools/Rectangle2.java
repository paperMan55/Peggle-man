package ObjectTools;

import java.awt.*;

public class Rectangle2 extends Objecto2{
    public Rectangle2(float posX, float posY, float width, float height) {
        super(posX, posY, width, height);
        type = Objecto2.SQUARE;
    }

    public Rectangle2(float posX, float posY, float width, float height, Color color) {
        super(posX, posY, width, height, color);
        type = Objecto2.SQUARE;
    }

    public Rectangle2(float posX, float posY, float width, float height, Color color, boolean solid) {
        super(posX, posY, width, height, color, solid);
        type = Objecto2.SQUARE;
    }

    public Rectangle2(float posX, float posY, float width, float height, Color color, boolean solid, boolean static_) {
        super(posX, posY, width, height, color, solid, static_);
        type = Objecto2.SQUARE;
    }

    @Override
    public boolean collideWithCircle(Objecto2 o) {
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
        res = collideWithLine(new Line2(o.position[0],o.position[1],o.position[0]+o.size[0],o.position[1]));
        res = res || collideWithLine(new Line2(o.position[0],o.position[1],o.position[0],o.position[1]+o.size[1]));
        res = res || collideWithLine(new Line2(o.position[0]+o.size[0],o.position[1],o.position[0]+o.size[0],o.position[1]+o.size[1]));
        res = res || collideWithLine(new Line2(o.position[0],o.position[1]+o.size[1],o.position[0]+o.size[0],o.position[1]));
        return res;
    }

    @Override
    public boolean collideWithLine(Objecto2 o) {
        if(!new Rectangle((int)position[0],(int)position[1],(int)size[0],(int)size[1]).intersectsLine(o.position[0],o.position[1],o.size[0],o.size[1])){
            return false;
        }

        float lineM = (o.position[1]-o.size[1])/(o.position[0]-o.size[0]);//coefficiente angolare retta

        float momentumM = momentum[1]/momentum[0];
        float[] toAdjust = adjustPosition(o);
        new Collision(this,o,toAdjust, momentumM,lineM);
        return true;
    }

    @Override
    public float[] adjustPosition(Objecto2 o) {
        float[] center = getCenter();

        float lineM = (o.position[1]-o.size[1])/(o.position[0]-o.size[0]);//coefficiente angolare retta
        float lineQ = o.position[1]-lineM*o.position[0];//termine noto retta
        //System.out.println(getVelocity() + " [ENTER] --> "+getMomentum()+"; "+lineM+"x+"+lineQ);

        float[] angPos;
        if(Double.isInfinite(lineM)){
            if(o.position[0]>center[0]){
                angPos = getAnglePos(1);
            }else{
                angPos = getAnglePos(3);
            }
        }else if(center[1]>=lineM*center[0]+lineQ){ //vuol dire che sta sotto alla linea
            if(lineM>=0){ //angolo alto destra
                angPos = getAnglePos(1);
            }else{ //angolo alto sinistra
                angPos = getAnglePos(4);
            }
        }else{ //vuol dire che sta sopra alla linea
            if(lineM>=0){ //angolo basso sinistra
                angPos = getAnglePos(3);
            }else{ // angolo basso destra
                angPos = getAnglePos(2);
            }
        }
        float momentumM = momentum[1]/momentum[0];
        float momentumQ = angPos[1]-momentumM*angPos[0];
        if(lineM-momentumM == 0){
            return null;
        }
        float incidentX;
        float incidentY;

        if(Double.isInfinite(lineM)){
            
            incidentX = o.position[0];
        }else{
            incidentX = (momentumQ-lineQ)/(lineM-momentumM);
        }

        incidentY = momentumM * incidentX + momentumQ;
        float moveX = Math.abs(angPos[0]-incidentX);
        float moveY = Math.abs(angPos[1]-incidentY);
        float[] toadjust= new float[]{(float)(momentum[0]>0?-moveX:moveX),(float)(momentum[1]>0?-moveY:moveY)};
        return toadjust;
    }

	@Override
	public void onCollisionEnter(Objecto2 o) {}
}
