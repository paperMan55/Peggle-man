package game;

import UI.pages.Game;
import UI.pages.PlayerSelector;

import java.util.ArrayList;

public class GameManager {
    public static ArrayList<Player> players= new ArrayList<>();

    public static int currentPlayer;
    public static int turnPoints = 0;

    public static void startGame(ArrayList<Player> players){
        GameManager.players=players;
        currentPlayer = 0;
        Game.panelbottom.setPlayer(players.get(currentPlayer).name);
        giveBalls(10);
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

    public static void endTurn(int combo){
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
    }
}