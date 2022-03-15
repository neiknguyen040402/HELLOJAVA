package com.kien.userinterface;

import com.kien.gameobjects.GameWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {   //JPanel là 1 container

    private Thread thread;

    private Boolean isRunning;

    private InputManager inputManager;

    private BufferedImage bufImage;
    private Graphics2D bufG2D;

    public GameWorld gameWorld;


    public GamePanel(){
        gameWorld = new GameWorld();
        inputManager = new InputManager(gameWorld);
        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bufImage, 0, 0, this);
    }

    public void UpdateGame() {
        gameWorld.Update();
    }

    public void RenderGame() {
        if(bufImage == null) {
            bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        }

        if(bufImage != null) {
            bufG2D = (Graphics2D) bufImage.getGraphics();
        }

        if(bufG2D != null) {
            bufG2D.setColor(Color.white);
            //bufG2D.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);

            //draw objects game here
            //megaman.draw(bufG2D);
            gameWorld.Render(bufG2D);

        }
    }

    public void startGame() {
        if (thread == null) {
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {   // game loop: 2 quá trình update game and render game , mục đích tạo tg ngủ sau update và render

        long FPS = 80;
        long period = 1000 * 1000000 / FPS;
        long beginTime;
        long sleepTime;

        beginTime = System.nanoTime();// lấy thòi gian hệ thống hiện tại (trc khi update và render)
        while (isRunning) {

            //update
            //render
            UpdateGame();
            RenderGame();
            repaint();

            long deltaTime = System.nanoTime() - beginTime; // lấy thời gian thực thi update và render
            sleepTime = period - deltaTime;

            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime / 1000000);
                } else {
                    Thread.sleep(period / 2000000);
                }

            } catch (InterruptedException ex) {
            }

            beginTime = System.nanoTime();

        }
    }

    //bộ lắng nghe sự kiện
    @Override
    public void keyTyped(KeyEvent e) {  //callback method

    }

    @Override
    public void keyPressed(KeyEvent e) {    //phím nhấn, xác định ta nhấn phím nào
        inputManager.processKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {     //khi thả phím ra sẽ gọi release
        inputManager.processKeyReleased(e.getKeyCode());
    }
}
