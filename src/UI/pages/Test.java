package UI.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JPanel {

    JTextField t1;
    JTextField t2;
    JTextField t3;
    JTextField t4;
    double[] out = new double[]{1,1};
    public  Test(){
        setLayout(null);
        setBounds(0,0,500,500);
        t1 = new JTextField("1");
        t1.setBounds(20,20,50,20);
        add(t1);
        t2 = new JTextField("1");
        t2.setBounds(90,20,50,20);
        add(t2);
        t3 = new JTextField("1");
        t3.setBounds(20,60,50,20);
        add(t3);
        t4 = new JTextField("1");
        t4.setBounds(90,60,50,20);
        add(t4);
        JButton b = new JButton("reload");
        b.setBounds(20,100,100,20);
        add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        resolveBounce();
        super.paint(g);
        if(t1.getText().isEmpty() || t2.getText().isEmpty() ||t3.getText().isEmpty() ||t4.getText().isEmpty() ){
            System.out.println("nope");
            return;
        }
        double[] v1 = new double[]{Double.parseDouble(t1.getText()),Double.parseDouble(t2.getText())};
        double[] v2 = new double[]{Double.parseDouble(t3.getText()),Double.parseDouble(t4.getText())};
        v1 = vector2Normalize(v1);
        v2 = vector2Normalize(v2);
        g.setColor(Color.GREEN);
        g.drawLine(300,100,(int)(300+v1[0]*100),(int)(100+v1[1]*100));
        g.setColor(Color.BLACK);
        g.drawLine(300,100,(int)(300+v2[0]*100),(int)(100+v2[1]*100));
        g.setColor(Color.RED);
        g.drawLine(300,100,(int)(300+out[0]*100),(int)(100+out[1]*100));

    }



    public static double[] vector2Normalize(double[] v) {
        if(v.length > 2) return null;
        double[] result = new double[v.length];
        double sum = Math.sqrt(v[0]*v[0] + v[1]*v[1]);
        result[0] = v[0] / sum;
        result[1] = v[1] / sum;
        return result;
    }
    public void resolveBounce(){
        double ang = Math.atan2(Double.parseDouble( t2.getText()),Double.parseDouble( t1.getText()));
        ang = Math.toDegrees(ang);

        double ang1 = Math.atan2(Double.parseDouble( t4.getText()),Double.parseDouble( t3.getText()));
        ang1 = Math.toDegrees(ang1);

        if(ang<0){
            ang = 360 + ang;
        }
        if(ang1<0){
            ang1 = 360 + ang1;
        }
        System.out.println("sup1: "+ang);
        System.out.println("sup2: "+ang1);
        double ang3 = 0;
        double tmp = (ang+180)%360;
        double diff = Math.abs(tmp-ang1);
        System.out.println(tmp+ " - "+ ang1 +" = "+ diff);
        if(tmp>ang1){
            ang3 = tmp-(2*diff);
        }else{
            ang3 = tmp+(2*diff);
        }
        System.out.println("out: "+ang3);
        ang3 = Math.toRadians(ang3);
        System.out.println("["+Math.cos(ang3)+" ; "+ Math.sin(ang3)+"]");
        out = new double[]{Math.cos(ang3),Math.sin(ang3)};
        System.out.println("--------------------");
    }
}
