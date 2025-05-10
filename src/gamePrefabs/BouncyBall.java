package gamePrefabs;

import ObjectTools.StillTexture;

public class BouncyBall extends Ball{
    public BouncyBall(float posX, float posY) {
        super(posX, posY);
        bounce = 1f;
        texture = new StillTexture("src/Images/ballMan_green.png",BALL_SIZE,BALL_SIZE);
    }
}
