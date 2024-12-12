package ObjectTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ObjectList { //questa classe non fa altro che tenere tutti gli oggetti della scena
    public static ArrayList<Objecto2> objects = new ArrayList<>();
    private static HashMap<Objecto2,Boolean> modificationQueue = new HashMap<>();
    public static HashMap<Objecto2,Collision> collisions = new HashMap<>();

    public static void addObject(Objecto2 obj){
        modificationQueue.put(obj,true);
    }
    public static void removeObject(Objecto2 obj){
        modificationQueue.put(obj,false);
    }
    public static void updateList(){
        for (Map.Entry<Objecto2,Boolean> obj: modificationQueue.entrySet()){
            if(obj.getValue()){
                objects.add(obj.getKey());
            }else {
                objects.remove(obj.getKey());
            }
        }
    }
}
//oggetto che contiene tutti gli oggetti della mappa
