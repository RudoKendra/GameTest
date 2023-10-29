package sk.rudo.gameService;

import sk.rudo.entity.Entity;
import sk.rudo.object.Object_Life;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tools {

    Panel panel;
    Graphics2D graphics2D;
    BufferedImage heartFull, heartHalf, heartBlank;

    public Tools (Panel panel) {
        this.panel = panel;

        Entity life = new Object_Life(panel);
        heartFull = life.lifeImage;
        heartHalf = life.lifeImageHalf;
        heartBlank = life.lifeImageBlank;
    }

    public void draw (Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        drawLife();
    }

    public void drawLife () {

        int x = panel.size/2;
        int y = panel.size * 10;
        int i = 0;

        while (i < panel.player.maxLife/2) {
            graphics2D.drawImage(heartBlank, x, y, panel.size, panel.size, null);
            i++;
            x += panel.size;
        }

        x = panel.size/2;
        y = panel.size * 10;
        i = 0;

        while (i < panel.player.life) {
            graphics2D.drawImage(heartHalf, x, y, panel.size, panel.size, null);
            i++;
            if (i < panel.player.life) {
                graphics2D.drawImage(heartFull, x, y, panel.size, panel.size, null);
            }
            i++;
            x += panel.size;
        }
    }

    public BufferedImage images (BufferedImage bufferedImage, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, bufferedImage.getType());
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, width, height, null);
        graphics2D.dispose();
        return image;
    }
}
