package game;

import ObjectTools.ObjectList;
import UI.Disegno;

public class Clock extends Thread {
    private static Disegno d;
    public static boolean flag = true;
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
            if(flag) {
                flag = false;
                drawFrame();
            }
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
        ObjectList.updateList();
        d.repaint();

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
