import UI.UIManager;
import game.Clock;
import menu.MenuManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Main {
    public static void main(String[] args) {

        UIManager m= new UIManager("Prova_Man");
        Clock clock = new Clock(m.panelOnTheCenter);
        clock.start();






        //MenuManager menuManager= new MenuManager();
    }
    //@TODO mettere qua clock.start e poi boh
}
