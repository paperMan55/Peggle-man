package ObjectTools;

import java.awt.*;

public abstract class Texture {
    protected Image currentImage;
    public String currentImageName;

    public abstract Image getCurrentImage();
}
