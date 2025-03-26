package ObjectTools;

import java.awt.*;

public class Line2 extends Objecto2{
    public Line2(float posX_1, float posY_1, float posX_2, float posY_2) {
        super(posX_1, posY_1, posX_2, posY_2);
        type = Objecto2.LINE;
    }

    public Line2(float posX_1, float posY_1, float posX_2, float posY_2, Color color) {
        super(posX_1, posY_1, posX_2, posY_2, color);
        type = Objecto2.LINE;
    }

    public Line2(float posX_1, float posY_1, float posX_2, float posY_2, Color color, boolean solid) {
        super(posX_1, posY_1, posX_2, posY_2, color, solid);
        type = Objecto2.LINE;
    }

    @Override
    public boolean collideWithCircle(Objecto2 o) {
        //I decided that lines cant move so... non need of this
        return false;
    }

    @Override
    public boolean collideWithSquare(Objecto2 o) {
        //I decided that lines cant move so... non need of this
        return false;
    }

    @Override
    public boolean collideWithLine(Objecto2 o) {
        //I decided that lines cant move so... non need of this
        return false;
    }

    @Override
    public float[] adjustPosition(Objecto2 o) {
        //I decided that lines cant move so... non need of this
        return null;
    }
    @Override
    public void onUpdate() {
    }
    @Override
    public Objecto2 getCopy() {
        Objecto2 copy = new Line2(position[0],position[1],size[0],size[1]);
        copy.type = this.type;
        copy.static_ = this.static_;
        copy.debug = this.debug;
        copy.position[0] = this.position[0];
        copy.position[1] = this.position[1];
        copy.momentum[0] = this.momentum[0];
        copy.momentum[1] = this.momentum[1];
        copy.size = this.size;
        copy.image = this.image;
        copy.drag = this.drag;
        copy.gravity = this.gravity;
        copy.bounce = this.bounce;
        copy.color = this.color;
        copy.solid = this.solid;
        return copy;
    }

	@Override
	public void onCollisionEnter(Objecto2 o) {}

    @Override
    public void onCollisionExit(Objecto2 o) {

    }

    @Override
    public void onCollisionStay(Objecto2 o) {

    }
}
