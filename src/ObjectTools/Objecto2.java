package ObjectTools;

import game.Clock;

import java.awt.*;

import java.util.ArrayList;
import java.lang.*;

public abstract class Objecto2 {

    public static final String SQUARE = "square";
    public static final String OVAL = "oval";
    public static final String LINE = "line";
    public static final String IMAGE = "image";

    public String type;
    public boolean static_ = true;
    public boolean debug = false;
    public float[] position;
    public float[] momentum;
    public float[] size;
    public Image image;
    public float drag;
    public float gravity;
    public float bounce;
    public Color color;
    public boolean solid;
    private ArrayList<Objecto2> objs;

    //questa classe permetterà una gestione facilitata degli oggetti con attributi tipo posizione e grandezza
    public Objecto2(float posX, float posY, float width, float height) {
        init(posX,posY,width,height,new Color(0x000000),true,true);
    }
    public Objecto2(float posX, float posY, float width, float height, Color color){
        init(posX,posY,width,height,color,true, true);
    }
    public Objecto2(float posX, float posY, float width, float height, Color color,boolean solid){
        init(posX,posY,width,height,color,solid,true);
    }
    public Objecto2(float posX, float posY, float width, float height, Color color,boolean solid, boolean static_){
        init(posX,posY,width,height,color,solid,static_);
    }
    private void init(float posX, float posY, float width, float height,Color color,boolean solid, boolean static_){
        position = new float[]{posX, posY};
        size = new float[]{width,height};
        momentum = new float[]{0,0};
        drag = 1;
        gravity = 0f;
        objs = ObjectList.objects;
        
        bounce = 0f;
        this.static_ = static_;
        this.color = color;
        this.solid = solid;
    }
    public Objecto2(int posX, int posY, float width, float height, Image image){
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

        this.position[0] += (float) (momentum[0]* Clock.deltaTime);
        this.position[1] += (float) (momentum[1]*Clock.deltaTime);

        ArrayList<Integer> cols = new ArrayList<>();

        if(solid){
            checkCollision();
        }
    }
    public void checkCollision(){
        for (Objecto2 o:objs) {
            if(!o.equals(this)){
                switch (o.type){
                    case Objecto2.LINE:
                        collideWithLine(o);
                        break;
                    case Objecto2.OVAL:
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
    public abstract void collideWithCircle(Objecto2 o);
    public abstract void collideWithSquare(Objecto2 o);
    public abstract void collideWithLine(Objecto2 o);
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

    public abstract void adjustPosition(Objecto2 o);

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

