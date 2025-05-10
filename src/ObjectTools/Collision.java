package ObjectTools;

import javax.xml.transform.Source;
import java.awt.*;

public class Collision {
    public Objecto2 main_obj;
    public Objecto2 second_obj;

    public float[] pos_adjust;
    public float[] momentum_ang;
    public float[] surface_ang;
    public static final  int ENTER = 0;
    public static final  int STAY = 1;
    public static final  int EXIT = 2;

    public Collision(Objecto2 main_obj, Objecto2 second_obj, float[] pos_adjust, float[] momentum_ang, float[] surface_normal) {
        this.main_obj = main_obj;
        this.second_obj = second_obj;
        this.pos_adjust = pos_adjust;
        this.momentum_ang = momentum_ang;
        this.surface_ang = surface_normal;


    }
    public void resolve(int type){

        if((!main_obj.static_) && main_obj.solid && second_obj.solid && type!=EXIT){
            main_obj.move(pos_adjust);
            main_obj.resolveBounce(surface_ang);
        }
        switch (type){
            case ENTER:

                main_obj.onCollisionEnter(second_obj);
                break;
            case STAY:
                main_obj.onCollisionStay(second_obj);
                break;
            case EXIT:
                main_obj.onCollisionExit(second_obj);
                break;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() == Collision.class){
            return main_obj.equals(((Collision) obj).main_obj) && second_obj.equals(((Collision) obj).second_obj);
        }
        return false;
    }
    public void register(){
        ObjectList.collisions.put(this,main_obj);
    }
}
