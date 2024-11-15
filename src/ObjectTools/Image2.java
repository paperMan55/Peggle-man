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
    public Image2(int posX, int posY, float width, float height, Image image) {
        super(posX, posY, width, height, image);
        type = Objecto2.IMAGE;

    }

    @Override
    public void collideWithCircle(Objecto2 o) {

    }

    @Override
    public void collideWithSquare(Objecto2 o) {

        Rectangle this_ = new Rectangle(Math.round(position[0]),Math.round(position[1]),(int)size[0],(int)size[1]);
        Rectangle other = new Rectangle(Math.round(o.position[0]),Math.round(o.position[1]),(int)o.size[0],(int)o.size[1]);
        //b  = se due oggetti si toccano
        if(!o.solid || !this_.intersects(other)){
            return;
        }
        collideWithLine(new Line2(o.position[0],o.position[1],o.position[0]+o.size[0],o.position[1]));
        collideWithLine(new Line2(o.position[0],o.position[1],o.position[0],o.position[1]+o.size[1]));
        collideWithLine(new Line2(o.position[0]+o.size[0],o.position[1],o.position[0]+o.size[0],o.position[1]+o.size[1]));
        collideWithLine(new Line2(o.position[0],o.position[1]+o.size[1],o.position[0]+o.size[0],o.position[1]));
    }

    @Override
    public void collideWithLine(Objecto2 o) {
        if(!new Rectangle((int)position[0],(int)position[1],(int)size[0],(int)size[1]).intersectsLine(o.position[0],o.position[1],o.size[0],o.size[1])){
            return;
        }

        float lineM = (o.position[1]-o.size[1])/(o.position[0]-o.size[0]);//coefficiente angolare retta

        float momentumM = momentum[1]/momentum[0];
        adjustPosition(o);
        resolveBounce(momentumM,lineM);
    }

    @Override
    public void adjustPosition(Objecto2 o) {
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
            return;
        }
        float incidentX;
        float incidentY;
        if(Double.isInfinite(lineM)){
            System.out.println("fixed");
            incidentX = o.position[0];
        }else{
            incidentX = (momentumQ-lineQ)/(lineM-momentumM);
        }
        incidentY = momentumM*incidentX + momentumQ;
        System.out.println(lineM+"----->"+(incidentX)+";"+incidentY);

        System.out.println("\t "+getPosition());

        position[0] -= angPos[0]-incidentX;
        position[1] -= angPos[1]-incidentY;
        System.out.println("\t "+getPosition());
    }
}
