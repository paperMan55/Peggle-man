package UI;

import javax.swing.*;
import java.awt.*;

public class Label2 extends JPanel {
    private JLabel text = new JLabel();
    private JLabel b = new JLabel();
    private Image background;

    public Label2(String text){
        setLayout(null);
        add(this.text);
        this.text.setText(text);
        this.text.setHorizontalAlignment(SwingConstants.CENTER);
        add(b);
        setComponentZOrder(b,1);
        setComponentZOrder(this.text,0);
    }
    public Label2(String text, ImageIcon background){
        setLayout(null);
        this.text.setText(text);
        this.background = background.getImage();
        add(this.text);
        this.text.setHorizontalAlignment(SwingConstants.CENTER);
        add(b);
        setComponentZOrder(b,1);
        setComponentZOrder(this.text,0);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        text.setBounds(0,0,width,height);
        b.setBounds(0,0,width,height);
        if(background == null)return;
        background = background.getScaledInstance(width,height,4);
        b.setIcon(new ImageIcon(background));
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(r);
        text.setBounds(0,0,r.width,r.height);
        b.setBounds(0,0,r.width,r.height);
        if(background == null)return;
        background = background.getScaledInstance(r.width,r.height,4);
        b.setIcon(new ImageIcon(background));
    }

    public void setText(String text) {
        this.text.setText(text);
    }
}
