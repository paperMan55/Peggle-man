package UI;

import game.Player;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class PanelBottom extends JPanel{

    private ArrayList<PlayerLabel> playerLabels = new ArrayList<>();

    public PanelBottom(int x, int y, int width, int height){

        this.setBackground(Color.lightGray);
        this.setBounds(x,y,width,height);
    }

    public void setPlayers (ArrayList<Player> players){
        for (Player p : players){
            PlayerLabel label = new PlayerLabel(p);
            playerLabels.add(label);
            add(label);
        }
    }
    public void refresh(){
        repaint();
    }

    private static class PlayerLabel extends JPanel{
        public Player p;
        private final JLabel points;
        public PlayerLabel(Player p){
            this.p = p;
            JLabel name = new JLabel(p.name);
            points = new JLabel(p.points + "");
            add(name);
            add(points);
        }


        @Override
        public void paint(Graphics g) {
            super.paint(g);
            points.setText(p.points+"");
        }
    }
}
