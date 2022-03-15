package com.kien.userinterface;

import com.kien.gameobjects.GameWorld;
import com.kien.gameobjects.Megaman;

import java.awt.event.KeyEvent;

public class InputManager { //xử lý cho game
    private GameWorld gameWorld;

    public InputManager(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void processKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:

                break;

            case KeyEvent.VK_DOWN:
                System.out.println("down");
                break;

            case KeyEvent.VK_LEFT:
                gameWorld.megaman.setDirection(gameWorld.megaman.LEFT_DIR);
                gameWorld.megaman.setSpeedX(-5);

                break;

            case KeyEvent.VK_RIGHT:
                gameWorld.megaman.setDirection(gameWorld.megaman.RIGHT_DIR);
                gameWorld.megaman.setSpeedX(5);

                break;

            case KeyEvent.VK_ENTER:
                System.out.println("enter");
                break;

            case KeyEvent.VK_SPACE:
                gameWorld.megaman.jump();
                break;

            case KeyEvent.VK_A:  //phím bắn
                gameWorld.megaman.attack();
                break;
        }
    }

    public void processKeyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                System.out.println("You released up");
                break;

            case KeyEvent.VK_DOWN:
                System.out.println("You released down");
                break;

            case KeyEvent.VK_LEFT:
                if(gameWorld.megaman.getSpeedX() < 0) {
                    gameWorld.megaman.stopRun();
                }
                break;

            case KeyEvent.VK_RIGHT:
                if(gameWorld.megaman.getSpeedX() > 0) {
                    gameWorld.megaman.stopRun();
                }
                break;

            case KeyEvent.VK_ENTER:
                System.out.println("you released enter");
                break;

            case KeyEvent.VK_SPACE:
                System.out.println("You released space");
                break;

            case KeyEvent.VK_A:
                System.out.println("You released A");
                break;
        }
    }

}
