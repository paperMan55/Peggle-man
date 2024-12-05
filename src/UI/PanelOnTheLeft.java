package UI;

import java.awt.Color;

import javax.swing.JPanel;

public class PanelOnTheLeft extends JPanel{
    private int height=0;
    private int width=0;
    private float [] position={0,0}; 

    public PanelOnTheLeft(int relativeHeight){
        this.setBackground(Color.MAGENTA);
        this.setBounds(0,0,215,relativeHeight);
        //non ancora aggiunto al frame
    }
    public PanelOnTheLeft(){
        this.setBackground(Color.MAGENTA);
        this.setBounds(0,0,215,0);
        //non ancora aggiunto al frame
    }
    public PanelOnTheLeft(int x, int y, int height, int width){
        this.height=height;
        this.width=width;
        this.position[0]=x;
        this.position[1]=y;
        this.setBackground(Color.MAGENTA);
        //215 -----width
        this.setBounds((int)position[0],(int)position[1],width,height);
        //non ancora aggiunto al frame
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
    
    
    
}
