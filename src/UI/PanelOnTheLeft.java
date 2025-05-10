package UI;

import game.Cache;
import gamePrefabs.Ball;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelOnTheLeft extends JPanel{

    private Label2 balls_left;
    private ArrayList<BallLabel> ballLabels = new ArrayList<>();

    public PanelOnTheLeft(int x, int y, int width, int height){
        setLayout(null);

        //this.setBackground(Color.MAGENTA);
        this.setBounds(x,y,width,height);


        balls_left = new Label2("",new ImageIcon("src/Images/squarePanel.png"));
        balls_left.setBounds(0,0,100,40);
        add(balls_left);
        try {
            JLabel selected = new JLabel(new ImageIcon(ImageIO.read(new File("src/Images/squarePanel.png")).getScaledInstance(BallLabel.SIZE+6,BallLabel.SIZE+6,4)));
            selected.setBounds(47,97,BallLabel.SIZE+6,BallLabel.SIZE+6);
            setComponentZOrder(selected,0);
            add(selected);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setBalls_left(ArrayList<Ball> balls){
        balls_left.setText("left: "+balls.size());
        for (BallLabel b : ballLabels){
            remove(b);
        }
        for (int i = 0; i < balls.size(); i++) {
            BallLabel b = new BallLabel(balls.get(i));
            b.setLocation(50,100+(i*(BallLabel.SIZE+6)));
            add(b);
            ballLabels.add(b);
            setComponentZOrder(b,1);
        }
        repaint();
    }
    private class BallLabel extends JLabel{
        private final static int SIZE = 40;
        public BallLabel(Ball ball){
            setSize(SIZE, SIZE);
            setIcon(new ImageIcon(Cache.getCachedPNG(ball.texture.currentImageName, SIZE, SIZE)));
        }
    }
}
