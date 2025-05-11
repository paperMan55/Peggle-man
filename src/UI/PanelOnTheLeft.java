package UI;

import game.Cache;
import gamePrefabs.Ball;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelOnTheLeft extends JPanel{

    private Label2 currentName;
    private Label2 currentPoints;
    private ArrayList<BallLabel> ballLabels = new ArrayList<>();

    public PanelOnTheLeft(int x, int y, int width, int height){
        setLayout(null);

        //this.setBackground(Color.MAGENTA);
        this.setBounds(x,y,width,height);


        currentName = new Label2("","src/Images/squarePanel.png",210);
        currentName.setBounds(5,5,this.getWidth()-10,50);
        add(currentName);

        currentPoints = new Label2("","src/Images/squarePanel.png",210);
        currentPoints.setBounds(5,60,this.getWidth()-10,50);
        add(currentPoints);
        try {
            JLabel selected = new JLabel(new ImageIcon(ImageIO.read(new File("src/Images/squarePanel.png")).getScaledInstance(BallLabel.SIZE+6,BallLabel.SIZE+6,4)));
            selected.setBounds((getWidth()-BallLabel.SIZE)/2-3,147,BallLabel.SIZE+6,BallLabel.SIZE+6);
            setComponentZOrder(selected,0);
            add(selected);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setCurrentPoints(int points){
        currentPoints.setText("points:"+points);
    }

    public void setCurrentName(String currentName) {
        this.currentName.setText(currentName);
    }

    public void setBalls_left(ArrayList<Ball> balls){
        for (BallLabel b : ballLabels){
            remove(b);
        }
        for (int i = 0; i < balls.size(); i++) {
            BallLabel b = new BallLabel(balls.get(i));
            b.setLocation((getWidth()-BallLabel.SIZE)/2,150+(i*(BallLabel.SIZE+6)));
            add(b);
            ballLabels.add(b);
            setComponentZOrder(b,1);
        }
        repaint();
    }
    private static class BallLabel extends JLabel{
        private final static int SIZE = 40;
        public BallLabel(Ball ball){
            setSize(SIZE, SIZE);
            setIcon(new ImageIcon(Cache.getCachedPNG(ball.texture.currentImageName, SIZE, SIZE)));
        }
    }
}
