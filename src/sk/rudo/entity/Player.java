package sk.rudo.entity;

import sk.rudo.gameService.KeyboardHandler;
import sk.rudo.gameService.Panel;
import sk.rudo.gameService.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    KeyboardHandler keyboardHandler;
    int standCounter = 0;
    int counter = 0;
    public final int screenX;
    public final int screenY;
    public int immortalStatusCounter = 0;
    Font font = new Font("TimesRoman", Font.BOLD, 11);
    Font biggerFont = new Font("TimesRoman", Font.BOLD, 18);

    public Player (Panel panel, KeyboardHandler keyboardHandler) {
        super(panel);
        this.keyboardHandler = keyboardHandler;

        screenX = panel.screenWidth/2 - (panel.size/2);
        screenY = panel.screenHeight/2 - (panel.size/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 28;

        attackArea = new Rectangle();
        attackArea.width = 42;
        attackArea.height = 42;

        setDefaultValues();
        getPlayerImage();
        getPlayerDamageImage();
    }

    public void setDefaultValues () {
        worldX = panel.size * 24;
        worldY = panel.size * 24;
        speed = 4;
        direction = "down";

        maxLife = 6;
        life = maxLife;
        level = 1;
        exp = 0;
        expForNextLevel = 10;
    }

    public void update () {
        attack();
        if (attack == true) {
            spriteCounter++;
            if (spriteCounter <= 10) {
                spriteNum = 1;
            }
            if (spriteCounter > 10 && spriteCounter <= 30) {
                spriteNum = 2;

                int attackWorldX = worldX;
                int attackWorldY = worldY;
                int solidAreaW = solidArea.width;
                int solidAreaH = solidArea.height;

                switch (direction) {
                    case "up" -> worldY -= attackArea.height;
                    case "down" -> worldY += attackArea.height;
                    case "left" -> worldX -= attackArea.width;
                    case "right" -> worldX += attackArea.width;
                }

                solidArea.width = attackArea.width;
                solidArea.height = attackArea.height;
                int monsterIndex = panel.collisionChecker.checkEntity(this, panel.monster);
                damageMonster(monsterIndex);

                worldX = attackWorldX;
                worldY = attackWorldY;
                solidArea.width = solidAreaW;
                solidArea.height = solidAreaH;
            }
            if (spriteCounter > 25) {
                spriteNum = 1;
                spriteCounter = 0;
                attack = false;
            }
        } else if (keyboardHandler.up || keyboardHandler.down || keyboardHandler.left || keyboardHandler.right) {
            if (keyboardHandler.up) {
                direction = "up";
            } else if (keyboardHandler.down) {
                direction = "down";
            } else if (keyboardHandler.left) {
                direction = "left";
            } else if (keyboardHandler.right) {
                direction = "right";
            }

            collisionEntity = false;
            panel.collisionChecker.checkTile(this);

            int monsterIndex = panel.collisionChecker.checkEntity(this, panel.monster);
            hitMonster(monsterIndex);

            if (!collisionEntity) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;
            if(spriteCounter > 10) {
                if (spriteNum == 3 || spriteNum == 4) {
                    spriteNum = 1;
                } else if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            if (standCounter == 5) {
                spriteNum = 3;
                standCounter = 0;
            }
            counter++;
            if (counter >= 30) {
                spriteNum = 4;
                if (counter >= 60) {
                    spriteNum = 3;
                    counter = 0;
                }
            }
        }
        if (immortal) {
            immortalCounter ++;
            if (immortalCounter > 70) {
                immortal = false;
                immortalCounter = 0;
            }
        }
    }

    public void attack () {
        if (panel.player.keyboardHandler.attackKey == true) {
            attack = true;
        }
        panel.player.keyboardHandler.attackKey = false;
    }

    public void damageMonster (int i) {
        if (i != 999) {
            if (panel.monster[i].immortal == false) {
                panel.monster[i].life--;
                panel.monster[i].immortal = true;
                if (panel.monster[i].life <= 0) {
                    panel.monster[i].dying = true;
                    exp += panel.monster[i].exp;
                    nextLevel();
                }
            }
        }
    }

    public void nextLevel () {
        if (exp >= expForNextLevel) {
            level ++;
            expForNextLevel = expForNextLevel+10;
            exp = 0;
        }
    }

    public void hitMonster (int i) {
        if (i != 999) {
            if (immortal == false) {
                life--;
                immortal = true;
            }
            System.out.println("auuuuu");
        }
    }

    public void draw (Graphics2D graphics2D) {
        BufferedImage bufferedImage = null;

        int attackSizeX = screenX;
        int attackSizeY = screenY;

        switch (direction) {
            case "up" -> {
                if (attack == false) {
                    if (spriteNum == 1) {
                        bufferedImage = up1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = up2;
                    }
                    if (spriteNum == 3) {
                        bufferedImage = up3;
                    }
                    if (spriteNum == 4) {
                        bufferedImage = up4;
                    }
                }
                if (attack == true) {
                    attackSizeY = screenY - panel.size;
                    if (spriteNum == 1) {
                        bufferedImage = upAttack1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = upAttack2;
                    }
                }
            }
            case "down" -> {
                if (attack == false) {
                    if (spriteNum == 1) {
                        bufferedImage = down1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = down2;
                    }
                    if (spriteNum == 3) {
                        bufferedImage = down3;
                    }
                    if (spriteNum == 4) {
                        bufferedImage = down4;
                    }
                }
                if (attack == true) {
                    if (spriteNum == 1) {
                        bufferedImage = downAttack1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = downAttack2;
                    }
                }
            }
            case "left" -> {
                if (attack == false) {
                    if (spriteNum == 1) {
                        bufferedImage = left2;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = left1;
                    }
                    if (spriteNum == 3) {
                        bufferedImage = left3;
                    }
                    if (spriteNum == 4) {
                        bufferedImage = left4;
                    }
                }
                if (attack == true) {
                    attackSizeX = screenX - panel.size;
                    if (spriteNum == 1) {
                        bufferedImage = leftAttack1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = leftAttack2;
                    }
                }
            }
            case "right" -> {
                if (attack == false) {
                    if (spriteNum == 1) {
                        bufferedImage = right2;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = right1;
                    }
                    if (spriteNum == 3) {
                        bufferedImage = right3;
                    }
                    if (spriteNum == 4) {
                        bufferedImage = right4;
                    }
                }
                if (attack == true) {
                    if (spriteNum == 1) {
                        bufferedImage = rightAttack1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = rightAttack2;
                    }
                }
            }
        }

        double experience = (double)750/expForNextLevel;
        double expBar = experience*exp;

        graphics2D.setColor(new Color(20,25,20));
        graphics2D.fillRect(8,553,754,12);
        graphics2D.setColor(new Color(102,255,102));
        graphics2D.fillRect(10,555,(int)expBar,8);

        graphics2D.setFont(biggerFont);
        graphics2D.setColor(new Color(240,240,220));
        graphics2D.drawString("Level: " + level, 340, 545);

        graphics2D.setColor(new Color(240,240,220));
        graphics2D.setFont(font);
        graphics2D.drawString("Exp: " +exp + " / " + expForNextLevel, 350, 562);

        if (immortal == true) {
            immortalStatusCounter++;
            if (immortalStatusCounter < 15) {
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
            }
            if (immortalStatusCounter >= 15) {
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                immortalStatusCounter = 0;
            }
        }
        graphics2D.drawImage(bufferedImage, attackSizeX, attackSizeY, null);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

    public void getPlayerImage () {
            up1 = getScale("/player/up1.png", panel.size, panel.size);
            up2 = getScale("/player/up2.png", panel.size, panel.size);
            up3 = getScale("/player/up3.png", panel.size, panel.size);
            up4 = getScale("/player/up4.png", panel.size, panel.size);
            down1 = getScale("/player/down1.png", panel.size, panel.size);
            down2 = getScale("/player/down2.png", panel.size, panel.size);
            down3 = getScale("/player/down3.png", panel.size, panel.size);
            down4 = getScale("/player/down4.png", panel.size, panel.size);
            left1 = getScale("/player/left1.png", panel.size, panel.size);
            left2 = getScale("/player/left2.png", panel.size, panel.size);
            left3 = getScale("/player/left1.png", panel.size, panel.size);
            left4 = getScale("/player/left4.png", panel.size, panel.size);
            right1 = getScale("/player/right1.png", panel.size, panel.size);
            right2 = getScale("/player/right2.png", panel.size, panel.size);
            right3 = getScale("/player/right1.png", panel.size, panel.size);
            right4 = getScale("/player/right4.png", panel.size, panel.size);
    }

    public void getPlayerDamageImage () {
        upAttack1 = getScale("/player/up_attack1.png", panel.size, panel.size*2);
        upAttack2 = getScale("/player/up_attack2.png", panel.size, panel.size*2);
        downAttack1 = getScale("/player/down_attack1.png", panel.size, panel.size*2);
        downAttack2 = getScale("/player/down_attack2.png", panel.size, panel.size*2);
        leftAttack1 = getScale("/player/left_attack1.png", panel.size*2, panel.size);
        leftAttack2 = getScale("/player/left_attack2.png", panel.size*2, panel.size);
        rightAttack1 = getScale("/player/right_attack1.png", panel.size*2, panel.size);
        rightAttack2 = getScale("/player/right_attack2.png", panel.size*2, panel.size);
    }
    public BufferedImage getScale(String image, int width, int height) {
        BufferedImage bufferedImage = null;
        Tools tools = new Tools(panel);
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(image)));
            bufferedImage = tools.images(bufferedImage, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
