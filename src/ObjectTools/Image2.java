package ObjectTools;

import java.awt.*;

public class Image2 extends Objecto2{

    public Image2(float posX, float posY, float width, float height, Color color) {
        super(posX, posY, width, height, color);
        type = Objecto2.IMAGE;
    }

    public Image2(float posX, float posY, float width, float height, Color color, boolean solid) {
        super(posX, posY, width, height, color, solid);
        type = Objecto2.IMAGE;
    }

    public Image2(float posX, float posY, float width, float height, Color color, boolean solid, boolean static_) {
        super(posX, posY, width, height, color, solid, static_);
        type = Objecto2.IMAGE;
    }
    public Image2(int posX, int posY, float width, float height, Texture texture) {

        super(posX, posY, width, height, texture);
        type = Objecto2.IMAGE;

    }

    @Override
    public Collision collideWithCircle(Objecto2 o) {
        return null;
    }

    @Override
    public Collision collideWithSquare(Objecto2 o) {

        Rectangle this_ = new Rectangle(Math.round(position[0]),Math.round(position[1]),(int)size[0],(int)size[1]);
        Rectangle other = new Rectangle(Math.round(o.position[0]),Math.round(o.position[1]),(int)o.size[0],(int)o.size[1]);
        //b  = se due oggetti si toccano
        if( !this_.intersects(other)){
            return null;
        }
        Collision res = collideWithLine(new Line2(o.position[0]+o.size[0],o.position[1],o.position[0],o.position[1]));
        if(res == null){
            res = collideWithLine(new Line2(o.position[0],o.position[1],o.position[0],o.position[1]+o.size[1]));
        }
        if(res == null){
            res = collideWithLine(new Line2(o.position[0]+o.size[0],o.position[1]+o.size[1],o.position[0]+o.size[0],o.position[1]));
        }
        if(res == null){
            res = collideWithLine(new Line2(o.position[0],o.position[1]+o.size[1],o.position[0]+o.size[0],o.position[1]+o.size[1]));
        }
        if(res!=null){
            res.second_obj = o;
        }
        return res;
    }

    @Override
    public Collision collideWithLine(Objecto2 o) {
        if(!new Rectangle((int)position[0],(int)position[1],(int)size[0],(int)size[1]).intersectsLine(o.position[0],o.position[1],o.size[0],o.size[1])){
            return null;
        }
        float[] toAdjust = adjustPosition(o);
        return new Collision(this,o,toAdjust, momentum,new float[]{-(o.position[1]-o.size[1]), (o.position[0]-o.size[0])});
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
        incidentY = momentumM*incidentX + momentumQ;
    
        float moveX = Math.abs(angPos[0]-incidentX);
        float moveY = Math.abs(angPos[1]-incidentY);
        float[] toadjust= new float[]{(float)(momentum[0]>0?-moveX:moveX),(float)(momentum[1]>0?-moveY:moveY)};
        return toadjust;
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
    public void onUpdate() {
    }
    @Override
    public Objecto2 getCopy() {
        Objecto2 copy = new Image2(position[0],position[1],size[0],size[1],Color.GREEN);
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
