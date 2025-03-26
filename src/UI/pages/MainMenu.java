package UI.pages;

import UI.UIManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    public MainMenu() {
        this.setLayout(null);
        System.out.println("ciao");
        setBounds(0,0, UI.UIManager.WINDOWS_WIDTH, UI.UIManager.WINDOWS_HEIGHT);
        JButton b = new JButton("toGame");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.goToPage(Pages.PLAYER_SELECTOR);
            }
        });
        b.setBounds(0,0,200,200);
        add(b);
        JLabel t = new JLabel("ajgnjearaen");
        t.setBounds(300,300,300,300);
        t.setVisible(true);
        add(t);
        setVisible(true);


    }
}
