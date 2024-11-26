package game;

import ObjectTools.ObjectList;

public class Clock extends Thread {
    private static Disegno d;
    private static Map m;
    public static double deltaTime = 0;
    public static int fpsLimit = 10000;
    private static long exTime = 0;
    public static float timeScale = 0.5f;

    public Clock(Disegno graphic, Map map) {

        super();
        exTime = System.nanoTime();
        d = graphic;
        m = map;
    }

    @Override
    public void run() {
/*
        while (true) {

            long tmp = System.nanoTime();
            deltaTime =  (double)(Math.abs(tmp) - Math.abs(exTime)) / 1000000000;
            deltaTime *= timeScale;
            m.update();

            d.repaint();
            ObjectList.objects.removeAll(ObjectList.deletionQueue); //elimina gli oggetti che vanno eliminati
            ObjectList.deletionQueue.clear(); //svuota la lista degli oggetti da eliminare
            try {
                if (fpsLimit > 0) {
                    Thread.sleep(1000 / fpsLimit);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            exTime = tmp;
        }

 */
        drawFrame();

    }

    public static void drawFrame(){
        long tmp = System.nanoTime();
        deltaTime =  (double)(Math.abs(tmp) - Math.abs(exTime)) / 1000000000;
        deltaTime *= timeScale;
        m.update();

        d.repaint();
        ObjectList.objects.removeAll(ObjectList.deletionQueue); //elimina gli oggetti che vanno eliminati
        ObjectList.deletionQueue.clear(); //svuota la lista degli oggetti da eliminare
        try {
            if (fpsLimit > 0) {
                Thread.sleep(1000 / fpsLimit);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        exTime = tmp;
    }
    //il loop viene fatto dal metodo in repaint(), che chiama di nuovo drawFrame() altrimenti non sarebbe sincrono
    //e creerebbe problemi
}
