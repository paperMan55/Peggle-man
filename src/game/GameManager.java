package game;

import ObjectTools.ObjectList;
import UI.UIManager;
import UI.pages.Game;
import UI.pages.Pages;
import UI.pages.PlayerSelector;
import gamePrefabs.*;
import map_creator.MapManager;

import java.util.ArrayList;

public class GameManager {
    public static ArrayList<Player> players= new ArrayList<>();
    public static boolean canShoot = true;
    public static int currentPlayer;
    public static int turnPoints = 0;

    public static void startGame(ArrayList<Player> players){
        GameManager.players=players;
        restart();
    }
    public static void restart(){
        MapManager.setMapFromPNG(MapManager.imagename);
        currentPlayer = 0;
        turnPoints = 0;
        Game.panelbottom.setPlayers(players);
        Game.panelontheleft.setCurrentName(getCurrentPlayer().name);
        setBalls(new Ball(0,0),3);
        for(Player p : players){
            p.points = 0;
        }
        Game.panelontheleft.setBalls_left(getCurrentPlayer().balls);
    }
    public static Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    public static void addPoints(int points){
        turnPoints += points;
        Game.panelontheleft.setCurrentPoints(getCurrentPlayer().points+turnPoints);
    }
    private static void giveBalls(Ball ball, int count){
        for (Player p : players){
            p.addBalls(ball,count);
        }
    }
    public static void setBalls(Ball ball, int count){
        for (Player p : players){
            p.setBalls(ball, count);
        }
    }
    public static void addBalls(Ball ball, int count){
        for (Player p : players){
            p.addBalls(ball, count);
        }
    }
    public static void addBallsToCurrent(Ball ball, int count){
        getCurrentPlayer().addBalls(ball, count);
        Game.panelontheleft.setBalls_left(getCurrentPlayer().balls);

    }
    public static void setBallsToCurrent(Ball ball, int count){
        getCurrentPlayer().setBalls(ball, count);
        Game.panelontheleft.setBalls_left(getCurrentPlayer().balls);
    }

    public static void endTurn(int combo){
        boolean end = checkEndGame();
        if(end){

            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                UIManager.goToPage(Pages.END_GAME);
            }).start();

        }
        canShoot = true;
        if(getCurrentPlayer().bestCombo<=combo){
            getCurrentPlayer().bestCombo = combo;
        }
        getCurrentPlayer().points += turnPoints;
        turnPoints = 0;
        do {
            currentPlayer++;
            if(currentPlayer >= players.size()){
                currentPlayer = 0;
            }
        }while (!end &&getCurrentPlayer().balls.isEmpty());

        Game.panelbottom.refresh();
        Game.panelontheleft.setBalls_left(getCurrentPlayer().balls);
        Game.panelontheleft.setCurrentName(getCurrentPlayer().name);

    }

    public static boolean checkEndGame(){
        for(Player p:players){
            if(!p.balls.isEmpty()){
                return false;
            }
        }
        return true;
    }
}