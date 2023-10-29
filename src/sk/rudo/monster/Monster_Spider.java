package sk.rudo.monster;

import sk.rudo.entity.Entity;
import sk.rudo.gameService.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class Monster_Spider extends Entity {

    public Monster_Spider (Panel panel) {
        super(panel);
        name = "Spider";
        speed = 1;
        maxLife = 2;
        life = maxLife;

        exp = 5;

        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 24;
        solidArea.width = 36;
        solidArea.height = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage () {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/spider/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/spider/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/spider/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/spider/down2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/spider/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/spider/right2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/spider/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monster/spider/left2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction () {
        actionCounter++;

        if (actionCounter == 60) {
            Random random = new Random();
            int i = random.nextInt(101) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "right";
            }
            if (i > 75 && i <= 100) {
                direction = "left";
            }
            actionCounter = 0;
        }
    }
}
