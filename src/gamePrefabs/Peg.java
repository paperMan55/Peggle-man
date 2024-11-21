package gamePrefabs;

import ObjectTools.Circle2;
import ObjectTools.Objecto2;

public class Peg extends Circle2 {
    /*
    - il colore rosso è 1
    - il colore blue è 2

    * */
    //private int[] position;
    private int  colorcod;
    final static float SIZE=20;


    public Peg(float x, float y, int colorcod){
        super(x, y, SIZE);
        //this.position= new int[]{x, y};
        this.colorcod=colorcod;
    }

    public int getColor() {
        return colorcod;
    }
    @Override
    public void onCollisionEnter(Objecto2 o){
        destroy();
    }

}

