package sk.rudo.gameService;

import sk.rudo.monster.Monster_Skeleton;
import sk.rudo.monster.Monster_Snake;
import sk.rudo.monster.Monster_Spider;

public class CreateEntity {

    Panel panel;

    public CreateEntity (Panel panel) {
        this.panel = panel;
    }

    public void setMonster () {
        panel.monster[0] = new Monster_Snake(panel);
        panel.monster[0].worldX = panel.size * 9;
        panel.monster[0].worldY = panel.size * 40;

        panel.monster[1] = new Monster_Snake(panel);
        panel.monster[1].worldX = panel.size * 11;
        panel.monster[1].worldY = panel.size * 41;

        panel.monster[2] = new Monster_Snake(panel);
        panel.monster[2].worldX = panel.size * 12;
        panel.monster[2].worldY = panel.size * 42;

        panel.monster[3] = new Monster_Skeleton(panel);
        panel.monster[3].worldX = panel.size * 15;
        panel.monster[3].worldY = panel.size * 9;

        panel.monster[4] = new Monster_Skeleton(panel);
        panel.monster[4].worldX = panel.size * 16;
        panel.monster[4].worldY = panel.size * 9;

        panel.monster[5] = new Monster_Skeleton(panel);
        panel.monster[5].worldX = panel.size * 14;
        panel.monster[5].worldY = panel.size * 9;

        panel.monster[6] = new Monster_Spider(panel);
        panel.monster[6].worldX = panel.size * 28;
        panel.monster[6].worldY = panel.size * 8;

        panel.monster[7] = new Monster_Spider(panel);
        panel.monster[7].worldX = panel.size * 30;
        panel.monster[7].worldY = panel.size * 10;

        panel.monster[8] = new Monster_Spider(panel);
        panel.monster[8].worldX = panel.size * 29;
        panel.monster[8].worldY = panel.size * 13;
    }
}
