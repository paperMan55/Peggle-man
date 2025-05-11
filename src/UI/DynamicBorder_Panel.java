package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DynamicBorder_Panel extends JPanel {
    private Image background;
    public int borderWidth;
    private final String source;

    public DynamicBorder_Panel(String background_source, int borderWidth){
        this.borderWidth = borderWidth;
        source = background_source;
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if (source == null || width ==0 || height == 0) {
            return;
        }
        try {
            generateBackground();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(r);
        if (source == null || r.width ==0 || r.height == 0) {
            return;
        }
        try {
            generateBackground();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //all this shit just to maintain border proportions
    private void generateBackground() throws IOException {
        BufferedImage biOut = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D out = biOut.createGraphics();
        BufferedImage in = ImageIO.read(new File(source));
        float rateoY = (float) in.getHeight() /borderWidth;
        int dest = (int)(getWidth()/rateoY); // destination border width
        int destY = (int)(getHeight()/rateoY);
        if(destY<dest) dest = destY;

        int inX = in.getWidth();
        int inY = in.getHeight();
        out.drawImage(in.getSubimage(0,0,borderWidth,borderWidth).getScaledInstance(dest,dest,Image.SCALE_SMOOTH),0,0,this);//up-left corner
        out.drawImage(in.getSubimage(inX-borderWidth,0,borderWidth,borderWidth).getScaledInstance(dest,dest,Image.SCALE_SMOOTH),getWidth()-dest,0,this); //up-right corner
        out.drawImage(in.getSubimage(0,inY-borderWidth,borderWidth,borderWidth).getScaledInstance(dest,dest,Image.SCALE_SMOOTH),0,getHeight()-dest,this);//low-left corner
        out.drawImage(in.getSubimage(inX-borderWidth,inY-borderWidth,borderWidth,borderWidth).getScaledInstance(dest,dest,Image.SCALE_SMOOTH),getWidth()-dest,getHeight()-dest,this);//low-right corner
        out.drawImage(in.getSubimage(borderWidth,0,inX-2*borderWidth,borderWidth).getScaledInstance(getWidth()-2*dest,dest,Image.SCALE_SMOOTH),dest,0,this);//up corner
        out.drawImage(in.getSubimage(inX-borderWidth,borderWidth,borderWidth,inY-2*borderWidth).getScaledInstance(dest,getHeight()-2*dest,Image.SCALE_SMOOTH),getWidth()-dest,dest,this); //right border
        out.drawImage(in.getSubimage(borderWidth,inY-borderWidth,inX-2*borderWidth,borderWidth).getScaledInstance(getWidth()-2*dest,dest,Image.SCALE_SMOOTH),dest,getHeight()-dest,this);//low border
        out.drawImage(in.getSubimage(0,borderWidth,borderWidth,inY-2*borderWidth).getScaledInstance(dest,getHeight()-2*dest,Image.SCALE_SMOOTH),0,dest,this); //left border
        out.drawImage(in.getSubimage(borderWidth,borderWidth,inX-2*borderWidth,inY-2*borderWidth).getScaledInstance(getWidth()-2*dest,getHeight()-2*dest,Image.SCALE_SMOOTH),dest,dest,this);
        out.dispose();
        background = biOut;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background,0,0,this);
    }
}
