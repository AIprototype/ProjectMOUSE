import game_characters.ZombieMouseCharacter;
import in_game_items.CollectableHalloweenPumpkin;
import in_game_items.InGameItemsBaseClass;
import platform.PlatformBaseClass;
import platform.StandardPlatform;
import platform.UnstablePlatform;
import platform.WallSeparationPlatform;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

import static constants.Constants.*;

public class GameEngine {
    //Platform sprite images
    PApplet pApplet;
    PImage[] standardPlatformImages;
    PImage[] unstablePlatformImages;
    PImage[] wallSeparationPlatformImages;
    PImage[] enemyWalkLeft;
    PImage[] enemyWalkRight;
    PImage[] enemyDeathLeft;
    PImage[] enemyDeathRight;
    ArrayList<PlatformBaseClass> platformArray;
    ArrayList<InGameItemsBaseClass> collectableArray;
    ArrayList<ZombieMouseCharacter> enemyArray;

    public GameEngine(PApplet pApplet) {
        //loading platform sprites
        this.platformArray = new ArrayList<>();
        this.collectableArray = new ArrayList<>();
        this.enemyArray = new ArrayList<>();
        this.standardPlatformImages = new PImage[3];
        this.unstablePlatformImages = new PImage[3];
        this.wallSeparationPlatformImages = new PImage[4];
        this.enemyWalkLeft = new PImage[12];
        this.enemyWalkRight = new PImage[12];
        this.enemyDeathLeft = new PImage[4];
        this.enemyDeathRight = new PImage[4];
        this.pApplet = pApplet;
        for (int i = 0; i < 3; ++i) {
            PImage std_img = pApplet.loadImage("platform" + PApplet.nf(i + 1, 4) + ".png");
            PImage uns_img = pApplet.loadImage("unstable_platform" + PApplet.nf(i + 1, 4) + ".png");
            std_img.resize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
            uns_img.resize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
            standardPlatformImages[i] = std_img;
            unstablePlatformImages[i] = uns_img;
        }
        for (int i = 0; i < 4; ++i) {
            String fileName = "wall_platform" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(PLATFORM_WIDTH, PLATFORM_HEIGHT);
            wallSeparationPlatformImages[i] = img;
        }
        //for enemy walk left
        for (int i = 0; i < 12; ++i) {
            String fileName = "enemy_left_walk/black_rat_walk" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyWalkLeft[i] = img;
        }
        //for enemy walk right
        for (int i = 0; i < 12; ++i) {
            String fileName = "enemy_right_walk/enemy_right_walk" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyWalkRight[i] = img;
        }
        //enemy death left
        for (int i = 0; i < 4; ++i) {
            String fileName = "black_mouse_death_left/black_mouse_death" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyDeathLeft[i] = img;
        }
        //enemy death right
        for (int i = 0; i < 4; ++i) {
            String fileName = "black_mouse_death_right/black_mouse_death" + PApplet.nf(i + 1, 4) + ".png";
            PImage img = pApplet.loadImage(fileName);
            img.resize(ENEMY_WIDTH, ENEMY_HEIGHT);
            enemyDeathRight[i] = img;
        }
    }

    public ArrayList<PlatformBaseClass> createLevelOnePlatforms() {
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                PLATFORM_WIDTH, 17 * PLATFORM_HEIGHT,
                2 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                4 * PLATFORM_WIDTH, 15 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                9 * PLATFORM_WIDTH, 13 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                12 * PLATFORM_WIDTH, 10 * PLATFORM_HEIGHT,
                5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                8 * PLATFORM_WIDTH, 6 * PLATFORM_HEIGHT,
                4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                3 * PLATFORM_WIDTH, 3 * PLATFORM_HEIGHT,
                4 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                20 * PLATFORM_WIDTH, 8 * PLATFORM_HEIGHT,
                5 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                PLATFORM_WIDTH * 28, PLATFORM_HEIGHT * 5,
                "safe"));
        platformArray.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                PLATFORM_WIDTH * 30, PLATFORM_HEIGHT * 17,
                "safe"));
        platformArray.add(new WallSeparationPlatform(wallSeparationPlatformImages, pApplet,
                PLATFORM_WIDTH * 35, PLATFORM_HEIGHT * 14,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                37 * PLATFORM_WIDTH, 10 * PLATFORM_HEIGHT,
                2 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                43 * PLATFORM_WIDTH, 13 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, 2 * PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                48 * PLATFORM_WIDTH, 10 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                44 * PLATFORM_WIDTH, 8 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new UnstablePlatform(unstablePlatformImages, pApplet,
                48 * PLATFORM_WIDTH, 4 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, PLATFORM_HEIGHT,
                "safe"));
        platformArray.add(new StandardPlatform(standardPlatformImages, pApplet,
                43 * PLATFORM_WIDTH, 2 * PLATFORM_HEIGHT,
                3 * PLATFORM_WIDTH, 2 * PLATFORM_HEIGHT,
                "safe"));
        return platformArray;
    }

    public ArrayList<InGameItemsBaseClass> createLevelOneCollectables() {
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, 3 * PLATFORM_WIDTH, 2 * PLATFORM_HEIGHT));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, PLATFORM_WIDTH * 30, PLATFORM_HEIGHT * 16));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, 43 * PLATFORM_WIDTH, PLATFORM_HEIGHT));
        collectableArray.add(new CollectableHalloweenPumpkin(pApplet, PLATFORM_WIDTH * 34, PLATFORM_HEIGHT));
        return collectableArray;
    }

    public ArrayList<ZombieMouseCharacter> createLevelOneEnemies() throws Exception {
        if (platformArray.size() > 0) {
            enemyArray.add(new ZombieMouseCharacter(
                    platformArray.get((int) pApplet.random(platformArray.size())),
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    pApplet,
                    null,
                    enemyWalkRight,
                    enemyWalkLeft, enemyDeathLeft, enemyDeathRight));
            enemyArray.add(new ZombieMouseCharacter(
                    platformArray.get((int) pApplet.random(platformArray.size())),
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    pApplet,
                    null,
                    enemyWalkRight,
                    enemyWalkLeft, enemyDeathLeft, enemyDeathRight));
            enemyArray.add(new ZombieMouseCharacter(
                    platformArray.get((int) pApplet.random(platformArray.size())),
                    ENEMY_WIDTH,
                    ENEMY_HEIGHT,
                    pApplet,
                    null,
                    enemyWalkRight,
                    enemyWalkLeft, enemyDeathLeft, enemyDeathRight));
//            for (PlatformBaseClass platform : platformArray) {
//                enemyArray.add(new ZombieMouseCharacter(
//                        platform,
//                        PLAYER_WIDTH,
//                        PLATFORM_HEIGHT,
//                        pApplet,
//                        null));
//            }
        } else {
            throw new Exception("Platforms not created !!");
        }
        return enemyArray;
    }

    public ArrayList<PlatformBaseClass> getPlatformArray() {
        return platformArray;
    }
}
