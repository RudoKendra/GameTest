package sk.rudo.tile;

import sk.rudo.gameService.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    Panel panel;
    public Tile[] tile;
    public int[][] mapTile;

    public TileManager (Panel panel) {
        this.panel = panel;
        tile = new Tile[100];
        mapTile = new int[panel.maxWorldCol][panel.maxWorldRow];
        getTileImage();
        loadMap("/maps/world_map_start.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass1.png")));

            tile[2] = new Tile();
            tile[2].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass2.png")));

            tile[3] = new Tile();
            tile[3].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water1.png")));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water2.png")));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/edge_up.png")));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/edge_up_left.png")));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/edge_up_right.png")));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/edge_left.png")));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/edge_right.png")));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/edge_down_left.png")));
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/edge_down_right.png")));
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/edge_down.png")));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/dirt.png")));

            tile[14] = new Tile();
            tile[14].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water_edge_up.png")));
            tile[14].collision = true;

            tile[15] = new Tile();
            tile[15].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water_edge_up_left.png")));
            tile[15].collision = true;

            tile[16] = new Tile();
            tile[16].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water_edge_up_right.png")));
            tile[16].collision = true;

            tile[17] = new Tile();
            tile[17].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water_edge_left.png")));
            tile[17].collision = true;

            tile[18] = new Tile();
            tile[18].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water_edge_right.png")));
            tile[18].collision = true;

            tile[19] = new Tile();
            tile[19].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water_edge_down_left.png")));
            tile[19].collision = true;

            tile[20] = new Tile();
            tile[20].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water_edge_down_right.png")));
            tile[20].collision = true;

            tile[21] = new Tile();
            tile[21].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water_edge_down.png")));
            tile[21].collision = true;

            tile[22] = new Tile();
            tile[22].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/house_left1.png")));
            tile[22].collision = true;

            tile[23] = new Tile();
            tile[23].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/house_left2.png")));
            tile[23].collision = true;

            tile[24] = new Tile();
            tile[24].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/house_right1.png")));
            tile[24].collision = true;

            tile[25] = new Tile();
            tile[25].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/house_right2.png")));
            tile[25].collision = true;

            tile[26] = new Tile();
            tile[26].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/haunted_house1.png")));
            tile[26].collision = true;

            tile[27] = new Tile();
            tile[27].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/haunted_house2.png")));
            tile[27].collision = true;

            tile[28] = new Tile();
            tile[28].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/haunted_house3.png")));
            tile[28].collision = true;

            tile[29] = new Tile();
            tile[29].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/haunted_house4.png")));
            tile[29].collision = true;

            tile[30] = new Tile();
            tile[30].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/haunted_house5.png")));
            tile[30].collision = true;

            tile[31] = new Tile();
            tile[31].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/haunted_house6.png")));
            tile[31].collision = true;

            tile[32] = new Tile();
            tile[32].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/haunted_house7.png")));
            tile[32].collision = true;

            tile[33] = new Tile();
            tile[33].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/haunted_house8.png")));
            tile[33].collision = true;

            tile[34] = new Tile();
            tile[34].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/haunted_house9.png")));
            tile[34].collision = true;

            tile[35] = new Tile();
            tile[35].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/fance1.png")));
            tile[35].collision = true;

            tile[36] = new Tile();
            tile[36].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/fance2.png")));
            tile[36].collision = true;

            tile[37] = new Tile();
            tile[37].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/fance3.png")));
            tile[37].collision = true;

            tile[38] = new Tile();
            tile[38].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/fance4.png")));
            tile[38].collision = true;

            tile[39] = new Tile();
            tile[39].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass3.png")));

            tile[40] = new Tile();
            tile[40].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/fance5.png")));
            tile[40].collision = true;

            tile[41] = new Tile();
            tile[41].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/fance6.png")));
            tile[41].collision = true;

            tile[42] = new Tile();
            tile[42].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/fance7.png")));
            tile[42].collision = true;

            tile[43] = new Tile();
            tile[43].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grave1.png")));

            tile[44] = new Tile();
            tile[44].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grave2.png")));

            tile[45] = new Tile();
            tile[45].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/dirt2.png")));

            tile[46] = new Tile();
            tile[46].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/stairs.png")));

            tile[47] = new Tile();
            tile[47].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rock_down.png")));
            tile[47].collision = true;

            tile[48] = new Tile();
            tile[48].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rock_down_left.png")));
            tile[48].collision = true;

            tile[49] = new Tile();
            tile[49].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rock_down_right.png")));
            tile[49].collision = true;

            tile[50] = new Tile();
            tile[50].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rock_left.png")));
            tile[50].collision = true;

            tile[51] = new Tile();
            tile[51].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rock_right.png")));
            tile[51].collision = true;

            tile[52] = new Tile();
            tile[52].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rock_floor.png")));

            tile[53] = new Tile();
            tile[53].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/stairs2.png")));

            tile[54] = new Tile();
            tile[54].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rock_up.png")));
            tile[54].collision = true;

            tile[55] = new Tile();
            tile[55].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rock_up_left.png")));
            tile[55].collision = true;

            tile[56] = new Tile();
            tile[56].bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/rock_up_right.png")));
            tile[56].collision = true;

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw (Graphics2D graphics2D) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < panel.maxWorldCol && worldRow < panel.maxWorldRow) {
            int tileNum = mapTile[worldCol][worldRow];

            int worldX = worldCol * panel.size;
            int worldY = worldRow * panel.size;
            int screenX = worldX - panel.player.worldX + panel.player.screenX;
            int screenY = worldY - panel.player.worldY + panel.player.screenY;

            if (worldX + panel.size > panel.player.worldX - panel.player.screenX && worldX - panel.size < panel.player.worldX + panel.player.screenX && worldY + panel.size > panel.player.worldY - panel.player.screenY && worldY - panel.size < panel.player.worldY + panel.player.screenY) {
                graphics2D.drawImage(tile[tileNum].bufferedImage, screenX, screenY, panel.size, panel.size, null);
            }
            worldCol++;

            if (worldCol == panel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
    public void loadMap (String mapFile) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

            int col = 0;
            int row = 0;

            while (col < panel.maxWorldCol && row < panel.maxWorldRow) {
                String line = bufferedReader.readLine();
                while (col < panel.maxWorldCol) {
                    String[] number = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTile[col][row] = num;
                    col++;
                }
                if (col == panel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
