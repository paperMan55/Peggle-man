package game;

import gamePrefabs.Ball;

import java.util.ArrayList;

public class Player {
    public long points = 0;
    public int bestCombo = 0;
    public ArrayList<Ball> balls = new ArrayList<>();
    public String name="";

    public Player(String name) {
        this.name = name;
    }

    public void addBalls(Ball ball, int count){
        for (int i = 0; i < count; i++) {
            balls.add((Ball)ball.getCopy());
        }
    }
    public void setBalls(Ball ball, int count){
        balls.clear();
        addBalls(ball,count);
    }
}