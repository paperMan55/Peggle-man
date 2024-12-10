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
	public void onCollisionEnter(Objecto2 o) {}
}
