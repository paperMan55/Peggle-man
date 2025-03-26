package gamePrefabs;

import ObjectTools.Line2;
import UI.UIManager;

public class BottomTrigger extends Line2 {
    public BottomTrigger() {
        super(0, UIManager.GAME_HEIGHT, UIManager.GAME_WIDTH, UIManager.GAME_HEIGHT);
        static_ = true;
        solid = false;
    }
}
