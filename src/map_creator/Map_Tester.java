package map_creator;
import game.Disegno;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Map_Tester {
final static int window_lenght=900;
final static int window_height=900;

static JFrame thisframe;
static Disegno thisdisegno;
static String imagename= "prova2.png";
static BufferedImage backgroundImage;

    public static void main(String[] args) {
        createWindow();

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
                    scanningImage(backgroundImage);

                }
            });

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
                    listaPeg.add(new Peg(j,i, colortoscan));
                }
                else if (colortoscan==Color.BLUE.getRGB()){
                    System.out.println("Rilevato pixel blu");
                    listaPeg.add(new Peg(j,i, colortoscan));

                }
                else if(colortoscan==Color.RED.getRGB()){
                    System.out.println("Rilevato pixel rosso");
                    listaPeg.add(new Peg(j,i, colortoscan));

                }

            }
        }
    }
    public static void createWindow(){
        thisframe= new JFrame("diocanvuoifunzionarediocandiocan");
        thisframe.setSize(window_lenght, 900);
        thisdisegno = new Disegno(new ArrayList<>());
        thisframe.add(thisdisegno);

        thisframe.setVisible(true);
    }

}
