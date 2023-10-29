package sk.rudo.main;

import sk.rudo.gameService.Panel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setTitle("2D Game");
        jFrame.setLocationRelativeTo(null);

        Panel panel = new Panel();
        jFrame.add(panel);

        panel.setupGame();
        panel.gameThread();

        jFrame.pack();
        jFrame.setVisible(true);
    }
}
