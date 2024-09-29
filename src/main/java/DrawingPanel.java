import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

// bots panel
// fon panel
// wires panel
// resources panel

public class DrawingPanel extends JPanel {
    private final Image botImage;
    private final Server server;

    Color[] colors;

    final double tileSize = 16;
    final int width;
    final int height;

    public DrawingPanel(Server s, int width, int height) throws IOException {
        botImage = ImageIO.read(new File("src/BotTexture.png"));
        server = s;
        this.width = width;
        this.height = height;
        colors = new Color[s.MaxTeams];
        if (s.MaxTeams >= 1) colors[0] = new Color(57, 255, 35);
        if (s.MaxTeams >= 2) colors[1] = new Color(35, 79, 255);
        if (s.MaxTeams >= 3) colors[2] = new Color(255, 35, 35);
    }

    private void drawBot(Graphics2D g, Bot bot) {
        double angle = Math.atan2(bot.dirY, bot.dirX);
//        AffineTransform oldTransform=g.getTransform();
//        AffineTransform newTransform=new AffineTransform();
//        newTransform.rotate(angle,tileSize / 2,tileSize / 2);

//        g.setColor(colors[bot.uid.team]);
        g.rotate(-angle, tileSize * bot.pos.x + tileSize / 2, tileSize * bot.pos.y + tileSize / 2);
        g.drawImage(botImage, (int) tileSize * bot.pos.x, (int) tileSize * bot.pos.y, (int) tileSize, (int) tileSize, this);
//        g.setTransform(oldTransform);
        g.rotate(+angle, tileSize * bot.pos.x + tileSize / 2, tileSize * bot.pos.y + tileSize / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.clearRect(0, 0, width, height);

        for (int t = 0; t < server.MaxTeams; ++t) {
            for (int i = 0; i < server.MaxBots; ++i) {
                if (server.bots[t][i] != null)
                    drawBot(g2d, server.bots[t][i]);
            }
        }


//        g2d.setBackground(new Color(120, 120, 120));
        // Draw an image
    }
}