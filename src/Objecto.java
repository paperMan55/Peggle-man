import org.w3c.dom.css.*;

import javax.naming.NameNotFoundException;
import java.awt.*;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.lang.*;

public class Objecto {
    public static final String SQUARE = "square";
    public static final String OVAL = "oval";
    public static final String LINE = "line";
    public static final String IMAGE = "image";

    public boolean static_ = true;
    public boolean debug = false;
    public float[] position;
    public float[] momentum;
    public float[] size;
    public String type;
    public Image image;
    public float drag;
    public float gravity;
    public float bounce;
    public Color color;
    public boolean solid;
    private ArrayList<Objecto> objs;
    private boolean exCollision = false;

    //questa classe permetterà una gestione facilitata degli oggetti con attributi tipo posizione e grandezza
    public Objecto(float posX, float posY, float width, float height, String type) {
        init(posX,posY,width,height,type,new Color(0x000000),true,true);
    }
    public Objecto(float posX, float posY, float width, float height, String type,Color color){
        init(posX,posY,width,height,type,color,true, true);
    }
    public Objecto(float posX, float posY, float width, float height, String type,Color color,boolean solid){
        init(posX,posY,width,height,type,color,solid,true);
    }
    public Objecto(float posX, float posY, float width, float height, String type,Color color,boolean solid, boolean static_){
        init(posX,posY,width,height,type,color,solid,static_);
    }
    private void init(float posX, float posY, float width, float height, String type,Color color,boolean solid, boolean static_){
        this.type = type;
        position = new float[]{posX, posY};
        size = new float[]{width,height};
        momentum = new float[]{0,0};
        drag = 1;
        gravity = 0f;
        objs = new StupidBounces().getObjs();
        bounce = 0f;
        this.static_ = static_;
        this.color = color;
        this.solid = solid;
    }
    public Objecto(int posX, int posY, int width, int height, Image image){
        this.type = IMAGE;
        this.image = image;
        position = new float[]{posX, posY};
        size = new float[]{width,height};
    }

    public void addForce(int xForce, int yForce){
        this.position[0] += xForce;
        this.position[1] += yForce;
        momentum[0] += xForce;
        momentum[1] += yForce;
    }

    public void update(){
        if (static_) return;

        momentum[0] -= momentum[0] * (drag);
        momentum[1] -= momentum[1] * (drag) - gravity;

        this.position[0] += (float) (momentum[0]*Clock.deltaTime);
        this.position[1] += (float) (momentum[1]*Clock.deltaTime);

        ArrayList<Integer> cols = new ArrayList<>();

        if(solid){
            checkCollision();
        }




    }
    public void checkCollision(){
        for (Objecto o:objs) {
            if(!o.equals(this)){
                switch (o.type){
                    case Objecto.LINE:
                        collideWithLine(o);
                        break;
                    case Objecto.OVAL:
                        collideWithCircle(o);
                        break;
                    default:
                        collideWithSquare(o);
                }
            }
        }
    }
    public void setDrag(float drag){
        this.drag = drag;
    }
    public void move(float x, float y){
        this.position[0] += x;
        this.position[1]+=y;
    }
    private void collideWithCircle(Objecto o){

        if(type == OVAL){
            if(o.size[0] == o.size[1]){
                double distance = Math.sqrt(Math.pow(getCenter()[0]-o.getCenter()[0],2)+Math.pow(getCenter()[1]-o.getCenter()[1],2));
                if(distance<size[0]/2+o.size[0]/2){
                    double toMove = (size[0]/2+o.size[0]/2) -distance;
                    float lineM = (getCenter()[1]-o.getCenter()[1])/(getCenter()[0]-o.getCenter()[0]);
                    double moveX = (toMove/Math.sqrt(Math.pow(lineM,2)+1));
                    double moveY = Math.sqrt(Math.pow(toMove,2)-Math.pow(moveX,2));
                    System.out.println("distance: "+toMove);
                    System.out.println("["+moveX+";"+moveY+"]");
                    if(getCenter()[0]>o.getCenter()[0]){
                        position[0] += moveX;
                        position[1] += moveY;
                    }else {
                        position[0] -= moveX;
                        position[1] -= moveY;
                    }
                    resolveBounce(momentum[1]/momentum[0],-1/lineM);
                }
            }
        }
    }
    private void collideWithSquare(Objecto o){

        Rectangle this_ = new Rectangle(Math.round(position[0]),Math.round(position[1]),(int)size[0],(int)size[1]);
        Rectangle other = new Rectangle(Math.round(o.position[0]),Math.round(o.position[1]),(int)o.size[0],(int)o.size[1]);
        //b  = se due oggetti si toccano
        if(!o.solid || !this_.intersects(other)){
            return;
        }
        collideWithLine(new Objecto(o.position[0],o.position[1],o.position[0]+o.size[0],o.position[1],Objecto.LINE));
        collideWithLine(new Objecto(o.position[0],o.position[1],o.position[0],o.position[1]+o.size[1],Objecto.LINE));
        collideWithLine(new Objecto(o.position[0]+o.size[0],o.position[1],o.position[0]+o.size[0],o.position[1]+o.size[1],Objecto.LINE));

        collideWithLine(new Objecto(o.position[0],o.position[1]+o.size[1],o.position[0]+o.size[0],o.position[1],Objecto.LINE));
    }
    private void collideWithLine(Objecto o){

        if(!new Rectangle((int)position[0],(int)position[1],(int)size[0],(int)size[1]).intersectsLine(o.position[0],o.position[1],o.size[0],o.size[1])){
            return;
        }


        float lineM = (o.position[1]-o.size[1])/(o.position[0]-o.size[0]);//coefficiente angolare retta

        float momentumM = momentum[1]/momentum[0];
        adjustPosition(o);
        resolveBounce(momentumM,lineM);
    }
    //ang è il coefficiente angolare tra y/x
    public void resolveBounce(float angObj2move,float angObj){

        if(angObj-angObj2move == 0){
            return;
        }

        double momentumForce = getVelocity();
        double angBetween = Math.atan(Math.abs((angObj-angObj2move)/(angObj*angObj2move+1))); //in radiant

        double repelF = Math.sin(angBetween)* momentumForce;

        double outputX;
        double outputY;
        if(angObj == 0){
            outputX = 0;
            outputY = momentum[1];
        }else if(Double.isInfinite(angObj)){
            System.out.println("fatto qualcosa");
            outputX = momentum[0];
            outputY = 0;
        } else{
            double perpendicularM = -1/angObj;
            outputX = Math.sqrt(Math.pow(repelF,2)/(Math.pow(perpendicularM,2)+1));
            outputY = outputX * perpendicularM;
        }

        double tmpX = momentum[0] + outputX+outputX*bounce;
        double tmpY = momentum[1] + outputY+outputY*bounce;

        if(Math.sqrt(Math.pow(tmpX,2)+Math.pow(tmpY,2)) - momentumForce > 1){
            momentum[0] -= (float) (outputX+outputX*bounce);
            momentum[1] -= (float) (outputY+outputY*bounce);
        }else {
            momentum[0] += (float) (outputX+outputX*bounce);
            momentum[1] += (float) (outputY+outputY*bounce);
        }
    }


    public float[] getCenter(){
        return new float[]{position[0]+(float)size[0]/2,position[1]+(float)size[1]/2};
    }

    /*
        4-------1
        |       |
        |       |
        3-------2
    */
    public float[] getAnglePos(int anglenum){
        switch (anglenum){
            case 1: //alto destra
                return new float[]{position[0]+size[0],position[1]};
            case 2: //basso destra
                return new float[]{position[0]+size[0],position[1]+size[1]};
            case 3: //basso sinistra
                return new float[]{position[0],position[1]+size[1]};
            default: //:4 alto sinistra
                return new float[]{position[0],position[1]};
        }
    }

    private void adjustPosition(Objecto o){
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

    public String getMomentum(){
        return "["+momentum[0]+";"+momentum[1]+"]";
    }
    public String getPosition(){
        return "["+position[0]+";"+position[1]+"]";
    }
    public double getVelocity(){
        return Math.sqrt(Math.pow(momentum[0],2)+Math.pow(momentum[1],2));
    }
}

