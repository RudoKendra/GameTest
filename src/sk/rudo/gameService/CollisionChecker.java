package sk.rudo.gameService;

import sk.rudo.entity.Entity;

public class CollisionChecker {

    Panel panel;

    public CollisionChecker (Panel panel) {
        this.panel = panel;
    }

    public void checkTile (Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/panel.size;
        int entityRightCol = entityRightWorldX/panel.size;
        int entityTopRow = entityTopWorldY/panel.size;
        int entityBottomRow = entityBottomWorldY/ panel.size;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / panel.size;
                tileNum1 = panel.tileManager.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTile[entityRightCol][entityTopRow];
                if (panel.tileManager.tile[tileNum1].collision || panel.tileManager.tile[tileNum2].collision) {
                    entity.collisionEntity = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / panel.size;
                tileNum1 = panel.tileManager.mapTile[entityLeftCol][entityBottomRow];
                tileNum2 = panel.tileManager.mapTile[entityRightCol][entityBottomRow];
                if (panel.tileManager.tile[tileNum1].collision || panel.tileManager.tile[tileNum2].collision) {
                    entity.collisionEntity = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / panel.size;
                tileNum1 = panel.tileManager.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTile[entityLeftCol][entityBottomRow];
                if (panel.tileManager.tile[tileNum1].collision || panel.tileManager.tile[tileNum2].collision) {
                    entity.collisionEntity = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / panel.size;
                tileNum1 = panel.tileManager.mapTile[entityRightCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTile[entityRightCol][entityBottomRow];
                if (panel.tileManager.tile[tileNum1].collision || panel.tileManager.tile[tileNum2].collision) {
                    entity.collisionEntity = true;
                }
            }
        }
    }

    public int checkEntity (Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {

            if (target[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionEntity = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionEntity = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionEntity = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionEntity = true;
                            index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer (Entity entity) {

        boolean contact = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        panel.player.solidArea.x = panel.player.worldX + panel.player.solidArea.x;
        panel.player.solidArea.y = panel.player.worldY + panel.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(panel.player.solidArea)) {
                    entity.collisionEntity = true;
                    contact = true;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(panel.player.solidArea)) {
                    entity.collisionEntity = true;
                    contact = true;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(panel.player.solidArea)) {
                    entity.collisionEntity = true;
                    contact = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(panel.player.solidArea)) {
                    entity.collisionEntity = true;
                    contact = true;
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        panel.player.solidArea.x = panel.player.solidAreaDefaultX;
        panel.player.solidArea.y = panel.player.solidAreaDefaultY;

        return contact;
    }
}
