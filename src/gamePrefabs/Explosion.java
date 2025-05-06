package gamePrefabs;

import ObjectTools.AnimatedTexture;
import ObjectTools.AnimationAction;
import ObjectTools.Circle2;
import ObjectTools.Objecto2;

import java.awt.*;

public class Explosion extends Circle2 {

    public Explosion(float posX, float posY, float diameter) {
        super(posX-(diameter/2), posY-(diameter/2), diameter);
        texture = new AnimatedTexture("src/Images/Explosion",new Point((int)diameter,(int)diameter),12);
        AnimatedTexture texture1 = (AnimatedTexture) texture;
        texture1.addAnimationAction(new AnimationAction() {
            @Override
            public void onEnd() {
                destroy();
            }
        });

    }
    public Explosion(float[] position, float diameter) {
        super(position[0]-(diameter/2),position[1]-(diameter/2), diameter);
        texture = new AnimatedTexture("src/Images/Explosion",new Point((int)diameter,(int)diameter),12);
        AnimatedTexture texture1 = (AnimatedTexture) texture;
        texture1.addAnimationAction(new AnimationAction() {
            @Override
            public void onEnd() {
                destroy();
            }
        });
    }
}
