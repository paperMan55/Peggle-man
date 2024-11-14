package ObjectTools;

import java.awt.*;

public class Line2 extends Objecto2{
    public Line2(float posX_1, float posY_1, float posX_2, float posY_2) {
        super(posX_1, posY_1, posX_2, posY_2);
    }

    public Line2(float posX_1, float posY_1, float posX_2, float posY_2, Color color) {
        super(posX_1, posY_1, posX_2, posY_2, color);
    }

    public Line2(float posX_1, float posY_1, float posX_2, float posY_2, Color color, boolean solid) {
        super(posX_1, posY_1, posX_2, posY_2, color, solid);
    }

    @Override
    public void collideWithCircle(Objecto2 o) {
        //I decided that lines cant move so... non need of this
    }

    @Override
    public void collideWithSquare(Objecto2 o) {
        //I decided that lines cant move so... non need of this
    }

    @Override
    public void collideWithLine(Objecto2 o) {
        //I decided that lines cant move so... non need of this
    }

    @Override
    public void adjustPosition(Objecto2 o) {
        //I decided that lines cant move so... non need of this
    }
}
