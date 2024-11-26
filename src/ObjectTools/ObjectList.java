package ObjectTools;

import java.util.ArrayList;
import java.util.HashMap;


public class ObjectList { //questa classe non fa altro che tenere tutti gli oggetti della scena
    public static ArrayList<Objecto2> objects = new ArrayList<>();
    public static ArrayList<Objecto2> deletionQueue = new ArrayList<>();
    public static HashMap<Objecto2,Collision> collisions = new HashMap<>();
}
//oggetto che contiene tutti gli oggetti della mappa
