package UI.pages;

import UI.UIManager;

import javax.swing.*;
import java.awt.*;
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
        setVisible(true);
        ImageIcon imageIcon = new ImageIcon("src/Images/peggleMan_logo.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(400, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

        JLabel l= new JLabel(imageIcon);
        l.setBounds(200,200,400,100);
        add(l);

    }
}
