import java.awt.*;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;


public class Drawer {
    private final static int Width = 1900;
    private final static int Height = 1000;
    private final static long FPS = 15;
    private final static long TargetTime = 1_000_000_000 / FPS;//Time for Frame in nanoseconds
    private final Thread thread;
    private Boolean started=true;

    public Drawer() throws IOException {
        JFrame frame = new JFrame("Java 2D Graphics Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (started) {
                    long startTime=System.nanoTime();
                    Server serv = new Server(new Random());
                    DrawingPanel pp = null;
                    try {
                        pp = new DrawingPanel(serv, Width, Height);
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    frame.add(pp);
                    frame.setVisible(true);
                    long delta=System.nanoTime()-startTime;
                    if (delta<TargetTime)sleep((TargetTime-delta)/1_000_000);
                }
            }
        });
        thread.start();
    }
    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}
