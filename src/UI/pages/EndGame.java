package UI.pages;

import game.GameManager;
import game.Player;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EndGame extends JPanel {
    ArrayList<PlayerEntry> player_entries;
    public EndGame() {
        ArrayList<Player> players = GameManager.players;
        for (int i = 0; i < players.size(); i++) {
            PlayerEntry p = new PlayerEntry(players.get(i).name);
            player_entries.add(p);
            p.setBounds(400,100 + 50*i,500,40);
        }
        System.out.println("reload");
        repaint();

    }

    private class PlayerEntry extends JPanel{
        public Player player;
        public PlayerEntry(String name){
            setLayout(null);

        }
    }
}
