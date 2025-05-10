package gamePrefabs;

import ObjectTools.Line2;
import UI.UIManager;

public class BottomTrigger extends Line2 {
    public BottomTrigger() {
        super(-1000, 4000, UIManager.GAME_WIDTH, UIManager.GAME_HEIGHT);
        static_ = true;
        solid = false;
    }
}
