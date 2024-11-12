import java.sql.Timestamp;

public class Clock extends Thread {
    private static Disegno d;
    private static StupidBounces m;
    public static double deltaTime = 0;
    public static int fpsLimit = 60;
    private long exTime = 0;
    public static float timeScale = 1f;

    public Clock(Disegno graphic) {

        super();
        exTime = System.nanoTime();
        d = graphic;
        m = new StupidBounces();
    }

    @Override
    public void run() {

        while (true) {

            long tmp = System.nanoTime();
            deltaTime = (double) (Math.abs(tmp) - Math.abs(exTime)) / 1000000000;
            deltaTime *= timeScale;
            m.update();

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
}
