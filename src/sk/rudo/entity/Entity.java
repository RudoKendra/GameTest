package sk.rudo.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import sk.rudo.gameService.Panel;

import javax.imageio.ImageIO;

public class Entity {

    Panel panel;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4;
    public BufferedImage lifeImage, lifeImageHalf, lifeImageBlank;
    public BufferedImage dead, dead2;
    public BufferedImage upAttack1, upAttack2, downAttack1, downAttack2, leftAttack1, leftAttack2, rightAttack1, rightAttack2;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionEntity = false;
    public String name;
    public int maxLife;
    public int life;
    public int level;
    public int exp;
    public int expForNextLevel;
    public int actionCounter = 0;
    public boolean immortal = false;
    public int immortalCounter = 0;
    public boolean attack = false;
    public boolean alive = true;
    public boolean dying = false;
    int dyingCounter = 0;
    public Rectangle attackArea = new Rectangle(0,0,0,0);

    public Entity(Panel panel) {
        this.panel = panel;
    }

    public void setAction() {
    }

    public void update() {
        setAction();
        collisionEntity = false;
        panel.collisionChecker.checkTile(this);
        boolean contactPlayer = panel.collisionChecker.checkPlayer(this);

        if (contactPlayer == true) {
            if (panel.player.immortal == false) {
                panel.player.life--;
                panel.player.immortal = true;
            }
            System.out.println("AU");
        }

        if (!collisionEntity) {
            switch (direction) {
                case "up": worldY -= speed;
                break;
                case "down": worldY += speed;
                break;
                case "left": worldX -= speed;
                break;
                case "right": worldX += speed;
                break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (immortal) {
            immortalCounter ++;
            if (immortalCounter > 40) {
                immortal = false;
                immortalCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage bufferedImage = null;

        int screenX = worldX - panel.player.worldX + panel.player.screenX;
        int screenY = worldY - panel.player.worldY + panel.player.screenY;

        if (worldX + panel.size > panel.player.worldX - panel.player.screenX &&
                worldX - panel.size < panel.player.worldX + panel.player.screenX &&
                worldY + panel.size > panel.player.worldY - panel.player.screenY &&
                worldY - panel.size < panel.player.worldY + panel.player.screenY) {

            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {
                        bufferedImage = up1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = up2;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        bufferedImage = down1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = down2;
                    }
                }
                case "left" -> {
                    if (spriteNum == 1) {
                        bufferedImage = left1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = left2;
                    }
                }
                case "right" -> {
                    if (spriteNum == 1) {
                        bufferedImage = right2;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = right1;
                    }
                }
            }
            if (immortal == true) {
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            }
            if (dying == true) {
                bufferedImage = null;
                solidArea = new Rectangle(0,0,0,0);
                dyingAnimation();
                dyingCounter++;
                if (dyingCounter <= 15) {
                    graphics2D.drawImage(dead, screenX, screenY, panel.size, panel.size, null);
                }
                if (dyingCounter > 15 && dyingCounter <= 30) {
                    graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    graphics2D.drawImage(dead2, screenX, screenY, panel.size, panel.size, null);
                }
                if (dyingCounter > 30) {
                    dying = false;
                    alive = false;
                }
            }
            graphics2D.drawImage(bufferedImage, screenX, screenY, panel.size, panel.size, null);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }
    public void dyingAnimation () {
        try {
            dead = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/dying/BoomOne.png")));
            dead2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/dying/BoomTwo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
