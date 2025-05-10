package ObjectTools;

import game.Cache;

import java.awt.*;

public class StillTexture extends Texture{
    public StillTexture(String source, Point size){
        currentImage = Cache.getCachedPNG(source,size);
        currentImageName=source;
    }
    public StillTexture(String source, int sizeX,int sizeY){
        currentImage = Cache.getCachedPNG(source,new Point(sizeX,sizeY));
        currentImageName=source;
    }

    @Override
    public Image getCurrentImage() {
        return currentImage;
    }
}
