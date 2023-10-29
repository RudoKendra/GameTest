package sk.rudo.gameService;

import sk.rudo.entity.Entity;
import sk.rudo.entity.Player;
import sk.rudo.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel implements Runnable{

    final int originalSize = 16;
    final int scale = 3;
    public final int size = originalSize * scale;
    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = size * maxScreenColumn;
    public final int screenHeight = size * maxScreenRow;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;
    Thread thread;
    KeyboardHandler keyboardHandler = new KeyboardHandler();
    public Player player = new Player(this, keyboardHandler);
    public Entity monster[] = new Entity[15];
    ArrayList<Entity> entities = new ArrayList<>();
    TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public CreateEntity createEntity = new CreateEntity(this);
    public Tools tools = new Tools(this);


    public Panel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);
    }

    public void setupGame () {
        createEntity.setMonster();
    }

    public void gameThread () {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long time = System.nanoTime();
        long currentTime;

        while (thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - time) / drawInterval;
            time = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update () {
        player.update();

        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) {
                if (monster[i].alive == true && monster[i].dying == false) {
                    monster[i].update();
                }
                if (monster[i].alive == false) {
                    monster[i] = null;
                }
            }
        }
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;

        tileManager.draw(graphics2D);

        player.draw(graphics2D);
        tools.draw(graphics2D);

        entities.add(player);

        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) {
                entities.add(monster[i]);
            }
        }

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).draw(graphics2D);
        }

        entities.clear();

        graphics2D.dispose();
    }
}
