package game;

import ObjectTools.ObjectList;
import UI.UIManager;
import UI.pages.Game;
import UI.pages.Pages;
import UI.pages.PlayerSelector;
import gamePrefabs.*;

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
        setBalls(new GhostBall(0,0),4);

        Game.panelontheleft.setBalls_left(getCurrentPlayer().balls);
    }
    public static Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    public static void addPoints(int points){
        turnPoints += points;
        Game.panelbottom.setPoints(turnPoints);
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
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                UIManager.goToPage(Pages.END_GAME);
            }).start();

        }
    }
    public static void restart(){
        currentPlayer = 0;
        turnPoints = 0;
        Game.panelbottom.setPlayer(players.get(currentPlayer).name);
        setBalls(new Ball(0,0),3);
        for(Player p : players){
            p.points = 0;
        }
        Game.panelontheleft.setBalls_left(getCurrentPlayer().balls);
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