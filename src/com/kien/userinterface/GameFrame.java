package com.kien.userinterface;

import com.kien.effect.CacheDataLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class GameFrame extends JFrame {

    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;

    GamePanel gamePanel;

    public GameFrame() {

        Toolkit toolkit = this.getToolkit();          //lấy size màn hình
        Dimension dimension = toolkit.getScreenSize();  // lưu cặp giá trị
        this.setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH, SCREEN_HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gamePanel = new GamePanel();
        add(gamePanel);

        this.addKeyListener(gamePanel);

    }

    public void startGame() {
        gamePanel.startGame();
    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);   //hiển thị frame
        gameFrame.startGame();
    }
}
