package UI;

import java.awt.Color;

import javax.swing.JPanel;

import game.Disegno;

public class PanelOnTheCenter extends JPanel {
    private int height=0;
    private int width=0;
    private float [] position={0,0}; 
    public Disegno disegno;

    public PanelOnTheCenter(int relativeHeight){
        //this.setBackground(Color.black);
        this.setBounds(0,0,215,relativeHeight);
        //non ancora aggiunto al frame
    }
    public PanelOnTheCenter(){
        //this.setBackground(Color.black);
        this.setBounds(0,0,215,0);
        //non ancora aggiunto al frame
    }
    public PanelOnTheCenter(int x, int y, int width, int height){
        this.height=height;
        this.width=width;
        this.position[0]=x;
        this.position[1]=y;
        //this.setBackground(Color.black);
        
        this.setBounds((int)position[0],(int)position[1],width,height);
        //non ancora aggiunto al frame
        this.setLayout(null);
        disegno= new Disegno();
        disegno.setBounds(0,0,750,750);
        this.add(disegno);
    }
    @Override
    public int getWidth() {
        // TODO Auto-generated method stub
        return this.width;
    }
    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return this.height;    }
    
}
