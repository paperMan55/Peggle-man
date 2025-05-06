package UI;

import javax.swing.*;

import UI.pages.*;

import java.awt.*;

public class UIManager{
    public static JFrame finestraGioco;
    public static Pages currentPage = Pages.NONE;
    private static Component content = new JLabel("tmp");
    public static final int WINDOWS_HEIGHT=900;
    public static final int WINDOWS_WIDTH=1200;
    public static final int GAME_HEIGHT=550;
    public static final int GAME_WIDTH=750;

    public static void initPage(Pages mainpage){
        finestraGioco= new JFrame(mainpage.name());
        finestraGioco.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finestraGioco.setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        finestraGioco.setLayout(null);
        finestraGioco.setVisible(true);
        finestraGioco.add(content);
        goToPage(mainpage);
    }
    public static void goToPage(Pages page){
        finestraGioco.remove(content);
        currentPage = page;
        Component panel = switch (page) {
            case Pages.PLAYER_SELECTOR -> new PlayerSelector();
            case Pages.GAME -> new Game();
            case Pages.MAIN_MENU -> new MainMenu();
            case Pages.TEST -> new Test();
            case Pages.END_GAME -> new EndGame();
            default -> null;
        };
        panel.setBounds(0,0,WINDOWS_WIDTH,WINDOWS_HEIGHT);
        content = finestraGioco.add(panel);
        finestraGioco.revalidate();
        finestraGioco.repaint();
    }

    public static Point getWindowSize(){
        int x = finestraGioco.getWidth();
        int y = finestraGioco.getHeight();
        return new Point(x,y);
    }
}