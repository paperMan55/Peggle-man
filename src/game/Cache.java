package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Cache {
    private static HashMap<String,ArrayList<CachedPNG>> cachedPNGS = new HashMap<>();

    public static Image getCachedPNG(String file_name, Point size){
        ArrayList<CachedPNG> pngs = cachedPNGS.get(file_name);
        CachedPNG png;
        if(pngs!=null){

            png = findSize(pngs,size);
            if(png==null){
                png = new CachedPNG(file_name,size);
                pngs.add(png);
            }
        }else {

            pngs = new ArrayList<>();
            png = new CachedPNG(file_name,size);
            pngs.add(png);
            cachedPNGS.put(file_name,pngs);
        }
        return png.image;
    }
    public static Image getCachedPNG(String file_name, int sizeX, int sizeY){
        return getCachedPNG(file_name,new Point(sizeX,sizeY));
    }
    private static CachedPNG findSize(ArrayList<CachedPNG> array, Point size){
        for (CachedPNG png:array){
            if(png.size.equals(size)){
                return png;
            }
        }
        return null;
    }


    private static class CachedPNG{
        public Point size;
        public String file_name;
        public Image image;

        public CachedPNG(String file_name, int sizeX, int sizeY){
            this.file_name = file_name;
            this.size = new Point(sizeX,sizeY);
            image = new ImageIcon(file_name).getImage().getScaledInstance(sizeX,sizeY,4);
        }
        public CachedPNG(String file_name, Point size){
            this.file_name = file_name;
            this.size = size;
            image = new ImageIcon(file_name).getImage().getScaledInstance(size.x,size.y,4);
        }
    }
}
