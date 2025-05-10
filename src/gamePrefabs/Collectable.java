package gamePrefabs;

import ObjectTools.Rectangle2;

public class Collectable extends Rectangle2 {
    public static final int SIZE = 25;
    public Ball collectable;
    public Collectable(float posX, float posY, Ball collectable) {
        super(posX, posY, SIZE, SIZE);
        this.collectable = collectable;
        texture = collectable.texture;
        solid = false;

    }
}
