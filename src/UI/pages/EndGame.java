package UI.pages;

import UI.DynamicBorder_Panel;
import UI.UIManager;
import game.GameManager;
import game.Player;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EndGame extends JPanel {


    ArrayList<PlayerEntry> player_entries = new ArrayList<>();
    public EndGame() {

        setLayout(null);
        ArrayList<Player> players = GameManager.players;

        for (int i = 0; i < players.size(); i++) {
            PlayerEntry p = new PlayerEntry(players.get(i));
            player_entries.add(p);
            p.setBounds(400,100 + 50*i,500,40);
            this.add(p);
        }
        JButton replay = new JButton("Replay");
        replay.setBounds(100,200,200,60);
        add(replay);
        JButton main = new JButton("Main menu");
        main.setBounds(100,280,200,60);
        add(main);
        main.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.goToPage(Pages.MAIN_MENU);
            }
        });
        replay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UIManager.goToPage(Pages.GAME);
                GameManager.restart();
            }
        });

        repaint();

    }

    private class PlayerEntry extends JPanel{
        public Player player;
        public PlayerEntry(Player p){
            player = p;
            setLayout(null);
            JLabel namel = new JLabel(player.name);
            namel.setBounds(10,10,100,20);
            this.add(namel);
            JLabel pointsl = new JLabel(player.points+"");
            pointsl.setBounds(120,10,100,20);
            this.add(pointsl);
            repaint();
        }
    }
}
