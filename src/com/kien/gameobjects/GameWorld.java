package com.kien.gameobjects;

import com.kien.effect.CacheDataLoader;
import com.kien.effect.FrameImage;
import com.kien.userinterface.GameFrame;
import com.kien.userinterface.GamePanel;

import java.applet.AudioClip;
import java.awt.*;

public class GameWorld {
    public Megaman megaman;
    public PhysicalMap physicalMap;

    public BackgroundMap backgroundMap;

    public BulletManager bulletManager;

    public ParticularObjectManager particularObjectManager;

    public Camera camera;

    public AudioClip bgMusic;

    public GameWorld() {
        megaman = new Megaman(400, 400, this);
        megaman.setTeamType(ParticularObject.LEAGUE_TEAM);
        physicalMap = new PhysicalMap(0, 0, this);
        backgroundMap = new BackgroundMap(0, 0, this);
        camera = new Camera(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);

        bulletManager = new BulletManager(this);

        particularObjectManager = new ParticularObjectManager(this);
        particularObjectManager.addObject(megaman);

        bgMusic = CacheDataLoader.getInstance().getSound("bgmusic");

        initEnemies();
    }

    private void initEnemies() {
        ParticularObject redeye = new RedEyeDevil(1250, 410, this);
        redeye.setDirection(ParticularObject.LEFT_DIR);
        redeye.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(redeye);
    }

    public void Update() {
        particularObjectManager.UpdateObjects();
        camera.Update();

        bulletManager.UpdateObjects();
    }

    public void Render(Graphics2D g2) {
//        physicalMap.draw(g2);
        backgroundMap.draw(g2);
        particularObjectManager.draw(g2);
        bulletManager.draw(g2);
    }
}
