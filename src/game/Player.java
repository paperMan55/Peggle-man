package game;

public class Player {
    public long points = 0;
    public int bestCombo = 0;
    public int balls = 0;
    public String name="";

    public Player(String name) {
        this.name = name;
    }
}