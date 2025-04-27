package game;

import UI.UIManager;
import UI.pages.Game;
import UI.pages.Pages;
import UI.pages.PlayerSelector;

import java.util.ArrayList;

public class GameManager {
    public static ArrayList<Player> players= new ArrayList<>();
    public static boolean canShoot = true;
    public static int currentPlayer;
    public static int turnPoints = 0;

    public static void startGame(ArrayList<Player> players){
        GameManager.players=players;
        currentPlayer = 0;
        turnPoints = 0;
        Game.panelbottom.setPlayer(players.get(currentPlayer).name);
        setBalls(3);
    }
    public static Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    public static void addPoints(int points){
        turnPoints += points;
        Game.panelbottom.setPoints(turnPoints);
    }
    private static void giveBalls(int balls){
        for (Player p : players){
            p.balls += balls;
        }
    }
    private static void setBalls(int balls){
        for (Player p : players){
            p.balls = balls;
        }
    }

    public static void endTurn(int combo){
        canShoot = true;
        if(players.get(currentPlayer).bestCombo<=combo){
            players.get(currentPlayer).bestCombo = combo;
        }
        players.get(currentPlayer).points += turnPoints;
        turnPoints = 0;
        currentPlayer++;
        if(currentPlayer == players.size()){
            currentPlayer = 0;
        }
        Game.panelbottom.setPlayer(getCurrentPlayer().name);
        Game.panelontheleft.setBalls_left(getCurrentPlayer().balls);
        if(checkEndGame()){
            UIManager.goToPage(Pages.END_GAME);
        }
    }
    public static void restart(){
        currentPlayer = 0;
        turnPoints = 0;
        Game.panelbottom.setPlayer(players.get(currentPlayer).name);
        setBalls(3);
        for(Player p : players){
            p.points = 0;
        }
    }
    public static boolean checkEndGame(){
        for(Player p:players){
            if(p.balls>0){
                return false;
            }
        }
        return true;
    }
}