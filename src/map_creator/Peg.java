package map_creator;

public class Peg{
    /*
    - il colore rosso è 1
    - il colore blue è 2

    * */
    private int[] position;
    private int  color;
    public Peg(int x, int y, int colorcod){
        this.position= new int[]{x, y};
        this.color=colorcod;
    }

    public int getColor() {
        return color;
    }

    public int[] getPosition() {
        return position;
    }
    public int getY(){
        return position[1];
    }
    public int getX(){
        return position[0];
    }
}
