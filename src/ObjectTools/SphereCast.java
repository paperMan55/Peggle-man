package ObjectTools;

import java.awt.*;
import java.util.ArrayList;

public class SphereCast{

    private float[] position;
    private float size;

    public SphereCast(float posX, float posY, float range) {
        position = new float[]{posX-range,posY-range};
        size = range*2;
    }
    public SphereCast(float[] position, float range) {
        this.position = new float[]{position[0]-range,position[1]-range};
        size = range*2;
    }


    public ArrayList<Objecto2> overlap(){
        ArrayList<Objecto2> objs = new ArrayList<>();
        for (Objecto2 o:ObjectList.objects) {
                boolean b;
                switch (o.type){
                    case Objecto2.LINE:
                        b = collideWithLine(o);
                        break;
                    case Objecto2.OVAL:
                        b= collideWithCircle(o);
                        break;
                    default:
                        b= collideWithSquare(o);
                }
                if(b){
                    objs.add(o);
                }

        }
        return objs;
    }

    public boolean collideWithCircle(Objecto2 o) {
        if(o.size[0] == o.size[1]){
            double distance = Math.sqrt(Math.pow(getCenter()[0]-o.getCenter()[0],2)+Math.pow(getCenter()[1]-o.getCenter()[1],2));
            if(distance<=size/2+o.size[0]/2){
                return true;
            }
        }
        return false;
    }

    public boolean collideWithSquare(Objecto2 o) {

        Rectangle this_ = new Rectangle((int)Math.round(position[0]),(int)Math.round(position[1]),(int)size,(int)size);
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

    public boolean collideWithLine(Objecto2 o) {


        if(!new Rectangle((int)position[0],(int)position[1],(int)size,(int)size).intersectsLine(o.position[0],o.position[1],o.size[0],o.size[1])){
            return false;
        }

        float[] toAdjust = adjustPosition(o);
        if(toAdjust == null){
            return false;
        }

        return true;
    }
    public float[] getCenter(){
        return new float[]{position[0]+(float)size/2,position[1]+(float)size/2};
    }
    public float[] adjustPosition(Objecto2 o) {
        float[] center = getCenter();

        float lineM = (o.position[1]-o.size[1])/(o.position[0]-o.size[0]);//coefficiente angolare retta
        float lineQ = o.position[1]-lineM*o.position[0];//termine noto retta
        //System.out.println(getVelocity() + " [ENTER] --> "+getMomentum()+"; "+lineM+"x+"+lineQ);
        float distance;
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
        if(Math.abs(distance)>size/2){
            return null; // vuol dire che il quadrato interseca ma non il cerchio
        }

        return new float[]{1,1};  // a caso, mi basta che non sia null
    }
}
