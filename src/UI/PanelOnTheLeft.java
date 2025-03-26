package UI;

import java.awt.Color;

import javax.swing.*;

public class PanelOnTheLeft extends JPanel{
    private int height=0;
    private int width=0;
    private float [] position={0,0}; 
    private JLabel balls_left;

    public PanelOnTheLeft(int relativeHeight){

        this.setBackground(Color.MAGENTA);
        this.setBounds(0,0,215,relativeHeight);
        //non ancora aggiunto al frame
        init();
    }
    public PanelOnTheLeft(){

        this.setBackground(Color.MAGENTA);
        this.setBounds(0,0,215,0);
        //non ancora aggiunto al frame
        init();
    }
    public PanelOnTheLeft(int x, int y, int width, int height){

        this.height=height;
        this.width=width;
        this.position[0]=x;
        this.position[1]=y;
        this.setBackground(Color.MAGENTA);

        //215 -----width
        this.setBounds((int)position[0],(int)position[1],width,height);
        init();
        //non ancora aggiunto al frame
    }
    private void init(){
        balls_left = new JLabel();
        add(balls_left);
    }
    public void setBalls_left(int balls){
        balls_left.setText("left: "+balls);
    }
}
