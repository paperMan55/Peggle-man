package UI;

import javax.swing.*;
import java.awt.*;

public class Label2 extends JPanel {
    private JLabel text = new JLabel();
    private DynamicBorder_Panel b;
    private Image background;

    public Label2(String text){
        setLayout(null);
        add(this.text);
        this.text.setText(text);
        this.text.setHorizontalAlignment(SwingConstants.CENTER);
        setComponentZOrder(this.text,0);
    }
    public Label2(String text, String background_source,int borderWidth){
        setLayout(null);
        this.text.setText(text);

        add(this.text);
        this.text.setHorizontalAlignment(SwingConstants.CENTER);
        b = new DynamicBorder_Panel(background_source,borderWidth);
        add(b);
        setComponentZOrder(b,1);
        setComponentZOrder(this.text,0);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        text.setBounds(0,0,width,height);
        b.setBounds(0,0,width,height);

    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(r);
        text.setBounds(0,0,r.width,r.height);
        b.setBounds(0,0,r.width,r.height);
    }

    public void setText(String text) {
        this.text.setText(text);
    }
}
