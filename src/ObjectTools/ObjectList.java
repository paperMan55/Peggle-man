package ObjectTools;

import java.util.*;


public class ObjectList { //questa classe non fa altro che tenere tutti gli oggetti della scena
    public static ArrayList<Objecto2> objects = new ArrayList<>();
    private static HashMap<Objecto2,Boolean> modificationQueue = new HashMap<>();
    public static HashMap<Collision,Objecto2> collisions = new HashMap<>();
    private static HashMap<Collision,Objecto2> exCollisions = new HashMap<>();

    private static boolean toClear = false;

    public static void addObject(Objecto2 obj){
        modificationQueue.put(obj,true);
    }
    public static void removeObject(Objecto2 obj){
        modificationQueue.put(obj,false);
    }
    public static void updateList(){
        if(toClear){
            toClear= false;
            immediateClearAll();
            return;
        }
        for (Map.Entry<Objecto2,Boolean> obj: modificationQueue.entrySet()){
            if(obj.getValue()){
                objects.add(obj.getKey());
            }else {
                obj.getKey().onDestroy(false);
                objects.remove(obj.getKey());
            }
        }
        modificationQueue.clear();
    }
    public static void resolveCollisions(){
        for(Collision c:ObjectList.collisions.keySet()){
            Collision isEx = exCollisionsContains(c);
            if(isEx != null){
                c.resolve(Collision.STAY);
                exCollisions.remove(isEx);
            }else{
                c.resolve(Collision.ENTER);
            }
        }
        for (Collision c:ObjectList.exCollisions.keySet()){
            c.resolve(Collision.EXIT);
        }
        exCollisions = (HashMap<Collision, Objecto2>) collisions.clone();
        ObjectList.collisions.clear();

    }
    private static Collision exCollisionsContains(Collision c){
        for(Collision col:ObjectList.exCollisions.keySet()){
            if(col.equals(c)){
                return col;
            }
        }
        return null;
    }
    public static void clearAll(){
        toClear = true;
    }
    public static void immediateClearAll(){
        for(Objecto2 obj: objects){
            obj.onDestroy(true);
        }
        objects = new ArrayList<>();
        modificationQueue = new HashMap<>();
        collisions = new HashMap<>();
        exCollisions = new HashMap<>();
    }

}
//oggetto che contiene tutti gli oggetti della mappa
