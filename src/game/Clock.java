package game;

import ObjectTools.ObjectList;

public class Clock extends Thread {
    private static Disegno d;
    public static double deltaTime = 0;
    public static int fpsLimit = 10000;
    private static long exTime = 0;
    public static float timeScale = 1f;

    public Clock(Disegno graphic) {

        super();
        exTime = System.nanoTime();
        d = graphic;
    }

    @Override
    public void run() {

        while (true) {
            drawFrame();
            try {
                if (fpsLimit > 0) {
                    Thread.sleep(1000 / fpsLimit);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }





    }

    public static void drawFrame(){
            long tmp = System.nanoTime();
            deltaTime =  (double)(Math.abs(tmp) - Math.abs(exTime)) / 1000000000;
            deltaTime *= timeScale;
            d.update();

            d.repaint();
            try{
                ObjectList.objects.removeAll(ObjectList.deletionQueue); //elimina gli oggetti che vanno eliminati
                ObjectList.deletionQueue.clear(); //svuota la lista degli oggetti da eliminare
            }catch (Exception e){
                System.out.println(e.getStackTrace());
                System.out.println("--*****! errore !*****--");
            }

            try {
                if (fpsLimit > 0) {
                    Thread.sleep(1000 / fpsLimit);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            exTime = tmp;
        }
        
    
}
