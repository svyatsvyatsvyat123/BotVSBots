import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphics2D_Demo extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] xValues = {55, 67, 109, 73, 83, 55, 27, 37, 1, 43};
        int[] yValues = {0, 36, 36, 54, 96, 72, 96, 54, 36, 36};

        Graphics2D graphics2D = (Graphics2D) g;
        GeneralPath path = new GeneralPath();

        path.moveTo(xValues[0], yValues[0]);

        for (int i = 1; i < xValues.length; i++) {
            path.lineTo(xValues[i], yValues[i]);
        }

        path.closePath();
        graphics2D.translate(150, 150);
        Random rNumbers = new Random();
        for (int ind = 1; ind <= 1000; ++ind) {
            graphics2D.rotate(Math.PI / (rNumbers.nextInt(20) + 1));
            graphics2D.setColor(new Color(rNumbers.nextInt(256), rNumbers.nextInt(256), rNumbers.nextInt(256), 155));
            graphics2D.fill(path);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Graphics2D Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 10000; ++i) {
            frame.add(new Graphics2D_Demo());
            frame.setBackground(Color.BLACK);
            frame.setSize(1900, 1000);
            frame.setVisible(true);
        }
    }

}
