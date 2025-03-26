package UI;

import javax.swing.*;

import UI.pages.Game;
import UI.pages.MainMenu;
import UI.pages.Pages;
import UI.pages.PlayerSelector;

import java.awt.*;

public class UIManager{
    public static JFrame finestraGioco;
    public static Pages currentPage = Pages.NONE;
    private static Component content = new JLabel("tmp");
    public static final int WINDOWS_HEIGHT=900;
    public static final int WINDOWS_WIDTH=1200;
    public static final int GAME_HEIGHT=550;
    public static final int GAME_WIDTH=750;

    public static void initPage(String nameofFrame){
        finestraGioco= new JFrame(nameofFrame);
        finestraGioco.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finestraGioco.setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        finestraGioco.setLayout(null);
        finestraGioco.setVisible(true);
        finestraGioco.add(content);
        goToPage(Pages.MAIN_MENU);
    }
    public static void goToPage(Pages page){
        finestraGioco.remove(content);
        currentPage = page;
        switch (page){
            case Pages.PLAYER_SELECTOR:
                content = finestraGioco.add( new PlayerSelector());
                break;
            case Pages.GAME:
                content = finestraGioco.add( new Game());
                break;
            case Pages.MAIN_MENU:
                content = finestraGioco.add( new MainMenu());
                break;
        }
        finestraGioco.revalidate();
        finestraGioco.repaint();
    }
}