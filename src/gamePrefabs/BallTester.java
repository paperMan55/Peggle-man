package gamePrefabs;

import ObjectTools.Circle2;
import UI.UIManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallTester extends Circle2 {
    public BallTester() {
        super(0, 0, 100);
        UIManager.finestraGioco.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        System.out.println("aaaaaaaaaaaaaaaa");
                        switch (e.getKeyChar()){
                            case 'w':
                                move(0,-10);
                                break;
                            case 'a':
                                move(-10,0);
                                break;
                            case 's':
                                move(0,10);
                                break;
                            case 'd':
                                move(10,0);
                                break;
                        }
                    }
                }
        );
    }
}
