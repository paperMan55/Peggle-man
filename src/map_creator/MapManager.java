package map_creator;
import UI.Disegno;
import UI.UIManager;
import gamePrefabs.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import ObjectTools.ObjectList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;




public class MapManager {


static JFrame thisframe;
static Disegno thisdisegno;
public static String imagename= "samuele_e_mario_rinco";
static BufferedImage backgroundImage;
public static ArrayList<Peg> listapegstatica;
public MapManager(){
    //createWindow();
/*
    thisframe.setContentPane(new JPanel(){
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            try {
                backgroundImage= ImageIO.read(new File("src/map_creator/Images/"+imagename));
                g.drawImage(backgroundImage, 0, 0, null);

                //System.out.println(backgroundImage.getType());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    });
 */
}
    public static void setMapFromPNG(String src){
        BufferedImage image = getImage(src);
        scanImage(image);
        for(Peg peg: listapegstatica){
            ObjectList.objects.add(peg);
        }
    }

    public static ArrayList<Peg> scanImage(BufferedImage imagetoscan){
        ArrayList<Peg> listaPeg= new ArrayList<>();/*il primo int è il colore del peg, il secondo int è la y, il terzo int è la x */

        for (int i = 0; i < imagetoscan.getHeight(); i++) {
            for (int j = 0; j < imagetoscan.getWidth(); j++) {
                int colortoscan=imagetoscan.getRGB(j,i);
                if(colortoscan==Color.WHITE.getRGB()){

                }
                else if(colortoscan==Color.BLACK.getRGB()){
                    //System.out.println("Rilevato pixel nero");
                    float posX= (float) (j * UIManager.GAME_WIDTH) / imagetoscan.getWidth();
                    float posY = (float) (i * UIManager.GAME_HEIGHT) / imagetoscan.getHeight();
                    listaPeg.add(new Peg(posX,posY, colortoscan));
                }
                else if (colortoscan==Color.BLUE.getRGB()){
                    //System.out.println("Rilevato pixel blu");
                    float posX= (float) (j * UIManager.GAME_WIDTH) / imagetoscan.getWidth();
                    float posY = (float) (i*UIManager.GAME_HEIGHT) / imagetoscan.getHeight();
                    listaPeg.add(new Peg(posX,posY, colortoscan));
                }
                    else if(colortoscan==Color.RED.getRGB()){
                       // System.out.println("Rilevato pixel rosso");
                        float posX= (float) (j * UIManager.GAME_WIDTH) / imagetoscan.getWidth();
                        float posY = (float) (i*UIManager.GAME_HEIGHT) / imagetoscan.getHeight();
                        listaPeg.add(new Peg(posX,posY, colortoscan));
                    }

            }


        }
        listapegstatica=listaPeg;
        return listaPeg;
    }
    public static void createWindow(){
        thisframe= new JFrame("diocanvuoifunzionarediocandiocan");
        thisframe.setSize(UIManager.WINDOWS_WIDTH, UIManager.WINDOWS_HEIGHT);
        
        thisdisegno = new Disegno();
        thisframe.add(thisdisegno);

        thisframe.setVisible(true);
    }

    public static ArrayList<Peg> getListapeg() {
        return listapegstatica;
    }

    public static BufferedImage getImage(String nameofImage){
        try {
            backgroundImage= ImageIO.read(new File("src/Images/"+nameofImage+".png"));
            //g.drawImage(backgroundImage, 0, 0, null);
    
            //System.out.println(backgroundImage.getType());
            
            //thisdisegno.repaint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return backgroundImage;
    
    }
}

