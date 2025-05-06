package ObjectTools;

import game.Cache;

import java.awt.*;

public class StillTexture extends Texture{
    public StillTexture(String source, Point size){
        currentImage = Cache.getCachedPNG(source,size);
    }
    public StillTexture(String source, int sizeX,int sizeY){
        currentImage = Cache.getCachedPNG(source,new Point(sizeX,sizeY));
    }
    public StillTexture(Image image){
        currentImage = image;
    }

    @Override
    public Image getCurrentImage() {
        return currentImage;
    }
}
