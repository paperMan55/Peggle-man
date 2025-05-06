package ObjectTools;

import game.Cache;
import game.Clock;

import java.awt.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
/*
  @USAGE: bisogna preparare una cartella di png chiamati nel seguente modo:
            .../nome.*numero frame*.png
 */
public class AnimatedTexture extends Texture{
    public int frame_rate;
    public int current_frame = 0;
    private double exFrame = 0;
    private ArrayList<AnimationAction> actions = new ArrayList<>();

    private Image[] frames;
    public AnimatedTexture(String source_folder,Point size, int frame_rate)  {
        init(source_folder,size,frame_rate);
    }
    public AnimatedTexture(String source_folder,Point size){
        init(source_folder,size,12);
    }

    private void init(String source_folder,Point size, int frame_rate){
        this.frame_rate = frame_rate;
        ArrayList<String> sources = new ArrayList<>();
        try {
            DirectoryStream<Path> d = Files.newDirectoryStream(Path.of(source_folder));

            for (Path path:d){
                String name = path.toString();
                if(isImage(name)){
                    int n = getImageNumber(name);
                    int i = 0;
                    while (i<sources.size() &&  getImageNumber(sources.get(i)) < n ){
                        i++;
                    }
                    sources.add(name);
                }
            }
            frames = new Image[sources.size()];
            for (int i = 0; i < frames.length; i++) {
                frames[i] = Cache.getCachedPNG(sources.get(i),size);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean isImage(String path){
        return path.endsWith(".png") || path.endsWith(".jpg");
    }
    private int getImageNumber(String name){
        String[] strings = name.split("\\.");

        return Integer.parseInt(strings[strings.length-2]);
    }
    @Override
    public Image getCurrentImage() {
        exFrame+= Clock.deltaTime;
        if(exFrame>= 1.0 /frame_rate){
            exFrame = 0;
            current_frame++;
            if(current_frame == 0){
                for (AnimationAction animationAction:actions){
                    animationAction.onStart();
                }
            }else if(current_frame==frames.length){
                for (AnimationAction animationAction:actions){
                    animationAction.onEnd();
                }
                current_frame = 0;
            }
            for (AnimationAction animationAction:actions){
                animationAction.onFrame(current_frame);
            }
            currentImage = frames[current_frame];
        }
        return currentImage;
    }
    public void addAnimationAction(AnimationAction animationAction){
        actions.add(animationAction);
    }
    public void removeAnimationAction(AnimationAction animationAction){
        actions.remove(animationAction);
    }
    public void removeAllAnimationActions(){
        actions.clear();
    }
}
