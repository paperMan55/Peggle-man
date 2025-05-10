package ObjectTools;

import game.Clock;
import org.w3c.dom.Text;

import java.awt.*;

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
    public Texture texture;
    public float drag;
    public float gravity;
    public float bounce;
    public Color color = Color.black;
    public boolean solid = true;


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
        
        bounce = 0f;
        this.static_ = static_;
        this.color = color;
        this.solid = solid;//hello
        
    }
    public Objecto2(int posX, int posY, float width, float height, Texture texture){
        this.texture = texture;
        position = new float[]{posX, posY};
        size = new float[]{width,height};
        momentum = new float[]{0,0};
    }

    public void addForce(double xForce, double yForce){
        momentum[0] += (float) xForce;
        momentum[1] += (float) yForce;
    }
    public void addForce(double[] force){
        addForce(force[0],force[1]);
    }

    public void update(){

        if (!static_){
            momentum[0] -= momentum[0] * (drag);
            momentum[1] -= (float) (momentum[1] * (drag) - gravity* Clock.deltaTime* 100);

            this.position[0] += (float) (momentum[0]* Clock.deltaTime);
            this.position[1] += (float) (momentum[1]* Clock.deltaTime);
        }

        checkCollision();
        onUpdate();
    }
    public void checkCollision(){
        for (Objecto2 o:ObjectList.objects) {
            if(!o.equals(this)){
                Collision c = switch (o.type) {
                    case Objecto2.LINE -> collideWithLine(o);
                    case Objecto2.OVAL -> collideWithCircle(o);
                    default -> collideWithSquare(o);
                };
                if(c!=null){
                    c.register();
                }
            }
        }
    }
    public void setDrag(float drag){
        this.drag = drag;
    }

    public abstract Collision collideWithCircle(Objecto2 o);
    public abstract Collision collideWithSquare(Objecto2 o);
    public abstract Collision collideWithLine(Objecto2 o);
    //ang è il coefficiente angolare tra y/x

    public void resolveBounce(float[] normal){
        if(getVelocity()==0){
            return;
        }
        double ang = Math.atan2(momentum[1],momentum[0] );
        ang = Math.toDegrees(ang);

        double ang1 = Math.atan2(normal[1],normal[0]);
        ang1 = Math.toDegrees(ang1);

        if(ang<0){
            ang = 360 + ang;
        }
        if(ang1<0){
            ang1 = 360 + ang1;
        }
        if((ang>ang1-90 && ang<ang1+90)){
            return; //se sta andando nella direzione della normale non sta sbattendo
        }
        double ang3 = 0;
        double tmp = (ang+180)%360;
        double diff = Math.abs(tmp-ang1);
        double force_to_apply = Math.abs(getVelocity()*Math.cos(Math.toRadians(diff)));
        if(tmp>ang1){
            ang3 = tmp-(2*diff);
        }else{
            ang3 = tmp+(2*diff);
        }

        ang3 = Math.toRadians(ang3);
        double v = getVelocity();
        /*
        momentum[0] = (float)(Math.cos(ang3) * v)*bounce;
        momentum[1] = (float)(Math.sin(ang3) * v)*bounce;
         */

        float forceX = (float)(force_to_apply * Math.cos(Math.toRadians(ang1)));
        float forceY = (float)(force_to_apply * Math.sin(Math.toRadians(ang1)));
        momentum[0] += forceX+forceX*bounce;
        momentum[1] += forceY+forceY*bounce;


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

    public abstract float[] adjustPosition(Objecto2 o);

    public  void onCollisionEnter(Objecto2 o){};
    public  void onCollisionExit(Objecto2 o){};
    public  void onCollisionStay(Objecto2 o){};

    public String getMomentum(){
        return "["+momentum[0]+";"+momentum[1]+"]";
    }
    public String getPosition(){
        return "["+position[0]+";"+position[1]+"]";
    }
    public double getVelocity(){
        return Math.sqrt(Math.pow(momentum[0],2)+Math.pow(momentum[1],2));
    }

    public void destroy(){
        ObjectList.removeObject(this);

    }
    public void destroy(int millis) {
        Objecto2 o = this;
        new Thread(() -> {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ObjectList.removeObject(o);
            System.out.println("destroyed");

        }).start();
    }
    public void move(float[] toMove){
        this.position[0] += toMove[0];
        this.position[1] += toMove[1];
    }
    public void onUpdate(){};
    public void onDestroy(boolean forced){};
    public void move(float x, float y){
        this.position[0] += x;
        this.position[1] += y;
    }
    public abstract Objecto2 getCopy();
}

