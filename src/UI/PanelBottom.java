package UI;

import java.awt.Color;

import javax.swing.*;

public class PanelBottom extends JPanel{
    private int height=0;
    private int width=0;
    private float [] position={0,0};
    private JLabel points;
    private JLabel player;

    public PanelBottom(int relativeHeight){
        this.setBackground(Color.lightGray);
        this.setBounds(0,0,215,relativeHeight);
        init();
        //non ancora aggiunto al frame
    }
    public PanelBottom(){
        this.setBackground(Color.lightGray);
        this.setBounds(0,0,215,0);
        init();
        //non ancora aggiunto al frame
    }
    public PanelBottom(int x, int y, int width, int height){
        this.height=height;
        this.width=width;
        this.position[0]=x;
        this.position[1]=y;
        this.setBackground(Color.lightGray);
        //215 -----width
        this.setBounds((int)position[0],(int)position[1],width,height);
        init();
        //non ancora aggiunto al frame
    }
    private void init(){
        points = new JLabel();
        player = new JLabel();
        this.add(points);
        this.add(player);
    }
    @Override
    public int getWidth() {
        // TODO Auto-generated method stub
        return this.width;
    }
    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return this.height;
    }
    public void setPlayer(String name){
        this.player.setText(name.toUpperCase());
    }
    public void setPoints(int points){
        this.points.setText("Points: "+points);

    }
}
