package UI.pages;

import UI.Button2;
import UI.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JLayeredPane {
    public MainMenu() {
        this.setLayout(null);


        setBounds(0,0, UI.UIManager.WINDOWS_WIDTH, UI.UIManager.WINDOWS_HEIGHT);
        Button2 b1 = new Button2("src/Images/buttonMan.png","src/Images/buttonMan_hover.png","src/Images/buttonMan_pressed.png","GIOCA",new Point(170,47)) {
            @Override
            public void onPressed() {

                UIManager.goToPage(Pages.PLAYER_SELECTOR);

            }
        };
        b1.setBounds((UIManager.WINDOWS_WIDTH/2)-200,(UIManager.WINDOWS_HEIGHT/2)-40,400,80);
        add(b1,0);

        setBackground();
        setTitle();

        setVisible(true);

    }
    public void setBackground(){

        ImageIcon imageIcon = new ImageIcon("src/Images/peggleMan_background.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(UIManager.WINDOWS_WIDTH, UIManager.WINDOWS_HEIGHT,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        JLabel l = new JLabel(imageIcon);
        l.setBounds(0,0,UIManager.WINDOWS_WIDTH,UIManager.WINDOWS_HEIGHT);
        add(l,2);
    }
    public void setTitle(){

        ImageIcon imageIcon = new ImageIcon("src/Images/peggleMan_logo.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(400, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

        JLabel l= new JLabel(imageIcon);
        l.setBounds((UIManager.WINDOWS_WIDTH/2)-250,30,500,130);
        add(l,1);
    }
}
