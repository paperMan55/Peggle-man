package map_creator;
import game.Disegno;
import game.Map;
import gamePrefabs.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;




public class Map_Tester {


static JFrame thisframe;
static Disegno thisdisegno;
static String imagename= "prova2.png";
static BufferedImage backgroundImage;
public static ArrayList<Peg> listapeg;
public Map_Tester(){
    //createWindow();
    try {
        backgroundImage= ImageIO.read(new File("src/map_creator/Images/"+imagename));
        //g.drawImage(backgroundImage, 0, 0, null);

        //System.out.println(backgroundImage.getType());
        scanningImage(backgroundImage);
        //thisdisegno.repaint();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }


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
    public static void scanningImage(BufferedImage imagetoscan){
        ArrayList<Peg> listaPeg= new ArrayList<>();/*il primo int è il colore del peg, il secondo int è la y, il terzo int è la x */

        for (int i = 0; i < imagetoscan.getHeight(); i++) {
            for (int j = 0; j < imagetoscan.getWidth(); j++) {
                int colortoscan=imagetoscan.getRGB(j,i);
                if(colortoscan==Color.WHITE.getRGB()){

                }
                else if(colortoscan==Color.BLACK.getRGB()){
                    System.out.println("Rilevato pixel nero");
                    float posX= (float) (j * Map.window_width) / imagetoscan.getWidth();
                    float posY = (float) (i*Map.window_height) / imagetoscan.getHeight();
                    listaPeg.add(new Peg(posX,posY, colortoscan));
                }
                else if (colortoscan==Color.BLUE.getRGB()){
                    System.out.println("Rilevato pixel blu");
                    float posX= (float) (j * Map.window_width) / imagetoscan.getWidth();
                    float posY = (float) (i*Map.window_height) / imagetoscan.getHeight();
                    listaPeg.add(new Peg(posX,posY, colortoscan));

                }
                else if(colortoscan==Color.RED.getRGB()){
                    System.out.println("Rilevato pixel rosso");
                    float posX= (float) (j * Map.window_width) / imagetoscan.getWidth();
                    float posY = (float) (i*Map.window_height) / imagetoscan.getHeight();
                    listaPeg.add(new Peg(posX,posY, colortoscan));
                }

            }


        }
        listapeg=listaPeg;
    }
    public static void createWindow(){
        thisframe= new JFrame("diocanvuoifunzionarediocandiocan");
        thisframe.setSize(Map.window_width, Map.window_height);
        thisdisegno = new Disegno();
        thisframe.add(thisdisegno);

        thisframe.setVisible(true);
    }

    public static ArrayList<Peg> getListapeg() {
        return listapeg;
    }

    public static Map createdefinitiveMap(ArrayList<Peg> listaPegs){

        return new Map (listaPegs);
    }
}

