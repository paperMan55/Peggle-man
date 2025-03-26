package UI.pages;

import UI.UIManager;
import game.GameManager;
import game.Player;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerSelector extends JPanel {
    private ArrayList<PlayerEntry> players = new ArrayList<>();
    public PlayerSelector(){
        this.setBounds(0,0, UI.UIManager.WINDOWS_WIDTH, UI.UIManager.WINDOWS_HEIGHT);
        setLayout(null);
        JPanel form = new JPanel();
        form.setBounds(100,100,550,40);
        form.setLayout(null);
        form.setBackground(Color.yellow);
        form.setBorder(new LineBorder(Color.red,2));


        Component label = form.add(new JLabel("nickname: "));
        label.setBounds(10,10,100,20);
        JTextField nameField = new JTextField("porco cane");
        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addPlayer(nameField.getText());
                nameField.setText("");
            }
        });
        JButton playButton = new JButton("PLAY");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!players.isEmpty()){
                    UIManager.goToPage(Pages.GAME);
                    ArrayList<Player> true_players = new ArrayList<>();
                    for (PlayerEntry p : players){
                        true_players.add(new Player(p.name));
                    }
                    GameManager.startGame(true_players);
                }

            }
        });
        playButton.setBounds(100,400,200,50);
        add(playButton);
        form.add(nameField);
        nameField.setBounds(120,10,200,20);
        Button add = new Button("add player");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addPlayer(nameField.getText());
                nameField.setText("");
            }
        });
        form.add(add);
        add.setBounds(400,10,100,20);
        System.out.println("thfuzvböohb");
        add(form);

        repaint();
    }

    private class PlayerEntry extends JPanel{
        public String name;
        public PlayerEntry(String name){
            setLayout(null);
            this.name = name;
            setBorder(new EtchedBorder());
            JLabel label = new JLabel(name);
            label.setBounds(10,10,200,20);
            add(label);
            Button delete = new Button("delete");
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removePlayer(name);
                }
            });
            delete.setBounds(250,10,100,20);
            add(delete);
        }
    }
    public void addPlayer(String name){
        int num = findPlayer(name);
        if(num == -1){
            PlayerEntry p = new PlayerEntry(name);
            players.add(p);
            add(p);
        }
        reloadList();
    }
    public void removePlayer(String name){
        int num = findPlayer(name);
        if(num != -1){
            remove(players.get(num));
            players.remove(num);
        }
        reloadList();
    }
    public int findPlayer(String name){
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).name.equals(name)){
                return i;
            }
        }
        return -1;
    }
    public void reloadList(){
        for (int i = 0; i < players.size(); i++) {
            PlayerEntry p = players.get(i);
            p.setBounds(700,100 + 50*i,500,40);
        }
        System.out.println("reload");
        repaint();
    }
}