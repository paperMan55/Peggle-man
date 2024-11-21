package ObjectTools;

public class Collision {
    public Objecto2 main_obj;
    public Objecto2 second_obj;

    public float[] pos_adjust;
    public float momentum_ang;
    public float surface_ang;

    public Collision(Objecto2 main_obj, Objecto2 second_obj, float[] pos_adjust, float momentum_ang, float surface_ang) {
        this.main_obj = main_obj;
        this.second_obj = second_obj;
        this.pos_adjust = pos_adjust;
        this.momentum_ang = momentum_ang;
        this.surface_ang = surface_ang;

        ObjectList.collisions.add(this);
    }
    public void resolve(){
        main_obj.move(pos_adjust);
        main_obj.resolveBounce(momentum_ang,surface_ang);
        main_obj.onCollisionEnter(second_obj);
    }
}
