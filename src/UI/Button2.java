package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Button2 extends JPanel {
    public Image image;
    public Image hoverImage;
    public Image pressedImage;
    public String text;
    private Image currentImage;
    private Image exImage;

    private JLabel background;
    private JLabel text1;
    private JLayeredPane pane;
    private Point textPosition;

    public Button2(String image_, String hoverImage_, String pressedImage_, String text, Point textPosition){
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        this.image = new ImageIcon(image_).getImage();
        this.hoverImage = new ImageIcon(hoverImage_).getImage();
        this.pressedImage = new ImageIcon(pressedImage_).getImage();
        currentImage = this.image;
        this.text = text;
        pane = new JLayeredPane();
        this.textPosition = textPosition;

        background = new JLabel(new ImageIcon(currentImage));
        text1 = new JLabel(this.text);

        pane.add(background,1);
        pane.add(text1,0);

        //add(pane);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exImage = currentImage;
                currentImage=hoverImage;
                pane.remove(1);
                pane.add(new JLabel(new ImageIcon(currentImage)),1);
                pane.revalidate();
                pane.repaint();
                pane.setVisible(false);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                exImage = currentImage;
                currentImage = pressedImage;
                pane.remove(1);
                pane.add(new JLabel(new ImageIcon(currentImage)),1);
                pane.revalidate();
                pane.repaint();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exImage = currentImage;
                currentImage=image;
                pane.remove(1);
                pane.add(new JLabel(new ImageIcon(currentImage)),1);
                pane.revalidate();
                pane.repaint();
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                onPressed();
            }
        });
        //add(this.text);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        image = image.getScaledInstance(width,height,4);
        hoverImage = hoverImage.getScaledInstance(width,height,4);
        pressedImage = pressedImage.getScaledInstance(width,height,4);
        currentImage = this.image;

        text1.setBounds(0,0,width,height);
        background.setBounds(0,0,width,height);
        pane.setBounds(0,0,width,height);
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(r);
        image = image.getScaledInstance(r.width,r.height,4);
        hoverImage = hoverImage.getScaledInstance(r.width,r.height,4);
        pressedImage = pressedImage.getScaledInstance(r.width,r.height,4);
        currentImage = this.image;

        text1.setBounds(0,0,r.width,r.height);
        background.setBounds(0,0,r.width,r.height);
        pane.setBounds(0,0,r.width,r.height);
    }

    public abstract void onPressed();


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);


        g.setPaintMode();

        g.drawImage(currentImage,0,0,this);

        Graphics2D g2 = (Graphics2D)g;
        g2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawString(text, textPosition.x, textPosition.y);
    }




}
