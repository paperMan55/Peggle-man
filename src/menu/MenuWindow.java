package menu;

import javax.swing.*;
import java.awt.*;

public class MenuWindow extends JFrame {
    private final int HEIGHT=800;
    private final int WIDTH=900;
    private JPanel panelSelezioneModalita;
    public MenuWindow(String nameofWindow){

        super(nameofWindow);
        this.setLayout(null);
        this.setSize(this.WIDTH,this.HEIGHT);
        this.setVisible(true);
        System.out.println("ciao sono il costruttore");
        setUpPanelSelezioneModalita();

    }
    private void setUpPanelSelezioneModalita(){
        int panelWidth=300;
        int panelHeight=200;
        panelSelezioneModalita= new JPanel();
        panelSelezioneModalita.setBounds((this.WIDTH/2)-(panelWidth/2), (this.HEIGHT/2)-(panelHeight+panelWidth/3), panelWidth, panelHeight);
        panelSelezioneModalita.setBackground(Color.GRAY);
        panelSelezioneModalita.setLayout(null);
        panelSelezioneModalita.setBorder(BorderFactory.createLineBorder(Color.black, 10, true));
        addComponent(panelSelezioneModalita);
        //JLabel label1= new JLabel("PVP");
        //label1=setUpLbabelPVP(label1,);
        panelSelezioneModalita.add(setUpLbabel("PVP",  50, 100, panelHeight, panelWidth));
        
    }
    private JLabel setUpLbabel(String text,  int heightofLabel, int widthofLabel, int heightOfContainer, int widthOfContainer){
        JLabel label= new JLabel(text);

        label.setBounds(widthOfContainer/2-(widthofLabel/2-25), heightOfContainer/2-heightofLabel/2, widthofLabel, heightofLabel);
        label.setFont(new Font("serif", Font.BOLD, 30));
        return label;

    }
    private void addComponent(JComponent component){
        this.add(component);
    }

}
