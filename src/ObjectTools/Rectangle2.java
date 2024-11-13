package ObjectTools;

import java.awt.*;

public class Rectangle2 extends Objecto2{
    public Rectangle2(float posX, float posY, float width, float height) {
        super(posX, posY, width, height);
        type = Objecto2.SQUARE;
    }

    public Rectangle2(float posX, float posY, float width, float height, Color color) {
        super(posX, posY, width, height, color);
        type = Objecto2.SQUARE;
    }

    public Rectangle2(float posX, float posY, float width, float height, Color color, boolean solid) {
        super(posX, posY, width, height, color, solid);
        type = Objecto2.SQUARE;
    }

    public Rectangle2(float posX, float posY, float width, float height, Color color, boolean solid, boolean static_) {
        super(posX, posY, width, height, color, solid, static_);
        type = Objecto2.SQUARE;
    }

    @Override
    public void collideWithCircle(Objecto2 o) {

    }

    @Override
    public void collideWithSquare(Objecto2 o) {

    }

    @Override
    public void collideWithLine(Objecto2 o) {

    }

    @Override
    public void adjustPosition(Objecto2 o) {

    }
}
