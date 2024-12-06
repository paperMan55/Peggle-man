import UI.UIManager;
import game.Clock;

public class Main {
    public static void main(String[] args) {

        UIManager m= new UIManager("Prova_Man");
        Clock clock = new Clock(m.panelOnTheCenter);
        clock.start();
    }
    //@TODO mettere qua clock.start e poi boh
}
